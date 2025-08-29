/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.servicios.cargas;

import com.saviasaludeps.savia.dominio.cargas.CarProceso;
import com.saviasaludeps.savia.dominio.cargas.CarProcesoPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CarProcesoPrestadores;
import com.saviasaludeps.savia.ejb.entidades.CarProcesos;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadores;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.cargas.CarProcesoPrestadoresRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import org.hibernate.Session;

/**
 *
 * @author aguevara
 */
@Stateless
@Remote(CarProcesoPrestadoresRemoto.class)
public class CarProcesoPrestadoresServicio extends GenericoServicio implements CarProcesoPrestadoresRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {

        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM CarProcesoPrestadores c "
                    + " WHERE c.carProcesosId.id = " + paramConsulta.getParametroConsulta1();
            boolean agregarFiltro = true;
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        //Campos prestadores procesos

                        case "activo":
                            strQuery += "AND c.activo  = " + (String) e.getValue() + " ";
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
    public List<CarProcesoPrestador> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CarProcesoPrestador> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM CarProcesoPrestadores c"
                    + " WHERE c.carProcesosId.id = " + paramConsulta.getParametroConsulta1();
            boolean agregarFiltro = true;
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "activo":
                            strQuery += "AND c.activo  = " + (String) e.getValue() + " ";
                            break;

                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "c." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "c.id DESC";
            }
            List<CarProcesoPrestadores> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CarProcesoPrestadores procesos : list) {
                listaResultados.add(castEntidadNegocio(procesos));
            }
        } catch (NoResultException e) {
            listaResultados = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultados;
    }

    @Override
    public CarProcesoPrestador consultar(int id) throws Exception {
        CarProcesoPrestador objRes = null;
        try {
            objRes = castEntidadNegocio((CarProcesoPrestadores) getEntityManager().find(CarProcesoPrestadores.class, id));
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
            String strQuery = "FROM CarProcesosPrestadores a "
                    + "WHERE a.activo = '" + nombre + "' ";
            List<CarProcesoPrestadores> list = getEntityManager().createQuery(strQuery).getResultList();
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
    public int insertar(CarProcesoPrestador obj) throws Exception {
        int _id = 0;
        try {
            CarProcesoPrestadores pro = castNegocioEntidad(obj);
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
    public CarProcesoPrestador eliminar(int id) throws Exception {
        CarProcesoPrestador obj = null;
        try {
            CarProcesoPrestadores ent = getEntityManager().find(CarProcesoPrestadores.class, id);
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
    public void actualizar(CarProcesoPrestador obj) throws Exception {
        try {
            CarProcesoPrestadores carProcesoPrestadores = castNegocioEntidad(obj);
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE CarProcesoPrestadores a SET ";
            strQuery += " a.activo = :activo ,";
            strQuery += " a.usuarioModifica = :usuarioModifica ,";
            strQuery += " a.fechaHoraModifica = :fechaHoraModifica ,";
            strQuery += " a.terminalModifica = :terminalModifica ";
            //campos objetos
            // campos objetos 
            if (carProcesoPrestadores.getCntPrestadoresId() != null) {
                strQuery += ", a.cntPrestadoresId.id = " + carProcesoPrestadores.getCntPrestadoresId().getId() + " ";
            }
            if (carProcesoPrestadores.getCarProcesosId() != null) {
                strQuery += ", a.carProcesosId.id = " + carProcesoPrestadores.getCarProcesosId().getId() + " ";
            }
            strQuery += " WHERE a.id = :id ";
            org.hibernate.Query query = session.createQuery(strQuery);
            query.setProperties(carProcesoPrestadores);
            query.executeUpdate();
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public List<CarProcesoPrestador> listarPorIdProceso(int id) throws Exception {
        List<CarProcesoPrestador> listaResultado = new ArrayList<>();
        try {
            String strQuery = "FROM CarProcesoPrestadores c "
                    + " WHERE c.carProcesosId.id = " + id
                    + " ORDER BY c.id DESC";
            List<CarProcesoPrestadores> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (CarProcesoPrestadores proceso : list) {
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
    public List<CarProcesoPrestador> consultarListaTotal(ParamConsulta paramConsulta) throws java.lang.Exception {
        List<CarProcesoPrestador> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM CarProcesoPrestadores c WHERE 1 = 1";                  
            boolean agregarFiltro = true;
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {

                        case "nombre":
                            strQuery += "AND c.nombre  LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "activo":
                            strQuery += "AND c.activo  = " + (String) e.getValue() + " ";
                            break;

                    }
                }
            }

            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "c." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "c.id DESC";
            }
            List<CarProcesoPrestadores> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CarProcesoPrestadores procesos : list) {
                listaResultados.add(castEntidadNegocio(procesos));
            }
        } catch (NoResultException e) {
            listaResultados = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultados;

    }

    @Override
    public int consultarCantidadListaTotal(ParamConsulta paramConsulta) throws java.lang.Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM CarProcesoPrestadores c "
                    + " WHERE 1 = 1 ";
            boolean agregarFiltro = true;
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {

                        case "nombre":
                            strQuery += "AND c.nombre  LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "activo":
                            strQuery += "AND c.activo  = " + (String) e.getValue() + " ";
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

    private CarProcesoPrestador castEntidadNegocio(CarProcesoPrestadores entidad) {
        CarProcesoPrestador neg = new CarProcesoPrestador();
        neg.setId(entidad.getId());
        neg.setActivo(entidad.getActivo());

        //Objetos
        if (entidad.getCarProcesosId() != null) {
            neg.setProceso(castProcesoEntidadNegocio(entidad.getCarProcesosId()));
        }

        if (entidad.getCntPrestadoresId().getId() != null) {
            neg.setPrestador(castPrestadorEntidadNegocio(entidad.getCntPrestadoresId()));
        }

        //auditoria
        neg.setUsuarioCrea(entidad.getUsuarioCrea());
        neg.setTerminalCrea(entidad.getTerminalCrea());
        neg.setFechaHoraCrea(entidad.getFechaHoraCrea());
        neg.setUsuarioModifica(entidad.getUsuarioModifica());
        neg.setTerminalModifica(entidad.getTerminalModifica());
        neg.setFechaHoraModifica(entidad.getFechaHoraModifica());
        return neg;

    }

    private CarProceso castProcesoEntidadNegocio(CarProcesos entidad) {
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

    private CntPrestador castPrestadorEntidadNegocio(CntPrestadores per) {
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

    private static CarProcesoPrestadores castNegocioEntidad(CarProcesoPrestador obj) {
        CarProcesoPrestadores per = new CarProcesoPrestadores();
        per.setId(obj.getId());
        per.setActivo(obj.isActivo());

        //Objetos
        if (obj.getProceso() != null) {
            per.setCarProcesosId(new CarProcesos(obj.getProceso().getId()));
        }

        if (obj.getPrestador().getId() != null) {
            per.setCntPrestadoresId(new CntPrestadores(obj.getPrestador().getId()));
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



}
