import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class usuarios extends conexion {
    public JPanel ventana_usuario;
    public JTabbedPane tabbedPane1;
    public JTable table2;
    public JButton productosConStockMenorButton;
    public JTable table1;
    public JButton verTodosLosProductosButton;
    public JButton salirButton;

    public usuarios() {
        verTodosLosProductosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection conn = connect()) {
                    String sql = "SELECT nombre, descripcion, precio FROM productos";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    ResultSet resultSet = pstmt.executeQuery();

                    String[] columnas = {"Nombre", "Descripción", "Precio"};
                    DefaultTableModel modelo1 = new DefaultTableModel(columnas, 0) {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    };

                    boolean hayResultados = false;
                    while (resultSet.next()) {
                        hayResultados = true;
                        String nombre = resultSet.getString("nombre");
                        String descripcion = resultSet.getString("descripcion");
                        double precio = resultSet.getDouble("precio");

                        // Agregar los datos al modelo
                        Object[] fila = {nombre, descripcion, precio};
                        modelo1.addRow(fila);
                    }

                    if (!hayResultados) {
                        JOptionPane.showMessageDialog(null, "No existen productos registrados");
                    }

                    // Asignar el modelo a la tabla
                    table1.setModel(modelo1);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error en clever cloud");
                }
            }
        });
        productosConStockMenorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection conn = connect()) {
                    String sql = "SELECT nombre, descripcion, precio, stock FROM productos WHERE stock <= 20";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    ResultSet resultSet = pstmt.executeQuery();

                    // Configurar el modelo de la tabla
                    String[] columnas = {"Nombre", "Descripción", "Precio", "Stock"};
                    DefaultTableModel modelo2 = new DefaultTableModel(columnas, 0) {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    };

                    // Procesar los resultados del ResultSet
                    boolean hayResultados2 = false;
                    while (resultSet.next()) {
                        hayResultados2 = true;
                        String nombre = resultSet.getString("nombre");
                        String descripcion = resultSet.getString("descripcion");
                        double precio = resultSet.getDouble("precio");
                        int stock = resultSet.getInt("stock");

                        // Agregar los datos al modelo
                        Object[] fila = {nombre, descripcion, precio, stock};
                        modelo2.addRow(fila);
                    }

                    // Mostrar mensaje si no hay productos con stock <= 20
                    if (!hayResultados2) {
                        JOptionPane.showMessageDialog(null, "No existen productos con stock <= 20");
                    }

                    // Asignar el modelo a la tabla
                    table2.setModel(modelo2);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error en clever cloud");
                }
            }
        });
    }
}
