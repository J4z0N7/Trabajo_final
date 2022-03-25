package modelos;


import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class personaValidacion implements Validator{
    @Override
    public boolean supports (Class<?>type){
        return PersonaBean.class.isAssignableFrom(type);}
    
    @Override
    public void validate(Object o, Errors errors){
        PersonaBean persona = new PersonaBean();
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"nombre","required.nombre","El campo nombre es obligatorio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"apellido","required.apellido","El campo apellido es obligatorio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"edad","required.edad","El campo edad es obligatorio");
        
        
        }
    
    
    }

