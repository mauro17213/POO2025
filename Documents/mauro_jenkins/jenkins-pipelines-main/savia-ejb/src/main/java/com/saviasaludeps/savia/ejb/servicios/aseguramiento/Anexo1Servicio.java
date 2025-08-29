/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.aseguramiento;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAnexo1;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAnexo1Adjunto;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliados;
import com.saviasaludeps.savia.ejb.entidades.AsegAnexo1Adjuntos;
import com.saviasaludeps.savia.ejb.entidades.AsegAnexos1;
import com.saviasaludeps.savia.ejb.entidades.GnUbicaciones;
import com.saviasaludeps.savia.ejb.entidades.GnEmpresas;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.negocio.aseguramiento.Anexo1Remoto;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.hibernate.Session;

/**
 *
 * @author jose perez hernandez
 */
@Stateless
@Remote(Anexo1Remoto.class)
public class Anexo1Servicio extends GenericoServicio implements Anexo1Remoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String strQuery = "SELECT COUNT(p) FROM AsegAnexos1 p ";
            //Enviaremos en el parámetro de la empresa, el id del prestador si lo posee. Sólo cuando es null, pertenece a Savia Salud EPS
            if (paramConsulta.getEmpresaId() != null) {
                strQuery += "WHERE p.gnEmpresasId.id = " + paramConsulta.getEmpresaId() + " ";
            } else {
                strQuery += "WHERE p.id > 0 ";
            }
            //parámetros de las fechas
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND p.fechaHoraCrea >= '" + sdf.format((Date) paramConsulta.getParametroConsulta1()) + "' ";
            }
            if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND p.fechaHoraCrea <= '" + sdf.format((Date) paramConsulta.getParametroConsulta2()) + "' ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "estado":
                            strQuery += "AND p.estado = " +  e.getValue() + " ";
                            break;
                        case "nombre1Nuevo":
                            strQuery += "AND p.nombre1Nuevo LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "nombre2Nuevo":
                            strQuery += "AND p.nombre2Nuevo LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "apellido1Nuevo":
                            strQuery += "AND p.apellido1Nuevo LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "apellido2Nuevo":
                            strQuery += "AND p.apellido2Nuevo LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
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
    public List<AsegAnexo1> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AsegAnexo1> listResult = new ArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String strQuery = "FROM AsegAnexos1 p ";
            //Enviaremos en el parámetro de la empresa, el id del prestador si lo posee. Sólo cuando es null, pertenece a Savia Salud EPS
            if (paramConsulta.getEmpresaId() != null) {
                strQuery += "WHERE p.gnEmpresasId.id = " + paramConsulta.getEmpresaId() + " ";
            } else {
                strQuery += "WHERE p.id > 0 ";
            }
            //parámetros de las fechas
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND p.fechaHoraCrea >= '" + sdf.format((Date) paramConsulta.getParametroConsulta1()) + "' ";
            }
            if (paramConsulta.getParametroConsulta2() != null) {
                strQuery += "AND p.fechaHoraCrea <= '" + sdf.format((Date) paramConsulta.getParametroConsulta2()) + "' ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + e.getValue() + " ";
                            break;
                        case "estado":
                            strQuery += "AND p.estado = " +  e.getValue() + " ";
                            break;
                        case "nombre1Nuevo":
                            strQuery += "AND p.nombre1Nuevo LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "nombre2Nuevo":
                            strQuery += "AND p.nombre2Nuevo LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "apellido1Nuevo":
                            strQuery += "AND p.apellido1Nuevo LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "apellido2Nuevo":
                            strQuery += "AND p.apellido2Nuevo LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "p.id DESC";
            }
            List<AsegAnexos1> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    //.setParameter("empresa_id", paramConsulta.getEmpresaId())
                    .getResultList();
            for (AsegAnexos1 per : list) {
                listResult.add(castEntidadNegocioCorto(per));
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
    public AsegAnexo1 consultar(int id) throws Exception {
        AsegAnexo1 objRes = null;
        try {
            objRes = castEntidadNegocioLargo((AsegAnexos1) getEntityManager().find(AsegAnexos1.class, id));
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
    public int insertar(AsegAnexo1 obj) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(castNegocioEntidadLargo(obj)).getId();
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
    public void actualizar(AsegAnexo1 obj) throws Exception {
        try {
            AsegAnexos1 per = castNegocioEntidadLargo(obj);
            //getEntityManager().merge(castNegocioEntidadLargo(obj));
            Session session = getEntityManager().unwrap(Session.class);
            //session.update(per);
            String strQuery = "UPDATE AsegAnexos1 a SET ";
            //strQuery += " a.id = :id";
            strQuery += " a.tipoDocumentoInconsistencia = :tipoDocumentoInconsistencia ,";
            strQuery += " a.numeroDocumentoInconsistencia = :numeroDocumentoInconsistencia ,";
            strQuery += " a.apellido1Inconsistencia = :apellido1Inconsistencia ,";
            strQuery += " a.apellido2Inconsistencia = :apellido2Inconsistencia ,";
            strQuery += " a.nombre1Inconsistencia = :nombre1Inconsistencia ,";
            strQuery += " a.nombre2Inconsistencia = :nombre2Inconsistencia ,";
            strQuery += " a.fechaNacimientoInconsistencia = :fechaNacimientoInconsistencia ,";
            strQuery += " a.sexoInconsistencia = :sexoInconsistencia ,";
            strQuery += " a.direccionInconsistencia = :direccionInconsistencia ,";
            strQuery += " a.telefonoInconsistencia = :telefonoInconsistencia ,";
            strQuery += " a.celularInconsistencia = :celularInconsistencia ,";
            //strQuery += " a.fechaExpedicionCedulaInconsistencia = :fechaExpedicionCedulaInconsistencia ,";
            strQuery += " a.tipoDocumentoNuevo = :tipoDocumentoNuevo ,";
            strQuery += " a.numeroDocumentoNuevo = :numeroDocumentoNuevo ,";
            strQuery += " a.apellido1Nuevo = :apellido1Nuevo ,";
            strQuery += " a.apellido2Nuevo = :apellido2Nuevo ,";
            strQuery += " a.nombre1Nuevo = :nombre1Nuevo ,";
            strQuery += " a.nombre2Nuevo = :nombre2Nuevo ,";
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            if(obj.getFechaNacimientoNuevo() != null) {
                String fechaNacimientoNuevo = formatoFecha.format(obj.getFechaNacimientoNuevo());
                strQuery += " a.fechaNacimientoNuevo = '" + fechaNacimientoNuevo + "', ";
            }
            strQuery += " a.sexoNuevo = :sexoNuevo ,";
            strQuery += " a.direccionNuevo = :direccionNuevo ,";
            strQuery += " a.telefonoNuevo = :telefonoNuevo ,";
            strQuery += " a.celularNuevo = :celularNuevo ,";
            //2024-03-22 jyperez RES 2335
            strQuery += " a.version = :version ,";
            strQuery += " a.cntPrestadoresId = :cntPrestadoresId ,";
            strQuery += " a.cntPrestadorSedesId = :cntPrestadorSedesId ,";
            strQuery += " a.emailNuevo = :emailNuevo ,";
            strQuery += " a.direccionAlternaContactoNuevo = :direccionAlternaContactoNuevo ,";
            strQuery += " a.nombreContactoEmergenciaNuevo = :nombreContactoEmergenciaNuevo ,";
            strQuery += " a.telefonoContactoEmergenciaNuevo = :telefonoContactoEmergenciaNuevo ,";
            //2024-04-12 jyperez nuevo campo res 2335
            strQuery += " a.consecutivo = :consecutivo ,";
            //2024-03-27 jyperez nuevos campos inconsistencia
            strQuery += " a.residenciaUbicacionIdIncosistencia = :residenciaUbicacionIdIncosistencia ,";
            strQuery += " a.emailIncosistencia = :emailIncosistencia ,";
            strQuery += " a.direccionAlternaContactoIncosistencia = :direccionAlternaContactoIncosistencia ,";
            strQuery += " a.nombreContactoEmergenciaIncosistencia = :nombreContactoEmergenciaIncosistencia ,";
            strQuery += " a.telefonoContactoEmergenciaIncosistencia = :telefonoContactoEmergenciaIncosistencia ,";
            /*String fechaExpedicionCedulaNuevo;
            if (obj.getFechaExpedicionCedulaNuevo()!= null) {
                fechaExpedicionCedulaNuevo = formatoFecha.format(obj.getFechaExpedicionCedulaNuevo());
                strQuery += " a.fechaExpedicionCedulaNuevo = '" + fechaExpedicionCedulaNuevo + "', ";
            }*/
            strQuery += " a.observacion = :observacion ,";
            strQuery += " a.estado = :estado ";
            
            // campos objetos 
            if (per.getAsegAfiliadosId() != null) {
                strQuery += ", a.asegAfiliadosId.id = " + per.getAsegAfiliadosId().getId() + " ";
            }
            if (per.getResidenciaUbicacionIdNuevo()!= null) {
                strQuery += ", a.residenciaUbicacionIdNuevo.id = " + per.getResidenciaUbicacionIdNuevo().getId() + " ";
            }
            
            strQuery += " WHERE a.id = :id ";
            org.hibernate.Query query = session.createQuery(strQuery);
            query.setProperties(per);
            query.executeUpdate();
            
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public AsegAnexo1 eliminar(int id) throws Exception {
        AsegAnexo1 obj = null;
        try {
            AsegAnexos1 ent = getEntityManager().find(AsegAnexos1.class, id);
            if (ent != null) {
                obj = castEntidadNegocioCorto(ent);
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
    public List<AsegAnexo1> consultarTodos() throws Exception {
        List<AsegAnexo1> listResult = new ArrayList();
        try {
            String strQuery = "FROM AsegAnexos1 p "
                    + "ORDER BY p.fechaHoraCrea DESC";
            List<AsegAnexos1> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AsegAnexos1 per : list) {
                listResult.add(castEntidadNegocioLargo(per));
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
    
    @SuppressWarnings("null")
    public static AsegAnexo1 castEntidadNegocioCorto(AsegAnexos1 per) {
        AsegAnexo1 obj = new AsegAnexo1();
        obj.setId(per.getId());
        obj.setTipoDocumentoInconsistencia(per.getTipoDocumentoInconsistencia());
        obj.setNumeroDocumentoInconsistencia(per.getNumeroDocumentoInconsistencia());
        obj.setApellido1Inconsistencia(per.getApellido1Inconsistencia());
        obj.setApellido2Inconsistencia(per.getApellido2Inconsistencia());
        obj.setNombre1Inconsistencia(per.getNombre1Inconsistencia());
        obj.setNombre2Inconsistencia(per.getNombre2Inconsistencia());
        obj.setFechaNacimientoInconsistencia(per.getFechaNacimientoInconsistencia());
        obj.setSexoInconsistencia(per.getSexoInconsistencia());
        obj.setDireccionInconsistencia(per.getDireccionInconsistencia());
        obj.setTelefonoInconsistencia(per.getTelefonoInconsistencia());
        obj.setCelularInconsistencia(per.getCelularInconsistencia());
        obj.setFechaExpedicionCedulaInconsistencia(per.getFechaExpedicionInconsistencia());
        obj.setTipoDocumentoNuevo(per.getTipoDocumentoNuevo());
        obj.setNumeroDocumentoNuevo(per.getNumeroDocumentoNuevo());
        obj.setApellido1Nuevo(per.getApellido1Nuevo());
        obj.setApellido2Nuevo(per.getApellido2Nuevo());
        obj.setNombre1Nuevo(per.getNombre1Nuevo());
        obj.setNombre2Nuevo(per.getNombre2Nuevo());
        obj.setFechaNacimientoNuevo(per.getFechaNacimientoNuevo());
        obj.setSexoNuevo(per.getSexoNuevo());
        obj.setDireccionNuevo(per.getDireccionNuevo());
        obj.setTelefonoNuevo(per.getTelefonoNuevo());
        obj.setCelularNuevo(per.getCelularNuevo());
        obj.setFechaExpedicionCedulaNuevo(per.getFechaExpedicionNuevo());
        obj.setObservacion(per.getObservacion());
        obj.setEstado(per.getEstado());
        //nuevos campos
        obj.setTratamientoDatosAutoriza(per.getTratamientoDatosAutoriza());
        obj.setTratamientoDatosFechaHora(per.getTratamientoDatosFechaHora());
        //2024-03-22 jyperez RES 2335
        obj.setVersion(per.getVersion());
        obj.setCntPrestadoresId(per.getCntPrestadoresId());
        obj.setCntPrestadorSedesId(per.getCntPrestadorSedesId());
        obj.setEmailNuevo(per.getEmailNuevo());
        obj.setDireccionAlternaContactoNuevo(per.getDireccionAlternaContactoNuevo());
        obj.setNombreContactoEmergenciaNuevo(per.getNombreContactoEmergenciaNuevo());
        obj.setTelefonoContactoEmergenciaNuevo(per.getTelefonoContactoEmergenciaNuevo());
        //2024-04-12 jyperez nuevos campo
        obj.setConsecutivo(per.getConsecutivo());
        //2023-06-26 nuevos campos inconsistencia
        obj.setResidenciaUbicacionInconsistencia((per.getResidenciaUbicacionIdIncosistencia() == null) ? 0 : per.getResidenciaUbicacionIdIncosistencia());
        obj.setEmailInconsistencia((per.getEmailIncosistencia() == null) ? 0 : per.getEmailIncosistencia());
        obj.setDireccionAlternaContactoInconsistencia((per.getDireccionAlternaContactoIncosistencia()== null) ? 0 : per.getDireccionAlternaContactoIncosistencia());
        obj.setNombreContactoEmergenciaInconsistencia((per.getNombreContactoEmergenciaIncosistencia()== null) ? 0 : per.getNombreContactoEmergenciaIncosistencia());
        obj.setTelefonoContactoEmergenciaInconsistencia((per.getTelefonoContactoEmergenciaIncosistencia()== null) ? 0 : per.getTelefonoContactoEmergenciaIncosistencia());
        //objetos
        if(per.getAsegAfiliadosId() != null){
            obj.setAsegAfiliadosId(new AsegAfiliado(per.getAsegAfiliadosId().getId()));
        }
        if(per.getGnEmpresasId() != null) {
            obj.setGnEmpresa(new Empresa(per.getGnEmpresasId().getId()));
        }
         if(per.getResidenciaUbicacionIdNuevo() != null) {
            Ubicacion ubicacion = new Ubicacion();
            ubicacion.setId(per.getResidenciaUbicacionIdNuevo().getId());
            ubicacion.setNombre(per.getResidenciaUbicacionIdNuevo().getNombre());
            obj.setResidenciaUbicacionNuevo(ubicacion);
        }
        // auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        //objetos

        return obj;
    }

    @SuppressWarnings("null")
    public static AsegAnexo1 castEntidadNegocioLargo(AsegAnexos1 per) {
        AsegAnexo1 obj = new AsegAnexo1();
        List<AsegAnexo1Adjunto> listaAnexo1Adjuntos = new ArrayList<>();
        obj.setId(per.getId());
        obj.setTipoDocumentoInconsistencia(per.getTipoDocumentoInconsistencia());
        obj.setNumeroDocumentoInconsistencia(per.getNumeroDocumentoInconsistencia());
        obj.setApellido1Inconsistencia(per.getApellido1Inconsistencia());
        obj.setApellido2Inconsistencia(per.getApellido2Inconsistencia());
        obj.setNombre1Inconsistencia(per.getNombre1Inconsistencia());
        obj.setNombre2Inconsistencia(per.getNombre2Inconsistencia());
        obj.setFechaNacimientoInconsistencia(per.getFechaNacimientoInconsistencia());
        obj.setSexoInconsistencia(per.getSexoInconsistencia());
        obj.setDireccionInconsistencia(per.getDireccionInconsistencia());
        obj.setTelefonoInconsistencia(per.getTelefonoInconsistencia());
        obj.setCelularInconsistencia(per.getCelularInconsistencia());
        obj.setFechaExpedicionCedulaInconsistencia(per.getFechaExpedicionInconsistencia());
        obj.setTipoDocumentoNuevo(per.getTipoDocumentoNuevo());
        obj.setNumeroDocumentoNuevo(per.getNumeroDocumentoNuevo());
        obj.setApellido1Nuevo(per.getApellido1Nuevo());
        obj.setApellido2Nuevo(per.getApellido2Nuevo());
        obj.setNombre1Nuevo(per.getNombre1Nuevo());
        obj.setNombre2Nuevo(per.getNombre2Nuevo());
        obj.setFechaNacimientoNuevo(per.getFechaNacimientoNuevo());
        obj.setSexoNuevo(per.getSexoNuevo());
        obj.setDireccionNuevo(per.getDireccionNuevo());
        obj.setTelefonoNuevo(per.getTelefonoNuevo());
        obj.setCelularNuevo(per.getCelularNuevo());
        obj.setFechaExpedicionCedulaNuevo(per.getFechaExpedicionNuevo());
        obj.setObservacion(per.getObservacion());
        obj.setEstado(per.getEstado());
        //2024-03-22 jyperez RES 2335
        obj.setVersion(per.getVersion());
        obj.setCntPrestadoresId(per.getCntPrestadoresId());
        obj.setCntPrestadorSedesId(per.getCntPrestadorSedesId());
        obj.setEmailNuevo(per.getEmailNuevo());
        obj.setDireccionAlternaContactoNuevo(per.getDireccionAlternaContactoNuevo());
        obj.setNombreContactoEmergenciaNuevo(per.getNombreContactoEmergenciaNuevo());
        obj.setTelefonoContactoEmergenciaNuevo(per.getTelefonoContactoEmergenciaNuevo());
        //2024-04-12 jyperez nuevos campo
        obj.setConsecutivo(per.getConsecutivo());
        //2023-06-26 nuevos campos inconsistencia
        obj.setResidenciaUbicacionInconsistencia((per.getResidenciaUbicacionIdIncosistencia() == null) ? 0 : per.getResidenciaUbicacionIdIncosistencia());
        obj.setEmailInconsistencia((per.getEmailIncosistencia() == null) ? 0 : per.getEmailIncosistencia());
        obj.setDireccionAlternaContactoInconsistencia((per.getDireccionAlternaContactoIncosistencia()== null) ? 0 : per.getDireccionAlternaContactoIncosistencia());
        obj.setNombreContactoEmergenciaInconsistencia((per.getNombreContactoEmergenciaIncosistencia()== null) ? 0 : per.getNombreContactoEmergenciaIncosistencia());
        obj.setTelefonoContactoEmergenciaInconsistencia((per.getTelefonoContactoEmergenciaIncosistencia()== null) ? 0 : per.getTelefonoContactoEmergenciaIncosistencia());
        //nuevos campos
        obj.setTratamientoDatosAutoriza(per.getTratamientoDatosAutoriza());
        obj.setTratamientoDatosFechaHora(per.getTratamientoDatosFechaHora());
        //objetos
        if(per.getAsegAfiliadosId() != null){
            AsegAfiliado afiliado = new AsegAfiliado(per.getAsegAfiliadosId().getId());
            afiliado.setMaeTipoDocumento(per.getAsegAfiliadosId().getMaeTipoDocumentoId());
            afiliado.setMaeTipoDocumentoCodigo(per.getAsegAfiliadosId().getMaeTipoDocumentoCodigo());
            afiliado.setMaeTipoDocumentoValor(per.getAsegAfiliadosId().getMaeTipoDocumentoValor());
            afiliado.setNumeroDocumento(per.getAsegAfiliadosId().getNumeroDocumento());
            afiliado.setPrimerNombre(per.getAsegAfiliadosId().getPrimerNombre());
            afiliado.setPrimerApellido(per.getAsegAfiliadosId().getPrimerApellido());
            afiliado.setSegundoNombre(per.getAsegAfiliadosId().getSegundoNombre());
            afiliado.setSegundoApellido(per.getAsegAfiliadosId().getSegundoApellido());
            afiliado.setGenero(per.getAsegAfiliadosId().getGenero());
            afiliado.setMaeGeneroId(per.getAsegAfiliadosId().getMaeGeneroId());
            afiliado.setMaeGeneroCodigo(per.getAsegAfiliadosId().getMaeGeneroCodigo());
            afiliado.setMaeGeneroValor(per.getAsegAfiliadosId().getMaeGeneroValor());
            if (per.getAsegAfiliadosId().getAfiliacionUbicacionesId() != null) {
            afiliado.setAfiliacionUbicacion(castUbicacionEntidadNegocio(per.getAsegAfiliadosId().getAfiliacionUbicacionesId()));
        }
            obj.setAsegAfiliadosId(afiliado);
        }
        if(per.getGnEmpresasId() != null) {
            obj.setGnEmpresa(new Empresa(per.getGnEmpresasId().getId()));
        }
        if(per.getResidenciaUbicacionIdNuevo() != null) {
            Ubicacion ubicacion = new Ubicacion();
            ubicacion.setId(per.getResidenciaUbicacionIdNuevo().getId());
            ubicacion.setNombre(per.getResidenciaUbicacionIdNuevo().getNombre());
            obj.setResidenciaUbicacionNuevo(ubicacion);
        }
        //lista
        if (per.getAsegAnexo1AdjuntosList() != null) {
            for (AsegAnexo1Adjuntos adj: per.getAsegAnexo1AdjuntosList()) {
                AsegAnexo1Adjunto adjunto = castAsegAnexo1AdjuntoEntidadNegocioLargo(adj);
                listaAnexo1Adjuntos.add(adjunto);
            }
            obj.setListaAnexo1Adjuntos(listaAnexo1Adjuntos);
        }
        // auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        //objetos

        return obj;
    }
    
    public static AsegAnexo1Adjunto castAsegAnexo1AdjuntoEntidadNegocioLargo(AsegAnexo1Adjuntos per) {
        AsegAnexo1Adjunto obj = new AsegAnexo1Adjunto();
        obj.setId(per.getId());
        obj.setArchivo(per.getArchivo());
        obj.setRuta(per.getRuta());
        //objetos
        if (per.getAsegAnexos1Id() != null) {
            obj.setAsegAnexo1Id(new AsegAnexo1(per.getAsegAnexos1Id().getId()));
        }
        // auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        //objetos

        return obj;
    }
    
    public static Ubicacion castUbicacionEntidadNegocio(GnUbicaciones per) {
        Ubicacion obj = new Ubicacion();
        if (per != null) {
            obj.setId(per.getId());
            obj.setNombre(per.getNombre());
            obj.setTipo(per.getTipo());
            obj.setPrefijo(per.getPrefijo());
            // 2020-10-21 jyperez ajuste para soportar datos de la ubicación padre - Req Validacion Derechos de Afiliado
            if (per.getGnUbicacionesId() != null) {
                obj.setUbicacionPadre(new Ubicacion(null, per.getGnUbicacionesId().getId(), per.getGnUbicacionesId().getTipo(), per.getGnUbicacionesId().getCodigoPostal(), per.getGnUbicacionesId().getNombre()));
                obj.getUbicacionPadre().setPrefijo(per.getGnUbicacionesId().getPrefijo());
            }
        }
        return obj;
    }

    public static AsegAnexos1 castNegocioEntidadLargo(AsegAnexo1 obj) {
        AsegAnexos1 per = new AsegAnexos1();
        per.setId(obj.getId());
        per.setTipoDocumentoInconsistencia(obj.getTipoDocumentoInconsistencia());
        per.setNumeroDocumentoInconsistencia(obj.getNumeroDocumentoInconsistencia());
        per.setApellido1Inconsistencia(obj.getApellido1Inconsistencia());
        per.setApellido2Inconsistencia(obj.getApellido2Inconsistencia());
        per.setNombre1Inconsistencia(obj.getNombre1Inconsistencia());
        per.setNombre2Inconsistencia(obj.getNombre2Inconsistencia());
        per.setFechaNacimientoInconsistencia(obj.getFechaNacimientoInconsistencia());
        per.setSexoInconsistencia(obj.getSexoInconsistencia());
        per.setDireccionInconsistencia(obj.getDireccionInconsistencia());
        per.setTelefonoInconsistencia(obj.getTelefonoInconsistencia());
        per.setCelularInconsistencia(obj.getCelularInconsistencia());
        per.setFechaExpedicionInconsistencia(obj.getFechaExpedicionCedulaInconsistencia());
        per.setTipoDocumentoNuevo(obj.getTipoDocumentoNuevo());
        per.setNumeroDocumentoNuevo(obj.getNumeroDocumentoNuevo());
        per.setApellido1Nuevo(obj.getApellido1Nuevo());
        per.setApellido2Nuevo(obj.getApellido2Nuevo());
        per.setNombre1Nuevo(obj.getNombre1Nuevo());
        per.setNombre2Nuevo(obj.getNombre2Nuevo());
        per.setFechaNacimientoNuevo(obj.getFechaNacimientoNuevo());
        per.setSexoNuevo(obj.getSexoNuevo());
        per.setDireccionNuevo(obj.getDireccionNuevo());
        per.setTelefonoNuevo(obj.getTelefonoNuevo());
        per.setCelularNuevo(obj.getCelularNuevo());
        per.setFechaExpedicionNuevo(obj.getFechaExpedicionCedulaNuevo());
        per.setObservacion(obj.getObservacion());
        per.setEstado(obj.getEstado());
        //nuevos campos
        per.setTratamientoDatosAutoriza(obj.getTratamientoDatosAutoriza());
        per.setTratamientoDatosFechaHora(obj.getTratamientoDatosFechaHora());
        //2024-03-22 jyperez RES 2335
        per.setVersion(obj.isVersion());
        per.setCntPrestadoresId(obj.getCntPrestadoresId());
        per.setCntPrestadorSedesId(obj.getCntPrestadorSedesId());
        per.setEmailNuevo(obj.getEmailNuevo());
        per.setDireccionAlternaContactoNuevo(obj.getDireccionAlternaContactoNuevo());
        per.setNombreContactoEmergenciaNuevo(obj.getNombreContactoEmergenciaNuevo());
        per.setTelefonoContactoEmergenciaNuevo(obj.getTelefonoContactoEmergenciaNuevo());
        //2024-04-12 jyperez nuevos campo
        per.setConsecutivo(obj.getConsecutivo());
        //2023-06-26 nuevos campos inconsistencia
        per.setResidenciaUbicacionIdIncosistencia(obj.getResidenciaUbicacionInconsistencia());
        per.setEmailIncosistencia(obj.getEmailInconsistencia());
        per.setDireccionAlternaContactoIncosistencia(obj.getDireccionAlternaContactoInconsistencia());
        per.setNombreContactoEmergenciaIncosistencia(obj.getNombreContactoEmergenciaInconsistencia());
        per.setTelefonoContactoEmergenciaIncosistencia(obj.getTelefonoContactoEmergenciaInconsistencia());
        //objetos
        if (obj.getAsegAfiliadosId() != null) {
            per.setAsegAfiliadosId(new AsegAfiliados(obj.getAsegAfiliadosId().getId()));
        }
        if(obj.getGnEmpresa() != null) {
            per.setGnEmpresasId(new GnEmpresas(obj.getGnEmpresa().getId()));
        }
        if (obj.getResidenciaUbicacionNuevo() != null) {
            per.setResidenciaUbicacionIdNuevo(new GnUbicaciones(obj.getResidenciaUbicacionNuevo().getId()));
        }
        // auditoria
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        return per;
    }

}
