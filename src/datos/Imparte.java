/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.util.Calendar;
import java.util.Date;


public class Imparte {
    private int id;
    private Docentes id_Docente;
    private Taller id_taller;
    private Date fecha_inicio;
    private Date fecha_termino;

    public Imparte() {
    }

    public Imparte(int id, Docentes id_Docente, Taller id_taller, Date fecha_inicio, Date fecha_termino) {
        this.id = id;
        this.id_Docente = id_Docente;
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

    
      public Docentes getId_Docente() {
        return id_Docente;
    }

    public void setId_Docente(Docentes id_Docente) {
        this.id_Docente = id_Docente;
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
