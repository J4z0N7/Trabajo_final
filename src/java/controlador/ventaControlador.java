package controlador;

import Dao.ConectarDb;
import Dao.VentaDao;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import modelos.DVentas;
import org.springframework.jdbc.core.JdbcTemplate;


public class ventaControlador {
    
    JdbcTemplate jdbctemplate;
    ConectarDb con = new ConectarDb();
    private JdbcTemplate jdbcTemplate;
    
    @RequestMapping(value="formVenta.htm" , method = RequestMethod.GET)
    public ModelAndView formVenta(){
        ModelAndView mav = new ModelAndView();
        this.jdbcTemplate = new JdbcTemplate(con.conectar());
        DVentas venta = new DVentas();
        VentaDao ventasDao = new VentaDao();
        List datos = ventasDao.consultarVentas();
        mav.addObject("venta", datos);
        mav.setViewName("vista/formVenta");
        return mav;
}@RequestMapping(value="formRegistrarVentas.htm" , method = RequestMethod.GET)
    public ModelAndView agregarVenta(){
        ModelAndView mav = new ModelAndView();
        this.jdbcTemplate = new JdbcTemplate(con.conectar());
        //DVentas venta = new DVentas();
        VentaDao ventasDao = new VentaDao();
//-------------------------------------------------------------------------//
        int Prol = ventasDao.consultarCtd_Producto();
        mav.addObject("producto1", Prol);        
//-------------------------------------------------------------------------//
        List datos = ventasDao.consultarVentas();
        mav.addObject("venta1", datos); 
//      return new ModelAndView("vista/formRegistrarVentas", "venta", new Dventas());
//-------------------------------------------------------------------------//
        List consul = ventasDao.consultarNombreCliente();
        mav.addObject("cliente1", consul);        

    mav.setViewName("vista/formRegistrarVentas");
        return mav;
}
}
    
  