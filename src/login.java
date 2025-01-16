import javax.swing.*;

public class login extends JFrame{
    private JButton ingresarButton;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPanel panelLogin;

    public login(){
        setTitle("Login");
        setSize(500,500);
        setContentPane(panelLogin);
        setVisible(true);
    }
}
