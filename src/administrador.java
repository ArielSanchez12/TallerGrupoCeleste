import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.function.IntToDoubleFunction;

public class administrador extends Component {
    private JPanel ventana_administrador;
    private JTabbedPane pestañas;
    private JTextField producto1;
    private JTextField cantidad1;
    private JTextField nuevo_usuario;
    private JTextField pass_new_user;
    private JTextArea textArea1;
    private JButton view_productos;
    private JButton view_users;
    private JTextArea textArea2;
    private JButton add_productos;
    private JButton add_user;
    private JPanel agregar_usuario1;
    private JTextField descripcion;
    private JTextField precio;
    private JTextField new_rol;

    public static void main(String[] args) {
        JFrame frame = new JFrame("administrador");
        frame.setContentPane(new administrador().ventana_administrador);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

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
        view_productos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        view_users.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void agregar_productos(){
        String nombreProducto = producto1.getText();  // Obtener nombre del producto
        String descripcionProducto = descripcion.getText();  // Obtener descripción del producto
        String precioProducto = precio.getText();
        int cantidadProducto = Integer.parseInt(cantidad1.getText());  // Convertir cantidad a entero



        String query = "INSERT INTO productos (nombre, descripcion, precio, stock) VALUES (?, ?, ?, ?)";

        try (Connection connect = conexion.connect();  // Asegúrate de tener la clase Conexion correctamente configurada
             PreparedStatement statement = connect.prepareStatement(query)) {

            // Asignar los valores a los parámetros
            statement.setString(1, nombreProducto);  // Nombre del producto
            statement.setString(2, descripcionProducto);  // Descripción del producto
            statement.setInt(3, cantidadProducto);
            statement.setString(4, precioProducto);  // Precio del producto
              // Stock (Cantidad)

            // Ejecutar la consulta
            statement.executeUpdate();

            // Mostrar mensaje de éxito y recargar la lista de productos
            JOptionPane.showMessageDialog(null, "Producto agregado exitosamente.");
              // Recargar la lista de productos

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al agregar producto.");
        }

    }

    public void agregar_usuario(){
        String nombreUsuario = nuevo_usuario.getText();  // Obtener nombre de usuario
        String passUsuario = pass_new_user.getText();  // Obtener contraseña de usuario
        String nuevoRol = new_rol.getText(); // Obtener nuevo rol

        // Verificar si los campos no están vacíos
        if (nombreUsuario.isEmpty() || passUsuario.isEmpty() || nuevoRol.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos.");
            return;  // Salir si algún campo está vacío
        }

        // Consulta SQL para insertar un usuario con rol
        String query = "INSERT INTO usuarios (nombre, password, rol) VALUES (?, ?, ?)";

        try (Connection connect = conexion.connect();  // Asegúrate de tener la clase Conexion correctamente configurada
             PreparedStatement statement = connect.prepareStatement(query)) {

            // Asignar los valores a los parámetros
            statement.setString(1, nombreUsuario);  // Nombre del usuario
            statement.setString(2, passUsuario);  // Contraseña del usuario
            statement.setString(3, nuevoRol);  // Rol del usuario

            // Ejecutar la consulta
            statement.executeUpdate();

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this, "Usuario agregado exitosamente.");

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al agregar usuario.");
        }

    }

    public void lista_productos(){



    }

    public void lista_usuarios(){

    }




}
