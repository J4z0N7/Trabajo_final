package modelos;

import javax.ejb.Stateless;

/**
 *
 * @author JEIZZON-PC
 */

@Stateless
public class ProductosBean {
   private int id_producto; 
   private String descripcion;
   private String foto_producto;
   private int precio;

   public ProductosBean(){
}
   public ProductosBean(int id_producto, String descripcion, String foto_producto, int precio){
       this.id_producto=id_producto;
       this.descripcion=descripcion;
       this.foto_producto=foto_producto;
       this.precio=precio;
            
   }
   
    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }
   
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto_producto() {
        return foto_producto;
    }

    public void setFoto_producto(String foto_producto) {
        this.foto_producto = foto_producto;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
   
   
}
