/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.contratacion;

import com.saviasaludeps.savia.dominio.contratacion.CntProfesional;
import com.saviasaludeps.savia.dominio.contratacion.CntProfesionalPrestador;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.CntProfesionalPrestadores;
import com.saviasaludeps.savia.ejb.entidades.CntProfesionales;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.contratacion.CntProfesionalRemoto;
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
 * @author rpalacios
 */
@Stateless
@Local(CntProfesionalLocal.class)
@Remote(CntProfesionalRemoto.class)
public class CntProfesionalServicio extends GenericoServicio implements CntProfesionalLocal, CntProfesionalRemoto {

    @Override
    public List<CntProfesional> consultarTodos() throws Exception {
        List<CntProfesional> listResult = new ArrayList();
        try {
            String strQuery = "FROM CntProfesionales p ";
            List<CntProfesionales> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (CntProfesionales ent : list) {
                listResult.add(castEntidadNegocioMinimo(ent));
            }
        } catch (NoResultException e) {
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    @Override
    public CntProfesional consultarProfesional(ParamConsulta paramConsulta) throws Exception {
        CntProfesional profesional = new CntProfesional();
        try {
            String strQuery = "FROM CntProfesionales p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "tipoDocumento":
                            strQuery += "AND p.maeTipoCodumentoId = " + e.getValue() + " ";
                            break;
                        case "numeroDocumento":
                            strQuery += "AND p.documento = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            CntProfesionales profesionalBD = (CntProfesionales) getEntityManager().createQuery(strQuery).getSingleResult();
            if (profesionalBD != null) {
                profesional = castEntidadNegocio(profesional);
            }
        } catch (NoResultException e) {
            profesional = new CntProfesional();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return profesional;
    }

    @Override
    public CntProfesional consultarNumDocumento(Integer tipodocumento, String numeroDocumento) throws Exception {
        CntProfesional resultado = null;
        try {
            String strQuery = "FROM CntProfesionales p "
                    + " WHERE p.maeTipoCodumentoId = :maeTipoCodumentoId "
                    + " AND p.documento = :documento ";
            CntProfesionales obj = (CntProfesionales) getEntityManager().createQuery(strQuery)
                    .setParameter("maeTipoCodumentoId", tipodocumento)
                    .setParameter("documento", numeroDocumento)
                    .setMaxResults(1)
                    .getSingleResult();
            resultado = castEntidadNegocio(obj);
        } catch (NoResultException e) {
            resultado = null;
        } catch (Exception e) {
            resultado = null;
        } finally {
            cerrarEntityManager();
        }
        return resultado;
    }

    @Override
    public CntProfesional consultar(String tipodocumento, String numeroDocumento) throws Exception {
        CntProfesional resultado = null;
        try {
            String strQuery = "FROM CntProfesionales p "
                    + " WHERE p.maeTipoDocumentoCodigo = :maeTipoCodumento "
                    + " AND p.documento = :documento ";
            CntProfesionales obj = (CntProfesionales) getEntityManager().createQuery(strQuery)
                    .setParameter("maeTipoCodumento", tipodocumento)
                    .setParameter("documento", numeroDocumento)
                    .setMaxResults(1)
                    .getSingleResult();
            resultado = castEntidadNegocio(obj);
        } catch (NoResultException e) {
            resultado = null;
        } catch (Exception e) {
            resultado = null;
        } finally {
            cerrarEntityManager();
        }
        return resultado;
    }

    @Override
    public CntProfesional consultar(int id) throws Exception {
        CntProfesional objResult = new CntProfesional();

        try {
            objResult = castEntidadNegocio((CntProfesionales) getEntityManager().find(CntProfesionales.class, id));
        } catch (NoResultException e) {
            objResult = null;
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }

        return objResult;
    }

    @Override
    public int insertar(CntProfesional obj) throws Exception {
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
    public void actualizar(CntProfesional obj) throws Exception {

        try {
            String sql = "UPDATE CntProfesionales SET maeTipoCodumentoId = :maeTipoCodumentoId, "
                    + "maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo, "
                    + "maeTipoDocumentoValor = :maeTipoDocumentoValor, "
                    + "documento = :documento, "
                    + "registroMedico = :registroMedico, "
                    + "primerNombre = :primerNombre, "
                    + "segundoNombre = :segundoNombre, "
                    + "primerApellido = :primerApellido, "
                    + "segundoApellido = :segundoApellido, "
                    + "usuarioCrea = :usuarioCrea, "
                    + "terminalCrea = :terminalCrea, "
                    + "fechaHoraCrea = :fechaHoraCrea, "
                    + "usuarioModifica = :usuarioModifica, "
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica "
                    + "WHERE id = :id";

            Query query = getEntityManager().createQuery(sql);
            query.setParameter("maeTipoCodumentoId", obj.getMaeTipoCodumentoId());
            query.setParameter("maeTipoDocumentoCodigo", obj.getMaeTipoDocumentoCodigo());
            query.setParameter("maeTipoDocumentoValor", obj.getMaeTipoDocumentoValor());
            query.setParameter("documento", obj.getDocumento());
            query.setParameter("registroMedico", obj.getRegistroMedico());
            query.setParameter("primerNombre", obj.getPrimerNombre());
            query.setParameter("segundoNombre", obj.getSegundoNombre());
            query.setParameter("primerApellido", obj.getPrimerApellido());
            query.setParameter("segundoApellido", obj.getSegundoApellido());
            query.setParameter("usuarioCrea", obj.getUsuarioCrea());
            query.setParameter("terminalCrea", obj.getTerminalCrea());
            query.setParameter("fechaHoraCrea", obj.getFechaHoraCrea());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());

            query.executeUpdate();

        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }

    }
    
    
    @Override
    public CntProfesional eliminar(int id) throws Exception {
        CntProfesional obj = null;
        try {
            CntProfesionales ent = getEntityManager().find(CntProfesionales.class, id);
            if (ent.getAuAnexos2List() != null && !ent.getAuAnexos2List().isEmpty()
                    || ent.getAuAnexos3List() != null && !ent.getAuAnexos3List().isEmpty()
                    || ent.getRefAnexos9List() != null && !ent.getRefAnexos9List().isEmpty()
                    || ent.getMpPrescripcionesList() != null && !ent.getMpPrescripcionesList().isEmpty()) {
                return obj;
            }
            ent.setAuAnexos2List(null);
            ent.setAuAnexos3List(null);
            ent.setRefAnexos9List(null);
            ent.setMpPrescripcionesList(null);
            List<CntProfesionalPrestadores> listaProfesionalPrestador = new ArrayList<>();
            if (ent != null) {
                obj = castEntidadNegocio(ent);
                obj.setListaCntProfesionalPrestador(new ArrayList<>());
                for (CntProfesionalPrestadores item : ent.getCntProfesionalPrestadoresList()) {
                    CntProfesionalPrestador cntProfesionalPrestador = CntPrestadorProfesionalServicio.castEntidadNegocio(item);
                    obj.getListaCntProfesionalPrestador().add(cntProfesionalPrestador);
                    listaProfesionalPrestador.add(item);
                }
                ent.setCntProfesionalPrestadoresList(null);
                for (CntProfesionalPrestadores cntProfesionalPrestadores : listaProfesionalPrestador) {
                    getEntityManager().remove(cntProfesionalPrestadores);
                }
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

    public static CntProfesional castEntidadNegocio(CntProfesionales ent) {
        CntProfesional obj = new CntProfesional();
        obj.setDocumento(ent.getDocumento());
        obj.setId(ent.getId());
        obj.setMaeTipoCodumentoId(ent.getMaeTipoCodumentoId());
        obj.setMaeTipoDocumentoCodigo(ent.getMaeTipoDocumentoCodigo());
        obj.setMaeTipoDocumentoValor(ent.getMaeTipoDocumentoValor());
        obj.setPrimerApellido(ent.getPrimerApellido());
        obj.setPrimerNombre(ent.getPrimerNombre());
        obj.setRegistroMedico(ent.getRegistroMedico());
        obj.setSegundoApellido(ent.getSegundoApellido());
        obj.setSegundoNombre(ent.getSegundoNombre());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        return obj;
    }

    private CntProfesional castEntidadNegocio(CntProfesional profesionalBD) {
        CntProfesional profesional = new CntProfesional();
        profesional.setId(profesionalBD.getId());
        profesional.setMaeTipoCodumentoId(profesionalBD.getMaeTipoCodumentoId());
        profesional.setPrimerNombre(profesionalBD.getPrimerNombre());
        profesional.setSegundoNombre(profesionalBD.getSegundoNombre());
        profesional.setPrimerApellido(profesionalBD.getPrimerApellido());
        profesional.setSegundoApellido(profesionalBD.getSegundoApellido());
        return profesional;
    }

    public static CntProfesionales castNegocioEntidad(CntProfesional obj) {
        CntProfesionales ent = new CntProfesionales();
        ent.setDocumento(obj.getDocumento());
        ent.setId(obj.getId());
        ent.setMaeTipoCodumentoId(obj.getMaeTipoCodumentoId());
        ent.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
        ent.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());
        ent.setPrimerApellido(obj.getPrimerApellido());
        ent.setPrimerNombre(obj.getPrimerNombre());
        ent.setRegistroMedico(obj.getRegistroMedico());
        ent.setSegundoApellido(obj.getSegundoApellido());
        ent.setSegundoNombre(obj.getSegundoNombre());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        ent.setUsuarioModifica(obj.getUsuarioModifica());
        ent.setTerminalModifica(obj.getTerminalModifica());
        ent.setFechaHoraModifica(obj.getFechaHoraModifica());
        return ent;
    }

    private CntProfesional castEntidadNegocioMinimo(CntProfesionales ent) {
        CntProfesional neg = new CntProfesional();
        neg.setDocumento(ent.getDocumento());
        neg.setId(ent.getId());
        neg.setMaeTipoCodumentoId(ent.getMaeTipoCodumentoId());
        return neg;
    }

    @Override
    public void actualizarRegistroMedico(CntProfesional obj) throws Exception {
         try {
            String sql = "UPDATE CntProfesionales SET registroMedico = :registroMedico, "
                    + "usuarioModifica = :usuarioModifica, "
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica "
                    + "WHERE id = :id";

            Query query = getEntityManager().createQuery(sql);
            query.setParameter("registroMedico", obj.getRegistroMedico());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());

            query.executeUpdate();

        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

}
