/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.financiera;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.financiera.FinGiro;
import com.saviasaludeps.savia.dominio.financiera.FinPostulacion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadores;
import com.saviasaludeps.savia.ejb.entidades.FinGiros;
import com.saviasaludeps.savia.ejb.entidades.FinPostulaciones;
import com.saviasaludeps.savia.ejb.entidades.GnEmpresas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.financiera.FinPostulacionRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author sgiraldv
 */
@Stateless
@Remote(FinPostulacionRemoto.class)
public class FinPostulacionServicio extends GenericoServicio implements FinPostulacionRemoto {
    

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(fp) FROM FinPostulaciones fp "
                    + " WHERE 1 = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += " AND fp.id = " + e.getValue() + " ";
                            break;
                        case "finGiro.id":
                            strQuery += " AND fp.finGirosId.id = " + e.getValue() + " ";
                            break;
                        case "finGiro.tipo":
                            strQuery += " AND fp.finGirosId.tipo = " + e.getValue() + " ";
                            break;
                        case "finGiro.nombre":
                            strQuery += " AND fp.finGirosId.nombre = '" + e.getValue() + "' ";
                            break;
                        case "tipoPostulacion":
                            strQuery += " AND fp.tipoPostulacion = " + e.getValue() + " ";
                            break;
                        case "prestadorNit":
                            strQuery += " AND fp.prestadorNit = '" + e.getValue() + "' ";
                            break;
                         case "prestadorRazonSocial":
                            strQuery += " AND fp.prestadorRazonSocial = '" + e.getValue() + "' ";
                            break;
                    }
                }
            }
                  
            
           if (paramConsulta.getParametroConsulta3() != null) {
                strQuery += " AND fp.cntPrestadoresId.id =  "+paramConsulta.getParametroConsulta3();
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
    public List<FinPostulacion> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<FinPostulacion> listaResultados = new ArrayList();
        try {
            String strQuery = "FROM FinPostulaciones fp WHERE 1 = 1 ";

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += " AND fp.id = " + e.getValue() + " ";
                            break;
                        case "finGiro.id":
                            strQuery += " AND fp.finGirosId.id = " + e.getValue() + " ";
                            break;
                        case "finGiro.tipo":
                            strQuery += " AND fp.finGirosId.tipo = " + e.getValue() + " ";
                            break;
                        case "finGiro.nombre":
                            strQuery += " AND fp.finGirosId.nombre = '" + e.getValue() + "' ";
                            break;
                        case "tipoPostulacion":
                            strQuery += " AND fp.tipoPostulacion = " + e.getValue() + " ";
                            break;
                        case "prestadorNit":
                            strQuery += " AND fp.prestadorNit = '" + e.getValue() + "' ";
                            break;
                        case "prestadorRazonSocial":
                            strQuery += " AND fp.prestadorRazonSocial = '" + e.getValue() + "' ";
                            break;
                    }
                }
            }
            
            if (paramConsulta.getParametroConsulta3() != null) {
                strQuery += " AND fp.cntPrestadoresId.id =  "+paramConsulta.getParametroConsulta3();
            }
            
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "fp." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "fp.id DESC";
            }
            List<FinPostulaciones> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (FinPostulaciones postulacion : list) {
                listaResultados.add(castEntidadNegocio(postulacion));
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
    public FinPostulacion consultar(int id) throws Exception {
        FinPostulacion objRes = null;
        try {
            FinPostulaciones per = getEntityManager().find(FinPostulaciones.class, id);
            if (per != null) {
                objRes = castEntidadNegocio(per);
            } 
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
    public int insertar(FinPostulacion obj) throws Exception {
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
    public void actualizar(FinPostulacion obj) throws Exception {
        try {
          
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public FinPostulacion eliminar(int id) throws Exception {
        FinPostulacion obj = null;
        try {
            FinPostulaciones ent = getEntityManager().find(FinPostulaciones.class, id);
            if (ent != null) {
                obj = castEntidadNegocio(ent);
                getEntityManager().remove(ent);
            }
        } catch (NoResultException e) {
            Exception(ELIMINAR, e);
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
        return obj;
    }

    private FinPostulacion castEntidadNegocio(FinPostulaciones ent) {
        FinPostulacion neg = new FinPostulacion();
        neg.setId(ent.getId());
        
        if(ent.getFinGirosId() != null){
            neg.setFinGiro(new FinGiro( ent.getFinGirosId().getId(),
                       ent.getFinGirosId().getTipo(),
                ent.getFinGirosId().getNombre() ));
        }
        
        neg.setEmpresa(new Empresa(ent.getGnEmpresasId().getId()));
        neg.setCntPrestador(new CntPrestador(ent.getCntPrestadoresId().getId()));
        neg.setTipoPostulacion(ent.getTipoPostulacion());
        neg.setPrestadorNit(ent.getPrestadorNit());
        neg.setPrestadorRazonSocial(ent.getPrestadorRazonSocial());
        neg.setPrestadorDepartamento(ent.getPrestadorDepartamento());
        neg.setPrestadorMunicipio(ent.getPrestadorMunicipio());
        neg.setPrestadorNaturaleza(ent.getPrestadorNaturaleza());
        neg.setPrestadorEstadoAdres(ent.getPrestadorEstadoAdres());
        neg.setValorCapita(ent.getValorCapita());
        neg.setValorCapitaReajuste(ent.getValorCapitaReajuste());
        neg.setValorPgp(ent.getValorPgp());
        neg.setValorCompromisos(ent.getValorCompromisos());
        neg.setValorEvento(ent.getValorEvento());
        neg.setValorProgramadoTotal(ent.getValorProgramadoTotal());
        neg.setTotalPagado(ent.getTotalPagado());
        neg.setBorrado(ent.getBorrado());

       
        neg.setFechaHoraCrea(ent.getFechaHoraCrea());
        neg.setUsuarioCrea(ent.getUsuarioCrea());
        neg.setTerminalCrea(ent.getTerminalCrea());   
        neg.setFechaHoraModifica(ent.getFechaHoraModifica());
        neg.setUsuarioModifica(ent.getUsuarioModifica());
        neg.setTerminalModifica(ent.getTerminalModifica());
        neg.setFechaHoraBorra(ent.getFechaHoraBorra());
        neg.setUsuarioBorra(ent.getUsuarioBorra());
        neg.setTerminalBorra(ent.getTerminalBorra());
        return neg;
    }

    private FinPostulaciones castNegocioEntidad(FinPostulacion neg) {
        FinPostulaciones ent = new FinPostulaciones();
        ent.setId(neg.getId());
        
        ent.setFinGirosId( new FinGiros(neg.getFinGiro().getId()));
        ent.setGnEmpresasId(new GnEmpresas(neg.getEmpresa().getId()));
        ent.setCntPrestadoresId(new CntPrestadores(neg.getCntPrestador().getId()));
        ent.setTipoPostulacion(neg.getTipoPostulacion());
        ent.setPrestadorNit(neg.getPrestadorNit());
        ent.setPrestadorRazonSocial(neg.getPrestadorRazonSocial());
        ent.setPrestadorDepartamento(neg.getPrestadorDepartamento());
        ent.setPrestadorMunicipio(neg.getPrestadorMunicipio());
        ent.setPrestadorNaturaleza(neg.getPrestadorNaturaleza());
        ent.setPrestadorEstadoAdres(neg.getPrestadorEstadoAdres());
        ent.setValorCapita(neg.getValorCapita());
        ent.setValorCapitaReajuste(neg.getValorCapitaReajuste());
        ent.setValorPgp(neg.getValorPgp());
        ent.setValorCompromisos(neg.getValorCompromisos());
        ent.setValorEvento(neg.getValorEvento());
        ent.setValorProgramadoTotal(neg.getValorProgramadoTotal());
        ent.setTotalPagado(neg.getTotalPagado());
        ent.setBorrado(neg.getBorrado());
        
        //Auditoria
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        ent.setTerminalCrea(neg.getTerminalCrea());   
        ent.setFechaHoraModifica(neg.getFechaHoraModifica());
        ent.setUsuarioModifica(neg.getUsuarioModifica());
        ent.setTerminalModifica(neg.getTerminalModifica());
        ent.setFechaHoraBorra(neg.getFechaHoraBorra());
        ent.setUsuarioBorra(neg.getUsuarioBorra());
        ent.setTerminalBorra(neg.getTerminalBorra());
        return ent;
    }



}
