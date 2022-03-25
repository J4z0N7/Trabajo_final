
package modelos;


public class DVentas {
   private int Ctd_Producto;
   private String Fecha_Venta;
   private String Nombre_Producto;
   private String Nom_Cliente;
   
public DVentas(){
    
    /**
     *
     * @param Ctd_Producto
     * @param Fecha_Venta
     * @param Nombre_Producto
     * @param Nom_Cliente
     */}
    public DVentas(int Ctd_Producto, String Fecha_Venta, String Nombre_Producto,String Nom_Cliente){
    this.Ctd_Producto=Ctd_Producto;
    this.Fecha_Venta=Fecha_Venta;
    this.Nombre_Producto=Nombre_Producto;
    this.Nom_Cliente=Nom_Cliente;
    }

    public int getCtd_Producto() {
        return Ctd_Producto;
    }

    public void setCtd_Producto(int Ctd_Producto) {
        this.Ctd_Producto = Ctd_Producto;
    }

    public String getFecha_Venta() {
        return Fecha_Venta;
    }

    public void setFecha_Venta(String Fecha_Venta) {
        this.Fecha_Venta = Fecha_Venta;
    }

    public String getNombre_Producto() {
        return Nombre_Producto;
    }

    public void setNombre_Producto(String Nombre_Producto) {
        this.Nombre_Producto = Nombre_Producto;
    }

    public String getNom_Cliente() {
        return Nom_Cliente;
    }

    public void setNom_Cliente(String Nom_Cliente) {
        this.Nom_Cliente = Nom_Cliente;
    }
}
