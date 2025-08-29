/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.servicios.cargas;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.cargas.CarCarga;
import com.saviasaludeps.savia.dominio.cargas.CarPeriodo;
import com.saviasaludeps.savia.dominio.cargas.CarProceso;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CarCargas;
import com.saviasaludeps.savia.ejb.entidades.CarPeriodos;
import com.saviasaludeps.savia.ejb.entidades.CarProcesos;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadores;
import com.saviasaludeps.savia.ejb.entidades.GnEmpresas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.cargas.CarCargaRemoto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import org.hibernate.Session;

/**
 *
 * @author aguevara
 */
@Stateless
@Remote(CarCargaRemoto.class)
public class CarCargaServicio extends GenericoServicio implements CarCargaRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            StringBuilder strQuery = new StringBuilder("SELECT COUNT(c) FROM CarCargas c ");
            strQuery.append(" JOIN c.carPeriodosId cp "
                    + " JOIN c.carProcesosId crp ");
            
            if (paramConsulta.getEmpresaId() != null) {
                strQuery.append(" INNER JOIN GnEmpresas gne ON c.gnEmpresasId = gne.id ");
                strQuery.append("WHERE 1 = 1 ");
                strQuery.append("AND gne.id = ").append(paramConsulta.getEmpresaId()).append(" ");
            } else {
                strQuery.append("WHERE 1 = 1 ");
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "carProcesoId.nombre":
                            strQuery.append("AND crp.nombre = :nombreProceso ");
                            break;
                        case "estado":
                            strQuery.append("AND c.estado = :estado ");
                            break;
                    }
                }
            }

            // RANGO DE FECHA CARGA
            if (paramConsulta.getParametroConsulta1() != null && paramConsulta.getParametroConsulta2() != null) {
                strQuery.append("AND c.fechaHoraInicio >= :fh_inicio AND c.fechaHoraFin <= :fh_fin ");
            } else if (paramConsulta.getParametroConsulta1() != null) {
                strQuery.append("AND c.fechaHoraInicio >= :fh_inicio ");
            } else if (paramConsulta.getParametroConsulta2() != null) {
                strQuery.append("AND c.fechaHoraFin <= :fh_fin ");
            }

            Query query = getEntityManager().createQuery(strQuery.toString());

            // Asignar los parámetros de filtro
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    String key = (String) e.getKey();
                    String value = (String) e.getValue();

                    switch (key) {
                        case "carProcesoId.nombre":
                            query.setParameter("nombreProceso", value);
                            break;
                        case "estado":
                        try {
                            // Convierte el valor del estado a Short, si es requerido
                            query.setParameter("estado", Integer.valueOf(value));
                        } catch (NumberFormatException ex) {
                            throw new IllegalArgumentException("El valor del estado debe ser un número válido de tipo Short.");
                        }
                        break;
                    }
                }
            }

            // Asignar los parámetros de fecha
            if (paramConsulta.getParametroConsulta1() != null) {
                query.setParameter("fh_inicio", (Date) paramConsulta.getParametroConsulta1(), TemporalType.TIMESTAMP);
            }
            if (paramConsulta.getParametroConsulta2() != null) {
                query.setParameter("fh_fin", (Date) paramConsulta.getParametroConsulta2(), TemporalType.TIMESTAMP);
            }

            cant = ((Long) query.getSingleResult()).intValue();
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
    public List<CarCarga> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CarCarga> listaResultados = new ArrayList<>();
        try {
            StringBuilder strQuery = new StringBuilder("SELECT DISTINCT c FROM CarCargas c ");
            strQuery.append("JOIN c.carPeriodosId cp "
                    + "JOIN c.carProcesosId crp ");
            
            if (paramConsulta.getEmpresaId() != null) {
                strQuery.append(" INNER JOIN GnEmpresas gne ON c.gnEmpresasId = gne.id ");
                strQuery.append("WHERE 1 = 1 ");
                strQuery.append("AND gne.id = ").append(paramConsulta.getEmpresaId()).append(" ");
            } else {
                strQuery.append("WHERE 1 = 1 ");
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    String key = (String) e.getKey();
                    String value = (String) e.getValue();

                    if ("carProcesoId.nombre".equals(key)) {
                        strQuery.append("AND crp.nombre = :nombreProceso "); // Usando parámetro
                    } else if ("estado".equals(key)) {
                        strQuery.append("AND c.estado = :estado "); // Usando parámetro
                    }
                }
            }

            // RANGO DE FECHA CARGA
            if (paramConsulta.getParametroConsulta1() != null && paramConsulta.getParametroConsulta2() != null) {
                strQuery.append("AND c.fechaHoraInicio >= :fh_inicio AND c.fechaHoraFin <= :fh_fin ");
            } else if (paramConsulta.getParametroConsulta1() != null) {
                strQuery.append("AND c.fechaHoraInicio >= :fh_inicio ");
            } else if (paramConsulta.getParametroConsulta2() != null) {
                strQuery.append("AND c.fechaHoraFin <= :fh_fin ");
            }

            strQuery.append("ORDER BY ");
            if (paramConsulta.getOrden() != null) {
                strQuery.append("c.").append(paramConsulta.getOrden()).append(" ");
                strQuery.append(paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery.append("c.id DESC");
            }

            Query query = getEntityManager().createQuery(strQuery.toString());

            // Asignar los parámetros de fecha
            if (paramConsulta.getParametroConsulta1() != null) {
                query.setParameter("fh_inicio", (Date) paramConsulta.getParametroConsulta1(), TemporalType.TIMESTAMP);
            }
            if (paramConsulta.getParametroConsulta2() != null) {
                query.setParameter("fh_fin", (Date) paramConsulta.getParametroConsulta2(), TemporalType.TIMESTAMP);
            }

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    String key = (String) e.getKey();
                    String value = (String) e.getValue();

                    if ("carProcesoId.nombre".equals(key)) {
                        query.setParameter("nombreProceso", value);
                    } else if ("estado".equals(key)) {
                    try {
                            // Convierte el valor del estado a Short, si es requerido
                            query.setParameter("estado", Integer.valueOf(value));
                        } catch (NumberFormatException ex) {
                            throw new IllegalArgumentException("El valor del estado debe ser un número válido de tipo Short.");
                        }
                    }
                }
            }

            // Obtener la lista de CarCargas
            List<CarCargas> list = query
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();

            for (CarCargas cargas : list) {
                listaResultados.add(castEntidadNegocio(cargas));
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
    public CarCarga consultar(int id) throws java.lang.Exception {
        CarCarga objRes = null;
        try {

            objRes = castEntidadNegocio((CarCargas) getEntityManager().find(CarCargas.class, id));
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
    public boolean validarExiste(String nombre) throws java.lang.Exception {
        boolean existe = false;
        try {
            String strQuery = "FROM CarCargas a "
                    + "WHERE a.nombre = '" + nombre + "' ";
            List<CarCargas> list = getEntityManager().createQuery(strQuery).getResultList();
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
    public int insertar(CarCarga obj) throws java.lang.Exception {
        int _id = 0;
        try {
            CarCargas pro = castNegocioEntidad(obj);
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
    public CarCarga eliminar(int id) throws java.lang.Exception {
        CarCarga obj = null;
        try {
            CarCargas ent = getEntityManager().find(CarCargas.class, id);
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
    public void actualizar(CarCarga obj) throws java.lang.Exception {
        try {
            CarCargas carCargas = castNegocioEntidad(obj);
            Session session = getEntityManager().unwrap(Session.class);

            // Construcción de la consulta HQL de actualización
            String strQuery = "UPDATE CarCargas a SET ";
            strQuery += " a.estado = :estado ,";
            strQuery += " a.nombreArchivo = :nombreArchivo ,";
            strQuery += " a.ruta = :ruta ,";
            strQuery += " a.archivo = :archivo ,";
            strQuery += " a.existe = :existe ,";
            strQuery += " a.fechaHoraInicio = :fechaHoraInicio ,";
            strQuery += " a.fechaHoraFin = :fechaHoraFin ,";
            strQuery += " a.registros = :registros ,";
            strQuery += " a.exitosos = :exitosos ,";
            strQuery += " a.fallidos = :fallidos ,";
            strQuery += " a.usuarioModifica = :usuarioModifica ,";
            strQuery += " a.terminalModifica = :terminalModifica ,";
            strQuery += " a.fechaHoraModifica = :fechaHoraModifica ";

            // Si existen relaciones a actualizar, las incluimos en la consulta
            if (carCargas.getCntPrestadoresId() != null) {
                strQuery += ", a.cntPrestadoresId = :cntPrestadoresId ";
            }
            if (carCargas.getCarProcesosId() != null) {
                strQuery += ", a.carProcesosId = :carProcesosId ";
            }
            if (carCargas.getCarPeriodosId() != null) {
                strQuery += ", a.carPeriodosId = :carPeriodosId ";
            }

            // Cláusula WHERE
            strQuery += " WHERE a.id = :id";

            // Crear la consulta HQL
            Query query = session.createQuery(strQuery);

            // Establecer parámetros
            query.setParameter("estado", obj.getEstado());
            query.setParameter("nombreArchivo", obj.getNombreArchivo());
            query.setParameter("ruta", obj.getRuta());
            query.setParameter("archivo", obj.getArchivo());
            query.setParameter("existe", obj.isExiste());
            query.setParameter("fechaHoraInicio", obj.getFechaHoraInicio());
            query.setParameter("fechaHoraFin", obj.getFechaHoraFin());
            query.setParameter("registros", obj.getRegistros());
            query.setParameter("exitosos", obj.getExitosos());
            query.setParameter("fallidos", obj.getFallidos());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());

            // Establecer parámetros de las relaciones usando las ENTIDADES completas
            if (carCargas.getCntPrestadoresId() != null) {
                CntPrestadores prestador = session.get(CntPrestadores.class, obj.getPrestador().getId());
                query.setParameter("cntPrestadoresId", prestador);
            }
            if (carCargas.getCarProcesosId() != null) {
                CarProcesos carProceso = session.get(CarProcesos.class, obj.getCarProcesoId().getId());
                query.setParameter("carProcesosId", carProceso);
            }
            if (carCargas.getCarPeriodosId() != null) {
                CarPeriodos carPeriodo = session.get(CarPeriodos.class, obj.getCarPeriodoId().getId());
                query.setParameter("carPeriodosId", carPeriodo);
            }

            // Ejecutar la actualización
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
    public List<CarCarga> consultarListaPorUsuario(int id) throws java.lang.Exception {
        List<CarCarga> listaResultado = new ArrayList<>();
        try {
            String strQuery = "FROM CarCargas c "
                    + " WHERE c.gnEmpresasId.id = " + id
                    + " ORDER BY c.id DESC";
            List<CarCargas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (CarCargas proceso : list) {
                listaResultado.add(castEntidadNegocio(proceso));
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
    public List<CarCarga> consultarListaPorId(int id) throws java.lang.Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<CarCarga> consultarPorEstadoCola(int numeroMaximoRegistros) throws java.lang.Exception {
        List<CarCarga> listResult = new ArrayList();
        try {

            String strQuery = "FROM CarCargas c "
                    + "WHERE c.id > 0 "
                    + "AND c.estado = 0 "
                    + "ORDER BY c.id ASC ";

            List<CarCargas> list = getEntityManager().createQuery(strQuery).setMaxResults(numeroMaximoRegistros)
                    .getResultList();
            for (CarCargas per : list) {
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
    public void actualizarEstado(CarCarga obj) throws java.lang.Exception {
        try {
            String hql = "UPDATE CarCargas SET"
                    + " estado = :estado"
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            query.setParameter("estado", obj.getEstado());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (Exception e) {
            Exception(ACTUALIZAR, e, "Error al actualizar estado de la carga: " + obj.getCarProcesoId().getNombre());
        } finally {
            cerrarEntityManager();
        }

    }

    private static CarCarga castEntidadNegocio(CarCargas entidad) {
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

    private CarCargas castNegocioEntidad(CarCarga obj) {
        CarCargas ent = new CarCargas();
        ent.setEstado(obj.getEstado());
        ent.setNombreArchivo(obj.getNombreArchivo());
        ent.setRuta(obj.getRuta());
        ent.setArchivo(obj.getArchivo());
        ent.setExiste(obj.isExiste());
        ent.setFechaHoraFin(obj.getFechaHoraInicio());
        ent.setFechaHoraFin(obj.getFechaHoraFin());
        ent.setRegistros(obj.getRegistros());
        ent.setExitosos(obj.getExitosos());
        ent.setFallidos(obj.getFallidos());

        //Objetos
        if (obj.getEmpresaId() != null) {
            ent.setGnEmpresasId(new GnEmpresas(obj.getEmpresaId().getId()));
        }

        if (obj.getCarProcesoId() != null) {
            ent.setCarProcesosId(new CarProcesos(obj.getCarProcesoId().getId()));
        }

        if (obj.getPrestadorId() != null) {
            ent.setCntPrestadoresId(new CntPrestadores(obj.getPrestadorId().getPrestador().getId()));
        }

        if (obj.getCarPeriodoId() != null) {
            ent.setCarPeriodosId(new CarPeriodos(obj.getCarPeriodoId().getId()));
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

}
