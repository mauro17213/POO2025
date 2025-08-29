/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.aseguramiento.servicio;

import com.saviasaludeps.savia.dominio.administracion.GnUbicacionBarrio;
import com.saviasaludeps.savia.web.aseguramiento.utilidades.AfiliadoValidacion;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroAccion;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAdjunto;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoCertificado;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoContacto;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoHistorico;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegRadicadoNovedad;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegTabulacionEncuesta;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegTraslado;
import com.saviasaludeps.savia.dominio.aseguramiento.CertificadoAfiliacion;
import com.saviasaludeps.savia.dominio.aseguramiento.CertificadoContactoAfiliado;
import com.saviasaludeps.savia.dominio.aseguramiento.CsContribucionSolidaria;
import com.saviasaludeps.savia.dominio.aseguramiento.FormularioAfiliadoRes1823;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.aseguramiento.ReporteAfiliacion;
import com.saviasaludeps.savia.dominio.aseguramiento.ReporteEncuesta016;
import com.saviasaludeps.savia.negocio.administracion.GnUbicacionBarrioRemoto;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AdjuntoRemoto;
import com.saviasaludeps.savia.web.aseguramiento.bean.AfiliadosBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.EncuestaRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.NovedadAfiliadoRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.PortabilidadRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.TrasladoRemoto;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.IOUtils;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoCertificadoRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoContactoRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoHistoricoRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.CsContribucionSolidariaRemoto;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.negocio.especial.PeAfiliadosProgramaRemoto;
import com.saviasaludeps.savia.web.aseguramiento.utilidades.AfiliadoParametro;
import com.saviasaludeps.savia.web.singleton.MaestroSingle;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;

/**
 *
 * @author José Pérez Hernández
 */
public class AfiliadosServicioImpl extends AccionesBO implements AfiliadosServicioIface {

    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
        return (AfiliadoRemoto) object;
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
        return (MaestroRemoto) object;
    }

    private CntPrestadorRemoto getPrestadoresRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }

    private PortabilidadRemoto getPortabilidadRemoto() throws Exception {
        return (PortabilidadRemoto) RemotoEJB.getEJBRemoto("PortabilidadServicio", PortabilidadRemoto.class.getName());
    }

    private TrasladoRemoto getTrasladoRemoto() throws Exception {
        return (TrasladoRemoto) RemotoEJB.getEJBRemoto("TrasladoServicio", TrasladoRemoto.class.getName());
    }

    private EncuestaRemoto getEncuestaRemoto() throws Exception {
        return (EncuestaRemoto) RemotoEJB.getEJBRemoto("EncuestaServicio", EncuestaRemoto.class.getName());
    }

    private NovedadAfiliadoRemoto getNovedadAfiliadoRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("NovedadAfiliadoServicio", NovedadAfiliadoRemoto.class.getName());
        return (NovedadAfiliadoRemoto) object;
    }

    private AdjuntoRemoto getAdjuntoRemoto() throws Exception {
        return (AdjuntoRemoto) RemotoEJB.getEJBRemoto("AdjuntoServicio", AdjuntoRemoto.class.getName());
    }

    private AfiliadoHistoricoRemoto getAfiliadoHistoricoRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("AfiliadoHistoricoServicio", AfiliadoHistoricoRemoto.class.getName());
        return (AfiliadoHistoricoRemoto) object;
    }

    private AfiliadoCertificadoRemoto getAfiliadoCertificadoRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("AfiliadoCertificadoServicio", AfiliadoCertificadoRemoto.class.getName());
        return (AfiliadoCertificadoRemoto) object;
    }

    private AfiliadoContactoRemoto getAfiliadoContactoRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("AfiliadoContactoServicio", AfiliadoContactoRemoto.class.getName());
        return (AfiliadoContactoRemoto) object;
    }

    private PeAfiliadosProgramaRemoto getPeAfiliadosProgramaRemoto() throws Exception {
        return (PeAfiliadosProgramaRemoto) RemotoEJB.getEJBRemoto("PeAfiliadosProgramaServicio", PeAfiliadosProgramaRemoto.class.getName());
    }

    private CsContribucionSolidariaRemoto getContribucionSolidariaRemoto() throws Exception {
        return (CsContribucionSolidariaRemoto) RemotoEJB.getEJBRemoto("CsContribucionSolidariaServicio", CsContribucionSolidariaRemoto.class.getName());
    }
    
    private GnUbicacionBarrioRemoto getGnUbicacionBarrioRemoto() throws Exception {
        return (GnUbicacionBarrioRemoto) RemotoEJB.getEJBRemoto("GnUbicacionBarrioServicio", GnUbicacionBarrioRemoto.class.getName());
    }

    @Override
    public void Accion(AfiliadosBean bean) {
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
                    verHistorialNovedades(bean);
                    break;
                case Url.ACCION_ADICIONAL_2:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            listarAdjuntos(bean);
                            break;
                        case Url.ACCION_VER:
                            verAdjunto(bean);
                            break;
                        case Url.ACCION_CREAR:
                            crearAdjunto(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarAdjunto(bean);
                            break;
                        case Url.ACCION_BORRAR:

                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_3:
                    reporte016(bean);
                    break;
                case Url.ACCION_ADICIONAL_4:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_ADICIONAL_1:
                            reporteAfiliacion(bean);
                        break;
                        case Url.ACCION_ADICIONAL_2:
                            generarFormularioAfiliacion(bean);
                        break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_6:
                    generarCertificadoAfiliacion(bean);
                    break;
                case Url.ACCION_ADICIONAL_7:
                    desmarcarAfiliadoDuplicado(bean);
                    break;
                case Url.ACCION_ADICIONAL_8:
                    generarCertificadoContactoAfiliado(bean);
                    break;
                default:
                    break;
            }
            cargas(bean);
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    private void listar(AfiliadosBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getAfiliadoRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getAfiliadoRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(AfiliadosBean bean) {
        try {
            bean.setObjeto(getAfiliadoRemoto().consultar(bean.getObjeto().getId()));
            //obtenemos la información adicional a cargar para el afiliado
            bean.setListaGrupoFamiliarAfiliado(getAfiliadoRemoto().consultarGrupoFamiliar(bean.getObjeto().getMaeTipoDocumento(), bean.getObjeto().getNumeroDocumento()));
            bean.setListaPortabilidadAfiliado(getPortabilidadRemoto().consultarPorAfiliado(bean.getObjeto().getId()));
            //2025-05-20 jyperez nuevo panel programa afiliado
            bean.setListaProgramaAfiliado(getPeAfiliadosProgramaRemoto().consultarAfiliados(bean.getObjeto().getId()));
            //2021-05-27 jyperez cargamos la lista de contactos
            bean.setListaContactos(bean.getObjeto().getListaAsegAfiliadoContacto());
            //2021-11-08 jyperez en caso de ser beneficiario, se carga la información del Cabeza de Familia
            consultarAfiliadoCabezaFamiliaVer(bean);
            //consultar información objeto traslado
            bean.setObjetoTraslado(getTrasladoRemoto().consultarPorAfiliado(bean.getObjeto().getId()));
            if (bean.getObjetoTraslado().getId() != null) {
                //bean.setTrasladoExistente(true);
                //2020-08-19 jyperez se adiciona la carga del objeto maestro de eps anterior
                if (bean.getObjetoTraslado().getMaeEpsOrigenId() != null) {
                    bean.getObjetoTraslado().setMaestroEps(bean.getListadoEpsRecursiva().get(bean.getObjetoTraslado().getMaeEpsOrigenId()));
                }
            }
            //consultar pregunta encuesta listaEncuestaAfiliado
            bean.setListaEncuestaAfiliado(getEncuestaRemoto().consultarPorAfiliado(bean.getObjeto().getId()));
            //2022-07-21 jyperez cargamos el valor de la contribución solidaria
            consultarValorContribucionSolidaria(bean);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void verHistorialNovedades(AfiliadosBean bean) {
        try {

            bean.getParamConsultaHistorial().setCantidadRegistros(getNovedadAfiliadoRemoto().consultarCantidadLista(bean.getParamConsultaHistorial()));
            bean.setListaHistorialNovedad(getNovedadAfiliadoRemoto().consultarLista(bean.getParamConsultaHistorial()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(AfiliadosBean bean) {
        try {
            bean.setObjeto(new AsegAfiliado());
            bean.getObjeto().setPrimariaPrestadorSede(new CntPrestadorSede());
            bean.setObjetoTraslado(new AsegTraslado());
            //2021-05-27 jyperez adicionamos lista Contactos
            bean.setListaContactos(new ArrayList<>());
            //2021-11-12 adicionamos objeto pais de nacimiento
            bean.getObjeto().setNacimientoUbicacion(new Ubicacion());
            //2021-12-15 adicionamos objeto pais de nacionalidad
            bean.getObjeto().setNacionalidadUbicacion(new Ubicacion());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(AfiliadosBean bean) {
        try {
            bean.setObjeto(getAfiliadoRemoto().consultar(bean.getObjeto().getId()));
            // consultal la ips primaria y cargarla. Asi con todos los objetos que necesite editar
            consultarSedesMunicipio(bean);
            //actualizamos la funcionalidad que cargará los estados dependiendo del estado y el régimen
            consultarMaestroEstadoPorEstadoYRegimen(bean);
            // 2020-07-14 jyperez se adiciona la inicialización del objeto traslado, en caso de que exista en la BD
            bean.setObjetoTraslado(getTrasladoRemoto().consultarPorAfiliado(bean.getObjeto().getId()));
            if (bean.getObjetoTraslado().getId() != null) {
                bean.setTrasladoExistente(true);
                //2020-08-19 jyperez se adiciona la carga del objeto maestro de eps anterior
                if (bean.getObjetoTraslado().getMaeEpsOrigenId() != null) {
                    bean.getObjetoTraslado().setMaestroEps(bean.getListadoEpsRecursiva().get(bean.getObjetoTraslado().getMaeEpsOrigenId()));
                }
            } else {
                bean.setTrasladoExistente(false);
            }
            //2020-08-18 jyperez INC 274 adicionar cmapos nombres CF y Apellidos CF
            // hacemos llamado a la funcion de consulta
            if (bean.getObjeto().getTipoBeneficiario().equals(AfiliadoParametro.TIPO_AFILIADO_BENEFICIARIO)) {
                consultarAfiliadoCabezaFamiliaEditar(bean);
            }
            //2021-05-27 jyperez adicionamos lista Contactos
            bean.setListaContactos(bean.getObjeto().getListaAsegAfiliadoContacto());
            bean.setListaContactosBorrar(new ArrayList<>());
            //2021-11-11 cargamos información de portabilidades, grupo familiar y encuesta listaEncuestaAfiliado
            bean.setListaEncuestaAfiliado(getEncuestaRemoto().consultarPorAfiliado(bean.getObjeto().getId()));
            bean.setListaGrupoFamiliarAfiliado(getAfiliadoRemoto().consultarGrupoFamiliar(bean.getObjeto().getMaeTipoDocumento(), bean.getObjeto().getNumeroDocumento()));
            bean.setListaPortabilidadAfiliado(getPortabilidadRemoto().consultarPorAfiliado(bean.getObjeto().getId()));
            //2025-05-20 jyperez nuevo panel programa afiliado
            bean.setListaProgramaAfiliado(getPeAfiliadosProgramaRemoto().consultarAfiliados(bean.getObjeto().getId()));
            //2021-11-12 jyperez obtenemos el id del pais de nacimiento para el manejo del campo en pantalla.
            if (bean.getObjeto().getNacimientoUbicacion() != null) {
                bean.getObjeto().setPaisNacimiento(bean.getObjeto().getNacimientoUbicacion().getId());
            }
            //2021-12-15 jyperez obtenemos el id del pais de nacionalidad para el manejo del campo en pantalla.
            if (bean.getObjeto().getNacionalidadUbicacion() != null) {
                bean.getObjeto().setPaisNacionalidad(bean.getObjeto().getNacionalidadUbicacion().getId());
            }
            //2023-06- jyperez obtenemos el id del pais de nacionalidad para el manejo del campo en pantalla.
            if (bean.getObjeto().getGnUbicacionesLugarNacimientoId() != null) {
                bean.getObjeto().setLugarNacimiento(bean.getObjeto().getGnUbicacionesLugarNacimientoId().getId());
            }
            //2022-07-21 jyperez cargamos el valor de la contribución solidaria
            consultarValorContribucionSolidaria(bean);
            //2022-09-23 jyperez validamos si existe una portabilidad Vigente
            if (bean.getObjeto().getMaeRegimenCodigo().equals(AfiliadoParametro.REGIMEN_SUBSIDIADO)
                    || bean.getObjeto().getRegimen().equals(AfiliadoParametro.REGIMEN_SUBSIDIADO_ANTIGUO)) {
                bean.setPortabilidadVigente(getPortabilidadRemoto().consultarPortabilidadAfiliadoVigente(bean.getObjeto().getId()));
                if (bean.getPortabilidadVigente() != null) {
                    bean.setAfiliadoPortabilidadVigente(true);
                } else {
                    bean.setAfiliadoPortabilidadVigente(false);
                }
            } else {
                bean.setAfiliadoPortabilidadVigente(false);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(AfiliadosBean bean) {
        AsegAfiliadoHistorico afiliadoHistorico;
        List<AsegAfiliadoContacto> listaContactosAuditar = new ArrayList<>();
        Ubicacion ubi = null;
        char accionAdicional = 0;// Se inicializa en 0 que es un valor que no esta entre los que se definen para acciones adicionales
        try {
            // sección validaciones
            if (!bean.isAfiliadoExistente()) {
                //Proceso de validación
                AfiliadoValidacion validacion = new AfiliadoValidacion();
                validacion.setHashCausaNovedad(bean.getHashCausaNovedad());
                //2022-02-01 jyperez se cambia el hash de validación debido a que no estan todos los valores usados en la validación
                validacion.setHashEstadosAfiliacion(bean.getHashEstadosAfiliacionCompleto());
                validacion.setHashGrupoPoblacional(bean.getHashGrupoPoblacional());
                validacion.setHashOrigenAfiliado(bean.getHashOrigenAfiliado());
                validacion.setHashTiposDocumento(bean.getHashTiposDocumento());
                //2021-03-16  jyperez INC 0000720 Vamos a quitarle los espacios al final y al inicio a los nombres y apellidos del afiliado
                bean.getObjeto().setPrimerApellido(bean.getObjeto().getPrimerApellido().trim());
                bean.getObjeto().setSegundoApellido(bean.getObjeto().getSegundoApellido().trim());
                bean.getObjeto().setPrimerNombre(bean.getObjeto().getPrimerNombre().trim());
                bean.getObjeto().setSegundoNombre(bean.getObjeto().getSegundoNombre().trim());
                if (bean.getObjeto().isRegistroBdua()) {
                    //2021-03-16  jyperez INC 0000720 Vamos a quitarle los espacios al final y al inicio a los nombres y apellidos del afiliado
                    bean.getObjetoTraslado().setPrimerApellidoBdua(bean.getObjetoTraslado().getPrimerApellidoBdua().trim());
                    bean.getObjetoTraslado().setSegundoApellidoBdua(bean.getObjetoTraslado().getSegundoApellidoBdua().trim());
                    bean.getObjetoTraslado().setPrimerNombreBdua(bean.getObjetoTraslado().getPrimerNombreBdua().trim());
                    bean.getObjetoTraslado().setSegundoNombreBdua(bean.getObjetoTraslado().getSegundoNombreBdua().trim());
                }
                //2020-06-08 Se adiciona almacenamiento de objeto de traslado al objeto de afiliado.
                bean.getObjeto().setAsegTraslado(bean.getObjetoTraslado());
                //2021-05-20 jyperez obtenemos los valores del maestro nivel Sisben
                try {
                    Maestro mae = bean.getHashNivelSisbenNuevo().get(bean.getObjeto().getMaeNivelSisbenId());
                    bean.getObjeto().setMaeNivelSisbenCodigo(mae.getValor());
                    bean.getObjeto().setMaeNivelSisbenValor(mae.getNombre());
                    //actualizamos campo anterior, teniendo en cuenta que es obligatorio
                    bean.getObjeto().setNivelSisben(mae.getValor());
                } catch (Exception ex) {
                    bean.addError("Ocurrió un error obteniendo los valores del Maestro de Nivel Sisben. Error: " + ex.getMessage());
                }
                //2023-01-12 jyperez cargamos los objetos de ubicacion - pais nacimiento y nacionalidad
                try {
                    //2023-01-12 jyperez obtenemos el objeto de ubicación necesario a partir del id
                    ubi = bean.getHashPaises().get(bean.getObjeto().getPaisNacimiento());
                    bean.getObjeto().setNacimientoUbicacion(ubi);
                } catch (Exception ex) {
                    bean.addError("Ocurrió un error obteniendo los valores de Pais de Nacimiento. Error: " + ex.getMessage());
                }
                //2023-06-20 jyperez cargamos el lugar de nacimiento
                try {
                    //2023-01-12 jyperez obtenemos el objeto de ubicación necesario a partir del id
                    ubi = bean.getHashLugarNacimiento().get(bean.getObjeto().getLugarNacimiento());
                    bean.getObjeto().setGnUbicacionesLugarNacimientoId(ubi);
                } catch (Exception ex) {
                    bean.addError("Ocurrió un error obteniendo los valores de Lugar de Nacimiento. Error: " + ex.getMessage());
                }
                //2021-12-15 jyperez cargamos el id de la ubicación
                try {
                    //2023-01-12 jyperez obtenemos el objeto de ubicación necesario a partir del id
                    ubi = bean.getHashPaises().get(bean.getObjeto().getPaisNacionalidad());
                    bean.getObjeto().setNacionalidadUbicacion(ubi);
                } catch (Exception ex) {
                    bean.addError("Ocurrió un error obteniendo los valores de Pais de Nacionalidad. Error: " + ex.getMessage());
                }
                //2021-10-29 jyperez incluimos la carga de los valores de Maestro
                //tipoDocumento
                try {
                    Maestro mae = bean.getHashTiposDocumento().get(bean.getObjeto().getMaeTipoDocumento());
                    bean.getObjeto().setMaeTipoDocumentoCodigo(mae.getValor());
                    bean.getObjeto().setMaeTipoDocumentoValor(mae.getNombre());
                } catch (Exception ex) {
                    bean.addError("Ocurrió un error obteniendo los valores del Maestro de Tipo Documento. Error: " + ex.getMessage());
                }
                //genero
                try {
                    Maestro mae = bean.getHashTiposGenero().get(bean.getObjeto().getMaeGeneroId());
                    bean.getObjeto().setMaeGeneroCodigo(mae.getValor());
                    bean.getObjeto().setMaeGeneroValor(mae.getNombre());
                    //actualizamos campo anterior, teniendo en cuenta que es obligatorio -- Se envia siempre el id
                    //2024-05-08 jyperez se corrije valor de código al campo genero que ya no se debe usar.
                    bean.getObjeto().setGenero(mae.getValor());
                } catch (Exception ex) {
                    bean.addError("Ocurrió un error obteniendo los valores del Maestro de Género. Error: " + ex.getMessage());
                }
                //genero identificacion
                try {
                    Maestro mae = bean.getHashTiposGeneroIdentificacion().get(bean.getObjeto().getMaeGeneroIdentificacionId());
                    bean.getObjeto().setMaeGeneroIdentificacionCodigo(mae.getValor());
                    bean.getObjeto().setMaeGeneroIdentificacionValor(mae.getNombre());
                } catch (Exception ex) {
                    bean.addError("Ocurrió un error obteniendo los valores del Maestro de Género de Identificación. Error: " + ex.getMessage());
                }
                //estadoCivil
                try {
                    Maestro mae = bean.getHashEstadoCivil().get(bean.getObjeto().getMaeEstadoCivilId());
                    bean.getObjeto().setMaeEstadoCivilCodigo(mae.getValor());
                    bean.getObjeto().setMaeEstadoCivilValor(mae.getNombre());
                    //actualizamos campo anterior, teniendo en cuenta que es obligatorio
                    bean.getObjeto().setEstadoCivil(mae.getValor());
                } catch (Exception ex) {
                    bean.addError("Ocurrió un error obteniendo los valores del Maestro de Estado Civil. Error: " + ex.getMessage());
                }
                //origenAfiliado
                try {
                    Maestro mae = bean.getHashOrigenAfiliado().get(bean.getObjeto().getMaeOrigenAfiliado());
                    bean.getObjeto().setMaeOrigenAfiliadoCodigo(mae.getValor());
                    bean.getObjeto().setMaeOrigenAfiliadoValor(mae.getNombre());
                } catch (Exception ex) {
                    bean.addError("Ocurrió un error obteniendo los valores del Maestro de Origen Afiliado. Error: " + ex.getMessage());
                }
                //tipoAfiliado
                try {
                    Maestro mae = bean.getHashTipoAfiliadoCompleto().get(bean.getObjeto().getMaeTipoAfiliadoId());
                    bean.getObjeto().setMaeTipoAfiliadoCodigo(mae.getValor());
                    bean.getObjeto().setMaeTipoAfiliadoValor(mae.getNombre());
                    //actualizamos campo anterior, teniendo en cuenta que es obligatorio
                    bean.getObjeto().setTipoBeneficiario(mae.getValor());
                } catch (Exception ex) {
                    bean.addError("Ocurrió un error obteniendo los valores del Maestro de Tipo Afiliado. Error: " + ex.getMessage());
                }
                //parentesco NR
                try {
                    if (bean.getObjeto().getMaeParentescoCotizanteId() != null && bean.getObjeto().getMaeParentescoCotizanteId() != 0) {
                        Maestro mae = bean.getHashParentescoCotizante().get(bean.getObjeto().getMaeParentescoCotizanteId());
                        bean.getObjeto().setMaeParentescoCotizanteCodigo(mae.getValor());
                        bean.getObjeto().setMaeParentescoCotizanteValor(mae.getNombre());
                        //actualizamos campo anterior, teniendo en cuenta que es obligatorio
                        bean.getObjeto().setParentescoCotizante(mae.getValor());
                    } else if (bean.getObjeto().getMaeParentescoCotizanteId() != null) {
                        bean.getObjeto().setMaeParentescoCotizanteId(null);
                    }
                } catch (Exception ex) {
                    bean.addError("Ocurrió un error obteniendo los valores del Maestro de Parentesco. Error: " + ex.getMessage());
                }
                //tipoDocumentoCF NR
                try {
                    if (bean.getObjeto().getMaeTipoDocumentoCf() != null && bean.getObjeto().getMaeTipoDocumentoCf() != 0) {
                        Maestro mae = bean.getHashTiposDocumento().get(bean.getObjeto().getMaeTipoDocumentoCf());
                        bean.getObjeto().setMaeTipoDocumentoCfCodigo(mae.getValor());
                        bean.getObjeto().setMaeTipoDocumentoCfValor(mae.getNombre());
                    }
                } catch (Exception ex) {
                    bean.addError("Ocurrió un error obteniendo los valores del Maestro de Tipo Documento CF. Error: " + ex.getMessage());
                }
                //zona
                try {
                    Maestro mae = bean.getHashZona().get(bean.getObjeto().getMaeZonaId());
                    bean.getObjeto().setMaeZonaCodigo(mae.getValor());
                    bean.getObjeto().setMaeZonaValor(mae.getNombre());
                    //actualizamos campo anterior, teniendo en cuenta que es obligatorio
                    bean.getObjeto().setZona(mae.getValor());
                } catch (Exception ex) {
                    bean.addError("Ocurrió un error obteniendo los valores del Maestro de Zona. Error: " + ex.getMessage());
                }
                //grupo poblacional
                try {
                    Maestro mae = bean.getHashGrupoPoblacional().get(bean.getObjeto().getMaeGrupoPoblacional());
                    bean.getObjeto().setMaeGrupoPoblacionalCodigo(mae.getValor());
                    bean.getObjeto().setMaeGrupoPoblacionalValor(mae.getNombre());
                } catch (Exception ex) {
                    bean.addError("Ocurrió un error obteniendo los valores del Maestro de Grupo Poblacional. Error: " + ex.getMessage());
                }
                //etnia
                try {
                    Maestro mae = bean.getHashEtnia().get(bean.getObjeto().getMaeEtniaId());
                    bean.getObjeto().setMaeEtniaCodigo(mae.getValor());
                    bean.getObjeto().setMaeEtniaValor(mae.getNombre());
                    //actualizamos campo anterior, teniendo en cuenta que es obligatorio
                    bean.getObjeto().setEtnia(mae.getValor());
                } catch (Exception ex) {
                    bean.addError("Ocurrió un error obteniendo los valores del Maestro de Etnia. Error: " + ex.getMessage());
                }
                //grupoSisben NR
                try {
                    if (bean.getObjeto().getMaeGrupoSisbenId() != null) {
                        Maestro mae = bean.getHashGrupoSisben().get(bean.getObjeto().getMaeGrupoSisbenId());
                        bean.getObjeto().setMaeGrupoSisbenCodigo(mae.getValor());
                        bean.getObjeto().setMaeGrupoSisbenValor(mae.getNombre());
                    }
                } catch (Exception ex) {
                    bean.addError("Ocurrió un error obteniendo los valores del Maestro de Grupo Sisben. Error: " + ex.getMessage());
                }
                //subGrupoSisben NR
                try {
                    if (bean.getObjeto().getMaeSubGrupoSisbenId() != null) {
                        Maestro mae = bean.getHashSubGrupoSisben().get(bean.getObjeto().getMaeSubGrupoSisbenId());
                        bean.getObjeto().setMaeSubGrupoSisbenCodigo(mae.getValor());
                        bean.getObjeto().setMaeSubGrupoSisbenValor(mae.getNombre());
                    }
                } catch (Exception ex) {
                    bean.addError("Ocurrió un error obteniendo los valores del Maestro de SubGrupo Sisben. Error: " + ex.getMessage());
                }
                //tipo discapacidad NR
                try {
                    if (bean.getObjeto().getMaeTipoDiscapacidadId() != null) {
                        Maestro mae = bean.getHashTipoDiscapacidad().get(bean.getObjeto().getMaeTipoDiscapacidadId());
                        bean.getObjeto().setMaeTipoDiscapacidadCodigo(mae.getValor());
                        bean.getObjeto().setMaeTipoDiscapacidadValor(mae.getNombre());
                        //actualizamos campo anterior, teniendo en cuenta que es obligatorio
                        bean.getObjeto().setTipoDiscapacidad(mae.getValor());
                    }
                } catch (Exception ex) {
                    bean.addError("Ocurrió un error obteniendo los valores del Maestro de Tipo Discapacidad. Error: " + ex.getMessage());
                }
                //2021-12-15 jyperez nuevos maestros
                //condicion discapacidad NR
                try {
                    if (bean.getObjeto().getMaeCondicionDiscapacidadId() != null) {
                        Maestro mae = bean.getHashCondicionDiscapacidad().get(bean.getObjeto().getMaeCondicionDiscapacidadId());
                        bean.getObjeto().setMaeCondicionDiscapacidadCodigo(mae.getValor());
                        bean.getObjeto().setMaeCondicionDiscapacidadValor(mae.getNombre());
                        //actualizamos campo anterior, teniendo en cuenta que es obligatorio
                        bean.getObjeto().setCondicionDiscapacidad(mae.getValor());
                    }
                } catch (Exception ex) {
                    bean.addError("Ocurrió un error obteniendo los valores del Maestro de Condición Discapacidad. Error: " + ex.getMessage());
                }
                //2021-12-28 maestro causa novedad para crear
                //causa novedad
                try {
                    if (bean.getObjeto().getMaeCausaNovedad() != 0) {
                        Maestro mae = bean.getHashCausaNovedad().get(bean.getObjeto().getMaeCausaNovedad());
                        bean.getObjeto().setMaeCausaNovedadCodigo(mae.getValor());
                        bean.getObjeto().setMaeCausaNovedadValor(mae.getNombre());
                    }
                } catch (Exception ex) {
                    bean.addError("Ocurrió un error obteniendo los valores del Maestro de Causa Novedad. Error: " + ex.getMessage());
                }
                //comunidad_etnica NR
                try {
                    if (bean.getObjeto().getMaeComunidadEtniaId() != null && bean.getObjeto().getMaeComunidadEtniaId() != 0) {
                        Maestro mae = bean.getHashComunidadEtnica().get(bean.getObjeto().getMaeComunidadEtniaId());
                        bean.getObjeto().setMaeComunidadEtniaCodigo(mae.getValor());
                        bean.getObjeto().setMaeComunidadEtniaValor(mae.getNombre());
                    }
                } catch (Exception ex) {
                    bean.addError("Ocurrió un error obteniendo los valores del Maestro de Comunidad Etnica. Error: " + ex.getMessage());
                }
                //metodologia_grupo_poblacional NR
                try {
                    if (bean.getObjeto().getMaeMetodologiaGrupoPoblacionalId() != null && bean.getObjeto().getMaeMetodologiaGrupoPoblacionalId() != 0) {
                        Maestro mae = bean.getHashMetodologiaGrupoPoblacional().get(bean.getObjeto().getMaeMetodologiaGrupoPoblacionalId());
                        bean.getObjeto().setMaeMetodologiaGrupoPoblacionalCodigo(mae.getValor());
                        bean.getObjeto().setMaeMetodologiaGrupoPoblacionalValor(mae.getNombre());
                    }
                } catch (Exception ex) {
                    bean.addError("Ocurrió un error obteniendo los valores del Maestro de Metodología Grupo Poblacional. Error: " + ex.getMessage());
                }
                // origen ultimo registro
                bean.getObjeto().setOrigenUltimoRegistro(AfiliadoParametro.ORIGEN_ULTIMO_REGISTRO_PANTALLA);
                //2021-10 29 fin carga
                //2021-04-07 incluimos la acción adicional en el guardar.
                if (bean.isAccionAdicional5()) {
                    accionAdicional = AfiliadosBean.ACCION_ADICIONAL_5;
                }
                //2022-06-03 jyperez se copia inicialmente la lista de contactos para poder realizar las validaciones correspondientes
                bean.getObjeto().setListaAsegAfiliadoContacto(bean.getListaContactos());
                validacion.validarCreacion(bean.getObjeto(), AfiliadoValidacion.TIPO_VALIDACION_APLICACION_WEB, accionAdicional);
                //2022-06-03 jyperez se quita la lista de contactos, para no afectar las actualizaciones
                bean.getObjeto().setListaAsegAfiliadoContacto(new ArrayList<>());
                bean.addErrores(validacion.getErrores());
                //2022-05-25 jyperez validación de que el municipio de residencia no sea un objeto nulo
                if (bean.getObjeto().getResidenciaUbicacion() == null) {
                    bean.addError("No se cargó correctamente el Municipio de Residencia. Contacte al administrador.");
                }

                // validamos el vector de errores asi como lo preguntamos al inicio
                if (!bean.isError()) {
                    bean.auditoriaGuardar(bean.getObjeto());
                    bean.auditoriaGuardar(bean.getObjetoTraslado());
                    // 2020-04-15  jyperez aca se debe realizar la inserción del afiliado, la inserción de la encuesta
                    //   y la inserción del objeto de Traslado, en caso de que exista la BDUA seleccionada
                    int id = getAfiliadoRemoto().insertar(bean.getObjeto());
                    bean.getObjeto().setId(id);
                    if (bean.getObjeto().isRegistroBdua()) {
                        bean.getObjetoTraslado().setAsegAfiliado(new AsegAfiliado(id));
                        bean.getObjetoTraslado().setId(getTrasladoRemoto().insertar(bean.getObjetoTraslado()));
                    }
                    // aca se insertaria tambien la capa para la creación de la encuesta
                    if (bean.getListaEncuestaAfiliado() != null && !bean.getListaEncuestaAfiliado().isEmpty()) {
                        for (AsegTabulacionEncuesta enc : bean.getListaEncuestaAfiliado()) {
                            enc.setAsegAfiliado(new AsegAfiliado(id));
                            enc.setPrimerApellido(bean.getObjeto().getPrimerApellido());
                            enc.setSegundoApellido(bean.getObjeto().getSegundoApellido());
                            enc.setPrimerNombre(bean.getObjeto().getPrimerNombre());
                            enc.setSegundoNombre(bean.getObjeto().getSegundoNombre());
                            enc.setFechaNacimiento(bean.getObjeto().getFechaNacimiento());
                            //adicionar datos de auditoría
                            bean.auditoriaGuardar(enc);
                            enc.setId(getEncuestaRemoto().insertar(enc));
                        }
                    }
                    //2020-11-20 jyperez adicion de almacenamiento de histórico afiliado
                    afiliadoHistorico = new AsegAfiliadoHistorico();
                    afiliadoHistorico.setTostringAfiliado(bean.getObjeto().toString());
                    bean.auditoriaGuardar(afiliadoHistorico);
                    afiliadoHistorico.setAsegAfiliado(new AsegAfiliado(bean.getObjeto().getId()));
                    getAfiliadoHistoricoRemoto().insertar(afiliadoHistorico);
                    //2021-05-27 jyperez guardamos los contactos si se han creado
                    if (bean.getListaContactos() != null) {
                        for (AsegAfiliadoContacto contacto : bean.getListaContactos()) {
                            //reseteamos el valor de id asignado
                            contacto.setId(null);
                            contacto.setAsegAfiliado(bean.getObjeto());
                            bean.auditoriaGuardar(contacto);
                            contacto.setId(getAfiliadoContactoRemoto().insertar(contacto));
                            listaContactosAuditar.add(contacto);
                        }
                    }
                    bean.getObjeto().setListaAsegAfiliadoContacto(listaContactosAuditar);
                    bean.addMensaje("Se creo un registro de manera exitosa");
                }
            } else {
                bean.addError("Existe un afiliado registrado con los datos ingresados");
            }

        } catch (Exception e) {
            bean.addError("Ocurrió un error. Contacte al administrador del sistema. Mensaje: " + e.toString());
        }
    }

    private void mostrarMensajePantalla(AfiliadosBean bean, String msj) {
        if (msj != null && !msj.trim().equals("")) {
            bean.addMensaje(msj);
        }
    }

    private void mostrarMensajeErrorPantalla(AfiliadosBean bean, String msj) {
        if (msj != null && !msj.trim().equals("")) {
            bean.addError(msj);
        }
    }

    private void modificar(AfiliadosBean bean) {
        AsegAfiliadoHistorico afiliadoHistorico;
        String mensajeExitoso = "Se actualizó un registro de manera exitosa";
        List<AsegAfiliadoContacto> listaContactosAuditar = new ArrayList<>();
        boolean actualizarGrupoFamiliar = false;
        Ubicacion ubi = null;
        try {
            char accionAdicional = 0;// Se inicializa en 0 que es un valor que no esta entre los que se definen para acciones adicionales
            //Proceso de validación
            AfiliadoValidacion validacion = new AfiliadoValidacion();
            validacion.setHashCausaNovedad(bean.getHashCausaNovedad());
            validacion.setHashEstadosAfiliacion(bean.getHashEstadosAfiliacion());
            validacion.setHashGrupoPoblacional(bean.getHashGrupoPoblacional());
            validacion.setHashOrigenAfiliado(bean.getHashOrigenAfiliado());
            validacion.setHashTiposDocumento(bean.getHashTiposDocumento());
            //2021-03-16  jyperez INC 0000720 Vamos a quitarle los espacios al final y al inicio a los nombres y apellidos del afiliado
            bean.getObjeto().setPrimerApellido(bean.getObjeto().getPrimerApellido().trim());
            bean.getObjeto().setSegundoApellido(bean.getObjeto().getSegundoApellido().trim());
            bean.getObjeto().setPrimerNombre(bean.getObjeto().getPrimerNombre().trim());
            bean.getObjeto().setSegundoNombre(bean.getObjeto().getSegundoNombre().trim());
            if (bean.getObjeto().isRegistroBdua()) {
                //2021-03-16  jyperez INC 0000720 Vamos a quitarle los espacios al final y al inicio a los nombres y apellidos del afiliado
                bean.getObjetoTraslado().setPrimerApellidoBdua(bean.getObjetoTraslado().getPrimerApellidoBdua().trim());
                bean.getObjetoTraslado().setSegundoApellidoBdua(bean.getObjetoTraslado().getSegundoApellidoBdua().trim());
                bean.getObjetoTraslado().setPrimerNombreBdua(bean.getObjetoTraslado().getPrimerNombreBdua().trim());
                bean.getObjetoTraslado().setSegundoNombreBdua(bean.getObjetoTraslado().getSegundoNombreBdua().trim());
            }
            // 2020-07-14 jyperez se adiciona el objetoTraslado al objeto de Afiliado, para realizar su respectiva validación
            bean.getObjeto().setAsegTraslado(bean.getObjetoTraslado());
            //2021-05-20 jyperez obtenemos los valores del maestro nivel Sisben
            try {
                Maestro mae = bean.getHashNivelSisbenNuevo().get(bean.getObjeto().getMaeNivelSisbenId());
                bean.getObjeto().setMaeNivelSisbenCodigo(mae.getValor());
                bean.getObjeto().setMaeNivelSisbenValor(mae.getNombre());
                //actualizamos campo anterior, teniendo en cuenta que es obligatorio
                bean.getObjeto().setNivelSisben(mae.getValor());
            } catch (Exception ex) {
                bean.addError("Ocurrió un error obteniendo los valores del Maestro de Nivel Sisben. Error: " + ex.getMessage());
            }
            try {
                Maestro mae = bean.getHashRegimen().get(bean.getObjeto().getMaeRegimenId());
                bean.getObjeto().setMaeRegimenCodigo(mae.getValor());
                bean.getObjeto().setMaeRegimenValor(mae.getNombre());
                //actualizamos campo anterior, teniendo en cuenta que es obligatorio
                //2021-06-15 jyperez INC 811 se solicita seguir almacenando el valor antiguo en el campo regimen
                if (mae.getValor().equals(AfiliadoParametro.REGIMEN_SUBSIDIADO)) {
                    bean.getObjeto().setRegimen(AfiliadoParametro.REGIMEN_SUBSIDIADO_ANTIGUO);
                } else {
                    bean.getObjeto().setRegimen(AfiliadoParametro.REGIMEN_CONTRIBUTIVO_ANTIGUO);
                }
            } catch (Exception ex) {
                bean.addError("Ocurrió un error obteniendo los valores del Maestro de Régimen. Error: " + ex.getMessage());
            }
            //2021-11-12 jyperez cargamos el id de la ubicación
            try {
                //2023-01-12 jyperez obtenemos el objeto de ubicación necesario a partir del id
                ubi = bean.getHashPaises().get(bean.getObjeto().getPaisNacimiento());
                bean.getObjeto().setNacimientoUbicacion(ubi);
            } catch (Exception ex) {
                bean.addError("Ocurrió un error obteniendo los valores de Pais de Nacimiento. Error: " + ex.getMessage());
            }
            //2023-06-20 jyperez cargamos el lugar de nacimiento
            try {
                //2023-01-12 jyperez obtenemos el objeto de ubicación necesario a partir del id
                ubi = bean.getHashLugarNacimiento().get(bean.getObjeto().getLugarNacimiento());
                bean.getObjeto().setGnUbicacionesLugarNacimientoId(ubi);
            } catch (Exception ex) {
                bean.addError("Ocurrió un error obteniendo los valores de Lugar de Nacimiento. Error: " + ex.getMessage());
            }
            //2021-12-15 jyperez cargamos el id de la ubicación
            try {
                //2023-01-12 jyperez obtenemos el objeto de ubicación necesario a partir del id
                ubi = bean.getHashPaises().get(bean.getObjeto().getPaisNacionalidad());
                bean.getObjeto().setNacionalidadUbicacion(ubi);
            } catch (Exception ex) {
                bean.addError("Ocurrió un error obteniendo los valores de Pais de Nacionalidad. Error: " + ex.getMessage());
            }
            //2021-10-29 jyperez incluimos la carga de los valores de Maestro
            //estadoAfiliacion
            try {
                Maestro mae = bean.getHashEstadosAfiliacion().get(bean.getObjeto().getMaeEstadoAfiliacion());
                bean.getObjeto().setMaeEstadoAfiliacionCodigo(mae.getValor());
                bean.getObjeto().setMaeEstadoAfiliacionValor(mae.getNombre());
            } catch (Exception ex) {
                bean.addError("Ocurrió un error obteniendo los valores del Maestro de Estado Afiliación. Error: " + ex.getMessage());
            }
            //modeloLiquidacion
            try {
                Maestro mae = bean.getHashModeloLiquidacion().get(bean.getObjeto().getMaeModeloLiquidacionId());
                bean.getObjeto().setMaeModeloLiquidacionCodigo(mae.getValor());
                bean.getObjeto().setMaeModeloLiquidacionValor(mae.getNombre());
                //actualizamos campo anterior, teniendo en cuenta que es obligatorio
                bean.getObjeto().setModeloLiquidacion(mae.getValor());
            } catch (Exception ex) {
                bean.addError("Ocurrió un error obteniendo los valores del Maestro de Modelo Liquidacion. Error: " + ex.getMessage());
            }
            //tipoDocumento
            try {
                Maestro mae = bean.getHashTiposDocumento().get(bean.getObjeto().getMaeTipoDocumento());
                bean.getObjeto().setMaeTipoDocumentoCodigo(mae.getValor());
                bean.getObjeto().setMaeTipoDocumentoValor(mae.getNombre());
            } catch (Exception ex) {
                bean.addError("Ocurrió un error obteniendo los valores del Maestro de Tipo Documento. Error: " + ex.getMessage());
            }
            //tipoDocumentoAportante NR
            try {
                if (bean.getObjeto().getMaeTipoDocumentoAportante() != null) {
                    Maestro mae = bean.getHashTiposDocumento().get(bean.getObjeto().getMaeTipoDocumentoAportante());
                    bean.getObjeto().setMaeTipoDocumentoAportanteCodigo(mae.getValor());
                    bean.getObjeto().setMaeTipoDocumentoAportanteValor(mae.getNombre());
                }
            } catch (Exception ex) {
                bean.addError("Ocurrió un error obteniendo los valores del Maestro de Tipo Documento Aportante. Error: " + ex.getMessage());
            }
            //genero
            try {
                Maestro mae = bean.getHashTiposGenero().get(bean.getObjeto().getMaeGeneroId());
                bean.getObjeto().setMaeGeneroCodigo(mae.getValor());
                bean.getObjeto().setMaeGeneroValor(mae.getNombre());
                //actualizamos campo anterior, teniendo en cuenta que es obligatorio -- Se envia el id siempre en este campo
                bean.getObjeto().setGenero(bean.getObjeto().getMaeGeneroCodigo());
            } catch (Exception ex) {
                bean.addError("Ocurrió un error obteniendo los valores del Maestro de Género. Error: " + ex.getMessage());
            }
            //genero identificacion
            try {
                Maestro mae = bean.getHashTiposGeneroIdentificacion().get(bean.getObjeto().getMaeGeneroIdentificacionId());
                bean.getObjeto().setMaeGeneroIdentificacionCodigo(mae.getValor());
                bean.getObjeto().setMaeGeneroIdentificacionValor(mae.getNombre());
            } catch (Exception ex) {
                bean.addError("Ocurrió un error obteniendo los valores del Maestro de Género de Identificación. Error: " + ex.getMessage());
            }
            //estadoCivil
            try {
                Maestro mae = bean.getHashEstadoCivil().get(bean.getObjeto().getMaeEstadoCivilId());
                bean.getObjeto().setMaeEstadoCivilCodigo(mae.getValor());
                bean.getObjeto().setMaeEstadoCivilValor(mae.getNombre());
                //actualizamos campo anterior, teniendo en cuenta que es obligatorio
                bean.getObjeto().setEstadoCivil(mae.getValor());
            } catch (Exception ex) {
                bean.addError("Ocurrió un error obteniendo los valores del Maestro de Estado Civil. Error: " + ex.getMessage());
            }
            //origenAfiliado
            try {
                Maestro mae = bean.getHashOrigenAfiliado().get(bean.getObjeto().getMaeOrigenAfiliado());
                bean.getObjeto().setMaeOrigenAfiliadoCodigo(mae.getValor());
                bean.getObjeto().setMaeOrigenAfiliadoValor(mae.getNombre());
            } catch (Exception ex) {
                bean.addError("Ocurrió un error obteniendo los valores del Maestro de Origen Afiliado. Error: " + ex.getMessage());
            }
            //tipoAfiliado
            try {
                Maestro mae = bean.getHashTipoAfiliadoCompleto().get(bean.getObjeto().getMaeTipoAfiliadoId());
                bean.getObjeto().setMaeTipoAfiliadoCodigo(mae.getValor());
                bean.getObjeto().setMaeTipoAfiliadoValor(mae.getNombre());
                //actualizamos campo anterior, teniendo en cuenta que es obligatorio
                bean.getObjeto().setTipoBeneficiario(mae.getValor());
            } catch (Exception ex) {
                bean.addError("Ocurrió un error obteniendo los valores del Maestro de Tipo Afiliado. Error: " + ex.getMessage());
            }
            //parentesco NR
            try {
                if (bean.getObjeto().getMaeParentescoCotizanteId() != null && bean.getObjeto().getMaeParentescoCotizanteId() != 0) {
                    Maestro mae = bean.getHashParentescoCotizante().get(bean.getObjeto().getMaeParentescoCotizanteId());
                    bean.getObjeto().setMaeParentescoCotizanteCodigo(mae.getValor());
                    bean.getObjeto().setMaeParentescoCotizanteValor(mae.getNombre());
                    //actualizamos campo anterior, teniendo en cuenta que es obligatorio
                    bean.getObjeto().setParentescoCotizante(mae.getValor());
                } else if (bean.getObjeto().getMaeParentescoCotizanteId() != null) {
                    bean.getObjeto().setMaeParentescoCotizanteId(null);
                }
            } catch (Exception ex) {
                bean.addError("Ocurrió un error obteniendo los valores del Maestro de Parentesco. Error: " + ex.getMessage());
            }
            //tipoDocumentoCF NR
            try {
                if (bean.getObjeto().getMaeTipoDocumentoCf() != null && bean.getObjeto().getMaeTipoDocumentoCf() != 0) {
                    Maestro mae = bean.getHashTiposDocumento().get(bean.getObjeto().getMaeTipoDocumentoCf());
                    bean.getObjeto().setMaeTipoDocumentoCfCodigo(mae.getValor());
                    bean.getObjeto().setMaeTipoDocumentoCfValor(mae.getNombre());
                }
            } catch (Exception ex) {
                bean.addError("Ocurrió un error obteniendo los valores del Maestro de Tipo Documento CF. Error: " + ex.getMessage());
            }
            //zona
            try {
                Maestro mae = bean.getHashZona().get(bean.getObjeto().getMaeZonaId());
                bean.getObjeto().setMaeZonaCodigo(mae.getValor());
                bean.getObjeto().setMaeZonaValor(mae.getNombre());
                //actualizamos campo anterior, teniendo en cuenta que es obligatorio
                bean.getObjeto().setZona(mae.getValor());
            } catch (Exception ex) {
                bean.addError("Ocurrió un error obteniendo los valores del Maestro de Zona. Error: " + ex.getMessage());
            }
            //grupo poblacional
            try {
                Maestro mae = bean.getHashGrupoPoblacional().get(bean.getObjeto().getMaeGrupoPoblacional());
                bean.getObjeto().setMaeGrupoPoblacionalCodigo(mae.getValor());
                bean.getObjeto().setMaeGrupoPoblacionalValor(mae.getNombre());
            } catch (Exception ex) {
                bean.addError("Ocurrió un error obteniendo los valores del Maestro de Grupo Poblacional. Error: " + ex.getMessage());
            }
            //etnia
            try {
                Maestro mae = bean.getHashEtnia().get(bean.getObjeto().getMaeEtniaId());
                bean.getObjeto().setMaeEtniaCodigo(mae.getValor());
                bean.getObjeto().setMaeEtniaValor(mae.getNombre());
                //actualizamos campo anterior, teniendo en cuenta que es obligatorio
                bean.getObjeto().setEtnia(mae.getValor());
            } catch (Exception ex) {
                bean.addError("Ocurrió un error obteniendo los valores del Maestro de Etnia. Error: " + ex.getMessage());
            }
            //grupoSisben
            try {
                if (bean.getObjeto().getMaeGrupoSisbenId() != null) {
                    Maestro mae = bean.getHashGrupoSisben().get(bean.getObjeto().getMaeGrupoSisbenId());
                    bean.getObjeto().setMaeGrupoSisbenCodigo(mae.getValor());
                    bean.getObjeto().setMaeGrupoSisbenValor(mae.getNombre());
                } else {
                    bean.getObjeto().setMaeGrupoSisbenCodigo("");
                    bean.getObjeto().setMaeGrupoSisbenValor("");
                }
            } catch (Exception ex) {
                bean.addError("Ocurrió un error obteniendo los valores del Maestro de Grupo Sisben. Error: " + ex.getMessage());
            }
            //subGrupoSisben NR
            try {
                if (bean.getObjeto().getMaeSubGrupoSisbenId() != null) {
                    Maestro mae = bean.getHashSubGrupoSisben().get(bean.getObjeto().getMaeSubGrupoSisbenId());
                    bean.getObjeto().setMaeSubGrupoSisbenCodigo(mae.getValor());
                    bean.getObjeto().setMaeSubGrupoSisbenValor(mae.getNombre());
                } else {
                    bean.getObjeto().setMaeSubGrupoSisbenCodigo("");
                    bean.getObjeto().setMaeSubGrupoSisbenValor("");
                }
            } catch (Exception ex) {
                bean.addError("Ocurrió un error obteniendo los valores del Maestro de SubGrupo Sisben. Error: " + ex.getMessage());
            }
            //tipo discapacidad NR depende de validación en pantalla
            try {
                if (bean.getObjeto().getMaeTipoDiscapacidadId() != null) {
                    Maestro mae = bean.getHashTipoDiscapacidad().get(bean.getObjeto().getMaeTipoDiscapacidadId());
                    bean.getObjeto().setMaeTipoDiscapacidadCodigo(mae.getValor());
                    bean.getObjeto().setMaeTipoDiscapacidadValor(mae.getNombre());
                    //actualizamos campo anterior, teniendo en cuenta que es obligatorio
                    bean.getObjeto().setTipoDiscapacidad(mae.getValor());
                }
            } catch (Exception ex) {
                bean.addError("Ocurrió un error obteniendo los valores del Maestro de Tipo Discapacidad. Error: " + ex.getMessage());
            }
            //categoriaIbc NR
            try {
                if (bean.getObjeto().getMaeCategoriaIbcId() != null) {
                    Maestro mae = bean.getHashCategoriaIbc().get(bean.getObjeto().getMaeCategoriaIbcId());
                    bean.getObjeto().setMaeCategoriaIbcCodigo(mae.getValor());
                    bean.getObjeto().setMaeCategoriaIbcValor(mae.getNombre());
                    //actualizamos campo anterior, teniendo en cuenta que es obligatorio
                    bean.getObjeto().setCategoriaIbc(mae.getValor());
                } else {
                    bean.getObjeto().setMaeCategoriaIbcCodigo("");
                    bean.getObjeto().setMaeCategoriaIbcValor("");
                }
            } catch (Exception ex) {
                bean.addError("Ocurrió un error obteniendo los valores del Maestro de Categoria Ibc. Error: " + ex.getMessage());
            }
            //causa novedad NR VALIDAR ESTE CAMPO PUEDEN OCURRIR ERRORES POR EL VALOR DEBIDO A QUE ES INT NO INTEGER
            try {
                if (bean.getObjeto().getMaeCausaNovedad() != 0) {
                    Maestro mae = bean.getHashCausaNovedad().get(bean.getObjeto().getMaeCausaNovedad());
                    bean.getObjeto().setMaeCausaNovedadCodigo(mae.getValor());
                    bean.getObjeto().setMaeCausaNovedadValor(mae.getNombre());
                }
            } catch (Exception ex) {
                bean.addError("Ocurrió un error obteniendo los valores del Maestro de Causa Novedad. Error: " + ex.getMessage());
            }
            //2021-12-15 jyperez nuevos maestros
            //condicion discapacidad NR
            try {
                if (bean.getObjeto().getMaeCondicionDiscapacidadId() != null) {
                    Maestro mae = bean.getHashCondicionDiscapacidad().get(bean.getObjeto().getMaeCondicionDiscapacidadId());
                    bean.getObjeto().setMaeCondicionDiscapacidadCodigo(mae.getValor());
                    bean.getObjeto().setMaeCondicionDiscapacidadValor(mae.getNombre());
                    //actualizamos campo anterior, teniendo en cuenta que es obligatorio
                    bean.getObjeto().setCondicionDiscapacidad(mae.getValor());
                }
            } catch (Exception ex) {
                bean.addError("Ocurrió un error obteniendo los valores del Maestro de Condición Discapacidad. Error: " + ex.getMessage());
            }
            //actividad economica NR
            try {
                if (bean.getObjeto().getMaeActividadEconomica() != null) {
                    Maestro mae = bean.getHashActividadEconomica().get(bean.getObjeto().getMaeActividadEconomica());
                    bean.getObjeto().setMaeActividadEconomicaCodigo(mae.getValor());
                    bean.getObjeto().setMaeActividadEconomicaValor(mae.getNombre());
                }
            } catch (Exception ex) {
                bean.addError("Ocurrió un error obteniendo los valores del Maestro de Actividad Económica. Error: " + ex.getMessage());
            }
            //arl NR
            try {
                if (bean.getObjeto().getMaeArl() != null) {
                    Maestro mae = bean.getHashARL().get(bean.getObjeto().getMaeArl());
                    bean.getObjeto().setMaeArlCodigo(mae.getValor());
                    bean.getObjeto().setMaeArlValor(mae.getNombre());
                }
            } catch (Exception ex) {
                bean.addError("Ocurrió un error obteniendo los valores del Maestro de ARL. Error: " + ex.getMessage());
            }
            //afp NR
            try {
                if (bean.getObjeto().getMaeAfp() != null) {
                    Maestro mae = bean.getHashAFP().get(bean.getObjeto().getMaeAfp());
                    bean.getObjeto().setMaeAfpCodigo(mae.getValor());
                    bean.getObjeto().setMaeAfpValor(mae.getNombre());
                }
            } catch (Exception ex) {
                bean.addError("Ocurrió un error obteniendo los valores del Maestro AFP. Error: " + ex.getMessage());
            }
            //ccf NR
            try {
                if (bean.getObjeto().getMaeCajaCompensacion() != null) {
                    Maestro mae = bean.getHashCajaCompensacion().get(bean.getObjeto().getMaeCajaCompensacion());
                    bean.getObjeto().setMaeCajaCompensacionCodigo(mae.getValor());
                    bean.getObjeto().setMaeCajaCompensacionValor(mae.getNombre());
                }
            } catch (Exception ex) {
                bean.addError("Ocurrió un error obteniendo los valores del Maestro de Caja Compensación. Error: " + ex.getMessage());
            }
            //comunidad_etnica NR
            try {
                if (bean.getObjeto().getMaeComunidadEtniaId() != null && bean.getObjeto().getMaeComunidadEtniaId() != 0) {
                    Maestro mae = bean.getHashComunidadEtnica().get(bean.getObjeto().getMaeComunidadEtniaId());
                    bean.getObjeto().setMaeComunidadEtniaCodigo(mae.getValor());
                    bean.getObjeto().setMaeComunidadEtniaValor(mae.getNombre());
                }
            } catch (Exception ex) {
                bean.addError("Ocurrió un error obteniendo los valores del Maestro de Comunidad Etnica. Error: " + ex.getMessage());
            }
            //metodologia_grupo_poblacional NR
            try {
                if (bean.getObjeto().getMaeMetodologiaGrupoPoblacionalId() != null && bean.getObjeto().getMaeMetodologiaGrupoPoblacionalId() != 0) {
                    Maestro mae = bean.getHashMetodologiaGrupoPoblacional().get(bean.getObjeto().getMaeMetodologiaGrupoPoblacionalId());
                    bean.getObjeto().setMaeMetodologiaGrupoPoblacionalCodigo(mae.getValor());
                    bean.getObjeto().setMaeMetodologiaGrupoPoblacionalValor(mae.getNombre());
                } else {
                    bean.getObjeto().setMaeMetodologiaGrupoPoblacionalCodigo(null);
                    bean.getObjeto().setMaeMetodologiaGrupoPoblacionalValor(null);
                }
            } catch (Exception ex) {
                bean.addError("Ocurrió un error obteniendo los valores del Maestro de Metodología Grupo Poblacional. Error: " + ex.getMessage());
            }
            // origen ultimo registro
            bean.getObjeto().setOrigenUltimoRegistro(AfiliadoParametro.ORIGEN_ULTIMO_REGISTRO_PANTALLA);
            //2021-10 29 fin carga

            //2020-08-13 jyperez Se adiciona campo con el valor de la acción adicional
            if (bean.isAccionAdicional5()) {
                accionAdicional = AfiliadosBean.ACCION_ADICIONAL_5;
            }
            //2022-06-03 jyperez se copia inicialmente la lista de contactos para poder realizar las validaciones correspondientes
            bean.getObjeto().setListaAsegAfiliadoContacto(bean.getListaContactos());
            validacion.validarEdicion(bean.getObjeto(), bean.getObjetoAnterior(), AfiliadoValidacion.TIPO_VALIDACION_APLICACION_WEB, accionAdicional);
            //2022-06-03 jyperez se quita la lista de contactos, para no afectar las actualizaciones
            bean.getObjeto().setListaAsegAfiliadoContacto(new ArrayList<>());
            bean.addErrores(validacion.getErrores());

            //2022-09-23 jyperez validamos si se realizó cambio de municipio de afiliación y de residencia ( en caso del subsidiado deben de ser hacia el mismo)
            if (bean.isAfiliadoPortabilidadVigente()
                    && !Objects.equals(bean.getObjeto().getResidenciaUbicacion().getId(), bean.getObjetoAnterior().getResidenciaUbicacion().getId())
                    && Objects.equals(bean.getObjeto().getResidenciaUbicacion().getId(), bean.getPortabilidadVigente().getUbicacion().getId())) {
                mensajeExitoso = mensajeExitoso + ". La portabilidad vigente del afiliado será cancelada a las 00:00:00 horas del día siguiente.";
            }
            //2022-09-28 jyperez validamos si se realizó un cambio de tipo documento y/o numero documento, esto para actualizar
            // el grupo familiar del afiliado en caso de que lo tenga
            if (bean.getListaGrupoFamiliarAfiliado() != null && !bean.getListaGrupoFamiliarAfiliado().isEmpty()) {
                if (bean.getObjeto().getMaeTipoDocumento() != bean.getObjetoAnterior().getMaeTipoDocumento()
                        || !bean.getObjeto().getNumeroDocumento().equals(bean.getObjetoAnterior().getNumeroDocumento())) {
                    actualizarGrupoFamiliar = true;
                }
            }

            // validamos el vector de errores asi como lo preguntamos al inicio
            if (!bean.isError()) {
                //actualizamos el prestadorSede si el objeto de consulta se modificó
                if (bean.getObjetoAnterior().getPrimariaPrestadorSede() != null
                        && bean.getObjetoAnterior().getPrimariaPrestadorSede().getId() != null
                        && bean.getObjetoAnterior().getPrimariaPrestadorSede().getId() != 0
                        && !Objects.equals(bean.getObjetoAnterior().getPrimariaPrestadorSede().getId(), bean.getObjeto().getPrimariaPrestadorSede().getId())) {
                    bean.getObjeto().setPrimariaPrestadorSede(bean.getObjetoAnterior().getPrimariaPrestadorSede());
                }

                bean.auditoriaModificar(bean.getObjeto());
                getAfiliadoRemoto().actualizar(bean.getObjeto());
                // 2020-07-14 jyperez se adiciona la actualización del objeto traslado
                if (bean.getObjeto().isRegistroBdua()) {
                    //2022-07-22 jyperez se adiciona validación de que si el valor del id es 0, proceda a crear el registro
                    if (bean.getObjetoTraslado().getId() != null && bean.getObjetoTraslado().getId() != 0) {
                        bean.auditoriaModificar(bean.getObjetoTraslado());
                        getTrasladoRemoto().actualizar(bean.getObjetoTraslado());
                        bean.addMensaje("Se actualizó el registro de BDUA de manera exitosa");
                    } else {
                        bean.getObjetoTraslado().setAsegAfiliado(new AsegAfiliado(bean.getObjeto().getId()));
                        //2022-07-22 se corrige debido a que si se ingresa un valor en el id, este no será creado.
                        bean.getObjetoTraslado().setId(null);
                        bean.auditoriaGuardar(bean.getObjetoTraslado());
                        getTrasladoRemoto().insertar(bean.getObjetoTraslado());
                        bean.addMensaje("Se creó el registro de BDUA de manera exitosa");
                    }
                }
                //2022-02-18 se adiciona funcionalidad para actualizar estado afiliado en programas especiales al cambiar
                // el estado del afiliado

                //Se comenta codigo para actualizar estado de afilaido en programas especiales. Caso mantis 1209
                /*boolean estado_activo = false;
                Maestro mae_estado_afiliacion = bean.getHashEstadosAfiliacion().get(bean.getObjeto().getMaeEstadoAfiliacion());
                if(mae_estado_afiliacion.getMaestroAccion().getId() == MaestroAccion.ASEG_ESTADO_AFILIACION_AFILIADO_ACTIVO){
                    estado_activo = true;
                }
                PeAfiliadosPrograma afiliado = new PeAfiliadosPrograma();
                afiliado.setActivo(estado_activo);
                afiliado.setAsegAfiliado(new AsegAfiliado(bean.getObjetoAnterior().getId()));
                getPeAfiliadosProgramaRemoto().actualizarEstadoActivo(afiliado);*/
                //2020-11-20 jyperez adicion de almacenamiento de histórico afiliado
                // se solicita que en modificar se guarde la información anterior
                afiliadoHistorico = new AsegAfiliadoHistorico();
                if (bean.getPrestadorAnterior() != null) {
                    bean.getObjetoAnterior().setPrimariaPrestadorSede(bean.getPrestadorAnterior());
                }
                afiliadoHistorico.setTostringAfiliado(bean.getObjetoAnterior().toString());
                bean.auditoriaGuardar(afiliadoHistorico);
                afiliadoHistorico.setAsegAfiliado(new AsegAfiliado(bean.getObjetoAnterior().getId()));
                getAfiliadoHistoricoRemoto().insertar(afiliadoHistorico);
                //ahora procedemos a realizar la validación de los registros a Editar y a Crear
                for (AsegAfiliadoContacto contacto : bean.getListaContactos()) {
                    if (contacto.isNuevoRegistro()) {
                        contacto.setId(null);
                        contacto.setAsegAfiliado(bean.getObjeto());
                        bean.auditoriaGuardar(contacto);
                        contacto.setId(getAfiliadoContactoRemoto().insertar(contacto));
                        //2021-03-15 jyperez adicionamos el registro con el id correspondiente
                        listaContactosAuditar.add(contacto);
                    } else if (contacto.isEditado()) {
                        bean.auditoriaModificar(contacto);
                        getAfiliadoContactoRemoto().actualizar(contacto);
                        //2021-03-15 jyperez adicionamos el registro modificado
                        listaContactosAuditar.add(contacto);
                    } else {
                        listaContactosAuditar.add(contacto);
                    }
                }
                bean.getObjeto().setListaAsegAfiliadoContacto(listaContactosAuditar);
                // procedemos por ultimo a ejecutar los procesos de los registros a eliminar
                for (AsegAfiliadoContacto contacto : bean.getListaContactosBorrar()) {
                    getAfiliadoContactoRemoto().eliminar(contacto.getId());
                }
                //2022-09-28 jyperez validamos si se debe actualizar el grupo familiar del afiliado con los valores de tipo Documento y Numero Documento
                if (actualizarGrupoFamiliar) {
                    for (AsegAfiliado beneficiario : bean.getListaGrupoFamiliarAfiliado()) {
                        beneficiario.setMaeTipoDocumentoCf(bean.getObjeto().getMaeTipoDocumento());
                        beneficiario.setMaeTipoDocumentoCfCodigo(bean.getObjeto().getMaeTipoDocumentoCodigo());
                        beneficiario.setMaeTipoDocumentoCfValor(bean.getObjeto().getMaeTipoDocumentoValor());
                        beneficiario.setNumeroDocumentoCf(bean.getObjeto().getNumeroDocumento());
                        bean.auditoriaGuardar(beneficiario);
                        getAfiliadoRemoto().actualizar(beneficiario);
                    }
                }
                bean.addMensaje(mensajeExitoso);
            }
        } catch (Exception e) {
            bean.addError("Ocurrió un error. Contacte al administrador del sistema. Mensaje: " + e.toString());
        }
    }

    private void borrar(AfiliadosBean bean) {
        try {
            bean.setObjeto(getAfiliadoRemoto().eliminar(bean.getObjeto().getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void cargas(AfiliadosBean bean) {
        try {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    break;
                case Url.ACCION_VER:
                    break;
                case Url.ACCION_CREAR:
                case Url.ACCION_EDITAR:
                    break;
                case Url.ACCION_ADICIONAL_1:
                    break;
                case Url.ACCION_ADICIONAL_2:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_CREAR:
                            bean.setListaRadicados(getNovedadAfiliadoRemoto().consultarPorAfiliado(bean.getObjeto().getId()));
                            break;
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {

        }
    }

    @Override
    public void cargaInicial(AfiliadosBean bean) {
        try {
            // carga de listas maestras
            bean.setListaTiposDocumento(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));
            bean.setHashTiposDocumento(Util.convertToHash(bean.getListaTiposDocumento()));
            bean.setListaTiposGenero(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.GN_SEXO));
            bean.setHashTiposGenero(Util.convertToHash(bean.getListaTiposGenero()));
            //2023-06-20 jyperez N45 genero identificación
            bean.setListaTiposGeneroIdentificacion(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.ASEG_GENERO_IDENTIFICACION));
            bean.setHashTiposGeneroIdentificacion(Util.convertToHash(bean.getListaTiposGeneroIdentificacion()));
            bean.setListaEstadosAfiliacion(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.ASEG_ESTADO_AFILIACION));
            bean.setListaEstadosAfiliacionListar(bean.getListaEstadosAfiliacion());
            bean.setHashEstadosAfiliacion(Util.convertToHash(bean.getListaEstadosAfiliacionListar()));
            //2022-02-01 jyperez se crea hash que tendrá todos los estados de afiliación activos. Necesario para validación
            bean.setHashEstadosAfiliacionCompleto(Util.convertToHash(bean.getListaEstadosAfiliacionListar()));
            bean.setHashValorEstadosAfiliacion(Util.convertToHashValor(bean.getListaEstadosAfiliacion()));
            bean.setListaOrigenAfiliado(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.ASEG_ORIGEN_AFILIADO));
            bean.setHashOrigenAfiliado(Util.convertToHash(bean.getListaOrigenAfiliado()));
            bean.setListaGrupoPoblacional(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.ASEG_GRUPO_POBLACIONAL));
            bean.setHashGrupoPoblacional(Util.convertToHash(bean.getListaGrupoPoblacional()));
            bean.setListaCausaNovedad(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.ASEG_CAUSA_NOVEDAD));
            bean.setHashCausaNovedad(Util.convertToHash(bean.getListaCausaNovedad()));
            bean.setListaCajaCompensacion(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.ASEG_CAJA_COMPENSACION));
            bean.setHashCajaCompensacion(Util.convertToHash(bean.getListaCajaCompensacion()));
            bean.setListaARL(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.ASEG_ARL));
            bean.setHashARL(Util.convertToHash(bean.getListaARL()));
            bean.setListaAFP(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.ASEG_AFP));
            bean.setHashAFP(Util.convertToHash(bean.getListaAFP()));
            bean.setListaActividadEconomica(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.ASEG_ACTIVIDAD_ECONOMICA));
            bean.setHashActividadEconomica(Util.convertToHash(bean.getListaActividadEconomica()));
            //2021-05-04 jyperez INC 0000797
            bean.setListaGrupoSisben(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.ASEG_SISBEN_CATEGORIA));
            bean.setHashGrupoSisben(Util.convertToHash(bean.getListaGrupoSisben()));
            bean.setListaSubGrupoSisben(MaestroSingle.getInstance().listarPorTipoYOrdenPorId(MaestroTipo.ASEG_SISBEN_SUBCATEGORIA));
            bean.setHashSubGrupoSisben(MaestroSingle.getInstance().getHashPorTipo(MaestroTipo.ASEG_SISBEN_SUBCATEGORIA));
            //Se pasa a un singleton para reducir uso de Memoria
            // carga de lista de valores de ubicacion
            //bean.setUbicaciones(getUbicacionRemoto().consultarHijosSegunIdPadreCobertura(AfiliadosBean.IDENTIFICADOR_DEPARTAMENTO_POR_DEFECTO));
            //bean.setUbicacionesRecursiva(getUbicacionRemoto().consultarMunicipiosSegunIdPadreCobertura(AfiliadosBean.IDENTIFICADOR_DEPARTAMENTO_POR_DEFECTO));
            //2025-05-23 jyperez 
            bean.setUbicaciones(UbicacionSingle.getInstance().consultarMunicipiosConCobertura());
            bean.setUbicacionesRecursiva(Util.convertToHashUbicacion(UbicacionSingle.getInstance().consultarMunicipiosConCobertura()));
            //2021-11-12 jyperez cargamos la lista de paises
            bean.setListaPaises(UbicacionSingle.getInstance().getListaPaises());
            bean.setHashPaises(Util.convertToHashUbicacion(bean.getListaPaises()));
            //2021-16-20 jyperez N43 cargamos la lista de municipios para el nacimiento
            bean.setListaLugarNacimiento(UbicacionSingle.getInstance().getListaMunicipios());
            bean.setHashLugarNacimiento(Util.convertToHashUbicacion(bean.getListaLugarNacimiento()));
            //carga de listas de prestadores sedes prestadores, por defecto los de Medellin
            //se comenta el negocio indica que no se deberia cargar nada hasta seleccionar municipio
            //bean.setListaPrestadoresSedes(getPrestadoresRemoto().consultarListaSedes(AfiliadosBean.IDENTIFICADOR_UBICACION_ID_POR_DEFECTO));
            //bean.setHashPrestadoresSedes(getPrestadoresRemoto().consultarHashSedesPorId(AfiliadosBean.IDENTIFICADOR_UBICACION_ID_POR_DEFECTO));
            // carga de listas para encuesta
            bean.setListaPreguntasEncuesta(getEncuestaRemoto().consultarPreguntasEncuesta());

            //2020-08-19 jyperez carga lista eps anteriores
            bean.setListadoEps(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.ASEG_EPS));
            bean.setListadoEpsRecursiva(Util.convertToHash(bean.getListadoEps()));
            //2021-05-20 jyperez nuevos maestros regimen y nivel
            //2023-02-10 jyperez creamos una nueva lista para la pantalla listar debido a que se esta modificando por los filtros de la edición y creación
            bean.setListaNivelSisbenListar(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.ASEG_SISBEN_NIVEL));
            bean.setListaNivelSisbenNuevo(bean.getListaNivelSisbenListar());
            bean.setHashNivelSisbenNuevo(Util.convertToHash(bean.getListaNivelSisbenNuevo()));
            bean.setHashValorNivelSisbenNuevo(Util.convertToHashValor(bean.getListaNivelSisbenNuevo()));
            bean.setListaRegimen(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.GN_REGIMEN));
            bean.setHashRegimen(Util.convertToHash(bean.getListaRegimen()));
            //2021-05-27 jyperez carga maestro tipo Contacto
            bean.setListaTiposContacto(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.GN_TIPO_CONTACTO));
            bean.setHashTiposContacto(Util.convertToHash(bean.getListaTiposContacto()));
            //2021-10-28 jyperez carga nuevos maestros
            bean.setListaCategoriaIbc(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.ASEG_CATEGORIA_IBC));
            bean.setHashCategoriaIbc(Util.convertToHash(bean.getListaCategoriaIbc()));
            bean.setListaModeloLiquidacion(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.ASEG_MODELO_LIQUIDACION));
            bean.setHashModeloLiquidacion(Util.convertToHash(bean.getListaModeloLiquidacion()));
            bean.setHashValorModeloLiquidacion(MaestroSingle.getInstance().getHashValorPorTipo(MaestroTipo.ASEG_MODELO_LIQUIDACION));
            bean.setListaEstadoCivil(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.GN_ESTADO_CIVIL));
            bean.setHashEstadoCivil(Util.convertToHash(bean.getListaEstadoCivil()));
            bean.setListaTipoAfiliado(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.ASEG_AFILIADO_TIPO));
            bean.setListaTipoAfiliadoCompleto(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.ASEG_AFILIADO_TIPO));
            bean.setHashTipoAfiliadoCompleto(Util.convertToHash(bean.getListaTipoAfiliadoCompleto()));
            bean.setListaParentescoCotizante(new ArrayList<>());
            bean.setHashParentescoCotizante(MaestroSingle.getInstance().getHashPorTipo(MaestroTipo.ASEG_COTIZANTE_PARENTESCO));
            bean.setListaZona(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.GN_ZONA_UBICACION));
            bean.setHashZona(Util.convertToHash(bean.getListaZona()));
            bean.setListaEtnia(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.GN_ETNIA));
            bean.setHashEtnia(Util.convertToHash(bean.getListaEtnia()));
            bean.setListaTipoDiscapacidad(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.GN_TIPO_DISCAPACIDAD));
            bean.setHashTipoDiscapacidad(Util.convertToHash(bean.getListaTipoDiscapacidad()));
            bean.setListaCondicionDiscapacidad(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.ASEG_CONDICION_DISCAPACIDAD));
            bean.setHashCondicionDiscapacidad(Util.convertToHash(bean.getListaCondicionDiscapacidad()));
            //2022-01-13 jyperez nuevo campo
            bean.setListaComunidadEtnica(new ArrayList<>());//SOLO SE INICIALIZA DEBIDO A QUE DEPENDE DE ETNIA
            bean.setHashComunidadEtnica(MaestroSingle.getInstance().getHashPorTipo(MaestroTipo.GN_COMUNIDAD_ETNIA));
            //2022-02-21 jyperez nuevo campo
            bean.setListaMetodologiaGrupoPoblacional(new ArrayList<>());//SOLO SE INICIALIZA DEBIDO A QUE DEPENDE DE GRUPO POBLACIONAL
            bean.setHashMetodologiaGrupoPoblacional(MaestroSingle.getInstance().getHashPorTipo(MaestroTipo.ASEG_METODOLOGIA_GRUPO_POBLACIONAL));
            //2022-06-08 jyperez nuevo maestro se inicializa
            bean.setListaPorcentajeDistribucion(new ArrayList());
            bean.setHashPorcentajeDistribucion(new HashMap());
            //2025-03-13 jyperez obtenemos el objeto ubicacion del municipio de Medellín
            bean.setCiudadMedellin(UbicacionSingle.getInstance().consultarMunicipiPorPrefijos(AfiliadoParametro.UBICACION_PREFIJO_ANTIOQUIA, AfiliadoParametro.UBICACION_PREFIJO_MEDELLIN));
            //2025-03-13 jyperez cargamos la ruta del google maps con key
            bean.setRutaImagenes(PropApl.getInstance().get(PropApl.GN_MAPS_IMAGENES));
            bean.setRutaGoogleMapsEmpresarial(PropApl.getInstance().get(PropApl.GN_MAPS_KEY));
        } catch (Exception ex) {
        }
    }

    // Funcionalidades adicionales - ASEGURAMIENTO
    /**
     * Funcionalidad que permite recargar la lista y el hash de Sedes teniendo
     * en cuenta el valor de divipola del municipio seleccionado por el usuario
     *
     * @param bean
     */
    @Override
    public void consultarSedesMunicipio(AfiliadosBean bean) {
        String divipola = "";
        Ubicacion padre;
        try {
            // 2020-05-20 adicionamos validación del campo consultarResidencia, para poder realizar el cambio en edición. Siempre consultará por residencia como se indica.
            if (bean.isConsultarResidencia()) {
                // validamos la existencia del objeto de ubicación. Esto porque el campo no es obligatorio y algunos datos puede no tenerlo
                // si es así, entonces cargará por afiliado inicialmente. En la edición si se modifica, este valor cargará con la ubicación de residencia
                if (bean.getObjeto().getResidenciaUbicacion() != null) {
                    // obtenemos valor del padre
                    //padre = getUbicacionRemoto().consultar(AfiliadosBean.IDENTIFICADOR_DEPARTAMENTO_POR_DEFECTO);
                    //obtenemos el valor de divipola sumando padre.prefijo + hijo.prefijo, en este caso el hijo es el municipio de residencia seleccionado
                    // 2020-11-13 jyperez cambiamos el uso del prefijo por el uso de la ubicacion id
                    //divipola = padre.getPrefijo() + bean.getObjeto().getResidenciaUbicacion().getPrefijo();
                    if (bean.getObjeto().getResidenciaUbicacion().getId() != null) {
                        divipola = bean.getObjeto().getResidenciaUbicacion().getId().toString();
                    }
                    // una vez obtenido el valor, recargamos nuevamente las listas
                    bean.setListaPrestadoresSedes(getPrestadoresRemoto().consultarListaSedesCapitadas(divipola));
                    bean.setHashPrestadoresSedes(getPrestadoresRemoto().consultarHashSedesCapitadasPorId(divipola));
                }
            } else {
                if (bean.getObjeto().getAfiliacionUbicacion() != null) {
                    // obtenemos valor del padre
                    //padre = getUbicacionRemoto().consultar(AfiliadosBean.IDENTIFICADOR_DEPARTAMENTO_POR_DEFECTO);
                    //obtenemos el valor de divipola sumando padre.prefijo + hijo.prefijo, en este caso el hijo es el municipio de afiliación seleccionado
                    // 2020-11-13 jyperez cambiamos el uso del prefijo por el uso de la ubicacion id
                    //divipola = padre.getPrefijo() + bean.getObjeto().getAfiliacionUbicacion().getPrefijo();
                    if (bean.getObjeto().getAfiliacionUbicacion().getId() != null) {
                        divipola = bean.getObjeto().getAfiliacionUbicacion().getId().toString();
                    }
                    // una vez obtenido el valor, recargamos nuevamente las listas
                    bean.setListaPrestadoresSedes(getPrestadoresRemoto().consultarListaSedesCapitadas(divipola));
                    bean.setHashPrestadoresSedes(getPrestadoresRemoto().consultarHashSedesCapitadasPorId(divipola));
                }
            }
        } catch (Exception ex) {
//            Logger.getLogger(AfiliadosServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void consultarAfiliadoExistente(AfiliadosBean bean) {
        AsegAfiliado afiliadoResultado = null;
        List<AsegAfiliado> listaAfiliados = null;
        if (bean.getObjeto() != null) {
            try {
                //2022-05-27 jyperez se validará el usuario existente por Tipo Documento y Numero documento.
                //los otros registros seguiremos consultando por numero de documento
                if (bean.getObjeto().getMaeTipoDocumento() != 0 && bean.getObjeto().getNumeroDocumento() != null) {
                    listaAfiliados = getAfiliadoRemoto().consultarPorTipoDocumentoYNumeroDocumento(bean.getObjeto().getMaeTipoDocumento(), bean.getObjeto().getNumeroDocumento());
                }
                if (listaAfiliados != null && listaAfiliados.size() > 0) {
                    afiliadoResultado = listaAfiliados.get(0);
                }
                if (afiliadoResultado != null && afiliadoResultado.getId() != null && afiliadoResultado.getId() != 0) {
                    //se encontró un afiliado
                    //bean.addError("Existe un afiliado registrado con los datos ingresados");
                    bean.setMensajeAfiliadoExistente("Existe un afiliado registrado con los datos ingresados");
                    bean.setAfiliadoExistente(true);
                } else {
                    bean.setMensajeAfiliadoExistente("");
                    bean.setAfiliadoExistente(false);
                }

            } catch (Exception ex) {
                //bean.addError(ex.getMessage());
                bean.setMensajeAfiliadoExistente("");
                bean.setAfiliadoExistente(false);
            }
        }
    }

    @Override
    public void consultarAfiliadoCabezaFamiliaVer(AfiliadosBean bean) {
        AsegAfiliado afiliadoResultado;
        if (bean.getObjeto() != null) {
            try {
                afiliadoResultado = getAfiliadoRemoto().consultar(bean.getObjeto().getMaeTipoDocumentoCf(), bean.getObjeto().getNumeroDocumentoCf());
                Maestro estadoAfiliacion = bean.getEstadoAfiliacionCompleto(afiliadoResultado.getMaeEstadoAfiliacion());
                if (afiliadoResultado != null && afiliadoResultado.getId() != null && afiliadoResultado.getId() != 0
                        && estadoAfiliacion != null && estadoAfiliacion.contieneAccion(MaestroAccion.ASEG_ESTADO_AFILIACION_AFILIADO_ACTIVO)) {
                    //se encontró un afiliado
                    bean.getObjeto().setNombresCF(afiliadoResultado.getPrimerNombre() + " " + validarCampoCadenaNulo(afiliadoResultado.getSegundoNombre()));
                    bean.getObjeto().setApellidosCF(afiliadoResultado.getPrimerApellido() + " " + validarCampoCadenaNulo(afiliadoResultado.getSegundoApellido()));
                } else {
                    bean.getObjeto().setNombresCF("");
                    bean.getObjeto().setApellidosCF("");
                }
            } catch (Exception ex) {
                bean.getObjeto().setNombresCF("");
                bean.getObjeto().setApellidosCF("");
            }
        }
    }

    @Override
    public void consultarAfiliadoCabezaFamilia(AfiliadosBean bean) {
        AsegAfiliado afiliadoResultado;
        if (bean.getObjeto() != null) {
            try {
                //2022-09-28 jyperez validamos que los datos ingresados no correspondan a los del afiliado
                if (bean.getObjeto().getMaeTipoDocumento() != bean.getObjeto().getMaeTipoDocumentoCf()
                        || !bean.getObjeto().getNumeroDocumento().equals(bean.getObjeto().getNumeroDocumentoCf())) {
                    afiliadoResultado = getAfiliadoRemoto().consultar(bean.getObjeto().getMaeTipoDocumentoCf(), bean.getObjeto().getNumeroDocumentoCf());
                    Maestro estadoAfiliacion = bean.getEstadoAfiliacionCompleto(afiliadoResultado.getMaeEstadoAfiliacion());
                    if (afiliadoResultado != null && afiliadoResultado.getId() != null && afiliadoResultado.getId() != 0
                            && estadoAfiliacion != null && estadoAfiliacion.contieneAccion(MaestroAccion.ASEG_ESTADO_AFILIACION_AFILIADO_ACTIVO)) {
                        //se encontró un afiliado
                        bean.setMensajeAfiliadoCFExistente("");
                        bean.setAfiliadoCFExistente(false);
                        //2020-08-18 jyperez CC afiliado Se adicionan los campos nombres CF y apellidos CF
                        bean.getObjeto().setNombresCF(afiliadoResultado.getPrimerNombre() + " " + validarCampoCadenaNulo(afiliadoResultado.getSegundoNombre()));
                        bean.getObjeto().setApellidosCF(afiliadoResultado.getPrimerApellido() + " " + validarCampoCadenaNulo(afiliadoResultado.getSegundoApellido()));
                        // copiamos los datos necesarios de las secciones Georeferenciacion, IPS Atención Primaria y Datos Socioeconómicos
                        copiarDatosAfiliadoCF(bean, afiliadoResultado);
                        //consultamos la lista de sedes del municipio
                        bean.setConsultarResidencia(false);
                        consultarSedesMunicipio(bean);
                    } else {
                        //bean.addError("El afiliado registrado como Cabeza de Familia no se encuentra activo o registrado en el Sistema");
                        bean.setMensajeAfiliadoCFExistente("El afiliado registrado como Cabeza de Familia no se encuentra activo o registrado en el sistema");
                        bean.setAfiliadoCFExistente(true);
                        bean.getObjeto().setNombresCF("");
                        bean.getObjeto().setApellidosCF("");
                    }
                } else {
                    bean.setMensajeAfiliadoCFExistente("El afiliado no puede ser su propio cabeza de familia");
                    bean.setAfiliadoCFExistente(true);
                    bean.getObjeto().setNombresCF("");
                    bean.getObjeto().setApellidosCF("");
                }

            } catch (Exception ex) {
                //bean.addError(ex.getMessage());
                bean.setMensajeAfiliadoCFExistente("");
                bean.setAfiliadoCFExistente(false);
                bean.getObjeto().setNombresCF("");
                bean.getObjeto().setApellidosCF("");
            }
        }
    }

    @Override
    public void consultarAfiliadoCabezaFamiliaEditar(AfiliadosBean bean) {
        AsegAfiliado afiliadoResultado;
        if (bean.getObjeto() != null) {
            try {
                //2022-09-28 jyperez validamos que los datos ingresados no correspondan a los del afiliado
                if (bean.getObjeto().getMaeTipoDocumento() != bean.getObjeto().getMaeTipoDocumentoCf()
                        || !bean.getObjeto().getNumeroDocumento().equals(bean.getObjeto().getNumeroDocumentoCf())) {
                    afiliadoResultado = getAfiliadoRemoto().consultar(bean.getObjeto().getMaeTipoDocumentoCf(), bean.getObjeto().getNumeroDocumentoCf());
                    Maestro estadoAfiliacion = bean.getEstadoAfiliacionCompleto(afiliadoResultado.getMaeEstadoAfiliacion());
                    if (afiliadoResultado != null && afiliadoResultado.getId() != null && afiliadoResultado.getId() != 0
                            && estadoAfiliacion != null && estadoAfiliacion.contieneAccion(MaestroAccion.ASEG_ESTADO_AFILIACION_AFILIADO_ACTIVO)) {
                        //se encontró un afiliado
                        bean.setMensajeAfiliadoCFExistente("");
                        bean.setAfiliadoCFExistente(false);
                        //2020-08-18 jyperez CC afiliado Se adicionan los campos nombres CF y apellidos CF
                        bean.getObjeto().setNombresCF(afiliadoResultado.getPrimerNombre() + " " + validarCampoCadenaNulo(afiliadoResultado.getSegundoNombre()));
                        bean.getObjeto().setApellidosCF(afiliadoResultado.getPrimerApellido() + " " + validarCampoCadenaNulo(afiliadoResultado.getSegundoApellido()));
                    } else {
                        //bean.addError("El afiliado registrado como Cabeza de Familia no se encuentra activo o registrado en el Sistema");
                        bean.setMensajeAfiliadoCFExistente("El afiliado registrado como Cabeza de Familia no se encuentra activo o registrado en el sistema");
                        bean.setAfiliadoCFExistente(true);
                        bean.getObjeto().setNombresCF("");
                        bean.getObjeto().setApellidosCF("");
                    }
                } else {
                    bean.setMensajeAfiliadoCFExistente("El afiliado no puede ser su propio cabeza de familia");
                    bean.setAfiliadoCFExistente(true);
                    bean.getObjeto().setNombresCF("");
                    bean.getObjeto().setApellidosCF("");
                }

            } catch (Exception ex) {
                //bean.addError(ex.getMessage());
                bean.setMensajeAfiliadoCFExistente("");
                bean.setAfiliadoCFExistente(false);
                bean.getObjeto().setNombresCF("");
                bean.getObjeto().setApellidosCF("");
            }
        }
    }

    /**
     * Función que actualiza la lista de estados de Afiliación dependiendo del
     * estado de afiliado Esto para la opción de Editar Afiliado
     *
     * @deprecated se reemplaza con consultarMaestroEstadoPorEstadoYRegimen
     * @param bean
     */
    /*private void actualizarListaEstadosAfiliado(AfiliadosBean bean) {
        // dependiendo del estado del usuario, se deberá 
        List<Maestro> listaEstadosActualizados = new ArrayList<>();
        try {
            // dependiendo del estado del usuario, se deberá
            switch (bean.getHashEstadosAfiliacion().get(bean.getObjeto().getMaeEstadoAfiliacion()).getValor()) {
                case AfiliadosBean.ESTADO_AFILIACION_ACTIVO:
                    listaEstadosActualizados.add(bean.getHashValorEstadosAfiliacion().get(AfiliadosBean.ESTADO_AFILIACION_ACTIVO));
                    //2021-10-21 jyperez INC 0001046
                    //listaEstadosActualizados.add(bean.getHashValorEstadosAfiliacion().get(AfiliadosBean.ESTADO_AFILIACION_PROTECCION_LABORAL));
                    //listaEstadosActualizados.add(bean.getHashValorEstadosAfiliacion().get(AfiliadosBean.ESTADO_AFILIACION_ACTIVO_POR_EMERGENCIA));
                    listaEstadosActualizados.add(bean.getHashValorEstadosAfiliacion().get(AfiliadosBean.ESTADO_AFILIACION_SUSPENDIDO));
                    listaEstadosActualizados.add(bean.getHashValorEstadosAfiliacion().get(AfiliadosBean.ESTADO_AFILIACION_RETIRADO));
                    listaEstadosActualizados.add(bean.getHashValorEstadosAfiliacion().get(AfiliadosBean.ESTADO_AFILIACION_FALLECIDO));
                    // se adiciona la lista a la nueva lista de maestros
                    bean.setListaEstadosAfiliacion(listaEstadosActualizados);
                    break;
                case AfiliadosBean.ESTADO_AFILIACION_INACTIVO:
                    listaEstadosActualizados.add(bean.getHashValorEstadosAfiliacion().get(AfiliadosBean.ESTADO_AFILIACION_INACTIVO));
                    listaEstadosActualizados.add(bean.getHashValorEstadosAfiliacion().get(AfiliadosBean.ESTADO_AFILIACION_ACTIVO));
                    //2021-10-21 jyperez INC 0001046
                    //listaEstadosActualizados.add(bean.getHashValorEstadosAfiliacion().get(AfiliadosBean.ESTADO_AFILIACION_PROTECCION_LABORAL));
                    //listaEstadosActualizados.add(bean.getHashValorEstadosAfiliacion().get(AfiliadosBean.ESTADO_AFILIACION_ACTIVO_POR_EMERGENCIA));
                    listaEstadosActualizados.add(bean.getHashValorEstadosAfiliacion().get(AfiliadosBean.ESTADO_AFILIACION_RETIRADO));
                    // se adiciona la lista a la nueva lista de maestros
                    bean.setListaEstadosAfiliacion(listaEstadosActualizados);
                    break;
                case AfiliadosBean.ESTADO_AFILIACION_SUSPENDIDO:
                    listaEstadosActualizados.add(bean.getHashValorEstadosAfiliacion().get(AfiliadosBean.ESTADO_AFILIACION_SUSPENDIDO));
                    listaEstadosActualizados.add(bean.getHashValorEstadosAfiliacion().get(AfiliadosBean.ESTADO_AFILIACION_ACTIVO));
                    //2021-10-21 jyperez INC 0001046
                    //listaEstadosActualizados.add(bean.getHashValorEstadosAfiliacion().get(AfiliadosBean.ESTADO_AFILIACION_PROTECCION_LABORAL));
                    //listaEstadosActualizados.add(bean.getHashValorEstadosAfiliacion().get(AfiliadosBean.ESTADO_AFILIACION_ACTIVO_POR_EMERGENCIA));
                    listaEstadosActualizados.add(bean.getHashValorEstadosAfiliacion().get(AfiliadosBean.ESTADO_AFILIACION_RETIRADO));
                    // se adiciona la lista a la nueva lista de maestros
                    bean.setListaEstadosAfiliacion(listaEstadosActualizados);
                    break;
                case AfiliadosBean.ESTADO_AFILIACION_RETIRADO:
                    listaEstadosActualizados.add(bean.getHashValorEstadosAfiliacion().get(AfiliadosBean.ESTADO_AFILIACION_RETIRADO));
                    listaEstadosActualizados.add(bean.getHashValorEstadosAfiliacion().get(AfiliadosBean.ESTADO_AFILIACION_PENDIENTE_SOPORTE));
                    listaEstadosActualizados.add(bean.getHashValorEstadosAfiliacion().get(AfiliadosBean.ESTADO_AFILIACION_FALLECIDO));
                    // se adiciona la lista a la nueva lista de maestros
                    bean.setListaEstadosAfiliacion(listaEstadosActualizados);
                    break;
                //2020-09-09 jyperez INC 298 permitir al rol analista poder editar un afiliado fallecido
                case AfiliadosBean.ESTADO_AFILIACION_FALLECIDO:
                    if (bean.isAccionAdicional5()) {
                        listaEstadosActualizados.add(bean.getHashValorEstadosAfiliacion().get(AfiliadosBean.ESTADO_AFILIACION_FALLECIDO));
                        listaEstadosActualizados.add(bean.getHashValorEstadosAfiliacion().get(AfiliadosBean.ESTADO_AFILIACION_RETIRADO));
                    } else {
                        // Se contemplan los estados Retirado, Fallecido y Pendiente de Soporte los cuales cargarán solo su propia opción
                        listaEstadosActualizados.add(bean.getHashEstadosAfiliacion().get(bean.getObjeto().getMaeEstadoAfiliacion()));
                    }
                    // se adiciona la lista a la nueva lista de maestros
                    bean.setListaEstadosAfiliacion(listaEstadosActualizados);
                    break;
                //2021-09-27 jyperez REQ 140 nuevos estados
                    // se permitiran los mismos cambios de estado del estado ACTIVO - Información entregada por el analista de negocio.
                case AfiliadosBean.ESTADO_AFILIACION_ACTIVO_POR_EMERGENCIA:
                    listaEstadosActualizados.add(bean.getHashValorEstadosAfiliacion().get(AfiliadosBean.ESTADO_AFILIACION_ACTIVO_POR_EMERGENCIA));
                    listaEstadosActualizados.add(bean.getHashValorEstadosAfiliacion().get(AfiliadosBean.ESTADO_AFILIACION_ACTIVO));
                    listaEstadosActualizados.add(bean.getHashValorEstadosAfiliacion().get(AfiliadosBean.ESTADO_AFILIACION_SUSPENDIDO));
                    listaEstadosActualizados.add(bean.getHashValorEstadosAfiliacion().get(AfiliadosBean.ESTADO_AFILIACION_RETIRADO));
                    listaEstadosActualizados.add(bean.getHashValorEstadosAfiliacion().get(AfiliadosBean.ESTADO_AFILIACION_FALLECIDO));
                    listaEstadosActualizados.add(bean.getHashValorEstadosAfiliacion().get(AfiliadosBean.ESTADO_AFILIACION_PROTECCION_LABORAL));
                    // se adiciona la lista a la nueva lista de maestros
                    bean.setListaEstadosAfiliacion(listaEstadosActualizados);
                    break;
                case AfiliadosBean.ESTADO_AFILIACION_PROTECCION_LABORAL:
                    listaEstadosActualizados.add(bean.getHashValorEstadosAfiliacion().get(AfiliadosBean.ESTADO_AFILIACION_PROTECCION_LABORAL));
                    listaEstadosActualizados.add(bean.getHashValorEstadosAfiliacion().get(AfiliadosBean.ESTADO_AFILIACION_ACTIVO));
                    listaEstadosActualizados.add(bean.getHashValorEstadosAfiliacion().get(AfiliadosBean.ESTADO_AFILIACION_SUSPENDIDO));
                    listaEstadosActualizados.add(bean.getHashValorEstadosAfiliacion().get(AfiliadosBean.ESTADO_AFILIACION_RETIRADO));
                    listaEstadosActualizados.add(bean.getHashValorEstadosAfiliacion().get(AfiliadosBean.ESTADO_AFILIACION_FALLECIDO));
                    listaEstadosActualizados.add(bean.getHashValorEstadosAfiliacion().get(AfiliadosBean.ESTADO_AFILIACION_ACTIVO_POR_EMERGENCIA));
                    // se adiciona la lista a la nueva lista de maestros
                    bean.setListaEstadosAfiliacion(listaEstadosActualizados);
                    break;
                default:
                    // Se contemplan los estados Retirado, Fallecido y Pendiente de Soporte los cuales cargarán solo su propia opción
                    listaEstadosActualizados.add(bean.getHashEstadosAfiliacion().get(bean.getObjeto().getMaeEstadoAfiliacion()));
                    // se adiciona la lista a la nueva lista de maestros
                    bean.setListaEstadosAfiliacion(listaEstadosActualizados);
                    break;
            }
        } catch (Exception ex) {

        }
    }*/
    private void listarAdjuntos(AfiliadosBean bean) {
        try {
            bean.setObjeto(getAfiliadoRemoto().consultar(bean.getObjeto().getId()));
            bean.setListaAdjuntos(getAdjuntoRemoto().consultarListaPorAfiliado(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void verAdjunto(AfiliadosBean bean) {
        try {
            bean.setAdjunto(getAdjuntoRemoto().consultar(bean.getAdjunto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crearAdjunto(AfiliadosBean bean) {
        try {
            bean.setAdjunto(new AsegAdjunto());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public void guardarAdjunto(AfiliadosBean bean) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        Date fecha = new Date();
        Maestro mae;
        try {
            //PropApl propiedades = new PropApl();
            String ruta = PropApl.getInstance().get(PropApl.RUTA_ASEGURAMIENTO);
            //Validar origen de afiliado y actualizar 
            if (bean.getHashEstadosAfiliacion().get(bean.getObjeto().getMaeEstadoAfiliacion()).getValor().equals(AfiliadoParametro.ESTADO_AFILIACION_PENDIENTE_SOPORTE)) {
                if (bean.getObjeto().getMaeOrigenAfiliado() != 0
                        && (bean.getHashOrigenAfiliado()).get(bean.getObjeto().getMaeOrigenAfiliado()).getValor().equals(AfiliadoParametro.ORIGEN_AFILIADO_TRASLADO_OTRA_EPS)
                        || bean.getHashOrigenAfiliado().get(bean.getObjeto().getMaeOrigenAfiliado()).getValor().equals(AfiliadoParametro.ORIGEN_AFILIADO_AFILIACION_TRANSACCIONAL_SAT)) {
                    mae = bean.getHashValorEstadosAfiliacion().get("102"); //Valor estado inactivo
                    if (mae != null) {
                        bean.getObjeto().setMaeEstadoAfiliacion(mae.getId());
                        bean.getObjeto().setMaeEstadoAfiliacionCodigo(mae.getValor());
                        bean.getObjeto().setMaeEstadoAfiliacionValor(mae.getNombre());
                    }
                } else {
                    mae = bean.getHashValorEstadosAfiliacion().get("101"); //Valor estado activo
                    if (mae != null) {
                        bean.getObjeto().setMaeEstadoAfiliacion(mae.getId());
                        bean.getObjeto().setMaeEstadoAfiliacionCodigo(mae.getValor());
                        bean.getObjeto().setMaeEstadoAfiliacionValor(mae.getNombre());
                    }
                }
                bean.auditoriaModificar(bean.getObjeto());
                getAfiliadoRemoto().actualizar(bean.getObjeto());
            }
            String archivo = bean.getObjeto().getNumeroDocumento() + "_" + bean.getAdjunto().getTipoArchivo() + "_" + sdf.format(fecha) + bean.getAdjunto().getExtensionAdjunto();
            bean.getAdjunto().setArchivo(archivo);
            bean.auditoriaGuardar(bean.getAdjunto());
            bean.getAdjunto().setRuta(ruta);
            generarArchivo(bean.getAdjunto());
//            bean.getAdjunto().setRadicadoNovedadesId(bean.getObjetoRadicado());
            bean.getAdjunto().setAdjuntoStream(null);
            bean.getAdjunto().setId(getAdjuntoRemoto().insertar(bean.getAdjunto()));
            mostrarMensajePantalla(bean, "El archivo " + bean.getAdjunto().getNombreArchivo() + " se cargó con éxito.");
            mostrarMensajePantalla(bean, "Se actualizó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private boolean generarArchivo(AsegAdjunto adjunto) throws Exception {
        boolean esArchivoGuardado = false;
        OutputStream destino = null;
        try {
            File archivo = new File(adjunto.getRuta(), adjunto.getArchivo());
            destino = new FileOutputStream(archivo);
            IOUtils.copy(adjunto.getAdjuntoStream(), destino);
            IOUtils.closeQuietly(adjunto.getAdjuntoStream());
            IOUtils.closeQuietly(destino);
            esArchivoGuardado = true;
        } catch (FileNotFoundException ex) {
            throw new Exception("Error al subir un adjunto " + ex.getMessage());
        } catch (IOException ex) {
            throw new Exception("Error al subir un adjunto " + ex.getMessage());
        } finally {
            try {
                destino.close();
            } catch (IOException ex) {

            }
        }

        return esArchivoGuardado;
    }

    @Override
    public void reporte016(AfiliadosBean bean) {
        try {
            ReporteEncuesta016 reporte = getAfiliadoRemoto().reporte016(bean.getReporte().getId());
            bean.setReporte(reporte);
        } catch (Exception ex) {
//            Logger.getLogger(AfiliadosServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void guardarAnexosNovedades(AfiliadosBean bean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Función que valida un afiliado existente en función de Primer Nombre
     * Primer Apellido Fecha de Nacimiento
     *
     * @param bean
     * @return
     */
    public String validarAfiliadoExistenteDatosBasicos(AfiliadosBean bean) {
        String mensaje;
        AsegAfiliado afiliadoConsulta;
        try {

            afiliadoConsulta = getAfiliadoRemoto().consultar(bean.getObjeto().getPrimerApellido(), bean.getObjeto().getPrimerNombre(), bean.getObjeto().getFechaNacimiento());
            if (afiliadoConsulta != null
                    && afiliadoConsulta.getId() != null) {
                mensaje = "Existe un afiliado registrado con los datos ingresados: Primer Apellido, Primer Nombre, Fecha de Nacimiento";
            } else {
                mensaje = "";
            }
        } catch (Exception ex) {
            mensaje = "Ocurrió un error consultando afliado Existente. Mensaje: " + ex.getMessage();
        }
        return mensaje;
    }

    /**
     * Función que valida un afiliado existente en función de Primer Nombre
     * Primer Apellido Fecha de Nacimiento Esta funcionalidad aplica para un
     * usuario que se está editando, al cual se le está cambiando sus datos
     * basicos
     *
     * @param bean
     * @return
     */
    public String validarAfiliadoExistenteDatosBasicosEditar(AfiliadosBean bean) {
        String mensaje = "";
        AsegAfiliado afiliadoConsulta;
        try {
            //validamos que sus datos básicos hayan cambiado para ejecutar la consulta
            if (!bean.getObjeto().getPrimerApellido().equals(bean.getObjetoAnterior().getPrimerApellido())
                    || !bean.getObjeto().getPrimerNombre().equals(bean.getObjetoAnterior().getPrimerNombre())
                    || !(bean.getObjeto().getFechaNacimiento().compareTo(bean.getObjetoAnterior().getFechaNacimiento()) == 0)) {
                afiliadoConsulta = getAfiliadoRemoto().consultar(bean.getObjeto().getPrimerApellido(), bean.getObjeto().getPrimerNombre(), bean.getObjeto().getFechaNacimiento());
                if (afiliadoConsulta != null
                        && afiliadoConsulta.getId() != null
                        && afiliadoConsulta.getId().compareTo(bean.getObjeto().getId()) != 0) {
                    mensaje = "Existe un afiliado registrado con los datos ingresados: Primer Apellido, Primer Nombre, Fecha de Nacimiento";
                } else {
                    mensaje = "";
                }
            }
        } catch (Exception ex) {
            mensaje = "Ocurrió un error consultando afliado Existente. Mensaje: " + ex.getMessage();
        }
        return mensaje;
    }

    /**
     * Función para validar que cuando se editan los valores tipo documento y
     * número documento de un afiliado existente, que no exista un afliado
     * registrado con los valores a editar.
     *
     * @param bean
     * @return
     */
    public String consultarAfiliadoExistenteEditar(AfiliadosBean bean) {
        String mensaje = "";
        AsegAfiliado afiliadoResultado;
        try {
            if (bean.getObjeto().getMaeTipoDocumento() != bean.getObjetoAnterior().getMaeTipoDocumento()
                    || !bean.getObjeto().getNumeroDocumento().equals(bean.getObjetoAnterior().getNumeroDocumento())) {
                afiliadoResultado = getAfiliadoRemoto().consultar(bean.getObjeto().getMaeTipoDocumento(), bean.getObjeto().getNumeroDocumento());
                if (afiliadoResultado != null
                        && afiliadoResultado.getId() != null && afiliadoResultado.getId() != 0) {
                    //se encontró un afiliado
                    //bean.addError("Existe un afiliado registrado con los datos ingresados");
                    mensaje = "Existe un afiliado registrado con los datos Tipo Documento y Número Documento ingresados";

                } else {
                    mensaje = "";
                }
            }

        } catch (Exception ex) {
            //bean.addError(ex.getMessage());
            mensaje = "Ocurrió un error consultando afliado Existente. Mensaje: " + ex.getMessage();
        }
        return mensaje;
    }

    @Override
    public void reporteAfiliacion(AfiliadosBean bean) {
        try {
            List<ReporteAfiliacion> listaReportes = getAfiliadoRemoto().reporteAfiliacion(bean.getReporteAfiliacion().getId());
            bean.setListaReporteAfiliacion(listaReportes);
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    private void copiarDatosAfiliadoCF(AfiliadosBean bean, AsegAfiliado afiliado) {

        // copiar datos GeoReferenciacion
        bean.getObjeto().setAfiliacionUbicacion(afiliado.getAfiliacionUbicacion());
        bean.getObjeto().setDireccionResidencia(afiliado.getDireccionResidencia());
        bean.getObjeto().setZona(afiliado.getZona());
        bean.getObjeto().setMaeZonaId(afiliado.getMaeZonaId());
        bean.getObjeto().setMaeZonaCodigo(afiliado.getMaeZonaCodigo());
        bean.getObjeto().setMaeZonaValor(afiliado.getMaeZonaValor());
        bean.getObjeto().setTelefonoFijo(afiliado.getTelefonoFijo());
        bean.getObjeto().setTelefonoMovil(afiliado.getTelefonoMovil());
        bean.getObjeto().setEmail(afiliado.getEmail());
        bean.getObjeto().setAutorizaEnvioEmail(afiliado.getAutorizaEnvioEmail());
        bean.getObjeto().setAutorizaEnvioSms(afiliado.getAutorizaEnvioSms());

        // copiar datos IPS Primaria
        bean.getObjeto().setPrimariaPrestadorSede(afiliado.getPrimariaPrestadorSede());

        // 2022-08-01 jyperez inc 1270 se actualiza tambien la residencia de afiliación
        bean.getObjeto().setResidenciaUbicacion(afiliado.getResidenciaUbicacion());

        // copiar datos socioeconómicos
        bean.getObjeto().setMaeGrupoPoblacional(afiliado.getMaeGrupoPoblacional());
        bean.getObjeto().setMaeGrupoPoblacionalCodigo(afiliado.getMaeGrupoPoblacionalCodigo());
        bean.getObjeto().setMaeGrupoPoblacionalValor(afiliado.getMaeGrupoPoblacionalValor());
        bean.getObjeto().setEtnia(afiliado.getEtnia());
        bean.getObjeto().setMaeEtniaId(afiliado.getMaeEtniaId());
        bean.getObjeto().setMaeEtniaCodigo(afiliado.getMaeEtniaCodigo());
        bean.getObjeto().setMaeEtniaValor(afiliado.getMaeEtniaValor());
        bean.getObjeto().setNivelSisben(afiliado.getNivelSisben());
        //2021-05-20 jyperez adicion valores maestros
        bean.getObjeto().setMaeNivelSisbenId(afiliado.getMaeNivelSisbenId());
        bean.getObjeto().setMaeNivelSisbenCodigo(afiliado.getMaeNivelSisbenCodigo());
        bean.getObjeto().setMaeNivelSisbenValor(afiliado.getMaeNivelSisbenValor());
        bean.getObjeto().setPuntajeSisben(afiliado.getPuntajeSisben());
        bean.getObjeto().setFichaSisben(afiliado.getFichaSisben());
        bean.getObjeto().setFechaSisben(afiliado.getFechaSisben());
        bean.getObjeto().setDiscapacidad(afiliado.isDiscapacidad());
        bean.getObjeto().setTipoDiscapacidad(afiliado.getTipoDiscapacidad());
        bean.getObjeto().setMaeTipoDiscapacidadId(afiliado.getMaeTipoDiscapacidadId());
        bean.getObjeto().setMaeTipoDiscapacidadCodigo(afiliado.getMaeTipoDiscapacidadCodigo());
        bean.getObjeto().setMaeTipoDiscapacidadValor(afiliado.getMaeTipoDiscapacidadValor());
        bean.getObjeto().setVictima(afiliado.getVictima());
        bean.getObjeto().setObservacionNovedad(afiliado.getObservacionNovedad());
    }

    @Override
    public void consultarAfiliadosHomonimos(AfiliadosBean bean) {
        List<AsegAfiliado> listaAfiliadosHomonimos;
        List<String> listaHomonimos = new ArrayList<>();
        int i = 0;
        int id = 0;
        try {
            if (bean.getObjeto().getId() != null) {
                id = bean.getObjeto().getId();
            }
            listaAfiliadosHomonimos = getAfiliadoRemoto().consultarPorFoneticos(bean.getObjeto().getPrimerApellido(), bean.getObjeto().getSegundoApellido(), bean.getObjeto().getPrimerNombre(), bean.getObjeto().getSegundoNombre());
            if (listaAfiliadosHomonimos != null && listaAfiliadosHomonimos.size() > 0) {
                // generamos el mensaje para dar resultados
                for (AsegAfiliado reg : listaAfiliadosHomonimos) {
                    if (i < 5 && reg.getId().compareTo(id) != 0) {
                        //mensaje = mensaje + bean.getHashTiposDocumento().get(reg.getMaeTipoDocumento()).getValor() + reg.getNumeroDocumento() + " - " +  reg.getPrimerNombre() +" " + reg.getSegundoNombre() + " " + reg.getPrimerApellido() + " " + reg.getSegundoApellido() + "\n";
                        listaHomonimos.add(bean.getHashTiposDocumento().get(reg.getMaeTipoDocumento()).getValor() + " " + reg.getNumeroDocumento() + " - " + reg.getPrimerNombre() + " " + validarMensajeNulo(reg.getSegundoNombre()) + " " + reg.getPrimerApellido() + " " + validarMensajeNulo(reg.getSegundoApellido()));
                        i++;
                    }
                }
            }
            bean.setRegistrosHomonimos(listaHomonimos);
        } catch (Exception ex) {
            Logger.getLogger(AfiliadosServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
            bean.setRegistrosHomonimos(listaHomonimos);
        }
    }

    @Override
    public void consultarAfiliadoExistenteDatosBasicos(AfiliadosBean bean) {
        String mensaje = "";
        if (bean.isAccionCreacion()) {
            mensaje = validarAfiliadoExistenteDatosBasicos(bean.getObjeto());
        } else {
            mensaje = validarAfiliadoExistenteDatosBasicosEditar(bean.getObjeto(), bean.getObjetoAnterior());
        }
        // actualizamos el mensaje en el objeto
        bean.setMensajeDatosBasicos(mensaje);
    }

    private String validarAfiliadoExistenteDatosBasicos(AsegAfiliado objeto) {
        String mensaje;
        AsegAfiliado afiliadoConsulta;
        try {
            //2020-07-24 jyperez Se ajusta la consulta para adicionar los campos segundoApellido y SegundoNombre, solicitados en el inc 260
            afiliadoConsulta = getAfiliadoRemoto().consultar(objeto.getPrimerApellido(), objeto.getSegundoApellido(), objeto.getPrimerNombre(), objeto.getSegundoNombre(), objeto.getFechaNacimiento());
            if (afiliadoConsulta != null
                    && afiliadoConsulta.getId() != null) {
                mensaje = "Existe un afiliado registrado con los datos ingresados: Primer Apellido, Segundo Apellido, Primer Nombre, Segundo Nombre, Fecha de Nacimiento";
            } else {
                mensaje = "";
            }
        } catch (Exception ex) {
            mensaje = "Ocurrió un error consultando afliado Existente. Mensaje: " + ex.getMessage();
        }
        return mensaje;
    }

    private String validarAfiliadoExistenteDatosBasicosEditar(AsegAfiliado objeto, AsegAfiliado objetoAnterior) {
        String mensaje = "";
        AsegAfiliado afiliadoConsulta;
        String segundoApellido = "";
        String segundoApellidoAnterior = "";
        String segundoNombre = "";
        String segundoNombreAnterior = "";

        // 2020-08-25 jyperez INC 281 erro con valores nulos en los campos segundo nombre y segundo apellido
        if (objeto.getSegundoApellido() != null) {
            segundoApellido = objeto.getSegundoApellido();
        }
        if (objetoAnterior.getSegundoApellido() != null) {
            segundoApellidoAnterior = objetoAnterior.getSegundoApellido();
        }
        if (objeto.getSegundoNombre() != null) {
            segundoNombre = objeto.getSegundoNombre();
        }
        if (objetoAnterior.getSegundoNombre() != null) {
            segundoNombreAnterior = objetoAnterior.getSegundoNombre();
        }
        try {
            //validamos que sus datos básicos hayan cambiado para ejecutar la consulta
            //2020-07-24 jyperez Se ajusta la consulta para adicionar los campos segundoApellido y SegundoNombre, solicitados en el inc 260
            if (!objeto.getPrimerApellido().equals(objetoAnterior.getPrimerApellido())
                    || !segundoApellido.equals(segundoApellidoAnterior)
                    || !objeto.getPrimerNombre().equals(objetoAnterior.getPrimerNombre())
                    || !segundoNombre.equals(segundoNombreAnterior)
                    || !(objeto.getFechaNacimiento().compareTo(objetoAnterior.getFechaNacimiento()) == 0)) {
                //2020-07-24 jyperez Se ajusta la consulta para adicionar los campos segundoApellido y SegundoNombre, solicitados en el inc 260
                afiliadoConsulta = getAfiliadoRemoto().consultar(objeto.getPrimerApellido(), objeto.getSegundoApellido(), objeto.getPrimerNombre(), objeto.getSegundoNombre(), objeto.getFechaNacimiento());
                if (afiliadoConsulta != null
                        && afiliadoConsulta.getId() != null
                        && afiliadoConsulta.getId().compareTo(objeto.getId()) != 0) {
                    mensaje = "Existe un afiliado registrado con los datos ingresados: Primer Apellido, Segundo Apellido, Primer Nombre, Segundo Nombre, Fecha de Nacimiento";
                } else {
                    mensaje = "";
                }
            }
        } catch (Exception ex) {
            mensaje = "Ocurrió un error consultando afliado Existente por Datos Básicos. Mensaje: " + ex.getMessage();
        }
        return mensaje;
    }

    public String validarMensajeNulo(String mensaje) {
        if (mensaje == null) {
            mensaje = "";
        }
        return mensaje;
    }

    /*public HashMap<Integer, Maestro> convertToHash(List<Maestro> list) {
        HashMap<Integer, Maestro> map = new HashMap<>();
        for (Maestro i : list) {
            map.put(i.getId(), i);
        }
        return map;
    }
    
    public HashMap<String, Maestro> convertToHashValor(List<Maestro> list) {
        HashMap<String, Maestro> map = new HashMap<>();
        for (Maestro i : list) {
            map.put(i.getValor(), i);
        }
        return map;
    }*/
    public void generarCertificadoAfiliacion(AfiliadosBean bean) {
        // obtenemos los datos del afiliado
        AsegAfiliadoCertificado obj = new AsegAfiliadoCertificado();
        Maestro maestro;
        CertificadoAfiliacion certificado = new CertificadoAfiliacion();
        List<CertificadoAfiliacion> listaCertificado = new ArrayList();
        Date fechaActual = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdfHoraCert = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            AsegAfiliado afiliado = getAfiliadoRemoto().consultar(bean.getCertificadoAfiliacion().getIdAfiliado());
            if (afiliado != null) {
                //con los datos del afiliado llenamos el objeto del afiliado certificado
                obj.setIdAfiliado(afiliado.getIdAfiliado());
                obj.setMaeTipoDocumentoId(afiliado.getMaeTipoDocumento());
                //traer del maestro
                maestro = bean.getHashTiposDocumento().get(afiliado.getMaeTipoDocumento());
                if (maestro != null) {
                    obj.setMaeTipoDocumentoCodigo(maestro.getValor());
                    obj.setMaeTipoDocumentoValor(maestro.getNombre());
                }
                obj.setNumeroDocumento(afiliado.getNumeroDocumento());
                obj.setPrimerNombre(afiliado.getPrimerNombre());
                obj.setSegundoNombre(afiliado.getSegundoNombre());
                obj.setPrimerApellido(afiliado.getPrimerApellido());
                obj.setSegundoApellido(afiliado.getSegundoApellido());
                obj.setFechaNacimiento(afiliado.getFechaNacimiento());
                obj.setMaeSubgrupoSisbenId(afiliado.getMaeGrupoSisbenId());
                //maestro
                maestro = bean.getHashSubGrupoSisben().get(afiliado.getMaeGrupoSisbenId());
                if (maestro != null) {
                    obj.setMaeSubgrupoSisbenCodigo(maestro.getValor());
                    obj.setMaeSubgrupoSisbenValor(maestro.getNombre());
                }
                if (afiliado.getMaeNivelSisbenId() != null) {
                    obj.setMaeNivelId(afiliado.getMaeNivelSisbenId());
                    obj.setMaeNivelCodigo(afiliado.getMaeNivelSisbenCodigo());
                    obj.setMaeNivelValor(afiliado.getMaeNivelSisbenValor());
                } else {
                    obj.setMaeNivelId(0);
                    obj.setMaeNivelCodigo(afiliado.getNivelSisben());
                    obj.setMaeNivelValor(afiliado.getNivelSisben());
                }

                obj.setMaeGrupoPoblacionalId(afiliado.getMaeGrupoPoblacional());
                //maestro
                maestro = bean.getHashGrupoPoblacional().get(afiliado.getMaeGrupoPoblacional());
                if (maestro != null) {
                    obj.setMaeGrupoPoblacionalCodigo(maestro.getValor());
                    obj.setMaeGrupoPoblacionalValor(maestro.getNombre());
                }
                obj.setFechaAfiliacion(afiliado.getFechaAfiliacionEps());
                if (afiliado.getMaeRegimenId() != null) {
                    obj.setMaeRegimenId(afiliado.getMaeRegimenId());
                    obj.setMaeRegimenDescripcion(afiliado.getMaeRegimenValor());
                    obj.setMaeRegimenValor(afiliado.getMaeRegimenCodigo());
                } else {
                    obj.setMaeRegimenId(0);
                    obj.setMaeRegimenDescripcion(afiliado.getRegimen());
                    obj.setMaeRegimenValor(afiliado.getRegimen());
                }

                obj.setMaeEstadoAfiliacionId(afiliado.getMaeEstadoAfiliacion());
                //maestro
                maestro = bean.getHashEstadosAfiliacion().get(afiliado.getMaeEstadoAfiliacion());
                if (maestro != null) {
                    obj.setMaeEstadoAfiliacionCodigo(maestro.getValor());
                    obj.setMaeEstadoAfiliacionValor(maestro.getNombre());
                }
                try {
                    obj.setModeloLiquidacion(Integer.parseInt(afiliado.getModeloLiquidacion()));
                } catch (Exception e) {
                    obj.setModeloLiquidacion(0);
                }
                obj.setFechaRetiro(afiliado.getFechaEgresoEps());
                obj.setSemanaAfiliacion(calcularDiferenciaSemanas(afiliado.getFechaAfiliacionEps(), fechaActual));
                obj.setDireccionAfiliado(afiliado.getDireccionResidencia());
                obj.setTelefonoAfiliado(afiliado.getTelefonoFijo());
                obj.setCelularAfiliado(afiliado.getTelefonoMovil());
                obj.setAfiliacionUbicacionId(afiliado.getAfiliacionUbicacion().getId());
                obj.setAfiliacionUbicacionValor(afiliado.getAfiliacionUbicacion().getNombre());
                obj.setResidenciaUbicacionId(afiliado.getResidenciaUbicacion().getId());
                obj.setResidenciaUbicacionValor(afiliado.getResidenciaUbicacion().getNombre());
                obj.setCntPrestadorSedesId(afiliado.getPrimariaPrestadorSede().getId());
                obj.setCntPrestadorSedesValor(afiliado.getPrimariaPrestadorSede().getNombreSede());
                obj.setCorreoElectronico(afiliado.getEmail());
                obj.setTipo(AfiliadoParametro.TIPO_CERTIFICADO_AFILIACION);
                obj.setFechaInicioVigencia(fechaActual);
                obj.setFechaFinVigencia(sumarDiasFecha(fechaActual, AfiliadoParametro.CERTIFICADO_DIAS_VIGENCIA));
                obj.setDiasVigencia(AfiliadoParametro.CERTIFICADO_DIAS_VIGENCIA);
                //PropApl propiedades = new PropApl();
                obj.setNombreArchivo("CA" + "_" + obj.getMaeTipoDocumentoCodigo() + obj.getNumeroDocumento() + sdfHoraCert.format(fechaActual) + ".pdf");
                obj.setRuta(PropApl.getInstance().get(PropApl.RUTA_ASEGURAMIENTO_CERTIFICADOS));
                obj.setAsegAfiliado(afiliado);
                // auditoria
                bean.auditoriaGuardar(obj);
                // guardamos el registro del certificado
                obj.setId(getAfiliadoCertificadoRemoto().insertar(obj));
                //y procedemos a generar el certificado para impresión
                if (obj.getId() != null) {
                    certificado.setId(obj.getId());
                } else {
                    // lanzar excepción con la no generación del registro
                    throw new Exception("No se almacenó el Certicado del Afiliado en la Base de Datos.");
                }
                certificado.setNombreArchivo(obj.getNombreArchivo());
                certificado.setRuta(obj.getRuta());
                certificado.setStrCiudad(obj.getResidenciaUbicacionValor());
                certificado.setStrDocumentoCompleto(obj.getMaeTipoDocumentoValor() + " " + obj.getNumeroDocumento());
                certificado.setStrEstado(obj.getMaeEstadoAfiliacionValor());
                certificado.setStrFechaAfiliacion(sdf.format(obj.getFechaAfiliacion()));
                certificado.setStrFechaGeneracion(sdfHora.format(fechaActual));
                if (obj.getFechaRetiro() != null) {
                    certificado.setStrFechaRetiro(sdf.format(obj.getFechaRetiro()));
                } else {
                    certificado.setStrFechaRetiro("");
                }
                certificado.setStrModelo(bean.getModeloLiquidacion("" + obj.getModeloLiquidacion()));
                certificado.setStrNivelSisben(obj.getMaeNivelValor());
                String nombreCompleto = "";
                nombreCompleto = obj.getPrimerNombre();
                if (obj.getSegundoNombre() != null && !obj.getSegundoNombre().trim().equals("")) {
                    nombreCompleto = nombreCompleto + " " + obj.getSegundoNombre();
                }
                nombreCompleto = nombreCompleto + " " + obj.getPrimerApellido();
                if (obj.getSegundoApellido() != null && !obj.getSegundoApellido().trim().equals("")) {
                    nombreCompleto = nombreCompleto + " " + obj.getSegundoApellido();
                }
                certificado.setStrNombreCompleto(nombreCompleto);
                certificado.setStrRegimen(obj.getMaeRegimenDescripcion());
                certificado.setStrSemanas("" + obj.getSemanaAfiliacion());
                //2021-05-21 jyperez adición nuevos campos
                certificado.setStrId("" + obj.getId());
                //2022-08-10 jyperez INC 0001275 se cambia para tomar el valor del grupo del afiliado. LA tabla AsegAfiliadoCertificado no lo tiene
                certificado.setStrGrupoSisben(afiliado.getMaeGrupoSisbenValor());
                //2025-05-13 jyperez adición nuevo campo categoría IBC. Si el regimen es SUBSIDIADO, ponemos "NO APLICA"
                if (obj.getMaeRegimenValor() != null && obj.getMaeRegimenValor().equals(AfiliadoParametro.REGIMEN_CONTRIBUTIVO)) {
                    certificado.setStrCategoriaIBC(afiliado.getMaeCategoriaIbcValor());
                } else {
                    certificado.setStrCategoriaIBC(AfiliadoParametro.CATEGORIA_IBC_NO_APLICA);
                }
                bean.setCertificadoAfiliacion(certificado);
                listaCertificado.add(certificado);
                // Generar report para guardarlo en la ruta.
                //Estructura de JasperReport
                InputStream is = getClass().getResourceAsStream("/reportes/Certificado_Afiliacion.jasper");
                JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(listaCertificado);
                Map parameters = new HashMap();
                parameters.put(JRParameter.REPORT_LOCALE, new Locale("es", "CO"));
                byte[] bytes = JasperRunManager.runReportToPdf(is, parameters, beanColDataSource);
                InputStream streamArchivo = new ByteArrayInputStream(bytes);
                InputStream streamImpresion = new ByteArrayInputStream(bytes);
                bean.setAdjuntoStream(streamArchivo);
                //el certificado debe guardarse en la ruta.Tener en cuenta si se almacena entonces el flujo de bytes para pasarselo 
                generarArchivoCertificado(bean, streamImpresion);
                // al streamedContent y no generarlo en el bean. De igual forma necesitamos almacenarlo en ruta.
            }

        } catch (Exception e) {
            bean.addError("Ocurrió un error en el proceso de Generación del Certificado: " + e.getMessage());
        }

    }
    
    public void generarCertificadoContactoAfiliado(AfiliadosBean bean) {
        CertificadoContactoAfiliado certificado = new CertificadoContactoAfiliado();
        List<CertificadoContactoAfiliado> listaCertificado = new ArrayList();
        Date fechaActual = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdfHoraCert = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            AsegAfiliado afiliado = getAfiliadoRemoto().consultar(bean.getObjeto().getIdAfiliado());
            if (afiliado != null) {
                //datos de donde se almacenaria el certificados (Por ahora solo usaremos la generación del nombre
                certificado.setNombreArchivo("CCA" + "_" + afiliado.getMaeTipoDocumentoCodigo() + afiliado.getNumeroDocumento() + sdfHoraCert.format(fechaActual) + ".pdf");
                certificado.setRuta(PropApl.getInstance().get(PropApl.RUTA_ASEGURAMIENTO_CERTIFICADOS));
                certificado.setStrId(afiliado.getIdAfiliado());
                certificado.setStrNombreSolicitante(bean.getNombreSolicitante());
                certificado.setStrCargoSolicitante(bean.getCargoSolicitante());
                certificado.setStrEnteControlSolicitante(bean.getEnteControlSolicitante());
                certificado.setStrTipoDocumento(afiliado.getMaeTipoDocumentoCodigo());
                certificado.setStrDocumento(afiliado.getNumeroDocumento());
                certificado.setStrNombreCompleto(afiliado.getNombreCompleto());
                certificado.setStrEstado(afiliado.getMaeEstadoAfiliacionValor());
                certificado.setStrRegimen(afiliado.getMaeRegimenValor());
                certificado.setStrDireccion(afiliado.getDireccionResidencia());
                certificado.setStrBarrio(afiliado.getBarrio());
                certificado.setStrEmail(afiliado.getEmail());
                //certificado.setStrFechaGeneracion(sdf.format(fechaActual));
                certificado.setStrFechaGeneracion(obtenerFechaTexto(fechaActual));
                //Telefonos
                String telefonos = "";
                if (afiliado.getListaAsegAfiliadoContacto() != null && afiliado.getListaAsegAfiliadoContacto().size() > 0) {
                    int size = afiliado.getListaAsegAfiliadoContacto().size();
                    int i = 1;
                    for (AsegAfiliadoContacto ac: afiliado.getListaAsegAfiliadoContacto()) {
                        telefonos += ac.getNumeroContacto();
                        if (i != size) {
                            telefonos += " - ";
                        }
                        i++;
                    }
                } else {
                    throw new Exception("No se ha encontrado contactos del afiliado.");
                }
                certificado.setStrTelefonos(telefonos);
                //Departamento y Ciudad
                if (afiliado.getResidenciaUbicacion().getUbicacionPadre() != null) {
                    certificado.setStrDepartamento(afiliado.getResidenciaUbicacion().getUbicacionPadre().getNombre());
                } else {
                    certificado.setStrDepartamento("");
                }
                certificado.setStrCiudad(afiliado.getResidenciaUbicacion().getNombre());
                //Prestador
                certificado.setStrPrestador(afiliado.getPrimariaPrestadorSede().getNombreSede());
                listaCertificado.add(certificado);
                bean.setCertificadoContactoAfiliado(certificado);
                // Generar report para guardarlo en la ruta.
                //Estructura de JasperReport
                InputStream is = getClass().getResourceAsStream("/reportes/certificado_contacto_afiliado.jasper");
                JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(listaCertificado);
                Map parameters = new HashMap();
                parameters.put(JRParameter.REPORT_LOCALE, new Locale("es", "CO"));
                byte[] bytes = JasperRunManager.runReportToPdf(is, parameters, beanColDataSource);
                InputStream streamArchivo = new ByteArrayInputStream(bytes);
                InputStream streamImpresion = new ByteArrayInputStream(bytes);
                bean.setAdjuntoStream(streamArchivo);
                //POR ahora no se guardará: el certificado debe guardarse en la ruta.Tener en cuenta si se almacena entonces el flujo de bytes para pasarselo 
                //generarArchivoCertificado(bean, streamImpresion);
                // al streamedContent y no generarlo en el bean. De igual forma necesitamos almacenarlo en ruta.
            }

        } catch (Exception e) {
            bean.addError("Ocurrió un error en el proceso de Generación del Certificado de Contactos: " + e.getMessage());
        }

    }
    
    public void generarFormularioAfiliacion(AfiliadosBean bean) {
        FormularioAfiliadoRes1823 certificado = new FormularioAfiliadoRes1823();
        List<FormularioAfiliadoRes1823> listaCertificado = new ArrayList();
        Date fechaActual = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdfHoraCert = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            AsegAfiliado afiliado = getAfiliadoRemoto().consultar(bean.getObjeto().getId());
            if (afiliado != null) {
                //datos de donde se almacenaria el certificados (Por ahora solo usaremos la generación del nombre
                certificado.setNombreArchivo("FA" + "_" + afiliado.getMaeTipoDocumentoCodigo() + afiliado.getNumeroDocumento() + sdfHoraCert.format(fechaActual) + ".pdf");
                certificado.setRuta(PropApl.getInstance().get(PropApl.RUTA_ASEGURAMIENTO_CERTIFICADOS));
                certificado.setStrId(afiliado.getIdAfiliado());
                //2024-12-05 jyperez PENDIENTE NRO RADICADO de donde lo obtendríamos
                //2024-12-11 jyperez obtenemos el valor mas reciente de la lista de asegRadicadoNovedades
                if (afiliado.getListaAsegRadicadoNovedades() != null) {
                    Date fechaRadicado = new Date();
                    int i = 0;
                    for (AsegRadicadoNovedad aux: afiliado.getListaAsegRadicadoNovedades()) {
                        if (i == 0) {
                            i++;
                            fechaRadicado = aux.getFechaHoraCrea();
                            certificado.setStrNoRadicado(aux.getId().toString());
                        } else if (aux.getFechaHoraCrea().after(fechaRadicado)) {
                            fechaRadicado = aux.getFechaHoraCrea();
                            certificado.setStrNoRadicado(aux.getId().toString());
                        }
                    }
                } else {
                    certificado.setStrNoRadicado("");
                }
                //obtenemos la fecha actual y la dividimos para obtener cada letra
                String [] fechaFomulario = obtenerFechaEnLetras(fechaActual);
                if(fechaFomulario != null) {
                    try {
                        certificado.setStrDiaLetra1(fechaFomulario[0]);
                        certificado.setStrDiaLetra2(fechaFomulario[1]);
                        certificado.setStrMesLetra1(fechaFomulario[2]);
                        certificado.setStrMesLetra2(fechaFomulario[3]);
                        certificado.setStrAnioLetra1(fechaFomulario[4]);
                        certificado.setStrAnioLetra2(fechaFomulario[5]);
                        certificado.setStrAnioLetra3(fechaFomulario[6]);
                        certificado.setStrAnioLetra4(fechaFomulario[7]);
                        } catch(Exception e) {
                            certificado.setStrDiaLetra1("");
                            certificado.setStrDiaLetra2("");
                            certificado.setStrMesLetra1("");
                            certificado.setStrMesLetra2("");
                            certificado.setStrAnioLetra1("");
                            certificado.setStrAnioLetra2("");
                            certificado.setStrAnioLetra3("");
                            certificado.setStrAnioLetra4("");
                        }
                }
                //por defecto configuramos tipo afiliación
                certificado.setStrTipoTramite(AfiliadoParametro.TIPO_TRAMITE_AFILIACION);
                certificado.setStrTipodeAfiliacion(AfiliadoParametro.TIPO_AFILIACION_INDIVIDUAL_COTIZANTE);
                certificado.setStrRegimen(afiliado.getMaeRegimenCodigo());
                if (afiliado.getAceptaContribucionSolidaria()!= null && afiliado.getAceptaContribucionSolidaria()) {
                    certificado.setStrContribucionSolidaria("1");
                } else {
                    certificado.setStrContribucionSolidaria("0");
                }
                certificado.setStrTipoAfiliado(afiliado.getMaeTipoAfiliadoCodigo());
                //POR DEFECTO COMO EL SUBSIDIADO NO COTIZA MANDAMOS VACIO
                certificado.setStrTipoCotizante("");
                //CODIGO A REGISTRAR POR LA EPS
                certificado.setStrCodigo("");
                certificado.setStrPrimerApellido(afiliado.getPrimerApellido());
                certificado.setStrSegundoApellido(afiliado.getSegundoApellido());
                certificado.setStrPrimerNombre(afiliado.getPrimerNombre());
                certificado.setStrSegundoNombre(afiliado.getSegundoNombre());
                certificado.setStrNumeroDocumento(afiliado.getNumeroDocumento());
                certificado.setStrTipoDocumento(afiliado.getMaeTipoDocumentoCodigo());
                certificado.setStrSexoBiologico(afiliado.getMaeGeneroCodigo());
                certificado.setStrSexoIdentificacion(afiliado.getMaeGeneroIdentificacionCodigo());
                certificado.setStrNacionalidad(afiliado.getPaisNacionalidadStr());
                certificado.setStrPais(afiliado.getPaisNacimientoStr());
                if (afiliado.getGnUbicacionesLugarNacimientoId() != null) {
                    if ( afiliado.getGnUbicacionesLugarNacimientoId().getUbicacionPadre() != null) {
                        certificado.setStrDepartamento(afiliado.getGnUbicacionesLugarNacimientoId().getUbicacionPadre().getNombre());
                    }
                    certificado.setStrMunicipio(afiliado.getGnUbicacionesLugarNacimientoId().getNombre());
                }
                String [] fechaNacimiento = obtenerFechaEnLetras(afiliado.getFechaNacimiento());
                if(fechaNacimiento != null) {
                    try {
                        certificado.setStrNacimientoDiaLetra1(fechaNacimiento[0]);
                        certificado.setStrNacimientoDiaLetra2(fechaNacimiento[1]);
                        certificado.setStrNacimientoMesLetra1(fechaNacimiento[2]);
                        certificado.setStrNacimientoMesLetra2(fechaNacimiento[3]);
                        certificado.setStrNacimientoAnioLetra1(fechaNacimiento[4]);
                        certificado.setStrNacimientoAnioLetra2(fechaNacimiento[5]);
                        certificado.setStrNacimientoAnioLetra3(fechaNacimiento[6]);
                        certificado.setStrNacimientoAnioLetra4(fechaNacimiento[7]);
                        } catch(Exception e) {
                            certificado.setStrNacimientoDiaLetra1("");
                            certificado.setStrNacimientoDiaLetra2("");
                            certificado.setStrNacimientoMesLetra1("");
                            certificado.setStrNacimientoMesLetra2("");
                            certificado.setStrNacimientoAnioLetra1("");
                            certificado.setStrNacimientoAnioLetra2("");
                            certificado.setStrNacimientoAnioLetra3("");
                            certificado.setStrNacimientoAnioLetra4("");
                        }
                }
                certificado.setStrEtnia(afiliado.getMaeEtniaValor());
                certificado.setStrComunidad(afiliado.getMaeComunidadEtniaValor());
                certificado.setStrDiscapacidad( afiliado.isDiscapacidad() == true? "1":"0" );
                certificado.setStrCategoriaDiscapacidad(afiliado.getMaeCondicionDiscapacidadValor());
                // PENDIENTE como sé si tiene encuesta
                certificado.setStrEncuestaSisben("0");
                certificado.setStrNivelSisben(afiliado.getMaeNivelSisbenValor());
                certificado.setStrGrupoSisben(afiliado.getMaeGrupoSisbenValor());
                // PENDIENTE como sé si es población especial
                certificado.setStrGrupoPoblacionEspecial("");
                certificado.setStrARL(afiliado.getMaeArlValor());
                certificado.setStrAdminPension(afiliado.getMaeAfpValor());
                certificado.setStrIBC(afiliado.getMaeCategoriaIbcValor());
                certificado.setStrTarifaContribucionSolidaria(afiliado.getValorContribucionSolidariaStrFrmMoneda());
                certificado.setStrDireccion(afiliado.getDireccionResidencia());
                certificado.setStrCorreoElectronico(afiliado.getEmail());
                //certificado.setStrFechaGeneracion(sdf.format(fechaActual));
                //Telefonos
                String telefonos = "";
                if (afiliado.getListaAsegAfiliadoContacto() != null && afiliado.getListaAsegAfiliadoContacto().size() > 0) {
                    for (AsegAfiliadoContacto ac: afiliado.getListaAsegAfiliadoContacto()) {
                        if (ac.getMaeTipoContactoCodigo().equals(AfiliadoParametro.TIPO_CONTACTO_TELEFONO)) {
                            certificado.setStrTelefonoFijo(ac.getNumeroContacto());
                        } else if (ac.getMaeTipoContactoCodigo().equals(AfiliadoParametro.TIPO_CONTACTO_CELULAR)) {
                            certificado.setStrTelefonoCelular(ac.getNumeroContacto());
                        }
                    }
                }/* else {
                    throw new Exception("No se ha encontrado contactos del afiliado.");
                }*/
                //Departamento y Ciudad
                if (afiliado.getResidenciaUbicacion().getUbicacionPadre() != null) {
                    certificado.setStrDepartamentoResidencia(afiliado.getResidenciaUbicacion().getUbicacionPadre().getNombre());
                } else {
                    certificado.setStrDepartamentoResidencia("");
                }
                certificado.setStrMunicipioResidencia(afiliado.getResidenciaUbicacion().getNombre());
                //PENDIENTE nosotros no tenemos definido este campo en ubicación
                certificado.setStrLocalidadResidencia("");
                certificado.setStrZona(afiliado.getMaeZonaCodigo());
                //Campos del conyuge no se identifican actualmente en afiliados
                
                listaCertificado.add(certificado);
                bean.setFormularioAfiliadoRes1823(certificado);
                // Generar report para guardarlo en la ruta.
                //Estructura de JasperReport
                //2024-12-03 prueba de imprimir dos jasper pegados en el mismo flujo
                List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
                //InputStream is = getClass().getResourceAsStream("/reportes/certificado_contacto_afiliado.jasper");
                JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(listaCertificado);
                JRBeanCollectionDataSource beanColDataSource1 = new JRBeanCollectionDataSource(listaCertificado);
                Map parameters = new HashMap();
                parameters.put(JRParameter.REPORT_LOCALE, new Locale("es", "CO"));
                Map parameters1 = new HashMap();
                parameters1.put(JRParameter.REPORT_LOCALE, new Locale("es", "CO"));
                JasperPrint reporte1 = JasperFillManager.fillReport(getClass().getResourceAsStream("/reportes/certificado_afiliacion_hoja1_v2.0.jasper"),parameters,beanColDataSource);
                jasperPrints.add(reporte1);
                JasperPrint reporte2 = JasperFillManager.fillReport(getClass().getResourceAsStream("/reportes/certificado_afiliacion_hoja2_v2.0.jasper"),parameters1,beanColDataSource1);
                jasperPrints.add(reporte2);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                JRPdfExporter exporter = new JRPdfExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrints);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
                exporter.exportReport();
                byte[] bytes = out.toByteArray();
                //byte[] bytes = JasperRunManager.runReportToPdf(is, parameters, beanColDataSource);
                InputStream streamArchivo = new ByteArrayInputStream(bytes);
                //InputStream streamImpresion = new ByteArrayInputStream(bytes);
                bean.setAdjuntoStream(streamArchivo);
                //POR ahora no se guardará: el certificado debe guardarse en la ruta.Tener en cuenta si se almacena entonces el flujo de bytes para pasarselo 
                //generarArchivoCertificado(bean, streamImpresion);
                // al streamedContent y no generarlo en el bean. De igual forma necesitamos almacenarlo en ruta.
            }

        } catch (Exception e) {
            bean.addError("Ocurrió un error en el proceso de Generación del Formulario de Afiliación: " + e.getMessage());
        }

    }
    
    public static String[] obtenerFechaEnLetras(Date fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyy");
        String fechaString = formato.format(fecha);
        String[] letras = new String[fechaString.length()];
        for (int i = 0; i < fechaString.length(); i++) {
            letras[i] = String.valueOf(fechaString.charAt(i));
        } return letras; 
    }
    
    private String obtenerFechaTexto(Date fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //Define formato default de entrada.   
        String input = "yyyy-MM-dd";
        //Define formato default de salida.
        String output = "EEEEE d 'de' MMMM 'del' yyyy";
      String outputDate = sdf.format(fecha);
      try {
          outputDate = new SimpleDateFormat(output, AfiliadoParametro.LOCALE_CO).format(new SimpleDateFormat(input, AfiliadoParametro.LOCALE_CO).parse(outputDate));
      } catch (ParseException e) {
          System.out.println("obtenerFechaTexto(): " + e.getMessage());           
      }
      return outputDate;
    }

    private boolean generarArchivoCertificado(AfiliadosBean bean, InputStream impresion) throws Exception {
        boolean esArchivoGuardado = false;
        OutputStream destino = null;
        try {
            File archivo = new File(bean.getCertificadoAfiliacion().getRuta(), bean.getCertificadoAfiliacion().getNombreArchivo());
            destino = new FileOutputStream(archivo);
            IOUtils.copy(impresion, destino);
            IOUtils.closeQuietly(impresion);
            IOUtils.closeQuietly(destino);
            esArchivoGuardado = true;
        } catch (FileNotFoundException ex) {
            throw new Exception("Error al subir un adjunto " + ex.getMessage());
        } catch (IOException ex) {
            throw new Exception("Error al subir un adjunto " + ex.getMessage());
        } finally {
            try {
                destino.close();
            } catch (IOException ex) {
                Logger.getLogger(AfiliadosServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return esArchivoGuardado;
    }

    public static int calcularDiferenciaSemanas(Date fechaInicio, Date fechaFin) {
        int semanas = 0;
        Calendar fechaCalInicio = Calendar.getInstance();
        Calendar fechaCalActual = Calendar.getInstance();
        // cargamos la fecha a evaluar
        fechaCalInicio.setTime(fechaInicio);
        fechaCalActual.setTime(fechaFin);
        long ms1 = fechaCalInicio.getTimeInMillis();
        long ms2 = fechaCalActual.getTimeInMillis();
        // Cálculo de las diferencias.
        long difMs = ms2 - ms1;
        long msPorWeek = 1000 * 60 * 60 * 24 * 7;
        semanas = (int) (difMs / msPorWeek);
        return semanas;
    }

    public static Date sumarDiasFecha(Date fechaActual, int dias) {
        Calendar calendar = Calendar.getInstance();
        //le sumamos los días definidos a la fecha actual
        calendar.setTime(fechaActual);
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        return calendar.getTime();
    }

    @Override
    public void consultarMaestroTipoAfiliadoPorRegimen(AfiliadosBean bean) {
        try {
            bean.setListaTipoAfiliado(getMaestroRemoto().consultarListaPorPadre(MaestroTipo.ASEG_AFILIADO_TIPO, bean.getObjeto().getMaeRegimenId()));
            bean.setHashTipoAfiliado(Util.convertToHash(bean.getListaTipoAfiliado()));
        } catch (Exception ex) {
            //cargamos las listas vacias si ocurre algun error.
            bean.setListaTipoAfiliado(new ArrayList<>());
            bean.setHashTipoAfiliado(new HashMap());
        }
    }

    @Override
    public void consultarMaestroParentescoPorRegimen(AfiliadosBean bean) {
        try {
            bean.setListaParentescoCotizante(getMaestroRemoto().consultarListaPorPadre(MaestroTipo.ASEG_COTIZANTE_PARENTESCO, bean.getObjeto().getMaeRegimenId()));
            bean.setHashParentescoCotizante(Util.convertToHash(bean.getListaParentescoCotizante()));
        } catch (Exception ex) {
            //cargamos las listas vacias si ocurre algun error.
            bean.setListaParentescoCotizante(new ArrayList<>());
            bean.setHashParentescoCotizante(new HashMap());
        }
    }

    @Override
    public void consultarMaestroEtniaPorGrupoPoblacional(AfiliadosBean bean) {
        try {
            bean.setListaEtnia(getMaestroRemoto().consultarListaPorPadre(MaestroTipo.GN_ETNIA, bean.getObjeto().getMaeGrupoPoblacional()));
            bean.setHashEtnia(Util.convertToHash(bean.getListaEtnia()));
        } catch (Exception ex) {
            //cargamos las listas vacias si ocurre algun error.
            bean.setListaEtnia(new ArrayList<>());
            bean.setHashEtnia(new HashMap());
        }
    }

    @Override
    public void consultarMaestroGrupoSisbenPorGrupoPoblacional(AfiliadosBean bean) {
        try {
            bean.setListaGrupoSisben(getMaestroRemoto().consultarListaPorPadre(MaestroTipo.ASEG_SISBEN_CATEGORIA, bean.getObjeto().getMaeGrupoPoblacional()));
            bean.setHashGrupoSisben(Util.convertToHash(bean.getListaGrupoSisben()));
        } catch (Exception ex) {
            //cargamos las listas vacias si ocurre algun error.
            bean.setListaGrupoSisben(new ArrayList<>());
            bean.setHashGrupoSisben(new HashMap());
        }
    }

    @Override
    public void consultarMaestroNivelSisbenPorGrupoPoblacionalYGrupoSisben(AfiliadosBean bean) {
        try {
            //2022-04-25 jyperez se actualiza el nuevo padre grupo a evaluar.
            if (bean.getObjeto().getMaeGrupoSisbenId() != null) {
                bean.setListaNivelSisbenNuevo(getMaestroRemoto().consultarListaPorPadre(MaestroTipo.ASEG_SISBEN_NIVEL, bean.getObjeto().getMaeGrupoPoblacional(), bean.getObjeto().getMaeGrupoSisbenId()));
            } else {
                bean.setListaNivelSisbenNuevo(getMaestroRemoto().consultarListaPorPadre(MaestroTipo.ASEG_SISBEN_NIVEL, bean.getObjeto().getMaeGrupoPoblacional()));
            }
            bean.setHashNivelSisbenNuevo(Util.convertToHash(bean.getListaNivelSisbenNuevo()));
            bean.setHashNivelSisben(Util.convertToHash(bean.getListaNivelSisbenNuevo()));
            bean.setHashValorNivelSisbenNuevo(Util.convertToHashValor(bean.getListaNivelSisbenNuevo()));
        } catch (Exception ex) {
            //cargamos las listas vacias si ocurre algun error.
            bean.setListaNivelSisbenNuevo(new ArrayList<>());
            bean.setHashNivelSisbenNuevo(new HashMap());
            bean.setHashNivelSisben(new HashMap());
            bean.setHashValorNivelSisbenNuevo(new HashMap());
        }
    }

    @Override
    public void consultarMaestroEstadoPorEstadoYRegimen(AfiliadosBean bean) {
        try {
            //hay otro ListaEstadosAfiliacionListar que se usa en el buscar
            bean.setListaEstadosAfiliacion(getMaestroRemoto().consultarListaPorPadre(MaestroTipo.ASEG_ESTADO_AFILIACION, bean.getObjeto().getMaeEstadoAfiliacion(), bean.getObjeto().getMaeRegimenId()));
            bean.setHashEstadosAfiliacion(Util.convertToHash(bean.getListaEstadosAfiliacion()));
        } catch (Exception ex) {
            //cargamos las listas vacias si ocurre algun error.
            bean.setListaEstadosAfiliacion(new ArrayList<>());
            bean.setHashEstadosAfiliacion(new HashMap());
        }
    }

    @Override
    public void consultarMaestroCausaNovedadPorEstado(AfiliadosBean bean) {
        try {
            bean.setListaCausaNovedad(getMaestroRemoto().consultarListaPorPadre(MaestroTipo.ASEG_CAUSA_NOVEDAD, bean.getObjeto().getMaeEstadoAfiliacion()));
            bean.setHashCausaNovedad(Util.convertToHash(bean.getListaCausaNovedad()));
            bean.setHashCausaNovedadValor(Util.convertToHashValor(bean.getListaCausaNovedad()));
        } catch (Exception ex) {
            //cargamos las listas vacias si ocurre algun error.
            bean.setListaCausaNovedad(new ArrayList<>());
            bean.setHashCausaNovedad(new HashMap());
            bean.setHashCausaNovedadValor(new HashMap());
        }
    }

    @Override
    public void consultarMaestroComunidadEtnicaPorEtnia(AfiliadosBean bean) {
        try {
            bean.setListaComunidadEtnica(getMaestroRemoto().consultarListaPorPadre(MaestroTipo.GN_COMUNIDAD_ETNIA, bean.getObjeto().getMaeEtniaId()));
        } catch (Exception ex) {
            //cargamos las listas vacias si ocurre algun error.
            bean.setListaComunidadEtnica(new ArrayList<>());
            //bean.setHashComunidadEtnica(new HashMap());
        }
    }

    @Override
    public void consultarMaestroMetodGrupoPoblacionalPorGrupoPob(AfiliadosBean bean) {
        try {
            bean.setListaMetodologiaGrupoPoblacional(getMaestroRemoto().consultarListaPorPadre(MaestroTipo.ASEG_METODOLOGIA_GRUPO_POBLACIONAL, bean.getObjeto().getMaeGrupoPoblacional()));
        } catch (Exception ex) {
            bean.setListaMetodologiaGrupoPoblacional(new ArrayList<>());
        }
    }

    @Override
    public void consultarMaestroSubGrupoSisbenPorGrupoSisben(AfiliadosBean bean) {
        try {
            bean.setListaSubGrupoSisben(getMaestroRemoto().consultarListaPorPadre(MaestroTipo.ASEG_SISBEN_SUBCATEGORIA, bean.getObjeto().getMaeGrupoSisbenId()));
            bean.setHashSubGrupoSisben(Util.convertToHash(bean.getListaSubGrupoSisben()));
        } catch (Exception ex) {
            bean.setListaSubGrupoSisben(new ArrayList<>());
            bean.setHashSubGrupoSisben(new HashMap());
        }
    }

    @Override
    public void consultarMaestroPorcentajeDistribucionPorSubGrupoSisben(AfiliadosBean bean) {
        try {
            bean.setListaPorcentajeDistribucion(getMaestroRemoto().consultarListaPorPadre(MaestroTipo.ASEG_SOLIDARIA_PORCENTAJE, bean.getObjeto().getMaeSubGrupoSisbenId()));
            bean.setHashPorcentajeDistribucion(Util.convertToHash(bean.getListaPorcentajeDistribucion()));
            //a pesar de cargar una lista, se entiende que solo hay un único valor. Entonces tomamos el primer valor de la lista para cargar la información al campo
            if (bean.getListaPorcentajeDistribucion() != null && !bean.getListaPorcentajeDistribucion().isEmpty()) {
                for (Maestro var : bean.getListaPorcentajeDistribucion()) {
                    bean.getObjeto().setMaeSolidariaPorcentajeId(var.getId());
                    bean.getObjeto().setMaeSolidariaPorcentajeCodigo(var.getValor());
                    bean.getObjeto().setMaeSolidariaPorcentajeValor(var.getNombre());
                    //2022-07-21 jyperez consultamos tambien los valores asociados al valor asociado al porcentaje
                    consultarValorContribucionSolidaria(bean);
                }
            } else {
                bean.getObjeto().setMaeSolidariaPorcentajeId(null);
                bean.getObjeto().setMaeSolidariaPorcentajeCodigo("");
                bean.getObjeto().setMaeSolidariaPorcentajeValor("");
            }
        } catch (Exception ex) {
            bean.setListaSubGrupoSisben(new ArrayList<>());
            bean.setHashSubGrupoSisben(new HashMap());
            bean.getObjeto().setMaeSolidariaPorcentajeId(null);
            bean.getObjeto().setMaeSolidariaPorcentajeCodigo("");
            bean.getObjeto().setMaeSolidariaPorcentajeValor("");
        }
    }

    @Override
    public void consultarValorContribucionSolidaria(AfiliadosBean bean) {
        BigDecimal valor = new BigDecimal(0);
        if (bean.getObjeto().getMaeSolidariaPorcentajeId() != null) {
            try {
                CsContribucionSolidaria contribucionSolidaria = getContribucionSolidariaRemoto().consultarPorPorcentaje(bean.getObjeto().getMaeSolidariaPorcentajeId());
                if (contribucionSolidaria != null) {
                    valor = contribucionSolidaria.getValor();
                    bean.getObjeto().setValorContribucionSolidaria(valor);
                }
            } catch (Exception ex) {
                bean.getObjeto().setValorContribucionSolidaria(valor);
            }
        }
    }
    
    public void desmarcarAfiliadoDuplicado(AfiliadosBean bean) {
        try {
            //obtenemos la información del afiliado
            bean.setObjeto(getAfiliadoRemoto().consultar(bean.getObjeto().getId()));
            //configuramos los datos anteriores en el histórico antes de hacer cambios
            AsegAfiliadoHistorico afiliadoHistorico = new AsegAfiliadoHistorico();
            afiliadoHistorico.setTostringAfiliado(bean.getObjeto().toString());
            //modificamos el bit de duplicado
            bean.getObjeto().setDuplicado(false);
            // configuramos el estado retirado
            Maestro estadoAfiliacion = bean.getHashValorEstadosAfiliacion().get(AfiliadoParametro.ESTADO_AFILIACION_RETIRADO);
            if (estadoAfiliacion != null) {
                bean.getObjeto().setMaeEstadoAfiliacion(estadoAfiliacion.getId());
                bean.getObjeto().setMaeEstadoAfiliacionCodigo(estadoAfiliacion.getValor());
                bean.getObjeto().setMaeEstadoAfiliacionValor(estadoAfiliacion.getNombre());
                //obtenemos la causa novedad para el estado afiliación retirado
                consultarMaestroCausaNovedadPorEstado(bean);
                Maestro causaNovedadRetiro = bean.getHashCausaNovedadValor().get(AfiliadoParametro.CAUSA_NOVEDAD_RETIRO_REALIZADO_POR_AUDITOR);
                if (causaNovedadRetiro != null) {
                    bean.getObjeto().setMaeCausaNovedad(causaNovedadRetiro.getId());
                    bean.getObjeto().setMaeCausaNovedadCodigo(causaNovedadRetiro.getValor());
                    bean.getObjeto().setMaeCausaNovedadValor(causaNovedadRetiro.getNombre());
                    //actualizamos la fecha de novedad
                    bean.getObjeto().setFechaNovedad(new Date());
                    bean.getObjeto().setFechaEgresoEps(bean.getObjeto().getFechaNovedad());
                    
                    //actualizamos el registro
                    bean.getObjeto().setOrigenUltimoRegistro(AfiliadoParametro.ORIGEN_ULTIMO_REGISTRO_PANTALLA);
                    //configuramos la auditoria
                    bean.auditoriaModificar(bean.getObjeto());
                    getAfiliadoRemoto().actualizar(bean.getObjeto());
                    //guardamos el histórico
                    bean.auditoriaGuardar(afiliadoHistorico);
                    afiliadoHistorico.setAsegAfiliado(new AsegAfiliado(bean.getObjeto().getId()));
                    getAfiliadoHistoricoRemoto().insertar(afiliadoHistorico);
                    bean.addMensaje("Se actualizó un registro de manera exitosa");
                } else {
                    throw new Exception("No se encontró la causa novedad para configurar la desmarcación de Duplicado");
                }
            } else {
                throw new Exception("No se encontró el estado Retirado para configurar la desmarcación de Duplicado");
            }
            
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    String validarCampoCadenaNulo(String dato) {
        if (dato == null) {
            return "";
        } else {
            return dato;
        }
    }

    @Override
    public void consultarListaBarriosMedellin(AfiliadosBean bean) {
        try {
            //consultamos los barrios para Medellin
            bean.setListaUbicacionBarrios(getGnUbicacionBarrioRemoto().consultarPorUbicacion(bean.getCiudadMedellin().getId()));
            bean.setHashUbicacionBarrios(new HashMap<>());
            for (GnUbicacionBarrio barrio: bean.getListaUbicacionBarrios()) {
                bean.getHashUbicacionBarrios().put(barrio.getId(), barrio);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
}
