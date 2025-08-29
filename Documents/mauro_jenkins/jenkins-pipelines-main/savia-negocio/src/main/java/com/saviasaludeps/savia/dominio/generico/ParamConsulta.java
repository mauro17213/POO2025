
package com.saviasaludeps.savia.dominio.generico;

import java.io.Serializable;
import java.util.Map;

public class ParamConsulta implements Serializable{

    private int pagina;
    private Integer empresaId;
    private Integer usuarioId;
    
    private int primerRegistro;
    private int registrosPagina;
    private String orden; //Pendientes por borrar
    private boolean ascendente; //Pendiente por borrar
    private Map<String, String> listaOrden;
    private Map<String, Object> filtros;
    private int cantidadRegistros;
    
    private Object parametroConsulta1;
    private Object parametroConsulta2;
    private Object parametroConsulta3;
    private Object parametroConsulta4;
    private Object parametroConsulta5;
    private Object parametroConsulta6;
    private Object parametroConsulta7;
    private Object parametroConsulta8;
    private Object parametroConsulta9;

    public int getPagina() {
        return pagina;
    }

    public void setPagina(int pagina) {
        this.pagina = pagina;
    }

    public Integer getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Integer empresaId) {
        this.empresaId = empresaId;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getPrimerRegistro() {
        return primerRegistro;
    }

    public void setPrimerRegistro(int primerRegistro) {
        this.primerRegistro = primerRegistro;
    }

    public int getRegistrosPagina() {
        return registrosPagina;
    }

    public void setRegistrosPagina(int registrosPagina) {
        this.registrosPagina = registrosPagina;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public boolean isAscendente() {
        return ascendente;
    }

    public void setAscendente(boolean ascendente) {
        this.ascendente = ascendente;
    }

    public Map<String, String> getListaOrden() {
        return listaOrden;
    }

    public void setListaOrden(Map<String, String> listaOrden) {
        this.listaOrden = listaOrden;        
        if (getListaOrden() != null) {
            getListaOrden().forEach((k,v) -> {
                orden = k;
                ascendente = v.equals("ASC") ? true : false;
            });
        }
    }

    public Map<String, Object> getFiltros() {
        return filtros;
    }

    public void setFiltros(Map<String, Object> filtros) {
        this.filtros = filtros;
    }

    public int getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCantidadRegistros(int cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

    public Object getParametroConsulta1() {
        return parametroConsulta1;
    }

    public void setParametroConsulta1(Object parametroConsulta1) {
        this.parametroConsulta1 = parametroConsulta1;
    }

    public Object getParametroConsulta2() {
        return parametroConsulta2;
    }

    public void setParametroConsulta2(Object parametroConsulta2) {
        this.parametroConsulta2 = parametroConsulta2;
    }

    public Object getParametroConsulta3() {
        return parametroConsulta3;
    }

    public void setParametroConsulta3(Object parametroConsulta3) {
        this.parametroConsulta3 = parametroConsulta3;
    }

    public Object getParametroConsulta4() {
        return parametroConsulta4;
    }

    public void setParametroConsulta4(Object parametroConsulta4) {
        this.parametroConsulta4 = parametroConsulta4;
    }

    public Object getParametroConsulta5() {
        return parametroConsulta5;
    }

    public void setParametroConsulta5(Object parametroConsulta5) {
        this.parametroConsulta5 = parametroConsulta5;
    }

    public Object getParametroConsulta6() {
        return parametroConsulta6;
    }

    public void setParametroConsulta6(Object parametroConsulta6) {
        this.parametroConsulta6 = parametroConsulta6;
    }

    public Object getParametroConsulta7() {
        return parametroConsulta7;
    }

    public void setParametroConsulta7(Object parametroConsulta7) {
        this.parametroConsulta7 = parametroConsulta7;
    }

    public Object getParametroConsulta8() {
        return parametroConsulta8;
    }

    public void setParametroConsulta8(Object parametroConsulta8) {
        this.parametroConsulta8 = parametroConsulta8;
    }

    public Object getParametroConsulta9() {
        return parametroConsulta9;
    }

    public void setParametroConsulta9(Object parametroConsulta9) {
        this.parametroConsulta9 = parametroConsulta9;
    }
    
    protected String verificar(String _str){
        if(_str==null)_str="";
        return _str;
    }
 }
