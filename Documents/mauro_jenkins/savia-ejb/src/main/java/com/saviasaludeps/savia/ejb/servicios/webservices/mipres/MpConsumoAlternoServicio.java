package com.saviasaludeps.savia.ejb.servicios.webservices.mipres;

import com.saviasaludeps.savia.dominio.mipres.MpConsumo;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import com.saviasaludeps.savia.ejb.entidades.MpConsumos;
import com.saviasaludeps.savia.ejb.entidades.MpConsumosAlterno;
import com.saviasaludeps.savia.negocio.webservices.mipres.PrescripcionWsRemoto;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import com.saviasaludeps.savia.ejb.utilidades.RemotoEJB;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.webservices.mipres.MpConsumoAlternoRemoto;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author yjimenez
 */
@Stateless
@Remote(MpConsumoAlternoRemoto.class)
public class MpConsumoAlternoServicio extends GenericoServicio implements MpConsumoAlternoRemoto {

    public final static int TIPO_MEDICAMENTO = 1;
    public final static int TIPO_TECNOLOGIA = 2;
    public final static int TIPO_DISPOSITIVO_MEDICO = 3;
    public final static int TIPO_PRODUCTO_NUTRICIONAL = 4;
    public final static int TIPO_SERVICIO_COMPLEMENTARIO = 5;

    public final static int ESTADO_CONSUMO_CONSULTANDO = 0;
    public final static int ESTADO_CONSUMO_GUARDANDO = 1;
    public final static int ESTADO_CONSUMO_EXITOSO = 2;
    public final static int ESTADO_CONSUMO_CONSULTANDO_ERROR = 3;
    public final static int ESTADO_CONSUMO_GUARDANDO_ERROR = 4;

    public final static int ESTADO_PRESCRIPCION_PENDIENTE = 0;
    public final static int ESTADO_PRESCRIPCION_DIRECCIONADA = 1;
    public final static int ESTADO_PRESCRIPCION_PROGRAMADA = 2;

    public final static int ESTADO_PRESCRIPCION_SERVICIO_DIRECCIONADA = 1;
    public final static int ESTADO_PRESCRIPCION_SERVICIO_NO_DIRECCIONADA = 2;
    public final static int ESTADO_PRESCRIPCION_SERVICIO_PENDIENTE = 3;

    public final static String TIPO_DOCUMENTO_NACIDOVIVO = "NV";
    public final static String TIPO_DOCUMENTO_CERTIFICADONACIDOVIVO = "CN";
    SimpleDateFormat formatoSimple = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat formatoFH = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        return (AfiliadoRemoto) RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }
    
    @Override
    public Integer insertarMpConsumo(MpConsumo mpConsumo) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(
                    castEntidadNegocioMpConsumo(mpConsumo)).getId();
        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
//            System.out.println(e);
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }
    
//    @Override
//    public void actualizarMpConsumo(MpConsumo mpConsumo) throws Exception {
//        MpConsumosAlterno consumo = BucarMpConsumosPorId(mpConsumo.getId());
//        if (consumo == null) {
//            return;
//        }
//
//        consumo.setEstado(mpConsumo.getEstado());
//        consumo.setRegistros(mpConsumo.getRegistros());
//        if (mpConsumo.getDescripcion() != null) {
//            int maxSize = 1024;
//            if (mpConsumo.getDescripcion().length() > maxSize) {
//                mpConsumo.setDescripcion(mpConsumo.getDescripcion().substring(0, maxSize));
//            }
//            consumo.setObservacion(new String(mpConsumo.getDescripcion().getBytes(StandardCharsets.UTF_8)));
//        }
//        if (mpConsumo.getEstado() == MpConsumo.ESTADO_CONSUMO_EXITOSO
//                || mpConsumo.getEstado() == MpConsumo.ESTADO_CONSUMO_CONSULTANDO_ERROR
//                || mpConsumo.getEstado() == MpConsumo.ESTADO_CONSUMO_GUARDANDO_ERROR) {
//            consumo.setFechaHoraFin(new Date());
//        }
//        consumo.setRegistrosExitosos(mpConsumo.getRegistros_Exitosos());
//        actualizarMpConsumos(consumo);
//    }

    @Override
    public MpConsumo consultarIdConsumo(Integer id) throws Exception {
        MpConsumo result = new MpConsumo();
        try {
            String strQuery = "FROM MpConsumosAlterno mp "
                    + "WHERE mp.id =  '" + id
                    + "' ORDER BY mp.id";
            MpConsumosAlterno per = (MpConsumosAlterno) getEntityManager()
                    .createQuery(strQuery).getSingleResult();
            //for (MpConsumoFallos per : list.subList(0, 1000)) {
            result = (castMpConsumoEntidadNegocio(per));

        } catch (NoResultException e) {
            result = null;
        } catch (Exception e) {
            result = null;
        } finally {
            cerrarEntityManager();
        }
        return result;
    }
    
    @Override
    public String obtenerUltimaFechaEjecucionServicio(String servicio) throws Exception {
        String fecha = "";
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //        se reemplaza la consulta 
            String strQuery = "SELECT MAX(m.periodo) "
                    + "FROM MpConsumosAlterno m "
                    + "WHERE m.servicio = :servicio "
                    + "AND m.estado = 2";

            TypedQuery<Date> query = getEntityManager().createQuery(strQuery, Date.class);
            query.setParameter("servicio", servicio);
            Date periodo = query.getSingleResult();
            if (periodo != null) {
                fecha = formato.format(periodo);
            }
        } catch (NoResultException e) {
            fecha = "";
        } catch (Exception e) {
//        System.out.println(e);
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return fecha;
    }
    
    @Override
    public String obtenerUltimaFechaEjecucionServicio(String servicio, String fechaInicio, String fechaFin) throws Exception {
        SimpleDateFormat formatoF = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = "";
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //        se reemplaza la consulta 
            String strQuery = "SELECT MAX(m.periodo) "
                    + "FROM MpConsumosAlterno m "
                    + "WHERE m.servicio = :servicio "
                    + "AND m.estado = 2 "
                    + "AND m.periodo BETWEEN :inicio AND :fin";

            TypedQuery<Date> query = getEntityManager().createQuery(strQuery, Date.class);
            query.setParameter("servicio", servicio);
            query.setParameter("inicio", formatoF.parse(fechaInicio));
            query.setParameter("fin", formatoF.parse(fechaFin));
            Date periodo = query.getSingleResult();
            if (periodo != null) {
                fecha = formato.format(periodo);
            }
        } catch (NoResultException e) {
            fecha = "";
        } catch (Exception e) {
//        System.out.println(e);
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return fecha;
    }
    
    @Override
    public void actualizarMpConsumo(MpConsumo obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE MpConsumosAlterno m SET ";
            strQuery += " m.estado = '" + obj.getEstado() + "', ";
            strQuery += " m.registros = '" + obj.getRegistros() + "', ";
            strQuery += " m.registrosExitosos = '" + obj.getRegistrosExitosos() + "', ";
//            strQuery += " m.periodo = '" + obj.getPeriodo() + "', ";
            if (obj.getFechaHoraFin() != null) {
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                strQuery += " m.fechaHoraFin = '" + formato.format(obj.getFechaHoraFin()) + "', ";
            }
            strQuery += " m.observacion = '" + obj.getObservacion()+ "' ";
            strQuery += "  WHERE m.id = " + obj.getId();
            org.hibernate.Query query = session.createQuery(strQuery);
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
    public MpConsumo consultarUltimoConsumo(String servicio, String fecha) throws Exception {
        MpConsumo result = new MpConsumo();
        try {
            String strQuery = "FROM MpConsumosAlterno mp "
                    + "WHERE mp.servicio =  '" + servicio
                    + "' AND mp.periodo = '" + fecha
                    + "' AND mp.estado = 2 "
                    + " ORDER BY mp.id DESC";
            Query query = getEntityManager().createQuery(strQuery).setMaxResults(1);
            List<MpConsumosAlterno> list = query
                    .getResultList();
            if (!list.isEmpty()) {
                for (MpConsumosAlterno per : list) {
                    result = (castMpConsumoEntidadNegocio(per));
                }
            } else {
                result = null;
            }
        } catch (NoResultException e) {
            result = null;
        } catch (Exception e) {
            result = null;
        } finally {
            cerrarEntityManager();
        }
        return result;
    }

    

    @Override
    public MpConsumo consultarUltimoConsumo(String servicio) throws Exception {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        MpConsumo consumo;
        try {
            String strQuery = "SELECT m FROM MpConsumosAlterno m "
                    + "WHERE m.servicio = :servicio "
                    + "AND m.estado = 2 "
                    + "ORDER BY m.id DESC ";
            MpConsumos entRes = getEntityManager()
                    .createQuery(strQuery, MpConsumos.class)
                    .setParameter("servicio", servicio)
                    .setMaxResults(1)
                    .getSingleResult();
            consumo = new MpConsumo();
            consumo.setId(entRes.getId());
            consumo.setPeriodo(formato.format(entRes.getPeriodo()));
            consumo.setServicio(entRes.getServicio());
            consumo.setRegistros(entRes.getRegistros());
            consumo.setRegistrosExitosos(entRes.getRegistrosExitosos());
            consumo.setEstado(entRes.getEstado());
            consumo.setObservacion(entRes.getObservacion());
            consumo.setFechaHoraInicio(entRes.getFechaHoraInicio());
            consumo.setFechaHoraFin(entRes.getFechaHoraFin());
        } catch (NoResultException e) {
            consumo = null;
        } catch (Exception e) {
            consumo = null;
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return consumo;
    }

    public static MpConsumo castMpConsumoEntidadNegocio(MpConsumosAlterno per) {
        MpConsumo obj = new MpConsumo();
        obj.setId(per.getId());
        obj.setRegistros(per.getRegistros());
        obj.setRegistrosExitosos(per.getRegistrosExitosos());
        obj.setServicio(per.getServicio());
        obj.setObservacion(per.getObservacion());
        obj.setEstado(per.getEstado());
        obj.setPeriodo(per.getPeriodo().toString());
        return obj;
    }
    
     public MpConsumosAlterno castEntidadNegocioMpConsumo(MpConsumo obj) throws Exception {
        MpConsumosAlterno per = new MpConsumosAlterno();
        per.setPeriodo(formatoSimple.parse(obj.getPeriodo()));
        per.setServicio(obj.getServicio());
        per.setRegistros(obj.getRegistros());
        per.setEstado(obj.getEstado());
        per.setObservacion(obj.getObservacion());
        per.setFechaHoraInicio(obj.getFechaHoraInicio());
        return per;
    }

}
