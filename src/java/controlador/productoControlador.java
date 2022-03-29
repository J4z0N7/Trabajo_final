package controlador;

import Dao.ConectarDb;
import Dao.ProductoDao;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import modelos.ProductosBean;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author JEIZZON-PC
 */

@Controller

public class productoControlador {
        private JdbcTemplate jdbcTemplate;
        
    public productoControlador (){
          ConectarDb con = new ConectarDb();
        this.jdbcTemplate = new JdbcTemplate(con.conectar());
    }
    
//    @RequestMapping(value = "formProducto.htm", method = RequestMethod.GET)
//    public ModelAndView form() {
//        ProductosBean producto = new ProductosBean();
//        return new ModelAndView("vista/formProducto", "producto", new ProductosBean());
//    }
//    
//    @RequestMapping(value="formProducto.htm", method = RequestMethod.POST)
//    public ModelAndView form(
//            @ModelAttribute("producto") ProductosBean produc3,
//            BindingResult result,
//            SessionStatus status
//    ){
//        ModelAndView mav = new ModelAndView();
//        mav.setViewName("vista/listarProducto");
//        mav.addObject("id_producto", produc3.getId_producto());
//        mav.addObject("descripcion", produc3.getDescripcion());
//        mav.addObject("precio", produc3.getPrecio());
//        mav.addObject("foto_producto", produc3.getFoto_producto());
//        return mav;  
//    } 
    
     @RequestMapping("listarProducto.htm")
    public ModelAndView listarProducto() {
        String sql = "select * from productos";
        ModelAndView mav = new ModelAndView();
        ProductosBean producto = new ProductosBean();
        List datos = this.jdbcTemplate.queryForList(sql);
        mav.addObject("producto", datos);
        mav.setViewName("vista/listarProducto");
        return mav;
    }

    @RequestMapping(value = "agregarProducto.htm", method = RequestMethod.GET)
    public ModelAndView agregarProducto() {

        ModelAndView mav = new ModelAndView();
        mav.addObject("producto", new ProductosBean());
        mav.setViewName("vista/agregarProducto");
        return mav;
    }

    private static final String UPLOAD_DIRECTORY = "..\\..\\web\\imagenes\\fotos";
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50;
    
    @RequestMapping(value = "agregarProducto.htm", method = RequestMethod.POST)
    public ModelAndView agregarProducto(HttpServletRequest request, ProductosBean produc2) {
        ModelAndView mav = new ModelAndView();
        String uploadFilePath = request.getSession().getServletContext().getRealPath("imagenes/fotos/");
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        ArrayList<String> listados = new ArrayList<>();
        if (isMultipart){
            DiskFileItemFactory file = new DiskFileItemFactory();
            file.setSizeThreshold(MEMORY_THRESHOLD);
            file.setRepository(new File(System.getProperty("java.io.tmpdir")));
            ServletFileUpload fileUpload = new ServletFileUpload(file);
            fileUpload.setFileSizeMax(MAX_FILE_SIZE);
            fileUpload.setSizeMax(MAX_REQUEST_SIZE);
            String uploadPath = request.getServletContext().getRealPath("")+File.separator+UPLOAD_DIRECTORY;
            File uploadDir = new File (uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            
                List<FileItem> items = null;
                try{
                    items = fileUpload.parseRequest(request);
                } catch(FileUploadException ex){
                System.out.print("carga..." + ex.getMessage());
                }
                for (int i=0; i<items.size(); i++){
                    FileItem fileItem = (FileItem) items.get(i);
                    if(!fileItem.isFormField()){
                        String fileName = new File (fileItem.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File uploadFile = new File (filePath);
                        
                            String nameFile=("imagenes/fotos/"+ fileName);

                        //File f = new File(fileItem.getName());
                        //String nameFile = ("imagenes/fotos/"+ f.getName());
                        //File uploadFile = new File(uploadFilePath, f.getName());
                    
                            try {
                                fileItem.write(uploadFile);
                                produc2.setFoto_producto(nameFile);
                            } catch (Exception e){
                                System.out.printf("escritura..." + e.getMessage());
                            }                
                } else {
                    listados.add(fileItem.getString());
                }
            }
            produc2.setDescripcion(listados.get(0));
            produc2.setPrecio(Integer.parseInt(listados.get(1)));
        }
        
        
        String sql = "insert into productos(descripcion, precio, foto_producto) values(?,?,?)";
        jdbcTemplate.update(sql, produc2.getDescripcion(), produc2.getPrecio(), produc2.getFoto_producto());
        mav.setViewName("redirect:/listarProducto.htm");
        return mav;
    }
    
    //Borrar fila de la consulta
    @RequestMapping("deleteProducto.htm")
    public ModelAndView deleteProducto(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        ProductoDao produc = new ProductoDao();
        int id = Integer.parseInt(req.getParameter("id_producto"));
        String deletePath = req.getServletContext().getRealPath("") + File.separator;
        String foto = req.getParameter("foto_producto");
        produc.borrarImagen(foto,deletePath, id);
        mav.setViewName("redirect:/listarProducto.htm");
        return mav;
    }
//Actualizar datos de un producto

    @RequestMapping(value = "updateProducto.htm", method = RequestMethod.GET)
    public ModelAndView updateProducto(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        int id = Integer.parseInt(req.getParameter("id_producto"));
        String sql = "select * from productos where id_producto = ?";
        //List cli = this.jdbcTemplate.queryForList(sql, id);
        ProductosBean produc = consultaProductoxId(id);
        mav.addObject("producto", produc);
        mav.setViewName("vista/updateProducto");
        return mav;
    }

//Metodo Conversor
    public ProductosBean consultaProductoxId(int id) {
        ProductosBean producto = new ProductosBean();
        String sql = "select * from productos where id_producto = " + id;
        return (ProductosBean) this.jdbcTemplate.query(sql, new ResultSetExtractor<ProductosBean>() {
            public ProductosBean extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    producto.setDescripcion(rs.getString("descripcion"));
                    producto.setPrecio(rs.getInt("precio"));                    
                }
                return producto;                
            }

        });
    }

//Actualizar el producto
    /**
     * 
     * @param produc
     * @return Este metodo ingresa los datos actualizados del producto dentro de la BD
     */
    @RequestMapping(value = "updateProducto.htm", method = RequestMethod.POST)
    public ModelAndView updateProducto(ProductosBean produc, HttpServletRequest req){
    ModelAndView mav = new ModelAndView();
    ProductoDao produc1 = new ProductoDao();
    ArrayList<String> list4 = new ArrayList<>();
    boolean isMultipart = ServletFileUpload.isMultipartContent(req);
    DiskFileItemFactory file = new DiskFileItemFactory();
    ServletFileUpload fileUpload = new ServletFileUpload(file);
    List <FileItem> items = null;
    try {
        items=fileUpload.parseRequest(req);
        for (int i = 0; i <items.size(); i++){
            FileItem fileItem = (FileItem) items.get(i);
            list4.add(fileItem.getString());
    }
    } catch (FileUploadException ex){
        System.out.print("Error en la carga de la imagen productoControlador/updateProducto..." + ex.getMessage());
    }
    if (list4.get(3).isEmpty() || list4.get(3).equals("") || list4.get(3).equals(null)){
        produc1.actUsuarioSinFoto(produc, items);
    } else {
        produc1.actUsuarioConFoto(produc, isMultipart, req, items);
    }
    mav.setViewName("redirect://listarProducto.htm");
    return mav;
    }
}

