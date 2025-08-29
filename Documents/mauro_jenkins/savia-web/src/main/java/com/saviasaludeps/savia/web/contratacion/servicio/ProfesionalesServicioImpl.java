/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.contratacion.servicio;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.contratacion.CntProfesional;
import com.saviasaludeps.savia.dominio.contratacion.CntProfesionalPrestador;
import com.saviasaludeps.savia.negocio.administracion.UbicacionRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorProfesionalRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorSedeRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntProfesionalRemoto;
import com.saviasaludeps.savia.web.contratacion.bean.ProfesionalesBean;
import com.saviasaludeps.savia.web.contratacion.utilidades.ContratacionParametro;
import com.saviasaludeps.savia.web.singleton.MaestroSingle;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jaime Andres Olarte
 */
public class ProfesionalesServicioImpl extends AccionesBO implements ProfesionalesServicioIface {

    private CntProfesionalRemoto getCntProfesionalRemoto() throws Exception {
        return (CntProfesionalRemoto) RemotoEJB.getEJBRemoto("CntProfesionalServicio", CntProfesionalRemoto.class.getName());
    }

    private CntPrestadorProfesionalRemoto getCntPrestadorProfesionalRemoto() throws Exception {
        return (CntPrestadorProfesionalRemoto) RemotoEJB.getEJBRemoto("CntPrestadorProfesionalServicio", CntPrestadorProfesionalRemoto.class.getName());
    }
    
    private CntPrestadorSedeRemoto getCntPrestadorSedeRemoto() throws Exception {
        return (CntPrestadorSedeRemoto) RemotoEJB.getEJBRemoto("CntPrestadorSedeServicio", CntPrestadorSedeRemoto.class.getName());
    }

    private UbicacionRemoto getUbicacionRemoto() throws Exception {
        return (UbicacionRemoto) RemotoEJB.getEJBRemoto("UbicacionServicio", UbicacionRemoto.class.getName());
    }

    @Override
    public void Accion(ProfesionalesBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_VER:
                    ver(bean);
                    break;
                case Url.ACCION_CREAR:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_CREAR:
                            crear(bean);
                            break;
                        case Url.ACCION_BORRAR:
                            borrarProfesionalIps(bean);
                            break;
                    }
                    break;
                case Url.ACCION_GUARDAR:
                    guardar(bean);
                    break;
                case Url.ACCION_EDITAR:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_EDITAR:
                            editar(bean);
                            break;
                        case Url.ACCION_BORRAR:
                            borrarProfesionalIps(bean);
                            break;
                    }
                    break;
                case Url.ACCION_MODIFICAR:
                    modificar(bean);
                    break;
                case Url.ACCION_BORRAR:
                    borrar(bean);
                    break;
            }
        }
    }

    @Override
    public void cargaInicial(ProfesionalesBean bean) {
        try {
            if (bean.getConexion().getEmpresa().getId().intValue() == ContratacionParametro.ID_EMPRESA_SAVIA) {
                bean.setBuscaPrestador(true);
            } else {
                bean.setBuscaPrestador(false);
            }
            //Maestro Tipo Documento Profesional
//            bean.setListaTiposDocumentoProfesional(bean.getContratacionSingle().listarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PROFESIONAL));
            bean.setListaTiposDocumentoProfesional(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PROFESIONAL));
            bean.setHashTiposDocumentoProfesional(Util.convertToHash(bean.getListaTiposDocumentoProfesional()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void consultarProfesional(ProfesionalesBean bean) {
        try {
            int tipoDocumento = bean.getObjeto().getCntProfesionalesId().getMaeTipoCodumentoId();
            String documento = bean.getObjeto().getCntProfesionalesId().getDocumento();
            CntProfesional cntProfesional = getCntProfesionalRemoto().consultarNumDocumento(tipoDocumento, documento);
            bean.getObjeto().setCntProfesionalesId(new CntProfesional());
            bean.setListaCntProfesionalPrestador(new ArrayList<>());
            if (cntProfesional != null) {
                bean.getObjeto().setCntProfesionalesId(cntProfesional);
                bean.getObjeto().getCntProfesionalesId().setMaeTipoCodumentoId(tipoDocumento);
                bean.getObjeto().getCntProfesionalesId().setDocumento(documento);
                bean.getObjeto().getCntProfesionalesId().setGuardar(false);
                consultarPrestadorProfesional(bean);
                existePrestadorEspecialidad(bean);
            } else {
                bean.getObjeto().getCntProfesionalesId().setGuardar(true);
                bean.getObjeto().getCntProfesionalesId().setMaeTipoCodumentoId(tipoDocumento);
                bean.getObjeto().getCntProfesionalesId().setDocumento(documento);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void cerrarDialogoSelPrestador(ProfesionalesBean bean) {
        consultarUbicacionPrestador(bean);
        consultarPrestadorProfesional(bean);
        bean.setAgregarEspecialidad(true);
        existePrestadorEspecialidad(bean);
    }

    @Override
    public void agregarProfesionalPrestador(ProfesionalesBean bean) {
        try {
            boolean existe = false;
            for (CntProfesionalPrestador item : bean.getListaCntProfesionalPrestador()) {
                if (item.getCntPrestador().getId().intValue() == bean.getObjeto().getCntPrestador().getId().intValue()
                        || item.getCntPrestador().getNumeroDocumento().equals(bean.getObjeto().getCntPrestador().getNumeroDocumento())) {
                    existe = true;
                    break;
                }
            }
            if (!existe) {
                CntProfesionalPrestador cntProfesionalPrestador = new CntProfesionalPrestador();
                cntProfesionalPrestador.setCntPrestador(bean.getObjeto().getCntPrestador());
                if (bean.getObjeto().getCntProfesionalesId().getId() != null) {
                    cntProfesionalPrestador.setCntProfesionalesId(bean.getObjeto().getCntProfesionalesId());
                }                
                cntProfesionalPrestador.setActivo(true);
                cntProfesionalPrestador.setMaEspecialidadCodigo(bean.getObjeto().getMaEspecialidadCodigo());
                cntProfesionalPrestador.setMaEspecialidadId(bean.getObjeto().getMaEspecialidadId());
                cntProfesionalPrestador.setMaEspecialidadValor(bean.getObjeto().getMaEspecialidadValor());
                bean.getListaCntProfesionalPrestador().add(cntProfesionalPrestador);
            }
        } catch (Exception e) {
            bean.addError("Hubo un error al agregar el prestador y especialidad, favor contactar al adminitrador");
        }
    }

    private void listar(ProfesionalesBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getCntPrestadorProfesionalRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getCntPrestadorProfesionalRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(ProfesionalesBean bean) {
        try {
            if (bean.getConexion().getEmpresa().getCntPrestador() != null) {
                bean.setListaCntProfesionalPrestador(getCntPrestadorProfesionalRemoto().consultarListaPorProfesionalYPrestador(bean.getObjeto().getCntProfesionalesId().getId(), bean.getConexion().getEmpresa().getCntPrestador().getId()));
            } else {
                bean.setListaCntProfesionalPrestador(getCntPrestadorProfesionalRemoto().consultarPorProfesional(bean.getObjeto().getCntProfesionalesId().getId()));
            }

            if (!bean.getListaCntProfesionalPrestador().isEmpty()) {
                bean.setObjeto(new CntProfesionalPrestador());
                bean.getObjeto().setActivo(bean.getListaCntProfesionalPrestador().get(0).isActivo());
                bean.getObjeto().setActualizar(bean.getListaCntProfesionalPrestador().get(0).isActualizar());
                bean.getObjeto().setCntPrestador(bean.getListaCntProfesionalPrestador().get(0).getCntPrestador());
                bean.getObjeto().setCntProfesionalesId(bean.getListaCntProfesionalPrestador().get(0).getCntProfesionalesId());
                bean.getObjeto().setFechaHoraCrea(bean.getListaCntProfesionalPrestador().get(0).getFechaHoraCrea());
                bean.getObjeto().setFechaHoraModifica(bean.getListaCntProfesionalPrestador().get(0).getFechaHoraModifica());
                bean.getObjeto().setId(bean.getListaCntProfesionalPrestador().get(0).getId());
                bean.getObjeto().setMaEspecialidadCodigo(bean.getListaCntProfesionalPrestador().get(0).getMaEspecialidadCodigo());
                bean.getObjeto().setMaEspecialidadId(bean.getListaCntProfesionalPrestador().get(0).getMaEspecialidadId());
                bean.getObjeto().setMaEspecialidadValor(bean.getListaCntProfesionalPrestador().get(0).getMaEspecialidadValor());
                bean.getObjeto().setTerminalCrea(bean.getListaCntProfesionalPrestador().get(0).getTerminalCrea());
                bean.getObjeto().setTerminalModifica(bean.getListaCntProfesionalPrestador().get(0).getTerminalModifica());
                bean.getObjeto().setUsuarioCrea(bean.getListaCntProfesionalPrestador().get(0).getUsuarioCrea());
                bean.getObjeto().setUsuarioModifica(bean.getListaCntProfesionalPrestador().get(0).getUsuarioModifica());
                bean.getObjeto().getCntProfesionalesId().setListaCntProfesionalPrestador(bean.getListaCntProfesionalPrestador());
                for (CntProfesionalPrestador cntProfesionalPrestador : bean.getObjeto().getCntProfesionalesId().getListaCntProfesionalPrestador()) {
                    cntProfesionalPrestador.setCntProfesionalesId(null);
                }
                
                consultarUbicacionPrestador(bean);
            }

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(ProfesionalesBean bean) {
        try {
            bean.setObjeto(new CntProfesionalPrestador());
            bean.getObjeto().setCntPrestador(new CntPrestador());
            bean.getObjeto().setCntProfesionalesId(new CntProfesional());
            bean.setObjetoPrestadorSede(new CntPrestadorSede());
            bean.setListaCntProfesionalPrestador(new ArrayList<>());
            bean.setAgregarEspecialidad(true);

            if (bean.getConexion().getEmpresa().getId().intValue() != ContratacionParametro.ID_EMPRESA_SAVIA && bean.getConexion().getEmpresa().getCntPrestador() != null) {
                bean.getObjeto().setCntPrestador(bean.getConexion().getEmpresa().getCntPrestador());
                consultarUbicacionPrestador(bean);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(ProfesionalesBean bean) {
        try {
            boolean validar = true;

            if ((bean.getObjeto().getMaEspecialidadId() == 0)) {
                bean.addError("Especialidad Profesional : Error de validación: se necesita un valor.");
                validar = false;
            }

            if (bean.getObjeto().getCntProfesionalesId().isGuardar()) {
                if (bean.getObjeto().getCntProfesionalesId().getPrimerNombre() == null || bean.getObjeto().getCntProfesionalesId().getPrimerNombre().isBlank()) {
                    bean.addError("Primer Nombre Profesional : Error de validación: se necesita un valor.");
                    validar = false;
                }
                if (bean.getObjeto().getCntProfesionalesId().getPrimerApellido() == null || bean.getObjeto().getCntProfesionalesId().getPrimerApellido().isBlank()) {
                    bean.addError("Primer Apellido Profesional : Error de validación: se necesita un valor.");
                    validar = false;
                }
                if (bean.getObjeto().getCntProfesionalesId().getRegistroMedico() == null || bean.getObjeto().getCntProfesionalesId().getRegistroMedico().isBlank()) {
                    bean.addError("Registro médico : Error de validación: se necesita un valor.");
                    validar = false;
                }
            }

            if (!validar) {
                return;
            }

            if (bean.getObjeto().getCntProfesionalesId().isGuardar()) {
                Maestro maestroProTipoDocumento = bean.getHashTiposDocumentoProfesional().get(bean.getObjeto().getCntProfesionalesId().getMaeTipoCodumentoId());
                bean.getObjeto().getCntProfesionalesId().setMaeTipoDocumentoCodigo(maestroProTipoDocumento.getValor());
                bean.getObjeto().getCntProfesionalesId().setMaeTipoDocumentoValor(maestroProTipoDocumento.getNombre());
                bean.auditoriaGuardar(bean.getObjeto().getCntProfesionalesId());
                bean.getObjeto().getCntProfesionalesId().setId(getCntProfesionalRemoto().insertar(bean.getObjeto().getCntProfesionalesId()));
            }

            for (CntProfesionalPrestador item : bean.getListaCntProfesionalPrestador()) {
                if (item.getId() == null || item.getId() == 0) {
                    if (bean.getObjeto().getCntProfesionalesId().isGuardar()) {
                        item.setCntProfesionalesId(new CntProfesional(bean.getObjeto().getCntProfesionalesId().getId()));
                    }
                    bean.auditoriaGuardar(item);
                    item.setId(getCntPrestadorProfesionalRemoto().insertar(item));
                }
            }
            bean.getObjeto().getCntProfesionalesId().setListaCntProfesionalPrestador(bean.getListaCntProfesionalPrestador());

            bean.addMensaje("El registro se creo correctamente");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(ProfesionalesBean bean) {
        try {
            CntProfesionalPrestador cntProfesionalPrestador = getCntPrestadorProfesionalRemoto().consultar(bean.getObjeto().getId());
            bean.setObjeto(cntProfesionalPrestador);
            consultarUbicacionPrestador(bean);
            if (bean.isAccionAdicional1()) {
                bean.getObjeto().setCntProfesionalesId(getCntProfesionalRemoto().consultar(bean.getObjeto().getCntProfesionalesId().getId()));
            }

            if (bean.getConexion().getEmpresa().getCntPrestador() != null) {
                bean.setListaCntProfesionalPrestador(getCntPrestadorProfesionalRemoto().consultarListaPorProfesionalYPrestador(bean.getObjeto().getCntProfesionalesId().getId(), bean.getConexion().getEmpresa().getCntPrestador().getId()));
            } else {
                bean.setListaCntProfesionalPrestador(getCntPrestadorProfesionalRemoto().consultarPorProfesional(bean.getObjeto().getCntProfesionalesId().getId()));
            }
            bean.getObjeto().getCntProfesionalesId().setListaCntProfesionalPrestador(bean.getListaCntProfesionalPrestador());

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(ProfesionalesBean bean) {
        try {
            if (bean.isAccionAdicional1()) {
                bean.auditoriaModificar(bean.getObjeto().getCntProfesionalesId());
                getCntProfesionalRemoto().actualizar(bean.getObjeto().getCntProfesionalesId());
            }

            for (CntProfesionalPrestador item : bean.getListaCntProfesionalPrestador()) {
                if (item.getId() == null || item.getId() == 0) {
                    bean.auditoriaGuardar(item);
                    item.setId(getCntPrestadorProfesionalRemoto().insertar(item));
                } else if (item.isActualizar()) {
                    bean.auditoriaModificar(item);
                    getCntPrestadorProfesionalRemoto().actualizar(item);
                }
                item.setCntProfesionalesId(new CntProfesional(bean.getObjeto().getCntProfesionalesId().getId()));
            }
            
            bean.getObjeto().getCntProfesionalesId().setListaCntProfesionalPrestador(bean.getListaCntProfesionalPrestador());
            if (bean.getListaCntProfesonalPrestadorBorrar() != null) {
                for (CntProfesionalPrestador item : bean.getListaCntProfesonalPrestadorBorrar()) {
                    if (item.getId() != null) {
                        getCntPrestadorProfesionalRemoto().eliminar(item.getId());
                    }
                }
            }            
            bean.addMensaje("El registro se modificó correctamente");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(ProfesionalesBean bean) {
        try {
            bean.getObjeto().setCntProfesionalesId(getCntProfesionalRemoto().eliminar(bean.getObjeto().getCntProfesionalesId().getId()));
            if (bean.getObjeto().getCntProfesionalesId() == null) {
                bean.addError("El profesional esta asociado alguna solicitudes dentro del sistema, por lo cual no puede ser eliminado.");
            } else {
                bean.addMensaje("Se eliminó el registro de manera exitosa");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrarProfesionalIps(ProfesionalesBean bean) {
        try {
            bean.setObjeto(getCntPrestadorProfesionalRemoto().eliminar(bean.getObjeto().getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
            consultarPrestadorProfesional(bean);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void consultarUbicacionPrestador(ProfesionalesBean bean) {
        try {
            int cntPrestadorId = 0;
            if (bean.getConexion().getEmpresa().getId().intValue() != ContratacionParametro.ID_EMPRESA_SAVIA && bean.getConexion().getEmpresa().getCntPrestador() != null) {
                cntPrestadorId = bean.getConexion().getEmpresa().getCntPrestador().getId();
            } else {
                cntPrestadorId = bean.getObjeto().getCntPrestador().getId();
            }
            List<CntPrestadorSede> cntPrestadorSedes = getCntPrestadorSedeRemoto().consultarPorPrestador(cntPrestadorId);
            if (cntPrestadorSedes != null && !cntPrestadorSedes.isEmpty()) {
                bean.setObjetoPrestadorSede(cntPrestadorSedes.get(0));
                bean.getObjetoPrestadorSede().setUbicacion(getUbicacionRemoto().consultar(bean.getObjetoPrestadorSede().getUbicacionId()));
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void consultarPrestadorProfesional(ProfesionalesBean bean) {
        try {
            List<CntProfesionalPrestador> temp = new ArrayList<>();
            for (CntProfesionalPrestador item : bean.getListaCntProfesionalPrestador()) {
                if (item.getId() == null) {
                    temp.add(item);
                }
            }

            if (bean.getConexion().getEmpresa().getId().intValue() != ContratacionParametro.ID_EMPRESA_SAVIA && bean.getConexion().getEmpresa().getCntPrestador() != null) {
                if (bean.getObjeto() != null && bean.getObjeto().getCntProfesionalesId() != null && bean.getObjeto().getCntProfesionalesId().getId() != null
                        && bean.getObjeto().getCntPrestador() != null && bean.getObjeto().getCntPrestador().getId() != null) {
                    bean.setListaCntProfesionalPrestador(getCntPrestadorProfesionalRemoto().consultarListaPorProfesionalYPrestador(bean.getObjeto().getCntProfesionalesId().getId(), bean.getObjeto().getCntPrestador().getId()));
                }
            } else {
                bean.setListaCntProfesionalPrestador(getCntPrestadorProfesionalRemoto().consultarPorProfesional(bean.getObjeto().getCntProfesionalesId().getId()));
            }

            bean.getListaCntProfesionalPrestador().addAll(temp);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void existePrestadorEspecialidad(ProfesionalesBean bean) {
        bean.setAgregarEspecialidad(true);

        if (bean.getObjeto().getCntPrestador() != null) {
            for (CntProfesionalPrestador item : bean.getListaCntProfesionalPrestador()) {
                if (item.getId() != null && item.getCntPrestador().getId().intValue() == bean.getObjeto().getCntPrestador().getId().intValue()) {
                    bean.getObjeto().setMaEspecialidadCodigo(item.getMaEspecialidadCodigo());
                    bean.getObjeto().setMaEspecialidadValor(item.getMaEspecialidadValor());
                    bean.getObjeto().setMaEspecialidadId(item.getMaEspecialidadId());
                    bean.setAgregarEspecialidad(false);
                }
            }
        }
    }

}
