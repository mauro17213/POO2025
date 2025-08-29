/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.auditoria.servicio;



import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaAdjunto;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmGrupoUsuario;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsCarga;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaAdjuntoRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaFacturaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmGrupoUsuarioRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmDetalleRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmFacturaRemoto;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.cuentamedica.auditoria.bean.CmAuditoriaLiderFacturaBean;
import com.saviasaludeps.savia.web.cuentamedica.auditoria.utilidades.CmAuditoriaFacturaBeanUtil;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author AlexanderDiaz
 */
public class CmAuditoriaLiderFacturaServicioImpl extends AccionesBO implements CmAuditoriaLiderFacturaServicioIface {

    public final static int CONSULTA_TODOS_LOS_ITEMS = 1;
    
    public String postFijoError;

    public String getPostFijoError() {
        return postFijoError;
    }
    
    public void setPostFijoError(String postFijoError) {
        this.postFijoError = postFijoError;
    }
     

    
    private CmAuditoriaFacturaRemoto getCmAuditoriaFacturaRemoto() throws Exception {
        return (CmAuditoriaFacturaRemoto) RemotoEJB.getEJBRemoto(("CmAuditoriaFacturaServicio"), CmAuditoriaFacturaRemoto.class.getName());
    }
     

    private CmDetalleRemoto getCmDetalleRemoto() throws Exception {
        return (CmDetalleRemoto) RemotoEJB.getEJBRemoto("CmDetalleServicio", CmDetalleRemoto.class.getName());
    }
    
    private CmFacturaRemoto getCmFacturaRemoto() throws Exception {
        return (CmFacturaRemoto) RemotoEJB.getEJBRemoto("CmFacturaServicio", CmFacturaRemoto.class.getName());
    }
   
    
    private CmAuditoriaAdjuntoRemoto getCmAuditoriaAdjuntoRemoto() throws Exception {
        return (CmAuditoriaAdjuntoRemoto) RemotoEJB.getEJBRemoto("CmAuditoriaAdjuntoServicio", CmAuditoriaAdjuntoRemoto.class.getName());
    }

    private CmGrupoUsuarioRemoto getCmGrupoUsuarioRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("CmGrupoUsuarioServicio", CmGrupoUsuarioRemoto.class.getName());
        return (CmGrupoUsuarioRemoto) object;
    }
    
    private CntContratoRemoto getCntContratoRemoto() throws Exception {
        return (CntContratoRemoto) RemotoEJB.getEJBRemoto("CntContratoServicio", CntContratoRemoto.class.getName());
    }
       
    @Override
    public void Accion(CmAuditoriaLiderFacturaBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_VER:
                    switch (bean.getDoAccion()) {
                        case CmAuditoriaLiderFacturaBean.ACCION_VER_AUDITORIA_FACTURA:
                            ver(bean);
                            obtenerEstadosAuditoria(bean);
                            obtenerNumeroContrato(bean);
                            break;
                        case CmAuditoriaLiderFacturaBean.ACCION_LISTAR_AUDITORIA_DETALLES:
                            listarAuditoriaDetalles(bean);
                            break;
                        case CmAuditoriaLiderFacturaBean.ACCION_LISTAR_GLOSA_FACTURA_DETALLES:
                            listarGlosaRespuestaDetalles(bean);
                            break;
                        case CmAuditoriaLiderFacturaBean.ACCION_VER_AUDITORIA_DETALLE:
                            verAuditoriaDetalle(bean);
                            break;
                       case CmAuditoriaLiderFacturaBean.ACCION_LISTAR_ADJUNTOS:
                            listarAuditoriaAdjuntos(bean);
                            break;
                    }
                    break;
          
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                    
                 case Url.ACCION_ADICIONAL_1:
                     switch (bean.getDoAccion()) {  
                         case Url.ACCION_GUARDAR:
                             asignarFacurasUsuario(bean);
                            break;
                     }
                     break;
                    
                case Url.ACCION_ADICIONAL_4:
                    switch (bean.getDoAccion()) {      
                         case CmAuditoriaLiderFacturaBean.ACCION_LISTAR_ADJUNTOS:
                            listarAuditoriaAdjuntos(bean);
                            break;
                         case CmAuditoriaLiderFacturaBean.ACCION_GUARDAR:
                             asignarFacurasUsuario(bean);
                            break;
                    }
                    break;

            }
        }
    }


    @Override
    public void cargaInicial(CmAuditoriaLiderFacturaBean bean) {
        try {
          
            List<CmGrupoUsuario> grupos =  getCmGrupoUsuarioRemoto().
               consultarPorUsuarioLista(bean.getConexion().getUsuario().getId());
            bean.getCmAuditoriaUsuarioActual().setGruposDeAccesoGrupos(grupos);

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public void obtenerNumeroContrato(CmAuditoriaLiderFacturaBean bean) {
       try {
           
           String numeroContrato = "";
           if (bean.getObjeto().getNumeroContrato() == null
                   || "".equals(bean.getObjeto().getNumeroContrato())) {

               CmRipsCarga ripcarga = bean.getObjeto().getCmRipCarga();
               if (ripcarga != null
                       && ripcarga.getCntContrato() != null
                       && ripcarga.getCntContrato().getId() != null
                       && ripcarga.getCntContrato().getId() > 0) {
                   CntContrato contrato;
                   try {
                       contrato = getCntContratoRemoto().consultarDatosBasicos(ripcarga.getCntContrato().getId());
                   } catch (Exception e) {
                       contrato = null;
                   }
                   numeroContrato = contrato != null ? contrato.getContrato() : "";
               }
               bean.getObjeto().setNumeroContrato(numeroContrato);
           }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    public void obtenerEstadosAuditoria(CmAuditoriaLiderFacturaBean bean) {
        
        if (bean.getObjeto().getCmGrupo() != null && bean.getObjeto().getCmGrupo().getId() != null) {
            
            int idGrupoFactura = bean.getObjeto().getCmGrupo().getId();
            Map< String, Integer> tiposEstadoAuditoria = new HashMap<>();
            bean.getCmAuditoriaUsuarioActual().setGrupoAccesoFactura( bean.getObjeto(),
                                                                      bean.getConexion().getUsuario().getId());     
            if (bean.getCmAuditoriaUsuarioActual().isUsuarioLider(idGrupoFactura)) {
                tiposEstadoAuditoria.put(CmFactura.getTipoAuditoriaStr(CmFactura.TIPO_AUDITORIA_SIN_AUDITORIA), 
                                         CmFactura.TIPO_AUDITORIA_SIN_AUDITORIA);
                tiposEstadoAuditoria.put(CmFactura.getTipoAuditoriaStr(CmFactura.TIPO_AUDITORIA_PERTINENCIA_TECNICA), 
                                         CmFactura.TIPO_AUDITORIA_PERTINENCIA_TECNICA);
                tiposEstadoAuditoria.put(CmFactura.getTipoAuditoriaStr(CmFactura.TIPO_AUDITORIA_PERTINENCIA_MEDICA), 
                                         CmFactura.TIPO_AUDITORIA_PERTINENCIA_MEDICA);
                
            }
            
            if (bean.getCmAuditoriaUsuarioActual().isUsuarioTecnico(idGrupoFactura)
                    || bean.getCmAuditoriaUsuarioActual().isUsuarioLider(idGrupoFactura)) {
                tiposEstadoAuditoria.put(CmFactura.getTipoAuditoriaStr(CmFactura.TIPO_AUDITORIA_DEVOLUCION_TECNICA), 
                                         CmFactura.TIPO_AUDITORIA_DEVOLUCION_TECNICA);
            }
            
            if (bean.getCmAuditoriaUsuarioActual().isUsuarioMedico(idGrupoFactura)
                    || bean.getCmAuditoriaUsuarioActual().isUsuarioLider(idGrupoFactura)) {
                tiposEstadoAuditoria.put(CmFactura.getTipoAuditoriaStr(CmFactura.TIPO_AUDITORIA_DEVOLUCION_MEDICA), 
                                         CmFactura.TIPO_AUDITORIA_DEVOLUCION_MEDICA);
            }
            
            if(!tiposEstadoAuditoria.isEmpty()){
                tiposEstadoAuditoria.put(CmFactura.getTipoAuditoriaStr(CmFactura.TIPO_AUDITORIA_CIERRE_AUDITORIA), 
                        CmFactura.TIPO_AUDITORIA_CIERRE_AUDITORIA);
            }
            bean.setListaTiposEstadoAuditoria(tiposEstadoAuditoria);
        } else {
            bean.addError("La factura no esta asociada a un grupo  para ser auditada.");
        }
        
    }

    
    private void listar(CmAuditoriaLiderFacturaBean bean) {
        try {
         
            
            boolean esConsultaGlobal = false;
            for (String key : bean.getParamConsulta().getFiltros().keySet()) {
                switch (key) {
                    case CmAuditoriaFacturaBeanUtil.CAMPO_FILTRO_NIT :
                    case CmAuditoriaFacturaBeanUtil.CAMPO_FILTRO_NUM_FACTURADO:
                        esConsultaGlobal = true;
                     break;
                }
                if(esConsultaGlobal){
                    break;
                }
            }
     
            // se busca en todas las facturas
            bean.getParamConsulta().setParametroConsulta2(null);
            if (esConsultaGlobal) {
                bean.getParamConsulta().setParametroConsulta2(esConsultaGlobal);
            }
            
            //se buscan las facturas propias
            bean.getParamConsulta().setParametroConsulta3(null);
            if ( ( bean.getParamConsulta().getFiltros() != null && bean.getParamConsulta().getFiltros().isEmpty())||
                 ( bean.getParamConsulta().getFiltros() != null && !esConsultaGlobal) ) {
                 bean.getParamConsulta().setParametroConsulta3(bean.getConexion().getUsuario().getId());
            }
            
            //super usuario se busca todos lasd facturas
            bean.getParamConsulta().setParametroConsulta4(null);
            if (bean.isAccionAdicional2()) {
                bean.getParamConsulta().setParametroConsulta4(bean.getConexion().getUsuario().getId());
            }
            
            //se restringe la busqueda para factura de tipo auditada-glosada y auditada
            bean.getParamConsulta().setParametroConsulta5(bean.getConexion().getUsuario().getId());
            
            bean.getParamConsulta().setCantidadRegistros(getCmAuditoriaFacturaRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getCmAuditoriaFacturaRemoto().consultarLista(bean.getParamConsulta()));

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void ver(CmAuditoriaLiderFacturaBean bean) {
        try {
            bean.setObjeto(getCmFacturaRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void verAuditoriaDetalle(CmAuditoriaLiderFacturaBean bean) {
        try {
            bean.setObjetoItemServicio(getCmDetalleRemoto().consultar(bean.getObjetoItemServicio().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
     private void listarAuditoriaDetalles(CmAuditoriaLiderFacturaBean bean) {
        try {
            bean.getParamConsultaServiciosAuditoria().setParametroConsulta1(bean.getObjeto().getId());
            bean.getParamConsultaServiciosAuditoria().setCantidadRegistros(getCmDetalleRemoto().consultarCantidadListaDetallesPorFactura(bean.getParamConsultaServiciosAuditoria()));
            bean.setRegistrosAuditoriaDetalles(getCmDetalleRemoto().consultarListaDetallesPorFactura(bean.getParamConsultaServiciosAuditoria()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    

     private void listarGlosaRespuestaDetalles(CmAuditoriaLiderFacturaBean bean) {
        try { 
            bean.getParamConsultaGlosaFacturaDetalles().setParametroConsulta1(bean.getObjeto().getId());
            bean.getParamConsultaGlosaFacturaDetalles().setCantidadRegistros(getCmDetalleRemoto().consultarCantidadListaDetallesPorFactura(bean.getParamConsultaGlosaFacturaDetalles()));
            bean.setRegistrosGlosaFacturaDetalle(getCmDetalleRemoto().consultarListaDetallesPorFactura(bean.getParamConsultaGlosaFacturaDetalles()));
        }  catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
  
    private void listarAuditoriaAdjuntos(CmAuditoriaLiderFacturaBean bean) {
        try { 
            bean.getParamConsultaAuditoriaAdjunto().setParametroConsulta1(bean.getObjeto().getId());
            bean.getParamConsultaAuditoriaAdjunto().setParametroConsulta2(CONSULTA_TODOS_LOS_ITEMS);
            bean.getParamConsultaAuditoriaAdjunto().setParametroConsulta4(CmAuditoriaAdjunto.TIPO_FACTURA);
            bean.getParamConsultaAuditoriaAdjunto().setCantidadRegistros(getCmAuditoriaAdjuntoRemoto().consultarCantidadPorDetalle(bean.getParamConsultaAuditoriaAdjunto()));
            bean.setRegistrosAuditoriaAdjuto(getCmAuditoriaAdjuntoRemoto().consultarListaPorDetalle(bean.getParamConsultaAuditoriaAdjunto()));
        }  catch (Exception e) {
            bean.addError(e.getMessage());
        }
    } 

   
    public void asignarFacurasUsuario(CmAuditoriaLiderFacturaBean bean){
        try{
            int tipo = bean.getTipoUsuarioAsignar();
            Usuario usuario = bean.getGnUsuario();
            String nombreUsuarioModifica = bean.getConexion().getUsuario().getNombre();
            String terminalModifica = bean.getConexion().getIp();
            for(CmFactura fac : bean.getListAsignar()){
                
                ParamConsulta paramConsulta = new ParamConsulta();
                paramConsulta.setParametroConsulta1(tipo);
                paramConsulta.setParametroConsulta2(usuario.getId());
                paramConsulta.setParametroConsulta3(fac.getId());
                paramConsulta.setParametroConsulta4(nombreUsuarioModifica);
                paramConsulta.setParametroConsulta5(terminalModifica);
                paramConsulta.setParametroConsulta6(new Date());
                
                getCmFacturaRemoto().actualizarUsuarioGestionaAsignado(paramConsulta);
            }
            
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
}

