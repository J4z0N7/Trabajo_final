package modelos;

public class ClienteBean {
    private int id_cliente;
    private String nombre;
    private String direccion;
    private int telefono;
    private String ciudad;
    private String foto;

    public ClienteBean(){
    }
    
    public ClienteBean(int id_cliente, String nombre, String direccion, int telefono, String ciudad, String foto){
    
    this.id_cliente=id_cliente;
    this.nombre=nombre;
    this.direccion=direccion;
    this.telefono=telefono;
    this.ciudad=ciudad;
    this.foto=foto;
    }
    
    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
    
    
    
    
    