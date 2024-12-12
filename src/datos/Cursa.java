/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;
import java.util.Date;


/**
 *
 * @author LEMUEL
 */
public class Cursa {
    private int id;
    private Alumnos id_alumno;
    private Taller id_taller;
    private Date fecha_inicio;
    private Date fecha_termino;

    public Cursa() {
    }

    public Cursa(int id, Alumnos id_alumno, Taller id_taller, Date fecha_inicio, Date fecha_termino) {
        this.id = id;
        this.id_alumno = id_alumno;
        this.id_taller = id_taller;
        this.fecha_inicio = fecha_inicio;
        this.fecha_termino = fecha_termino;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Alumnos getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(Alumnos id_alumno) {
        this.id_alumno = id_alumno;
    }

    public Taller getId_taller() {
        return id_taller;
    }

    public void setId_taller(Taller id_taller) {
        this.id_taller = id_taller;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_termino() {
        return fecha_termino;
    }

    public void setFecha_termino(Date fecha_termino) {
        this.fecha_termino = fecha_termino;
    }

    
}

