package modelo;

import java.sql.Connection;
import java.sql.SQLException;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class GenerarReporte {

    public boolean GenerarReporte(String rutaReporte) {
        
        Conexion conec = new Conexion();
        Connection  con= conec.getConnection();

        try {
            
            JasperReport reporte;
            reporte = (JasperReport) JRLoader.loadObjectFromFile(rutaReporte);

            JasperPrint imprimirReporte = JasperFillManager.fillReport(reporte, null, con);
            JasperViewer vistaReporte = new JasperViewer(imprimirReporte, false);
            vistaReporte.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            vistaReporte.setVisible(true);
            
            return true;

        } catch (JRException ex) {
            System.err.println("Archivo no pudo ser cargado " + ex);
            return false;
        }
        finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println("Error : "+e);
            }
        }
    }
}
