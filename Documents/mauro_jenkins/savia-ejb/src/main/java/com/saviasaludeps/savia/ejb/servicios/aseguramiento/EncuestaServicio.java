/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.aseguramiento;

import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegEncuestaPregunta;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegTabulacionEncuesta;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliados;
import com.saviasaludeps.savia.ejb.entidades.AsegEncuestaPreguntas;
import com.saviasaludeps.savia.ejb.entidades.AsegTabulacionEncuestas;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.GnUbicaciones;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.negocio.aseguramiento.EncuestaRemoto;

/**
 *
 * @author raul-palacios
 */
@Stateless
@Remote(EncuestaRemoto.class)
public class EncuestaServicio extends GenericoServicio implements EncuestaRemoto {

    @Override
    public AsegTabulacionEncuesta consultar(int id) throws Exception {
        AsegTabulacionEncuesta objRes = null;
        try {
            objRes = castEntidadNegocio((AsegTabulacionEncuestas) getEntityManager().find(AsegTabulacionEncuestas.class, id));
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
    public int insertar(AsegTabulacionEncuesta obj) throws Exception {
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
    public void actualizar(AsegTabulacionEncuesta obj) throws Exception {
        try {
            getEntityManager().merge(castNegocioEntidad(obj));
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public AsegTabulacionEncuesta eliminar(int id) throws Exception {
        AsegTabulacionEncuesta obj = null;
        try {
            AsegTabulacionEncuestas ent = getEntityManager().find(AsegTabulacionEncuestas.class, id);
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
    public List<AsegTabulacionEncuesta> consultarTodos() throws Exception {
        List<AsegTabulacionEncuesta> listResult = new ArrayList();
        try {
            String strQuery = "FROM AsegTabulacionEncuestas p "
                    + "ORDER BY p.fechaHoraCrea DESC";
            List<AsegTabulacionEncuestas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AsegTabulacionEncuestas per : list) {
                listResult.add(castEntidadNegocio(per));
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

    public static AsegTabulacionEncuesta castEntidadNegocio(AsegTabulacionEncuestas per) {
        AsegTabulacionEncuesta obj = new AsegTabulacionEncuesta();
        obj.setId(per.getId());
        obj.setPrimerApellido(per.getPrimerApellido());
        obj.setSegundoApellido(per.getSegundoApellido());
        obj.setPrimerNombre(per.getPrimerNombre());
        obj.setSegundoNombre(per.getSegundoNombre());
        obj.setFechaNacimiento(per.getFechaNacimiento());
        obj.setUbicacion(per.getUbicacionesId());
        obj.setRespuesta(per.getRespuesta());
        obj.setObservacion(per.getObservacion());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setUbicacion(per.getUbicacionesId());
        if (per.getAsegAfiliadosId() != null) {
            obj.setAsegAfiliado(new AsegAfiliado(per.getAsegAfiliadosId().getId()));
        }
        if (per.getAsegEncuestaPreguntasId() != null) {
            obj.setEncuestaPregunta(castEncuestaPreguntasEntidadNegocio(per.getAsegEncuestaPreguntasId()));
        }
        return obj;
    }

    public static Ubicacion castUbicacionEntidadNegocio(GnUbicaciones per) {
        Ubicacion obj = new Ubicacion();
        if (per != null) {
            obj.setId(per.getId());
            obj.setNombre(per.getNombre());
            obj.setTipo(per.getTipo());
            obj.setPrefijo(per.getPrefijo());
        }
        return obj;
    }

    public static CntPrestadorSede castPrestadorSedesEntidadNegocio(CntPrestadorSedes per) {
        CntPrestadorSede obj = new CntPrestadorSede();
        if (per != null) {
            obj.setId(per.getId());
            obj.setUbicacionId(per.getUbicacionId());
            obj.setCodigoPrestador(per.getCodigoPrestador());
            obj.setDireccion(per.getDireccion());
            obj.setNombreSede(per.getNombre());
            obj.setCodigoSede(per.getCodigo());
            obj.setCodigoHabilitacionSede(per.getCodigoHabilitacion());
            obj.setZonaPrecedencia(per.getZonaPrecedencia());
            obj.setEstadoSede(per.getEstadoSede());
            obj.setNivelAtencion(per.getNivelAtencion());
            obj.setClasePrestador(per.getMaeClasePrestadorId());
            obj.setFax(per.getFax());
            obj.setTelefonoCitas(per.getTelefonoCitas());
            obj.setCorreoElectronico(per.getCorreoElectronico());
            obj.setTelefonoAdministrativo(per.getTelefonoAdministrativo());
            obj.setCapitacion(per.getCapitacion());
            // no se mapea prestador
        }
        return obj;
    }

    public static AsegTabulacionEncuestas castNegocioEntidad(AsegTabulacionEncuesta obj) {
        AsegTabulacionEncuestas per = new AsegTabulacionEncuestas();
        per.setId(obj.getId());
        per.setPrimerApellido(obj.getPrimerApellido());
        per.setSegundoApellido(obj.getSegundoApellido());
        per.setPrimerNombre(obj.getPrimerNombre());
        per.setSegundoNombre(obj.getSegundoNombre());
        per.setFechaNacimiento(obj.getFechaNacimiento());
        per.setUbicacionesId(obj.getUbicacion());
        per.setRespuesta(obj.isRespuesta());
        per.setObservacion(obj.getObservacion());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setUsuarioModifica(obj.getTerminalModifica());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setUbicacionesId(obj.getUbicacion());
        if (obj.getAsegAfiliado() != null) {
            per.setAsegAfiliadosId(new AsegAfiliados(obj.getAsegAfiliado().getId()));
        }
        if (obj.getEncuestaPregunta() != null) {
            per.setAsegEncuestaPreguntasId(new AsegEncuestaPreguntas(obj.getEncuestaPregunta().getId()));
        }
        return per;
    }

    //funciones utliidad
    public String retornarConector(int i) {
        if (i == 0) {
            return "WHERE ";
        } else {
            return "AND ";
        }
    }

    @Override
    public List<AsegEncuestaPregunta> consultarPreguntasEncuesta() throws Exception {
        List<AsegEncuestaPregunta> listResult = new ArrayList();
        try {
            String strQuery = "FROM AsegEncuestaPreguntas p WHERE p.estado = 1 "
                    + "ORDER BY p.fechaHoraCrea DESC";
            List<AsegEncuestaPreguntas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AsegEncuestaPreguntas per : list) {
                listResult.add(castEncuestaPreguntasEntidadNegocio(per));
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

    public static AsegEncuestaPregunta castEncuestaPreguntasEntidadNegocio(AsegEncuestaPreguntas per) {
        AsegEncuestaPregunta obj = new AsegEncuestaPregunta();
        obj.setId(per.getId());
        obj.setPregunta(per.getPregunta());
        obj.setNorma(per.getNorma());
        obj.setEstado(per.getEstado());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        return obj;
    }

    @Override
    public List<AsegTabulacionEncuesta> consultarPorAfiliado(int id) throws Exception {
        List<AsegTabulacionEncuesta> listResult = new ArrayList();
        try {
            String strQuery = "FROM AsegTabulacionEncuestas p "
                    + " WHERE p.asegAfiliadosId.id = " + id
                    + "ORDER BY p.fechaHoraCrea DESC";
            List<AsegTabulacionEncuestas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AsegTabulacionEncuestas per : list) {
                listResult.add(castEntidadNegocio(per));
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

}
