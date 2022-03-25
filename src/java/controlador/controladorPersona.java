package controlador;


import Dao.ConectarDb;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import modelos.ClienteBean;
import modelos.PersonaBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
@Controller


public class controladorPersona{
    private PersonaValidation PersonaValidar;
    private JdbcTemplate jdbcTemplate;
    
    public controladorPersona (){
    this.PersonaValidar = new PersonaValidation();
    ConectarDb con = new ConectarDb();
    this.jdbcTemplate = new JdbcTemplate(con.conectar());
    }
    

    @RequestMapping(value="formJstl.htm", method = RequestMethod.GET)
    public ModelAndView form(){
        PersonaBean persona = new PersonaBean();
        return new ModelAndView ("vista/formJstl", "persona", new PersonaBean());
   }

    
    @RequestMapping(value="formJstl.htm", method = RequestMethod.POST)
    public ModelAndView form(
            @ModelAttribute("persona") PersonaBean pers,
            BindingResult result,
            SessionStatus status
    ){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("vista/mostrarDatos");
        mav.addObject("nombre", pers.getNombre());
        mav.addObject("apellido", pers.getApellido());
        mav.addObject("correo", pers.getCorreo());
        mav.addObject("telefono", pers.getEdad());
        return mav;  
    }        
@RequestMapping("listarJstl.htm")
public ModelAndView listarPersona(){
    String sql = "select * from clientes";
    ModelAndView mav = new ModelAndView();
    PersonaBean per = new PersonaBean();
    List datos = this.jdbcTemplate.queryForList(sql);
    mav.addObject("persona", datos);
    mav.setViewName("vista/listarJstl");
    return mav;

}
}