package com.saviasaludeps.savia.ejb.servicios.atencionusuario;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoContactoConsulta;
import com.saviasaludeps.savia.dominio.atencionusuario.AusPersona;
import com.saviasaludeps.savia.dominio.atencionusuario.AusPersonaTelefono;
//import com.saviasaludeps.savia.ejb.entidades.AusPersonaTelefono;

import com.saviasaludeps.savia.ejb.entidades.AusPersonaTelefonos;
//import com.saviasaludeps.savia.ejb.entidades.AusPersonaTelefono;
import com.saviasaludeps.savia.ejb.entidades.AusPersonas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.negocio.atencionusuario.AusPersonaTelefonoRemoto;
import javax.ejb.Local;

@Stateless
@Remote(AusPersonaTelefonoRemoto.class)
@Local(AusPersonaTelefonoLocal.class)

public class AusPersonaTelefonoServicio extends GenericoServicio implements AusPersonaTelefonoRemoto, AusPersonaTelefonoLocal {

    @Override
    public AusPersonaTelefono consultar(int id) throws Exception {
        AusPersonaTelefono objRes = null;
        try {
            AusPersonaTelefonos personaNegocio = (AusPersonaTelefonos) getEntityManager().find(AusPersonaTelefonos.class, id);
            objRes = castEntidadNegocio(personaNegocio);
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
    public AusPersonaTelefono eliminar(int id) throws Exception {
        AusPersonaTelefono obj = null;
        try {
            AusPersonaTelefonos ent = getEntityManager().find(AusPersonaTelefonos.class, id);
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
    public List<AsegAfiliadoContactoConsulta> consultarListaContactosPorAsegAfiliado(Integer idAsegAfilido, String numeroExcluidos) throws Exception {
        List<AsegAfiliadoContactoConsulta> listResult = new ArrayList();
        try {
            if (idAsegAfilido != null) {
//                String strQuery = "SELECT pt FROM AusPersonaTelefonos pt "
//                        + "WHERE pt.ausPersonasId.id IN (SELECT p.id FROM AusPersonas p WHERE p.asegAfiliadosId.id = :idAsegAfiliado ) "
//                        + "AND pt.numero NOT IN (" + numeroExcluidos +") "
//                        + "ORDER BY pt.id ASC";
                String strQuery = "SELECT pt FROM AusPersonaTelefonos pt "
                        + "WHERE pt.ausPersonasId.id IN (SELECT p.id FROM AusPersonas p WHERE p.asegAfiliadosId.id = :idAsegAfiliado ) ";
                if (numeroExcluidos != null && !numeroExcluidos.isEmpty()) {
                    strQuery += "AND pt.numero NOT IN (" + numeroExcluidos + ") ";
                }
                strQuery += "ORDER BY pt.id ASC";
                List<AusPersonaTelefonos> list = getEntityManager().createQuery(strQuery)
                        .setParameter("idAsegAfiliado", idAsegAfilido)
                        .getResultList();
                int i = 0;
                for (AusPersonaTelefonos per : list) {
                    listResult.add(castEntidadNegocioAfiliado(per));
                    i++;
                }
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

    public static AusPersonaTelefonos castNegocioEntidad(AusPersonaTelefono obj) {
        AusPersonaTelefonos perTelefonos = new AusPersonaTelefonos();
        perTelefonos.setAusPersonasId(new AusPersonas(obj.getAusPersona().getId()));
        if (obj.getId() != null) {
            perTelefonos.setId(obj.getId());
        }

        perTelefonos.setNumero(obj.getNumero().trim());
        String observacion = obj.getObservacion() == null || obj.getObservacion().isEmpty() ? " " : obj.getObservacion();
        perTelefonos.setObservacion(observacion);
        perTelefonos.setUsuarioCrea(obj.getUsuarioCrea());
        perTelefonos.setFechaHoraCrea(obj.getFechaHoraCrea());
        perTelefonos.setTerminalCrea(obj.getTerminalCrea());
        return perTelefonos;
    }

    public static AusPersonaTelefono castEntidadNegocio(AusPersonaTelefonos obj) {
        AusPersonaTelefono perTelefono = new AusPersonaTelefono();
        perTelefono.setId(obj.getId());
        if (obj.getAusPersonasId() != null) {
            perTelefono.setAusPersona(new AusPersona(obj.getId()));
        }
        perTelefono.setNumero(String.valueOf(obj.getNumero()));
        perTelefono.setObservacion(obj.getObservacion());
        perTelefono.setUsuarioCrea(obj.getUsuarioCrea());
        perTelefono.setFechaHoraCrea(obj.getFechaHoraCrea());
        perTelefono.setTerminalCrea(obj.getTerminalCrea());
        return perTelefono;
    }

    public static AusPersonaTelefono castEntidadNegocio(AusPersonaTelefonos obj, int pos) {
        AusPersonaTelefono perTelefono = new AusPersonaTelefono();
        perTelefono.setId(obj.getId());
        if (obj.getAusPersonasId() != null) {
            perTelefono.setAusPersona(new AusPersona(obj.getId()));
        }
        perTelefono.setPos(pos);
        perTelefono.setNumero(String.valueOf(obj.getNumero()));
        perTelefono.setObservacion(obj.getObservacion());
        perTelefono.setUsuarioCrea(obj.getUsuarioCrea());
        perTelefono.setFechaHoraCrea(obj.getFechaHoraCrea());
        perTelefono.setTerminalCrea(obj.getTerminalCrea());
        return perTelefono;
    }

    private AsegAfiliadoContactoConsulta castEntidadNegocioAfiliado(AusPersonaTelefonos ent) {
        AsegAfiliadoContactoConsulta neg = new AsegAfiliadoContactoConsulta();
        neg.setId(ent.getId());
        neg.setNumeroContacto(ent.getNumero());
        neg.setObservacion(ent.getObservacion());
        neg.setMaeTipoContactoValor(AsegAfiliadoContactoConsulta.TIPO_CONTACTO_NULL);
        neg.setOrigen(AsegAfiliadoContactoConsulta.ORIGEN_ATENCION_USUARIO);
        neg.setUsuarioCrea(ent.getUsuarioCrea());
        neg.setTerminalCrea(ent.getTerminalCrea());
        neg.setFechaHoraCrea(ent.getFechaHoraCrea());
        return neg;
    }

}
