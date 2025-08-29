package com.saviasaludeps.savia.web.judicial.servicio;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.judicial.GjTercero;
import com.saviasaludeps.savia.dominio.judicial.GjTerceroContacto;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.negocio.judicial.GjTerceroContactoRemoto;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuConstantes;
import com.saviasaludeps.savia.web.judicial.bean.GjTerceroBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.negocio.judicial.GjTerceroRemoto;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bsteven_gomez
 */
public class GjTerceroServicioImpl extends AccionesBO implements GjTerceroServicioIface {

    private GjTerceroRemoto getTerceroRemoto() throws Exception {
        return (GjTerceroRemoto) RemotoEJB.getEJBRemoto("GjTerceroServicio", GjTerceroRemoto.class.getName());
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }

    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        return (AfiliadoRemoto) RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
    }

    private CntPrestadorRemoto getCntPrestadorRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }

    private GjTerceroContactoRemoto getGjTerceroContactoRemoto() throws Exception {
        return (GjTerceroContactoRemoto) RemotoEJB.getEJBRemoto("GjTerceroContactoServicio", GjTerceroContactoRemoto.class.getName());
    }

    @Override
    public void Accion(GjTerceroBean bean) {
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
                case Url.ACCION_BORRAR:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_BORRAR:
                            borrar(bean);
                            break;
                        case GjTerceroBean.BORRAR_TELEFONO_CONTACTO:
                            listarContactoEditar(bean);
                            break;
                        case GjTerceroBean.LISTAR_TELEFONO_SELECCIONADO:
                            listarContacto(bean);
                            break;
                        case GjTerceroBean.EDITAR_CONTACTO:
                            modificarContac(bean);
                            break;
                    }
                default:
                    break;
            }
            cargas(bean);
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    private void listar(GjTerceroBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getTerceroRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getTerceroRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(GjTerceroBean bean) {
        try {
            bean.setObjeto(getTerceroRemoto().consultar(bean.getObjeto().getId()));
            listarContactos(bean);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(GjTerceroBean bean) {
        try {
            bean.setObjeto(new GjTercero());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(GjTerceroBean bean) {
        try {
            bean.setObjeto(getTerceroRemoto().consultar(bean.getObjeto().getId()));
            listarContactos(bean);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(GjTerceroBean bean) {
        try {
            Maestro tipoDoc = bean.getHashTipoDocumento().get(bean.getObjeto().getMaeTipoDocumentoId());
            bean.getObjeto().setMaeTipoDocumentoCodigo(tipoDoc.getValor());
            bean.getObjeto().setMaeTipoDocumentoValor(tipoDoc.getNombre());
            bean.auditoriaGuardar(bean.getObjeto());
            int idTercero = getTerceroRemoto().insertar(bean.getObjeto());
            bean.getObjeto().setId(idTercero);
            procesarTelefonos(bean);
            bean.addMensaje("El registro numero " + idTercero + " Se creo de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void listarContactos(GjTerceroBean bean) {
        try {
            if (bean.getObjeto().getId() != null) {
                bean.setListaafiliadoContacto(getGjTerceroContactoRemoto().consultarListaContactos(bean.getObjeto().getId()));
            }
        } catch (Exception ex) {
            bean.setListaafiliadoContacto(new ArrayList<>());
        }
    }

    public void procesarTelefonos(GjTerceroBean bean) {
        try {
            for (GjTerceroContacto contacto : bean.getListaafiliadoContacto()) {
                if (contacto.getId() == null) {
                    contacto.setGjTercerosId(new GjTercero(bean.getObjeto().getId()));
                    bean.auditoriaGuardar(contacto);
                    getGjTerceroContactoRemoto().insertar(contacto);
                }
            }
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    private void modificar(GjTerceroBean bean) {
        try {
            Maestro tipoDoc = bean.getHashTipoDocumento().get(bean.getObjeto().getMaeTipoDocumentoId());
            bean.getObjeto().setMaeTipoDocumentoCodigo(tipoDoc.getValor());
            bean.getObjeto().setMaeTipoDocumentoValor(tipoDoc.getNombre());

            bean.auditoriaModificar(bean.getObjeto());
            getTerceroRemoto().actualizar(bean.getObjeto());
            procesarTelefonos(bean);
            bean.addMensaje("Se actualizó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificarContac(GjTerceroBean bean) {
        try {
            Maestro tipoDoc = bean.getHashTipoContacto().get(bean.getObjeto().getGjTerceroContacto().getMaeTipoContactoId());
            if (tipoDoc != null) {
                bean.getObjeto().getGjTerceroContacto().setMaeTipoContactoId(tipoDoc.getId());
                bean.getObjeto().getGjTerceroContacto().setMaeTipoContactoCodigo(tipoDoc.getValor());
                bean.getObjeto().getGjTerceroContacto().setMaeTipoContactoValor(tipoDoc.getNombre());

            }
            bean.auditoriaModificar(bean.getObjeto().getGjTerceroContacto());
            getGjTerceroContactoRemoto().actualizar(bean.getObjeto().getGjTerceroContacto());
            bean.addMensaje("Se actualizó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(GjTerceroBean bean) {
        try {
            bean.setObjeto(getTerceroRemoto().eliminar(bean.getObjeto().getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

//    private void borrarTelefonoContacto(GjTerceroBean bean) {
//        try {
//            GjTerceroContacto contacto = getGjTerceroContactoRemoto().consultar(bean.getAfiliadoContacto().getId());
//            if (contacto != null) {
//                getGjTerceroContactoRemoto().eliminar(contacto.getId());
//            }
//        } catch (Exception e) {
//            bean.addError(e.getMessage());
//        }
//    }
    private void listarContactoEditar(GjTerceroBean bean) {
        try {
            if (bean.getObjeto().getId() != null) {
                bean.getParamConsulta().setCantidadRegistros(getGjTerceroContactoRemoto().consultarCantidadLista(bean.getParamConsulta()));
                bean.setListaafiliadoContacto(getGjTerceroContactoRemoto().consultarLista(bean.getParamConsulta()));
            }
        } catch (Exception ex) {
        }
    }

    private void listarContacto(GjTerceroBean bean) {
        try {
            bean.getObjeto().setGjTerceroContacto(getGjTerceroContactoRemoto().consultar(bean.getObjeto().getGjTerceroContacto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void cargas(GjTerceroBean bean) {
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

    @Override
    public void consultarPersonaAfiliada(GjTerceroBean bean) {
        try {
            //AsegAfiliado ausPersona = getAfiliadoRemoto().consultar(bean.getObjeto().getTuPersona().getMaeTipoDocumentoId(), bean.getObjeto().getTuPersona().getNumeroDocumento());
            List<AsegAfiliado> ausPersona = getAfiliadoRemoto().consultarListaAfiliados(bean.getObjeto().getMaeTipoDocumentoCodigo(), bean.getObjeto().getDocumento());
            if (ausPersona != null && !ausPersona.isEmpty()) {
                if (ausPersona.size() == 1) {
                    castEntidadNegocio(bean, ausPersona.get(0));
                } else {
                    Collections.sort(ausPersona, (o1, o2) -> o1.getFechaHoraCrea().compareTo(o2.getFechaHoraCrea()));
                    castEntidadNegocio(bean, ausPersona.get(ausPersona.size() - 1));
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void consultarPrestadorAfiliado(GjTerceroBean bean) {
        try {
            //AsegAfiliado ausPersona = getAfiliadoRemoto().consultar(bean.getObjeto().getTuPersona().getMaeTipoDocumentoId(), bean.getObjeto().getTuPersona().getNumeroDocumento());
            List<CntPrestador> ausPersona = getCntPrestadorRemoto().consultarListaPrestador(bean.getObjeto().getMaeTipoDocumentoCodigo(), bean.getObjeto().getDocumento());
            if (ausPersona != null && !ausPersona.isEmpty()) {
                if (ausPersona.size() == 1) {
                    castEntidadNegocio(bean, ausPersona.get(0));
                } else {
                    Collections.sort(ausPersona, (o1, o2) -> o1.getFechaHoraCrea().compareTo(o2.getFechaHoraCrea()));
                    castEntidadNegocio(bean, ausPersona.get(ausPersona.size() - 1));
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void consultarUbicaciones(GjTerceroBean bean) {
        try {
            bean.setUbicaciones(UbicacionSingle.getInstance().getListaMunicipiosAntioquia());
            bean.setUbicacionesRecursiva(UbicacionSingle.getInstance().getHashMunicipiosAntioquia());
        } catch (Exception e) {
            bean.addError("No fue posible cargar las listas de apoyo");
        }
    }

    public void castEntidadNegocio(GjTerceroBean bean, AsegAfiliado afiliadoDto) {
        GjTercero persona = bean.getObjeto();
//        persona.setId(afiliadoDto.getId());
        persona.setAsegAfiliadoId(afiliadoDto.getId());

        int idMaeTipoDoc = 0;
        String tipoDocumentoCodigo = "";
        String tipoDocumentoValor = "";

        persona.setNombres(afiliadoDto.getNombres());
        persona.setApellidos(afiliadoDto.getApellidos());
        if (afiliadoDto.getResidenciaUbicacion() != null) {
            persona.setGnUbicacionesId(new Ubicacion(afiliadoDto.getResidenciaUbicacion().getId()));
        }
        persona.setDireccion(afiliadoDto.getDireccionResidencia());
        persona.setTelefono(afiliadoDto.getTelefonoMovil());
        persona.setCorreoElectronico(afiliadoDto.getEmail());
//        persona.setAsegAfiliado((afiliadoDto.getId())) sewtear campos a llenar el from;

        for (Map.Entry<Integer, Maestro> entry : bean.getHashTipoDocumento().entrySet()) {
            Maestro maestro = entry.getValue();
            if (afiliadoDto.getMaeTipoDocumentoCodigo().equals(maestro.getValor())) {
                idMaeTipoDoc = maestro.getId();
                tipoDocumentoCodigo = maestro.getValor();
                tipoDocumentoValor = maestro.getNombre();
                break;
            }
        }
        persona.setMaeTipoDocumentoId(idMaeTipoDoc);
        persona.setMaeTipoDocumentoCodigo(tipoDocumentoCodigo);
        persona.setMaeTipoDocumentoValor(tipoDocumentoValor);

        persona.setEsAfiliado(true);
    }

    public void castEntidadNegocio(GjTerceroBean bean, CntPrestador afiliadoDto) {//casteo terceros-prestador 
        GjTercero persona = bean.getObjeto();

        persona.setCntPrestadorId(afiliadoDto.getId());

        int idMaeTipoDoc = 0;
        String tipoDocumentoCodigo = "";
        String tipoDocumentoValor = "";

        persona.setNombres(afiliadoDto.getRazonSocial());

        if (afiliadoDto.getRazonSocial() != null) {
            persona.setRazonSocial(afiliadoDto.getRazonSocial());
        } else {
            persona.setRazonSocial("N/A");
        }

//        persona.setAsegAfiliado((afiliadoDto.getId())) sewtear campos a llenar el from;
        for (Map.Entry<Integer, Maestro> entry : bean.getHashTipoDocumento().entrySet()) {
            Maestro maestro = entry.getValue();
            if (afiliadoDto.getMaeTipoDocumentoCodigo().equals(maestro.getValor())) {
                idMaeTipoDoc = maestro.getId();
                tipoDocumentoCodigo = maestro.getValor();
                tipoDocumentoValor = maestro.getNombre();
                break;
            }
        }
        persona.setMaeTipoDocumentoId(idMaeTipoDoc);
        persona.setMaeTipoDocumentoCodigo(tipoDocumentoCodigo);
        persona.setMaeTipoDocumentoValor(tipoDocumentoValor);

        persona.setEsAfiliado(true);
    }

    @Override
    public void consultarPersona(GjTerceroBean bean) {
        try {
            //bean.getObjeto().setEmpresa(bean.getConexion().getEmpresa());
            GjTercero ausPersona = getTerceroRemoto().consultarPersona(bean.getObjeto());
            if (ausPersona.exitePersona()) {
                bean.getObjeto().setGjPersona(ausPersona);
            }
            /*if (ausPersona.exitePersona()) {
                List<AusPersonaTelefono> telefonosPersona = getPersonaRemoto().consultarTelefonosPersonas(ausPersona.getDocumento());
                ausPersona.setListaTelefonos(telefonosPersona);
                bean.setObjeto(ausPersona);

                bean.setAusPersonaTelefono((AusPersonaTelefono) ausPersona.getListaTelefonos());
            }*/
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void consultarTerCero(GjTerceroBean bean) {
        try {

            GjTercero ausPersona = getTerceroRemoto().consultarTerCero(bean.getObjeto());

            if (ausPersona.exiteTercero()) {
                bean.setTerceroRegistradoEnSistema(true);
                throw new Exception("El documento que ingreso ya está asignado a otro tercero");
            } else {
                bean.setTerceroRegistradoEnSistema(false);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void cargasInicial(GjTerceroBean bean) {
        try {
            bean.setListaTipoDocumento(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO));
            bean.setHashTipoDocumento(AuConstantes.obtenerHashMaestro(bean.getListaTipoDocumento()));
            bean.setHashTipoDocumento(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO));

            bean.setUbicaciones(UbicacionSingle.getInstance().getListaMunicipios());
            bean.setUbicacionesRecursiva(UbicacionSingle.getInstance().getHashMunicipios());

            bean.setListaTipoContacto(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_CONTACTO));
            bean.setHashTipoContacto(AuConstantes.obtenerHashMaestro(bean.getListaTipoContacto()));
        } catch (Exception e) {
            bean.addError("No fue posible cargar las listas de apoyo");
        }
    }

}
