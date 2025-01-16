import javax.swing.*;

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
    }
}
