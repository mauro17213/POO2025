package com.saviasaludeps.savia.web.juridico.servicio;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.juridico.CntjDocumento;
import com.saviasaludeps.savia.dominio.juridico.CntjTercero;
import com.saviasaludeps.savia.dominio.juridico.CntjTerceroUt;
import com.saviasaludeps.savia.negocio.administracion.UsuarioRemoto;
import com.saviasaludeps.savia.negocio.juridico.CtnjTerceroRemoto;
import com.saviasaludeps.savia.negocio.juridico.CtnjTerceroUtRemoto;
import com.saviasaludeps.savia.web.juridico.bean.TerceroBean;
import com.saviasaludeps.savia.web.juridico.utilidades.CntjConstantes;
import com.saviasaludeps.savia.web.singleton.MaestroSingle;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author idbohorquez
 */
public class TerceroServicioImpl extends AccionesBO implements TerceroServicioIface {
    
    private CtnjTerceroRemoto getCtnjTerceroRemoto() throws Exception {
        return (CtnjTerceroRemoto) RemotoEJB.getEJBRemoto("CntjTerceroServicio", CtnjTerceroRemoto.class.getName());
    }
    
    private UsuarioRemoto getUsuarioRemoto() throws Exception {
        return (UsuarioRemoto) RemotoEJB.getEJBRemoto("UsuarioServicio", UsuarioRemoto.class.getName());
    }
    
    private CtnjTerceroUtRemoto getCtnjTerceroUtRemoto() throws Exception {
        return (CtnjTerceroUtRemoto) RemotoEJB.getEJBRemoto("CntjTerceroUtServicio", CtnjTerceroUtRemoto.class.getName());
    }
    
    
    @Override
    public void Accion(TerceroBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break; 
                case Url.ACCION_VER:
                    ver(bean);
                    break; 
                case Url.ACCION_CREAR:
                    crear(bean);
                    break;
                case Url.ACCION_GUARDAR:
                    guardar(bean);
                    break;
                case Url.ACCION_EDITAR:
                    editar(bean);
                    break;
                case Url.ACCION_MODIFICAR:
                    modificar(bean);
                    break;  
                case Url.ACCION_ADICIONAL_1:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            listarUsuarios(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            listarTipoDocumento(bean);
                            break;
                        default:
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_2:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            listarUnionesTemporales(bean);
                            break;
                        case Url.ACCION_CREAR:
                            agregarIntegranteUt(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarIntegranteUt(bean);
                            break;
                        case Url.ACCION_BORRAR:
                            borrarIntegranteUt(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            guardarListaIntegrantes(bean);
                            break;
                    }
                    break;
            }
        } else {
            System.err.println("Sesion inactiva");
        }
    }
    
    @Override
    public void cargaInicial(TerceroBean bean) {
        try {
            bean.setListaTipoTercero(CntjConstantes.listaTipoTercero());
            bean.setListaMunicipios(UbicacionSingle.getInstance().getListaMunicipios());
            bean.setHashListaMunicipios(Util.convertToHashUbicacion(bean.getListaMunicipios()));
            bean.setListaNaturaleza(CntjConstantes.listaTipoNaturaleza());
            bean.setListaTiposDocumentoRepLegal(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_EMPRESA));
            bean.setHashTiposDocumentoRepLegal(Util.convertToHash(bean.getListaTiposDocumentoRepLegal()));
            bean.setListaTiposDocumentoPersona(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO));
            //bean.getListaTiposDocumentoPersona().addAll(bean.getListaTiposDocumentoRepLegal());
            bean.setHashTiposDocumentoPersona(Util.convertToHash(bean.getListaTiposDocumentoPersona()));
            bean.setListaetapaDesignacion(CntjConstantes.listaEtapadesignacion());
        } catch (Exception e) {
            Logger.getLogger(TerceroServicioImpl.class.getName()).log(Level.SEVERE, String.format("Se presento inconveniente al cargas información inicial. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al cargas información inicial.");
        }
    }

    private void listar(TerceroBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getCtnjTerceroRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistro(getCtnjTerceroRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            Logger.getLogger(TerceroServicioImpl.class.getName()).log(Level.SEVERE, String.format("Se presento inconveniente al listar información. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al listar información.");
        }
    }

    private void ver(TerceroBean bean) {
        try {
            bean.setObjeto(getCtnjTerceroRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            Logger.getLogger(TerceroServicioImpl.class.getName()).log(Level.SEVERE, String.format("Se presento inconveniente al consultar información. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al consultar información.");
        }
    }

    private void crear(TerceroBean bean) {
        try {
            bean.setObjeto(new CntjTercero());            
        } catch (Exception e) {
            Logger.getLogger(TerceroServicioImpl.class.getName()).log(Level.SEVERE, String.format("Se presento inconveniente al realizar el proceso. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al realizar el proceso.");
        }
    }
    
     private void guardar(TerceroBean bean) {
        try {
            Maestro tipoDoc = bean.getHashTiposDocumento().get(bean.getObjeto().getMaeTipoDocumentoId());
            if(tipoDoc != null){
                bean.getObjeto().setMaeTipoDocumentoCodigo(tipoDoc.getValor());
                bean.getObjeto().setMaeTipoDocumentoValor(tipoDoc.getDescripcion());
            }
            tipoDoc = bean.getHashTiposDocumentoRepLegal().get(bean.getObjeto().getMaeRepresentanteTipoDocumentoId());
            if(tipoDoc != null){
                bean.getObjeto().setMaeRepresentanteTipoDocumentoCodigo(tipoDoc.getValor());
                bean.getObjeto().setMaeRepresentanteTipoDocumentoValor(tipoDoc.getDescripcion());
            }
            
            if(bean.getObjeto().getTipoTercero() == null){
                bean.addError("Debe indicar el tipo de tercero a crear.");
            }            
            if(bean.getObjeto().getNaturalezaJuridica() == null){
                bean.addError("Debe indicar la naturaleza del registro.");
            }            
            if(bean.getObjeto().getMaeTipoDocumentoId() == 0){
                bean.addError("Debe indicar el tipo de documento.");
            }  
            if(bean.getObjeto().getMaeTipoDocumentoCodigo().isEmpty()){
                bean.addError("Debe indicar el código tipo documento.");
            } 
            if(bean.getObjeto().getMaeTipoDocumentoValor().isEmpty()){
                bean.addError("Debe indicar el valor tipo documento.");
            } 
            if(bean.getObjeto().getNumeroDocumento().isEmpty()){
                bean.addError("Debe indicar el número de documento.");
            }
            if(bean.getObjeto().getGnUbicacionId() == null){
                bean.addError("Debe indicar el municipio.");
            }
            
            if( bean.getObjeto().getRazonSocial() == null){
                bean.addError("Debe indicar el nombre o la razón social.");
            }
            
            boolean existe = bean.getRegistro().stream()
                .anyMatch(elemento -> Objects.equals(elemento.getTipoTercero(), bean.getObjeto().getTipoTercero()) && 
                        elemento.getNumeroDocumento().equals(bean.getObjeto().getNumeroDocumento()));
            if(existe){
                bean.addError("Ya existe un tercero del mismo tipo y documento de identidad.");
            }
            
            if (!bean.getErrores().isEmpty()) {
                return;
            }
            
            bean.auditoriaGuardar(bean.getObjeto());
            getCtnjTerceroRemoto().insertar(bean.getObjeto());
            bean.addMensaje("Se agregó el registro de manera exitosa.");            
        } catch (Exception e) {
            Logger.getLogger(TerceroServicioImpl.class.getName()).log(Level.SEVERE, String.format("Se presento inconveniente al guardar el registro. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al guardar el registro.");
        }
    }
     
     private void editar(TerceroBean bean) {
        try {
           bean.setObjeto(getCtnjTerceroRemoto().consultar(bean.getObjeto().getId()));
            listarTipoDocumento(bean);
        } catch (Exception e) {
            bean.addError("Se presento inconveniente al consultar plantilla. ");
        }
    }

    private void listarUsuarios(TerceroBean bean) {
        try {
            if (!bean.getConexion().getEmpresa().isAdministradora()) {
                bean.getParamConsulta(0).setEmpresaId(bean.getConexion().getEmpresa().getId());
            }
            bean.getParamConsulta(0).getFiltros().put("activo", "1");
            bean.getParamConsulta(0).setCantidadRegistros(getUsuarioRemoto().consultarCantidadLista(bean.getParamConsulta(0)));
            bean.setListaUsuario(getUsuarioRemoto().consultarLista(bean.getParamConsulta(0)));
        } catch (Exception e) {
            bean.addError("Se presento inconveniente al realizar el proceso.");
        }
    }

    private void listarTipoDocumento(TerceroBean bean) {
        try {
            if (bean.getObjeto().getTipoTercero().equals(CntjConstantes.TIPO_SUPERVISOR)) {
                bean.setListaTiposDocumento(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO));
                bean.setHashTiposDocumento(Util.convertToHash(bean.getListaTiposDocumento()));
            }else{
                bean.setListaTiposDocumento(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_EMPRESA));
                bean.setHashTiposDocumento(Util.convertToHash(bean.getListaTiposDocumento()));
            }
        } catch (Exception e) {
            bean.addError("Se presento inconveniente al realizar el proceso.");
        }
    }

    private void modificar(TerceroBean bean) {
        try {
            Maestro tipoDoc = bean.getHashTiposDocumento().get(bean.getObjeto().getMaeTipoDocumentoId());
            if(tipoDoc != null){
                bean.getObjeto().setMaeTipoDocumentoCodigo(tipoDoc.getValor());
                bean.getObjeto().setMaeTipoDocumentoValor(tipoDoc.getDescripcion());
            }
            tipoDoc = bean.getHashTiposDocumentoRepLegal().get(bean.getObjeto().getMaeRepresentanteTipoDocumentoId());
            if(tipoDoc != null){
                bean.getObjeto().setMaeRepresentanteTipoDocumentoCodigo(tipoDoc.getValor());
                bean.getObjeto().setMaeRepresentanteTipoDocumentoValor(tipoDoc.getDescripcion());
            }
            
            if(bean.getObjeto().getTipoTercero() == null){
                bean.addError("Debe indicar el tipo de tercero a crear.");
            }            
            if(bean.getObjeto().getNaturalezaJuridica() == null){
                bean.addError("Debe indicar la naturaleza del registro.");
            }            
            if(bean.getObjeto().getMaeTipoDocumentoId() == 0){
                bean.addError("Debe indicar el tipo de documento.");
            }  
            if(bean.getObjeto().getMaeTipoDocumentoCodigo().isEmpty()){
                bean.addError("Debe indicar el código tipo documento.");
            } 
            if(bean.getObjeto().getMaeTipoDocumentoValor().isEmpty()){
                bean.addError("Debe indicar el valor tipo documento.");
            } 
            if(bean.getObjeto().getNumeroDocumento().isEmpty()){
                bean.addError("Debe indicar el número de documento.");
            }
            if(bean.getObjeto().getGnUbicacionId() == null){
                bean.addError("Debe indicar el municipio.");
            }
            
            if(bean.getObjeto().getRazonSocial() == null){
                bean.addError("Debe indicar el nombre o la razón social.");
            }
            
            boolean existe = bean.getRegistro().stream()
                .anyMatch(elemento -> (Objects.equals(elemento.getTipoTercero(), bean.getObjeto().getTipoTercero()) && 
                        elemento.getNumeroDocumento().equals(bean.getObjeto().getNumeroDocumento()) && 
                        !Objects.equals(elemento.getId(), bean.getObjeto().getId())));
            if(existe){
                bean.addError("Ya existe un tercero del mismo tipo y documento de identidad.");
            }
            
            if (!bean.getErrores().isEmpty()) {
                return;
            }
            
            bean.auditoriaModificar(bean.getObjeto());
            getCtnjTerceroRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("Se modificó el registro de manera exitosa");            
        } catch (Exception e) {
            Logger.getLogger(TerceroServicioImpl.class.getName()).log(Level.SEVERE, String.format("Se presento inconveniente al guardar el registro. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al modificar el registro.");
        }
    }
    
    private void listarUnionesTemporales(TerceroBean bean) {
        try {
            //bean.getParamConsulta(0).setCantidadRegistros(getCtnjTerceroUtRemoto().consultarCantidadLista(bean.getParamConsulta(0)));
            bean.setRegistrosUnionTemporal(getCtnjTerceroUtRemoto().consultarPorTercero(bean.getObjeto().getId()));
            bean.setEliminadosUnionTemporal(new ArrayList<>());
        } catch (Exception e) {
            Logger.getLogger(TerceroServicioImpl.class.getName()).log(Level.SEVERE, String.format("Se presento inconveniente al agregar integrante unión temporal. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente consultar integrantes unión temporal.");
        }
    }

    private void agregarIntegranteUt(TerceroBean bean) {
        try {
            bean.setObjetoUt(new CntjTercero());
            bean.setListaTercerosUt(getCtnjTerceroRemoto().listaTercerosUt());
            bean.setHashlistaTercerosUt(CntjConstantes.convertTerceroToHash(bean.getListaTercerosUt()));
            bean.setObjetoTerceroUt(new CntjTerceroUt());
        } catch (Exception e) {
            Logger.getLogger(TerceroServicioImpl.class.getName()).log(Level.SEVERE, String.format("Se presento inconveniente al realizar la acción. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al realizar la acción.");
        }
    }

    private void guardarIntegranteUt(TerceroBean bean) {
        try {
            if(bean.getObjetoTerceroUt().getMaeTipoDocumentoId() == null){
                bean.addError("Debe seleccionar el tipo de documento.");
            }else{
                Maestro tipoDoc = bean.getHashTiposDocumentoPersona().get(bean.getObjetoTerceroUt().getMaeTipoDocumentoId());
                if (tipoDoc != null) {
                    bean.getObjetoTerceroUt().setMaeTipoDocumentoCodigo(tipoDoc.getValor());
                    bean.getObjetoTerceroUt().setMaeTipoDocumentoValor(tipoDoc.getDescripcion());
                }
            }
            
            if(bean.getObjetoTerceroUt().getMaeTipoDocumentoCodigo() == null){
                bean.addError("Debe indicar código de tipo documento.");
            }
            
            if(bean.getObjetoTerceroUt().getMaeTipoDocumentoValor() == null){
                bean.addError("Debe indicar valor de tipo documento.");
            }
            
            if(bean.getObjetoTerceroUt().getNumeroDocumento() == null){
                bean.addError("Debe indicar número de documento.");
            }
            
            if(bean.getObjetoTerceroUt().getRazonSocial() == null){
                bean.addError("Debe indicar la razón social.");
            }
            
            if(bean.getObjetoTerceroUt().getNaturalezaJuridica() == null){
                bean.addError("Debe indicar la naturaleza jurídica.");
            }
            
            if(bean.getObjetoTerceroUt().getPorcentajeParticipacion() == null){
                bean.addError("Debe indicar el porcentae de participación.");
            }
            
            if(bean.getObjetoTerceroUt().getPorcentajeParticipacion().compareTo(new BigDecimal("100")) > 0){
                bean.addError("El valor del porcentaje de participación no puedes ser superior a 100%");
            }
            
            boolean existe = bean.getRegistrosUnionTemporal().stream()
                .anyMatch(elemento -> Objects.equals(elemento.getMaeTipoDocumentoId(), bean.getObjetoTerceroUt().getMaeTipoDocumentoId()) && elemento.getNumeroDocumento().equals(bean.getObjetoTerceroUt().getNumeroDocumento()));
            if(existe){
                bean.addError("Ya existe un integrante con el mismo tipo y número de identificación.");
            }
            
            if(!bean.getErrores().isEmpty()){
                return;
            }
            
            if(bean.getRegistrosUnionTemporal().isEmpty()){
                bean.getObjetoTerceroUt().setId(-1);
            }else{
                Optional<CntjTerceroUt> maxItem = bean.getRegistrosUnionTemporal().stream().max((a, b) -> Integer.compare(a.getId(), b.getId()));
                int idadjunto = (maxItem.get().getId() + 1) * -1;
                bean.getObjetoTerceroUt().setId(idadjunto);
            }
                
            bean.getObjetoTerceroUt().setCntjTercero(bean.getObjeto());            
            bean.getObjetoTerceroUt().setBorrado(false);
            bean.auditoriaGuardar(bean.getObjetoTerceroUt());
//            getCtnjTerceroUtRemoto().insertar(bean.getObjetoTerceroUt());
            bean.getRegistrosUnionTemporal().add(bean.getObjetoTerceroUt());
            bean.setVerOpcionGuardar(true);
            bean.addMensaje("Registro agregado correctamente.");
        } catch (Exception e) {
            Logger.getLogger(TerceroServicioImpl.class.getName()).log(Level.SEVERE, String.format("Se presento inconveniente al agregar integrante unión temporal. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al agregar integrante unión temporal.");
        }
    }
    
    private void guardarListaIntegrantes(TerceroBean bean){
        try {
            List<CntjTerceroUt> listaNuevos = new ArrayList<>();
            if(bean.getRegistrosUnionTemporal().isEmpty() && bean.getEliminadosUnionTemporal().isEmpty()){
                bean.addError("No hay registros de unión temporal para guardar.");
            }else{
                listaNuevos = bean.getRegistrosUnionTemporal().stream()
                        .filter(obj -> obj.getId() != null && obj.getId() < 0)
                        .collect(Collectors.toList());
                if(listaNuevos.isEmpty() && bean.getEliminadosUnionTemporal().isEmpty()){
                    bean.addError("No hay nuevos registros de unión temporal para guardar.");
                }
            }   
            
            BigDecimal totalPorcentaje = bean.getRegistrosUnionTemporal().stream()
                    .map(CntjTerceroUt::getPorcentajeParticipacion) 
                    .filter(Objects::nonNull) 
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            
            if (totalPorcentaje.compareTo(new BigDecimal("100")) < 0) {
                bean.addError(String.format("La suma de los porcentajes no puede ser inferior a 100, el porcentaje actual es %s , verifique los porcentajes de cada participante, la suma de todos debe ser igual a 100",totalPorcentaje.toPlainString()));
            }
            
            if(!bean.getErrores().isEmpty()){
                return;
            }
            
            //eliminar registros 
            for(CntjTerceroUt it : bean.getEliminadosUnionTemporal()){
                getCtnjTerceroUtRemoto().borrar(it);
            }
            
            //insertar nuevos registros
            for (CntjTerceroUt it : listaNuevos) {
                getCtnjTerceroUtRemoto().insertar(it);
            }
            //listarUnionesTemporales(bean);
            bean.addMensaje("Registros guardados correctamente.");
        } catch (Exception e) {
            Logger.getLogger(TerceroServicioImpl.class.getName()).log(Level.SEVERE, String.format("Se presento inconveniente al guardar lista de integrantes unión temporal. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al guardar lista de integrantes unión temporal.");
        }
    }

    private void borrarIntegranteUt(TerceroBean bean) {
        try {
            if(bean.getObjetoTerceroUt() == null || bean.getObjetoTerceroUt().getId() == null){
                bean.addError("Debe indicar el registro a borrar.");
            }
                        
            if(!bean.getErrores().isEmpty()){
                return;
            }
            
            bean.getRegistrosUnionTemporal().removeIf(obj -> obj.getId().equals(bean.getObjetoTerceroUt().getId()));                       
            bean.getObjetoTerceroUt().setBorrado(true);
            bean.auditoriaModificar(bean.getObjetoTerceroUt());
            bean.getEliminadosUnionTemporal().add(bean.getObjetoTerceroUt()); 
//            getCtnjTerceroUtRemoto().borrar(bean.getObjetoTerceroUt());
            bean.setVerOpcionGuardar(true);
            bean.addMensaje("Registro borrado.");
        } catch (Exception e) {
            Logger.getLogger(TerceroServicioImpl.class.getName()).log(Level.SEVERE, String.format("Se presento inconveniente al borrar registro. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al borrar registro.");
        }
    }

    

}
