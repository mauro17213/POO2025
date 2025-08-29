/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Item;
import com.saviasaludeps.savia.dominio.autorizacion.AuGrupo;
import com.saviasaludeps.savia.ejb.entidades.AuGrupos;
import com.saviasaludeps.savia.ejb.entidades.GnUsuarios;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuAsignacionRemoto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Stiven Giraldo
 */
@Stateless
@Remote(AuAsignacionRemoto.class)
public class AuAsignacionServicio extends GenericoServicio implements AuAsignacionRemoto {

    @Override
    public boolean asignar(AuAnexo3 anexo3) throws Exception {
        boolean asignado = false;
        TreeMap<Integer, AuGrupo> mapGrupos = new TreeMap();
        try {
            Ubicacion ubicacionAfiliacion = anexo3.getAsegAfiliadoId().getAfiliacionUbicacion();
            Integer regionId = (ubicacionAfiliacion.getMaeRegionCodigo() == null) ? null : Integer.parseInt(ubicacionAfiliacion.getMaeRegionCodigo());
            Integer sedeId = anexo3.getCntPrestadorSedeId().getId();
            if (anexo3.getAuAnexo3TutelasList().isEmpty()) {//Tutela
                List<AuGrupo> listaGrupos = new ArrayList();
                for (AuGrupo grupo : listaGrupos) {
                    if (grupo.isTutela()) {//Es tutela
                        mapGrupos.put(grupo.getId(), grupo);
                        break;
                    }
                }
            } else if (anexo3.getMaeAmbitoAtencionId() == 3131) {//Hospitalario
                List<AuGrupo> listaGrupos = new ArrayList();
                for (AuGrupo grupo : listaGrupos) {
                    if (grupo.getMaeAmbitoId() == 3131) {//Es Ambulatorio
                        throw new Exception();
                    }
                }
            } else if (anexo3.getMaeAmbitoAtencionId() == 3130) {//Ambulatorio
                List<AuGrupo> listaGrupos = new ArrayList();
                for (AuAnexo3Item item : anexo3.getAuAnexo3ItemsList()) {
                    int tipoTecnologia = item.getTipoTecnologia();
                    Integer tecnologiaId = item.getMaTecnologiaId();
                    Integer diagnosticoId = item.getMaDiagnosticoId();
                    //Validar cada grupo
                    for (AuGrupo grupo : listaGrupos) {
                        try {
                            if (grupo.isTutela()) {//No es tutela
                                throw new Exception();
                            }
                            if (grupo.getMaeAmbitoId() == 3130) {//Es Ambulatorio
                                throw new Exception();
                            }
                            switch (tipoTecnologia) {
                                case 1://Tecnologia
                                    if (!grupo.isEsTecnologia()) {
                                        throw new Exception();
                                    } else {
                                        if (grupo.getHashGrupoTecnologias() != null) {
                                            if (grupo.getGrupoTecnologia(tecnologiaId) == null) {
                                                throw new Exception();
                                            }
                                        }
                                    }
                                    break;
                                case 2://Medicamenrto
                                    if (!grupo.isEsMedicamento()) {
                                        throw new Exception();
                                    }
                                    break;
                                case 3://Insumo
                                    if (!grupo.isEsInsumo()) {
                                        throw new Exception();
                                    }
                                    break;
                                case 4://Paquete
                                    if (!grupo.isEsPaquete()) {
                                        throw new Exception();
                                    }
                                    break;
                            }
                            if (grupo.getHashGrupoDiagnosticos() != null) {
                                if (grupo.getGrupoDiagnostico(diagnosticoId) == null) {
                                    throw new Exception();
                                }
                            }
                            if (grupo.getHashGrupoSedes() != null) {
                                if (grupo.getGrupoSede(sedeId) == null) {
                                    throw new Exception();
                                }
                            }
                            if (regionId != null) {
                                if (grupo.getHashGrupoRegiones() != null) {
                                    if (grupo.getGrupoRegion(regionId) == null) {
                                        throw new Exception();
                                    }
                                }
                            }
                            //ADICIONAR

                            break;
                        } catch (Exception e) {

                        }
                    }
                }
//                id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
//                obj.setId(id);
                asignado = true;
            }
        } catch (NoResultException e) {
            asignado = false;
        } catch (Exception e) {
            asignado = false;
        } finally {
            cerrarEntityManager();
        }
        return asignado;
    }

//    private void asignarItems(boolean usuarios, int grupoId, List<AuAnexo3Item> listaItems) throws Exception {
//        try {
//            if (usuarios) {//Trabaja con usuarios
//                Integer ultimoUsuarioId = null;
//                try {
//                    //ultimoUsuarioId = ((AuGrupos) getEntityManager().find(AuGrupos.class, grupoId)).getUltimoUsuarioId();
//                } catch (NoResultException e) {
//                    ultimoUsuarioId = null;
//                } catch (Exception e) {
//                    ultimoUsuarioId = null;
//                }
//                Integer usuarioId;
//                try {
//                    String strQuery = "FROM GnUsuarios u"
//                            + " WHERE u.auGruposId.id = " + grupoId
//                            + ((ultimoUsuarioId == null) ? "" : " AND u.id > " + ultimoUsuarioId)
//                            + " ORDER BY u.id"
//                            + " LIMIT 1";
//                    usuarioId = ((GnUsuarios) getEntityManager().createQuery(strQuery).getSingleResult()).getId();
//                } catch (NoResultException e) {
//                    usuarioId = null;
//                } catch (Exception e) {
//                    usuarioId = null;
//                }
//                Session session = getEntityManager().unwrap(Session.class);
//                String strQuery;
//                //Actualizar asignación en grupo
//                if (usuarioId != null) {
//                    strQuery = "UPDATE AuGrupos g SET";
//                    strQuery += " g.ultimoUsuarioId = " + usuarioId;
//                    strQuery += " WHERE g.id = " + grupoId;
//                    Query query = session.createQuery(strQuery);
//                    query.executeUpdate();
//                }
//                //Actualizar asignación de items
//                for (AuAnexo3Item item : listaItems) {
//                    strQuery = "UPDATE AuAnexo3Items i SET";
//                    strQuery += " i.auGruposId.id = " + grupoId;
//                    if (usuarioId != null) {
//                        strQuery += " i.gnUsuariosId.id = " + usuarioId;
//                    }
//                    strQuery += " WHERE i.id = " + item.getId();
//                    Query query = session.createQuery(strQuery);
//                    query.executeUpdate();
//                }
//            } else {//Trabaja sin usuarios
//                //Actualizar asignación de items
//                Session session = getEntityManager().unwrap(Session.class);
//                String strQuery;
//                for (AuAnexo3Item item : listaItems) {
//                    strQuery = "UPDATE AuAnexo3Items i SET";
//                    strQuery += " i.auGruposId.id = " + grupoId;
//                    strQuery += " WHERE i.id = " + item.getId();
//                    Query query = session.createQuery(strQuery);
//                    query.executeUpdate();
//                }
//            }
//        } catch (NoResultException e) {
//            Exception(ACTUALIZAR, e);
//        } catch (Exception e) {
//            Exception(ACTUALIZAR, e);
//        } finally {
//            cerrarEntityManager();
//        }
//    }

//    private AuSolicitud castNegocioEntidad(AuSolicitud negocio){
//        AuSolicitudes entidad = new AuSolicitudes();
//        entidad.setGnEmpresasId(new GnEmpresas(negocio.getEmpresasId().getId()));
//        entidad.setAsegAfiliadosId(new AsegAfiliados(negocio.getAsegAfiliadoId().getId()));
//        entidad.setAuAnexos3Id(new AuAnexos3(negocio.getAuAnexo3Id().getId()));
//        entidad.setMaeCausaRechazoId(negocio.getMaeCausaRechazoId());
//        entidad.setMaeCausaRechazoCodigo(negocio.getMaeCausaRechazoCodigo());
//        entidad.setMaeCausaRechazoValor(negocio.getMaeCausaRechazoValor());
//        entidad.setJustificacion(negocio.getJustificacion());
//        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
//        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
//        entidad.setTerminalCrea(negocio.getTerminalCrea());
//        return entidad;
//    }
}
