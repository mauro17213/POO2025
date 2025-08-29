/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.servicios.especial;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadosPrograma;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.especial.PeProgramaTraslado;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliados;
import com.saviasaludeps.savia.ejb.entidades.PeAfiliadosProgramas;
import com.saviasaludeps.savia.ejb.entidades.PeProgramas;
import com.saviasaludeps.savia.ejb.entidades.PeProgramasTraslados;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.especial.PeProgramaTrasladoRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author jdlopez
 */
@Stateless
@Remote(PeProgramaTrasladoRemoto.class)
@Local(PeProgramaTrasladoLocal.class)
public class PeProgramaTrasladoServicio extends GenericoServicio implements PeProgramaTrasladoRemoto, PeProgramaTrasladoLocal {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(pt) FROM PeProgramasTraslados pt WHERE pt.id > 0 ";

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND pt.id = " + e.getValue() + " ";
                            break;
                        case "afiliadoAseg":
                            strQuery += "AND pt.asegAfiliadosId.id = " + e.getValue() + " ";
                            break;
                        case "afiliadoPrograma":
                            strQuery += "AND pt.peAfiliadosProgramasId.id = " + e.getValue() + " ";
                            break;
                        case "programaOrigen":
                            strQuery += "AND pt.peProgramaIdOrigen.id = " + e.getValue() + " ";
                            break;
                        case "programaDestino":
                            strQuery += "AND pt.peProgramaIdDestino.id = " + e.getValue() + " ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND pt.usuarioCrea like '%" + e.getValue().toString() + "%' ";
                            break;
                        case "fechaHoraCrea":
                            strQuery += "AND pt.fechaHoraCrea =  '" + e.getValue().toString() + "' ";
                            break;
                    }
                }
            }
            Query query = getEntityManager().createQuery(strQuery);
            cant = (int) (long) query.getSingleResult();
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
    public List<PeProgramaTraslado> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<PeProgramaTraslado> listResult = new ArrayList();
        try {
            String strQuery = "SELECT pt FROM PeProgramasTraslados pt WHERE pt.id > 0 ";

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND pt.id = " + e.getValue() + " ";
                            break;
                        case "afiliadoAseg":
                            strQuery += "AND pt.asegAfiliadosId.id = " + e.getValue() + " ";
                            break;
                        case "afiliadoPrograma":
                            strQuery += "AND pt.peAfiliadosProgramasId.id = " + e.getValue() + " ";
                            break;
                        case "programaOrigen":
                            strQuery += "AND pt.peProgramaIdOrigen.id = " + e.getValue() + " ";
                            break;
                        case "programaDestino":
                            strQuery += "AND pt.peProgramaIdDestino.id = " + e.getValue() + " ";
                            break;
                        case "usuarioCrea":
                            strQuery += "AND pt.usuarioCrea like '%" + e.getValue().toString() + "%' ";
                            break;
                        case "fechaHoraCrea":
                            strQuery += "AND pt.fechaHoraCrea =  '" + e.getValue().toString() + "' ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                switch (paramConsulta.getOrden()) {
                    default:
                        strQuery += "pt." + paramConsulta.getOrden() + " "
                                + (paramConsulta.isAscendente() ? "ASC" : "DESC");
                        if (!paramConsulta.isAscendente()) {
                            strQuery += ", pt.id DESC";
                        }
                        break;
                }
            } else {
                strQuery += "pt.id DESC";
            }
            Query query = getEntityManager().createQuery(strQuery);
            query.setFirstResult(paramConsulta.getPrimerRegistro());
            query.setMaxResults(paramConsulta.getRegistrosPagina());
            List<PeProgramasTraslados> list = query.getResultList();

            for (PeProgramasTraslados ent : list) {
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
    public int insertar(PeProgramaTraslado obj) throws Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(this.castNegocioEntidad(obj)).getId();
            obj.setId(id);
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return id;
    }

    private PeProgramasTraslados castNegocioEntidad(PeProgramaTraslado obj) {
        PeProgramasTraslados ent = new PeProgramasTraslados();
        ent.setId(obj.getId());
        ent.setAsegAfiliadosId(new AsegAfiliados(obj.getAfiliadoAseg().getId()));
        ent.setPeAfiliadosProgramasId(new PeAfiliadosProgramas(obj.getAfiliadoPrograma().getId()));
        ent.setPeProgramaIdOrigen(new PeProgramas(obj.getProgramaOrigen().getId()));
        ent.setPeProgramaIdDestino(new PeProgramas(obj.getProgramaDestino().getId()));
        ent.setObservacion(obj.getObservacion());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    public PeProgramaTraslado castEntidadNegocio(PeProgramasTraslados ent) {
        PeProgramaTraslado obj = new PeProgramaTraslado();
        obj.setId(ent.getId());
        //aseg afiliado
        obj.setAfiliadoAseg(new AsegAfiliado(ent.getAsegAfiliadosId().getId()));
        //Pe afiliado programa
        PeAfiliadosPrograma peAfiliadosPrograma = new PeAfiliadosPrograma();
        peAfiliadosPrograma.setId(ent.getPeAfiliadosProgramasId().getId());
        obj.setAfiliadoPrograma(peAfiliadosPrograma);
        //programa origen
        PePrograma peProgramaOrigen = new PePrograma(ent.getPeProgramaIdOrigen().getId());
        peProgramaOrigen.setDescripcionPrograma(ent.getPeProgramaIdOrigen().getDescripcionPrograma());
        obj.setProgramaOrigen(peProgramaOrigen);
        //programa destino
        PePrograma peProgramaDestino = new PePrograma(ent.getPeProgramaIdDestino().getId());
        peProgramaDestino.setDescripcionPrograma(ent.getPeProgramaIdDestino().getDescripcionPrograma());
        obj.setProgramaDestino(peProgramaDestino);
        
        obj.setObservacion(ent.getObservacion());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        return obj;
    }
}
