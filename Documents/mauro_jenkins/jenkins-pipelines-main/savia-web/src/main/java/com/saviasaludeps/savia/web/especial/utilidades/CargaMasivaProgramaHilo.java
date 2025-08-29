/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.especial.utilidades;

import com.saviasaludeps.savia.dominio.maestro.MaDiagnostico;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroAccion;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadoDiagnostico;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadosPrograma;
import com.saviasaludeps.savia.dominio.especial.PeCarga;
import com.saviasaludeps.savia.dominio.especial.PeCargaHistorico;
import com.saviasaludeps.savia.dominio.especial.PeIpsPrograma;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.especial.PeTelefono;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.especial.PeAfiliadosProgramaRemoto;
import com.saviasaludeps.savia.negocio.especial.PeCargaMasivaRemoto;
import com.saviasaludeps.savia.negocio.especial.PeIpsProgramaRemoto;
import com.saviasaludeps.savia.negocio.especial.PeProgramaRemoto;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.negocio.especial.PeAfiliadoDiagnosticoRemoto;
import com.saviasaludeps.savia.negocio.especial.PeCargaHistoricoRemoto;
import com.saviasaludeps.savia.negocio.especial.PeTelefonosRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaDiagnosticoRemoto;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Jaime Andres Olarte
 */
public class CargaMasivaProgramaHilo extends Thread {

    public static final int CARGA_PROGRAMAS_ESPECIALES = 3;
    public static final String REGIMEN_SUBSIDIADO = "1";
    public static final String MODELO_LIQUIDACION_EVENTO = "1";
    public static final String EXITOSO_STR = "Exitoso";
    public static final String FALLIDO_STR = "Fallido";
    public static final String CANCELADO_STR = "Cancelado";
    public static final int ESTADO_CARGA_PROCESANDO = 1;
    public static final int ESTADO_CARGA_PROCESADO = 2;
    public static final int ESTADO_CARGA_ABORTADO = 3;

    public static final int TIPO_TELEFONO_FIJO = 1;
    public static final int TIPO_TELEFONO_MOVIL = 2;
    public static final int TIPO_TELEFONO_OFICINA = 3;

    private HashMap<String, Maestro> hashTipoDocumento;
    private HashMap<String, Maestro> hashCausaActivo;
    private HashMap<String, Maestro> hashCausaInactivo;
    private HashMap<String, Maestro> hashRegionCorporal;

    private PeCarga carga;
    private List<PeAfiliadosPrograma> listaCarga;
    private Date fechaActual;

    public CargaMasivaProgramaHilo(PeCarga carga) {
        try {
            this.fechaActual = new Date();
            this.carga = carga;
            this.hashTipoDocumento = getMaestroRemoto().consultarHashPorTipoValor(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA);
            this.hashCausaActivo = getMaestroRemoto().consultarHashPorTipoValor(MaestroTipo.PE_CAUSA_ESTADO_ACTIVO);
            this.hashCausaInactivo = getMaestroRemoto().consultarHashPorTipoValor(MaestroTipo.PE_CAUSA_ESTADO_INACTIVO);
            this.hashRegionCorporal = getMaestroRemoto().consultarHashPorTipoValor(MaestroTipo.PE_REGION_CORPORAL);
            /*if (idPrestador > 0) {
                this.listaProgramas = getPeIpsProgramaRemoto().consultarProgramaPorPrestador(idPrestador);
            } */
        } catch (Exception ex) {
            Logger.getLogger(CargaMasivaProgramaHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
        return (MaestroRemoto) object;
    }

    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
        return (AfiliadoRemoto) object;
    }

    private PeProgramaRemoto getPeProgramaRemoto() throws Exception {
        return (PeProgramaRemoto) RemotoEJB.getEJBRemoto("PeProgramaServicio", PeProgramaRemoto.class.getName());
    }

    private CntPrestadorRemoto getPrestadoresRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }

    private PeAfiliadosProgramaRemoto getPeAfiliadosProgramaRemoto() throws Exception {
        return (PeAfiliadosProgramaRemoto) RemotoEJB.getEJBRemoto("PeAfiliadosProgramaServicio", PeAfiliadosProgramaRemoto.class.getName());
    }

    private PeIpsProgramaRemoto getPeIpsProgramaRemoto() throws Exception {
        return (PeIpsProgramaRemoto) RemotoEJB.getEJBRemoto("PeIpsProgramaServicio", PeIpsProgramaRemoto.class.getName());
    }

    private PeCargaMasivaRemoto getCargaMasivaRemoto() throws Exception {
        return (PeCargaMasivaRemoto) RemotoEJB.getEJBRemoto("PeCargaMasivaServicio", PeCargaMasivaRemoto.class.getName());
    }

    private MaDiagnosticoRemoto getDiagnosticoRemoto() throws Exception {
        return (MaDiagnosticoRemoto) RemotoEJB.getEJBRemoto("MaDiagnosticoServicio", MaDiagnosticoRemoto.class.getName());
    }

    private PeCargaHistoricoRemoto getPeCargaHistoricoRemoto() throws Exception {
        return (PeCargaHistoricoRemoto) RemotoEJB.getEJBRemoto("PeCargaHistoricoServicio", PeCargaHistoricoRemoto.class.getName());
    }

    private PeTelefonosRemoto getPeTelefonosRemoto() throws Exception {
        return (PeTelefonosRemoto) RemotoEJB.getEJBRemoto("PeTelefonosServicio", PeTelefonosRemoto.class.getName());
    }

    private PeAfiliadoDiagnosticoRemoto getPeAfiliadoDiagnosticoRemoto() throws Exception {
        return (PeAfiliadoDiagnosticoRemoto) RemotoEJB.getEJBRemoto("PeAfiliadoDiagnosticoServicio", PeAfiliadoDiagnosticoRemoto.class.getName());
    }

    @Override
    public void run() {
        cargaProgramasEspeciales();
    }

    private void cargaProgramasEspeciales() {
        int pos = 0;
        int exitosos = 0;
        int fallidos = 0;
        StringBuilder error = new StringBuilder();
        StringBuilder contenidoArchivo = new StringBuilder();
        // Obtener registros de afiliado del archivo de AsegCarga
        obtenerListaCargaAfiliados();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (PeAfiliadosPrograma objeto : listaCarga) {
            try {
                Date fecha_actual = sdf.parse(sdf.format(new Date()));
                // validamos si el registro en la lista, no contiene errores
                if (objeto.getErrorCarga().equals("")) {
                    //ejecutamos validaciones de negocio
                    error = new StringBuilder();
                    String telefono_fijo = objeto.getAsegAfiliado().getTelefonoFijo();
                    String telefono_movil = objeto.getAsegAfiliado().getTelefonoMovil();

                    if (telefono_fijo == null && telefono_movil == null) {
                        error.append("Debe ingresar un valor para el campo [numero_telefono] o [numero_celular]. ");
                    }

                    //Se valida la existencia del afiliado y que se encuentre activo
                    AsegAfiliado afiliadoActivo = getAfiliadoRemoto().consultarActivo(objeto.getAsegAfiliado().getMaeTipoDocumento(), objeto.getAsegAfiliado().getNumeroDocumento());
                    if (afiliadoActivo == null) {
                        objeto.setAsegAfiliado(null);
                        error.append("No se encontro ningún afiliado con estado Activo, por favor validar el estado de la afiliación. ");
                    } else {
                        objeto.setAsegAfiliado(afiliadoActivo);
                    }

                    //Se valida la existencia del programa especial
                    PePrograma pePrograma = getPeProgramaRemoto().consultar(carga.getIdPrograma());
                    if (pePrograma == null) {
                        error.append(String.format("No se encontro ningún programa con el codigo %s.  ", objeto.getPePrograma().getCodigoPrograma()));
                    } else {
                        objeto.setPePrograma(pePrograma);
                        objeto.setGnUsuario(new Usuario(objeto.getPePrograma().getUsuariosId().getId()));
                        if (!objeto.getPePrograma().isActivo()) {
                            error.append(String.format("El programa %s no se encuentra activo. ", objeto.getPePrograma().getDescripcionPrograma()));
                        }
                    }
                    
                    //Se valida la existencia de los contactos programa
                    PeTelefono telefono = new PeTelefono();
                    PeTelefono existe_tel_fijo = new PeTelefono();
                    PeTelefono existe_tel_celular = new PeTelefono();
                    if (objeto.getAsegAfiliado() != null) {
                        telefono.setAsegAfiliadosId(new AsegAfiliado(objeto.getAsegAfiliado().getId()));
                        existe_tel_fijo = getPeTelefonosRemoto().consultarTelefonoAfiliado(objeto.getAsegAfiliado().getId(), PeConstantes.PE_TELEFONO_TIPO_FIJO, telefono_fijo);
                        existe_tel_celular = getPeTelefonosRemoto().consultarTelefonoAfiliado(objeto.getAsegAfiliado().getId(), PeConstantes.PE_TELEFONO_TIPO_MOVIL, telefono_movil);
                    }                    
                    telefono.setFechaHoraCrea(carga.getFechaHoraCrea());
                    telefono.setTerminalCrea(carga.getTerminalCrea());
                    telefono.setUsuarioCrea(carga.getUsuarioCrea());
                    
                    //Se validan que los diagnosticos sean correctos
                    if (objeto.getPeAfiliadoDiagnosticoList().isEmpty()) {
                        error.append("No se contraron diagnósticos especificados. ");
                    } else {
                        for (PeAfiliadoDiagnostico item : objeto.getPeAfiliadoDiagnosticoList()) {
                            MaDiagnostico diagnostico = getDiagnosticoRemoto().consultarPorCodigo(item.getMaDiagnosticosCodigo());                            
                            if (diagnostico != null && diagnostico.getId() != null && objeto.getAsegAfiliado() != null) {
                                boolean aplicaSexoDiagnostico = validarSexoAplicaDiagnostico(objeto.getAsegAfiliado(), diagnostico);
                                if (!aplicaSexoDiagnostico) {
                                    error.append("El género del diagnostico ").append(diagnostico.getNombre()).append(" no corresponde al genero del afiliado, por favor validar en el sistema. ");
                                }
                                item.setMaDiagnosticosId(diagnostico.getId().toString());
                                item.setMaDiagnosticosCodigo(diagnostico.getCodigo());
                                item.setMaDiagnosticosValor(diagnostico.getNombre());
                                item.setFechaHoraCrea(carga.getFechaHoraCrea());
                                item.setTerminalCrea(carga.getTerminalCrea());
                                item.setUsuarioCrea(carga.getUsuarioCrea());
                                item.setFechaHoraModifica(carga.getFechaHoraCrea());
                                item.setTerminalModifica(carga.getTerminalCrea());
                                item.setUsuarioModifica(carga.getUsuarioCrea());
                            } else {
                                error.append("No se encontro ningún diagnóstico con codigo " + item.getMaDiagnosticosCodigo() + ". ");
                            }
                        }
                    }
                    
                    //Se valida la region corporal
                    if (objeto.getMaeRegionCorporal() != null) {
                        Maestro regionCorporal = hashRegionCorporal.get(objeto.getMaeRegionCorporal().getValor());                        
                        if (regionCorporal != null) {
                            objeto.setMaeRegionCorporal(regionCorporal);
                            objeto.setMaeRegionCorporalId(objeto.getMaeRegionCorporal().getId());
                            objeto.setMaeRegionCorporalCodigo(objeto.getMaeRegionCorporal().getValor());
                            objeto.setMaeRegionCorporalValor(objeto.getMaeRegionCorporal().getNombre());
                        } else {
                            error.append("No se encontro la región corporal especificada. ");
                        }
                    }
                    
                    //Se valida la ips
                    objeto.setCntPrestadorSede(getPrestadoresRemoto().consultarSedePorCodigoHabilitacionTodos(objeto.getCntPrestadorSede().getCodigoHabilitacionSede()));
                    if (objeto.getCntPrestadorSede() == null) {
                        error.append("No se encontro ningúna IPS. ");
                    }
                    
                    //Se valida la fecha inicio programa
                    if (objeto.getFechaInicioPrograma() != null) {
                        if (objeto.getFechaInicioPrograma().after(fecha_actual)) {
                            error.append("La fecha incio programa no debe ser mayor a la fecha actual");
                        }
                    }

                    //Se valida fecha de fin programa
                    if (objeto.getFechaFinPrograma() != null) {
                        if (objeto.getFechaInicioPrograma() == null) {
                            error.append("No se puede asignar fecha de egreso sin una fecha de inicio");
                        } else if (objeto.getFechaFinPrograma().before(objeto.getFechaInicioPrograma())) {
                            error.append("La fecha de egreso es menor a la fecha de inicio del programa");
                        } 
                    }

                    //Se valida fecha de diagnostico
                    if (objeto.getFechaDiagnostico() != null) {
                        if (objeto.getFechaDiagnostico().after(fecha_actual)) {
                            error.append("La fecha de diagnostico no puede ser mayor a la fecha actual");
                        }
                    } 
                    
                    //Se establece causa estado
                    Maestro estado_causa = null;
                    if (objeto.getActivo()) {
                        estado_causa = new Maestro();
                        estado_causa = hashCausaActivo.get(objeto.getMaeCausaActivoObj().getValor());
                    } else {
                        estado_causa = new Maestro();
                        estado_causa = hashCausaInactivo.get(objeto.getMaeCausaInactivoObj().getValor());
                    }
                    
                    //Se valida si existen errores en el proceso
                    if (!error.toString().isEmpty()) {
                        throw new Exception(error.toString());
                    }
                    
                    
                    //Se valida el proceso a realizar creación o actualización
                    if(objeto.getActualizar()){
                        PeAfiliadosPrograma afiliadoConsulta = new PeAfiliadosPrograma();
                        int cantidad = getPeAfiliadosProgramaRemoto().consultarCantidadProgramaEstado(objeto.getAsegAfiliado().getId(), objeto.getPePrograma().getId(), PeConstantes.PE_PROGRAMA_ESTADO_NULL);
                        if (cantidad == 0) {
                            error.append(String.format("No es posible actualizar el registro ya que el afiliado no cuenta con matricula en el programa de %s, por favor validar.", objeto.getPePrograma().getDescripcionPrograma()));
                        } else if (cantidad > 1) {
                            cantidad = getPeAfiliadosProgramaRemoto().consultarCantidadProgramaEstado(objeto.getAsegAfiliado().getId(), objeto.getPePrograma().getId(), PeConstantes.PE_PROGRAMA_ACTIVO);
                            if(cantidad == 0){
                                error.append(String.format("No es posible actualizar el registro ya que el afiliado no cuenta con matricula activa en el programa de %s, por favor validar.", objeto.getPePrograma().getDescripcionPrograma()));
                            }else if(cantidad > 1){
                                error.append(String.format("El afiliado se encuentra con más de una matricula activa en el mismo programa de %s, por favor validar.", objeto.getPePrograma().getDescripcionPrograma()));
                            }else if(cantidad == 1){
                                afiliadoConsulta = getPeAfiliadosProgramaRemoto().consultarAfiliadoPorProgramaEstado(objeto.getAsegAfiliado().getId(), objeto.getPePrograma().getId(), true).get(0);
                            }                            
                        } else if (cantidad == 1) {
                            afiliadoConsulta = getPeAfiliadosProgramaRemoto().consultarAfiliadoPorPrograma(objeto.getAsegAfiliado().getId(), objeto.getPePrograma().getId());
                        }
                        
                        afiliadoConsulta.setPeAfiliadoDiagnosticoList(objeto.getPeAfiliadoDiagnosticoList());
                        afiliadoConsulta.setFechaInicioPrograma(objeto.getFechaInicioPrograma());
                        afiliadoConsulta.setFechaDiagnostico(objeto.getFechaDiagnostico());
                        afiliadoConsulta.setFechaFinPrograma(objeto.getFechaFinPrograma());
                        afiliadoConsulta.setMaeRegionCorporalId(objeto.getMaeRegionCorporalId());
                        afiliadoConsulta.setMaeRegionCorporalCodigo(objeto.getMaeRegionCorporalCodigo());
                        afiliadoConsulta.setMaeRegionCorporalValor(objeto.getMaeRegionCorporalValor());
                        afiliadoConsulta.setCntPrestadorSede(objeto.getCntPrestadorSede());
                        afiliadoConsulta.setActivo(objeto.getActivo());  
                        afiliadoConsulta.setAdherente(objeto.getAdherente());
                        afiliadoConsulta.setDisentimiento(objeto.getDisentimiento());
                        
                        if (estado_causa != null) {
                            if (objeto.getActivo()) {
                                afiliadoConsulta.setMaeCausaActivoId(estado_causa.getId());
                                afiliadoConsulta.setMaeCausaActivoCodigo(estado_causa.getValor());
                                afiliadoConsulta.setMaeCausaActivoValor(estado_causa.getNombre());
                            } else {
                                afiliadoConsulta.setMaeCausaInactivoId(estado_causa.getId());
                                afiliadoConsulta.setMaeCausaInactivoCodigo(estado_causa.getValor());
                                afiliadoConsulta.setMaeCausaInactivoValor(estado_causa.getNombre());
                            }
                        } else {
                            error.append("No se encontro ningún causa estado. ");
                        }
                        
                        if (!objeto.getActivo()) {
                            if (objeto.getFechaFinPrograma() == null) {
                                error.append("No se encontro Fecha Egreso. ");
                            }
                        }
                        
                        if (!error.toString().isEmpty()) {
                            throw new Exception(error.toString());
                        }
                        
                        //actualizar
                        telefono.setFechaHoraModifica(carga.getFechaHoraCrea());
                        telefono.setTerminalModifica(carga.getTerminalCrea());
                        telefono.setUsuarioModifica(carga.getUsuarioCrea());

                        afiliadoConsulta.setFechaHoraModifica(carga.getFechaHoraCrea());
                        afiliadoConsulta.setTerminalModifica(carga.getTerminalCrea());
                        afiliadoConsulta.setUsuarioModifica(carga.getUsuarioCrea());
                        getPeAfiliadosProgramaRemoto().actualizar(afiliadoConsulta);
                        
                        //Se consulta el diagnostico principal asociado
                        PeAfiliadoDiagnostico diagnosticoPrincipal = getPeAfiliadoDiagnosticoRemoto().consultarDiagnosticoPrincipal(afiliadoConsulta.getId());
                        if(diagnosticoPrincipal != null && diagnosticoPrincipal.getMaDiagnosticosId() != afiliadoConsulta.getPeAfiliadoDiagnosticoList().get(0).getMaDiagnosticosId()){
                            diagnosticoPrincipal.setFechaHoraModifica(carga.getFechaHoraCrea());
                            diagnosticoPrincipal.setTerminalModifica(carga.getTerminalCrea());
                            diagnosticoPrincipal.setUsuarioModifica(carga.getUsuarioCrea());
                            getPeAfiliadoDiagnosticoRemoto().removerDiagnosticoPrincipal(diagnosticoPrincipal);
                        }
                        
                        //List<PeAfiliadoDiagnostico> diagnosticosAfiliado = getPeAfiliadoDiagnosticoRemoto().getDialDiagnosticosAfiliadoPrograma(objeto.getId());
                        for (PeAfiliadoDiagnostico diagnostico : afiliadoConsulta.getPeAfiliadoDiagnosticoList()) {
                            diagnostico.setPeAfiliadosProgramasId(new PeAfiliadosPrograma(afiliadoConsulta.getId()));
                            PeAfiliadoDiagnostico existe = getPeAfiliadoDiagnosticoRemoto().consultarAfiliadoPrograma(afiliadoConsulta.getId(), diagnostico.getMaDiagnosticosCodigo());
                            if (existe == null) {
                                getPeAfiliadoDiagnosticoRemoto().insertar(diagnostico);
                            } else {
                                diagnostico.setId(existe.getId());
                                getPeAfiliadoDiagnosticoRemoto().actualizar(diagnostico);
                            }
                        }
                        if (telefono_fijo != null && !telefono_fijo.isEmpty() && existe_tel_fijo.getId() == null) {
                            telefono.setTipo(TIPO_TELEFONO_FIJO);
                            telefono.setNumero(telefono_fijo);
                            getPeTelefonosRemoto().insertarTelefonosAfiliadosProgramas(telefono);
                        }
                        if (telefono_movil != null && !telefono_movil.isEmpty() && existe_tel_celular.getId() == null) {
                            telefono.setTipo(TIPO_TELEFONO_MOVIL);
                            telefono.setNumero(telefono_movil);
                            getPeTelefonosRemoto().insertarTelefonosAfiliadosProgramas(telefono);
                        }
                        PeCargaHistorico historico = castearHistorico(afiliadoConsulta);
                        historico.setPeCargaId(carga);
                        historico.setFechaHoraCrea(carga.getFechaHoraCrea());
                        historico.setTerminalCrea(carga.getTerminalCrea());
                        historico.setUsuarioCrea(carga.getUsuarioCrea());
                        historico.setId(getPeCargaHistoricoRemoto().insertar(historico));
                    }else{
                        int cantidad = getPeAfiliadosProgramaRemoto().consultarCantidadProgramaEstado(objeto.getAsegAfiliado().getId(), objeto.getPePrograma().getId(), PeConstantes.PE_PROGRAMA_ESTADO_NULL);
                        if (objeto.getPePrograma().getCantidadRegistro() == 0) {
                            if (cantidad >= 1) {
                                error.append(String.format("El afiliado ya cuenta con una matricula en el mismo programa de %s, por favor validar.", objeto.getPePrograma().getDescripcionPrograma()));
                            }
                        }else{
                            Integer activo = getPeAfiliadosProgramaRemoto().consultarCantidadProgramaEstado(objeto.getAsegAfiliado().getId(), objeto.getPePrograma().getId(), PeConstantes.PE_PROGRAMA_ACTIVO);
                            if (activo > 0) {
                                error.append("El afiliado ya tiene una matrícula para este programa de ").append(objeto.getPePrograma().getDescripcionPrograma()).append(" en estado ACTIVO; no se podrá generar un nuevo registro si el usuario se encuentra vigente en el programa.");
                            }
                        }
                        

                        objeto.setTipoPaciente(validarTipoPaciente(objeto.getFechaInicioPrograma()));
                        if (!objeto.getActivo()) {
                            if (objeto.getFechaFinPrograma() == null) {
                                error.append("No se encontro Fecha Egreso. ");
                            }
                        }

                        if (objeto.getAsegAfiliado().getId() != null && objeto.getPePrograma().getId() != null) {
                            boolean sexoAplica = validarSexoAplicaPrograma(objeto.getAsegAfiliado(), objeto.getPePrograma());
                            if (!sexoAplica) {
                                error.append("El género del afiliado no corresponde al permitido por el programa, el programa ").append(objeto.getPePrograma().getDescripcionPrograma()).append(" solo permite género ").append(PeConstantes.getListaSexoAplicaDescripcion(objeto.getPePrograma().getSexoAplica())).append(". ");
                            }
                        }                        

                        if (estado_causa != null) {
                            if (objeto.getActivo()) {
                                objeto.setMaeCausaActivoId(estado_causa.getId());
                                objeto.setMaeCausaActivoCodigo(estado_causa.getValor());
                                objeto.setMaeCausaActivoValor(estado_causa.getNombre());
                            } else {
                                objeto.setMaeCausaInactivoId(estado_causa.getId());
                                objeto.setMaeCausaInactivoCodigo(estado_causa.getValor());
                                objeto.setMaeCausaInactivoValor(estado_causa.getNombre());
                            }
                        } else {
                            error.append("No se encontro ningún causa estado. ");
                        }

                        if (objeto.getPePrograma() != null && objeto.getCntPrestadorSede() != null) {
                            List<PeIpsPrograma> listIpsProgramas = getPeIpsProgramaRemoto().consultarPorProgramaEIPS(objeto.getPePrograma().getId(), objeto.getCntPrestadorSede().getId());

                            if (listIpsProgramas.isEmpty()) {
                                error.append("El programa no cuenta con esta Ips matriculada. ");
                            }
                        }

                        if (objeto.getMaeMedioDx() != null) {
                            objeto.setMaeMedioDx(getMaestroRemoto().consultarPorValorTipo(objeto.getMaeMedioDx().getValor(), MaestroTipo.GN_MEDIO_DIAGNOSTICO));

                            if (objeto.getMaeMedioDx() != null) {
                                objeto.setMaeMedioDxId(objeto.getMaeMedioDx().getId());
                                objeto.setMaeMedioDxCodigo(objeto.getMaeMedioDx().getValor());
                                objeto.setMaeMedioDxValor(objeto.getMaeMedioDx().getNombre());
                            } else {
                                error.append("No se encontro el medio diagnóstico.");
                            }
                        }

                        objeto.setFuenteOrigen(PeConstantes.ORIGEN_CARGA_MASIVA);

                        if (!error.toString().isEmpty()) {
                            throw new Exception(error.toString());
                        }
                                                  
                        //insertar
                        int idAfiliadoPrograma = getPeAfiliadosProgramaRemoto().insertar(objeto);
                        for (PeAfiliadoDiagnostico diagnostico : objeto.getPeAfiliadoDiagnosticoList()) {
                            diagnostico.setPeAfiliadosProgramasId(new PeAfiliadosPrograma(idAfiliadoPrograma));
                            PeAfiliadoDiagnostico existe = getPeAfiliadoDiagnosticoRemoto().consultarAfiliadoPrograma(idAfiliadoPrograma, diagnostico.getMaDiagnosticosCodigo());
                            if (existe == null) {
                                getPeAfiliadoDiagnosticoRemoto().insertar(diagnostico);
                            } else {
                                error.append("Ya existe matriculado un diagnostico con codigo " + diagnostico.getMaDiagnosticosCodigo() + " para el afilaido en el programa. ");
                            }
                        }
                        if (telefono_fijo != null && !telefono_fijo.isEmpty() && existe_tel_fijo.getId() == null) {
                            telefono.setTipo(TIPO_TELEFONO_FIJO);
                            telefono.setNumero(telefono_fijo);
                            getPeTelefonosRemoto().insertarTelefonosAfiliadosProgramas(telefono);
                        }
                        if (telefono_movil != null && !telefono_movil.isEmpty() && existe_tel_celular.getId() == null) {
                            telefono.setTipo(TIPO_TELEFONO_MOVIL);
                            telefono.setNumero(telefono_movil);
                            getPeTelefonosRemoto().insertarTelefonosAfiliadosProgramas(telefono);
                        }                       
                        
                    }
                    contenidoArchivo.append(objeto.getProgramaCargaMasiva());
                    contenidoArchivo.append(" ");
                    contenidoArchivo.append(EXITOSO_STR);
                    contenidoArchivo.append(" ");
                    contenidoArchivo.append("\n");
                    exitosos++;
                } else {
                    fallidos++;
                    contenidoArchivo.append(objeto.getProgramaCargaMasiva());
                    contenidoArchivo.append(" ");
                    contenidoArchivo.append(FALLIDO_STR);
                    contenidoArchivo.append(" ").append(objeto.getErrorCarga());
                    contenidoArchivo.append("\n");
                }
            } catch (Exception ex) {
                //Aqui se controla el error                
                fallidos++;
                contenidoArchivo.append(objeto.getProgramaCargaMasiva());
                contenidoArchivo.append(" ");
                contenidoArchivo.append(FALLIDO_STR);
                contenidoArchivo.append(" ");
                contenidoArchivo.append(ex.getMessage());
                contenidoArchivo.append("\n");
            }
            pos++;
            try {
                //2020-07-17 jyperez Se valida el estado de carga. Si es Abortado, entonces se procede a finalizar la carga
                PeCarga cargaAux = getCargaMasivaRemoto().consultar(carga.getId());
                if (cargaAux.getEstado() == ESTADO_CARGA_ABORTADO) {
                    carga.setEstado(ESTADO_CARGA_ABORTADO);
                    carga.setUsuarioModifica(cargaAux.getUsuarioModifica());
                    carga.setExitosos(exitosos);
                    carga.setFallidos(fallidos);
                    carga.setFechaHoraFin(new Date());
                    getCargaMasivaRemoto().actualizar(carga);
                    if (pos != listaCarga.size()) {
                        for (int i = pos++; i < listaCarga.size(); i++) {
                            PeAfiliadosPrograma objetoFallido = listaCarga.get(i);
                            contenidoArchivo.append(objetoFallido.getProgramaCargaMasiva());
                            contenidoArchivo.append(" ");
                            contenidoArchivo.append(CANCELADO_STR);
                            contenidoArchivo.append(" ");
                            contenidoArchivo.append("");
                            contenidoArchivo.append("\n");
                        }
                    }
                    generarArchivo(contenidoArchivo);
                    return;
                }
            } catch (Exception ex) {
                Logger.getLogger(CargaMasivaProgramaHilo.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Actualizar estado de carga
            carga.setExitosos(exitosos);
            carga.setFallidos(fallidos);
            carga.setEstado(ESTADO_CARGA_PROCESANDO);
            try {
                getCargaMasivaRemoto().actualizar(carga);
            } catch (Exception ex) {
                Logger.getLogger(CargaMasivaProgramaHilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        generarArchivo(contenidoArchivo);
        if (pos > 0) {
            //Actualizar estado de carga
            carga.setExitosos(exitosos);
            carga.setFallidos(fallidos);
            carga.setFechaHoraFin(new Date());
            carga.setEstado(ESTADO_CARGA_PROCESADO);
            try {
                getCargaMasivaRemoto().actualizar(carga);
            } catch (Exception ex) {
                Logger.getLogger(CargaMasivaProgramaHilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void obtenerListaCargaAfiliados() {
        try {
            listaCarga = new ArrayList<>();
            FileReader archivo;
            BufferedReader buffer;
            PeAfiliadosPrograma objeto;
            // lectura del archivo en ruta
            String texto;
            archivo = new FileReader(this.carga.getRuta() + this.carga.getArchivo());
            buffer = new BufferedReader(new InputStreamReader(new FileInputStream(this.carga.getRuta() + this.carga.getArchivo()), "utf-8"));
            // leemos la primera linea pero no se usará. esto debido a que es el encabezado.
            texto = buffer.readLine();
            while ((texto = buffer.readLine()) != null) {
                // obtención de campo a campo del valor y transformación
                objeto = new PeAfiliadosPrograma();
                objeto = obtenerAfiliadoPrograma(texto);
                objeto.setProgramaCargaMasiva(texto);
                objeto.setSincronizado(0);
                objeto.setUsuarioCrea(this.carga.getUsuarioCrea());
                objeto.setTerminalCrea(this.carga.getTerminalCrea());
                objeto.setFechaHoraCrea(this.carga.getFechaHoraCrea());
                // se guarda el objeto afiliado creado a partir de la lista en listaCarga.
                listaCarga.add(objeto);
            }
            // termina el proceso.
            buffer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CargaMasivaProgramaHilo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargaMasivaProgramaHilo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
    }

    private PeAfiliadosPrograma obtenerAfiliadoPrograma(String texto) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        PeAfiliadosPrograma obj = new PeAfiliadosPrograma();
        obj.setAsegAfiliado(new AsegAfiliado());
        int i = 0;
        String[] campos;
        String aux;
        obj.setErrorCarga("");
        obj.getAsegAfiliado().setErrorCarga("");
        // valores por defecto de los campos obligatorios
        obj.getAsegAfiliado().setRegimen(REGIMEN_SUBSIDIADO);
        obj.getAsegAfiliado().setModeloLiquidacion(MODELO_LIQUIDACION_EVENTO);
        obj.getAsegAfiliado().setIdAfiliado("0");
        PeAfiliadoDiagnostico diagnosticoPrincipal = null;

        //guardamos el registro completo en el objeto
        obj.getAsegAfiliado().setRegistroArchivo(texto);
        campos = texto.split(",", 50);
        //consecutivo
        if (campos[i] == null || campos[i].trim().equals("")) {
            aux = obj.getErrorCarga() + "[consecutivo]: valor nulo. ";
            obj.setErrorCarga(aux);
        }else{
           aux = validarCampoNumerico(campos[i].trim()); 
           if(!aux.equals("")){
                aux = obj.getErrorCarga() + "[consecutivo]: " + aux;
                obj.setErrorCarga(aux);
            }
        }
        i++;
        //actualizar
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarCampoNumerico(campos[i].trim(), 1);
            if (aux.equals("")) {
                if(Integer.parseInt(campos[i].trim()) == 1){
                    obj.setActualizar(true);
                }else{
                    obj.setActualizar(false);
                }                
            } else {
                aux = obj.getErrorCarga() + "[actualizar]: " + aux;
                obj.setErrorCarga(aux);
            }
        }
        i++;
        //tipo_documento
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux=validarCampoTexto(campos[i].trim());
            if(aux.isEmpty()){
                Maestro maTipoDoc = hashTipoDocumento.get(campos[i].trim());
                if(maTipoDoc != null){
                    obj.getAsegAfiliado().setMaeTipoDocumento(maTipoDoc.getId());
                }else{
                    aux = obj.getAsegAfiliado().getErrorCarga() + "[tipo_documento]: El valor indicado no se encuentra dentro de los valores permitidos. ";
                    obj.getAsegAfiliado().setErrorCarga(aux);
                }
            }else{
                aux = obj.getAsegAfiliado().getErrorCarga() + "[tipo_documento]: " + aux;
                obj.getAsegAfiliado().setErrorCarga(aux);
            }            
        } else {
            aux = obj.getErrorCarga() + "[tipo_documento]: valor obligatorio.";
            obj.setErrorCarga(aux);
        }
        i++;

        //numero_documento
        if (campos[i] != null && !campos[i].trim().equals("")) {
            obj.getAsegAfiliado().setNumeroDocumento(campos[i].trim());
        } else {
            aux = obj.getErrorCarga() + "[numero_documento]: " + "valor obligatorio.";
            obj.setErrorCarga(aux);
        }
        i++;

        //primer_apellido
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarCampoTexto(campos[i].trim());
            if (aux.equals("")) {
                obj.getAsegAfiliado().setPrimerApellido(campos[i].trim());
            } else {
                aux = obj.getAsegAfiliado().getErrorCarga() + "[primer_apellido]: " + aux;
                obj.getAsegAfiliado().setErrorCarga(aux);
            }
        } else {
            aux = obj.getErrorCarga() + "[primer_apellido]: valor obligatorio.";
            obj.setErrorCarga(aux);
        }
        i++;

        //segundo_apellido
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarCampoTexto(campos[i].trim());
            if (aux.equals("")) {
                obj.getAsegAfiliado().setSegundoApellido(campos[i].trim());
            } else {
                aux = obj.getErrorCarga() + "[segundo_apellido]: " + aux;
                obj.setErrorCarga(aux);
            }
        }
        i++;

        //primer_nombre
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarCampoTexto(campos[i].trim());
            if (aux.equals("")) {
                obj.getAsegAfiliado().setPrimerNombre(campos[i].trim());
            } else {
                aux = obj.getErrorCarga() + "[primer_nombre]: " + aux;
                obj.setErrorCarga(aux);
            }
        } else {
            aux = obj.getErrorCarga() + "[primer_nombre]: valor obligatorio.";
            obj.setErrorCarga(aux);
        }
        i++;

        //segundo_nombre
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarCampoTexto(campos[i].trim());
            if (aux.equals("")) {
                obj.getAsegAfiliado().setSegundoNombre(campos[i].trim());
            } else {
                aux = obj.getErrorCarga() + "[segundo_nombre]: " + aux;
                obj.setErrorCarga(aux);
            }
        }
        i++;

        //fecha_nacimiento
        try {
            obj.getAsegAfiliado().setFechaNacimiento(sdf.parse(campos[i].trim()));
        } catch (ParseException ex) {
            obj.getAsegAfiliado().setFechaNacimiento(null);
            aux = obj.getErrorCarga() + "[fecha_nacimiento]: valor obligatorio.";
            obj.setErrorCarga(aux);
        }
        i++;

        //numero_telefono
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarCampoNumerico(campos[i].trim(), 10);
            if (aux.equals("")) {                
                String mensaje = validarTelefonosFijosNoPermitidos(campos[i].trim());
                if(mensaje.isEmpty()){
                    obj.getAsegAfiliado().setTelefonoFijo(campos[i].trim());
                }else{
                    aux = obj.getErrorCarga() + "[telefono_fijo]: " + mensaje;
                obj.setErrorCarga(aux);
                }
            } else {
                aux = obj.getErrorCarga() + "[telefono_fijo]: " + aux;
                obj.setErrorCarga(aux);
            }
        }
        i++;

        //numero_movil
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarCampoNumerico(campos[i].trim(), 10);
            if (aux.equals("")) {
                String mensaje = validarRangoTelefonoMovil(campos[i].trim());
                if(mensaje.isEmpty()){
                    obj.getAsegAfiliado().setTelefonoMovil(campos[i].trim());
                }else{
                    aux = obj.getErrorCarga() + "[telefono_movil]: " + mensaje;
                    obj.setErrorCarga(aux);
                }                
            } else {
                aux = obj.getErrorCarga() + "[telefono_movil]: " + aux;
                obj.setErrorCarga(aux);
            }
        }
        i++;
        
        //diagnosito principal
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarCampoTextoNumero(campos[i].trim());
            if (aux.equals("")) {
                PeAfiliadoDiagnostico diagnostico = new PeAfiliadoDiagnostico();
                diagnostico.setMaDiagnosticosCodigo(campos[i].trim());
                diagnostico.setPrincipal(true);
                obj.getPeAfiliadoDiagnosticoList().add(diagnostico);
                diagnosticoPrincipal = diagnostico;
            } else {
                aux = obj.getErrorCarga() + "[diagnostico_principal]: " + aux;
                obj.setErrorCarga(aux);
            }
        } else {
            aux = obj.getErrorCarga() + "[diagnostico_principal]: " + "valor obligatorio.";
            obj.setErrorCarga(aux);
        }
        i++;

        //diagnostico_2
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarCampoTextoNumeroSeprarador(campos[i].trim());
            if (aux.equals("")) {
                boolean separadores = campos[i].contains("|");
                if (separadores) {
                    String[] diagnosticos = campos[i].split("\\|");
                    for (String item : diagnosticos) {
                        PeAfiliadoDiagnostico diagnostico = new PeAfiliadoDiagnostico();
                        diagnostico.setMaDiagnosticosCodigo(item);
                        diagnostico.setPrincipal(false);
                        obj.getPeAfiliadoDiagnosticoList().add(diagnostico);
                        if (diagnosticoPrincipal != null && diagnosticoPrincipal.getMaDiagnosticosCodigo().equalsIgnoreCase(diagnostico.getMaDiagnosticosCodigo())) {
                            aux = obj.getErrorCarga() + "[diagnostico_principal]: " + "El codigo del diagnostico principal se encuentra en diagnostico_secundario. ";
                            obj.setErrorCarga(aux);
                        }
                    }
                } else {
                    PeAfiliadoDiagnostico diagnostico = new PeAfiliadoDiagnostico();
                    diagnostico.setMaDiagnosticosCodigo(campos[i]);
                    diagnostico.setPrincipal(false);
                    obj.getPeAfiliadoDiagnosticoList().add(diagnostico);
                    if (diagnosticoPrincipal != null && diagnosticoPrincipal.getMaDiagnosticosCodigo().equalsIgnoreCase(diagnostico.getMaDiagnosticosCodigo())) {
                        aux = obj.getErrorCarga() + "[diagnostico_principal]: " + "El codigo del diagnostico principal se encuentra en diagnostico_secundario. ";
                        obj.setErrorCarga(aux);
                    }
                }
            } else {
                aux = obj.getErrorCarga() + "[diagnostico_secundario]: " + aux;
                obj.setErrorCarga(aux);
            }
        }
        i++;

        //fecha_diagnostico
        try {
            if (campos[i] != null && !campos[i].trim().equals("")) {
                obj.setFechaDiagnostico(sdf.parse(campos[i].trim()));
            }            
        } catch (ParseException ex) {
            obj.setFechaDiagnostico(null);
            aux = obj.getErrorCarga() + "[fecha_diagnostico]: "+ex.getMessage()+". ";
            obj.setErrorCarga(aux);
        }
        i++;

        //region_corporal
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarCampoTextoNumero(campos[i].trim());
            if (aux.equals("")) {
                obj.setMaeRegionCorporal(new Maestro());
                obj.getMaeRegionCorporal().setValor(campos[i].trim());
            } else {
                aux = obj.getErrorCarga() + "[region_corporal]: " + aux;
                obj.setErrorCarga(aux);
            }
        }
        i++;

        //medio_diagnostico
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarCampoTextoNumero(campos[i].trim());
            if (aux.equals("")) {
                obj.setMaeMedioDx(new Maestro());
                obj.getMaeMedioDx().setValor(campos[i].trim());
            } else {
                aux = obj.getErrorCarga() + "[medio_diagnostico]: " + aux;
                obj.setErrorCarga(aux);
            }
        }
        i++;

        //estado_programa
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarCampoBoolean(campos[i].trim());
            if (aux.equals("")) {
                obj.setActivo(!campos[i].trim().equals("0"));
            } else {
                aux = obj.getErrorCarga() + "[estado_programa]: " + aux;
                obj.setErrorCarga(aux);
            }
        } else {
            aux = obj.getErrorCarga() + "[estado_programa]: " + "valor obligatorio.";
            obj.setErrorCarga(aux);
        }
        i++;

        //causa_estado
        if (campos[i] != null && !campos[i].trim().equals("")) {
            //2021-01-18 jyperez se elimina validación de tamaño
            aux = validarCampoNumerico(campos[i].trim());
            if (aux.equals("")) {
                if (obj.getActivo()) {
                    obj.setMaeCausaActivoObj(new Maestro());
                    obj.getMaeCausaActivoObj().setValor(campos[i].trim());
                } else {
                    obj.setMaeCausaInactivoObj(new Maestro());
                    obj.getMaeCausaInactivoObj().setValor(campos[i].trim());
                }
            } else {
                aux = obj.getErrorCarga() + "[causa_estado]: " + aux;
                obj.setErrorCarga(aux);
            }
        } else {
            aux = obj.getErrorCarga() + "[causa_estado]: valor obligatorio.";
            obj.setErrorCarga(aux);
        }
        i++;

        //fecha_ingreso
        try {
            obj.setFechaInicioPrograma(sdf.parse(campos[i].trim()));
        } catch (ParseException ex) {
            obj.setFechaDiagnostico(null);
            aux = obj.getErrorCarga() + "[fecha_ingreso]: valor obligatorio.";
            obj.setErrorCarga(aux);
        }
        i++;

        //fecha_egreso
        try {
            if (!campos[i].trim().equals("")) {
                obj.setFechaFinPrograma(sdf.parse(campos[i].trim()));
            }
        } catch (ParseException ex) {
            obj.setFechaDiagnostico(null);
            aux = obj.getErrorCarga() + "[fecha_egreso]: valor obligatorio.";
            obj.setErrorCarga(aux);
        }
        i++;

        //codigo_ips
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarCampoTextoNumero(campos[i].trim());
            if (aux.equals("")) {
                obj.setCntPrestadorSede(new CntPrestadorSede());
                obj.getCntPrestadorSede().setCodigoHabilitacionSede(campos[i].trim());
                boolean validar = false;
                if (carga.getCodigoHabilitacion() == null) {
                    validar = true;
                } else if (obj.getCntPrestadorSede().getCodigoHabilitacionSede().contains(carga.getCodigoHabilitacion())) {
                    validar = true;
                }
                if (!validar) {
                    aux = obj.getErrorCarga() + "[codigo_habilitacion_ips]: " + aux + " el código de habilitación no corresponde a la empresa. ";
                    obj.setErrorCarga(aux);
                }
            } else {
                aux = obj.getErrorCarga() + "[codigo_habilitacion_ips]: " + aux;
                obj.setErrorCarga(aux);
            }
        } else {
            aux = obj.getErrorCarga() + "[codigo_habilitacion_ips]: " + "valor obligatorio.";
            obj.setErrorCarga(aux);
        }
        i++;
        
        //adherente
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarBooleanNumerico(campos[i].trim());
            if (aux.equals("")) {
                obj.setAdherente(campos[i].trim().equals("1"));
            } else {
                aux = obj.getErrorCarga() + "[adherente]: " + aux;
                obj.setErrorCarga(aux);
            }
        } else {
            aux = obj.getErrorCarga() + "[adherente]: " + "valor obligatorio.";
            obj.setErrorCarga(aux);
        }
        i++;
        
        //disentimiento
        if (campos[i] != null && !campos[i].trim().equals("")) {
            aux = validarBooleanNumerico(campos[i].trim());
            if (aux.equals("")) {
                obj.setDisentimiento(campos[i].trim().equals("1"));
            } else {
                aux = obj.getErrorCarga() + "[disentimiento]: " + aux;
                obj.setErrorCarga(aux);
            }
        } else {
            aux = obj.getErrorCarga() + "[disentimiento]: " + "valor obligatorio.";
            obj.setErrorCarga(aux);
        }
        i++;        
        return obj;
    }


    private String validarCampoTexto(String texto) {
        String mensaje = "";
        Pattern patron = Pattern.compile("[A-ZÑÁÀÂÄÉÈÊËÍÌÎÏÓÒÔÖÚÙÛÜ.\\s]+");
        Matcher emparejador = patron.matcher(texto);
        if (!emparejador.matches()) {
            mensaje = "no cumple con el formato Texto.";
        }
        return mensaje;
    }

    private String validarCampoTextoNumero(String texto) {
        String mensaje = "";
        Pattern patron = Pattern.compile("[a-zA-Z0-9]+");
        Matcher emparejador = patron.matcher(texto);
        if (!emparejador.matches()) {
            mensaje = "no cumple con el formato Texto.";
        }
        return mensaje;
    }

    private String validarCampoTextoNumeroSeprarador(String texto) {
        String mensaje = "";
        Pattern patron = Pattern.compile("[a-zA-Z0-9|]+");
        Matcher emparejador = patron.matcher(texto);
        if (!emparejador.matches()) {
            mensaje = "no cumple con el formato Texto.";
        }
        return mensaje;
    }

    private String validarCampoNumerico(String numero, int tamanio) {
        String mensaje;
        if (numero.matches("\\d*") && numero.length() == tamanio) {
            mensaje = "";
        } else if (!numero.matches("\\d*")) {
            mensaje = "no cumple con el formato numérico.";
        } else {
            mensaje = "no cumple con el formato igual a " + tamanio + " dígitos.";
        }

        return mensaje;
    }

    private String validarCampoNumerico(String numero) {
        String mensaje;
        if (numero.matches("\\d*")) {
            mensaje = "";
        } else {
            mensaje = "no cumple con el formato numérico.";
        }
        return mensaje;
    }

    private String validarCampoBoolean(String numero) {
        String mensaje;
        if (numero.matches("\\d*") && (numero.equals("1") || numero.equals("0"))) {
            mensaje = "";
        } else if (!numero.matches("\\d*")) {
            mensaje = "no cumple con el formato boolean.";
        } else {
            mensaje = "no cumple con el formato igual a " + 1 + " dígito.";
        }

        return mensaje;
    }

    private String validarCampoNumericoTamanioMaximo(String numero, int tamanio) {
        String mensaje;
        if (numero.matches("\\d*") && (numero.length() > 0 && numero.length() <= tamanio)) {
            mensaje = "";
        } else if (!numero.matches("\\d*")) {
            mensaje = "no cumple con el formato numérico.";
        } else {
            mensaje = "no cumple con el formato menor que " + tamanio + " dígitos.";
        }

        return mensaje;
    }
    
    private String validarBooleanNumerico(String numero) {
        String mensaje;
        if (!numero.matches("\\d*")) {
            return "no cumple con el formato numérico.";
        }
        if(Integer.parseInt(numero) > 1 || Integer.parseInt(numero) < 0){
            return "no cumple con los valores permitidos 0 y 1. ";
        }
        return "";
    }

    private void generarArchivo(StringBuilder contenidoArchivo) {
        File archivo;
        BufferedWriter bw = null;
        try {
            archivo = new File(carga.getRuta(), carga.getArchivo());
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write("consecutivo,actualizar,tipo_documento,numero_documento,primer_apellido,segundo_apellido,primer_nombre,segundo_nombre,fecha_nacimiento,numero_telefono,numero_celular,dx_principal,dx_secundarios,fecha_diagnostico,region_corporal,medio_diagnostico,estado_programa,causa_estado,fecha_ingreso,fecha_egreso,codigo_hibilitacion_ips,adherente,disentimiento");
            bw.write("\n");
            bw.write(contenidoArchivo.toString());
            bw.close();
        } catch (Exception e) {
            Logger.getLogger(CargaMasivaProgramaHilo.class.getName()).log(Level.SEVERE, null, "Error creando el archivo " + carga.getRuta() + e.getMessage());
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException ex1) {
                }
            }
        }
    }

    private PeCargaHistorico castearHistorico(PeAfiliadosPrograma objeto) {
        PeCargaHistorico historico = new PeCargaHistorico();
        historico.setTipo(PeCargaHistorico.ORIGINAL);
        historico.setPeAfiliadoProgramaId(objeto);
        historico.setCntPrestadorSedeId(objeto.getCntPrestadorSede());
        historico.setFechaDiagnostico(objeto.getFechaDiagnostico());
        historico.setFechaInicioPrograma(objeto.getFechaInicioPrograma());
        historico.setFechaFinPrograma(objeto.getFechaFinPrograma());
        historico.setMaeRegionCorporalId(objeto.getMaeRegionCorporalId());
        historico.setMaeRegionCorporalCodigo(objeto.getMaeRegionCorporalCodigo());
        historico.setMaeRegionCorporalValor(objeto.getMaeRegionCorporalValor());
        return historico;
    }

    private boolean validarEstadoAfiliado(int maeEstadoAfiliacion) {
        try {
            Maestro maeEstado = getMaestroRemoto().consultar(maeEstadoAfiliacion);
            return maeEstado.contieneAccion(MaestroAccion.ASEG_ESTADO_AFILIACION_AFILIADO_ACTIVO);
        } catch (Exception ex) {
            return false;
        }
    }

    private Integer validarTipoPaciente(Date fechaInicio) {
        Integer tipoPaciente = 0;
        try {
            if (fechaInicio != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
                String strDiaActual = sdf.format(new Date());
                String strDiaInicio = sdf.format(fechaInicio);
                Long dia = ChronoUnit.DAYS.between(LocalDate.parse(strDiaInicio), LocalDate.parse(strDiaActual));
                //System.err.println("Dias diferencia : " + dia);
                if (dia < 90) {
                    return PeConstantes.PE_TIPO_PACIENTE_NUEVO;
                }
                if (dia > 90) {
                    return PeConstantes.PE_TIPO_PACIENTE_PREVALENTE;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tipoPaciente;
    }

    private boolean validarSexoAplicaPrograma(AsegAfiliado afiliado, PePrograma programa) {
        if (programa.getSexoAplica() == PeConstantes.APLICA_SEXO_AMBOS) {
            return true;
        }
        if (!PeConstantes.getCodigoSexoAplica(programa.getSexoAplica()).equalsIgnoreCase(afiliado.getMaeGeneroCodigo())) {
            return false;
        }
        return true;
    }
    
    private boolean validarSexoAplicaDiagnostico(AsegAfiliado afiliado, MaDiagnostico diagnostico){
        if(diagnostico.getSexoAplica() == PeConstantes.SEXO_APLICA_AMBOS){
            return true;
        }
        if (!PeConstantes.getCodigoSexoAplicaDiagnostico(diagnostico.getSexoAplica()).equalsIgnoreCase(afiliado.getMaeGeneroCodigo())) {
            return false;
        }
        return true;
        
    }
    
    private String validarRangoTelefonoMovil(String movil) {
        String mensaje = "";
        int prefijo = 0;
        //2020-07-27 jyperez se controla excepción debido a que hay datos erróneos ingresados por carga masiva - INC 261
        try{
            if(movil != null && !movil.equals("") && !movil.isBlank()) {
                if (movil.length() >= 3) {
                    prefijo = Integer.valueOf(movil.substring(0, 3));
                    if (prefijo >= 300 && prefijo <= 350) {
                        mensaje = "";
                    } else {
                        mensaje = "El teléfono móvil no inicia entre el rango permitido de valores (300 - 350) ";
                    }

                } else {
                    mensaje = "El teléfono móvil no inicia entre el rango permitido de valores (300 - 350) ";
                }

            }
        } catch (Exception e) {
            mensaje = "El teléfono móvil no contiene un valor numérico.";
        }
        return mensaje;
    }
    
    
    private String validarTelefonosFijosNoPermitidos(String fijo) {
        String mensaje = "";
        if (fijo != null && !fijo.equals("") && !fijo.isBlank()) {
            if (PeConstantes.getNumerosFijosNoPermitidos().contains(fijo)) {
                mensaje = "el telefono fijo no puede contener una secuencia de números repetitivos, lo cual lo hace inválido.";
            }
        }
        return mensaje;
    }
    
}
