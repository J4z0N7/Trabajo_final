
package Dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import modelos.ClienteBean;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.jdbc.core.JdbcTemplate;
import static sun.font.CreatedFontTracker.MAX_FILE_SIZE;


public class VentaDao {
    JdbcTemplate jdbctemplate;
    ConectarDb con = new ConectarDb();
    private JdbcTemplate jdbcTemplate;
    private int MEMORY_THRESHOLD;
    private long MAX_REQUEST_SIZE;
    private String UPLOAD_DIRECTORY;

    public List consultarVentas(){
        List datos = new ArrayList();
        this.jdbcTemplate = new JdbcTemplate(con.conectar());
        String sql = "select * from ventas";
        datos = this.jdbcTemplate.queryForList(sql);
        return datos;
}
    /**
     * 
     * @return Este método retorna los datos de los nombres consultados en la BD
     */
    public List consultarNombreCliente(){
        //int cod=0;
        List consul = new ArrayList();
        this.jdbcTemplate = new JdbcTemplate(con.conectar());
        String sql = "select nombre from clientes;";
        //cod=jdbcTemplate.queryForObject(sql, Integer.class);
        //cod=this.jdbcTemplate.queryForObject(sql, Integer.class);
        consul = this.jdbcTemplate.queryForList(sql);
        return consul;
    }
    /**
     * 
     * @return Este método retorna la cantidad de productos dentro de la BD
     */
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
        File borrar = new File (deleteFile);
        if (borrar.delete()){
            String sql = "delete from clientes where id_cliente = ?";
            jdbcTemplate.update(sql, id);
        } else {
            System.out.println("no se puede borrar...");
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
    
    public void actUsuarioSinFoto(ClienteBean cli, List lista){
        this.jdbcTemplate = new JdbcTemplate(con.conectar());
            ArrayList<String> lista2 = new ArrayList<>();
            for (int i = 0; i<lista.size(); i++){
                FileItem fileItem = (FileItem) lista.get(i);
                lista2.add(fileItem.getString());
            }
            cli.setNombre(lista2.get(0));
            cli.setDireccion(lista2.get(1));
            cli.setTelefono(Integer.parseInt(lista2.get(2)));
            cli.setCiudad(lista2.get(3));       
//-----------------------------------------------------------------------------            
            String sql = "update clientes set"
                + "nombre = ?,"
                + "direccion = ?,"
                + "telefono = ?,"
                + "ciudad = ? "
                + "where id_cliente = ?";
        jdbcTemplate.update(
                sql, cli.getNombre(), cli.getDireccion(), cli.getTelefono(), cli.getCiudad(), cli.getId_cliente()
        );
    }
//----------------------------------------------------------------------------------------------------------------        
    
    public void actUsuarioConFoto (ClienteBean cli, boolean isMultipart, HttpServletRequest request, List items){
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
                    String filePath = uploadPath + File.separator + "ID-" + lista5.get(0)+"-"+fileName;
                    File uploadFile = new File(filePath);
                    
                    String nameFile = ("imagenes/fotos/" + "ID-"+lista5.get(0)+"-"+fileName);
                    try{
                    uploadFile.delete();
                    fileItem.write(uploadFile);
                    System.out.print(nameFile);
                    cli.setFoto(nameFile);
                    } catch (Exception e) {
                        System.out.print("escritura ..." + e.getMessage());
                    }
                } else {
                    lista5.add(fileItem.getString());
                }
            }
            cli.setNombre(lista5.get(0));
            cli.setDireccion(lista5.get(1));
            cli.setTelefono(Integer.parseInt(lista5.get(2)));
            cli.setCiudad(lista5.get(3));         
        }
        String sql="update clientes set nombre = ?, direccion = ?, telefono = ?, ciudad = ?, foto = ? where id_cliente = ?";
        jdbcTemplate.update(sql, cli.getNombre(), cli.getDireccion(), cli.getTelefono(), cli.getCiudad(), cli.getFoto(), cli.getId_cliente());
    }
    public void actUsuarioSinFoto(ClienteBean cli) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}   

    