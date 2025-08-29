package com.saviasaludeps.savia.dominio.financiera;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jeperez
 */
public class FinGiro extends Auditoria implements Serializable {
    
    public static final int TIPO_GIRO_DIRECTO_SUBSIDIADO     = 1;
    public static final int TIPO_GIRO_DIRECTO_CONTRIBUTIVO   = 2;
    public static final int TIPO_GIRO_DIRECTO_PRESUPUESTOS_MAXIMOS   = 3;
    public static final int TIPO_OTROS_PAGOS         = 4;
    

    private Integer id;
    private int tipo;
    private String nombre;
    private boolean borrado;
    private String usuarioBorra;
    private String terminalBorra;
    private Date fechaHoraBorra;
    private List<FinPostulacion> finPostulacionesList;


    public FinGiro() {
    }

    public FinGiro(int id) {
        this.id = id;
    }

    public FinGiro(Integer id, int tipo, String nombre) {
        this.id = id;
        this.tipo = tipo;
        this.nombre = nombre;
    }
    
    

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isBorrado() {
        return borrado;
    }
    
    public boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public String getTerminalBorra() {
        return terminalBorra;
    }

    public void setTerminalBorra(String terminalBorra) {
        this.terminalBorra = terminalBorra;
    }

    public Date getFechaHoraBorra() {
        return fechaHoraBorra;
    }

    public void setFechaHoraBorra(Date fechaHoraBorra) {
        this.fechaHoraBorra = fechaHoraBorra;
    }

    public List<FinPostulacion> getFinPostulacionesList() {
        return finPostulacionesList;
    }

    public void setFinPostulacionesList(List<FinPostulacion> finPostulacionesList) {
        this.finPostulacionesList = finPostulacionesList;
    }
    
    public String getTipoStr() {
        return FinGiro.getTipoStr(getTipo());
    }  

    public String getUsuarioBorra() {
        return usuarioBorra;
    }

    public void setUsuarioBorra(String usuarioBorra) {
        this.usuarioBorra = usuarioBorra;
    }
    
    
    
   public static String getTipoStr(int tipo) {
        switch (tipo) {
            case TIPO_GIRO_DIRECTO_CONTRIBUTIVO:
                return "Giro directo contributivo ";
            case TIPO_GIRO_DIRECTO_PRESUPUESTOS_MAXIMOS:
                return "Giro directo presupuestos máximos ";
            case TIPO_GIRO_DIRECTO_SUBSIDIADO:
                return "Giro directo subsidiado ";
                 case TIPO_OTROS_PAGOS:
                return "Otros Pagos ";
            default:
                return "";
        }
    }

    @Override
    public String toString() {
        return "FinGiro{" + "id=" + id + ", tipo=" + tipo + ", nombre=" + nombre + ", borrado=" + borrado + ", usuarioBorra=" + usuarioBorra + ", terminalBorra=" + terminalBorra + ", fechaHoraBorra=" + fechaHoraBorra + '}';
    }
   
}
