package com.saviasaludeps.savia.dominio.juridico;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author idbohorquez
 */
public class CntjCampoGestion implements Serializable{
    
    private Integer id;
    private String etiqueta;
    private String tabla;
    private String campo;
    private String nombre;
    private String valor;
    private Integer tipoDato;
    private String maestroTipo;
    private Date valorDt;
    
    //informacion terceros
    private String naturaleza_juridica;
    private String tipo_documento;
    private String numero_documento;
    private String razon_social;
    private String tipo_documento_representante_legal;
    private String numero_documento_representante_legal;
    private String nombre_representante_legal;
    private String codigo_habilitacion;
    private String direccion;
    private String correo_electronico;
    private String telefono;
    private String cargo;
    private String area;

    public CntjCampoGestion() {
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
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

    public String getNaturaleza_juridica() {
        return naturaleza_juridica;
    }

    public void setNaturaleza_juridica(String naturaleza_juridica) {
        this.naturaleza_juridica = naturaleza_juridica;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public String getTipo_documento_representante_legal() {
        return tipo_documento_representante_legal;
    }

    public void setTipo_documento_representante_legal(String tipo_documento_representante_legal) {
        this.tipo_documento_representante_legal = tipo_documento_representante_legal;
    }

    public String getNumero_documento_representante_legal() {
        return numero_documento_representante_legal;
    }

    public void setNumero_documento_representante_legal(String numero_documento_representante_legal) {
        this.numero_documento_representante_legal = numero_documento_representante_legal;
    }

    public String getNombre_representante_legal() {
        return nombre_representante_legal;
    }

    public void setNombre_representante_legal(String nombre_representante_legal) {
        this.nombre_representante_legal = nombre_representante_legal;
    }

    public String getCodigo_habilitacion() {
        return codigo_habilitacion;
    }

    public void setCodigo_habilitacion(String codigo_habilitacion) {
        this.codigo_habilitacion = codigo_habilitacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(Integer tipoDato) {
        this.tipoDato = tipoDato;
    }

    public String getMaestroTipo() {
        return maestroTipo;
    }

    public void setMaestroTipo(String maestroTipo) {
        this.maestroTipo = maestroTipo;
    }

    public Date getValorDt() {
        return valorDt;
    }

    public void setValorDt(Date valorDt) {
        this.valorDt = valorDt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero_documento() {
        return numero_documento;
    }

    public void setNumero_documento(String numero_documento) {
        this.numero_documento = numero_documento;
    }
    

    @Override
    public String toString() {
        return "CntjCampoGestion{" + "etiqueta=" + etiqueta + ", tabla=" + tabla + ", campo=" + campo + ", nombre=" + nombre + ", valor=" + valor + ", tipoDato=" + tipoDato + ", maestroTipo=" + maestroTipo + ", naturaleza_juridica=" + naturaleza_juridica + ", tipo_documento=" + tipo_documento + ", razon_social=" + razon_social + ", tipo_documento_representante_legal=" + tipo_documento_representante_legal + ", numero_documento_representante_legal=" + numero_documento_representante_legal + ", nombre_representante_legal=" + nombre_representante_legal + ", codigo_habilitacion=" + codigo_habilitacion + ", direccion=" + direccion + ", correo_electronico=" + correo_electronico + ", telefono=" + telefono + ", cargo=" + cargo + ", area=" + area + '}';
    }
    

}
