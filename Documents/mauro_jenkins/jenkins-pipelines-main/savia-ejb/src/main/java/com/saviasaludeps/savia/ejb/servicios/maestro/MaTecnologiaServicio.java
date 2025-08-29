package com.saviasaludeps.savia.ejb.servicios.maestro;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaIss2000Tarifario;
import com.saviasaludeps.savia.dominio.maestro.MaIss2001Tarifario;
import com.saviasaludeps.savia.dominio.maestro.MaSoatTarifario;
import com.saviasaludeps.savia.dominio.maestro.MaSoatTarifarioValor;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologia;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologiaGrupo;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologiaMipres;
import com.saviasaludeps.savia.dominio.mipres.MpCodigoInsumo;
import com.saviasaludeps.savia.ejb.entidades.MaIss2000Tarifarios;
import com.saviasaludeps.savia.ejb.entidades.MaIss2001Tarifarios;
import com.saviasaludeps.savia.ejb.entidades.MaSoatTarifarioValores;
import com.saviasaludeps.savia.ejb.entidades.MaSoatTarifarios;
import com.saviasaludeps.savia.ejb.entidades.MaTecnologiaGrupos;
import com.saviasaludeps.savia.ejb.entidades.MaTecnologias;
import com.saviasaludeps.savia.ejb.entidades.MaTecnologiasMipres;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.maestro.MaTecnologiaRemoto;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author jyperez
 */
@Stateless
@Remote(MaTecnologiaRemoto.class)
public class MaTecnologiaServicio extends GenericoServicio implements MaTecnologiaRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        int i = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM MaTecnologias p WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maeGrupoTecnologiaId":
                            strQuery += " AND p.maeGrupoTecnologiaId = " + e.getValue() + " ";
                            break;
                        case "grupoDescripcion":
                            strQuery += " AND p.grupoDescripcion  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeServicioId":
                            strQuery += " AND p.maeServicioId  = " + e.getValue() + " ";
                            break;
                        case "maeServicioCodigo":
                            strQuery += " AND p.maeServicioCodigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeServicioValor":
                            strQuery += " AND p.maeServicioValor  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cups":
                            strQuery += " AND p.cups  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cupsDescipcion":
                            strQuery += " AND p.cupsDescipcion  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "abreviatura":
                            strQuery += " AND p.abreviatura  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "codigoPropio":
                            strQuery += " AND p.codigoPropio LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "propioDescripcion":
                            strQuery += " AND p.propioDescripcion LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "aplicaSubsidiado":
                            strQuery += " AND p.aplicaSubsidiado = " + e.getValue() + " ";
                            break;
                        case "aplicaContributivo":
                            strQuery += " AND p.aplicaContributivo = " + e.getValue() + " ";
                            break;
                        case "sexoAplica":
                            strQuery += " AND p.sexoAplica = " + e.getValue() + " ";
                            break;
                        case "esPbs":
                            strQuery += " AND p.esPbs = " + e.getValue() + " ";
                            break;
                        case "nivelComplejidad":
                            strQuery += " AND p.nivelComplejidad = " + e.getValue() + " ";
                            break;
                        case "unidadDesde":
                            strQuery += " AND p.unidadDesde = " + e.getValue() + " ";
                            break;
                        case "edadDesde":
                            strQuery += " AND p.edadDesde = " + e.getValue() + " ";
                            break;
                        case "edadHasta":
                            strQuery += " AND p.edadHasta = " + e.getValue() + " ";
                            break;
                        case "unidadHasta":
                            strQuery += " AND p.unidadHasta = " + e.getValue() + " ";
                            break;
                        case "codigoFinanciador":
                            strQuery += " AND p.codigoFinanciador LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "frecuencia":
                            strQuery += " AND p.frecuencia LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maIss2000Tarifario.id":
                            strQuery += " AND p.maIss200TarifariosId.id = " + e.getValue() + " ";
                            break;
                        case "maIss2001Tarifario.id":
                            strQuery += " AND p.maIss2001TarifariosId.id = " + e.getValue() + " ";
                            break;
                        case "maSoatTarifario.id":
                            strQuery += " AND p.maSoatTarifariosId.id = " + e.getValue() + " ";
                            break;
                        case "cobertura":
                            strQuery += " AND p.cobertura = " + e.getValue() + " ";
                            break;
                        case "maeCoberturaId":
                            strQuery += " AND p.maeCoberturaId = " + e.getValue() + " ";
                            break;
                        case "activo":
                            strQuery += " AND p.activo = " + e.getValue() + " ";
                            break;
                        case "vigenciaAutorizacion":
                            strQuery += " AND p.vigenciaAutorizacion = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
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
    public List<MaTecnologia> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<MaTecnologia> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaTecnologias p WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maeGrupoTecnologiaId":
                            strQuery += " AND p.maeGrupoTecnologiaId = " + e.getValue() + " ";
                            break;
                        case "grupoDescripcion":
                            strQuery += " AND p.grupoDescripcion  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeServicioId":
                            strQuery += " AND p.maeServicioId  = " + e.getValue() + " ";
                            break;
                        case "maeServicioCodigo":
                            strQuery += " AND p.maeServicioCodigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeServicioValor":
                            strQuery += " AND p.maeServicioValor  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cups":
                            strQuery += " AND p.cups  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cupsDescipcion":
                            strQuery += " AND p.cupsDescipcion  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "abreviatura":
                            strQuery += " AND p.abreviatura  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "codigoPropio":
                            strQuery += " AND p.codigoPropio LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "propioDescripcion":
                            strQuery += " AND p.propioDescripcion LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "aplicaSubsidiado":
                            strQuery += " AND p.aplicaSubsidiado = " + e.getValue() + " ";
                            break;
                        case "aplicaContributivo":
                            strQuery += " AND p.aplicaContributivo = " + e.getValue() + " ";
                            break;
                        case "sexoAplica":
                            strQuery += " AND p.sexoAplica = " + e.getValue() + " ";
                            break;
                        case "esPbs":
                            strQuery += " AND p.esPbs = " + e.getValue() + " ";
                            break;
                        case "nivelComplejidad":
                            strQuery += " AND p.nivelComplejidad = " + e.getValue() + " ";
                            break;
                        case "unidadDesde":
                            strQuery += " AND p.unidadDesde = " + e.getValue() + " ";
                            break;
                        case "edadDesde":
                            strQuery += " AND p.edadDesde = " + e.getValue() + " ";
                            break;
                        case "edadHasta":
                            strQuery += " AND p.edadHasta = " + e.getValue() + " ";
                            break;
                        case "unidadHasta":
                            strQuery += " AND p.unidadHasta = " + e.getValue() + " ";
                            break;
                        case "codigoFinanciador":
                            strQuery += " AND p.codigoFinanciador LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "frecuencia":
                            strQuery += " AND p.frecuencia LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maIss2000Tarifario.monto":
                            strQuery += " AND p.maIss2000TarifariosId.monto = " + e.getValue() + " ";
                            break;
                        case "maIss2001Tarifario.monto":
                            strQuery += " AND p.maIss2001TarifariosId.monto = " + e.getValue() + " ";
                            break;
                        case "maSoatTarifario.valor":
                            strQuery += " AND p.maSoatTarifariosId.valor = " + e.getValue() + " ";
                            break;
                        case "cobertura":
                            strQuery += " AND p.cobertura = " + e.getValue() + " ";
                            break;
                        case "maeCoberturaId":
                            strQuery += " AND p.maeCoberturaId = " + e.getValue() + " ";
                            break;
                        case "activo":
                            strQuery += " AND p.activo = " + e.getValue() + " ";
                            break;
                        case "vigenciaAutorizacion":
                            strQuery += " AND p.vigenciaAutorizacion = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                switch (paramConsulta.getOrden()) {
                    case "maIss2000Tarifario.monto":
                        strQuery += "p.maIss2000TarifariosId.monto "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "maIss2001Tarifario.monto":
                        strQuery += "p.maIss2001TarifariosId.monto "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "maSoatTarifario.valor":
                        strQuery += "p.maSoatTarifariosId.valor "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    default:
                        strQuery += "p." + paramConsulta.getOrden() + " "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                }
            } else {
                strQuery += "p.id DESC";
            }
            List<MaTecnologias> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (MaTecnologias per : list) {
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
    public List<MaTecnologia> consultarListaTodos(ParamConsulta paramConsulta) throws Exception {
        List<MaTecnologia> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaTecnologias p WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maeGrupoTecnologiaId":
                            strQuery += " AND p.maeGrupoTecnologiaId = " + e.getValue() + " ";
                            break;
                        case "grupoDescripcion":
                            strQuery += " AND p.grupoDescripcion  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeServicioId":
                            strQuery += " AND p.maeServicioId  = " + e.getValue() + " ";
                            break;
                        case "maeServicioCodigo":
                            strQuery += " AND p.maeServicioCodigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeServicioValor":
                            strQuery += " AND p.maeServicioValor  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cups":
                            strQuery += " AND p.cups  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cupsDescipcion":
                            strQuery += " AND p.cupsDescipcion  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "abreviatura":
                            strQuery += " AND p.abreviatura  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "codigoPropio":
                            strQuery += " AND p.codigoPropio LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "propioDescripcion":
                            strQuery += " AND p.propioDescripcion LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "aplicaSubsidiado":
                            strQuery += " AND p.aplicaSubsidiado = " + e.getValue() + " ";
                            break;
                        case "aplicaContributivo":
                            strQuery += " AND p.aplicaContributivo = " + e.getValue() + " ";
                            break;
                        case "sexoAplica":
                            strQuery += " AND p.sexoAplica = " + e.getValue() + " ";
                            break;
                        case "esPbs":
                            strQuery += " AND p.esPbs = " + e.getValue() + " ";
                            break;
                        case "nivelComplejidad":
                            strQuery += " AND p.nivelComplejidad = " + e.getValue() + " ";
                            break;
                        case "unidadDesde":
                            strQuery += " AND p.unidadDesde = " + e.getValue() + " ";
                            break;
                        case "edadDesde":
                            strQuery += " AND p.edadDesde = " + e.getValue() + " ";
                            break;
                        case "edadHasta":
                            strQuery += " AND p.edadHasta = " + e.getValue() + " ";
                            break;
                        case "unidadHasta":
                            strQuery += " AND p.unidadHasta = " + e.getValue() + " ";
                            break;
                        case "codigoFinanciador":
                            strQuery += " AND p.codigoFinanciador LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "frecuencia":
                            strQuery += " AND p.frecuencia LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maIss2000Tarifario.monto":
                            strQuery += " AND p.maIss2000TarifariosId.monto = " + e.getValue() + " ";
                            break;
                        case "maIss2001Tarifario.monto":
                            strQuery += " AND p.maIss2001TarifariosId.monto = " + e.getValue() + " ";
                            break;
                        case "maSoatTarifario.valor":
                            strQuery += " AND p.maSoatTarifariosId.valor = " + e.getValue() + " ";
                            break;
                        case "cobertura":
                            strQuery += " AND p.cobertura = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                switch (paramConsulta.getOrden()) {
                    case "maIss2000Tarifario.monto":
                        strQuery += "p.maIss2000TarifariosId.monto "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "maIss2001Tarifario.monto":
                        strQuery += "p.maIss2001TarifariosId.monto "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "maSoatTarifario.valor":
                        strQuery += "p.maSoatTarifariosId.valor "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    default:
                        strQuery += "p." + paramConsulta.getOrden() + " "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                }
            } else {
                strQuery += "p.id DESC";
            }
            List<MaTecnologias> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaTecnologias per : list) {
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
    public MaTecnologia consultar(int id) throws Exception {
        MaTecnologia objRes = null;
        try {
            objRes = castEntidadNegocio((MaTecnologias) getEntityManager().find(MaTecnologias.class, id));
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
    public int insertar(MaTecnologia obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(_id);
        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    @Override
    public void actualizar(MaTecnologia obj) throws Exception {
        try {
            //getEntityManager().merge(castNegocioEntidad(obj));
            MaTecnologias tecnologia = castNegocioEntidad(obj);
            Session session = getEntityManager().unwrap(Session.class);
            //session.update(per);
            String strQuery = "UPDATE MaTecnologias a SET ";
            strQuery += " a.maeGrupoTecnologiaId = :maeGrupoTecnologiaId ,";
            strQuery += " a.maeGrupoTecnologiaCodigo = :maeGrupoTecnologiaCodigo ,";
            strQuery += " a.maeGrupoTecnologiaValor = :maeGrupoTecnologiaValor ,";
            strQuery += " a.maeTipoTecnologiaId = :maeTipoTecnologiaId ,";
            strQuery += " a.maeTipoTecnologiaCodigo = :maeTipoTecnologiaCodigo ,";
            strQuery += " a.maeTipoTecnologiaValor = :maeTipoTecnologiaValor ,";
            strQuery += " a.grupoDescripcion = :grupoDescripcion ,";
            strQuery += " a.cups = :cups ,";
            strQuery += " a.cupsDescipcion = :cupsDescipcion ,";
            strQuery += " a.codigoPropio = :codigoPropio ,";
            strQuery += " a.propioDescripcion = :propioDescripcion ,";
            strQuery += " a.aplicaSubsidiado = :aplicaSubsidiado ,";
            strQuery += " a.aplicaContributivo = :aplicaContributivo ,";
            strQuery += " a.sexoAplica = :sexoAplica ,";
            strQuery += " a.maeCoberturaId = :maeCoberturaId ,";
            strQuery += " a.maeCoberturaCodigo = :maeCoberturaCodigo ,";
            strQuery += " a.maeCoberturaValor = :maeCoberturaValor ,";
            strQuery += " a.cobertura = :cobertura ,";
            strQuery += " a.nivelComplejidad = :nivelComplejidad ,";
            strQuery += " a.edadDesde = :edadDesde ,";
            strQuery += " a.unidadDesde = :unidadDesde ,";
            strQuery += " a.edadHasta = :edadHasta ,";
            strQuery += " a.unidadHasta = :unidadHasta ,";
            strQuery += " a.codigoFinanciador = :codigoFinanciador ,";
            strQuery += " a.tipoFrecuencia = :tipoFrecuencia ,";
            strQuery += " a.frecuencia = :frecuencia ,";
            strQuery += " a.tipoFrecuencia2 = :tipoFrecuencia2 ,";
            strQuery += " a.frecuencia2 = :frecuencia2 ,";
            strQuery += " a.eventoUnico = :eventoUnico ,";
            strQuery += " a.activo = :activo ,";
            strQuery += " a.maeAmbitoId = :maeAmbitoId ,";
            strQuery += " a.maeAmbitoCodigo = :maeAmbitoCodigo ,";
            strQuery += " a.maeAmbitoValor = :maeAmbitoValor ,";
            strQuery += " a.tipoPago = :tipoPago ,";
            strQuery += " a.complejidad = :complejidad ,";
            strQuery += " a.aclaracion = :aclaracion ,";
            strQuery += " a.condicion = :condicion ,";
            strQuery += " a.vigenciaAutorizacion = :vigenciaAutorizacion ,";
            //campos auditoria
            strQuery += " a.usuarioModifica = :usuarioModifica ,";
            strQuery += " a.terminalModifica = :terminalModifica ,";
            //campo fechas
            //SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatoFechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fechaModifica = formatoFechaHora.format(obj.getFechaHoraModifica());
            strQuery += " a.fechaHoraModifica = '" + fechaModifica + "' ";
            //campos objetos
            if (tecnologia.getMaIss2000TarifariosId() != null) {
                strQuery += ", a.maIss2000TarifariosId.id = " + tecnologia.getMaIss2000TarifariosId().getId() + " ";
            }
            if (tecnologia.getMaIss2001TarifariosId() != null) {
                strQuery += ", a.maIss2001TarifariosId.id = " + tecnologia.getMaIss2001TarifariosId().getId() + " ";
            }
            if (tecnologia.getMaSoatTarifariosId() != null) {
                strQuery += ", a.maSoatTarifariosId.id = " + tecnologia.getMaSoatTarifariosId().getId() + " ";
            }

            strQuery += " WHERE a.id = :id ";
            org.hibernate.Query query = session.createQuery(strQuery);
            query.setProperties(tecnologia);
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public MaTecnologia eliminar(int id) throws Exception {
        MaTecnologia obj = null;
        try {
            MaTecnologias ent = getEntityManager().find(MaTecnologias.class, id);
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

    @Override
    public List<MaTecnologia> consultarTodos() throws Exception {
        List<MaTecnologia> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaTecnologias p "
                    + "ORDER BY p.id ";
            List<MaTecnologias> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaTecnologias per : list) {
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
    public List<MaTecnologia> consultarTodosCorto() throws Exception {
        List<MaTecnologia> listResult = new ArrayList();
        try {
            String strQuery = "SELECT t.id, "
                    + "t.activo, "
                    + "t.codigoPropio, "
                    + "t.propioDescripcion "
                    + "FROM MaTecnologias t ORDER by t.id ";
            Query q = getEntityManager().createQuery(strQuery);
            List<Object[]> lista = q.getResultList();
            for (Object[] per : lista) {
                MaTecnologia tecnologia = new MaTecnologia();
                tecnologia.setId((Integer) per[0]);
                tecnologia.setActivo((Boolean) per[1]);
                tecnologia.setCodigoPropio(per[2].toString());
                tecnologia.setPropioDescripcion(per[3].toString());
                listResult.add(tecnologia);
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

    public static MaTecnologia castEntidadNegocio(MaTecnologias per) {
        MaTecnologia obj = new MaTecnologia();
        obj.setId(per.getId());
        obj.setActivo(per.getActivo());
        obj.setMaeGrupoTecnologiaId(per.getMaeGrupoTecnologiaId());
        obj.setMaeGrupoTecnologiaCodigo(per.getMaeGrupoTecnologiaCodigo());
        obj.setMaeGrupoTecnologiaValor(per.getMaeGrupoTecnologiaValor());
        obj.setGrupoDescripcion(per.getGrupoDescripcion());
        obj.setCups(per.getCups());
        obj.setCupsDescipcion(per.getCupsDescipcion());
        obj.setCodigoPropio(per.getCodigoPropio());
        obj.setPropioDescripcion(per.getPropioDescripcion());
        obj.setAplicaSubsidiado(per.getAplicaSubsidiado());
        obj.setAplicaContributivo(per.getAplicaContributivo());
        obj.setSexoAplica(per.getSexoAplica());
        //obj.setEsPbs(per.getEsPbs());
        obj.setNivelComplejidad(per.getNivelComplejidad());
        obj.setEdadDesde(per.getEdadDesde());
        obj.setEdadHasta(per.getEdadHasta());
        obj.setUnidadDesde(per.getUnidadDesde());
        obj.setUnidadHasta(per.getUnidadHasta());
        obj.setCodigoFinanciador(per.getCodigoFinanciador());
        obj.setFrecuencia(per.getFrecuencia());
        obj.setTipoFrecuencia(per.getTipoFrecuencia());
        //2021-04-23 jyperez nuevos campos
        obj.setTipoFrecuencia2(per.getTipoFrecuencia2());
        obj.setFrecuencia2(per.getFrecuencia2());
        obj.setCobertura(per.getCobertura());
        obj.setMaeCoberturaId(per.getMaeCoberturaId());
        obj.setMaeCoberturaCodigo(per.getMaeCoberturaCodigo());
        obj.setMaeCoberturaValor(per.getMaeCoberturaValor());
        //2022-03-22 jyperez nuevos campos
        obj.setEventoUnico(per.getEventoUnico());
        obj.setAclaracion(per.getAclaracion());
        obj.setCondicion(per.getCondicion());
        obj.setTipoPago(per.getTipoPago());
        obj.setVigenciaAutorizacion(per.getVigenciaAutorizacion());
        //objetos
        if (per.getMaTecnologiaGruposList() != null) {
            obj.setListaTecnologiaGrupos(new ArrayList<>());
            for (MaTecnologiaGrupos grupo : per.getMaTecnologiaGruposList()) {
                MaTecnologiaGrupo aux = castTecnologiaGrupoEntidadNegocio(grupo);
                obj.getListaTecnologiaGrupos().add(aux);
            }
        }
        if (per.getMaIss2000TarifariosId() != null) {
            obj.setMaIss2000Tarifario(castMaIss2000TarifarioEntidadNegocio(per.getMaIss2000TarifariosId()));
        }
        if (per.getMaIss2001TarifariosId() != null) {
            obj.setMaIss2001Tarifario(castMaIss2001TarifarioEntidadNegocio(per.getMaIss2001TarifariosId()));
        }
        if (per.getMaSoatTarifariosId() != null) {
            obj.setMaSoatTarifario(castMaSoatTarifarioEntidadNegocio(per.getMaSoatTarifariosId()));
        }
        if (per.getMaTecnologiasMipresList() != null) {
            List<MaTecnologiaMipres> list = new ArrayList();
            for (MaTecnologiasMipres aux : per.getMaTecnologiasMipresList()) {
                MaTecnologiaMipres tecmp = castMaTecnologiaMipresEntidadNegocio(aux);
                list.add(tecmp);
            }
            obj.setListaTecnologiaMipres(list);
        }
        //auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        return obj;
    }

    public static MaTecnologia castEntidadNegocioCorto(MaTecnologias per) {
        MaTecnologia obj = new MaTecnologia();
        obj.setId(per.getId());
        obj.setActivo(per.getActivo());
        obj.setCodigoPropio(per.getCodigoPropio());
        obj.setPropioDescripcion(per.getPropioDescripcion());
        //auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        return obj;
    }

    public static MaTecnologias castNegocioEntidad(MaTecnologia obj) {
        MaTecnologias per = new MaTecnologias();
        per.setId(obj.getId());
        per.setActivo(obj.isActivo());
        per.setMaeGrupoTecnologiaId(obj.getMaeGrupoTecnologiaId());
        per.setMaeGrupoTecnologiaCodigo(obj.getMaeGrupoTecnologiaCodigo());
        per.setMaeGrupoTecnologiaValor(obj.getMaeGrupoTecnologiaValor());
        per.setGrupoDescripcion(obj.getGrupoDescripcion());
        per.setCups(obj.getCups());
        per.setCupsDescipcion(obj.getCupsDescipcion().trim());
        per.setCodigoPropio(obj.getCodigoPropio());
        per.setPropioDescripcion(obj.getPropioDescripcion().trim());
        per.setAplicaSubsidiado(obj.getAplicaSubsidiado());
        per.setAplicaContributivo(obj.getAplicaContributivo());
        per.setSexoAplica(obj.getSexoAplica());
        //per.setEsPbs(obj.isEsPbs());
        per.setNivelComplejidad(obj.getNivelComplejidad());
        per.setEdadDesde(obj.getEdadDesde());
        per.setEdadHasta(obj.getEdadHasta());
        per.setUnidadDesde(obj.getUnidadDesde());
        per.setUnidadHasta(obj.getUnidadHasta());
        per.setCodigoFinanciador(obj.getCodigoFinanciador());
        per.setFrecuencia(obj.getFrecuencia());
        //2021-04-23 jyperez nuevos campos
        per.setTipoFrecuencia2(obj.getTipoFrecuencia2());
        per.setFrecuencia2(obj.getFrecuencia2());
        per.setTipoFrecuencia(obj.getTipoFrecuencia());
        per.setCobertura(obj.getCobertura());
        per.setMaeCoberturaId(obj.getMaeCoberturaId());
        per.setMaeCoberturaCodigo(obj.getMaeCoberturaCodigo());
        per.setMaeCoberturaValor(obj.getMaeCoberturaValor());
        //2022-03-22 jyperez nuevos campos
        per.setEventoUnico(obj.getEventoUnico());
        per.setAclaracion(obj.getAclaracion());
        per.setCondicion(obj.getCondicion());
        per.setTipoPago(obj.getTipoPago());
        per.setVigenciaAutorizacion(obj.getVigenciaAutorizacion());
        //objetos
        if (obj.getMaIss2000Tarifario() != null) {
            per.setMaIss2000TarifariosId(new MaIss2000Tarifarios(obj.getMaIss2000Tarifario().getId()));
        }
        if (obj.getMaIss2001Tarifario() != null) {
            per.setMaIss2001TarifariosId(new MaIss2001Tarifarios(obj.getMaIss2001Tarifario().getId()));
        }
        if (obj.getMaSoatTarifario() != null) {
            per.setMaSoatTarifariosId(new MaSoatTarifarios(obj.getMaSoatTarifario().getId()));
        }
        //auditoria
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        return per;
    }

    private static MaIss2000Tarifario castMaIss2000TarifarioEntidadNegocio(MaIss2000Tarifarios per) {
        MaIss2000Tarifario obj = new MaIss2000Tarifario();
        obj.setId(per.getId());
        obj.setCodigo(per.getCodigo());
        obj.setDescripcion(per.getDescripcion());
        obj.setTipo(per.getTipo());
        obj.setUvr(per.getUvr());
        obj.setMonto(per.getMonto());
        //auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        return obj;
    }

    private static MaIss2001Tarifario castMaIss2001TarifarioEntidadNegocio(MaIss2001Tarifarios per) {
        MaIss2001Tarifario obj = new MaIss2001Tarifario();
        obj.setId(per.getId());
        obj.setCodigo(per.getCodigo());
        obj.setDescripcion(per.getDescripcion());
        obj.setTipo(per.getTipo());
        obj.setUvr(per.getUvr());
        obj.setMonto(per.getMonto());
        obj.setReferencia(per.getReferencia());
        //auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        return obj;
    }

    private static MaSoatTarifario castMaSoatTarifarioEntidadNegocio(MaSoatTarifarios per) {
        MaSoatTarifario obj = new MaSoatTarifario();
        obj.setId(per.getId());
        obj.setCodigo(per.getCodigo());
        obj.setDescripcion(per.getDescripcion());
        obj.setTipo(per.getTipo());
        obj.setGrupo(per.getGrupo());
        obj.setPuntos(per.getPuntos());
        //objetos
        if (per.getMaSoatTarifarioValoresList() != null) {
            List<MaSoatTarifarioValor> listaTarifarioValor = new ArrayList();
            MaSoatTarifarioValor tarifarioValor = new MaSoatTarifarioValor();
            for (MaSoatTarifarioValores valor : per.getMaSoatTarifarioValoresList()) {
                tarifarioValor = castMaSoatTarifarioValorEntidadNegocio(valor);
                listaTarifarioValor.add(tarifarioValor);
            }
            obj.setListaMaSoatTarifarioValor(listaTarifarioValor);
        }
        //auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        return obj;
    }

    private static MaSoatTarifarioValor castMaSoatTarifarioValorEntidadNegocio(MaSoatTarifarioValores per) {
        MaSoatTarifarioValor obj = new MaSoatTarifarioValor();

        obj.setId(per.getId());
        obj.setAgno(per.getAgno());
        obj.setValor(per.getValor());
        //objetos
        if (per.getMaSoatTarifariosId() != null) {
            obj.setMaSoatTarifario(new MaSoatTarifario(per.getMaSoatTarifariosId().getId()));
        }
        //auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        //obj.setFechaHoraModifica(per.getFechaHoraModifica());
        //obj.setTerminalModifica(per.getTerminalModifica());
        //obj.setUsuarioModifica(per.getUsuarioModifica());
        return obj;
    }

    public static MaTecnologiaMipres castMaTecnologiaMipresEntidadNegocio(MaTecnologiasMipres per) {
        MaTecnologiaMipres obj = new MaTecnologiaMipres();
        obj.setId(per.getId());
        obj.setCodigoMipres(per.getCodigoMipres());
        obj.setDescripcion(per.getDescripcion());
        //objeto
        if (per.getMaTecnologiasId() != null) {
            obj.setMaTecnologia(new MaTecnologia(per.getMaTecnologiasId().getId()));
        }
        if (per.getInsumosMipresId() != null) {
            MpCodigoInsumo cod = new MpCodigoInsumo(per.getInsumosMipresId().getId());
            //cod.setMaeInsumoId(per.getInsumosMipresId().getMaeInsumoId());
            cod.setCodigoMipres(per.getInsumosMipresId().getCodigoMipres());
            cod.setDescripcion(per.getInsumosMipresId().getDescripcion());
            cod.setActivo(per.getInsumosMipresId().getActivo());
            cod.setVersionMipres(per.getInsumosMipresId().getVersionMipres());
            //pendiente si necesitamos auditoria pero no se considera
            obj.setMpCodigoInsumo(cod);
        }
        //auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        return obj;
    }

    @Override
    public MaTecnologia consultarPorCodigo(String codigo) throws java.lang.Exception {
        MaTecnologia objRes = null;
        int i = 0;
        try {
            String strQuery = "FROM MaTecnologias p "
                    + "WHERE p.codigoPropio = '" + codigo + "'";
            objRes = castEntidadNegocio((MaTecnologias) getEntityManager().createQuery(strQuery).getSingleResult());
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    public static MaTecnologiaGrupo castTecnologiaGrupoEntidadNegocio(MaTecnologiaGrupos per) {
        MaTecnologiaGrupo obj = new MaTecnologiaGrupo();
        obj.setId(per.getId());
        obj.setMaeGrupoTecnologiaId(per.getMaeGrupoTecnologiaId());
        obj.setMaeGrupoTecnologiaCodigo(per.getMaeGrupoTecnologiaCodigo());
        obj.setMaeGrupoTecnologiaValor(per.getMaeGrupoTecnologiaValor());
        //objetos
        if (per.getMaTecnologiasId() != null) {
            obj.setMaTecnologia(new MaTecnologia(per.getMaTecnologiasId().getId()));
        }
        //auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        return obj;
    }

    @Override
    public int consultarCantidadListaBuscador(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        int i = 0;
        try {
            String strQuery = "SELECT COUNT(p) FROM MaTecnologias p WHERE p.id > 0 ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND p.activo = " + (String) paramConsulta.getParametroConsulta1() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maeGrupoTecnologiaId":
                            strQuery += " AND p.maeGrupoTecnologiaId = " + e.getValue() + " ";
                            break;
                        case "grupoDescripcion":
                            strQuery += " AND p.grupoDescripcion  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeServicioId":
                            strQuery += " AND p.maeServicioId  = " + e.getValue() + " ";
                            break;
                        case "maeServicioCodigo":
                            strQuery += " AND p.maeServicioCodigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeServicioValor":
                            strQuery += " AND p.maeServicioValor  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cups":
                            strQuery += " AND p.cups  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cupsDescipcion":
                            strQuery += " AND p.cupsDescipcion  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "abreviatura":
                            strQuery += " AND p.abreviatura  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "codigoPropio":
                            strQuery += " AND p.codigoPropio LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "propioDescripcion":
                            strQuery += " AND p.propioDescripcion LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "aplicaSubsidiado":
                            strQuery += " AND p.aplicaSubsidiado = " + e.getValue() + " ";
                            break;
                        case "aplicaContributivo":
                            strQuery += " AND p.aplicaContributivo = " + e.getValue() + " ";
                            break;
                        case "sexoAplica":
                            strQuery += " AND p.sexoAplica = " + e.getValue() + " ";
                            break;
                        case "esPbs":
                            strQuery += " AND p.esPbs = " + e.getValue() + " ";
                            break;
                        case "nivelComplejidad":
                            strQuery += " AND p.nivelComplejidad = " + e.getValue() + " ";
                            break;
                        case "unidadDesde":
                            strQuery += " AND p.unidadDesde = " + e.getValue() + " ";
                            break;
                        case "edadDesde":
                            strQuery += " AND p.edadDesde = " + e.getValue() + " ";
                            break;
                        case "edadHasta":
                            strQuery += " AND p.edadHasta = " + e.getValue() + " ";
                            break;
                        case "unidadHasta":
                            strQuery += " AND p.unidadHasta = " + e.getValue() + " ";
                            break;
                        case "codigoFinanciador":
                            strQuery += " AND p.codigoFinanciador LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "frecuencia":
                            strQuery += " AND p.frecuencia LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maIss2000Tarifario.id":
                            strQuery += " AND p.maIss200TarifariosId.id = " + e.getValue() + " ";
                            break;
                        case "maIss2001Tarifario.id":
                            strQuery += " AND p.maIss2001TarifariosId.id = " + e.getValue() + " ";
                            break;
                        case "maSoatTarifario.id":
                            strQuery += " AND p.maSoatTarifariosId.id = " + e.getValue() + " ";
                            break;
                        case "cobertura":
                            strQuery += " AND p.cobertura = " + e.getValue() + " ";
                            break;
                        case "activo":
                            strQuery += " AND p.activo = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
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
    public List<MaTecnologia> consultarListaBuscador(ParamConsulta paramConsulta) throws Exception {
        List<MaTecnologia> listResult = new ArrayList();
        try {
            String strQuery = "FROM MaTecnologias p WHERE p.id > 0 ";
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += " AND p.activo = " + (String) paramConsulta.getParametroConsulta1() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maeGrupoTecnologiaId":
                            strQuery += " AND p.maeGrupoTecnologiaId = " + e.getValue() + " ";
                            break;
                        case "grupoDescripcion":
                            strQuery += " AND p.grupoDescripcion  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeServicioId":
                            strQuery += " AND p.maeServicioId  = " + e.getValue() + " ";
                            break;
                        case "maeServicioCodigo":
                            strQuery += " AND p.maeServicioCodigo  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maeServicioValor":
                            strQuery += " AND p.maeServicioValor  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cups":
                            strQuery += " AND p.cups  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "cupsDescipcion":
                            strQuery += " AND p.cupsDescipcion  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "abreviatura":
                            strQuery += " AND p.abreviatura  LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "codigoPropio":
                            strQuery += " AND p.codigoPropio LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "propioDescripcion":
                            strQuery += " AND p.propioDescripcion LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "aplicaSubsidiado":
                            strQuery += " AND p.aplicaSubsidiado = " + e.getValue() + " ";
                            break;
                        case "aplicaContributivo":
                            strQuery += " AND p.aplicaContributivo = " + e.getValue() + " ";
                            break;
                        case "sexoAplica":
                            strQuery += " AND p.sexoAplica = " + e.getValue() + " ";
                            break;
                        case "esPbs":
                            strQuery += " AND p.esPbs = " + e.getValue() + " ";
                            break;
                        case "nivelComplejidad":
                            strQuery += " AND p.nivelComplejidad = " + e.getValue() + " ";
                            break;
                        case "unidadDesde":
                            strQuery += " AND p.unidadDesde = " + e.getValue() + " ";
                            break;
                        case "edadDesde":
                            strQuery += " AND p.edadDesde = " + e.getValue() + " ";
                            break;
                        case "edadHasta":
                            strQuery += " AND p.edadHasta = " + e.getValue() + " ";
                            break;
                        case "unidadHasta":
                            strQuery += " AND p.unidadHasta = " + e.getValue() + " ";
                            break;
                        case "codigoFinanciador":
                            strQuery += " AND p.codigoFinanciador LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "frecuencia":
                            strQuery += " AND p.frecuencia LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "maIss2000Tarifario.monto":
                            strQuery += " AND p.maIss2000TarifariosId.monto = " + e.getValue() + " ";
                            break;
                        case "maIss2001Tarifario.monto":
                            strQuery += " AND p.maIss2001TarifariosId.monto = " + e.getValue() + " ";
                            break;
                        case "maSoatTarifario.valor":
                            strQuery += " AND p.maSoatTarifariosId.valor = " + e.getValue() + " ";
                            break;
                        case "cobertura":
                            strQuery += " AND p.cobertura = " + e.getValue() + " ";
                            break;
                        case "activo":
                            strQuery += " AND p.activo = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                switch (paramConsulta.getOrden()) {
                    case "maIss2000Tarifario.monto":
                        strQuery += "p.maIss2000TarifariosId.monto "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "maIss2001Tarifario.monto":
                        strQuery += "p.maIss2001TarifariosId.monto "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    case "maSoatTarifario.valor":
                        strQuery += "p.maSoatTarifariosId.valor "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                    default:
                        strQuery += "p." + paramConsulta.getOrden() + " "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        break;
                }
            } else {
                strQuery += "p.id DESC";
            }
            List<MaTecnologias> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (MaTecnologias per : list) {
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
    public HashMap<String, MaTecnologia> consultarHash() throws Exception {
        HashMap<String, MaTecnologia> hashResult = new HashMap();
        try {
            String strQuery = "FROM MaTecnologias m "
                    + "ORDER BY m.id ";
            List<MaTecnologias> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (MaTecnologias per : list) {
                MaTecnologia obj = castEntidadNegocio(per);
                hashResult.put(obj.getCodigoPropio(), obj);
            }
        } catch (NoResultException e) {
            hashResult = new HashMap();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return hashResult;
    }

    @Override
    public List<MaTecnologia> consultarTodoSingleton() throws Exception {
        List<MaTecnologia> listResult = new ArrayList();
        try {
            String strQuery = "SELECT t.id, "
                    + "t.activo, "
                    + "t.codigoPropio, "
                    + "t.cupsDescipcion "
                    + "FROM MaTecnologias t ORDER by t.id ";
            Query q = getEntityManager().createQuery(strQuery);
            List<Object[]> lista = q.getResultList();
            for (Object[] per : lista) {
                MaTecnologia tecnologia = new MaTecnologia();
                tecnologia.setId((Integer) per[0]);
                tecnologia.setActivo((Boolean) per[1]);
                tecnologia.setCodigoPropio(per[2].toString());
                tecnologia.setCupsDescipcion(per[3].toString());
                listResult.add(tecnologia);
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

}
