package modelo;

import com.mysql.jdbc.Connection;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.Date;
import java.util.HashMap;
//import java.util.List;
import java.util.Map;
//import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
//import javax.swing.ComboBoxModel;
//import javax.swing.DefaultComboBoxModel;
//import javax.swing.JDialog;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
//import javax.swing.table.TableModel;
//import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
//import vista.GestionarFacturas;

public class Sql extends Conexion {

    PreparedStatement ps = null;
    ResultSet rs = null;

    public boolean iniciarSesion(Usuario usuario, Cargo cargo) {
        Connection Conexion = getConnection();
        String nickname = usuario.nickName;

        try {

            ps = Conexion.prepareStatement("Select * from usuario where nickname_usuario= ? ");
            ps.setString(1, nickname);
            rs = ps.executeQuery();

            if (rs.next()) {
                if (usuario.getPassword().equals(rs.getString("password_usuario"))) {

                    ps = Conexion.prepareStatement("update usuario set ultima_sesion=? where id_usuario=?");
                    ps.setString(1, usuario.getFechaUltimaSesion());
                    ps.setInt(2, rs.getInt("id_usuario"));
                    ps.executeUpdate();

                    usuario.setIdUsuario(rs.getInt("id_usuario"));
                    usuario.setpNombre(rs.getString("primer_nombre_usuario"));
                    usuario.setsNombre(rs.getString("segundo_nombre_usuario"));
                    usuario.setpApellido(rs.getString("primer_apellido_usuario"));
                    usuario.setsApellido(rs.getString("segundo_apellido_usuario"));
                    usuario.setCedula(rs.getInt("cedula_usuario"));
                    cargo.setNombreCargo(rs.getString("cargo_usuario"));
                    cargo.setIdCargo(rs.getInt("id_cargo_usuario"));
                    usuario.setCargo(cargo);
                    usuario.setCorreo(rs.getString("correo"));

                    return true;
                } else {
                    return false;
                }

            } else {
                return false;
            }

        } catch (SQLException ex) {
            System.err.println(" Error al consultar el Usuario : " + ex);
            return false;
        } finally {
            try {
                Conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    public boolean comprobarEmail(String correo) {     // Verificar si el correo a ingresar es correcto 
        // Patrón para validar el email
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        // El email a validar
        //String email = "ervin0060@hotmail.com";
        Matcher matcher = pattern.matcher(correo);

        //Validacion 
        /*if (matcher.find() == true) {
            System.out.println("El email ingresado es válido.");
        } else {
            System.out.println("El email ingresado es inválido.");
        }*/
        return matcher.find();
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    public boolean ModificarInformation_configuration(EmpresaClienteDelSoftware empresaClienta) {
        Connection conexion = getConnection();

        try {
            ps = conexion.prepareStatement("update empresaclientedelsoftware set nombre_empresaCDSF=?, nit_empresaCDSF=?, direccion_empresaCDSF=?, celular_empresaCDSF=?, telefono_empresaCDSF=?, ubicacion_empresaCDSF=? where id_empresaClienteDelSoftware=1");
            ps.setString(1, empresaClienta.getNombreEmpresaCDS());
            ps.setString(2, empresaClienta.getNitEmpresaCDS());
            ps.setString(3, empresaClienta.getDireccionEmpresaCDS());
            ps.setString(4, empresaClienta.getCelularEmpresaCDS());
            ps.setString(5, empresaClienta.getTelefonoEmpresaCDS());
            ps.setString(6, empresaClienta.getUbicacionEmpresaCDS());

            int resultado = ps.executeUpdate();

            if (resultado > 0) {
                return true;
            }

            return false;

        } catch (Exception ex) {
            System.err.println("Error : " + ex);
            return false;
        } finally {
            try {
                conexion.close();
            } catch (Exception ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    public EmpresaClienteDelSoftware ConsultarInformation_configuration() {
        Connection conexion = getConnection();
        EmpresaClienteDelSoftware empresa = new EmpresaClienteDelSoftware();

        try {
            ps = conexion.prepareStatement("select * from empresaclientedelsoftware  where id_empresaClienteDelSoftware=1");
            rs = ps.executeQuery();

            if (rs.next()) {
                empresa.setNombreEmpresaCDS(rs.getString("nombre_empresaCDSF"));
                empresa.setNitEmpresaCDS(rs.getString("nit_empresaCDSF"));
                empresa.setDireccionEmpresaCDS(rs.getString("direccion_empresaCDSF"));
                empresa.setCelularEmpresaCDS(rs.getString("celular_empresaCDSF"));
                empresa.setTelefonoEmpresaCDS(rs.getString("telefono_empresaCDSF"));
                empresa.setUbicacionEmpresaCDS(rs.getString("ubicacion_empresaCDSF"));
                return empresa;
            }
            return null;
        } catch (Exception ex) {
            System.err.println("Error : " + ex);
            return null;
        } finally {
            try {
                conexion.close();
            } catch (Exception ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    public configuracionIvaDes Consultar_configurationDesIva() {
        Connection conexion = getConnection();
        configuracionIvaDes configuracion = new configuracionIvaDes();

        try {
            ps = conexion.prepareStatement("select * from configuracion_ivades  where id_configuracion_IvaDes=1");
            rs = ps.executeQuery();

            if (rs.next()) {
                configuracion.setIva_configuracion_IvaDes(rs.getInt("iva_configuracion_IvaDes"));
                configuracion.setDescuento_configuracion_IvaDes(rs.getInt("descuento_configuracion_IvaDes"));
                return configuracion;
            }
            return null;
        } catch (SQLException ex) {
            System.err.println("Error : " + ex);
            return null;
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    public boolean Modificar_configurationDesIva(configuracionIvaDes configuracion) {
        Connection conexion = getConnection();

        try {
            ps = conexion.prepareStatement("update configuracion_ivades set iva_configuracion_IvaDes=?, descuento_configuracion_IvaDes=? where id_configuracion_IvaDes=1");
            ps.setInt(1, configuracion.getIva_configuracion_IvaDes());
            ps.setInt(2, configuracion.getDescuento_configuracion_IvaDes());

            int resultado = ps.executeUpdate();

            if (resultado > 0) {
                return true;
            }

            return false;

        } catch (Exception ex) {
            System.err.println("Error : " + ex);
            return false;
        } finally {
            try {
                conexion.close();
            } catch (Exception ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    public int verificarSiUsuarioExiste(String nicknameValidar) {  // Verificar si el usuario a ingresar existe 
        // Verifica solo, si hay o No un usuario igual, que recibe de parametro.
        Connection Conexion = getConnection();
        try {
            ps = Conexion.prepareStatement("select count(id_usuario) from usuario where nickname_usuario=?");
            ps.setString(1, nicknameValidar);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1); // 1 es la columna id donde estara el conteo, entonces retorna 0 si no ha encontrado ningun usuario igual al que se le envio como parametro y uno si ya hay registrado.  
            }
            return 1;
        } catch (SQLException ex) {
            return 1;
        } finally {
            try {
                Conexion.close();
            } catch (SQLException ex) {
                System.err.println("Ha ocurrido un error : " + ex);
            }
        }
    }

    public boolean ingresarUsuario(Usuario usuario, Cargo cargo) {  // Ingresar un usuario a la DB
        Connection Conexion = getConnection();

        try {

            ps = Conexion.prepareStatement("insert into usuario (primer_nombre_usuario,segundo_nombre_usuario,primer_apellido_usuario,segundo_apellido_usuario,cedula_usuario,cargo_usuario,id_cargo_usuario,correo ,nickname_usuario,password_usuario,ultima_sesion) values(?,?,?,?,?,?,?,?,?,?,?) ");
            ps.setString(1, usuario.getpNombre());
            ps.setString(2, usuario.getsNombre());
            ps.setString(3, usuario.getpApellido());
            ps.setString(4, usuario.getsApellido());
            ps.setInt(5, usuario.getCedula());
            ps.setString(6, usuario.getCargo().getNombreCargo());
            ps.setInt(7, usuario.getCargo().getIdCargo());
            ps.setString(8, usuario.getCorreo());
            ps.setString(9, usuario.getNickName());
            ps.setString(10, usuario.getPassword());
            ps.setString(11, usuario.getFechaUltimaSesion());

            ps.executeUpdate();

            return true;

        } catch (SQLException ex) {
            System.err.println(" Error al ingresar el Usuario : " + ex);
            return false;
        } finally {
            try {
                Conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    public Usuario buscarUsuario(int cedula) {
        Usuario user = new Usuario();
        Cargo carg = new Cargo();
        Connection Conexion = getConnection();
        try {
            ps = Conexion.prepareStatement("Select * from usuario where cedula_usuario= ? ");
            ps.setInt(1, cedula);
            rs = ps.executeQuery();

            if (rs.next()) {
                user.setIdUsuario(rs.getInt("id_usuario"));
                user.setpNombre(rs.getString("primer_nombre_usuario"));
                user.setsNombre(rs.getString("segundo_nombre_usuario"));
                user.setpApellido(rs.getString("primer_apellido_usuario"));
                user.setsApellido(rs.getString("segundo_apellido_usuario"));
                user.setCedula(rs.getInt("cedula_usuario"));
                carg.setNombreCargo(rs.getString("cargo_usuario"));
                carg.setIdCargo(rs.getInt("id_cargo_usuario"));
                user.setCargo(carg);
                user.setCorreo(rs.getString("correo"));
                user.setNickName(rs.getString("nickname_usuario"));
                user.setPassword(rs.getString("password_usuario"));
                return user;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.err.println(" Error al consultar el Usuario : " + ex);
            return null;
        } finally {
            try {
                Conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    public Usuario buscarUsuario(String nickname) {
        Usuario user = new Usuario();
        Cargo carg = new Cargo();
        Connection Conexion = getConnection();
        try {
            ps = Conexion.prepareStatement("Select * from usuario where nickname_usuario= ? ");
            ps.setString(1, nickname);
            rs = ps.executeQuery();

            if (rs.next()) {
                user.setIdUsuario(rs.getInt("id_usuario"));
                user.setpNombre(rs.getString("primer_nombre_usuario"));
                user.setsNombre(rs.getString("segundo_nombre_usuario"));
                user.setpApellido(rs.getString("primer_apellido_usuario"));
                user.setsApellido(rs.getString("segundo_apellido_usuario"));
                user.setCedula(rs.getInt("cedula_usuario"));
                carg.setNombreCargo(rs.getString("cargo_usuario"));
                carg.setIdCargo(rs.getInt("id_cargo_usuario"));
                user.setCargo(carg);
                user.setCorreo(rs.getString("correo"));
                user.setNickName(rs.getString("nickname_usuario"));
                user.setPassword(rs.getString("password_usuario"));
                return user;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.err.println(" Error al consultar el Usuario : " + ex);
            return null;
        } finally {
            try {
                Conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    public boolean editarUsuario(Usuario usuario, int idUsuario) {
        Connection Conexion = getConnection();

        try {

            ps = Conexion.prepareStatement("update usuario set primer_nombre_usuario=?,segundo_nombre_usuario=?,primer_apellido_usuario=?,segundo_apellido_usuario=?,cedula_usuario=?,cargo_usuario=?,id_cargo_usuario=?,correo=? ,nickname_usuario=?,password_usuario=?,ultima_sesion=? where id_usuario=?");
            ps.setString(1, usuario.getpNombre());
            ps.setString(2, usuario.getsNombre());
            ps.setString(3, usuario.getpApellido());
            ps.setString(4, usuario.getsApellido());
            ps.setInt(5, usuario.getCedula());
            ps.setString(6, usuario.getCargo().getNombreCargo());
            ps.setInt(7, usuario.getCargo().getIdCargo());
            ps.setString(8, usuario.getCorreo());
            ps.setString(9, usuario.getNickName());
            ps.setString(10, usuario.getPassword());
            ps.setString(11, usuario.getFechaUltimaSesion());
            ps.setInt(12, idUsuario);

            ps.executeUpdate();

            return true;

        } catch (SQLException ex) {
            System.err.println(" Error al ingresar el Usuario : " + ex);
            return false;
        } finally {
            try {
                Conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    public boolean eliminarUsuario(int idUsuario) {
        Connection Conexion = getConnection();
        try {
            ps = Conexion.prepareStatement("delete from usuario  where id_usuario=?");
            ps.setInt(1, idUsuario);

            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.err.println(" Error al ingresar el Usuario : " + ex);
            return false;
        } finally {
            try {
                Conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    public int verificarSiClienteExiste(int cedula) {  // Verificar si el usuario a ingresar existe 

        Connection Conexion = getConnection();
        try {
            ps = Conexion.prepareStatement("select count(id_cliente) from cliente where cedula_cliente=?");
            ps.setInt(1, cedula);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1); // 1 es la columna id donde estara el conteo, entonces retorna 0 si no ha encontrado ningun usuario igual al que se le envio como parametro y uno si ya hay registrado.  
            }
            return 1;
        } catch (SQLException ex) {
            return 1;
        } finally {
            try {
                Conexion.close();
            } catch (SQLException ex) {
                System.err.println("Ha ocurrido un error : " + ex);
            }
        }
    }

    public Cliente buscarCliente(int cedulaCliente) {

        Cliente cliente = new Cliente();
        Connection Conexion = getConnection();

        try {
            ps = Conexion.prepareStatement("Select * from cliente where cedula_cliente= ? ");
            ps.setInt(1, cedulaCliente);
            rs = ps.executeQuery();

            if (rs.next()) {
                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setNombre(rs.getString("nombre_cliente"));
                cliente.setApellido(rs.getString("apellido_cliente"));
                cliente.setCedula(rs.getInt("cedula_cliente"));
                cliente.setCorreo(rs.getString("correo_cliente"));
                cliente.setCelular(rs.getString("celular_cliente"));
                cliente.setDireccion(rs.getString("direccion_cliente"));
                return cliente;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.err.println(" Error al consultar el Usuario : " + ex);
            return null;
        } finally {
            try {
                Conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    public Cliente buscarClienteXid(int idCliente) {

        Cliente cliente = new Cliente();
        Connection Conexion = getConnection();

        try {
            ps = Conexion.prepareStatement("Select * from cliente where id_cliente= ? ");
            ps.setInt(1, idCliente);
            rs = ps.executeQuery();

            if (rs.next()) {
                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setNombre(rs.getString("nombre_cliente"));
                cliente.setApellido(rs.getString("apellido_cliente"));
                cliente.setCedula(rs.getInt("cedula_cliente"));
                cliente.setCorreo(rs.getString("correo_cliente"));
                cliente.setCelular(rs.getString("celular_cliente"));
                cliente.setDireccion(rs.getString("direccion_cliente"));
                return cliente;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.err.println(" Error al consultar el Usuario : " + ex);
            return null;
        } finally {
            try {
                Conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    public DefaultTableModel buscarTodoCliente() {

        Cliente cliente = new Cliente();
        Connection Conexion = getConnection();

        try {
            ps = Conexion.prepareStatement("Select * from cliente");
            rs = ps.executeQuery();

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

            while (rs.next()) {

                ResultSetMetaData rssmd = rs.getMetaData();
                int cantidadColumnasTablaSql = rssmd.getColumnCount();

                Object[] filaTabla = new Object[cantidadColumnasTablaSql];

                for (int i = 0; i < cantidadColumnasTablaSql - 1; i++) {
                    filaTabla[i] = rs.getObject(i + 2);
                }
                modeloTabla.addRow(filaTabla);
            }
            return modeloTabla;

        } catch (SQLException ex) {
            System.err.println(" Error al consultar el Usuario : " + ex);
            return null;
        } finally {
            try {
                Conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    public DefaultTableModel buscarPosiblesClientesSegunNumerosDigitadosEnLaCaja(int cedulaCliente) {

        Connection Conexion = getConnection();
        String LIKE = cedulaCliente + "%";
        try {
            ps = Conexion.prepareStatement("Select * from cliente where cedula_cliente LIKE ?");
            ps.setString(1, LIKE);
            rs = ps.executeQuery();

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

//            rst = pst.executeQuery();
//           tablaproductos.setModel(DbUtils.resultSetToTableModel(rst));
            while (rs.next()) {

                ResultSetMetaData rssmd = rs.getMetaData();
                int cantidadColumnasTablaSql = rssmd.getColumnCount();

                Object[] filaTabla = new Object[cantidadColumnasTablaSql];

                for (int i = 0; i < cantidadColumnasTablaSql - 1; i++) {
                    filaTabla[i] = rs.getObject(i + 2);
                }
                modeloTabla.addRow(filaTabla);
            }
            return modeloTabla;

        } catch (SQLException ex) {
            System.err.println(" Error al consultar el Cliente : " + ex);
            return null;
        } finally {
            try {
                Conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    public boolean ingresarCliente(Cliente cliente) {  // Ingresar un usuario a la DB
        Connection Conexion = getConnection();

        try {

            ps = Conexion.prepareStatement("insert into cliente (nombre_cliente,apellido_cliente,cedula_cliente,correo_cliente ,celular_cliente, direccion_cliente) values(?,?,?,?,?,?) ");
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getCedula());
            ps.setString(4, cliente.getCorreo());
            ps.setString(5, cliente.getCelular());
            ps.setString(6, cliente.getDireccion());

            ps.executeUpdate();

            return true;

        } catch (SQLException ex) {
            System.err.println(" Error al ingresar el Usuario : " + ex);
            return false;
        } finally {
            try {
                Conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    public boolean editarCliente(Cliente cliente, int idUsuario) {
        Connection Conexion = getConnection();

        try {

            ps = Conexion.prepareStatement("update cliente set nombre_cliente=?,apellido_cliente=?,cedula_cliente=?,correo_cliente=? ,celular_cliente=? , direccion_cliente = ? where id_cliente=?");
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getCedula());
            ps.setString(4, cliente.getCorreo());
            ps.setString(5, cliente.getCelular());
            ps.setString(6, cliente.getDireccion());
            ps.setInt(7, idUsuario);

            ps.executeUpdate();

            return true;

        } catch (SQLException ex) {
            System.err.println(" Error al ingresar el Usuario : " + ex);
            return false;
        } finally {
            try {
                Conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    public boolean eliminarCliente(int idUsuario) {
        Connection Conexion = getConnection();
        try {
            ps = Conexion.prepareStatement("delete from cliente  where id_cliente=?");
            ps.setInt(1, idUsuario);

            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.err.println(" Error al ingresar el Usuario : " + ex);
            return false;
        } finally {
            try {
                Conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    public int verificarSiLaboratorioExiste(String nombreLaboratorio) {  // Verificar si el usuario a ingresar existe 

        Connection Conexion = getConnection();
        try {
            ps = Conexion.prepareStatement("select count(id_laboratorio) from laboratorio where nombre_laboratorio=?");
            ps.setString(1, nombreLaboratorio);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
            return 1;
        } catch (SQLException ex) {
            return 1;
        } finally {
            try {
                Conexion.close();
            } catch (SQLException ex) {
                System.err.println("Ha ocurrido un error : " + ex);
            }
        }
    }

    public Laboratorio buscarLaboratorio(String nombreLaboratorio) {

        Laboratorio laboratorio = new Laboratorio();
        Connection Conexion = getConnection();

        try {
            ps = Conexion.prepareStatement("Select * from laboratorio where nombre_laboratorio= ? ");
            ps.setString(1, nombreLaboratorio);
            rs = ps.executeQuery();

            if (rs.next()) {
                laboratorio.setIdLaboratorio(rs.getInt("id_laboratorio"));
                laboratorio.setNombre(rs.getString("nombre_laboratorio"));

                return laboratorio;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.err.println(" Error al consultar Laboratorio : " + ex);
            return null;
        } finally {
            try {
                Conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    public DefaultTableModel buscarPosiblesLaboratorioSegunLetrasDigitadasEnLaCaja(String nombre) {

        Connection Conexion = getConnection();
        ResultSet rrS;
        int idDelLaboratorio;

        try {

            DefaultTableModel tm = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tm.addColumn("Nombre");
            tm.addColumn("Codigo Barras");
            tm.addColumn("Cantidad en Stock");
            tm.addColumn("Precio");
            tm.addColumn("Fecha Vencimientos");
            tm.addColumn("Descripcion");

            ps = Conexion.prepareStatement("Select * from laboratorio where nombre_laboratorio = ?");
            ps.setString(1, nombre);
            rs = ps.executeQuery();

            while (rs.next()) {
                idDelLaboratorio = rs.getInt("id_laboratorio");
                ps = Conexion.prepareStatement("select * from producto where id_laboratorio_producto= ?");
                ps.setInt(1, idDelLaboratorio);
                rs = ps.executeQuery();

                while (rs.next()) {
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int cantCol = rsmd.getColumnCount();
                    Object[] fila = new Object[cantCol];

                    for (int i = 0; i < cantCol - 3; i++) {
                        fila[i] = rs.getObject(i + 2);
                    }
                    tm.addRow(fila);
                }
            }
            return tm;
        } catch (SQLException ex) {
            System.err.println(" Error al consultar el Laboratorio: " + ex);
            return null;
        } finally {
            try {
                Conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    public ArrayList<Laboratorio> buscarLaboratorio() {

        ArrayList<Laboratorio> listaLaboratorio = new ArrayList<>();
        Laboratorio laboratorio;
        Connection Conexion = getConnection();

        try {
            ps = Conexion.prepareStatement("Select *  from laboratorio");
            rs = ps.executeQuery();

            while (rs.next()) {
                laboratorio = new Laboratorio();
                laboratorio.setIdLaboratorio(rs.getInt("id_laboratorio"));
                laboratorio.setNombre(rs.getString("nombre_laboratorio"));
                listaLaboratorio.add(laboratorio);
            }
            return listaLaboratorio;
        } catch (SQLException ex) {
            System.err.println(" Error al consultar Laboratorio : " + ex);
            return null;
        } finally {
            try {
                Conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    public boolean ingresarLaboratorio(Laboratorio laboratorio) {  // Ingresar un usuario a la DB
        Connection Conexion = getConnection();

        try {

            ps = Conexion.prepareStatement("insert into laboratorio (nombre_laboratorio) values(?) ");
            ps.setString(1, laboratorio.getNombre());
            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.err.println(" Error intentar ingresar Laboratorio : " + ex);
            return false;
        } finally {
            try {
                Conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    public boolean editarLaboratorio(Laboratorio laboratorio, int idLaboratorio) {
        Connection Conexion = getConnection();

        try {

            ps = Conexion.prepareStatement("update laboratorio set nombre_laboratorio=? where id_laboratorio=?");
            ps.setString(1, laboratorio.getNombre());
            ps.setInt(2, idLaboratorio);
            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.err.println(" Error al intentar editar Laboratorio : " + ex);
            return false;
        } finally {
            try {
                Conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    public boolean eliminarLaboratorio(int idLaboratorio) {
        Connection Conexion = getConnection();
        try {
            ps = Conexion.prepareStatement("delete from laboratorio  where id_laboratorio=?");
            ps.setInt(1, idLaboratorio);
            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.err.println(" Error al intentar eliminar Laboratorio : " + ex);
            return false;
        } finally {
            try {
                Conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    public int verificarSiProveedorExiste(int cedula) {

        Connection Conexion = getConnection();
        try {
            ps = Conexion.prepareStatement("select count(id_proveedor) from proveedor where cedula_proveedor=?");
            ps.setInt(1, cedula);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1); // 1 es la columna id donde estara el conteo, entonces retorna 0 si no ha encontrado ningun usuario igual al que se le envio como parametro y uno si ya hay registrado.  
            }
            return 1;
        } catch (SQLException ex) {
            return 1;
        } finally {
            try {
                Conexion.close();
            } catch (SQLException ex) {
                System.err.println("Ha ocurrido un error : " + ex);
            }
        }
    }

    public Proveedor buscarProveedor(int cedulaProveedor) {

        Proveedor proveedor = new Proveedor();
        Connection Conexion = getConnection();

        try {
            ps = Conexion.prepareStatement("Select * from proveedor where cedula_proveedor= ? ");
            ps.setInt(1, cedulaProveedor);
            rs = ps.executeQuery();

            if (rs.next()) {
                proveedor.setIdProveedor(rs.getInt("id_proveedor"));
                proveedor.setNombre(rs.getString("nombre_proveedor"));
                proveedor.setApellido(rs.getString("apellido_proveedor"));
                proveedor.setCedula(rs.getInt("cedula_proveedor"));
                proveedor.setCelular(rs.getString("celular_proveedor"));
                proveedor.setCorreo(rs.getString("correo_proveedor"));
                proveedor.setNombreEmpresa(rs.getString("empresa_proveedor"));
                proveedor.setTelEmpresa(rs.getString("tel_empresa_proveedor"));
                proveedor.setDirEmpresa(rs.getString("dir_empresa_proveedor"));
                return proveedor;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.err.println(" Error al intentar consultar Proveedor: " + ex);
            return null;
        } finally {
            try {
                Conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    public ArrayList<Proveedor> buscarProveedor() {

        ArrayList<Proveedor> listaProveedor = new ArrayList<>();
        Proveedor nProveedor;
        Connection Conexion = getConnection();

        try {
            ps = Conexion.prepareStatement("Select *  from proveedor");
            rs = ps.executeQuery();

            while (rs.next()) {
                nProveedor = new Proveedor();
                nProveedor.setIdProveedor(rs.getInt("id_proveedor"));
                nProveedor.setNombre(rs.getString("nombre_proveedor"));
                nProveedor.setApellido(rs.getString("apellido_proveedor"));
                nProveedor.setCedula(rs.getInt("cedula_proveedor"));
                nProveedor.setCelular(rs.getString("celular_proveedor"));
                nProveedor.setCorreo(rs.getString("correo_proveedor"));
                nProveedor.setNombreEmpresa(rs.getString("empresa_proveedor"));
                nProveedor.setTelEmpresa(rs.getString("tel_empresa_proveedor"));
                nProveedor.setDirEmpresa(rs.getString("dir_empresa_proveedor"));
                listaProveedor.add(nProveedor);
            }
            return listaProveedor;
        } catch (SQLException ex) {
            System.err.println(" Error al consultar Laboratorio : " + ex);
            return null;
        } finally {
            try {
                Conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    public DefaultTableModel buscarTodoProveedor() {

        ArrayList<Proveedor> listaProveedor = new ArrayList<>();
        Proveedor nProveedor;
        Connection Conexion = getConnection();

        try {
            ps = Conexion.prepareStatement("Select *  from proveedor ");
            rs = ps.executeQuery();

            DefaultTableModel tM = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tM.addColumn("Nombre");
            tM.addColumn("Apellido");
            tM.addColumn("Cedula");
            tM.addColumn("Correo");
            tM.addColumn("Celular");
            tM.addColumn("Empresa");
            tM.addColumn("Tel Emp.");
            tM.addColumn("Dir Emp.");

            while (rs.next()) {
                nProveedor = new Proveedor();
                nProveedor.setIdProveedor(rs.getInt("id_proveedor"));
                nProveedor.setNombre(rs.getString("nombre_proveedor"));
                nProveedor.setApellido(rs.getString("apellido_proveedor"));
                nProveedor.setCedula(rs.getInt("cedula_proveedor"));
                nProveedor.setCelular(rs.getString("celular_proveedor"));
                nProveedor.setCorreo(rs.getString("correo_proveedor"));
                nProveedor.setNombreEmpresa(rs.getString("empresa_proveedor"));
                nProveedor.setTelEmpresa(rs.getString("tel_empresa_proveedor"));
                nProveedor.setDirEmpresa(rs.getString("dir_empresa_proveedor"));
                listaProveedor.add(nProveedor);

                ResultSetMetaData rsmd = rs.getMetaData();
                int cantidadColumnas = rsmd.getColumnCount();
                Object fila[] = new Object[cantidadColumnas];

                for (int i = 0; i < cantidadColumnas - 1; i++) {
                    fila[i] = rs.getObject(i + 2);
                }
                tM.addRow(fila);
            }
            return tM;
        } catch (SQLException ex) {
            System.err.println(" Error al consultar Laboratorio : " + ex);
            return null;
        } finally {
            try {
                Conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    public DefaultTableModel buscarPosiblesProveedoresSegunNumerosDigitadosEnLaCaja(int cedulaProveedor) {

        Connection Conexion = getConnection();
        String LIKE = cedulaProveedor + "%";

        try {
            ps = Conexion.prepareStatement("Select * from proveedor where cedula_proveedor LIKE ?");
            ps.setString(1, LIKE);
            rs = ps.executeQuery();

            DefaultTableModel tM = new DefaultTableModel();
            tM.addColumn("Nombre");
            tM.addColumn("Apellido");
            tM.addColumn("Cedula");
            tM.addColumn("Correo");
            tM.addColumn("Celular");
            tM.addColumn("Empresa");
            tM.addColumn("Tel Emp.");
            tM.addColumn("Dir Emp.");

            while (rs.next()) {

                ResultSetMetaData rssmd = rs.getMetaData();
                int cantidadColumnasTablaSql = rssmd.getColumnCount();

                Object[] filaTabla = new Object[cantidadColumnasTablaSql];

                for (int i = 0; i < cantidadColumnasTablaSql - 1; i++) {
                    filaTabla[i] = rs.getObject(i + 2);
                }
                tM.addRow(filaTabla);
            }
            return tM;

        } catch (SQLException ex) {
            System.err.println(" Error al consultar el Proveedor : " + ex);
            return null;
        } finally {
            try {
                Conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    public boolean ingresarProveedor(Proveedor proveedor) {
        Connection Conexion = getConnection();

        try {
            ps = Conexion.prepareStatement("insert into proveedor (nombre_proveedor, apellido_proveedor, cedula_proveedor , celular_proveedor, correo_proveedor ,empresa_proveedor, tel_empresa_proveedor, dir_empresa_proveedor) values(?,?,?,?,?,?,?,?) ");
            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getApellido());
            ps.setInt(3, proveedor.getCedula());
            ps.setString(4, proveedor.getCelular());
            ps.setString(5, proveedor.getCorreo());
            ps.setString(6, proveedor.getNombreEmpresa());
            ps.setString(7, proveedor.getTelEmpresa());
            ps.setString(8, proveedor.getDirEmpresa());
            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.err.println(" Error al intentar crear Proveedor : " + ex);
            return false;
        } finally {
            try {
                Conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    public boolean editarProveedor(Proveedor proveedor, int idProveedor) {
        Connection Conexion = getConnection();

        try {

            ps = Conexion.prepareStatement("update proveedor set nombre_proveedor=?,apellido_proveedor=?,cedula_proveedor=?,celular_proveedor=? ,correo_proveedor=? , empresa_proveedor = ?  , tel_empresa_proveedor = ? , dir_empresa_proveedor = ? where id_proveedor=?");
            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getApellido());
            ps.setInt(3, proveedor.getCedula());
            ps.setString(4, proveedor.getCelular());
            ps.setString(5, proveedor.getCorreo());
            ps.setString(6, proveedor.getNombreEmpresa());
            ps.setString(7, proveedor.getTelEmpresa());
            ps.setString(8, proveedor.getDirEmpresa());
            ps.setInt(9, idProveedor);

            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.err.println(" Error al intentar editar Proveedor : " + ex);
            return false;
        } finally {
            try {
                Conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    public boolean eliminarProveedor(int idProveedor) {
        Connection Conexion = getConnection();
        try {
            ps = Conexion.prepareStatement("delete from proveedor where id_proveedor=?");
            ps.setInt(1, idProveedor);
            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.err.println(" Error al intentar eliminar Proveedor: " + ex);
            return false;
        } finally {
            try {
                Conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    public int verificarSiProductoExiste(String codigoBarras) {

        Connection Conexion = getConnection();
        try {
            ps = Conexion.prepareStatement("select count(id_producto) from producto where codigo_barras_producto=?");
            ps.setString(1, codigoBarras);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1); // 1 es la columna id donde estara el conteo, entonces retorna 0 si no ha encontrado ningun usuario igual al que se le envio como parametro y uno si ya hay registrado.  
            }
            return 1;
        } catch (SQLException ex) {
            return 1;
        } finally {
            try {
                Conexion.close();
            } catch (SQLException ex) {
                System.err.println("Ha ocurrido un error : " + ex);
            }
        }
    }

    public Producto buscarProducto(String codigoBarraProducto) {

        Producto producto = new Producto();
        Laboratorio laboratorio = new Laboratorio();
        Proveedor proveedor = new Proveedor();
        Connection Conexion = getConnection();
        ImagenProducto img = new ImagenProducto();

        try {
            ps = Conexion.prepareStatement("select * from producto where codigo_barras_producto= ? ");
            ps.setString(1, codigoBarraProducto);
            rs = ps.executeQuery();

            if(rs.next()) {
                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setNombre(rs.getString("nombre_producto"));
                producto.setCodigoBarras(rs.getString("codigo_barras_producto"));
                producto.setCantidadStock(rs.getInt("cantidad_stock_producto"));
                producto.setPrecio(rs.getFloat("precio_producto"));
                producto.setFechaVencimiento(String.valueOf(rs.getDate("fecha_vencimiento_producto")));
                producto.setDescripcion(rs.getString("descripcion_producto"));
                img.setIdImagen(rs.getInt("id_imagen_producto"));
                producto.setImagen(img);

                int idLaboratorioEncontrado = rs.getInt("id_laboratorio_producto");
                int idProveedorEncontrado = rs.getInt("id_proveedor_producto");

                ps = Conexion.prepareStatement("select * from laboratorio where id_laboratorio =?");
                ps.setInt(1, idLaboratorioEncontrado);
                rs = ps.executeQuery();
                if (rs.next()) {
                    laboratorio.setIdLaboratorio(rs.getInt("id_laboratorio"));
                    laboratorio.setNombre(rs.getString("nombre_laboratorio"));
                    producto.setLaboratorio(laboratorio);
                }

                ps = Conexion.prepareStatement("select * from proveedor where id_proveedor =?");
                ps.setInt(1, idProveedorEncontrado);
                rs = ps.executeQuery();
                if (rs.next()) {
                    proveedor.setIdProveedor(rs.getInt("id_proveedor"));
                    proveedor.setNombre(rs.getString("nombre_proveedor"));
                    proveedor.setApellido(rs.getString("apellido_proveedor"));
                    proveedor.setNombreEmpresa(rs.getString("empresa_proveedor"));
                    producto.setProveedor(proveedor);
                }
                return producto;
            }
            return null;
        } catch (SQLException ex) {
            System.err.println(" Error al intentar consultar Producto: " + ex);
            return null;
        } finally {
            try {
                Conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    public Producto buscarProductoXid(int idProducto) {

        Producto producto = new Producto();
        Laboratorio laboratorio = new Laboratorio();
        Proveedor proveedor = new Proveedor();
        Connection Conexion = getConnection();
        ImagenProducto img = new ImagenProducto();

        try {
            ps = Conexion.prepareStatement("select * from producto where id_producto= ? ");
            ps.setInt(1, idProducto);
            rs = ps.executeQuery();

            if (rs.next()) {
                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setNombre(rs.getString("nombre_producto"));
                producto.setCodigoBarras(rs.getString("codigo_barras_producto"));
                producto.setCantidadStock(rs.getInt("cantidad_stock_producto"));
                producto.setPrecio(rs.getFloat("precio_producto"));
                producto.setFechaVencimiento(String.valueOf(rs.getDate("fecha_vencimiento_producto")));
                producto.setDescripcion(rs.getString("descripcion_producto"));
                img.setIdImagen(rs.getInt("id_imagen_producto"));
                producto.setImagen(img);

                int idLaboratorioEncontrado = rs.getInt("id_laboratorio_producto");
                int idProveedorEncontrado = rs.getInt("id_proveedor_producto");

                ps = Conexion.prepareStatement("select * from laboratorio where id_laboratorio =?");
                ps.setInt(1, idLaboratorioEncontrado);
                rs = ps.executeQuery();
                if (rs.next()) {
                    laboratorio.setIdLaboratorio(rs.getInt("id_laboratorio"));
                    laboratorio.setNombre(rs.getString("nombre_laboratorio"));
                    producto.setLaboratorio(laboratorio);
                }

                ps = Conexion.prepareStatement("select * from proveedor where id_proveedor =?");
                ps.setInt(1, idProveedorEncontrado);
                rs = ps.executeQuery();
                if (rs.next()) {
                    proveedor.setIdProveedor(rs.getInt("id_proveedor"));
                    proveedor.setNombre(rs.getString("nombre_proveedor"));
                    proveedor.setApellido(rs.getString("apellido_proveedor"));
                    proveedor.setNombreEmpresa(rs.getString("empresa_proveedor"));
                    producto.setProveedor(proveedor);
                }

                return producto;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.err.println(" Error al intentar consultar Producto: " + ex);
            return null;
        } finally {
            try {
                Conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    public Producto buscarProductoPorNombre(String nombre) {

        Producto producto = new Producto();
        Laboratorio laboratorio = new Laboratorio();
        Proveedor proveedor = new Proveedor();
        Connection Conexion = getConnection();

        try {
            ps = Conexion.prepareStatement("select * from producto where nombre_producto= ?");
            ps.setString(1, nombre);
            rs = ps.executeQuery();

            if (rs.next()) {
                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setNombre(rs.getString("nombre_producto"));
                producto.setCodigoBarras(rs.getString("codigo_barras_producto"));
                producto.setCantidadStock(rs.getInt("cantidad_stock_producto"));
                producto.setPrecio(rs.getFloat("precio_producto"));
                producto.setFechaVencimiento(String.valueOf(rs.getDate("fecha_vencimiento_producto")));
                producto.setDescripcion(rs.getString("descripcion_producto"));

                ImagenProducto img = new ImagenProducto();
                img.setIdImagen(rs.getInt("id_imagen_producto"));
                producto.setImagen(img);

                int idLaboratorioEncontrado = rs.getInt("id_laboratorio_producto");
                int idProveedorEncontrado = rs.getInt("id_proveedor_producto");

                ps = Conexion.prepareStatement("select * from laboratorio where id_laboratorio =?");
                ps.setInt(1, idLaboratorioEncontrado);
                rs = ps.executeQuery();
                if (rs.next()) {
                    laboratorio.setIdLaboratorio(rs.getInt("id_laboratorio"));
                    laboratorio.setNombre(rs.getString("nombre_laboratorio"));
                    producto.setLaboratorio(laboratorio);
                }

                ps = Conexion.prepareStatement("select * from proveedor where id_proveedor =?");
                ps.setInt(1, idProveedorEncontrado);
                rs = ps.executeQuery();
                if (rs.next()) {
                    proveedor.setIdProveedor(rs.getInt("id_proveedor"));
                    proveedor.setNombre(rs.getString("nombre_proveedor"));
                    proveedor.setApellido(rs.getString("apellido_proveedor"));
                    proveedor.setNombreEmpresa(rs.getString("empresa_proveedor"));
                    producto.setProveedor(proveedor);
                }
                return producto;
            }
            return null;
        } catch (SQLException ex) {
            System.err.println(" Error al intentar consultar Producto: " + ex);
            return null;
        } finally {
            try {
                Conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    public ArrayList<Producto> buscarProducto(int idLaboratorio) {

        ArrayList<Producto> listaProducto = new ArrayList<>();
        Producto nProducto;
        Proveedor proveedor = new Proveedor();
        Laboratorio laboratorio = new Laboratorio();
        Connection Conexion = getConnection();

        try {
            ps = Conexion.prepareStatement("Select *  from producto where id_laboratorio_producto=?");
            ps.setInt(1, idLaboratorio);
            rs = ps.executeQuery();

            while (rs.next()) {
                nProducto = new Producto();
                nProducto.setIdProducto(rs.getInt("id_producto"));
                nProducto.setNombre(rs.getString("nombre_producto"));
                nProducto.setCodigoBarras(rs.getString("codigo_barras_producto"));
                nProducto.setCantidadStock(rs.getInt("cantidad_stock_producto"));
                nProducto.setPrecio(rs.getFloat("precio_producto"));
                nProducto.setFechaVencimiento(String.valueOf(rs.getDate("fecha_vencimiento_producto")));
                nProducto.setDescripcion(rs.getString("descripcion_producto"));

                proveedor.setIdProveedor(rs.getInt("id_proveedor_producto"));
                laboratorio.setIdLaboratorio(rs.getInt("id_laboratorio_producto"));

                nProducto.setProveedor(proveedor);
                nProducto.setLaboratorio(laboratorio);
                listaProducto.add(nProducto);
            }
            return listaProducto;

        } catch (SQLException ex) {
            System.err.println(" Error al consultar Laboratorio : " + ex);
            return null;
        } finally {
            try {
                Conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    public ArrayList<Producto> buscarProductoPorProveedor(int idProveedor) {

        ArrayList<Producto> listaProducto = new ArrayList<>();
        Producto nProducto;
        Proveedor proveedor = new Proveedor();
        Laboratorio laboratorio = new Laboratorio();
        Connection Conexion = getConnection();

        try {
            ps = Conexion.prepareStatement("Select *  from producto where id_proveedor_producto=?");
            ps.setInt(1, idProveedor);
            rs = ps.executeQuery();

            while (rs.next()) {
                nProducto = new Producto();
                nProducto.setIdProducto(rs.getInt("id_producto"));
                nProducto.setNombre(rs.getString("nombre_producto"));
                nProducto.setCodigoBarras(rs.getString("codigo_barras_producto"));
                nProducto.setCantidadStock(rs.getInt("cantidad_stock_producto"));
                nProducto.setPrecio(rs.getFloat("precio_producto"));
                nProducto.setFechaVencimiento(String.valueOf(rs.getDate("fecha_vencimiento_producto")));
                nProducto.setDescripcion(rs.getString("descripcion_producto"));

                proveedor.setIdProveedor(rs.getInt("id_proveedor_producto"));
                laboratorio.setIdLaboratorio(rs.getInt("id_laboratorio_producto"));

                nProducto.setProveedor(proveedor);
                nProducto.setLaboratorio(laboratorio);
                listaProducto.add(nProducto);
            }
            return listaProducto;
        } catch (SQLException ex) {
            System.err.println(" Error al consultar Laboratorio : " + ex);
            return null;
        } finally {
            try {
                Conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    public DefaultTableModel buscarTodoProducto() {

        ArrayList<Producto> listaProducto = new ArrayList<>();
        Producto nProducto;
        Proveedor proveedor = new Proveedor();
        Laboratorio laboratorio = new Laboratorio();
        Connection Conexion = getConnection();

        try {
            ps = Conexion.prepareStatement("Select *  from producto");
            rs = ps.executeQuery();

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

            while (rs.next()) {
                nProducto = new Producto();
                nProducto.setIdProducto(rs.getInt("id_producto"));
                nProducto.setNombre(rs.getString("nombre_producto"));
                nProducto.setCodigoBarras(rs.getString("codigo_barras_producto"));
                nProducto.setCantidadStock(rs.getInt("cantidad_stock_producto"));
                nProducto.setPrecio(rs.getFloat("precio_producto"));
                nProducto.setFechaVencimiento(String.valueOf(rs.getDate("fecha_vencimiento_producto")));
                nProducto.setDescripcion(rs.getString("descripcion_producto"));

                proveedor.setIdProveedor(rs.getInt("id_proveedor_producto"));
                laboratorio.setIdLaboratorio(rs.getInt("id_laboratorio_producto"));

                nProducto.setProveedor(proveedor);
                nProducto.setLaboratorio(laboratorio);

                listaProducto.add(nProducto);

                //Esto lo utilizo para tomar valores de la tabla de la db y acomodarlos en la tabla de forma perzonalizada*
                ResultSetMetaData rsmd = rs.getMetaData();
                int cantidadColumnas = rsmd.getColumnCount();
                Object fila[] = new Object[cantidadColumnas];

                for (int i = 0; i < cantidadColumnas - 1; i++) {
                    switch (i) {
                        case 0:
                            fila[i] = rs.getObject(i + 2);//obtengo posicion 2 (me vuelo la 1)
                            break;
                        case 1:
                            fila[i] = rs.getObject(i + 4);//obtengo posicion 5 
                            break;
                        case 2:
                            fila[i] = rs.getObject(i + 2);//obtengo posicion 4 
                            break;
                        case 3:
                            fila[i] = rs.getObject(i + 3);//obtengo posicion 6 
                            break;
                        case 4:
                            fila[i] = rs.getObject(i + 3);//obtengo posicion 7 
                            break;
                        case 5:
                            String nombreLaboratorioEncontrado = null;

                            int idLaboratorioABuscar = (int) rs.getObject(i + 3);//obtengo posicion 8

                            PreparedStatement pps = Conexion.prepareStatement("select * from laboratorio where id_laboratorio = ? ");
                            pps.setInt(1, idLaboratorioABuscar);
                            ResultSet rss = pps.executeQuery();

                            if (rss.next()) {
                                nombreLaboratorioEncontrado = rss.getString("nombre_laboratorio");
                            }
                            fila[i] = nombreLaboratorioEncontrado;
                            break;
                        case 6:
                            String nombreProveedorEncontrado = null;
                            if (rs.getObject(i + 3) != null) {
                                int idProveedorABuscar = (int) rs.getObject(i + 3);//obtengo posicion 9
                                PreparedStatement ppss = Conexion.prepareStatement("select * from proveedor where id_proveedor = ? ");
                                ppss.setInt(1, idProveedorABuscar);
                                ResultSet rsss = ppss.executeQuery();

                                if (rsss.next()) {
                                    nombreProveedorEncontrado = rsss.getString("nombre_proveedor");
                                }
                            } else {
                                nombreProveedorEncontrado = "N/A";
                            }

                            fila[i] = nombreProveedorEncontrado;
                            break;
                        case 7:
                            fila[i] = rs.getObject(i - 4);//obtengo posicion 3
                            break;
                            
                        default:
                            break;
                    }
                }
                modeloTabla.addRow(fila);
            }
            return modeloTabla;
        } catch (SQLException ex) {
            System.err.println(" Error al consultar Laboratorio : " + ex);
            return null;
        } finally {
            try {
                Conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    public DefaultTableModel buscarTodoProducto(int idLaboratorio) {
        Producto nProducto;
        Connection Conexion = getConnection();

        try {
            ArrayList<Producto> listaProducto = new ArrayList<>();
            ps = Conexion.prepareStatement("Select *  from producto where id_laboratorio_producto=?");
            ps.setInt(1, idLaboratorio);
            rs = ps.executeQuery();

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

            while (rs.next()) {
                nProducto = new Producto();
                nProducto.setIdProducto(rs.getInt("id_producto"));
                nProducto.setNombre(rs.getString("nombre_producto"));
                nProducto.setCodigoBarras(rs.getString("codigo_barras_producto"));
                nProducto.setCantidadStock(rs.getInt("cantidad_stock_producto"));
                nProducto.setPrecio(rs.getFloat("precio_producto"));
                nProducto.setFechaVencimiento(String.valueOf(rs.getDate("fecha_vencimiento_producto")));
                nProducto.setDescripcion(rs.getString("descripcion_producto"));
                listaProducto.add(nProducto);

                ResultSetMetaData rsmd = rs.getMetaData();
                int cantidadColumnas = rsmd.getColumnCount();
                Object fila[] = new Object[cantidadColumnas];

                for (int i = 0; i < cantidadColumnas - 3; i++) {
                    fila[i] = rs.getObject(i + 2);
                }
                tM.addRow(fila);
            }
            return tM;
        } catch (SQLException ex) {
            System.err.println(" Error al consultar Laboratorio : " + ex);
            return null;
        } finally {
            try {
                Conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    public DefaultTableModel buscarUnProducto(String nomLab) {

        Producto nProducto;
        Connection Conexion = getConnection();

        try {
            ps = Conexion.prepareStatement("Select *  from producto where nombre_producto=?");
            ps.setString(1, nomLab);
            rs = ps.executeQuery();

            DefaultTableModel tM = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tM.addColumn("Nombre");
            tM.addColumn("Codigo Barras");
            tM.addColumn("Cantidad en Stock");
            tM.addColumn("Precio");
            tM.addColumn("Fecha Vencimientos");
            tM.addColumn("Descripcion");

            if (rs.next()) {
                nProducto = new Producto();
                nProducto.setIdProducto(rs.getInt("id_producto"));
                nProducto.setNombre(rs.getString("nombre_producto"));
                nProducto.setCodigoBarras(rs.getString("codigo_barras_producto"));
                nProducto.setCantidadStock(rs.getInt("cantidad_stock_producto"));
                nProducto.setPrecio(rs.getFloat("precio_producto"));
                nProducto.setFechaVencimiento(String.valueOf(rs.getDate("fecha_vencimiento_producto")));
                nProducto.setDescripcion(rs.getString("descripcion_producto"));

                ResultSetMetaData rsmd = rs.getMetaData();
                int cantidadColumnas = rsmd.getColumnCount();
                Object fila[] = new Object[cantidadColumnas];

                for (int i = 0; i < cantidadColumnas - 3; i++) {
                    fila[i] = rs.getObject(i + 2);
                }
                tM.addRow(fila);
            }
            return tM;
        } catch (SQLException ex) {
            System.err.println(" Error al consultar Laboratorio : " + ex);
            return null;
        } finally {
            try {
                Conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    public boolean ingresarProducto(Producto producto) {
        Connection Conexion = getConnection();

        try {
            ImagenProducto imagen;

            if (producto.getImagen() == null) {
                ps = Conexion.prepareStatement("insert into producto (nombre_producto, codigo_barras_producto, cantidad_stock_producto , precio_producto, fecha_vencimiento_producto ,descripcion_producto, id_laboratorio_producto, id_proveedor_producto, id_imagen_producto) values(?,?,?,?,?,?,?,?,NULL)");
                ps.setString(1, producto.getNombre());
                ps.setString(2, producto.getCodigoBarras());
                ps.setInt(3, producto.getCantidadStock());
                ps.setFloat(4, producto.getPrecio());
                ps.setString(5, producto.getFechaVencimiento());
                ps.setString(6, producto.getDescripcion());
                ps.setInt(7, producto.getLaboratorio().getIdLaboratorio());
                ps.setInt(8, producto.getProveedor().getIdProveedor());

                ps.executeUpdate();
            } else {
                ps = Conexion.prepareStatement("insert into producto (nombre_producto, codigo_barras_producto, cantidad_stock_producto , precio_producto, fecha_vencimiento_producto ,descripcion_producto, id_laboratorio_producto, id_proveedor_producto, id_imagen_producto) values(?,?,?,?,?,?,?,?,?)");
                ps.setString(1, producto.getNombre());
                ps.setString(2, producto.getCodigoBarras());
                ps.setInt(3, producto.getCantidadStock());
                ps.setFloat(4, producto.getPrecio());
                ps.setString(5, producto.getFechaVencimiento());
                ps.setString(6, producto.getDescripcion());
                ps.setInt(7, producto.getLaboratorio().getIdLaboratorio());
                ps.setInt(8, producto.getProveedor().getIdProveedor());
                imagen = producto.getImagen();
                ps.setInt(9, imagen.getIdImagen());

                ps.executeUpdate();
            }

            return true;

        } catch (SQLException ex) {
            System.err.println(" Error al intentar ingresar producto : " + ex);
            return false;
        } finally {
            try {
                Conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    public boolean editarProducto(Producto producto, int idProducto, int id_Imagen) {
        Connection Conexion = getConnection();

        try {
            if (id_Imagen > 0) {
                ps = Conexion.prepareStatement("update producto set nombre_producto=?,codigo_barras_producto=?,cantidad_stock_producto=?,precio_producto=? ,fecha_vencimiento_producto=? , descripcion_producto = ?, id_laboratorio_producto = ? , id_proveedor_producto = ?,id_imagen_producto=? where id_producto=?");
                ps.setString(1, producto.getNombre());
                ps.setString(2, producto.getCodigoBarras());
                ps.setInt(3, producto.getCantidadStock());
                ps.setFloat(4, producto.getPrecio());
                ps.setString(5, producto.getFechaVencimiento());
                ps.setString(6, producto.getDescripcion());
                ps.setInt(7, producto.getLaboratorio().getIdLaboratorio());
                ps.setInt(8, producto.getProveedor().getIdProveedor());
                ps.setInt(9, id_Imagen);
                ps.setInt(10, idProducto);
                ps.executeUpdate();
            } else {
                ps = Conexion.prepareStatement("update producto set nombre_producto=?,codigo_barras_producto=?,cantidad_stock_producto=?,precio_producto=? ,fecha_vencimiento_producto=? , descripcion_producto = ?, id_laboratorio_producto = ? , id_proveedor_producto = ? where id_producto=?");
                ps.setString(1, producto.getNombre());
                ps.setString(2, producto.getCodigoBarras());
                ps.setInt(3, producto.getCantidadStock());
                ps.setFloat(4, producto.getPrecio());
                ps.setString(5, producto.getFechaVencimiento());
                ps.setString(6, producto.getDescripcion());
                ps.setInt(7, producto.getLaboratorio().getIdLaboratorio());
                ps.setInt(8, producto.getProveedor().getIdProveedor());
                ps.setInt(9, idProducto);
                ps.executeUpdate();
            }
            return true;

        } catch (SQLException ex) {
            System.err.println(" Error al intentar editar Producto : " + ex);
            return false;
        } finally {
            try {
                Conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    public boolean eliminarProducto(int idProducto) {
        Connection Conexion = getConnection();
        try {
            ps = Conexion.prepareStatement("delete from producto where id_producto=?");
            ps.setInt(1, idProducto);
            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.err.println(" Error al intentar eliminar Producto: " + ex);
            return false;
        } finally {
            try {
                Conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    public int ultimoCodigoImagen() {
        Connection conexion = getConnection();
        int ultimoNumero = 0;
        try {
            ps = conexion.prepareStatement("select * from imagen order by codigo_imagen desc limit 1");
            rs = ps.executeQuery();

            if (rs.next()) {
                ultimoNumero = rs.getInt(3);
            }

            return ultimoNumero;

        } catch (SQLException e) {
            System.err.println("Error : " + e);
            return ultimoNumero;
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    public boolean ingresarImagen(FileInputStream archivoEntrada, File archivoImagen, int codigoImagen) {
        Connection conexion = getConnection();

        try {
            ps = conexion.prepareStatement("insert into imagen (imagen,codigo_imagen) values(?,?)");
            ps.setBinaryStream(1, archivoEntrada, (int) archivoImagen.length());
            ps.setInt(2, codigoImagen);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error : " + e);
            return false;
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    public boolean modificar_Imagen(FileInputStream archivoEntrada, File archivoImagen, int id_Imagen) {
        Connection conexion = getConnection();
        try {
            ps = conexion.prepareStatement("update imagen set imagen=? where id_imagen=? ");
            ps.setBinaryStream(1, archivoEntrada, (int) archivoImagen.length());
            ps.setInt(2, id_Imagen);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error : " + e);
            return false;
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    public void llenarDatosImagen(int idProducto, int idImagen) {
        // ingreso el dato faltante a la tabla imagen (idProducto) ya ingresado en la tabla producto.
        Connection conexion = getConnection();
        try {
            ps = conexion.prepareStatement("update imagen set id_producto_imagen=? where id_imagen=?");
            ps.setInt(1, idProducto);
            ps.setInt(2, idImagen);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error : " + e);
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    public BufferedImage ObtenerImagen(int idImagen, JPanel panelimagen) {
        Connection conexion = getConnection();
        try {
            ps = conexion.prepareStatement("select imagen from imagen where id_imagen= ?");
            ps.setInt(1, idImagen);
            rs = ps.executeQuery();

            BufferedImage bFI = null;
            while (rs.next()) {
                InputStream img = rs.getBinaryStream(1);
                bFI = ImageIO.read(img);

                int ancho = panelimagen.getWidth();
                int alto = panelimagen.getHeight();
                ImagenProducto imagen = new ImagenProducto(ancho, alto, bFI);
                panelimagen.removeAll();
                panelimagen.add(imagen);
                panelimagen.repaint();
            }
            return bFI;

        } catch (SQLException e) {
            return null;
        } catch (IOException ex) {
            return null;
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    public int Obtener_Id_Imagen(int codImagen) {
        Connection conexion = getConnection();
        int id_Imagen = 0;
        try {
            ps = conexion.prepareStatement("select id_imagen from imagen where codigo_imagen= ?");
            ps.setInt(1, codImagen);
            rs = ps.executeQuery();

            if (rs.next()) {
                id_Imagen = rs.getInt("id_imagen");
            }
            return id_Imagen;

        } catch (SQLException e) {
            System.err.println("Error : " + e);
            return 0;
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    public int buscarNumero_facturaActual() {

        Connection conexion = getConnection();
        int numeroFactura = 0;

        try {
            ps = conexion.prepareStatement("select MAX(consecutivo_factura) from factura");
            rs = ps.executeQuery();

            if (rs.next()) {
                numeroFactura = rs.getInt(1);
            }
            //System.out.println("numero encontrado en la base de datos : "+numeroFactura);
            return numeroFactura;

        } catch (SQLException ex) {
            return numeroFactura;
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
            }
        }
    }

    public boolean ingresarFactura(Factura factura) {
        Connection conexion = getConnection();

        try {
            ps = conexion.prepareStatement("insert into factura(consecutivo_factura,forma_pago_factura,fecha_emision_factura,iva_factura_venta,subtotal_factura,total_factura,descuentos_factura,id_cliente_factura,id_usuario_factura, estado_factura) values(?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, factura.getConsecutivo());
            ps.setString(2, factura.getFormaPago());
            ps.setString(3, factura.getFechaEmision());
            ps.setFloat(4, factura.getIva());
            ps.setFloat(5, factura.getSubtotal());
            ps.setFloat(6, factura.getTotal());
            ps.setFloat(7, factura.getDescuento());
            ps.setInt(8, factura.getIdCliente());
            ps.setInt(9, factura.getIdUsuario());
            ps.setString(10, "pagado");

            int resultado = ps.executeUpdate();
            if (resultado > 0) {
                return true;
            }
            return false;

        } catch (Exception ex) {
            System.err.println("Error : " + ex);
            return false;
        } finally {
            try {
                conexion.close();
            } catch (Exception e) {
            }
        }
    }

    public boolean editarFactura(Factura factura, String estado, String notasFactura) {
        Connection conexion = getConnection();

        try {
            ps = conexion.prepareStatement("update factura set consecutivo_factura=? , forma_pago_factura=?, fecha_emision_factura=? ,iva_factura_venta=? ,subtotal_factura=? ,total_factura=? ,descuentos_factura=? ,id_cliente_factura=? ,id_usuario_factura=? , estado_factura=?, notas_factura=?  where id_factura=?");
            ps.setString(1, factura.getConsecutivo());
            ps.setString(2, factura.getFormaPago());
            ps.setString(3, factura.getFechaEmision());
            ps.setFloat(4, factura.getIva());
            ps.setFloat(5, factura.getSubtotal());
            ps.setFloat(6, factura.getTotal());
            ps.setFloat(7, factura.getDescuento());
            ps.setInt(8, factura.getIdCliente());
            ps.setInt(9, factura.getIdUsuario());
            ps.setString(10, estado);
            ps.setString(11, notasFactura);
            ps.setInt(12, factura.getIdFactura());

            int resultado = ps.executeUpdate();
            if (resultado > 0) {
                return true;
            }
            return false;
        } catch (SQLException ex) {
            System.err.println("Error : " + ex);
            return false;
        } finally {
            try {
                conexion.close();
            } catch (Exception e) {
            }
        }
    }

    public boolean ingresar_DetalleFactura(int cantidadProducto_unidad, float importe, int idProducto, int idUnidad, int idFac, int porcentajeIVA, int porcentajeDescuento) {
        Connection conexion = getConnection();

        try {
            ps = conexion.prepareStatement("insert into detalle_factura(cantidad_producto_y_unidades_detalle, importe_detalle_factura, id_producto_detalle, id_unidad_detalle, id_factura_detalle, porcentaje_iva, porcentaje_descuento) values(?,?,?,?,?,?,?)");
            ps.setInt(1, cantidadProducto_unidad);
            ps.setFloat(2, importe);
            ps.setInt(3, idProducto);
            ps.setInt(4, idUnidad);
            ps.setInt(5, idFac);
            ps.setInt(6, porcentajeIVA);
            ps.setInt(7, porcentajeDescuento);

            int resultado = ps.executeUpdate();
            if (resultado > 0) {
                return true;
            }
            return false;

        } catch (Exception ex) {
            System.err.println("Error : " + ex);
            return false;
        } finally {
            try {
                conexion.close();
            } catch (Exception e) {
            }
        }
    }

    public boolean eliminarDetalleFactura(int idFactura, int idProducto) {
        Connection Conexion = getConnection();
        try {
            ps = Conexion.prepareStatement("delete from detalle_factura where id_factura_detalle=? AND id_producto_detalle=?");
            ps.setInt(1, idFactura);
            ps.setInt(2, idProducto);
            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.err.println(" Error al intentar eliminar detalle: " + ex);
            return false;
        } finally {
            try {
                Conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    public boolean editar_DetalleFactura(int cantidadProducto_unidad, float importe, int porcentajeIVA, int porcentajeDescuento, int idProducto, int idUnidad, int idFac, int idDetalleFactura) {
        Connection conexion = getConnection();

        try {
            ps = conexion.prepareStatement("update detalle_factura set cantidad_producto_y_unidades_detalle=?, importe_detalle_factura=?, porcentaje_iva=?, porcentaje_descuento=?, id_producto_detalle=?, id_unidad_detalle=?, id_factura_detalle=? where id_detalle_factura=?");
            ps.setInt(1, cantidadProducto_unidad);
            ps.setFloat(2, importe);
            ps.setInt(3, porcentajeIVA);
            ps.setInt(4, porcentajeDescuento);
            ps.setInt(5, idProducto);
            ps.setInt(6, idUnidad);
            ps.setInt(7, idFac);
            ps.setInt(8, idDetalleFactura);

            int resultado = ps.executeUpdate();
            if (resultado > 0) {
                return true;
            }
            return false;

        } catch (Exception ex) {
            System.err.println("Error : " + ex);
            return false;
        } finally {
            try {
                conexion.close();
            } catch (Exception e) {
            }
        }
    }

    public DetalleFactura buscarDetalleFactura(int idFac) {
        Connection conexion = getConnection();
        DetalleFactura dFac = new DetalleFactura();

        try {
            ps = conexion.prepareStatement("select * from detalle_factura where id_factura_detalle=? ");
            ps.setInt(1, idFac);

            rs = ps.executeQuery();

            if (rs.next()) {
                dFac.setId_detalle_factura(rs.getInt("id_detalle_factura"));
                dFac.setCantidad_producto_y_unidades_detalle(rs.getInt("cantidad_producto_y_unidades_detalle"));
                dFac.setImporte_detalle_factura(rs.getFloat("importe_detalle_factura"));
                dFac.setId_producto_detalle(rs.getInt("id_producto_detalle"));
                dFac.setId_unidad_detalle(rs.getInt("id_unidad_detalle"));
                dFac.setId_factura_detalle(rs.getInt("id_factura_detalle"));
                dFac.setPorcentaje_IVA(rs.getInt("porcentaje_iva"));
                dFac.setPorcentaje_Descuento(rs.getInt("porcentaje_descuento"));
                return dFac;
            }
            return null;
        } catch (Exception ex) {
            return null;
        } finally {
            try {
                conexion.close();
            } catch (Exception exc) {
            }
        }
    }

    public int obtenerCantidadFilasDetalleFacturaBuscada(int idFac) {
        Connection conexion = getConnection();
        DetalleFactura dFac = new DetalleFactura();
        int resultado = 0;
        try {
            ps = conexion.prepareStatement("select count(id_detalle_factura) from detalle_factura where id_factura_detalle=? ");
            ps.setInt(1, idFac);

            rs = ps.executeQuery();

            if (rs.next()) {
                resultado = rs.getInt(1);
            }

            return resultado;

        } catch (Exception ex) {
            return resultado;
        } finally {
            try {
                conexion.close();
            } catch (Exception exc) {
            }
        }
    }

    public DetalleFactura buscarDetalleFacturaXid(int idDetalleFactura) {
        Connection conexion = getConnection();
        DetalleFactura dFac = new DetalleFactura();

        try {
            ps = conexion.prepareStatement("select * from detalle_factura where id_detalle_factura=? ");
            ps.setInt(1, idDetalleFactura);

            rs = ps.executeQuery();

            if (rs.next()) {
                dFac.setId_detalle_factura(rs.getInt("id_detalle_factura"));
                dFac.setCantidad_producto_y_unidades_detalle(rs.getInt("cantidad_producto_y_unidades_detalle"));
                dFac.setImporte_detalle_factura(rs.getFloat("importe_detalle_factura"));
                dFac.setId_producto_detalle(rs.getInt("id_producto_detalle"));
                dFac.setId_unidad_detalle(rs.getInt("id_unidad_detalle"));
                dFac.setId_factura_detalle(rs.getInt("id_factura_detalle"));
                dFac.setPorcentaje_IVA(rs.getInt("porcentaje_iva"));
                dFac.setPorcentaje_Descuento(rs.getInt("porcentaje_descuento"));
                return dFac;
            }

            return null;

        } catch (SQLException ex) {
            return null;
        } finally {
            try {
                conexion.close();
            } catch (Exception exc) {
            }
        }
    }

    public DetalleFactura BuscarDetalleFactura(int idFactura, int idProducto) {
        Connection conexion = getConnection();
        DetalleFactura dFac = new DetalleFactura();

        try {
            ps = conexion.prepareStatement("select * from detalle_factura where id_factura_detalle=? AND id_producto_detalle=? ");
            ps.setInt(1, idFactura);
            ps.setInt(2, idProducto);

            rs = ps.executeQuery();

            if (rs.next()) {
                dFac.setId_detalle_factura(rs.getInt("id_detalle_factura"));
                dFac.setCantidad_producto_y_unidades_detalle(rs.getInt("cantidad_producto_y_unidades_detalle"));
                dFac.setImporte_detalle_factura(rs.getFloat("importe_detalle_factura"));
                dFac.setId_producto_detalle(rs.getInt("id_producto_detalle"));
                dFac.setId_unidad_detalle(rs.getInt("id_unidad_detalle"));
                dFac.setId_factura_detalle(rs.getInt("id_factura_detalle"));
                dFac.setPorcentaje_IVA(rs.getInt("porcentaje_iva"));
                dFac.setPorcentaje_Descuento(rs.getInt("porcentaje_descuento"));
                return dFac;
            }
            return null;
        } catch (SQLException ex) {
            System.err.println(" Error al intentar Buscar detalle Factura: " + ex);
            return null;
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                System.err.println("Error : " + ex);
            }
        }
    }

    public Factura buscarFactura(int consecutivoFac) {
        Connection conexion = getConnection();
        Factura fac = new Factura();

        try {
            ps = conexion.prepareStatement("select * from factura where consecutivo_factura=? ");
            ps.setInt(1, consecutivoFac);

            rs = ps.executeQuery();

            if (rs.next()) {
                fac.setIdFactura(rs.getInt("id_factura"));
                fac.setConsecutivo(String.valueOf(rs.getInt("consecutivo_factura")));
                fac.setFormaPago(rs.getString("forma_pago_factura"));
                fac.setFechaEmision(String.valueOf(rs.getDate("fecha_emision_factura")));
                fac.setIva(rs.getFloat("iva_factura_venta"));
                fac.setSubtotal(rs.getFloat("subtotal_factura"));
                fac.setTotal(rs.getFloat("total_factura"));
                fac.setDescuento(rs.getFloat("descuentos_factura"));
                fac.setIdCliente(rs.getInt("id_cliente_factura"));
                fac.setIdUsuario(rs.getInt("id_usuario_factura"));
                fac.setEstadoFactura(rs.getString("estado_factura"));
                fac.setNotasFactura(rs.getString("notas_factura"));

                return fac;
            }
            return null;
        } catch (SQLException ex) {
            return null;
        } finally {
            try {
                conexion.close();
            } catch (SQLException exc) {
            }
        }
    }

    public void cierreDeCaja(String ruta, String fecha, int idUsuario) {
        // recibo el parametro 
        Connection conexion = getConnection();
        JasperReport reporte = null;
        try {
            reporte = (JasperReport) JRLoader.loadObjectFromFile(ruta);
        } catch (JRException ex) {
            Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
        }

        Map parametro = new HashMap(); // almaceno el parametro para enviarlo
        parametro.put("parametroFecha", fecha + "%");
        if (idUsuario > 0) {
            parametro.put("parametroIdUsuario", idUsuario);
        }

        try {
            JasperPrint imprimirReporte = JasperFillManager.fillReport(reporte, parametro, conexion); // lo envio al reporte
            JasperViewer vistaReporte = new JasperViewer(imprimirReporte, false);
            vistaReporte.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            vistaReporte.setVisible(true);

        } catch (JRException ex) {
            Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public float tatalVendidoEnDia(String fecha, int idUsuario) {
        Connection conexion = getConnection();
        String like = fecha + "%";
        float totalVendidoDia = 0;
        try {
            ps = conexion.prepareStatement("select SUM(total_factura) from factura where fecha_emision_factura LIKE ? AND id_usuario_factura=?");
            ps.setString(1, like);
            ps.setInt(2, idUsuario);
            rs = ps.executeQuery();

            if (rs.next()) {
                totalVendidoDia = rs.getFloat(1);
            }
            return totalVendidoDia;
        } catch (SQLException ex) {
            return totalVendidoDia;
        }

    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    //Reportes
    public void GeneralDeVentas(String ruta, String fechaInicio, String fechaFin, int idUsuario) {
        // recibo el parametro 
        Connection conexion = getConnection();
        JasperReport reporte = null;
        try {
            reporte = (JasperReport) JRLoader.loadObjectFromFile(ruta);
        } catch (JRException ex) {
            Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
        }

        Map parametro = new HashMap(); // almaceno el parametro para enviarlo
        parametro.put("parametroFechaInicio", fechaInicio + " 00:00:00");
        parametro.put("parameterFechaFin", fechaFin + " 23:59:59");

        if (idUsuario > 0) {
            parametro.put("parametroIdUsuario", idUsuario);
        }

        try {
            JasperPrint imprimirReporte = JasperFillManager.fillReport(reporte, parametro, conexion); // lo envio al reporte
            JasperViewer vistaReporte = new JasperViewer(imprimirReporte, false);
            vistaReporte.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            vistaReporte.setVisible(true);

        } catch (JRException ex) {
            Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
