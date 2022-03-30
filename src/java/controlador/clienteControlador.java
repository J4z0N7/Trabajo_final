package controlador;

import Dao.ConectarDb;
import Dao.VentaDao;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import modelos.ClienteBean;
import modelos.PersonaBean;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class clienteControlador {

    private PersonaValidation PersonaValidar;
    private JdbcTemplate jdbcTemplate;
//    private List lista;

    public clienteControlador() {
        this.PersonaValidar = new PersonaValidation();
        ConectarDb con = new ConectarDb();
        this.jdbcTemplate = new JdbcTemplate(con.conectar());
    }

    @RequestMapping("listarJstl.htm")
    public ModelAndView listarPersona() {
        String sql = "select * from clientes";
        ModelAndView mav = new ModelAndView();
        PersonaBean per = new PersonaBean();
        List datos = this.jdbcTemplate.queryForList(sql);
        mav.addObject("persona", datos);
        mav.setViewName("vista/listarJstl");
        return mav;
    }

    @RequestMapping(value = "agregarCliente.htm", method = RequestMethod.GET)
    public ModelAndView agregarCliente() {

        ModelAndView mav = new ModelAndView();
        mav.addObject("cliente", new ClienteBean());
        mav.setViewName("vista/agregarCliente");
        return mav;
    }

    private static final String UPLOAD_DIRECTORY = "..\\..\\web\\imagenes\\fotos";
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50;
    
    @RequestMapping(value = "agregarCliente.htm", method = RequestMethod.POST)
    public ModelAndView agregarCliente(HttpServletRequest request, ClienteBean cli) {
        //ClienteBean cli = new ClienteBean();
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
                                cli.setFoto(nameFile);
                            } catch (Exception e){
                                System.out.printf("escritura..." + e.getMessage());
                            }                
                } else {
                    listados.add(fileItem.getString());
                }
            }
            cli.setNombre(listados.get(0));
            cli.setDireccion(listados.get(1));
            cli.setTelefono(Integer.parseInt(listados.get(2)));
            cli.setCiudad(listados.get(3));
        }
        
        
        String sql = "insert into clientes(nombre,direccion, "
                + "telefono,ciudad,foto) values(?,?,?,?,?)";
        jdbcTemplate.update(sql, cli.getNombre(), cli.getDireccion(), cli.getTelefono(), cli.getCiudad(), cli.getFoto());
        mav.setViewName("redirect:/listarJstl.htm");
        return mav;
    }
//Borrar fila de la consulta
    @RequestMapping("deleteCliente.htm")
    public ModelAndView deleteCliente(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        VentaDao cliB = new VentaDao();
        int id = Integer.parseInt(req.getParameter("id_cliente"));
        String deletePath = req.getServletContext().getRealPath("") + File.separator;
        String foto = req.getParameter("foto");
        System.out.println(deletePath + " ------ " + foto);
        cliB.borrarImagen(foto,deletePath, id);
        mav.setViewName("redirect:/listarJstl.htm");
        return mav;
    }
//Actualizar datos de un cliente

    @RequestMapping(value = "updateCliente.htm", method = RequestMethod.GET)
    public ModelAndView updateCliente(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        int id = Integer.parseInt(req.getParameter("id_cliente"));
        String sql = "select * from clientes where id_cliente = ?";
        //List cli = this.jdbcTemplate.queryForList(sql, id);
        ClienteBean cli = consultaClientexId(id);
        mav.addObject("cliente", cli);
        mav.setViewName("vista/updateCliente");
        return mav;
    }

//Metodo Conversor
    public ClienteBean consultaClientexId(int id) {
        ClienteBean cliente = new ClienteBean();
        String sql = "select * from clientes where id_cliente = " + id;
        return (ClienteBean) this.jdbcTemplate.query(sql, new ResultSetExtractor<ClienteBean>() {
            public ClienteBean extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setDireccion(rs.getString("direccion"));
                    cliente.setTelefono(rs.getInt("telefono"));
                    cliente.setCiudad(rs.getString("ciudad"));
                }
                return cliente;                
            }

        });
    }

//Actualizar el cliente
    /**
     * 
     * @param cli
     * @param req
     * @return Este metodo ingresa los datos actualizados del cliente dentro de la BD
     */
    @RequestMapping(value = "updateCliente.htm", method = RequestMethod.POST)
    public ModelAndView updateCliente(ClienteBean cli, HttpServletRequest req){
    ModelAndView mav = new ModelAndView();
    VentaDao vent4 = new VentaDao();
    ArrayList<String> list4 = new ArrayList<>();
    boolean isMultipart = ServletFileUpload.isMultipartContent(req);
    DiskFileItemFactory file = new DiskFileItemFactory();
    ServletFileUpload fileUpload = new ServletFileUpload(file);
    List <FileItem> items = null;
    System.out.print(list4);
    try {
        items=fileUpload.parseRequest(req);
        for (int i = 0; i <items.size(); i++){
            FileItem fileItem = (FileItem) items.get(i);
            list4.add(fileItem.getString());
    }
    } catch (FileUploadException ex){
        System.out.print("Error en la carga de la imagen clienteControlador/updateCliente..." + ex.getMessage());
    }
    if (list4.get(4).isEmpty() || list4.get(4).equals("") || list4.get(4).equals(null)){
        vent4.actUsuarioSinFoto(cli, items);
    } else {
        vent4.actUsuarioConFoto(cli, isMultipart, req, items);
    }
    mav.setViewName("redirect://listarJstl.htm");
    return mav;
    }
}


