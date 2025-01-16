import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class login extends JFrame{
    private JButton btnIngreso;
    private JTextField txtUser;
    private JPasswordField txtPassword;
    private JPanel panelLogin;
    private JComboBox comboBox1;

    public login(){
        setTitle("Login");
        setSize(500,500);
        setContentPane(panelLogin);
        setVisible(true);

        // Configuración básica del formulario
        setTitle("Inicio de Sesión");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Etiquetas y campos de texto
        JLabel lblUsername = new JLabel("Usuario:");
        lblUsername.setBounds(20, 30, 80, 25);
        add(lblUsername);

        txtUser = new JTextField();
        txtUser.setBounds(100, 30, 150, 25);
        add(txtUser);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(20, 70, 80, 25);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(100, 70, 150, 25);
        add(txtPassword);

        // Botón de iniciar sesión
        btnIngreso = new JButton("Iniciar Sesión");
        btnIngreso.setBounds(100, 110, 150, 25);
        add(btnIngreso);
        btnIngreso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //authenticate(); // Llama al método para validar las credenciales
            }
        });

        //private void authenticate() {
            String user = txtUser.getText(); // Obtiene el texto ingresado
            String password = new String(txtPassword.getPassword());

            /*try (Connection conn = conexion.getConnection()) { //Conecta a la base de datos
                String query = "SELECT * FROM usuarios WHERE nombre_usuario = ? AND password = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, user);
                stmt.setString(2, password);

                ResultSet rs = stmt.executeQuery(); // Ejecuta la consulta
                if (rs.next()) { // Si encuentra un registro, inicia sesión
                    new administrador(user).setVisible(true); //Abre el formulario principal
                    this.dispose(); //Cierra el formulario actual
                } else {
                    JOptionPane.showMessageDialog(this, "Credenciales incorrectas.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error en la conexión a la base de datos.");
            }
        }*/
    }
}
