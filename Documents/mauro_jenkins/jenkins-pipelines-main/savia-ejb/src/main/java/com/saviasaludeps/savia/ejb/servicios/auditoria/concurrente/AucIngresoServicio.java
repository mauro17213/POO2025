/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucIngreso;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.ejb.entidades.AucIngresos;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucIngresoRemoto;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author sgiraldov
 */
@Stateless
@Remote(AucIngresoRemoto.class)
public class AucIngresoServicio extends GenericoServicio implements AucIngresoRemoto {

    @Override
    public AucIngreso consultar(int id) throws Exception {
        AucIngreso objRes = null;
        try {
            objRes = castEntidadNegocio((AucIngresos) getEntityManager().find(AucIngresos.class, id));
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
    public int insertar(AucIngreso obj) throws Exception {
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
    public void actualizar(AucIngreso obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE AucIngresos a SET ";
            strQuery += "a.fechaIngreso = :fechaIngreso ,";
            strQuery += (obj.getIngreso() != null) ? "a.ingreso = :ingreso ," :"";
            strQuery += "a.maeTipoIngresoId = :maeTipoIngresoId ,";
            strQuery += "a.maeTipoIngresoCodigo = :maeTipoIngresoCodigo ,";
            strQuery += "a.maeTipoIngresoValor = :maeTipoIngresoValor ,";
            strQuery += "a.maeCntModalidadId = :maeCntModalidadId ,";
            strQuery += "a.maeCntModalidadCodigo = :maeCntModalidadCodigo ,";
            strQuery += "a.maeCntModalidadValor = :maeCntModalidadValor ,";
            strQuery += "a.maeRemisionNoPertinenteId = :maeRemisionNoPertinenteId ,";
            strQuery += "a.maeRemisionNoPertinenteCodigo = :maeRemisionNoPertinenteCodigo ,";
            strQuery += "a.maeRemisionNoPertinenteValor = :maeRemisionNoPertinenteValor ,";
            strQuery += "a.descripcionRemisionNoPertinente = :descripcion ,";
            strQuery += "a.indiceCharlson = :indiceCharlson ,";
            strQuery += "a.maeCausaIngresoPrevalenteId = :maeCausaIngresoPrevalenteId ,";
            strQuery += "a.maeCausaIngresoPrevalenteCodigo = :maeCausaIngresoPrevalenteCodigo ,";
            strQuery += "a.maeCausaIngresoPrevalenteValor = :maeCausaIngresoPrevalenteValor ,";
            strQuery += "a.maeAreaIngresoPrevenibleId = :maeAreaIngresoPrevenibleId ,";
            strQuery += "a.maeAreaIngresoPrevenibleCodigo = :maeAreaIngresoPrevenibleCodigo ,";
            strQuery += "a.maeAreaIngresoPrevenibleValor = :maeAreaIngresoPrevenibleValor ,";
            strQuery += "a.descripcionIngresoPrevenible = :descripcionIngresoPrevenible ,";
            strQuery += "a.maeReingresoMotivoId = :maeReingresoMotivoId ,";
            strQuery += "a.maeReingresoMotivoCodigo = :maeReingresoMotivoCodigo ,";
            strQuery += "a.maeReingresoMotivoValor = :maeReingresoMotivoValor ,";
            strQuery += "a.maeReingresoMotivoTipo = :maeReingresoMotivoTipo ,";
            strQuery += "a.altaTemprana = :altaTemprana ,";
            strQuery += "a.maeAltaTempranaId = :maeAltaTempranaId ,";
            strQuery += "a.maeAltaTempranaCodigo = :maeAltaTempranaCodigo ,";
            strQuery += "a.maeAltaTempranaValor = :maeAltaTempranaValor ,";
            strQuery += "a.maeAltaTempranaTipo = :maeAltaTempranaTipo ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("fechaIngreso", obj.getFechaIngreso());
            if(obj.getIngreso() != null){
                query.setParameter("ingreso", obj.getIngreso());
            }
            query.setParameter("maeTipoIngresoId", obj.getMaeTipoIngresoId());
            query.setParameter("maeTipoIngresoCodigo", obj.getMaeTipoIngresoCodigo());
            query.setParameter("maeTipoIngresoValor", obj.getMaeTipoIngresoValor());
            query.setParameter("maeCntModalidadId", obj.getMaeCntModalidadId());
            query.setParameter("maeCntModalidadCodigo", obj.getMaeCntModalidadCodigo());
            query.setParameter("maeCntModalidadValor", obj.getMaeCntModalidadValor());
            query.setParameter("maeRemisionNoPertinenteId", obj.getMaeRemisionNoPertinenteId());
            query.setParameter("maeRemisionNoPertinenteCodigo", obj.getMaeRemisionNoPertinenteCodigo());
            query.setParameter("maeRemisionNoPertinenteValor", obj.getMaeRemisionNoPertinenteValor());
            query.setParameter("descripcion", obj.getDescripcion());
            query.setParameter("indiceCharlson", obj.getIndiceCharlson());
            query.setParameter("maeCausaIngresoPrevalenteId", obj.getMaeCausaIngresoPrevalenteId());
            query.setParameter("maeCausaIngresoPrevalenteCodigo", obj.getMaeCausaIngresoPrevalenteCodigo());
            query.setParameter("maeCausaIngresoPrevalenteValor", obj.getMaeCausaIngresoPrevalenteValor());
            query.setParameter("maeAreaIngresoPrevenibleId", obj.getMaeAreaIngresoPrevenibleId());
            query.setParameter("maeAreaIngresoPrevenibleCodigo", obj.getMaeAreaIngresoPrevenibleCodigo());
            query.setParameter("maeAreaIngresoPrevenibleValor", obj.getMaeAreaIngresoPrevenibleValor());
            query.setParameter("descripcionIngresoPrevenible", obj.getDescripcionIngresoPrevenible());
            query.setParameter("maeReingresoMotivoId", obj.getMaeReingresoMotivoId());
            query.setParameter("maeReingresoMotivoCodigo", obj.getMaeReingresoMotivoCodigo());
            query.setParameter("maeReingresoMotivoValor", obj.getMaeReingresoMotivoValor());
            query.setParameter("maeReingresoMotivoTipo", obj.getMaeReingresoMotivoTipo());
            query.setParameter("altaTemprana", obj.getAltaTemprana());
            query.setParameter("maeAltaTempranaId", obj.getMaeAltaTempranaId());
            query.setParameter("maeAltaTempranaCodigo", obj.getMaeAltaTempranaCodigo());
            query.setParameter("maeAltaTempranaValor", obj.getMaeAltaTempranaValor());
            query.setParameter("maeAltaTempranaTipo", obj.getMaeAltaTempranaTipo());
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
    public AucIngreso eliminar(int id) throws Exception {
        AucIngreso obj = null;
        try {
            AucIngresos ent = getEntityManager().find(AucIngresos.class, id);
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
    
    private AucIngreso castEntidadNegocio(AucIngresos entidad) {
        AucIngreso negocio = new AucIngreso();
        negocio.setId(entidad.getId());
        negocio.setFechaIngreso(entidad.getFechaIngreso());
        negocio.setIngreso(entidad.getIngreso());
        negocio.setMaeTipoIngresoId(entidad.getMaeTipoIngresoId());
        negocio.setMaeTipoIngresoCodigo(entidad.getMaeTipoIngresoCodigo());
        negocio.setMaeTipoIngresoValor(entidad.getMaeTipoIngresoValor());
        negocio.setMaeCntModalidadId(entidad.getMaeCntModalidadId());
        negocio.setMaeCntModalidadCodigo(entidad.getMaeCntModalidadCodigo());
        negocio.setMaeCntModalidadValor(entidad.getMaeCntModalidadValor());
        negocio.setMaeRemisionNoPertinenteId(entidad.getMaeRemisionNoPertinenteId());
        negocio.setMaeRemisionNoPertinenteCodigo(entidad.getMaeRemisionNoPertinenteCodigo());
        negocio.setMaeRemisionNoPertinenteValor(entidad.getMaeRemisionNoPertinenteValor());
        negocio.setIndiceCharlson(entidad.getIndiceCharlson());
        negocio.setFuenteOrigen(entidad.getFuenteOrigen());
        negocio.setDescripcion(entidad.getDescripcionRemisionNoPertinente());
        negocio.setMaeCausaIngresoPrevalenteId(entidad.getMaeCausaIngresoPrevalenteId());
        negocio.setMaeCausaIngresoPrevalenteCodigo(entidad.getMaeCausaIngresoPrevalenteCodigo());
        negocio.setMaeCausaIngresoPrevalenteValor(entidad.getMaeCausaIngresoPrevalenteValor());
        negocio.setMaeAreaIngresoPrevenibleId(entidad.getMaeAreaIngresoPrevenibleId());
        negocio.setMaeAreaIngresoPrevenibleCodigo(entidad.getMaeAreaIngresoPrevenibleCodigo());
        negocio.setMaeAreaIngresoPrevenibleValor(entidad.getMaeAreaIngresoPrevenibleValor());
        negocio.setDescripcionIngresoPrevenible(entidad.getDescripcionIngresoPrevenible());
        negocio.setMaeReingresoMotivoId(entidad.getMaeReingresoMotivoId());
        negocio.setMaeReingresoMotivoCodigo(entidad.getMaeReingresoMotivoCodigo());
        negocio.setMaeReingresoMotivoValor(entidad.getMaeReingresoMotivoValor());
        negocio.setMaeReingresoMotivoTipo(entidad.getMaeReingresoMotivoTipo());
        if(!entidad.getAucDiagnosticosList().isEmpty()){
            negocio.setAucDiagnosticosList(AucDiagnosticoServicio.castEntidadNegocio(entidad.getAucDiagnosticosList())); ;
        }
        negocio.setAltaTemprana(entidad.getAltaTemprana());
        negocio.setMaeAltaTempranaId(entidad.getMaeAltaTempranaId());
        negocio.setMaeAltaTempranaCodigo(entidad.getMaeAltaTempranaCodigo());
        negocio.setMaeAltaTempranaValor(entidad.getMaeAltaTempranaValor());
        negocio.setMaeAltaTempranaTipo(entidad.getMaeAltaTempranaTipo());
        //negocio.setDescripcion(entidad.getDescripcion());
        //Auditoria
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        negocio.setTerminalModifica(entidad.getTerminalModifica());
        negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        return negocio;
    }
    
    private AucIngresos castNegocioEntidad(AucIngreso negocio) {
        AucIngresos entidad = new AucIngresos();
        entidad.setFechaIngreso(negocio.getFechaIngreso());
        if(negocio.getIngreso() != null){
            entidad.setIngreso(negocio.getIngreso());
        }
        entidad.setMaeTipoIngresoId(negocio.getMaeTipoIngresoId());
        entidad.setMaeTipoIngresoCodigo(negocio.getMaeTipoIngresoCodigo());
        entidad.setMaeTipoIngresoValor(negocio.getMaeTipoIngresoValor());
        entidad.setMaeCntModalidadId(negocio.getMaeCntModalidadId());
        entidad.setMaeCntModalidadCodigo(negocio.getMaeCntModalidadCodigo());
        entidad.setMaeCntModalidadValor(negocio.getMaeCntModalidadValor());
        entidad.setMaeRemisionNoPertinenteId(negocio.getMaeRemisionNoPertinenteId());
        entidad.setMaeRemisionNoPertinenteCodigo(negocio.getMaeRemisionNoPertinenteCodigo());
        entidad.setMaeRemisionNoPertinenteValor(negocio.getMaeRemisionNoPertinenteValor());
        entidad.setIndiceCharlson(negocio.getIndiceCharlson());
        entidad.setFuenteOrigen(negocio.getFuenteOrigen());
        entidad.setDescripcionRemisionNoPertinente(negocio.getDescripcion());
        entidad.setMaeCausaIngresoPrevalenteId(negocio.getMaeCausaIngresoPrevalenteId());
        entidad.setMaeCausaIngresoPrevalenteCodigo(negocio.getMaeCausaIngresoPrevalenteCodigo());
        entidad.setMaeCausaIngresoPrevalenteValor(negocio.getMaeCausaIngresoPrevalenteValor());
        entidad.setMaeAreaIngresoPrevenibleId(negocio.getMaeAreaIngresoPrevenibleId());
        entidad.setMaeAreaIngresoPrevenibleCodigo(negocio.getMaeAreaIngresoPrevenibleCodigo());
        entidad.setMaeAreaIngresoPrevenibleValor(negocio.getMaeAreaIngresoPrevenibleValor());
        entidad.setDescripcionIngresoPrevenible(negocio.getDescripcionIngresoPrevenible());
        entidad.setMaeReingresoMotivoId(negocio.getMaeReingresoMotivoId());
        entidad.setMaeReingresoMotivoCodigo(negocio.getMaeReingresoMotivoCodigo());
        entidad.setMaeReingresoMotivoValor(negocio.getMaeReingresoMotivoValor());
        entidad.setMaeReingresoMotivoTipo(negocio.getMaeReingresoMotivoTipo());
        entidad.setAltaTemprana(negocio.getAltaTemprana());
        entidad.setMaeAltaTempranaId(negocio.getMaeAltaTempranaId());
        entidad.setMaeAltaTempranaCodigo(negocio.getMaeAltaTempranaCodigo());
        entidad.setMaeAltaTempranaValor(negocio.getMaeAltaTempranaValor());
        entidad.setMaeAltaTempranaTipo(negocio.getMaeAltaTempranaTipo());
        //Auditoria
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setTerminalCrea(negocio.getTerminalCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        return entidad;
    }
    
}
