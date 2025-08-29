package com.saviasaludeps.savia.ejb.servicios.recobro;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.recobro.RcoFactura;
import com.saviasaludeps.savia.ejb.entidades.CmFacturas;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadores;
import com.saviasaludeps.savia.ejb.entidades.RcoFacturas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.recobro.RcoFacturaRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author Stiven Giraldo
 */
@Stateless
@Remote(RcoFacturaRemoto.class)
public class RcoFacturaServicio extends GenericoServicio implements RcoFacturaRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(m) FROM RcoFacturas m WHERE m.id > 0";

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += " AND m.id = " + e.getValue() + " ";
                            break;
                        case "numeroRadicado":
                            strQuery += " AND m.numeroRadicado = " + e.getValue() + " ";
                            break;
                        case "cmFacturaId.id":
                            strQuery += " AND m.cmFacturasId.id = " + e.getValue() + " ";
                            break;
                        case "nit":
                            strQuery += " AND m.nit = " + e.getValue() + " ";
                            break;
                        case "ips":
                            strQuery += " AND m.ips LIKE '%" + e.getValue() + "% ";
                            break;
                        case "maeTipoContratoValor":
                            strQuery += " AND m.maeTipoContratoValor = '" + e.getValue() + "' ";
                            break;
                        case "maeEstadoRecobroId":
                            strQuery += " AND m.maeEstadoRecobroId = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    .getSingleResult();
        } catch (NoResultException e) {
            cant = 0;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cant;
    }

    @Override
    public List<RcoFactura> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<RcoFactura> listResult = new ArrayList();
        try {
            String strQuery = "SELECT m FROM RcoFacturas m WHERE m.id > 0";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += " AND m.id = " + e.getValue() + " ";
                            break;
                        case "numeroRadicado":
                            strQuery += " AND m.numeroRadicado = " + e.getValue() + " ";
                            break;
                        case "cmFacturaId.id":
                            strQuery += " AND m.cmFacturasId.id = " + e.getValue() + " ";
                            break;
                        case "nit":
                            strQuery += " AND m.nit = " + e.getValue() + " ";
                            break;
                        case "cntPrestadoreId.razonSocial":
                            strQuery += " AND m.cntPrestadoresId.razonSocial LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeTipoContratoValor":
                            strQuery += " AND m.maeTipoContratoValor = '" + e.getValue() + "' ";
                            break;
                        case "maeEstadoRecobroId":
                            strQuery += " AND m.maeEstadoRecobroId = " + e.getValue() + " ";
                            break;

                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "m." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "DESC" : "ASC");
            } else {
                strQuery += "m.id DESC ";
            }
            List<RcoFacturas> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (RcoFacturas per : list) {
                listResult.add(castEntidadNegocio(per));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;

    }
    @Override
    public int consultarCantidadListaConciliacion(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strTitulo = "SELECT COUNT(u) FROM RcoFacturas u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append("AND u.id = ").append(e.getValue()).append(" ");
                            break;
                        case "cntPrestadoresId.id":
                            strTitulo = agregarJoin("INNER JOIN CntPrestadores cntp ON u.cntPrestadoresId = cntp.id ", strTitulo);
                            strQuery.append("AND cntp.id = ").append(e.getValue()).append(" ");
                            break;
                        case "maeEstadoRecobroCodigo":
                            strQuery.append("AND u.maeEstadoRecobroCodigo = '").append(e.getValue()).append("' ");
                            break;

                    }
                }
            }
            sql.append(strTitulo).append(strQuery);
            cant = (int) (long) getEntityManager().createQuery(sql.toString())
                    .getSingleResult();
        } catch (NoResultException e) {
            cant = 0;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return cant;
    }

    @Override
    public List<RcoFactura> consultarListaConciliacion(ParamConsulta paramConsulta) throws Exception {
        List<RcoFactura> listResult = new ArrayList();
        try {
            String strTitulo = "SELECT u FROM RcoFacturas u ";
            StringBuilder strQuery = new StringBuilder("WHERE u.id > 0 ");
            StringBuilder sql = new StringBuilder();
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery.append("AND u.id = ").append(e.getValue()).append(" ");
                            break;
                        case "cntPrestadoresId.id":
                            strTitulo = agregarJoin("INNER JOIN CntPrestadores cntp ON u.cntPrestadoresId = cntp.id ", strTitulo);
                            strQuery.append("AND cntp.id = ").append(e.getValue()).append(" ");
                            break;
                        case "maeEstadoRecobroCodigo":
                            strQuery.append("AND u.maeEstadoRecobroCodigo = '").append(e.getValue()).append("' ");
                            break;
                    }
                }
            }
            sql.append(strTitulo).append(strQuery);
            sql.append("ORDER BY ");
            if (paramConsulta.getOrden() != null) {
                sql.append("u.").append(paramConsulta.getOrden()).append((paramConsulta.isAscendente() ? " ASC" : " DESC"));
            } else {
                sql.append("u.id DESC");
            }
            List<RcoFacturas> list = getEntityManager().createQuery(sql.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (RcoFacturas per : list) {
                listResult.add(castEntidadNegocio(per));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    @SuppressWarnings("UnusedAssignment")
    private String agregarJoin(String join, String sql) {
        if (sql.contains(join)) {
            return sql;
        } else {
            return sql += join;
        }
    }


    @Override
    public RcoFactura consultar(int id) throws Exception {
        RcoFactura objRes = null;
        try {
            objRes = castEntidadNegocio((RcoFacturas) getEntityManager().find(RcoFacturas.class, id));
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    @Override
    public int insertar(RcoFactura obj) throws java.lang.Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(_id);
        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e, "La combinación nombre, tipo, valor ya existe.");
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    @Override
    public void actualizar(RcoFactura obj) throws java.lang.Exception {

        try {
            Session session = getEntityManager().unwrap(Session.class);

            String strQuery = "UPDATE RcoFacturas a SET ";
            strQuery += "a.aplicaRecobro = :aplicaRecobro ,";
            strQuery += "a.tipoRecobro = :tipoRecobro ,";
            strQuery += "a.maeTipoContratoId = :maeTipoContratoId ,";
            strQuery += "a.maeTipoContratoCodigo = :maeTipoContratoCodigo , ";
            strQuery += "a.maeTipoContratoValor = :maeTipoContratoValor  ,";
            strQuery += "a.numeroContrato = :numeroContrato ,";
            strQuery += "a.nit = :nit ,";
            strQuery += "a.ips = :ips ,";
            strQuery += "a.numeroRadicado = :numeroRadicado , ";
            strQuery += "a.numeroFacturado = :numeroFacturado  ,";
            strQuery += "a.valorFactura = :valorFactura ,";
            strQuery += "a.valorPagadoFactura = :valorPagadoFactura ,";
            strQuery += "a.estadoFactura = :estadoFactura ,";
            strQuery += "a.fechaHoraPrestacion = :fechaHoraPrestacion , ";
            strQuery += "a.fechaHoraRadicacion = :fechaHoraRadicacion  ,";
            strQuery += "a.maeRegimenId = :maeRegimenId ,";
            strQuery += "a.maeRegimenCodigo = :maeRegimenCodigo ,";
            strQuery += "a.maeRegimenValor = :maeRegimenValor ,";
            strQuery += "a.valorInicialGlosa = :valorInicialGlosa , ";
            strQuery += "a.maeEstadoRecobroId = :maeEstadoRecobroId , ";
            strQuery += "a.maeEstadoRecobroCodigo = :maeEstadoRecobroCodigo , ";
            strQuery += "a.maeEstadoRecobroValor = :maeEstadoRecobroValor , ";
            strQuery += "a.usuarioModifica = :usuarioModifica  ,";
            strQuery += "a.terminalModifica= :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ,";
            strQuery += "a.usuarioAuditoria = :usuarioAuditoria ,";
            strQuery += "a.terminalAuditoria = :terminalAuditoria ,";
            strQuery += "a.fechaHoraAuditoria = :fechaHoraAuditoria ";
            strQuery += " WHERE a.id = :id ";

            Query query = session.createQuery(strQuery);
            query.setParameter("aplicaRecobro", obj.isAplicaRecobro());
            query.setParameter("tipoRecobro", obj.getTipoRecobro());
            query.setParameter("maeTipoContratoId", obj.getMaeTipoContratoId());
            query.setParameter("maeTipoContratoCodigo", obj.getMaeTipoContratoCodigo());
            query.setParameter("maeTipoContratoValor", obj.getMaeTipoContratoValor());
            query.setParameter("numeroContrato", obj.getNumeroContrato());
            query.setParameter("nit", obj.getNit());
            query.setParameter("ips", obj.getIps());
            query.setParameter("numeroRadicado", obj.getNumeroRadicado());
            query.setParameter("numeroFacturado", obj.getNumeroFacturado());
            query.setParameter("valorFactura", obj.getValorFactura());
            query.setParameter("valorPagadoFactura", obj.getValorPagadoFactura());
            query.setParameter("estadoFactura", obj.getEstadoFactura());
            query.setParameter("fechaHoraPrestacion", obj.getFechaHoraPrestacion());
            query.setParameter("fechaHoraRadicacion", obj.getFechaHoraRadicacion());
            query.setParameter("maeRegimenId", obj.getMaeRegimenId());
            query.setParameter("maeRegimenCodigo", obj.getMaeRegimenCodigo());
            query.setParameter("maeRegimenValor", obj.getMaeRegimenValor());
            query.setParameter("valorInicialGlosa", obj.getValorInicialGlosa());
            query.setParameter("maeEstadoRecobroId", obj.getMaeEstadoRecobroId());
            query.setParameter("maeEstadoRecobroCodigo", obj.getMaeEstadoRecobroCodigo());
            query.setParameter("maeEstadoRecobroValor", obj.getMaeEstadoRecobroValor());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("usuarioAuditoria", obj.getUsuarioAuditoria());
            query.setParameter("terminalAuditoria", obj.getTerminalAuditoria());
            query.setParameter("fechaHoraAuditoria", obj.getFechaHoraAuditoria());

            query.setParameter("id", obj.getId());
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
    public RcoFactura eliminar(int id) throws java.lang.Exception {
        RcoFactura obj = null;
        try {
            RcoFacturas ent = getEntityManager().find(RcoFacturas.class, id);
            if (ent != null) {
                obj = castEntidadNegocio(ent);
                getEntityManager().remove(ent);
            }
        } catch (NoResultException e) {
            obj = null;
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    private RcoFactura castEntidadNegocio(RcoFacturas entidad) {
        RcoFactura negocio = new RcoFactura();
        negocio.setId(entidad.getId());
        negocio.setCmFacturaId(new CmFactura(entidad.getCmFacturasId().getId()));
        negocio.setCntPrestadoreId(new CntPrestador(entidad.getCntPrestadoresId().getId()));
        negocio.getCntPrestadoreId().setRazonSocial(entidad.getCntPrestadoresId().getRazonSocial());
        negocio.setAplicaRecobro(entidad.getAplicaRecobro());
        negocio.setTipoRecobro(entidad.getTipoRecobro());
        negocio.setMaeTipoContratoId(entidad.getTipoRecobro());
        negocio.setMaeTipoContratoCodigo(entidad.getMaeTipoContratoCodigo());
        negocio.setMaeTipoContratoValor(entidad.getMaeTipoContratoValor());
        negocio.setNumeroContrato(entidad.getNumeroContrato());
        negocio.setNit(entidad.getNit());
        negocio.setIps(entidad.getIps());
        negocio.setNumeroRadicado(entidad.getNumeroRadicado());
        negocio.setNumeroFacturado(entidad.getNumeroFacturado());
        negocio.setValorFactura(entidad.getValorFactura());
        negocio.setValorPagadoFactura(entidad.getValorPagadoFactura());
        negocio.setEstadoFactura(entidad.getEstadoFactura());
        negocio.setFechaHoraPrestacion(entidad.getFechaHoraPrestacion());
        negocio.setFechaHoraRadicacion(entidad.getFechaHoraRadicacion());
        negocio.setMaeRegimenId(entidad.getMaeRegimenId());
        negocio.setMaeRegimenCodigo(entidad.getMaeRegimenCodigo());
        negocio.setMaeRegimenValor(entidad.getMaeRegimenValor());
        negocio.setCmFechaHoraCrea(entidad.getCmFechaHoraCrea());
        negocio.setValorInicialGlosa(entidad.getValorInicialGlosa());
        negocio.setMaeEstadoRecobroId(entidad.getMaeEstadoRecobroId());
        negocio.setMaeEstadoRecobroCodigo(entidad.getMaeEstadoRecobroCodigo());
        negocio.setMaeEstadoRecobroValor(entidad.getMaeEstadoRecobroValor());
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        negocio.setTerminalModifica(entidad.getTerminalModifica());
        negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        negocio.setUsuarioAuditoria(entidad.getUsuarioModifica());
        negocio.setTerminalAuditoria(entidad.getTerminalModifica());
        negocio.setFechaHoraAuditoria(entidad.getFechaHoraAuditoria());
        return negocio;
    }

    private RcoFacturas castNegocioEntidad(RcoFactura negocio) {
        RcoFacturas entidad = new RcoFacturas();
        entidad.setId(negocio.getId());
        entidad.setCmFacturasId(new CmFacturas(negocio.getCmFacturaId().getId()));
        entidad.setCntPrestadoresId(new CntPrestadores(negocio.getCntPrestadoreId().getId()));
        entidad.setAplicaRecobro(negocio.isAplicaRecobro());
        entidad.setTipoRecobro(negocio.getTipoRecobro());
        entidad.setMaeTipoContratoId(negocio.getTipoRecobro());
        entidad.setMaeTipoContratoCodigo(negocio.getMaeTipoContratoCodigo());
        entidad.setMaeTipoContratoValor(negocio.getMaeTipoContratoValor());
        entidad.setNumeroContrato(negocio.getNumeroContrato());
        entidad.setNit(negocio.getNit());
        entidad.setIps(negocio.getIps());
        entidad.setNumeroRadicado(negocio.getNumeroRadicado());
        entidad.setNumeroFacturado(negocio.getNumeroFacturado());
        entidad.setValorFactura(negocio.getValorFactura());
        entidad.setValorPagadoFactura(negocio.getValorPagadoFactura());
        entidad.setEstadoFactura(negocio.getEstadoFactura());
        entidad.setFechaHoraPrestacion(negocio.getFechaHoraPrestacion());
        entidad.setFechaHoraRadicacion(negocio.getFechaHoraRadicacion());
        entidad.setMaeRegimenId(negocio.getMaeRegimenId());
        entidad.setMaeRegimenCodigo(negocio.getMaeRegimenCodigo());
        entidad.setMaeRegimenValor(negocio.getMaeRegimenValor());
        entidad.setCmFechaHoraCrea(negocio.getCmFechaHoraCrea());
        entidad.setValorInicialGlosa(negocio.getValorInicialGlosa());
        entidad.setMaeEstadoRecobroId(negocio.getMaeEstadoRecobroId());
        entidad.setMaeEstadoRecobroCodigo(negocio.getMaeEstadoRecobroCodigo());
        entidad.setMaeEstadoRecobroValor(negocio.getMaeEstadoRecobroValor());
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setUsuarioModifica(negocio.getUsuarioModifica());
        entidad.setTerminalModifica(negocio.getTerminalModifica());
        entidad.setFechaHoraModifica(negocio.getFechaHoraModifica());
        entidad.setUsuarioAuditoria(negocio.getUsuarioModifica());
        entidad.setTerminalAuditoria(negocio.getTerminalModifica());
        entidad.setFechaHoraAuditoria(negocio.getFechaHoraAuditoria());
        return entidad;
    }

}
