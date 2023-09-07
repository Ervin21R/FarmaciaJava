
package modelo;


public class DetalleFactura {
    private int id_detalle_factura;
    private int cantidad_producto_y_unidades_detalle;
    private float importe_detalle_factura;
    private int id_producto_detalle;
    private int id_unidad_detalle;
    private int id_factura_detalle;
    private int porcentaje_IVA;
    private int porcentaje_Descuento;

    public int getPorcentaje_IVA() {
        return porcentaje_IVA;
    }

    public void setPorcentaje_IVA(int porcentaje_IVA) {
        this.porcentaje_IVA = porcentaje_IVA;
    }

    public int getPorcentaje_Descuento() {
        return porcentaje_Descuento;
    }

    public void setPorcentaje_Descuento(int porcentaje_Descuento) {
        this.porcentaje_Descuento = porcentaje_Descuento;
    }

    public int getId_detalle_factura() {
        return id_detalle_factura;
    }

    public void setId_detalle_factura(int id_detalle_factura) {
        this.id_detalle_factura = id_detalle_factura;
    }

    public int getCantidad_producto_y_unidades_detalle() {
        return cantidad_producto_y_unidades_detalle;
    }

    public void setCantidad_producto_y_unidades_detalle(int cantidad_producto_y_unidades_detalle) {
        this.cantidad_producto_y_unidades_detalle = cantidad_producto_y_unidades_detalle;
    }

    public float getImporte_detalle_factura() {
        return importe_detalle_factura;
    }

    public void setImporte_detalle_factura(float importe_detalle_factura) {
        this.importe_detalle_factura = importe_detalle_factura;
    }

    public int getId_producto_detalle() {
        return id_producto_detalle;
    }

    public void setId_producto_detalle(int id_producto_detalle) {
        this.id_producto_detalle = id_producto_detalle;
    }

    public int getId_unidad_detalle() {
        return id_unidad_detalle;
    }

    public void setId_unidad_detalle(int id_unidad_detalle) {
        this.id_unidad_detalle = id_unidad_detalle;
    }

    public int getId_factura_detalle() {
        return id_factura_detalle;
    }

    public void setId_factura_detalle(int id_factura_detalle) {
        this.id_factura_detalle = id_factura_detalle;
    }
    
    

}
