package com.saviasaludeps.savia.ejb.servicios.webservices.mipres.prescripcion;

import com.saviasaludeps.savia.dominio.mipres.DireccionamientoBaseDTO;
import com.saviasaludeps.savia.dominio.mipres.MpDireccionamiento;
import com.saviasaludeps.savia.dominio.mipres.MpNoDireccionado;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcion;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionInsumo;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionMedicamento;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionTecnologia;
import com.saviasaludeps.savia.ejb.entidades.MpDireccionamientos;
import com.saviasaludeps.savia.ejb.entidades.MpNoDireccionados;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionInsumos;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionMedicamentos;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionTecnologias;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.webservices.mipres.prescripcion.MpPrescripcionTecnologiaWSRemoto;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
@Remote(MpPrescripcionTecnologiaWSRemoto.class)
public class MpPrescripcionTecnologiaWSServicio extends GenericoServicio implements MpPrescripcionTecnologiaWSRemoto {

    @Override
    public Map<String, MpPrescripcionMedicamento> consultarListaMpMedicamentos(
            List<MpPrescripcionMedicamento> listamedicamentos) throws java.lang.Exception {

        Map<String, MpPrescripcionMedicamento> mapMedicamentos = new HashMap<>();
        try {
            if (!listamedicamentos.isEmpty()) {
                StringBuilder strQuery = new StringBuilder();
                strQuery.append("FROM MpPrescripcionMedicamentos p ")
                        .append("WHERE (p.mpPrescripcionesId.numeroPrescripcion, p.consecutivoOrden, p.tipoTecnologia) IN (");
                StringBuilder strWhere = new StringBuilder();
                for (MpPrescripcionMedicamento med : listamedicamentos) {
                    strWhere.append("('").append(med.getMpPrescripcion().getNumeroPrescripcion())
                            .append("',").append(med.getConsecutivoOrden())
                            .append(",").append(med.getTipoTecnologia()).append("),");
                }
                String whereIn = strWhere.toString();
                whereIn = whereIn.substring(0, whereIn.length() - 1);
                strQuery.append(whereIn);
                strQuery.append(")");
                List<MpPrescripcionMedicamentos> list = getEntityManager()
                        .createQuery(strQuery.toString())
                        .getResultList();

                for (MpPrescripcionMedicamentos tec : list) {
                    MpPrescripcionMedicamento tecnologia = new MpPrescripcionMedicamento();
                    tecnologia.setId(tec.getId());
                    tecnologia.setConsecutivoOrden(tec.getConsecutivoOrden());
                    tecnologia.setTipoTecnologia(tec.getTipoTecnologia());
                    tecnologia.setCantidadTotalEntrega(tec.getCantidadTotalEntrega());
                    tecnologia.setEntregados(tec.getEntregados());
                    tecnologia.setPendientes(tec.getPendientes());
                    tecnologia.setDuracionTratamiento(tec.getDuracionTratamiento());
                    tecnologia.setCantidadTratamiento(tec.getCantidadTratamiento());
                    tecnologia.setCantidadTotalFormulada((tec.getCantidadTotalFormulada()));
                    if (tec.getMpPrescripcionesId() != null) {
                        MpPrescripcion pres = new MpPrescripcion();
                        pres.setId((tec.getMpPrescripcionesId().getId()));
                        pres.setNumeroPrescripcion(tec.getMpPrescripcionesId().getNumeroPrescripcion());
                        tecnologia.setMpPrescripcion(pres);
                    }
                    tecnologia.getMpPrescripcion().setPrestadorRazonSocial(tec.getMpPrescripcionesId().getPrestadorRazonSocial());
                    tecnologia.getMpPrescripcion().setNumeroPrescripcion(tec.getMpPrescripcionesId().getNumeroPrescripcion());
                    tecnologia.getMpPrescripcion().setMaTipoDocumentoPrestadorId(tec.getMpPrescripcionesId().getMaTipoDocumentoPrestadorId());
                    tecnologia.getMpPrescripcion().setMaTipoDocumentoPrestadorCodigo(tec.getMpPrescripcionesId().getMaTipoDocumentoPrestadorCodigo());
                    tecnologia.getMpPrescripcion().setMaTipoDocumentoPrestadorValor(tec.getMpPrescripcionesId().getMaTipoDocumentoPrestadorValor());
                    tecnologia.getMpPrescripcion().setPrestadorNumeroDocumento(tec.getMpPrescripcionesId().getPrestadorNumeroDocumento());
                    tecnologia.getMpPrescripcion().setSedeCodigoHabilitacion(tec.getMpPrescripcionesId().getSedeCodigoHabilitacion());
                    tecnologia.setUsuarioCrea(tec.getUsuarioCrea());
                    tecnologia.setTerminalCrea(tec.getTerminalCrea());
                    tecnologia.setFechaHoraCrea(tec.getFechaHoraCrea());
                    mapMedicamentos.put(tec.getMpPrescripcionesId().getNumeroPrescripcion() + "||" + tec.getConsecutivoOrden() + "||" + tec.getTipoTecnologia(), tecnologia);
                }
            }
        } catch (NoResultException e) {
            mapMedicamentos = null;
        } catch (Exception e) {
            throw new Exception("Error consultarListaTecnologias2  - " + e.getMessage());
        } finally {
            cerrarEntityManager();
        }
        return mapMedicamentos;
    }

    @Override
    public Map<String, MpPrescripcionTecnologia> consultarListaMpTecnologias(List<MpPrescripcionTecnologia> listaTecnologia) throws Exception {
        Map<String, MpPrescripcionTecnologia> mapTecnologias = new HashMap<>();
        try {
            if (!listaTecnologia.isEmpty()) {
                StringBuilder strQuery = new StringBuilder();
                strQuery.append("FROM MpPrescripcionTecnologias p ")
                        .append("WHERE (p.mpPrescripcionId.numeroPrescripcion, p.consecutivoOrden, p.tipoTecnologia) IN (");
                StringBuilder strWhere = new StringBuilder();
                for (MpPrescripcionTecnologia tec : listaTecnologia) {
                    strWhere.append("('").append(tec.getMpPrescripcion().getNumeroPrescripcion())
                            .append("',").append(tec.getConsecutivoOrden())
                            .append(",").append("'").append(tec.getTipoTecnologia()).append("'),");
                }

                String whereIn = strWhere.toString();
                whereIn = whereIn.substring(0, whereIn.length() - 1);
                strQuery.append(whereIn);
                strQuery.append(")");
                List<MpPrescripcionTecnologias> list = getEntityManager()
                        .createQuery(strQuery.toString())
                        .getResultList();

                for (MpPrescripcionTecnologias tec : list) {
                    MpPrescripcionTecnologia tecnologia = new MpPrescripcionTecnologia();
                    tecnologia.setId(tec.getId());
                    tecnologia.setConsecutivoOrden(tec.getConsecutivoOrden());
                    tecnologia.setTipoTecnologia(tec.getTipoTecnologia());
                    tecnologia.setCantidadTotalEntrega(tec.getCantidadTotalEntrega());
                    tecnologia.setEntregados(tec.getEntregados());
                    tecnologia.setPendientes(tec.getPendientes());
                    tecnologia.setCantidadDuracionTratamiento(tec.getCantidadDuracionTratamiento());
                    tecnologia.setCantidadTotal(tec.getCantidadTotal());
                    tecnologia.setCantidadFormulada(tec.getCantidadFormulada());
                    if (tec.getMpPrescripcionId() != null) {
                        MpPrescripcion pres = new MpPrescripcion();
                        pres.setId((tec.getMpPrescripcionId().getId()));
                        pres.setNumeroPrescripcion(tec.getMpPrescripcionId().getNumeroPrescripcion());
                        tecnologia.setMpPrescripcion(pres);
                    }
                    tecnologia.getMpPrescripcion().setPrestadorRazonSocial(tec.getMpPrescripcionId().getPrestadorRazonSocial());
                    tecnologia.getMpPrescripcion().setNumeroPrescripcion(tec.getMpPrescripcionId().getNumeroPrescripcion());
                    tecnologia.getMpPrescripcion().setMaTipoDocumentoPrestadorId(tec.getMpPrescripcionId().getMaTipoDocumentoPrestadorId());
                    tecnologia.getMpPrescripcion().setMaTipoDocumentoPrestadorCodigo(tec.getMpPrescripcionId().getMaTipoDocumentoPrestadorCodigo());
                    tecnologia.getMpPrescripcion().setMaTipoDocumentoPrestadorValor(tec.getMpPrescripcionId().getMaTipoDocumentoPrestadorValor());
                    tecnologia.getMpPrescripcion().setPrestadorNumeroDocumento(tec.getMpPrescripcionId().getPrestadorNumeroDocumento());
                    tecnologia.getMpPrescripcion().setSedeCodigoHabilitacion(tec.getMpPrescripcionId().getSedeCodigoHabilitacion());
                    tecnologia.setUsuarioCrea(tec.getUsuarioCrea());
                    tecnologia.setTerminalCrea(tec.getTerminalCrea());
                    tecnologia.setFechaHoraCrea(tec.getFechaHoraCrea());
                    mapTecnologias.put(tec.getMpPrescripcionId().getNumeroPrescripcion() + "||" + tec.getConsecutivoOrden() + "||" + tec.getTipoTecnologia(), tecnologia);
                }
            }
        } catch (NoResultException e) {
            mapTecnologias = null;
        } catch (Exception e) {
            throw new Exception("Error consultarListaTecnologias2  - " + e.getMessage());
        } finally {
            cerrarEntityManager();
        }
        return mapTecnologias;
    }

    @Override
    public Map<String, MpPrescripcionInsumo> consultarListaMpInsumos(List<MpPrescripcionInsumo> listaInsumos) throws Exception {
        Map<String, MpPrescripcionInsumo> mapInsumos = new HashMap<>();
        try {
            if (!listaInsumos.isEmpty()) {
                StringBuilder strQuery = new StringBuilder();
                strQuery.append("FROM MpPrescripcionInsumos p ")
                        .append("WHERE (p.mpPrescripcionId.numeroPrescripcion, p.consecutivoOrden, p.tipoTecnologia) IN (");
                StringBuilder strWhere = new StringBuilder();

                for (MpPrescripcionInsumo ins : listaInsumos) {
                    strWhere.append("('").append(ins.getMpPrescripcion().getNumeroPrescripcion())
                            .append("',").append(ins.getConsecutivoOrden())
                            .append(",").append(ins.getTipoTecnologia()).append("),");

                }

                String whereIn = strWhere.toString();
                whereIn = whereIn.substring(0, whereIn.length() - 1);
                strQuery.append(whereIn);
                strQuery.append(")");
                List<MpPrescripcionInsumos> list = getEntityManager()
                        .createQuery(strQuery.toString())
                        .getResultList();

                for (MpPrescripcionInsumos tec : list) {
                    MpPrescripcionInsumo tecnologia = new MpPrescripcionInsumo();
                    tecnologia.setId(tec.getId());
                    tecnologia.setConsecutivoOrden(tec.getConsecutivoOrden());
                    tecnologia.setTipoTecnologia(tec.getTipoTecnologia());
                    tecnologia.setCantidadTotalEntrega(tec.getCantidadTotalEntrega());
                    tecnologia.setEntregados(tec.getEntregados());
                    tecnologia.setPendiente(tec.getPendiente());
                    tecnologia.setDuracionTratamiento(tec.getDuracionTratamiento());
                    //tecnologia.setCantidadTotal(tec.getCantidadTotal());
                    tecnologia.setCantidadFormulada(tec.getCantidadFormulada());
                    if (tec.getMpPrescripcionId() != null) {
                        MpPrescripcion pres = new MpPrescripcion();
                        pres.setId((tec.getMpPrescripcionId().getId()));
                        pres.setNumeroPrescripcion(tec.getMpPrescripcionId().getNumeroPrescripcion());
                        tecnologia.setMpPrescripcion(pres);
                    }
                    tecnologia.getMpPrescripcion().setPrestadorRazonSocial(tec.getMpPrescripcionId().getPrestadorRazonSocial());
                    tecnologia.getMpPrescripcion().setNumeroPrescripcion(tec.getMpPrescripcionId().getNumeroPrescripcion());
                    tecnologia.getMpPrescripcion().setMaTipoDocumentoPrestadorId(tec.getMpPrescripcionId().getMaTipoDocumentoPrestadorId());
                    tecnologia.getMpPrescripcion().setMaTipoDocumentoPrestadorCodigo(tec.getMpPrescripcionId().getMaTipoDocumentoPrestadorCodigo());
                    tecnologia.getMpPrescripcion().setMaTipoDocumentoPrestadorValor(tec.getMpPrescripcionId().getMaTipoDocumentoPrestadorValor());
                    tecnologia.getMpPrescripcion().setPrestadorNumeroDocumento(tec.getMpPrescripcionId().getPrestadorNumeroDocumento());
                    tecnologia.getMpPrescripcion().setSedeCodigoHabilitacion(tec.getMpPrescripcionId().getSedeCodigoHabilitacion());
                    tecnologia.setUsuarioCrea(tec.getUsuarioCrea());
                    tecnologia.setTerminalCrea(tec.getTerminalCrea());
                    tecnologia.setFechaHoraCrea(tec.getFechaHoraCrea());
                    mapInsumos.put(tec.getMpPrescripcionId().getNumeroPrescripcion() + "||" + tec.getConsecutivoOrden() + "||" + tec.getTipoTecnologia(), tecnologia);
                }
            }
        } catch (NoResultException e) {
            mapInsumos = null;
        } catch (Exception e) {
            throw new Exception("Error consultarListaTecnologias2  - " + e.getMessage());
        } finally {
            cerrarEntityManager();
        }
        return mapInsumos;
    }

//    @Override
//    public Map<String, MpPrescripcionInsumo> consultarListaMpInsumosSublistas(List<List<MpPrescripcionInsumo>> sublistasInsumos) throws Exception {
//        Map<String, MpPrescripcionInsumo> mapInsumosFinal = new HashMap<>();
//
//        try {
//            for (List<MpPrescripcionInsumo> sublista : sublistasInsumos) {
//                if (!sublista.isEmpty()) {
//                    StringBuilder strQuery = new StringBuilder();
//                    strQuery.append("FROM MpPrescripcionInsumos p ")
//                            .append("WHERE (p.mpPrescripcionId.numeroPrescripcion, p.consecutivoOrden, p.tipoTecnologia) IN (");
//                    StringBuilder strWhere = new StringBuilder();
//
//                    for (MpPrescripcionInsumo ins : sublista) {
//                        strWhere.append("('").append(ins.getMpPrescripcion().getNumeroPrescripcion())
//                                .append("',").append(ins.getConsecutivoOrden())
//                                .append(",").append(ins.getTipoTecnologia()).append("),");
//                    }
//
//                    String whereIn = strWhere.toString();
//                    if (whereIn.endsWith(",")) {
//                        whereIn = whereIn.substring(0, whereIn.length() - 1); // Remove trailing comma
//                    }
//                    strQuery.append(whereIn);
//                    strQuery.append(")");
//
//                    List<MpPrescripcionInsumos> list = getEntityManager()
//                            .createQuery(strQuery.toString())
//                            .getResultList();
//
//                    for (MpPrescripcionInsumos tec : list) {
//                        MpPrescripcionInsumo tecnologia = new MpPrescripcionInsumo();
//                        tecnologia.setId(tec.getId());
//                        tecnologia.setConsecutivoOrden(tec.getConsecutivoOrden());
//                        tecnologia.setTipoTecnologia(tec.getTipoTecnologia());
//                        tecnologia.setCantidadTotalEntrega(tec.getCantidadTotalEntrega());
//                        tecnologia.setEntregados(tec.getEntregados());
//                        tecnologia.setPendiente(tec.getPendiente());
//                        tecnologia.setDuracionTratamiento(tec.getDuracionTratamiento());
//                        //tecnologia.setCantidadTotal(tec.getCantidadTotal());
//                        tecnologia.setCantidadFormulada(tec.getCantidadFormulada());
//                        tecnologia.setMpPrescripcion(new MpPrescripcion(tec.getMpPrescripcionId().getId()));
//                        tecnologia.getMpPrescripcion().setPrestadorRazonSocial(tec.getMpPrescripcionId().getPrestadorRazonSocial());
//                        tecnologia.getMpPrescripcion().setNumeroPrescripcion(tec.getMpPrescripcionId().getNumeroPrescripcion());
//                        tecnologia.getMpPrescripcion().setMaTipoDocumentoPrestadorId(tec.getMpPrescripcionId().getMaTipoDocumentoPrestadorId());
//                        tecnologia.getMpPrescripcion().setMaTipoDocumentoPrestadorCodigo(tec.getMpPrescripcionId().getMaTipoDocumentoPrestadorCodigo());
//                        tecnologia.getMpPrescripcion().setMaTipoDocumentoPrestadorValor(tec.getMpPrescripcionId().getMaTipoDocumentoPrestadorValor());
//                        tecnologia.getMpPrescripcion().setPrestadorNumeroDocumento(tec.getMpPrescripcionId().getPrestadorNumeroDocumento());
//                        tecnologia.getMpPrescripcion().setSedeCodigoHabilitacion(tec.getMpPrescripcionId().getSedeCodigoHabilitacion());
//                        tecnologia.setUsuarioCrea(tec.getUsuarioCrea());
//                        tecnologia.setTerminalCrea(tec.getTerminalCrea());
//                        tecnologia.setFechaHoraCrea(tec.getFechaHoraCrea());
//                        mapInsumosFinal.put(tec.getMpPrescripcionId().getNumeroPrescripcion() + "||" + tec.getConsecutivoOrden(), tecnologia);
//                    }
//                }
//            }
//        } catch (NoResultException e) {
//            mapInsumosFinal = null;
//        } catch (Exception e) {
//            throw new Exception("Error consultarListaMpInsumos  - " + e.getMessage());
//        } finally {
//            cerrarEntityManager();
//        }
//        return mapInsumosFinal;
//    }
    @Override
    public void actualizarJuntaMpPrescripcionTecnologia(
            MpPrescripcionTecnologia tecnologia) throws Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE MpPrescripcionTecnologias ");
            sql.append("SET estadoJuntaProfesionales = :estado ");
            if (tecnologia.getJustificacionTecJunta() != null) {
                sql.append(", justificacionTecJunta = :justificacion ");
            }
            if (tecnologia.getModJunta() != null) {
                sql.append(", modJunta = :modalidad ");
            }
            if (tecnologia.getNumActaJunta() != null
                    && !tecnologia.getNumActaJunta().equals("")) {
                sql.append(", numActaJunta = :numeroActa ");
            }
            if (tecnologia.getFechaActaJunta() != null) {
                sql.append(", fechaActaJunta = :fecha ");
            }
            sql.append("WHERE id = :id ");
            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("estado", tecnologia.getEstadoJuntaProfesionales());
            if (tecnologia.getJustificacionTecJunta() != null) {
                query.setParameter("justificacion", tecnologia.getJustificacionTecJunta());
            }
            if (tecnologia.getModJunta() != null) {
                query.setParameter("modalidad", tecnologia.getModJunta());
            }
            if (tecnologia.getNumActaJunta() != null
                    && !tecnologia.getNumActaJunta().equals("")) {
                query.setParameter("numeroActa", tecnologia.getNumActaJunta());
            }
            if (tecnologia.getFechaActaJunta() != null) {
                query.setParameter("fecha", tecnologia.getFechaActaJunta());
            }
            query.setParameter("id", tecnologia.getId());
            query.executeUpdate();
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void actualizarJuntaMpPrescripcionMedicamento(
            MpPrescripcionMedicamento medicamento) throws Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE MpPrescripcionMedicamentos ");
            sql.append("SET estadoJuntaProfesionales = :estado ");
            if (medicamento.getJustificacionTecJunta() != null) {
                sql.append(", justificacionTecJunta = :justificacion ");
            }
            if (medicamento.getModJunta() != null) {
                sql.append(", modJunta = :modalidad ");
            }
            if (medicamento.getNumActaJunta() != null
                    && !medicamento.getNumActaJunta().equals("")) {
                sql.append(", numActaJunta = :numeroActa ");
            }
            if (medicamento.getFechaActaJunta() != null) {
                sql.append(", fechaActaJunta = :fecha ");
            }
            sql.append("WHERE id = :id ");
            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("estado", medicamento.getEstadoJuntaProfesionales());
            if (medicamento.getJustificacionTecJunta() != null) {
                query.setParameter("justificacion", medicamento.getJustificacionTecJunta());
            }
            if (medicamento.getModJunta() != null) {
                query.setParameter("modalidad", medicamento.getModJunta());
            }
            if (medicamento.getNumActaJunta() != null
                    && !medicamento.getNumActaJunta().equals("")) {
                query.setParameter("numeroActa", medicamento.getNumActaJunta());
            }
            if (medicamento.getFechaActaJunta() != null) {

                query.setParameter("fecha", medicamento.getFechaActaJunta());
            }
            query.setParameter("id", medicamento.getId());
            query.executeUpdate();

        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void actualizarJuntaMpPrescripcionInsumo(
            MpPrescripcionInsumo insumo) throws Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE MpPrescripcionInsumos ");
            sql.append("SET estadoJuntaProfesionales = :estado ");
            if (insumo.getJustificacionTecJunta() != null) {
                sql.append(", justificacionTecJunta = :justificacion ");
            }
            if (insumo.getModJunta() != null) {
                sql.append(", modalidadJunta = :modalidad ");
            }
            if (insumo.getNumActaJunta() != null
                    && !insumo.getNumActaJunta().equals("")) {
                sql.append(", numActaJunta = :numeroActa ");
            }
            if (insumo.getFechaActaJunta() != null) {
                sql.append(", fechaActaJunta = :fecha ");
            }
            sql.append("WHERE id = :id ");
            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("estado", insumo.getEstadoJuntaProfesionales());
            if (insumo.getJustificacionTecJunta() != null) {
                query.setParameter("justificacion", insumo.getJustificacionTecJunta());
            }
            if (insumo.getModJunta() != null) {
                query.setParameter("modalidad", insumo.getModJunta());
            }
            if (insumo.getNumActaJunta() != null
                    && !insumo.getNumActaJunta().equals("")) {
                query.setParameter("numeroActa", insumo.getNumActaJunta());
            }
            if (insumo.getFechaActaJunta() != null) {
                query.setParameter("fecha", insumo.getFechaActaJunta());
            }
            query.setParameter("id", insumo.getId());
            query.executeUpdate();
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void cambiarEstadoDireccionamiento(Integer id, Integer estado) throws Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE MpDireccionamientos ");
            sql.append("SET estado = :estado ");
            boolean esEliminado = estado != null && estado == 0;
            if (esEliminado) {
//                sql.append(", eliminado = :eliminado ");
                sql.append(", preEliminado = :preEliminado ");
            }
            sql.append("WHERE id = :id ");
            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("estado", estado);
            query.setParameter("id", id);

            if (esEliminado) {
//                query.setParameter("eliminado", 1);
                query.setParameter("preEliminado", 1);
            }

            query.executeUpdate();

        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void cambiarEstadoNoDireccionamiento(Integer id, Integer estado, String fechaAnula) throws Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE MpNoDireccionados ");
            sql.append("SET estadoNoDireccionamiento = :estado ");
            boolean esEliminado = estado != null && estado == 0;
            boolean tieneFecha = fechaAnula != null && !fechaAnula.trim().isEmpty();
            if (esEliminado && tieneFecha) {
                sql.append(", fechaAnulacion = :fechaAnulacion ");
            }
            sql.append("WHERE id = :id ");
            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("estado", estado);
            query.setParameter("id", id);
            if (esEliminado && tieneFecha) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date fecha = sdf.parse(fechaAnula);
                query.setParameter("fechaAnulacion", fecha);
            }
            query.executeUpdate();
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public List<MpDireccionamiento> consultarDireccionamientosPorIdPrescripcion(int id) {
        List<MpDireccionamiento> entrega = new ArrayList<>();
        try {
            String strQuery = "SELECT p FROM MpDireccionamientos p WHERE p.mpPrescripcionesId.id = :id";

            // Crear la consulta correctamente
            TypedQuery<MpDireccionamientos> query = getEntityManager().createQuery(strQuery, MpDireccionamientos.class);
            query.setParameter("id", id);

            // Usar la consulta con el parámetro ya asignado
            List<MpDireccionamientos> list = query.getResultList();

            for (MpDireccionamientos per : list) {
                entrega.add(castEntidadNegocioD(per));
            }

        } catch (Exception e) {
            e.printStackTrace();
            entrega = null;
        } finally {
            cerrarEntityManager();
        }
        return entrega;
    }

    @Override
    public List<MpNoDireccionado> consultarNoDireccionamientosPorIdPrescripcion(int id) {
        List<MpNoDireccionado> entrega = new ArrayList<>();
        try {
            String strQuery = "SELECT p FROM MpNoDireccionados p WHERE p.mpPrescripcionesId.id = :id";

            // Crear la consulta correctamente
            TypedQuery<MpNoDireccionados> query = getEntityManager().createQuery(strQuery, MpNoDireccionados.class);
            query.setParameter("id", id);

            // Usar la consulta con el parámetro ya asignado
            List<MpNoDireccionados> list = query.getResultList();

            for (MpNoDireccionados per : list) {
                entrega.add(castEntidadNegocioND(per));
            }

        } catch (Exception e) {
            e.printStackTrace();
            entrega = null;
        } finally {
            cerrarEntityManager();
        }
        return entrega;
    }

    public static MpDireccionamiento castEntidadNegocioD(MpDireccionamientos ent) {
        MpDireccionamiento obj = new MpDireccionamiento();
        obj.setId(ent.getId());
        obj.setIdDireccionamiento(ent.getIdDireccionamiento());
        obj.setIdTransaccion(ent.getIdTransaccion());
        obj.setEstado(ent.getEstado());
        return obj;
    }

    public static MpNoDireccionado castEntidadNegocioND(MpNoDireccionados ent) {
        MpNoDireccionado obj = new MpNoDireccionado();
        obj.setId(ent.getId());
        obj.setIdNoDireccionamiento(ent.getIdNoDireccionamiento());
        obj.setIdTransaccion(ent.getIdTransaccion());
        obj.setEstadoNoDireccionamiento(ent.getEstadoNoDireccionamiento());
        return obj;
    }

}
