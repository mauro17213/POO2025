/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.servicios.cargas;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.cargas.CarCarga;
import com.saviasaludeps.savia.dominio.cargas.CarCargaRegistro;
import com.saviasaludeps.savia.dominio.cargas.CarPeriodo;
import com.saviasaludeps.savia.dominio.cargas.CarProceso;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CarCargaRegistros;
import com.saviasaludeps.savia.ejb.entidades.CarCargas;
import com.saviasaludeps.savia.ejb.entidades.CarPeriodos;
import com.saviasaludeps.savia.ejb.entidades.CarProcesos;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadores;
import com.saviasaludeps.savia.ejb.entidades.GnEmpresas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.cargas.CarCargaGestionRemoto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author aguevara
 */
@Stateless
@Remote(CarCargaGestionRemoto.class)
public class CarCargaGestionServicio extends GenericoServicio implements CarCargaGestionRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT cc.id, ccr.tipo FROM CarCargaRegistros ccr ");
            strQuery.append("JOIN ccr.carCargasId cc " +
                    "JOIN cc.carProcesosId cp2 " +
                    "JOIN cc.cntPrestadoresId cp " +
                    "JOIN cc.carPeriodosId cp3 "
                    + " WHERE 1 = 1 AND cc.estado = 2 ");

            // Filtrar por la entidad completa de empresa
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery.append("AND cc.gnEmpresasId = :empresa ");
            }

            /*if (paramConsulta.getParametroConsulta2() != null) {
                strQuery.append("AND cc.cntPrestadoresId.id = :prestador ");
            }

            if (paramConsulta.getParametroConsulta3() != null) {
                strQuery.append("AND cc.carProcesosId.id = :idParam ");
            }*/

            // Aplicamos los filtros de paramConsulta
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry<String, Object> e : paramConsulta.getFiltros().entrySet()) {
                    switch (e.getKey()) {
                        case "nombre":
                            strQuery.append("AND cc.carProcesosId.nombre = :nombre ");
                            break;
                        case "estado":
                            strQuery.append("AND cc.estado = :estado ");
                            break;
                        case "carCargasId.carProcesoId.id":
                            strQuery.append("AND cc.carProcesosId.id = :idParam ");
                            break;
                        case "carCargasId.prestador.id":
                            strQuery.append("AND cc.cntPrestadoresId.id = :prestador ");
                            break;
                    }
                }
            }
            
            //debemos adicionar siempre el group by
            strQuery.append(" GROUP BY cc.id, ccr.tipo ");
            
            // Creamos la consulta
            Query query = getEntityManager().createQuery(strQuery.toString());

            // Enlazamos la entidad completa de empresa solo si se ha definido
            if (paramConsulta.getParametroConsulta1() != null) {
                Empresa empresa = (Empresa) paramConsulta.getParametroConsulta1();
                GnEmpresas gnEmpresa = new GnEmpresas();
                gnEmpresa.setId(empresa.getId());
                query.setParameter("empresa", gnEmpresa);  // Pasar la entidad de GnEmpresas
            }

            /*if (paramConsulta.getParametroConsulta2() != null) {
                query.setParameter("prestador",Integer.valueOf((String)paramConsulta.getParametroConsulta2()));
            }

            if (paramConsulta.getParametroConsulta3() != null) {
                query.setParameter("idParam", Integer.valueOf((String)paramConsulta.getParametroConsulta3()));
            }*/

            // Enlazar otros parámetros de filtro
            if (paramConsulta.getFiltros() != null) {
                if (paramConsulta.getFiltros().containsKey("nombre")) {
                    query.setParameter("nombre", "%" + paramConsulta.getFiltros().get("nombre") + "%");
                }
                if (paramConsulta.getFiltros().containsKey("estado")) {
                    query.setParameter("estado", paramConsulta.getFiltros().get("estado"));
                }
                if (paramConsulta.getFiltros().containsKey("carCargasId.carProcesoId.id")) {
                    query.setParameter("idParam", Integer.valueOf((String)paramConsulta.getFiltros().get("carCargasId.carProcesoId.id")));
                }
                if (paramConsulta.getFiltros().containsKey("carCargasId.prestador.id")) {
                    query.setParameter("prestador",Integer.valueOf((String)paramConsulta.getFiltros().get("carCargasId.prestador.id")));
                }
            }

            // Ejecutamos la consulta y obtenemos el resultado
            List<Object[]> list = query.getResultList();
            //procedemos a contar los registros
            if (list != null && !list.isEmpty()) {
                cant = list.size();
            }

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
    public List<CarCargaRegistro> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CarCargaRegistro> listaResultados = new ArrayList<>();
        try {
            // Consulta inicial
            StringBuilder strQuery = new StringBuilder("SELECT cc.id, cp2.nombre, cp.razonSocial , cc.exitosos , cc.fallidos , cc.estado "
                    + ", cp3.nombre, cc.fechaHoraCrea , ccr.tipo, cc.nombreArchivo , cc.usuarioCrea , cc.terminalCrea, cc.fechaHoraModifica, cc.usuarioModifica, cc.terminalModifica FROM CarCargaRegistros ccr ");
            strQuery.append("JOIN ccr.carCargasId cc " +
                    "JOIN cc.carProcesosId cp2 " +
                    "JOIN cc.cntPrestadoresId cp " +
                    "JOIN cc.carPeriodosId cp3 "
                    + " WHERE 1 = 1 AND cc.estado = 2 ");

            // Filtrar por la entidad completa de empresa
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery.append("AND cc.gnEmpresasId = :empresa ");
            }

            /*if (paramConsulta.getParametroConsulta2() != null) {
                strQuery.append("AND cc.cntPrestadoresId.id = :prestador ");
            }

            if (paramConsulta.getParametroConsulta3() != null) {
                strQuery.append("AND cc.carProcesosId.id = :idParam ");
            }*/

            // Aplicamos los filtros de paramConsulta
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry<String, Object> e : paramConsulta.getFiltros().entrySet()) {
                    switch (e.getKey()) {
                        case "nombre":
                            strQuery.append("AND cc.carProcesosId.nombre = :nombre ");
                            break;
                        case "estado":
                            strQuery.append("AND cc.estado = :estado ");
                            break;
                        case "carCargasId.carProcesoId.id":
                            strQuery.append("AND cc.carProcesosId.id = :idParam ");
                            break;
                        case "carCargasId.prestador.id":
                            strQuery.append("AND cc.cntPrestadoresId.id = :prestador ");
                            break;
                    }
                }
            }

            //debemos adicionar siempre el group by
            strQuery.append(" GROUP BY cc.id, ccr.tipo ");
            
            // Ordenar resultados
            strQuery.append("ORDER BY ");
            if (paramConsulta.getOrden() != null) {
                strQuery.append("cc.").append(paramConsulta.getOrden()).append(" ");
                strQuery.append(paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery.append("cc.id DESC");
            }

            // Crear la consulta
            Query query = getEntityManager().createQuery(strQuery.toString())
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina());

            // Enlazamos la entidad completa de empresa solo si se ha definido
            if (paramConsulta.getParametroConsulta1() != null) {
                Empresa empresa = (Empresa) paramConsulta.getParametroConsulta1();
                GnEmpresas gnEmpresa = new GnEmpresas();
                gnEmpresa.setId(empresa.getId());
                query.setParameter("empresa", gnEmpresa); // Pasar la entidad de GnEmpresas
            }

            /*if (paramConsulta.getParametroConsulta2() != null) {
                query.setParameter("prestador",Integer.valueOf((String)paramConsulta.getParametroConsulta2()));
            }

            if (paramConsulta.getParametroConsulta3() != null) {
                query.setParameter("idParam", Integer.valueOf((String)paramConsulta.getParametroConsulta3()));
            }*/

            // Enlazar parámetros de filtro
            if (paramConsulta.getFiltros() != null) {
                if (paramConsulta.getFiltros().containsKey("nombre")) {
                    query.setParameter("nombre", "%" + paramConsulta.getFiltros().get("nombre") + "%");
                }
                if (paramConsulta.getFiltros().containsKey("estado")) {
                    query.setParameter("estado", paramConsulta.getFiltros().get("estado"));
                }
                if (paramConsulta.getFiltros().containsKey("carCargasId.carProcesoId.id")) {
                    query.setParameter("idParam", Integer.valueOf((String)paramConsulta.getFiltros().get("carCargasId.carProcesoId.id")));
                }
                if (paramConsulta.getFiltros().containsKey("carCargasId.prestador.id")) {
                    query.setParameter("prestador",Integer.valueOf((String)paramConsulta.getFiltros().get("carCargasId.prestador.id")));
                }
            }

            // Ejecutar la consulta y recibimos objeto abstracto. Toca crear cada registro en la lista manualmente
            
            List<Object[]> list = query.getResultList();
            for (Object[] cargas : list) {
                int i = 0;
                CarCarga cc = new CarCarga();
                CarCargaRegistro ccr = new CarCargaRegistro();
                //cc.id
                cc.setId((Integer) cargas[i]);
                i++;
                //cp2.nombre as nombreCarga
                if(cargas[i] != null) {
                    cc.setCarProcesoId(new CarProceso());
                    cc.getCarProcesoId().setNombre(cargas[i].toString());
                } else {
                    cc.setCarProcesoId(new CarProceso());
                    cc.getCarProcesoId().setNombre("");
                }
                i++;
                //cp.razonSocial
                if(cargas[i] != null) {
                    cc.setPrestador(new CntPrestador());
                    cc.getPrestador().setRazonSocial(cargas[i].toString());
                } else {
                    cc.setPrestador(new CntPrestador());
                    cc.getPrestador().setRazonSocial("");
                }
                i++;
                //cc.exitosos
                if(cargas[i] != null) {
                    cc.setExitosos((int) cargas[i]);
                } else {
                    cc.setExitosos(0);
                }
                i++;
                //cc.fallidos
                if(cargas[i] != null) {
                    cc.setFallidos((int) cargas[i]);
                } else {
                    cc.setFallidos(0);
                }
                i++;
                //cc.estado
                cc.setEstado((int) cargas[i]);
                i++;
                // cp3.nombre
                if(cargas[i] != null) {
                    cc.setCarPeriodoId(new CarPeriodo());
                    cc.getCarPeriodoId().setNombre(cargas[i].toString());
                } else {
                    cc.setCarPeriodoId(new CarPeriodo());
                    cc.getCarPeriodoId().setNombre("");
                }
                i++;
                // cc.fechaHoraCrea
                cc.setFechaHoraCrea((Date) cargas[i]);
                i++;
                // ccr.tipo
                ccr.setTipo((int) cargas[i]);
                i++;
                //cc.nombreArchivo
                if (cargas[i] != null) {
                    cc.setNombreArchivo(cargas[i].toString());
                } else {
                    cc.setNombreArchivo("");
                }
                i++;
                //cc.usuarioCrea
                cc.setUsuarioCrea(cargas[i].toString());
                i++;
                //cc.terminalCrea
                cc.setTerminalCrea(cargas[i].toString());
                i++;
                //cc.fechaHoraModifica
                if (cargas[i] != null) {
                    cc.setFechaHoraModifica((Date) cargas[i]);
                }
                i++;
                //cc.usuarioModifica
                if (cargas[i] != null) {
                    cc.setUsuarioModifica(cargas[i].toString());
                }
                i++;
                //cc.terminalModifica
                if (cargas[i] != null) {
                    cc.setTerminalModifica(cargas[i].toString());
                }
                ccr.setCarCargasId(cc);
                listaResultados.add(ccr);
            }

        } catch (NoResultException e) {
            listaResultados = new ArrayList<>();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultados;
    }

    @Override
    public CarCargaRegistro consultar(int id) throws Exception {
        CarCargaRegistro objRes = null;
        try {

            objRes = castEntidadNegocio((CarCargaRegistros) getEntityManager().find(CarCargaRegistros.class, id));
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            objRes = null;
        } finally {
            cerrarEntityManager();
        }
        return objRes;

    }

    @Override
    public boolean validarExiste(String nombre) throws Exception {
        boolean existe = false;
        try {
            String strQuery = "FROM CarCargaRegistros a "
                    + "WHERE a.nombre = '" + nombre + "' ";
            List<CarCargaRegistros> list = getEntityManager().createQuery(strQuery).getResultList();
            if (list != null && list.size() > 0) {
                existe = true;
            }
        } catch (NoResultException e) {
            existe = false;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return existe;
    }

    @Override
    public int insertar(CarCargaRegistro obj) throws Exception {
        int _id = 0;
        try {
            CarCargaRegistros pro = castNegocioEntidad(obj);
            _id = (int) getEntityManager().merge(pro).getId();
            pro.setId(_id);
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
    public CarCargaRegistro eliminar(int id) throws Exception {
        CarCargaRegistro obj = null;
        try {
            CarCargaRegistros ent = getEntityManager().find(CarCargaRegistros.class, id);
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
    public void actualizar(CarCargaRegistro obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE CarCargaRegistros a SET ";
            strQuery += "a.carCargasId.id = :carCargasId ,";
            strQuery += "a.jsonDatos = :jsonDatos ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            if (obj.getCarCargasId() != null) {
                query.setParameter("carCargasId", obj.getCarCargasId().getId());
            } else {
                query.setParameter("carCargasId", null);
            }
            query.setParameter("jsonDatos", obj.getJsonDatos());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
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
    public List<CarCargaRegistro> consultarPorIdCarga(int carCargaId) throws Exception {
        List<CarCargaRegistro> listaResultado = new ArrayList<>();
        try {
            // Crear una consulta JPQL para buscar por carCargasId
            String strQuery = "FROM CarCargaRegistros cr"
                    + " WHERE cr.carCargasId.id = :carCargaId"
                    + " ORDER BY cr.id ASC";

            List<CarCargaRegistros> list = getEntityManager().createQuery(strQuery, CarCargaRegistros.class)
                    .setParameter("carCargaId", carCargaId)
                    .getResultList();

            for (CarCargaRegistros registro : list) {
                listaResultado.add(castEntidadNegocio(registro));
            }

        } catch (NoResultException e) {
            listaResultado = new ArrayList<>();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultado;
    }

    @Override
    public List<CarCargaRegistro> consultarListaTipoCarga(int tipo, int idCarga) throws Exception {
        List<CarCargaRegistro> listaResultado = new ArrayList<>();
        try {
            // Crear una consulta JPQL para buscar por carCargasId y tipo
            String strQuery = "FROM CarCargaRegistros cr "
                    + "WHERE cr.tipo = :tipo "
                    + "AND cr.carCargasId.id = :idCarga "
                    + "ORDER BY cr.id ASC";

            // Ejecutar la consulta
            List<CarCargaRegistros> list = getEntityManager().createQuery(strQuery, CarCargaRegistros.class)
                    .setParameter("tipo", tipo)
                    .setParameter("idCarga", idCarga)
                    .getResultList();

            // Mapear los resultados a la lista de negocio
            for (CarCargaRegistros registro : list) {
                listaResultado.add(castEntidadNegocio(registro));
            }

        } catch (NoResultException e) {
            listaResultado = new ArrayList<>();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultado;
    }

    private CarCargaRegistro castEntidadNegocio(CarCargaRegistros entidad) {
        CarCargaRegistro neg = new CarCargaRegistro();

        neg.setId(entidad.getId());
        neg.setFila(entidad.getFila());
        neg.setTipo(entidad.getTipo());
        neg.setJsonDatos(entidad.getJsonDatos());
        neg.setFallido(entidad.getFallido());
        neg.setFallos(entidad.getFallos());

        //Objetos     
        if (entidad.getCarCargasId().getId() != null) {
            neg.setCarCargasId(castCargasEntidadNegocio(entidad.getCarCargasId()));
        }

        //Auditoría
        neg.setUsuarioCrea(entidad.getUsuarioCrea());
        neg.setTerminalCrea(entidad.getTerminalCrea());
        neg.setFechaHoraCrea(entidad.getFechaHoraCrea());
        neg.setUsuarioModifica(entidad.getUsuarioModifica());
        neg.setTerminalModifica(entidad.getTerminalModifica());
        neg.setFechaHoraModifica(entidad.getFechaHoraModifica());
        return neg;

    }

    private CarCargaRegistros castNegocioEntidad(CarCargaRegistro obj) {
        CarCargaRegistros ent = new CarCargaRegistros();
        ent.setId(obj.getId());
        ent.setFila(obj.getFila());
        ent.setTipo(obj.getTipo());
        ent.setJsonDatos(obj.getJsonDatos());
        ent.setFallido(obj.isFallido());
        ent.setFallos(obj.getFallos());

        //Objetos
        if (obj.getCarCargasId() != null) {
            ent.setCarCargasId(new CarCargas(obj.getCarCargasId().getId()));
        }

        //Auditoria
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setUsuarioModifica(obj.getUsuarioModifica());
        ent.setTerminalModifica(obj.getTerminalModifica());
        ent.setFechaHoraModifica(obj.getFechaHoraModifica());
        return ent;

    }

    private CarCarga castCargasEntidadNegocio(CarCargas entidad) {
        CarCarga neg = new CarCarga();
        neg.setId(entidad.getId());

        neg.setEstado(entidad.getEstado());
        neg.setNombreArchivo(entidad.getNombreArchivo());
        neg.setRuta(entidad.getRuta());
        neg.setArchivo(entidad.getArchivo());
        neg.setExiste(entidad.getExiste());
        neg.setFechaHoraInicio(entidad.getFechaHoraInicio());
        neg.setFechaHoraFin(entidad.getFechaHoraFin());
        neg.setRegistros(entidad.getRegistros());
        neg.setExitosos(entidad.getExitosos());
        neg.setFallidos(entidad.getFallidos());

        //Objetos
        if (entidad.getGnEmpresasId() != null) {
            neg.setEmpresaId(new Empresa(entidad.getGnEmpresasId().getId()));
        }
        if (entidad.getCarProcesosId() != null) {
            neg.setCarProcesoId(castProcesoEntidadNegocio(entidad.getCarProcesosId()));
        }

        if (entidad.getCntPrestadoresId().getId() != null) {
            neg.setPrestador(castPrestadorEntidadNegocio(entidad.getCntPrestadoresId()));
        }

        if (entidad.getCarPeriodosId().getId() != null) {
            neg.setCarPeriodoId(castPeriodoEntidadNegocio(entidad.getCarPeriodosId()));
        }

        //Auditoría
        neg.setUsuarioCrea(entidad.getUsuarioCrea());
        neg.setTerminalCrea(entidad.getTerminalCrea());
        neg.setFechaHoraCrea(entidad.getFechaHoraCrea());
        neg.setUsuarioModifica(entidad.getUsuarioModifica());
        neg.setTerminalModifica(entidad.getTerminalModifica());
        neg.setFechaHoraModifica(entidad.getFechaHoraModifica());
        return neg;

    }

    private static CarProceso castProcesoEntidadNegocio(CarProcesos entidad) {
        CarProceso negocio = new CarProceso();
        negocio.setId(entidad.getId());
        negocio.setNombre(entidad.getNombre());
        negocio.setDescripcion(entidad.getDescripcion());
        negocio.setActivo(entidad.getActivo());
        negocio.setEditable(entidad.getEditable());
        negocio.setEstructuraJson(entidad.getEstructuraJson());

        //Auditoria
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        negocio.setTerminalModifica(entidad.getTerminalModifica());
        return negocio;
    }

    private static CntPrestador castPrestadorEntidadNegocio(CntPrestadores per) {
        CntPrestador obj = new CntPrestador();
        obj.setId(per.getId());
        obj.setCodigoMinSalud(per.getCodigoMinSalud());
        obj.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
        obj.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
        obj.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
        obj.setNumeroDocumento(per.getNumeroDocumento());
        obj.setDigitoVerificacion(per.getDigitoVerificacion());
        obj.setRazonSocial(per.getRazonSocial());
        obj.setNaturalezaJuridica(per.getNaturalezaJuridica());
        obj.setPrefijo(per.getPrefijo());
        obj.setMaeClasePrestador(per.getMaeClasePrestadorId());
        obj.setMaeClasePrestadorCodigo(per.getMaeClasePrestadorCodigo());
        obj.setMaeClasePrestadorValor(per.getMaeClasePrestadorValor());
        obj.setCategoriaPrestador(per.getCategoriaPrestador());
        obj.setNivelAtencion(per.getNivelAtencion());
        obj.setMaeTipoDocumentoRepId(per.getMaeTipoDocumentoRepId());
        obj.setMaeTipoDocumentoRepCodigo(per.getMaeTipoDocumentoRepCodigo());
        obj.setMaeTipoDocumentoRepValor(per.getMaeTipoDocumentoRepValor());
        obj.setNumeroDocumentoRep(per.getNumeroDocumentoRep());
        obj.setNombreRepresentanteLegal(per.getNombreRepresentanteLegal());
        //objetos
        //auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        return obj;
    }

    private static CarPeriodo castPeriodoEntidadNegocio(CarPeriodos entidad) {
        CarPeriodo negocio = new CarPeriodo();
        negocio.setId(entidad.getId());
        //Objetos
        if (entidad.getCarProcesosId() != null) {
            negocio.setCarProceso(castProcesoEntidadNegocio(entidad.getCarProcesosId()));
        }
        negocio.setNombre(entidad.getNombre());
        negocio.setFechaInicio(entidad.getFechaInicio());
        negocio.setFechaFin(entidad.getFechaFin());

        //Auditoria
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        negocio.setTerminalModifica(entidad.getTerminalModifica());
        return negocio;

    }

    @Override
    public List<CarProceso> listarProcesosUnicosPorUsuario(Long idUsuario, ParamConsulta paramConsulta) {
        List<CarProceso> listaProcesos = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder(
                    "SELECT DISTINCT pu.proceso "
                    + "FROM CarProcesoUsuarios pu "
                    + "WHERE pu.usuario.id = :idUsuario "
            );

            // Filtros adicionales si existen
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry<String, Object> filtro : paramConsulta.getFiltros().entrySet()) {
                    if ("procesoNombre".equals(filtro.getKey())) {
                        strQuery.append("AND pu.proceso.nombre = :procesoNombre ");
                    }
                }
            }

            Query query = getEntityManager().createQuery(strQuery.toString());
            query.setParameter("idUsuario", idUsuario);

            // Asignar parámetros adicionales
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry<String, Object> filtro : paramConsulta.getFiltros().entrySet()) {
                    if ("procesoNombre".equals(filtro.getKey())) {
                        query.setParameter("procesoNombre", filtro.getValue());
                    }
                }
            }

            listaProcesos = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(); // Registrar el error en el log del servidor
            throw new RuntimeException("Error al listar procesos únicos para el usuario con ID: " + idUsuario, e);
        }
        return listaProcesos;
    }

    @Override
    public List<CntPrestador> listarPrestadoresUnicosPorRegistros(ParamConsulta paramConsulta) {
        List<CntPrestador> listaPrestadores = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder(
                    "SELECT DISTINCT p "
                    + "FROM CarCargaRegistros r "
                    + "JOIN r.carCargasId c "
                    + "JOIN c.cntPrestadoresId p "
                    + "WHERE c.estado = 2 "
            );

            // Aplicar filtros adicionales de la consulta
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery.append("AND c.gnEmpresasId = :empresa ");
            }
            if (paramConsulta.getParametroConsulta2() != null) {
                strQuery.append("AND c.cntPrestadoresId = :prestador ");
            }

            Query query = getEntityManager().createQuery(strQuery.toString());

            // Asignar parámetros
            if (paramConsulta.getParametroConsulta1() != null) {
                Empresa empresa = (Empresa) paramConsulta.getParametroConsulta1();
                GnEmpresas gnEmpresa = new GnEmpresas();
                gnEmpresa.setId(empresa.getId());
                query.setParameter("empresa", gnEmpresa);
            }
            if (paramConsulta.getParametroConsulta2() != null) {
                CntPrestadores prestador = (CntPrestadores) paramConsulta.getParametroConsulta2();
                query.setParameter("prestador", prestador);
            }

            listaPrestadores = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(); // Registrar el error en el log del servidor
            throw new RuntimeException("Error al listar prestadores únicos basados en registros.", e);
        }
        return listaPrestadores;
    }

}
