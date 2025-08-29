/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.especial.servicio;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroAccion;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.especial.PeCarga;
import com.saviasaludeps.savia.dominio.especial.PeIpsPrograma;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.especial.PeProgramaDiagnostico;
import com.saviasaludeps.savia.dominio.especial.PeUsuariosPrograma;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.administracion.UsuarioRemoto;
import com.saviasaludeps.savia.negocio.especial.PeIpsProgramaRemoto;
import com.saviasaludeps.savia.negocio.especial.PeProgramaRemoto;
import com.saviasaludeps.savia.negocio.especial.PeUsuariosProgramaRemoto;
import com.saviasaludeps.savia.web.especial.bean.GestionProgramaBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.negocio.especial.PeAfiliadosProgramaRemoto;
import com.saviasaludeps.savia.negocio.especial.PeCargaMasivaRemoto;
import com.saviasaludeps.savia.negocio.especial.PeProgramaDiagnosticosRemoto;
import com.saviasaludeps.savia.negocio.especial.PeProgramaTecnologiasRemoto;
import com.saviasaludeps.savia.web.especial.utilidades.CargaMasivaProgramaDiagnosticoHilo;
import com.saviasaludeps.savia.web.especial.utilidades.CargaMasivaProgramaTecnologiasHilo;
import com.saviasaludeps.savia.web.especial.utilidades.PeConstantes;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.Fichero;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Jaime Andres Olarte
 */
public class GestionProgramaServicioImpl extends AccionesBO implements GestionProgramaServicioIface {

    private PeProgramaRemoto getPeProgramaRemoto() throws Exception {
        return (PeProgramaRemoto) RemotoEJB.getEJBRemoto("PeProgramaServicio", PeProgramaRemoto.class.getName());
    }

    private PeUsuariosProgramaRemoto getPeUsuariosProgramaRemoto() throws Exception {
        return (PeUsuariosProgramaRemoto) RemotoEJB.getEJBRemoto("PeUsuariosProgramaServicio", PeUsuariosProgramaRemoto.class.getName());
    }

    private PeIpsProgramaRemoto getPeIpsProgramaRemoto() throws Exception {
        return (PeIpsProgramaRemoto) RemotoEJB.getEJBRemoto("PeIpsProgramaServicio", PeIpsProgramaRemoto.class.getName());
    }

    private UsuarioRemoto getUsuarioRemoto() throws Exception {
        return (UsuarioRemoto) RemotoEJB.getEJBRemoto("UsuarioServicio", UsuarioRemoto.class.getName());
    }

    private CntPrestadorRemoto getPrestadoresRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }

    private PeProgramaDiagnosticosRemoto getPeProgramaDiagnosticosRemoto() throws Exception {
        return (PeProgramaDiagnosticosRemoto) RemotoEJB.getEJBRemoto("PeProgramaDiagnosticosServicio", PeProgramaDiagnosticosRemoto.class.getName());
    }

    private PeProgramaTecnologiasRemoto getPeProgramaTecnologiasRemoto() throws Exception {
        return (PeProgramaTecnologiasRemoto) RemotoEJB.getEJBRemoto("PeProgramaTecnologiasServicio", PeProgramaTecnologiasRemoto.class.getName());
    }

    private PeCargaMasivaRemoto getPeCargaMasivaRemoto() throws Exception {
        return (PeCargaMasivaRemoto) RemotoEJB.getEJBRemoto(("PeCargaMasivaServicio"), PeCargaMasivaRemoto.class.getName());
    }
    
    private PeAfiliadosProgramaRemoto getPeAfiliadosProgramaRemoto() throws Exception {
        return (PeAfiliadosProgramaRemoto) RemotoEJB.getEJBRemoto("PeAfiliadosProgramaServicio", PeAfiliadosProgramaRemoto.class.getName());
    }
    
    private CntPrestadorRemoto getPrestadorRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }

    @Override
    public void Accion(GestionProgramaBean bean) {
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
                case Url.ACCION_ADICIONAL_1: //Crear y Modificar de usuarios
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            consultarUsuarios(bean);
                            break;
                        case Url.ACCION_CREAR:
                            crearUsuario(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            agregarUsuario(bean);
                            break;
                        case Url.ACCION_MODIFICAR:
                            modificarUsuario(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_2: //Gestión IPS's
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            listarIps(bean);
                            break;
                        case Url.ACCION_CREAR:
                            crearIps(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            agregarIps(bean);
                            break;
                        case Url.ACCION_MODIFICAR:
                            modificarIps(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_3: //Borrar usuarios
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_BORRAR:
                            borrarUsuario(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_4: //Borrar IPS´s
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_BORRAR:
                            borrarIps(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_5:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            listarGestionTecnologias(bean);
                            break; 
                        case Url.ACCION_GUARDAR:
                            guardarTecnologiaPrograma(bean);
                            break;
                        case Url.ACCION_MODIFICAR:
                            modificarTecnologiaPrograma(bean);
                            break;
                        case Url.ACCION_BORRAR:
                            borrarTecnologiaPrograma(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_6:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_GUARDAR:
                            guardarCargaMasiva(bean);
                            break;
                        case Url.ACCION_CREAR :
                            crearAdjuntoCargaMasiva(bean);
                            break;
                        case Url.ACCION_LISTAR:
                            listarCargaMasiva(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            descargar(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_7:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            listarGestionDiagnosticos(bean);                          
                            break; 
                        case Url.ACCION_GUARDAR:
                            guardarDiagnosticoPrograma(bean);
                            break;
                        case Url.ACCION_MODIFICAR:
                            modificarDiagnosticoPrograma(bean);
                            break;
                        case Url.ACCION_BORRAR:
                            borrarDiagnosticoPrograma(bean);
                            break;
                    }
                    break;
            }
        }
    }

    @Override
    public void cargaInicial(GestionProgramaBean bean) {
        try {
            bean.setListaTipoDocumento(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));
            bean.setHashTipoDocumento(PeConstantes.obtenerHashMaestro(bean.getListaTipoDocumento()));
           
            bean.setListaUsuario(new ArrayList<>());
            bean.setUsuarioRecursiva(new HashMap<>());
            bean.setSedeRecursiva(new HashMap<>());
            //Tipos de programas
            bean.setListaProgramaTipo(getMaestroRemoto().consultarPorTipo(MaestroTipo.PE_PROGRAMA_TIPO));
            bean.setHashProgramaTipo(PeConstantes.obtenerHashMaestro(bean.getListaProgramaTipo()));
            //Categorias de programas
            bean.setListaProgramaCategoria(getMaestroRemoto().consultarPorTipo(MaestroTipo.PE_PROGRAMA_CATEGORIA));
            bean.setHashProgramaCategoria(PeConstantes.obtenerHashMaestro(bean.getListaProgramaCategoria()));
            //Maestro usuario responsable tipo
            bean.setListaUsuarioResponsableTipo(getMaestroRemoto().consultarPorTipo(MaestroTipo.PE_USUARIO_RESPONSABLE_TIPO));
            bean.setHashUsuarioResponsableTipo(new HashMap());
            bean.setHashUsuarioResponsableTipo(PeConstantes.obtenerHashMaestro(bean.getListaUsuarioResponsableTipo()));
            bean.setListaProgramaActivo(PeConstantes.listaSino());              
            bean.setListaTipoRegistroAfiliado(PeConstantes.listaRegistrosAfiliados());
            //Lista maestro agrupador
            bean.setListaMaeAgrupador(getMaestroRemoto().consultarPorTipo(MaestroTipo.PE_AGRUPADOR));
            bean.setHashListaMaeAgrupador(PeConstantes.obtenerHashMaestro(bean.getListaMaeAgrupador()));
            //Sexo aplica programas
            bean.setListaSexoAplica(PeConstantes.getListaSexoAplica());
            bean.setListaCantidadRegistro(PeConstantes.getListaCantidadRegistros());
            //estado de carga masiva
            bean.setListaEstadoCarga(PeConstantes.listaEstadoCargaMasiva());
            //Ubicaciones
            bean.setHashUbicaciones(UbicacionSingle.getInstance().getHashUbicaciones());
            bean.setListaUbicaciones(UbicacionSingle.getInstance().getListaMunicipios());
            bean.setListaCiudades(UbicacionSingle.getInstance().getListaMunicipios());
            bean.setListaDepartamentos(UbicacionSingle.getInstance().getListaDepartamentos());
            bean.setListaTipoCarga(PeConstantes.obtenerMaestroTipoCarga());
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    private void listar(GestionProgramaBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getPeProgramaRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getPeProgramaRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    private void ver(GestionProgramaBean bean) {
        try {
            bean.setObjeto(getPeProgramaRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setPeUsuariosProgramaList(getPeUsuariosProgramaRemoto().consultarPorPrograma(bean.getObjeto().getId()));
            
            bean.getObjeto().setPeIpsProgramasList(getPeIpsProgramaRemoto().consultarPorPrograma(bean.getObjeto().getId()));
            /*for (PeIpsPrograma peIpsPrograma : bean.getObjeto().getPeIpsProgramaList()) {
                peIpsPrograma.setCntPrestadorSedesId(bean.getSedeRecursiva().get(peIpsPrograma.getCntPrestadorSedesId().getId()));
            }*/
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    private void crear(GestionProgramaBean bean) {
        try {
            bean.setObjeto(new PePrograma());
            bean.getObjeto().setPeIpsProgramasList(new ArrayList());
            bean.getObjeto().setPeUsuariosProgramaList(new ArrayList());
            bean.setObjetoUsuario(new PeUsuariosPrograma());
            bean.setObjetoIps(new PeIpsPrograma());
            bean.getObjeto().setUsuariosId(new Usuario());
            bean.setListaTipoRegistroAfiliado(PeConstantes.listaRegistrosAfiliados());
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    private void guardar(GestionProgramaBean bean) {
        PePrograma auxiliar = null;
        try {
            if (bean.getObjeto().getCodigoPrograma().trim().isEmpty()) {
                bean.addError("Código: Error de validación: se necesita un valor.");
            }

            if (bean.getObjeto().getDescripcionPrograma().trim().isEmpty()) {
                bean.addError("Descripción: Error de validación: se necesita un valor.");
            }

            if (bean.getObjeto().getMaeTipoProgramaId()== 0) {
                bean.addError("Tipo: Error de validación: se necesita un valor.");
            }

            if (bean.getObjeto().getUsuariosId() == null || bean.getObjeto().getUsuariosId().getId() == null) {
                bean.addError("Lider de Programa: Error de validación: se necesita un valor.");
            }

            //2021-01-04 jyperez se adiciona validación para evitar que se cree un programa con código existente
            auxiliar = getPeProgramaRemoto().consultarPorCodigo(bean.getObjeto().getCodigoPrograma());
            if (auxiliar != null) {
                bean.addError("Código de Programa: Error: Ya existe un programa con el código ingresado.");
            }

            if (!bean.getErrores().isEmpty()) {
                return;
            }

            bean.auditoriaGuardar(bean.getObjeto());
            List<PeUsuariosPrograma> usuariosPrograma = new ArrayList<>();
            usuariosPrograma.addAll(bean.getObjeto().getPeUsuariosProgramaList());
            bean.getObjeto().setPeUsuariosProgramaList(null);

            List<PeIpsPrograma> listIpsPrograma = new ArrayList<>();
            listIpsPrograma.addAll(bean.getObjeto().getPeIpsProgramaList());
            bean.getObjeto().setPeIpsProgramasList(null);

            Maestro mae_tipo_programa = bean.getHashProgramaTipo().get(bean.getObjeto().getMaeTipoProgramaId());
            bean.getObjeto().setMaeTipoProgramaId(mae_tipo_programa.getId());
            bean.getObjeto().setMaeTipoProgramaCodigo(mae_tipo_programa.getValor());
            bean.getObjeto().setMaeTipoProgramaValor(mae_tipo_programa.getNombre());
            
            Maestro maeCategoria = bean.getHashProgramaCategoria().get(bean.getObjeto().getMaeCategoriaId());
            bean.getObjeto().setMaeCategoriaId(maeCategoria.getId());
            bean.getObjeto().setMaeCategoriaCodigo(maeCategoria.getValor());
            bean.getObjeto().setMaeCategoriaValor(maeCategoria.getNombre());
            
            if (bean.getObjeto().getMaeAgrupadorId() != null) {
                Maestro agrupador = bean.getHashListaMaeAgrupador().get(bean.getObjeto().getMaeAgrupadorId());
                bean.getObjeto().setMaeAgrupadorCodigo(agrupador.getValor());
                bean.getObjeto().setMaeAgrupadorValor(agrupador.getNombre());
            }            
            
            int peProgramaid = getPeProgramaRemoto().insertar(bean.getObjeto());

            for (PeUsuariosPrograma peUsuariosPrograma : usuariosPrograma) {
                peUsuariosPrograma.setPeProgramasId(new PePrograma(peProgramaid));
                bean.auditoriaGuardar(peUsuariosPrograma);
                getPeUsuariosProgramaRemoto().insertar(peUsuariosPrograma);
            }

            for (PeIpsPrograma ipsPrograma : listIpsPrograma) {
                ipsPrograma.setPeProgramasId(new PePrograma(peProgramaid));
                bean.auditoriaGuardar(ipsPrograma);
                getPeIpsProgramaRemoto().insertar(ipsPrograma);
            }

            bean.addMensaje("Se creo un registro de manera exitosa");
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    private void editar(GestionProgramaBean bean) {
        try {
            bean.setObjeto(getPeProgramaRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setPeUsuariosProgramaList(getPeUsuariosProgramaRemoto().consultarPorPrograma(bean.getObjeto().getId()));
            //bean.getObjeto().setUsuariosId(bean.getUsuarioRecursiva().get(bean.getObjeto().getUsuariosId().getId()));
            /*for (PeUsuariosPrograma peUsuariosPrograma : bean.getObjeto().getPeUsuariosProgramaList()) {
                peUsuariosPrograma.setUsuariosId(bean.getUsuarioRecursiva().get(peUsuariosPrograma.getUsuariosId().getId()));
            }*/
            bean.getObjeto().setPeIpsProgramasList(getPeIpsProgramaRemoto().consultarPorPrograma(bean.getObjeto().getId()));
            /*for (PeIpsPrograma peIpsPrograma : bean.getObjeto().getPeIpsProgramaList()) {
                peIpsPrograma.setCntPrestadorSedesId(bean.getSedeRecursiva().get(peIpsPrograma.getCntPrestadorSedesId().getId()));
            }*/
            if (bean.getListaProgramaTipo().isEmpty()) {
                bean.setListaProgramaTipo(getMaestroRemoto().consultarPorTipo(MaestroTipo.PE_PROGRAMA_TIPO));
                bean.setHashProgramaTipo(PeConstantes.obtenerHashMaestro(bean.getListaProgramaTipo()));
            }
            if (bean.getListaProgramaCategoria().isEmpty()) {
                bean.setListaProgramaCategoria(getMaestroRemoto().consultarPorTipo(MaestroTipo.PE_PROGRAMA_CATEGORIA));
                bean.setHashProgramaCategoria(PeConstantes.obtenerHashMaestro(bean.getListaProgramaCategoria()));
            }
            bean.setListaTipoRegistroAfiliado(PeConstantes.listaRegistrosAfiliados());
            bean.setObjetoIps(new PeIpsPrograma());
            bean.setObjetoUsuario(new PeUsuariosPrograma());
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    private void modificar(GestionProgramaBean bean) {
        PePrograma auxiliar = null;
        try {

            //2021-01-04 jyperez se adiciona validación para evitar que se cree un programa con código existente
            auxiliar = getPeProgramaRemoto().consultarPorCodigo(bean.getObjeto().getCodigoPrograma());
            if (auxiliar != null && bean.getObjeto().getId().compareTo(auxiliar.getId()) != 0) {
                bean.addError("Código de Programa: Error: Ya existe un programa con el código ingresado.");
            }

            if (!bean.getErrores().isEmpty()) {
                return;
            }

            bean.auditoriaModificar(bean.getObjeto());
            List<PeUsuariosPrograma> usuariosPrograma = new ArrayList<>();
            if(bean.getObjeto().getPeUsuariosProgramaList() != null){
                usuariosPrograma.addAll(bean.getObjeto().getPeUsuariosProgramaList());
            }            
            bean.getObjeto().setPeUsuariosProgramaList(null);

            List<PeIpsPrograma> listIpsPrograma = new ArrayList<>();
            if(bean.getObjeto().getPeIpsProgramaList() != null){
                listIpsPrograma.addAll(bean.getObjeto().getPeIpsProgramaList());
            }            
            bean.getObjeto().setPeIpsProgramasList(null);

            Maestro mae_tipo_programa = bean.getHashProgramaTipo().get(bean.getObjeto().getMaeTipoProgramaId());
            bean.getObjeto().setMaeTipoProgramaId(mae_tipo_programa.getId());
            bean.getObjeto().setMaeTipoProgramaCodigo(mae_tipo_programa.getValor());
            bean.getObjeto().setMaeTipoProgramaValor(mae_tipo_programa.getNombre());
            
            Maestro maeCategoria = bean.getHashProgramaCategoria().get(bean.getObjeto().getMaeCategoriaId());
            bean.getObjeto().setMaeCategoriaId(maeCategoria.getId());
            bean.getObjeto().setMaeCategoriaCodigo(maeCategoria.getValor());
            bean.getObjeto().setMaeCategoriaValor(maeCategoria.getNombre());
            
            if (bean.getObjeto().getMaeAgrupadorId() != null) {
                Maestro maeAgrupador = bean.getHashListaMaeAgrupador().get(bean.getObjeto().getMaeAgrupadorId());
                bean.getObjeto().setMaeAgrupadorCodigo(maeAgrupador.getValor());
                bean.getObjeto().setMaeAgrupadorValor(maeAgrupador.getNombre());
            }

            getPeProgramaRemoto().actualizar(bean.getObjeto());
            
            // Se implementa funcionalidad para inactiva todos los afiliados 
            // del programa cuando el programa se inactiva.             
            if(!bean.getObjeto().isActivo()){
                List<Maestro> causasInactivo = getMaestroRemoto().consultarMaestrosPorAccion(MaestroAccion.PE_CAUSA_ESTADO_INACTIVO_AUTOMATICO);
                Maestro causaEstado = causasInactivo.stream().filter(valor -> valor.getId() != null).findFirst().orElse(new Maestro());
                if(causaEstado.getId() != null){
                    bean.auditoriaModificar(bean.getObjeto());
                    getPeAfiliadosProgramaRemoto().cambiarEstadoAfiliadosPrograma(bean.getObjeto(), causaEstado);
                }else{
                    bean.addError("No fue posible inactivar los afiliados del programa, no se definió la causa estado");
                }                
            }

            bean.addMensaje("Se modifico un registro de manera exitosa");
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    private void borrar(GestionProgramaBean bean) {
        try {
            Integer cantidadAfilaidos = getPeAfiliadosProgramaRemoto().consultarCantidadAfiliadoPrograma(bean.getObjeto().getId());
            if (cantidadAfilaidos > 0) {
                bean.addError("No es posible eliminar el programa ya que cuenta con afiliados matriculados.");
            } else {
                bean.setObjeto(getPeProgramaRemoto().eliminar(bean.getObjeto().getId()));
                bean.addMensaje("Se eliminó un registro de manera exitosa");
            }            
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void consultarUsuarios(GestionProgramaBean bean){
        try { 
            if (!bean.getConexion().getEmpresa().isAdministradora()) {
                bean.getParamConsulta(1).setEmpresaId(bean.getConexion().getEmpresa().getId());
            }
            bean.getParamConsulta(1).getFiltros().put("activo", "1");
            bean.getParamConsulta(1).setCantidadRegistros(getUsuarioRemoto().consultarCantidadLista(bean.getParamConsulta(1)));
            bean.setListaUsuario(getUsuarioRemoto().consultarLista(bean.getParamConsulta(1)));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crearUsuario(GestionProgramaBean bean) {
        try {
            bean.setObjetoUsuario(new PeUsuariosPrograma());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void agregarUsuario(GestionProgramaBean bean) {
        try {
            boolean validado = true;
            for (PeUsuariosPrograma peUsuariosPrograma : bean.getObjeto().getPeUsuariosProgramaList()) {
                if (peUsuariosPrograma.getUsuariosId().getId().intValue() == bean.getObjetoUsuario().getUsuariosId().getId()) {
                    validado = false;
                }
            }

            if (!validado) {
                bean.addError("El usuario " + bean.getObjetoUsuario().getUsuariosId().getNombre() + " ya fue agregado.");
                return;
            }

            bean.getObjeto().getPeUsuariosProgramaList().add(bean.getObjetoUsuario());

            Maestro mae_tipo_responsable = getMaestroRemoto().consultarPorValorTipo(String.valueOf(bean.getObjetoUsuario().getTipo()), MaestroTipo.PE_USUARIO_RESPONSABLE_TIPO);
            if (mae_tipo_responsable != null) {
                bean.getObjetoUsuario().setMaeTipoId(mae_tipo_responsable.getId());
                bean.getObjetoUsuario().setMaeTipoCodigo(mae_tipo_responsable.getValor());
                bean.getObjetoUsuario().setMaeTipoValor(mae_tipo_responsable.getDescripcion());
            }

            if (bean.getObjeto().getId() != null) {
                bean.getObjetoUsuario().setPeProgramasId(new PePrograma(bean.getObjeto().getId()));
                bean.auditoriaGuardar(bean.getObjetoUsuario());
                getPeUsuariosProgramaRemoto().insertar(bean.getObjetoUsuario());
            }

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificarUsuario(GestionProgramaBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjetoUsuario());
            getPeUsuariosProgramaRemoto().actualizar(bean.getObjetoUsuario());
//            PeUsuariosPrograma objBorrar = null;
//            for (PeUsuariosPrograma peUsuariosPrograma : bean.getObjeto().getPeUsuariosProgramaList()) {
//                if (peUsuariosPrograma.getId().intValue() == bean.getObjetoUsuario().getId().intValue()) {
//                    objBorrar = peUsuariosPrograma;
//                }
//            }
//            
//            if (objBorrar != null) {
//                bean.getObjeto().getPeUsuariosProgramaList().remove(objBorrar);
//                bean.getObjeto().getPeUsuariosProgramaList().add(bean.getObjetoUsuario());
//            }
            bean.addMensaje("Se modifico un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrarUsuario(GestionProgramaBean bean) {
        try {
            bean.setObjetoUsuario(getPeUsuariosProgramaRemoto().eliminar(bean.getObjetoUsuario().getId()));
            PeUsuariosPrograma objBorrar = null;
            for (PeUsuariosPrograma peUsuariosPrograma : bean.getObjeto().getPeUsuariosProgramaList()) {
                if (peUsuariosPrograma.getId().intValue() == bean.getObjetoUsuario().getId().intValue()) {
                    objBorrar = peUsuariosPrograma;
                }
            }
            if (objBorrar != null) {
                bean.getObjeto().getPeUsuariosProgramaList().remove(objBorrar);
            }
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void listarIps(GestionProgramaBean bean) {
        try {
            if (!bean.getConexion().getEmpresa().isAdministradora()) {
                bean.getParamConsulta(0).setParametroConsulta5(bean.getConexion().getEmpresa().getCntPrestador().getId());
            }
            bean.getParamConsulta(0).getFiltros().put("cntContratosId.activo", true);
            bean.getParamConsulta(0).getFiltros().put("fecha", Util.fechaDateAString(new Date()));
            bean.getParamConsulta(0).setCantidadRegistros(getPrestadorRemoto().consultarCantidadListaSede(bean.getParamConsulta(0)));
            bean.setListaIps(getPrestadorRemoto().consultarListaSede(bean.getParamConsulta(0)));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crearIps(GestionProgramaBean bean) {
        try {
            bean.setObjetoIps(new PeIpsPrograma());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void agregarIps(GestionProgramaBean bean) {
        try {

            boolean validado = true;
            for (PeIpsPrograma ipsPrograma : bean.getObjeto().getPeIpsProgramaList()) {
                if (ipsPrograma.getCntPrestadorSedesId().getId().intValue() == bean.getObjetoIps().getCntPrestadorSedesId().getId()) {
                    validado = false;
                }
            }

            if (!validado) {
                bean.addError("La IPS " + bean.getObjetoIps().getCntPrestadorSedesId().getNombreSede() + " ya fue agregada.");
                return;
            }

            bean.getObjeto().getPeIpsProgramaList().add(bean.getObjetoIps());

            if (bean.getObjeto().getId() != null) {
                bean.getObjetoIps().setPeProgramasId(new PePrograma(bean.getObjeto().getId()));
                bean.auditoriaGuardar(bean.getObjetoIps());
                getPeIpsProgramaRemoto().insertar(bean.getObjetoIps());
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificarIps(GestionProgramaBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjetoIps());
            getPeIpsProgramaRemoto().actualizar(bean.getObjetoIps());
            bean.addMensaje("Se modifico un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrarIps(GestionProgramaBean bean) {
        try {
            bean.setObjetoIps(getPeIpsProgramaRemoto().eliminar(bean.getObjetoIps().getId()));
            PeIpsPrograma objBorrar = null;
            for (PeIpsPrograma peIpsPrograma : bean.getObjeto().getPeIpsProgramaList()) {
                if (peIpsPrograma.getId().intValue() == bean.getObjetoIps().getId().intValue()) {
                    objBorrar = peIpsPrograma;
                }
            }
            if (objBorrar != null) {
                bean.getObjeto().getPeIpsProgramaList().remove(objBorrar);
                bean.setObjetoIps(objBorrar);
            }
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardarTecnologiaPrograma(GestionProgramaBean bean) {
        try {            
            bean.auditoriaGuardar(bean.getObjeto().getObjetoTecnologia());
            getPeProgramaTecnologiasRemoto().insertar(bean.getObjeto().getObjetoTecnologia()); 
            bean.addMensaje("Se guardo la tecnología de manera exitosa");
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }
    
    private void guardarDiagnosticoPrograma(GestionProgramaBean bean) {
        try {            
            bean.auditoriaGuardar(bean.getObjeto().getObjetoDiagnostico());
            getPeProgramaDiagnosticosRemoto().insertar(bean.getObjeto().getObjetoDiagnostico());  
            bean.addMensaje("Se guardo el diagnostico de manera exitosa");
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    private void listarGestionTecnologias(GestionProgramaBean bean) {
        try {
            bean.getParamConsulta(2).getFiltros().put("idPrograma", bean.getObjeto().getId().toString());
            bean.getParamConsulta(2).setCantidadRegistros(getPeProgramaTecnologiasRemoto().consultarCantidadLista(bean.getParamConsulta(2)));
            bean.getObjeto().setPeProgramaTecnologiasList(getPeProgramaTecnologiasRemoto().consultarLista(bean.getParamConsulta(2)));
            bean.setListaTipoTecnologia(PeConstantes.obtenerTipoTecnologia());
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }
    
    private void listarGestionDiagnosticos(GestionProgramaBean bean) {
        try {           
            bean.getParamConsulta(3).getFiltros().put("idPrograma", bean.getObjeto().getId().toString());
            bean.getParamConsulta(3).setCantidadRegistros(getPeProgramaDiagnosticosRemoto().consultarCantidadLista(bean.getParamConsulta(3)));
            bean.getObjeto().setPeProgramaDiagnosticosList(getPeProgramaDiagnosticosRemoto().consultarLista(bean.getParamConsulta(3)));           
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }
    
    private void borrarTecnologiaPrograma(GestionProgramaBean bean) {
        try {
            if (bean.getObjeto().getObjetoTecnologia() == null) {
                bean.addError("Debe indicar la tecnología a eliminar. ");
                return;
            }
            bean.auditoriaModificar(bean.getObjeto().getObjetoTecnologia());
            getPeProgramaTecnologiasRemoto().eliminar(bean.getObjeto().getObjetoTecnologia());
            bean.addMensaje("Tecnología eliminada de manera exitosa");
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    private void borrarDiagnosticoPrograma(GestionProgramaBean bean) {
        try {
            if (bean.getObjeto().getObjetoDiagnostico() == null) {
                bean.addError("Debe indicar el diagnostico a eliminar. ");
                return;
            } 
            bean.auditoriaModificar(bean.getObjeto().getObjetoDiagnostico());
            getPeProgramaDiagnosticosRemoto().eliminar(bean.getObjeto().getObjetoDiagnostico());
            bean.addMensaje("Diagnostico eliminado de manera exitosa");
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }
    
    private void modificarTecnologiaPrograma(GestionProgramaBean bean) {
        try {
            if (bean.getObjeto().getObjetoTecnologia() == null) {
                bean.addError("Debe indicar la tecnología a modificar.");
                return;
            }
            bean.auditoriaModificar(bean.getObjeto().getObjetoTecnologia());
            getPeProgramaTecnologiasRemoto().actualizarMarcaAutomatica(bean.getObjeto().getObjetoTecnologia());
            bean.addMensaje("Se modificó la tecnología de manera exitosa");
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }
    
    private void modificarDiagnosticoPrograma(GestionProgramaBean bean){
        try {
            if (bean.getObjeto().getObjetoDiagnostico() == null) {
                bean.addError("Debe indicar el diagnostico a modificar.");
                return;
            } 
            bean.auditoriaModificar(bean.getObjeto().getObjetoDiagnostico());
            getPeProgramaDiagnosticosRemoto().actualizarMarcaAutomatica(bean.getObjeto().getObjetoDiagnostico());
            bean.addMensaje("Se modificó el diagnostico de manera exitosa");
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    private void guardarCargaMasiva(GestionProgramaBean bean) {
        try {
            int id = 0;
            int indiceExtension;
            String extension;
            String nombre;
            Date fecha = new Date();
            String ruta = PropApl.getInstance().get(PropApl.PE_RUTA_CARGA_MASIVA);
            // validamos si el archivo cargado es correcto
            String mensaje="";
            indiceExtension = bean.getObjetoCarga().getNombre().lastIndexOf(".");
            extension = bean.getObjetoCarga().getNombre().substring(indiceExtension, bean.getObjetoCarga().getNombre().length());
            if(bean.getObjetoCarga().getTipo() == PeConstantes.TIPO_CARGA_TECNOLOGIA){
                mensaje = validarArchivoCargaTecnologia(bean);
                bean.getObjetoCarga().setArchivo(String.format("%s%s%s", PeConstantes.NOMBRE_ARCHIVO_CARGA_TECNOLOGIA, PeConstantes.formato6.format(fecha),extension));
                bean.getObjetoCarga().setRespArchivo(String.format("%s%s%s", PeConstantes.NOMBRE_ARCHIVO_RESPUESTA_CARGA_TECNOLOGIA, PeConstantes.formato6.format(fecha),extension));
            }else{
                mensaje = validarArchivoCargaDiagnostico(bean);
                bean.getObjetoCarga().setArchivo(String.format("%s%s%s", PeConstantes.NOMBRE_ARCHIVO_CARGA_DIAGNOSTICO, PeConstantes.formato6.format(fecha),extension));
                bean.getObjetoCarga().setRespArchivo(String.format("%s%s%s", PeConstantes.NOMBRE_ARCHIVO_RESPUESTA_CARGA_DIAGNOSTICO, PeConstantes.formato6.format(fecha),extension));
            }
            
            if (mensaje.trim().equals("")) {
                // actualizamos valores del objeto a guardar
                bean.getObjetoCarga().setRuta(ruta);
                bean.getObjetoCarga().setEstado(PeConstantes.ESTADO_EN_COLA);
                bean.getObjetoCarga().setFechaHoraInicio(fecha);
                bean.getObjetoCarga().setIdPrograma(bean.getObjeto().getId());
                bean.getObjetoCarga().setExiste(true);
                bean.getObjetoCarga().setRespRuta(ruta);
                bean.getObjetoCarga().setRespNombre(bean.getObjetoCarga().getNombre());
                bean.getObjetoCarga().setRespExiste(false);
                //generamos la auditoria para el objeto nuevo
                bean.auditoriaGuardar(bean.getObjetoCarga());
                int idPrestador = bean.getConexion().getEmpresa().isAdministradora() ? 1 : bean.getConexion().getEmpresa().getCntPrestador().getId();
                bean.getObjetoCarga().setEmpresa(new Empresa(idPrestador));
                //guardamos el registro en PeCarga
                bean.getObjetoCarga().setId(getPeCargaMasivaRemoto().insertar(bean.getObjetoCarga()));
                //guardamos el archivo en la ruta
                generarArchivoCargaMasiva(bean.getObjetoCarga());
                bean.addMensaje("El archivo " + bean.getObjetoCarga().getNombre() + " se cargó con exito, con número de radicado " + bean.getObjetoCarga().getId());                
            } else {
                bean.addError("Error en formato del archivo: " + mensaje);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private String validarArchivoCargaTecnologia(GestionProgramaBean bean) {
        int i = 0;
        StringBuilder mensaje = new StringBuilder("");        
        int cantidadCampos = PeConstantes.ENCABEZADO_CARGA_TECNOLOGIA.split(PeConstantes.COMA).length;
        InputStream aux;
        BufferedReader br;
        InputStream copia;
        // variables para clonar el inputStream
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        String line;
        try {
            aux = bean.getObjetoCarga().getAdjuntoStream();
            while ((len = aux.read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
            copia = new ByteArrayInputStream(baos.toByteArray());
            //hacemos copia nuevamente en el inputStream que clonamos
            bean.getObjetoCarga().setAdjuntoStream(new ByteArrayInputStream(baos.toByteArray()));

            br = new BufferedReader(new InputStreamReader(copia));

            while ((line = br.readLine()) != null) {
                // validamos el encabezado
                if (i == 0) {
                    if (!PeConstantes.ENCABEZADO_CARGA_TECNOLOGIA.equals(line)) {
                        mensaje.append("* El encabezado del archivo no se encontró o es erróneo.").append(PeConstantes.SALTO_LINEA); 
                        //break;
                    }
                }
                if (contarCaracteres(line, PeConstantes.COMA) != cantidadCampos) {
                    mensaje.append("* linea ").append((i+1)).append(": la cantidad de campos no corresponde al numero de campos permitidos (").append(cantidadCampos).append(") ").append(PeConstantes.SALTO_LINEA);
                }
                i++;
            }
            // copiamos la cantidad de registros en el objeto de carga
            bean.getObjetoCarga().setRegistros(i - 1);
            bean.getObjetoCarga().setExitosos(0);
            bean.getObjetoCarga().setFallidos(0);
        } catch (IOException e) {
            bean.addError(e.getMessage());
        }
        return mensaje.toString();
    }
    
    private String validarArchivoCargaDiagnostico(GestionProgramaBean bean) {
        int i = 0;
        StringBuilder mensaje = new StringBuilder("");       
        int cantidadCampos = PeConstantes.ENCABEZADO_CARGA_DIAGNOSTICO.split(PeConstantes.COMA).length;
        InputStream aux;
        BufferedReader br;
        InputStream copia;
        // variables para clonar el inputStream
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        String line;
        try {
            aux = bean.getObjetoCarga().getAdjuntoStream();
            while ((len = aux.read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
            copia = new ByteArrayInputStream(baos.toByteArray());
            //hacemos copia nuevamente en el inputStream que clonamos
            bean.getObjetoCarga().setAdjuntoStream(new ByteArrayInputStream(baos.toByteArray()));

            br = new BufferedReader(new InputStreamReader(copia));

            while ((line = br.readLine()) != null) {
                // validamos el encabezado
                if (i == 0) {
                    if (!PeConstantes.ENCABEZADO_CARGA_DIAGNOSTICO.equals(line)) {
                        mensaje.append("* El encabezado del archivo no se encontró o es erróneo.").append(PeConstantes.SALTO_LINEA); 
                        //break;
                    }
                }
                if (contarCaracteres(line, PeConstantes.COMA) != cantidadCampos) {
                    mensaje.append("* linea ").append((i+1)).append(": la cantidad de campos no corresponde al numero de campos permitidos (").append(cantidadCampos).append(") ").append(PeConstantes.SALTO_LINEA);
                }
                i++;
            }
            // copiamos la cantidad de registros en el objeto de carga
            bean.getObjetoCarga().setRegistros(i - 1);
            bean.getObjetoCarga().setExitosos(0);
            bean.getObjetoCarga().setFallidos(0);
        } catch (IOException e) {
            bean.addError(e.getMessage());
        }
        return mensaje.toString();
    }

    private int contarCaracteres(String cadena, String caracter) {
        return cadena.split(caracter).length;
    }

    private boolean generarArchivoCargaMasiva(PeCarga objeto) throws Exception {
        boolean esArchivoGuardado = false;
        OutputStream destino = null;
        try {
            File archivo = new File(objeto.getRuta(), objeto.getArchivo());
            destino = new FileOutputStream(archivo);
            IOUtils.copy(objeto.getAdjuntoStream(), destino);
            IOUtils.closeQuietly(objeto.getAdjuntoStream());
            IOUtils.closeQuietly(destino);
            Fichero.permisos(archivo.toPath());
            esArchivoGuardado = true;
        } catch (FileNotFoundException ex) {
            throw new Exception("Error al subir un adjunto " + ex.getMessage());
        } catch (IOException ex) {
            throw new Exception("Error al subir un adjunto " + ex.getMessage());
        } finally {
            try {
                destino.close();
            } catch (IOException e) {
                esArchivoGuardado = false;
            }
        }

        return esArchivoGuardado;
    }

    private void listarCargaMasiva(GestionProgramaBean bean) {
        try {
            bean.getParamConsulta(4).setCantidadRegistros(getPeCargaMasivaRemoto().cantidadListaCargaDiagnosticoTecnologia(bean.getParamConsulta(4)));
            bean.setRegistrosCarga(getPeCargaMasivaRemoto().consultarListaCargaDiagnosticoTecnologia(bean.getParamConsulta(4)));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }

    }

    private void crearAdjuntoCargaMasiva(GestionProgramaBean bean) {
        try {
            bean.setObjetoCarga(new PeCarga());           
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void descargar(GestionProgramaBean bean) {
        try {
            bean.setObjetoCarga(getPeCargaMasivaRemoto().consultar(bean.getObjetoCarga().getId()));
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    @Override
    public boolean existeDiagnosticoPrograma(PeProgramaDiagnostico objeto) {
        boolean existe = true;
        try {
            PeProgramaDiagnostico diagnostico = getPeProgramaDiagnosticosRemoto().consultarDiagnosticoPrograma(objeto);
            if(diagnostico == null){
                existe = false;
            }
        } catch (Exception ex) {
            Logger.getLogger(GestionProgramaServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existe;
    }
}
