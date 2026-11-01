package Polizas.Marcas;

import java.awt.event.MouseAdapter;
import java.util.List;
import javax.swing.JOptionPane;

// Controlador de marcas: une la vista con el modelo
public class MarcasControl {

    // Atributos
    private MarcasVista mv;
    private MarcasModelo mm;
    private List<Marca> lista;

    // Constructor. Parámetro marcasVista: la pantalla a controlar
    public MarcasControl(MarcasVista marcasVista) {
        this.mv = marcasVista;
        this.mm = new MarcasModelo();

        marcasEventos();
        mv.actualizarVista(mm.read());   // carga inicial de la tabla
    }

    // Método: asigna la acción de cada botón
    public void marcasEventos() {

        // Botón Consultar
        mv.btn1.addActionListener(e -> {
            lista = mm.read();
            mv.actualizarVista(lista);
        });

        // Botón Agregar
        mv.btn_a.addActionListener(e -> {
            String nombre = mv.txt1.getText().trim();
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Escribe el nombre de la marca");
                return;
            }
            Object marca = new Marca(0, nombre); // id 0: la base asigna el real
            int resultado = mm.create(marca);
            JOptionPane.showMessageDialog(null,
                    resultado == 1 ? "Marca registrada" : "No se pudo registrar");
            mv.actualizarVista(mm.read());
        });

        // Botón Actualizar: cambia el nombre de la marca seleccionada
        mv.btn_u.addActionListener(e -> {
            int fila = mv.tabla.getSelectedRow(); // -1 si no hay selección
            if (fila < 0) {
                JOptionPane.showMessageDialog(null, "Selecciona una marca de la tabla");
                return;
            }
            String nombre = mv.txt1.getText().trim();
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Escribe el nuevo nombre de la marca");
                return;
            }
            Marca seleccionada = mv.marcasModelo.getMarca(fila);
            Object marca = new Marca(seleccionada.getId(), nombre);
            int resultado = mm.update(marca);
            JOptionPane.showMessageDialog(null,
                    resultado == 1 ? "Marca actualizada" : "No se pudo actualizar");
            mv.actualizarVista(mm.read());
        });

        // Botón Eliminar: borra la marca seleccionada (pide confirmación)
        mv.btn_e.addActionListener(e -> {
            int fila = mv.tabla.getSelectedRow();
            if (fila < 0) {
                JOptionPane.showMessageDialog(null, "Selecciona una marca de la tabla");
                return;
            }
            Marca seleccionada = mv.marcasModelo.getMarca(fila);
            int opcion = JOptionPane.showConfirmDialog(null,
                    "¿Eliminar la marca \"" + seleccionada.getNombre() + "\"?",
                    "Confirmar", JOptionPane.YES_NO_OPTION);
            if (opcion != JOptionPane.YES_OPTION) {
                return;
            }
            int resultado = mm.delete(seleccionada);
            JOptionPane.showMessageDialog(null,
                    resultado == 1 ? "Marca eliminada" : "No se pudo eliminar");
            mv.actualizarVista(mm.read());
        });

        // Clic en la tabla: pasa el nombre al campo de texto
        mv.tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int fila = mv.tabla.getSelectedRow();
                if (fila >= 0) {
                    System.out.println("Registro: " + mv.marcasModelo.getValueAt(fila, 1));
                    mv.txt1.setText((String) mv.marcasModelo.getValueAt(fila, 1));
                }
            }
        });

        // Selección en la tabla: imprime la fila en consola
        mv.tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int fila = mv.tabla.getSelectedRow();
                if (fila >= 0) {
                    System.out.println("Registro: " + fila + " Valor: " + mv.tabla.getValueAt(fila, 1));
                }
            }
        });

        // Combo de marcas
        mv.cmbMarcas.addActionListener(e -> {
            Marca marca = (Marca) mv.cmbMarcas.getSelectedItem();
        });
    }
}
