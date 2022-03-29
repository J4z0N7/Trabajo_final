package Dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import modelos.ProductosBean;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.jdbc.core.JdbcTemplate;
import static sun.font.CreatedFontTracker.MAX_FILE_SIZE;

/**
 *
 * @author JEIZZON-PC
 */
public class ProductoDao {
    JdbcTemplate jdbctemplate;
    ConectarDb con = new ConectarDb();
    private JdbcTemplate jdbcTemplate;
    private int MEMORY_THRESHOLD;
    private long MAX_REQUEST_SIZE;
    private String UPLOAD_DIRECTORY;
    
    public int consultarCtd_Producto(){
        int Pro1=0;
        this.jdbcTemplate = new JdbcTemplate(con.conectar());
        String sql = "select * from productos;";
        Pro1=jdbcTemplate.queryForObject(sql, Integer.class);
        Pro1=this.jdbcTemplate.queryForObject(sql, Integer.class);
        return Pro1;
    }
    public void borrarImagen(String foto, String deletePath, int id){
        final String DELETE_DIRECTORY="..\\..\\web\\";
        this.jdbcTemplate = new JdbcTemplate(con.conectar());
        String deleteFile = deletePath + DELETE_DIRECTORY + foto;
            System.out.println(deleteFile);
        File borrar = new File(deleteFile);
        if (borrar.delete()){
            String sql = "delete from productos where id_producto = ?";
            jdbcTemplate.update(sql, id);
        } else {
            System.out.println("no se puede borrar la imagen...");
        }   
    }
    
    public void borrarImagenActualizada(String foto, String deletePath) {
        final String DELETE_DIRECTORY = "..\\..\\web\\";
        String deleteFile= deletePath + DELETE_DIRECTORY + foto ;
        File borrar = new File(deleteFile);
        if (borrar.delete()) {
            System.out.println("borrado");
        } 
          else {
            System.out.println("No se logro borrar");
            
        }
    }
    
    public void actUsuarioSinFoto(ProductosBean produc, List lista){
        this.jdbcTemplate = new JdbcTemplate(con.conectar());
            ArrayList<String> lista2 = new ArrayList<>();
            for (int i = 0; i<lista.size(); i++){
                FileItem fileItem = (FileItem) lista.get(i);
                lista2.add(fileItem.getString());
            }
            produc.setDescripcion(lista2.get(0));
            produc.setPrecio(Integer.parseInt(lista2.get(1)));
//-----------------------------------------------------------------------------            
            String sql = "update productos set"
                + "descripcion = ?,"
                + "precio = ?,"
                + "where id_producto = ?";
        jdbcTemplate.update(
                sql, produc.getDescripcion(), produc.getPrecio(), produc.getId_producto()
        );
    }
//----------------------------------------------------------------------------------------------------------------        
    
    public void actUsuarioConFoto (ProductosBean produc, boolean isMultipart, HttpServletRequest request, List items){
        this.jdbcTemplate = new JdbcTemplate(con.conectar());
        
//----------------------------------------------------------------------------------------------------------------        
        
        ArrayList<String> lista5 = new ArrayList<>();
        if (isMultipart){
            DiskFileItemFactory file = new DiskFileItemFactory();
            file.setSizeThreshold(MEMORY_THRESHOLD);
            file.setRepository(new File(System.getProperty("java.io.tmpdir")));
            ServletFileUpload fileUpload = new ServletFileUpload(file);
            fileUpload.setFileSizeMax(MAX_FILE_SIZE);
            fileUpload.setSizeMax(MAX_REQUEST_SIZE);
            String uploadPath = request.getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            for (int i = 0; i < items.size(); i++){
                FileItem fileItem = (FileItem) items.get(i);
                if(!fileItem.isFormField()){
                    String fileName = new File (fileItem.getName()).getName();
                    String filePath = uploadPath + File.separator + "Nro-" + lista5.get(0)+"-"+fileName;
                    File uploadFile = new File(filePath);
                    
                    String nameFile = ("imagenes/fotos/" + "Nro-"+lista5.get(0)+"-"+fileName);
                    try{
                    uploadFile.delete();
                    fileItem.write(uploadFile);
                    produc.setFoto_producto(nameFile);
                    } catch (Exception e) {
                        System.out.print("escritura ..." + e.getMessage());
                    }
                } else {
                    lista5.add(fileItem.getString());
                }
            }
            produc.setDescripcion(lista5.get(0));
            produc.setPrecio(Integer.parseInt(lista5.get(1)));        
        }
        String sql = "update productos set"
                + "descripcion = ?,"
                + "precio = ?,"
                + "foto_producto = ?,"
                + "where id_producto = ?";
        jdbcTemplate.update(sql, produc.getDescripcion(), produc.getPrecio(), produc.getFoto_producto(), produc.getId_producto());
    }
    public void actUsuarioSinFoto(ProductosBean produc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
