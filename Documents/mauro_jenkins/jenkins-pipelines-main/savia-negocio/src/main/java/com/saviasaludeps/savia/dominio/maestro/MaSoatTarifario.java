/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.maestro;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author AlexanderDiaz
 */
public class MaSoatTarifario extends Auditoria {
    private Integer id;
    private String codigo;
    private String descripcion;
    private int tipo;
    private Integer grupo;
    private BigDecimal puntos;
    
    private List<MaSoatTarifarioValor> listaMaSoatTarifarioValor;
    public MaSoatTarifario(){
        
    }
    
    public MaSoatTarifario(Integer id){
        this.id = id;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Integer getGrupo() {
        return grupo;
    }

    public void setGrupo(Integer grupo) {
        this.grupo = grupo;
    }

    public BigDecimal getPuntos() {
        return puntos;
    }

    public void setPuntos(BigDecimal puntos) {
        this.puntos = puntos;
    }
    

    @Override
    public String toString() {
        return "MaSoat{" + "id=" + id +"codigo=" + codigo +"descripcion=" + descripcion +"tipo=" + tipo +"grupo=" + grupo +"puntos=" + puntos + "}" ;
    }

    /**
     * @return the listaMaSoatTarifarioValor
     */
    public List<MaSoatTarifarioValor> getListaMaSoatTarifarioValor() {
        return listaMaSoatTarifarioValor;
    }

    /**
     * 
     * @param listaMaSoatTarifarioValor 
     */
    public void setListaMaSoatTarifarioValor(List<MaSoatTarifarioValor> listaMaSoatTarifarioValor) {
        this.listaMaSoatTarifarioValor = listaMaSoatTarifarioValor;
    }
    
    public String getListaValoresPorAnio() {
        String mensaje = "";
        if (this.listaMaSoatTarifarioValor != null && !this.listaMaSoatTarifarioValor.isEmpty()) {
            for (MaSoatTarifarioValor valor: this.listaMaSoatTarifarioValor) {
                mensaje += valor.getAgnoStr() + ": " + valor.getValor() +"\n";
            }
        }
        return mensaje;
    }
}
