package modelo;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ImagenProducto extends javax.swing.JLabel {

    private BufferedImage ruta;
    private int idImagen;
    private int codImagen;
    
    public ImagenProducto() {
    }

    public ImagenProducto(int idImagen, int codImagen) {
        this.idImagen = idImagen;
        this.codImagen = codImagen;
    }

    public ImagenProducto(int codImagen) {
        this.codImagen = codImagen;
    }

    public ImagenProducto(int ancho, int alto, BufferedImage ruta) {
        this.setSize(ancho, alto);
        this.ruta = ruta;
    }

    public int getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }

    public int getCodImagen() {
        return codImagen;
    }

    public void setCodImagen(int codImagen) {
        this.codImagen = codImagen;
    }
    
    

    @Override
    public void paint(Graphics g) {
        Dimension tamaño = getSize();
        BufferedImage img = ruta;

        g.drawImage(img, 0, 0, tamaño.width, tamaño.height, null);
        setOpaque(false);
        super.paintComponent(g);

    }
}
