package com.saviasaludeps.savia.web.judicial.servicio;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.judicial.GjAbogado;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.administracion.UsuarioRemoto;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuConstantes;
import com.saviasaludeps.savia.web.judicial.bean.GjAbogadoBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.negocio.judicial.GjAbogadoRemoto;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bsteven_gomez
 */
public class GjAbogadoServicioImpl extends AccionesBO implements GjAbogadoServicioIface {

    private GjAbogadoRemoto getAbogadoRemoto() throws Exception {
        return (GjAbogadoRemoto) RemotoEJB.getEJBRemoto("GjAbogadoServicio", GjAbogadoRemoto.class.getName());
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }

    private GjAbogadoRemoto getGjAbogadoRemoto() throws Exception {
        return (GjAbogadoRemoto) RemotoEJB.getEJBRemoto("GjAbogadoServicio", GjAbogadoRemoto.class.getName());
    }

    private UsuarioRemoto getUsuarioRemoto() throws Exception {
        return (UsuarioRemoto) RemotoEJB.getEJBRemoto("UsuarioServicio", UsuarioRemoto.class.getName());
    }

    @Override
    public void Accion(GjAbogadoBean bean) {
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
                    borrar(bean);
                    break;
                default:
                    break;
            }
            cargas(bean);
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    private void listar(GjAbogadoBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getAbogadoRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getAbogadoRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(GjAbogadoBean bean) {
        try {
            bean.setObjeto(getAbogadoRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(GjAbogadoBean bean) {
        try {
            bean.setObjeto(new GjAbogado());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(GjAbogadoBean bean) {
        try {
            bean.setObjeto(getAbogadoRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(GjAbogadoBean bean) {
        try {
            Maestro tipoDoc = bean.getHashTipoDocumento().get(bean.getObjeto().getMaeTipoDocumentoId());
            bean.getObjeto().setMaeTipoDocumentoCodigo(tipoDoc.getValor());

            bean.getObjeto().setMaeTipoDocumentoValor(tipoDoc.getNombre());
            bean.getObjeto().setNombre(bean.getObjeto().getGnUsuario().getNombre());           
            bean.auditoriaGuardar(bean.getObjeto());
            bean.getObjeto().setId(getAbogadoRemoto().insertar(bean.getObjeto()));
            bean.addMensaje("Se creo un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(GjAbogadoBean bean) {
        try {
            Maestro tipoDoc = bean.getHashTipoDocumento().get(bean.getObjeto().getMaeTipoDocumentoId());
            bean.getObjeto().setMaeTipoDocumentoCodigo(tipoDoc.getValor());
            bean.getObjeto().setMaeTipoDocumentoValor(tipoDoc.getNombre());
            bean.getObjeto().setNombre(bean.getObjeto().getGnUsuario().getNombre());
            bean.auditoriaModificar(bean.getObjeto());
            getAbogadoRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("Se actualizó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(GjAbogadoBean bean) {
        try {
            bean.setObjeto(getAbogadoRemoto().eliminar(bean.getObjeto().getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void cargas(GjAbogadoBean bean) {
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
    public void consultarAbogadoAfiliado(GjAbogadoBean bean) {
        try {
            //AsegAfiliado ausPersona = getAfiliadoRemoto().consultar(bean.getObjeto().getTuPersona().getMaeTipoDocumentoId(), bean.getObjeto().getTuPersona().getNumeroDocumento());
            List<Usuario> ausPersona = getUsuarioRemoto().consultarPorNombre(bean.getObjeto().getNombre());
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
    public void consultarPersona(GjAbogadoBean bean) {
        try {

            GjAbogado ausPersona = getGjAbogadoRemoto().consultarPersona(bean.getObjeto());
            if (ausPersona.exitePersona()) {
                bean.getObjeto().setGjPersona(ausPersona);
            }

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    @Override
    public void consultarAbogado(GjAbogadoBean bean) {
        try {

            GjAbogado ausPersona = getGjAbogadoRemoto().consultarAbogado(bean.getObjeto());

            if (ausPersona.exiteAbogado()) {
                bean.setAbogadoRegistradoEnSistema(true);
                throw new Exception("El documento que ingreso ya fue asignado a otro abogado");
            } else {
                bean.setAbogadoRegistradoEnSistema(false);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public HashMap<Integer, Usuario> convertToHashUsuarios(List<Usuario> list) {
        HashMap<Integer, Usuario> map = new HashMap<>();
        for (Usuario i : list) {
            map.put(i.getId(), i);
        }
        return map;
    }

    public void castEntidadNegocio(GjAbogadoBean bean, Usuario afiliadoDto) {//casteo terceros-prestador 
        GjAbogado persona = bean.getObjeto();

        persona.setGnUsuarioId(afiliadoDto.getId());

        int idMaeTipoDoc = 0;
        String tipoDocumentoCodigo = "";
        String tipoDocumentoValor = "";

        String prestadorName = afiliadoDto.getNombre();
        persona.setNombre(prestadorName);
//        String[] parts = prestadorName.split(" ");
//
//        if (parts.length == 4) {
//            persona.setNombres(parts[0] + " " + parts[1]);
//            persona.setApellidos(parts[2] + " " + parts[3]);
//        } else if (parts.length == 3) {
//            persona.setNombres(parts[0]);
//            persona.setApellidos(parts[1] + " " + parts[2]);
//        } else if (parts.length == 2) {
//            persona.setNombres(parts[0]);
//            persona.setApellidos(parts[1]);
//        }
//        persona.setDireccion(afiliadoDto.getDireccionResidencia());
//        persona.setTelefono(afiliadoDto.getTelefonoMovil());
//        persona.setCorreoElectronico(afiliadoDto.get());
//        if (afiliadoDto.getRazonSocial() != null) {
//            persona.setRazonSocial(afiliadoDto.getRazonSocial());
//        }else{
//            persona.setRazonSocial("N/A");
//        }

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
    public void cargasInicial(GjAbogadoBean bean) {
        try {
            bean.setListaTipoDocumento(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO));
            bean.setHashTipoDocumento(AuConstantes.obtenerHashMaestro(bean.getListaTipoDocumento()));

//           bean.setListaTipoDocumento(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO));
            bean.setHashTipoDocumento(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO));

            bean.setListaUsuarios(getGjAbogadoRemoto().consultarTodosUsuarios());
            bean.setHashListaUsuarios(convertToHashUsuarios(bean.getListaUsuarios()));

        } catch (Exception e) {
            bean.addError("No fue posible cargar las listas de apoyo");
        }
    }

}
