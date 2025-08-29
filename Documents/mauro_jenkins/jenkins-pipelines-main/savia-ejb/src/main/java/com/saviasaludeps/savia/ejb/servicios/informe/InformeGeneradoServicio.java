package com.saviasaludeps.savia.ejb.servicios.informe;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.informe.InfGenerado;
import com.saviasaludeps.savia.dominio.informe.InfGrupo;
import com.saviasaludeps.savia.dominio.informe.InfInforme;
import com.saviasaludeps.savia.dominio.informe.InfInformeValor;
import com.saviasaludeps.savia.dominio.informe.InfInformeVariable;
import com.saviasaludeps.savia.ejb.entidades.InfInformeGenerados;
import com.saviasaludeps.savia.ejb.entidades.InfInformeValores;
import com.saviasaludeps.savia.ejb.entidades.InfInformes;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.informe.InformeGeneradoRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

@Stateless
@Remote(InformeGeneradoRemoto.class)
public class InformeGeneradoServicio extends GenericoServicio implements InformeGeneradoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(g) "
                    + "FROM InfInformeGenerados g "
                    + "WHERE g.id > 0 ";
            if (paramConsulta.getParametroConsulta1() != null) {
                int[] grupos = (int[]) paramConsulta.getParametroConsulta1();
                boolean primero = true;
                for (int i = 0; i <= (grupos.length - 1); i++) {
                    if (primero) {
                        strQuery += "AND (g.infInformesId.infGruposId.id = " + grupos[i] + " ";
                        primero = false;
                    } else {
                        strQuery += "OR g.infInformesId.infGruposId.id = " + grupos[i] + " ";
                    }
                }
                strQuery += ") ";
            }
            if (paramConsulta.getParametroConsulta2() != null && paramConsulta.getParametroConsulta3() != null) {
                strQuery += "AND (g.usuarioCrea LIKE '%" + paramConsulta.getParametroConsulta2() + "%' OR g.gnEmpresasId = " + paramConsulta.getParametroConsulta3() + " )";
            } else if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND g.usuarioCrea LIKE '%" + paramConsulta.getParametroConsulta2() + "%' ";
            } else if (paramConsulta.getParametroConsulta3() != null) {
                strQuery += "AND g.gnEmpresasId = " + paramConsulta.getParametroConsulta3() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "infInforme.grupo.id":
                            strQuery += "AND g.infInformesId.infGruposId.id = " + e.getValue() + " ";
                            break;
                        case "infInforme.id":
                            strQuery += "AND g.infInformesId.id = " + e.getValue() + " ";
                            break;
                        case "infInforme.programado":
                            strQuery += "AND g.infInformesId.programado = " + e.getValue() + " ";
                            break;
                        case "archivoNombre":
                            strQuery += "AND g.archivo LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "estado":
                            strQuery += "AND g.estado = " + (String) e.getValue() + " ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND g.usuarioCrea LIKE '%" + (String) e.getValue() + "%' ";
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
    public List<InfGenerado> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<InfGenerado> listResult = new ArrayList();
        try {
            String strQuery = "FROM InfInformeGenerados g "
                    + "WHERE g.id > 0 ";
            if (paramConsulta.getParametroConsulta1() != null) {
                int[] grupos = (int[]) paramConsulta.getParametroConsulta1();
                boolean primero = true;
                for (int i = 0; i <= (grupos.length - 1); i++) {
                    if (primero) {
                        strQuery += "AND (g.infInformesId.infGruposId.id = " + grupos[i] + " ";
                        primero = false;
                    } else {
                        strQuery += "OR g.infInformesId.infGruposId.id = " + grupos[i] + " ";
                    }
                }
                strQuery += ") ";
            }
            if (paramConsulta.getParametroConsulta2() != null && paramConsulta.getParametroConsulta3() != null) {
                strQuery += "AND (g.usuarioCrea LIKE '%" + paramConsulta.getParametroConsulta2() + "%' OR g.gnEmpresasId = " + paramConsulta.getParametroConsulta3() + " )";
            } else if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND g.usuarioCrea LIKE '%" + paramConsulta.getParametroConsulta2() + "%' ";
            } else if (paramConsulta.getParametroConsulta3() != null) {
                strQuery += "AND g.gnEmpresasId = " + paramConsulta.getParametroConsulta3() + " ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "infInforme.grupo.id":
                            strQuery += "AND g.infInformesId.infGruposId.id = " + e.getValue() + " ";
                            break;
                        case "infInforme.id":
                            strQuery += "AND g.infInformesId.id = " + e.getValue() + " ";
                            break;
                        case "infInforme.programado":
                            strQuery += "AND g.infInformesId.programado = " + e.getValue() + " ";
                            break;
                        case "archivoNombre":
                            strQuery += "AND g.archivo LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "estado":
                            strQuery += "AND g.estado = " + (String) e.getValue() + " ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND g.usuarioCrea LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "g." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "g.id DESC ";
            }
            List<InfInformeGenerados> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (InfInformeGenerados ent : list) {
                listResult.add(castEntidadNegocio(ent));
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
    public int insertar(InfGenerado obj) throws Exception {
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
    public InfGenerado consultar(int id) throws java.lang.Exception {
        InfGenerado objRes = null;
        try {
            objRes = castEntidadNegocio((InfInformeGenerados) getEntityManager().find(InfInformeGenerados.class, id));
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    private InfGenerado castEntidadNegocio(InfInformeGenerados ent) {
        InfGenerado neg = new InfGenerado();
        neg.setId(ent.getId());
        neg.setArchivo(ent.getArchivo());
        neg.setNombreArchivo(ent.getNombreArchivo());
        neg.setInfInforme(new InfInforme(ent.getInfInformesId().getId()));
        neg.getInfInforme().setNombre(ent.getInfInformesId().getNombre());
        neg.getInfInforme().setGrupo(new InfGrupo(ent.getInfInformesId().getInfGruposId().getId()));
        neg.getInfInforme().getGrupo().setNombre(ent.getInfInformesId().getInfGruposId().getNombre());
        neg.getInfInforme().setRequiereAprobacion(ent.getInfInformesId().getRequiereAprobacion());
        neg.getInfInforme().setProgramado(ent.getInfInformesId().getProgramado());
        neg.getInfInforme().setMultipleGeneracion(ent.getInfInformesId().getMultipleGeneracion());
        neg.getInfInforme().setMultipleEmpresa(ent.getInfInformesId().getMultipleEmpresa());
        neg.setListaValores(new ArrayList<>());
        if (ent.getInfInformeValoresList() != null) {
            for (InfInformeValores entValor : ent.getInfInformeValoresList()) {
                InfInformeValor valor = new InfInformeValor();
                valor.setVariable(entValor.getVariable());
                valor.setInfInformeVariable(
                        new InfInformeVariable(entValor.getInfInformeVariablesId().getId())
                );
                valor.getInfInformeVariable().setNombre(entValor.getInfInformeVariablesId().getNombre());
                valor.setVariable(entValor.getVariable());
                neg.getListaValores().add(valor);
            }
        }
        neg.setRuta(ent.getRuta());
        neg.setEstado(ent.getEstado());
        neg.setDescripcion(ent.getDescripcion());
        neg.setExiste(ent.getExiste());
        neg.setPlantilla(ent.getPlantilla());
        neg.setFechaHoraInicio(ent.getFechaHoraInicio());
        neg.setFechaHoraFin(ent.getFechaHoraFin());
        neg.setTiempo(ent.getTiempo());
        neg.setGnEmpresa(ent.getGnEmpresasId());
        neg.setFechaHoraCrea(ent.getFechaHoraCrea());
        neg.setTerminalCrea(ent.getTerminalCrea());
        neg.setUsuarioCrea(ent.getUsuarioCrea());
        return neg;
    }

    private InfInformeGenerados castNegocioEntidad(InfGenerado neg) {
        InfInformeGenerados ent = new InfInformeGenerados();
        ent.setArchivo(neg.getArchivo());
        ent.setEstado(neg.getEstado());
        ent.setNombreArchivo(neg.getNombreArchivo());
        ent.setInfInformesId(new InfInformes(neg.getInfInforme().getId()));
        ent.setRuta(neg.getRuta());
        ent.setDescripcion(neg.getDescripcion());
        ent.setExiste(neg.isExiste());
        ent.setPlantilla(neg.getPlantilla());
        ent.setFechaHoraInicio(neg.getFechaHoraInicio());
        ent.setFechaHoraFin(neg.getFechaHoraFin());
        ent.setTiempo(neg.getTiempo());
        ent.setGnEmpresasId(neg.getGnEmpresa());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        ent.setTerminalCrea(neg.getTerminalCrea());
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        return ent;
    }

    @Override
    public InfGenerado eliminar(int id) throws Exception {
        InfGenerado obj = null;
        try {
            InfInformeGenerados ent = getEntityManager().find(InfInformeGenerados.class, id);
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
    public List<InfGenerado> consultarPorInforme(int idInforme) throws Exception {
        List<InfGenerado> listResult = new ArrayList();
        try {
            String strQuery = "FROM InfInformeGenerados g "
                    + "WHERE g.estado = 2 AND g.existe = 1 AND g.infInformesId.id = " + idInforme;
            List<InfInformeGenerados> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (InfInformeGenerados ent : list) {
                listResult.add(castEntidadNegocio(ent));
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
    public boolean consultarLimiteGeneracion(String usuario, String cantidad) throws Exception {
        boolean permitir = false;
        try {
            if (cantidad != null) {
                int cant = 0;
                String strQuery = "SELECT COUNT(g) "
                        + "FROM InfInformeGenerados g "
                        + "WHERE g.usuarioCrea LIKE '%" + usuario + "%' "
                        + " AND g.estado < 2";
                cant = (int) (long) getEntityManager().createQuery(strQuery)
                        .getSingleResult();

                Integer limite = Integer.parseInt(cantidad);
                if (cant < limite) {
                    permitir = true;
                }
            }
        } catch (NoResultException e) {
            permitir = false;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return permitir;
    }

}
