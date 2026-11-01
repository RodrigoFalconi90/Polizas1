package Polizas.Marcas;


// Entidad: una fila de la tabla marca
class Marca {

    // Atributos
    protected int id;
    protected String nombre;

    // Método: lo que muestra el combo
    @Override
    public String toString() {
        return this.nombre;
    }

    // Constructor. Parámetros: id y nombre de la marca
    public Marca(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
