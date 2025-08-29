/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.administracion;

import com.saviasaludeps.savia.dominio.administracion.GnSede;
import com.saviasaludeps.savia.dominio.administracion.GnSedeHorario;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.ejb.entidades.GnSedeHorarios;
import com.saviasaludeps.savia.ejb.entidades.GnSedes;
import com.saviasaludeps.savia.negocio.administracion.GnSedeHorarioRemoto;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author sgiraldov
 */
@Stateless
@Remote(GnSedeHorarioRemoto.class)
public class GnSedeHorarioServicio extends GenericoServicio implements GnSedeHorarioRemoto {

    @Override
    public int insertar(GnSedeHorario obj) throws Exception {
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
    public void actualizar(GnSedeHorario obj) throws Exception {
        try {
            Session session = getEntityManager().unwrap(Session.class);
            String strQuery = "UPDATE GnSedeHorarios a SET ";
            strQuery += "a.dias = :dias ,";
            strQuery += "a.horaDesde = :horaDesde ,";
            strQuery += "a.horaHasta = :horaHasta ,";
            strQuery += "a.activo = :activo ,";
            strQuery += "a.fechaHoraModifica = :fechaHoraModifica ,";
            strQuery += "a.terminalModifica = :terminalModifica ,";
            strQuery += "a.usuarioModifica = :usuarioModifica ";
            strQuery += " WHERE a.id = :id ";
            Query query = session.createQuery(strQuery);
            query.setParameter("dias", obj.getDias());
            query.setParameter("horaDesde", obj.getHoraDesde());
            query.setParameter("horaHasta", obj.getHoraHasta());
            query.setParameter("activo", obj.isActivo());
            query.setParameter("fechaHoraModifica", obj.getFechaHoraModifica());
            query.setParameter("terminalModifica", obj.getTerminalModifica());
            query.setParameter("usuarioModifica", obj.getUsuarioModifica());
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
    public GnSedeHorario eliminar(int id) throws Exception {
        GnSedeHorario obj = null;
        try {
            GnSedeHorarios ent = getEntityManager().find(GnSedeHorarios.class, id);
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

    @Override
    public List<GnSedeHorario> listarPorIdSede(int idSede) throws Exception {
        List<GnSedeHorario> listaResultado = new ArrayList<>();
        try {
            String strQuery = "FROM GnSedeHorarios c "
                    + " WHERE c.gnSedesId.id = " + idSede
                    + " ORDER BY c.id DESC";
            List<GnSedeHorarios> list = getEntityManager().createQuery(strQuery)
                    .getResultList();
            for (GnSedeHorarios horario : list) {
                listaResultado.add(castEntidadNegocio(horario));
            }
        } catch (NoResultException e) {
            listaResultado = new ArrayList<>();
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return listaResultado;
    }

    private GnSedeHorarios castNegocioEntidad(GnSedeHorario negocio) {
        GnSedeHorarios entidad = new GnSedeHorarios();
        entidad.setGnSedesId(new GnSedes(negocio.getGnSedeId().getId()));
        entidad.setDias(negocio.getDias());
        entidad.setHoraDesde(negocio.getHoraDesde());
        entidad.setHoraHasta(negocio.getHoraHasta());
        entidad.setActivo(negocio.isActivo());
        //Auditoria
        entidad.setUsuarioCrea(negocio.getUsuarioCrea());
        entidad.setFechaHoraCrea(negocio.getFechaHoraCrea());
        entidad.setTerminalCrea("1.0.0");
        entidad.setUsuarioModifica(negocio.getUsuarioModifica());
        entidad.setFechaHoraModifica(negocio.getFechaHoraModifica());
        entidad.setTerminalModifica(negocio.getTerminalModifica());
        return entidad;
    }

    private GnSedeHorario castEntidadNegocio(GnSedeHorarios entidad) {
        GnSedeHorario negocio = new GnSedeHorario();
        negocio.setId(entidad.getId());
        negocio.setGnSedeId(new GnSede(entidad.getGnSedesId().getId()));
        negocio.setDias(entidad.getDias());
        negocio.setHoraDesde(entidad.getHoraDesde());
        negocio.setHoraHasta(entidad.getHoraHasta());
        negocio.setActivo(entidad.getActivo());
        //Auditoria
        negocio.setUsuarioCrea(entidad.getUsuarioCrea());
        negocio.setFechaHoraCrea(entidad.getFechaHoraCrea());
        negocio.setTerminalCrea(entidad.getTerminalCrea());
        negocio.setUsuarioModifica(entidad.getUsuarioModifica());
        negocio.setFechaHoraModifica(entidad.getFechaHoraModifica());
        negocio.setTerminalModifica(entidad.getTerminalModifica());
        return negocio;
    }

    @Override
    public boolean estaEnHorario(int idSede) throws Exception {
        boolean enHorario = false;
        try {
            String dia = "";
            Calendar cal = Calendar.getInstance();
            switch (cal.get(Calendar.DAY_OF_WEEK)) {
                case 1:
                    dia = "D";
                    break;
                case 2:
                    dia = "L";
                    break;
                case 3:
                    dia = "M";
                    break;
                case 4:
                    dia = "W";
                    break;
                case 5:
                    dia = "J";
                    break;
                case 6:
                    dia = "V";
                    break;
                case 7:
                    dia = "S";
                    break;
            }
            if (!dia.equals("")) {
                SimpleDateFormat formatoHora = new SimpleDateFormat("HH");
                SimpleDateFormat formatoMinuto = new SimpleDateFormat("mm");
                String strQuery = "FROM GnSedeHorarios c "
                        + " WHERE c.gnSedesId.id = " + idSede
                        + " AND c.activo = 1 AND c.dias LIKE '%" + dia + "%' "
                        + " ORDER BY c.id DESC";
                List<GnSedeHorarios> list = getEntityManager().createQuery(strQuery)
                        .getResultList();
                if (list != null) {
                    Date fechaActual = new Date();
                    String horaStrActual = formatoHora.format(fechaActual);
                    String minutoStrActual = formatoMinuto.format(fechaActual);
                    Integer horaActual = Integer.parseInt(horaStrActual);
                    Integer minutoActual = Integer.parseInt(minutoStrActual);
                    for (GnSedeHorarios horario : list) {
                        String horaStr = formatoHora.format(horario.getHoraDesde());
                        String minutoStr = formatoMinuto.format(horario.getHoraDesde());
                        Integer hora = Integer.parseInt(horaStr);
                        Integer minuto = Integer.parseInt(minutoStr);
                        if ((horaActual > hora) || (horaActual == hora && minutoActual >= minuto)) {
                            horaStr = formatoHora.format(horario.getHoraHasta());
                            minutoStr = formatoMinuto.format(horario.getHoraHasta());
                            hora = Integer.parseInt(horaStr);
                            minuto = Integer.parseInt(minutoStr);
                            if ((horaActual < hora) || (horaActual == hora && minutoActual <= minuto)) {
                                enHorario = true;
                                break;
                            }
                        }
                    }
                }
            }

        } catch (NoResultException e) {
            enHorario = false;
        } catch (Exception e) {
            Exception(CONSULTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return enHorario;
    }

    @Override
    public boolean estaEnHorarioDia(int idSede) throws Exception {
        boolean enHorario = false;
        String dia = "";
        Calendar cal = Calendar.getInstance();
        switch (cal.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                dia = "D";
                break;
            case 2:
                dia = "L";
                break;
            case 3:
                dia = "M";
                break;
            case 4:
                dia = "W";
                break;
            case 5:
                dia = "J";
                break;
            case 6:
                dia = "V";
                break;
            case 7:
                dia = "S";
                break;
        }
        if (!dia.equals("")) {
            String strQuery = "FROM GnSedeHorarios c "
                    + " WHERE c.gnSedesId.id = " + idSede
                    + " AND c.activo = 1 AND c.dias LIKE '%" + dia + "%' "
                    + " ORDER BY c.id DESC";
            
            List<GnSedeHorarios> list = getEntityManager().createQuery(strQuery)
                        .getResultList();            
            if(list!= null){
                if (!list.isEmpty()) {
                   enHorario = true;
                }
            }

        }
        return enHorario;       
       
    }

}
