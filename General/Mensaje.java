package Polizas.General;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

// Panel del menú Archivo → Mensaje
public class Mensaje extends JPanel {

    // Constructor
    Mensaje() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 30));
        add(
            new JLabel(
                "Actualización mensajes actualizados ...",
                SwingConstants.CENTER)
        );
    }
}
