/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Item;
import com.saviasaludeps.savia.dominio.autorizacion.AuItemBitacora;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo3Items;
import com.saviasaludeps.savia.ejb.entidades.AuItemBitacoras;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuItemBitacoraRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author Stiven
 */
@Stateless
@Remote(AuItemBitacoraRemoto.class)
public class AuItemBitacoraServicio extends GenericoServicio implements AuItemBitacoraRemoto {

    @Override
    public int insertar(AuItemBitacora objeto) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidad(objeto)).getId();
            objeto.setId(_id);
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
    public List<AuItemBitacora> listarPorIdItem(int idItem) throws Exception {
        List<AuItemBitacora> bitacoras = new ArrayList();
        try {
            String strQuery = "FROM AuItemBitacoras p "
                    + "WHERE p.auAnexo3ItemsId.id =" + idItem
                    + " ORDER BY p.id DESC";

            List<AuItemBitacoras> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AuItemBitacoras bitacora : list) {
                bitacoras.add(castEntidadNegocio(bitacora));
            }
        } catch (NoResultException e) {
            bitacoras = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return bitacoras;
    }

    @Override
    public AuItemBitacora penultimaBitacora(int idItem) throws Exception {
        AuItemBitacora bitacora = null;
        try {
            String strQuery = "FROM AuItemBitacoras p "
                    + "WHERE p.auAnexo3ItemsId.id =" + idItem
                    + " ORDER BY p.id DESC";

            List<AuItemBitacoras> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            int cont = 0;
            for (AuItemBitacoras bt : list) {
                if (cont == 1) {
                    bitacora = castEntidadNegocio(bt);
                    break;
                }
                cont++;
            }
        } catch (NoResultException e) {
            bitacora = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return bitacora;
    }

    private static AuItemBitacoras castNegocioEntidad(AuItemBitacora negocio) {
        AuItemBitacoras entidad = new AuItemBitacoras();
        entidad.setAuAnexo3ItemsId(new AuAnexo3Items(negocio.getAuAnexo3ItemId().getId()));
        entidad.setDescripcion(negocio.getDescripcion());
        entidad.setTipo(negocio.getTipo());
        entidad.setEstado(negocio.getEstado());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        return entidad;
    }

    private static AuItemBitacora castEntidadNegocio(AuItemBitacoras entidad) {
        AuItemBitacora negocio = new AuItemBitacora();
        negocio.setId(entidad.getId());
        negocio.setAuAnexo3ItemId(new AuAnexo3Item(entidad.getAuAnexo3ItemsId().getId()));
        negocio.setDescripcion(entidad.getDescripcion());
        negocio.setTipo(entidad.getTipo());
        negocio.setEstado(entidad.getEstado());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        return negocio;
    }

    public static List<AuItemBitacora> castListaEntidadNegocio(List<AuItemBitacoras> listaEntidad) {
        List<AuItemBitacora> listaNegocio = new ArrayList();
        listaEntidad.forEach(entidad -> {
            listaNegocio.add(castEntidadNegocio(entidad));
        });
        return listaNegocio;
    }
}
