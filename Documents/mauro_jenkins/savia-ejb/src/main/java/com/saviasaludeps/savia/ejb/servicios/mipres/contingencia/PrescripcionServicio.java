/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.mipres.contingencia;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.mipres.contingencia.MpcPrescripcion;
import com.saviasaludeps.savia.dominio.mipres.contingencia.MpcPrescripcionHistorico;
import com.saviasaludeps.savia.ejb.entidades.AsegAfiliados;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.GnEmpresas;
import com.saviasaludeps.savia.ejb.entidades.MpcPrescripciones;
import com.saviasaludeps.savia.ejb.entidades.MpcPrescripcionesHistoricos;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.mipres.contingencia.PrescripcionRemoto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author raul-palacios
 */
@Stateless
@Remote(PrescripcionRemoto.class)
public class PrescripcionServicio extends GenericoServicio implements PrescripcionRemoto {
    
    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(p) "
                    + "FROM MpcPrescripciones p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getEmpresaId() != null) {
                strQuery += "AND p.gnEmpresasId.id = :empresa_id ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + (String) e.getValue() + " ";
                            break;
                        case "estado":
                            strQuery += "AND p.estado = " + (String) e.getValue() + " ";
                            break;
                        case "tipo":
                            strQuery += "AND p.tipoTecnologia = " + (String) e.getValue() + " ";
                            break;
                        case "cntPrescriptorPrestadorSede.cntPrestador.numeroDocumento":
                            strQuery += " AND p.cntPrescriptorPrestadorSedesId.cntPrestadoresId.numeroDocumento LIKE '%" + (String) e.getValue() + "' ";
                            break;
                        case "consecutivo":
                            strQuery += " AND p.consecutivo = '" + (String) e.getValue() + "' ";
                            break;
                    }
                }
            }
            Query query = getEntityManager().createQuery(strQuery);
            if (paramConsulta.getEmpresaId() != null) {
                query.setParameter("empresa_id", paramConsulta.getEmpresaId());
            }
            cant = (int) (long) query.getSingleResult();
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
    public List<MpcPrescripcion> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<MpcPrescripcion> listResult = new ArrayList();
        try {
            String strQuery = "SELECT p "
                    + "FROM MpcPrescripciones p "
                    + "WHERE p.id > 0 ";
            if (paramConsulta.getEmpresaId() != null) {
                strQuery += "AND p.gnEmpresasId.id = :empresa_id ";
            }
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "id":
                            strQuery += "AND p.id = " + (String) e.getValue() + " ";
                            break;
                        case "estado":
                            strQuery += "AND p.estado = " + (String) e.getValue() + " ";
                            break;
                        case "tipoTecnologia":
                            strQuery += "AND p.tipoTecnologia = " + (String) e.getValue() + " ";
                            break;
                        case "consecutivo":
                            strQuery += " AND p.consecutivo = '" + (String) e.getValue() + "' ";
                            break;
                        case "cntPrescriptorPrestadorSede.cntPrestador.numeroDocumento":
                            strQuery += " AND p.cntPrescriptorPrestadorSedesId.cntPrestadoresId.numeroDocumento LIKE '%" + (String) e.getValue() + "' ";
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
            Query query = getEntityManager().createQuery(strQuery);
            if (paramConsulta.getEmpresaId() != null) {
                query.setParameter("empresa_id", paramConsulta.getEmpresaId());
            }
            List<MpcPrescripciones> list = query
                    .setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (MpcPrescripciones per : list) {
                listResult.add(castEntidadNegocioSimple(per));
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
    public MpcPrescripcion consultar(int id) throws Exception {
        MpcPrescripcion objRes = null;
        try {
            objRes = castEntidadNegocio((MpcPrescripciones) getEntityManager().find(MpcPrescripciones.class, id));
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
    public MpcPrescripcionHistorico consultarH(int id) throws Exception {
        MpcPrescripcionHistorico objRes = null;
        try {
            MpcPrescripciones prescripcion = (MpcPrescripciones) getEntityManager().find(MpcPrescripciones.class, id);
            if (prescripcion != null) {
                objRes = castEntidadNegocioH(prescripcion);
            }
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
    public boolean consultarHis(int id) throws Exception {
        boolean objRes = false;
        
        try {
            MpcPrescripcionesHistoricos prescripcion = (MpcPrescripcionesHistoricos) getEntityManager().find(MpcPrescripcionesHistoricos.class, id);
            
            if (prescripcion != null) {
                objRes = true;
            }
        } catch (NoResultException e) {
            objRes = false;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        
        return objRes;
    }
    
    @Override
    public MpcPrescripcionHistorico consultarHistorico(int id) throws Exception {
        MpcPrescripcionHistorico objRes = null;
        try {
            MpcPrescripcionesHistoricos prescripcion = (MpcPrescripcionesHistoricos) getEntityManager().find(MpcPrescripcionesHistoricos.class, id);
            if (prescripcion != null) {
                objRes = castEntidadNegocioHistorico(prescripcion);
            }
        } catch (NoResultException e) {
            objRes = null;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
            e.printStackTrace();
        } finally {
            cerrarEntityManager();
        }
        return objRes;
    }
    
    @Override
    public int insertar(MpcPrescripcion obj) throws Exception {
        int id = 0;
        try {
            id = (int) getEntityManager().merge(castNegocioEntidad(obj)).getId();
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
    public void actualizar(MpcPrescripcion obj) throws Exception {
        try {
            String hql = "UPDATE MpcPrescripciones SET "
                    //                    + "estado = :estado, "
                    //                    + "mae_prescriptor_tipo_documento_id = :mae_prescriptor_tipo_documento_id, "
                    //                    + "mae_prescriptor_tipo_documento_codigo = :mae_prescriptor_tipo_documento_codigo, "
                    //                    + "mae_prescriptor_tipo_documento_valor = :mae_prescriptor_tipo_documento_valor, "
                    //                    + "prescriptor_numero_documento = :prescriptor_numero_documento, "
                    //                    + "prescriptor_codigo_habilitacion = :prescriptor_codigo_habilitacion, "
                    + "prescriptor_correo_electronico = :prescriptor_correo_electronico, "
                    //                    + "cnt_prescriptor_prestador_sedes_id = :cnt_prescriptor_prestador_sedes_id, "
                    //                    + "aseg_afiliados_id = :aseg_afiliados_id, "
                    //                    + "mae_afiliado_tipo_documento_id = :mae_afiliado_tipo_documento_id, "
                    //                    + "mae_afiliado_tipo_documento_codigo = :mae_afiliado_tipo_documento_codigo, "
                    //                    + "mae_afiliado_tipo_documento_valor = :mae_afiliado_tipo_documento_valor, "
                    //                    + "afiliado_numero_documento = :afiliado_numero_documento, "
                    //                    + "afiliado_primer_nombre = :afiliado_primer_nombre, "
                    //                    + "afiliado_segundo_nombre = :afiliado_segundo_nombre, "
                    //                    + "afiliado_primer_apellido = :afiliado_primer_apellido, "
                    //                    + "afiliado_segundo_apellido = :afiliado_segundo_apellido, "
                    //                    + "fecha_hora = :fecha_hora, "
                    + "recobrante = :recobrante, "
                    + "tipo_tecnologia = :tipo_tecnologia, "
                    //                    + "acta_juanta_profesionales = :acta_juanta_profesionales, "
                    //                    + "concentimiento_informado = :concentimiento_informado, "
                    //                    + "formato_integralidad = :formato_integralidad, "
                    //                    + "rechazo_justificacion = :rechazo_justificacion, "
                    //                    + "consecutivo = :consecutivo, "
                    //                    + "ma_tecnologia_id = :ma_tecnologia_id, "
                    //                    + "ma_tecnologia_codigo = :ma_tecnologia_codigo, "
                    //                    + "ma_tecnologia_valor = :ma_tecnologia_valor, "
                    //                    + "cantidad = :cantidad, "
                    //                    + "periodicidad = :periodicidad, "
                    + "referenciaAmbitoAtencion = :referenciaAmbitoAtencion, "
                    + "ambito = :ambito, ";
//                    + "numero_entregas = :numero_entregas, ";
            hql += "usuario_modifica = :usuario_modifica, "
                    + "terminal_modifica = :terminal_modifica, "
                    + "fecha_hora_modifica = :fecha_hora_modifica "
                    + "WHERE id = :id ";
            Query query = getEntityManager().createQuery(hql);
            //Variables
//            query.setParameter("estado", obj.getEstado());
//            query.setParameter("mae_prescriptor_tipo_documento_id", obj.getMaePrescriptorTipoDocumentoId());
//            query.setParameter("mae_prescriptor_tipo_documento_codigo", obj.getMaePrescriptorTipoDocumentoCodigo());
//            query.setParameter("mae_prescriptor_tipo_documento_valor", obj.getMaePrescriptorTipoDocumentoValor());
//            query.setParameter("prescriptor_numero_documento", obj.getPrescriptorNumeroDocumento());
//            query.setParameter("prescriptor_codigo_habilitacion", obj.getPrescriptorCodigoHabilitacion());
            query.setParameter("prescriptor_correo_electronico", obj.getPrescriptorCorreoElectronico());
//            query.setParameter("cnt_prescriptor_prestador_sedes_id", obj.getCntPrescriptorPrestadorSede().getId());
//            query.setParameter("aseg_afiliados_id", obj.getAsegAfiliado().getId());
//            query.setParameter("mae_afiliado_tipo_documento_id", obj.getMaeAfiliadoTipoDocumentoId());
//            query.setParameter("mae_afiliado_tipo_documento_codigo", obj.getMaeAfiliadoTipoDocumentoCodigo());
//            query.setParameter("mae_afiliado_tipo_documento_valor", obj.getMaeAfiliadoTipoDocumentoValor());
//            query.setParameter("afiliado_numero_documento", obj.getAfiliadoNumeroDocumento());
//            query.setParameter("afiliado_primer_nombre", obj.getAfiliadoPrimerNombre());
//            query.setParameter("afiliado_segundo_nombre", obj.getAfiliadoSegundoNombre());
//            query.setParameter("afiliado_primer_apellido", obj.getAfiliadoPrimerApellido());
//            query.setParameter("afiliado_segundo_apellido", obj.getAfiliadoSegundoApellido());
//            query.setParameter("fecha_hora", obj.getFechaHora());
            query.setParameter("recobrante", obj.isRecobrante());
            query.setParameter("tipo_tecnologia", obj.getTipoTecnologia());
//            query.setParameter("acta_juanta_profesionales", obj.isActaJuantaProfesionales());
//            query.setParameter("concentimiento_informado", obj.isConcentimientoInformado());
//            query.setParameter("formato_integralidad", obj.isFormatoIntegralidad());
//            query.setParameter("rechazo_justificacion", obj.getRechazoJustificacion());
//            query.setParameter("consecutivo", obj.getConsecutivo());
//            query.setParameter("ma_tecnologia_id", obj.getMaTecnologiaId());
//            query.setParameter("ma_tecnologia_codigo", obj.getMaTecnologiaCodigo());
//            query.setParameter("ma_tecnologia_valor", obj.getMaTecnologiaValor());
//            query.setParameter("cantidad", obj.getCantidad());
//            query.setParameter("periodicidad", obj.getPeriodicidad());
            query.setParameter("referenciaAmbitoAtencion", obj.getReferenciaAmbitoAtencion());
            query.setParameter("ambito", obj.getAmbito());
//            query.setParameter("numero_entregas", obj.getNumeroEntregas());

            //Auditoria Modifica
            query.setParameter("usuario_modifica", obj.getUsuarioModifica());
            query.setParameter("terminal_modifica", obj.getTerminalModifica());
            query.setParameter("fecha_hora_modifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {
            
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public void direccionar(MpcPrescripcion obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE MpcPrescripciones SET "
                    + "estado = :estado, "
                    + "consecutivo = :consecutivo, "
                    + "cantidad = :cantidad, "
                    + "periodicidad = :periodicidad, "
                    + "maTecnologiaId = :maTecnologiaId, "
                    + "maTecnologiaCodigo = :maTecnologiaCodigo, "
                    + "maTecnologiaValor = :maTecnologiaValor, "
                    + "numeroEntregas = :numeroEntregas, "
                    + "rechazoJustificacion = :rechazoJustificacion, ";
            if (obj.getCntDireccionadoPrestadorSede() != null) {
                strQuery += "cntDireccionadoPrestadorSedesId.id = :cntDireccionado, ";
            }
            strQuery += "usuarioModifica = :usuarioModifica, "
                    + "terminalModifica = :terminalModifica, "
                    + "fechaHoraModifica = :fechaHoraModifica "
                    + "WHERE id = :id ";
            Query query = session.createQuery(strQuery);
            //Variables
            query.setParameter("estado", obj.getEstado());
            query.setParameter("consecutivo", obj.getConsecutivo());
            query.setParameter("cantidad", obj.getCantidad());
            query.setParameter("periodicidad", obj.getPeriodicidad());
            query.setParameter("maTecnologiaId", obj.getMaTecnologiaId());
            query.setParameter("maTecnologiaCodigo", obj.getMaTecnologiaCodigo());
            query.setParameter("maTecnologiaValor", obj.getMaTecnologiaValor());
            query.setParameter("numeroEntregas", obj.getNumeroEntregas());
            query.setParameter("rechazoJustificacion", obj.getRechazoJustificacion());
            if (obj.getCntDireccionadoPrestadorSede() != null) {
                query.setParameter("cntDireccionado", obj.getCntDireccionadoPrestadorSede().getId());
            }
            //Auditoria Modifica
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("id", obj.getId());
            query.executeUpdate();
        } catch (NoResultException e) {
            
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
    }
    
    @Override
    public MpcPrescripcion eliminar(int id) throws Exception {
        MpcPrescripcion obj = null;
        try {
            MpcPrescripciones ent = getEntityManager().find(MpcPrescripciones.class, id);
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
    
    public static MpcPrescripcion castEntidadNegocioSimple(MpcPrescripciones per) {
        MpcPrescripcion obj = new MpcPrescripcion();
        if (per.getGnEmpresasId() != null) {
            obj.setEmpresa(new Empresa(per.getGnEmpresasId().getId()));
        }
        obj.setId(per.getId());
        //Prescriptor
        obj.setMaePrescriptorTipoDocumentoId(per.getMaePrescriptorTipoDocumentoId());
        obj.setMaePrescriptorTipoDocumentoCodigo(per.getMaePrescriptorTipoDocumentoCodigo());
        obj.setMaePrescriptorTipoDocumentoValor(per.getMaePrescriptorTipoDocumentoValor());
        obj.setPrescriptorNumeroDocumento(per.getPrescriptorNumeroDocumento());
        obj.setPrescriptorCodigoHabilitacion(per.getPrescriptorCodigoHabilitacion());
        obj.setPrescriptorCorreoElectronico(per.getPrescriptorCorreoElectronico());
        CntPrestador prestador = new CntPrestador(
                per.getCntPrescriptorPrestadorSedesId().getCntPrestadoresId().getId(),
                per.getCntPrescriptorPrestadorSedesId().getCntPrestadoresId().getRazonSocial()
        );
        prestador.setNumeroDocumento(per.getCntPrescriptorPrestadorSedesId().getCntPrestadoresId().getNumeroDocumento());
        CntPrestadorSede sede = new CntPrestadorSede(
                per.getCntPrescriptorPrestadorSedesId().getId(),
                per.getCntPrescriptorPrestadorSedesId().getNombre(),
                prestador
        );
        sede.setCodigoHabilitacionSede(per.getCntPrescriptorPrestadorSedesId().getCodigoHabilitacion());
        obj.setCntPrescriptorPrestadorSede(sede);
        //Afiliado
        obj.setMaeAfiliadoTipoDocumentoId(per.getMaeAfiliadoTipoDocumentoId());
        obj.setMaeAfiliadoTipoDocumentoCodigo(per.getMaeAfiliadoTipoDocumentoCodigo());
        obj.setMaeAfiliadoTipoDocumentoValor(per.getMaeAfiliadoTipoDocumentoValor());
        obj.setAfiliadoNumeroDocumento(per.getAfiliadoNumeroDocumento());
        obj.setAfiliadoPrimerNombre(per.getAfiliadoPrimerNombre());
        obj.setAfiliadoSegundoNombre(per.getAfiliadoSegundoNombre());
        obj.setAfiliadoPrimerApellido(per.getAfiliadoPrimerApellido());
        obj.setAfiliadoSegundoApellido(per.getAfiliadoSegundoApellido());
        obj.setAsegAfiliado(
                new AsegAfiliado(
                        per.getAsegAfiliadosId().getId(),
                        per.getAsegAfiliadosId().getIdAfiliado(),
                        per.getAsegAfiliadosId().getPrimerNombre(),
                        per.getAsegAfiliadosId().getSegundoNombre(),
                        per.getAsegAfiliadosId().getPrimerApellido(),
                        per.getAsegAfiliadosId().getSegundoApellido(),
                        per.getAsegAfiliadosId().getGenero(),
                        per.getAsegAfiliadosId().getMaeTipoDocumentoId(),
                        per.getAsegAfiliadosId().getMaeTipoDocumentoValor(),
                        per.getAsegAfiliadosId().getMaeTipoDocumentoCodigo(),
                        per.getAsegAfiliadosId().getNumeroDocumento(),
                        per.getAsegAfiliadosId().getMaeEstadoAfiliacionId()
                )
        );
//        obj.setAsegAfiliado(new AsegAfiliado(per.getAsegAfiliadosId().getId()));
        //Prescripcion
        obj.setConsecutivo(per.getConsecutivo());
        obj.setEstado(per.getEstado());
        obj.setFechaHora(per.getFechaHora());
        obj.setRecobrante(per.getRecobrante());
        obj.setTipoTecnologia(per.getTipoTecnologia());
        obj.setMaTecnologiaValor(per.getMaTecnologiaValor());
        obj.setActaJuantaProfesionales(per.getActaJuantaProfesionales());
        obj.setConcentimientoInformado(per.getConcentimientoInformado());
        obj.setFormatoIntegralidad(per.getFormatoIntegralidad());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        return obj;
    }
    
    public static MpcPrescripcion castEntidadNegocio(MpcPrescripciones per) {
        MpcPrescripcion obj = new MpcPrescripcion();
        if (per.getGnEmpresasId() != null) {
            obj.setEmpresa(new Empresa(per.getGnEmpresasId().getId()));
        }
        obj.setId(per.getId());
        //Prescriptor
        obj.setMaePrescriptorTipoDocumentoId(per.getMaePrescriptorTipoDocumentoId());
        obj.setMaePrescriptorTipoDocumentoCodigo(per.getMaePrescriptorTipoDocumentoCodigo());
        obj.setMaePrescriptorTipoDocumentoValor(per.getMaePrescriptorTipoDocumentoValor());
        obj.setPrescriptorNumeroDocumento(per.getPrescriptorNumeroDocumento());
        obj.setPrescriptorCodigoHabilitacion(per.getPrescriptorCodigoHabilitacion());
        obj.setPrescriptorCorreoElectronico(per.getPrescriptorCorreoElectronico());
        CntPrestador prestador = new CntPrestador(
                per.getCntPrescriptorPrestadorSedesId().getCntPrestadoresId().getId(),
                per.getCntPrescriptorPrestadorSedesId().getCntPrestadoresId().getRazonSocial()
        );
        prestador.setNumeroDocumento(per.getCntPrescriptorPrestadorSedesId().getCntPrestadoresId().getNumeroDocumento());
        CntPrestadorSede sede = new CntPrestadorSede(
                per.getCntPrescriptorPrestadorSedesId().getId(),
                per.getCntPrescriptorPrestadorSedesId().getNombre(),
                prestador
        );
        sede.setCodigoHabilitacionSede(per.getCntPrescriptorPrestadorSedesId().getCodigoHabilitacion());
        obj.setCntPrescriptorPrestadorSede(sede);
        //Afiliado
        obj.setMaeAfiliadoTipoDocumentoId(per.getMaeAfiliadoTipoDocumentoId());
        obj.setMaeAfiliadoTipoDocumentoCodigo(per.getMaeAfiliadoTipoDocumentoCodigo());
        obj.setMaeAfiliadoTipoDocumentoValor(per.getMaeAfiliadoTipoDocumentoValor());
        obj.setAfiliadoNumeroDocumento(per.getAfiliadoNumeroDocumento());
        obj.setAfiliadoPrimerNombre(per.getAfiliadoPrimerNombre());
        obj.setAfiliadoSegundoNombre(per.getAfiliadoSegundoNombre());
        obj.setAfiliadoPrimerApellido(per.getAfiliadoPrimerApellido());
        obj.setAfiliadoSegundoApellido(per.getAfiliadoSegundoApellido());
        obj.setAsegAfiliado(
                new AsegAfiliado(
                        per.getAsegAfiliadosId().getId(),
                        per.getAsegAfiliadosId().getIdAfiliado(),
                        per.getAsegAfiliadosId().getPrimerNombre(),
                        per.getAsegAfiliadosId().getSegundoNombre(),
                        per.getAsegAfiliadosId().getPrimerApellido(),
                        per.getAsegAfiliadosId().getSegundoApellido(),
                        per.getAsegAfiliadosId().getGenero(),
                        per.getAsegAfiliadosId().getMaeTipoDocumentoId(),
                        per.getAsegAfiliadosId().getMaeTipoDocumentoCodigo(),
                        per.getAsegAfiliadosId().getMaeTipoDocumentoValor(),
                        per.getAsegAfiliadosId().getNumeroDocumento(),
                        per.getAsegAfiliadosId().getMaeEstadoAfiliacionId()
                )
        );
        //Prescripcion
        obj.setEstado(per.getEstado());
        obj.setFechaHora(per.getFechaHora());
        obj.setRecobrante(per.getRecobrante());
        obj.setTipoTecnologia(per.getTipoTecnologia());
        obj.setActaJuantaProfesionales(per.getActaJuantaProfesionales());
        obj.setConcentimientoInformado(per.getConcentimientoInformado());
        obj.setFormatoIntegralidad(per.getFormatoIntegralidad());
        obj.setConsecutivo(per.getConsecutivo());
        obj.setAmbito(per.getAmbito());
        obj.setReferenciaAmbitoAtencion(per.getReferenciaAmbitoAtencion() != null ? per.getReferenciaAmbitoAtencion() : false);
        obj.setMaTecnologiaId(per.getMaTecnologiaId());
        obj.setMaTecnologiaCodigo(per.getMaTecnologiaCodigo());
        obj.setMaTecnologiaValor(per.getMaTecnologiaValor());
        obj.setNumeroEntregas(per.getNumeroEntregas());
        obj.setCantidad(per.getCantidad());
        obj.setPeriodicidad(per.getPeriodicidad());
        if (per.getCntDireccionadoPrestadorSedesId() != null
                && per.getCntDireccionadoPrestadorSedesId().getCntPrestadoresId() != null) {
            CntPrestador prestador2 = new CntPrestador(
                    per.getCntDireccionadoPrestadorSedesId().getCntPrestadoresId().getId(),
                    per.getCntDireccionadoPrestadorSedesId().getCntPrestadoresId().getRazonSocial()
            );
            prestador2.setNumeroDocumento(per.getCntDireccionadoPrestadorSedesId().getCntPrestadoresId().getNumeroDocumento());
            CntPrestadorSede sede2 = new CntPrestadorSede(
                    per.getCntDireccionadoPrestadorSedesId().getId(),
                    per.getCntDireccionadoPrestadorSedesId().getNombre(),
                    prestador
            );
            obj.setCntDireccionadoPrestadorSede(sede2);
        }
        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        return obj;
    }
    
    public static MpcPrescripcionHistorico castEntidadNegocioH(MpcPrescripciones per) {
        MpcPrescripcionHistorico obj = new MpcPrescripcionHistorico();
        if (per.getGnEmpresasId() != null) {
            obj.setEmpresa(new Empresa(per.getGnEmpresasId().getId()));
        }
        obj.setId(per.getId());
        //Prescriptor
        obj.setMaePrescriptorTipoDocumentoId(per.getMaePrescriptorTipoDocumentoId());
        obj.setMaePrescriptorTipoDocumentoCodigo(per.getMaePrescriptorTipoDocumentoCodigo());
        obj.setMaePrescriptorTipoDocumentoValor(per.getMaePrescriptorTipoDocumentoValor());
        obj.setPrescriptorNumeroDocumento(per.getPrescriptorNumeroDocumento());
        obj.setPrescriptorCodigoHabilitacion(per.getPrescriptorCodigoHabilitacion());
        obj.setPrescriptorCorreoElectronico(per.getPrescriptorCorreoElectronico());
        CntPrestador prestador = new CntPrestador(
                per.getCntPrescriptorPrestadorSedesId().getCntPrestadoresId().getId(),
                per.getCntPrescriptorPrestadorSedesId().getCntPrestadoresId().getRazonSocial()
        );
        prestador.setNumeroDocumento(per.getCntPrescriptorPrestadorSedesId().getCntPrestadoresId().getNumeroDocumento());
        CntPrestadorSede sede = new CntPrestadorSede(
                per.getCntPrescriptorPrestadorSedesId().getId(),
                per.getCntPrescriptorPrestadorSedesId().getNombre(),
                prestador
        );
        sede.setCodigoHabilitacionSede(per.getCntPrescriptorPrestadorSedesId().getCodigoHabilitacion());
        obj.setCntPrescriptorPrestadorSede(sede);
        //Afiliado
        obj.setMaeAfiliadoTipoDocumentoId(per.getMaeAfiliadoTipoDocumentoId());
        obj.setMaeAfiliadoTipoDocumentoCodigo(per.getMaeAfiliadoTipoDocumentoCodigo());
        obj.setMaeAfiliadoTipoDocumentoValor(per.getMaeAfiliadoTipoDocumentoValor());
        obj.setAfiliadoNumeroDocumento(per.getAfiliadoNumeroDocumento());
        obj.setAfiliadoPrimerNombre(per.getAfiliadoPrimerNombre());
        obj.setAfiliadoSegundoNombre(per.getAfiliadoSegundoNombre());
        obj.setAfiliadoPrimerApellido(per.getAfiliadoPrimerApellido());
        obj.setAfiliadoSegundoApellido(per.getAfiliadoSegundoApellido());
        obj.setAsegAfiliado(
                new AsegAfiliado(
                        per.getAsegAfiliadosId().getId(),
                        per.getAsegAfiliadosId().getIdAfiliado(),
                        per.getAsegAfiliadosId().getPrimerNombre(),
                        per.getAsegAfiliadosId().getSegundoNombre(),
                        per.getAsegAfiliadosId().getPrimerApellido(),
                        per.getAsegAfiliadosId().getSegundoApellido(),
                        per.getAsegAfiliadosId().getGenero(),
                        per.getAsegAfiliadosId().getMaeTipoDocumentoId(),
                        per.getAsegAfiliadosId().getNumeroDocumento(),
                        per.getAsegAfiliadosId().getMaeEstadoAfiliacionId()
                )
        );
        //Prescripcion
        obj.setEstado(per.getEstado());
        obj.setFechaHora(per.getFechaHora());
        obj.setRecobrante(per.getRecobrante());
        obj.setTipoTecnologia(per.getTipoTecnologia());
        obj.setActaJuantaProfesionales(per.getActaJuantaProfesionales());
        obj.setConcentimientoInformado(per.getConcentimientoInformado());
        obj.setFormatoIntegralidad(per.getFormatoIntegralidad());
        obj.setConsecutivo(per.getConsecutivo());
        obj.setAmbito(per.getAmbito());
        obj.setReferenciaAmbitoAtencion(per.getReferenciaAmbitoAtencion() != null ? per.getReferenciaAmbitoAtencion() : false);
        obj.setMaTecnologiaId(per.getMaTecnologiaId());
        obj.setMaTecnologiaCodigo(per.getMaTecnologiaCodigo());
        obj.setMaTecnologiaValor(per.getMaTecnologiaValor());
        obj.setNumeroEntregas(per.getNumeroEntregas());
        obj.setCantidad(per.getCantidad());
        obj.setPeriodicidad(per.getPeriodicidad());
        if (per.getCntDireccionadoPrestadorSedesId() != null
                && per.getCntDireccionadoPrestadorSedesId().getCntPrestadoresId() != null) {
            CntPrestador prestador2 = new CntPrestador(
                    per.getCntDireccionadoPrestadorSedesId().getCntPrestadoresId().getId(),
                    per.getCntDireccionadoPrestadorSedesId().getCntPrestadoresId().getRazonSocial()
            );
            prestador2.setNumeroDocumento(per.getCntDireccionadoPrestadorSedesId().getCntPrestadoresId().getNumeroDocumento());
            CntPrestadorSede sede2 = new CntPrestadorSede(
                    per.getCntDireccionadoPrestadorSedesId().getId(),
                    per.getCntDireccionadoPrestadorSedesId().getNombre(),
                    prestador
            );
            obj.setCntDireccionadoPrestadorSede(sede2);
        }
        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        return obj;
    }
    
    public static MpcPrescripcionHistorico castEntidadNegocioHistorico(MpcPrescripcionesHistoricos per) {
        MpcPrescripcionHistorico obj = new MpcPrescripcionHistorico();
        obj.setId(per.getId());
        //Prescriptor
        obj.setRecobrante(per.getRecobrante());
        obj.setPrescriptorCorreoElectronico(per.getPrescriptorCorreoElectronico());
        //Prescripcion
        obj.setEstado((short) per.getEstado());
        obj.setTipoTecnologia(per.getTipoTecnologia().shortValue());
        obj.setAmbito(per.getAmbito());
        obj.setReferenciaAmbitoAtencion(per.getReferenciaAmbitoAtencion() != null ? per.getReferenciaAmbitoAtencion() : false);

        //Auditoría
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        return obj;
    }
    
    public static String validarAmbito(String ambito) {
        String valor = "";
        if (ambito != null) {
            switch (ambito) {
                case "11":
                    valor = "Ambulatorio - Priorizado";
                    break;
                case "12":
                    valor = "Ambulatorio - No Priorizado";
                    break;
                case "21":
                    valor = "Hospitalario - Domiciliario";
                    break;
                case "22":
                    valor = "Hospitalario - Internacion";
                    break;
                case "30":
                    valor = "Urgencias";
                    break;
                default:
                    valor = "-- -- --";
            }
        } else {
            valor = "-- -- --";
        }
        return valor;
    }
    
    public static MpcPrescripciones castNegocioEntidad(MpcPrescripcion obj) {
        MpcPrescripciones per = new MpcPrescripciones();
        if (obj.getEmpresa() != null) {
            per.setGnEmpresasId(new GnEmpresas(obj.getEmpresa().getId()));
        }
        per.setId(obj.getId());
        //Prescriptor
        per.setMaePrescriptorTipoDocumentoId(obj.getMaePrescriptorTipoDocumentoId());
        per.setMaePrescriptorTipoDocumentoCodigo(obj.getMaePrescriptorTipoDocumentoCodigo());
        per.setMaePrescriptorTipoDocumentoValor(obj.getMaePrescriptorTipoDocumentoValor());
        per.setPrescriptorNumeroDocumento(obj.getPrescriptorNumeroDocumento());
        per.setPrescriptorCodigoHabilitacion(obj.getPrescriptorCodigoHabilitacion());
        per.setPrescriptorCorreoElectronico(obj.getPrescriptorCorreoElectronico());
        per.setCntPrescriptorPrestadorSedesId(new CntPrestadorSedes(obj.getCntPrescriptorPrestadorSede().getId()));
        //Afiliado
        per.setMaeAfiliadoTipoDocumentoId(obj.getMaeAfiliadoTipoDocumentoId());
        per.setMaeAfiliadoTipoDocumentoCodigo(obj.getMaeAfiliadoTipoDocumentoCodigo());
        per.setMaeAfiliadoTipoDocumentoValor(obj.getMaeAfiliadoTipoDocumentoValor());
        per.setAfiliadoNumeroDocumento(obj.getAfiliadoNumeroDocumento());
        per.setAfiliadoPrimerNombre(obj.getAfiliadoPrimerNombre());
        per.setAfiliadoSegundoNombre(obj.getAfiliadoSegundoNombre());
        per.setAfiliadoPrimerApellido(obj.getAfiliadoPrimerApellido());
        per.setAfiliadoSegundoApellido(obj.getAfiliadoSegundoApellido());
        per.setAsegAfiliadosId(new AsegAfiliados(obj.getAsegAfiliado().getId()));
        //Prescripcion
        per.setEstado(obj.getEstado());
        per.setFechaHora(obj.getFechaHora());
        per.setRecobrante(obj.isRecobrante());
        per.setAmbito(obj.getAmbito());
        per.setReferenciaAmbitoAtencion(obj.getReferenciaAmbitoAtencion());
        per.setTipoTecnologia(obj.getTipoTecnologia());
        per.setActaJuantaProfesionales(obj.isActaJuantaProfesionales());
        per.setConcentimientoInformado(obj.isConcentimientoInformado());
        per.setFormatoIntegralidad(obj.isFormatoIntegralidad());
        //Auditoria
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        return per;
    }
    
    @Override
    public void insertarHistorico(MpcPrescripcionHistorico obj) throws Exception {
        int id = obj.getId();
        try {
            obj.setId(id);
            getEntityManager().merge(castNegocioEntidadH(obj));
        } catch (NoResultException e) {
            id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        
    }
    
    public static MpcPrescripcionesHistoricos castNegocioEntidadH(MpcPrescripcionHistorico obj) {
        MpcPrescripcionesHistoricos per = new MpcPrescripcionesHistoricos();
        
        per.setGnEmpresasId(new GnEmpresas(1));
        
        per.setId(obj.getId());
        //Prescriptor
//        per.setMaePrescriptorTipoDocumentoId(obj.getMaePrescriptorTipoDocumentoId());
//        per.setMaePrescriptorTipoDocumentoCodigo(obj.getMaePrescriptorTipoDocumentoCodigo());
//        per.setMaePrescriptorTipoDocumentoValor(obj.getMaePrescriptorTipoDocumentoValor());
//        per.setPrescriptorNumeroDocumento(obj.getPrescriptorNumeroDocumento());
//        per.setPrescriptorCodigoHabilitacion(obj.getPrescriptorCodigoHabilitacion());
        per.setPrescriptorCorreoElectronico(obj.getPrescriptorCorreoElectronico());
        per.setCntPrescriptorPrestadorSedesId(new CntPrestadorSedes(obj.getCntPrescriptorPrestadorSede().getId()));
        //Afiliado
//        per.setMaeAfiliadoTipoDocumentoId(obj.getMaeAfiliadoTipoDocumentoId());
//        per.setMaeAfiliadoTipoDocumentoCodigo(obj.getMaeAfiliadoTipoDocumentoCodigo());
//        per.setMaeAfiliadoTipoDocumentoValor(obj.getMaeAfiliadoTipoDocumentoValor());
//        per.setAfiliadoNumeroDocumento(obj.getAfiliadoNumeroDocumento());
//        per.setAfiliadoPrimerNombre(obj.getAfiliadoPrimerNombre());
//        per.setAfiliadoSegundoNombre(obj.getAfiliadoSegundoNombre());
//        per.setAfiliadoPrimerApellido(obj.getAfiliadoPrimerApellido());
//        per.setAfiliadoSegundoApellido(obj.getAfiliadoSegundoApellido());
        per.setAsegAfiliadosId(new AsegAfiliados(obj.getAsegAfiliado().getId()));
        //Prescripcion
        per.setEstado(obj.getEstado());
//        per.setFechaHora(obj.getFechaHora());
        per.setRecobrante(obj.isRecobrante());
        per.setAmbito(obj.getAmbito());
        per.setReferenciaAmbitoAtencion(obj.getReferenciaAmbitoAtencion());
        per.setTipoTecnologia((int) obj.getTipoTecnologia());
//        per.setActaJuantaProfesionales(obj.isActaJuantaProfesionales());
//        per.setConcentimientoInformado(obj.isConcentimientoInformado());
//        per.setFormatoIntegralidad(obj.isFormatoIntegralidad());
        //Auditoria
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        return per;
    }
}
