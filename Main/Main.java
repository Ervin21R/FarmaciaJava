package Main;

import controlador.Programa;
import modelo.Cargo;
import modelo.Cliente;
import modelo.GenerarReporte;
import modelo.Sql;
import modelo.Usuario;
import vista.Configurar_IVA_Descuentos;
import vista.Configurar_InformacionEmpresaClienteDelSoftware;
import vista.ConsultarClientes;
import vista.ConsultarGeneralVentas;
import vista.ConsultarLaboratorio;
import vista.ConsultarProducto;
import vista.ConsultarProveedor;
import vista.ConsultarVentas;
import vista.GestionarUsuario;
import vista.EstaSeguro;
import vista.GestionarCliente;
import vista.GestionarFacturas;
import vista.GestionarLaboratorio;
import vista.GestionarProducto;
import vista.GestionarProveedor;
import vista.VentanaBienvenido;
import vista.VentanaFactura;
import vista.VentanaFacturacion;
import vista.VentanaInicioSesion;
import vista.VentanaPrincipal;

public class Main {

    public static void main(String[] Args) {

        Sql sql = new Sql();
        Usuario usuario = new Usuario();
        Cargo cargo = new Cargo();
        Cliente cliente = new Cliente();
        VentanaInicioSesion ventanaInicioSesion = new VentanaInicioSesion();
        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
        GestionarUsuario gestionarUsuario = new GestionarUsuario(ventanaPrincipal, true);
        GestionarCliente gestionarCliente = new GestionarCliente(ventanaPrincipal, false);
        GestionarLaboratorio gestionarLaboratorio = new GestionarLaboratorio(ventanaPrincipal, false);
        GestionarProveedor gestionarProveedor = new GestionarProveedor(ventanaPrincipal, false);
        GestionarProducto gestionarProducto = new GestionarProducto(ventanaPrincipal, false);
        ConsultarClientes consultarCliente = new ConsultarClientes(ventanaPrincipal, false);
        ConsultarLaboratorio consultarLaboratorio = new ConsultarLaboratorio(ventanaPrincipal, false);
        ConsultarProveedor consultarProveedor = new ConsultarProveedor(ventanaPrincipal, false);
        ConsultarProducto consultarProducto = new ConsultarProducto(ventanaPrincipal, false);
        ConsultarVentas consultarVentas = new ConsultarVentas(ventanaPrincipal, true);
        ConsultarGeneralVentas generalVentas = new ConsultarGeneralVentas(ventanaPrincipal, false);
        VentanaFacturacion ventanaFacturacion = new VentanaFacturacion(ventanaPrincipal, true);
        VentanaFactura vFactura = new VentanaFactura(ventanaPrincipal, true);
        GestionarFacturas gestionarFactura = new GestionarFacturas(ventanaPrincipal, true);
        VentanaBienvenido bienvenido = new VentanaBienvenido(ventanaPrincipal, true);
        GenerarReporte generarReporte = new GenerarReporte();
        EstaSeguro SioNO = new EstaSeguro(ventanaPrincipal, true);
        Configurar_IVA_Descuentos conf_IVA_Des = new Configurar_IVA_Descuentos(ventanaPrincipal, true);
        Configurar_InformacionEmpresaClienteDelSoftware conf_infoEmpresa = new Configurar_InformacionEmpresaClienteDelSoftware(ventanaPrincipal, true);
        Programa programa = new Programa(ventanaInicioSesion, ventanaPrincipal, cliente, usuario, cargo, sql, gestionarUsuario, SioNO, gestionarCliente, gestionarLaboratorio, gestionarProveedor, gestionarProducto, consultarCliente, consultarLaboratorio, consultarProveedor, consultarProducto, generarReporte, ventanaFacturacion, conf_IVA_Des, vFactura, gestionarFactura, conf_infoEmpresa, bienvenido, consultarVentas, generalVentas);

        programa.iniciar();

    }

}
