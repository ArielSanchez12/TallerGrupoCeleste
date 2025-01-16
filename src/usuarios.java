import javax.swing.*;

public class usuarios extends JFrame {
    private JTabbedPane tabMenor;
    private JPanel panel1;
    private JPanel tabTodo;
    private JList listNombre20;
    private JList listaStock20;
    private JButton verProductosButton;
    private JList listID;
    private JList listNombre;
    private JList listDes;
    private JList listPrecio;
    private JList Stock;
    private JButton actualizarButton;

    public usuarios(){
        setTitle("Usuarios");
        setSize(500,500);
        setContentPane(panel1);
        setVisible(true);
    }
}
