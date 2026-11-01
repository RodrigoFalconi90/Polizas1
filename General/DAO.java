package Polizas.General;

import java.util.List;

// Interfaz DAO: métodos del CRUD que debe implementar cada modelo
public abstract interface DAO {

    int create(Object obj);   // insertar
    List read();              // consultar
    int update(Object lista); // actualizar
    int delete(Object obj);   // eliminar

}
