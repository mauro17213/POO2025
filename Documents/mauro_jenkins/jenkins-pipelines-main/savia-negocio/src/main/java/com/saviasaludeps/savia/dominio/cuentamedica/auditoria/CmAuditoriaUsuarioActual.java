/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.auditoria;


import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;



public class CmAuditoriaUsuarioActual extends Auditoria implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idUsuario;
    private String gruposAccesoStr = "";
    private HashMap<Integer,HashMap> hashAccesosPorGrupo;
    List<CmGrupoUsuario> gruposDeAcceso;
    private boolean esRetristradoEnAuditoria;
    private boolean auditorGrupoLider; 
    private boolean auditorGrupoMedico; 
    private boolean auditorGrupoTecnico; 
    private boolean auditorGrupoGestiona;
    private boolean auditorFacturaLider; 
    private boolean auditorFacturaMedico; 
    private boolean auditorFacturaTecnico; 
    private boolean auditorFacturaGestiona; 
    private boolean superAdministrador;
   
    public CmAuditoriaUsuarioActual() {
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getGruposAccesoStr() {
        return gruposAccesoStr;
    }

    public void setGruposAccesoStr(String gruposAccesoStr) {
        this.gruposAccesoStr = gruposAccesoStr;
    }


    public HashMap<Integer, HashMap> getHashAccesosPorGrupo() {
        if(hashAccesosPorGrupo == null){
           hashAccesosPorGrupo = new HashMap<>();
        }
        return hashAccesosPorGrupo;
    }

    public void setHashAccesosPorGrupo(HashMap<Integer, HashMap> hashAccesosPorGrupo) {
        this.hashAccesosPorGrupo = hashAccesosPorGrupo;
    }

    public List<CmGrupoUsuario> getGruposDeAcceso() {
        if(gruposDeAcceso == null){
        gruposDeAcceso = new ArrayList<>();
        }
        return gruposDeAcceso;
    }

    public boolean isAuditorFacturaLider() {
        return auditorFacturaLider;
    }

    public void setAuditorFacturaLider(boolean auditorFacturaLider) {
        this.auditorFacturaLider = auditorFacturaLider;
    }

    public boolean isAuditorFacturaMedico() {
        return auditorFacturaMedico;
    }

    public void setAuditorFacturaMedico(boolean auditorFacturaMedico) {
        this.auditorFacturaMedico = auditorFacturaMedico;
    }

    public boolean isAuditorFacturaTecnico() {
        return auditorFacturaTecnico;
    }

    public void setAuditorFacturaTecnico(boolean auditorFacturaTecnico) {
        this.auditorFacturaTecnico = auditorFacturaTecnico;
    }

    public boolean isAuditorFacturaGestiona() {
        return auditorFacturaGestiona;
    }

    public void setAuditorFacturaGestiona(boolean auditorFacturaGestiona) {
        this.auditorFacturaGestiona = auditorFacturaGestiona;
    }

 
    public void setGrupoAccesoFactura(CmFactura factura, int idUsuarioSolicitante){
       boolean esUsuarioLider = factura.getUsuarioLider()!= null &&
                            factura.getUsuarioLider().getId() != null ? 
                            factura.getUsuarioLider().getId() == idUsuarioSolicitante : false;
       boolean esUsuarioMedico=  factura.getUsuarioMedico() != null &&
                            factura.getUsuarioMedico().getId() != null ? 
                            factura.getUsuarioMedico().getId() == idUsuarioSolicitante : false;
       boolean esUsuarioTecnico =  factura.getUsuarioTecnico() != null &&
                            factura.getUsuarioTecnico().getId() != null ? 
                            factura.getUsuarioTecnico().getId() == idUsuarioSolicitante : false;
        boolean esUsuarioGestiona =  factura.getUsuarioGestiona() != null &&
                            factura.getUsuarioGestiona().getId() != null ? 
                            factura.getUsuarioGestiona().getId() == idUsuarioSolicitante : false;
        
        setAuditorFacturaLider(esUsuarioLider);
        setAuditorFacturaMedico(esUsuarioMedico);
        setAuditorFacturaTecnico(esUsuarioTecnico);
        setAuditorFacturaGestiona(esUsuarioGestiona);
    }

    public void setGruposDeAccesoGrupos(List<CmGrupoUsuario> gruposDeAcceso) {
        this.gruposDeAcceso = gruposDeAcceso;
        
        if (this.gruposDeAcceso != null) {
            HashMap<Integer, HashMap> perfil = new HashMap<>();
            for (CmGrupoUsuario grupo : this.gruposDeAcceso) {
                if (perfil.get(grupo.getCmGrupo().getId()) == null) {
                    HashMap<Integer, Integer> tipoAuditor = new HashMap();
                    tipoAuditor.put(grupo.getTipo(), grupo.getTipo());
                    perfil.put(grupo.getCmGrupo().getId(), tipoAuditor);
                } else {
                    if (perfil.get(grupo.getCmGrupo().getId()).get(grupo.getTipo()) == null) {
                        perfil.get(grupo.getCmGrupo().getId()).put(grupo.getTipo(), grupo.getTipo());
                    }
                }
            }
            gruposAccesoStr = perfil.keySet().stream().map(Object::toString).collect(Collectors.joining(",")); 
            hashAccesosPorGrupo = perfil;
        }
        
    }

    public boolean isEsRetristradoEnAuditoria() {
        if (!"".equals(getGruposAccesoStr())) {
            esRetristradoEnAuditoria = true;
        } else {
            esRetristradoEnAuditoria = false;
        }
        return esRetristradoEnAuditoria;
    }

    public void setEsRetristradoEnAuditoria(boolean esRetristradoEnAuditoria) {
        this.esRetristradoEnAuditoria = esRetristradoEnAuditoria;
    }

    public boolean isAuditorGrupoLider(int idGrupo) {
        if(getHashAccesosPorGrupo().get(idGrupo) != null){
          auditorGrupoLider =  getHashAccesosPorGrupo().get(idGrupo).
                            get(CmGrupoUsuario.TIPO_LIDER) != null; 
        }
        return auditorGrupoLider;
    }

    public void setAuditorGrupoLider(boolean auditorGrupoLider) {
        this.auditorGrupoLider = auditorGrupoLider;
    }

    public boolean isAuditorGrupoMedico(int idGrupo) {
         if(getHashAccesosPorGrupo().get(idGrupo) != null){
          auditorGrupoMedico =  getHashAccesosPorGrupo().get(idGrupo).
                                get(CmGrupoUsuario.TIPO_MEDICO) != null; 
        }
        return auditorGrupoMedico;
    }

    public void setAuditorGrupoMedico(boolean auditorGrupoMedico) {
        this.auditorGrupoMedico = auditorGrupoMedico;
    }

    public boolean isAuditorGrupoTecnico(int idGrupo) {
         if(getHashAccesosPorGrupo().get(idGrupo) != null){
          auditorGrupoTecnico =  getHashAccesosPorGrupo().get(idGrupo).
                             get(CmGrupoUsuario.TIPO_TECNICO) != null; 
        }
        return auditorGrupoTecnico;
    }

    public void setAuditorGrupoTecnico(boolean auditorGrupoTecnico) {
        this.auditorGrupoTecnico = auditorGrupoTecnico;
    }
    
    public boolean isAuditorGrupoGestiona(int idGrupo) {
        if(getHashAccesosPorGrupo().get(idGrupo) != null){
          auditorGrupoGestiona =  getHashAccesosPorGrupo().get(idGrupo).
                                  get(CmGrupoUsuario.TIPO_USUARIO_GESTIONA) != null; 
        }
        return auditorGrupoGestiona;
    }

    public void setAuditorGrupoGestiona(boolean auditorGrupoGestiona) {
        this.auditorGrupoGestiona = auditorGrupoGestiona;
    }

    public boolean isUsuarioLider(int idGrupo) {
        return isAuditorGrupoLider(idGrupo) || isAuditorFacturaLider()|| isSuperAdministrador()  ;
    }
   
    public boolean isUsuarioMedico(int idGrupo) {
       return isAuditorGrupoMedico(idGrupo) || isAuditorFacturaMedico();
    }

    public boolean isUsuarioTecnico(int idGrupo) {
        return isAuditorGrupoTecnico(idGrupo) || isAuditorFacturaTecnico();
    }

    public boolean isUsuarioGestiona(int idGrupo) {
        return isAuditorGrupoGestiona(idGrupo)|| isAuditorFacturaGestiona() ;
    }

    public boolean isSuperAdministrador() {
        return superAdministrador;
    }

    public void setSuperAdministrador(boolean superAdministrador) {
        this.superAdministrador = superAdministrador;
    }
    
    
   
}
