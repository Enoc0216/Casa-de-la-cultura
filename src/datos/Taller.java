/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

/**
 *
 * @author zahori
 */
public class Taller {
    private int id;
    private String nombre;
    private String descripcion;
    private Eventos id_evento;

    public Taller() {
    }

    public Taller(int id, String nombre, String descripcion, Eventos id_evento) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.id_evento = id_evento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Eventos getId_evento() {
        return id_evento;
    }

    public void setId_evento(Eventos id_evento) {
        this.id_evento = id_evento;
    }  
}
