/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Item;
import com.saviasaludeps.savia.dominio.autorizacion.AuCotizacion;
import com.saviasaludeps.savia.dominio.autorizacion.AuCotizacionItem;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.ejb.entidades.AuAnexo3Items;
import com.saviasaludeps.savia.ejb.entidades.AuCotizacionItems;
import com.saviasaludeps.savia.ejb.entidades.AuCotizaciones;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.autorizacion.AuCotizacionItemRemoto;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Stiven Giraldo
 */
@Stateless
@Remote(AuCotizacionItemRemoto.class)
public class AuCotizacionItemServicio extends GenericoServicio implements AuCotizacionItemRemoto {

    @Override
    public int insertar(AuCotizacionItem obj) throws Exception {
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
    public int insertarItem(AuCotizacionItem item) throws Exception {
        int id = 0;

        try {
            AuCotizacionItems entidad = castNegocioEntidad(item);

            // Verifica el valor de ID antes de persistir o merge
            System.out.println("ID antes de persist/merge: " + entidad.getId());

            if (entidad.getId() == null) {
                getEntityManager().persist(entidad);
                getEntityManager().flush();
                id = entidad.getId();  // El ID ahora debería ser asignado por la base de datos
            } else {
                entidad = getEntityManager().merge(entidad);
                id = entidad.getId();
            }

            System.out.println("ID después de persist/merge: " + id);

        } catch (Exception e) {
            // Manejo de la excepción
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }

        return id;
    }

    @Override
    public AuCotizacionItem consultarPorIdAnexo3(int idAnexo3Item) throws Exception {
        AuCotizacionItem objRes = null;
        try {
            String strQuery = "SELECT c FROM AuCotizacionItems c "
                    + "WHERE c.auAnexo3ItemsId.id = :auAnexo3ItemsId "
                    + "ORDER by c.id DESC";
            Query query = getEntityManager().createQuery(strQuery);
            query.setMaxResults(1);
            query.setParameter("auAnexo3ItemsId", idAnexo3Item);
            objRes = castEntidadNegocio((AuCotizacionItems) query.getSingleResult());
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }

    private AuCotizacionItem castEntidadNegocio(AuCotizacionItems entidad) {
        AuCotizacionItem negocio = new AuCotizacionItem();
        negocio.setId(entidad.getId());
        negocio.setAuCotizacionId(castEntidadNegocioCotizacion(entidad.getAuCotizacionesId()));
        negocio.setAuAnexo3ItemId(new AuAnexo3Item(entidad.getAuAnexo3ItemsId().getId()));
        return negocio;
    }

    private AuCotizacion castEntidadNegocioCotizacion(AuCotizaciones entidad) {
        AuCotizacion negocio = new AuCotizacion();
        negocio.setId(entidad.getId());
        negocio.setActivo(entidad.getActivo());
        negocio.setFechaFinVigencia(entidad.getFechaFinVigencia());
        negocio.setMaTarifarioId(entidad.getMaTarifarioId());
        negocio.setMaTarifarioCodigo(entidad.getMaTarifarioCodigo());
        negocio.setMaTarifarioValor(entidad.getMaTarifarioValor());
        negocio.setValorTecnologia(entidad.getValorTecnologia());
        negocio.setCntPrestadorSede(new CntPrestadorSede(entidad.getCntPrestadorSedesId().getId()));
        negocio.setMaMedicamentoId(entidad.getMaMedicamentoId());
        negocio.setMaMedicamentoCodigo(entidad.getMaMedicamentoCodigo());
        negocio.setMaMedicamentoValor(entidad.getMaMedicamentoValor());
        return negocio;
    }

    private AuCotizacionItems castNegocioEntidad(AuCotizacionItem negocio) {
        AuCotizacionItems entidad = new AuCotizacionItems();
        entidad.setAuCotizacionesId(new AuCotizaciones(negocio.getAuCotizacionId().getId()));
        if (negocio.getAuAnexo3ItemId() != null) {
            entidad.setAuAnexo3ItemsId(new AuAnexo3Items(negocio.getAuAnexo3ItemId().getId()));
        }
        entidad.setMpPrescripcionItems(negocio.getMpPrescripcionItem());
        entidad.setTipoTecnologiaMipres(negocio.getTipoTecnologiaMipres());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        return entidad;
    }

}
