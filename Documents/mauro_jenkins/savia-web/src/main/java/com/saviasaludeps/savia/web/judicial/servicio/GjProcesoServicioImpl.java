package com.saviasaludeps.savia.web.judicial.servicio;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.judicial.GjAbogado;
import com.saviasaludeps.savia.dominio.judicial.GjProceso;
import com.saviasaludeps.savia.dominio.judicial.GjProcesoAdjunto;
import com.saviasaludeps.savia.dominio.judicial.GjProcesoGarantia;
import com.saviasaludeps.savia.dominio.judicial.GjProcesoHistorico;
import com.saviasaludeps.savia.dominio.judicial.GjProcesoTercero;
import com.saviasaludeps.savia.dominio.judicial.GjTercero;
import com.saviasaludeps.savia.dominio.judicial.GjTerceroContacto;
import com.saviasaludeps.savia.dominio.tutela.TuJuzgado;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.judicial.GjAbogadoRemoto;
import com.saviasaludeps.savia.negocio.judicial.GjProcesoAbogadoRemoto;
import com.saviasaludeps.savia.negocio.judicial.GjProcesoAdjuntoRemoto;
import com.saviasaludeps.savia.negocio.judicial.GjProcesoGarantiaRemoto;
import com.saviasaludeps.savia.negocio.judicial.GjProcesoHistoricoRemoto;
import com.saviasaludeps.savia.negocio.judicial.GjProcesoPretencionRemoto;
import com.saviasaludeps.savia.negocio.judicial.GjProcesoRemoto;
import com.saviasaludeps.savia.negocio.judicial.GjProcesoTerceroRemoto;
import com.saviasaludeps.savia.negocio.judicial.GjTerceroContactoRemoto;
import com.saviasaludeps.savia.negocio.judicial.GjTerceroRemoto;
import com.saviasaludeps.savia.negocio.tutela.TuJuzgadoRemoto;
import com.saviasaludeps.savia.web.judicial.bean.GjProcesoBean;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.tutela.bean.TuTutelaBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author bsgomez
 *
 */
public class GjProcesoServicioImpl extends AccionesBO implements GjProcesoServicioIface {

    private TuJuzgadoRemoto getTuJuzgadoRemoto() throws Exception {
        return (TuJuzgadoRemoto) RemotoEJB.getEJBRemoto("TuJuzgadoServicio", TuJuzgadoRemoto.class.getName());
    }

    private GjTerceroRemoto getGjTerceroRemoto() throws Exception {
        return (GjTerceroRemoto) RemotoEJB.getEJBRemoto("GjTerceroServicio", GjTerceroRemoto.class.getName());
    }

    private GjAbogadoRemoto getGjAbogadoRemoto() throws Exception {
        return (GjAbogadoRemoto) RemotoEJB.getEJBRemoto("GjAbogadoServicio", GjAbogadoRemoto.class.getName());
    }

    private GjProcesoPretencionRemoto getGjProcesoPretencionRemoto() throws Exception {
        return (GjProcesoPretencionRemoto) RemotoEJB.getEJBRemoto("GjProcesoPretencionServicio", GjProcesoPretencionRemoto.class.getName());
    }

    private GjTerceroContactoRemoto getGjTerceroContactoRemoto() throws Exception {
        return (GjTerceroContactoRemoto) RemotoEJB.getEJBRemoto("GjTerceroContactoServicio", GjTerceroContactoRemoto.class.getName());
    }

    private GjProcesoHistoricoRemoto getGjProcesoHistoricoRemoto() throws Exception {
        return (GjProcesoHistoricoRemoto) RemotoEJB.getEJBRemoto("GjProcesoHistoricoServicio", GjProcesoHistoricoRemoto.class.getName());
    }

    private GjProcesoAbogadoRemoto getGjProcesoAbogadoRemoto() throws Exception {
        return (GjProcesoAbogadoRemoto) RemotoEJB.getEJBRemoto("GjProcesoAbogadoServicio", GjProcesoAbogadoRemoto.class.getName());
    }

    private GjProcesoTerceroRemoto getGjProcesoTerceroRemoto() throws Exception {
        return (GjProcesoTerceroRemoto) RemotoEJB.getEJBRemoto("GjProcesoTerceroServicio", GjProcesoTerceroRemoto.class.getName());
    }

    private GjProcesoGarantiaRemoto getGjProcesoGarantiaRemoto() throws Exception {
        return (GjProcesoGarantiaRemoto) RemotoEJB.getEJBRemoto("GjProcesoGarantiaServicio", GjProcesoGarantiaRemoto.class.getName());
    }

    private GjProcesoRemoto getGjProcesoRemoto() throws Exception {
        return (GjProcesoRemoto) RemotoEJB.getEJBRemoto("GjProcesoServicio", GjProcesoRemoto.class.getName());
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
        return (MaestroRemoto) object;
    }

    private GjProcesoAdjuntoRemoto getGjProcesoAdjuntoRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("GjProcesoAdjuntoServicio", GjProcesoAdjuntoRemoto.class.getName());
        return (GjProcesoAdjuntoRemoto) object;
    }

    @Override
    public void cargasInicial(GjProcesoBean bean) {
        try {
//maeTopoDocumento
            bean.setListaTiposDocumento(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO));
            bean.setHashTiposDocumento(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO));
//gnUbicaciones
            bean.setUbicaciones(UbicacionSingle.getInstance().getListaMunicipios());
            bean.setUbicacionesRecursiva(UbicacionSingle.getInstance().getHashMunicipios());
//gnTipoContacto 
            bean.setListaTipoContacto(getMaestroRemoto().consultarPorTipo(MaestroTipo.GJ_TIPO_CONTACTO));
            bean.setHashTipoContacto(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GJ_TIPO_CONTACTO));
//maeJurisdiccion
            bean.setListaJurisdiccion(getMaestroRemoto().consultarPorTipo(MaestroTipo.GJ_JURISDICCION));
            bean.setHashJurisdiccion(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GJ_JURISDICCION));
//maeClase
            bean.setListaClase(getMaestroRemoto().consultarPorTipo(MaestroTipo.GJ_CLASE));
            bean.setHashClase(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GJ_CLASE));
//maeCalidad
            bean.setListaCalidadAcatua(getMaestroRemoto().consultarPorTipo(MaestroTipo.GJ_CALIDAD));
            bean.setHashCalidadAcatua(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GJ_CALIDAD));
//maeInstancia
            bean.setListaInstancia(getMaestroRemoto().consultarPorTipo(MaestroTipo.GJ_INSTANCIA));
            bean.setHashInstancia(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GJ_INSTANCIA));
//maeActuacionTerminacion
            bean.setListaActuacionTerminacion(getMaestroRemoto().consultarPorTipo(MaestroTipo.GJ_ACTUACION_TERMINACION));
            bean.setHashActuacionTerminacion(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GJ_ACTUACION_TERMINACION));
//maeMedicaCautelar
            bean.setListaMedicaCautelar(getMaestroRemoto().consultarPorTipo(MaestroTipo.GJ_MEDIDA_CAUTELAR));
            bean.setHashMedicaCautelar(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GJ_MEDIDA_CAUTELAR));
//maePretencion
            bean.setListaPretencion(getMaestroRemoto().consultarPorTipo(MaestroTipo.GJ_PRETENCION));
            bean.setHashPretencion(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GJ_PRETENCION));
//
        } catch (Exception e) {
            bean.addError("No fue posible cargar las listas de apoyo");
        }
    }

    @Override
    public void Accion(GjProcesoBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    switch (bean.getDoAccion()) {
                        case GjProcesoBean.ACCION_LISTAR3:
                            listar(bean);
                            break;
                        case Url.ACCION_LISTAR:
                            consultarHistorial(bean);
                            break;
                        case GjProcesoBean.ACCION_VER3:
                            consultarAdjunto(bean);
                            break;
                    }
                    break;
                case Url.ACCION_VER:
                    switch (bean.getDoAccion()) {
                        case GjProcesoBean.ACCION_VER1:
                            consultarSeguimiento(bean);
                            break;
                        case Url.ACCION_VER:
                            ver(bean);
                            listarContactosTerceros(bean);
                            validarGarantias(bean);
                            break;
                    }
                    break;
                case Url.ACCION_CREAR:
                    crear(bean);
                    listaClaseNull(bean);
                    break;
                case Url.ACCION_GUARDAR:
                    guardar(bean);
                    break;
                case Url.ACCION_EDITAR:
                    editar(bean);
                    listarContactosTerceros(bean);
                    listarClaseDes(bean);
                    break;
                case Url.ACCION_MODIFICAR:
                    modificar(bean);
                    break;
                case Url.ACCION_BORRAR:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_BORRAR:
                            borrar(bean);
                            break;
                        case TuTutelaBean.BORRAR_ADJUNTO:
                            borrarAdjunto(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (bean.getDoAccion()) {
                        case GjProcesoBean.ACCION_INICIO_GESTION:
                            gestionarProceso(bean);
                            listarContactosTerceros(bean);
                            listarClaseDes(bean);
                            validarGarantias(bean);
                            break;
                        case GjProcesoBean.ACCION_INSERTAR_GESTION:
                            registroGestion(bean);
                            break;
                    }
                    break;
            }
//            cargas(bean);
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    private void listar(GjProcesoBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getGjProcesoRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getGjProcesoRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(GjProcesoBean bean) {
        try {
            bean.setObjeto(getGjProcesoRemoto().consultar(bean.getObjeto().getId()));
            listarTerceros(bean);
            bean.getGjTerceroContacto().setGjTercerosId(new GjTercero(bean.getObjeto().getGjProcesoTercero().getGjTercerosId().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void listarTerceros(GjProcesoBean bean) {
        try {
            if (bean.getObjeto().getId() != null) {
                bean.setRegistrosProcesoTerceros(getGjProcesoTerceroRemoto().consultarListaPorIdProceso(bean.getObjeto().getId()));
            }
        } catch (Exception ex) {
            bean.setRegistrosProcesoTerceros(new ArrayList<>());
        }
    }

    private void crear(GjProcesoBean bean) {
        try {
            bean.setObjeto(new GjProceso());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(GjProcesoBean bean) {
        try {
            bean.setObjeto(getGjProcesoRemoto().consultar(bean.getObjeto().getId()));
            listarTerceros(bean);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void validarGarantias(GjProcesoBean bean) {
        int idProceso = bean.getObjeto().getId();
        List<GjProcesoGarantia> garantias = null;
        try {
            garantias = getGjProcesoGarantiaRemoto().consultarListaPorIdProceso(idProceso);
        } catch (Exception ex) {
            // manejo de excepción
        }
        if (garantias != null && !garantias.isEmpty()) {
            bean.setRegistrosProcesoGarantias(garantias);
            bean.setDisabeBotonAgregarGarantia(true);
            bean.setDisablegarantia(true);
        } else {
            bean.setDisabeBotonAgregarGarantia(true);
            bean.setDisablegarantia(false);
        }
    }

    private void gestionarProceso(GjProcesoBean bean) {
        try {
            bean.setObjeto(getGjProcesoRemoto().consultar(bean.getObjeto().getId()));
            listarTerceros(bean);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(GjProcesoBean bean) {
        try {

            int idterproces = 0;
            GjProceso gjProceso = bean.getObjeto();

            bean.auditoriaGuardar(bean.getObjeto());

            Maestro jurisdiccion = bean.getHashJurisdiccion().get(bean.getObjeto().getMaeJurisdiccionId());
            if (jurisdiccion != null) {
                bean.getObjeto().setMaeJurisdiccionCodigo(jurisdiccion.getValor());
                bean.getObjeto().setMaeJurisdiccionValor(jurisdiccion.getNombre());
            }
//            Maestro tipo = bean.getHashTipo().get(bean.getObjeto().getGjProcesoAdjunto().getMaeTipoId());
//            Maestro instancia = bean.getHashInstancia().get(bean.getObjeto().getMaeInstanciaId());
            Maestro tipoDocumentoReprecentante = bean.getHashTiposDocumento().get(bean.getObjeto().getGjProcesoTercero().getMaeTipoDocumentorepreId());
            if (tipoDocumentoReprecentante != null) {
                bean.getObjeto().getGjProcesoTercero().setMaeTipoDocumentoCodigo(tipoDocumentoReprecentante.getValor());
                bean.getObjeto().getGjProcesoTercero().setMaeTipoDocumentoValor(tipoDocumentoReprecentante.getNombre());
            }
            Maestro claseDescepcion = bean.getHashClaseDescripcion().get(bean.getObjeto().getMaeClaseDescripcionId());
            if (claseDescepcion != null) {
                bean.getObjeto().setMaeClaseDescripcionCodigo(claseDescepcion.getValor());
                bean.getObjeto().setMaeClaseDescripcionValor(claseDescepcion.getNombre());
            }
            Maestro clase = bean.getHashClase().get(bean.getObjeto().getMaeClaseId());
            if (clase != null) {
                bean.getObjeto().setMaeClaseCodigo(clase.getValor());
                bean.getObjeto().setMaeClaseValor(clase.getNombre());
            }
            Maestro pretenDes = bean.getHashPretencion().get(bean.getObjeto().getGjProcesoPretencion().getMaePretencionId());
            if (pretenDes != null) {
                bean.getObjeto().getGjProcesoPretencion().setMaePretencionCodigo(pretenDes.getValor());
                bean.getObjeto().getGjProcesoPretencion().setMaePretencionValor(pretenDes.getNombre());
            }
            bean.getObjeto().setId(getGjProcesoRemoto().insertar(bean.getObjeto()));

            if (gjProceso.getGjProcesoAbogado().getGjAbogadosId().getId() != null || gjProceso.getGjProcesoAbogado().getGjAbogadosId().getId() >= 0) {

                bean.getObjeto().setGjProceso(gjProceso);
                idterproces = gjProceso.getId();

                bean.getObjeto().setId(idterproces);
                gjProceso.getGjProcesoTercero().setGjProcesosId(gjProceso);
                gjProceso.getGjProcesoAbogado().setGjProcesosId(gjProceso);
                gjProceso.getGjProcesoPretencion().setGjProcesosId(gjProceso);
                gjProceso.getGjProcesoHistorico().setGjProcesosId(gjProceso);

                guardarProcesoTerceros(bean);

                bean.auditoriaGuardar(bean.getObjeto().getGjProceso().getGjProcesoPretencion());
                gjProceso.setGjProcesoPretencion(bean.getObjeto().getGjProcesoPretencion());
                bean.getObjeto().getGjProcesoPretencion().setId(getGjProcesoPretencionRemoto().insertar(gjProceso.getGjProcesoPretencion()));

                bean.auditoriaGuardar(bean.getObjeto().getGjProceso().getGjProcesoAbogado());
                gjProceso.setGjProcesoAbogado(bean.getObjeto().getGjProcesoAbogado());
                bean.getObjeto().getGjProcesoAbogado().setId(getGjProcesoAbogadoRemoto().insertar(gjProceso.getGjProcesoAbogado()));

                bean.auditoriaGuardar(bean.getObjeto().getGjProceso().getGjProcesoHistorico());
                gjProceso.setGjProcesoHistorico(bean.getObjeto().getGjProcesoHistorico());
                bean.getObjeto().getGjProcesoHistorico().setId(getGjProcesoHistoricoRemoto().insertar(gjProceso.getGjProcesoHistorico()));

            }

//            bean.getObjeto().setId(getGjProcesoRemoto().insertar(bean.getObjeto()));
            if (!bean.isError()) {
                bean.addMensaje("El Proceso Se Creo  De Manera Exitosa");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public void guardarProcesoTerceros(GjProcesoBean bean) {
        try {
            for (GjProcesoTercero contacto : bean.getRegistrosProcesoTerceros()) {
                if (contacto.getId() == null) {
                    contacto.setGjProcesosId(new GjProceso(bean.getObjeto().getId()));
                    bean.auditoriaGuardar(contacto);
                    getGjProcesoTerceroRemoto().insertar(contacto);
                }
            }
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    private void modificar(GjProcesoBean bean) {
        try {
            int idterproces = 0;
            GjProceso gjProceso = bean.getObjeto();

            bean.auditoriaModificar(gjProceso);

            Maestro jurisdiccion = bean.getHashJurisdiccion().get(bean.getObjeto().getMaeJurisdiccionId());
            if (jurisdiccion != null) {
                bean.getObjeto().setMaeJurisdiccionCodigo(jurisdiccion.getValor());
                bean.getObjeto().setMaeJurisdiccionValor(jurisdiccion.getNombre());
            }
            Maestro tipoDocumentoReprecentante = bean.getHashTiposDocumento().get(bean.getObjeto().getGjProcesoTercero().getMaeTipoDocumentorepreId());
            if (tipoDocumentoReprecentante != null) {
                bean.getObjeto().getGjProcesoTercero().setMaeTipoDocumentoCodigo(tipoDocumentoReprecentante.getValor());
                bean.getObjeto().getGjProcesoTercero().setMaeTipoDocumentoValor(tipoDocumentoReprecentante.getNombre());
            }
            Maestro claseDescepcion = bean.getHashClaseDescripcion().get(bean.getObjeto().getMaeClaseDescripcionId());
            if (claseDescepcion != null) {
                bean.getObjeto().setMaeClaseDescripcionCodigo(claseDescepcion.getValor());
                bean.getObjeto().setMaeClaseDescripcionValor(claseDescepcion.getNombre());
            }
            Maestro clase = bean.getHashClase().get(bean.getObjeto().getMaeClaseId());
            if (clase != null) {
                bean.getObjeto().setMaeClaseCodigo(clase.getValor());
                bean.getObjeto().setMaeClaseValor(clase.getNombre());
            }
            Maestro pretenDes = bean.getHashPretencion().get(bean.getObjeto().getGjProcesoPretencion().getMaePretencionId());
            if (pretenDes != null) {
                bean.getObjeto().getGjProcesoPretencion().setMaePretencionCodigo(pretenDes.getValor());
                bean.getObjeto().getGjProcesoPretencion().setMaePretencionValor(pretenDes.getNombre());
            }
            Maestro calidadActua = bean.getHashCalidadAcatua().get(bean.getObjeto().getGjProcesoTercero().getMaeCalidadActuaId());
            if (calidadActua != null) {
                bean.getObjeto().getGjProcesoTercero().setMaeCalidadActuaCodigo(calidadActua.getValor());
                bean.getObjeto().getGjProcesoTercero().setMaeCalidadActuaValor(calidadActua.getNombre());
            }

            getGjProcesoRemoto().actualizar(gjProceso);

            if (bean.getObjeto().getGjProcesoAbogado().getGjAbogadosId().getId() != null || bean.getObjeto().getGjProcesoAbogado().getGjAbogadosId().getId() >= 0) {

                idterproces = bean.getObjeto().getId();
                bean.getObjeto().setGjProcesoTercero(gjProceso.getGjProcesoTercero());
                bean.getObjeto().getGjProcesoTercero().setGjProcesosId(gjProceso);
                bean.getObjeto().getGjProcesoTercero().setGjTercerosId(gjProceso.getGjProcesoTercero().getGjTercerosId());
                bean.getObjeto().setGjProcesoAbogado(gjProceso.getGjProcesoAbogado());
                bean.getObjeto().getGjProcesoAbogado().setGjProcesosId(gjProceso);
                bean.getObjeto().getGjProcesoAbogado().setGjAbogadosId(gjProceso.getGjProcesoAbogado().getGjAbogadosId());
                bean.getObjeto().setGjProcesoPretencion(gjProceso.getGjProcesoPretencion());
                bean.getObjeto().getGjProcesoPretencion().setGjProcesosId(gjProceso);
                bean.getObjeto().getGjProcesoPretencion().setGjProcesosId(gjProceso.getGjProcesoPretencion().getGjProcesosId());
//                bean.auditoriaGuardar(gjProceso.getGjProcesoTercero());
//                getGjProcesoTerceroRemoto().actualizar(gjProceso.getGjProcesoTercero());
                bean.auditoriaGuardar(gjProceso.getGjProcesoPretencion());
                getGjProcesoPretencionRemoto().actualizar(gjProceso.getGjProcesoPretencion());

                bean.auditoriaGuardar(gjProceso.getGjProcesoAbogado());
                getGjProcesoAbogadoRemoto().actualizar(gjProceso.getGjProcesoAbogado());

            }

//            bean.getObjeto().setId(getGjProcesoRemoto().insertar(bean.getObjeto()));
            if (!bean.isError()) {
                bean.addMensaje("Se Edito El Proceso Con Id (" + idterproces + ") De Manera Exitosa \n ");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void consultarSeguimiento(GjProcesoBean bean) {
        try {
            bean.getObjeto().setGjProcesoHistorico(getGjProcesoHistoricoRemoto().consultar(bean.getObjeto().getGjProcesoHistorico().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void registroGestion(GjProcesoBean bean) {
        try {
            int idproces = 0;
            int idProcesh = 0;

            Maestro jurisdiccion = bean.getHashJurisdiccion().get(bean.getObjeto().getMaeJurisdiccionId());
            if (jurisdiccion != null) {
                bean.getObjeto().setMaeJurisdiccionCodigo(jurisdiccion.getValor());
                bean.getObjeto().setMaeJurisdiccionValor(jurisdiccion.getNombre());
            }
//            Maestro tipo = bean.getHashTipo().get(bean.getObjeto().getGjProcesoAdjunto().getMaeTipoId());
//            Maestro instancia = bean.getHashInstancia().get(bean.getObjeto().getMaeInstanciaId());
            Maestro tipoDocumentoReprecentante = bean.getHashTiposDocumento().get(bean.getObjeto().getGjProcesoTercero().getMaeTipoDocumentorepreId());
            if (tipoDocumentoReprecentante != null) {
                bean.getObjeto().getGjProcesoTercero().setMaeTipoDocumentoCodigo(tipoDocumentoReprecentante.getValor());
                bean.getObjeto().getGjProcesoTercero().setMaeTipoDocumentoValor(tipoDocumentoReprecentante.getNombre());
            }
            Maestro claseDescepcion = bean.getHashClaseDescripcion().get(bean.getObjeto().getMaeClaseDescripcionId());
            if (claseDescepcion != null) {
                bean.getObjeto().setMaeClaseDescripcionCodigo(claseDescepcion.getValor());
                bean.getObjeto().setMaeClaseDescripcionValor(claseDescepcion.getNombre());
            }
            Maestro clase = bean.getHashClase().get(bean.getObjeto().getMaeClaseId());
            if (clase != null) {
                bean.getObjeto().setMaeClaseCodigo(clase.getValor());
                bean.getObjeto().setMaeClaseValor(clase.getNombre());
            }
            Maestro pretenDes = bean.getHashPretencion().get(bean.getObjeto().getGjProcesoPretencion().getMaePretencionId());
            if (pretenDes != null) {
                bean.getObjeto().getGjProcesoPretencion().setMaePretencionCodigo(pretenDes.getValor());
                bean.getObjeto().getGjProcesoPretencion().setMaePretencionValor(pretenDes.getNombre());
            }
            Maestro calidadActua = bean.getHashCalidadAcatua().get(bean.getObjeto().getGjProcesoTercero().getMaeCalidadActuaId());
            if (calidadActua != null) {
                bean.getObjeto().getGjProcesoTercero().setMaeCalidadActuaCodigo(calidadActua.getValor());
                bean.getObjeto().getGjProcesoTercero().setMaeCalidadActuaValor(calidadActua.getNombre());
            }

            Maestro claseDes = bean.getHashClaseDescripcion().get(bean.getObjeto().getMaeClaseDescripcionId());
            if (claseDes != null) {
                bean.getObjeto().setMaeClaseDescripcionCodigo(claseDes.getValor());
                bean.getObjeto().setMaeClaseDescripcionValor(claseDes.getNombre());
            }
            Maestro instancia = bean.getHashInstancia().get(bean.getObjeto().getMaeInstanciaId());
            if (instancia != null) {
                bean.getObjeto().setMaeInstanciaCodigo(instancia.getValor());
                bean.getObjeto().setMaeInstanciaValor(instancia.getNombre());
            }
            Maestro medicacautelar = bean.getHashMedicaCautelar().get(bean.getObjeto().getMaeMedicaCautelarId());
            if (medicacautelar != null) {
                bean.getObjeto().setMaeMedicaCautelarCodigo(medicacautelar.getValor());
                bean.getObjeto().setMaeMedicaCautelarValor(medicacautelar.getNombre());
            }
            Maestro actuaciontermina = bean.getHashActuacionTerminacion().get(bean.getObjeto().getMaeActuacionTerminacionId());
            if (actuaciontermina != null) {
                bean.getObjeto().setMaeActuacionTerminacionCodigo(actuaciontermina.getValor());
                bean.getObjeto().setMaeActuacionTerminacionValor(actuaciontermina.getNombre());
            }
            GjProceso gjProceso = bean.getObjeto();
            bean.auditoriaModificar(gjProceso);
            bean.getObjeto().setUltimaActuacion(bean.getObjeto().getUltimaActuacionStr().getBytes());
            getGjProcesoRemoto().actualizar(bean.getObjeto());

            bean.getObjeto().setGjProceso(gjProceso);

            gjProceso.getGjProcesoHistorico().setGjProcesosId(gjProceso);
            bean.getObjeto().getGjProcesoAbogado().setGjProcesosId(gjProceso);

            bean.getObjeto().setGjProcesoAbogado(gjProceso.getGjProcesoAbogado());
            bean.getObjeto().getGjProcesoAbogado().setGjAbogadosId(gjProceso.getGjProcesoAbogado().getGjAbogadosId());

            getGjProcesoAbogadoRemoto().actualizar(gjProceso.getGjProcesoAbogado());
            bean.getObjeto().getGjProcesoHistorico().setId(getGjProcesoHistoricoRemoto().insertar(gjProceso.getGjProcesoHistorico()));
            idproces = bean.getObjeto().getGjProceso().getId();
            idProcesh = bean.getObjeto().getGjProcesoHistorico().getId();

            if (gjProceso.getGjProcesoAdjuntosList() != null && !gjProceso.getGjProcesoAdjuntosList().isEmpty()) {
                insertarAdjuntos(gjProceso.getGjProcesoAdjuntosList(), bean, idproces, idProcesh);

            }

            validarGarantiasRegistradas(bean, idproces, idProcesh);

            bean.addMensaje("Se Gestiono El registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(GjProcesoBean bean) {
        try {
            GjProceso proces = bean.getObjeto();
            bean.auditoriaModificar(proces);
            proces.setUsuarioBorra(proces.getUsuarioModifica());
            proces.setTerminalBorra(proces.getTerminalModifica());
            proces.setFechaHoraBorra(proces.getFechaHoraModifica());
            bean.auditoriaModificar(proces);
            getGjProcesoRemoto().borradoActualizar(bean.getObjeto());
            bean.addMensaje("Se elimino un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void consultarHistorial(GjProcesoBean bean) {
        try {
            bean.getParamConsultaHistorialProceso().setCantidadRegistros(getGjProcesoHistoricoRemoto().consultarCantidadLista(bean.getParamConsultaHistorialProceso()));
            bean.setRegistrosProcesoHistoricos(getGjProcesoHistoricoRemoto().consultarLista(bean.getParamConsultaHistorialProceso()));
        } catch (Exception ex) {
        }
    }

    private void consultarAdjunto(GjProcesoBean bean) {
        try {
            bean.getParamConsultaProcesoAdjunto().setCantidadRegistros(getGjProcesoAdjuntoRemoto().consultarCantidadLista(bean.getParamConsultaProcesoAdjunto()));
            bean.setRegistrosProcesoAdjuntos(getGjProcesoAdjuntoRemoto().consultarLista(bean.getParamConsultaProcesoAdjunto()));
        } catch (Exception ex) {
        }
    }

    private void borrarAdjunto(GjProcesoBean bean) {
        try {
            if (bean.getBorrarAdjunto() != null) {
                bean.setBorrarAdjunto(getGjProcesoAdjuntoRemoto().eliminar(bean.getBorrarAdjunto().getId()));
                bean.addMensaje("Se eliminó un registro de manera exitosa");
            }

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void cargas(GjProcesoBean bean) {
        try {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    break;
                case Url.ACCION_VER:
                    break;
                case Url.ACCION_CREAR:
                    break;
                case Url.ACCION_EDITAR:
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
        }
    }

//    private void listarContactosTerceros(GjProcesoBean bean) {
//        ParamConsulta paramConsulta = new ParamConsulta();
//        try {
//            if (bean.getRegistrosProcesoTerceros() != null) {
//                for (GjProcesoTercero object : bean.getRegistrosProcesoTerceros()) {
//                    int idTer = object.getGjTercerosId().getId();
//                    paramConsulta.setParametroConsulta2(idTer);
//                    bean.getListaGjTerceroContactos().addAll(getGjTerceroContactoRemoto().consultarLista(paramConsulta));
//                }
//            }
//        } catch (Exception ex) {
//            bean.setListaGjTerceroContactos(new ArrayList<>());
//        }
//    }
    private void listarContactosTerceros(GjProcesoBean bean) {
        ParamConsulta paramConsulta = new ParamConsulta();
        try {
            if (bean.getRegistrosProcesoTerceros() != null) {
                // Obtener los IDs de todos los registros de "bean.getRegistrosProcesoTerceros()"
                List<Integer> ids = new ArrayList<>();
                for (GjProcesoTercero object : bean.getRegistrosProcesoTerceros()) {
                    int idTer = object.getGjTercerosId().getId();
                    ids.add(idTer);
                }
                // Obtener los contactos de los registros que no están en "bean.getListaGjTerceroContactos()"
                List<GjTerceroContacto> contactos = new ArrayList<>();
                for (GjTerceroContacto contacto : getGjTerceroContactoRemoto().consultarLista(paramConsulta)) {
                    if (!bean.getListaGjTerceroContactos().contains(contacto) && ids.contains(contacto.getGjTercerosId().getId())) {
                        contactos.add(contacto);
                    }
                }
                // Agregar los contactos a "bean.getListaGjTerceroContactos()"
                bean.getListaGjTerceroContactos().addAll(contactos);
            }
        } catch (Exception ex) {
            // Manejar el error adecuadamente en el nivel superior
        }
    }

    private boolean validarGarantiasRegistradas(GjProcesoBean bean, int idproces, int idProcesh) {
        boolean existe = false;
        try {
            if (!getGjProcesoGarantiaRemoto().consultarListaPorIdProceso(idproces).isEmpty()) {
                existe = true;
            }
        } catch (Exception ex) {
            existe = false;
        }
        if (existe == false) {
            if (!bean.getRegistrosProcesoGarantias().isEmpty()) {
                guardarProcesoGarantias(bean, idproces, idProcesh);
            }
        }
        return existe;

    }

    public void guardarProcesoGarantias(GjProcesoBean bean, int idproces, int idProcesh) {
        try {
            for (GjProcesoGarantia contacto : bean.getRegistrosProcesoGarantias()) {
                if (contacto.getId() == null) {
                    contacto.setGjProcesosId(new GjProceso(idproces));
                    contacto.setGjProcesoHistoricosId(new GjProcesoHistorico(idProcesh));
                    bean.auditoriaGuardar(contacto);
                    getGjProcesoGarantiaRemoto().insertar(contacto);
                }
            }
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    @Override
    public void consultarUbicaciones(GjProcesoBean bean) {
        try {
            bean.setUbicaciones(UbicacionSingle.getInstance().getListaMunicipiosAntioquia());
            bean.setUbicacionesRecursiva(UbicacionSingle.getInstance().getHashMunicipiosAntioquia());
        } catch (Exception e) {
            bean.addError("No fue posible cargar las listas de apoyo");
        }
    }

    @Override
    public void listarTerceroAfiliado(GjProcesoBean bean) {
        try {
            bean.getParamConsultaTercero().setCantidadRegistros(getGjTerceroRemoto().consultarCantidadListaBuscador(bean.getParamConsultaTercero()));
            bean.setRegistrosTerceros(getGjTerceroRemoto().consultarListaBuscador(bean.getParamConsultaTercero()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void listarAbogadoAfiliado(GjProcesoBean bean) {
        try {
            bean.getParamConsultaAbogado().setCantidadRegistros(getGjAbogadoRemoto().consultarCantidadListaBuscador(bean.getParamConsultaAbogado()));
            bean.setRegistrosAbogados(getGjAbogadoRemoto().consultarListaBuscador(bean.getParamConsultaAbogado()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void completarTerceroAfiliado(GjProcesoBean bean) {
        try {
            int idAfiliado = bean.getObjeto().getGjProcesoTercero().getGjTercerosId().getGjPersona().getId();
            GjTercero afiliado = getGjTerceroRemoto().consultar(idAfiliado);
            if (afiliado != null) {
                bean.addMensaje("Tercero agregado correctamente");
            }
        } catch (Exception e) {
            bean.addError("Hubo un error al agregar información del tercero, favor comunicarse con el adminitrador");
        }
    }

    @Override
    public void completarAbogadoAfiliado(GjProcesoBean bean) {
        try {
            int idAfiliado = bean.getObjeto().getGjProcesoAbogado().getGjAbogadosId().getGjPersona().getId();
            GjAbogado afiliado = getGjAbogadoRemoto().consultar(idAfiliado);

            if (afiliado != null) {
                bean.getObjeto().getGjProcesoAbogado().getGjAbogadosId().setId(afiliado.getId());
                bean.getObjeto().getGjProcesoAbogado().getGjAbogadosId().setTipo(afiliado.getTipo());
                bean.getObjeto().getGjProcesoAbogado().getGjAbogadosId().setMaeTipoDocumentoId(afiliado.getMaeTipoDocumentoId());
                bean.getObjeto().getGjProcesoAbogado().getGjAbogadosId().setMaeTipoDocumentoValor(afiliado.getMaeTipoDocumentoValor());
                bean.getObjeto().getGjProcesoAbogado().getGjAbogadosId().setDocumento(afiliado.getDocumento());
                bean.getObjeto().getGjProcesoAbogado().getGjAbogadosId().setTarjetaProfecional(afiliado.getTarjetaProfecional());
                bean.getObjeto().getGjProcesoAbogado().getGjAbogadosId().setNombre(afiliado.getNombre());

            }

        } catch (Exception e) {
            bean.addError("Hubo un error al completar información del afiliado, favor comunicarse con el adminitrador");
        }
    }

    @Override
    public void consultarRadicado(GjProcesoBean bean) {
        try {

            GjProceso proces = getGjProcesoRemoto().consultarRadicado(bean.getObjeto());

            if (proces.exiteRadicado()) {
                bean.setRadicadoRegistradoEnSistema(true);

            } else {
                bean.setRadicadoRegistradoEnSistema(false);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void listarJuzgados(GjProcesoBean bean) {
        try {
            if (bean.getObjeto().getGnUbicacionesProcesoId() != null) {
                int idJuzgado = (bean.getObjeto() != null
                        && bean.getObjeto().getTuJuzgadosId() != null
                        && bean.getObjeto().getTuJuzgadosId().getId() != null) ? bean.getObjeto().getTuJuzgadosId().getId() : 0;
                if (bean.getObjeto().getGnUbicacionesProcesoId().getId() != null) {
                    bean.setListaJuzgados(getTuJuzgadoRemoto().consultarJuzgadoPorUbicacion(bean.getObjeto().getGnUbicacionesProcesoId().getId(), idJuzgado));
                    bean.setHashJuzgados(convertToHashJuzgados(bean.getListaJuzgados()));
                }
            }
        } catch (Exception ex) {
            bean.addError("Error al consultar lista de juzgados");
        }
    }

    @Override
    public void listarClaseDes(GjProcesoBean bean) {
        try {
            //maeClaseDescripcion
            bean.setListaClaseDescripcion(getMaestroRemoto().consultarPorTipo(MaestroTipo.GJ_CLASE_DESCRIPCION));
            bean.setHashClaseDescripcion(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GJ_CLASE_DESCRIPCION));

        } catch (Exception e) {
            bean.addError("No fue posible cargar las listas de apoyo");
        }
        try {

            if (bean.getObjeto().getMaeClaseId() > 0) {
                int padre = bean.getObjeto().getMaeClaseId();
                String maet = MaestroTipo.GJ_CLASE_DESCRIPCION;
                bean.setListaClaseDescripcion(getMaestroRemoto().consultarListaPorPadre(maet, padre));
            }
        } catch (Exception ex) {
            bean.addError("Error al consultar lista de juzgados");
        }
    }

    public void listaClaseNull(GjProcesoBean bean) {
        List<Maestro> listaClaseDescripcion = null;
        bean.setListaClaseDescripcion(listaClaseDescripcion);
    }

    private void insertarAdjuntos(List<GjProcesoAdjunto> adjuntosIn, GjProcesoBean bean, int idProces, int idProcesoH) {

        try {
            for (GjProcesoAdjunto adjunto : adjuntosIn) {
                if (adjunto.getId() == null) {
                    bean.auditoriaGuardar(adjunto);

                    if (idProces > 0) {
                        adjunto.setGjProcesosId(new GjProceso(idProces));
                    }

                    if (idProcesoH > 0) {
                        adjunto.setGjProcesoHistoricosId(new GjProcesoHistorico(idProcesoH));
                    }

                    int idAdjunto = getGjProcesoAdjuntoRemoto().insertar(adjunto);

                    adjunto.setId(idAdjunto);
                }

            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public HashMap<Integer, TuJuzgado> convertToHashJuzgados(List<TuJuzgado> list) {
        HashMap<Integer, TuJuzgado> map = new HashMap<>();
        for (TuJuzgado i : list) {
            map.put(i.getId(), i);
        }
        return map;
    }

}
