
package Dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;


public class VentaDao {
    JdbcTemplate jdbctemplate;
    ConectarDb con = new ConectarDb();
    private JdbcTemplate jdbcTemplate;

    public List consultarVentas(){
        List datos = new ArrayList();
        this.jdbcTemplate = new JdbcTemplate(con.conectar());
        String sql = "select * from dventas";
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
        File borrar = new File (deleteFile);
        if (borrar.delete()){
            String sql = "delete from clientes where id_cliente = ?";
            jdbcTemplate.update(sql, id);
        } else {
            System.out.println("no se puede borrar...");
        }   
    }
}

    

    