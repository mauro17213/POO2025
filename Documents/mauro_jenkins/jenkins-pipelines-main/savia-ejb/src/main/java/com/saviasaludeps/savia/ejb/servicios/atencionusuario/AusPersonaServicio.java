package com.saviasaludeps.savia.ejb.servicios.atencionusuario;

import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.atencionusuario.AusPersona;
import com.saviasaludeps.savia.dominio.atencionusuario.AusPersonaTelefono;
//import com.saviasaludeps.savia.ejb.entidades.AusPersonaTelefono;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliados;
import com.saviasaludeps.savia.ejb.entidades.AusPersonaTelefonos;
//import com.saviasaludeps.savia.ejb.entidades.AusPersonaTelefono;
import com.saviasaludeps.savia.ejb.entidades.AusPersonas;
import com.saviasaludeps.savia.ejb.entidades.GnUbicaciones;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.negocio.atencionusuario.AusPersonaRemoto;
import java.util.Optional;
import javax.ejb.Local;
import javax.persistence.Query;

@Stateless
@Remote(AusPersonaRemoto.class)
@Local(AusPersonaLocal.class)

public class AusPersonaServicio extends GenericoServicio implements AusPersonaRemoto, AusPersonaLocal {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p.id) FROM AusPersonas p "
                    + "WHERE 1 = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maeTipoDocumentoId":
                            strQuery += "AND p.maeTipoDocumentoId = " + (String) e.getValue() + " ";
                            break;
                        case "documento":
                            strQuery += "AND p.documento LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "nombres":
                            strQuery += "AND p.nombres LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "apellidos":
                            strQuery += "AND p.apellidos LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "maeSexo":
                            strQuery += "AND p.maeSexo = " + (String) e.getValue() + " ";
                            break;
                        case "maeEstado":
                            strQuery += "AND p.maeEstado = " + (String) e.getValue() + " ";
                            break;
                        case "mae_estado_id":
                            strQuery += "AND p.maeEstadoId = " + (String) e.getValue() + " ";
                            break;
                        case "mae_sexo_id":
                            strQuery += "AND p.maeSexoId = " + (String) e.getValue() + " ";
                            break;
                    }
                }
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
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
    public List<AusPersona> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<AusPersona> listResult = new ArrayList();
        int i = 0;
        try {
            String strQuery = "SELECT p FROM AusPersonas p "
                    + "WHERE 1 = 1 ";
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maeTipoDocumentoId":
                            strQuery += "AND p.maeTipoDocumentoId = " + (String) e.getValue() + " ";
                            break;
                        case "documento":
                            strQuery += "AND p.documento LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "nombres":
                            strQuery += "AND p.nombres LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "apellidos":
                            strQuery += "AND p.apellidos LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "maeSexo":
                            strQuery += "AND p.maeSexo = " + (String) e.getValue() + " ";
                            break;
                        case "maeEstado":
                            strQuery += "AND p.maeEstado = " + (String) e.getValue() + " ";
                            break;
                        case "mae_estado_id":
                            strQuery += "AND p.maeEstadoId = " + (String) e.getValue() + " ";
                            break;
                        case "mae_sexo_id":
                            strQuery += "AND p.maeSexoId = " + (String) e.getValue() + " ";
                            break;
                    }
                }
            }
            strQuery += "ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "p." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "p.id ASC";
            }
            List<AusPersonas> list = getEntityManager().createQuery(strQuery)
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (AusPersonas per : list) {
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

    @Override
    public AusPersona consultarPersona(AusPersona obj) throws Exception {
        AusPersona ausPersona = new AusPersona();
        try {
            String strQuery = "FROM AusPersonas p "
                    + "WHERE";
            strQuery += " p.maeTipoDocumentoCodigo = '" + obj.getMae_tipo_documento_codigo()+ "' ";
            strQuery += "AND p.documento = " + obj.getDocumento() + " ";

            List<AusPersonas> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (AusPersonas per : list) {
                ausPersona = castEntidadNegocio(per);
            }

        } catch (NoResultException e) {
            ausPersona = new AusPersona();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return ausPersona;
    }

//    @Override
//    public AusPersona consultarPersona(AusPersona obj) throws Exception {
//        AusPersona ausPersona = new AusPersona();
//        try {
//            String strQuery = "FROM AusPersonas p "
//                    + "WHERE  ";
//            strQuery += "AND p.maeTipoDocumento = " + obj.getMae_tipo_documento_id() + " ";
//            strQuery += "AND p.documento = " + obj.getDocumento() + " ";
//
////            List<AusPersonas> list = getEntityManager().createQuery(strQuery).setParameter("empresaid", obj.getEmpresa().getId())
////                    .getResultList();
////            for (AusPersonas per : list) {
//            AusPersonas per = (AusPersonas) getEntityManager().find(AusPersonas.class, obj);
//            ausPersona = castEntidadNegocio(per);
////            }
//        } catch (NoResultException e) {
//            ausPersona = new AusPersona();
//        } catch (Exception e) {
//            Exception(CONSULTAR_TODOS, e);
//        } finally {
//            cerrarEntityManager();
//        }
//        return ausPersona;
//    }
    @Override
    public AusPersona consultar(int id) throws Exception {
        AusPersona objRes = null;
        try {
            AusPersonas personaNegocio = (AusPersonas) getEntityManager().find(AusPersonas.class, id);
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
    public int insertar(AusPersona obj) throws Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(id);
            List<AusPersonaTelefono> telefonosPersonaBD = consultarTelefonosPersonas(obj.getDocumento());
            List<AusPersonaTelefono> listaTelefonos = new ArrayList();
            if (!telefonosPersonaBD.isEmpty() && telefonosPersonaBD != null){
                for (AusPersonaTelefono objTel : obj.getListaTelefonos()) {
                    Boolean exiteTelefono = obj.existeTelefonoPersona(telefonosPersonaBD, objTel.getNumero());
                    if (!exiteTelefono){
                        objTel.setAusPersona(new AusPersona(id));
                        objTel.setUsuarioCrea(obj.getUsuarioCrea());
                        objTel.setTerminalCrea(obj.getTerminalCrea());
                        objTel.setFechaHoraCrea(obj.getFechaHoraCrea());
                        AusPersonaTelefonos perTel = castNegocioEntidad(objTel);
                        int idDet = (int) getEntityManager().merge(perTel).getId();
                        objTel.setId(idDet);
                        listaTelefonos.add(objTel);
                    }
                }
            }else{
                for (AusPersonaTelefono objTel : obj.getListaTelefonos()) {
                    objTel.setAusPersona(new AusPersona(id));
                    objTel.setUsuarioCrea(obj.getUsuarioCrea());
                    objTel.setTerminalCrea(obj.getTerminalCrea());
                    objTel.setFechaHoraCrea(obj.getFechaHoraCrea());
                    AusPersonaTelefonos perTel = castNegocioEntidad(objTel);
                    int idDet = (int) getEntityManager().merge(perTel).getId();
                    objTel.setId(idDet);
                    listaTelefonos.add(objTel);
                }
            }
            obj.setListaTelefonos(listaTelefonos);
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return id;
    }
    
    @Override
    public int insertarSinValidacionTelefonoExistente(AusPersona obj) throws Exception {
        int id = 0;
        List<AusPersonaTelefono> listaTelefonos = new ArrayList();
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
            obj.setId(id);
            for (AusPersonaTelefono objTel : obj.getListaTelefonos()) {
                objTel.setAusPersona(new AusPersona(id));
                objTel.setUsuarioCrea(obj.getUsuarioCrea());
                objTel.setTerminalCrea(obj.getTerminalCrea());
                objTel.setFechaHoraCrea(obj.getFechaHoraCrea());
                AusPersonaTelefonos perTel = castNegocioEntidad(objTel);
                int idDet = (int) getEntityManager().merge(perTel).getId();
                objTel.setId(idDet);
                listaTelefonos.add(objTel);
            }
            obj.setListaTelefonos(listaTelefonos);
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return id;
    }

    @Override
    public void actualizar(AusPersona obj) throws Exception {
        try {
            String hql = "UPDATE AusPersonas SET"
                    + " maeEstadoId = :maeEstadoId,"
                    + " maeTipoDocumentoId = :maeTipoDocumentoId,"
                    + " maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo,"
                    + " maeTipoDocumentoValor = :maeTipoDocumentoValor,"
                    + " documento = :documento,"
                    + " nombres = :nombres,"
                    + " apellidos = :apellidos,"
                    + " fechaNacimento = :fechaNacimento,"
                    + " maeSexoId = :maeSexoId,"
                    + " maeSexoCodigo = :maeSexoCodigo,"
                    + " maeSexoValor = :maeSexoValor,"
                    + " correoElectronico = :correoElectronico,"
                    + " gnUbicacionesId.id = :ubicacionesId,"
                    + " direccion = :direccion,"
                    + " estratro = :estratro,"
                    + " contrato = :contrato,"
                    + " regimen = :regimen,"
                    + " dicapacidad = :dicapacidad,"
                    + " gestante = :gestante,"
                    + " usuarioModifica = :usuarioModifica,"
                    + " terminalModifica = :terminalModifica,"
                    + " fechaHoraModifica = :fechaHoraModifica"
                    + " WHERE id = :id";
            Query query = getEntityManager().createQuery(hql);
            if(obj.getEsRegimen()!= null){
                int regimen = obj.getEsRegimen() ? AusPersona.REGIMEN_CONTRIBUTIVO : AusPersona.REGIMEN_SUBSIDIADO;
                if(regimen == 0){
                    obj.setRegimen(false);
                }else{
                    obj.setRegimen(true);
                }
                
            }
            query.setParameter("maeEstadoId", obj.getMae_estado_id());
            query.setParameter("maeTipoDocumentoId", obj.getMae_tipo_documento_id());
            query.setParameter("maeTipoDocumentoCodigo", obj.getMae_tipo_documento_codigo());
            query.setParameter("maeTipoDocumentoValor", obj.getMae_tipo_documento_valor());
            query.setParameter("documento", obj.getDocumento());
            query.setParameter("nombres", obj.getNombres());
            query.setParameter("apellidos", obj.getApellidos());
//            query.setParameter("nombreCompleto", obj.getNombreCompleto());
            query.setParameter("fechaNacimento", obj.getFechaNacimiento());
            query.setParameter("maeSexoId", obj.getMae_sexo_id());
            query.setParameter("maeSexoCodigo", obj.getMae_sexo_codigo());
            query.setParameter("maeSexoValor", obj.getMae_sexo_valor());
            query.setParameter("correoElectronico", obj.getCorreoElectronico());
            //query.setParameter("ubicacionesId", obj.getUbicacion().getId());
            query.setParameter("ubicacionesId", obj.getPersonaUbicacion().getId());
            query.setParameter("direccion", obj.getDireccion());
            query.setParameter("estratro", obj.getEstrato());
            query.setParameter("contrato", obj.getContrato());
            query.setParameter("regimen", obj.getRegimen());
            query.setParameter("dicapacidad", obj.getDiscapacidad());
            query.setParameter("gestante", obj.getGestante());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
            int id = obj.getId();
            AusPersona personaBD = consultar(id);
            List<AusPersonaTelefono> listaTelefonos = new ArrayList();
            for (AusPersonaTelefono objTel : obj.getListaTelefonos()) {
                if (objTel.getAccion() == AusPersonaTelefono.ACCION_INSERTAR) {
                    objTel.setAusPersona(new AusPersona(id));
                    objTel.setUsuarioCrea(obj.getUsuarioModifica());
                    objTel.setTerminalCrea(obj.getTerminalModifica());
                    objTel.setFechaHoraCrea(obj.getFechaHoraModifica());
                    if (objTel.getId() == null) {
                        AusPersonaTelefonos perTel = castNegocioEntidad(objTel);
                        boolean validar = true;
                        for(AusPersonaTelefono tel : personaBD.getListaTelefonos()){
                            if(tel.getNumero().equals(perTel.getNumero())){
                                validar = false;
                            }
                        }
                       if(validar){
                            int idTel = (int) getEntityManager().merge(perTel).getId();
                            objTel.setId(idTel);
                        }

                    }
                    listaTelefonos.add(objTel);
                } else if (objTel.getAccion() == AusPersonaTelefono.ACCION_BORRAR) {
                    AusPersonaTelefonos ent = getEntityManager().find(AusPersonaTelefonos.class, objTel.getId());
                    if (ent != null) {
                        try {
                          
                            getEntityManager().remove(ent);
                        } catch (Exception e) {
                            System.out.println("com"+ e);
                        }
                       //getEntityManager().remove(getEntityManager().merge(ent));
                    }
                }
            }
            obj.setListaTelefonos(listaTelefonos);
        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public AusPersona eliminar(int id) throws Exception {
        AusPersona obj = null;
        try {
            AusPersonas ent = getEntityManager().find(AusPersonas.class, id);
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
    public List<AusPersona> consultarTodas(int empresaId) throws Exception {
        List<AusPersona> listResult = new ArrayList();
        try {
            String strQuery = "FROM AusPersonas p "
                    + "WHERE p.empresasId.id = :empresaid "
                    + "ORDER BY p.nombreCompleto ASC";
            List<AusPersonas> list = getEntityManager().createQuery(strQuery)
                    .setParameter("empresaid", empresaId)
                    .getResultList();
            for (AusPersonas per : list) {
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

    @Override
    public List<AusPersona> consultarInternas(int empresaId) throws Exception {
        List<AusPersona> listResult = new ArrayList();
        try {
            String strQuery = "FROM AusPersonas p "
                    + "WHERE p.empresasId.id = :empresaid "
                    + "ORDER BY p.nombreCompleto ASC";
            List<AusPersonas> list = getEntityManager().createQuery(strQuery)
                    .setParameter("empresaid", empresaId)
                    .getResultList();
            for (AusPersonas per : list) {
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
    
    @Override
    public List<AusPersonaTelefono> consultarTelefonosPersonas(String documentoPersona) throws Exception {
        List<AusPersonaTelefono> listResult = new ArrayList();
        try {
            String strQuery = "SELECT DISTINCT pt FROM AusPersonaTelefonos pt "
                    + "INNER JOIN AusPersonas mth ON mth.id = pt.ausPersonasId.id "
                    + "WHERE mth.documento = :documentoPersona ";
                  
            Query query = getEntityManager().createQuery(strQuery);
            query.setParameter("documentoPersona", documentoPersona);
            List<AusPersonaTelefonos> list = query.getResultList();
            int i = 0;
            for (AusPersonaTelefonos per : list) {
                listResult.add(castEntidadNegocio(per, i));
                i++;
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
    public List<AusPersonaTelefono> consultarTelefonosPorAusPersona(Integer idAusPersona) throws Exception {
        List<AusPersonaTelefono> listResult = new ArrayList();
        try {
            if (idAusPersona != null) {
                String strQuery = "SELECT pt FROM AusPersonaTelefonos pt "
                        + "WHERE pt.ausPersonasId.id = :idAusPersona ";

                List<AusPersonaTelefonos> list = getEntityManager().createQuery(strQuery)
                        .setParameter("idAusPersona", idAusPersona)
                        .getResultList();
                int i = 0;
                for (AusPersonaTelefonos per : list) {
                    listResult.add(castEntidadNegocio(per, i));
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
    
      @Override
    public AusPersona agregarPersona(AusPersona ausPersona) throws Exception {
        AusPersona nueva = new AusPersona();
        try {
            String strQuery = "FROM AusPersonas p "
                    + "WHERE p.documento = :documento ";
            List<AusPersonas> list = getEntityManager().createQuery(strQuery)
                    .setParameter("documento", ausPersona.getDocumento())
                    .getResultList();
            if (list.size() <= 0) {
                int id = (int) getEntityManager().merge(castNegocioEntidad(ausPersona)).getId();
                nueva = ausPersona;
                nueva.setId(id);
            } else {
                nueva = castEntidadNegocio(list.get(0));
            }
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return nueva;
    }
    
    
    @Override
    public List<AusPersona> consultarUltimosRegistroPersonaLista(ParamConsulta paramConsulta) throws Exception {
        List<AusPersona> listResult = new ArrayList();
        try {
            if (paramConsulta.getParametroConsulta1() != null) {

                String strQuery = "SELECT  Max( p.id) as id FROM aus_personas p  "
                        + "WHERE 1 = 1 ";

                if (paramConsulta.getParametroConsulta1() != null) {
                    strQuery += " and  p.documento IN (" + paramConsulta.getParametroConsulta1() + ") GROUP BY  p.documento  ";
                }

                List<Object[]> listAfiliados =   getEntityManager().createNativeQuery(strQuery).getResultList();

                if (listAfiliados != null) {
                    for (Object idAfiliado : listAfiliados) {
                      AusPersona afiliadoIn = new AusPersona((Integer) idAfiliado);
                      listResult.add(afiliadoIn);
                    }
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
    
    @Override
    public List<AusPersona> consultarPersonaConTelefonosLista(ParamConsulta paramConsulta) throws Exception {
        List<AusPersona> listResult = new ArrayList();
        try {
            if (paramConsulta.getParametroConsulta1() != null) {

                String strQuery = " SELECT GROUP_CONCAT( CONCAT_WS('&&', pt.numero, pt.observacion) SEPARATOR '#'),"
                                + " pt.aus_personas_id, p.documento, p.mae_tipo_documento_codigo " 
                                + " FROM aus_persona_telefonos pt  " 
                                + " INNER JOIN aus_personas p on p.ID = pt.aus_personas_id "
                                + " WHERE 1 = 1 ";

                if (paramConsulta.getParametroConsulta1() != null) {
                    strQuery += "  AND p.id IN (" + paramConsulta.getParametroConsulta1() + ") GROUP BY aus_personas_id  ";
                }

                List<Object[]> listAfiliados =   getEntityManager().createNativeQuery(strQuery).getResultList();

                if (listAfiliados != null) {
                    
                     for (Object[] per : listAfiliados) {
                         List<AusPersonaTelefono> listaTelefonos = new ArrayList<>();
                         AusPersona afiliadoIn = new AusPersona((Integer) per[1]);
                         String telefonos = (String) per[0];
                         telefonos = Optional.ofNullable(telefonos).orElse("");
                                 
                         String documento = (String) per[2];
                         String tipodocumento = (String) per[3];
                         
                         afiliadoIn.setMae_tipo_documento_codigo(tipodocumento);
                         afiliadoIn.setDocumento(documento);
                        
                         String telefonosList [] = telefonos.split("#");
                         for (String telefono : telefonosList) {
                             AusPersonaTelefono telef = new AusPersonaTelefono();
                             String telefonoDescripcon[]  = telefono.split("&&");
                             telef.setNumero(telefonoDescripcon[0]);
                             telef.setObservacion(telefonoDescripcon[1]);                             
                             listaTelefonos.add(telef);
                         }
                         afiliadoIn.setListaTelefonos(listaTelefonos);
                         listResult.add(afiliadoIn);
                     }
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
    
    public static AusPersona castEntidadNegocio(AusPersonas per) {
        AusPersona obj = new AusPersona();
        obj.setId(per.getId());
//        obj.setAseg_afiliados_id(per.getAsegAfiliadosId());
//        obj.setGn_ubicaciones_id(per.getGnUbicacionesId());
        obj.setMae_estado_id(per.getMaeEstadoId());
        obj.setMae_estado_codigo(per.getMaeEstadoCodigo());
        obj.setMae_estado_valor(per.getMaeEstadoValor());
        obj.setMaeTipoParentescoId( Optional.ofNullable(per.getMaeTipoParentescoId()).orElse(0) );
        obj.setMaeTipoParentescoCodigo(per.getMaeTipoParentescoCodigo());
        obj.setMaeTipoParentescoValor(per.getMaeTipoParentescoValor());
        obj.setMae_tipo_documento_id(per.getMaeTipoDocumentoId());
        obj.setMae_tipo_documento_codigo(per.getMaeTipoDocumentoCodigo());
        obj.setMae_tipo_documento_valor(per.getMaeTipoDocumentoValor());
        obj.setDocumento(per.getDocumento());
        obj.setNombres(per.getNombres());
        obj.setApellidos(per.getApellidos());
        obj.setFechaNacimiento(per.getFechaNacimento());
        obj.setMae_sexo_id(per.getMaeSexoId());
        obj.setMae_sexo_codigo(per.getMaeSexoCodigo());
        obj.setMae_sexo_valor(per.getMaeSexoValor());
        String correo = per.getCorreoElectronico() == null || per.getCorreoElectronico().equalsIgnoreCase("NULL") ? "" : per.getCorreoElectronico();
        correo = correo.trim();
        obj.setCorreoElectronico(correo);
        obj.setPersonaUbicacion(new Ubicacion(per.getGnUbicacionesId().getId()));
        obj.setDireccion(per.getDireccion());
//        obj.setEstrato(per.getEstratro());
        obj.setContrato(per.getContrato());
        if (per.getRegimen() != null) {
            //obj.setEsRegimen((per.getRegimen() == AusPersona.REGIMEN_CONTRIBUTIVO));
            obj.setEsRegimen((per.getRegimen() == false));
        }
        //obj.setRegimen(per.getRegimen());
        obj.setDiscapacidad(false);
        obj.setGestante(per.getGestante());
        obj.setDiscapacidad(per.getDicapacidad());
        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        
        /*List<AusPersonaTelefono> listaTelefonos = new ArrayList();
        int postTel = 0;
        for(AusPersonaTelefonos perDer: per.getAusPersonaTelefonosList()){
            AusPersonaTelefono personaTel = castEntidadNegocio(perDer);
            personaTel.setPos(postTel);
            listaTelefonos.add(personaTel);
            postTel++;
        }
        obj.setListaTelefonos(listaTelefonos);*/

        return obj;
    }
    
    public static AusPersonas castNegocioEntidad(AusPersona obj) {
        AusPersonas per = new AusPersonas();
        per.setId(obj.getId());
        if(obj.getAseg_afiliados_id() > 0){
            per.setAsegAfiliadosId(new AsegAfiliados(obj.getAseg_afiliados_id()));
        }
//        per.setAsegAfiliadosId(obj.getContrato());
        if (obj.getPersonaUbicacion() != null && obj.getPersonaUbicacion().getId() != null) {
            per.setGnUbicacionesId(new GnUbicaciones(obj.getPersonaUbicacion().getId()));
        }
        per.setMaeEstadoId(obj.getMae_estado_id());
        per.setMaeEstadoCodigo(obj.getMae_estado_codigo());
        per.setMaeEstadoValor(obj.getMae_estado_valor());
        per.setMaeTipoParentescoId(obj.getMaeTipoParentescoId());
        per.setMaeTipoParentescoCodigo(obj.getMaeTipoParentescoCodigo());
        per.setMaeTipoParentescoValor(obj.getMaeTipoParentescoValor());
        per.setMaeTipoDocumentoId(obj.getMae_tipo_documento_id());
        per.setMaeTipoDocumentoCodigo(obj.getMae_tipo_documento_codigo());
        per.setMaeTipoDocumentoValor(obj.getMae_tipo_documento_valor());
        per.setDocumento(obj.getDocumento());
        per.setNombres(obj.getNombres());
        if(obj.getApellidos() != null){
            per.setApellidos(obj.getApellidos().replace("Ń", "Ñ"));
        }
        per.setFechaNacimento(obj.getFechaNacimiento());
        per.setMaeSexoId(obj.getMae_sexo_id());
        per.setMaeSexoCodigo(obj.getMae_sexo_codigo());
        per.setMaeSexoValor(obj.getMae_sexo_valor());
        per.setCorreoElectronico(obj.getCorreoElectronico());
        per.setDireccion(obj.getDireccion());
        per.setEstratro(1);
        Boolean estrasto = obj.getEstrato() == null;
        per.setContrato(obj.getContrato());
        int regimen = obj.getEsRegimen() == null ? AusPersona.REGIMEN_CONTRIBUTIVO : AusPersona.REGIMEN_SUBSIDIADO;
        if(regimen == 0){
            per.setRegimen(true);
        }else{
            per.setRegimen(false);
        }
        //per.setRegimen(regimen);
        per.setDicapacidad(obj.isDiscapacidad());
        per.setGestante(obj.isGestante());
// 
//        if (obj.getUbicacion() != null) {
//            per.setUbicacionesId(new Ubicaciones(obj.getUbicacion().getId()));
//        }        
        //Auditoria
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        return per;
    }

    public static AusPersonaTelefonos castNegocioEntidad(AusPersonaTelefono obj) {
        AusPersonaTelefonos perTelefonos = new AusPersonaTelefonos();
        perTelefonos.setAusPersonasId(new AusPersonas(obj.getAusPersona().getId()));
        if (obj.getId() != null){
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
    
    public static AusPersonaTelefono castEntidadNegocio(AusPersonaTelefonos obj, int pos) {
        AusPersonaTelefono perTelefono = new AusPersonaTelefono();
        perTelefono.setId(obj.getId());
        if(obj.getAusPersonasId() != null){
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
  

}
