/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.gestion.atencion.servicio;

import com.saviasaludeps.savia.dominio.administracion.GnSede;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadosPrograma;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatSedeConfiguracion;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatTaquillaServicio;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatTiquete;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatUsuario;
import com.saviasaludeps.savia.negocio.administracion.GnSedeHorarioRemoto;
import com.saviasaludeps.savia.negocio.administracion.GnSedeRemoto;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.administracion.UsuarioRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.especial.PeAfiliadosProgramaRemoto;
import com.saviasaludeps.savia.negocio.gestionAtencion.GatSedeConfiguracionRemoto;
import com.saviasaludeps.savia.negocio.gestionAtencion.GatTaquillaServicioRemoto;
import com.saviasaludeps.savia.negocio.gestionAtencion.GatTiqueteRemoto;
import com.saviasaludeps.savia.negocio.gestionAtencion.GatUsuarioRemoto;
import com.saviasaludeps.savia.web.gestion.atencion.bean.GatTurnoBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author sgiraldov
 */
public class GatTurnoServicioImpl extends AccionesBO implements GatTurnoServicioIface {

    private GatTiqueteRemoto getGatTiqueteRemoto() throws Exception {
        return (GatTiqueteRemoto) RemotoEJB.getEJBRemoto("GatTiqueteServicio", GatTiqueteRemoto.class.getName());
    }
    
    private GatTaquillaServicioRemoto getGatTaquillaRemoto() throws Exception {
        return (GatTaquillaServicioRemoto) RemotoEJB.getEJBRemoto("GatTaquillaServicioServicio", GatTaquillaServicioRemoto.class.getName());
    }

    private GnSedeRemoto getGnSedeRemoto() throws Exception {
        return (GnSedeRemoto) RemotoEJB.getEJBRemoto("GnSedeServicio", GnSedeRemoto.class.getName());
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }

    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        return (AfiliadoRemoto) RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
    }

    private GatUsuarioRemoto getGatUsuarioRemoto() throws Exception {
        return (GatUsuarioRemoto) RemotoEJB.getEJBRemoto("GatUsuarioServicio", GatUsuarioRemoto.class.getName());
    }

    private UsuarioRemoto getUsuarioRemoto() throws Exception {
        return (UsuarioRemoto) RemotoEJB.getEJBRemoto("UsuarioServicio", UsuarioRemoto.class.getName());
    }

    private PeAfiliadosProgramaRemoto getPeAfiliadosProgramaRemoto() throws Exception {
        return (PeAfiliadosProgramaRemoto) RemotoEJB.getEJBRemoto("PeAfiliadosProgramaServicio", PeAfiliadosProgramaRemoto.class.getName());
    }

    private GatSedeConfiguracionRemoto getGatSedeConfiguracionRemoto() throws Exception {
        return (GatSedeConfiguracionRemoto) RemotoEJB.getEJBRemoto("GatSedeConfiguracionServicio", GatSedeConfiguracionRemoto.class.getName());
    }

    private GnSedeHorarioRemoto getGnSedeHorarioRemoto() throws Exception {
        return (GnSedeHorarioRemoto) RemotoEJB.getEJBRemoto("GnSedeHorarioServicio", GnSedeHorarioRemoto.class.getName());
    }

    @Override
    public void Accion(GatTurnoBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            listar(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            actualizarUsuario(bean);
                            break;
                    }
                    break;
                case Url.ACCION_CREAR:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_CREAR:
                            crear(bean);
                            break;
                        case Url.ACCION_LISTAR:
                            buscarAfiliado(bean);
                            break;
                    }

                    break;
                case Url.ACCION_GUARDAR:
                    guardar(bean);
                    break;
            }
        }
    }

    @Override
    public void cargaInicial(GatTurnoBean bean) {
        try {
            if (bean.getConexion().getUsuario().getGnSedeTurno() != null) {
                if (bean.getObjetoSede().getId() != null) {
                    bean.setObjetoSede(getGnSedeRemoto().consultar(bean.getConexion().getUsuario().getGnSedeTurno()));
                    bean.setConfiguracion(getGatSedeConfiguracionRemoto().consultarPorIdSede(bean.getObjetoSede().getId()));
                    if (bean.getConfiguracion().getTurnero() == 0) {
                        bean.setObjetoSede(new GnSede());
                    }
                }
            }
            bean.setListaSedes(getGnSedeRemoto().listarSedesPorFuncionario(bean.getConexion().getUsuario().getId()));
            bean.setListaTipoDocumentos(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));
            bean.setHashTipoDocumentos(Util.convertToHash(bean.getListaTipoDocumentos()));
            /**
             * 2025-02-07 - rpalacic - Temporal para segmentar en las sedes actuales
             */
//            bean.setListaServicios(getMaestroRemoto().consultarPorTipo(MaestroTipo.GAT_TAQUILLA_TIPO_SERVICIO));
//            bean.setHashServicios(Util.convertToHash(bean.getListaServicios()));
            bean.setFechaMaxima(new Date());
        } catch (Exception e) {
            bean.addError("Hubo un fallo en la carga inicial, favor contactar al adminitrador");
        }
    }

    private void crear(GatTurnoBean bean) {
        try {
            if (getGnSedeHorarioRemoto().estaEnHorario(bean.getObjetoSede().getId())) {
                bean.setObjeto(new GatTiquete());
                bean.getObjeto().setGnSede(bean.getObjetoSede());
                bean.getObjeto().setGatUsuario(new GatUsuario());
                bean.getObjeto().setAfiliado(true);
            } else {
                bean.addError("No se encuentra en un horario disponible para generacion de turnos");
            }

        } catch (Exception e) {
            bean.addError("Hubo un error al crear, favor contactar al administrador");
        }
    }

    private void guardar(GatTurnoBean bean) {
        try {
            if (bean.getConfiguracion().getTurnero() == 1) {
                Maestro tipoDocumento = bean.getHashTipoDocumentos().get(bean.getObjeto().getGatUsuario().getMaeTipoDocumentoId());
                bean.getObjeto().getGatUsuario().setMaeTipoDocumentoCodigo(tipoDocumento.getValor());
                bean.getObjeto().getGatUsuario().setMaeTipoDocumentoValor(tipoDocumento.getNombre());
                bean.auditoriaGuardar(bean.getObjeto().getGatUsuario());
                /**
                 * 2025-02-07 - rpalacic - Deshabilitar prioridad por edad
                 */
//                if (bean.getObjeto().getGatUsuarioId().getAsegAfiliadoId() == null
//                        && bean.getObjeto().getGatUsuarioId().getFechaNacimiento() != null) {
//                    Calendar actual = Calendar.getInstance();
//                    int anoActual = actual.get(Calendar.YEAR);
//                    Calendar nacimiento = Calendar.getInstance();
//                    nacimiento.setTime(bean.getObjeto().getGatUsuarioId().getFechaNacimiento());
//                    int anoNacimiento = nacimiento.get(Calendar.YEAR);
//                    int diferencia = anoActual - anoNacimiento;
//                    if (actual.get(Calendar.MONTH) > nacimiento.get(Calendar.MONTH)
//                            || (actual.get(Calendar.MONTH) == nacimiento.get(Calendar.MONTH)
//                            && actual.get(Calendar.DATE) > nacimiento.get(Calendar.DATE))) {
//                        diferencia--;
//                    }
//                    if (diferencia >= 60 || diferencia <= 5) {
//                        bean.getObjeto().setPrioritario(true);
//                    }
//                }
                bean.getObjeto().getGatUsuario().setId(getGatUsuarioRemoto().insertar(bean.getObjeto().getGatUsuario()));
            }
            Maestro tipoServicio = bean.getHashServicios().get(bean.getObjeto().getMaeTipoServicio());
            bean.getObjeto().setMaeTipoServicioCodigo(tipoServicio.getValor());
            bean.getObjeto().setMaeTipoServicioValor(tipoServicio.getNombre());
            bean.auditoriaGuardar(bean.getObjeto());
            int anterior = getGatTiqueteRemoto().consultarTotalHoy(bean.getObjetoSede().getId(), bean.getObjeto().getMaeTipoServicio()) + 1;
            bean.getObjeto().setNumero(bean.getObjeto().getMaeTipoServicioCodigo() + "-" + anterior);
            getGatTiqueteRemoto().insertar(bean.getObjeto());
            bean.addMensaje("Turno creado correctamente.");
        } catch (Exception e) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }

    private void listar(GatTurnoBean bean) {
        try {
            if (bean.getObjetoSede() != null && bean.getObjetoSede().getId() != null) {
                bean.getParamConsulta().setParametroConsulta1(bean.getObjetoSede().getId());
                bean.getParamConsulta().setParametroConsulta2(new Date());
                bean.getParamConsulta().setCantidadRegistros(getGatTiqueteRemoto().consultarCantidadLista(bean.getParamConsulta()));
                bean.setRegistros(getGatTiqueteRemoto().consultarLista(bean.getParamConsulta()));
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo al listar, favor contactar al administrador");
        }
    }

    private void actualizarUsuario(GatTurnoBean bean) {
        try {
            bean.setConfiguracion(getGatSedeConfiguracionRemoto().consultarPorIdSede(bean.getObjetoSede().getId()));
            if (bean.getConfiguracion().getTurnero() > 0) {
                bean.setObjetoSede(getGnSedeRemoto().consultar(bean.getObjetoSede().getId()));
                bean.getConexion().getUsuario().setGnSedeTurno(bean.getObjetoSede().getId());
                bean.auditoriaModificar(bean.getConexion().getUsuario());
                getUsuarioRemoto().actualizarSedeTurno(bean.getConexion().getUsuario());
                /**
                 * 2025-02-07 - rpalacic - Temporal para segmentar en las sedes actuales
                 */
                List<Maestro> listaMaestros = getMaestroRemoto().consultarPorTipo(MaestroTipo.GAT_TAQUILLA_TIPO_SERVICIO);
                List<GatTaquillaServicio> listaTaquillaServicios = getGatTaquillaRemoto().listarPorIdSede(bean.getObjetoSede().getId());
                List<Maestro> listaServicios = new ArrayList();
                for (Maestro maestro : listaMaestros) {
                    for (GatTaquillaServicio servicio : listaTaquillaServicios) {
                        if (servicio.getMaeTipoServicioCodigo().equals(maestro.getValor())) {
                            listaServicios.add(maestro);
                            break;
                        }
                    }
                }
                bean.setListaServicios(listaServicios);
                bean.setHashServicios(Util.convertToHash(listaServicios));
                //Fin del ajuste
            } else {
                bean.setObjetoSede(new GnSede());
                bean.addError("La sede seleccionada no tiene configurado la opción de turno.");
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo al actualizar la sede, favor contactar al administrador");
        }
    }

    private boolean validarPrioritario(AsegAfiliado afiliado) {
        try {
            if (afiliado.isDiscapacidad()) {
                return true;
            }
            Calendar actual = Calendar.getInstance();
            int anoActual = actual.get(Calendar.YEAR);
            Calendar nacimiento = Calendar.getInstance();
            nacimiento.setTime(afiliado.getFechaNacimiento());
            int anoNacimiento = nacimiento.get(Calendar.YEAR);
            int diferencia = anoActual - anoNacimiento;
            if (actual.get(Calendar.MONTH) > nacimiento.get(Calendar.MONTH)
                    || (actual.get(Calendar.MONTH) == nacimiento.get(Calendar.MONTH)
                    && actual.get(Calendar.DATE) > nacimiento.get(Calendar.DATE))) {
                diferencia--;
            }
            if (diferencia >= 60 || diferencia <= 5) {
                return true;
            } else {
                List<PeAfiliadosPrograma> programas = getPeAfiliadosProgramaRemoto().listarPorAfiliadoYCodigoPrograma(afiliado.getId(), "SP11");
                return programas.size() > 0;
            }
        } catch (Exception e) {
            return false;
        }
    }

    private void buscarAfiliado(GatTurnoBean bean) {
        try {
            AsegAfiliado afiliado = getAfiliadoRemoto().consultar(bean.getObjeto().getGatUsuario().getMaeTipoDocumentoId(), bean.getObjeto().getGatUsuario().getNumeroDocumento());
            if (afiliado != null) {
                bean.getObjeto().getGatUsuario().setAsegAfiliado(afiliado);
                bean.getObjeto().getGatUsuario().setApellidos(afiliado.getApellidos());
                bean.getObjeto().getGatUsuario().setNombres(afiliado.getNombres());
                Maestro tipo = bean.getHashTipoDocumentos().get(bean.getObjeto().getGatUsuario().getMaeTipoDocumentoId());
                bean.getObjeto().getGatUsuario().setMaeTipoDocumentoCodigo(tipo.getValor());
                bean.getObjeto().getGatUsuario().setMaeTipoDocumentoValor(tipo.getNombre());
                bean.getObjeto().getGatUsuario().setFechaNacimiento(afiliado.getFechaNacimiento());
                /**
                 * 2025-02-07 - rpalacic - Deshabilitar prioridad por condiciones
                 * de afiliado
                 */
//                bean.getObjeto().setPrioritario(validarPrioritario(afiliado));
                bean.getObjeto().setPrioritario(false);
            } else {
                bean.getObjeto().getGatUsuario().setApellidos(null);
                bean.getObjeto().getGatUsuario().setNombres(null);
                bean.getObjeto().getGatUsuario().setFechaNacimiento(null);
                bean.getObjeto().setAfiliado(false);
            }
        } catch (Exception e) {
            bean.addError("Hubo un error al buscar, favor contactar al administrador");
        }
    }

    private void imprimirTicket(GatTiquete objeto) throws Exception {
        /*InputStream is = getClass().getResourceAsStream("/reportes/ticket.jasper");
        Map parameters = new HashMap();
        parameters.put(JRParameter.REPORT_LOCALE, new Locale("es", "CO"));
        parameters.put(GatContantes.TURNO, objeto.getNumero());
        parameters.put(GatContantes.SERVICIO, objeto.getMaeTipoServicioValor());  
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(is);
        // Genera el JasperPrint (el informe lleno de datos)
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
        // Imprime el informe automáticamente
        JasperPrintManager.printReport(jasperPrint, false);*/
    }

}
