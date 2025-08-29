package com.saviasaludeps.savia.dominio.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;
import java.util.List;

public class CmRipsRegla extends Auditoria {

    private Integer id;
    private int tipo;
    private int maeCntTipoContratoId;
    private String maeCntTipoContratoValor;
    private String maeCntTipoContratoCodigo;
    private String nombre;
    private String descripcion;
    private int alerta;
    private int orden;
    private Date fechaInicial;
    private Date fechaFinal;
    private boolean activa;
    private String archivo;
    private String funcion;
    private byte[] jsonRegla;
    private int idInsertar;
    private List<CmRipsReglaEntrada> listaCmRipsReglaEntrada;
    private List<CmRipsReglaSalida> listaCmRipsReglaSalida;
    private Short bdEjecucion;

    public static final int ALERTA_ADVERTENCIA = 1;
    public static final int ALERTA_ERROR = 0;
    public static final int TIPO_ESTRUCTURA = 1;
    public static final int TIPO_NORMATIVA = 2;
    public static final int TIPO_NEGOCIO = 3;
    public static final boolean ACTIVA = true;
    public static final boolean INACTIVA = false;

    public CmRipsRegla() {
    }

    public CmRipsRegla(Integer id) {
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

    public int getMaeCntTipoContratoId() {
        return maeCntTipoContratoId;
    }

    public void setMaeCntTipoContratoId(int maeCntTipoContratoId) {
        this.maeCntTipoContratoId = maeCntTipoContratoId;
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

    public int getAlerta() {
        return alerta;
    }

    public void setAlerta(int alerta) {
        this.alerta = alerta;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public byte[] getJsonRegla() {
        return jsonRegla;
    }

    public void setJsonRegla(byte[] jsonRegla) {
        this.jsonRegla = jsonRegla;
    }

    public int getIdInsertar() {
        return idInsertar;
    }

    public void setIdInsertar(int idInsertar) {
        this.idInsertar = idInsertar;
    }

    public String getMaeCntTipoContratoValor() {
        return maeCntTipoContratoValor;
    }

    public void setMaeCntTipoContratoValor(String maeCntTipoContratoValor) {
        this.maeCntTipoContratoValor = maeCntTipoContratoValor;
    }

    public String getMaeCntTipoContratoCodigo() {
        return maeCntTipoContratoCodigo;
    }

    public void setMaeCntTipoContratoCodigo(String maeCntTipoContratoCodigo) {
        this.maeCntTipoContratoCodigo = maeCntTipoContratoCodigo;
    }

    public List<CmRipsReglaEntrada> getListaCmRipsReglaEntrada() {
        return listaCmRipsReglaEntrada;
    }

    public void setListaCmRipsReglaEntrada(List<CmRipsReglaEntrada> listaCmRipsReglaEntrada) {
        this.listaCmRipsReglaEntrada = listaCmRipsReglaEntrada;
    }

    public List<CmRipsReglaSalida> getListaCmRipsReglaSalida() {
        return listaCmRipsReglaSalida;
    }

    public void setListaCmRipsReglaSalida(List<CmRipsReglaSalida> listaCmRipsReglaSalida) {
        this.listaCmRipsReglaSalida = listaCmRipsReglaSalida;
    }

    public Short getBdEjecucion() {
        return bdEjecucion;
    }

    public void setBdEjecucion(Short bdEjecucion) {
        this.bdEjecucion = bdEjecucion;
    }

    public String getAlertaStr() {
        String strAlerta = "";
        switch (this.alerta) {
            case ALERTA_ADVERTENCIA:
                strAlerta = "Advertencia";
                break;
            case ALERTA_ERROR:
                strAlerta = "Error";
                break;
        }
        return strAlerta;
    }

    public String getTipoStr() {
        String strTipo = "";
        switch (this.tipo) {
            case TIPO_ESTRUCTURA:
                strTipo = "Estructura";
                break;
            case TIPO_NORMATIVA:
                strTipo = "Normativa";
                break;
            case TIPO_NEGOCIO:
                strTipo = "Negocio";
                break;
        }
        return strTipo;
    }

    public String getActivaStr() {
        String strActiva;
        if (this.activa == true) {
            strActiva = "Activa";
        } else {
            strActiva = "Inactiva";
        }
        return strActiva;
    }

    @Override
    public String toString() {
        return "CmRipsRegla{" + "id=" + id + ", tipo=" + tipo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", alerta=" + alerta + '}';
    }

}
