/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.List;

/**
 *
 * @author Stiven Giraldo
 */
public class AuRechazo extends Auditoria {
    
    private Integer id;
    private Integer maeCausaRechazoId;
    private String maeCausaRechazoCodigo;
    private String maeCausaRechazoValor;
    private String justificacion;
    private String alternativa;
    private List<AuRechazoItem> auRechazoItemsList;
    private AsegAfiliado asegAfiliadoId;
    private AuAnexo3 auAnexo3Id;
    private Empresa empresasId;

    public AuRechazo(){
        
    }
    
    public AuRechazo(int id){
        this.id = id;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaeCausaRechazoId() {
        return maeCausaRechazoId;
    }

    public void setMaeCausaRechazoId(Integer maeCausaRechazoId) {
        this.maeCausaRechazoId = maeCausaRechazoId;
    }

    public String getMaeCausaRechazoCodigo() {
        return maeCausaRechazoCodigo;
    }

    public void setMaeCausaRechazoCodigo(String maeCausaRechazoCodigo) {
        this.maeCausaRechazoCodigo = maeCausaRechazoCodigo;
    }

    public String getMaeCausaRechazoValor() {
        return maeCausaRechazoValor;
    }

    public void setMaeCausaRechazoValor(String maeCausaRechazoValor) {
        this.maeCausaRechazoValor = maeCausaRechazoValor;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public List<AuRechazoItem> getAuRechazoItemsList() {
        return auRechazoItemsList;
    }

    public void setAuRechazoItemsList(List<AuRechazoItem> auRechazoItemsList) {
        this.auRechazoItemsList = auRechazoItemsList;
    }

    public AsegAfiliado getAsegAfiliadoId() {
        return asegAfiliadoId;
    }

    public void setAsegAfiliadoId(AsegAfiliado asegAfiliadoId) {
        this.asegAfiliadoId = asegAfiliadoId;
    }

    public AuAnexo3 getAuAnexo3Id() {
        return auAnexo3Id;
    }

    public void setAuAnexo3Id(AuAnexo3 auAnexo3Id) {
        this.auAnexo3Id = auAnexo3Id;
    }

    public Empresa getEmpresasId() {
        return empresasId;
    }

    public void setEmpresasId(Empresa empresasId) {
        this.empresasId = empresasId;
    }

    public String getAlternativa() {
        return alternativa;
    }

    public void setAlternativa(String alternativa) {
        this.alternativa = alternativa;
    }

    @Override
    public String toString() {
        return "AuRechazo{" + "id=" + id + ", maeCausaRechazoId=" + maeCausaRechazoId + ", maeCausaRechazoCodigo=" + maeCausaRechazoCodigo + ", maeCausaRechazoValor=" + maeCausaRechazoValor + ", justificacion=" + justificacion + '}';
    }
}
