package Polizas.General;

import Polizas.Marcas.MarcasVista;
import Polizas.Marcas.MarcasControl;
import javax.swing.JLabel;

// Controlador del menú principal
public class Controlador {

    // Atributos
    protected Bienvenida vista;
    protected MarcasVista marcasVista;

    // Constructor. Parámetro vista: la ventana principal
    public Controlador(Bienvenida vista) {
        this.vista = vista;
        this.eventos();
    }

    // Método: asigna la acción de cada opción del menú
    public void eventos() {
        vista.mensaje.addActionListener(
                e -> vista.cl.show(vista.pc, "mensaje")
        );

        vista.home.addActionListener(e -> {
            vista.pc.add(new JLabel("Panel Home"), "home");
            vista.cl.show(vista.pc, "home");
        });

        // Marcas: crea la vista, su controlador y la muestra en el centro
        vista.mMarcas.addActionListener(e -> {
            marcasVista = new MarcasVista();
            new MarcasControl(marcasVista);
            vista.pc.add(marcasVista, "mk");
            vista.cl.show(vista.pc, "mk");
        });
    }
}
