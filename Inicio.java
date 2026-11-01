package Polizas;

import Polizas.General.Controlador;
import Polizas.General.Bienvenida;
import javax.swing.SwingUtilities;

// Clase principal: arranca el sistemaq
public class Inicio {

    // Atributos
    private Controlador controlador;
    private Bienvenida vista;

    // Constructor: crea la vista y le conecta su controlador
    public Inicio() {
        vista = new Bienvenida();
        controlador = new Controlador(vista);
    }

    // Método main. Parámetro args: argumentos de consola (no se usan)
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Inicio::new); // crea la ventana en el hilo de Swing
    }
}
