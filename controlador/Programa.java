package controlador;

import java.awt.Color;
//import java.awt.Component;
//import java.awt.Container;
//import java.awt.FocusTraversalPolicy;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
//import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.ContainerEvent;
//import java.awt.event.ContainerListener;
//import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
//import static java.awt.event.KeyEvent.VK_DOWN;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
//import java.awt.event.WindowFocusListener;
//import java.awt.event.WindowListener;
//import java.awt.image.BufferedImage;
//import java.awt.print.PageFormat;
//import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
//import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
//import java.text.DecimalFormatSymbols;
//import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.Date;
//import java.util.EventObject;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Locale;
//import java.util.Map;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javax.swing.ButtonGroup;
//import javax.swing.ButtonModel;
//import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
//import javax.swing.JComboBox;
import javax.swing.JFileChooser;
//import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
//import javax.swing.JTable;
//import javax.swing.WindowConstants;
//import javax.swing.event.CellEditorListener;
//import javax.swing.event.ChangeListener;
//import javax.swing.event.DocumentEvent;
//import javax.swing.event.DocumentListener;
//import javax.swing.event.TableModelEvent;
//import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
//import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;
//import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
//import javax.swing.text.DefaultFormatterFactory;
//import javax.swing.text.MaskFormatter;
//import javax.swing.text.NumberFormatter;
import modelo.Cargo;
import modelo.CifrarContraseña;
import modelo.Cliente;
import modelo.DetalleFactura;
import modelo.EmpresaClienteDelSoftware;
import modelo.Factura;
import modelo.GenerarReporte;
import modelo.ImagenProducto;
import modelo.Laboratorio;
import modelo.Producto;
import modelo.Proveedor;
import modelo.Sql;
import modelo.TablaRenderer;
import modelo.Usuario;
import modelo.configuracionIvaDes;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.util.JRLoader;
//import net.sf.jasperreports.view.JasperViewer;
import vista.Configurar_IVA_Descuentos;
import vista.Configurar_InformacionEmpresaClienteDelSoftware;
import vista.ConsultarClientes;
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
import vista.ConsultarGeneralVentas;

public class Programa {

    private VentanaInicioSesion ventanaSesion;
    private VentanaPrincipal ventanaPrincipal;
    private Cliente cliente;
    private Sql sql = new Sql();
    private GestionarUsuario gestionarUsuario;
    private GestionarCliente gestionarCliente;
    private GestionarLaboratorio gestionarLaboratorio;
    private GestionarProveedor gestionarProveedor;
    private GestionarProducto gestionarProducto;
    private ConsultarClientes consultarCliente;
    private ConsultarLaboratorio consultarLaboratorio;
    private ConsultarProveedor consultarProveedor;
    private ConsultarProducto consultarProducto;
    private ConsultarVentas consultarVentas;
    private ConsultarGeneralVentas generalVentas;
    private VentanaFacturacion ventanaFacturacion;
    private VentanaFactura vFactura;
    private GestionarFacturas gestionarFactura;
    private GenerarReporte generarReporte;
    private VentanaBienvenido bienvenido;
    private EstaSeguro siNo;
    private ImagenProducto imagenProducto;
    private int idClienteEncontrado, cedulaClienteEncontrada;
    private Integer idUsuarioBuscado;
    private String nicknameEncontrado;
    private int idProveedorEncontrado, cedulaProveedorEncontrada;
    private int idLaboratorioEncontrado;
    private String nombreLaboratorioEncontrado;
    private int idProductoEncontrado;
    private float ivaTotal, subTotalFactura;
    private String codigoBarraProductoEncontrado;
    private boolean estarSeguro = false;
    public static DefaultComboBoxModel modeloCombo;
    public static DefaultComboBoxModel modeloCombo2;
    private static DefaultTableModel modeloTablaDetalleFactura, modeloBackupModificarVentas;
    private File archivoImagen = null;
    private Usuario usuario = new Usuario();
    private Cargo cargo = new Cargo();
    private Configurar_IVA_Descuentos conf_IVA_Des;
    private Configurar_InformacionEmpresaClienteDelSoftware conf_InfoEmpresa;
    Date date = new Date();
    DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Icon iconWelcome = new ImageIcon("C:\\Users\\HP\\Documents\\NetBeansProjects\\Farmacia\\src\\vista\\Imagenes\\bienvenido1.png");
    Icon iconWarning = new ImageIcon("C:\\Users\\HP\\Documents\\NetBeansProjects\\Farmacia\\src\\vista\\Imagenes\\advertencia.png");
    Icon icon = new ImageIcon("C:\\Users\\HP\\Documents\\NetBeansProjects\\Farmacia\\src\\vista\\Imagenes\\failForm.png");
    Icon iconError = new ImageIcon("C:\\Users\\HP\\Documents\\NetBeansProjects\\Farmacia\\src\\vista\\Imagenes\\errorBaseDeDatos.png");
    Icon iconInicioIncorrecto = new ImageIcon("C:\\Users\\HP\\Documents\\NetBeansProjects\\Farmacia\\src\\vista\\Imagenes\\inicioSesionIncorrecto.png");
    Icon iconOk = new ImageIcon("C:\\Users\\HP\\Documents\\NetBeansProjects\\Farmacia\\src\\vista\\Imagenes\\Ok.png");

    public Programa(VentanaInicioSesion ventanaSesion, VentanaPrincipal ventanaPrincipal, Cliente cliente, Usuario usuario, Cargo cargo, Sql sql, GestionarUsuario gestionarUsuario, EstaSeguro siNo, GestionarCliente gestionarCliente, GestionarLaboratorio gestionarLaboratorio, GestionarProveedor gestionarProveedor, GestionarProducto gestionarProducto, ConsultarClientes consultarCliente, ConsultarLaboratorio consultarLaboratorio, ConsultarProveedor consultarProveedor, ConsultarProducto consultarProducto, GenerarReporte generarReporte, VentanaFacturacion ventanaFacturacion, Configurar_IVA_Descuentos conf_IVA_Des, VentanaFactura vFactura, GestionarFacturas gestionarFactura, Configurar_InformacionEmpresaClienteDelSoftware conf_InfoEmpresa, VentanaBienvenido bienvenido, ConsultarVentas consultarVentas, ConsultarGeneralVentas generalVentas) {

        this.ventanaSesion = ventanaSesion;
        this.ventanaPrincipal = ventanaPrincipal;
        this.cliente = cliente;
        this.usuario = usuario;
        this.cargo = cargo;
        this.sql = sql;
        this.gestionarUsuario = gestionarUsuario;
        this.gestionarCliente = gestionarCliente;
        this.bienvenido = bienvenido;
        this.siNo = siNo;
        this.gestionarLaboratorio = gestionarLaboratorio;
        this.gestionarProveedor = gestionarProveedor;
        this.gestionarProducto = gestionarProducto;
        this.consultarCliente = consultarCliente;
        this.consultarLaboratorio = consultarLaboratorio;
        this.consultarProveedor = consultarProveedor;
        this.consultarProducto = consultarProducto;
        this.consultarVentas = consultarVentas;
        this.generalVentas = generalVentas;
        this.generarReporte = generarReporte;
        this.ventanaFacturacion = ventanaFacturacion;
        this.conf_IVA_Des = conf_IVA_Des;
        this.vFactura = vFactura;
        this.gestionarFactura = gestionarFactura;
        this.conf_InfoEmpresa = conf_InfoEmpresa;
    }

    public void iniciar() {

        oyenteAccion_VentanaInicioSesion_BotonIniciarSesion();
        oyenteAccion_VentanaInicioSesion_cajaContraseña();
        oyenteAccion_VentanaInicioSesion_BotonSalir();
        oyenteRaton_VentanaInicioSesion_VerClave();
        oyenteRaton_VentanaInicioSesion_ocultarClave();
        ventanaSesion.ocultarClave.setVisible(false);
        ventanaSesion.verClave.setVisible(true);
        ventanaSesion.setLocationRelativeTo(null);
        ventanaSesion.setVisible(true);

    }

    //---------------------------------------------------------------------------------------------------------// Ventana de confirmaciones
    private void oyenteAccion_NoEliminar() {
        MouseListener mL = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ((e.getModifiers() & 16) != 0) {
                    estarSeguro = false;
                    siNo.dispose();
                    siNo = null;
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        siNo.labelCancelar.addMouseListener(mL);
    }

    private void oyenteAccion_SiEliminar() {
        MouseListener mL = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ((e.getModifiers() & 16) != 0) {
                    estarSeguro = true;
                    siNo.dispose();
                    siNo = null;
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        siNo.labelOk.addMouseListener(mL);
    }

    private void windowClosing_VentanaEstaSeguro() {
        siNo.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                siNo.dispose();
                siNo = null;
            }
        });
    }

    //---------------------------------------------------------------------------------------------------------// Ventana de inicio de sesion
    private void oyenteRaton_VentanaInicioSesion_VerClave() {
        MouseListener mL = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ((e.getModifiers() & 16) != 0) {
                    ventanaSesion.verClave.setVisible(false);
                    ventanaSesion.ocultarClave.setVisible(true);
                    ventanaSesion.cajaPassword.setEchoChar((char) 0);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        ventanaSesion.verClave.addMouseListener(mL);
    }

    private void oyenteRaton_VentanaInicioSesion_ocultarClave() {
        MouseListener mL = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ((e.getModifiers() & 16) != 0) {
                    ventanaSesion.ocultarClave.setVisible(false);
                    ventanaSesion.verClave.setVisible(true);
                    ventanaSesion.cajaPassword.setEchoChar('•');
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        ventanaSesion.ocultarClave.addMouseListener(mL);
    }

    private void oyenteAccion_VentanaInicioSesion_BotonIniciarSesion() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == ventanaSesion.botonIniciarSesion) {   // Boton de Iniciar Sesion

                    if (!ventanaSesion.cajaUsuario.getText().equals("") && ventanaSesion.cajaPassword.getPassword().length != 0) {
                        usuario.setNickName(ventanaSesion.cajaUsuario.getText());
                        String pass = new String(ventanaSesion.cajaPassword.getPassword());
                        usuario.setPassword(pass);
                        usuario.setFechaUltimaSesion(fechaHora.format(date));

                        if (sql.iniciarSesion(usuario, cargo)) {
                            ventanaSesion.cajaUsuario.setText(null);
                            ventanaSesion.cajaPassword.setText(null);
                            ventanaSesion.dispose();
                            ventanaSesion = null;

                            if (ventanaPrincipal == null) {
                                ventanaPrincipal = new VentanaPrincipal();
                            }
                            activarFuncionesDeLosComponentes_ModuloVentas();
                            cargar_Acciones_VentanaPrincipal();
                            deshabilitar_ModuloDeVentas();
                            MouseListener_LabelClienteNoRegistradoEnDB_ModuloDeVentas();

                            ventanaPrincipal.setVisible(true);
                            ventanaPrincipal.setLocationRelativeTo(null);

                            ventanaPrincipal.labelUsuarioActivo.setText("Sesión activa : " + usuario.getpNombre() + " " + usuario.getpApellido());
                            ventanaPrincipal.labelCargo.setText(usuario.getCargo().getNombreCargo());

                            if (bienvenido == null) {
                                bienvenido = new VentanaBienvenido(ventanaPrincipal, true);
                            }
                            ActionListener_VentanaBienvenido_BotonCerrar();

                            bienvenido.labelNombreBienvenido.setText(usuario.getpNombre() + " " + usuario.getpApellido());
                            bienvenido.setLocationRelativeTo(null);
                            bienvenido.setVisible(true);

                        } else {
                            JOptionPane.showMessageDialog(null, "Usuario o Contraseña son incorrectos", " Volver a intentarlo", JOptionPane.PLAIN_MESSAGE, iconInicioIncorrecto);
                            ventanaSesion.cajaUsuario.setText(null);
                            ventanaSesion.cajaPassword.setText(null);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Debe llenar todos los campos", "Ingresar Datos", JOptionPane.PLAIN_MESSAGE, iconInicioIncorrecto);
                    }
                }
            }
        };
        ventanaSesion.botonIniciarSesion.addActionListener(aL);
    }

    private void oyenteAccion_VentanaInicioSesion_cajaContraseña() {

        KeyListener kL = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {

                if (e.VK_ENTER == e.getKeyCode()) {

                    if (!ventanaSesion.cajaUsuario.getText().equals("") && ventanaSesion.cajaPassword.getPassword().length != 0) {
                        usuario.setNickName(ventanaSesion.cajaUsuario.getText());
                        String pass = new String(ventanaSesion.cajaPassword.getPassword());
                        usuario.setPassword(pass);
                        usuario.setFechaUltimaSesion(fechaHora.format(date));

                        if (sql.iniciarSesion(usuario, cargo)) {
                            ventanaSesion.cajaUsuario.setText(null);
                            ventanaSesion.cajaPassword.setText(null);
                            ventanaSesion.dispose();
                            ventanaSesion = null;

                            if (ventanaPrincipal == null) {
                                ventanaPrincipal = new VentanaPrincipal();
                            }
                            activarFuncionesDeLosComponentes_ModuloVentas();
                            cargar_Acciones_VentanaPrincipal();
                            deshabilitar_ModuloDeVentas();
                            MouseListener_LabelClienteNoRegistradoEnDB_ModuloDeVentas();

                            ventanaPrincipal.setVisible(true);
                            ventanaPrincipal.setLocationRelativeTo(null);

                            ventanaPrincipal.labelUsuarioActivo.setText("Sesión activa : " + usuario.getpNombre() + " " + usuario.getpApellido());
                            ventanaPrincipal.labelCargo.setText(usuario.getCargo().getNombreCargo());

                            if (bienvenido == null) {
                                bienvenido = new VentanaBienvenido(ventanaPrincipal, true);
                            }
                            ActionListener_VentanaBienvenido_BotonCerrar();

                            bienvenido.labelNombreBienvenido.setText(usuario.getpNombre() + " " + usuario.getpApellido());
                            bienvenido.setLocationRelativeTo(null);
                            bienvenido.setVisible(true);

                        } else {
                            JOptionPane.showMessageDialog(null, "Usuario o Contraseña son incorrectos", " Volver a intentarlo", JOptionPane.PLAIN_MESSAGE, iconInicioIncorrecto);
                            ventanaSesion.cajaUsuario.setText(null);
                            ventanaSesion.cajaPassword.setText(null);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Debe llenar todos los campos", "Ingresar Datos", JOptionPane.PLAIN_MESSAGE, iconInicioIncorrecto);
                    }
                }
            }
        };
        ventanaSesion.cajaPassword.addKeyListener(kL);
    }

    private void oyenteAccion_VentanaInicioSesion_BotonSalir() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == ventanaSesion.botonSalir) {    // Menu Archivo item Salir
                    System.exit(0);
                }
            }
        };
        ventanaSesion.botonSalir.addActionListener(aL);
    }

    //------------------------------------------------------------------------------------------------------------// Ventana Bienvenido
    private void ActionListener_VentanaBienvenido_BotonCerrar() {
        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bienvenido.dispose();
                bienvenido = null;
            }
        };
        bienvenido.botonCerrar.addActionListener(aL);
    }

    //------------------------------------------------------------------------------------------------------------// Ventana Principal
    private void cargar_Acciones_VentanaPrincipal() {
        //Inicio de sesion
        oyenteAccion_VentanaPrincipal_BarMenuArchivo_ItemSalir();
        oyenteAccion_VentanaPrincipal_BarMenuArchivo_ItemCerrarSesion();

        //Gestiones de configuracion
        ActionListener_ConfigurarValoresPorDefecto_IvaDescuento();
        ActionListener_Configurar_InfoEmpresaClienteDelSoftware();

        //Gestiones usuario
        oyenteAccion_VentanaPrincipal_BarMenuAdministrador_SubMenuGestionarUsuarios_ItemBuscarUsuarios();
        oyenteAccion_VentanaPrincipal_BarMenuAdministrador_SubMenuGestionarUsuarios_ItemCrearUsuarios();
        oyenteAccion_VentanaPrincipal_BarMenuAdministrador_SubMenuGestionarUsuarios_ItemEditarUsuarios();
        oyenteAccion_VentanaPrincipal_BarMenuAdministrador_SubMenuGestionarUsuarios_ItemEliminarUsuarios();

        //Gestiones 
        oyenteAccion_VentanaPrincipal_BarMenuGestionar_itemClientes();

        oyenteAccion_VentanaPrincipal_BarMenuGestionar_itemLaboratorio();

        oyenteAccion_VentanaPrincipal_BarMenuGestionar_itemProveedores();

        oyenteAccion_VentanaPrincipal_BarMenuGestionar_itemProducto();

        //Consultar
        oyenteAccion_VentanaPrincipal_BarMenuConsultar_MenuitemCliente();

        oyenteAccion_VentanaPrincipal_BarMenuConsultar_MenuitemLaboratorio();

        oyenteAccion_VentanaPrincipal_BarMenuConsultar_MenuitemProveedor();

        oyenteAccion_VentanaPrincipal_BarMenuConsultar_MenuitemMedicamentos();

        oyenteAccion_VentanaPrincipal_BarMenuRealizar_MenuitemVentas();

        ActionListener_VentanaPrincipal_BarMenuConsultar_itemConsultarVentas();

        ActionListener_VentanaPrincipal_BarMenuAdministrar_itemGestionarFactura();
        ActionListener_VentanaPrincipal_BarMenuConsultar_MenuitemFacturas();
        ActionListener_VentanaPrincipal_BarMenuRealizar_itemDevolucionesYCambio();

        //Reportes
        oyenteAccion_VentanaPrincipal_BarMenuReportes_MenuitemInventarioMedicamentos();
        oyenteAccion_VentanaPrincipal_BarMenuReportes_itemReporteGeneralVentas();
        oyenteAccion_VentanaPrincipal_BarMenuReportes_itemReporteDevolucionesCambios();
        oyenteAccion_VentanaPrincipal_BarMenuReportes_itemMedicamentosVencer();

    }

    private void oyenteAccion_VentanaPrincipal_BarMenuArchivo_ItemSalir() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == ventanaPrincipal.menuItemSalir) {    // Menu Archivo item Salir
                    System.exit(0);
                }
            }
        };
        ventanaPrincipal.menuItemSalir.addActionListener(aL);
    }

    private void oyenteAccion_VentanaPrincipal_BarMenuArchivo_ItemCerrarSesion() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == ventanaPrincipal.menuItemCerrarSesion) {     // Menu Archivo item Cerrar Sesion
                    limpiarCajas_ModuloVentas();
                    ventanaPrincipal.dispose();
                    ventanaPrincipal = null;

                    bienvenido = null;
                    gestionarUsuario = null;
                    gestionarCliente = null;
                    gestionarLaboratorio = null;
                    gestionarProveedor = null;
                    gestionarProducto = null;

                    consultarCliente = null;
                    consultarLaboratorio = null;
                    consultarProveedor = null;
                    consultarProducto = null;
                    consultarVentas = null;
                    generalVentas = null;
                    ventanaFacturacion = null;
                    generarReporte = null;
                    vFactura = null;

                    conf_IVA_Des = null;
                    gestionarFactura = null;
                    conf_InfoEmpresa = null;

                    if (ventanaSesion == null) {
                        ventanaSesion = new VentanaInicioSesion();
                    }
                    iniciar();
                }
            }
        };
        ventanaPrincipal.menuItemCerrarSesion.addActionListener(aL);

    }

    //------------------------------------------------------------------------------------------------------------   Gestionar usuarios
    private void oyenteAccion_VentanaPrincipal_BarMenuAdministrador_SubMenuGestionarUsuarios_ItemBuscarUsuarios() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gestionarUsuario == null) {
                    gestionarUsuario = new GestionarUsuario(ventanaPrincipal, true);
                }

                ventanaGestionarUsuario_WindowClossing();
                oyenteAccion_VentanaCrearUsuario_BotonCancelar();
                oyenteAccion_ventanaCrearUsuario_BotonBuscarUsuarioPorCedula();
                oyenteAccion_VentanaCrearUsuario_BotonCrearUsuario_BotonEditarUsuario_BotonEliminarUsuario();

                modeloCombo = new DefaultComboBoxModel();
                modeloCombo.addElement("Selecionar");
                modeloCombo.addElement("Administrador");
                modeloCombo.addElement("Farmaceutico");
                modeloCombo.addElement("Auxiliar");
                gestionarUsuario.comboCargo.setModel(modeloCombo);

                gestionarUsuario.botonBuscarUsuarioPorCedula.setVisible(true);
                gestionarUsuario.cajaBuscarPorCedula.setVisible(true);
                gestionarUsuario.setLocationRelativeTo(null);
                gestionarUsuario.boton_CrearEditarEliminar_Usuario.setVisible(false);
                gestionarUsuario.botonCancelar.setVisible(false);
                gestionarUsuario.labelTipoGestionUsuario.setText("BUSCAR USUARIOS");
                gestionarUsuario.cajaBuscarPorCedula.requestFocus();
                formWindowActivated_Usuario();

                gestionarUsuario.cajaPrimerNombre.setEditable(false);
                gestionarUsuario.cajaSegundoNombre.setEditable(false);
                gestionarUsuario.cajaPrimerApellido.setEditable(false);
                gestionarUsuario.cajaSegundoApellido.setEditable(false);
                gestionarUsuario.cajaCedula.setEditable(false);
                gestionarUsuario.comboCargo.setEnabled(false);
                gestionarUsuario.cajaCorreo.setEditable(false);
                gestionarUsuario.cajaNickName.setEditable(false);
                gestionarUsuario.cajaPassword.setEditable(false);
                gestionarUsuario.setVisible(true);
            }
        };
        ventanaPrincipal.itemBuscarUsuarios.addActionListener(aL);
    }

    private void oyenteAccion_VentanaPrincipal_BarMenuAdministrador_SubMenuGestionarUsuarios_ItemCrearUsuarios() {

        ActionListener aL = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == ventanaPrincipal.itemCrearUsuarios) { // (Item crear Usuarios) 

                    if (ventanaPrincipal.labelCargo.getText().equals("Administrador")) {
                        if (gestionarUsuario == null) {
                            gestionarUsuario = new GestionarUsuario(ventanaPrincipal, true);
                        }

                        ventanaGestionarUsuario_WindowClossing();
                        oyenteAccion_VentanaCrearUsuario_BotonCancelar();
                        oyenteAccion_ventanaCrearUsuario_BotonBuscarUsuarioPorCedula();
                        oyenteAccion_VentanaCrearUsuario_BotonCrearUsuario_BotonEditarUsuario_BotonEliminarUsuario();

                        modeloCombo = new DefaultComboBoxModel();
                        modeloCombo.addElement("Selecionar");
                        modeloCombo.addElement("Administrador");
                        modeloCombo.addElement("Farmaceutico");
                        modeloCombo.addElement("Auxiliar");
                        gestionarUsuario.comboCargo.setModel(modeloCombo);

                        gestionarUsuario.cajaPrimerNombre.setEnabled(true);
                        gestionarUsuario.cajaSegundoNombre.setEnabled(true);
                        gestionarUsuario.cajaPrimerApellido.setEnabled(true);
                        gestionarUsuario.cajaSegundoApellido.setEnabled(true);
                        gestionarUsuario.cajaCedula.setEnabled(true);
                        gestionarUsuario.comboCargo.setEnabled(true);
                        gestionarUsuario.cajaCorreo.setEnabled(true);
                        gestionarUsuario.cajaNickName.setEnabled(true);
                        gestionarUsuario.cajaPassword.setEnabled(true);
                        gestionarUsuario.cajaPassword.setEchoChar('•');

                        gestionarUsuario.botonBuscarUsuarioPorCedula.setVisible(false);
                        gestionarUsuario.cajaBuscarPorCedula.setVisible(false);
                        gestionarUsuario.botonCancelar.setVisible(true);
                        gestionarUsuario.boton_CrearEditarEliminar_Usuario.setText("Crear Usuario");
                        gestionarUsuario.boton_CrearEditarEliminar_Usuario.setEnabled(true);
                        gestionarUsuario.boton_CrearEditarEliminar_Usuario.setVisible(true);
                        gestionarUsuario.labelTipoGestionUsuario.setText("CREAR TU USUARIO");
                        gestionarUsuario.setLocationRelativeTo(null);
                        gestionarUsuario.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(ventanaPrincipal, "Usted no tiene permisos para\ncrear usuarios en el sistema", "  No tiene permisos", JOptionPane.PLAIN_MESSAGE, iconWarning);
                    }
                }
            }
        };
        ventanaPrincipal.itemCrearUsuarios.addActionListener(aL);
    }

    private void oyenteAccion_VentanaPrincipal_BarMenuAdministrador_SubMenuGestionarUsuarios_ItemEditarUsuarios() {
        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (ventanaPrincipal.labelCargo.getText().equals("Administrador")) {
                    if (gestionarUsuario == null) {
                        gestionarUsuario = new GestionarUsuario(ventanaPrincipal, true);
                    }
                    // (Item Editar Usuarios)

                    ventanaGestionarUsuario_WindowClossing();
                    oyenteAccion_VentanaCrearUsuario_BotonCancelar();
                    oyenteAccion_ventanaCrearUsuario_BotonBuscarUsuarioPorCedula();
                    oyenteAccion_VentanaCrearUsuario_BotonCrearUsuario_BotonEditarUsuario_BotonEliminarUsuario();

                    gestionarUsuario.labelTipoGestionUsuario.setText("EDITAR USUARIOS");
                    modeloCombo = new DefaultComboBoxModel();
                    modeloCombo.addElement("Selecionar");
                    modeloCombo.addElement("Administrador");
                    modeloCombo.addElement("Farmaceutico");
                    modeloCombo.addElement("Auxiliar");

                    gestionarUsuario.comboCargo.setModel(modeloCombo);

                    gestionarUsuario.cajaPrimerNombre.setEditable(false);
                    gestionarUsuario.cajaSegundoNombre.setEditable(false);
                    gestionarUsuario.cajaPrimerApellido.setEditable(false);
                    gestionarUsuario.cajaSegundoApellido.setEditable(false);
                    gestionarUsuario.cajaCedula.setEditable(false);
                    gestionarUsuario.comboCargo.setEnabled(true);
                    gestionarUsuario.cajaCorreo.setEditable(false);
                    gestionarUsuario.cajaNickName.setEditable(false);
                    gestionarUsuario.cajaPassword.setEditable(false);

                    gestionarUsuario.botonBuscarUsuarioPorCedula.setVisible(true);
                    gestionarUsuario.cajaBuscarPorCedula.setVisible(true);
                    gestionarUsuario.botonCancelar.setVisible(true);
                    gestionarUsuario.boton_CrearEditarEliminar_Usuario.setText("Editar Usuario");
                    gestionarUsuario.boton_CrearEditarEliminar_Usuario.setEnabled(false);
                    gestionarUsuario.boton_CrearEditarEliminar_Usuario.setVisible(true);
                    gestionarUsuario.cajaBuscarPorCedula.requestFocus();
                    formWindowActivated_Usuario();

                    gestionarUsuario.setLocationRelativeTo(null);
                    gestionarUsuario.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(ventanaPrincipal, "Usted no tiene permisos para editar \nusuarios del sistema", "  No tiene permisos", JOptionPane.PLAIN_MESSAGE, iconWarning);
                }
            }
        };
        ventanaPrincipal.itemEditarUsuarios.addActionListener(aL);
    }

    private void oyenteAccion_VentanaPrincipal_BarMenuAdministrador_SubMenuGestionarUsuarios_ItemEliminarUsuarios() {
        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (ventanaPrincipal.labelCargo.getText().equals("Administrador")) {
                    // (Item Editar Usuarios)
                    if (gestionarUsuario == null) {
                        gestionarUsuario = new GestionarUsuario(ventanaPrincipal, true);
                    }

                    ventanaGestionarUsuario_WindowClossing();
                    oyenteAccion_VentanaCrearUsuario_BotonCancelar();
                    oyenteAccion_ventanaCrearUsuario_BotonBuscarUsuarioPorCedula();
                    oyenteAccion_VentanaCrearUsuario_BotonCrearUsuario_BotonEditarUsuario_BotonEliminarUsuario();

                    gestionarUsuario.labelTipoGestionUsuario.setText("ELIMINAR USUARIOS");
                    modeloCombo = new DefaultComboBoxModel();
                    modeloCombo.addElement("Selecionar");
                    modeloCombo.addElement("Administrador");
                    modeloCombo.addElement("Farmaceutico");
                    modeloCombo.addElement("Auxiliar");

                    gestionarUsuario.comboCargo.setModel(modeloCombo);

                    gestionarUsuario.cajaPrimerNombre.setEditable(false);
                    gestionarUsuario.cajaSegundoNombre.setEditable(false);
                    gestionarUsuario.cajaPrimerApellido.setEditable(false);
                    gestionarUsuario.cajaSegundoApellido.setEditable(false);
                    gestionarUsuario.cajaCedula.setEditable(false);
                    gestionarUsuario.comboCargo.setEnabled(false);
                    gestionarUsuario.cajaCorreo.setEditable(false);
                    gestionarUsuario.cajaNickName.setEditable(false);
                    gestionarUsuario.cajaPassword.setEditable(false);

                    gestionarUsuario.botonBuscarUsuarioPorCedula.setVisible(true);
                    gestionarUsuario.cajaBuscarPorCedula.setVisible(true);
                    gestionarUsuario.botonCancelar.setVisible(true);
                    gestionarUsuario.boton_CrearEditarEliminar_Usuario.setText("Eliminar Usuario");
                    gestionarUsuario.boton_CrearEditarEliminar_Usuario.setEnabled(false);
                    gestionarUsuario.boton_CrearEditarEliminar_Usuario.setVisible(true);
                    gestionarUsuario.cajaBuscarPorCedula.requestFocus();
                    formWindowActivated_Usuario();

                    gestionarUsuario.setLocationRelativeTo(null);
                    gestionarUsuario.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(ventanaPrincipal, "Usted no tiene permisos para eliminar \nusuarios del sistema", "  No tiene permisos", JOptionPane.PLAIN_MESSAGE, iconWarning);
                }
            }
        };
        ventanaPrincipal.itemEliminarUsuarios.addActionListener(aL);
    }

    private void oyenteAccion_ventanaCrearUsuario_BotonBuscarUsuarioPorCedula() {

        ActionListener aLi = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == gestionarUsuario.botonBuscarUsuarioPorCedula) {    // Boton buscar usuario de la ventana GestionarUsuario
                    Usuario usuarioEncontrado = new Usuario();

                    try {
                        int cedula = Integer.parseInt(gestionarUsuario.cajaBuscarPorCedula.getText());

                        if (sql.buscarUsuario(cedula) != null) {
                            usuarioEncontrado = sql.buscarUsuario(cedula);
                            idUsuarioBuscado = usuarioEncontrado.getIdUsuario();
                            nicknameEncontrado = usuarioEncontrado.getNickName();
                            gestionarUsuario.cajaPrimerNombre.setText(usuarioEncontrado.getpNombre());
                            gestionarUsuario.cajaSegundoNombre.setText(usuarioEncontrado.getsNombre());
                            gestionarUsuario.cajaPrimerApellido.setText(usuarioEncontrado.getpApellido());
                            gestionarUsuario.cajaSegundoApellido.setText(usuarioEncontrado.getsApellido());
                            gestionarUsuario.cajaCedula.setText(String.valueOf(usuarioEncontrado.getCedula()));
                            gestionarUsuario.comboCargo.setSelectedItem(usuarioEncontrado.getCargo().getNombreCargo());
                            gestionarUsuario.cajaCorreo.setText(usuarioEncontrado.getCorreo());
                            gestionarUsuario.cajaNickName.setText(usuarioEncontrado.getNickName());
                            gestionarUsuario.cajaPassword.setText(usuarioEncontrado.getPassword());

                            if (ventanaPrincipal.labelCargo.getText().equals("Administrador")) {
                                gestionarUsuario.cajaPassword.setEchoChar((char) 0);   // Descubirir Clave
                            }

                            gestionarUsuario.cajaPrimerNombre.setEditable(false);
                            gestionarUsuario.cajaSegundoNombre.setEditable(false);
                            gestionarUsuario.cajaPrimerApellido.setEditable(false);
                            gestionarUsuario.cajaSegundoApellido.setEditable(false);
                            gestionarUsuario.cajaCedula.setEditable(false);
                            gestionarUsuario.comboCargo.setEnabled(false);
                            gestionarUsuario.cajaCorreo.setEditable(false);
                            gestionarUsuario.cajaNickName.setEditable(false);
                            gestionarUsuario.cajaPassword.setEditable(false);

                            if (gestionarUsuario.labelTipoGestionUsuario.getText().equals("EDITAR USUARIOS")) {
                                gestionarUsuario.cajaPrimerNombre.setEnabled(true);
                                gestionarUsuario.cajaSegundoNombre.setEnabled(true);
                                gestionarUsuario.cajaPrimerApellido.setEnabled(true);
                                gestionarUsuario.cajaSegundoApellido.setEnabled(true);
                                gestionarUsuario.cajaCedula.setEnabled(true);
                                gestionarUsuario.comboCargo.setEnabled(true);
                                gestionarUsuario.cajaCorreo.setEnabled(true);
                                gestionarUsuario.cajaNickName.setEnabled(true);
                                gestionarUsuario.cajaPassword.setEnabled(true);
                                gestionarUsuario.boton_CrearEditarEliminar_Usuario.setEnabled(true);
                                gestionarUsuario.botonCancelar.setVisible(true);
                            }
                            if (gestionarUsuario.labelTipoGestionUsuario.getText().equals("ELIMINAR USUARIOS")) {
                                gestionarUsuario.boton_CrearEditarEliminar_Usuario.setEnabled(true);
                                gestionarUsuario.botonCancelar.setVisible(true);
                            }
                        } else if (sql.buscarUsuario(cedula) == null) {
                            JOptionPane.showMessageDialog(null, "El usuario buscado\nno existe en el sistema", " Usuario no existe", JOptionPane.PLAIN_MESSAGE, icon);
                        }
                    } catch (NumberFormatException nEx) {
                        JOptionPane.showMessageDialog(null, "Solo puedes ingresar numeros en el campo de identificacion\nsin punto,  sin coma.", " Validar cedula", JOptionPane.PLAIN_MESSAGE, icon);
                        //System.err.println("Error : "+nEx);
                    }
                }
            }
        };
        gestionarUsuario.botonBuscarUsuarioPorCedula.addActionListener(aLi);
    }

    private void oyenteAccion_VentanaCrearUsuario_BotonCrearUsuario_BotonEditarUsuario_BotonEliminarUsuario() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == gestionarUsuario.boton_CrearEditarEliminar_Usuario) {    // Boton crear usuario de la ventana GestionarUsuario
                    if (gestionarUsuario.cajaPrimerNombre.getText().equals("") || gestionarUsuario.cajaSegundoNombre.getText().equals("") || gestionarUsuario.cajaPrimerApellido.getText().equals("") || gestionarUsuario.cajaSegundoApellido.getText().equals("") || gestionarUsuario.cajaCedula.getText().equals("") || gestionarUsuario.comboCargo.getSelectedItem().toString().equals("Selecionar") || gestionarUsuario.cajaCorreo.getText().equals("") || gestionarUsuario.cajaNickName.getText().equals("") || gestionarUsuario.cajaPassword.getPassword().equals("")) {
                        JOptionPane.showMessageDialog(null, "Falta informacion por completar", "Faltan Datos", JOptionPane.PLAIN_MESSAGE, icon);

                    } else if (!gestionarUsuario.cajaPrimerNombre.getText().equals("") && !gestionarUsuario.cajaSegundoNombre.getText().equals("") && !gestionarUsuario.cajaPrimerApellido.getText().equals("") && !gestionarUsuario.cajaSegundoApellido.getText().equals("") && !gestionarUsuario.cajaCedula.getText().equals("") && !gestionarUsuario.comboCargo.getSelectedItem().toString().equals("Selecionar") && !gestionarUsuario.cajaCorreo.getText().equals("") && !gestionarUsuario.cajaNickName.getText().equals("") && !gestionarUsuario.cajaPassword.getPassword().equals("")) {

                        if (gestionarUsuario.labelTipoGestionUsuario.getText().equals("CREAR TU USUARIO")) {
                            String usuarioValidar = gestionarUsuario.cajaNickName.getText();
                            if (sql.verificarSiUsuarioExiste(usuarioValidar) == 0) {
                                Usuario user = new Usuario();
                                Cargo carg = new Cargo();
                                String pass = new String(gestionarUsuario.cajaPassword.getPassword());
                                int idDeCargo = 0;

                                user.setpNombre(gestionarUsuario.cajaPrimerNombre.getText());
                                user.setsNombre(gestionarUsuario.cajaSegundoNombre.getText());
                                user.setpApellido(gestionarUsuario.cajaPrimerApellido.getText());
                                user.setsApellido(gestionarUsuario.cajaSegundoApellido.getText());
                                try {
                                    user.setCedula(Integer.parseInt(gestionarUsuario.cajaCedula.getText()));

                                    if (gestionarUsuario.comboCargo.getSelectedItem().equals("Administrador")) {
                                        idDeCargo = 1;
                                    } else if (gestionarUsuario.comboCargo.getSelectedItem().equals("Farmaceutico")) {
                                        idDeCargo = 2;
                                    } else if (gestionarUsuario.comboCargo.getSelectedItem().equals("Auxiliar")) {
                                        idDeCargo = 3;
                                    }
                                    carg.setNombreCargo(String.valueOf(gestionarUsuario.comboCargo.getSelectedItem()));
                                    carg.setIdCargo(idDeCargo);
                                    user.setCargo(carg);
                                    user.setCorreo(gestionarUsuario.cajaCorreo.getText());
                                    user.setNickName(gestionarUsuario.cajaNickName.getText());
                                    user.setPassword(pass);
                                    user.setFechaUltimaSesion(fechaHora.format(date));

                                    if (sql.comprobarEmail(gestionarUsuario.cajaCorreo.getText())) {
                                        if (sql.ingresarUsuario(user, carg)) {
                                            limpiarCajasUsuario();
                                            gestionarUsuario.boton_CrearEditarEliminar_Usuario.setEnabled(false);
                                            JOptionPane.showMessageDialog(null, "Usuario Creado en el sistema", " Usuario Creado ", JOptionPane.PLAIN_MESSAGE, iconOk);
                                        } else {
                                            JOptionPane.showMessageDialog(null, "No se pudo acceder a la base de datos", " Error en la Base de datos", JOptionPane.PLAIN_MESSAGE, iconError);
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "El correo que usted ingreso no es un correo valido", " Validar correo", JOptionPane.PLAIN_MESSAGE, icon);
                                    }
                                } catch (NumberFormatException nEx) {
                                    JOptionPane.showMessageDialog(null, "Solo ingresar numeros en campo identificacion\\nsin punto,  sin coma.", " Validar cedula", JOptionPane.PLAIN_MESSAGE, icon);
                                    //System.err.println("Error : "+nEx); 
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "El usuario que usted intento ingresar ya existe ", " Validar usuario", JOptionPane.PLAIN_MESSAGE, icon);
                            }
                        } else if (gestionarUsuario.labelTipoGestionUsuario.getText().equals("EDITAR USUARIOS")) {

                            String usuarioValidar = gestionarUsuario.cajaNickName.getText();
                            if (sql.verificarSiUsuarioExiste(usuarioValidar) == 0 || nicknameEncontrado.equals(usuarioValidar)) {
                                Cargo carg = new Cargo();
                                Usuario user = new Usuario();
                                String pass = new String(gestionarUsuario.cajaPassword.getPassword());
                                int idDeCargo = 0;

                                user.setpNombre(gestionarUsuario.cajaPrimerNombre.getText());
                                user.setsNombre(gestionarUsuario.cajaSegundoNombre.getText());
                                user.setpApellido(gestionarUsuario.cajaPrimerApellido.getText());
                                user.setsApellido(gestionarUsuario.cajaSegundoApellido.getText());
                                try {
                                    user.setCedula(Integer.parseInt(gestionarUsuario.cajaCedula.getText()));

                                    if (gestionarUsuario.comboCargo.getSelectedItem().equals("Administrador")) {
                                        idDeCargo = 1;
                                    } else if (gestionarUsuario.comboCargo.getSelectedItem().equals("Farmaceutico")) {
                                        idDeCargo = 2;
                                    } else if (gestionarUsuario.comboCargo.getSelectedItem().equals("Auxiliar")) {
                                        idDeCargo = 3;
                                    }
                                    carg.setNombreCargo(String.valueOf(gestionarUsuario.comboCargo.getSelectedItem()));
                                    carg.setIdCargo(idDeCargo);
                                    user.setCargo(carg);
                                    user.setCorreo(gestionarUsuario.cajaCorreo.getText());
                                    user.setNickName(gestionarUsuario.cajaNickName.getText());
                                    user.setPassword(pass);
                                    user.setFechaUltimaSesion(fechaHora.format(date));

                                    if (sql.comprobarEmail(gestionarUsuario.cajaCorreo.getText())) {
                                        if (sql.editarUsuario(user, idUsuarioBuscado)) {
                                            limpiarCajasUsuario();
                                            gestionarUsuario.boton_CrearEditarEliminar_Usuario.setEnabled(false);
                                            JOptionPane.showMessageDialog(null, "Usuario Modificado exitosamente", " Usuario Modificado ", JOptionPane.PLAIN_MESSAGE, iconOk);
                                        } else {
                                            JOptionPane.showMessageDialog(null, "No se pudo acceder a la base de datos", " Error en la Base de datos", JOptionPane.PLAIN_MESSAGE, iconError);
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "El correo que usted ingreso no es un correo valido", " Validar correo", JOptionPane.PLAIN_MESSAGE, icon);
                                    }
                                } catch (NumberFormatException nEx) {
                                    JOptionPane.showMessageDialog(null, "Solo ingresar numeros en campo identificacion\\nsin punto,  sin coma.", " Validar cedula", JOptionPane.PLAIN_MESSAGE, icon);
                                    //System.err.println("Error : "+nEx);
                                }
                            } else if (sql.verificarSiUsuarioExiste(usuarioValidar) != 0) {
                                JOptionPane.showMessageDialog(null, "El usuario que usted intento ingresar ya existe ", " Validar usuario", JOptionPane.PLAIN_MESSAGE, icon);
                            }
                        } else if (gestionarUsuario.labelTipoGestionUsuario.getText().equals("ELIMINAR USUARIOS")) {

                            String usuarioValidar = gestionarUsuario.cajaNickName.getText();
                            Cargo carg = new Cargo();
                            Usuario user = new Usuario();

                            user.setsApellido(gestionarUsuario.cajaSegundoApellido.getText());

                            try {

                                user.setCedula(Integer.parseInt(gestionarUsuario.cajaCedula.getText()));

                                if (siNo == null) {
                                    siNo = new EstaSeguro(ventanaPrincipal, true);
                                }
                                oyenteAccion_NoEliminar();
                                oyenteAccion_SiEliminar();
                                windowClosing_VentanaEstaSeguro();
                                siNo.setVisible(true);

                                if (estarSeguro) {
                                    if (sql.eliminarUsuario(idUsuarioBuscado)) {
                                        limpiarCajasUsuario();
                                        gestionarUsuario.boton_CrearEditarEliminar_Usuario.setEnabled(false);
                                        JOptionPane.showMessageDialog(null, "Usuario Eliminado del sistema", " Usuario Eliminado ", JOptionPane.PLAIN_MESSAGE, iconOk);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "No se pudo acceder a la base de datos", " Error en la Base de datos", JOptionPane.PLAIN_MESSAGE, iconError);
                                    }
                                }
                            } catch (NumberFormatException nEx) {
                                JOptionPane.showMessageDialog(null, "Solo ingresar numeros en campo identificacion\\nsin punto,  sin coma.", " Validar cedula", JOptionPane.PLAIN_MESSAGE, icon);
                                //System.err.println("Error : "+nEx);
                            }
                        }
                    }
                }
            }
        };
        gestionarUsuario.boton_CrearEditarEliminar_Usuario.addActionListener(aL);
    }

    private void formWindowActivated_Usuario() {
        gestionarUsuario.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent evt) {
                gestionarUsuario.cajaBuscarPorCedula.requestFocus();
            }
        });
    }

    private void oyenteAccion_VentanaCrearUsuario_BotonCancelar() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCajasUsuario();
                gestionarUsuario.cajaBuscarPorCedula.setText(null);
                gestionarUsuario.dispose();
                gestionarUsuario = null;
            }
        };
        gestionarUsuario.botonCancelar.addActionListener(aL);
    }

    public void ventanaGestionarUsuario_WindowClossing() {
        gestionarUsuario.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                limpiarCajasUsuario();
                gestionarUsuario.cajaBuscarPorCedula.setText(null);
                gestionarUsuario.dispose();
                gestionarUsuario = null;
            }
        });
    }

    private void limpiarCajasUsuario() {  // limpiar cajas de texto de ventana crear usuario
        gestionarUsuario.cajaPrimerNombre.setText(null);
        gestionarUsuario.cajaSegundoNombre.setText(null);
        gestionarUsuario.cajaPrimerApellido.setText(null);
        gestionarUsuario.cajaSegundoApellido.setText(null);
        gestionarUsuario.cajaCedula.setText(null);
        gestionarUsuario.comboCargo.setSelectedIndex(0);
        gestionarUsuario.cajaCorreo.setText(null);
        gestionarUsuario.cajaNickName.setText(null);
        gestionarUsuario.cajaPassword.setText(null);
    }

    //------------------------------------------------------------------------------------------------------------Configuracion de iva y descuento
    private void ActionListener_ConfigurarValoresPorDefecto_IvaDescuento() {
        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (conf_IVA_Des == null) {
                    conf_IVA_Des = new Configurar_IVA_Descuentos(ventanaPrincipal, true);
                }
                botonCambiar_IVA_Descuentos();
                ventanaConf_IVA_Des_WindowClossing();
                botonCancelar_IVA_Descuentos();

                configuracionIvaDes configuracion = sql.Consultar_configurationDesIva();
                conf_IVA_Des.cajaDescuento.setText(String.valueOf(configuracion.getDescuento_configuracion_IvaDes()));
                conf_IVA_Des.cajaIVA.setText(String.valueOf(configuracion.getIva_configuracion_IvaDes()));

                conf_IVA_Des.setLocationRelativeTo(null);
                conf_IVA_Des.setVisible(true);
            }
        };
        ventanaPrincipal.itemConfIVADes.addActionListener(aL);
    }

    private void botonCambiar_IVA_Descuentos() {
        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    configuracionIvaDes configuracion = new configuracionIvaDes();
                    configuracion.setIva_configuracion_IvaDes(Integer.parseInt(conf_IVA_Des.cajaIVA.getText()));
                    configuracion.setDescuento_configuracion_IvaDes(Integer.parseInt(conf_IVA_Des.cajaDescuento.getText()));
                    if (sql.Modificar_configurationDesIva(configuracion)) {
                        JOptionPane.showMessageDialog(null, "Configuración Realizada en el sistema", "Configuración Exitosa", JOptionPane.PLAIN_MESSAGE, iconOk);
                    } else {
                        JOptionPane.showMessageDialog(null, "Configuración no realizada vuelve a intentarlo", "Configuración sin Exito", JOptionPane.PLAIN_MESSAGE, iconError);
                    }

                    conf_IVA_Des.dispose();
                    conf_IVA_Des = null;

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Escribir solo numeros", " Volver a intentarlo", JOptionPane.PLAIN_MESSAGE, iconError);
                }
            }
        };
        conf_IVA_Des.botonCambiar.addActionListener(aL);
    }

    private void botonCancelar_IVA_Descuentos() {
        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conf_IVA_Des.dispose();
                conf_IVA_Des = null;
            }
        };
        conf_IVA_Des.botonCancelar.addActionListener(aL);
    }

    private void ventanaConf_IVA_Des_WindowClossing() {
        conf_IVA_Des.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                conf_IVA_Des.dispose();
                conf_IVA_Des = null;
            }
        });
    }

    //-------------------------------------------------------------------------//configuracion informacion de empresa Cliente del Software
    private void ActionListener_Configurar_InfoEmpresaClienteDelSoftware() {
        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (ventanaPrincipal.labelCargo.getText().equals("Administrador")) {
                    if (conf_InfoEmpresa == null) {
                        conf_InfoEmpresa = new Configurar_InformacionEmpresaClienteDelSoftware(ventanaPrincipal, true);
                    }
                    ActionListener_Ventana_ConfInfoEmpresaClienteDelSoftware_botonOK();
                    windowListener_placeHolder_Configurar_InfoEmpresaClienteDelSoftware_cajaRazonSocial();
                    windowListener_placeHolder_Configurar_InfoEmpresaClienteDelSoftware_cajaNit();
                    windowListener_placeHolder_Configurar_InfoEmpresaClienteDelSoftware_CajaDireccion();
                    windowListener_placeHolder_Configurar_InfoEmpresaClienteDelSoftware_CelularContacto();
                    windowListener_placeHolder_Configurar_InfoEmpresaClienteDelSoftware_TelefonoContacto();
                    windowListener_placeHolder_Configurar_InfoEmpresaClienteDelSoftware_DepartamentoMunicipio();
                    ventanaConf_infoEmpresaClienteDelSoftware_WindowClossing();

                    EmpresaClienteDelSoftware empresaClienta = new EmpresaClienteDelSoftware();
                    empresaClienta = sql.ConsultarInformation_configuration();
                    conf_InfoEmpresa.cajaRazonSocial.setText(empresaClienta.getNombreEmpresaCDS());
                    conf_InfoEmpresa.cajaNit.setText(empresaClienta.getNitEmpresaCDS());
                    conf_InfoEmpresa.cajaDireccion.setText(empresaClienta.getDireccionEmpresaCDS());
                    conf_InfoEmpresa.cajaCelular.setText(empresaClienta.getCelularEmpresaCDS());
                    conf_InfoEmpresa.cajaTelefono.setText(empresaClienta.getTelefonoEmpresaCDS());
                    conf_InfoEmpresa.cajaUbicacion.setText(empresaClienta.getUbicacionEmpresaCDS());

                    conf_InfoEmpresa.cajaRazonSocial.setForeground(new Color(102, 102, 102));
                    conf_InfoEmpresa.cajaNit.setForeground(new Color(102, 102, 102));
                    conf_InfoEmpresa.cajaDireccion.setForeground(new Color(102, 102, 102));
                    conf_InfoEmpresa.cajaCelular.setForeground(new Color(102, 102, 102));
                    conf_InfoEmpresa.cajaTelefono.setForeground(new Color(102, 102, 102));
                    conf_InfoEmpresa.cajaUbicacion.setForeground(new Color(102, 102, 102));

                    conf_InfoEmpresa.setLocationRelativeTo(null);
                    conf_InfoEmpresa.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(ventanaPrincipal, "Usted no tiene permisos de administrador\npara realizar esta acción", "  No tiene permisos", JOptionPane.PLAIN_MESSAGE, iconWarning);
                }
            }
        };
        ventanaPrincipal.conf_infoEmpresaClienteDelSoftware.addActionListener(aL);
    }

    private void ActionListener_Ventana_ConfInfoEmpresaClienteDelSoftware_botonOK() {
        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmpresaClienteDelSoftware empresaClienta = new EmpresaClienteDelSoftware();
                empresaClienta.setNombreEmpresaCDS(conf_InfoEmpresa.cajaRazonSocial.getText());
                empresaClienta.setNitEmpresaCDS(conf_InfoEmpresa.cajaNit.getText());
                empresaClienta.setDireccionEmpresaCDS(conf_InfoEmpresa.cajaDireccion.getText());
                empresaClienta.setCelularEmpresaCDS(conf_InfoEmpresa.cajaCelular.getText());
                empresaClienta.setTelefonoEmpresaCDS(conf_InfoEmpresa.cajaTelefono.getText());
                empresaClienta.setUbicacionEmpresaCDS(conf_InfoEmpresa.cajaUbicacion.getText());

                if (sql.ModificarInformation_configuration(empresaClienta)) {
                    JOptionPane.showMessageDialog(null, "Configuración Realizada en el sistema", "Configuración Exitosa", JOptionPane.PLAIN_MESSAGE, iconOk);
                } else {
                    JOptionPane.showMessageDialog(null, "Configuración no realizada vuelve a intentarlo", "Configuración sin Exito", JOptionPane.PLAIN_MESSAGE, iconError);
                }

                conf_InfoEmpresa.dispose();
                conf_InfoEmpresa = null;
            }
        };
        conf_InfoEmpresa.botonOk.addActionListener(aL);
    }

    private void windowListener_placeHolder_Configurar_InfoEmpresaClienteDelSoftware_cajaRazonSocial() {
        FocusListener holder = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (conf_InfoEmpresa.cajaRazonSocial.getText().equals("Nombre de la Empresa") || conf_InfoEmpresa.cajaRazonSocial.getText().equals("")) {
                    conf_InfoEmpresa.cajaRazonSocial.setText("");
                    conf_InfoEmpresa.cajaRazonSocial.setForeground(new Color(102, 102, 102));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (conf_InfoEmpresa != null && conf_InfoEmpresa.cajaRazonSocial.getText().equals("")) {
                    conf_InfoEmpresa.cajaRazonSocial.setText("Nombre de la Empresa");
                    conf_InfoEmpresa.cajaRazonSocial.setForeground(new Color(204, 204, 204));
                }
            }
        };
        conf_InfoEmpresa.cajaRazonSocial.addFocusListener(holder);
    }

    private void windowListener_placeHolder_Configurar_InfoEmpresaClienteDelSoftware_cajaNit() {
        FocusListener holder = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (conf_InfoEmpresa.cajaNit.getText().equals("Nit. la empresa - Cedula representante legal") || conf_InfoEmpresa.cajaNit.getText().equals("")) {
                    conf_InfoEmpresa.cajaNit.setText("");
                    conf_InfoEmpresa.cajaNit.setForeground(new Color(102, 102, 102));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (conf_InfoEmpresa != null && conf_InfoEmpresa.cajaNit.getText().equals("")) {
                    conf_InfoEmpresa.cajaNit.setText("Nit. la empresa - Cedula representante legal");
                    conf_InfoEmpresa.cajaNit.setForeground(new Color(204, 204, 204));
                }
            }
        };
        conf_InfoEmpresa.cajaNit.addFocusListener(holder);
    }

    private void windowListener_placeHolder_Configurar_InfoEmpresaClienteDelSoftware_CajaDireccion() {
        FocusListener holder = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (conf_InfoEmpresa.cajaDireccion.getText().equals("Direccion Empresa") || conf_InfoEmpresa.cajaDireccion.getText().equals("")) {
                    conf_InfoEmpresa.cajaDireccion.setText("");
                    conf_InfoEmpresa.cajaDireccion.setForeground(new Color(102, 102, 102));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (conf_InfoEmpresa != null && conf_InfoEmpresa.cajaDireccion.getText().equals("")) {
                    conf_InfoEmpresa.cajaDireccion.setText("Direccion Empresa");
                    conf_InfoEmpresa.cajaDireccion.setForeground(new Color(204, 204, 204));
                }
            }
        };
        conf_InfoEmpresa.cajaDireccion.addFocusListener(holder);
    }

    private void windowListener_placeHolder_Configurar_InfoEmpresaClienteDelSoftware_CelularContacto() {
        FocusListener holder = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (conf_InfoEmpresa.cajaCelular.getText().equals("Celular de contacto") || conf_InfoEmpresa.cajaCelular.getText().equals("")) {
                    conf_InfoEmpresa.cajaCelular.setText("");
                    conf_InfoEmpresa.cajaCelular.setForeground(new Color(102, 102, 102));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (conf_InfoEmpresa != null && conf_InfoEmpresa.cajaCelular.getText().equals("")) {
                    conf_InfoEmpresa.cajaCelular.setText("Celular de contacto");
                    conf_InfoEmpresa.cajaCelular.setForeground(new Color(204, 204, 204));
                }
            }
        };
        conf_InfoEmpresa.cajaCelular.addFocusListener(holder);
    }

    private void windowListener_placeHolder_Configurar_InfoEmpresaClienteDelSoftware_TelefonoContacto() {
        FocusListener holder = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (conf_InfoEmpresa.cajaTelefono.getText().equals("Telefono de contacto") || conf_InfoEmpresa.cajaTelefono.getText().equals("")) {
                    conf_InfoEmpresa.cajaTelefono.setText("");
                    conf_InfoEmpresa.cajaTelefono.setForeground(new Color(102, 102, 102));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (conf_InfoEmpresa != null && conf_InfoEmpresa.cajaTelefono.getText().equals("")) {
                    conf_InfoEmpresa.cajaTelefono.setText("Telefono de contacto");
                    conf_InfoEmpresa.cajaTelefono.setForeground(new Color(204, 204, 204));
                }
            }
        };
        conf_InfoEmpresa.cajaTelefono.addFocusListener(holder);
    }

    private void windowListener_placeHolder_Configurar_InfoEmpresaClienteDelSoftware_DepartamentoMunicipio() {
        FocusListener holder = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (conf_InfoEmpresa.cajaUbicacion.getText().equals("Departamento - Municipio") || conf_InfoEmpresa.cajaUbicacion.getText().equals("")) {
                    conf_InfoEmpresa.cajaUbicacion.setText("");
                    conf_InfoEmpresa.cajaUbicacion.setForeground(new Color(102, 102, 102));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (conf_InfoEmpresa != null && conf_InfoEmpresa.cajaUbicacion.getText().equals("")) {
                    conf_InfoEmpresa.cajaUbicacion.setText("Departamento - Municipio");
                    conf_InfoEmpresa.cajaUbicacion.setForeground(new Color(204, 204, 204));
                }
            }
        };
        conf_InfoEmpresa.cajaUbicacion.addFocusListener(holder);
    }

    private void ventanaConf_infoEmpresaClienteDelSoftware_WindowClossing() {
        conf_InfoEmpresa.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                conf_InfoEmpresa.dispose();
                conf_InfoEmpresa = null;
            }
        });
    }

    //----------------------------------------------------------------------------------------------------------------------//Ventana Gestionar Clientes
    private void oyenteAccion_VentanaPrincipal_BarMenuGestionar_itemClientes() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == ventanaPrincipal.itemGestionarClientes) {   // Item Gestionar Clientes
                    if (gestionarCliente == null) {
                        gestionarCliente = new GestionarCliente(ventanaPrincipal, false);
                    }
                    formWindowActivated_Clientes();
                    oyenteAccion_VentanaGestionarClientes_BotonCrearCliente();
                    oyenteAccion_VentanaGestionarClientes_BotonBuscarCliente();
                    oyenteAccion_VentanaGestionarClientes_BotonEditarClientes();
                    oyenteAccion_VentanaGestionarClientes_BotonEliminarClientes();
                    oyenteAccion_VentanaGestionarClientes_BotonCancelar();
                    ventanaGestionarCliente_WindowClossing();

                    gestionarCliente.cajaNombre.setEnabled(true);
                    gestionarCliente.cajaApellido.setEnabled(true);
                    gestionarCliente.cajaCedula.setEnabled(true);
                    gestionarCliente.cajaDireccion.setEnabled(true);
                    gestionarCliente.cajaCorreo.setEnabled(true);
                    gestionarCliente.cajaCelular.setEnabled(true);

                    gestionarCliente.cajaBuscarPorCedula.setVisible(true);
                    gestionarCliente.botonBuscarPorCedula.setVisible(true);
                    gestionarCliente.botonCrear.setVisible(true);
                    gestionarCliente.botonEditar.setVisible(true);
                    gestionarCliente.botonEliminar.setVisible(true);
                    gestionarCliente.botonCancelar.setVisible(true);

                    gestionarCliente.botonCrear.setEnabled(true);
                    gestionarCliente.botonEditar.setEnabled(false);
                    gestionarCliente.botonEliminar.setEnabled(false);
                    gestionarCliente.botonCancelar.setEnabled(true);
                    gestionarCliente.cajaBuscarPorCedula.requestFocus();

                    gestionarCliente.setLocationRelativeTo(null);
                    gestionarCliente.setVisible(true);
                }
            }
        };
        ventanaPrincipal.itemGestionarClientes.addActionListener(aL);
    }

    private void oyenteAccion_VentanaGestionarClientes_BotonCrearCliente() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == gestionarCliente.botonCrear) {    // Boton crear cliente de la ventana Gestionar Cliente
                    if (gestionarCliente.cajaNombre.getText().equals("") || gestionarCliente.cajaApellido.getText().equals("") || gestionarCliente.cajaCedula.getText().equals("") || gestionarCliente.cajaCorreo.getText().equals("") || gestionarCliente.cajaCelular.getText().equals("") || gestionarCliente.cajaDireccion.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Falta informacion por completar", "Faltan Datos", JOptionPane.PLAIN_MESSAGE, icon);

                    } else if (!gestionarCliente.cajaNombre.getText().equals("") && !gestionarCliente.cajaApellido.getText().equals("") && !gestionarCliente.cajaCedula.getText().equals("") && !gestionarCliente.cajaCorreo.getText().equals("") && !gestionarCliente.cajaCelular.getText().equals("") && !gestionarCliente.cajaDireccion.getText().equals("")) {

                        cliente = new Cliente();

                        cliente.setNombre(gestionarCliente.cajaNombre.getText());
                        cliente.setApellido(gestionarCliente.cajaApellido.getText());

                        try {

                            cliente.setCedula(Integer.parseInt(gestionarCliente.cajaCedula.getText()));
                            int cedulaClienteValidar = Integer.parseInt(gestionarCliente.cajaCedula.getText());
                            cliente.setCorreo(gestionarCliente.cajaCorreo.getText());
                            cliente.setCelular(gestionarCliente.cajaCelular.getText());
                            cliente.setDireccion(gestionarCliente.cajaDireccion.getText());

                            if (sql.verificarSiClienteExiste(cedulaClienteValidar) == 0) {

                                if (sql.comprobarEmail(gestionarCliente.cajaCorreo.getText())) {
                                    if (sql.ingresarCliente(cliente)) {
                                        limpiarCajasCliente();
                                        JOptionPane.showMessageDialog(null, "Cliente ingresado con exito", " Cliente Creado ", JOptionPane.PLAIN_MESSAGE, iconOk);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "No se pudo acceder a la base de datos", " Error en la Base de datos", JOptionPane.PLAIN_MESSAGE, iconError);
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "El correo que usted ingreso no es un correo valido", " Validar correo", JOptionPane.PLAIN_MESSAGE, icon);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Ya existe un cliente en nuestro sistema\ncon el numero de cedula ingresado", " Validar Cliente", JOptionPane.PLAIN_MESSAGE, icon);
                            }
                        } catch (NumberFormatException nEx) {
                            JOptionPane.showMessageDialog(null, "Solo ingresar numeros en campo identificacion\nsin punto,  sin coma.", " Validar cedula", JOptionPane.PLAIN_MESSAGE, icon);
                            //System.err.println("Error : "+nEx);
                        }
                    }
                }
            }
        };
        gestionarCliente.botonCrear.addActionListener(aL);
    }

    private void oyenteAccion_VentanaGestionarClientes_BotonBuscarCliente() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == gestionarCliente.botonBuscarPorCedula) {    // Boton buscar cliente ventana GestionarCliente

                    try {

                        int cedulaClienteValidar = Integer.parseInt(gestionarCliente.cajaBuscarPorCedula.getText());

                        if (sql.buscarCliente(cedulaClienteValidar) != null) {
                            cliente = new Cliente();
                            cliente = sql.buscarCliente(cedulaClienteValidar);
                            idClienteEncontrado = cliente.getIdCliente();
                            cedulaClienteEncontrada = cliente.getCedula();
                            gestionarCliente.cajaNombre.setText(cliente.getNombre());
                            gestionarCliente.cajaApellido.setText(cliente.getApellido());
                            gestionarCliente.cajaCedula.setText(String.valueOf(cliente.getCedula()));
                            gestionarCliente.cajaCorreo.setText(cliente.getCorreo());
                            gestionarCliente.cajaCelular.setText(cliente.getCelular());
                            gestionarCliente.cajaDireccion.setText(cliente.getDireccion());
                            gestionarCliente.botonEditar.setEnabled(true);
                            gestionarCliente.botonEliminar.setEnabled(true);
                            gestionarCliente.cajaBuscarPorCedula.setText(null);

                        } else {
                            limpiarCajasCliente();
                            JOptionPane.showMessageDialog(null, "No se pudo encontrar el cliente", " Cliente no existe", JOptionPane.PLAIN_MESSAGE, iconError);
                        }

                    } catch (NumberFormatException nEx) {
                        JOptionPane.showMessageDialog(null, "Solo ingresar numeros en campo identificacion\nsin punto,  sin coma.", " Validar cedula", JOptionPane.PLAIN_MESSAGE, icon);
                        //System.err.println("Error : "+nEx);
                    }
                }
            }
        };
        gestionarCliente.botonBuscarPorCedula.addActionListener(aL);
    }

    private void oyenteAccion_VentanaGestionarClientes_BotonEditarClientes() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == gestionarCliente.botonEditar) {    // Boton editar cliente ventana GestionarCliente

                    if (gestionarCliente.cajaNombre.getText().equals("") || gestionarCliente.cajaApellido.getText().equals("") || gestionarCliente.cajaCedula.getText().equals("") || gestionarCliente.cajaCorreo.getText().equals("") || gestionarCliente.cajaCelular.getText().equals("") || gestionarCliente.cajaDireccion.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Falta informacion por completar", "Faltan Datos", JOptionPane.PLAIN_MESSAGE, icon);

                    } else if (!gestionarCliente.cajaNombre.getText().equals("") && !gestionarCliente.cajaApellido.getText().equals("") && !gestionarCliente.cajaCedula.getText().equals("") && !gestionarCliente.cajaCorreo.getText().equals("") && !gestionarCliente.cajaCelular.getText().equals("") && !gestionarCliente.cajaDireccion.getText().equals("")) {

                        cliente = new Cliente();

                        cliente.setNombre(gestionarCliente.cajaNombre.getText());
                        cliente.setApellido(gestionarCliente.cajaApellido.getText());

                        try {

                            cliente.setCedula(Integer.parseInt(gestionarCliente.cajaCedula.getText()));
                            int cedulaClienteValidar = Integer.parseInt(gestionarCliente.cajaCedula.getText());

                            if (sql.verificarSiClienteExiste(cedulaClienteValidar) == 0 || cedulaClienteEncontrada == cedulaClienteValidar) {

                                if (sql.comprobarEmail(gestionarCliente.cajaCorreo.getText())) {
                                    cliente.setCorreo(gestionarCliente.cajaCorreo.getText());
                                    cliente.setCelular(gestionarCliente.cajaCelular.getText());
                                    cliente.setDireccion(gestionarCliente.cajaDireccion.getText());

                                    if (sql.editarCliente(cliente, idClienteEncontrado)) {
                                        limpiarCajasCliente();
                                        JOptionPane.showMessageDialog(null, "Cliente Modificado exitosamente", " Cliente Modificado ", JOptionPane.PLAIN_MESSAGE, iconOk);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "No se pudo acceder a la base de datos", " Error en la Base de datos", JOptionPane.PLAIN_MESSAGE, iconError);
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "El correo que usted ingreso no es un correo valido", " Validar correo", JOptionPane.PLAIN_MESSAGE, icon);
                                }
                            } else if (sql.verificarSiClienteExiste(cedulaClienteValidar) != 0) {
                                JOptionPane.showMessageDialog(null, "El Cliente que usted intento ingresar ya existe ", " Validar Cliente", JOptionPane.PLAIN_MESSAGE, icon);
                            }
                        } catch (NumberFormatException nEx) {
                            JOptionPane.showMessageDialog(null, "Solo ingresar numeros en campo identificacion\\nsin punto,  sin coma.", " Validar cedula", JOptionPane.PLAIN_MESSAGE, icon);
                            //System.err.println("Error : "+nEx);
                        }
                    }
                }
            }
        };
        gestionarCliente.botonEditar.addActionListener(aL);
    }

    private void oyenteAccion_VentanaGestionarClientes_BotonEliminarClientes() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == gestionarCliente.botonEliminar) {
                    if (siNo == null) {
                        siNo = new EstaSeguro(ventanaPrincipal, true);
                    }
                    oyenteAccion_NoEliminar();
                    oyenteAccion_SiEliminar();
                    windowClosing_VentanaEstaSeguro();
                    siNo.setVisible(true);

                    if (estarSeguro) {
                        if (sql.eliminarCliente(idClienteEncontrado)) {
                            limpiarCajasCliente();
                            JOptionPane.showMessageDialog(null, "Cliente Eliminado exitosamente", " Cliente Eliminado ", JOptionPane.PLAIN_MESSAGE, iconOk);
                        } else {
                            JOptionPane.showMessageDialog(null, "No se pudo acceder a la base de datos", " Error en la Base de datos", JOptionPane.PLAIN_MESSAGE, iconError);
                        }
                    }
                }
            }
        };
        gestionarCliente.botonEliminar.addActionListener(aL);
    }

    private void formWindowActivated_Clientes() {
        gestionarCliente.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent evt) {
                gestionarCliente.cajaBuscarPorCedula.requestFocus();
            }
        });
    }

    private void oyenteAccion_VentanaGestionarClientes_BotonCancelar() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Menu Archivo item Cancelar
                limpiarCajasCliente();
                gestionarCliente.dispose();
                gestionarCliente = null;
                idClienteEncontrado = 0;
            }
        };
        gestionarCliente.botonCancelar.addActionListener(aL);
    }

    private void limpiarCajasCliente() { // terminar de arreglar el error al abrir la ventana gestionar usuario desde el modulo de ventas
        gestionarCliente.cajaBuscarPorCedula.setText(null);
        gestionarCliente.cajaNombre.setText(null);
        gestionarCliente.cajaApellido.setText(null);
        gestionarCliente.cajaCedula.setText(null);
        gestionarCliente.cajaCorreo.setText(null);
        gestionarCliente.cajaCelular.setText(null);
        gestionarCliente.cajaDireccion.setText(null);
    }

    public void ventanaGestionarCliente_WindowClossing() {
        gestionarCliente.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                limpiarCajasCliente();
                gestionarCliente.dispose();
                gestionarCliente = null;
                idClienteEncontrado = 0;
            }
        });
    }

    //-------------------------------------------------------------------------------------------------------------------------------//Ventana Gestionar Laboratorio
    private void oyenteAccion_VentanaPrincipal_BarMenuGestionar_itemLaboratorio() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == ventanaPrincipal.itemGestionarLaboratorios) {

                    if (gestionarLaboratorio == null) {
                        gestionarLaboratorio = new GestionarLaboratorio(ventanaPrincipal, false);
                    }

                    oyenteAccion_VentanaGestionarLaboratorio_BotonCrearLaboratorio();
                    oyenteAccion_VentanaGestionarLaboratorio_BotonBuscarLaboratorio();
                    oyenteAccion_VentanaGestionarLaboratorio_BotonEditarLaboratorio();
                    oyenteAccion_VentanaGestionarLaboratorio_BotonEliminarLaboratorio();
                    oyenteAccion_VentanaGestionarLaboratorio_BotonCancelar();
                    ventanaGestionarLaboratorio_WindowClossing();

                    gestionarLaboratorio.cajaNombreEditado.setVisible(false);
                    gestionarLaboratorio.labelNombreEditado.setVisible(false);
                    gestionarLaboratorio.botonCrear.setEnabled(true);
                    gestionarLaboratorio.botonBuscar.setEnabled(true);
                    gestionarLaboratorio.botonCancelar.setEnabled(true);
                    gestionarLaboratorio.botonEditar.setEnabled(false);
                    gestionarLaboratorio.botonEliminar.setEnabled(false);
                    gestionarLaboratorio.labelLaboratorioSelecionado.setVisible(false);
                    gestionarLaboratorio.labelLaboratorioSelecionado1.setVisible(false);
                    gestionarLaboratorio.labelMuestraLaboratorio.setVisible(false);

                    gestionarLaboratorio.setLocationRelativeTo(null);
                    gestionarLaboratorio.setVisible(true);
                }
            }
        };
        ventanaPrincipal.itemGestionarLaboratorios.addActionListener(aL);
    }

    private void oyenteAccion_VentanaGestionarLaboratorio_BotonCrearLaboratorio() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == gestionarLaboratorio.botonCrear) {
                    if (gestionarLaboratorio.cajaNombreNuevo.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Ingresar nombre del Laboratorio", "Faltan Datos", JOptionPane.PLAIN_MESSAGE, icon);

                    } else if (!gestionarLaboratorio.cajaNombreNuevo.getText().equals("")) {

                        Laboratorio laboratorio = new Laboratorio();

                        laboratorio.setNombre(gestionarLaboratorio.cajaNombreNuevo.getText());

                        if (sql.verificarSiLaboratorioExiste(laboratorio.getNombre()) == 0) {
                            if (sql.ingresarLaboratorio(laboratorio)) {
                                limpiarCajasLaboratorio();
                                JOptionPane.showMessageDialog(null, "Laboratorio ingresado con exito", " Laboratorio Ingresado ", JOptionPane.PLAIN_MESSAGE, iconOk);
                            } else {
                                JOptionPane.showMessageDialog(null, "No se pudo acceder a la base de datos", " Error en la Base de datos", JOptionPane.PLAIN_MESSAGE, iconError);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Ya existe un Laboratorio en nuestro sistema\ncon el nombre ingresado", " Validar Laboratorio", JOptionPane.PLAIN_MESSAGE, icon);
                        }
                    }
                }
            }
        };
        gestionarLaboratorio.botonCrear.addActionListener(aL);
    }

    private void oyenteAccion_VentanaGestionarLaboratorio_BotonBuscarLaboratorio() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == gestionarLaboratorio.botonBuscar) {
                    String nombreBuscar = gestionarLaboratorio.cajaNombreBuscar.getText();

                    if (sql.buscarLaboratorio(nombreBuscar) != null) {
                        Laboratorio LaboratorioEncontrado = new Laboratorio();
                        LaboratorioEncontrado = sql.buscarLaboratorio(nombreBuscar);

                        gestionarLaboratorio.cajaNombreBuscar.setText(LaboratorioEncontrado.getNombre());

                        idLaboratorioEncontrado = LaboratorioEncontrado.getIdLaboratorio();
                        nombreLaboratorioEncontrado = LaboratorioEncontrado.getNombre();

                        gestionarLaboratorio.botonEditar.setEnabled(true);
                        gestionarLaboratorio.botonEliminar.setEnabled(true);

                        JOptionPane.showMessageDialog(null, "Laboratorio encontrado\nPuedes editar o eliminarlo del sistema", " Laboratorio Existente", JOptionPane.PLAIN_MESSAGE, iconOk);
                        gestionarLaboratorio.labelLaboratorioSelecionado.setVisible(true);
                        gestionarLaboratorio.labelLaboratorioSelecionado1.setVisible(true);
                        gestionarLaboratorio.labelMuestraLaboratorio.setVisible(true);
                        gestionarLaboratorio.labelMuestraLaboratorio.setText(LaboratorioEncontrado.getNombre());
                        gestionarLaboratorio.cajaNombreEditado.setVisible(true);
                        gestionarLaboratorio.labelNombreEditado.setVisible(true);

                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo encontrar Laboratorio:\n" + gestionarLaboratorio.cajaNombreBuscar.getText() + "  ", " Laboratorio no existe", JOptionPane.PLAIN_MESSAGE, iconError);
                    }
                }
            }
        };
        gestionarLaboratorio.botonBuscar.addActionListener(aL);
    }

    private void oyenteAccion_VentanaGestionarLaboratorio_BotonEditarLaboratorio() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == gestionarLaboratorio.botonEditar) {

                    if (gestionarLaboratorio.cajaNombreEditado.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Debe editar el nombre en el campo Nombre", "Faltan Datos", JOptionPane.PLAIN_MESSAGE, icon);

                    } else if (!gestionarLaboratorio.cajaNombreEditado.getText().equals("")) {

                        Laboratorio laboratorio = new Laboratorio();

                        laboratorio.setNombre(gestionarLaboratorio.cajaNombreEditado.getText());

                        String laboratorioaValidar = gestionarLaboratorio.cajaNombreEditado.getText();

                        if (sql.verificarSiLaboratorioExiste(laboratorioaValidar) == 0 || laboratorioaValidar.equals(nombreLaboratorioEncontrado)) {

                            if (sql.editarLaboratorio(laboratorio, idLaboratorioEncontrado)) {
                                limpiarCajasLaboratorio();
                                gestionarLaboratorio.labelMuestraLaboratorio.setText(laboratorioaValidar);
                                gestionarLaboratorio.cajaNombreBuscar.setText(laboratorioaValidar);
                                JOptionPane.showMessageDialog(null, "Laboratorio Modificado exitosamente\nNuevo nombre : " + laboratorioaValidar + " ", "  Laboratorio Modificado ", JOptionPane.PLAIN_MESSAGE, iconOk);
                            } else {
                                JOptionPane.showMessageDialog(null, "No se pudo acceder a la base de datos", " Error en la Base de datos", JOptionPane.PLAIN_MESSAGE, iconError);
                            }
                        } else if (sql.verificarSiLaboratorioExiste(laboratorioaValidar) != 0) {
                            JOptionPane.showMessageDialog(null, "El Laboratorio que usted intento ingresar ya existe ", " Validar Laboratorio", JOptionPane.PLAIN_MESSAGE, icon);
                        }
                    }
                }
            }
        };
        gestionarLaboratorio.botonEditar.addActionListener(aL);
    }

    private void oyenteAccion_VentanaGestionarLaboratorio_BotonEliminarLaboratorio() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == gestionarLaboratorio.botonEliminar) {

                    if (siNo == null) {
                        siNo = new EstaSeguro(ventanaPrincipal, true);
                    }
                    oyenteAccion_NoEliminar();
                    oyenteAccion_SiEliminar();
                    windowClosing_VentanaEstaSeguro();
                    siNo.setVisible(true);

                    if (estarSeguro) {
                        if (sql.eliminarLaboratorio(idLaboratorioEncontrado)) {
                            limpiarCajasLaboratorio();
                            gestionarLaboratorio.labelMuestraLaboratorio.setText(null);
                            JOptionPane.showMessageDialog(null, "Laboratorio Eliminado exitosamente", " Laboratorio Eliminado ", JOptionPane.PLAIN_MESSAGE, iconOk);
                        } else {
                            JOptionPane.showMessageDialog(null, "No se pudo acceder a la base de datos", " Error en la Base de datos", JOptionPane.PLAIN_MESSAGE, iconError);
                        }
                    }
                }
            }
        };
        gestionarLaboratorio.botonEliminar.addActionListener(aL);
    }

    private void oyenteAccion_VentanaGestionarLaboratorio_BotonCancelar() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == gestionarLaboratorio.botonCancelar) {
                    limpiarCajasLaboratorio();
                    gestionarLaboratorio.dispose();
                    gestionarLaboratorio = null;
                }
            }
        };
        gestionarLaboratorio.botonCancelar.addActionListener(aL);
    }

    private void limpiarCajasLaboratorio() {
        gestionarLaboratorio.cajaNombreNuevo.setText(null);
        gestionarLaboratorio.cajaNombreBuscar.setText(null);
        gestionarLaboratorio.cajaNombreEditado.setText(null);
    }

    public void ventanaGestionarLaboratorio_WindowClossing() {
        gestionarLaboratorio.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                limpiarCajasLaboratorio();
                gestionarLaboratorio.dispose();
                gestionarLaboratorio = null;
            }
        });
    }

    //-------------------------------------------------------------------------------------------------------------------------------------//Ventana Gestionar Proveedres
    private void oyenteAccion_VentanaPrincipal_BarMenuGestionar_itemProveedores() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == ventanaPrincipal.itemGestionarProveedores) {

                    if (gestionarProveedor == null) {
                        gestionarProveedor = new GestionarProveedor(ventanaPrincipal, false);
                    }

                    oyenteAccion_VentanaGestionarProveedores_BotonCrearProveedores();
                    oyenteAccion_VentanaGestionarProveedores_BotonBuscarProveedores();
                    oyenteAccion_VentanaGestionarProveedores_BotonEditarProveedores();
                    oyenteAccion_VentanaGestionarProveedores_BotonEliminarProveedores();
                    oyenteAccion_VentanaGestionarProveedores_BotonCancelar();
                    formWindowActivated_Proveedor();
                    ventanaGestionarProveedores_WindowClossing();

                    gestionarProveedor.cajaBuscarPorCedula.setEnabled(true);
                    gestionarProveedor.cajaNombre.setEnabled(true);
                    gestionarProveedor.cajaApellido.setEnabled(true);
                    gestionarProveedor.cajaCedula.setEnabled(true);
                    gestionarProveedor.cajaCelular.setEnabled(true);
                    gestionarProveedor.cajaCorreo.setEnabled(true);
                    gestionarProveedor.cajaNombreEmpresa.setEnabled(true);
                    gestionarProveedor.cajaTelEmpresa.setEnabled(true);
                    gestionarProveedor.cajaDirEmpresa.setEnabled(true);

                    gestionarProveedor.botonBuscarPorCedula.setEnabled(true);
                    gestionarProveedor.botonCrear.setEnabled(true);
                    gestionarProveedor.botonEditar.setEnabled(false);
                    gestionarProveedor.botonEliminar.setEnabled(false);
                    gestionarProveedor.botonCancelar.setEnabled(true);

                    gestionarProveedor.setLocationRelativeTo(null);
                    gestionarProveedor.setVisible(true);
                }
            }
        };
        ventanaPrincipal.itemGestionarProveedores.addActionListener(aL);
    }

    private void oyenteAccion_VentanaGestionarProveedores_BotonCrearProveedores() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == gestionarProveedor.botonCrear) {
                    if (gestionarProveedor.cajaNombre.getText().equals("") || gestionarProveedor.cajaApellido.getText().equals("") || gestionarProveedor.cajaCedula.getText().equals("") || gestionarProveedor.cajaCorreo.getText().equals("") || gestionarProveedor.cajaCelular.getText().equals("") || gestionarProveedor.cajaNombreEmpresa.getText().equals("") || gestionarProveedor.cajaTelEmpresa.getText().equals("") || gestionarProveedor.cajaDirEmpresa.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Falta informacion por completar", "Faltan Datos", JOptionPane.PLAIN_MESSAGE, icon);

                    } else if (!gestionarProveedor.cajaNombre.getText().equals("") || !gestionarProveedor.cajaApellido.getText().equals("") || !gestionarProveedor.cajaCedula.getText().equals("") || !gestionarProveedor.cajaCorreo.getText().equals("") || !gestionarProveedor.cajaCelular.getText().equals("") || !gestionarProveedor.cajaNombreEmpresa.getText().equals("") || !gestionarProveedor.cajaTelEmpresa.getText().equals("") || !gestionarProveedor.cajaDirEmpresa.getText().equals("")) {

                        Proveedor proveedor = new Proveedor();

                        proveedor.setNombre(gestionarProveedor.cajaNombre.getText());
                        proveedor.setApellido(gestionarProveedor.cajaApellido.getText());

                        try {

                            proveedor.setCedula(Integer.parseInt(gestionarProveedor.cajaCedula.getText()));
                            int cedulaProveedorValidar = Integer.parseInt(gestionarProveedor.cajaCedula.getText());

                            proveedor.setCelular(gestionarProveedor.cajaCelular.getText());
                            proveedor.setCorreo(gestionarProveedor.cajaCorreo.getText());
                            proveedor.setNombreEmpresa(gestionarProveedor.cajaNombreEmpresa.getText());
                            proveedor.setTelEmpresa(gestionarProveedor.cajaTelEmpresa.getText());
                            proveedor.setDirEmpresa(gestionarProveedor.cajaDirEmpresa.getText());

                            if (sql.verificarSiProveedorExiste(cedulaProveedorValidar) == 0) {

                                if (sql.comprobarEmail(gestionarProveedor.cajaCorreo.getText())) {
                                    if (sql.ingresarProveedor(proveedor)) {
                                        limpiarCajasProveedor();
                                        JOptionPane.showMessageDialog(null, "Proveedor ingresado con exito", " ProveedorCreado ", JOptionPane.PLAIN_MESSAGE, iconOk);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "No se pudo acceder a la base de datos", " Error en la Base de datos", JOptionPane.PLAIN_MESSAGE, iconError);
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "El correo que usted ingreso no es un correo valido", " Validar correo", JOptionPane.PLAIN_MESSAGE, icon);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Ya existe un proveedor en nuestro sistema\ncon el numero de cedula ingresado", " Validar Proveedor", JOptionPane.PLAIN_MESSAGE, icon);
                            }
                        } catch (NumberFormatException nEx) {
                            JOptionPane.showMessageDialog(null, "Solo ingresar numeros en campo identificacion\nsin punto,  sin coma.", " Validar cedula", JOptionPane.PLAIN_MESSAGE, icon);
                            //System.err.println("Error : "+nEx);
                        }
                    }
                }
            }
        };
        gestionarProveedor.botonCrear.addActionListener(aL);
    }

    private void oyenteAccion_VentanaGestionarProveedores_BotonBuscarProveedores() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == gestionarProveedor.botonBuscarPorCedula) {

                    try {

                        int cedulaProveedorValidar = Integer.parseInt(gestionarProveedor.cajaBuscarPorCedula.getText());

                        if (sql.buscarProveedor(cedulaProveedorValidar) != null) {
                            Proveedor proveedorEncontrado = new Proveedor();
                            proveedorEncontrado = sql.buscarProveedor(cedulaProveedorValidar);

                            idProveedorEncontrado = proveedorEncontrado.getIdProveedor();
                            cedulaProveedorEncontrada = proveedorEncontrado.getCedula();

                            gestionarProveedor.cajaNombre.setText(proveedorEncontrado.getNombre());
                            gestionarProveedor.cajaApellido.setText(proveedorEncontrado.getApellido());
                            gestionarProveedor.cajaCedula.setText(String.valueOf(proveedorEncontrado.getCedula()));
                            gestionarProveedor.cajaCelular.setText(proveedorEncontrado.getCelular());
                            gestionarProveedor.cajaCorreo.setText(proveedorEncontrado.getCorreo());
                            gestionarProveedor.cajaNombreEmpresa.setText(proveedorEncontrado.getNombreEmpresa());
                            gestionarProveedor.cajaTelEmpresa.setText(proveedorEncontrado.getTelEmpresa());
                            gestionarProveedor.cajaDirEmpresa.setText(proveedorEncontrado.getDirEmpresa());
                            gestionarProveedor.cajaBuscarPorCedula.setText(null);

                            gestionarProveedor.botonEditar.setEnabled(true);
                            gestionarProveedor.botonEliminar.setEnabled(true);

                        } else if (sql.buscarProveedor(cedulaProveedorValidar) == null) {
                            limpiarCajasProveedor();
                            JOptionPane.showMessageDialog(null, "No se pudo encontrar el Proveedor", " Proveedor no existe", JOptionPane.PLAIN_MESSAGE, iconError);
                        }

                    } catch (NumberFormatException nEx) {
                        JOptionPane.showMessageDialog(null, "Solo ingresar numeros en campo identificacion\nsin punto,  sin coma.", " Validar cedula", JOptionPane.PLAIN_MESSAGE, icon);
                        //System.err.println("Error : "+nEx);
                    }
                }
            }
        };
        gestionarProveedor.botonBuscarPorCedula.addActionListener(aL);
    }

    private void oyenteAccion_VentanaGestionarProveedores_BotonEditarProveedores() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == gestionarProveedor.botonEditar) {

                    if (gestionarProveedor.cajaNombre.getText().equals("") || gestionarProveedor.cajaApellido.getText().equals("") || gestionarProveedor.cajaCedula.getText().equals("") || gestionarProveedor.cajaCorreo.getText().equals("") || gestionarProveedor.cajaCelular.getText().equals("") || gestionarProveedor.cajaNombreEmpresa.getText().equals("") || gestionarProveedor.cajaTelEmpresa.getText().equals("") || gestionarProveedor.cajaDirEmpresa.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Falta informacion por completar", "Faltan Datos", JOptionPane.PLAIN_MESSAGE, icon);

                    } else if (!gestionarProveedor.cajaNombre.getText().equals("") || !gestionarProveedor.cajaApellido.getText().equals("") || !gestionarProveedor.cajaCedula.getText().equals("") || !gestionarProveedor.cajaCorreo.getText().equals("") || !gestionarProveedor.cajaCelular.getText().equals("") || !gestionarProveedor.cajaNombreEmpresa.getText().equals("") || !gestionarProveedor.cajaTelEmpresa.getText().equals("") || !gestionarProveedor.cajaDirEmpresa.getText().equals("")) {

                        Proveedor proveedor = new Proveedor();

                        proveedor.setNombre(gestionarProveedor.cajaNombre.getText());
                        proveedor.setApellido(gestionarProveedor.cajaApellido.getText());
                        try {

                            proveedor.setCedula(Integer.parseInt(gestionarProveedor.cajaCedula.getText()));
                            int cedulaProveedorValidar = Integer.parseInt(gestionarProveedor.cajaCedula.getText());

                            proveedor.setCelular(gestionarProveedor.cajaCelular.getText());
                            proveedor.setCorreo(gestionarProveedor.cajaCorreo.getText());
                            proveedor.setNombreEmpresa(gestionarProveedor.cajaNombreEmpresa.getText());
                            proveedor.setTelEmpresa(gestionarProveedor.cajaTelEmpresa.getText());
                            proveedor.setDirEmpresa(gestionarProveedor.cajaDirEmpresa.getText());

                            if (sql.verificarSiProveedorExiste(cedulaProveedorValidar) == 0 || cedulaProveedorEncontrada == cedulaProveedorValidar) {

                                if (sql.comprobarEmail(gestionarProveedor.cajaCorreo.getText())) {
                                    if (sql.editarProveedor(proveedor, idProveedorEncontrado)) {
                                        gestionarProveedor.botonEditar.setEnabled(false);
                                        gestionarProveedor.botonEliminar.setEnabled(false);
                                        limpiarCajasProveedor();
                                        JOptionPane.showMessageDialog(null, "Proveedor Modificado exitosamente", " Proveedor Modificado ", JOptionPane.PLAIN_MESSAGE, iconOk);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "No se pudo acceder a la base de datos", " Error en la Base de datos", JOptionPane.PLAIN_MESSAGE, iconError);
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "El correo que usted ingreso no es un correo valido", " Validar correo", JOptionPane.PLAIN_MESSAGE, icon);
                                }
                            } else if (sql.verificarSiProveedorExiste(cedulaProveedorValidar) != 0) {
                                JOptionPane.showMessageDialog(null, "El Proveedor que usted intento ingresar\nCon cedula : " + cedulaProveedorValidar + " ya existe ", " Validar Proveedor", JOptionPane.PLAIN_MESSAGE, icon);
                            }
                        } catch (NumberFormatException nEx) {
                            JOptionPane.showMessageDialog(null, "Solo ingresar numeros en campo identificacion\\nsin punto,  sin coma.", " Validar cedula", JOptionPane.PLAIN_MESSAGE, icon);
                            //System.err.println("Error : "+nEx);
                        }
                    }
                }
            }
        };
        gestionarProveedor.botonEditar.addActionListener(aL);
    }

    private void oyenteAccion_VentanaGestionarProveedores_BotonEliminarProveedores() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == gestionarProveedor.botonEliminar) {

                    if (siNo == null) {
                        siNo = new EstaSeguro(ventanaPrincipal, true);
                    }
                    oyenteAccion_NoEliminar();
                    oyenteAccion_SiEliminar();
                    windowClosing_VentanaEstaSeguro();
                    siNo.setVisible(true);

                    if (estarSeguro) {
                        if (sql.eliminarProveedor(idProveedorEncontrado)) {
                            limpiarCajasProveedor();
                            gestionarProveedor.botonEditar.setEnabled(false);
                            gestionarProveedor.botonEliminar.setEnabled(false);
                            JOptionPane.showMessageDialog(null, "Proveedor Eliminado exitosamente", " Proveedor Eliminado ", JOptionPane.PLAIN_MESSAGE, iconOk);
                        } else {
                            JOptionPane.showMessageDialog(null, "No se pudo acceder a la base de datos", " Error en la Base de datos", JOptionPane.PLAIN_MESSAGE, iconError);
                        }
                    }
                }
            }
        };
        gestionarProveedor.botonEliminar.addActionListener(aL);
    }

    private void formWindowActivated_Proveedor() {
        gestionarProveedor.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent evt) {
                gestionarProveedor.cajaNombre.requestFocus();
            }
        });
    }

    private void oyenteAccion_VentanaGestionarProveedores_BotonCancelar() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == gestionarProveedor.botonCancelar) {    // Menu Archivo item Cancelar
                    limpiarCajasProveedor();
                    gestionarProveedor.dispose();
                    gestionarProveedor = null;
                }
            }
        };
        gestionarProveedor.botonCancelar.addActionListener(aL);
    }

    private void limpiarCajasProveedor() {
        gestionarProveedor.cajaBuscarPorCedula.setText(null);
        gestionarProveedor.cajaNombre.setText(null);
        gestionarProveedor.cajaApellido.setText(null);
        gestionarProveedor.cajaCedula.setText(null);
        gestionarProveedor.cajaCelular.setText(null);
        gestionarProveedor.cajaCorreo.setText(null);
        gestionarProveedor.cajaNombreEmpresa.setText(null);
        gestionarProveedor.cajaTelEmpresa.setText(null);
        gestionarProveedor.cajaDirEmpresa.setText(null);
    }

    public void ventanaGestionarProveedores_WindowClossing() {
        gestionarProveedor.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                limpiarCajasProveedor();
                gestionarProveedor.dispose();
                gestionarProveedor = null;
            }
        });
    }

    //--------------------------------------------------------------------------------------------------------------------------------// Ventana Gestionar Productos
    private void oyenteAccion_VentanaPrincipal_BarMenuGestionar_itemProducto() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == ventanaPrincipal.itemGestionarMedicamentos) {
                    if (gestionarProducto == null) {
                        gestionarProducto = new GestionarProducto(ventanaPrincipal, false);
                    }
                    ArrayList<Laboratorio> listaLaboratorio = new ArrayList<>();
                    ArrayList<Proveedor> listaProveedor = new ArrayList<>();
                    Laboratorio nLaboratorio;
                    Proveedor nProveedor;

                    ventanaGestionarProductos_WindowClossing();
                    mouseListener_ImagenUpLoad();
                    mouseListener_Boton_X_EliminarImagen();
                    mouseListener_BotonLimpiar_VentanaGestonarProducto();
                    oyente_Teclado_CajaBuscarPorCodigoBarras();
                    formWindowActived_Producto();
                    windowListener_placeHolder_Producto();
                    mouseListener_TextoCargarImagen();
                    oyenteAccion_VentanaGestionarProducto_BotonCrearProducto();
                    oyenteAccion_VentanaGestionarProducto_BotonBuscarProducto();
                    oyenteAccion_VentanaGestionarProducto_BotonEditarProducto();
                    oyenteAccion_VentanaGestionarProducto_BotonEliminarProducto();
                    oyenteAccion_VentanaGestionarProducto_BotonCancelar();

                    modeloCombo = new DefaultComboBoxModel();
                    modeloCombo2 = new DefaultComboBoxModel();
                    modeloCombo.addElement("Seleccionar");
                    modeloCombo2.addElement("Seleccionar");

                    if (sql.buscarLaboratorio() != null) {
                        int i = 0;
                        while (i < sql.buscarLaboratorio().size()) {
                            nLaboratorio = new Laboratorio();
                            nLaboratorio = sql.buscarLaboratorio().get(i);
                            modeloCombo.addElement(nLaboratorio);
                            listaLaboratorio.add(nLaboratorio);
                            i++;
                        }
                    }
                    if (sql.buscarProveedor() != null) {
                        int i = 0;
                        while (i < sql.buscarProveedor().size()) {
                            nProveedor = new Proveedor();
                            nProveedor = sql.buscarProveedor().get(i);
                            modeloCombo2.addElement(nProveedor);
                            listaProveedor.add(nProveedor);
                            i++;
                        }
                    }
                    gestionarProducto.comboLaboratorio.setModel(modeloCombo);
                    gestionarProducto.comboProveedor.setModel(modeloCombo2);

                    gestionarProducto.cajaNombre.setEnabled(true);
                    gestionarProducto.cajaCantidadStock.setEnabled(true);
                    gestionarProducto.cajaPrecio.setEnabled(true);
                    gestionarProducto.cajaCodigodeBarras.setEnabled(true);
                    gestionarProducto.TextAreaDescripcion.setEnabled(true);
                    gestionarProducto.comboLaboratorio.setEnabled(true);
                    gestionarProducto.comboProveedor.setEnabled(true);
                    gestionarProducto.dateChooserFechaVencimiento.setDate(date);
                    gestionarProducto.dateChooserFechaVencimiento.setEnabled(true);

                    gestionarProducto.botonBuscarCodigoBarras.setVisible(true);
                    gestionarProducto.botonCrear.setVisible(true);
                    gestionarProducto.botonEditar.setVisible(true);
                    gestionarProducto.botonEliminar.setVisible(true);
                    gestionarProducto.botonCancelar.setVisible(true);

                    gestionarProducto.botonBuscarCodigoBarras.setEnabled(true);
                    gestionarProducto.botonCrear.setEnabled(true);
                    gestionarProducto.botonEditar.setEnabled(false);
                    gestionarProducto.botonEliminar.setEnabled(false);
                    gestionarProducto.botonCancelar.setEnabled(true);

                    gestionarProducto.panelimagen.setVisible(true);
                    gestionarProducto.labelNoHayImagen.setVisible(false);
                    gestionarProducto.labelimagenNombreArchivo.setText("Opcional...");
                    gestionarProducto.labelimagenNombreArchivo.setVisible(true);
                    gestionarProducto.labelEliminarImagen.setVisible(false);
                    gestionarProducto.labelTextoCargar.setVisible(true);
                    gestionarProducto.labelimagenUpLoad.setVisible(true);

                    gestionarProducto.setLocationRelativeTo(null);
                    gestionarProducto.setVisible(true);
                }
            }
        };
        ventanaPrincipal.itemGestionarMedicamentos.addActionListener(aL);
    }

    private void oyenteAccion_VentanaGestionarProducto_BotonCrearProducto() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == gestionarProducto.botonCrear) {
                    if (gestionarProducto.cajaNombre.getText().equals("") || gestionarProducto.cajaCantidadStock.getText().equals("") || gestionarProducto.cajaPrecio.getText().equals("") || gestionarProducto.cajaCodigodeBarras.getText().equals("") || gestionarProducto.comboLaboratorio.getSelectedItem().equals("Seleccionar") || gestionarProducto.comboProveedor.getSelectedItem().equals("Seleccionar") || gestionarProducto.dateChooserFechaVencimiento.getDate() == null || gestionarProducto.TextAreaDescripcion.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Falta informacion por completar", "Faltan Datos", JOptionPane.PLAIN_MESSAGE, icon);

                    } else if (!gestionarProducto.cajaNombre.getText().equals("") && !gestionarProducto.cajaCantidadStock.getText().equals("") && !gestionarProducto.cajaPrecio.getText().equals("") && !gestionarProducto.cajaCodigodeBarras.getText().equals("") && !gestionarProducto.comboLaboratorio.getSelectedItem().equals("Seleccionar") && !gestionarProducto.comboProveedor.getSelectedItem().equals("Seleccionar") && gestionarProducto.dateChooserFechaVencimiento.getDate() != null && !gestionarProducto.TextAreaDescripcion.getText().equals("")) {

                        Producto producto = new Producto();

                        producto.setNombre(gestionarProducto.cajaNombre.getText());
                        try {

                            producto.setCantidadStock(Integer.parseInt(gestionarProducto.cajaCantidadStock.getText()));
                            producto.setPrecio(Float.parseFloat(gestionarProducto.cajaPrecio.getText()));
                            producto.setCodigoBarras(gestionarProducto.cajaCodigodeBarras.getText());
                            String verificarCodigoBarra = gestionarProducto.cajaCodigodeBarras.getText();
                            producto.setDescripcion(gestionarProducto.TextAreaDescripcion.getText());

                            Laboratorio pLaboratorio = (Laboratorio) gestionarProducto.comboLaboratorio.getSelectedItem();
                            producto.setLaboratorio(pLaboratorio);

                            Proveedor pProveedor = (Proveedor) gestionarProducto.comboProveedor.getSelectedItem();
                            producto.setProveedor(pProveedor);
                            producto.setFechaVencimiento(fechaHora.format(gestionarProducto.dateChooserFechaVencimiento.getDate()));
                            ImagenProducto imagen = null;
                            FileInputStream archivoEntrada;
                            int codi = 0;

                            if (archivoImagen != null) {
                                try {
                                    archivoEntrada = new FileInputStream(archivoImagen);
                                    int codImagen = sql.ultimoCodigoImagen();
                                    codi = codImagen + 1;
                                    if (sql.ingresarImagen(archivoEntrada, archivoImagen, codi)) {
                                        //JOptionPane.showMessageDialog(null, "ImagenProducto cargada con exito", "ImagenProducto cargada", JOptionPane.PLAIN_MESSAGE, iconOk);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Error al intentar cargar la imagen", "Error al cargar", JOptionPane.PLAIN_MESSAGE, icon);
                                    }
                                } catch (HeadlessException | FileNotFoundException ex) {
                                }
                                int idImagen = sql.Obtener_Id_Imagen(codi);
                                imagen = new ImagenProducto(idImagen, codi);
                                producto.setImagen(imagen);
                            }
                            Producto p = sql.buscarProducto(verificarCodigoBarra);
                            if (sql.verificarSiProductoExiste(verificarCodigoBarra) == 0 || sql.verificarSiProductoExiste(verificarCodigoBarra) == 1) {
                                if (sql.ingresarProducto(producto)) {
                                    Producto productoCompletado = sql.buscarProducto(producto.getCodigoBarras());
                                    if (imagen != null) {
                                        sql.llenarDatosImagen(productoCompletado.getIdProducto(), imagen.getIdImagen());
                                    }
                                    limpiarCajasProducto();
                                    habilitarCargarImagen();
                                    archivoImagen = null;
                                    JOptionPane.showMessageDialog(null, "Producto creado en el sistema", " Producto Creado ", JOptionPane.PLAIN_MESSAGE, iconOk);
                                } else {
                                    JOptionPane.showMessageDialog(null, "No se pudo acceder a la base de datos", " Error en la Base de datos", JOptionPane.PLAIN_MESSAGE, iconError);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Ya existe un Producto en nuestro sistema\ncon el Codigo de barras ingresado", " Validar Producto", JOptionPane.PLAIN_MESSAGE, icon);
                            }
                        } catch (NumberFormatException nEx) {
                            JOptionPane.showMessageDialog(null, "Solo ingresar numeros en campos que requieran\n datos numericos sin punto,  sin coma.", " Validar cedula", JOptionPane.PLAIN_MESSAGE, icon);
                            //System.err.println("Error : "+nEx);
                        }
                    }
                }
            }
        };
        gestionarProducto.botonCrear.addActionListener(aL);
    }

    private void oyenteAccion_VentanaGestionarProducto_BotonBuscarProducto() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == gestionarProducto.botonBuscarCodigoBarras) {
                    try {
                        String codigoBarrasValidar = gestionarProducto.cajaBuscarPorCodigoBarras.getText();
                        gestionarProducto.labelNoHayImagen.setVisible(false);
                        if (sql.buscarProducto(codigoBarrasValidar) != null) {
                            Producto productoEncontrado = sql.buscarProducto(codigoBarrasValidar);
                            Laboratorio laboratorio = productoEncontrado.getLaboratorio();
                            Proveedor proveedor = productoEncontrado.getProveedor();

                            idProductoEncontrado = productoEncontrado.getIdProducto();
                            codigoBarraProductoEncontrado = productoEncontrado.getCodigoBarras();

                            gestionarProducto.cajaNombre.setText(productoEncontrado.getNombre());
                            gestionarProducto.cajaCantidadStock.setText(String.valueOf(productoEncontrado.getCantidadStock()));
                            gestionarProducto.cajaPrecio.setText(String.valueOf(productoEncontrado.getPrecio()));
                            gestionarProducto.cajaCodigodeBarras.setText(productoEncontrado.getCodigoBarras());
                            gestionarProducto.comboLaboratorio.getModel().setSelectedItem(laboratorio);
                            gestionarProducto.comboProveedor.getModel().setSelectedItem(proveedor);
                            try {
                                DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd");
                                Date date = fechaHora.parse(productoEncontrado.getFechaVencimiento());  // Paso la fecha vencimiento de String a Date en el formato que necesito
                                gestionarProducto.dateChooserFechaVencimiento.setDate(date);
                            } catch (ParseException ex) {
                                System.err.println("Error al asignar asignar la fecha encontrada");
                            }

                            gestionarProducto.TextAreaDescripcion.setText(productoEncontrado.getDescripcion());

                            ImagenProducto img = productoEncontrado.getImagen();
                            int idImagen = img.getIdImagen();

                            if (idImagen == 0) {
                                gestionarProducto.labelNoHayImagen.setVisible(true);
                            }
                            sql.ObtenerImagen(idImagen, gestionarProducto.panelimagen);
                            gestionarProducto.labelimagenNombreArchivo.setText("Cambiar Imagen...");
                            gestionarProducto.labelTextoCargar.setVisible(true);
                            gestionarProducto.labelimagenUpLoad.setVisible(true);
                            gestionarProducto.botonEliminar.setEnabled(true);
                            gestionarProducto.botonEditar.setEnabled(true);
                            gestionarProducto.botonCrear.setEnabled(false);

                        } else if (sql.buscarProducto(codigoBarrasValidar) == null) {
                            JOptionPane.showMessageDialog(null, "Producto buscado no se encuentra\nregistrado en la base de datos.", "Prducto no existe"
                                    + "", JOptionPane.PLAIN_MESSAGE, icon);
                            gestionarProducto.botonEditar.setEnabled(false);
                            gestionarProducto.botonEliminar.setEnabled(false);
                            gestionarProducto.cajaNombre.setText(null);
                            gestionarProducto.cajaCantidadStock.setText(null);
                            gestionarProducto.cajaPrecio.setText(null);
                            gestionarProducto.comboLaboratorio.setSelectedIndex(0);
                            gestionarProducto.comboProveedor.setSelectedIndex(0);
                            gestionarProducto.cajaCodigodeBarras.setText(null);
                            gestionarProducto.TextAreaDescripcion.setText(null);
                            gestionarProducto.dateChooserFechaVencimiento.setDate(date);
                            gestionarProducto.labelimagenNombreArchivo.setText("Opcional...");
                            archivoImagen = null;
                            gestionarProducto.labelTextoCargar.setVisible(true);
                            gestionarProducto.labelimagenUpLoad.setVisible(true);

                            gestionarProducto.panelimagen.removeAll();
                            gestionarProducto.panelimagen.repaint();
                            gestionarProducto.botonCrear.setEnabled(true);
                        }
                    } catch (NumberFormatException nEx) {
                        JOptionPane.showMessageDialog(null, "Solo ingresar numeros en campo buscar \npor codigo Barras sin punto,  sin coma.", " Validar Codigo Barras", JOptionPane.PLAIN_MESSAGE, icon);
                        //System.err.println("Error : "+nEx);
                    }
                }
            }
        };
        gestionarProducto.botonBuscarCodigoBarras.addActionListener(aL);
    }

    private void oyenteAccion_VentanaGestionarProducto_BotonEditarProducto() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == gestionarProducto.botonEditar) {

                    if (gestionarProducto.cajaNombre.getText().equals("") || gestionarProducto.cajaCantidadStock.getText().equals("") || gestionarProducto.cajaPrecio.getText().equals("") || gestionarProducto.cajaCodigodeBarras.getText().equals("") || gestionarProducto.comboLaboratorio.getSelectedItem().equals("Seleccionar") || gestionarProducto.comboProveedor.getSelectedItem().equals("Seleccionar") || gestionarProducto.dateChooserFechaVencimiento.getDate() == null || gestionarProducto.TextAreaDescripcion.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Falta informacion por completar", "Faltan Datos", JOptionPane.PLAIN_MESSAGE, icon);

                    } else if (!gestionarProducto.cajaNombre.getText().equals("") && !gestionarProducto.cajaCantidadStock.getText().equals("") && !gestionarProducto.cajaPrecio.getText().equals("") && !gestionarProducto.cajaCodigodeBarras.getText().equals("") && !gestionarProducto.comboLaboratorio.getSelectedItem().equals("Seleccionar") && !gestionarProducto.comboProveedor.getSelectedItem().equals("Seleccionar") && gestionarProducto.dateChooserFechaVencimiento.getDate() != null && !gestionarProducto.TextAreaDescripcion.getText().equals("")) {

                        Producto producto = new Producto();

                        producto.setNombre(gestionarProducto.cajaNombre.getText());

                        try {
                            producto.setCantidadStock(Integer.parseInt(gestionarProducto.cajaCantidadStock.getText()));
                            producto.setPrecio(Float.parseFloat(gestionarProducto.cajaPrecio.getText()));
                            producto.setCodigoBarras(gestionarProducto.cajaCodigodeBarras.getText());
                            String verificarCodigoBarra = gestionarProducto.cajaCodigodeBarras.getText();
                            producto.setDescripcion(gestionarProducto.TextAreaDescripcion.getText());

                            Laboratorio pLaboratorio = (Laboratorio) gestionarProducto.comboLaboratorio.getSelectedItem();
                            producto.setLaboratorio(pLaboratorio);

                            Proveedor pProveedor = (Proveedor) gestionarProducto.comboProveedor.getSelectedItem();
                            producto.setProveedor(pProveedor);
                            producto.setFechaVencimiento(fechaHora.format(gestionarProducto.dateChooserFechaVencimiento.getDate()));

                            Producto p = sql.buscarProducto(codigoBarraProductoEncontrado);

                            if (sql.verificarSiProductoExiste(verificarCodigoBarra) == 0 || verificarCodigoBarra.equals(codigoBarraProductoEncontrado)) {
                                FileInputStream archivoEntrada;
                                int codi;
                                Producto productoEncontrado = sql.buscarProducto(codigoBarraProductoEncontrado);
                                ImagenProducto imagenEncontrada = productoEncontrado.getImagen();
                                try {
                                    if (archivoImagen != null) {
                                        archivoEntrada = new FileInputStream(archivoImagen);// la imagen
                                        int idimg = imagenEncontrada.getIdImagen();

                                        if (idimg == 0) { // si no tengo id de imagen en mi producto dice que no tenia imagen
                                            int codImagen = sql.ultimoCodigoImagen();
                                            codi = codImagen + 1;
                                            sql.ingresarImagen(archivoEntrada, archivoImagen, codi);
                                            int idImagen = sql.Obtener_Id_Imagen(codi);
                                            imagenEncontrada = new ImagenProducto(idImagen, codi);
                                            producto.setImagen(imagenEncontrada);

                                        } else {
                                            if (sql.modificar_Imagen(archivoEntrada, archivoImagen, imagenEncontrada.getIdImagen())) {
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Error al intentar modificar la imagen", "Error al modificar", JOptionPane.PLAIN_MESSAGE, icon);
                                            }
                                        }
                                    }

                                    if (sql.editarProducto(producto, idProductoEncontrado, imagenEncontrada.getIdImagen())) {
                                        sql.llenarDatosImagen(idProductoEncontrado, imagenEncontrada.getIdImagen());
                                        limpiarCajasProducto();
                                        JOptionPane.showMessageDialog(null, "Producto modificado exitosamente", " Producto modificado ", JOptionPane.PLAIN_MESSAGE, iconOk);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "No se pudo acceder a la base de datos", " Error en la Base de datos", JOptionPane.PLAIN_MESSAGE, iconError);
                                    }
                                } catch (HeadlessException | FileNotFoundException ex) {
                                }
                            } else if (sql.verificarSiProductoExiste(verificarCodigoBarra) != 0 || verificarCodigoBarra.equals(codigoBarraProductoEncontrado)) {
                                JOptionPane.showMessageDialog(null, "Ya existe un Producto en nuestro sistema\ncon el Codigo de barras  y lo te ingresado", " Validar Producto", JOptionPane.PLAIN_MESSAGE, icon);
                            }
                        } catch (NumberFormatException nEx) {
                            JOptionPane.showMessageDialog(null, "Solo ingresar numeros en campos que requieran\n datos numericos sin punto,  sin coma.", " Validar cedula", JOptionPane.PLAIN_MESSAGE, icon);
                            //System.err.println("Error : "+nEx);
                        }
                    }
                }
            }
        };
        gestionarProducto.botonEditar.addActionListener(aL);
    }

    private void oyenteAccion_VentanaGestionarProducto_BotonEliminarProducto() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == gestionarProducto.botonEliminar) {
                    if (siNo == null) {
                        siNo = new EstaSeguro(ventanaPrincipal, true);
                    }
                    oyenteAccion_NoEliminar();
                    oyenteAccion_SiEliminar();
                    windowClosing_VentanaEstaSeguro();
                    siNo.setVisible(true);

                    if (estarSeguro) {
                        if (sql.eliminarProducto(idProductoEncontrado)) {
                            limpiarCajasProducto();
                            JOptionPane.showMessageDialog(null, "Producto Eliminado exitosamente", " Producto Eliminado ", JOptionPane.PLAIN_MESSAGE, iconOk);
                        } else {
                            JOptionPane.showMessageDialog(null, "No se pudo acceder a la base de datos", " Error en la Base de datos", JOptionPane.PLAIN_MESSAGE, iconError);
                        }
                    }
                }
            }
        };
        gestionarProducto.botonEliminar.addActionListener(aL);
    }

    private void oyente_Teclado_CajaBuscarPorCodigoBarras() {
        KeyListener kL = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    String codigoBarrasValidar = gestionarProducto.cajaBuscarPorCodigoBarras.getText();
                    gestionarProducto.labelNoHayImagen.setVisible(false);
                    if (sql.buscarProducto(codigoBarrasValidar) != null) {
                        Producto productoEncontrado = sql.buscarProducto(codigoBarrasValidar);
                        Laboratorio laboratorio = productoEncontrado.getLaboratorio();
                        Proveedor proveedor = productoEncontrado.getProveedor();

                        idProductoEncontrado = productoEncontrado.getIdProducto();
                        codigoBarraProductoEncontrado = productoEncontrado.getCodigoBarras();

                        gestionarProducto.cajaNombre.setText(productoEncontrado.getNombre());
                        gestionarProducto.cajaCantidadStock.setText(String.valueOf(productoEncontrado.getCantidadStock()));
                        gestionarProducto.cajaPrecio.setText(String.valueOf(productoEncontrado.getPrecio()));
                        gestionarProducto.cajaCodigodeBarras.setText(productoEncontrado.getCodigoBarras());
                        gestionarProducto.comboLaboratorio.getModel().setSelectedItem(laboratorio);
                        gestionarProducto.comboProveedor.getModel().setSelectedItem(proveedor);
                        try {
                            DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = fechaHora.parse(productoEncontrado.getFechaVencimiento());  // Paso la fecha vencimiento de String a Date en el formato que necesito
                            gestionarProducto.dateChooserFechaVencimiento.setDate(date);
                        } catch (ParseException ex) {
                            System.err.println("Error al asignar asignar la fecha encontrada");
                        }

                        gestionarProducto.TextAreaDescripcion.setText(productoEncontrado.getDescripcion());

                        ImagenProducto img = productoEncontrado.getImagen();
                        int idImagen = img.getIdImagen();

                        if (idImagen == 0) {
                            gestionarProducto.labelNoHayImagen.setVisible(true);
                        }
                        sql.ObtenerImagen(idImagen, gestionarProducto.panelimagen);
                        gestionarProducto.labelimagenNombreArchivo.setText("Cambiar Imagen...");
                        gestionarProducto.labelTextoCargar.setVisible(true);
                        gestionarProducto.labelimagenUpLoad.setVisible(true);
                        gestionarProducto.botonEliminar.setEnabled(true);
                        gestionarProducto.botonEditar.setEnabled(true);
                        gestionarProducto.botonCrear.setEnabled(false);

                    } else if (sql.buscarProducto(codigoBarrasValidar) == null) {
                        gestionarProducto.botonEditar.setEnabled(false);
                        gestionarProducto.botonEliminar.setEnabled(false);
                        gestionarProducto.cajaNombre.setText(null);
                        gestionarProducto.cajaCantidadStock.setText(null);
                        gestionarProducto.cajaPrecio.setText(null);
                        gestionarProducto.comboLaboratorio.setSelectedIndex(0);
                        gestionarProducto.comboProveedor.setSelectedIndex(0);
                        gestionarProducto.cajaCodigodeBarras.setText(null);
                        gestionarProducto.TextAreaDescripcion.setText(null);
                        gestionarProducto.dateChooserFechaVencimiento.setDate(date);
                        gestionarProducto.labelimagenNombreArchivo.setText("Opcional...");
                        archivoImagen = null;
                        gestionarProducto.labelTextoCargar.setVisible(true);
                        gestionarProducto.labelimagenUpLoad.setVisible(true);

                        gestionarProducto.panelimagen.removeAll();
                        gestionarProducto.panelimagen.repaint();
                        gestionarProducto.botonCrear.setEnabled(true);
                    }
                } catch (NumberFormatException nEx) {
                    JOptionPane.showMessageDialog(null, "Solo ingresar numeros en campo buscar \npor codigo Barras sin punto,  sin coma.", " Validar Codigo Barras", JOptionPane.PLAIN_MESSAGE, icon);
                    //System.err.println("Error : "+nEx);
                }
            }
        };
        gestionarProducto.cajaBuscarPorCodigoBarras.addKeyListener(kL);
    }

    private void mouseListener_BotonLimpiar_VentanaGestonarProducto() {
        MouseListener mL = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                limpiarCajasProducto();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        gestionarProducto.botonLimpiar.addMouseListener(mL);
    }

    private void mouseListener_TextoCargarImagen() {
        MouseListener mL = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileCH = new JFileChooser();
                fileCH.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                FileNameExtensionFilter filtro1 = new FileNameExtensionFilter("*.png", "png");
                FileNameExtensionFilter filtro2 = new FileNameExtensionFilter("*.bmp", "bmp");
                FileNameExtensionFilter filtro3 = new FileNameExtensionFilter("*.jpeg", "jpeg");
                FileNameExtensionFilter filtro4 = new FileNameExtensionFilter("*.jpg", "jpg");

                fileCH.setFileFilter(filtro1);
                fileCH.setFileFilter(filtro2);
                fileCH.setFileFilter(filtro3);
                fileCH.setFileFilter(filtro4);

                int seleccion = fileCH.showOpenDialog(gestionarProducto);

                if (seleccion == JFileChooser.APPROVE_OPTION) {
                    archivoImagen = fileCH.getSelectedFile();
                    gestionarProducto.labelimagenNombreArchivo.setText(archivoImagen.getName());
                    gestionarProducto.labelEliminarImagen.setVisible(true);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        gestionarProducto.labelTextoCargar.addMouseListener(mL);
    }

    private void mouseListener_ImagenUpLoad() {
        MouseListener mL = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileCH = new JFileChooser();
                fileCH.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                FileNameExtensionFilter filtro1 = new FileNameExtensionFilter("*.png", "png");
                FileNameExtensionFilter filtro2 = new FileNameExtensionFilter("*.bmp", "bmp");
                FileNameExtensionFilter filtro3 = new FileNameExtensionFilter("*.jpeg", "jpeg");
                FileNameExtensionFilter filtro4 = new FileNameExtensionFilter("*.jpg", "jpg");

                fileCH.setFileFilter(filtro1);
                fileCH.setFileFilter(filtro2);
                fileCH.setFileFilter(filtro3);
                fileCH.setFileFilter(filtro4);

                int seleccion = fileCH.showOpenDialog(gestionarProducto);

                if (seleccion == JFileChooser.APPROVE_OPTION) {
                    archivoImagen = fileCH.getSelectedFile();
                    gestionarProducto.labelimagenNombreArchivo.setText(archivoImagen.getName());
                    gestionarProducto.labelEliminarImagen.setVisible(true);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        gestionarProducto.labelimagenUpLoad.addMouseListener(mL);
    }

    private void mouseListener_Boton_X_EliminarImagen() {
        MouseListener mL = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                archivoImagen = null;
                gestionarProducto.labelimagenNombreArchivo.setText(null);
                gestionarProducto.labelEliminarImagen.setVisible(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        gestionarProducto.labelEliminarImagen.addMouseListener(mL);
    }

    private void windowListener_placeHolder_Producto() {
        FocusListener holder = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (gestionarProducto.cajaBuscarPorCodigoBarras.getText().equals("Codigo de barras") || gestionarProducto.cajaBuscarPorCodigoBarras.getText().equals("")) {
                    gestionarProducto.cajaBuscarPorCodigoBarras.setText("");
                    gestionarProducto.cajaBuscarPorCodigoBarras.setForeground(new Color(102, 102, 102));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (gestionarProducto != null && gestionarProducto.cajaBuscarPorCodigoBarras.getText().equals("")) {
                    gestionarProducto.cajaBuscarPorCodigoBarras.setText("Codigo de barras");
                    gestionarProducto.cajaBuscarPorCodigoBarras.setForeground(new Color(204, 204, 204));
                }
            }
        };
        gestionarProducto.cajaBuscarPorCodigoBarras.addFocusListener(holder);
    }

    private void formWindowActived_Producto() {
        gestionarProducto.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent evt) {
                gestionarProducto.cajaNombre.requestFocus();
            }
        });

    }

    private void oyenteAccion_VentanaGestionarProducto_BotonCancelar() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCajasProducto();
                gestionarProducto.dispose();
                gestionarProducto = null;
            }
        };
        gestionarProducto.botonCancelar.addActionListener(aL);
    }

    private void limpiarCajasProducto() {
        gestionarProducto.cajaBuscarPorCodigoBarras.setText(null);
        gestionarProducto.cajaNombre.setText(null);
        gestionarProducto.cajaCantidadStock.setText(null);
        gestionarProducto.cajaPrecio.setText(null);
        gestionarProducto.comboLaboratorio.setSelectedIndex(0);
        gestionarProducto.comboProveedor.setSelectedIndex(0);
        gestionarProducto.cajaCodigodeBarras.setText(null);
        gestionarProducto.TextAreaDescripcion.setText(null);
        gestionarProducto.dateChooserFechaVencimiento.setDate(date);
        gestionarProducto.labelimagenNombreArchivo.setText(null);
        gestionarProducto.labelEliminarImagen.setVisible(false);
        gestionarProducto.labelTextoCargar.setVisible(false);
        gestionarProducto.labelimagenUpLoad.setVisible(false);
        gestionarProducto.botonEditar.setEnabled(false);
        gestionarProducto.botonEliminar.setEnabled(false);
        gestionarProducto.labelNoHayImagen.setVisible(false);
        gestionarProducto.botonCrear.setEnabled(true);
        habilitarCargarImagen();
        gestionarProducto.panelimagen.removeAll();
        gestionarProducto.panelimagen.repaint();
    }

    private void habilitarCargarImagen() {
        gestionarProducto.labelTextoCargar.setVisible(true);
        gestionarProducto.labelimagenUpLoad.setVisible(true);
        gestionarProducto.labelimagenNombreArchivo.setVisible(true);
        gestionarProducto.labelimagenNombreArchivo.setText("Opcional...");
        archivoImagen = null;
    }

    public void ventanaGestionarProductos_WindowClossing() {
        gestionarProducto.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                limpiarCajasProducto();
                gestionarProducto.dispose();
                gestionarProducto = null;
            }
        });
    }

    //---------------------------------------------------------------------------------------------------------------------------// Ventana Consultar Clientes
    private void oyenteAccion_VentanaPrincipal_BarMenuConsultar_MenuitemCliente() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == ventanaPrincipal.itemConsultarClientes) {

                    if (consultarCliente == null) {
                        consultarCliente = new ConsultarClientes(ventanaPrincipal, false);
                    }
                    oyente_Teclado_CajaBuscarPorCedula();
                    formWindowActivated_ConsultarCliente();
                    windowListener_placeHolder_ConsultarCliente();
                    mouseClicked_TableModel();
                    keyReleased_TableModel();
                    oyenteAccion_VentanaConsultarCliente_BotonBuscarTodo();
                    oyenteAccion_VentanaConsultarCliente_BotonSalir();
                    VentanaConsultarCliente_WindowClossing();

                    consultarCliente.cajaNombre.setEditable(false);
                    consultarCliente.cajaApellido.setEditable(false);
                    consultarCliente.cajaCedula.setEditable(false);
                    consultarCliente.cajaDireccion.setEditable(false);
                    consultarCliente.cajaCorreo.setEditable(false);
                    consultarCliente.cajaCelular.setEditable(false);

                    consultarCliente.botonBuscarTodoCliente.setVisible(true);
                    consultarCliente.botonSalir.setVisible(true);
                    consultarCliente.botonBuscarTodoCliente.setEnabled(true);
                    consultarCliente.botonSalir.setEnabled(true);

                    DefaultTableModel modeloTabla = new DefaultTableModel() {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    };
                    modeloTabla.addColumn("Nombre");
                    modeloTabla.addColumn("Apellido");
                    modeloTabla.addColumn("Cedula");
                    modeloTabla.addColumn("Correo");
                    modeloTabla.addColumn("Celular");
                    modeloTabla.addColumn("Direccion");
                    consultarCliente.tablaClientesConsultados.setModel(modeloTabla);
                    TableRowSorter<TableModel> trs = new TableRowSorter<>(modeloTabla);
                    consultarCliente.tablaClientesConsultados.setRowSorter(trs);

                    consultarCliente.setLocationRelativeTo(null);
                    consultarCliente.setVisible(true);
                }
            }
        };
        ventanaPrincipal.itemConsultarClientes.addActionListener(aL);
    }

    private void oyenteAccion_VentanaConsultarCliente_BotonBuscarTodo() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == consultarCliente.botonBuscarTodoCliente) {

                    try {
                        if (sql.buscarTodoCliente() != null) {
                            DefaultTableModel modeloTabla;
                            modeloTabla = sql.buscarTodoCliente();
                            consultarCliente.tablaClientesConsultados.setModel(modeloTabla);

                            TableRowSorter<TableModel> trs = new TableRowSorter<>(modeloTabla);
                            consultarCliente.tablaClientesConsultados.setRowSorter(trs);
                            consultarCliente.cajaBuscarPorCedula.setText(null);

                        } else {
                            JOptionPane.showMessageDialog(null, "Error al cargar los clientes desde la base de datos", "Error en la base de datos", JOptionPane.PLAIN_MESSAGE, iconError);
                        }
                    } catch (NumberFormatException nEx) {
                        JOptionPane.showMessageDialog(null, "Solo ingresar numeros en campo buscar \npor codigo Barras sin punto,  sin coma.", " Validar Codigo Barras", JOptionPane.PLAIN_MESSAGE, icon);
                        //System.err.println("Error : "+nEx);
                    }
                }
            }
        };
        consultarCliente.botonBuscarTodoCliente.addActionListener(aL);
    }

    private void oyente_Teclado_CajaBuscarPorCedula() {
        KeyListener kL = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (!consultarCliente.cajaBuscarPorCedula.getText().equals(" ") && !consultarCliente.cajaBuscarPorCedula.getText().equals("")) {
                    try {
                        int cedulaBuscada = Integer.parseInt(consultarCliente.cajaBuscarPorCedula.getText());

                        if (sql.buscarPosiblesClientesSegunNumerosDigitadosEnLaCaja(cedulaBuscada) != null) {
                            DefaultTableModel modeloTemporal = new DefaultTableModel();
                            modeloTemporal = sql.buscarPosiblesClientesSegunNumerosDigitadosEnLaCaja(cedulaBuscada);
                            consultarCliente.tablaClientesConsultados.setModel(modeloTemporal);

                            TableRowSorter<TableModel> trs = new TableRowSorter<>(modeloTemporal);
                            consultarCliente.tablaClientesConsultados.setRowSorter(trs);
                        } else {
                            limpiarCajasConsultarCliente();
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Cedula incorrecta, Solo ingresar numeros \nsin punto,  sin coma.", " Validar Cedula", JOptionPane.PLAIN_MESSAGE, icon);
                        limpiarCajasConsultarCliente();
                    }
                }
            }
        };
        consultarCliente.cajaBuscarPorCedula.addKeyListener(kL);
    }

    private void keyReleased_TableModel() {
        KeyListener kL = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.VK_DOWN == e.getKeyCode()) {
                    int fila = consultarCliente.tablaClientesConsultados.getSelectedRow();
                    String cedulaS = consultarCliente.tablaClientesConsultados.getValueAt(fila, 2).toString();
                    int cedula = Integer.parseInt(cedulaS);
                    cliente = new Cliente();
                    cliente = sql.buscarCliente(cedula);
                    consultarCliente.cajaNombre.setText(cliente.getNombre());
                    consultarCliente.cajaApellido.setText(cliente.getApellido());
                    consultarCliente.cajaCedula.setText(String.valueOf(cliente.getCedula()));
                    consultarCliente.cajaCorreo.setText(cliente.getCorreo());
                    consultarCliente.cajaCelular.setText(cliente.getCelular());
                    consultarCliente.cajaDireccion.setText(cliente.getDireccion());
                } else if (e.VK_UP == e.getKeyCode()) {
                    int fila = consultarCliente.tablaClientesConsultados.getSelectedRow();
                    String cedulaS = consultarCliente.tablaClientesConsultados.getValueAt(fila, 2).toString();
                    int cedula = Integer.parseInt(cedulaS);
                    cliente = new Cliente();
                    cliente = sql.buscarCliente(cedula);
                    consultarCliente.cajaNombre.setText(cliente.getNombre());
                    consultarCliente.cajaApellido.setText(cliente.getApellido());
                    consultarCliente.cajaCedula.setText(String.valueOf(cliente.getCedula()));
                    consultarCliente.cajaCorreo.setText(cliente.getCorreo());
                    consultarCliente.cajaCelular.setText(cliente.getCelular());
                    consultarCliente.cajaDireccion.setText(cliente.getDireccion());
                }
            }
        };
        consultarCliente.tablaClientesConsultados.addKeyListener(kL);
    }

    private void mouseClicked_TableModel() {
        MouseListener mL = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = consultarCliente.tablaClientesConsultados.getSelectedRow();
                String cedulaS = consultarCliente.tablaClientesConsultados.getValueAt(fila, 2).toString();
                int cedula = Integer.parseInt(cedulaS);
                cliente = new Cliente();
                cliente = sql.buscarCliente(cedula);
                consultarCliente.cajaNombre.setText(cliente.getNombre());
                consultarCliente.cajaApellido.setText(cliente.getApellido());
                consultarCliente.cajaCedula.setText(String.valueOf(cliente.getCedula()));
                consultarCliente.cajaCorreo.setText(cliente.getCorreo());
                consultarCliente.cajaCelular.setText(cliente.getCelular());
                consultarCliente.cajaDireccion.setText(cliente.getDireccion());
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        consultarCliente.tablaClientesConsultados.addMouseListener(mL);
    }

    private void windowListener_placeHolder_ConsultarCliente() {
        FocusListener holder = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (consultarCliente.cajaBuscarPorCedula.getText().equals("Filtrar por Cedula") || consultarCliente.cajaBuscarPorCedula.getText().equals("")) {
                    consultarCliente.cajaBuscarPorCedula.setText("");
                    consultarCliente.cajaBuscarPorCedula.setForeground(new Color(102, 102, 102));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (consultarCliente != null && consultarCliente.cajaBuscarPorCedula.getText().equals("")) {
                    consultarCliente.cajaBuscarPorCedula.setText("Filtrar por Cedula");
                    consultarCliente.cajaBuscarPorCedula.setForeground(new Color(204, 204, 204));
                }
            }
        };
        consultarCliente.cajaBuscarPorCedula.addFocusListener(holder);
    }

    private void formWindowActivated_ConsultarCliente() {
        consultarCliente.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent evt) {
                consultarCliente.cajaBuscarPorCedula.requestFocus();
            }
        });

    }

    private void oyenteAccion_VentanaConsultarCliente_BotonSalir() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCajasConsultarCliente();
                consultarCliente.dispose();
                consultarCliente = null;
            }
        };
        consultarCliente.botonSalir.addActionListener(aL);
    }

    private void limpiarCajasConsultarCliente() {
        consultarCliente.cajaBuscarPorCedula.setText(null);
        consultarCliente.cajaNombre.setText(null);
        consultarCliente.cajaApellido.setText(null);
        consultarCliente.cajaCedula.setText(null);
        consultarCliente.cajaCorreo.setText(null);
        consultarCliente.cajaCelular.setText(null);
        consultarCliente.cajaDireccion.setText(null);
        limpiarJTable();
    }

    private void limpiarJTable() {
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Apellido");
        modeloTabla.addColumn("Cedula");
        modeloTabla.addColumn("Correo");
        modeloTabla.addColumn("Celular");
        modeloTabla.addColumn("Direccion");
        consultarCliente.tablaClientesConsultados.setModel(modeloTabla);
    }

    public void VentanaConsultarCliente_WindowClossing() {
        consultarCliente.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                limpiarCajasConsultarCliente();
                consultarCliente.dispose();
                consultarCliente = null;
            }
        });
    }

    //--------------------------------------------------------------------------------------------------------------------------------------// Ventana Consultar Laboratorio
    private void oyenteAccion_VentanaPrincipal_BarMenuConsultar_MenuitemLaboratorio() {

        ActionListener aL;
        aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == ventanaPrincipal.itemConsultarLaboratorios) {

                    if (consultarLaboratorio == null) {
                        consultarLaboratorio = new ConsultarLaboratorio(ventanaPrincipal, false);
                    }
                    formWindowActivated_ConsultarLaboratorio();
                    oyenteAccion_ComboBox_VentanaConsultarLaboratorio_ComboLaboratorios();
                    oyenteAccion_ComboBox_VentanaConsultarLaboratorio_ComboProductos();
                    oyente_Teclado_CajaBuscarPorNombre_ConsultarLaboratorio();
                    windowListener_placeHolder_ConsultarLaboratorio();
                    oyenteAccion_VentanaConsultarLaboratorio_BotonSalir();
                    VentanaConsultarLaboratorio_WindowClossing();

                    consultarLaboratorio.comboProductosDistribuidos.setEnabled(false);
                    consultarLaboratorio.comboNombreLaboratorios.setEnabled(true);
                    consultarLaboratorio.cajaBuscarPorNombre.setEnabled(true);
                    consultarLaboratorio.botonSalir.setVisible(true);
                    consultarLaboratorio.botonSalir.setEnabled(true);

                    Laboratorio laboratorio;
                    DefaultComboBoxModel comboLab = new DefaultComboBoxModel();
                    DefaultComboBoxModel comboProd = new DefaultComboBoxModel();
                    comboLab.addElement("SELECCIONAR");
                    comboProd.addElement("Seleciona un Laboratorio");

                    if (sql.buscarLaboratorio() != null) { // La consulta devuelve un listado de laboratorios
                        int i = 0;
                        while (i < sql.buscarLaboratorio().size()) {
                            laboratorio = new Laboratorio();
                            laboratorio = sql.buscarLaboratorio().get(i);
                            comboLab.addElement(laboratorio);
                            i++;
                        }
                    }
                    consultarLaboratorio.comboNombreLaboratorios.setModel(comboLab);
                    consultarLaboratorio.comboProductosDistribuidos.setModel(comboProd);

                    DefaultTableModel tM;
                    tM = new DefaultTableModel() {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    };
                    tM.addColumn("Nombre");
                    tM.addColumn("Codigo Barras");
                    tM.addColumn("Cantidad Stock");
                    tM.addColumn("Precio");
                    tM.addColumn("Fecha Vencimiento");
                    tM.addColumn("Descripcion");
                    tM.addColumn("Lote");
                    consultarLaboratorio.tablaLaboratoriosConsultados.setModel(tM);
                    TableRowSorter<TableModel> trs = new TableRowSorter<>(tM);
                    consultarLaboratorio.tablaLaboratoriosConsultados.setRowSorter(trs);

                    consultarLaboratorio.setLocationRelativeTo(null);
                    consultarLaboratorio.setVisible(true);
                }
            }
        };
        ventanaPrincipal.itemConsultarLaboratorios.addActionListener(aL);
    }

    private void oyenteAccion_ComboBox_VentanaConsultarLaboratorio_ComboLaboratorios() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DefaultComboBoxModel comboProd = new DefaultComboBoxModel();

                if (e.getSource() == consultarLaboratorio.comboNombreLaboratorios && !consultarLaboratorio.comboNombreLaboratorios.getSelectedItem().equals("SELECCIONAR")) {

                    Laboratorio laboratorioSeleccionado = (Laboratorio) consultarLaboratorio.comboNombreLaboratorios.getSelectedItem();
                    int idLaboratorioSeleccionado = laboratorioSeleccionado.getIdLaboratorio();
                    Producto producto;

                    if (sql.buscarProducto(idLaboratorioSeleccionado) != null) {

                        int i = 0;
                        while (i < sql.buscarProducto(idLaboratorioSeleccionado).size()) {
                            producto = sql.buscarProducto(idLaboratorioSeleccionado).get(i);
                            comboProd.addElement(producto);
                            i++;
                        }

                        consultarLaboratorio.comboProductosDistribuidos.setModel(comboProd);
                        consultarLaboratorio.comboProductosDistribuidos.setEnabled(true);

                        DefaultTableModel modeloTabla;
                        modeloTabla = sql.buscarTodoProducto(idLaboratorioSeleccionado);

                        consultarLaboratorio.tablaLaboratoriosConsultados.setModel(modeloTabla);
                        TableRowSorter<TableModel> trs = new TableRowSorter<>(modeloTabla);
                        consultarLaboratorio.tablaLaboratoriosConsultados.setRowSorter(trs);

                        consultarLaboratorio.cajaBuscarPorNombre.setText("Filtrar por nombre");
                        consultarLaboratorio.cajaBuscarPorNombre.setForeground(new Color(204, 204, 204));
                    }
                } else if (consultarLaboratorio.comboNombreLaboratorios.getSelectedItem().equals("SELECCIONAR")) {
                    consultarLaboratorio.comboProductosDistribuidos.setEnabled(false);

                    DefaultComboBoxModel c = new DefaultComboBoxModel();
                    c.addElement("Seleciona un Laboratorio");
                    consultarLaboratorio.comboProductosDistribuidos.setModel(c);
                }
            }
        };
        consultarLaboratorio.comboNombreLaboratorios.addActionListener(aL);
    }

    private void oyenteAccion_ComboBox_VentanaConsultarLaboratorio_ComboProductos() {

        ItemListener icL = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Producto productoSeleccionado = new Producto();
                    Object item = new Object();

                    item = e.getItem();
                    productoSeleccionado = (Producto) item;

                    String nomLabSeleccionado = productoSeleccionado.getNombre();

                    if (sql.buscarUnProducto(nomLabSeleccionado) != null) {

                        DefaultTableModel modeloTabla;
                        modeloTabla = sql.buscarUnProducto(nomLabSeleccionado);
                        consultarLaboratorio.tablaLaboratoriosConsultados.setModel(modeloTabla);

                        TableRowSorter<TableModel> trs = new TableRowSorter<>(modeloTabla);
                        consultarLaboratorio.tablaLaboratoriosConsultados.setRowSorter(trs);

                        consultarLaboratorio.cajaBuscarPorNombre.setText(null);
                    }
                }
            }
        };
        consultarLaboratorio.comboProductosDistribuidos.addItemListener(icL);
    }

    private void oyente_Teclado_CajaBuscarPorNombre_ConsultarLaboratorio() {
        KeyListener kL = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (!consultarLaboratorio.cajaBuscarPorNombre.getText().equals(" ") && !consultarLaboratorio.cajaBuscarPorNombre.getText().equals("")) {

                    String nombreBuscado = consultarLaboratorio.cajaBuscarPorNombre.getText();
                    if (sql.buscarPosiblesLaboratorioSegunLetrasDigitadasEnLaCaja(nombreBuscado) != null) {

                        DefaultTableModel modeloTemporal = new DefaultTableModel();
                        modeloTemporal = sql.buscarPosiblesLaboratorioSegunLetrasDigitadasEnLaCaja(nombreBuscado);
                        consultarLaboratorio.tablaLaboratoriosConsultados.setModel(modeloTemporal);
                        TableRowSorter<TableModel> trs = new TableRowSorter<>(modeloTemporal);
                        consultarLaboratorio.tablaLaboratoriosConsultados.setRowSorter(trs);

                        consultarLaboratorio.comboNombreLaboratorios.setSelectedItem("SELECCIONAR");
                        consultarLaboratorio.comboProductosDistribuidos.setModel(new DefaultComboBoxModel());

                    } else if (sql.buscarPosiblesLaboratorioSegunLetrasDigitadasEnLaCaja(nombreBuscado) == null) {

                        consultarLaboratorio.comboNombreLaboratorios.setSelectedItem("SELECCIONAR");
                        consultarLaboratorio.comboProductosDistribuidos.setModel(new DefaultComboBoxModel());
                        limpiarJTable_ConsultarLaboratorio();
                    }
                }
            }
        };
        consultarLaboratorio.cajaBuscarPorNombre.addKeyListener(kL);
    }

    private void windowListener_placeHolder_ConsultarLaboratorio() {
        FocusListener holder = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (consultarLaboratorio.cajaBuscarPorNombre.getText().equals("Filtrar por nombre") || consultarLaboratorio.cajaBuscarPorNombre.getText().equals("")) {
                    consultarLaboratorio.cajaBuscarPorNombre.setText("");
                    consultarLaboratorio.cajaBuscarPorNombre.setForeground(new Color(102, 102, 102));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (consultarLaboratorio != null && consultarLaboratorio.cajaBuscarPorNombre.getText().equals("")) {
                    consultarLaboratorio.cajaBuscarPorNombre.setText("Filtrar por nombre");
                    consultarLaboratorio.cajaBuscarPorNombre.setForeground(new Color(204, 204, 204));
                }
            }
        };
        consultarLaboratorio.cajaBuscarPorNombre.addFocusListener(holder);
    }

    private void formWindowActivated_ConsultarLaboratorio() {
        consultarLaboratorio.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent evt) {
                consultarLaboratorio.cajaBuscarPorNombre.requestFocus();
            }
        });

    }

    private void oyenteAccion_VentanaConsultarLaboratorio_BotonSalir() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == consultarLaboratorio.botonSalir) {
                    consultarLaboratorio.dispose();
                    consultarLaboratorio = null;
                }
            }
        };
        consultarLaboratorio.botonSalir.addActionListener(aL);
    }

    private void limpiarJTable_ConsultarLaboratorio() {
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Nombre Laboratorio");
        modeloTabla.addColumn("Producto Distribuido");
        consultarLaboratorio.tablaLaboratoriosConsultados.setModel(modeloTabla);
    }

    private void VentanaConsultarLaboratorio_WindowClossing() {
        consultarLaboratorio.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                consultarLaboratorio.dispose();
                consultarLaboratorio = null;
            }
        });
    }

    //-----------------------------------------------------------------------------------------------------------------------------------// Ventana Consultar Proveedor
    private void oyenteAccion_VentanaPrincipal_BarMenuConsultar_MenuitemProveedor() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == ventanaPrincipal.itemConsultarProveedores) {

                    if (consultarProveedor == null) {
                        consultarProveedor = new ConsultarProveedor(ventanaPrincipal, false);
                    }

                    oyente_Teclado_CajaBuscarPorCedula_ConsultarProveedor();
                    formWindowActivated__ConsultarProveedor();
                    windowListener_placeHolder__ConsultarProveedor();
                    mouseClicked_TableModel_ConsultarProveedor();
                    keyReleased_TableModel_ConsultarProveedor();
                    oyenteAccion_VentanaConsultarProveedor_BotonBuscarTodoProveedor();
                    oyenteAccion_Ventana_ConsultarProveedor_BotonSalir();
                    Ventana_ConsultarProveedor_WindowClossing();

                    DefaultTableModel tM = new DefaultTableModel();
                    tM.addColumn("Nombre");
                    tM.addColumn("Apellido");
                    tM.addColumn("Cedula");
                    tM.addColumn("Correo");
                    tM.addColumn("Celular");
                    tM.addColumn("Empresa");
                    tM.addColumn("Tel Emp.");
                    tM.addColumn("Dir Emp.");
                    consultarProveedor.tablaProveedoresConsultados.setModel(tM);
                    TableRowSorter<TableModel> trs = new TableRowSorter<>(tM);
                    consultarProveedor.tablaProveedoresConsultados.setRowSorter(trs);

                    consultarProveedor.cajaNombre.setEditable(false);
                    consultarProveedor.cajaApellido.setEditable(false);
                    consultarProveedor.cajaCedula.setEditable(false);
                    consultarProveedor.cajaCorreo.setEditable(false);
                    consultarProveedor.cajaCelular.setEditable(false);
                    consultarProveedor.cajaEmpresa.setEditable(false);
                    consultarProveedor.cajaTelEmp.setEditable(false);
                    consultarProveedor.cajaDirEmp.setEditable(false);
                    consultarProveedor.comboMedicamentos.setEnabled(false);

                    consultarProveedor.botonBuscarTodoProveedor.setVisible(true);
                    consultarProveedor.botonSalir.setVisible(true);
                    consultarProveedor.botonBuscarTodoProveedor.setEnabled(true);
                    consultarProveedor.botonSalir.setEnabled(true);

                    consultarProveedor.setLocationRelativeTo(null);
                    consultarProveedor.setVisible(true);
                }
            }
        };
        ventanaPrincipal.itemConsultarProveedores.addActionListener(aL);
    }

    private void oyenteAccion_VentanaConsultarProveedor_BotonBuscarTodoProveedor() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == consultarProveedor.botonBuscarTodoProveedor) {

                    if (sql.buscarTodoProveedor() != null) {

                        DefaultTableModel modeloTabla = sql.buscarTodoProveedor();
                        consultarProveedor.tablaProveedoresConsultados.setModel(modeloTabla);
                        TableRowSorter<TableModel> trs = new TableRowSorter<>(modeloTabla);
                        consultarProveedor.tablaProveedoresConsultados.setRowSorter(trs);

                        consultarProveedor.cajaBuscarPorCedula.setText("");

                    } else {
                        JOptionPane.showMessageDialog(null, "Error al cargar los clientes desde la base de datos", "Error en la base de datos", JOptionPane.PLAIN_MESSAGE, iconError);
                    }
                }
            }
        };
        consultarProveedor.botonBuscarTodoProveedor.addActionListener(aL);
    }

    private void oyente_Teclado_CajaBuscarPorCedula_ConsultarProveedor() {
        KeyListener kL = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (!consultarProveedor.cajaBuscarPorCedula.getText().equals(" ") && !consultarProveedor.cajaBuscarPorCedula.getText().equals("")) {
                    try {
                        int cedulaBuscada = Integer.parseInt(consultarProveedor.cajaBuscarPorCedula.getText());

                        if (sql.buscarPosiblesProveedoresSegunNumerosDigitadosEnLaCaja(cedulaBuscada) != null) {
                            DefaultTableModel modeloTemporal = sql.buscarPosiblesProveedoresSegunNumerosDigitadosEnLaCaja(cedulaBuscada);
                            consultarProveedor.tablaProveedoresConsultados.setModel(modeloTemporal);
                            TableRowSorter<TableModel> trs = new TableRowSorter<>(modeloTemporal);
                            consultarProveedor.tablaProveedoresConsultados.setRowSorter(trs);
                        } else {
                            limpiarCajas_ConsultarProveedor();
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Cedula incorrecta, Solo ingresar numeros \nsin punto,  sin coma.", " Validar Cedula", JOptionPane.PLAIN_MESSAGE, icon);
                        limpiarCajas_ConsultarProveedor();
                        //consultarCliente.cajaBuscarPorCedula.setText(null);
                    }
                }
            }
        };
        consultarProveedor.cajaBuscarPorCedula.addKeyListener(kL);
    }

    private void keyReleased_TableModel_ConsultarProveedor() {
        KeyListener kL = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.VK_DOWN == e.getKeyCode()) {

                    ArrayList<Producto> listaProducto = new ArrayList<>();
                    Producto producto;
                    DefaultComboBoxModel cbm = new DefaultComboBoxModel();
                    Proveedor proveedor;

                    int fila = consultarProveedor.tablaProveedoresConsultados.getSelectedRow();
                    String cedulaP = consultarProveedor.tablaProveedoresConsultados.getValueAt(fila, 2).toString();
                    int cedula = Integer.parseInt(cedulaP);
                    proveedor = sql.buscarProveedor(cedula);
                    int idProveedor = proveedor.getIdProveedor();

                    consultarProveedor.cajaNombre.setText(proveedor.getNombre());
                    consultarProveedor.cajaApellido.setText(proveedor.getApellido());
                    consultarProveedor.cajaCedula.setText(String.valueOf(proveedor.getCedula()));
                    consultarProveedor.cajaCorreo.setText(proveedor.getCorreo());
                    consultarProveedor.cajaCelular.setText(proveedor.getCelular());
                    consultarProveedor.cajaEmpresa.setText(proveedor.getNombreEmpresa());
                    consultarProveedor.cajaTelEmp.setText(proveedor.getTelEmpresa());
                    consultarProveedor.cajaDirEmp.setText(proveedor.getDirEmpresa());

                    listaProducto = sql.buscarProductoPorProveedor(idProveedor);

                    int i = 0;
                    while (i < listaProducto.size()) {
                        producto = listaProducto.get(i);
                        cbm.addElement(producto);
                        i++;
                    }
                    consultarProveedor.comboMedicamentos.setModel(cbm);
                    consultarProveedor.comboMedicamentos.setEnabled(true);

                } else if (e.VK_UP == e.getKeyCode()) {

                    ArrayList<Producto> listaProducto = new ArrayList<>();
                    Producto producto;
                    DefaultComboBoxModel cbm = new DefaultComboBoxModel();
                    Proveedor proveedor;

                    int fila = consultarProveedor.tablaProveedoresConsultados.getSelectedRow();
                    String cedulaP = consultarProveedor.tablaProveedoresConsultados.getValueAt(fila, 2).toString();
                    int cedula = Integer.parseInt(cedulaP);
                    proveedor = sql.buscarProveedor(cedula);
                    int idProveedor = proveedor.getIdProveedor();

                    consultarProveedor.cajaNombre.setText(proveedor.getNombre());
                    consultarProveedor.cajaApellido.setText(proveedor.getApellido());
                    consultarProveedor.cajaCedula.setText(String.valueOf(proveedor.getCedula()));
                    consultarProveedor.cajaCorreo.setText(proveedor.getCorreo());
                    consultarProveedor.cajaCelular.setText(proveedor.getCelular());
                    consultarProveedor.cajaEmpresa.setText(proveedor.getNombreEmpresa());
                    consultarProveedor.cajaTelEmp.setText(proveedor.getTelEmpresa());
                    consultarProveedor.cajaDirEmp.setText(proveedor.getDirEmpresa());

                    listaProducto = sql.buscarProductoPorProveedor(idProveedor);

                    int i = 0;
                    while (i < listaProducto.size()) {
                        producto = listaProducto.get(i);
                        cbm.addElement(producto);
                        i++;
                    }
                    consultarProveedor.comboMedicamentos.setModel(cbm);
                    consultarProveedor.comboMedicamentos.setEnabled(true);
                }
            }
        };
        consultarProveedor.tablaProveedoresConsultados.addKeyListener(kL);
    }

    private void mouseClicked_TableModel_ConsultarProveedor() {
        MouseListener mL = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int fila = consultarProveedor.tablaProveedoresConsultados.getSelectedRow();
                String cedulaP = consultarProveedor.tablaProveedoresConsultados.getValueAt(fila, 2).toString();
                int cedula = Integer.parseInt(cedulaP);

                Proveedor proveedor;
                ArrayList<Producto> listaProducto;
                Producto producto;
                DefaultComboBoxModel cbm = new DefaultComboBoxModel();

                proveedor = sql.buscarProveedor(cedula);
                int idProveedor = proveedor.getIdProveedor();
                consultarProveedor.cajaNombre.setText(proveedor.getNombre());
                consultarProveedor.cajaApellido.setText(proveedor.getApellido());
                consultarProveedor.cajaCedula.setText(String.valueOf(proveedor.getCedula()));
                consultarProveedor.cajaCorreo.setText(proveedor.getCorreo());
                consultarProveedor.cajaCelular.setText(proveedor.getCelular());
                consultarProveedor.cajaEmpresa.setText(proveedor.getNombreEmpresa());
                consultarProveedor.cajaTelEmp.setText(proveedor.getTelEmpresa());
                consultarProveedor.cajaDirEmp.setText(proveedor.getDirEmpresa());

                listaProducto = sql.buscarProductoPorProveedor(idProveedor);

                int i = 0;
                while (i < listaProducto.size()) {
                    producto = listaProducto.get(i);
                    cbm.addElement(producto);
                    i++;
                }
                consultarProveedor.comboMedicamentos.setModel(cbm);
                consultarProveedor.comboMedicamentos.setEnabled(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        consultarProveedor.tablaProveedoresConsultados.addMouseListener(mL);
    }

    private void windowListener_placeHolder__ConsultarProveedor() {
        FocusListener holder = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (consultarProveedor.cajaBuscarPorCedula.getText().equals("Filtrar por Cedula") || consultarProveedor.cajaBuscarPorCedula.getText().equals("")) {
                    consultarProveedor.cajaBuscarPorCedula.setText("");
                    consultarProveedor.cajaBuscarPorCedula.setForeground(new Color(102, 102, 102));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (consultarProveedor != null && consultarProveedor.cajaBuscarPorCedula.getText().equals("")) {
                    consultarProveedor.cajaBuscarPorCedula.setText("Filtrar por Cedula");
                    consultarProveedor.cajaBuscarPorCedula.setForeground(new Color(204, 204, 204));
                }
            }
        };
        consultarProveedor.cajaBuscarPorCedula.addFocusListener(holder);
    }

    private void formWindowActivated__ConsultarProveedor() {
        consultarProveedor.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent evt) {
                consultarProveedor.cajaBuscarPorCedula.requestFocus();
            }
        });

    }

    private void oyenteAccion_Ventana_ConsultarProveedor_BotonSalir() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCajas_ConsultarProveedor();
                consultarProveedor.dispose();
                consultarProveedor = null;
            }
        };
        consultarProveedor.botonSalir.addActionListener(aL);
    }

    private void limpiarCajas_ConsultarProveedor() {
        consultarProveedor.cajaNombre.setText(null);
        consultarProveedor.cajaApellido.setText(null);
        consultarProveedor.cajaCedula.setText(null);
        consultarProveedor.cajaCorreo.setText(null);
        consultarProveedor.cajaCelular.setText(null);
        consultarProveedor.cajaEmpresa.setText(null);
        consultarProveedor.cajaTelEmp.setText(null);
        consultarProveedor.cajaDirEmp.setText(null);
        consultarProveedor.comboMedicamentos.setModel(new DefaultComboBoxModel());
        consultarProveedor.cajaBuscarPorCedula.setText(null);
        limpiarJTable_ConsultarProveedor();
    }

    private void limpiarJTable_ConsultarProveedor() {
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Apellido");
        modeloTabla.addColumn("Cedula");
        modeloTabla.addColumn("Correo");
        modeloTabla.addColumn("Celular");
        modeloTabla.addColumn("Empresa");
        modeloTabla.addColumn("Tel Emp.");
        modeloTabla.addColumn("Dir Emp.");
        consultarProveedor.tablaProveedoresConsultados.setModel(modeloTabla);
        TableRowSorter<TableModel> trs = new TableRowSorter<>(modeloTabla);
        consultarProveedor.tablaProveedoresConsultados.setRowSorter(trs);

    }

    private void Ventana_ConsultarProveedor_WindowClossing() {
        consultarProveedor.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                limpiarCajas_ConsultarProveedor();
                consultarProveedor.dispose();
                consultarProveedor = null;
            }
        });
    }

    //-------------------------------------------------------------------------------------------------------------------------------// Ventana Consultar Productos(Medicamentos)
    private void oyenteAccion_VentanaPrincipal_BarMenuConsultar_MenuitemMedicamentos() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == ventanaPrincipal.itemConsultarMedicamentos) {

                    if (consultarProducto == null) {
                        consultarProducto = new ConsultarProducto(ventanaPrincipal, false);
                    }

                    oyenteAccion_VentanaConsultarMedicamentos_BotonBuscar();
                    oyenteAccion_VentanaConsultarMedicamentos_BotonBuscarTodoMedicamentos();
                    oyente_Teclado_CajaBuscar_ConsultarMedicamentos();
                    formWindowActivated__ConsultarMedicamentos();
                    windowListener_placeHolder__ConsultarMedicamentos();
                    mouseClicked_TableModel_ConsultarMedicamentos();
                    keyReleased_TableModel_ConsultarMedicamentos();
                    Ventana_ConsultarMedicamentos_WindowClossing();

                    consultarProducto.cajaBuscar.setEnabled(true);
                    consultarProducto.cajaNombre.setEditable(false);
                    consultarProducto.cajaCantidadStock.setEditable(false);
                    consultarProducto.cajaPrecio.setEditable(false);
                    consultarProducto.cajaLaboratorio.setEditable(false);
                    consultarProducto.cajaProveedor.setEditable(false);
                    consultarProducto.cajaCodigodeBarras.setEditable(false);
                    consultarProducto.TextAreaDescripcion.setEditable(false);
                    consultarProducto.dateChooserFechaVencimiento.setEnabled(false);

                    consultarProducto.botonBuscarTodoProducto.setVisible(true);
                    consultarProducto.botonBuscarTodoProducto.setEnabled(true);

                    DefaultComboBoxModel modeloComboBucar = new DefaultComboBoxModel();
                    modeloComboBucar.addElement("Codigo de Barras");
                    modeloComboBucar.addElement("Nombre");
                    consultarProducto.comboElegirFormaBusqueda.setModel(modeloComboBucar);

                    DefaultTableModel modeloTabla = new DefaultTableModel() {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    };
                    modeloTabla.addColumn("Nombre");
                    modeloTabla.addColumn("Precio");
                    modeloTabla.addColumn("Cantidad Stock");
                    modeloTabla.addColumn("Fecha Vencimiento");
                    modeloTabla.addColumn("Descripción");
                    modeloTabla.addColumn("Laboratorio");
                    modeloTabla.addColumn("Proveedor");
                    modeloTabla.addColumn("Codigo barras");
                    consultarProducto.tablaProductosConsultados.setModel(modeloTabla);
                    TableRowSorter<TableModel> trs = new TableRowSorter<>(modeloTabla);
                    consultarProducto.tablaProductosConsultados.setRowSorter(trs);

                    consultarProducto.setLocationRelativeTo(null);
                    consultarProducto.setVisible(true);
                }
            }
        };
        ventanaPrincipal.itemConsultarMedicamentos.addActionListener(aL);
    }

    private void oyenteAccion_VentanaConsultarMedicamentos_BotonBuscar() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == consultarProducto.botonBuscarPorEleccion) {

                    if (!consultarProducto.cajaBuscar.getText().equals("")) {
                        String DatoProductoABuscar = consultarProducto.cajaBuscar.getText();

                        if (consultarProducto.comboElegirFormaBusqueda.getSelectedItem().equals("Codigo de Barras")) {

                            if (sql.buscarProducto(DatoProductoABuscar) != null) {
                                Producto producto = sql.buscarProducto(DatoProductoABuscar);

                                consultarProducto.cajaNombre.setText(producto.getNombre());
                                consultarProducto.cajaCantidadStock.setText(String.valueOf(producto.getCantidadStock()));
                                consultarProducto.cajaPrecio.setText(String.valueOf(producto.getPrecio()));
                                consultarProducto.cajaCodigodeBarras.setText(producto.getCodigoBarras());
                                consultarProducto.cajaLaboratorio.setText(producto.getLaboratorio().toString());
                                if (producto.getProveedor() != null) {
                                    consultarProducto.cajaProveedor.setText(producto.getProveedor().toString());
                                } else {
                                    consultarProducto.cajaProveedor.setText("N/A");
                                }
                                DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd");
                                try {
                                    Date date = fechaHora.parse(producto.getFechaVencimiento());  // Paso la fecha vencimiento de String a Date en el formato que necesito
                                    consultarProducto.dateChooserFechaVencimiento.setDate(date);
                                } catch (ParseException ex) {
                                }
                                consultarProducto.TextAreaDescripcion.setText(producto.getDescripcion());

                            } else {
                                JOptionPane.showMessageDialog(null, "Validar datos ingresados \nCodigo de Barras no encontrado.", " Validar Codigo de Barras", JOptionPane.PLAIN_MESSAGE, icon);
                                consultarProducto.cajaNombre.setText(null);
                                consultarProducto.cajaCantidadStock.setText(null);
                                consultarProducto.cajaPrecio.setText(null);
                                consultarProducto.cajaLaboratorio.setText(null);
                                consultarProducto.cajaProveedor.setText(null);
                                consultarProducto.cajaCodigodeBarras.setText(null);
                                consultarProducto.TextAreaDescripcion.setText(null);
                                consultarProducto.dateChooserFechaVencimiento.setDate(date);
                            }
                        } else if (consultarProducto.comboElegirFormaBusqueda.getSelectedItem().equals("Nombre")) {

                            if (sql.buscarProductoPorNombre(DatoProductoABuscar) != null) {

                                Producto producto = sql.buscarProductoPorNombre(DatoProductoABuscar);

                                consultarProducto.cajaNombre.setText(producto.getNombre());
                                consultarProducto.cajaCantidadStock.setText(String.valueOf(producto.getCantidadStock()));
                                consultarProducto.cajaPrecio.setText(String.valueOf(producto.getPrecio()));
                                consultarProducto.cajaCodigodeBarras.setText(producto.getCodigoBarras());
                                consultarProducto.cajaLaboratorio.setText(producto.getLaboratorio().toString());
                                if (producto.getProveedor() != null) {
                                    consultarProducto.cajaProveedor.setText(producto.getProveedor().toString());
                                } else {
                                    consultarProducto.cajaProveedor.setText("N/A");
                                }

                                DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd");
                                try {
                                    Date date = fechaHora.parse(producto.getFechaVencimiento());  // Paso la fecha vencimiento de String a Date en el formato que necesito
                                    consultarProducto.dateChooserFechaVencimiento.setDate(date);
                                } catch (ParseException ex) {
                                }
                                consultarProducto.TextAreaDescripcion.setText(producto.getDescripcion());
                            } else {
                                JOptionPane.showMessageDialog(null, "Validar datos ingresados \nNombre de producto no encontrado.", " Validar Nombre de producto", JOptionPane.PLAIN_MESSAGE, icon);
                                consultarProducto.cajaNombre.setText(null);
                                consultarProducto.cajaCantidadStock.setText(null);
                                consultarProducto.cajaPrecio.setText(null);
                                consultarProducto.cajaLaboratorio.setText(null);
                                consultarProducto.cajaProveedor.setText(null);
                                consultarProducto.cajaCodigodeBarras.setText(null);
                                consultarProducto.TextAreaDescripcion.setText(null);
                                consultarProducto.dateChooserFechaVencimiento.setDate(date);
                            }
                        }
                    } else if (consultarProducto.cajaBuscar.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Validar datos ingresados \nproducto no encontrado.", " Validar Datos", JOptionPane.PLAIN_MESSAGE, icon);
                    }
                }
            }
        };
        consultarProducto.botonBuscarPorEleccion.addActionListener(aL);
    }

    private void oyenteAccion_VentanaConsultarMedicamentos_BotonBuscarTodoMedicamentos() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == consultarProducto.botonBuscarTodoProducto) {

                    if (sql.buscarTodoProducto() != null) {

                        DefaultTableModel modeloTabla = sql.buscarTodoProducto();
                        consultarProducto.tablaProductosConsultados.setModel(modeloTabla);
                        TableRowSorter<TableModel> trs = new TableRowSorter<>(modeloTabla);
                        consultarProducto.tablaProductosConsultados.setRowSorter(trs);

                    } else {
                        JOptionPane.showMessageDialog(null, "Error al cargar los clientes desde la base de datos", "Error en la base de datos", JOptionPane.PLAIN_MESSAGE, iconError);
                    }
                }
            }
        };
        consultarProducto.botonBuscarTodoProducto.addActionListener(aL);
    }

    private void oyente_Teclado_CajaBuscar_ConsultarMedicamentos() {
        KeyListener kL = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (!consultarProducto.cajaBuscar.getText().equals(" ") && !consultarProducto.cajaBuscar.getText().equals("")) {

                    String DatoProductoABuscar = consultarProducto.cajaBuscar.getText();

                    if (consultarProducto.comboElegirFormaBusqueda.getSelectedItem().equals("Codigo de Barras")) {

                        if (sql.buscarProducto(DatoProductoABuscar) != null) {

                            Producto producto = sql.buscarProducto(DatoProductoABuscar);

                            consultarProducto.cajaNombre.setText(producto.getNombre());
                            consultarProducto.cajaCantidadStock.setText(String.valueOf(producto.getCantidadStock()));
                            consultarProducto.cajaPrecio.setText(String.valueOf(producto.getPrecio()));
                            consultarProducto.cajaCodigodeBarras.setText(producto.getCodigoBarras());
                            consultarProducto.cajaLaboratorio.setText(producto.getLaboratorio().toString());
                            if (producto.getProveedor() != null) {
                                consultarProducto.cajaProveedor.setText(producto.getProveedor().toString());
                            } else {
                                consultarProducto.cajaProveedor.setText("N/A");
                            }
                            DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd");
                            try {
                                Date date = fechaHora.parse(producto.getFechaVencimiento());  // Paso la fecha vencimiento de String a Date en el formato que necesito
                                consultarProducto.dateChooserFechaVencimiento.setDate(date);
                            } catch (ParseException ex) {
                            }
                            consultarProducto.TextAreaDescripcion.setText(producto.getDescripcion());

                        } else {
                            consultarProducto.cajaNombre.setText(null);
                            consultarProducto.cajaCantidadStock.setText(null);
                            consultarProducto.cajaPrecio.setText(null);
                            consultarProducto.cajaLaboratorio.setText(null);
                            consultarProducto.cajaProveedor.setText(null);
                            consultarProducto.cajaCodigodeBarras.setText(null);
                            consultarProducto.TextAreaDescripcion.setText(null);
                            consultarProducto.dateChooserFechaVencimiento.setDate(date);
                        }
                    }

                    if (consultarProducto.comboElegirFormaBusqueda.getSelectedItem().equals("Nombre")) {

                        if (sql.buscarProductoPorNombre(DatoProductoABuscar) != null) {

                            Producto producto = sql.buscarProductoPorNombre(DatoProductoABuscar);

                            consultarProducto.cajaNombre.setText(producto.getNombre());
                            consultarProducto.cajaCantidadStock.setText(String.valueOf(producto.getCantidadStock()));
                            consultarProducto.cajaPrecio.setText(String.valueOf(producto.getPrecio()));
                            consultarProducto.cajaCodigodeBarras.setText(producto.getCodigoBarras());
                            consultarProducto.cajaLaboratorio.setText(producto.getLaboratorio().toString());
                            if (producto.getProveedor() != null) {
                                consultarProducto.cajaProveedor.setText(producto.getProveedor().toString());
                            } else {
                                consultarProducto.cajaProveedor.setText("N/A");
                            }
                            DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd");
                            try {
                                Date date = fechaHora.parse(producto.getFechaVencimiento());  // Paso la fecha vencimiento de String a Date en el formato que necesito
                                consultarProducto.dateChooserFechaVencimiento.setDate(date);
                            } catch (ParseException ex) {
                            }
                            consultarProducto.TextAreaDescripcion.setText(producto.getDescripcion());

                        } else {
                            consultarProducto.cajaNombre.setText(null);
                            consultarProducto.cajaCantidadStock.setText(null);
                            consultarProducto.cajaPrecio.setText(null);
                            consultarProducto.cajaLaboratorio.setText(null);
                            consultarProducto.cajaProveedor.setText(null);
                            consultarProducto.cajaCodigodeBarras.setText(null);
                            consultarProducto.TextAreaDescripcion.setText(null);
                            consultarProducto.dateChooserFechaVencimiento.setDate(date);
                        }
                    }
                }
            }
        };
        consultarProducto.cajaBuscar.addKeyListener(kL);
    }

    private void keyReleased_TableModel_ConsultarMedicamentos() {
        KeyListener kL = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.VK_DOWN == e.getKeyCode()) {

                    int fila = consultarProducto.tablaProductosConsultados.getSelectedRow();
                    String codigoBarras = consultarProducto.tablaProductosConsultados.getValueAt(fila, 7).toString();

                    Producto producto = sql.buscarProducto(codigoBarras);
                    consultarProducto.cajaNombre.setText(producto.getNombre());
                    consultarProducto.cajaCantidadStock.setText(String.valueOf(producto.getCantidadStock()));
                    consultarProducto.cajaPrecio.setText(String.valueOf(producto.getPrecio()));
                    consultarProducto.cajaCodigodeBarras.setText(producto.getCodigoBarras());
                    consultarProducto.cajaLaboratorio.setText(producto.getLaboratorio().toString());
                    if (producto.getProveedor() != null) {
                        consultarProducto.cajaProveedor.setText(producto.getProveedor().toString());
                    } else {
                        consultarProducto.cajaProveedor.setText("N/A");
                    }
                    DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date date = fechaHora.parse(producto.getFechaVencimiento());  // Paso la fecha vencimiento de String a Date en el formato que necesito
                        consultarProducto.dateChooserFechaVencimiento.setDate(date);
                    } catch (ParseException ex) {
                    }
                    consultarProducto.TextAreaDescripcion.setText(producto.getDescripcion());

                } else if (e.VK_UP == e.getKeyCode()) {

                    int fila = consultarProducto.tablaProductosConsultados.getSelectedRow();
                    String codigoBarras = consultarProducto.tablaProductosConsultados.getValueAt(fila, 7).toString();

                    Producto producto = sql.buscarProducto(codigoBarras);
                    consultarProducto.cajaNombre.setText(producto.getNombre());
                    consultarProducto.cajaCantidadStock.setText(String.valueOf(producto.getCantidadStock()));
                    consultarProducto.cajaPrecio.setText(String.valueOf(producto.getPrecio()));
                    consultarProducto.cajaCodigodeBarras.setText(producto.getCodigoBarras());
                    consultarProducto.cajaLaboratorio.setText(producto.getLaboratorio().toString());
                    if (producto.getProveedor() != null) {
                        consultarProducto.cajaProveedor.setText(producto.getProveedor().toString());
                    } else {
                        consultarProducto.cajaProveedor.setText("N/A");
                    }
                    DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date date = fechaHora.parse(producto.getFechaVencimiento());  // Paso la fecha vencimiento de String a Date en el formato que necesito
                        consultarProducto.dateChooserFechaVencimiento.setDate(date);
                    } catch (ParseException ex) {
                    }
                    consultarProducto.TextAreaDescripcion.setText(producto.getDescripcion());
                }
            }
        };
        consultarProducto.tablaProductosConsultados.addKeyListener(kL);
    }

    private void mouseClicked_TableModel_ConsultarMedicamentos() {
        MouseListener mL = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int fila = consultarProducto.tablaProductosConsultados.getSelectedRow();
                String codigoBarras = consultarProducto.tablaProductosConsultados.getValueAt(fila, 7).toString();

                Producto producto = sql.buscarProducto(codigoBarras);
                consultarProducto.cajaNombre.setText(producto.getNombre());
                consultarProducto.cajaCantidadStock.setText(String.valueOf(producto.getCantidadStock()));
                consultarProducto.cajaPrecio.setText(String.valueOf(producto.getPrecio()));
                consultarProducto.cajaCodigodeBarras.setText(producto.getCodigoBarras());
                consultarProducto.cajaLaboratorio.setText(producto.getLaboratorio().toString());
                if (producto.getProveedor() != null) {
                    consultarProducto.cajaProveedor.setText(producto.getProveedor().toString());
                } else {
                    consultarProducto.cajaProveedor.setText("N/A");
                }
                DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date date = fechaHora.parse(producto.getFechaVencimiento());  // Paso la fecha vencimiento de String a Date en el formato que necesito
                    consultarProducto.dateChooserFechaVencimiento.setDate(date);
                } catch (ParseException ex) {
                }
                consultarProducto.TextAreaDescripcion.setText(producto.getDescripcion());
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        consultarProducto.tablaProductosConsultados.addMouseListener(mL);
    }

    private void windowListener_placeHolder__ConsultarMedicamentos() {
        FocusListener holder = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (consultarProducto.cajaBuscar.getText().equals("Digite los datos del medicamento") || consultarProducto.cajaBuscar.getText().equals("")) {
                    consultarProducto.cajaBuscar.setText("");
                    consultarProducto.cajaBuscar.setForeground(new Color(102, 102, 102));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (consultarProducto != null && consultarProducto.cajaBuscar.getText().equals("")) {
                    consultarProducto.cajaBuscar.setText("Digite los datos del medicamento");
                    consultarProducto.cajaBuscar.setForeground(new Color(204, 204, 204));
                }
            }
        };
        consultarProducto.cajaBuscar.addFocusListener(holder);
    }

    private void formWindowActivated__ConsultarMedicamentos() {
        consultarProducto.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent evt) {
                consultarProducto.cajaBuscar.requestFocus();
            }
        });

    }

    private void limpiarCajas_ConsultarMedicamentos() {
        consultarProducto.cajaBuscar.setText(null);
        consultarProducto.cajaNombre.setText(null);
        consultarProducto.cajaCantidadStock.setText(null);
        consultarProducto.cajaPrecio.setText(null);
        consultarProducto.cajaLaboratorio.setText(null);
        consultarProducto.cajaProveedor.setText(null);
        consultarProducto.cajaCodigodeBarras.setText(null);
        consultarProducto.TextAreaDescripcion.setText(null);
        consultarProducto.dateChooserFechaVencimiento.setDate(date);

        consultarProducto.comboElegirFormaBusqueda.setSelectedItem("Seleccionar");
        limpiarJTable_ConsultarMedicamentos();
    }

    private void limpiarJTable_ConsultarMedicamentos() {
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Cantidad Stock");
        modeloTabla.addColumn("Fecha Vencimiento");
        modeloTabla.addColumn("Descripción");
        modeloTabla.addColumn("Laboratorio");
        modeloTabla.addColumn("Proveedor");
        modeloTabla.addColumn("Codigo barras");
        consultarProducto.tablaProductosConsultados.setModel(modeloTabla);
        TableRowSorter<TableModel> trs = new TableRowSorter<>(modeloTabla);
        consultarProducto.tablaProductosConsultados.setRowSorter(trs);

    }

    private void Ventana_ConsultarMedicamentos_WindowClossing() {
        consultarProducto.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                limpiarCajas_ConsultarMedicamentos();
                consultarProducto.dispose();
                consultarProducto = null;
            }
        });
    }

    //---------------------------------------------------- ConsultarVentas
    private void ActionListener_VentanaPrincipal_BarMenuConsultar_itemConsultarVentas() {
        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (consultarVentas == null) {
                    consultarVentas = new ConsultarVentas(ventanaPrincipal, true);
                }

                ActionListener_BotonConsultar_VentanaConsultarVentas();
                windowClosing_VentanaConsultarVentas();

                consultarVentas.setLocationRelativeTo(null);
                consultarVentas.setVisible(true);

            }
        };
        ventanaPrincipal.itemConsultarVentas.addActionListener(aL);
    }

    private void ActionListener_BotonConsultar_VentanaConsultarVentas() {
        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Paso la fecha al formato necesario
                DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                int idUser = 0;

                if (consultarVentas.dateChooser.getDate() != null) {
                    String fechaEscogida = formato.format(consultarVentas.dateChooser.getDate());

                    if (!consultarVentas.cajaCedulaEmpleado.getText().equals("")) {
                        try {
                            int cedulaEmpleado = Integer.parseInt(consultarVentas.cajaCedulaEmpleado.getText());
                            Usuario user = sql.buscarUsuario(cedulaEmpleado);
                            if (user == null) {
                                JOptionPane.showMessageDialog(null, "El numero de cedula ingresado no  \nse encuentra en el sistema.", " Validar Cedula", JOptionPane.PLAIN_MESSAGE, icon);
                            } else {
                                idUser = user.getIdUsuario();
                                consultarVentas.dispose();
                                consultarVentas = null;
                                sql.cierreDeCaja("src\\Reportes\\ReporteDeVentas.jasper", fechaEscogida, idUser);
                            }

                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Cedula incorrecta, Solo ingresar numeros \nsin punto,  sin coma.", " Validar Cedula", JOptionPane.PLAIN_MESSAGE, icon);
                        }
                    } else if (consultarVentas.cajaCedulaEmpleado.getText().equals("")) {
                        consultarVentas.dispose();
                        consultarVentas = null;
                        sql.cierreDeCaja("src\\Reportes\\ReporteDeVentasTodosDia.jasper", fechaEscogida, idUser);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No ha indicado la fecha a consultar.", " Ingresar Fecha", JOptionPane.PLAIN_MESSAGE, icon);
                }
            }
        };
        consultarVentas.botonConsultar.addActionListener(aL);
    }

    private void windowClosing_VentanaConsultarVentas() {
        consultarVentas.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                consultarVentas.dispose();
                consultarVentas = null;
            }
        });
    }

    //--------------------------------------------------------------------------------------------------------------------------------------------------//Gestion Facturas
    private void ActionListener_VentanaPrincipal_BarMenuAdministrar_itemGestionarFactura() {
        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (ventanaPrincipal.labelCargo.getText().equals("Administrador")) {
                    if (gestionarFactura == null) {
                        gestionarFactura = new GestionarFacturas(ventanaPrincipal, true);
                    }

                    ActionListener_BotonBuscar_VentanaGestionarFactura();
                    ActionListener_BotonCambio_VentanaGestionarFactura();
                    ActionListener_BotonAnular_VentanaGestionarFactura();
                    ActionListener_BotonCancelar_VentanaGestionarFactura();
                    ActionListener_itemEliminarDetalleFactura_MenuDesplegableMouse_TablaDetalleFactura_VentanaGestionarFactura();
                    keyListener_CajaCodigoBarras1_IngresarProductoPorCodigoBarras_VentanaGestionarFactura();
                    mouseListener_MenuDesplegableMouse_TablaDetalleFactura_VentanaFacturar();
                    mouseListener_LabelBotonBorra_VentanaFacturar();
                    windowListener_placeHolder__VentanaGestionarFactura_cajaConsecutivoFactura();
                    windowListener_placeHolder__VentanaGestionarFactura_cajaCodigoBarras();
                    windowClosing_VentanaGestionarFactura();

                    modeloTablaDetalleFactura = new DefaultTableModel() {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            if (ventanaPrincipal.cajaCantidadDetalleAModificar.isVisible() && ventanaPrincipal.cajaDescuentoAModificar.isVisible() && ventanaPrincipal.caja_IvaModificar.isVisible()) {
                                if (column == 0 || column == 4 || column == 6) {
                                    //return true;
                                    return false;
                                } else {
                                    return false;
                                }
                            } else {
                                return false;
                            }
                        }
                    };
                    modeloTablaDetalleFactura.addColumn("Cant.");
                    modeloTablaDetalleFactura.addColumn("Descripción");
                    modeloTablaDetalleFactura.addColumn("Precio unit.");
                    modeloTablaDetalleFactura.addColumn("Importe");
                    modeloTablaDetalleFactura.addColumn("Desc.%");
                    modeloTablaDetalleFactura.addColumn("Total Importe");
                    modeloTablaDetalleFactura.addColumn("Iva%");
                    gestionarFactura.tablaDetalleFactura.setModel(modeloTablaDetalleFactura);
                    gestionarFactura.tablaDetalleFactura.setSelectionBackground(Color.lightGray);

                    int tamaño[] = {20, 200, 130, 130, 40, 130, 20};
                    int cantidadColumnas = gestionarFactura.tablaDetalleFactura.getColumnCount();

                    for (int i = 0; i < cantidadColumnas; i++) {
                        gestionarFactura.tablaDetalleFactura.getColumnModel().getColumn(i).setPreferredWidth(tamaño[i]);
                    }
                    gestionarFactura.cajaCodigoBarras1.setEnabled(false);
                    gestionarFactura.botonEditaroCambiar.setEnabled(false);
                    gestionarFactura.botonAnularFactura.setEnabled(false);
                    gestionarFactura.tANotas.setEnabled(false);
                    gestionarFactura.setLocationRelativeTo(null);
                    gestionarFactura.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(ventanaPrincipal, "Usted no tiene permisos de\nadministrador para anular facturas", "  No tiene permisos", JOptionPane.PLAIN_MESSAGE, iconWarning);
                }
            }
        };
        ventanaPrincipal.itemGestionarFacturas.addActionListener(aL);
    }

    private void ActionListener_VentanaPrincipal_BarMenuRealizar_itemDevolucionesYCambio() {
        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gestionarFactura == null) {
                    gestionarFactura = new GestionarFacturas(ventanaPrincipal, true);
                }

                ActionListener_BotonBuscar_VentanaGestionarFactura();
                ActionListener_BotonCambio_VentanaGestionarFactura();
                ActionListener_BotonAnular_VentanaGestionarFactura();
                ActionListener_BotonCancelar_VentanaGestionarFactura();
                ActionListener_itemEliminarDetalleFactura_MenuDesplegableMouse_TablaDetalleFactura_VentanaGestionarFactura();
                keyListener_CajaCodigoBarras1_IngresarProductoPorCodigoBarras_VentanaGestionarFactura();
                mouseListener_MenuDesplegableMouse_TablaDetalleFactura_VentanaFacturar();
                mouseListener_LabelBotonBorra_VentanaFacturar();
                windowListener_placeHolder__VentanaGestionarFactura_cajaConsecutivoFactura();
                windowListener_placeHolder__VentanaGestionarFactura_cajaCodigoBarras();
                windowClosing_VentanaGestionarFactura();

                modeloTablaDetalleFactura = new DefaultTableModel() {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        if (ventanaPrincipal.cajaCantidadDetalleAModificar.isVisible() && ventanaPrincipal.cajaDescuentoAModificar.isVisible() && ventanaPrincipal.caja_IvaModificar.isVisible()) {
                            if (column == 0 || column == 4 || column == 6) {
                                //return true;
                                return false;
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    }
                };
                modeloTablaDetalleFactura.addColumn("Cant.");
                modeloTablaDetalleFactura.addColumn("Descripción");
                modeloTablaDetalleFactura.addColumn("Precio unit.");
                modeloTablaDetalleFactura.addColumn("Importe");
                modeloTablaDetalleFactura.addColumn("Desc.%");
                modeloTablaDetalleFactura.addColumn("Total Importe");
                modeloTablaDetalleFactura.addColumn("Iva%");
                gestionarFactura.tablaDetalleFactura.setModel(modeloTablaDetalleFactura);
                gestionarFactura.tablaDetalleFactura.setSelectionBackground(Color.lightGray);

                int tamaño[] = {20, 200, 130, 130, 40, 130, 20};
                int cantidadColumnas = gestionarFactura.tablaDetalleFactura.getColumnCount();

                for (int i = 0; i < cantidadColumnas; i++) {
                    gestionarFactura.tablaDetalleFactura.getColumnModel().getColumn(i).setPreferredWidth(tamaño[i]);
                }
                gestionarFactura.cajaCodigoBarras1.setEnabled(false);
                gestionarFactura.botonEditaroCambiar.setEnabled(false);

                if (ventanaPrincipal.labelCargo.getText().equals("Administrador")) {
                    gestionarFactura.botonAnularFactura.setEnabled(false);
                }
                if (ventanaPrincipal.labelCargo.getText().equals("Farmaceutico") || ventanaPrincipal.labelCargo.getText().equals("Auxiliar")) {
                    gestionarFactura.botonAnularFactura.setVisible(false);
                }

                gestionarFactura.tANotas.setEnabled(false);
                gestionarFactura.setLocationRelativeTo(null);
                gestionarFactura.setVisible(true);
            }
        };
        ventanaPrincipal.itemRealizarDevolucionesOcambio.addActionListener(aL);
    }// Pertenece a una gestion de factura

    private void ActionListener_VentanaPrincipal_BarMenuConsultar_MenuitemFacturas() {
        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gestionarFactura == null) {
                    gestionarFactura = new GestionarFacturas(ventanaPrincipal, true);
                }

                ActionListener_BotonBuscar_VentanaGestionarFactura();
                ActionListener_BotonCambio_VentanaGestionarFactura();
                ActionListener_BotonAnular_VentanaGestionarFactura();
                ActionListener_BotonCancelar_VentanaGestionarFactura();
                //ActionListener_itemEliminarDetalleFactura_MenuDesplegableMouse_TablaDetalleFactura_VentanaGestionarFactura();
                keyListener_CajaCodigoBarras1_IngresarProductoPorCodigoBarras_VentanaGestionarFactura();
                //mouseListener_MenuDesplegableMouse_TablaDetalleFactura_VentanaFacturar();
                mouseListener_LabelBotonBorra_VentanaFacturar();
                windowListener_placeHolder__VentanaGestionarFactura_cajaConsecutivoFactura();
                windowListener_placeHolder__VentanaGestionarFactura_cajaCodigoBarras();
                windowClosing_VentanaGestionarFactura();

                modeloTablaDetalleFactura = new DefaultTableModel() {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        if (ventanaPrincipal.cajaCantidadDetalleAModificar.isVisible() && ventanaPrincipal.cajaDescuentoAModificar.isVisible() && ventanaPrincipal.caja_IvaModificar.isVisible()) {
                            if (column == 0 || column == 4 || column == 6) {
                                //return true;
                                return false;
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    }
                };
                modeloTablaDetalleFactura.addColumn("Cant.");
                modeloTablaDetalleFactura.addColumn("Descripción");
                modeloTablaDetalleFactura.addColumn("Precio unit.");
                modeloTablaDetalleFactura.addColumn("Importe");
                modeloTablaDetalleFactura.addColumn("Desc.%");
                modeloTablaDetalleFactura.addColumn("Total Importe");
                modeloTablaDetalleFactura.addColumn("Iva%");
                gestionarFactura.tablaDetalleFactura.setModel(modeloTablaDetalleFactura);
                gestionarFactura.tablaDetalleFactura.setSelectionBackground(Color.lightGray);

                int tamaño[] = {20, 200, 130, 130, 40, 130, 20};
                int cantidadColumnas = gestionarFactura.tablaDetalleFactura.getColumnCount();

                for (int i = 0; i < cantidadColumnas; i++) {
                    gestionarFactura.tablaDetalleFactura.getColumnModel().getColumn(i).setPreferredWidth(tamaño[i]);
                }
                gestionarFactura.cajaCodigoBarras1.setVisible(false);
                gestionarFactura.botonEditaroCambiar.setVisible(false);
                gestionarFactura.botonAnularFactura.setVisible(false);
                gestionarFactura.labelAgregarproducto.setVisible(false);
                gestionarFactura.botonBorrar1.setVisible(false);
                gestionarFactura.labelCantidad2.setVisible(false);
                gestionarFactura.cajaCantidadDetalle1.setVisible(false);
                gestionarFactura.labelDescuento1.setVisible(false);
                gestionarFactura.cajaIngresarDescuento1.setVisible(false);
                gestionarFactura.labelingresarIVA1.setVisible(false);
                gestionarFactura.cajaIngresarIVA1.setVisible(false);
                gestionarFactura.labelSigno5.setVisible(false);
                gestionarFactura.labelSigno6.setVisible(false);
                gestionarFactura.tANotas.setEnabled(false);

                gestionarFactura.setLocationRelativeTo(null);
                gestionarFactura.setVisible(true);
            }
        };
        ventanaPrincipal.itemConsultarFactura.addActionListener(aL);
    }

    private void ActionListener_BotonBuscar_VentanaGestionarFactura() {
        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!gestionarFactura.cajaConsecutivoFactura.getText().equals("")) {
                    try {
                        if (sql.buscarFactura(Integer.parseInt(gestionarFactura.cajaConsecutivoFactura.getText())) == null) {
                            JOptionPane.showMessageDialog(gestionarFactura, "Consecutivo de factura no existe ", "Verificar consecutivo", JOptionPane.PLAIN_MESSAGE, icon);

                        } else if (sql.buscarFactura(Integer.parseInt(gestionarFactura.cajaConsecutivoFactura.getText())) != null) {

                            Factura factura = sql.buscarFactura(Integer.parseInt(gestionarFactura.cajaConsecutivoFactura.getText()));

                            DecimalFormat formatoNumFac = new DecimalFormat("0000000");
                            gestionarFactura.labelConsecutivo.setText(formatoNumFac.format(Integer.parseInt(factura.getConsecutivo())));

                            gestionarFactura.labelEstadoFactura.setText(factura.getEstadoFactura());
                            gestionarFactura.cajaSubTotal.setText(String.valueOf(factura.getSubtotal()));
                            gestionarFactura.cajaIvaTotal.setText(String.valueOf(factura.getIva()));
                            gestionarFactura.cajaTotal.setText(String.valueOf(factura.getTotal()));

                            int idCliente = factura.getIdCliente();
                            Cliente cliente = new Cliente();
                            cliente = sql.buscarClienteXid(idCliente);
                            gestionarFactura.cajaNombreCliente.setText(cliente.getNombre() + " " + cliente.getApellido());
                            gestionarFactura.cajaCedulaCliente.setText(String.valueOf(cliente.getCedula()));

                            modeloTablaDetalleFactura = new DefaultTableModel() {
                                @Override
                                public boolean isCellEditable(int row, int column) {
                                    if (ventanaPrincipal.cajaCantidadDetalleAModificar.isVisible() && ventanaPrincipal.cajaDescuentoAModificar.isVisible() && ventanaPrincipal.caja_IvaModificar.isVisible()) {
                                        if (column == 0 || column == 4 || column == 6) {
                                            //return true;
                                            return false;
                                        } else {
                                            return false;
                                        }
                                    } else {
                                        return false;
                                    }
                                }
                            };
                            modeloTablaDetalleFactura.addColumn("Cant.");
                            modeloTablaDetalleFactura.addColumn("Descripción");
                            modeloTablaDetalleFactura.addColumn("Precio unit.");
                            modeloTablaDetalleFactura.addColumn("Importe");
                            modeloTablaDetalleFactura.addColumn("Desc.%");
                            modeloTablaDetalleFactura.addColumn("Total Importe");
                            modeloTablaDetalleFactura.addColumn("Iva%");
                            gestionarFactura.tablaDetalleFactura.setModel(modeloTablaDetalleFactura);
                            gestionarFactura.tablaDetalleFactura.setSelectionBackground(Color.lightGray);

                            int tamaño[] = {20, 200, 130, 130, 40, 130, 20};
                            int cantidadColumnas = gestionarFactura.tablaDetalleFactura.getColumnCount();

                            for (int i = 0; i < cantidadColumnas; i++) {
                                gestionarFactura.tablaDetalleFactura.getColumnModel().getColumn(i).setPreferredWidth(tamaño[i]);
                            }

                            gestionarFactura.tablaDetalleFactura.setDefaultRenderer(Object.class,
                                    new TablaRenderer());

                            int idFactura = factura.getIdFactura();
                            DetalleFactura detalleFactura = sql.buscarDetalleFactura(idFactura);
                            int idDetalleFactura = detalleFactura.getId_detalle_factura();

                            int cantidadFilas = sql.obtenerCantidadFilasDetalleFacturaBuscada(idFactura);
                            DefaultTableModel modelo = (DefaultTableModel) gestionarFactura.tablaDetalleFactura.getModel();

                            for (int i = 0; i < cantidadFilas; i++) {

                                if (sql.buscarDetalleFacturaXid(idDetalleFactura) != null) {

                                    DetalleFactura dF = sql.buscarDetalleFacturaXid(idDetalleFactura);
                                    int idProducto = dF.getId_producto_detalle();
                                    Producto producto = sql.buscarProductoXid(idProducto);
                                    int cantidadProducto = dF.getCantidad_producto_y_unidades_detalle();
                                    String nombreProducto = producto.getNombre();
                                    float precioProducto = producto.getPrecio();
                                    float importe = dF.getCantidad_producto_y_unidades_detalle() * producto.getPrecio();
                                    int porcentajeDescuento = dF.getPorcentaje_Descuento();
                                    float importeTotal = (dF.getCantidad_producto_y_unidades_detalle() * producto.getPrecio()) - (dF.getImporte_detalle_factura() * dF.getPorcentaje_Descuento());
                                    int porcentajeIVA = dF.getPorcentaje_IVA();

                                    Object fila[] = {cantidadProducto, nombreProducto, precioProducto, importe, porcentajeDescuento, importeTotal, porcentajeIVA};
                                    modelo.addRow(fila);
                                    idDetalleFactura += 1;
                                }
                            }

                            gestionarFactura.tANotas.setText(factura.getNotasFactura());

                            gestionarFactura.cajaCodigoBarras1.setEnabled(true);
                            gestionarFactura.botonEditaroCambiar.setEnabled(true);
                            gestionarFactura.botonAnularFactura.setEnabled(true);
                            gestionarFactura.tablaDetalleFactura.setEnabled(true);
                            gestionarFactura.botonBorrar1.setEnabled(true);
                            gestionarFactura.tANotas.setEnabled(true);

                            if (factura.getEstadoFactura().equals("anulado")) {
                                gestionarFactura.cajaCodigoBarras1.setEnabled(false);
                                gestionarFactura.botonEditaroCambiar.setEnabled(false);
                                gestionarFactura.tablaDetalleFactura.setEnabled(false);
                                gestionarFactura.botonAnularFactura.setEnabled(false);
                                gestionarFactura.botonBorrar1.setEnabled(false);
                                gestionarFactura.tANotas.setEnabled(false);
                            }
                            if (!gestionarFactura.cajaCodigoBarras1.isVisible() && factura.getEstadoFactura().equals("concambio")) {
                                gestionarFactura.tANotas.setEditable(false);
                            }
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(ventanaPrincipal, "Solo puede ingresar numeros", "Solo Numeros", JOptionPane.PLAIN_MESSAGE, icon);
                        gestionarFactura.cajaConsecutivoFactura.setText("");
                    }
                } else if (gestionarFactura.cajaConsecutivoFactura.getText().equals("")) {
                    JOptionPane.showMessageDialog(ventanaPrincipal, "Debe ingresar un numero consecutivo de factura", "Ingresar consecutivo", JOptionPane.PLAIN_MESSAGE, icon);
                }
            }
        };
        gestionarFactura.botonBuscar.addActionListener(aL);
    }

    private void ActionListener_BotonAnular_VentanaGestionarFactura() {
        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (siNo == null) {
                    siNo = new EstaSeguro(ventanaPrincipal, true);
                }
                siNo.labelPregunta.setText("¿Estas seguro de anular?");
                oyenteAccion_NoEliminar();
                oyenteAccion_SiEliminar();
                windowClosing_VentanaEstaSeguro();
                siNo.setVisible(true);

                if (estarSeguro) {
                    int consecutivo = Integer.parseInt(gestionarFactura.labelConsecutivo.getText());
                    Factura factura = sql.buscarFactura(consecutivo);
                    sql.editarFactura(factura, "anulado", gestionarFactura.tANotas.getText());

                    JOptionPane.showMessageDialog(ventanaPrincipal, "Factura Anulada Correctamente", "Factura Anulada", JOptionPane.PLAIN_MESSAGE, iconOk);
                    gestionarFactura.dispose();
                    gestionarFactura = null;
                }
            }
        };
        gestionarFactura.botonAnularFactura.addActionListener(aL);
    }

    private void ActionListener_BotonCambio_VentanaGestionarFactura() {
        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (siNo == null) {
                    siNo = new EstaSeguro(ventanaPrincipal, true);
                }
                siNo.labelPregunta.setText("¿Estas seguro realizar cambio?");
                oyenteAccion_NoEliminar();
                oyenteAccion_SiEliminar();
                windowClosing_VentanaEstaSeguro();
                siNo.setVisible(true);

                if (estarSeguro) {
                    int consecutivo = Integer.parseInt(gestionarFactura.labelConsecutivo.getText());
                    Factura factura = sql.buscarFactura(consecutivo);

                    factura.setConsecutivo(factura.getConsecutivo());
                    factura.setDescuento(0);
                    factura.setIva(Float.parseFloat(gestionarFactura.cajaIvaTotal.getText()));
                    factura.setSubtotal(Float.parseFloat(gestionarFactura.cajaSubTotal.getText()));
                    factura.setTotal(Float.parseFloat(gestionarFactura.cajaTotal.getText()));

                    float sumaDescuento = 0;
                    for (int i = 0; i < gestionarFactura.tablaDetalleFactura.getModel().getRowCount(); i++) {
                        float importe = Float.parseFloat(gestionarFactura.tablaDetalleFactura.getModel().getValueAt(i, 3).toString());
                        float porcentajeDescuento = Float.parseFloat(gestionarFactura.tablaDetalleFactura.getModel().getValueAt(i, 4).toString());
                        float descProduc = (importe * porcentajeDescuento) / 100;
                        sumaDescuento += descProduc;
                    }
                    factura.setDescuento(sumaDescuento);

                    if (sql.editarFactura(factura, "concambio", gestionarFactura.tANotas.getText())) {

                        for (int i = 0; i < gestionarFactura.tablaDetalleFactura.getRowCount(); i++) {

                            int cantidadProd = Integer.parseInt(gestionarFactura.tablaDetalleFactura.getModel().getValueAt(i, 0).toString());
                            float importeProd = Float.parseFloat(gestionarFactura.tablaDetalleFactura.getModel().getValueAt(i, 5).toString());
                            int porcentajeIVA = Integer.parseInt(gestionarFactura.tablaDetalleFactura.getModel().getValueAt(i, 6).toString());
                            int porcentajeDescuento = Integer.parseInt(gestionarFactura.tablaDetalleFactura.getModel().getValueAt(i, 4).toString());

                            String nombreProd = gestionarFactura.tablaDetalleFactura.getModel().getValueAt(i, 1).toString();
                            Producto p1 = sql.buscarProductoPorNombre(nombreProd); //EL ERROR ES QUE NO ENCUENTRA EL PRODUCTO, TODO LO DEMAS LO HACE BIEN
                            Factura f1 = sql.buscarFactura(consecutivo);

                            if (sql.BuscarDetalleFactura(f1.getIdFactura(), p1.getIdProducto()) != null) {
                                DetalleFactura detalle = sql.BuscarDetalleFactura(f1.getIdFactura(), p1.getIdProducto());
                                if (detalle.getId_producto_detalle() == p1.getIdProducto()) {
                                    sql.editar_DetalleFactura(cantidadProd, importeProd, porcentajeIVA, porcentajeDescuento, p1.getIdProducto(), 1, factura.getIdFactura(), detalle.getId_detalle_factura());

                                    if (detalle.getCantidad_producto_y_unidades_detalle() < cantidadProd) {
                                        p1.setCantidadStock(p1.getCantidadStock() - (cantidadProd - detalle.getCantidad_producto_y_unidades_detalle()));
                                    } else if (detalle.getCantidad_producto_y_unidades_detalle() > cantidadProd) {
                                        p1.setCantidadStock(p1.getCantidadStock() + (detalle.getCantidad_producto_y_unidades_detalle() - cantidadProd));
                                    }
                                    sql.editarProducto(p1, p1.getIdProducto(), 0);
                                }
                            } else if (sql.BuscarDetalleFactura(f1.getIdFactura(), p1.getIdProducto()) == null) {
                                sql.ingresar_DetalleFactura(cantidadProd, importeProd, p1.getIdProducto(), 1, factura.getIdFactura(), porcentajeIVA, porcentajeDescuento);
                                p1.setCantidadStock(p1.getCantidadStock() - cantidadProd);
                                sql.editarProducto(p1, p1.getIdProducto(), 0);
                            }
                        }
                    } else {
                        System.out.println("Fallo al realizar el cambio de Factura");
                    }
                    JOptionPane.showMessageDialog(ventanaPrincipal, "Cambio realizado Correctamente\n en la factura", "Cambio realizado", JOptionPane.PLAIN_MESSAGE, iconOk);
                    gestionarFactura.dispose();
                    gestionarFactura = null;
                }
            }
        };
        gestionarFactura.botonEditaroCambiar.addActionListener(aL);
    }

    private void ActionListener_BotonCancelar_VentanaGestionarFactura() {
        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gestionarFactura.dispose();
                gestionarFactura = null;
            }
        };
        gestionarFactura.botonCancelar.addActionListener(aL);
    }

    private void ActionListener_itemEliminarDetalleFactura_MenuDesplegableMouse_TablaDetalleFactura_VentanaGestionarFactura() {
        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (gestionarFactura.tablaDetalleFactura.getSelectedRowCount() > 0) {

                    if (gestionarFactura.tablaDetalleFactura.getRowCount() > 1) {

                        if (siNo == null) {
                            siNo = new EstaSeguro(ventanaPrincipal, true);
                        }
                        siNo.labelPregunta.setText("<html>¿Eliminar de la factura?<html/>");
                        oyenteAccion_NoEliminar();
                        oyenteAccion_SiEliminar();
                        windowClosing_VentanaEstaSeguro();
                        siNo.setVisible(true);

                        if (estarSeguro) {
                            float SumatoriaDeIVATotal = 0;
                            float SumatoriaCadaImporteTotal = 0;

                            DefaultTableModel modelo = (DefaultTableModel) gestionarFactura.tablaDetalleFactura.getModel();
                            String listaNombreProducto[] = new String[gestionarFactura.tablaDetalleFactura.getSelectedRows().length];

                            int q = 0;
                            for (int i = modelo.getRowCount() - 1; i >= 0; i--) {
                                int fila[] = gestionarFactura.tablaDetalleFactura.getSelectedRows();

                                for (int j = 0; j < fila.length; j++) {
                                    if (i == fila[j]) {
                                        listaNombreProducto[q] = modelo.getValueAt(i, 1).toString();
                                        q++;

                                        String nombreProd = gestionarFactura.tablaDetalleFactura.getModel().getValueAt(i, 1).toString();
                                        int cantidad = Integer.parseInt(gestionarFactura.tablaDetalleFactura.getModel().getValueAt(i, 0).toString());
                                        Producto p1 = sql.buscarProductoPorNombre(nombreProd);
                                        p1.setCantidadStock(p1.getCantidadStock() + cantidad);
                                        sql.editarProducto(p1, p1.getIdProducto(), 0);

                                        modelo.removeRow(i);
                                    }
                                }
                            }
                            for (int j = 0; j < gestionarFactura.tablaDetalleFactura.getRowCount(); j++) {
                                float valorCadaimporte = Float.parseFloat(gestionarFactura.tablaDetalleFactura.getValueAt(j, 5).toString());
                                SumatoriaCadaImporteTotal += valorCadaimporte;
                            }

                            for (int j = 0; j < gestionarFactura.tablaDetalleFactura.getRowCount(); j++) {
                                float valorCadaimporte = Float.parseFloat(gestionarFactura.tablaDetalleFactura.getValueAt(j, 5).toString());
                                float valorCadaIVA = Float.parseFloat(gestionarFactura.tablaDetalleFactura.getValueAt(j, 6).toString());
                                float EsteIVA = (valorCadaimporte * valorCadaIVA) / 100;
                                SumatoriaDeIVATotal += EsteIVA;
                            }

                            subTotalFactura = SumatoriaCadaImporteTotal;
                            ivaTotal = SumatoriaDeIVATotal;
                            float totalFactura = subTotalFactura + ivaTotal;

                            gestionarFactura.cajaIvaTotal.setText(String.valueOf(ivaTotal));
                            gestionarFactura.cajaSubTotal.setText(String.valueOf(subTotalFactura));
                            String totalFacturaST = String.valueOf(totalFactura);
                            gestionarFactura.cajaTotal.setText(totalFacturaST);

                            Factura factura = sql.buscarFactura(Integer.parseInt(gestionarFactura.labelConsecutivo.getText()));

                            for (int i = 0; i < listaNombreProducto.length; i++) {
                                Producto producto = sql.buscarProductoPorNombre(listaNombreProducto[i]);
                                sql.eliminarDetalleFactura(factura.getIdFactura(), producto.getIdProducto());

                                for (int j = 0; j < gestionarFactura.tablaDetalleFactura.getRowCount(); j++) {
                                    float valorCadaimporte = Float.parseFloat(gestionarFactura.tablaDetalleFactura.getValueAt(j, 5).toString());
                                    SumatoriaCadaImporteTotal += valorCadaimporte;
                                }
                                for (int j = 0; j < gestionarFactura.tablaDetalleFactura.getRowCount(); j++) {
                                    float valorCadaimporte = Float.parseFloat(gestionarFactura.tablaDetalleFactura.getValueAt(j, 5).toString());
                                    float valorCadaIVA = Float.parseFloat(gestionarFactura.tablaDetalleFactura.getValueAt(j, 6).toString());
                                    float EsteIVA = (valorCadaimporte * valorCadaIVA) / 100;
                                    SumatoriaDeIVATotal += EsteIVA;
                                }

                                subTotalFactura = SumatoriaCadaImporteTotal;
                                ivaTotal = SumatoriaDeIVATotal;
                                float totalFacturaNuevo = subTotalFactura + ivaTotal;

                                gestionarFactura.cajaIvaTotal.setText(String.valueOf(ivaTotal));
                                gestionarFactura.cajaSubTotal.setText(String.valueOf(subTotalFactura));
                                String totalFacturaSTNuevo = String.valueOf(totalFacturaNuevo);
                                gestionarFactura.cajaTotal.setText(totalFacturaSTNuevo);
                            }
                            gestionarFactura.botonCancelar.setEnabled(false);
                            gestionarFactura.cajaConsecutivoFactura.setEnabled(false);
                            gestionarFactura.botonBuscar.setEnabled(false);
                            JOptionPane.showMessageDialog(ventanaPrincipal, "Producto eliminado con exito", "Producto eliminado", JOptionPane.PLAIN_MESSAGE, iconOk);

                        }
                    } else {
                        JOptionPane.showMessageDialog(ventanaPrincipal, "- No puedes eliminar todos los productos\n  para esto necesario anular la factura.\n  Solicitar con administrador.\n\n- En caso de aplicar para cambio primero agrega el Producto\n  y luego elimina el anterior", "         Por favor validar la opcion a tomar", JOptionPane.PLAIN_MESSAGE, icon);
                    }
                } else if (gestionarFactura.tablaDetalleFactura.getSelectedRowCount() == 0) {
                    JOptionPane.showMessageDialog(ventanaPrincipal, "Primero debes seleccionar la fila", "Seleccionar Fila", JOptionPane.PLAIN_MESSAGE, icon);
                }
            }
        };
        gestionarFactura.eliminarDetalleFactura.addActionListener(aL);
    }

    private void keyListener_CajaCodigoBarras1_IngresarProductoPorCodigoBarras_VentanaGestionarFactura() {

        KeyListener kL = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int cantidadActual = 0;
                float importeProducto = 0;

                String codigoBarrasADetalle = gestionarFactura.cajaCodigoBarras1.getText();
                Producto productoVenta;
                boolean validarSiProductoYaEstaEnLaTabla = false;

                if (sql.buscarProducto(codigoBarrasADetalle) != null) {
                    productoVenta = sql.buscarProducto(codigoBarrasADetalle);

                    if (productoVenta.getCantidadStock() > 0) {
                        if (productoVenta.getCantidadStock() >= Integer.parseInt(gestionarFactura.cajaCantidadDetalle1.getText())) {
                            for (int i = 0; i < gestionarFactura.tablaDetalleFactura.getRowCount(); i++) { //  CUANDO  HAY UN PRODUCTO EN LA TABLA IGUAL AL INGRESADO POR CODIGO DE BARRAS
                                String nombreProductoAValidar = (String) gestionarFactura.tablaDetalleFactura.getValueAt(i, 1);

                                if (sql.buscarProductoPorNombre(nombreProductoAValidar) != null) {
                                    Producto productoAValidar = sql.buscarProductoPorNombre(nombreProductoAValidar);

                                    if (productoVenta.getCodigoBarras().equals(productoAValidar.getCodigoBarras())) {
                                        if (productoVenta.getCantidadStock() <= 3) {
                                            JOptionPane.showMessageDialog(ventanaPrincipal, "Medicamento pronto quedará sin Stock", "Reponer stock", JOptionPane.PLAIN_MESSAGE, icon);
                                        }

                                        validarSiProductoYaEstaEnLaTabla = true;
                                        float SumatoriaDeEsteIVATotal = 0;
                                        float SumatoriaDeCadaImporteTotal = 0;

                                        String idProducto = String.valueOf(productoVenta.getIdProducto());
                                        String precio = String.valueOf(productoVenta.getPrecio());

                                        int descuentoIngresado = Integer.parseInt(gestionarFactura.cajaIngresarDescuento1.getText());
                                        gestionarFactura.tablaDetalleFactura.setValueAt(descuentoIngresado, i, 4);

                                        int ivaIngresado = Integer.parseInt(gestionarFactura.cajaIngresarIVA1.getText());
                                        gestionarFactura.tablaDetalleFactura.setValueAt(ivaIngresado, i, 6);

                                        int cantidadEnc = Integer.parseInt(gestionarFactura.tablaDetalleFactura.getValueAt(i, 0).toString());
                                        int cantidadParaAgr = Integer.parseInt(gestionarFactura.cajaCantidadDetalle1.getText());
                                        cantidadActual = cantidadEnc + cantidadParaAgr;

                                        if (cantidadActual > productoVenta.getCantidadStock()) {
                                            JOptionPane.showMessageDialog(ventanaPrincipal, "Llego al limite del stock del producto", "Cantidad excedida", JOptionPane.PLAIN_MESSAGE, icon);
                                            gestionarFactura.cajaCodigoBarras1.setText("");
                                        } else {
                                            gestionarFactura.tablaDetalleFactura.setValueAt(cantidadActual, i, 0);

                                            importeProducto = cantidadActual * Float.parseFloat(precio);
                                            gestionarFactura.tablaDetalleFactura.setValueAt(importeProducto, i, 3);

                                            //Hallo el importe total es decir importe - el descuento
                                            float valorObtenidoProcentajeDescuento = (importeProducto * descuentoIngresado) / 100;
                                            float importeTotalProductoActual = importeProducto - valorObtenidoProcentajeDescuento;
                                            gestionarFactura.tablaDetalleFactura.setValueAt(importeTotalProductoActual, i, 5);

                                            //Hallo Sumatoria de importes es decir Subtotal
                                            for (int j = 0; j < gestionarFactura.tablaDetalleFactura.getRowCount(); j++) {
                                                float valorCadaimporte = Float.parseFloat(gestionarFactura.tablaDetalleFactura.getValueAt(j, 5).toString());
                                                SumatoriaDeCadaImporteTotal += valorCadaimporte;
                                            }
                                            //Hallo sumatoria de el iva obtenido para cada producto 
                                            for (int j = 0; j < gestionarFactura.tablaDetalleFactura.getRowCount(); j++) {
                                                float valorCadaimporte = Float.parseFloat(gestionarFactura.tablaDetalleFactura.getValueAt(j, 5).toString());
                                                float valorCadaIVA = Float.parseFloat(gestionarFactura.tablaDetalleFactura.getValueAt(j, 6).toString());
                                                float EsteIVA = (valorCadaimporte * valorCadaIVA) / 100;
                                                SumatoriaDeEsteIVATotal += EsteIVA;
                                            }

                                            subTotalFactura = SumatoriaDeCadaImporteTotal;
                                            ivaTotal = SumatoriaDeEsteIVATotal;
                                            float totalFactura = subTotalFactura + ivaTotal;

                                            // Muestro los valores
                                            gestionarFactura.cajaIvaTotal.setText(String.valueOf(ivaTotal));
                                            gestionarFactura.cajaSubTotal.setText(String.valueOf(subTotalFactura));
                                            String totalFacturaST = String.valueOf(totalFactura);
                                            gestionarFactura.cajaTotal.setText(totalFacturaST);
                                            gestionarFactura.cajaCodigoBarras1.setText("");
                                            gestionarFactura.cajaCantidadDetalle1.setText("1");
                                        }
                                    }
                                }
                            }

                            if (validarSiProductoYaEstaEnLaTabla == false) {  //  CUANDO NO HAY UN PRODUCTO EN LA TABLA IGUAL AL INGRESADO POR CODIGO DE BARRAS

                                float SumatoriaDeIVATotal = 0;
                                float SumatoriaCadaImporteTotal = 0;

                                if (productoVenta.getCantidadStock() <= 3) {
                                    JOptionPane.showMessageDialog(ventanaPrincipal, "Medicamento pronto quedará sin Stock", "Reponer stock", JOptionPane.PLAIN_MESSAGE, icon);
                                }

                                //Recolecto los Datos Necesarios para ingresar en la tabla   
                                String nombreProducto = productoVenta.getNombre();
                                String idProducto = String.valueOf(productoVenta.getIdProducto());

                                float precio = productoVenta.getPrecio();
                                int cantidad = Integer.parseInt(gestionarFactura.cajaCantidadDetalle1.getText());
                                importeProducto = cantidad * precio;

                                int descuento = Integer.parseInt(gestionarFactura.cajaIngresarDescuento1.getText());
                                float valorObtenidoDeDescuento = (importeProducto * descuento) / 100;
                                float importeTotalProducto = importeProducto - valorObtenidoDeDescuento;

                                String cantidadST = String.valueOf(cantidad);
                                String precioST = String.valueOf(precio);
                                String importeST = String.valueOf(importeProducto);
                                String descuentoST = String.valueOf(descuento);
                                String importeTotalProductoST = String.valueOf(importeTotalProducto);
                                int valorIVa = Integer.parseInt(gestionarFactura.cajaIngresarIVA1.getText());
                                String valorIVAST = String.valueOf(valorIVa);

                                //Ingreso los datos a la tabla
                                String datos[] = {cantidadST, nombreProducto, precioST, importeST, descuentoST, importeTotalProductoST, valorIVAST};
                                int numCol = gestionarFactura.tablaDetalleFactura.getColumnCount();
                                Object fila[] = new Object[numCol];
                                for (int i = 0; i < numCol; i++) {
                                    fila[i] = datos[i];

                                }
                                gestionarFactura.tablaDetalleFactura.setDefaultRenderer(Object.class,
                                        new TablaRenderer());
                                modeloTablaDetalleFactura.addRow(fila);
                                gestionarFactura.tablaDetalleFactura.setModel(modeloTablaDetalleFactura);

                                //Hallo Sumatoria de importes es decir Subtotal
                                for (int j = 0; j < gestionarFactura.tablaDetalleFactura.getRowCount(); j++) {
                                    float valorCadaimporte = Float.parseFloat(gestionarFactura.tablaDetalleFactura.getValueAt(j, 5).toString());
                                    SumatoriaCadaImporteTotal += valorCadaimporte;
                                }
                                //Hallo sumatoria de el iva obtenido para cada producto 
                                for (int j = 0; j < gestionarFactura.tablaDetalleFactura.getRowCount(); j++) {
                                    float valorCadaimporte = Float.parseFloat(gestionarFactura.tablaDetalleFactura.getValueAt(j, 5).toString());
                                    float valorCadaIVA = Float.parseFloat(gestionarFactura.tablaDetalleFactura.getValueAt(j, 6).toString());
                                    float EsteIVA = (valorCadaimporte * valorCadaIVA) / 100;
                                    SumatoriaDeIVATotal += EsteIVA;
                                }

                                //Muestro los valores Totales
                                subTotalFactura = SumatoriaCadaImporteTotal;
                                ivaTotal = SumatoriaDeIVATotal;
                                float totalFactura = subTotalFactura + ivaTotal;

                                gestionarFactura.cajaIvaTotal.setText(String.valueOf(ivaTotal));
                                gestionarFactura.cajaSubTotal.setText(String.valueOf(subTotalFactura));
                                String totalFacturaST = String.valueOf(totalFactura);
                                gestionarFactura.cajaTotal.setText(totalFacturaST);
                                gestionarFactura.cajaCodigoBarras1.setText("");
                                gestionarFactura.cajaCantidadDetalle1.setText("1");
                            }
                        } else {
                            gestionarFactura.cajaCodigoBarras1.setText("");
                            JOptionPane.showMessageDialog(ventanaPrincipal, "La cantidad a comprar excede la cantidad en Stock", "Cantidad excedida", JOptionPane.PLAIN_MESSAGE, icon);
                        }
                    } else {
                        gestionarFactura.cajaCodigoBarras1.setText("");
                        JOptionPane.showMessageDialog(ventanaPrincipal, "Medicamento Agotado", "Agotado", JOptionPane.PLAIN_MESSAGE, icon);
                    }
                }
            }
        };
        gestionarFactura.cajaCodigoBarras1.addKeyListener(kL);
    }

    private void mouseListener_MenuDesplegableMouse_TablaDetalleFactura_VentanaFacturar() {
        MouseListener mL = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {

                if (e.isPopupTrigger()) {
                    Factura fac = sql.buscarFactura(Integer.parseInt(gestionarFactura.labelConsecutivo.getText()));

                    if (!fac.getEstadoFactura().equals("anulado")) {
                        // paso las coordenadas que se hizo click y el componente que tiene el evento y muestro el desplegable
                        gestionarFactura.menuDesplegableMouse.show(e.getComponent(), e.getX(), e.getY());
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        gestionarFactura.tablaDetalleFactura.addMouseListener(mL);
    }

    private void mouseListener_LabelBotonBorra_VentanaFacturar() {
        MouseListener mL = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                gestionarFactura.cajaCodigoBarras1.setText("");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        gestionarFactura.botonBorrar1.addMouseListener(mL);
    }

    private void windowListener_placeHolder__VentanaGestionarFactura_cajaConsecutivoFactura() {
        FocusListener holder = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (gestionarFactura.cajaConsecutivoFactura.getText().equals("Consecutivo factura") || gestionarFactura.cajaConsecutivoFactura.getText().equals("")) {
                    gestionarFactura.cajaConsecutivoFactura.setText("");
                    gestionarFactura.cajaConsecutivoFactura.setForeground(new Color(102, 102, 102));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (gestionarFactura != null && gestionarFactura.cajaConsecutivoFactura.getText().equals("")) {
                    gestionarFactura.cajaConsecutivoFactura.setText("Consecutivo factura");
                    gestionarFactura.cajaConsecutivoFactura.setForeground(new Color(204, 204, 204));
                }
            }
        };
        gestionarFactura.cajaConsecutivoFactura.addFocusListener(holder);
    }

    private void windowListener_placeHolder__VentanaGestionarFactura_cajaCodigoBarras() {
        FocusListener holder = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (gestionarFactura.cajaCodigoBarras1.getText().equals("Codigo de barras") || gestionarFactura.cajaCodigoBarras1.getText().equals("")) {
                    gestionarFactura.cajaCodigoBarras1.setText("");
                    gestionarFactura.cajaCodigoBarras1.setForeground(new Color(102, 102, 102));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (gestionarFactura != null && gestionarFactura.cajaCodigoBarras1.getText().equals("")) {
                    gestionarFactura.cajaCodigoBarras1.setText("Codigo de barras");
                    gestionarFactura.cajaCodigoBarras1.setForeground(new Color(204, 204, 204));
                }
            }
        };
        gestionarFactura.cajaCodigoBarras1.addFocusListener(holder);
    }

    private void windowClosing_VentanaGestionarFactura() {
        gestionarFactura.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                gestionarFactura.dispose();
                gestionarFactura = null;
            }
        });
    }

    //--------------------------------------------------------------------------------------------------------------------------------------------------//Reportes inventario
    private void oyenteAccion_VentanaPrincipal_BarMenuReportes_MenuitemInventarioMedicamentos() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == ventanaPrincipal.itemReporteInventarioMedicamentos) {

                    if (generarReporte == null) {
                        generarReporte = new GenerarReporte();
                    }
                    generarReporte.GenerarReporte("src\\Reportes\\ReporteInventarioMedicamentos.jasper");
                }
            }
        };
        ventanaPrincipal.itemReporteInventarioMedicamentos.addActionListener(aL);
    }

    //--------------------------------------------------------------------------------------------------------------------------------------------------//Reportes Devoluciones/ Cambios
    private void oyenteAccion_VentanaPrincipal_BarMenuReportes_itemReporteDevolucionesCambios() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (generarReporte == null) {
                    generarReporte = new GenerarReporte();
                }
                generarReporte.GenerarReporte("src\\Reportes\\ReporteDevolucionesCambios.jasper");
            }
        };
        ventanaPrincipal.itemReporteDevolucionesCambios.addActionListener(aL);
    }

    //--------------------------------------------------------------------------------------------------------------------------------------------------//Reportes MEdicamentos a Vencer
    private void oyenteAccion_VentanaPrincipal_BarMenuReportes_itemMedicamentosVencer() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (generarReporte == null) {
                    generarReporte = new GenerarReporte();
                }
                generarReporte.GenerarReporte("src\\Reportes\\ReporteMedicamentosVencer.jasper");
            }
        };
        ventanaPrincipal.itemReporteMedicamentosVencer.addActionListener(aL);
    }

    //--------------------------------------------------------------------------------------------------------------------------------------------------//ReportesGeneralVentas
    private void oyenteAccion_VentanaPrincipal_BarMenuReportes_itemReporteGeneralVentas() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (generalVentas == null) {
                    generalVentas = new ConsultarGeneralVentas(ventanaPrincipal, false);
                }
                ActionListener_BotonConsultar_VentanaGeneralDeVentas();
                windowClosing_VentanaGeneralDeVentas();

                generalVentas.setLocationRelativeTo(null);
                generalVentas.setVisible(true);
            }
        };
        ventanaPrincipal.itemReporteGeneralVentas.addActionListener(aL);
    }

    private void ActionListener_BotonConsultar_VentanaGeneralDeVentas() {
        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Paso la fecha al formato necesario
                DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                int idUser = 0;

                if (generalVentas.dateChooser_Inicio.getDate() != null) {

                    if (generalVentas.dateChooserFin.getDate() != null) {

                        if (generalVentas.dateChooser_Inicio.getDate().before(generalVentas.dateChooserFin.getDate())) {
                            String fechaInicioEscogida = formato.format(generalVentas.dateChooser_Inicio.getDate());
                            String fechaFinEscogida = formato.format(generalVentas.dateChooserFin.getDate());

                            if (!generalVentas.cajaCedulaEmpleado.getText().equals("")) {
                                try {
                                    int cedulaEmpleado = Integer.parseInt(generalVentas.cajaCedulaEmpleado.getText());
                                    Usuario user = sql.buscarUsuario(cedulaEmpleado);
                                    if (user == null) {
                                        JOptionPane.showMessageDialog(null, "El numero de cedula ingresado no  \nse encuentra en el sistema.", " Validar Cedula", JOptionPane.PLAIN_MESSAGE, icon);
                                    } else {
                                        idUser = user.getIdUsuario();
                                        generalVentas.dispose();
                                        generalVentas = null;
                                        sql.GeneralDeVentas("src\\Reportes\\ReporteGeneralDeVentasPorEmpleado.jasper", fechaInicioEscogida, fechaFinEscogida, idUser);
                                    }

                                } catch (NumberFormatException ex) {
                                    JOptionPane.showMessageDialog(null, "Cedula incorrecta, Solo ingresar numeros \nsin punto,  sin coma.", " Validar Cedula", JOptionPane.PLAIN_MESSAGE, icon);
                                }
                            } else if (generalVentas.cajaCedulaEmpleado.getText().equals("")) {
                                generalVentas.dispose();
                                generalVentas = null;
                                sql.GeneralDeVentas("src\\Reportes\\ReporteGeneralVentasTodos.jasper", fechaInicioEscogida, fechaFinEscogida, idUser);
                            }
                        } else if (generalVentas.dateChooser_Inicio.getDate().after(generalVentas.dateChooserFin.getDate())) {
                            JOptionPane.showMessageDialog(null, "La fecha de inicio no puede ser\nmayor que la fecha de final.", " Corregir fechas", JOptionPane.PLAIN_MESSAGE, icon);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No ha indicado la fecha de fin.", " Ingresar Fecha", JOptionPane.PLAIN_MESSAGE, icon);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No ha indicado la fecha de inicio.", " Ingresar Fecha", JOptionPane.PLAIN_MESSAGE, icon);
                }
            }
        };
        generalVentas.botonConsultar.addActionListener(aL);
    }

    private void windowClosing_VentanaGeneralDeVentas() {
        generalVentas.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                generalVentas.dispose();
                generalVentas = null;
            }
        });
    }

    //--------------------------------------------------------------------------------------------------------------------------------------------------// Ventana Principal- Seccion Ventas
    private void oyenteAccion_VentanaPrincipal_BarMenuRealizar_MenuitemVentas() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == ventanaPrincipal.itemRealizarVentas) {

                    habilitar_ModuloDeVentas();

                    //METODO PARA PASAR LAS CELDAS O CULMNAS ENTERAS A NO EDITABLE
                    modeloTablaDetalleFactura = new DefaultTableModel() {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            if (ventanaPrincipal.cajaCantidadDetalleAModificar.isVisible() && ventanaPrincipal.cajaDescuentoAModificar.isVisible() && ventanaPrincipal.caja_IvaModificar.isVisible()) {
                                if (column == 0 || column == 4 || column == 6) {
                                    //return true;
                                    return false;
                                } else {
                                    return false;
                                }
                            } else {
                                return false;
                            }
                        }
                    };
                    modeloTablaDetalleFactura.addColumn("Cant.");
                    modeloTablaDetalleFactura.addColumn("Descripción");
                    modeloTablaDetalleFactura.addColumn("Precio unit.");
                    modeloTablaDetalleFactura.addColumn("Importe");
                    modeloTablaDetalleFactura.addColumn("Desc.%");
                    modeloTablaDetalleFactura.addColumn("Total Importe");
                    modeloTablaDetalleFactura.addColumn("Iva%");
                    ventanaPrincipal.tablaDetalleFactura.setModel(modeloTablaDetalleFactura);
                    ventanaPrincipal.tablaDetalleFactura.setSelectionBackground(Color.lightGray);

                    int tamaño[] = {15, 220, 130, 130, 15, 130, 15};
                    int cantidadColumnas = ventanaPrincipal.tablaDetalleFactura.getColumnCount();

                    for (int i = 0; i < cantidadColumnas; i++) {
                        ventanaPrincipal.tablaDetalleFactura.getColumnModel().getColumn(i).setPreferredWidth(tamaño[i]);
                    }
                    ventanaPrincipal.labelImagenProducto.setVisible(true);
                    ventanaPrincipal.cajaAtiende.setText(usuario.getpNombre() + " " + usuario.getpApellido());
                    ventanaPrincipal.cajaCliente.requestFocus();
                }
            }
        };
        ventanaPrincipal.itemRealizarVentas.addActionListener(aL);
    }

    private void activarFuncionesDeLosComponentes_ModuloVentas() {

        windowListener_placeHolder__ModuloVentas_CajaCliente();
        windowListener_placeHolder__ModuloVentas_CajaCodigoBarras();
        windowListener_placeHolder__ModuloVentas_CajaCantidadDetalle();
        windowListener_placeHolder__ModuloVentas_CajaIngresarIVA();
        windowListener_placeHolder__ModuloVentas_CajaIngresarDescuento();
        ActionListener_BotonLimpiarVenta_ModuloVentas();
        ActionListener_BotonModificarVenta_ModuloVentas();
        ActionListener_BotonCancelarModificacion_SeccionModificar_moduloVentas();
        ActionListener_BotonModificarDetalle_seccionModificar_ModuloVentas();
        ActionListener_BotonEliminar_SeccionModificar_ModuloVentas();
        keyListener_ValidarCliente_DeCajaCliente_ModuloDeVentas();
        keyListener_CajaIngresarDescuento_ModeloVentas();
        keyListener_CajaCodigoBarras_IngresarProductoPorCodigoBarras_ModuloDeVentas();
        keyListener_CajaCantidadDetalle_ModuloVentas();
        keyListener_CajaIngresarIVA_ModeloVentas();
        keyListener_TableModel_ModuloVentas();
        mouseClicked_TableModel_ModuloVentas();
        MouseLsitener_BotonBorrar_ModuloVentas();
        MouseLsitener_LabelEsBotonOK_ModuloVentas();
        ActionListener_BotonActualizarVenta_SeccionModificar_ModuloVentas();
        ActionListener_BotonCerrarCaja_ModuloVentas();
        ActionListener_BotonFacturar_ModuloVentas();
    }

    private void ActionListener_BotonLimpiarVenta_ModuloVentas() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (siNo == null) {
                    siNo = new EstaSeguro(ventanaPrincipal, true);
                }
                oyenteAccion_NoEliminar();
                oyenteAccion_SiEliminar();
                windowClosing_VentanaEstaSeguro();
                siNo.setVisible(true);

                if (estarSeguro) {
                    DefaultTableModel modelo = (DefaultTableModel) ventanaPrincipal.tablaDetalleFactura.getModel();
                    for (int i = modelo.getRowCount() - 1; i >= 0; i--) {
                        modelo.removeRow(i);
                    }
                    ventanaPrincipal.labelCantidadenStock.setText("X");
                    ventanaPrincipal.cajaSubtotal.setText("0");
                    ventanaPrincipal.caja_IvaTotal.setText("0");
                    ventanaPrincipal.cajaTotal.setText("0");
                    subTotalFactura = 0;
                    ivaTotal = 0;
                    estarSeguro = false;

                    ventanaPrincipal.labelImagenProducto.setVisible(true);
                    ventanaPrincipal.panelimagenes.removeAll();
                    ventanaPrincipal.panelimagenes.repaint();
                }
            }
        };
        ventanaPrincipal.botonLimpiarVenta.addActionListener(aL);
    }

    private void ActionListener_BotonModificarVenta_ModuloVentas() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                habilitar_SeccionModificarDetalles_ModuloVentas();
                ventanaPrincipal.cajaCantidadDetalleAModificar.setEnabled(false);
                ventanaPrincipal.cajaDescuentoAModificar.setEnabled(false);
                ventanaPrincipal.caja_IvaModificar.setEnabled(false);
                ventanaPrincipal.botonModificarDetalle.setEnabled(false);
                ventanaPrincipal.botonEliminarDetalle.setEnabled(false);
                ventanaPrincipal.cajaCantidadDetalleAModificar.setText("0");
                ventanaPrincipal.cajaDescuentoAModificar.setText("0");
                ventanaPrincipal.caja_IvaModificar.setText("0");

                //Editar una celda especifica a editable o no editable
                /*for (int i = 0; i < modeloTablaDetalleFactura.getRowCount(); i++) {    //Llamo al metodo sobreescrito y le paso los argumentos para que me cambie la columna a no editable
                    modeloTablaDetalleFactura.isCellEditable(i, 0);                                  //Si paso el numero de la fila y la culumna fuera del ciclo, modifico solo esa celda
                    modeloTablaDetalleFactura.isCellEditable(i, 4);
                    modeloTablaDetalleFactura.isCellEditable(i, 6); }*/
                //---------------------- Guardo la tabla para cuando se cancele eliminar o modificar-------------
                modeloBackupModificarVentas = new DefaultTableModel() {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        if (ventanaPrincipal.cajaCantidadDetalleAModificar.isVisible() && ventanaPrincipal.cajaDescuentoAModificar.isVisible() && ventanaPrincipal.caja_IvaModificar.isVisible()) {
                            if (column == 0 || column == 4 || column == 6) {
                                //return true;
                                return false;
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    }
                };
                modeloBackupModificarVentas.addColumn("Cant.");
                modeloBackupModificarVentas.addColumn("Descripción");
                modeloBackupModificarVentas.addColumn("Precio unit.");
                modeloBackupModificarVentas.addColumn("Importe");
                modeloBackupModificarVentas.addColumn("Desc.%");
                modeloBackupModificarVentas.addColumn("Total Importe");
                modeloBackupModificarVentas.addColumn("Iva%");

                int cantidadColumnas = ventanaPrincipal.tablaDetalleFactura.getColumnCount();
                int cantidadFilas = ventanaPrincipal.tablaDetalleFactura.getRowCount();
                String[] fila = new String[cantidadColumnas];
                for (int i = 0; i < cantidadFilas; i++) {
                    for (int j = 0; j < cantidadColumnas; j++) {
                        String dato = ventanaPrincipal.tablaDetalleFactura.getValueAt(i, j).toString();
                        fila[j] = dato;
                    }
                    modeloBackupModificarVentas.addRow(fila);
                }
            }
        };
        ventanaPrincipal.botonModificarVenta.addActionListener(aL);
    }

    private void ActionListener_BotonCancelarModificacion_SeccionModificar_moduloVentas() {
        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float SumatoriaDeIVATotal = 0;
                float SumatoriaCadaImporteTotal = 0;

                modeloTablaDetalleFactura = modeloBackupModificarVentas;
                ventanaPrincipal.tablaDetalleFactura.setModel(modeloTablaDetalleFactura);

                //Hallo Sumatoria de importes es decir Subtotal
                for (int j = 0; j < ventanaPrincipal.tablaDetalleFactura.getModel().getRowCount(); j++) {
                    float valorCadaimporte = Float.parseFloat(ventanaPrincipal.tablaDetalleFactura.getModel().getValueAt(j, 5).toString());
                    SumatoriaCadaImporteTotal += valorCadaimporte;
                }
                //Hallo sumatoria de el iva obtenido para cada producto 
                for (int j = 0; j < ventanaPrincipal.tablaDetalleFactura.getModel().getRowCount(); j++) {
                    float valorCadaimporte = Float.parseFloat(ventanaPrincipal.tablaDetalleFactura.getModel().getValueAt(j, 5).toString());
                    float valorCadaIVA = Float.parseFloat(ventanaPrincipal.tablaDetalleFactura.getModel().getValueAt(j, 6).toString());
                    float EsteIVA = (valorCadaimporte * valorCadaIVA) / 100;
                    SumatoriaDeIVATotal += EsteIVA;
                }

                //Muestro los valores Totales
                subTotalFactura = SumatoriaCadaImporteTotal;
                ivaTotal = SumatoriaDeIVATotal;
                float totalFactura = subTotalFactura + ivaTotal;

                ventanaPrincipal.caja_IvaTotal.setText(String.valueOf(ivaTotal));
                ventanaPrincipal.cajaSubtotal.setText(String.valueOf(subTotalFactura));
                String totalFacturaST = String.valueOf(totalFactura);
                ventanaPrincipal.cajaTotal.setText(totalFacturaST);

                deshabilitar_SeccionModificarDetalles();
            }
        };
        ventanaPrincipal.botonCancelarMod.addActionListener(aL);
    }

    private void ActionListener_BotonModificarDetalle_seccionModificar_ModuloVentas() {
        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float precio = 0;
                Producto producto;

                int fila = ventanaPrincipal.tablaDetalleFactura.getSelectedRow();
                int ivaAnterior = Integer.parseInt(ventanaPrincipal.tablaDetalleFactura.getValueAt(fila, 6).toString());
                float importeTotalAnterior = Float.parseFloat(ventanaPrincipal.tablaDetalleFactura.getValueAt(fila, 5).toString());

                int cantidadADD = Integer.parseInt(ventanaPrincipal.cajaCantidadDetalleAModificar.getText());
                int ivaADD = Integer.parseInt(ventanaPrincipal.caja_IvaModificar.getText());
                int descuentoADD = Integer.parseInt(ventanaPrincipal.cajaDescuentoAModificar.getText());

                String nombreMedicamento = ventanaPrincipal.tablaDetalleFactura.getValueAt(fila, 1).toString();
                ventanaPrincipal.tablaDetalleFactura.setValueAt(cantidadADD, fila, 0);
                ventanaPrincipal.tablaDetalleFactura.setValueAt(descuentoADD, fila, 4);
                ventanaPrincipal.tablaDetalleFactura.setValueAt(ivaADD, fila, 6);

                producto = sql.buscarProductoPorNombre(nombreMedicamento);
                precio = producto.getPrecio(); // PRECIO DEL PRODUCTO

                float importeDelProductoActual = (cantidadADD * precio);

                float DescuentoRealizado = (descuentoADD * importeDelProductoActual) / 100;
                float importeTotalActual = importeDelProductoActual - DescuentoRealizado;
                float valorADescontarIVAActual = (ivaADD * importeTotalActual) / 100;

                ventanaPrincipal.tablaDetalleFactura.setValueAt(importeDelProductoActual, fila, 3);
                ventanaPrincipal.tablaDetalleFactura.setValueAt(importeTotalActual, fila, 5);

                float importeTotalAAgregar = importeTotalActual;
                float valorADescontarIVaAnterior = (ivaAnterior * importeTotalAnterior) / 100;

                float nuevoSubtotal = subTotalFactura - importeTotalAnterior + importeTotalAAgregar;
                float nuevoIVA = ivaTotal - valorADescontarIVaAnterior + valorADescontarIVAActual;

                float totalFactura = nuevoSubtotal + nuevoIVA;

                ventanaPrincipal.caja_IvaTotal.setText(String.valueOf(nuevoIVA));
                ventanaPrincipal.cajaSubtotal.setText(String.valueOf(nuevoSubtotal));
                String totalFacturaST = String.valueOf(totalFactura);
                ventanaPrincipal.cajaTotal.setText(totalFacturaST);

                subTotalFactura = nuevoSubtotal;
                ivaTotal = nuevoIVA;

                ventanaPrincipal.botonActualizarVenta.setEnabled(true);
            }
        };
        ventanaPrincipal.botonModificarDetalle.addActionListener(aL);
    }

    private void ActionListener_BotonEliminar_SeccionModificar_ModuloVentas() {
        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float SumatoriaDeIVATotal = 0;
                float SumatoriaCadaImporteTotal = 0;

                int fila = ventanaPrincipal.tablaDetalleFactura.getSelectedRow();
                DefaultTableModel modelo = (DefaultTableModel) ventanaPrincipal.tablaDetalleFactura.getModel();
                for (int i = 0; i < modelo.getRowCount(); i++) {
                    if (i == fila) {
                        modelo.removeRow(i);
                    }
                }

                //Hallo Sumatoria de importes es decir Subtotal
                for (int j = 0; j < ventanaPrincipal.tablaDetalleFactura.getRowCount(); j++) {
                    float valorCadaimporte = Float.parseFloat(ventanaPrincipal.tablaDetalleFactura.getValueAt(j, 5).toString());
                    SumatoriaCadaImporteTotal += valorCadaimporte;
                }
                //Hallo sumatoria de el iva obtenido para cada producto 
                for (int j = 0; j < ventanaPrincipal.tablaDetalleFactura.getRowCount(); j++) {
                    float valorCadaimporte = Float.parseFloat(ventanaPrincipal.tablaDetalleFactura.getValueAt(j, 5).toString());
                    float valorCadaIVA = Float.parseFloat(ventanaPrincipal.tablaDetalleFactura.getValueAt(j, 6).toString());
                    float EsteIVA = (valorCadaimporte * valorCadaIVA) / 100;
                    SumatoriaDeIVATotal += EsteIVA;
                }

                //Muestro los valores Totales
                subTotalFactura = SumatoriaCadaImporteTotal;
                ivaTotal = SumatoriaDeIVATotal;
                float totalFactura = subTotalFactura + ivaTotal;

                ventanaPrincipal.caja_IvaTotal.setText(String.valueOf(ivaTotal));
                ventanaPrincipal.cajaSubtotal.setText(String.valueOf(subTotalFactura));
                String totalFacturaST = String.valueOf(totalFactura);
                ventanaPrincipal.cajaTotal.setText(totalFacturaST);

                ventanaPrincipal.botonActualizarVenta.setEnabled(true);
            }
        };
        ventanaPrincipal.botonEliminarDetalle.addActionListener(aL);
    }

    private void ActionListener_BotonActualizarVenta_SeccionModificar_ModuloVentas() {

        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deshabilitar_SeccionModificarDetalles();
            }
        };
        ventanaPrincipal.botonActualizarVenta.addActionListener(aL);
    }

    private void ActionListener_BotonCerrarCaja_ModuloVentas() {
        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deshabilitar_ModuloDeVentas();
                ventanaPrincipal.cajaCliente.setText("");
                ventanaPrincipal.labelCantidadenStock.setText("X");

                String fechaH = fechaHora.format(date);
                String fecha = fechaH.substring(0, 10);
                sql.cierreDeCaja("src\\Reportes\\ReporteCierreCaja.jasper", fecha, usuario.getIdUsuario());

            }
        };
        ventanaPrincipal.botonCerrarCaja.addActionListener(aL);
    }

    private void ActionListener_BotonFacturar_ModuloVentas() {
        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (ventanaPrincipal.labelMostrarClienteEncontrado.isVisible() && ventanaPrincipal.labelConfirmaClienteEnDB.isVisible() && !ventanaPrincipal.cajaCliente.getText().equals("")) {
                    if (ventanaFacturacion == null) {
                        ventanaFacturacion = new VentanaFacturacion(ventanaPrincipal, true);
                    }
                    if (ventanaPrincipal.tablaDetalleFactura.getRowCount() > 0) {
                        ventanaFacturacion.botonGrupo = new ButtonGroup();
                        ventanaFacturacion.botonGrupo.add(ventanaFacturacion.radioEfectivo);
                        ventanaFacturacion.botonGrupo.add(ventanaFacturacion.radioTarjeta);
                        ventanaFacturacion.botonGrupo.add(ventanaFacturacion.radioOtro);

                        windowActivated_VentanaFacturacion();
                        windowListener_placeHolder__VentanaFacturacion();
                        ActionListener_BotonCancelar_VentanaFacturacion();
                        ActionListener_BotonOK_VentanaFacturacion();
                        ActionListener_BotonPasarMonto_VentanaFacturacion();
                        keyListener_CajaMontoPagadoCliente_Ventana_Facturacion();
                        cargarInformacion_Defacturacion();
                        WindowClossing_VentanaFacturacion();

                        ventanaFacturacion.setLocationRelativeTo(null);
                        ventanaFacturacion.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Para facturar debes tener\nproductos en el carrito", " No has ingresado productos", JOptionPane.PLAIN_MESSAGE, icon);
                    }
                } else {
                    JOptionPane.showMessageDialog(ventanaSesion, "Para vender es necesario ingresar un cliente primero", "Ingresar Cliente", JOptionPane.PLAIN_MESSAGE, icon);
                    ventanaPrincipal.cajaCodigoBarras.setText("");
                }
            }
        };
        ventanaPrincipal.botonFacturar.addActionListener(aL);
    }  // Pasa a Facturacion

    private void keyListener_CajaCodigoBarras_IngresarProductoPorCodigoBarras_ModuloDeVentas() {

        KeyListener kL = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int cantidadActual;
                float importeProducto;

                if (e.VK_ENTER == e.getKeyCode()) {
                    ventanaPrincipal.cajaCantidadDetalle.requestFocus();
                }

                if (!ventanaPrincipal.cajaCliente.getText().equals("") && ventanaPrincipal.labelMostrarClienteEncontrado.isVisible() && ventanaPrincipal.labelConfirmaClienteEnDB.isVisible()) {
                    //int cedulacliente = Integer.parseInt(ventanaPrincipal.cajaCliente.getText());
                    String codigoBarrasADetalle = ventanaPrincipal.cajaCodigoBarras.getText();
                    Producto productoVenta;
                    boolean validarSiProductoYaEstaEnLaTabla = false;

                    if (sql.buscarProducto(codigoBarrasADetalle) != null) {
                        productoVenta = sql.buscarProducto(codigoBarrasADetalle);

                        if (productoVenta.getCantidadStock() > 0) {
                            if (productoVenta.getCantidadStock() >= Integer.parseInt(ventanaPrincipal.cajaCantidadDetalle.getText())) {

                                for (int i = 0; i < ventanaPrincipal.tablaDetalleFactura.getRowCount(); i++) { //  CUANDO  HAY UN PRODUCTO EN LA TABLA IGUAL AL INGRESADO POR CODIGO DE BARRAS
                                    String nombreProductoAValidar = (String) ventanaPrincipal.tablaDetalleFactura.getValueAt(i, 1);

                                    if (sql.buscarProductoPorNombre(nombreProductoAValidar) != null) {
                                        Producto productoAValidar = sql.buscarProductoPorNombre(nombreProductoAValidar);

                                        if (productoVenta.getCodigoBarras().equals(productoAValidar.getCodigoBarras())) {
                                            if (productoVenta.getCantidadStock() <= 3) {
                                                JOptionPane.showMessageDialog(ventanaSesion, "Medicamento pronto quedará sin Stock", "Reponer stock", JOptionPane.PLAIN_MESSAGE, icon);
                                            }

                                            validarSiProductoYaEstaEnLaTabla = true;
                                            float SumatoriaDeEsteIVATotal = 0;
                                            float SumatoriaDeCadaImporteTotal = 0;

                                            String idProducto = String.valueOf(productoVenta.getIdProducto());
                                            String cantidadStock = String.valueOf(productoVenta.getCantidadStock());
                                            String precio = String.valueOf(productoVenta.getPrecio());
                                            ventanaPrincipal.labelCantidadenStock.setText(cantidadStock);

                                            int descuentoIngresado = Integer.parseInt(ventanaPrincipal.cajaIngresarDescuento.getText());
                                            ventanaPrincipal.tablaDetalleFactura.setValueAt(descuentoIngresado, i, 4);

                                            int ivaIngresado = Integer.parseInt(ventanaPrincipal.cajaIngresarIVA.getText());
                                            ventanaPrincipal.tablaDetalleFactura.setValueAt(ivaIngresado, i, 6);

                                            int cantidadEnc = Integer.parseInt(ventanaPrincipal.tablaDetalleFactura.getValueAt(i, 0).toString());
                                            int cantidadParaAgr = Integer.parseInt(ventanaPrincipal.cajaCantidadDetalle.getText());
                                            cantidadActual = cantidadEnc + cantidadParaAgr;

                                            if (cantidadActual > productoVenta.getCantidadStock()) {
                                                ventanaPrincipal.cajaCodigoBarras.setText("");
                                                JOptionPane.showMessageDialog(ventanaSesion, "La cantidad ingresada supera\nel limite del stock del producto", "Cantidad excedida", JOptionPane.PLAIN_MESSAGE, icon);
                                            } else {
                                                ventanaPrincipal.tablaDetalleFactura.setValueAt(cantidadActual, i, 0);

                                                importeProducto = cantidadActual * Float.parseFloat(precio);
                                                ventanaPrincipal.tablaDetalleFactura.setValueAt(importeProducto, i, 3);

                                                //Hallo el importe total es decir importe - el descuento
                                                float valorObtenidoProcentajeDescuento = (importeProducto * descuentoIngresado) / 100;
                                                float importeTotalProductoActual = importeProducto - valorObtenidoProcentajeDescuento;
                                                ventanaPrincipal.tablaDetalleFactura.setValueAt(importeTotalProductoActual, i, 5);

                                                //Hallo Sumatoria de importes es decir Subtotal
                                                for (int j = 0; j < ventanaPrincipal.tablaDetalleFactura.getRowCount(); j++) {
                                                    float valorCadaimporte = Float.parseFloat(ventanaPrincipal.tablaDetalleFactura.getValueAt(j, 5).toString());
                                                    SumatoriaDeCadaImporteTotal += valorCadaimporte;
                                                }
                                                //Hallo sumatoria de el iva obtenido para cada producto 
                                                for (int j = 0; j < ventanaPrincipal.tablaDetalleFactura.getRowCount(); j++) {
                                                    float valorCadaimporte = Float.parseFloat(ventanaPrincipal.tablaDetalleFactura.getValueAt(j, 5).toString());
                                                    float valorCadaIVA = Float.parseFloat(ventanaPrincipal.tablaDetalleFactura.getValueAt(j, 6).toString());
                                                    float EsteIVA = (valorCadaimporte * valorCadaIVA) / 100;
                                                    SumatoriaDeEsteIVATotal += EsteIVA;
                                                }

                                                subTotalFactura = SumatoriaDeCadaImporteTotal;
                                                ivaTotal = SumatoriaDeEsteIVATotal;
                                                float totalFactura = subTotalFactura + ivaTotal;

                                                // Muestro los valores
                                                ventanaPrincipal.caja_IvaTotal.setText(String.valueOf(ivaTotal));
                                                ventanaPrincipal.cajaSubtotal.setText(String.valueOf(subTotalFactura));
                                                String totalFacturaST = String.valueOf(totalFactura);
                                                ventanaPrincipal.cajaTotal.setText(totalFacturaST);
                                                ventanaPrincipal.cajaCodigoBarras.setText("");
                                                ventanaPrincipal.cajaCantidadDetalle.setText("1");

                                                ImagenProducto img;
                                                img = productoVenta.getImagen();
                                                int idImagen = img.getIdImagen();
                                                sql.ObtenerImagen(idImagen, ventanaPrincipal.panelimagenes);
                                                ventanaPrincipal.labelImagenProducto.setVisible(false);

                                                if (idImagen <= 0) {
                                                    ventanaPrincipal.labelImagenProducto.setVisible(true);
                                                    ventanaPrincipal.panelimagenes.removeAll();
                                                    ventanaPrincipal.panelimagenes.repaint();
                                                }
                                                ventanaPrincipal.panelimagenes.setVisible(true);
                                            }
                                        }
                                    }
                                }

                                if (validarSiProductoYaEstaEnLaTabla == false) {  //  CUANDO NO HAY UN PRODUCTO EN LA TABLA IGUAL AL INGRESADO POR CODIGO DE BARRAS

                                    float SumatoriaDeIVATotal = 0;
                                    float SumatoriaCadaImporteTotal = 0;

                                    if (productoVenta.getCantidadStock() <= 3) {
                                        JOptionPane.showMessageDialog(ventanaSesion, "Medicamento pronto quedará sin Stock", "Reponer stock", JOptionPane.PLAIN_MESSAGE, icon);
                                    }

                                    //Recolecto los Datos Necesarios para ingresar en la tabla   
                                    String nombreProducto = productoVenta.getNombre();
                                    String idProducto = String.valueOf(productoVenta.getIdProducto());
                                    String cantidadStock = String.valueOf(productoVenta.getCantidadStock());
                                    ventanaPrincipal.labelCantidadenStock.setText(cantidadStock);

                                    float precio = productoVenta.getPrecio();
                                    int cantidad = Integer.parseInt(ventanaPrincipal.cajaCantidadDetalle.getText());
                                    importeProducto = cantidad * precio;

                                    int descuento = Integer.parseInt(ventanaPrincipal.cajaIngresarDescuento.getText());
                                    float valorObtenidoDeDescuento = (importeProducto * descuento) / 100;
                                    float importeTotalProducto = importeProducto - valorObtenidoDeDescuento;

                                    String cantidadST = String.valueOf(cantidad);
                                    String precioST = String.valueOf(precio);
                                    String importeST = String.valueOf(importeProducto);
                                    String descuentoST = String.valueOf(descuento);
                                    String importeTotalProductoST = String.valueOf(importeTotalProducto);
                                    int valorIVa = Integer.parseInt(ventanaPrincipal.cajaIngresarIVA.getText());
                                    String valorIVAST = String.valueOf(valorIVa);

                                    //Ingreso los datos a la tabla
                                    String datos[] = {cantidadST, nombreProducto, precioST, importeST, descuentoST, importeTotalProductoST, valorIVAST};
                                    int numCol = ventanaPrincipal.tablaDetalleFactura.getColumnCount();
                                    Object fila[] = new Object[numCol];

                                    for (int i = 0; i < numCol; i++) {
                                        fila[i] = datos[i];
                                    }

                                    ventanaPrincipal.tablaDetalleFactura.setDefaultRenderer(Object.class,
                                            new TablaRenderer());
                                    modeloTablaDetalleFactura.addRow(fila);
                                    ventanaPrincipal.tablaDetalleFactura.setModel(modeloTablaDetalleFactura);

                                    //Hallo Sumatoria de importes es decir Subtotal
                                    for (int j = 0; j < ventanaPrincipal.tablaDetalleFactura.getRowCount(); j++) {
                                        float valorCadaimporte = Float.parseFloat(ventanaPrincipal.tablaDetalleFactura.getValueAt(j, 5).toString());
                                        SumatoriaCadaImporteTotal += valorCadaimporte;
                                    }
                                    //Hallo sumatoria de el iva obtenido para cada producto 
                                    for (int j = 0; j < ventanaPrincipal.tablaDetalleFactura.getRowCount(); j++) {
                                        float valorCadaimporte = Float.parseFloat(ventanaPrincipal.tablaDetalleFactura.getValueAt(j, 5).toString());
                                        float valorCadaIVA = Float.parseFloat(ventanaPrincipal.tablaDetalleFactura.getValueAt(j, 6).toString());
                                        float EsteIVA = (valorCadaimporte * valorCadaIVA) / 100;
                                        SumatoriaDeIVATotal += EsteIVA;
                                    }

                                    //Muestro los valores Totales
                                    subTotalFactura = SumatoriaCadaImporteTotal;
                                    ivaTotal = SumatoriaDeIVATotal;
                                    float totalFactura = subTotalFactura + ivaTotal;

                                    ventanaPrincipal.caja_IvaTotal.setText(String.valueOf(ivaTotal));
                                    ventanaPrincipal.cajaSubtotal.setText(String.valueOf(subTotalFactura));
                                    String totalFacturaST = String.valueOf(totalFactura);
                                    ventanaPrincipal.cajaTotal.setText(totalFacturaST);
                                    ventanaPrincipal.cajaCodigoBarras.setText("");
                                    ventanaPrincipal.cajaCantidadDetalle.setText("1");

                                    ImagenProducto img;
                                    img = productoVenta.getImagen();
                                    int idImagen = img.getIdImagen();
                                    sql.ObtenerImagen(idImagen, ventanaPrincipal.panelimagenes);
                                    ventanaPrincipal.labelImagenProducto.setVisible(false);

                                    if (idImagen <= 0) {
                                        ventanaPrincipal.labelImagenProducto.setVisible(true);
                                        ventanaPrincipal.panelimagenes.removeAll();
                                        ventanaPrincipal.panelimagenes.repaint();
                                    }
                                    ventanaPrincipal.panelimagenes.setVisible(true);
                                }
                            } else {
                                ventanaPrincipal.cajaCodigoBarras.setText("");
                                JOptionPane.showMessageDialog(ventanaSesion, "La cantidad a comprar excede la cantidad en Stock", "Cantidad excedida", JOptionPane.PLAIN_MESSAGE, icon);
                            }
                        } else {
                            JOptionPane.showMessageDialog(ventanaSesion, "Medicamento Agotado", "Agotado", JOptionPane.PLAIN_MESSAGE, icon);
                            ventanaPrincipal.cajaCodigoBarras.setText("");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(ventanaSesion, "Para vender es necesario ingresar un cliente primero", "Ingresar Cliente", JOptionPane.PLAIN_MESSAGE, icon);
                    ventanaPrincipal.cajaCodigoBarras.setText("");
                }
            }
        };
        ventanaPrincipal.cajaCodigoBarras.addKeyListener(kL);
    }

    private void keyListener_ValidarCliente_DeCajaCliente_ModuloDeVentas() {

        KeyListener kL = new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {

                if (e.VK_ENTER == e.getKeyCode()) {
                    ventanaPrincipal.cajaCodigoBarras.requestFocus();
                }

                try {
                    int cedulaDelCliente = Integer.parseInt(ventanaPrincipal.cajaCliente.getText());
                    cliente = new Cliente();
                    if (sql.buscarCliente(cedulaDelCliente) != null) {

                        cliente = sql.buscarCliente(cedulaDelCliente);
                        ventanaPrincipal.labelConfirmaClienteEnDB.setVisible(true);
                        ventanaPrincipal.labelMostrarClienteEncontrado.setVisible(true);
                        ventanaPrincipal.labelClienteAMostrar.setVisible(true);
                        ventanaPrincipal.labelMostrarClienteEncontrado.setText(cliente.getNombre() + " " + cliente.getApellido());
                        ventanaPrincipal.labelClienteNoRegistrado.setVisible(false);

                    } else if (sql.buscarCliente(cedulaDelCliente) == null) {

                        ventanaPrincipal.labelClienteNoRegistrado.setVisible(true);
                        ventanaPrincipal.labelConfirmaClienteEnDB.setVisible(false);
                        ventanaPrincipal.labelMostrarClienteEncontrado.setVisible(false);
                        ventanaPrincipal.labelClienteAMostrar.setVisible(false);
                        ventanaPrincipal.labelMostrarClienteEncontrado.setText("");
                    }
                } catch (Exception ex) {
                    if (ventanaPrincipal.cajaCliente.getText().equals("")) {
                        ventanaPrincipal.labelConfirmaClienteEnDB.setVisible(false);
                        ventanaPrincipal.labelClienteNoRegistrado.setVisible(true);
                        ventanaPrincipal.labelMostrarClienteEncontrado.setVisible(false);
                        ventanaPrincipal.labelClienteAMostrar.setVisible(false);
                        ventanaPrincipal.labelMostrarClienteEncontrado.setText("");
                    } else if (!ventanaPrincipal.cajaCliente.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Cedula incorrecta, Solo ingresar numeros \nsin punto,  sin coma.", " Validar Cedula", JOptionPane.PLAIN_MESSAGE, icon);
                        ventanaPrincipal.labelConfirmaClienteEnDB.setVisible(false);
                        ventanaPrincipal.labelClienteNoRegistrado.setVisible(true);
                        ventanaPrincipal.labelMostrarClienteEncontrado.setVisible(false);
                        ventanaPrincipal.labelClienteAMostrar.setVisible(false);
                        ventanaPrincipal.labelMostrarClienteEncontrado.setText("");
                        ventanaPrincipal.cajaCliente.setText("");
                    }
                }
            }
        };
        ventanaPrincipal.cajaCliente.addKeyListener(kL);
    }

    private void keyListener_CajaIngresarDescuento_ModeloVentas() {

        KeyListener kL = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.VK_ENTER == e.getKeyCode()) {
                    ventanaPrincipal.botonFacturar.requestFocus();
                }
            }
        };
        ventanaPrincipal.cajaIngresarDescuento.addKeyListener(kL);
    }

    private void keyListener_CajaIngresarIVA_ModeloVentas() {

        KeyListener kL = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.VK_ENTER == e.getKeyCode()) {
                    ventanaPrincipal.botonFacturar.requestFocus();
                }
            }
        };
        ventanaPrincipal.cajaIngresarIVA.addKeyListener(kL);
    }

    private void keyListener_CajaCantidadDetalle_ModuloVentas() {
        KeyListener kL = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.VK_ENTER == e.getKeyCode()) {
                    ventanaPrincipal.botonFacturar.requestFocus();
                }
            }
        };
        ventanaPrincipal.cajaCantidadDetalle.addKeyListener(kL);
    }

    private void keyListener_TableModel_ModuloVentas() {
        KeyListener kL = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                ventanaPrincipal.labelSeleccioneItem.setVisible(false);
                int fila = ventanaPrincipal.tablaDetalleFactura.getSelectedRow();

                String cantidad = ventanaPrincipal.tablaDetalleFactura.getValueAt(fila, 0).toString();
                String iva = ventanaPrincipal.tablaDetalleFactura.getValueAt(fila, 6).toString();
                String descuento = ventanaPrincipal.tablaDetalleFactura.getValueAt(fila, 4).toString();
                String nombre = ventanaPrincipal.tablaDetalleFactura.getValueAt(fila, 1).toString();
                Producto producto = sql.buscarProductoPorNombre(nombre);

                ventanaPrincipal.labelCantidadenStock.setText(String.valueOf(producto.getCantidadStock()));

                ImagenProducto img = producto.getImagen();
                int idImagen = img.getIdImagen();

                sql.ObtenerImagen(idImagen, ventanaPrincipal.panelimagenes);
                ventanaPrincipal.labelImagenProducto.setVisible(false);

                if (idImagen <= 0) {
                    ventanaPrincipal.labelImagenProducto.setVisible(true);
                    ventanaPrincipal.panelimagenes.removeAll();
                    ventanaPrincipal.panelimagenes.repaint();
                }

                ventanaPrincipal.panelimagenes.setVisible(true);

                if (ventanaPrincipal.cajaCantidadDetalleAModificar.isVisible() && ventanaPrincipal.caja_IvaModificar.isVisible() && ventanaPrincipal.cajaDescuentoAModificar.isVisible()) {

                    ventanaPrincipal.cajaCantidadDetalleAModificar.setEnabled(true);
                    ventanaPrincipal.cajaDescuentoAModificar.setEnabled(true);
                    ventanaPrincipal.caja_IvaModificar.setEnabled(true);
                    ventanaPrincipal.botonModificarDetalle.setEnabled(true);
                    ventanaPrincipal.botonEliminarDetalle.setEnabled(true);

                    ventanaPrincipal.labelNombreProductoSelecionadoTabla.setText(nombre);
                    ventanaPrincipal.cajaCantidadDetalleAModificar.setText(cantidad);
                    ventanaPrincipal.caja_IvaModificar.setText(iva);
                    ventanaPrincipal.cajaDescuentoAModificar.setText(descuento);
                }
            }
        };
        ventanaPrincipal.tablaDetalleFactura.addKeyListener(kL);
    }

    private void mouseClicked_TableModel_ModuloVentas() {
        MouseListener mL = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                ventanaPrincipal.labelSeleccioneItem.setVisible(false);
                int fila = ventanaPrincipal.tablaDetalleFactura.getSelectedRow();

                String cantidad = ventanaPrincipal.tablaDetalleFactura.getValueAt(fila, 0).toString();
                String iva = ventanaPrincipal.tablaDetalleFactura.getValueAt(fila, 6).toString();
                String descuento = ventanaPrincipal.tablaDetalleFactura.getValueAt(fila, 4).toString();
                String nombre = ventanaPrincipal.tablaDetalleFactura.getValueAt(fila, 1).toString();
                Producto producto = sql.buscarProductoPorNombre(nombre);

                ventanaPrincipal.labelCantidadenStock.setText(String.valueOf(producto.getCantidadStock()));

                ImagenProducto img = producto.getImagen();
                int idImagen = img.getIdImagen();

                sql.ObtenerImagen(idImagen, ventanaPrincipal.panelimagenes);
                ventanaPrincipal.labelImagenProducto.setVisible(false);

                if (idImagen <= 0) {
                    ventanaPrincipal.labelImagenProducto.setVisible(true);
                    ventanaPrincipal.panelimagenes.removeAll();
                    ventanaPrincipal.panelimagenes.repaint();
                }

                ventanaPrincipal.panelimagenes.setVisible(true);

                if (ventanaPrincipal.cajaCantidadDetalleAModificar.isVisible() && ventanaPrincipal.caja_IvaModificar.isVisible() && ventanaPrincipal.cajaDescuentoAModificar.isVisible()) {

                    ventanaPrincipal.cajaCantidadDetalleAModificar.setEnabled(true);
                    ventanaPrincipal.cajaDescuentoAModificar.setEnabled(true);
                    ventanaPrincipal.caja_IvaModificar.setEnabled(true);
                    ventanaPrincipal.botonModificarDetalle.setEnabled(true);
                    ventanaPrincipal.botonEliminarDetalle.setEnabled(true);

                    ventanaPrincipal.labelNombreProductoSelecionadoTabla.setText(nombre);
                    ventanaPrincipal.cajaCantidadDetalleAModificar.setText(cantidad);
                    ventanaPrincipal.caja_IvaModificar.setText(iva);
                    ventanaPrincipal.cajaDescuentoAModificar.setText(descuento);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        ventanaPrincipal.tablaDetalleFactura.addMouseListener(mL);
    }

    private void MouseLsitener_LabelEsBotonOK_ModuloVentas() {

        MouseListener mL = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int cantidadActual = 0;
                float importeProducto = 0;

                if (ventanaPrincipal.labelConfirmaClienteEnDB.isVisible() && !ventanaPrincipal.labelMostrarClienteEncontrado.getText().equals("")) {
                    int cedulacliente = Integer.parseInt(ventanaPrincipal.cajaCliente.getText());
                    String codigoBarrasADetalle = ventanaPrincipal.cajaCodigoBarras.getText();
                    Producto productoVenta;
                    boolean validarSiProductoYaEstaEnLaTabla = false;

                    if (sql.buscarProducto(codigoBarrasADetalle) != null) {
                        productoVenta = sql.buscarProducto(codigoBarrasADetalle);

                        if (productoVenta.getCantidadStock() > 0) {
                            if (productoVenta.getCantidadStock() >= Integer.parseInt(ventanaPrincipal.cajaCantidadDetalle.getText())) {
                                for (int i = 0; i < ventanaPrincipal.tablaDetalleFactura.getRowCount(); i++) { //  CUANDO  HAY UN PRODUCTO EN LA TABLA IGUAL AL INGRESADO POR CODIGO DE BARRAS
                                    String nombreProductoAValidar = (String) ventanaPrincipal.tablaDetalleFactura.getValueAt(i, 1);

                                    if (sql.buscarProductoPorNombre(nombreProductoAValidar) != null) {
                                        Producto productoAValidar = sql.buscarProductoPorNombre(nombreProductoAValidar);

                                        if (productoVenta.getCodigoBarras().equals(productoAValidar.getCodigoBarras())) {
                                            if (productoVenta.getCantidadStock() <= 3) {
                                                JOptionPane.showMessageDialog(ventanaSesion, "Medicamento pronto quedará sin Stock", "Reponer stock", JOptionPane.PLAIN_MESSAGE, icon);
                                            }

                                            validarSiProductoYaEstaEnLaTabla = true;
                                            float SumatoriaDeEsteIVATotal = 0;
                                            float SumatoriaDeCadaImporteTotal = 0;

                                            String idProducto = String.valueOf(productoVenta.getIdProducto());
                                            String cantidadStock = String.valueOf(productoVenta.getCantidadStock());
                                            String precio = String.valueOf(productoVenta.getPrecio());
                                            ventanaPrincipal.labelCantidadenStock.setText(cantidadStock);

                                            int descuentoIngresado = Integer.parseInt(ventanaPrincipal.cajaIngresarDescuento.getText());
                                            ventanaPrincipal.tablaDetalleFactura.setValueAt(descuentoIngresado, i, 4);

                                            int ivaIngresado = Integer.parseInt(ventanaPrincipal.cajaIngresarIVA.getText());
                                            ventanaPrincipal.tablaDetalleFactura.setValueAt(ivaIngresado, i, 6);

                                            int cantidadEnc = Integer.parseInt(ventanaPrincipal.tablaDetalleFactura.getValueAt(i, 0).toString());
                                            int cantidadParaAgr = Integer.parseInt(ventanaPrincipal.cajaCantidadDetalle.getText());
                                            cantidadActual = cantidadEnc + cantidadParaAgr;

                                            if (cantidadActual > productoVenta.getCantidadStock()) {
                                                ventanaPrincipal.cajaCodigoBarras.setText("");
                                                JOptionPane.showMessageDialog(ventanaSesion, "Llego al limite del stock del producto", "Cantidad excedida", JOptionPane.PLAIN_MESSAGE, icon);
                                            } else {
                                                ventanaPrincipal.tablaDetalleFactura.setValueAt(cantidadActual, i, 0);

                                                importeProducto = cantidadActual * Float.parseFloat(precio);
                                                ventanaPrincipal.tablaDetalleFactura.setValueAt(importeProducto, i, 3);

                                                //Hallo el importe total es decir importe - el descuento
                                                float valorObtenidoProcentajeDescuento = (importeProducto * descuentoIngresado) / 100;
                                                float importeTotalProductoActual = importeProducto - valorObtenidoProcentajeDescuento;
                                                ventanaPrincipal.tablaDetalleFactura.setValueAt(importeTotalProductoActual, i, 5);

                                                //Hallo Sumatoria de importes es decir Subtotal
                                                for (int j = 0; j < ventanaPrincipal.tablaDetalleFactura.getRowCount(); j++) {
                                                    float valorCadaimporte = Float.parseFloat(ventanaPrincipal.tablaDetalleFactura.getValueAt(j, 5).toString());
                                                    SumatoriaDeCadaImporteTotal += valorCadaimporte;
                                                }
                                                //Hallo sumatoria de el iva obtenido para cada producto 
                                                for (int j = 0; j < ventanaPrincipal.tablaDetalleFactura.getRowCount(); j++) {
                                                    float valorCadaimporte = Float.parseFloat(ventanaPrincipal.tablaDetalleFactura.getValueAt(j, 5).toString());
                                                    float valorCadaIVA = Float.parseFloat(ventanaPrincipal.tablaDetalleFactura.getValueAt(j, 6).toString());
                                                    float EsteIVA = (valorCadaimporte * valorCadaIVA) / 100;
                                                    SumatoriaDeEsteIVATotal += EsteIVA;
                                                }

                                                subTotalFactura = SumatoriaDeCadaImporteTotal;
                                                ivaTotal = SumatoriaDeEsteIVATotal;
                                                float totalFactura = subTotalFactura + ivaTotal;

                                                // Muestro los valores
                                                ventanaPrincipal.caja_IvaTotal.setText(String.valueOf(ivaTotal));
                                                ventanaPrincipal.cajaSubtotal.setText(String.valueOf(subTotalFactura));
                                                String totalFacturaST = String.valueOf(totalFactura);
                                                ventanaPrincipal.cajaTotal.setText(totalFacturaST);
                                                ventanaPrincipal.cajaCodigoBarras.setText("");
                                                ventanaPrincipal.cajaCantidadDetalle.setText("1");

                                                ImagenProducto img;
                                                img = productoVenta.getImagen();
                                                int idImagen = img.getIdImagen();
                                                sql.ObtenerImagen(idImagen, ventanaPrincipal.panelimagenes);
                                                ventanaPrincipal.labelImagenProducto.setVisible(false);

                                                if (idImagen <= 0) {
                                                    ventanaPrincipal.labelImagenProducto.setVisible(true);
                                                    ventanaPrincipal.panelimagenes.removeAll();
                                                    ventanaPrincipal.panelimagenes.repaint();
                                                }
                                                ventanaPrincipal.panelimagenes.setVisible(true);
                                            }
                                        }
                                    }
                                }

                                if (validarSiProductoYaEstaEnLaTabla == false) {  //  CUANDO NO HAY UN PRODUCTO EN LA TABLA IGUAL AL INGRESADO POR CODIGO DE BARRAS

                                    float SumatoriaDeIVATotal = 0;
                                    float SumatoriaCadaImporteTotal = 0;

                                    if (productoVenta.getCantidadStock() <= 3) {
                                        JOptionPane.showMessageDialog(ventanaSesion, "Medicamento pronto quedará sin Stock", "Reponer stock", JOptionPane.PLAIN_MESSAGE, icon);
                                    }

                                    //Recolecto los Datos Necesarios para ingresar en la tabla   
                                    String nombreProducto = productoVenta.getNombre();
                                    String idProducto = String.valueOf(productoVenta.getIdProducto());
                                    String cantidadStock = String.valueOf(productoVenta.getCantidadStock());
                                    ventanaPrincipal.labelCantidadenStock.setText(cantidadStock);

                                    float precio = productoVenta.getPrecio();
                                    int cantidad = Integer.parseInt(ventanaPrincipal.cajaCantidadDetalle.getText());
                                    importeProducto = cantidad * precio;

                                    int descuento = Integer.parseInt(ventanaPrincipal.cajaIngresarDescuento.getText());
                                    float valorObtenidoDeDescuento = (importeProducto * descuento) / 100;
                                    float importeTotalProducto = importeProducto - valorObtenidoDeDescuento;

                                    String cantidadST = String.valueOf(cantidad);
                                    String precioST = String.valueOf(precio);
                                    String importeST = String.valueOf(importeProducto);
                                    String descuentoST = String.valueOf(descuento);
                                    String importeTotalProductoST = String.valueOf(importeTotalProducto);
                                    int valorIVa = Integer.parseInt(ventanaPrincipal.cajaIngresarIVA.getText());
                                    String valorIVAST = String.valueOf(valorIVa);

                                    //Ingreso los datos a la tabla
                                    String datos[] = {cantidadST, nombreProducto, precioST, importeST, descuentoST, importeTotalProductoST, valorIVAST};
                                    int numCol = ventanaPrincipal.tablaDetalleFactura.getColumnCount();
                                    Object fila[] = new Object[numCol];
                                    for (int i = 0; i < numCol; i++) {
                                        fila[i] = datos[i];

                                    }
                                    ventanaPrincipal.tablaDetalleFactura.setDefaultRenderer(Object.class,
                                            new TablaRenderer());
                                    modeloTablaDetalleFactura.addRow(fila);
                                    ventanaPrincipal.tablaDetalleFactura.setModel(modeloTablaDetalleFactura);

                                    //Hallo Sumatoria de importes es decir Subtotal
                                    for (int j = 0; j < ventanaPrincipal.tablaDetalleFactura.getRowCount(); j++) {
                                        float valorCadaimporte = Float.parseFloat(ventanaPrincipal.tablaDetalleFactura.getValueAt(j, 5).toString());
                                        SumatoriaCadaImporteTotal += valorCadaimporte;
                                    }
                                    //Hallo sumatoria de el iva obtenido para cada producto 
                                    for (int j = 0; j < ventanaPrincipal.tablaDetalleFactura.getRowCount(); j++) {
                                        float valorCadaimporte = Float.parseFloat(ventanaPrincipal.tablaDetalleFactura.getValueAt(j, 5).toString());
                                        float valorCadaIVA = Float.parseFloat(ventanaPrincipal.tablaDetalleFactura.getValueAt(j, 6).toString());
                                        float EsteIVA = (valorCadaimporte * valorCadaIVA) / 100;
                                        SumatoriaDeIVATotal += EsteIVA;
                                    }

                                    //Muestro los valores Totales
                                    subTotalFactura = SumatoriaCadaImporteTotal;
                                    ivaTotal = SumatoriaDeIVATotal;
                                    float totalFactura = subTotalFactura + ivaTotal;

                                    ventanaPrincipal.caja_IvaTotal.setText(String.valueOf(ivaTotal));
                                    ventanaPrincipal.cajaSubtotal.setText(String.valueOf(subTotalFactura));
                                    String totalFacturaST = String.valueOf(totalFactura);
                                    ventanaPrincipal.cajaTotal.setText(totalFacturaST);
                                    ventanaPrincipal.cajaCodigoBarras.setText("");
                                    ventanaPrincipal.cajaCantidadDetalle.setText("1");

                                    ImagenProducto img;
                                    img = productoVenta.getImagen();
                                    int idImagen = img.getIdImagen();
                                    sql.ObtenerImagen(idImagen, ventanaPrincipal.panelimagenes);

                                    ventanaPrincipal.labelImagenProducto.setVisible(false);

                                    if (idImagen <= 0) {
                                        ventanaPrincipal.labelImagenProducto.setVisible(true);
                                        ventanaPrincipal.panelimagenes.removeAll();
                                        ventanaPrincipal.panelimagenes.repaint();
                                    }

                                    ventanaPrincipal.panelimagenes.setVisible(true);
                                }
                            } else {
                                ventanaPrincipal.cajaCodigoBarras.setText("");
                                JOptionPane.showMessageDialog(ventanaSesion, "La cantidad a comprar excede la cantidad en Stock", "Cantidad excedida", JOptionPane.PLAIN_MESSAGE, icon);
                            }
                        } else {
                            JOptionPane.showMessageDialog(ventanaSesion, "Medicamento Agotado", "Agotado", JOptionPane.PLAIN_MESSAGE, icon);
                            ventanaPrincipal.cajaCodigoBarras.setText("");
                        }
                    } else {
                        JOptionPane.showMessageDialog(ventanaSesion, "Codigo de barras no existe\nen la base de datos ", "Producto no existe", JOptionPane.PLAIN_MESSAGE, icon);
                        ventanaPrincipal.cajaCodigoBarras.setText("");
                    }

                } else if (!ventanaPrincipal.labelConfirmaClienteEnDB.isVisible() && ventanaPrincipal.labelMostrarClienteEncontrado.getText().equals("")) {

                    JOptionPane.showMessageDialog(ventanaSesion, "Para vender es necesario ingresar un cliente primero", "Ingresar Cliente", JOptionPane.PLAIN_MESSAGE, icon);
                    ventanaPrincipal.cajaCodigoBarras.setText("");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        ventanaPrincipal.botonOk.addMouseListener(mL);
    }

    private void MouseLsitener_BotonBorrar_ModuloVentas() {

        MouseListener mL = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ventanaPrincipal.cajaCodigoBarras.setText("");
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        ventanaPrincipal.botonBorrar.addMouseListener(mL);
    }

    private void MouseListener_LabelClienteNoRegistradoEnDB_ModuloDeVentas() {

        MouseListener aL = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ((e.getModifiers() & 16) != 0) {
                    if (gestionarCliente == null) {
                        gestionarCliente = new GestionarCliente(ventanaPrincipal, false);
                    }
                    formWindowActivated_Clientes();
                    oyenteAccion_VentanaGestionarClientes_BotonCrearCliente();
                    oyenteAccion_VentanaGestionarClientes_BotonBuscarCliente();
                    oyenteAccion_VentanaGestionarClientes_BotonEditarClientes();
                    oyenteAccion_VentanaGestionarClientes_BotonEliminarClientes();
                    oyenteAccion_VentanaGestionarClientes_BotonCancelar();
                    ventanaGestionarCliente_WindowClossing();

                    gestionarCliente.botonEditar.setEnabled(false);
                    gestionarCliente.botonEliminar.setEnabled(false);

                    gestionarCliente.setLocationRelativeTo(null);
                    gestionarCliente.setVisible(true);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        ventanaPrincipal.labelClienteNoRegistrado.addMouseListener(aL);
    }

    private void windowListener_placeHolder__ModuloVentas_CajaCliente() {
        FocusListener holder = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (ventanaPrincipal.cajaCliente.getText().equals("Cedula Cliente") || ventanaPrincipal.cajaCliente.getText().equals("")) {
                    ventanaPrincipal.cajaCliente.setText("");
                    ventanaPrincipal.cajaCliente.setForeground(new Color(102, 102, 102));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (ventanaPrincipal.cajaCliente.getText().equals("")) {
                    ventanaPrincipal.cajaCliente.setText("Cedula Cliente");
                    ventanaPrincipal.cajaCliente.setForeground(new Color(204, 204, 204));
                }
            }
        };
        ventanaPrincipal.cajaCliente.addFocusListener(holder);
    }

    private void windowListener_placeHolder__ModuloVentas_CajaCantidadDetalle() {
        FocusListener holder = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (ventanaPrincipal.cajaCantidadDetalle.getText().equals("1") || ventanaPrincipal.cajaCantidadDetalle.getText().equals("")) {
                    ventanaPrincipal.cajaCantidadDetalle.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (ventanaPrincipal.cajaCantidadDetalle.getText().equals("")) {
                    ventanaPrincipal.cajaCantidadDetalle.setText("1");
                }
            }
        };
        ventanaPrincipal.cajaCantidadDetalle.addFocusListener(holder);
    }

    private void windowListener_placeHolder__ModuloVentas_CajaIngresarIVA() {
        FocusListener holder = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (ventanaPrincipal.cajaIngresarIVA.getText().equals("4") || ventanaPrincipal.cajaIngresarIVA.getText().equals("")) {
                    ventanaPrincipal.cajaIngresarIVA.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (ventanaPrincipal.cajaIngresarIVA.getText().equals("")) {
                    ventanaPrincipal.cajaIngresarIVA.setText("4");
                }
            }
        };
        ventanaPrincipal.cajaIngresarIVA.addFocusListener(holder);
    }

    private void windowListener_placeHolder__ModuloVentas_CajaIngresarDescuento() {
        FocusListener holder = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (ventanaPrincipal.cajaIngresarDescuento.getText().equals("0") || ventanaPrincipal.cajaIngresarDescuento.getText().equals("")) {
                    ventanaPrincipal.cajaIngresarDescuento.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (ventanaPrincipal.cajaIngresarDescuento.getText().equals("")) {
                    ventanaPrincipal.cajaIngresarDescuento.setText("0");
                }
            }
        };
        ventanaPrincipal.cajaIngresarDescuento.addFocusListener(holder);
    }

    private void windowListener_placeHolder__ModuloVentas_CajaCodigoBarras() {
        FocusListener holder = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (ventanaPrincipal.cajaCodigoBarras.getText().equals("Codigo de barras") || ventanaPrincipal.cajaCodigoBarras.getText().equals("")) {
                    ventanaPrincipal.cajaCodigoBarras.setText("");
                    ventanaPrincipal.cajaCodigoBarras.setForeground(new Color(102, 102, 102));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (ventanaPrincipal.cajaCodigoBarras.getText().equals("")) {
                    ventanaPrincipal.cajaCodigoBarras.setText("Codigo de barras");
                    ventanaPrincipal.cajaCodigoBarras.setForeground(new Color(204, 204, 204));
                }
            }
        };
        ventanaPrincipal.cajaCodigoBarras.addFocusListener(holder);
    }

    private void deshabilitar_ModuloDeVentas() {
        //___________Visibilidad de ventas en la ventana principal________
        ventanaPrincipal.labelImagenProducto.setVisible(false);
        ventanaPrincipal.labelStock.setVisible(false);
        ventanaPrincipal.labelCantidadenStock.setVisible(false);
        ventanaPrincipal.botonLimpiarVenta.setVisible(false);
        ventanaPrincipal.botonModificarVenta.setVisible(false);
        ventanaPrincipal.botonCerrarCaja.setVisible(false);
        ventanaPrincipal.botonFacturar.setVisible(false);
        ventanaPrincipal.labelBloqueBotonesParaDetalle.setVisible(false);
        ventanaPrincipal.labelimagenCodigobarras.setVisible(false);
        ventanaPrincipal.cajaCodigoBarras.setVisible(false);
        ventanaPrincipal.labelCantidad.setVisible(false);
        ventanaPrincipal.cajaCantidadDetalle.setVisible(false);
        ventanaPrincipal.botonBorrar.setVisible(false);
        ventanaPrincipal.botonOk.setVisible(false);
        ventanaPrincipal.labelBloqueIngresar.setVisible(false);
        ventanaPrincipal.labelTotal.setVisible(false);
        ventanaPrincipal.cajaTotal.setVisible(false);
        ventanaPrincipal.labelAtiende.setVisible(false);
        ventanaPrincipal.cajaAtiende.setVisible(false);
        ventanaPrincipal.labelCliente.setVisible(false);
        ventanaPrincipal.cajaCliente.setVisible(false);
        ventanaPrincipal.labelConfirmaClienteEnDB.setVisible(false);
        ventanaPrincipal.labelClienteNoRegistrado.setVisible(false);
        ventanaPrincipal.labelClienteAMostrar.setVisible(false);
        ventanaPrincipal.labelBloqueTabla.setVisible(false);
        ventanaPrincipal.jScrollPane1.setVisible(false);
        ventanaPrincipal.tablaDetalleFactura.setVisible(false);
        ventanaPrincipal.labelMostrarClienteEncontrado.setVisible(false);
        ventanaPrincipal.labelDescuento.setVisible(false);
        ventanaPrincipal.cajaIngresarDescuento.setVisible(false);
        ventanaPrincipal.labelingresarIVA.setVisible(false);
        ventanaPrincipal.cajaIngresarIVA.setVisible(false);
        ventanaPrincipal.labelSubtotal.setVisible(false);
        ventanaPrincipal.labelSigno1.setVisible(false);
        ventanaPrincipal.cajaSubtotal.setVisible(false);
        ventanaPrincipal.labelSigno2.setVisible(false);
        ventanaPrincipal.labelTotal.setVisible(false);
        ventanaPrincipal.cajaTotal.setVisible(false);
        ventanaPrincipal.labelSigno3.setVisible(false);
        ventanaPrincipal.labelSigno4.setVisible(false);
        ventanaPrincipal.label_IvaTotal.setVisible(false);
        ventanaPrincipal.caja_IvaTotal.setVisible(false);
        ventanaPrincipal.labelSigno5.setVisible(false);
        ventanaPrincipal.labelBloqueModificar.setVisible(false);
        ventanaPrincipal.labelCantidadAModificar.setVisible(false);
        ventanaPrincipal.cajaCantidadDetalleAModificar.setVisible(false);
        ventanaPrincipal.label_IvaModificar.setVisible(false);
        ventanaPrincipal.caja_IvaModificar.setVisible(false);
        ventanaPrincipal.cajaDescuentoAModificar.setVisible(false);
        ventanaPrincipal.labelDescuentoAModificar.setVisible(false);
        ventanaPrincipal.labelSigno6.setVisible(false);
        ventanaPrincipal.labelSigno7.setVisible(false);
        ventanaPrincipal.botonModificarDetalle.setVisible(false);
        ventanaPrincipal.botonEliminarDetalle.setVisible(false);
        ventanaPrincipal.botonCancelarMod.setVisible(false);
        ventanaPrincipal.labelproductoSelecionadoTabla.setVisible(false);
        ventanaPrincipal.labelNombreProductoSelecionadoTabla.setVisible(false);
        ventanaPrincipal.labelSeleccioneItem.setVisible(false);
        ventanaPrincipal.botonActualizarVenta.setVisible(false);
        ventanaPrincipal.panelimagenes.setVisible(false);
    }

    private void habilitar_ModuloDeVentas() {
        //___________Visibilidad Modulo de ventas en la ventana principal________
        ventanaPrincipal.labelImagenProducto.setVisible(true);
        ventanaPrincipal.labelStock.setVisible(true);
        ventanaPrincipal.labelCantidadenStock.setVisible(true);
        ventanaPrincipal.botonLimpiarVenta.setVisible(true);
        ventanaPrincipal.botonModificarVenta.setVisible(true);
        ventanaPrincipal.botonCerrarCaja.setVisible(true);
        ventanaPrincipal.botonFacturar.setVisible(true);
        ventanaPrincipal.labelBloqueBotonesParaDetalle.setVisible(true);
        ventanaPrincipal.labelimagenCodigobarras.setVisible(true);
        ventanaPrincipal.cajaCodigoBarras.setVisible(true);
        ventanaPrincipal.labelCantidad.setVisible(true);
        ventanaPrincipal.cajaCantidadDetalle.setVisible(true);
        ventanaPrincipal.botonBorrar.setVisible(true);
        ventanaPrincipal.botonOk.setVisible(true);
        ventanaPrincipal.labelBloqueIngresar.setVisible(true);
        ventanaPrincipal.labelTotal.setVisible(true);
        ventanaPrincipal.labelAtiende.setVisible(true);
        ventanaPrincipal.cajaAtiende.setVisible(true);
        ventanaPrincipal.labelCliente.setVisible(true);
        ventanaPrincipal.cajaCliente.setVisible(true);
        ventanaPrincipal.labelBloqueTabla.setVisible(true);
        ventanaPrincipal.jScrollPane1.setVisible(true);
        ventanaPrincipal.tablaDetalleFactura.setVisible(true);
        ventanaPrincipal.labelDescuento.setVisible(true);
        ventanaPrincipal.cajaIngresarDescuento.setVisible(true);
        ventanaPrincipal.labelingresarIVA.setVisible(true);
        ventanaPrincipal.cajaIngresarIVA.setVisible(true);
        ventanaPrincipal.labelSubtotal.setVisible(true);
        ventanaPrincipal.cajaSubtotal.setVisible(true);
        ventanaPrincipal.cajaSubtotal.setEditable(false);
        ventanaPrincipal.labelTotal.setVisible(true);
        ventanaPrincipal.cajaTotal.setVisible(true);
        ventanaPrincipal.cajaTotal.setEditable(false);
        ventanaPrincipal.labelSigno1.setVisible(true);
        ventanaPrincipal.labelSigno2.setVisible(true);
        ventanaPrincipal.labelSigno1.setEditable(false);
        ventanaPrincipal.labelSigno2.setEditable(false);
        ventanaPrincipal.labelSigno3.setVisible(true);
        ventanaPrincipal.labelSigno3.setEditable(false);
        ventanaPrincipal.labelSigno4.setVisible(true);
        ventanaPrincipal.labelSigno4.setEditable(false);
        ventanaPrincipal.label_IvaTotal.setVisible(true);
        ventanaPrincipal.caja_IvaTotal.setVisible(true);
        ventanaPrincipal.caja_IvaTotal.setEditable(false);
        ventanaPrincipal.labelSigno5.setVisible(true);
        ventanaPrincipal.labelSigno5.setEditable(false);
        ventanaPrincipal.cajaSubtotal.setText("0");
        ventanaPrincipal.caja_IvaTotal.setText("0");
        ventanaPrincipal.cajaTotal.setText("0");
    }

    private void deshabilitar_SeccionModificarDetalles() {

        ventanaPrincipal.labelCantidadAModificar.setVisible(false);
        ventanaPrincipal.cajaCantidadDetalleAModificar.setVisible(false);
        ventanaPrincipal.label_IvaModificar.setVisible(false);
        ventanaPrincipal.caja_IvaModificar.setVisible(false);
        ventanaPrincipal.cajaDescuentoAModificar.setVisible(false);
        ventanaPrincipal.labelDescuentoAModificar.setVisible(false);
        ventanaPrincipal.labelSigno6.setVisible(false);
        ventanaPrincipal.labelSigno6.setEditable(false);
        ventanaPrincipal.labelSigno7.setVisible(false);
        ventanaPrincipal.labelSigno7.setEditable(false);
        ventanaPrincipal.labelSubtotal.setVisible(true);
        ventanaPrincipal.cajaSubtotal.setVisible(true);
        ventanaPrincipal.label_IvaTotal.setVisible(true);
        ventanaPrincipal.caja_IvaTotal.setVisible(true);
        ventanaPrincipal.labelTotal.setVisible(true);
        ventanaPrincipal.cajaTotal.setVisible(true);
        ventanaPrincipal.labelSigno1.setVisible(true);
        ventanaPrincipal.labelSigno2.setVisible(true);
        ventanaPrincipal.labelSigno5.setVisible(true);
        ventanaPrincipal.botonModificarDetalle.setVisible(false);
        ventanaPrincipal.botonEliminarDetalle.setVisible(false);
        ventanaPrincipal.botonCancelarMod.setVisible(false);
        ventanaPrincipal.labelBloqueModificar.setVisible(false);
        ventanaPrincipal.labelproductoSelecionadoTabla.setVisible(false);
        ventanaPrincipal.labelNombreProductoSelecionadoTabla.setVisible(false);
        ventanaPrincipal.botonModificarVenta.setEnabled(true);
        ventanaPrincipal.botonLimpiarVenta.setEnabled(true);
        ventanaPrincipal.botonCerrarCaja.setEnabled(true);
        ventanaPrincipal.botonFacturar.setEnabled(true);
        ventanaPrincipal.labelSeleccioneItem.setVisible(false);
        ventanaPrincipal.botonActualizarVenta.setVisible(false);
    }

    private void habilitar_SeccionModificarDetalles_ModuloVentas() {

        ventanaPrincipal.labelCantidadAModificar.setVisible(true);
        ventanaPrincipal.cajaCantidadDetalleAModificar.setVisible(true);
        ventanaPrincipal.label_IvaModificar.setVisible(true);
        ventanaPrincipal.caja_IvaModificar.setVisible(true);
        ventanaPrincipal.cajaDescuentoAModificar.setVisible(true);
        ventanaPrincipal.labelDescuentoAModificar.setVisible(true);
        ventanaPrincipal.labelSigno6.setVisible(true);
        ventanaPrincipal.labelSigno6.setEditable(false);
        ventanaPrincipal.labelSigno7.setVisible(true);
        ventanaPrincipal.labelSigno7.setEditable(false);
        ventanaPrincipal.labelSubtotal.setVisible(false);
        ventanaPrincipal.cajaSubtotal.setVisible(false);
        ventanaPrincipal.label_IvaTotal.setVisible(false);
        ventanaPrincipal.caja_IvaTotal.setVisible(false);
        ventanaPrincipal.labelTotal.setVisible(false);
        ventanaPrincipal.cajaTotal.setVisible(false);
        ventanaPrincipal.labelSigno1.setVisible(false);
        ventanaPrincipal.labelSigno2.setVisible(false);
        ventanaPrincipal.labelSigno5.setVisible(false);
        ventanaPrincipal.botonModificarDetalle.setVisible(true);
        ventanaPrincipal.botonEliminarDetalle.setVisible(true);
        ventanaPrincipal.botonCancelarMod.setVisible(true);
        ventanaPrincipal.labelBloqueModificar.setVisible(true);
        ventanaPrincipal.labelproductoSelecionadoTabla.setVisible(true);
        ventanaPrincipal.labelNombreProductoSelecionadoTabla.setVisible(true);
        ventanaPrincipal.botonModificarVenta.setEnabled(false);
        ventanaPrincipal.botonLimpiarVenta.setEnabled(false);
        ventanaPrincipal.botonCerrarCaja.setEnabled(false);
        ventanaPrincipal.botonFacturar.setEnabled(false);
        ventanaPrincipal.labelNombreProductoSelecionadoTabla.setText("Ninguno");
        ventanaPrincipal.labelSeleccioneItem.setVisible(true);
        ventanaPrincipal.botonActualizarVenta.setVisible(true);
        ventanaPrincipal.botonActualizarVenta.setEnabled(false);
    }

    private void limpiarCajas_ModuloVentas() {
        ventanaPrincipal.cajaCodigoBarras.setText(null);
        ventanaPrincipal.labelCantidadenStock.setText("X");
        ventanaPrincipal.cajaSubtotal.setText("0");
        ventanaPrincipal.caja_IvaTotal.setText("0");
        ventanaPrincipal.cajaTotal.setText("0");
        ventanaPrincipal.cajaCliente.setText("Cedula Cliente");
        ventanaPrincipal.panelimagenes.setVisible(false);

        limpiarJTable_ModuloVentas();
    }

    private void limpiarJTable_ModuloVentas() {
        modeloTablaDetalleFactura = new DefaultTableModel();
        ventanaPrincipal.tablaDetalleFactura.setModel(modeloTablaDetalleFactura);
    }

    //-------------------------------------------------------------------------------------------------------------// Ventana Facturacion
    private void windowActivated_VentanaFacturacion() {

        ventanaFacturacion.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent E) {
                ventanaFacturacion.cajaMontoPagadoCliente.requestFocus();
            }
        });

    }

    private void windowListener_placeHolder__VentanaFacturacion() {
        FocusListener holder = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (ventanaFacturacion.cajaMontoPagadoCliente.getText().equals("0") || ventanaFacturacion.cajaMontoPagadoCliente.getText().equals("")) {
                    ventanaFacturacion.cajaMontoPagadoCliente.setText("");
                }
//                if (!ventanaFacturacion.cajaMontoPagadoCliente.getText().equals("")) {
//                    try {
//                        if (ventanaFacturacion.cajaMontoPagadoCliente.getValue() != null) {
//                            if (((Number) ventanaFacturacion.cajaMontoPagadoCliente.getValue()).doubleValue() > 0) {
//                                String montoPagado = ventanaFacturacion.cajaMontoPagadoCliente.getText();
//                                String sinComas = montoPagado.replace(",", "");
//                                String sinPuntoSinComas = sinComas.replace(".", "");
//                                ventanaFacturacion.cajaMontoPagadoCliente.setText(sinPuntoSinComas);
//                            }
//                        }
//                    } catch (NumberFormatException n) {
//                        JOptionPane.showMessageDialog(null, "Para Decimal usar punto(.)");
//                        String ingresado = ventanaFacturacion.cajaMontoPagadoCliente.getText();
//                        ventanaFacturacion.cajaMontoPagadoCliente.setText(ingresado.substring(0, ingresado.length() - 1));
//                    }
//                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (ventanaFacturacion != null && ventanaFacturacion.cajaMontoPagadoCliente.getText().equals("")) {
                    ventanaFacturacion.cajaMontoPagadoCliente.setText("0");
                }
            }
        };
        ventanaFacturacion.cajaMontoPagadoCliente.addFocusListener(holder);
    }

    private void ActionListener_BotonCancelar_VentanaFacturacion() {
        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaFacturacion.dispose();
                ventanaFacturacion = null;
            }
        };
        ventanaFacturacion.botonCancelar.addActionListener(aL);
    }

    private void ActionListener_BotonOK_VentanaFacturacion() {
        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String formaPago = "";
                String otroMedio = "";
                float sumaDescuento = 0;
                int idClienteFacturar;
                String montoPagado = ventanaFacturacion.cajaMontoPagadoCliente.getText();
                String SinPuntoMiles = montoPagado.replace(".", "");
                String SinPuntoMilesConDecimal = SinPuntoMiles.replace(",", ".");
                float numeroFlotante = Float.valueOf(SinPuntoMilesConDecimal);

                float total = Float.parseFloat(ventanaPrincipal.cajaTotal.getText());
                float diferencia = numeroFlotante - total;

                if (ventanaFacturacion.radioEfectivo.isSelected() == false && ventanaFacturacion.radioTarjeta.isSelected() == false && ventanaFacturacion.radioOtro.isSelected() == false) {
                    JOptionPane.showMessageDialog(null, "Usted no eligió una forma de pago");
                } else if (numeroFlotante < total) {
                    JOptionPane.showMessageDialog(null, "Usted no colocó un valor correcto\nen el monto pagado");

                } else if (numeroFlotante >= total && ventanaFacturacion.botonGrupo.getSelection().isSelected()) {
                    if (ventanaFacturacion.radioEfectivo.isSelected()) {
                        formaPago = "efectivo";
                    } else if (ventanaFacturacion.radioTarjeta.isSelected()) {
                        formaPago = "tarjeta";
                    } else if (ventanaFacturacion.radioOtro.isSelected()) {
                        do {
                            otroMedio = JOptionPane.showInputDialog("¿Cual fue el otro medio de pago usado?");
                            if (otroMedio == null) {
                                otroMedio = "ningun metodo seleccionado";
                            }
                        } while (otroMedio.equals(""));
                        formaPago = otroMedio;
                    }
                    if (formaPago.equals("ningun metodo seleccionado")) {
                        //ventanaFacturacion.botonGrupo.remove(ventanaFacturacion.radioEfectivo);
                        //ventanaFacturacion.botonGrupo.remove(ventanaFacturacion.radioTarjeta);
                        //ventanaFacturacion.botonGrupo.remove(ventanaFacturacion.radioOtro);
                        //ventanaFacturacion.radioEfectivo.setSelected(false);
                        //ventanaFacturacion.radioTarjeta.setSelected(false);
                        //ventanaFacturacion.radioOtro.setSelected(false);
                        //ventanaFacturacion.botonGrupo.add(ventanaFacturacion.radioEfectivo);
                        //ventanaFacturacion.botonGrupo.add(ventanaFacturacion.radioTarjeta);
                        //ventanaFacturacion.botonGrupo.add(ventanaFacturacion.radioOtro);
                        ventanaFacturacion.botonGrupo.clearSelection();// HACE TODO LO ANTERIOR ¬¬
                    } else {
                        Factura factura = new Factura();
                        String nF = ventanaFacturacion.numeroFactura.getText();
                        int q = 0;
                        boolean terminado = false;
                        while (terminado == false) {
                            String caracter = String.valueOf(nF.charAt(q));
                            if (!caracter.equals("0")) {
                                terminado = true;
                            }
                            q++;
                        }
                        q--;
                        String numeroFacSinCeroIzq = nF.substring(q, nF.length());
                        factura.setConsecutivo(numeroFacSinCeroIzq);

                        factura.setFormaPago(formaPago);
                        factura.setFechaEmision(fechaHora.format(date));
                        factura.setIva(Float.parseFloat(ventanaFacturacion.valorMasIVA.getText()));
                        factura.setSubtotal(Float.parseFloat(ventanaFacturacion.valorSubTotal.getText()));
                        factura.setTotal(Float.parseFloat(ventanaPrincipal.cajaTotal.getText()));

                        for (int i = 0; i < ventanaPrincipal.tablaDetalleFactura.getModel().getRowCount(); i++) {
                            float importe = Float.parseFloat(ventanaPrincipal.tablaDetalleFactura.getModel().getValueAt(i, 3).toString());
                            float porcentajeDescuento = Float.parseFloat(ventanaPrincipal.tablaDetalleFactura.getModel().getValueAt(i, 4).toString());
                            float descProduc = (importe * porcentajeDescuento) / 100;
                            sumaDescuento += descProduc;
                        }

                        factura.setDescuento(sumaDescuento);

                        int cedulaDelCliente = Integer.parseInt(ventanaPrincipal.cajaCliente.getText());
                        cliente = new Cliente();
                        cliente = sql.buscarCliente(cedulaDelCliente);
                        idClienteFacturar = cliente.getIdCliente();
                        factura.setIdCliente(idClienteFacturar);

                        int idU = usuario.getIdUsuario();
                        factura.setIdUsuario(idU);

                        if (sql.ingresarFactura(factura)) {
                            for (int i = 0; i < ventanaPrincipal.tablaDetalleFactura.getRowCount(); i++) {
                                int cantidadProd = Integer.parseInt(ventanaPrincipal.tablaDetalleFactura.getModel().getValueAt(i, 0).toString());
                                float importeProd = Float.parseFloat(ventanaPrincipal.tablaDetalleFactura.getModel().getValueAt(i, 5).toString());
                                int porcentajeIVA = Integer.parseInt(ventanaPrincipal.tablaDetalleFactura.getModel().getValueAt(i, 6).toString());
                                int porcentajeDescuento = Integer.parseInt(ventanaPrincipal.tablaDetalleFactura.getModel().getValueAt(i, 4).toString());
                                String nombreProd = ventanaPrincipal.tablaDetalleFactura.getModel().getValueAt(i, 1).toString();

                                Producto p1 = sql.buscarProductoPorNombre(nombreProd);
                                Factura f1 = sql.buscarFactura(Integer.parseInt(factura.getConsecutivo()));
                                sql.ingresar_DetalleFactura(cantidadProd, importeProd, p1.getIdProducto(), 1, f1.getIdFactura(), porcentajeIVA, porcentajeDescuento);
                                p1.setCantidadStock(p1.getCantidadStock() - cantidadProd);
                                sql.editarProducto(p1, p1.getIdProducto(), 0);
                            }
                            JOptionPane.showMessageDialog(null, "Factura ingresada");
                        } else {
                            System.out.println("Error al registar la factura ");
                        }
                        if (vFactura == null) {
                            vFactura = new VentanaFactura(ventanaPrincipal, true);
                        }

                        vFactura.TPFactura.setEditable(false);
                        WindowClossing_VentanaFactura();
                        MostrarTodaInfoFactura();
                        // IMPRIMIR ---------------------------------------------------------------------------------------------------------------------                       
                        try {
                            PrinterJob pj = PrinterJob.getPrinterJob();
                            pj.setPrintable(vFactura);
                            boolean dec = pj.printDialog();// para conocer la seleccion en el cuadro de dialogo
                            if (dec) {                                  //Si la seleccion es verdadera entonces se imprime
                                pj.print();
                            }
                        } catch (HeadlessException | PrinterException ex) {
                            System.err.println("Error al imprimir : " + ex);
                        }
                        // ----------------------------------------------------------------------------------------------------------------------------------------
                        vFactura.setLocationRelativeTo(null);
                        vFactura.setVisible(true);

                        ventanaFacturacion.dispose();
                        ventanaFacturacion = null;
                        LimpiarVentaLuegoDeFacturar();
                    }
                }
            }
        };
        ventanaFacturacion.botonOk.addActionListener(aL);
    } // Ingreso de factura y detalle a db - imprimir - ventana desprendible factura//

    private void ActionListener_BotonPasarMonto_VentanaFacturacion() {
        ActionListener aL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ventanaFacturacion.cajaMontoPagadoCliente.setText(ventanaFacturacion.valorTotal.getText());

                try {
                    String patron2 = "###, ###. ##";
                    DecimalFormat formatoVuelto = new DecimalFormat(patron2);

                    String montoPagado = ventanaFacturacion.cajaMontoPagadoCliente.getText();
                    String SinPuntoMiles = montoPagado.replace(".", "");
                    String SinPuntoMilesConDecimal = SinPuntoMiles.replace(",", ".");

                    float numeroFlotante = Float.valueOf(SinPuntoMilesConDecimal);
                    float vuelto = numeroFlotante - Float.parseFloat(ventanaPrincipal.cajaTotal.getText());
                    if (vuelto < 0) {
                        ventanaFacturacion.labelCambioDevolver.setText("0");
                    } else {
                        ventanaFacturacion.labelCambioDevolver.setText(formatoVuelto.format(vuelto));
                    }
                } catch (NumberFormatException ex) {
                }
            }
        };
        ventanaFacturacion.botonPasarMonto.addActionListener(aL);
    }

    private void keyListener_CajaMontoPagadoCliente_Ventana_Facturacion() {

        KeyListener kL = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {

                String patron2 = "###, ###. ##";
                DecimalFormat formatoVuelto = new DecimalFormat(patron2);

                if (ventanaFacturacion.cajaMontoPagadoCliente.getText().isEmpty()) {
                    ventanaFacturacion.labelCambioDevolver.setText("0");
                } else {
                    try {
                        String montoPagado = ventanaFacturacion.cajaMontoPagadoCliente.getText();
                        String SinPuntoMiles = montoPagado.replace(".", "");
                        String SinPuntoMilesConDecimal = SinPuntoMiles.replace(",", ".");
                        float numeroFlotante = Float.valueOf(SinPuntoMilesConDecimal);
                        float vuelto = numeroFlotante - Float.parseFloat(ventanaPrincipal.cajaTotal.getText());

                        if (vuelto < 0) {
                            ventanaFacturacion.labelCambioDevolver.setText("0");
                        } else {
                            ventanaFacturacion.labelCambioDevolver.setText(formatoVuelto.format(vuelto));
                        }
                    } catch (NumberFormatException ex) {
                    }
                }
            }
        };
        ventanaFacturacion.cajaMontoPagadoCliente.addKeyListener(kL);
    }

    private void cargarInformacion_Defacturacion() {

        EmpresaClienteDelSoftware empresaClienta = new EmpresaClienteDelSoftware();
        empresaClienta = sql.ConsultarInformation_configuration();
        ventanaFacturacion.NombreEmpresa.setText(empresaClienta.getNombreEmpresaCDS());
        ventanaFacturacion.nitEmpresa.setText(empresaClienta.getNitEmpresaCDS());
        ventanaFacturacion.dirEmpresa.setText(empresaClienta.getDireccionEmpresaCDS());
        ventanaFacturacion.celEmpresa.setText(empresaClienta.getCelularEmpresaCDS());
        ventanaFacturacion.telEmpresa.setText(empresaClienta.getTelefonoEmpresaCDS());
        ventanaFacturacion.departamentoUbicacion.setText(empresaClienta.getUbicacionEmpresaCDS());

        DecimalFormat formatoNumFac = new DecimalFormat("0000000");

        int numero_facturaActualDB = sql.buscarNumero_facturaActual();
        int numeroFacturaGenerado = generarNumeroFactura(numero_facturaActualDB);
        ventanaFacturacion.numeroFactura.setText(formatoNumFac.format(numeroFacturaGenerado));

        ventanaFacturacion.valorSubTotal.setText(ventanaPrincipal.cajaSubtotal.getText());
        ventanaFacturacion.valorMasIVA.setText(ventanaPrincipal.caja_IvaTotal.getText());

        String patron = "###,###.##";
        DecimalFormat formato = new DecimalFormat(patron);
        float total = Float.parseFloat(ventanaPrincipal.cajaTotal.getText());
        String valorTotal = formato.format(total);
        ventanaFacturacion.valorTotal.setText(valorTotal);

        ventanaFacturacion.nombreAtiende.setText(ventanaPrincipal.cajaAtiende.getText());

        int cantProdEntregados = 0;
        for (int i = 0; i < ventanaPrincipal.tablaDetalleFactura.getRowCount(); i++) {
            cantProdEntregados += Integer.parseInt(ventanaPrincipal.tablaDetalleFactura.getValueAt(i, 0).toString());
        }
        ventanaFacturacion.cantidad_Articulos_entregados.setText(String.valueOf(cantProdEntregados));
        ventanaFacturacion.fechaHora.setText(fechaHora.format(date));

        //Agrego la variable con el nit a el reporte--------------------------
        String nitEmp = ventanaFacturacion.nitEmpresa.getText();
        JRDataSource dataSource = new JREmptyDataSource();
        InputStream inputStream = null;
        JasperReport jasperReport = null;

        //Parametros
        /*
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("nitFarmacia", nitEmp);
        //Instancio el objeto inputStream con el archivo .jasper
//        try {
//            inputStream = new FileInputStream(new File("src\\Reportes\\ReporteInventarioMedicamentos.jasper"));
//        } catch (FileNotFoundException ex) {
//        }

        try {
            //jasperPrint.addVariable("variableName", variableValue); Para agregar la variable forma facil
            //Entrego argumento archivo .jasper, los parametros que voy a enviar y el datasource
            jasperReport = JasperCompileManager.compileReport("src\\Reportes\\ReporteInventarioMedicamentos.jrxml");
        } catch (JRException ex) {
        }

        JasperPrint jasperPrint = null;
        try {
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        } catch (JRException ex) {
        }
        JasperViewer.viewReport(jasperPrint);
        //end add variable--------------------------------------------------------------------
         */
    }

    private void MostrarTodaInfoFactura() {
        //vFactura.
        vFactura.TPFactura.setText(vFactura.TPFactura.getText() + "\n" + "                                             " + ventanaFacturacion.NombreEmpresa.getText() + "\n");
        vFactura.TPFactura.setText(vFactura.TPFactura.getText() + "                                                     " + ventanaFacturacion.nitEmpresa.getText() + "\n");
        vFactura.TPFactura.setText(vFactura.TPFactura.getText() + "                                                 " + ventanaFacturacion.dirEmpresa.getText() + "\n");
        vFactura.TPFactura.setText(vFactura.TPFactura.getText() + "                                          " + ventanaFacturacion.telEmpresa.getText() + "   " + ventanaFacturacion.celEmpresa.getText() + "\n");
        vFactura.TPFactura.setText(vFactura.TPFactura.getText() + "                                                 " + ventanaFacturacion.departamentoUbicacion.getText() + "\n");

        vFactura.TPFactura.setText(vFactura.TPFactura.getText() + "                                       FACTURA VENTA No." + ventanaFacturacion.numeroFactura.getText() + "\n");

        vFactura.TPFactura.setText(vFactura.TPFactura.getText() + "      ==================================================     \n");
        vFactura.TPFactura.setText(vFactura.TPFactura.getText() + "   Cant.  " + "  Cod.Barras" + "\t" + "Descripcion" + "\t" + "Val.Unit" + "\t" + "Importe\n");
        vFactura.TPFactura.setText(vFactura.TPFactura.getText() + "      ==================================================     \n");
        float descuentoTotal = 0;
        for (int i = 0; i < ventanaPrincipal.tablaDetalleFactura.getRowCount(); i++) {
            String cantiProducto = ventanaPrincipal.tablaDetalleFactura.getModel().getValueAt(i, 0).toString();
            String nomProducto = ventanaPrincipal.tablaDetalleFactura.getModel().getValueAt(i, 1).toString();

            Producto p = sql.buscarProductoPorNombre(nomProducto);
            String codBar = p.getCodigoBarras();

            String np = null;
            if (nomProducto.length() > 11) {
                np = nomProducto.trim();
                np = np.substring(0, 11);
                nomProducto = np;
            }

            String cb = null;
            if (codBar.length() > 13) {
                cb = codBar.substring(0, 13);
                codBar = cb;
            } else if (codBar.length() < 13) {
                int numEspaciosAdd = 13 - codBar.length();
                for (int j = 0; j <= numEspaciosAdd; j++) {
                    codBar += " ";
                }
            }

            String valorProducto = ventanaPrincipal.tablaDetalleFactura.getModel().getValueAt(i, 2).toString();
            String importeTotalProducto = ventanaPrincipal.tablaDetalleFactura.getModel().getValueAt(i, 5).toString();

            vFactura.TPFactura.setText(vFactura.TPFactura.getText() + "   " + cantiProducto + "    " + codBar + "\t" + nomProducto + "\t" + valorProducto + "\t" + importeTotalProducto + "\n");

            float valorDescuentoCadaProducto = (Float.parseFloat(ventanaPrincipal.tablaDetalleFactura.getModel().getValueAt(i, 3).toString()) * Float.parseFloat(ventanaPrincipal.tablaDetalleFactura.getModel().getValueAt(i, 4).toString())) / 100;
            descuentoTotal += valorDescuentoCadaProducto;
        }
        vFactura.TPFactura.setText(vFactura.TPFactura.getText() + "      ==================================================     \n");

        vFactura.TPFactura.setText(vFactura.TPFactura.getText() + "\t\t" + "Sub-Total : " + ventanaPrincipal.cajaSubtotal.getText() + "\n");
        vFactura.TPFactura.setText(vFactura.TPFactura.getText() + "\t\t" + "Total Descuento : " + descuentoTotal + "\n");
        vFactura.TPFactura.setText(vFactura.TPFactura.getText() + "\t\t" + "Total IVA :" + ventanaPrincipal.caja_IvaTotal.getText() + "\n");

        String patron = "###,###.##";
        DecimalFormat formato = new DecimalFormat(patron);
        float total = Float.parseFloat(ventanaPrincipal.cajaTotal.getText());
        String valorTotal = formato.format(total);
        vFactura.TPFactura.setText(vFactura.TPFactura.getText() + "\t\t" + "Total : " + valorTotal + " \n");
        vFactura.TPFactura.setText(vFactura.TPFactura.getText() + "                      *****************************************************\n");
        vFactura.TPFactura.setText(vFactura.TPFactura.getText() + "                                   GRACIAS POR SU COMPRA\n");
        vFactura.TPFactura.setText(vFactura.TPFactura.getText() + "                      *****************************************************\n");

        vFactura.TPFactura.setText(vFactura.TPFactura.getText() + "                           Sistema P.O.S  MARVEN - Desarrollado por : " + "\n");
        vFactura.TPFactura.setText(vFactura.TPFactura.getText() + "                                 DES Company NIT 114. 082.804-4" + "\n");
        vFactura.TPFactura.setText(vFactura.TPFactura.getText() + "                                       Atendido por : " + ventanaPrincipal.cajaAtiende.getText() + "\n");

        int cantProdEntregados = 0;
        for (int i = 0; i < ventanaPrincipal.tablaDetalleFactura.getRowCount(); i++) {
            cantProdEntregados += Integer.parseInt(ventanaPrincipal.tablaDetalleFactura.getValueAt(i, 0).toString());
        }
        vFactura.TPFactura.setText(vFactura.TPFactura.getText() + "                               Cantidad de Productos entregados : " + String.valueOf(cantProdEntregados) + "\n");
        vFactura.TPFactura.setText(vFactura.TPFactura.getText() + "                           Fecha de emisión : " + fechaHora.format(date) + "\n");
    }

    private int generarNumeroFactura(int facturaActual) {
        int numeroFactura;
        numeroFactura = facturaActual + 1;
        return numeroFactura;
    }

    private void LimpiarVentaLuegoDeFacturar() {
        DefaultTableModel modelo = (DefaultTableModel) ventanaPrincipal.tablaDetalleFactura.getModel();
        for (int i = modelo.getRowCount() - 1; i >= 0; i--) {
            modelo.removeRow(i);
        }
        ventanaPrincipal.labelCantidadenStock.setText("X");
        ventanaPrincipal.cajaCliente.setText("");
        ventanaPrincipal.cajaSubtotal.setText("0");
        ventanaPrincipal.caja_IvaTotal.setText("0");
        ventanaPrincipal.cajaTotal.setText("0");
        ventanaPrincipal.labelMostrarClienteEncontrado.setVisible(false);
        ventanaPrincipal.labelClienteAMostrar.setVisible(false);
        ventanaPrincipal.labelClienteNoRegistrado.setVisible(false);
        ventanaPrincipal.labelConfirmaClienteEnDB.setVisible(false);
        subTotalFactura = 0;
        ivaTotal = 0;
        ventanaPrincipal.labelImagenProducto.setVisible(true);
        ventanaPrincipal.panelimagenes.removeAll();
        ventanaPrincipal.panelimagenes.repaint();
        ventanaPrincipal.cajaCliente.requestFocus();
    }

    private void WindowClossing_VentanaFacturacion() {
        ventanaFacturacion.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ventanaFacturacion.dispose();
                ventanaFacturacion = null;
            }
        });
    }

    private void WindowClossing_VentanaFactura() {
        vFactura.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                vFactura.dispose();
                vFactura = null;
            }
        });
    }

}
