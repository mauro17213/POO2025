/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.dominio.especial;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jdlopez
 */
public class PeVariableValor extends Auditoria {

    private Integer id;
    private Integer idVariable;
    private Integer idAfiliado;
    private Integer idCarga;
    private String nombre;
    private String descripcion;
    private boolean activa;
    private Integer tipo;
    private Boolean recurrente;
    private Short recurrencia;
    private boolean obligatoria;
    private boolean editable;
    private Date fecha;
    private Integer entero;
    private BigDecimal decimal;
    private String texto;
    private String textoLargo;
    private boolean error;
    private String clase;
    private boolean advertencia;//util para carga masiva
    private Double valorCalculo;
    private PeVariableValorAlmacenado valorAlmacenado;//util para carga masiva en donde se puede comparar el id y valor almacenado en la db. (se establece en una sola consulta)
    List<PeValidacion> validaciones;
    private List<PeCorrelacion> validacionesCorrelacion;

    public PeVariableValor() {
    }

    public PeVariableValor(PeVariableValor valor) {
        this.idVariable = valor.getIdVariable();
        this.idAfiliado = valor.getIdAfiliado();
//        this.idCarga = valor.get
        this.nombre = valor.getNombre();
        this.descripcion = valor.getDescripcion();
        this.activa = valor.isActiva();
        this.tipo = valor.getTipo();
        this.recurrente = valor.isRecurrente();
        this.recurrencia = valor.getRecurrencia();
        this.obligatoria = valor.isObligatoria();
        this.validaciones = valor.getValidaciones();
        this.editable = valor.isEditable();
    }

    public PeVariableValor(PeVariable peVariable) {
        this.idVariable = peVariable.getId();
        this.tipo = peVariable.getTipo();
        this.nombre = peVariable.getNombre();
        this.activa = peVariable.isActiva();
        this.descripcion = peVariable.getDescripcion();
        this.recurrente = peVariable.isRecurrente();
        this.obligatoria = peVariable.isObligatoria();
        this.validaciones = peVariable.getValidaciones();
        this.editable = peVariable.isEditable();
        this.validacionesCorrelacion = peVariable.getValidacionesCorrelacion();
    }

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public Integer getTipo() {
        return tipo;
    }

    public String getTipoStr() {
        return PeTipoVariable.getNombreById(this.getTipo());
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public boolean isRecurrente() {
        return recurrente;
    }

    public void setRecurrente(boolean recurrente) {
        this.recurrente = recurrente;
    }

    public Boolean getRecurrente() {
        return recurrente;
    }

    public void setRecurrente(Boolean recurrente) {
        this.recurrente = recurrente;
    }

    public Short getRecurrencia() {
        return recurrencia;
    }

    public void setRecurrencia(Short recurrencia) {
        this.recurrencia = recurrencia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getEntero() {
        return entero;
    }

    public void setEntero(Integer entero) {
        this.entero = entero;
    }

    public BigDecimal getDecimal() {
        return decimal;
    }

    public void setDecimal(BigDecimal decimal) {
        this.decimal = decimal;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getTextoLargo() {
        return textoLargo;
    }

    public void setTextoLargo(String textoLargo) {
        this.textoLargo = textoLargo;
    }

    public Integer getIdVariable() {
        return idVariable;
    }

    public void setIdVariable(Integer idVariable) {
        this.idVariable = idVariable;
    }

    public Integer getIdAfiliado() {
        return idAfiliado;
    }

    public void setIdAfiliado(Integer idAfiliado) {
        this.idAfiliado = idAfiliado;
    }

    public boolean isObligatoria() {
        return obligatoria;
    }

    public void setObligatoria(boolean obligatoria) {
        this.obligatoria = obligatoria;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getClase() {
        return error ? "ui-state-error" : "";
    }

    public void setClase(String claseInput) {
        this.clase = claseInput;
    }

    public List<PeValidacion> getValidaciones() {
        return validaciones;
    }

    public void setValidaciones(List<PeValidacion> validaciones) {
        this.validaciones = validaciones;
    }

    public String getValor() {
        if (this.tipo == null) {
            return null;
        }
        switch (tipo) {
            case 0://Fecha
                if (fecha == null) {
                    return null;
                }
                SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
                String fechaFormateada = formateador.format(fecha);
                return fechaFormateada;
            case 1://Entero
                return entero != null ? String.valueOf(entero) : null;
            case 2://Decimal
                return decimal != null ? String.valueOf(decimal) : null;
            case 3://Texto
                return texto;
            case 4://Texto largo
                return textoLargo;
            default:
                return null;
        }
    }

    public Object getValorObject() {
        if (this.tipo == null) {
            return null;
        }
        switch (tipo) {
            case 0://Fecha
                if (fecha == null) {
                    return null;
                }
                SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
                String fechaFormateada = formateador.format(fecha);
                return fechaFormateada;
            case 1://Entero
                return entero;
            case 2://Decimal
                return decimal;
            case 3://Texto
                return texto;
            case 4://Texto largo
                return textoLargo;
            default:
                return null;
        }
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public Double getValorCalculo() {
        return valorCalculo;
    }

    public void setValorCalculo(Double valorCalculo) {
        this.valorCalculo = valorCalculo;
    }

    public boolean isAdvertencia() {
        return advertencia;
    }

    public void setAdvertencia(boolean advertencia) {
        this.advertencia = advertencia;
    }

    public Integer getIdCarga() {
        return idCarga;
    }

    public void setIdCarga(Integer idCarga) {
        this.idCarga = idCarga;
    }

    public PeVariableValorAlmacenado getVariableValorAlmacenado() {
        return valorAlmacenado;
    }

    public void setVariableValorAlmacenado(PeVariableValorAlmacenado valorAlmacenado) {
        this.valorAlmacenado = valorAlmacenado;
    }

    public List<PeCorrelacion> getValidacionesCorrelacion() {
        return validacionesCorrelacion;
    }

    public void setValidacionesCorrelacion(List<PeCorrelacion> validacionesCorrelacion) {
        this.validacionesCorrelacion = validacionesCorrelacion;
    }

    @Override
    public String toString() {
        return "PeVariableValor{" + "id=" + id + ", idVariable=" + idVariable + ", idAfiliado="
                + idAfiliado + ", idCarga=" + idCarga + ", nombre=" + nombre + ", descripcion=" + descripcion + ", activa="
                + activa + ", tipo=" + tipo + ", recurrente=" + recurrente + ", recurrencia=" + recurrencia
                + ", obligatoria=" + obligatoria + ", editable=" + editable + ", fecha=" + fecha + ", entero=" + entero
                + ", decimal=" + decimal + ", texto=" + texto + ", textoLargo=" + textoLargo + ", valorCalculo=" + valorCalculo
                + ", advertencia=" + advertencia + ", valorAlmacenado=" + valorAlmacenado + ", validaciones=" + validaciones
                + ", validacionesCorrelacion=" + validacionesCorrelacion + '}';
    }

}
