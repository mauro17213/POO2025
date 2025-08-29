package com.saviasaludeps.savia.dominio.informe;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InfInformeValor extends Auditoria {
    
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
    
    private final static SimpleDateFormat FECHA_CORTA = new SimpleDateFormat("yyyy-MM-dd");
    private final static SimpleDateFormat FECHA_LARGA = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private Integer id;
    private String variable;
    private InfGenerado infGenerado;
    private InfInformeVariable infInformeVariable;
    
    private Object variable1;
    private Object variable2;
    private Object variable3;
    private Object variable4;
    private Object variable5;
    private Object variable6;
    private Object variable7;
    private Object variable8;
    private Object variable9;
    private Object variable10;
    
    private int tipo;
    private String nombre;
    private String valor;
    
    //Auxiliares
    private Date fecha;
    private int numero;
    private boolean bool;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public InfGenerado getInfGenerado() {
        return infGenerado;
    }

    public void setInfGenerado(InfGenerado infGenerado) {
        this.infGenerado = infGenerado;
    }

    public InfInformeVariable getInfInformeVariable() {
        return infInformeVariable;
    }

    public void setInfInformeVariable(InfInformeVariable infInformeVariable) {
        this.infInformeVariable = infInformeVariable;
    }

    public Object getVariable1() {
        return variable1;
    }

    public void setVariable1(Object variable1) {
        this.variable1 = variable1;
    }

    public Object getVariable2() {
        return variable2;
    }

    public void setVariable2(Object variable2) {
        this.variable2 = variable2;
    }

    public Object getVariable3() {
        return variable3;
    }

    public void setVariable3(Object variable3) {
        this.variable3 = variable3;
    }

    public Object getVariable4() {
        return variable4;
    }

    public void setVariable4(Object variable4) {
        this.variable4 = variable4;
    }

    public Object getVariable5() {
        return variable5;
    }

    public void setVariable5(Object variable5) {
        this.variable5 = variable5;
    }

    public Object getVariable6() {
        return variable6;
    }

    public void setVariable6(Object variable6) {
        this.variable6 = variable6;
    }

    public Object getVariable7() {
        return variable7;
    }

    public void setVariable7(Object variable7) {
        this.variable7 = variable7;
    }

    public Object getVariable8() {
        return variable8;
    }

    public void setVariable8(Object variable8) {
        this.variable8 = variable8;
    }

    public Object getVariable9() {
        return variable9;
    }

    public void setVariable9(Object variable9) {
        this.variable9 = variable9;
    }

    public Object getVariable10() {
        return variable10;
    }

    public void setVariable10(Object variable10) {
        this.variable10 = variable10;
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

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "InfInformeValor{" + "id=" + id + ", variable=" + variable + ", infGenerado=" + infGenerado + ", infInformeVariable=" + infInformeVariable + '}';
    }

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
