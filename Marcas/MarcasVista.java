package Polizas.Marcas;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

// Pantalla de marcas
public class MarcasVista extends JPanel {

    // Atributos
    protected JButton btn1, btn_a, btn_u, btn_e; // Consultar, Agregar, Actualizar, Eliminar
    protected JTextField txt1;
    protected JLabel lbl1, lbl2;

    protected JTable tabla;
    protected MarcasTM marcasModelo;
    protected String[] columnas = {"Id", "Nombre"};

    protected JComboBox<Marca> cmbMarcas;

    // Constructor: crea los componentes
    public MarcasVista() {
        setLayout(null); // posiciones a mano con setBounds(x, y, ancho, alto)

        this.lbl1 = new JLabel("Welcome to Marcas Admin.");
        lbl1.setBounds(350, 5, 150, 30);

        lbl2 = new JLabel("Ingresa tu nombre: ");
        lbl2.setBounds(50, 40, 150, 30);

        this.txt1 = new JTextField("Marca 1");
        this.txt1.setBounds(170, 40, 250, 30);

        cmbMarcas = new JComboBox<>();
        cmbMarcas.setBounds(500, 40, 250, 30);
        add(cmbMarcas);

        this.btn1 = new JButton("Consultar");
        this.btn1.setBounds(150, 90, 100, 30);

        this.btn_a = new JButton("Agregar");
        this.btn_a.setBounds(250, 90, 100, 30);
        this.add(btn_a);

        this.btn_u = new JButton("Actualizar");
        this.btn_u.setBounds(350, 90, 110, 30);
        this.add(btn_u);

        this.btn_e = new JButton("Eliminar");
        this.btn_e.setBounds(460, 90, 100, 30);
        this.add(btn_e);

        // Tabla conectada a su modelo
        marcasModelo = new MarcasTM();
        tabla = new JTable(marcasModelo);

        marcasModelo.fireTableDataChanged();
        JScrollPane sc = new JScrollPane(tabla);
        sc.setBounds(200, 150, 800, 500);

        this.add(sc, BorderLayout.CENTER);

        this.add(lbl1);
        this.add(lbl2);
        this.add(txt1);
        this.add(btn1);
    }

    // Método: refresca tabla y combo. Parámetro lista: marcas a mostrar
    public void actualizarVista(List<Marca> lista) {
        marcasModelo.vaciarLista();
        marcasModelo.llenar(lista);
        marcasModelo.fireTableDataChanged(); // después de llenar, si no la tabla queda vacía

        cmbMarcas.removeAllItems();
        for (Marca m : lista) {
            cmbMarcas.addItem(m);
        }
    }
}
