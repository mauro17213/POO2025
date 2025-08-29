package com.saviasaludeps.savia.dominio.informe;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InfInformeVariable extends Auditoria {
    
    public final static int TIPO_TEXTO = 0;
    public final static int TIPO_NUMERO = 1;
    public final static int TIPO_FECHA = 2;
    public final static int TIPO_FECHA_INICIO_AUTOMATICO = 3;
    public final static int TIPO_FECHA_FIN_AUTOMATICO = 4;
    public final static int TIPO_CARPETA = 5;
    public final static int TIPO_BOOLEAN = 6;
    public final static int TIPO_LITERAL = 7;
    public final static int TIPO_FECHA_HORA = 8;
    public final static int TIPO_EMPRESA = 9;
    
    public final static int ESTADO_REGISTRO_EXISTENTE = 0;
    public final static int ESTADO_REGISTRO_NUEVO = 1;
    public final static int ESTADO_REGISTRO_BORRADO = 2;
    
    private final static SimpleDateFormat FECHA_CORTA = new SimpleDateFormat("yyyy-MM-dd");
    private final static SimpleDateFormat FECHA_LARGA = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private Integer id;
    private String valor;
    private String nombre;
    private int orden;
    private int tipo;
    private boolean dinamico;
    private InfInforme infInforme;
    
    private int estadoRegistro = ESTADO_REGISTRO_EXISTENTE;
    
    //Variables auxiliares
    private Date fecha;
    private int numero;
    private boolean bool;
    

    public InfInformeVariable() {
    }

    public InfInformeVariable(Integer id) {
        this.id = id;
    }

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

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public InfInforme getInfInforme() {
        return infInforme;
    }

    public void setInfInforme(InfInforme infInforme) {
        this.infInforme = infInforme;
    }

    public boolean isDinamico() {
        return dinamico;
    }

    public void setDinamico(boolean dinamico) {
        this.dinamico = dinamico;
    }

    public String getTipoStr() {
        String str = "";
        switch (tipo) {
            case TIPO_TEXTO:
                str = "Texto";
                break;
            case TIPO_NUMERO:
                str = "Número";
                break;
            case TIPO_FECHA:
                str = "Fecha";
                break;
            case TIPO_FECHA_INICIO_AUTOMATICO:
                str = "Fecha Inicio Automática";
                break;
            case TIPO_FECHA_FIN_AUTOMATICO:
                str = "Fecha Fin Automática";
                break;
            case TIPO_CARPETA:
                str = "Carpeta";
                break;
            case TIPO_BOOLEAN:
                str = "Boolean";
                break;
            case TIPO_LITERAL:
                str = "Literal";
                break;
            case TIPO_FECHA_HORA:
                str = "Fecha Hora";
                break;
            case TIPO_EMPRESA:
                str = "Empresa";
                break;
            default:
                break;
        }
        return str;
    }

    public int getEstadoRegistro() {
        return estadoRegistro;
    }
    
    public boolean isEstadoRegistroBorrado() {
        return estadoRegistro == ESTADO_REGISTRO_BORRADO;
    }

    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    @Override
    public String toString() {
        return "InfInformeVariable{" + "id=" + id + ", valor=" + valor + ", nombre=" + nombre + ", orden=" + orden + ", tipo=" + tipo + '}';
    }
    
    //Metodo auxiliares

    public Date getFecha() {
        if (valor != null) {
            try {
                switch(tipo) {
                    case TIPO_FECHA:
                        fecha = FECHA_CORTA.parse(valor);
                        break;
                    case TIPO_FECHA_HORA:
                        fecha = FECHA_LARGA.parse(valor);
                        break;
                }
            } catch (ParseException e) {
            } 
        }     
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
        try {
            switch(this.tipo) {
                case TIPO_FECHA:
                    this.valor = FECHA_CORTA.format(fecha);
                    break;
                case TIPO_FECHA_HORA:
                    this.valor = FECHA_LARGA.format(fecha);
                    break;
            }
        } catch (Exception e) {
        }
    }

    public int getNumero() {
        if (tipo == TIPO_NUMERO) {
            try {
                this.numero = Integer.valueOf(valor);
            } catch (NumberFormatException e) {
            }            
        }
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
        try {
            if(tipo == TIPO_NUMERO) {
                this.valor = ""+numero;
            }
        } catch (Exception e) {
        }
    }

    public boolean isBool() {
        if (valor != null) {
            if(tipo == TIPO_BOOLEAN) {
                return valor.equals("true");
            }
        }        
        return bool;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
        if (tipo == TIPO_BOOLEAN) {
            this.valor = ""+bool;
        }
    }

}
