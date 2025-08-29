package com.saviasaludeps.savia.ejb.servicios.contratacion;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSedeServicioHabilitacion;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaServicioHabilitacion;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedeServiciosHabilitacion;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.entidades.MaServiciosHabilitacion;
import javax.persistence.Query;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorSedeServicioHabilitacionRemoto;
import java.text.SimpleDateFormat;
import org.hibernate.Session;

@Stateless
@Remote(CntPrestadorSedeServicioHabilitacionRemoto.class)
public class CntPrestadorSedeServicioHabilitacionServicio extends GenericoServicio implements CntPrestadorSedeServicioHabilitacionRemoto {

    @Override
    public int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM CntPrestadorSedeServiciosHabilitacion c "
                    + "WHERE c.id > 0 ";
            //parametro id de la sede
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND c.cntPrestadorSedesId.id = " + (Integer) paramConsulta.getParametroConsulta1() + " ";
            }

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maServiciosHabilitacionCodigo":
                            strQuery += "AND c.maServiciosHabilitacionCodigo LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "maServiciosHabilitacionValor":
                            strQuery += "AND c.maServiciosHabilitacionValor LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "activo":
                            strQuery += "AND c.activo = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            Query query = getEntityManager().createQuery(strQuery);
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
    public List<CntPrestadorSedeServicioHabilitacion> consultarLista(ParamConsulta paramConsulta) throws Exception {
        List<CntPrestadorSedeServicioHabilitacion> listResult = new ArrayList();
        try {
            String strQuery = "SELECT c FROM CntPrestadorSedeServiciosHabilitacion c "
                    + "WHERE c.id > 0 ";
            //parametro id de la sede
            if (paramConsulta.getParametroConsulta1() != null) {
                strQuery += "AND c.cntPrestadorSedesId.id = " + (Integer) paramConsulta.getParametroConsulta1() + " ";
            }

            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "maServiciosHabilitacionCodigo":
                            strQuery += "AND c.maServiciosHabilitacionCodigo LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "maServiciosHabilitacionValor":
                            strQuery += "AND c.maServiciosHabilitacionValor LIKE '%" + (String) e.getValue() + "%' ";
                            break;
                        case "activo":
                            strQuery += "AND c.activo = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            //ordenamiento
            strQuery += " ORDER BY ";
            if (paramConsulta.getOrden() != null) {
                strQuery += "c." + paramConsulta.getOrden() + " "
                        + (paramConsulta.isAscendente() ? "ASC" : "DESC");
            } else {
                strQuery += "c.id DESC";
            }
            Query query = getEntityManager().createQuery(strQuery);
            //getEntityManager().createQuery(strQuery)
            List<CntPrestadorSedeServiciosHabilitacion> list = query.setFirstResult(paramConsulta.getPrimerRegistro())
                    .setMaxResults(paramConsulta.getRegistrosPagina())
                    .getResultList();
            for (CntPrestadorSedeServiciosHabilitacion per : list) {
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

    private CntPrestadorSedeServicioHabilitacion castEntidadNegocio(CntPrestadorSedeServiciosHabilitacion per) {
        CntPrestadorSedeServicioHabilitacion neg = new CntPrestadorSedeServicioHabilitacion();
        neg.setId(per.getId());
        neg.setMaServiciosHabilitacionCodigo(per.getMaServiciosHabilitacionCodigo());
        neg.setMaServiciosHabilitacionValor(per.getMaServiciosHabilitacionValor());
        neg.setActivo(per.getActivo());
        neg.setAplicaUrgencia(per.getAplicaUrgencia());
        neg.setAplicaDomiciliaria(per.getAplicaDomiciliaria());
        neg.setAplicaCirugia(per.getAplicaCirugia());
        neg.setAplicaHospitalizacion(per.getAplicaHospitalizacion());
        neg.setAplicaLaboratorio(per.getAplicaLaboratorio());
        neg.setAplicaImagenes(per.getAplicaImagenes());
        neg.setAplicaOdontologia(per.getAplicaOdontologia());
        neg.setAplicaTransporte(per.getAplicaTransporte());
        neg.setAplicaTrasplante(per.getAplicaTrasplante());
        neg.setAplicaConsulta(per.getAplicaConsulta());
        neg.setAplicaAlternativa(per.getAplicaAlternativa());
        neg.setAplicaOncologia(per.getAplicaOncologia());
        neg.setAplicaTerapia(per.getAplicaTerapia());
        neg.setAplicaProceso(per.getAplicaProceso());
        neg.setAplicaPedt(per.getAplicaPedt());
        neg.setAplicaAmbulatorio(per.getAplicaAmbulatorio());
        neg.setAplicaHospitalario(per.getAplicaHospitalario());
        neg.setAplicaUnidadMovil(per.getAplicaUnidadMovil());
        neg.setAplicaOtrasExtramural(per.getAplicaOtrasExtramural());
        neg.setAplicaCentroReferencia(per.getAplicaCentroReferencia());
        neg.setAplicaInstitucionRemisora(per.getAplicaInstitucionRemisora());
        neg.setAplicaComplejidadBaja(per.getAplicaComplejidadBaja());
        neg.setAplicaComplejidadMedia(per.getAplicaComplejidadMedia());
        neg.setAplicaComplejidadAlta(per.getAplicaComplejidadAlta());
        neg.setFechaApertura(per.getFechaApertura());
        //objetos
        if (per.getCntPrestadorSedesId() != null) {
            neg.setCntPrestadorSede(new CntPrestadorSede(per.getCntPrestadorSedesId().getId()));
        }
        if (per.getMaServiciosHabilitacionId() != null) {
            neg.setMaServicioHabilitacion(new MaServicioHabilitacion(per.getMaServiciosHabilitacionId().getId()));
        }
        //auditoria
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        neg.setUsuarioModifica(per.getUsuarioModifica());
        neg.setTerminalModifica(per.getTerminalModifica());
        neg.setFechaHoraModifica(per.getFechaHoraModifica());
        return neg;
    }

    @Override
    public CntPrestadorSedeServicioHabilitacion consultar(int id) throws Exception {
        CntPrestadorSedeServicioHabilitacion objRes = null;
        try {
            objRes = castEntidadNegocioLargo((CntPrestadorSedeServiciosHabilitacion) getEntityManager().find(CntPrestadorSedeServiciosHabilitacion.class, id));
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
    public int insertar(CntPrestadorSedeServicioHabilitacion obj) throws Exception {
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
    public void actualizar(CntPrestadorSedeServicioHabilitacion obj) throws Exception {
        try {
            CntPrestadorSedeServiciosHabilitacion servHabilitacion = castNegocioEntidad(obj);
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE CntPrestadorSedeServiciosHabilitacion a SET ";
            strQuery += " a.maServiciosHabilitacionCodigo = :maServiciosHabilitacionCodigo ,";
            strQuery += " a.maServiciosHabilitacionValor = :maServiciosHabilitacionValor ,";
            strQuery += " a.activo = :activo ,";
            strQuery += " a.aplicaUrgencia = :aplicaUrgencia ,";
            strQuery += " a.aplicaDomiciliaria = :aplicaDomiciliaria ,";
            strQuery += " a.aplicaCirugia = :aplicaCirugia ,";
            strQuery += " a.aplicaHospitalizacion = :aplicaHospitalizacion ,";
            strQuery += " a.aplicaLaboratorio = :aplicaLaboratorio ,";
            strQuery += " a.aplicaImagenes = :aplicaImagenes ,";
            strQuery += " a.aplicaOdontologia = :aplicaOdontologia ,";
            strQuery += " a.aplicaTransporte = :aplicaTransporte ,";
            strQuery += " a.aplicaTrasplante = :aplicaTrasplante ,";
            strQuery += " a.aplicaConsulta = :aplicaConsulta ,";
            strQuery += " a.aplicaAlternativa = :aplicaAlternativa ,";
            strQuery += " a.aplicaOncologia = :aplicaOncologia ,";
            strQuery += " a.aplicaTerapia = :aplicaTerapia ,";
            strQuery += " a.aplicaProceso = :aplicaProceso ,";
            strQuery += " a.aplicaPedt = :aplicaPedt ,";
            strQuery += " a.aplicaAmbulatorio = :aplicaAmbulatorio ,";
            strQuery += " a.aplicaHospitalario = :aplicaHospitalario ,";
            strQuery += " a.aplicaUnidadMovil = :aplicaUnidadMovil ,";
            strQuery += " a.aplicaOtrasExtramural = :aplicaOtrasExtramural ,";
            strQuery += " a.aplicaCentroReferencia = :aplicaCentroReferencia ,";
            strQuery += " a.aplicaInstitucionRemisora = :aplicaInstitucionRemisora ,";
            strQuery += " a.aplicaComplejidadBaja = :aplicaComplejidadBaja ,";
            strQuery += " a.aplicaComplejidadMedia = :aplicaComplejidadMedia ,";
            strQuery += " a.aplicaComplejidadAlta = :aplicaComplejidadAlta ,";
            //2021-09-08 campos nuevos
            strQuery += " a.aplicaIntramural = :aplicaIntramural ,";
            strQuery += " a.aplicaJornadaSalud = :aplicaJornadaSalud ,";
            strQuery += " a.aplicaTelemedicina = :aplicaTelemedicina ,";
            strQuery += " a.aplicaRefTelemedInteractiva = :aplicaRefTelemedInteractiva ,";
            strQuery += " a.aplicaRefTelemedNoInteractiva = :aplicaRefTelemedNoInteractiva ,";
            strQuery += " a.aplicaReferenciaTeleExperticia = :aplicaReferenciaTeleExperticia ,";
            strQuery += " a.aplicaReferenciaTeleMonitoreo = :aplicaReferenciaTeleMonitoreo ,";
            strQuery += " a.aplicapRemisorTeleExperticia = :aplicapRemisorTeleExperticia ,";
            strQuery += " a.aplicaRemisorTeleMonitoreo = :aplicaRemisorTeleMonitoreo ,";
            strQuery += " a.aplicaSinComplejidad = :aplicaSinComplejidad ,";
            strQuery += " a.aplicaTrasplanteOsteomuscular = :aplicaTrasplanteOsteomuscular ,";
            strQuery += " a.aplicaTrasplantePiel = :aplicaTrasplantePiel ,";
            strQuery += " a.aplicaTrasplanteCardiovascular = :aplicaTrasplanteCardiovascular ,";
            strQuery += " a.aplicaTrasplanteTejidoOcular = :aplicaTrasplanteTejidoOcular ,";
            strQuery += " a.aplicaAtencionPacienteQuemado = :aplicaAtencionPacienteQuemado ,";
            strQuery += " a.aplicaSaludMental = :aplicaSaludMental ,";
            strQuery += " a.aplicaSpa = :aplicaSpa ,";
            strQuery += " a.aplicaOtrasPatologias = :aplicaOtrasPatologias ,";
            strQuery += " a.aplicaTxCelProgeHematopoy = :aplicaTxCelProgeHematopoy ,";
            strQuery += " a.aplicaProcQuirurAmbulatorios = :aplicaProcQuirurAmbulatorios ,";
            strQuery += " a.aplicaOrganoRinon = :aplicaOrganoRinon ,";
            strQuery += " a.aplicaOrganoHigado = :aplicaOrganoHigado ,";
            strQuery += " a.aplicaOrganoPancreas = :aplicaOrganoPancreas ,";
            strQuery += " a.aplicaOrganoIntestino = :aplicaOrganoIntestino ,";
            strQuery += " a.aplicaOrganoMultivisceral = :aplicaOrganoMultivisceral ,";
            strQuery += " a.aplicaOrganoCorazon = :aplicaOrganoCorazon ,";
            strQuery += " a.aplicaOrganoPulmon = :aplicaOrganoPulmon ,";
            strQuery += " a.aplicaSustanciasPsicoactivas = :aplicaSustanciasPsicoactivas ,";
            strQuery += " a.aplicaTrasplanteRenal = :aplicaTrasplanteRenal ,";
            //campo fechas
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            String fechaApertura = null;
            if (obj.getFechaApertura() != null) {
                fechaApertura = formatoFecha.format(obj.getFechaApertura());
                strQuery += " a.fechaApertura = '" + fechaApertura + "', ";
            } else {
                strQuery += " a.fechaApertura = null, ";
            }
            //campos auditoria
            strQuery += " a.usuarioModifica = :usuarioModifica ,";
            strQuery += " a.fechaHoraModifica = :fechaHoraModifica ,";
            strQuery += " a.terminalModifica = :terminalModifica ";
            //campos objetos 
            if (servHabilitacion.getCntPrestadorSedesId() != null) {
                strQuery += ", a.cntPrestadorSedesId.id = " + servHabilitacion.getCntPrestadorSedesId().getId() + " ";
            }
            if (servHabilitacion.getMaServiciosHabilitacionId() != null) {
                strQuery += ", a.maServiciosHabilitacionId.id = " + servHabilitacion.getMaServiciosHabilitacionId().getId() + " ";
            }
            strQuery += " WHERE a.id = :id ";
            org.hibernate.Query query = session.createQuery(strQuery);
            query.setProperties(servHabilitacion);
            query.executeUpdate();

        } catch (NoResultException e) {

        } catch (Exception e) {
            Exception(ACTUALIZAR, e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public CntPrestadorSedeServicioHabilitacion eliminar(int id) throws Exception {
        CntPrestadorSedeServicioHabilitacion obj = null;
        try {
            CntPrestadorSedeServiciosHabilitacion ent = getEntityManager().find(CntPrestadorSedeServiciosHabilitacion.class, id);
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
    public List<CntPrestadorSedeServicioHabilitacion> consultarTodos() throws Exception {
        List<CntPrestadorSedeServicioHabilitacion> listResult = new ArrayList();
        try {
            String strQuery = "FROM CntPrestadorSedeServiciosHabilitacion p "
                    + "ORDER BY p.id ";
            List<CntPrestadorSedeServiciosHabilitacion> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (CntPrestadorSedeServiciosHabilitacion per : list) {
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

    public static CntPrestadorSedeServiciosHabilitacion castNegocioEntidad(CntPrestadorSedeServicioHabilitacion obj) {
        CntPrestadorSedeServiciosHabilitacion per = new CntPrestadorSedeServiciosHabilitacion();
        per.setId(obj.getId());
        per.setId(obj.getId());
        per.setMaServiciosHabilitacionCodigo(obj.getMaServiciosHabilitacionCodigo());
        per.setMaServiciosHabilitacionValor(obj.getMaServiciosHabilitacionValor());
        per.setActivo(obj.isActivo());
        per.setAplicaUrgencia(obj.isAplicaUrgencia());
        per.setAplicaDomiciliaria(obj.isAplicaDomiciliaria());
        per.setAplicaCirugia(obj.isAplicaCirugia());
        per.setAplicaHospitalizacion(obj.isAplicaHospitalizacion());
        per.setAplicaLaboratorio(obj.isAplicaLaboratorio());
        per.setAplicaImagenes(obj.isAplicaImagenes());
        per.setAplicaOdontologia(obj.isAplicaOdontologia());
        per.setAplicaTransporte(obj.isAplicaTransporte());
        per.setAplicaTrasplante(obj.isAplicaTrasplante());
        per.setAplicaConsulta(obj.isAplicaConsulta());
        per.setAplicaAlternativa(obj.isAplicaAlternativa());
        per.setAplicaOncologia(obj.isAplicaOncologia());
        per.setAplicaTerapia(obj.isAplicaTerapia());
        per.setAplicaProceso(obj.isAplicaProceso());
        per.setAplicaPedt(obj.isAplicaPedt());
        per.setAplicaAmbulatorio(obj.isAplicaAmbulatorio());
        per.setAplicaHospitalario(obj.isAplicaHospitalario());
        per.setAplicaUnidadMovil(obj.isAplicaUnidadMovil());
        per.setAplicaOtrasExtramural(obj.isAplicaOtrasExtramural());
        per.setAplicaCentroReferencia(obj.isAplicaCentroReferencia());
        per.setAplicaInstitucionRemisora(obj.isAplicaInstitucionRemisora());
        per.setAplicaComplejidadBaja(obj.isAplicaComplejidadBaja());
        per.setAplicaComplejidadMedia(obj.isAplicaComplejidadMedia());
        per.setAplicaComplejidadAlta(obj.isAplicaComplejidadAlta());
        //2021-09-01 nuevos campos
        per.setAplicaIntramural(obj.isAplicaIntramural());
        per.setAplicaJornadaSalud(obj.isAplicaJornadaSalud());
        per.setAplicaTelemedicina(obj.isAplicaTelemedicina());
        per.setAplicaRefTelemedInteractiva(obj.isAplicaRefTelemedInteractiva());
        per.setAplicaRefTelemedNoInteractiva(obj.isAplicaRefTelemedNoInteractiva());
        per.setAplicaReferenciaTeleExperticia(obj.isAplicaReferenciaTeleExperticia());
        per.setAplicaReferenciaTeleMonitoreo(obj.isAplicaReferenciaTeleMonitoreo());
        per.setAplicapRemisorTeleExperticia(obj.isAplicapRemisorTeleExperticia());
        per.setAplicaRemisorTeleMonitoreo(obj.isAplicaRemisorTeleMonitoreo());
        per.setAplicaSinComplejidad(obj.isAplicaSinComplejidad());
        per.setAplicaTrasplanteOsteomuscular(obj.isAplicaTrasplanteOsteomuscular());
        per.setAplicaTrasplantePiel(obj.isAplicaTrasplantePiel());
        per.setAplicaTrasplanteCardiovascular(obj.isAplicaTrasplanteCardiovascular());
        per.setAplicaTrasplanteTejidoOcular(obj.isAplicaTrasplanteTejidoOcular());
        per.setAplicaAtencionPacienteQuemado(obj.isAplicaAtencionPacienteQuemado());
        per.setAplicaSaludMental(obj.isAplicaSaludMental());
        per.setAplicaSpa(obj.isAplicaSpa());
        per.setAplicaOtrasPatologias(obj.isAplicaOtrasPatologias());
        per.setAplicaTxCelProgeHematopoy(obj.isAplicaTxCelProgeHematopoy());
        per.setAplicaProcQuirurAmbulatorios(obj.isAplicaProcQuirurAmbulatorios());
        per.setAplicaOrganoRinon(obj.isAplicaOrganoRinon());
        per.setAplicaOrganoHigado(obj.isAplicaOrganoHigado());
        per.setAplicaOrganoPancreas(obj.isAplicaOrganoPancreas());
        per.setAplicaOrganoIntestino(obj.isAplicaOrganoIntestino());
        per.setAplicaOrganoMultivisceral(obj.isAplicaOrganoMultivisceral());
        per.setAplicaOrganoCorazon(obj.isAplicaOrganoCorazon());
        per.setAplicaOrganoPulmon(obj.isAplicaOrganoPulmon());
        per.setAplicaSustanciasPsicoactivas(obj.isAplicaSustanciasPsicoactivas());
        per.setAplicaTrasplanteRenal(obj.isAplicaTrasplanteRenal());

        per.setFechaApertura(obj.getFechaApertura());
        //objetos
        if (obj.getCntPrestadorSede() != null) {
            per.setCntPrestadorSedesId(new CntPrestadorSedes(obj.getCntPrestadorSede().getId()));
        }
        if (obj.getMaServicioHabilitacion() != null) {
            per.setMaServiciosHabilitacionId(new MaServiciosHabilitacion(obj.getMaServicioHabilitacion().getId()));
        }
        //auditoria
        per.setFechaHoraCrea(obj.getFechaHoraCrea());
        per.setTerminalCrea(obj.getTerminalCrea());
        per.setUsuarioCrea(obj.getUsuarioCrea());
        per.setFechaHoraModifica(obj.getFechaHoraModifica());
        per.setTerminalModifica(obj.getTerminalModifica());
        per.setUsuarioModifica(obj.getUsuarioModifica());
        return per;
    }

    private CntPrestadorSedeServicioHabilitacion castEntidadNegocioLargo(CntPrestadorSedeServiciosHabilitacion per) {
        CntPrestadorSedeServicioHabilitacion neg = new CntPrestadorSedeServicioHabilitacion();
        neg.setId(per.getId());
        neg.setMaServiciosHabilitacionCodigo(per.getMaServiciosHabilitacionCodigo());
        neg.setMaServiciosHabilitacionValor(per.getMaServiciosHabilitacionValor());
        neg.setActivo(per.getActivo());
        neg.setAplicaUrgencia(per.getAplicaUrgencia());
        neg.setAplicaDomiciliaria(per.getAplicaDomiciliaria());
        neg.setAplicaCirugia(per.getAplicaCirugia());
        neg.setAplicaHospitalizacion(per.getAplicaHospitalizacion());
        neg.setAplicaLaboratorio(per.getAplicaLaboratorio());
        neg.setAplicaImagenes(per.getAplicaImagenes());
        neg.setAplicaOdontologia(per.getAplicaOdontologia());
        neg.setAplicaTransporte(per.getAplicaTransporte());
        neg.setAplicaTrasplante(per.getAplicaTrasplante());
        neg.setAplicaConsulta(per.getAplicaConsulta());
        neg.setAplicaAlternativa(per.getAplicaAlternativa());
        neg.setAplicaOncologia(per.getAplicaOncologia());
        neg.setAplicaTerapia(per.getAplicaTerapia());
        neg.setAplicaProceso(per.getAplicaProceso());
        neg.setAplicaPedt(per.getAplicaPedt());
        neg.setAplicaAmbulatorio(per.getAplicaAmbulatorio());
        neg.setAplicaHospitalario(per.getAplicaHospitalario());
        neg.setAplicaUnidadMovil(per.getAplicaUnidadMovil());
        neg.setAplicaOtrasExtramural(per.getAplicaOtrasExtramural());
        neg.setAplicaCentroReferencia(per.getAplicaCentroReferencia());
        neg.setAplicaInstitucionRemisora(per.getAplicaInstitucionRemisora());
        neg.setAplicaComplejidadBaja(per.getAplicaComplejidadBaja());
        neg.setAplicaComplejidadMedia(per.getAplicaComplejidadMedia());
        neg.setAplicaComplejidadAlta(per.getAplicaComplejidadAlta());
        //2021-09-01 nuevos campos
        neg.setAplicaIntramural(per.getAplicaIntramural());
        neg.setAplicaJornadaSalud(per.getAplicaJornadaSalud());
        neg.setAplicaTelemedicina(per.getAplicaTelemedicina());
        neg.setAplicaRefTelemedInteractiva(per.getAplicaRefTelemedInteractiva());
        neg.setAplicaRefTelemedNoInteractiva(per.getAplicaRefTelemedNoInteractiva());
        neg.setAplicaReferenciaTeleExperticia(per.getAplicaReferenciaTeleExperticia());
        neg.setAplicaReferenciaTeleMonitoreo(per.getAplicaReferenciaTeleMonitoreo());
        neg.setAplicapRemisorTeleExperticia(per.getAplicapRemisorTeleExperticia());
        neg.setAplicaRemisorTeleMonitoreo(per.getAplicaRemisorTeleMonitoreo());
        neg.setAplicaSinComplejidad(per.getAplicaSinComplejidad());
        neg.setAplicaTrasplanteOsteomuscular(per.getAplicaTrasplanteOsteomuscular());
        neg.setAplicaTrasplantePiel(per.getAplicaTrasplantePiel());
        neg.setAplicaTrasplanteCardiovascular(per.getAplicaTrasplanteCardiovascular());
        neg.setAplicaTrasplanteTejidoOcular(per.getAplicaTrasplanteTejidoOcular());
        neg.setAplicaAtencionPacienteQuemado(per.getAplicaAtencionPacienteQuemado());
        neg.setAplicaSaludMental(per.getAplicaSaludMental());
        neg.setAplicaSpa(per.getAplicaSpa());
        neg.setAplicaOtrasPatologias(per.getAplicaOtrasPatologias());
        neg.setAplicaTxCelProgeHematopoy(per.getAplicaTxCelProgeHematopoy());
        neg.setAplicaProcQuirurAmbulatorios(per.getAplicaProcQuirurAmbulatorios());
        neg.setAplicaOrganoRinon(per.getAplicaOrganoRinon());
        neg.setAplicaOrganoHigado(per.getAplicaOrganoHigado());
        neg.setAplicaOrganoPancreas(per.getAplicaOrganoPancreas());
        neg.setAplicaOrganoIntestino(per.getAplicaOrganoIntestino());
        neg.setAplicaOrganoMultivisceral(per.getAplicaOrganoMultivisceral());
        neg.setAplicaOrganoCorazon(per.getAplicaOrganoCorazon());
        neg.setAplicaOrganoPulmon(per.getAplicaOrganoPulmon());
        neg.setAplicaSustanciasPsicoactivas(per.getAplicaSustanciasPsicoactivas());
        neg.setAplicaTrasplanteRenal(per.getAplicaTrasplanteRenal());
        neg.setFechaApertura(per.getFechaApertura());
        //objetos
        if (per.getCntPrestadorSedesId() != null) {
            neg.setCntPrestadorSede(castPrestadorSedesEntidadNegocio(per.getCntPrestadorSedesId()));
        }
        if (per.getMaServiciosHabilitacionId() != null) {
            neg.setMaServicioHabilitacion(castServicioHabilitacionEntidadNegocio(per.getMaServiciosHabilitacionId()));
        }
        //auditoria
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        neg.setUsuarioModifica(per.getUsuarioModifica());
        neg.setTerminalModifica(per.getTerminalModifica());
        neg.setFechaHoraModifica(per.getFechaHoraModifica());
        return neg;
    }

    public MaServicioHabilitacion castServicioHabilitacionEntidadNegocio(MaServiciosHabilitacion ent) {
        MaServicioHabilitacion obj = new MaServicioHabilitacion();
        obj.setId(ent.getId());
        obj.setCodigo(ent.getCodigo());
        obj.setNombre(ent.getNombre());
        obj.setActivo(ent.getActivo());
        obj.setFechaHoraCrea(ent.getFechaHoraCrea());
        obj.setFechaHoraModifica(ent.getFechaHoraModifica());
        obj.setTerminalCrea(ent.getTerminalCrea());
        obj.setTerminalModifica(ent.getTerminalModifica());
        obj.setUsuarioCrea(ent.getUsuarioCrea());
        obj.setUsuarioModifica(ent.getUsuarioModifica());
        return obj;
    }

    public CntPrestadorSede castPrestadorSedesEntidadNegocio(CntPrestadorSedes per) {
        CntPrestadorSede obj = new CntPrestadorSede();
        obj.setId(per.getId());
        obj.setCodigoPrestador(per.getCodigoPrestador());
        obj.setUbicacionId(per.getUbicacionId());
        obj.setMaeRegionId(per.getMaeRegionId());
        obj.setMaeRegionCodigo(per.getMaeRegionCodigo());
        obj.setMaeRegionValor(per.getMaeRegionValor());
        obj.setDireccion(per.getDireccion());
        obj.setNombreSede(per.getNombre());
        obj.setCodigoSede(per.getCodigo());
        obj.setCodigoHabilitacionSede(per.getCodigoHabilitacion());
        obj.setZonaPrecedencia(per.getZonaPrecedencia());
        obj.setEstadoSede(per.getEstadoSede());
        obj.setNivelAtencion(per.getNivelAtencion());
        obj.setClasePrestador(per.getMaeClasePrestadorId());
        obj.setMaeClasePrestadorCodigo(per.getMaeClasePrestadorCodigo());
        obj.setMaeClasePrestadorValor(per.getMaeClasePrestadorValor());
        obj.setFax(per.getFax());
        obj.setTelefonoCitas(per.getTelefonoCitas());
        obj.setCorreoElectronico(per.getCorreoElectronico());
        obj.setTelefonoAdministrativo(per.getTelefonoAdministrativo());
        obj.setCapitacion(per.getCapitacion());
        if (per.getCntPrestadoresId() != null) {
            obj.setCntPrestador(new CntPrestador(per.getCntPrestadoresId().getId(), per.getCntPrestadoresId().getRazonSocial()));
        }
        //auditoria
        obj.setFechaHoraCrea(per.getFechaHoraCrea());
        obj.setTerminalCrea(per.getTerminalCrea());
        obj.setUsuarioCrea(per.getUsuarioCrea());
        obj.setFechaHoraModifica(per.getFechaHoraModifica());
        obj.setTerminalModifica(per.getTerminalModifica());
        obj.setUsuarioModifica(per.getUsuarioModifica());
        return obj;
    }

    @Override
    public CntPrestadorSedeServicioHabilitacion consultarPorSedeYServicioHabilitacion(int idSede, int idServicioHabilitacion) throws Exception {
        CntPrestadorSedeServicioHabilitacion objResult = null;
        try {
            String strQuery = "FROM CntPrestadorSedeServiciosHabilitacion p "
                    + "WHERE p.cntPrestadorSedesId.id = " + idSede + " "
                    + "AND p.maServiciosHabilitacionId.id = " + idServicioHabilitacion + " ";
            objResult = castEntidadNegocio((CntPrestadorSedeServiciosHabilitacion) getEntityManager().createQuery(strQuery)
                    .getSingleResult());

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
    public List<CntPrestadorSedeServicioHabilitacion> consultarPorSedeTecnologiaYServiciosHabilitacion(int idSede, String codigoTecnologia, String servicios) throws Exception {
        List<CntPrestadorSedeServicioHabilitacion> listResult = new ArrayList();
        try {
            String strQuery = "SELECT p FROM CntPrestadorSedeServiciosHabilitacion p "
                    + "INNER JOIN p.maServiciosHabilitacionId.maTecnologiaServiciosHabilitacionList t "
                    + "WHERE p.cntPrestadorSedesId.id = :idSede "
                    + "AND t.maTecnologiasId.codigoPropio= :codigoTecnologia "
                    + "AND p.maServiciosHabilitacionCodigo IN (" + servicios + ") "
                    + "AND p.activo=1";
            List<CntPrestadorSedeServiciosHabilitacion> list = getEntityManager().createQuery(strQuery)
                    .setParameter("idSede", idSede)
                    .setParameter("codigoTecnologia", codigoTecnologia)
                    .getResultList();
            for (CntPrestadorSedeServiciosHabilitacion per : list) {
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
