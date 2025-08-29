/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.atencionusuario;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.atencionusuario.AusGraficaRemoto;
import java.util.Map;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author pavacca
 */
@Stateless
@Remote(AusGraficaRemoto.class)
@Local(AusGraficaLocal.class)
public class AusGraficaServicio extends GenericoServicio implements AusGraficaLocal, AusGraficaRemoto{
    @Override
    public int contarCasos(ParamConsulta paramConsulta, int tipoConsulta, int estadoCerrado, int estadoRechazado) throws Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM AusCasos c "
                    + " WHERE c.gnEmpresasId.id = :empresaid  ";
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "fechas":
                            strQuery += "AND c.fechaHoraCrea BETWEEN '" + e.getValue() + "' ";
                            break;
                        case "idResponsable":
                            strQuery += "AND c.gnUsuariosResponsableId.id = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            switch(tipoConsulta){
                case 1:
                    /*strQuery += "AND (c.maeSolicitudEstadoId = :estadoCerrado OR c.maeSolicitudEstadoId = :estadoRechazado) "
                            + " AND c.fechaHoraResponsable < c.fechaVencimiento";*/
                    strQuery += "AND (c.maeSolicitudEstadoId = :estadoCerrado) "
                            + " AND c.fechaHoraResponsable < c.fechaVencimiento";
                    break;
                case 2:
                    /*strQuery += "AND (c.maeSolicitudEstadoId = :estadoCerrado OR c.maeSolicitudEstadoId = :estadoRechazado) "
                            + " AND c.fechaHoraResponsable > c.fechaVencimiento";*/
                    strQuery += "AND (c.maeSolicitudEstadoId = :estadoCerrado) "
                            + " AND c.fechaHoraResponsable > c.fechaVencimiento";
                    break;
                case 3:
                    /*strQuery += "AND (c.maeSolicitudEstadoId <> :estadoCerrado OR c.maeSolicitudEstadoId <> :estadoRechazado) ";*/
                    strQuery += "AND (c.maeSolicitudEstadoId <> :estadoCerrado) ";
                    break;
                default:
                    break;
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    .setParameter("empresaid", paramConsulta.getEmpresaId())
                    .setParameter("estadoCerrado", estadoCerrado)
                    //.setParameter("estadoRechazado", estadoRechazado)
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
    public int contarServicios(ParamConsulta paramConsulta, int tipoConsulta, int estadoCerrado, int estadoRechazado) throws java.lang.Exception {
        int cant = 0;
        try {
            String strQuery = "SELECT COUNT(c) FROM AusCasoServicios c "
                    + " WHERE 1=1 ";
            
            if (paramConsulta.getFiltros() != null) {
                for (Map.Entry e : paramConsulta.getFiltros().entrySet()) {
                    switch ((String) e.getKey()) {
                        case "fechas":
                            strQuery += "AND c.fechaHoraCrea BETWEEN '" + e.getValue() + "' ";
                            break;
                        case "idResponsable":
                            strQuery += " AND c.gnUsuariosAsignadoId.id = " + e.getValue() + " ";
                            break;
                        case "idIps":
                            strQuery += "AND c.cntPrestadorSedeDestinoId.id = " + e.getValue() + " ";
                            break;
                        case "idIpsPrescriptora":
                            strQuery += "AND c.cntPrestadorSedePrescriptoraId.id = " + e.getValue() + " ";
                            break;
                    }
                }
            }
            switch(tipoConsulta){
                case 1:
                    strQuery += "AND (c.maeEstadoId = :estadoCerrado OR c.maeEstadoId = :estadoRechazado) "
                            + " AND c.fechaCumplimiento < c.fechaVencimiento";
                    break;
                case 2:
                    strQuery += "AND (c.maeEstadoId = :estadoCerrado OR c.maeEstadoId = :estadoRechazado) "
                            + " AND c.fechaCumplimiento > c.fechaVencimiento";
                    break;
                case 3:
                    strQuery += "AND (c.maeEstadoId <> :estadoCerrado OR c.maeEstadoId <> :estadoRechazado) ";
                    break;
                default:
                    break;
            }
            cant = (int) (long) getEntityManager().createQuery(strQuery)
                    .setParameter("estadoCerrado", estadoCerrado)
                    .setParameter("estadoRechazado", estadoRechazado)
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
}
