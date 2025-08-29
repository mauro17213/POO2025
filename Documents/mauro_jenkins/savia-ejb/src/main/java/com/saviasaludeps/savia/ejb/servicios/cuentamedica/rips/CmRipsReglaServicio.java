package com.saviasaludeps.savia.ejb.servicios.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsRegla;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsReglaEntrada;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsReglaSalida;
import com.saviasaludeps.savia.ejb.entidades.CmRipsReglaEntradas;
import com.saviasaludeps.savia.ejb.entidades.CmRipsReglaSalidas;
import com.saviasaludeps.savia.negocio.cuentamedica.rips.CmRipsReglaRemoto;
import com.saviasaludeps.savia.ejb.entidades.CmRipsReglas;
import javax.persistence.Query;

@Stateless
@Remote(CmRipsReglaRemoto.class)
public class CmRipsReglaServicio extends GenericoServicio implements CmRipsReglaRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(r.id) FROM CmRipsReglas r "
                    + "WHERE r.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "tipo":
                            strQuery += "AND r.tipo = " + e.getValue() + " ";
                            break;
                        case "maeCntTipoContratoId":
                            strQuery += "AND r.maeCntTipoContratoId = " + e.getValue() + " ";
                            break;
                        case "nombre":
                            strQuery += "AND r.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "activa":
                            strQuery += "AND r.activa = " + e.getValue() + " ";
                            break;
                        case "archivo":
                            strQuery += "AND r.archivo = '" + e.getValue() + "' ";
                            break;
                        case "funcion":
                            strQuery += "AND r.funcion LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "alerta":
                            strQuery += "AND r.alerta = " + e.getValue() + " ";
                            break;
                        case "bdEjecucion":
                            strQuery += "AND r.bdEjecucion = " + e.getValue() + " ";
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
    public List<CmRipsRegla> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CmRipsRegla> listResult = new ArrayList();
        int i = 0;
        try {
            String strQuery = "SELECT r FROM CmRipsReglas r "
                    + "WHERE r.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "tipo":
                            strQuery += "AND r.tipo = " + e.getValue() + " ";
                            break;
                        case "maeCntTipoContratoId":
                            strQuery += "AND r.maeCntTipoContratoId = " + e.getValue() + " ";
                            break;
                        case "nombre":
                            strQuery += "AND r.nombre LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "activa":
                            strQuery += "AND r.activa = " + e.getValue() + " ";
                            break;
                        case "archivo":
                            strQuery += "AND r.archivo = '" + e.getValue() + "' ";
                            break;
                        case "alerta":
                            strQuery += "AND r.alerta = " + e.getValue() + " ";
                            break;
                        case "funcion":
                            strQuery += "AND r.funcion LIKE '%" + e.getValue() + "%' ";
                            break;
                        case "bdEjecucion":
                            strQuery += "AND r.bdEjecucion = " + e.getValue() + " ";
                            break;   
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "r." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "r.orden ASC";
            }
            List<CmRipsReglas> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (CmRipsReglas per : list) {
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
    public CmRipsRegla consultar(int id) throws Exception {
        CmRipsRegla objRes = null;
        try {
            objRes = castEntidadNegocio((CmRipsReglas) getEntityManager().find(CmRipsReglas.class, id));
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
    public int insertar(CmRipsRegla obj) throws Exception {
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
    public void actualizar(CmRipsRegla obj) throws Exception {
        try {
            String hql = "UPDATE CmRipsReglas SET "
                    + "tipo = :tipo, "
                    + "maeCntTipoContratoId = :mae_cnt_tipo_contrato_id, "
                    + "nombre = :nombre, "
                    + "descripcion = :descripcion, "
                    + "alerta = :alerta, "
                    + "orden = :orden, "
                    + "fechaInicial = :fecha_inicial, "
                    + "fechaFinal = :fecha_final, "
                    + "activa = :activa, "
                    + "archivo = :archivo, "
                    + "funcion = :funcion, "
                    + "bdEjecucion = :bdEjecucion, "
                    + "usuarioModifica = :usuario_modifica, "
                    + "terminalModifica = :terminal_modifica, "
                    + "fechaHoraModifica = :fecha_hora_modifica "
                    + "WHERE id = :id ";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("tipo", obj.getTipo());
            query.setParameter("mae_cnt_tipo_contrato_id", obj.getMaeCntTipoContratoId());
            query.setParameter("nombre", obj.getNombre());
            query.setParameter("descripcion", obj.getDescripcion());
            query.setParameter("alerta", obj.getAlerta());
            query.setParameter("orden", obj.getOrden());
            query.setParameter("fecha_inicial", obj.getFechaInicial());
            query.setParameter("fecha_final", obj.getFechaFinal());
            query.setParameter("activa", obj.isActiva());
            query.setParameter("archivo", obj.getArchivo());
            query.setParameter("funcion", obj.getFuncion());
            query.setParameter("bdEjecucion", obj.getBdEjecucion());
            query.setParameter("usuario_modifica", obj.getUsuarioModifica());
            query.setParameter("terminal_modifica", obj.getTerminalModifica());
            query.setParameter("fecha_hora_modifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());

            query.executeUpdate();
            //Actualizar roles
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public CmRipsRegla eliminar(int id) throws Exception {
        CmRipsRegla obj = null;
        try {
            CmRipsReglas ent = getEntityManager().find(CmRipsReglas.class, id);
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

    public static CmRipsRegla castEntidadNegocio(CmRipsReglas per) {
        CmRipsRegla neg = new CmRipsRegla();
        neg.setId(per.getId());
        neg.setMaeCntTipoContratoId(per.getMaeCntTipoContratoId());
        neg.setMaeCntTipoContratoCodigo(per.getMaeCntTipoContratoCodigo());
        if (per.getMaeCntTipoContratoValor() != null) {
            neg.setMaeCntTipoContratoId(per.getMaeCntTipoContratoId());
        }
        neg.setNombre(per.getNombre());
        neg.setDescripcion(per.getDescripcion());
        neg.setAlerta(per.getAlerta());
        neg.setOrden(per.getOrden());
        neg.setFechaInicial(per.getFechaInicial());
        neg.setFechaFinal(per.getFechaFinal());
        neg.setActiva(per.getActiva());
        neg.setArchivo(per.getArchivo());
        neg.setTipo(per.getTipo());
        if (per.getFuncion() != null) {
            neg.setFuncion(per.getFuncion());
        }
        neg.setFuncion(per.getFuncion());
        if (per.getJsonRegla() != null) {
            neg.setJsonRegla(per.getJsonRegla());
        }
        if (per.getCmRipsReglaEntradasList() != null) {
            neg.setListaCmRipsReglaEntrada(new ArrayList<>());
            for (CmRipsReglaEntradas cmRipsReglaEntradas : per.getCmRipsReglaEntradasList()) {
                CmRipsReglaEntrada cmRipsReglaEntrada = new CmRipsReglaEntrada();
                cmRipsReglaEntrada.setId(cmRipsReglaEntradas.getId());
                cmRipsReglaEntrada.setArchivo(cmRipsReglaEntradas.getArchivo());
                cmRipsReglaEntrada.setCampo(cmRipsReglaEntradas.getCampo());
                cmRipsReglaEntrada.setPosicion(cmRipsReglaEntradas.getPosicion());
                cmRipsReglaEntrada.setTipo(cmRipsReglaEntradas.getTipo());
                cmRipsReglaEntrada.setOrden(cmRipsReglaEntradas.getOrden());
                neg.getListaCmRipsReglaEntrada().add(cmRipsReglaEntrada);
            }
        }
        if (per.getCmRipsReglaSalidasList() != null) {
            neg.setListaCmRipsReglaSalida(new ArrayList<>());
            for (CmRipsReglaSalidas cmRipsReglaSalidas : per.getCmRipsReglaSalidasList()) {
                CmRipsReglaSalida cmRipsReglaSalida = new CmRipsReglaSalida();
                cmRipsReglaSalida.setId(cmRipsReglaSalidas.getId());
                cmRipsReglaSalida.setCodigo(cmRipsReglaSalidas.getCodigo());
                cmRipsReglaSalida.setDescripcion(cmRipsReglaSalidas.getDescripcion());
                neg.getListaCmRipsReglaSalida().add(cmRipsReglaSalida);
            }
        }
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setTerminalModifica(per.getTerminalModifica());
        neg.setBdEjecucion(per.getBdEjecucion());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setUsuarioModifica(per.getUsuarioModifica());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        neg.setFechaHoraModifica(per.getFechaHoraModifica());
        return neg;
    }

    public static CmRipsReglas castNegocioEntidad(CmRipsRegla obj) {
        CmRipsReglas ent = new CmRipsReglas();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        ent.setMaeCntTipoContratoId(obj.getMaeCntTipoContratoId());
        if (obj.getMaeCntTipoContratoValor() != null) {
            ent.setMaeCntTipoContratoValor(obj.getMaeCntTipoContratoValor());
        }
        if (obj.getMaeCntTipoContratoCodigo() != null) {
            ent.setMaeCntTipoContratoCodigo(obj.getMaeCntTipoContratoCodigo());
        }
        ent.setNombre(obj.getNombre());
        ent.setDescripcion(obj.getDescripcion());
        ent.setAlerta(obj.getAlerta());
        ent.setTipo(obj.getTipo());
        ent.setOrden(obj.getOrden());
        ent.setFechaInicial(obj.getFechaInicial());
        ent.setFechaFinal(obj.getFechaFinal());
        ent.setActiva(obj.isActiva());
        ent.setArchivo(obj.getArchivo());
        ent.setFuncion(obj.getFuncion());
        if (obj.getJsonRegla() != null) {
            ent.setJsonRegla(obj.getJsonRegla());
        }
        ent.setBdEjecucion(obj.getBdEjecucion());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setUsuarioModifica(obj.getUsuarioModifica());
        ent.setTerminalModifica(obj.getTerminalModifica());
        ent.setFechaHoraModifica(obj.getFechaHoraModifica());

        return ent;
    }

}
