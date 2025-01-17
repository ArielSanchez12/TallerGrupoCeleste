import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class administrador extends conexion{
    public JPanel ventana_administrador;
    public JTabbedPane tabbedPane1;
    public JTextField nombreProducto;
    public JTextField textField2;
    public JTextField textField3;
    public JTextField textField4;
    public JButton agregarProductosButton;
    public JTextField textField5;
    public JTextField textField7;
    public JPasswordField passwordField1;
    public JButton agregarUsuario;
    public JTable table1;
    public JTable table2;
    public JButton mostrarListaDeProductosButton;
    public JButton mostrarListaDeUsuariosButton;
    public JPanel Pproductos;
    private JButton salirButton;
    private JButton salirButton1;
    private JButton salirButton2;
    private JButton salirButton3;

    public administrador() {
        agregarProductosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nombreProductoText = nombreProducto.getText();
                String descripcionText = textField2.getText();
                String precioText = textField3.getText();
                String stockText = textField4.getText();

                if (nombreProductoText.isEmpty() || descripcionText.isEmpty() || precioText.isEmpty() || stockText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos");
                    return;
                }

                double precioDecimal = Double.parseDouble(textField3.getText());
                int stockEntero = Integer.parseInt(textField4.getText());

                if (stockEntero < 10 || stockEntero > 350) {
                    JOptionPane.showMessageDialog(null, "El stock minimo es 10 y el maximo es 350");
                    return;
                }

                try (Connection conn = connect()) {
                    String sql = "INSERT INTO productos (nombre, descripcion, precio, stock) VALUES (?, ?, ?, ?)";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, nombreProductoText);
                    pstmt.setString(2, descripcionText);
                    pstmt.setDouble(3, precioDecimal);
                    pstmt.setInt(4, stockEntero);
                    pstmt.execute();
                    JOptionPane.showMessageDialog(null, "Registro de productos exitoso");
                    nombreProducto.setText("");
                    textField2.setText("");
                    textField3.setText("");
                    textField4.setText("");

                } catch (SQLException exception) {
                    throw new RuntimeException(exception);
                }
            }
        });
        agregarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreUser = textField5.getText();
                String contra = passwordField1.getText();
                String rolUser = textField7.getText();

                if (nombreUser.isEmpty() || contra.isEmpty() || rolUser.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos");
                    return;
                }

                try (Connection conn = connect()) {
                    String sql = "INSERT INTO usuarios (nombre_usuario, password, rol) VALUES (?, ?, ?)";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, nombreUser);
                    pstmt.setString(2, contra);
                    pstmt.setString(3, rolUser);
                    pstmt.execute();
                    JOptionPane.showMessageDialog(null, "Registro de usuario exitoso");
                    textField5.setText("");
                    passwordField1.setText("");
                    textField7.setText("");

                } catch (SQLException exception) {
                    throw new RuntimeException(exception);
                }
            }
        });

        mostrarListaDeProductosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection conn = connect()) {
                    String sql = "SELECT id_producto, nombre, descripcion, precio, stock FROM productos";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    ResultSet resultSet = pstmt.executeQuery();

                    // Configurar el modelo de la tabla
                    String[] columnas = {"ID Producto", "Nombre", "Descripción", "Precio", "Stock"};
                    DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    };

                    // Procesar los resultados del ResultSet
                    boolean hayResultados = false;
                    while (resultSet.next()) {
                        hayResultados = true;
                        int idProducto = resultSet.getInt("id_producto");
                        String nombre = resultSet.getString("nombre");
                        String descripcion = resultSet.getString("descripcion");
                        double precio = resultSet.getDouble("precio");
                        int stock = resultSet.getInt("stock");

                        // Agregar los datos al modelo
                        Object[] fila = {idProducto, nombre, descripcion, precio, stock};
                        modelo.addRow(fila);
                    }

                    if (!hayResultados) {
                        JOptionPane.showMessageDialog(null, "No existen productos registrados");
                    }

                    // Asignar el modelo a la tabla
                    table1.setModel(modelo);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error en clever cloud");
                }
            }
        });

        mostrarListaDeUsuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection conn = connect()) {
                    String sql = "SELECT id_usuario, nombre_usuario, password, rol FROM usuarios";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    ResultSet resultSet = pstmt.executeQuery();

                    String[] columnas = {"ID Usuario", "Nombre Usuario", "Contraseña", "Rol"};
                    DefaultTableModel modelo2 = new DefaultTableModel(columnas, 0) {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            return false; // Hacer las celdas no editables
                        }
                    };

                    boolean hayResultados2 = false;
                    while (resultSet.next()) {
                        hayResultados2 = true;
                        int idUser = resultSet.getInt("id_usuario");
                        String nombreUser = resultSet.getString("nombre_usuario");
                        String contraUser = resultSet.getString("password");
                        String rol = resultSet.getString("rol");


                        Object[] fila = {idUser, nombreUser, contraUser, rol};
                        modelo2.addRow(fila);
                    }

                    if (!hayResultados2) {
                        JOptionPane.showMessageDialog(null, "No existen usuarios registrados");
                    }

                    table2.setModel(modelo2);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error en clever cloud");
                }
            }
        });
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(salirButton);
                if (frame != null) {
                    frame.dispose();
                }
                System.exit(0);
            }
        });
        salirButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(salirButton);
                if (frame != null) {
                    frame.dispose();
                }
                System.exit(0);
            }
        });
        salirButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(salirButton);
                if (frame != null) {
                    frame.dispose();
                }
                System.exit(0);
            }
        });
        salirButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(salirButton);
                if (frame != null) {
                    frame.dispose();
                }
                System.exit(0);
            }
        });
    }
}
