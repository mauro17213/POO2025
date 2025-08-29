package com.saviasaludeps.savia.web.atencionusuario.servicio;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.atencionusuario.AusPersona;
import com.saviasaludeps.savia.dominio.atencionusuario.AusPersonaTelefono;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.atencionusuario.AusPersonaRemoto;
import com.saviasaludeps.savia.web.atencionusuario.bean.AusPersonaBean;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.List;
import java.util.Map;

public class AusPersonaServicioImpl extends AccionesBO implements AusPersonaServicioIface {

    private AusPersonaRemoto getPersonaRemoto() throws Exception {
        return (AusPersonaRemoto) RemotoEJB.getEJBRemoto("AusPersonaServicio", AusPersonaRemoto.class.getName());
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
        return (MaestroRemoto) object;
    }

    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
        return (AfiliadoRemoto) object;
    }

    @Override
    public void Accion(AusPersonaBean bean) {
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
                case Url.ACCION_ADICIONAL_1:
                    break;
                case Url.ACCION_ADICIONAL_2:
                    break;
                case Url.ACCION_ADICIONAL_3:
                    break;
                default:
                    break;
            }
            cargas(bean);
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    private void listar(AusPersonaBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getPersonaRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getPersonaRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());

        }
    }

    private void ver(AusPersonaBean bean) {
        try {
            bean.setObjeto(getPersonaRemoto().consultar(bean.getObjeto().getId()));
            List<AusPersonaTelefono> listaTelefonosPersona = getPersonaRemoto().consultarTelefonosPersonas(bean.getObjeto().getDocumento());
            bean.getObjeto().setListaTelefonos(listaTelefonosPersona);
            bean.setListaausPersonaTelefono(bean.getObjeto().getListaTelefonos());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(AusPersonaBean bean) {
        try {
            bean.setObjeto(new AusPersona());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(AusPersonaBean bean) {
        try {
            AusPersona ausPersona = getPersonaRemoto().consultar(bean.getObjeto().getId());
            bean.setObjeto(ausPersona);
            List<AusPersonaTelefono> listaTelefonosPersona = getPersonaRemoto().consultarTelefonosPersonas(bean.getObjeto().getDocumento());
            bean.getObjeto().setListaTelefonos(listaTelefonosPersona);
            bean.setListaausPersonaTelefono(bean.getObjeto().getListaTelefonos());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(AusPersonaBean bean) {
        try {
            //Generador de códigos
            for (Map.Entry<Integer, Maestro> mae : bean.getHashEstadosPersona().entrySet()) {
                if (mae.getValue().getId().equals(bean.getObjeto().getMae_estado_id())) {
                    bean.getObjeto().setMae_estado_codigo(mae.getValue().getValor());
                    bean.getObjeto().setMae_estado_valor(mae.getValue().getNombre());
                }
            }

            for (Map.Entry<Integer, Maestro> mae : bean.getHashSexo().entrySet()) {
                if (mae.getValue().getId().equals(bean.getObjeto().getMae_sexo_id())) {
                    bean.getObjeto().setMae_sexo_codigo(mae.getValue().getValor());
                    bean.getObjeto().setMae_sexo_valor(mae.getValue().getNombre());
                }
            }

            for (Map.Entry<Integer, Maestro> mae : bean.getHashTiposDocumento().entrySet()) {
                if (mae.getValue().getId().equals(bean.getObjeto().getMae_tipo_documento_id())) {
                    bean.getObjeto().setMae_tipo_documento_codigo(mae.getValue().getValor());
                    bean.getObjeto().setMae_tipo_documento_valor(mae.getValue().getNombre());
                    bean.auditoriaGuardar(bean.getObjeto());
                    getPersonaRemoto().insertar(bean.getObjeto());
                    bean.addMensaje("Se creo un registro de manera exitosa");
                }
            }

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(AusPersonaBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjeto());
            getPersonaRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("Se actualizó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(AusPersonaBean bean) {
        try {
            bean.setObjeto(getPersonaRemoto().eliminar(bean.getObjeto().getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void cargas(AusPersonaBean bean) {
        try {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
//                     bean.setUbicacionesRecursiva(getUbicacionRemoto().consultarMunicipios());
//                     bean.setUbicaciones(getUbicacionRemoto().consultarPorTipo(Ubicacion.TIPO_MUNICIPIO));
                    break;
                case Url.ACCION_VER:

                    break;
                case Url.ACCION_CREAR:
                case Url.ACCION_EDITAR:
//                    bean.setUbicacionesRecursiva(getUbicacionRemoto().consultarMunicipios());
//                    bean.setUbicaciones(getUbicacionRemoto().consultarPorTipo(Ubicacion.TIPO_MUNICIPIO));
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    @Override
    public void cargaInial(AusPersonaBean bean) {
        try {
            //bean.setUbicaciones(bean.getUbicacionSingle().getListaMunicipiosPorDefecto());
            //bean.setUbicacionesRecursiva(bean.getUbicacionSingle().getHashMunicipiosPorDefecto());
            bean.setHashTiposDocumento(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO));
            bean.setListaTiposDocumento(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO));
            bean.setListaSexo(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_SEXO));
            bean.setHashSexo(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GN_SEXO));
            bean.setListaEstadosAusPersona(getMaestroRemoto().consultarPorTipo(MaestroTipo.ASEG_ESTADO_AFILIACION));
            bean.setHashEstadosPersona(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.ASEG_ESTADO_AFILIACION));
            bean.setAusPersonaServicio((AusPersonaServicioIface) getPersonaRemoto());

//            bean.getParamConsulta().setCantidadRegistros(getPersonaRemoto().consultarCantidadLista(bean.getParamConsulta()));
//            bean.setAusPersonaServicio((CmAuditoriaFacturaServicioIface) getPersonaRemoto());
//               List<AusPersona> Persona =  getPersonaRemoto().
//               consultarTodas(bean.getConexion().getUsuario().getId());
//            bean.getCmAuditoriaUsuarioActual().setGruposDeAccesoGrupos(grupos);
            //bean.setUbicacionesRecursiva(UbicacionSingle.getInstance().getHashUbicaciones());
            //bean.setUbicaciones(UbicacionSingle.getInstance().getListaMunicipios());
            
            bean.setUbicaciones(UbicacionSingle.getInstance().getListaMunicipiosAntioquia());
            bean.setUbicacionesRecursiva(UbicacionSingle.getInstance().getHashMunicipiosAntioquia());
            //bean.setUbicacionesRecursiva(getUbicacionRemoto().consultarMunicipios());
            //bean.setUbicaciones(getUbicacionRemoto().consultarPorTipo(Ubicacion.TIPO_MUNICIPIO));
        } catch (Exception ex) {
        }
    }

    @Override
    public void buscarAfiliado(AusPersonaBean bean) {
        try {
//            AsegAfiliado asegAfiliado = getAfiliadoRemoto().consultar(bean.getObjeto());
//            AusPersona ausPersona = getPersonaRemoto().consultar(bean.getObjeto());
//            if (ausPersona.exitePersona()) {
//                bean.setObjeto(ausPersona);
//            }
        } catch (Exception e) {
            //bean.addError(e.getMessage());
        }
    }

    @Override
    public void consultarPersona(AusPersonaBean bean) {
        try {
//            bean.getObjeto().setEmpresa(bean.getConexion().getEmpresa());
            AusPersona ausPersona = getPersonaRemoto().consultarPersona(bean.getObjeto());
//            AsegAfiliado asegAfiliado = getAfiliadoRemoto().consultar(bean.getObjeto());
            if (ausPersona.exitePersona()) {
                List<AusPersonaTelefono> telefonosPersona = getPersonaRemoto().consultarTelefonosPersonas(ausPersona.getDocumento());
                ausPersona.setListaTelefonos(telefonosPersona);
                bean.setObjeto(ausPersona);
                bean.setListaausPersonaTelefono(ausPersona.getListaTelefonos());
            }
        } catch (Exception e) {
            //bean.addError(e.getMessage());
        }
    }

//    public void castEntidadNegocio(AusPersonaBean bean, AfiliadoDto afiliadoDto) {
//        AusPersona ausPersona = bean.getObjeto();
//        ausPersona.setNombres(afiliadoDto.getNombresCompletos());
//        ausPersona.setApellidos(afiliadoDto.getApellidosCompletos());
//
//        int idMaeTipoDoc = 0;
//        for (Map.Entry<Integer, Maestro> entry : bean.getHashTiposDocumento().entrySet()) {
//            Maestro maestro = entry.getValue();
//            if (afiliadoDto.getTipoDocumentoAfiliado().equalsIgnoreCase(maestro.getValor())) {
//                idMaeTipoDoc = maestro.getId();
//                break;
//            }
//        }
//        ausPersona.setMaeTipoDocumento(idMaeTipoDoc);
//        String pattern = "yyyy-MM-dd";
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//        Date date = null;
//        try {
//            date = simpleDateFormat.parse(afiliadoDto.getFechaNacimientoAfiliado());
//        } catch (ParseException ex) {
//            Logger.getLogger(PersonaServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        ausPersona.setFechaNacimiento(date);
//        int idSexo = 0;
//        for (Map.Entry<Integer, Maestro> entry : bean.getHashSexo().entrySet()) {
//            Maestro maestro = entry.getValue();
//            if (afiliadoDto.getSexoAfiliado().equalsIgnoreCase(maestro.getDescripcion())) {
//                idSexo = maestro.getId();
//                break;
//            }
//        }
//        ausPersona.setMaeSexo(idSexo);
//        int idEstadoAfiliacion = 0;
//        for (Map.Entry<Integer, Maestro> entry : bean.getHashEstadosPersona().entrySet()) {
//            Maestro maestro = entry.getValue();
//            if (afiliadoDto.getEstadoAfiliacion().equalsIgnoreCase(maestro.getDescripcion())) {
//                idEstadoAfiliacion = maestro.getId();
//                break;
//            }
//        }
//        ausPersona.setMaeEstado(idEstadoAfiliacion);
//        boolean diascapacidad = !afiliadoDto.getDiscapacidad().equalsIgnoreCase("NO");
//        ausPersona.setDiscapacidad(diascapacidad);
//        ausPersona.setDireccion(afiliadoDto.getDireccion());
//        ausPersona.setContrato(afiliadoDto.getContratoInternoAfilado());
//        int regimen = afiliadoDto.getRegimen().equalsIgnoreCase("Subsidiado") ? 1 : 0;
//        ausPersona.setRegimen(regimen);
//        String telefono = afiliadoDto.getTelefono();
//        boolean agregarListaTel = (telefono != null && !"".equals(telefono));
//        if (agregarListaTel) {
//            List<PersonaTelefono> listaTelefonos = new ArrayList<>();
//            PersonaTelefono personatTelefono = new PersonaTelefono();
//            personatTelefono.setNumero(telefono);
//            listaTelefonos.add(personatTelefono);
//            ausPersona.setListaTelefonos(listaTelefonos);
//            bean.setListaPersonaTelefono(ausPersona.getListaTelefonos());
//        }
//        ausPersona.setEsAfiliado(true);
//    }
}
