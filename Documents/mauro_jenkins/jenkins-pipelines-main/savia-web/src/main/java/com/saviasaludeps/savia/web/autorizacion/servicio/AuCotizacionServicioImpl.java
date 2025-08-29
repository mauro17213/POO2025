package com.saviasaludeps.savia.web.autorizacion.servicio;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.anticipo.AntAnticipo;
import com.saviasaludeps.savia.dominio.anticipo.AntAnticipoItem;
import com.saviasaludeps.savia.dominio.anticipo.AntAnticipoValor;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Item;
import com.saviasaludeps.savia.dominio.autorizacion.AuCotizacion;
import com.saviasaludeps.savia.dominio.autorizacion.AuCotizacionItem;
import com.saviasaludeps.savia.dominio.autorizacion.AuItemBitacora;
import com.saviasaludeps.savia.dominio.autorizacion.AuSolicitudAdjunto;
import com.saviasaludeps.savia.dominio.maestro.MaMedicamento;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.anticipo.AnticipoItemRemoto;
import com.saviasaludeps.savia.negocio.anticipo.AnticipoRemoto;
import com.saviasaludeps.savia.negocio.anticipo.AnticipoValorRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo3ItemRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo3Remoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuCotizacionItemRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuCotizacionRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuItemBitacoraRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuSolicitudAdjuntoRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorSedeRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaMedicamentoRemoto;
import com.saviasaludeps.savia.web.autorizacion.bean.AuCotizacionBean;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuConstantes;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.commons.io.IOUtils;

public class AuCotizacionServicioImpl extends AccionesBO implements AuCotizacionServicioIface {

    private AuAnexo3ItemRemoto getAuAnexo3ItemRemoto() throws Exception {
        return (AuAnexo3ItemRemoto) RemotoEJB.getEJBRemoto("AuAnexo3ItemServicio", AuAnexo3ItemRemoto.class.getName());
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
        return (MaestroRemoto) object;
    }

    private AuAnexo3Remoto getAuAnexo3Remoto() throws Exception {
        return (AuAnexo3Remoto) RemotoEJB.getEJBRemoto("AuAnexo3Servicio", AuAnexo3Remoto.class.getName());
    }

    private AuCotizacionRemoto getAuCotizacionRemoto() throws Exception {
        return (AuCotizacionRemoto) RemotoEJB.getEJBRemoto("AuCotizacionServicio", AuCotizacionRemoto.class.getName());
    }

    private CntPrestadorRemoto getCntPrestadorRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }

    private CntPrestadorSedeRemoto getCntPrestadorSedeRemoto() throws Exception {
        return (CntPrestadorSedeRemoto) RemotoEJB.getEJBRemoto("CntPrestadorSedeServicio", CntPrestadorSedeRemoto.class.getName());
    }

    private AuCotizacionItemRemoto getAuCotizacionItemRemoto() throws Exception {
        return (AuCotizacionItemRemoto) RemotoEJB.getEJBRemoto("AuCotizacionItemServicio", AuCotizacionItemRemoto.class.getName());
    }

    private AuSolicitudAdjuntoRemoto getAuSolicitudAdjuntoRemoto() throws Exception {
        return (AuSolicitudAdjuntoRemoto) RemotoEJB.getEJBRemoto("AuSolicitudAdjuntoServicio", AuSolicitudAdjuntoRemoto.class.getName());
    }

    private AuItemBitacoraRemoto getAuItemBitacoraRemoto() throws Exception {
        return (AuItemBitacoraRemoto) RemotoEJB.getEJBRemoto("AuItemBitacoraServicio", AuItemBitacoraRemoto.class.getName());
    }

    private MaMedicamentoRemoto getMedicamentoRemoto() throws Exception {
        return (MaMedicamentoRemoto) RemotoEJB.getEJBRemoto(("MaMedicamentoServicio"), MaMedicamentoRemoto.class.getName());
    }

    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        return (AfiliadoRemoto) RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
    }
    
    private AnticipoRemoto getAnticipoRemoto() throws Exception {
        return (AnticipoRemoto) RemotoEJB.getEJBRemoto("AnticipoServicio", AnticipoRemoto.class.getName());
    }
    
    private AnticipoItemRemoto getAnticipoItemRemoto() throws Exception {
        return (AnticipoItemRemoto) RemotoEJB.getEJBRemoto("AnticipoItemServicio", AnticipoItemRemoto.class.getName());
    }
    
    private AnticipoValorRemoto getAnticipoValorRemoto() throws Exception {
        return (AnticipoValorRemoto) RemotoEJB.getEJBRemoto("AnticipoValorServicio", AnticipoValorRemoto.class.getName());
    }
    
    @Override
    public void Accion(AuCotizacionBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_VER:
                    ver(bean);
                    break;
                case Url.ACCION_CREAR:
                    break;
                case Url.ACCION_GUARDAR:
                    guardar(bean);
                    break;
                case Url.ACCION_EDITAR:
                case Url.ACCION_MODIFICAR:
                    modificar(bean);
                    break;
                case Url.ACCION_BORRAR:
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_ADICIONAL_1:
                            gestionar(bean);
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            verCotizacion(bean);
                            break;
                        case Url.ACCION_ADICIONAL_3:
                            verAntcipoItem(bean);
                            break;
                        default:
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_2:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_VER:
                            verGestionarAdjunto(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarGestionarAdjunto(bean);
                            break;
                        default:
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_3:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_VER:
                            break;
                        case Url.ACCION_GUARDAR:
                            rechazar(bean);
                            break;
                        default:
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_4:
                    verBitacoras(bean);
                    break;
                default:
                    break;
            }
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    @Override
    public void cargaInicial(AuCotizacionBean bean) {
        try {
            //CARGAR MAESTROS 

            bean.setHashTipoDocumentos((getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA)));
            bean.setHashUbicaciones(UbicacionSingle.getInstance().getHashUbicaciones());
            bean.setListaTipoTecnologia(AuConstantes.obtenerMaestroTipoTecnologias());
//            bean.setListaTipoAmbito(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_AMBITO));
//            if (!bean.getListaTipoAmbito().isEmpty()) {
//                List<Maestro> nuevaLista = new ArrayList();
//                for (Maestro maestro : bean.getListaTipoAmbito()) {
//                    if (!maestro.getValor().equalsIgnoreCase("M")) {
//                        nuevaLista.add(maestro);
//                    }
//                }
//                bean.setListaTipoAmbito(nuevaLista);
//            }
//            bean.setHashTipoAmbito(AuConstantes.obtenerHashMaestro(bean.getListaTipoAmbito())); 
            List<Maestro> listaTipoAmbito = getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_AMBITO);
            listaTipoAmbito.removeIf(maestro -> maestro.getValor().equalsIgnoreCase("M"));// removeIf elimina los elementos de la lista en una sola operacion
            bean.setListaTipoAmbito(listaTipoAmbito);
            bean.setHashTipoAmbito(
                    AuConstantes.obtenerHashMaestro(bean.getListaTipoAmbito())
            );

        } catch (Exception ex) {
            bean.addError("Error: " + ex.toString());
        }
    }

    public void listar(AuCotizacionBean bean) {
        try {
            long startTime = System.currentTimeMillis();
            long startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

            StringJoiner estados = new StringJoiner(",");
            estados.add(String.valueOf(AuAnexo3Item.ESTADO_SIN_COTIZACION))
                    .add(String.valueOf(AuAnexo3Item.ESTADO_CON_COTIZACION))
                    .add(String.valueOf(AuAnexo3Item.ESTADO_RECHAZO_COTIZACION))
                    .add(String.valueOf(AuAnexo3Item.ESTADO_CON_PAGO_ANTICIPADO));
            String estadosConcatenados = estados.toString();
            bean.getParamConsulta().setParametroConsulta1(estadosConcatenados);

            bean.getParamConsulta().setCantidadRegistros(getAuAnexo3ItemRemoto().consultarCantidadListaCotizacion(bean.getParamConsulta()));
            bean.setRegistros(getAuAnexo3ItemRemoto().consultarListaCotizacion(bean.getParamConsulta()));
            long endTime = System.currentTimeMillis();
            long endMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            System.out.println("Tiempo de ejecución del listar: " + (endTime - startTime) + " ms");
            System.out.println("Memoria consumida del listar: " + (endMemory - startMemory) + " bytes");
        } catch (Exception ex) {
            bean.addError("Error: " + ex.toString());
        }
    }

    public void ver(AuCotizacionBean bean) {
        try {
            bean.setObjetoAnexo3(getAuAnexo3Remoto().consultar(bean.getObjetoAnexo3().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public void gestionar(AuCotizacionBean bean) {
        try {
            bean.setListaTipoDocumentoArchivo(getMaestroRemoto().consultarPorTipo(MaestroTipo.AU_A3_ADJUNTO_TIPO));
            bean.setHashTipoDocumentoArchivo(AuConstantes.obtenerHashMaestro(bean.getListaTipoDocumentoArchivo()));

            bean.setObjeto(getAuAnexo3ItemRemoto().consultarParaCotizacion(bean.getObjeto().getId()));
            bean.setObjetoCotizacion(new AuCotizacion());
            if (bean.getObjeto().getTipoTecnologia() == AuCotizacion.TIPO_TECNOLOGIA) {
                bean.setTipoEditable(false);
            } else {
                bean.getObjetoCotizacion().setTipoTarifa(AuCotizacion.TIPO_TARIFA_PROPIA);
                bean.setTipoEditable(true);
                bean.setValorEditable(false);
                bean.getObjetoCotizacion().setPorcentajeNegociacion(BigDecimal.ZERO);
            }
            bean.getObjetoCotizacion().setAuSolicitudAdjuntosList(new ArrayList());
            bean.getObjetoCotizacion().setActivo(true);
            if (bean.validarTipoItemAgrupador(bean.getObjeto().getTipoTecnologia())) {
                //consulta cums del agrupador
                bean.setListaMedicamentoDeAgrupador(
                        getMedicamentoRemoto().consultarPorAgrupadorIdYEstadoRegSan(bean.getObjeto().getMaTecnologiaId())
                );
                bean.setHastMedicamentoDeAgrupador(obtenerHashMedicamento(bean.getListaMedicamentoDeAgrupador()));
            }
            if(bean.getObjeto().getAuAnexo3Id() != null){
                AntAnticipo anticipoAfiliadoYtecnologia = getAnticipoRemoto().consultarAnticipoAfiliadoYTecnologia(bean.getObjeto().getAuAnexo3Id().getAsegAfiliadoId().getId(), bean.getObjeto().getMaTecnologiaId());
                if(anticipoAfiliadoYtecnologia != null){
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ALERTA", "Hay un anticipo pago por esa tecnología para el afiliado"));
                }
            }
           
            AntAnticipo anticipoTecnologia = getAnticipoRemoto().consultarAnticipoByTecnologia(bean.getObjeto().getMaTecnologiaId());
            if(anticipoTecnologia != null){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ALERTA", "Hay un anticipo pago por esa tecnología"));
            }
            
        } catch (Exception ex) {
            bean.addError(ex.toString());
        }
    }

    private void guardar(AuCotizacionBean bean) {
        try {
            SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
            //funcionalidad para anticipo
            AntAnticipo anticipo = null;
            if(bean.getObjetoCotizacion().getAntAnticiposId() != null){
                anticipo = getAnticipoRemoto().consultar(bean.getObjetoCotizacion().getAntAnticiposId().getId());
                AntAnticipoItem item = getAnticipoItemRemoto().consultarItemAnticipoYTecnologia(bean.getObjetoCotizacion().getAntAnticiposId().getId(), bean.getObjeto().getMaTecnologiaId());
                Double valorDisponibleDouble = anticipo.getValorDisponible().doubleValue();
                Double valorTecnologiaDouble = item.getValorTecnologiaCotizada().doubleValue();
                if(valorTecnologiaDouble > valorDisponibleDouble){
                    bean.addError("El valor cotizado de la tecnología supera el valor disponible y no se puede asociar");
                    return;
                }
                
                AuAnexo3Item anexo3 = getAuAnexo3ItemRemoto().consultar(bean.getObjeto().getId());
                Double valorTecnologiaPorCantidad = item.getValorTecnologiaCotizada().doubleValue() * anexo3.getCantidadSolicitada();
                if(valorTecnologiaPorCantidad > valorDisponibleDouble){
                    bean.addError("Suepera el valor disponible por la cantidad y no se puede asociar");
                    return;
                }
            } 
            
            if(bean.getObjetoCotizacion().getValorTecnologia().longValue() < 1) {
                bean.addError("Debe ingresar un valor superior a 0");
                return;
            } 
            if (bean.getObjetoPrestadorSede() == null) {
                bean.addError("Indique la IPS asignada a la cotización");
                return;
            } 
            if (bean.getObjetoPrestadorSede().getId() == null) {
                bean.addError("Indique la IPS asignada a la cotización");
                return;
            } 
            if (validarEstadoItemCotizacion(bean)) {
                bean.addError("El ítem no tiene una acción valida, por favor refrescar la pagina");
                 return;
            } 
            if (bean.getObjetoCotizacion().getFechaInicioVigencia().after(bean.getObjetoCotizacion().getFechaFinVigencia())) {
                bean.addError("Fecha Inicio no puede ser superior a Fecha Final");
                return;
            } 
            if (bean.getObjeto().isPosfechado() && validarRangoFecha(bean)) {
                bean.addError("La fechas seleccionadas no entran dentro del rango posfechado");
                return;
            } 
            
            bean.auditoriaGuardar(bean.getObjetoCotizacion());
            //Asignar valores del anexo3item
            bean.getObjetoCotizacion().setCntPrestadorSede(bean.getObjetoPrestadorSede());
            bean.getObjetoCotizacion().setTipoTecnologia(bean.getObjeto().getTipoTecnologia());
            bean.getObjetoCotizacion().setMaTecnologiaCodigo(bean.getObjeto().getMaTecnologiaCodigo());
            bean.getObjetoCotizacion().setMaTecnologiaId(bean.getObjeto().getMaTecnologiaId());
            bean.getObjetoCotizacion().setMaTecnologiaValor(bean.getObjeto().getMaTecnologiaValor());
            bean.getObjetoCotizacion().setFuenteOrigen(AuCotizacion.FUENTE_ORIGEN_ANEXOS_3);
            if (bean.validarTipoItemAgrupador(bean.getObjeto().getTipoTecnologia())) {
                MaMedicamento medicamentoSel = bean.getHastMedicamentoDeAgrupador().get(bean.getObjetoCotizacion().getMaMedicamentoId());
                bean.getObjetoCotizacion().setMaMedicamentoCodigo(medicamentoSel.getCum());
                bean.getObjetoCotizacion().setMaMedicamentoValor(medicamentoSel.getDescripcionEstandarizada());
             }

            int idCotizacion = getAuCotizacionRemoto().insertar(bean.getObjetoCotizacion());
            bean.getObjetoCotizacion().setId(idCotizacion);
            // funcionalidad para anticipos
            if(bean.getObjetoCotizacion().getAntAnticiposId() != null){
                Double valorDisponibleDouble = (anticipo != null) ? anticipo.getValorDisponible().doubleValue(): 00;               
                AuAnexo3Item anexo3 = getAuAnexo3ItemRemoto().consultar(bean.getObjeto().getId());
                BigDecimal valor = bean.getObjetoCotizacion().getValorTecnologia().multiply(new BigDecimal(anexo3.getCantidadSolicitada()));
                Double nuevoValorDisponible = valorDisponibleDouble - valor.doubleValue();
                BigDecimal nueValorDisponible = new BigDecimal(nuevoValorDisponible);
                bean.getObjetoCotizacion().getAntAnticiposId().setValorDisponible(nueValorDisponible);
                bean.auditoriaModificar(bean.getObjetoCotizacion().getAntAnticiposId());
                getAnticipoRemoto().actualizarValorDisponible(bean.getObjetoCotizacion().getAntAnticiposId());              
                procesarAnticipoValor(bean, anticipo, valor, bean.getObjetoCotizacion().getAntAnticipoItemsId(), bean.getObjetoCotizacion(), AntAnticipoValor.TIPO_CREACION_COTIZACION);
            }
                
            //ivanegaa guardar cotizacion en items postfechados
            if (bean.getObjeto().isPosfechado()) {
                    Date fechaAutorizacion = new Date();
//                    List<AuAnexo3Item> listaPosfechado = getAuAnexo3ItemRemoto().listarItemsPosfechados(bean.getObjeto().getAuAnexo3Id().getId(), bean.getObjeto().getMaTecnologiaId());
                    for (AuAnexo3Item itemAnexo3 : bean.getListaPosfechados()) {
                        Date fechaValida;
                        fechaAutorizacion = itemAnexo3.getFechaPosfechado();
                        fechaValida = bean.getObjetoCotizacion().getFechaFinVigencia();
                        if (fechaValida != null) {
                            if (fechaAutorizacion.before(fechaValida)) {
                                //2023-09-27 jyperez validamos pago anticipado
                                if (bean.getObjetoCotizacion().isPagoAnticipado()) {
                                    itemAnexo3.setEstado(AuAnexo3Item.ESTADO_CON_PAGO_ANTICIPADO);
                                } else {
                                    itemAnexo3.setEstado(AuAnexo3Item.ESTADO_CON_COTIZACION);
                                }
                                bean.auditoriaModificar(itemAnexo3);
                                getAuAnexo3ItemRemoto().actualizarEstado(itemAnexo3);

                                AuCotizacionItem itemCotizacion = new AuCotizacionItem();
                                itemCotizacion.setAuAnexo3ItemId(itemAnexo3);
                                itemCotizacion.setAuCotizacionId(bean.getObjetoCotizacion());
                                bean.auditoriaGuardar(itemCotizacion);
                                getAuCotizacionItemRemoto().insertar(itemCotizacion);

                                guardarItemBitacora(itemAnexo3, bean, AuItemBitacora.ID_RESPUESTA_COTIZACION, bean.getObjetoCotizacion().getObservacion());
                                guardarItemBitacora(itemAnexo3, bean, AuItemBitacora.ID_CAMBIO_ESTADO, itemAnexo3.getEstadoStr());
                            }
                        }
                    }

            } else {
                //2023-09-27 jyperez validamos pago anticipado
                if (bean.getObjetoCotizacion().isPagoAnticipado()) {
                    bean.getObjeto().setEstado(AuAnexo3Item.ESTADO_CON_PAGO_ANTICIPADO);
                } else {
                     bean.getObjeto().setEstado(AuAnexo3Item.ESTADO_CON_COTIZACION);
                }
                bean.auditoriaModificar(bean.getObjeto());
                getAuAnexo3ItemRemoto().actualizarEstado(bean.getObjeto());
                AuCotizacionItem itemCotizacion = new AuCotizacionItem();
                itemCotizacion.setAuAnexo3ItemId(bean.getObjeto());
                itemCotizacion.setAuCotizacionId(bean.getObjetoCotizacion());
                bean.auditoriaGuardar(itemCotizacion);
                getAuCotizacionItemRemoto().insertar(itemCotizacion);
                guardarItemBitacora(bean.getObjeto(), bean, AuItemBitacora.ID_RESPUESTA_COTIZACION, bean.getObjetoCotizacion().getObservacion());
                guardarItemBitacora(bean.getObjeto(), bean, AuItemBitacora.ID_CAMBIO_ESTADO, bean.getObjeto().getEstadoStr());
                guardarItemBitacora(bean.getObjeto(), bean, AuItemBitacora.ID_VIGENCIA_COTIZACION, observacionVigenciaCotizacion(bean.getObjetoCotizacion().getFechaInicioVigencia(), bean.getObjetoCotizacion().getFechaFinVigencia(), idCotizacion));
            }
            if (!bean.getObjetoCotizacion().getAuSolicitudAdjuntosList().isEmpty()) {
                guardarAdjunto(bean);
            }
            bean.setObjetoCotizacion(new AuCotizacion());
            bean.addMensaje("Cotización realizada");
        } catch (Exception ex) {
            bean.addError(ex.toString());
        }
    }
    
    public void procesarAnticipoValor(AuCotizacionBean bean, AntAnticipo anticipo, BigDecimal valor, AntAnticipoItem item, AuCotizacion cotizacion, Integer tipoDevolucion){
        try{
            AntAnticipoValor antAnticipoValor = new AntAnticipoValor();
            bean.auditoriaGuardar(antAnticipoValor);
            antAnticipoValor.setAntAnticiposId(anticipo);
            antAnticipoValor.setAntAnticipoItemsId(item);
            antAnticipoValor.setAuCotizacionesId(cotizacion);
            antAnticipoValor.setDevolucion(Boolean.FALSE);
            antAnticipoValor.setTipoDevolucion(tipoDevolucion);
            antAnticipoValor.setValor(valor);
            antAnticipoValor.setId(getAnticipoValorRemoto().insertar(antAnticipoValor));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void modificar(AuCotizacionBean bean) {
        try {
            getAuCotizacionRemoto().actualizar(bean.getObjetoCotizacion());
            bean.addMensaje("Cotización actualizada");
        } catch (Exception ex) {
            bean.addError(ex.toString());
        }

    }

    private void verCotizacion(AuCotizacionBean bean) {
        try {
            bean.setObjeto(getAuAnexo3ItemRemoto().consultarParaCotizacion(bean.getObjeto().getId()));
            bean.setObjetoCotizacion(getAuCotizacionRemoto().consultarPorIdItemAnexo3(bean.getObjeto().getId()));
            bean.getObjetoCotizacion().setCntPrestadorSede(getCntPrestadorSedeRemoto().consultar(bean.getObjetoCotizacion().getCntPrestadorSede().getId()));
        } catch (Exception ex) {
            bean.addError(ex.toString());
        }
    }
    
    private void verAntcipoItem(AuCotizacionBean bean) {
        try {
            bean.setObjetoAnticipoItem(getAnticipoItemRemoto().consultarItemAnticipoYTecnologia(bean.getObjetoAnticipoItem().getAntAnticiposId().getId(), bean.getObjetoAnticipoItem().getMaTecnologiaId()));
            
        } catch (Exception ex) {
            bean.addError(ex.toString());
        }
    }
    
    @Override
    public void listarIPS(AuCotizacionBean bean) {
        try {
            bean.getParamConsulta2().getFiltros().put("cntContratosId.activo", true);
            bean.getParamConsulta2().setParametroConsulta1(bean.getConexion().getEmpresa().getCodigoHabilitacion());
            bean.getParamConsulta2().setCantidadRegistros(getCntPrestadorRemoto().consultarCantidadListaSede(bean.getParamConsulta2()));
            bean.setListaPrestadoresSedes(getCntPrestadorRemoto().consultarListaSede(bean.getParamConsulta2()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    @Override
    public void listarAnticipos(AuCotizacionBean bean) {
        try {
            if (bean.getParamConsulta(0).getFiltros() == null) {
                bean.getParamConsulta(0).setFiltros(new HashMap());
            }
            if(!bean.getConexion().getEmpresa().isAdministradora()){
                bean.getParamConsulta(0).setEmpresaId(bean.getConexion().getEmpresa().getId());
            }
            bean.getParamConsulta(0).getFiltros().put("idTecnologia", bean.getObjeto().getMaTecnologiaId()); 
            bean.getParamConsulta(0).getFiltros().put("estado", AntAnticipo.ESTADO_PAGADO);
            bean.getParamConsulta(0).getFiltros().put("borrado", 0); 
            bean.getParamConsulta(0).setCantidadRegistros(getAnticipoRemoto().consultarCantidadContizacionesLista(bean.getParamConsulta(0)));
            bean.setListaAnticipos(getAnticipoRemoto().consultarContizacionesLista(bean.getParamConsulta(0)));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void guardarAdjunto(AuCotizacionBean bean) {
        try {
            boolean error = false;
            String ruta = PropApl.getInstance().get(PropApl.AU_RUTA_ADJUNTOS_COTIZACIONES);
            //ruta = "C:\\Adjuntos\\";
            if (ruta == null || ruta.isEmpty()) {
                bean.addError("No esta configurada la ruta para los adjuntos");
                error = true;
            }
            if (error) {
                return;
            }
            //Generar nombre del archivo
            SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmssSSS");
            StringBuilder nombreArchivo = new StringBuilder();
            for (AuSolicitudAdjunto adjunto : bean.getObjetoCotizacion().getAuSolicitudAdjuntosList()) {
                if (adjunto.getId() == null) {
                    String tipoDocumento = adjunto.getMaeTipoArchivoCodigo();
                    nombreArchivo.append(tipoDocumento)
                            .append("_")
                            .append(sdf.format(new Date()));
                    nombreArchivo = new StringBuilder(Util.reemplazarCaracteresEspeciales(nombreArchivo.toString()));
                    adjunto.setArchivo(nombreArchivo.append(adjunto.getExtension()).toString());
                    adjunto.setOrigen(AuSolicitudAdjunto.ORIGEN_COTIZACION);
                    adjunto.setAuCotizacion(bean.getObjetoCotizacion());
                    Maestro maeTipoAdjunto = getMaestroRemoto().consultar(adjunto.getMaeTipoArchivoId());
                    adjunto.setMaeTipoArchivoCodigo(maeTipoAdjunto.getValor());
                    adjunto.setMaeTipoArchivoId(maeTipoAdjunto.getId());
                    adjunto.setMaeTipoArchivoValor(maeTipoAdjunto.getNombre());
                    //adjunto.setNombreArchivo(nombreArchivo.toString());
                    adjunto.setRuta(ruta);
                    File archivo = new File(ruta, adjunto.getArchivo());
                    OutputStream destino = new FileOutputStream(archivo);
                    IOUtils.copy(adjunto.getAdjuntoStream(), destino);
                    IOUtils.closeQuietly(adjunto.getAdjuntoStream());
                    IOUtils.closeQuietly(destino);
                    bean.auditoriaGuardar(adjunto);
                    adjunto.setAdjuntoStream(null);
                    adjunto.setId(getAuSolicitudAdjuntoRemoto().insertar(adjunto));
                }
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo guardando adjuntos");
        }
    }

    private void verGestionarAdjunto(AuCotizacionBean bean) {
        try {
            bean.setListaTipoDocumentoArchivo(getMaestroRemoto().consultarPorTipo(MaestroTipo.AU_A3_ADJUNTO_TIPO));
            bean.setHashTipoDocumentoArchivo(AuConstantes.obtenerHashMaestro(bean.getListaTipoDocumentoArchivo()));

            AuCotizacionItem item = getAuCotizacionItemRemoto().consultarPorIdAnexo3(bean.getObjeto().getId());
            bean.setObjetoCotizacion(getAuCotizacionRemoto().consultar(item.getAuCotizacionId().getId()));
//            bean.setListaAdjuntos(getAuCotizacionAdjuntoRemoto().listarAdjuntosByIdCotizacion(bean.getObjetoCotizacion().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al cargar gestionar adjuntos");
        }
    }

    private void guardarGestionarAdjunto(AuCotizacionBean bean) {
        try {
            if (!bean.getObjetoCotizacion().getAuSolicitudAdjuntosList().isEmpty()) {
                guardarAdjunto(bean);
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo guardando adjuntos");
        }
    }

    @Override
    public void borrarAdjunto(int idAdjunto, AuCotizacionBean bean) {
        try {
            getAuSolicitudAdjuntoRemoto().eliminar(idAdjunto);
        } catch (Exception e) {
            bean.addError("No se pudo eliminar el adjunto");
        }
    }

    @Override
    public BigDecimal consultarValorSoat(int idTarifario) {
        try {
            BigDecimal valor = getAuCotizacionRemoto().consultarValorSoat(idTarifario);
            return valor;
        } catch (Exception e) {
            return new BigDecimal(0);
        }
    }

    private void rechazar(AuCotizacionBean bean) {
        try {
            bean.setObjeto(getAuAnexo3ItemRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setEstado(AuAnexo3Item.ESTADO_RECHAZO_COTIZACION);
            bean.auditoriaModificar(bean.getObjeto());
            getAuAnexo3ItemRemoto().actualizar(bean.getObjeto());

            guardarItemBitacora(bean.getObjeto(), bean, AuItemBitacora.ID_RESPUESTA_COTIZACION, bean.getObservacion());
            guardarItemBitacora(bean.getObjeto(), bean, AuItemBitacora.ID_CAMBIO_ESTADO, bean.getObjeto().getEstadoStr());
            bean.addMensaje("Se realizo el rechazo del item " + bean.getObjeto().getId() + " con exito");
        } catch (Exception e) {
            bean.addError("Hubo un fallo rechazando, favor comunicarse con el administrador");
        }
    }

    private void verBitacoras(AuCotizacionBean bean) {
        try {
            bean.setListaBitacoras(getAuItemBitacoraRemoto().listarPorIdItem(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo consultando las bitacoras");
        }
    }

    private void guardarItemBitacora(AuAnexo3Item item, AuCotizacionBean bean, int tipo, String descripcion) throws Exception {
        //ivanegaa registro cambio estado
        AuItemBitacora bitacora = new AuItemBitacora();
        bitacora.setDescripcion(descripcion);
        bitacora.setTipo(tipo);
        bitacora.setAuAnexo3ItemId(item);
        bitacora.setEstado(item.getEstado());
        bean.auditoriaGuardar(bitacora);
        getAuItemBitacoraRemoto().insertar(bitacora);
    }

    private boolean validarRangoFecha(AuCotizacionBean bean) throws Exception {
        bean.setListaPosfechados(new ArrayList());
        boolean flag = false;
        List<AuAnexo3Item> listaPosfechado = getAuAnexo3ItemRemoto().listarItemsPosfechados(bean.getObjeto().getAuAnexo3Id().getId(), bean.getObjeto().getMaTecnologiaId());
        listaPosfechado = listaPosfechado.stream()
                .filter(p -> p.getEstado() == AuAnexo3Item.ESTADO_SIN_COTIZACION
                ).collect(Collectors.toList());
        if (!listaPosfechado.isEmpty()) {
            Date fechaPosInicial = listaPosfechado.get(0).getFechaPosfechado();
            Date fechaPosFinal = listaPosfechado.get(listaPosfechado.size() - 1).getFechaPosfechado();
            if (bean.getObjetoCotizacion().getFechaInicioVigencia().after(fechaPosFinal)
                    || bean.getObjetoCotizacion().getFechaFinVigencia().before(fechaPosInicial)) {
                flag = true;
            }
        }
        bean.setListaPosfechados(listaPosfechado);
        return flag;
    }

    private boolean validarEstadoItemCotizacion(AuCotizacionBean bean) throws Exception {
        bean.setObjeto(getAuAnexo3ItemRemoto().consultarParaCotizacion(bean.getObjeto().getId()));
        return !bean.validarEstadoSinCotizacion(bean.getObjeto().getEstado());
    }

    @Override
    public void consultarPosfechados(AuCotizacionBean bean) {
        try {
            bean.setListaPosfechados(getAuAnexo3ItemRemoto().listarItemsPosfechados(bean.getObjeto().getAuAnexo3Id().getId(), bean.getObjeto().getMaTecnologiaId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo consultando los posfechados");
        }
    }

    @Override
    public void completarAfiliado(AuCotizacionBean bean) {
        try {
            bean.getObjeto().getAuAnexo3Id().setAsegAfiliadoId(getAfiliadoRemoto().consultar(bean.getObjeto().getAuAnexo3Id().getAsegAfiliadoId().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al consultar el afiliado");
        }
    }
    
    @Override
    public void consultarMaestroParaListarAnticipos(AuCotizacionBean bean) {
        try {
            bean.setListaClasificacion(getMaestroRemoto().consultarPorTipo(MaestroTipo.ANT_CLASIFICACION));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al consultar el afiliado");
        }
    }
    
    @Override
    public int validarAfiliado(Integer idAsegAfiliado, AuCotizacionBean bean) {
        try {
            if (idAsegAfiliado.equals(bean.getObjeto().getAuAnexo3Id().getAsegAfiliadoId().getId())) {
                return 0;
            } else {
                bean.addError("No se puede seleccionar el afiliado ya que no le pertenece este anticipo ");
                return 1;
            }          
        } catch (Exception ex) {
            bean.addError("Hubo un fallo consultando el estado del afiliado, intentelo nuevamente");
            return 1;
        }
    }
    
    @Override
    public int consultarAnticipoValorDisponible(Integer idAnticipo, AuCotizacionBean bean) {
        int validate = 0;
        try {
            AntAnticipo anticipo = getAnticipoRemoto().consultar(idAnticipo);
            AntAnticipoItem item = getAnticipoItemRemoto().consultarItemAnticipoYTecnologia(idAnticipo, bean.getObjeto().getMaTecnologiaId());
            Double valorDisponibleDouble = anticipo.getValorDisponible().doubleValue();
            Double valorTecnologiaDouble = item.getValorTecnologiaCotizada().doubleValue();
             if(valorTecnologiaDouble > valorDisponibleDouble){
                    bean.addError("El valor cotizado de la tecnología supera el valor disponible y no se puede asociar");
                    validate = 1;
            }
            
        } catch (Exception ex) {
            bean.addError("Hubo un fallo consultando el estado del anticipo item, intentelo nuevamente"); 
        }
        return validate;
    }
    
    
    @Override
    public AntAnticipoItem consultarAticipoItem(Integer idAnticipo, Integer idTecnologia, AuCotizacionBean bean) {
        AntAnticipoItem item = null;
        try {
            item = getAnticipoItemRemoto().consultarItemAnticipoYTecnologia(idAnticipo, idTecnologia);
        } catch (Exception ex) {
            bean.addError("Hubo un fallo consultando el estado del anticipo item, intentelo nuevamente"); 
        }
        return item;
    }
    
    
    private HashMap<Integer, MaMedicamento> obtenerHashMedicamento(List<MaMedicamento> listaMedicamentos) {
        HashMap<Integer, MaMedicamento> hash = new HashMap();
        listaMedicamentos.forEach(medicamento -> {
            hash.put(medicamento.getId(), medicamento);
        });
        return hash;
    }
    
    public String observacionVigenciaCotizacion(Date fechaInicio, Date fechaFin, int idCotizacion){
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
        String strObservacion = "";
        if(fechaInicio != null && fechaFin != null && idCotizacion > 0){
            strObservacion = "FechaInicio:"+ formateador.format(fechaInicio)  + " | fechaFin:" +  formateador.format(fechaFin) + " | Cotización:" + idCotizacion;
        }
        return strObservacion;
    }
    
}
