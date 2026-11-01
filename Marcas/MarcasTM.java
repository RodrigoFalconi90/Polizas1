package Polizas.Marcas;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

// Modelo de la tabla: le da los datos al JTable
public class MarcasTM extends AbstractTableModel {

    // Atributos
    protected List<Marca> marcasLista;
    protected String[] encabezados = {"id", "nombre"};

    // Constructor
    public MarcasTM() {
        marcasLista = new ArrayList<>();
        this.setColumnNames(encabezados);
    }

    // Método: título de la columna. Parámetro cantidad: número de columna
    @Override
    public String getColumnName(int cantidad) {
        return encabezados[cantidad];
    }

    // Método: cambia los títulos. Parámetro nuevosNombres: títulos nuevos
    public void setColumnNames(String[] nuevosNombres) {
        if (nuevosNombres.length == encabezados.length) {
            this.encabezados = nuevosNombres;
            fireTableStructureChanged();
        } else {
            throw new IllegalArgumentException("Valida los campos");
        }
    }

    // Método: vacía la lista
    public void vaciarLista() {
        marcasLista.clear();
    }

    // Método: número de filas
    @Override
    public int getRowCount() {
        return marcasLista.size();
    }

    // Método: número de columnas
    @Override
    public int getColumnCount() {
        return encabezados.length;
    }

    // Método: valor de una celda. Parámetros: fila y columna
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex != -1) {
            Marca m = marcasLista.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return m.getId();
                case 1:
                    return m.getNombre();
                default:
                    return null;
            }
        }
        return null;
    }

    // Método: agrega una fila. Parámetro m: la marca
    public void addMarca(Marca m) {
        marcasLista.add(m);
        fireTableRowsInserted(marcasLista.size() - 1, marcasLista.size() - 1);
    }

    // Método: reemplaza una fila. Parámetros: fila y marca nueva
    public void updateMarca(int fila, Marca nueva) {
        marcasLista.set(fila, nueva);
        fireTableRowsUpdated(fila, fila);
    }

    // Método: regresa la marca de una fila. Parámetro fila: número de fila
    public Marca getMarca(int fila) {
        return marcasLista.get(fila);
    }

    // Método: quita una fila. Parámetro fila: número de fila
    public void removeMarca(int fila) {
        marcasLista.remove(fila);
        fireTableRowsDeleted(fila, fila);
    }

    // Método: reemplaza los datos. Parámetro lista: marcas de la base
    void llenar(List<Marca> lista) {
        this.marcasLista = lista;
    }
}
