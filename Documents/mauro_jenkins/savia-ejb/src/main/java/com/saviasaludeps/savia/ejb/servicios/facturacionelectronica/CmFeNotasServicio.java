package com.saviasaludeps.savia.ejb.servicios.facturacionelectronica;

import com.saviasaludeps.savia.dominio.facturacionelectronica.CmFeNota;
import com.saviasaludeps.savia.ejb.entidades.CmFacturas;
import com.saviasaludeps.savia.ejb.entidades.CmFeNotas;
import com.saviasaludeps.savia.ejb.entidades.CmFeRipsFacturas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.facturacionelectronica.CmFeNotasRemoto;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

/**
 *
 * @author LFRIVERA
 */
@Stateless
@Remote(CmFeNotasRemoto.class)
public class CmFeNotasServicio extends GenericoServicio implements CmFeNotasRemoto {

    @Transactional
    @Override
    public int insertar(CmFeNota nota) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidad(nota)).getId();
            nota.setId(_id);
        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }
    
    @Transactional
    @Override
    public void actualizar(CmFeNota nota) throws Exception {
        try {
            CmFeNotas entidadExistente = getEntityManager().find(CmFeNotas.class, nota.getId());

            if (entidadExistente != null) {
                // Actualizar los valores de la entidad existente con los datos del DTO
                entidadExistente.setTipo(nota.getTipo());
                entidadExistente.setNumeroNota(nota.getNumeroNota());
                entidadExistente.setValorNota(nota.getValorNota());
                entidadExistente.setCude(nota.getCude());
                entidadExistente.setFechaHoraEmision(nota.getFechaHoraEmision());
                entidadExistente.setUsuarioCrea(nota.getUsuarioCrea());
                entidadExistente.setTerminalCrea(nota.getTerminalCrea());
                entidadExistente.setFechaHoraCrea(nota.getFechaHoraCrea());

                if (nota.getCmFacturasId() != null) {
                    CmFacturas factura = new CmFacturas();
                    factura.setId(nota.getCmFacturasId());
                    entidadExistente.setCmFacturasId(factura);
                } else {
                    entidadExistente.setCmFacturasId(null);
                }

                if (nota.getCmFeRipsFacturasId() != null) {
                    CmFeRipsFacturas ripsFactura = new CmFeRipsFacturas();
                    ripsFactura.setId(nota.getCmFeRipsFacturasId());
                    entidadExistente.setCmFeRipsFacturasId(ripsFactura);
                } else {
                    entidadExistente.setCmFeRipsFacturasId(null);
                }

                // Actualizar la entidad en la base de datos
                getEntityManager().merge(entidadExistente);
            }
        } catch (NoResultException e) {
            Exception(ACTUALIZAR, e);
        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Transactional
    @Override
    public CmFeNota eliminar(int id) throws Exception {
        CmFeNota nota = null;
        try {
            CmFeNotas ent = getEntityManager().find(CmFeNotas.class, id);
            if (ent != null) {
                nota = castEntidadNegocio(ent);
                getEntityManager().remove(ent);
            }
        } catch (NoResultException e) {
            Exception(ELIMINAR, e);
        } catch (Exception e) {
            Exception(ELIMINAR, e);
        } finally {
            cerrarEntityManager();
        }
        return nota;
    }
    @Override
    public CmFeNota consultarPorCmFacturasId(int cmFacturasId) throws Exception {
        CmFeNota nota = null;
        try {
            CmFeNotas entidad = getEntityManager()
                    .createQuery("SELECT c FROM CmFeNotas c WHERE c.cmFacturasId.id = :cmFacturasId", CmFeNotas.class)
                    .setParameter("cmFacturasId", cmFacturasId)
                    .getSingleResult();

            if (entidad != null) {
                nota = castEntidadNegocio(entidad);
            }
        } catch (NoResultException e) {
            nota = new CmFeNota();
        } catch (Exception e) {
            Exception("Error al buscar por CmFacturasId", e);
        } finally {
            cerrarEntityManager();
        }
        return nota;
    }
     @Override
    public CmFeNota consultarPorCmFeCargaId(int cmFeCargaId) throws Exception {
        CmFeNota nota = new CmFeNota();
        try {
            CmFeNotas entidad = getEntityManager()
                    .createQuery("SELECT c FROM CmFeNotas c WHERE c.cmFeRipsFacturasId.cmFeRipsCargasId.id = :cmFeCargaId", CmFeNotas.class)
                    .setParameter("cmFeCargaId", cmFeCargaId)
                    .getSingleResult();

            if (entidad != null) {
                nota = castEntidadNegocio(entidad);
            }
        } catch (NoResultException e) {
            Exception("No se encontró ninguna nota para cmFeCargaId: " + cmFeCargaId, e);
        } catch (Exception e) {
            Exception("Error al buscar por consultarPorCmFeCargaId", e);
        } finally {
            cerrarEntityManager();
        }
        return nota;
    }
   @Override
    public CmFeNota consultarPorCmFeRipsFacturasId(int cmFeRipsFacturasId) throws Exception {
        CmFeNota nota = null;
        try {
            CmFeNotas entidad = getEntityManager()
                    .createQuery("SELECT c FROM CmFeNotas c WHERE c.cmFeRipsFacturasId.id = :cmFeRipsFacturasId", CmFeNotas.class)
                    .setParameter("cmFeRipsFacturasId", cmFeRipsFacturasId)
                    .getSingleResult();

            if (entidad != null) {
                nota = castEntidadNegocio(entidad);
            }
        } catch (NoResultException e) {
            Exception("No se encontró ninguna nota para CmFeRipsFacturasId: " + cmFeRipsFacturasId, e);
        } catch (Exception e) {
            Exception("Error al buscar por CmFeRipsFacturasId", e);
        } finally {
            cerrarEntityManager();
        }
        return nota;
    }
    
    public static CmFeNota castEntidadNegocio(CmFeNotas ent) {
        CmFeNota obj = new CmFeNota();
        obj.setId(ent.getId());
        obj.setTipo(ent.getTipo());
        obj.setNumeroNota(ent.getNumeroNota());
        obj.setValorNota(ent.getValorNota());
        obj.setCude(ent.getCude());
        obj.setFechaHoraEmision(ent.getFechaHoraEmision());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());

        if (ent.getCmFacturasId() != null) {
            obj.setCmFacturasId(ent.getCmFacturasId().getId());
        }
        if (ent.getCmFeRipsFacturasId() != null) {
            obj.setCmFeRipsFacturasId(ent.getCmFeRipsFacturasId().getId());
        }

        return obj;
    }

    public CmFeNotas castNegocioEntidad(CmFeNota neg) {
        CmFeNotas ent = new CmFeNotas();
        ent.setId(neg.getId());
        ent.setTipo(neg.getTipo());
        ent.setNumeroNota(neg.getNumeroNota());
        ent.setValorNota(neg.getValorNota());
        ent.setCude(neg.getCude());
        ent.setFechaHoraEmision(neg.getFechaHoraEmision());
        ent.setUsuarioCrea(neg.getUsuarioCrea());
        ent.setTerminalCrea(neg.getTerminalCrea());
        ent.setFechaHoraCrea(neg.getFechaHoraCrea());

        if (neg.getCmFacturasId() != null) {
            CmFacturas factura = new CmFacturas();
            factura.setId(neg.getCmFacturasId());
            ent.setCmFacturasId(factura);
        }
        if (neg.getCmFeRipsFacturasId() != null) {
            CmFeRipsFacturas ripsFactura = new CmFeRipsFacturas();
            ripsFactura.setId(neg.getCmFeRipsFacturasId());
            ent.setCmFeRipsFacturasId(ripsFactura);
        }

        return ent;
    }
}
