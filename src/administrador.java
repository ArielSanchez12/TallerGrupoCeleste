import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class administrador {
    private JPanel ventana_administrador;
    private JTabbedPane pestañas;
    private JTextField producto1;
    private JTextField producto2;
    private JTextField cantidad1;
    private JTextField cantidad2;
    private JTextField nuevo_usuario;
    private JTextField pass_new_user;
    private JTextArea textArea1;
    private JButton view_productos;
    private JButton view_users;
    private JTextArea textArea2;
    private JButton add_productos;
    private JButton add_user;
    private JPanel agregar_usuario1;


    public administrador() {
        add_productos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregar_productos();
            }
        });

        add_user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregar_usuario();
            }
        });
    }

    public void agregar_productos(){
        String query = "INSERT INTO productos (nombre, stock) VALUES (?, ?)";

        try (Connection connect = Conexion.connect();
             PreparedStatement statement = connect.prepareStatement(query)) {
            statement.setString(1, nombreProducto);
            statement.setInt(2, stock);
            statement.executeUpdate();

            JOptionPane.showMessageDialog(this, "Producto agregado exitosamente.");
            loadProductsAndUsers();  // Recargar la lista de productos
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al agregar producto.");
        }

    }

    public void agregar_usuario(){


    }




}
