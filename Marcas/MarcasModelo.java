package Polizas.Marcas;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import Polizas.General.Conexion;
import Polizas.General.DAO;

// Modelo: CRUD de la tabla marca (hereda Conexion, implementa DAO)
public class MarcasModelo extends Conexion implements DAO {

    // Constructor
    MarcasModelo() {
        super();
    }

    // Método: consulta todas las marcas
    @Override
    public List read() {
        List<Marca> lista = new ArrayList<>();
        try {
            this.conectar();
            if (cnx == null) {
                return lista;
            }
            ps = cnx.prepareStatement("SELECT idMarca, nombreMarca FROM marca");
            rs = ps.executeQuery();
            lista.clear();

            while (rs.next()) { // recorre fila por fila
                lista.add(new Marca(rs.getInt("idMarca"), rs.getString("nombreMarca")));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            this.close(); // siempre cierra la conexión
        }
        return lista;
    }

    // Método: inserta una marca. Parámetro obj: la marca
    @Override
    public int create(Object obj) {
        try {
            this.conectar();
            if (cnx == null) {
                return 0;
            }
            Marca marca = (Marca) obj;
            ps = cnx.prepareStatement("INSERT INTO marca(nombreMarca) VALUES (?)"); // ? evita inyección SQL
            ps.setString(1, marca.getNombre());
            return ps.executeUpdate(); // regresa filas afectadas
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
            return 0;
        } finally {
            this.close();
        }
    }

    // Método: elimina una marca. Parámetro obj: la marca
    @Override
    public int delete(Object obj) {
        try {
            this.conectar();
            if (cnx == null) {
                return 0;
            }
            Marca marca = (Marca) obj;
            ps = cnx.prepareStatement("DELETE FROM marca WHERE idMarca = ?");
            ps.setInt(1, marca.getId());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
            return 0;
        } finally {
            this.close();
        }
    }

    // Método: actualiza el nombre. Parámetro lista: la marca con el nombre nuevo
    @Override
    public int update(Object lista) {
        try {
            this.conectar();
            if (cnx == null) {
                return 0;
            }
            Marca marca = (Marca) lista;
            ps = cnx.prepareStatement("UPDATE marca SET nombreMarca = ? WHERE idMarca = ?");
            ps.setString(1, marca.getNombre());
            ps.setInt(2, marca.getId());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
            return 0;
        } finally {
            this.close();
        }
    }
}
