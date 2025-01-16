import javax.swing.*;

public class administrador extends JFrame{
    private JPanel ventana_administrador;
    private JTabbedPane pestañas;
    private JTextField producto1;
    private JTextField producto2;
    private JTextField cantidad1;
    private JTextField cantidad2;
    private JTextField nuevo_usuario;
    private JTextField pass_new_user;
    private JTextArea textArea1;
    private JButton mostrarButton;
    private JButton mostrar_usuariosButton;
    private JTextArea textArea2;

    public administrador(){
        setTitle("adminstrador");
        setSize(500,500);
        setContentPane(ventana_administrador);
        setVisible(true);
    }
}
