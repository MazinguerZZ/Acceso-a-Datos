package redis.server.modelo;

import java.io.Serializable;

public class Cafe implements Serializable {

    private String id;
    private String nombre;

    public Cafe() {
    }

    public Cafe(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
