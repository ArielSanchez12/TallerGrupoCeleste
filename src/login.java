import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class login extends JFrame{
    private JButton ingresarButton;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPanel panelLogin;

    public login(){
        setTitle("Login");
        setSize(500, 500);
        setContentPane(panelLogin);
        setVisible(true);

        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre_usuario = textField1.getText();
                String password = new String(passwordField1.getPassword());

                if (validarUsuario(nombre_usuario, password)) {
                    JOptionPane.showMessageDialog(null, "Ingresaste con exito");
                } else {
                    JOptionPane.showMessageDialog(null, "Credenciales incorrectas");
                }
            }
        });
    }
    private boolean validarUsuario(String nombre_usuario, String password){
        try(Connection con = conexion.connect()){
            String query ="SELECT * FROM usuarios WHERE nombre_usuario=? AND password=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,nombre_usuario);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"No se pudo conectar a la base de datos");
            return false;
        }
    }

}
