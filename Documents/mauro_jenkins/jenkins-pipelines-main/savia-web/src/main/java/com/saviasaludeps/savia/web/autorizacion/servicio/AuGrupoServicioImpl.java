package com.saviasaludeps.savia.web.autorizacion.servicio;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroAccion;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Item;
import com.saviasaludeps.savia.dominio.autorizacion.AuGrupo;
import com.saviasaludeps.savia.dominio.autorizacion.AuGrupoDiagnostico;
import com.saviasaludeps.savia.dominio.autorizacion.AuGrupoHistorico;
import com.saviasaludeps.savia.dominio.autorizacion.AuGrupoPrograma;
import com.saviasaludeps.savia.dominio.autorizacion.AuGrupoRegion;
import com.saviasaludeps.savia.dominio.autorizacion.AuGrupoSede;
import com.saviasaludeps.savia.dominio.autorizacion.AuGrupoTecnologia;
import com.saviasaludeps.savia.dominio.autorizacion.AuGrupoUsuario;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.maestro.MaDiagnostico;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.administracion.UsuarioRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo3ItemRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuGrupoDiagnosticoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuGrupoHistoricoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuGrupoProgramaRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuGrupoRegionRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuGrupoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuGrupoSedeRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuGrupoTecnologiaRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuGrupoUsuarioRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.negocio.especial.PeProgramaRemoto;
import com.saviasaludeps.savia.web.autorizacion.bean.GrupoBean;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuConstantes;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class AuGrupoServicioImpl extends AccionesBO implements AuGrupoServicioIface {

    private AuGrupoRemoto getAuGrupoRemoto() throws Exception {
        return (AuGrupoRemoto) RemotoEJB.getEJBRemoto("AuGrupoServicio", AuGrupoRemoto.class.getName());
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }

    private UsuarioRemoto getUsuarioRemoto() throws Exception {
        return (UsuarioRemoto) RemotoEJB.getEJBRemoto("UsuarioServicio", UsuarioRemoto.class.getName());
    }

    private AuGrupoUsuarioRemoto getAuGrupoUsuarioRemoto() throws Exception {
        return (AuGrupoUsuarioRemoto) RemotoEJB.getEJBRemoto("AuGrupoUsuarioServicio", AuGrupoUsuarioRemoto.class.getName());
    }

    private CntPrestadorRemoto getPrestadorRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }

    private AuGrupoSedeRemoto getAuGrupoSedeRemoto() throws Exception {
        return (AuGrupoSedeRemoto) RemotoEJB.getEJBRemoto("AuGrupoSedeServicio", AuGrupoSedeRemoto.class.getName());
    }

    private AuGrupoDiagnosticoRemoto getAuGrupoDiagnosticoRemoto() throws Exception {
        return (AuGrupoDiagnosticoRemoto) RemotoEJB.getEJBRemoto("AuGrupoDiagnosticoServicio", AuGrupoDiagnosticoRemoto.class.getName());
    }

    private AuGrupoTecnologiaRemoto getAuGrupoTecnologiaRemoto() throws Exception {
        return (AuGrupoTecnologiaRemoto) RemotoEJB.getEJBRemoto("AuGrupoTecnologiaServicio", AuGrupoTecnologiaRemoto.class.getName());
    }

    private AuGrupoRegionRemoto getAuGrupoRegionRemoto() throws Exception {
        return (AuGrupoRegionRemoto) RemotoEJB.getEJBRemoto("AuGrupoRegionServicio", AuGrupoRegionRemoto.class.getName());
    }

    private AuGrupoHistoricoRemoto getAuGrupoHistoricoRemoto() throws Exception {
        return (AuGrupoHistoricoRemoto) RemotoEJB.getEJBRemoto("AuGrupoHistoricoServicio", AuGrupoHistoricoRemoto.class.getName());
    }

    private AuAnexo3ItemRemoto getAuAnexo3ItemRemoto() throws Exception {
        return (AuAnexo3ItemRemoto) RemotoEJB.getEJBRemoto("AuAnexo3ItemServicio", AuAnexo3ItemRemoto.class.getName());
    }

    private AuGrupoProgramaRemoto getAuGrupoProgramaRemoto() throws Exception {
        return (AuGrupoProgramaRemoto) RemotoEJB.getEJBRemoto("AuGrupoProgramaServicio", AuGrupoProgramaRemoto.class.getName());
    }

    private PeProgramaRemoto getPeProgramaRemoto() throws Exception {
        return (PeProgramaRemoto) RemotoEJB.getEJBRemoto("PeProgramaServicio", PeProgramaRemoto.class.getName());
    }

    @Override
    public void Accion(GrupoBean bean) {
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
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_MODIFICAR:
                            modificar(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            modificarSoloGrupo(bean);
                            break;
                    }
                    break;
                case Url.ACCION_BORRAR:
                    borrar(bean);
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_VER:
                            gestionar(bean);
                            break;
                        case Url.ACCION_MODIFICAR:
                            reasignarItems(bean);
                            break;
                    }
                    break;
                default:
                    break;
            }
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    @Override
    public void cargaInicial(GrupoBean bean) {
        try {
            //Ambitos
            bean.setListaAmbitos(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_AMBITO));
            bean.setHashAmbitos(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GN_AMBITO));
            //Regiones
            bean.setListaRegiones(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_REGION));
            bean.setHashRegiones(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GN_REGION));
            //Ubicaciones
            bean.setHashUbicaciones(UbicacionSingle.getInstance().getHashUbicaciones());
            bean.setListaCiudades(UbicacionSingle.getInstance().getListaMunicipios());
            //Tipo Documento
            bean.setListaTipoDocumento(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_EMPRESA));
            bean.setHashTipoDocumento(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_EMPRESA));
            //Tipo auditor
            bean.setListaTipoAuditor(getMaestroRemoto().consultarPorTipo(MaestroTipo.AU_TIPO_AUDITOR));
            bean.setHashTipoAuditor(AuConstantes.obtenerHashMaestro(bean.getListaTipoAuditor()));
            //Seguimiento servicio
            bean.setListaSeguimientoServicio(getMaestroRemoto().consultarPorTipo(MaestroTipo.AU_SEGUIMIENTO_TIPO_SERVICIO));
            bean.setHashSeguimientoServicio(AuConstantes.obtenerHashMaestro(bean.getListaSeguimientoServicio()));
            //Tipo tecnologia
            bean.setListaTipoTecnologia(AuConstantes.obtenerMaestroTipoTecnologias());
        } catch (Exception e) {
            bean.addError("Hubo un error en la carga inicial, favor contactar con el administrador");
        }
    }

    private void listar(GrupoBean bean) {
        try {
            bean.getParamConsulta(0).setCantidadRegistros(getAuGrupoRemoto().consultarCantidadLista(bean.getParamConsulta(0)));
            bean.setRegistros(getAuGrupoRemoto().consultarLista(bean.getParamConsulta(0)));
        } catch (Exception ex) {
            bean.addError("Hubo un fallo listando, favor consultar con el administrador");
        }
    }

    private void ver(GrupoBean bean) {
        try {
            bean.setObjeto(getAuGrupoRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setAuGrupoRegionList(getAuGrupoRegionRemoto().consultarListaPorIdGrupo(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(GrupoBean bean) {
        try {
            bean.setObjeto(new AuGrupo());
            bean.getParamConsulta(0).setFiltros(new HashMap());
            int cantidad = getAuGrupoRemoto().consultarCantidadLista(bean.getParamConsulta(0));
            cantidad = cantidad + 1;
            bean.getObjeto().setOrden(cantidad);
            bean.getObjeto().setActivo(true);
            bean.getObjeto().setAuGrupoUsuarioList(new ArrayList());
            bean.getObjeto().setAuGrupoRegionList(new ArrayList());
            bean.getObjeto().setAuGrupoSedeList(new ArrayList());
            bean.getObjeto().setAuGrupoDiagnosticoList(new ArrayList());
            bean.getObjeto().setAuGrupoTecnologiaList(new ArrayList());
            bean.getObjeto().setAuGrupoProgramasList(new ArrayList());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(GrupoBean bean) {
        try {
            boolean fallo = false;
            //Insercion Grupo
            bean.auditoriaGuardar(bean.getObjeto());
            bean.getParamConsulta(0).setFiltros(new HashMap());
            bean.getParamConsulta(0).setCantidadRegistros(getAuGrupoRemoto().consultarCantidadLista(bean.getParamConsulta(0)));
            boolean ordenar = false;
            String nombre = bean.getObjeto().getNombre();
            try {
                if (bean.getObjeto().getOrden() < bean.getParamConsulta(0).getCantidadRegistros()) {
                    bean.setListaGrupos(getAuGrupoRemoto().consultarPorOrden(bean.getObjeto().getOrden()));
                    ordenar = true;
                } else if (bean.getObjeto().getOrden() >= bean.getParamConsulta(0).getCantidadRegistros()) {
                    int cantidad = bean.getParamConsulta(0).getCantidadRegistros() + 1;
                    bean.getObjeto().setOrden(cantidad);
                }
            } catch (Exception e) {
            }
            bean.getObjeto().setId(getAuGrupoRemoto().insertar(bean.getObjeto()));
            //Insercion Auditores
            for (AuGrupoUsuario auditor : bean.getObjeto().getAuGrupoUsuarioList()) {
                auditor.setAuGrupo(bean.getObjeto());
                bean.auditoriaGuardar(auditor);
                try {
                    Maestro tipoAuditor = bean.getHashTipoAuditor().get(auditor.getMaeTipoAuditorId());
                    auditor.setMaeTipoAuditorCodigo(tipoAuditor.getValor());
                    auditor.setMaeTipoAuditorValor(tipoAuditor.getNombre());
                    auditor.setTipo(Integer.parseInt(tipoAuditor.getValor()));

                    auditor.setId(getAuGrupoUsuarioRemoto().insertar(auditor));
                } catch (Exception e) {
                    bean.addError("Hubo un fallo guardando los auditores, favor contactar con el administrador");
                    fallo = true;
                }
            }
            if (!fallo) {
                //Insercion Regiones
                if (bean.getObjeto().getAuGrupoRegionList() != null) {
                    for (AuGrupoRegion region : bean.getObjeto().getAuGrupoRegionList()) {
                        region.setAuGrupo(bean.getObjeto());
                        bean.auditoriaGuardar(region);
                        try {
                            region.setId(getAuGrupoRegionRemoto().insertar(region));
                        } catch (Exception e) {
                            bean.addError("Hubo un fallo guardando las regiones, favor comunicarse con el administrador");
                            fallo = true;
                        }
                    }
                }
            }
            if (!fallo) {
                //Insercion Sedes
                for (AuGrupoSede sede : bean.getObjeto().getAuGrupoSedeList()) {
                    sede.setAuGrupo(bean.getObjeto());
                    bean.auditoriaGuardar(sede);
                    try {
                        sede.setId(getAuGrupoSedeRemoto().insertar(sede));
                    } catch (Exception e) {
                        bean.addError("Hubo un fallo guardando las sedes, favor comunicarse con el adminsitrador");
                        fallo = true;
                    }
                }
            }
            if (!fallo) {
                //Insercion Diagnosticos
                for (AuGrupoDiagnostico diagnostico : bean.getObjeto().getAuGrupoDiagnosticoList()) {
                    diagnostico.setAuGrupo(bean.getObjeto());
                    bean.auditoriaGuardar(diagnostico);
                    try {
                        diagnostico.setId(getAuGrupoDiagnosticoRemoto().insertar(diagnostico));
                    } catch (Exception e) {
                        bean.addError("Hubo un fallo guardado los diagnosticos, favor comunicarse con el administrador");
                        fallo = true;
                    }
                }
            }
            if (!fallo) {
                //Insercion Tecnologias
                for (AuGrupoTecnologia tecnologia : bean.getObjeto().getAuGrupoTecnologiaList()) {
                    tecnologia.setAuGrupo(bean.getObjeto());
                    bean.auditoriaGuardar(tecnologia);
                    try {
                        Maestro tipoAuditor = bean.getHashTipoAuditor().get(tecnologia.getMaeTipoAuditorId());
                        tecnologia.setMaeTipoAuditorCodigo(tipoAuditor.getValor());
                        tecnologia.setMaeTipoAuditorValor(tipoAuditor.getNombre());
                        tecnologia.setTipo(Integer.parseInt(tipoAuditor.getValor()));

                        if (tecnologia.getMaeSeguimientoServicioId() != null) {
                            Maestro tipoSeguimiento = bean.getHashSeguimientoServicio().get(tecnologia.getMaeSeguimientoServicioId());
                            tecnologia.setMaeSeguimientoServicioCodigo(tipoSeguimiento.getValor());
                            tecnologia.setMaeSeguimientoServicioValor(tipoSeguimiento.getNombre());
                        } else {
                            tecnologia.setMaeSeguimientoServicioCodigo(null);
                            tecnologia.setMaeSeguimientoServicioValor(null);
                        }

                        tecnologia.setId(getAuGrupoTecnologiaRemoto().insertar(tecnologia));
                    } catch (Exception e) {
                        bean.addError("Hubo un fallo guardando las tecnologías, favor comunicarse con el administrador");
                        fallo = true;
                    }
                }
            }
            if (!fallo) {
                //Insercion Programas
                if (bean.getObjeto().getAuGrupoProgramasList() != null) {
                    for (AuGrupoPrograma programa : bean.getObjeto().getAuGrupoProgramasList()) {
                        programa.setAuGrupoId(bean.getObjeto());
                        bean.auditoriaGuardar(programa);
                        try {
                            programa.setId(getAuGrupoProgramaRemoto().insertar(programa));
                        } catch (Exception e) {
                            bean.addError("Hubo un fallo guardando los programas, favor comunicarse con el administrador");
                            fallo = true;
                        }
                    }
                }
            }
            if (fallo) {
                try {
                    for (AuGrupoUsuario usuario : bean.getObjeto().getAuGrupoUsuarioList()) {
                        if (usuario.getId() != null) {
                            getAuGrupoUsuarioRemoto().eliminar(usuario.getId());
                        }
                    }
                    for (AuGrupoSede sede : bean.getObjeto().getAuGrupoSedeList()) {
                        if (sede.getId() != null) {
                            getAuGrupoSedeRemoto().eliminar(sede.getId());
                        }
                    }
                    for (AuGrupoRegion region : bean.getObjeto().getAuGrupoRegionList()) {
                        if (region.getId() != null) {
                            getAuGrupoRegionRemoto().eliminar(region.getId());
                        }
                    }
                    for (AuGrupoDiagnostico diagnostico : bean.getObjeto().getAuGrupoDiagnosticoList()) {
                        if (diagnostico.getId() != null) {
                            getAuGrupoDiagnosticoRemoto().eliminar(diagnostico.getId());
                        }
                    }
                    for (AuGrupoTecnologia tecnologia : bean.getObjeto().getAuGrupoTecnologiaList()) {
                        if (tecnologia.getId() != null) {
                            getAuGrupoTecnologiaRemoto().eliminar(tecnologia.getId());
                        }
                    }
                    getAuGrupoRemoto().eliminar(bean.getObjeto().getId());
                } catch (Exception e) {
                    bean.addError("Fallo la eliminación, favor contactar con el administrador");
                }
            } else {
                try {
                    //Insercion Historico
                    AuGrupoHistorico historico = new AuGrupoHistorico();
                    historico.setAuGruposId(bean.getObjeto());
                    historico.setToString(bean.getObjeto().toString());
                    bean.auditoriaGuardar(historico);
                    getAuGrupoHistoricoRemoto().insertar(historico);
                    int inicio = 0;
                    if (bean.getListaGrupos() == null) {
                        bean.setListaGrupos(new ArrayList());
                    }
                    int fin = bean.getListaGrupos().size() - 1;
                    if (bean.getObjeto().getOrden() > bean.getParamConsulta(0).getCantidadRegistros()) {
                        int nuevoOrden = bean.getParamConsulta(0).getCantidadRegistros() + 1;
                        bean.getObjeto().setOrden(nuevoOrden);
                    }
                    int orden = bean.getObjeto().getOrden() + 1;
                    calcularReorden(inicio, fin, orden, bean);
                } catch (Exception e) {
                    bean.addError("Hubo un fallo guardando el historico, favor comunicarse con el administrador");
                }
                bean.addMensaje("Se guardo el grupo " + nombre + " con exito");
            }

        } catch (Exception ex) {
            bean.addError(ex.toString());
        }
    }

    private void editar(GrupoBean bean) {
        try {
            bean.setObjeto(getAuGrupoRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setAuGrupoRegionList(getAuGrupoRegionRemoto().consultarListaPorIdGrupo(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(GrupoBean bean) {
        try {
            boolean fallo = false;
            //Insercion Grupo
            bean.auditoriaModificar(bean.getObjeto());
            getAuGrupoRemoto().actualizar(bean.getObjeto());
            //Insercion Auditores
            for (AuGrupoUsuario auditor : bean.getObjeto().getAuGrupoUsuarioList()) {
                try {
                    Maestro tipoAuditor = bean.getHashTipoAuditor().get(auditor.getMaeTipoAuditorId());
                    auditor.setMaeTipoAuditorCodigo(tipoAuditor.getValor());
                    auditor.setMaeTipoAuditorValor(tipoAuditor.getNombre());
                    auditor.setTipo(Integer.parseInt(tipoAuditor.getValor()));
                    if (auditor.getId() != null) {
                        bean.auditoriaModificar(auditor);
                        getAuGrupoUsuarioRemoto().actualizar(auditor);
                    } else {
                        auditor.setAuGrupo(bean.getObjeto());
                        bean.auditoriaGuardar(auditor);
                        auditor.setId(getAuGrupoUsuarioRemoto().insertar(auditor));
                    }
                } catch (Exception e) {
                    bean.addError("Hubo un fallo actualizando los auditores, favor contactar con el administrador");
                    fallo = true;
                }
            }
            if (!fallo) {
                //Insercion Regiones
                for (AuGrupoRegion region : bean.getObjeto().getAuGrupoRegionList()) {
                    if (region.getId() == null) {
                        try {
                            region.setAuGrupo(bean.getObjeto());
                            bean.auditoriaGuardar(region);
                            region.setId(getAuGrupoRegionRemoto().insertar(region));
                        } catch (Exception e) {
                            bean.addError("Hubo un fallo guardando las regiones, favor comunicarse con el administrador");
                            fallo = true;
                        }
                    }
                }
            }
            if (!fallo) {
                //Insercion Sedes
                for (AuGrupoSede sede : bean.getObjeto().getAuGrupoSedeList()) {
                    if (sede.getId() == null) {
                        try {
                            sede.setAuGrupo(bean.getObjeto());
                            bean.auditoriaGuardar(sede);
                            sede.setId(getAuGrupoSedeRemoto().insertar(sede));
                        } catch (Exception e) {
                            bean.addError("Hubo un fallo guardando las sedes, favor comunicarse con el adminsitrador");
                            fallo = true;
                        }
                    }
                }
            }
            if (!fallo) {
                //Insercion Diagnosticos
                for (AuGrupoDiagnostico diagnostico : bean.getObjeto().getAuGrupoDiagnosticoList()) {
                    if (diagnostico.getId() == null) {
                        try {
                            diagnostico.setAuGrupo(bean.getObjeto());
                            bean.auditoriaGuardar(diagnostico);
                            diagnostico.setId(getAuGrupoDiagnosticoRemoto().insertar(diagnostico));
                        } catch (Exception e) {
                            bean.addError("Hubo un fallo guardado los diagnosticos, favor comunicarse con el administrador");
                            fallo = true;
                        }
                    }
                }
            }
            if (!fallo) {
                //Insercion Tecnologias
                for (AuGrupoTecnologia tecnologia : bean.getObjeto().getAuGrupoTecnologiaList()) {
                    try {
                        Maestro tipoAuditor = bean.getHashTipoAuditor().get(tecnologia.getMaeTipoAuditorId());
                        tecnologia.setMaeTipoAuditorCodigo(tipoAuditor.getValor());
                        tecnologia.setMaeTipoAuditorValor(tipoAuditor.getNombre());
                        tecnologia.setTipo(Integer.parseInt(tipoAuditor.getValor()));
                        if (tecnologia.getMaeSeguimientoServicioId() != null) {
                            Maestro tipoSeguimiento = bean.getHashSeguimientoServicio().get(tecnologia.getMaeSeguimientoServicioId());
                            tecnologia.setMaeSeguimientoServicioCodigo(tipoSeguimiento.getValor());
                            tecnologia.setMaeSeguimientoServicioValor(tipoSeguimiento.getNombre());
                        } else {
                            tecnologia.setMaeSeguimientoServicioCodigo(null);
                            tecnologia.setMaeSeguimientoServicioValor(null);
                        }

                        if (tecnologia.getId() == null) {
                            tecnologia.setAuGrupo(bean.getObjeto());
                            bean.auditoriaGuardar(tecnologia);
                            tecnologia.setId(getAuGrupoTecnologiaRemoto().insertar(tecnologia));
                        } else {
                            bean.auditoriaModificar(tecnologia);
                            getAuGrupoTecnologiaRemoto().actualizar(tecnologia);
                        }
                    } catch (Exception e) {
                        bean.addError("Hubo un fallo modificando las tecnologías, favor comunicarse con el adminstrador");
                        fallo = true;
                    }
                }
            }
            if (!fallo) {
                //Insercion programas
                for (AuGrupoPrograma programa : bean.getObjeto().getAuGrupoProgramasList()) {
                    if (programa.getId() == null) {
                        try {
                            programa.setAuGrupoId(bean.getObjeto());
                            bean.auditoriaGuardar(programa);
                            programa.setId(getAuGrupoProgramaRemoto().insertar(programa));
                        } catch (Exception e) {
                            bean.addError("Hubo un fallo guardando los programas, favor comunicarse con el administrador");
                            fallo = true;
                        }
                    } else {
                        try {
                            bean.auditoriaModificar(programa);
                            getAuGrupoProgramaRemoto().actualizar(programa);
                        } catch (Exception e) {
                        }
                    }
                }
            }
            if (!fallo) {
                try {
                    //Insercion Historico
                    AuGrupoHistorico historico = new AuGrupoHistorico();
                    historico.setAuGruposId(bean.getObjeto());
                    historico.setToString(bean.getObjeto().toString());
                    bean.auditoriaGuardar(historico);
                    getAuGrupoHistoricoRemoto().insertar(historico);
                } catch (Exception e) {
                    bean.addError("Hubo un fallo guardando el historico, favor comunicarse con el administrador");
                }
                bean.addMensaje("Se actualizo el grupo " + bean.getObjeto().getNombre() + " con exito");
            }
        } catch (Exception ex) {
            bean.addError(ex.toString());
        }
    }

    private void borrar(GrupoBean bean) {
        try {
            bean.setObjeto(getAuGrupoRemoto().consultar(bean.getObjeto().getId()));
            // Items
            bean.getObjeto().setAuAnexo3ItemList(getAuAnexo3ItemRemoto().listarPorIdGrupo(bean.getObjeto().getId()));
            if (bean.getObjeto().getAuAnexo3ItemList() != null && !bean.getObjeto().getAuAnexo3ItemList().isEmpty()) {
                bean.addError("Este grupo tiene solicitudes asociadas, favor derivar antes de eliminar");
                return;
            }
            // Calculos para volver a ordenar
            int ordenBuscar = bean.getObjeto().getOrden() + 1;
            bean.setListaGrupos(getAuGrupoRemoto().consultarPorOrden(ordenBuscar));
            int inicio = 0;
            int fin = bean.getListaGrupos().size() - 1;
            int orden = bean.getObjeto().getOrden();
            //Diagnosticos
            bean.getObjeto().setAuGrupoDiagnosticoList(getAuGrupoDiagnosticoRemoto().consultarListaPorIdGrupo(bean.getObjeto().getId()));
            for (AuGrupoDiagnostico diagnostico : bean.getObjeto().getAuGrupoDiagnosticoList()) {
                getAuGrupoDiagnosticoRemoto().eliminar(diagnostico.getId());
            }
            //Historicos
            bean.getObjeto().setAuGrupoHistoricoList(getAuGrupoHistoricoRemoto().consultarListaPorIdGrupo(bean.getObjeto().getId()));
            for (AuGrupoHistorico historico : bean.getObjeto().getAuGrupoHistoricoList()) {
                getAuGrupoDiagnosticoRemoto().eliminar(historico.getId());
            }
            //Regiones
            bean.getObjeto().setAuGrupoRegionList(getAuGrupoRegionRemoto().consultarListaPorIdGrupo(bean.getObjeto().getId()));
            for (AuGrupoRegion region : bean.getObjeto().getAuGrupoRegionList()) {
                getAuGrupoRegionRemoto().eliminar(region.getId());
            }
            //Sedes
            bean.getObjeto().setAuGrupoSedeList(getAuGrupoSedeRemoto().consultarListaPorIdGrupo(bean.getObjeto().getId()));
            for (AuGrupoSede sede : bean.getObjeto().getAuGrupoSedeList()) {
                getAuGrupoSedeRemoto().eliminar(sede.getId());
            }
            //Tecnologias
            bean.getObjeto().setAuGrupoTecnologiaList(getAuGrupoTecnologiaRemoto().consultarListaPorIdGrupo(bean.getObjeto().getId()));
            for (AuGrupoTecnologia tecnologia : bean.getObjeto().getAuGrupoTecnologiaList()) {
                getAuGrupoTecnologiaRemoto().eliminar(tecnologia.getId());
            }
            //Usuarios
            bean.getObjeto().setAuGrupoUsuarioList(getAuGrupoUsuarioRemoto().consultarListaPorIdGrupo(bean.getObjeto().getId()));
            for (AuGrupoUsuario usuario : bean.getObjeto().getAuGrupoUsuarioList()) {
                getAuGrupoUsuarioRemoto().eliminar(usuario.getId());
            }
            getAuGrupoRemoto().eliminar(bean.getObjeto().getId());
            bean.addMensaje("Se eliminó el grupo " + bean.getObjeto().getNombre() + " con éxito");
            calcularReorden(inicio, fin, orden, bean);
        } catch (Exception e) {
            bean.addError("Hubo un fallo al borrar, favor contactar con el administrador");
        }
    }

    @Override
    public void listarAuditores(GrupoBean bean) {
        try {
            bean.getParamConsulta(1).setEmpresaId(bean.getConexion().getEmpresa().getId());
            bean.getParamConsulta(1).setCantidadRegistros(getUsuarioRemoto().consultarCantidadLista(bean.getParamConsulta(1)));
            bean.setListaUsuarios(getUsuarioRemoto().consultarLista(bean.getParamConsulta(1)));
        } catch (Exception e) {
            bean.addError("Hubo un error consultando auditores, favor comunicarse con el administrador");
        }
    }

    @Override
    public void borrarAuditor(int id) {
        try {
            getAuGrupoUsuarioRemoto().eliminar(id);
        } catch (Exception e) {
        }
    }

    @Override
    public void listarSedes(GrupoBean bean) {
        try {
            if (bean.getConexion().getEmpresa().getId() > 1) {
                bean.getParamConsulta(2).setParametroConsulta5(bean.getConexion().getEmpresa().getCntPrestador().getId());
            }
            bean.getParamConsulta(2).getFiltros().put("cntContratosId.activo", true);
            bean.getParamConsulta(2).getFiltros().put("fecha", Util.fechaDateAString(new Date()));
            bean.getParamConsulta(2).setCantidadRegistros(getPrestadorRemoto().consultarCantidadListaSede(bean.getParamConsulta(2)));
            bean.setListaSedes(getPrestadorRemoto().consultarListaSede(bean.getParamConsulta(2)));
            bean.getListaSedes().forEach((ips) -> {
                try {
                    ips.setCntPrestador(getPrestadorRemoto().consultar(ips.getCntPrestador().getId()));
                } catch (Exception e) {
                    bean.addError(e.getMessage());
                }
            });
        } catch (Exception e) {
            bean.addError("Hubo un fallo listando las sedes, favor comunicarse con el adminsitrador");
        }
    }

    @Override
    public void borrarSede(int id) {
        try {
            getAuGrupoSedeRemoto().eliminar(id);
        } catch (Exception e) {
        }
    }

    @Override
    public void borrarDiagnostico(int id) {
        try {
            getAuGrupoDiagnosticoRemoto().eliminar(id);
        } catch (Exception e) {
        }
    }

    @Override
    public void borrarTecnologia(int id) {
        try {
            getAuGrupoTecnologiaRemoto().eliminar(id);
        } catch (Exception e) {
        }
    }

    @Override
    public void borrarRegion(int id) {
        try {
            getAuGrupoRegionRemoto().eliminar(id);
        } catch (Exception e) {
        }
    }

    @Override
    public void listarCasos(GrupoBean bean) {
        try {
            bean.getParamConsulta(3).setParametroConsulta1(bean.getObjeto().getId());
            bean.getParamConsulta(3).setCantidadRegistros(getAuAnexo3ItemRemoto().consultarCantidadLista(bean.getParamConsulta(3)));
            bean.setListaCasos(getAuAnexo3ItemRemoto().consultarLista(bean.getParamConsulta(3)));
        } catch (Exception e) {
            bean.addError("Hubo un fallo listando los casos, favor comunicarse con el adminsitrador");
        }
    }

    private void gestionar(GrupoBean bean) {
        try {
            bean.setObjeto(getAuGrupoRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al gestionar, favor comunicarse con el administrador");
        }
    }

    @Override
    public void listarGrupoUsuario(GrupoBean bean) {
        try {
            if (bean.getObjeto().getId() != null) {

                bean.getParamConsulta(4).setParametroConsulta1(bean.getObjeto().getId());
                List<AuGrupoUsuario> registros = getAuGrupoUsuarioRemoto().consultarLista(bean.getParamConsulta(4));
                int cantidadRegistros = getAuGrupoUsuarioRemoto().consultarCantidadLista(bean.getParamConsulta(4));
//                List<AuGrupoUsuario> nueva = unificarListasUsuarios(bean.getListaUsuarioModificar(), registros);
                List<AuGrupoUsuario> nueva = new ArrayList();
                nueva.addAll(bean.getListaUsuarioModificar());
                nueva.addAll(registros);
                cantidadRegistros = cantidadRegistros + bean.getListaUsuarioModificar().size();
                bean.getParamConsulta(4).setCantidadRegistros(cantidadRegistros);
                bean.setListaGrupoUsuario(nueva);

            } else {
                bean.getParamConsulta(4).setCantidadRegistros(bean.getListaUsuarioModificar().size());
                bean.setListaGrupoUsuario(bean.getListaUsuarioModificar());
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo listando los usuarios del grupo favor contactar al administrador");
        }
    }

    @Override
    public void listarGrupoSede(GrupoBean bean) {
        try {
            if (bean.getObjeto().getId() != null) {
                boolean limpiar = bean.getListaSedeModificar().isEmpty();
                List<AuGrupoSede> copia = bean.getListaSedeModificar();
                bean.getParamConsulta(5).setParametroConsulta1(bean.getObjeto().getId());
                List<AuGrupoSede> registros = getAuGrupoSedeRemoto().consultarLista(bean.getParamConsulta(5));
                int cantidadRegistros = registros.size();
                List<AuGrupoSede> nueva = unificarListasSedes(bean.getListaSedeModificar(), registros);
                cantidadRegistros = getAuGrupoSedeRemoto().consultarCantidadLista(bean.getParamConsulta(5)) + nueva.size() - cantidadRegistros;
                bean.getParamConsulta(5).setCantidadRegistros(cantidadRegistros);
                bean.setListaGrupoSedes(nueva);
                if (limpiar) {
                    bean.setListaSedeModificar(new ArrayList());
                } else {
                    bean.setListaSedeModificar(copia);
                }
            } else {
                bean.getParamConsulta(5).setCantidadRegistros(bean.getListaSedeModificar().size());
                bean.setListaGrupoSedes(bean.getListaSedeModificar());
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo listando las sedes del grupo, favor contactar al administrador");
        }
    }

    @Override
    public boolean validarAuditor(Usuario usuario, GrupoBean bean) {
        boolean validar;
        try {
            validar = getAuGrupoUsuarioRemoto().validarUsuario(usuario.getId(), bean.getObjeto().getId());
            validar = !validar;
        } catch (Exception e) {
            validar = false;
        }
        return validar;
    }

    @Override
    public boolean validarAuditorTipo(AuGrupoUsuario grupo) {
        boolean validar;
        try {
            validar = getAuGrupoUsuarioRemoto().validarUsuarioTipo(
                    grupo.getUsuario().getId(), grupo.getAuGrupo().getId(), grupo.getMaeTipoAuditorId()
            );
        } catch (Exception e) {
            validar = false;
        }
        return validar;
    }

    public List<AuGrupoUsuario> unificarListasUsuarios(List<AuGrupoUsuario> pendientes, List<AuGrupoUsuario> actuales) {
        if (pendientes.isEmpty()) {
            return actuales;
        } else {
            List<AuGrupoUsuario> nueva = new ArrayList();
            for (AuGrupoUsuario actual : actuales) {
                boolean agregar = true;
                for (AuGrupoUsuario pendiente : pendientes) {
                    if (actual.getUsuario().getId().equals(pendiente.getUsuario().getId())) {
                        nueva.add(pendiente);
                        agregar = false;
                        break;
                    }
                }
                if (agregar) {
                    nueva.add(actual);
                }
            }
            List<AuGrupoUsuario> nuevaPendientes = new ArrayList();
            for (AuGrupoUsuario pendiente : pendientes) {
                boolean agregar = true;
                for (AuGrupoUsuario actual : nueva) {
                    if (pendiente.getUsuario().getId().equals(actual.getUsuario().getId())) {
                        agregar = false;
                    }
                }
                if (agregar) {
                    nuevaPendientes.add(pendiente);
                }
            }
            nuevaPendientes.addAll(nueva);
            return nuevaPendientes;
        }
    }

    @Override
    public void listarGrupoDiagnostico(GrupoBean bean) {
        try {
            if (bean.getObjeto().getId() != null) {
                boolean limpiar = bean.getListaDiagnosticoModificar().isEmpty();
                List<AuGrupoDiagnostico> copia = bean.getListaDiagnosticoModificar();
                bean.getParamConsulta(6).setParametroConsulta1(bean.getObjeto().getId());
                List<AuGrupoDiagnostico> registros = getAuGrupoDiagnosticoRemoto().consultarLista(bean.getParamConsulta(6));
                int cantidadRegistros = registros.size();
                List<AuGrupoDiagnostico> nueva = unificarListasDiagnostico(bean.getListaDiagnosticoModificar(), registros);
                cantidadRegistros = getAuGrupoDiagnosticoRemoto().consultarCantidadLista(bean.getParamConsulta(6)) + nueva.size() - cantidadRegistros;
                bean.getParamConsulta(6).setCantidadRegistros(cantidadRegistros);
                bean.setListaGrupoDiagnosticos(nueva);
                if (limpiar) {
                    bean.setListaDiagnosticoModificar(new ArrayList());
                } else {
                    bean.setListaDiagnosticoModificar(copia);
                }
            } else {
                bean.getParamConsulta(6).setCantidadRegistros(bean.getListaDiagnosticoModificar().size());
                bean.setListaGrupoDiagnosticos(bean.getListaDiagnosticoModificar());
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo listando los diagnosticos, favor contactar con el administrador");
        }
    }

    @Override
    public void listarGrupoTecnologia(GrupoBean bean) {
        try {
            if (bean.getObjeto().getId() != null) {
                bean.getParamConsulta(7).setParametroConsulta2(AuGrupoTecnologia.ID_NA);
                boolean limpiar = bean.getListaTecnologiaModificar().isEmpty();
                List<AuGrupoTecnologia> copia = bean.getListaTecnologiaModificar();
                bean.getParamConsulta(7).setParametroConsulta1(bean.getObjeto().getId());
                List<AuGrupoTecnologia> registros = getAuGrupoTecnologiaRemoto().consultarLista(bean.getParamConsulta(7));
                int cantidadRegistros = registros.size();
                List<AuGrupoTecnologia> nueva = unificarListasTecnologia(bean.getListaTecnologiaModificar(), registros);
                cantidadRegistros = getAuGrupoTecnologiaRemoto().consultarCantidadLista(bean.getParamConsulta(7)) + nueva.size() - cantidadRegistros;
                bean.getParamConsulta(7).setCantidadRegistros(cantidadRegistros);
                bean.setListaGrupoTecnologia(nueva);
                if (limpiar) {
                    bean.setListaTecnologiaModificar(new ArrayList());
                } else {
                    bean.setListaTecnologiaModificar(copia);
                }
            } else {
                bean.getParamConsulta(7).setCantidadRegistros(bean.getListaTecnologiaModificar().size());
                bean.setListaGrupoTecnologia(bean.getListaTecnologiaModificar());
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo listando las tecnologías, favor contactar con el administrador");
        }
    }

    private List<AuGrupoSede> unificarListasSedes(List<AuGrupoSede> pendientes, List<AuGrupoSede> actuales) {
        if (pendientes.isEmpty()) {
            return actuales;
        } else {
            List<AuGrupoSede> nueva = new ArrayList();
            for (AuGrupoSede actual : actuales) {
                boolean agregar = true;
                for (AuGrupoSede pendiente : pendientes) {
                    if (actual.getCntPrestadorSede().getId().equals(pendiente.getCntPrestadorSede().getId())) {
                        nueva.add(pendiente);
                        agregar = false;
                        break;
                    }
                }
                if (agregar) {
                    nueva.add(actual);
                }
            }
            List<AuGrupoSede> nuevaPendientes = new ArrayList();
            for (AuGrupoSede pendiente : pendientes) {
                boolean agregar = true;
                for (AuGrupoSede actual : nueva) {
                    if (pendiente.getCntPrestadorSede().getId().equals(actual.getCntPrestadorSede().getId())) {
                        agregar = false;
                    }
                }
                if (agregar) {
                    nuevaPendientes.add(pendiente);
                }
            }
            nuevaPendientes.addAll(nueva);
            return nuevaPendientes;
        }
    }

    private List<AuGrupoDiagnostico> unificarListasDiagnostico(List<AuGrupoDiagnostico> pendientes, List<AuGrupoDiagnostico> actuales) {
        if (pendientes.isEmpty()) {
            return actuales;
        } else {
            List<AuGrupoDiagnostico> nueva = new ArrayList();
            for (AuGrupoDiagnostico actual : actuales) {
                boolean agregar = true;
                for (AuGrupoDiagnostico pendiente : pendientes) {
                    if (actual.getMaDiagnosticoId() == pendiente.getMaDiagnosticoId()) {
                        nueva.add(pendiente);
                        agregar = false;
                        break;
                    }
                }
                if (agregar) {
                    nueva.add(actual);
                }
            }
            List<AuGrupoDiagnostico> nuevaPendientes = new ArrayList();
            for (AuGrupoDiagnostico pendiente : pendientes) {
                boolean agregar = true;
                for (AuGrupoDiagnostico actual : nueva) {
                    if (pendiente.getMaDiagnosticoId() == actual.getMaDiagnosticoId()) {
                        agregar = false;
                    }
                }
                if (agregar) {
                    nuevaPendientes.add(pendiente);
                }
            }
            nuevaPendientes.addAll(nueva);
            return nuevaPendientes;
        }
    }

    private List<AuGrupoTecnologia> unificarListasTecnologia(List<AuGrupoTecnologia> pendientes, List<AuGrupoTecnologia> actuales) {
        if (pendientes.isEmpty()) {
            return actuales;
        } else {
            List<AuGrupoTecnologia> nueva = new ArrayList();
            for (AuGrupoTecnologia actual : actuales) {
                boolean agregar = true;
                for (AuGrupoTecnologia pendiente : pendientes) {
                    if (actual.getMaTecnologiaId().equals(pendiente.getMaTecnologiaId())
                            && actual.getTipoTecnologia() == pendiente.getTipoTecnologia()) {
                        nueva.add(pendiente);
                        agregar = false;
                        break;
                    }
                }
                if (agregar) {
                    nueva.add(actual);
                }
            }
            List<AuGrupoTecnologia> nuevaPendientes = new ArrayList();
            for (AuGrupoTecnologia pendiente : pendientes) {
                boolean agregar = true;
                for (AuGrupoTecnologia actual : nueva) {
                    if (pendiente.getMaTecnologiaId().equals(actual.getMaTecnologiaId())
                            && pendiente.getTipoTecnologia() == actual.getTipoTecnologia()) {
                        agregar = false;
                    }
                }
                if (agregar) {
                    nuevaPendientes.add(pendiente);
                }
            }
            nuevaPendientes.addAll(nueva);
            return nuevaPendientes;
        }
    }

    @Override
    public boolean validarSede(CntPrestadorSede sede, GrupoBean bean) {
        boolean validar;
        try {
            validar = getAuGrupoSedeRemoto().validarSede(sede.getId(), bean.getObjeto().getId());
            validar = !validar;
        } catch (Exception e) {
            validar = false;
        }
        return validar;
    }

    @Override
    public boolean validarTecnologia(int idTecnologia, int tipoTecnologia, GrupoBean bean) {
        boolean validar;
        try {
            validar = getAuGrupoTecnologiaRemoto().validarTecnologia(idTecnologia, tipoTecnologia, bean.getObjeto().getId());
            validar = !validar;
        } catch (Exception e) {
            validar = false;
        }
        return validar;
    }

    @Override
    public boolean validarDiagnostico(MaDiagnostico diagnostico, GrupoBean bean) {
        boolean validar;
        try {
            validar = getAuGrupoDiagnosticoRemoto().validarDiagnostico(diagnostico.getId(), bean.getObjeto().getId());
            validar = !validar;
        } catch (Exception e) {
            validar = false;
        }
        return validar;
    }

    public void reasignarItems(GrupoBean bean) {
        try {
            String mensaje = "";
            boolean reasigno = false;
            for (AuAnexo3Item item : bean.getListaSeleccionCasos()) {
                int idItem = item.getMaTecnologiaId();
//                int tipo = bean.getObjeto().getAuditor().getTipo();
                Maestro maeTipoAuditor = bean.getHashTipoAuditor().get(bean.getObjeto().getAuditor().getMaeTipoAuditorId());
                if (maeTipoAuditor.contieneAccion(MaestroAccion.AU_TIPO_AUDITOR_REASIGNA_GRUPO_TECNOLOGIA)) {//medico
                    item.setGnUsuarioId(bean.getObjeto().getAuditor().getUsuario());
                    bean.auditoriaModificar(item);
                    getAuAnexo3ItemRemoto().actualizarGrupo(item);
                    reasigno = true;
                } else {
//                if (!tipo.equals(AuGrupoUsuario.TIPO_MEDICO)) {
                    if (getAuGrupoTecnologiaRemoto().validarAuditor(idItem, maeTipoAuditor.getValor())) {
                        item.setGnUsuarioId(bean.getObjeto().getAuditor().getUsuario());
                        bean.auditoriaModificar(item);
                        getAuAnexo3ItemRemoto().actualizarGrupo(item);
                        reasigno = true;
                    } else {
                        if (mensaje.isEmpty()) {
                            mensaje += item.getMaTecnologiaId();
                        } else {
                            mensaje += " - " + item.getMaTecnologiaId();
                        }
                    }
                }
            }
            if (reasigno) {
                bean.addMensaje("Se realizo la reasignación con exito");
            } else {
                bean.addMensaje("No se reasignó ningún caso");
            }
            if (!mensaje.isEmpty()) {
                bean.addMensaje("Los siguientes casos no se reasignaron : " + mensaje + " debido a que el auditor no cuenta con los permisos.");
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo reasignando los ítems, favor contactar con el administrador");
        }
    }

    @Override
    public void listarGrupoAuditores(GrupoBean bean) {
        try {
            bean.getParamConsulta(8).setParametroConsulta1(bean.getObjeto().getId());
            bean.getParamConsulta(8).setCantidadRegistros(getAuGrupoUsuarioRemoto().consultarCantidadLista(bean.getParamConsulta(8)));
            bean.setListaGrupoAuditores(getAuGrupoUsuarioRemoto().consultarLista(bean.getParamConsulta(8)));
        } catch (Exception e) {
            bean.addError("Hubo un fallo listando los auditores, favor contactar al administrador");
        }
    }

    private void modificarSoloGrupo(GrupoBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjeto());
            getAuGrupoRemoto().actualizar(bean.getObjeto());
        } catch (Exception e) {
            bean.addError("Hubo un fallo al modificar el grupo, favor contactar al adminitrador");
        }
    }

    @Override
    public boolean validarGenerico(GrupoBean bean) {
        try {
            if (bean.getObjeto().isGenerico()) {
                return false;
            } else {
                return getAuGrupoRemoto().validarGrupoGenerico();
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo validando generico del grupo, favor contactar al administrador");
            return false;
        }
    }

    @Override
    public boolean validarTutela(GrupoBean bean) {
        try {
            if (bean.getObjeto().isTutela()) {
                return false;
            } else {
                return getAuGrupoRemoto().validarGrupoTutela();
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo validando tutela del grupo, favor contactar al adminitrador");
            return false;
        }
    }

    @Override
    public boolean validarPbs(GrupoBean bean) {
        try {
            if (bean.getObjeto().isPbs()) {
                return false;
            } else {
                return getAuGrupoRemoto().validarGrupoPbs();
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo validando pbs del grupo, favor contactar al adminitrador");
            return false;
        }
    }

    @Override
    public boolean validarPrograma(int idPrograma, GrupoBean bean) {
        boolean validar;
        try {
            validar = getAuGrupoProgramaRemoto().validarPrograma(idPrograma, bean.getObjeto().getId());
            validar = !validar;
        } catch (Exception e) {
            validar = false;
        }
        return validar;
    }

    @Override
    public void listarProgramas(GrupoBean bean) {
        try {
            bean.getParamConsulta(10).setCantidadRegistros(getPeProgramaRemoto().consultarCantidadLista(bean.getParamConsulta(10)));
            bean.setListaProgramas(getPeProgramaRemoto().consultarLista(bean.getParamConsulta(10)));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al listar programas, favor contactar con el administrador");
        }
    }

    @Override
    public void listarGrupoProgramas(GrupoBean bean) {
        try {
            if (bean.getObjeto().getId() != null) {
                boolean limpiar = bean.getListaProgramaModificar().isEmpty();
                List<AuGrupoPrograma> copia = bean.getListaProgramaModificar();
                bean.getParamConsulta(9).setParametroConsulta1(bean.getObjeto().getId());
                List<AuGrupoPrograma> registros = getAuGrupoProgramaRemoto().consultarLista(bean.getParamConsulta(9));
                int cantidadRegistros = registros.size();
                List<AuGrupoPrograma> nueva = unificarListasProgramas(bean.getListaProgramaModificar(), registros);
                cantidadRegistros = getAuGrupoProgramaRemoto().consultarCantidadLista(bean.getParamConsulta(9)) + nueva.size() - cantidadRegistros;
                bean.getParamConsulta(9).setCantidadRegistros(cantidadRegistros);
                bean.setListaGrupoProgramas(nueva);
                if (limpiar) {
                    bean.setListaProgramaModificar(new ArrayList());
                } else {
                    bean.setListaProgramaModificar(copia);
                }
            } else {
                bean.getParamConsulta(9).setCantidadRegistros(bean.getListaProgramaModificar().size());
                bean.setListaGrupoProgramas(bean.getListaProgramaModificar());
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo al listar los programas del grupo, favor contactar al administrador");
        }
    }

    private List<AuGrupoPrograma> unificarListasProgramas(List<AuGrupoPrograma> pendientes, List<AuGrupoPrograma> actuales) {
        if (pendientes.isEmpty()) {
            return actuales;
        } else {
            List<AuGrupoPrograma> nueva = new ArrayList();
            for (AuGrupoPrograma actual : actuales) {
                boolean agregar = true;
                for (AuGrupoPrograma pendiente : pendientes) {
                    if (actual.getPeProgramaId().getId().equals(pendiente.getPeProgramaId().getId())) {
                        nueva.add(pendiente);
                        agregar = false;
                        break;
                    }
                }
                if (agregar) {
                    nueva.add(actual);
                }
            }
            List<AuGrupoPrograma> nuevaPendientes = new ArrayList();
            for (AuGrupoPrograma pendiente : pendientes) {
                boolean agregar = true;
                for (AuGrupoPrograma actual : nueva) {
                    if (pendiente.getPeProgramaId().getId().equals(actual.getPeProgramaId().getId())) {
                        agregar = false;
                    }
                }
                if (agregar) {
                    nuevaPendientes.add(pendiente);
                }
            }
            nuevaPendientes.addAll(nueva);
            return nuevaPendientes;
        }
    }

    @Override
    public boolean validarTecnologiaTieneSeguimiento(GrupoBean bean) {
        try {
            return getAuGrupoTecnologiaRemoto().validarAplicaSeguimiento(
                    bean.getObjetoGrupoTecnologia().getMaTecnologiaId(),
                    bean.getObjetoGrupoTecnologia().getTipoTecnologia(),
                    bean.getObjetoGrupoTecnologia().getAuGrupo().getId()
            );
        } catch (Exception e) {
            bean.addError("Hubo un error validadando seguimiento existente");
        }
        return true;
    }

    @Override
    public void borrarPrograma(int id) {
        try {
            getAuGrupoProgramaRemoto().eliminar(id);
        } catch (Exception e) {
        }
    }

    @Override
    public void reordenar(int posicionMover, int posicionMovida, AuGrupo grupoMovido, AuGrupo grupoMover, GrupoBean bean) {
        try {
            int inicial = 0;
            int fin = 0;
            int orden = 0;
            if (posicionMover > posicionMovida) { //Arriba
                inicial = posicionMovida;
                fin = posicionMover - 1;
                orden = grupoMovido.getOrden() + 1;
                grupoMover.setOrden(grupoMovido.getOrden());
            } else { //Abajo
                inicial = posicionMover + 1;
                fin = posicionMovida;
                orden = grupoMover.getOrden();
                grupoMover.setOrden(grupoMovido.getOrden());
            }
            bean.setObjeto(grupoMover);
            modificarSoloGrupo(bean);
            bean.setListaGrupos(bean.getRegistros());
            calcularReorden(inicial, fin, orden, bean);
        } catch (Exception e) {
            bean.addError("Hubo un fallo reordenando, favor contactar con el administrador");
        }
    }

    public void calcularReorden(int inicio, int fin, int orden, GrupoBean bean) {
        while (inicio <= fin) {
            AuGrupo grupo = bean.getListaGrupos().get(inicio);
            grupo.setOrden(orden);
            orden++;
            inicio++;
            bean.setObjeto(grupo);
            modificarSoloGrupo(bean);
        }
    }

}
