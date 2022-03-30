package modelos;


public class DVentas {
    private int id_ventas;
    private int cantidad;
    private int id_cliente;
    private int id_producto;

    public DVentas(){
    }
    
    public DVentas(int id_ventas, int cantidad, int id_cliente, int id_producto){
    
    this.id_ventas=id_ventas;
    this.cantidad=cantidad;
    this.id_cliente=id_cliente;
    this.id_producto=id_producto;
    }
    
    public int getId_ventas() {
        return id_ventas;
    }

    public void setId_ventas(int id_ventas) {
        this.id_ventas = id_ventas;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_Producto() {
        return id_producto;
    }

    public void setId_Producto(int id_producto) {
        this.id_producto = id_producto;
    }
    
}
