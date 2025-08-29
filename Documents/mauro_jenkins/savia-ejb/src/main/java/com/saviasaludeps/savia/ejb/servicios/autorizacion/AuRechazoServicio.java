/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Item;
import com.saviasaludeps.savia.dominio.autorizacion.AuRechazo;
import com.saviasaludeps.savia.dominio.autorizacion.AuRechazoItem;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliados;
import com.saviasaludeps.savia.ejb.entidades.AuAnexos3;
import com.saviasaludeps.savia.ejb.entidades.AuRechazoItems;
import com.saviasaludeps.savia.ejb.entidades.AuRechazos;
import com.saviasaludeps.savia.ejb.entidades.GnEmpresas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuRechazoRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author Stiven Giraldo
 */
@Stateless
@Remote(AuRechazoRemoto.class)
public class AuRechazoServicio extends GenericoServicio implements AuRechazoRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AuRechazo> consultarLista(ParamConsulta paramConsulta) throws java.lang.Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AuRechazo consultar(int id) throws java.lang.Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insertar(AuRechazo obj) throws Exception {
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
    public void actualizar(AuRechazo obj) throws java.lang.Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AuRechazo eliminar(int id) throws java.lang.Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public AuRechazos castNegocioEntidad(AuRechazo negocio) {
        AuRechazos entidad = new AuRechazos();
        if (negocio.getEmpresasId() != null) {
            entidad.setGnEmpresasId(new GnEmpresas(negocio.getEmpresasId().getId()));
        }
        entidad.setAsegAfiliadosId(new AsegAfiliados(negocio.getAsegAfiliadoId().getId()));
        entidad.setAuAnexos3Id(new AuAnexos3(negocio.getAuAnexo3Id().getId()));
        entidad.setMaeCausaRechazoId(negocio.getMaeCausaRechazoId());
        entidad.setMaeCausaRechazoCodigo(negocio.getMaeCausaRechazoCodigo());
        entidad.setMaeCausaRechazoValor(negocio.getMaeCausaRechazoValor());
        entidad.setJustificacion(negocio.getJustificacion());
        entidad.setAlternativa(negocio.getAlternativa());
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        return entidad;
    }

    public AuRechazo castEntidadNegocio(AuRechazos entidad) {
        AuRechazo negocio = new AuRechazo();
        negocio.setId(entidad.getId());
        negocio.setEmpresasId(new Empresa(entidad.getGnEmpresasId().getId()));
        negocio.setAsegAfiliadoId(new AsegAfiliado(entidad.getAsegAfiliadosId().getId()));
        negocio.setAuAnexo3Id(new AuAnexo3(entidad.getAuAnexos3Id().getId()));
        negocio.setMaeCausaRechazoId(entidad.getMaeCausaRechazoId());
        negocio.setMaeCausaRechazoCodigo(entidad.getMaeCausaRechazoCodigo());
        negocio.setMaeCausaRechazoValor(entidad.getMaeCausaRechazoValor());
        negocio.setJustificacion(entidad.getJustificacion());
        negocio.setAlternativa(entidad.getAlternativa());
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        return negocio;
    }

    @Override
    public AuRechazo consultarPorItem(int idItem) throws Exception {
        AuRechazo rechazo = null;
        try {
            String strQuery = "FROM AuRechazoItems p "
                    + "WHERE p.id > 0";
            strQuery += "AND p.auAnexo3ItemsId.id = " + idItem + " ";
            strQuery += "ORDER BY ";
            strQuery += "p.id DESC";
            List<AuRechazoItems> list = getEntityManager().createQuery(strQuery).getResultList();
            if (list.size() > 0) {
                rechazo = castEntidadNegocio(list.get(0).getAuRechazosId());
                if (rechazo.getId() != null) {
                    rechazo.setAuRechazoItemsList(new ArrayList());
                    strQuery = "FROM AuRechazoItems p "
                            + "WHERE p.id > 0";
                    strQuery += "AND p.auRechazosId.id = " + rechazo.getId() + " ";
                    strQuery += "ORDER BY ";
                    strQuery += "p.id DESC";
                    list = getEntityManager().createQuery(strQuery).getResultList();
                    for (AuRechazoItems items : list) {
                        AuRechazoItem item = castearEntidadNegocioItem(items);
                        if (item.getId() != null) {
                            rechazo.getAuRechazoItemsList().add(item);
                        }
                    }
                }

            }
        } catch (NoResultException e) {
            rechazo = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return rechazo;
    }

    private AuRechazoItem castearEntidadNegocioItem(AuRechazoItems entidad) {
        AuRechazoItem negocio = new AuRechazoItem();
        negocio.setId(entidad.getId());
        negocio.setAuRechazoId(new AuRechazo(entidad.getAuRechazosId().getId()));
        negocio.setAuAnexo3ItemId(new AuAnexo3Item(entidad.getAuAnexo3ItemsId().getId()));
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        return negocio;
    }

}
