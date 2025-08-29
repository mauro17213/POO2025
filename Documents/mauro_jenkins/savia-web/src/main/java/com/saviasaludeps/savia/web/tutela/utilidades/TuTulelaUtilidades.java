/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.tutela.utilidades;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.tutela.TuGrupo;
import com.saviasaludeps.savia.dominio.tutela.TuGrupoUsuario;
import com.saviasaludeps.savia.dominio.tutela.TuGrupoUsuariosAutomatico;
import com.saviasaludeps.savia.dominio.tutela.TuTutelaEstado;
import com.saviasaludeps.savia.negocio.tutela.TuGrupoRemoto;
import com.saviasaludeps.savia.negocio.tutela.TuGrupoUsuarioRemoto;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge Eliecer perez
 */
public class TuTulelaUtilidades {
    
    
    private static TuGrupoRemoto getGrupoRemoto() throws Exception {
        return (TuGrupoRemoto) RemotoEJB.getEJBRemoto("TuGrupoServicio", TuGrupoRemoto.class.getName());
    }
    
    private static TuGrupoUsuarioRemoto getGrupoUsuarioRemoto() throws Exception {
        return (TuGrupoUsuarioRemoto) RemotoEJB.getEJBRemoto("TuGrupoUsuarioServicio", TuGrupoUsuarioRemoto.class.getName());
    }

    /**
     * Función que consulta grupo para asignar segun el estado de grupo
     *
     * @param tuTutelaEstado
     * @return
     */
    public static TuGrupoUsuariosAutomatico consultarGrupoAutomatico(TuTutelaEstado tuTutelaEstado) {
        TuGrupoUsuariosAutomatico usuariosAutomaticos = new TuGrupoUsuariosAutomatico();
        try {
            if (tuTutelaEstado != null && tuTutelaEstado.getMaeEstadoId() != null) {
                TuGrupo grupo = obtenerGrupoAutomatico(tuTutelaEstado.getMaeEstadoId());
                if (grupo != null && grupo.getId() != null) {
                    usuariosAutomaticos = obtenerGrupoUsuarioAutomatico(grupo);
                } else {
                    usuariosAutomaticos.setEstadoOperacion(false);
                    usuariosAutomaticos.setMensajeOperacion("No se ha encontrado grupo para el estado :" + tuTutelaEstado.getMaeEstadoValor());
                }
            } else{
             usuariosAutomaticos.setEstadoOperacion(false);
             usuariosAutomaticos.setMensajeOperacion("Error consulta grupo automatico : el estado suministrado no tiene identificador ");
            }
        } catch (Exception e) {
            usuariosAutomaticos.setEstadoOperacion(false);
            usuariosAutomaticos.setMensajeOperacion("Error consulta grupo automatico : "+e.toString());
        }
        return usuariosAutomaticos;
    }

    private static TuGrupoUsuariosAutomatico obtenerGrupoUsuarioAutomatico(TuGrupo grupo) {
        TuGrupoUsuariosAutomatico tuGrupoUsuariosAutomatico = new TuGrupoUsuariosAutomatico();
        tuGrupoUsuariosAutomatico.setEstadoOperacion(true);
        tuGrupoUsuariosAutomatico.setTuGrupo(grupo);
        try {
            List<TuGrupoUsuario> abogados = new ArrayList<>();
            List<TuGrupoUsuario> asistentesLegales = new ArrayList<>();
            List<TuGrupoUsuario> digitadores = new ArrayList<>();
            List<TuGrupoUsuario> enfermeros = new ArrayList<>();
            List<TuGrupoUsuario> gestores = new ArrayList<>();
            List<TuGrupoUsuario> medicos = new ArrayList<>();
            
            int ID_TIPO_AUDITORES_INICIALES = grupo.getTipoAuditorInicial() == 0 ? 
                                      TuGrupoUsuario.TIPO_GESTOR :grupo.getTipoAuditorInicial() ; 
           
            ParamConsulta paramConsulta = new ParamConsulta();
            paramConsulta.setParametroConsulta1(grupo.getId());
            paramConsulta.setParametroConsulta3(true);
            List<TuGrupoUsuario> tuGrupoUsuarios = obtenerUsuariosPorGrupo(paramConsulta);

            for (TuGrupoUsuario tuGrupoUsuario : tuGrupoUsuarios) {
                if (TuGrupoUsuario.TIPO_ABOGADO == tuGrupoUsuario.getTipo()) {
                    abogados.add(tuGrupoUsuario);
                }
                if (TuGrupoUsuario.TIPO_ASISTENTE_LEGAL == tuGrupoUsuario.getTipo()) {
                    asistentesLegales.add(tuGrupoUsuario);
                }
                if (TuGrupoUsuario.TIPO_DIGITADOR == tuGrupoUsuario.getTipo()) {
                    digitadores.add(tuGrupoUsuario);
                }
                if (TuGrupoUsuario.TIPO_ENFERMERO == tuGrupoUsuario.getTipo()) {
                    enfermeros.add(tuGrupoUsuario);
                }
                if (TuGrupoUsuario.TIPO_GESTOR == tuGrupoUsuario.getTipo()) {
                    gestores.add(tuGrupoUsuario);
                }
                if (TuGrupoUsuario.TIPO_MEDICO == tuGrupoUsuario.getTipo()) {
                    medicos.add(tuGrupoUsuario);
                }
            }
          
            if (!abogados.isEmpty()) {
                tuGrupoUsuariosAutomatico.setTuGrupoUsuarioAbogado(
                        obtenerUsuarioSegunTipo(abogados, ID_TIPO_AUDITORES_INICIALES == TuGrupoUsuario.TIPO_ABOGADO, grupo.getOrden())
                );
            }

            if (!asistentesLegales.isEmpty()) {
                tuGrupoUsuariosAutomatico.setTuGrupoUsuarioAsistenteLegal(
                        obtenerUsuarioSegunTipo(asistentesLegales,ID_TIPO_AUDITORES_INICIALES == TuGrupoUsuario.TIPO_ASISTENTE_LEGAL, grupo.getOrden())
                );
            }

            if (!digitadores.isEmpty()) {
                tuGrupoUsuariosAutomatico.setTuGrupoUsuarioDigitaror(
                        obtenerUsuarioSegunTipo(digitadores,ID_TIPO_AUDITORES_INICIALES == TuGrupoUsuario.TIPO_DIGITADOR, grupo.getOrden())
                );
            }

            if (!enfermeros.isEmpty()) {
                tuGrupoUsuariosAutomatico.setTuGrupoUsuarioEnfermero(
                        obtenerUsuarioSegunTipo(enfermeros,ID_TIPO_AUDITORES_INICIALES == TuGrupoUsuario.TIPO_ENFERMERO, grupo.getOrden())
                );
            }

            if (!gestores.isEmpty()) {
                tuGrupoUsuariosAutomatico.setTuGrupoUsuarioGestor(
                        obtenerUsuarioSegunTipo(gestores,ID_TIPO_AUDITORES_INICIALES == TuGrupoUsuario.TIPO_GESTOR, grupo.getOrden())
                );
            }

            if (!medicos.isEmpty()) {
                tuGrupoUsuariosAutomatico.setTuGrupoUsuarioMedico(
                        obtenerUsuarioSegunTipo(medicos,ID_TIPO_AUDITORES_INICIALES == TuGrupoUsuario.TIPO_MEDICO, grupo.getOrden())
                );
            }
                       
            
            if(tuGrupoUsuarios == null || tuGrupoUsuarios.isEmpty()){
                tuGrupoUsuariosAutomatico.setEstadoOperacion(false);
                tuGrupoUsuariosAutomatico.setMensajeOperacion("No se han encontrado usuarios para el grupo :"+grupo.getNombre());
            }else{
                tuGrupoUsuariosAutomatico.setMensajeOperacion("Consulta Existosa");
            }
            
        } catch (Exception e) {
             tuGrupoUsuariosAutomatico.setMensajeOperacion("Error obtenerGrupoUsuarioAutomatico : "+e.toString());
             tuGrupoUsuariosAutomatico.setEstadoOperacion(false);
        }

        return tuGrupoUsuariosAutomatico;
    }
    
    private static TuGrupo obtenerGrupoAutomatico(int idMaeTipoEstado) throws Exception {
        TuGrupo grupo = new TuGrupo();
        try {

            ParamConsulta paramConsulta = new ParamConsulta();
            paramConsulta.setParametroConsulta1(idMaeTipoEstado);
            paramConsulta.setParametroConsulta2(true);
            paramConsulta.setOrden("id");
            List<TuGrupo> tuGrupos = getGrupoRemoto().consultarListaPorEstado(paramConsulta);
            
            if(tuGrupos != null &&  !tuGrupos.isEmpty()){
                 grupo = buscarUltimoGrupoCreado(tuGrupos);
            } 
        } catch (Exception e) {            
            throw new Exception("Error obtenerGrupoAutomatico : " + e.toString());
        }
        return grupo;
    }
    
    private static TuGrupoUsuario obtenerUsuarioSegunTipo(List<TuGrupoUsuario> usuarios, boolean isUsuarioAuditorInicial, int orden) throws Exception {
        TuGrupoUsuario usuario;
        usuario = obtenerUsuarioFacilitador(usuarios);
        if (isUsuarioAuditorInicial) {
            usuario = obtenerUsuarioAuditor(usuarios, orden);
        }
        return usuario;
    }
    
    private static TuGrupo buscarGrupoSiguentePorOrden(List<TuGrupo> tuGrupos) throws Exception {
        TuGrupo grupo = new TuGrupo();
        try {
            int cantidad = tuGrupos.size();
            int indiceFuturo;
            boolean noHayAsignacion = true;
            for (int inc = 0; inc < cantidad; inc++) {
                TuGrupo tuGrupo = tuGrupos.get(inc);
                indiceFuturo = inc + 1;
                if (tuGrupo.getUltimoUsuarioId() > 0 && indiceFuturo < cantidad) {
                    grupo = tuGrupos.get(indiceFuturo);
                    noHayAsignacion = false;
                    break;
                }
            }

            if (noHayAsignacion) {
                grupo = tuGrupos.get(0);
            }
        } catch (Exception e) {
            throw new Exception("Error buscarGrupoSiguentePorOrden : " + e.toString());
        }
        return grupo;
    }
    
    private static TuGrupo buscarUltimoGrupoCreado(List<TuGrupo> tuGrupos) throws Exception {
        TuGrupo grupo = new TuGrupo();
        try {   
            if (tuGrupos != null && !tuGrupos.isEmpty()) {
                grupo = tuGrupos.get(0);
            }
        } catch (Exception e) {
            throw new Exception("Error buscarGrupoSiguentePorOrden : " + e.toString());
        }
        return grupo;
    }
    
    private static List<TuGrupoUsuario> obtenerUsuariosPorGrupo(  ParamConsulta paramConsulta ) throws Exception {
        List<TuGrupoUsuario> usuarios = new ArrayList<>();
        try {  
            if(paramConsulta != null){
                paramConsulta.setAscendente(true);
                paramConsulta.setOrden("id");
                usuarios = getGrupoUsuarioRemoto().consultarListaPorGrupo(paramConsulta);
            }
        } catch (Exception e) {
            throw new Exception("Error buscarUsuariosPorGrupo : " + e.toString());
        }
        return usuarios;
    }
    
    private static TuGrupoUsuario obtenerUsuarioAuditor(List<TuGrupoUsuario> tuGrupoUsuarios, int ordenGrupo) throws Exception{
       
        TuGrupoUsuario grupoUsuario = new TuGrupoUsuario();
        try {
            if (tuGrupoUsuarios != null && !tuGrupoUsuarios.isEmpty()) {

                ordenGrupo = ( ordenGrupo > 0) ? ++ordenGrupo : 1 ;
                ordenGrupo = ordenGrupo > tuGrupoUsuarios.size() ? 1 : ordenGrupo;

                int ordenTemporal = 0;
                for (TuGrupoUsuario tuGrupoUsuario : tuGrupoUsuarios) {
                    ordenTemporal++;
                    if (ordenTemporal == ordenGrupo) {
                        grupoUsuario = tuGrupoUsuario;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception("Error obtenerUsuarioGestor : " + e.toString());
        }
        return grupoUsuario;
    }
    
    private static TuGrupoUsuario obtenerUsuarioFacilitador(List<TuGrupoUsuario> tuGrupoUsuarios) throws Exception{    
        TuGrupoUsuario grupoUsuario = new TuGrupoUsuario();
        try {
            if (tuGrupoUsuarios != null && !tuGrupoUsuarios.isEmpty()) {
               grupoUsuario = tuGrupoUsuarios.get(0);
            }
        } catch (Exception e) {
            throw new Exception("Error obtenerUsuarioFacilitador : " + e.toString());
        }
        return grupoUsuario;
    }
    
    private static int obtenerOrdenSegunPosicionUsuario(List<TuGrupoUsuario> tuGrupoUsuarios, int idUsuario) throws Exception {
        int orderGrupo = 0;
        try {
            if (tuGrupoUsuarios != null && !tuGrupoUsuarios.isEmpty()) {
                int ordenTemporal = 0;
                for (TuGrupoUsuario tuGrupoUsuario : tuGrupoUsuarios) {
                    ordenTemporal++;
                    if (tuGrupoUsuario.getUsuario().getId() == idUsuario) {
                        orderGrupo = ordenTemporal;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception("Error obtenerUsuarioGestor : " + e.toString());
        }
        return orderGrupo;
    }
 
    /**
     * funcion que permite actualiar ultimo usuario de un grupo dato
     *
     * @param idGrupo
     * @param idMaeEstado
     * @param idGnUsuarioGestor
     * @return
     */
    public static boolean actualizarUltimoUsurioGrupo(int idGrupo, int idMaeEstado, int idGnUsuarioGestor) {
        boolean esActualizada = true;
        TuGrupo tuGrupo = new TuGrupo();
        try {
            tuGrupo.setId(idGrupo);
            tuGrupo.setUltimoUsuarioId(idGnUsuarioGestor);
            TuGrupo consultaGrupo = getGrupoRemoto().consultar(idGrupo);
            
            ParamConsulta paramConsulta = new ParamConsulta();
            paramConsulta.setParametroConsulta1(idGrupo);
            paramConsulta.setParametroConsulta3(true);
            paramConsulta.setParametroConsulta2(consultaGrupo.getTipoAuditorInicial());
            List<TuGrupoUsuario> usuarios = obtenerUsuariosPorGrupo(paramConsulta);
            tuGrupo.setOrden(obtenerOrdenSegunPosicionUsuario(usuarios, idGnUsuarioGestor));
            
            getGrupoRemoto().actualizarUltimoUsuario(tuGrupo);
            getGrupoRemoto().actualizarOrden(tuGrupo);
        } catch (Exception e) {
            esActualizada = false;
        }
        return esActualizada;
    }
}
