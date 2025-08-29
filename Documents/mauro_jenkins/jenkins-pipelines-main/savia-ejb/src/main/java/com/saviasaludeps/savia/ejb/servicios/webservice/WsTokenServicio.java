/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.servicios.webservice;

import com.saviasaludeps.savia.dominio.webservice.WsConexion;
import com.saviasaludeps.savia.dominio.webservice.WsToken;
import com.saviasaludeps.savia.ejb.entidades.WsTokens;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.webservice.WsTokenRemoto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author Jaime Andres Olarte
 */
@Stateless
@Remote(WsTokenRemoto.class)
@Local(WsTokenLocal.class)
public class WsTokenServicio extends GenericoServicio implements WsTokenLocal, WsTokenRemoto {

    @Override
    public List<WsToken> consultarByWsConexiones(int ws_conexiones_id) throws java.lang.Exception {
        List<WsToken> listResult = new ArrayList<>();
        try {
            String strQuery = "FROM WsTokens w WHERE w.wsConexionesId.id = :id ORDER BY w.id DESC ";
            
            List<WsTokens> list = getEntityManager().createQuery(strQuery)
                    .setParameter("id", ws_conexiones_id)
                    .getResultList();
            for (WsTokens per : list) {
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
    
    public static WsToken castEntidadNegocio(WsTokens per) {
        WsToken obj = new WsToken();
        obj.setFechaHoraFin(per.getFechaHoraFin());
        obj.setFechaHoraInicio(per.getFechaHoraInicio());
        obj.setId(per.getId());
        obj.setIpSolicita(per.getIpSolicita());
        obj.setTiempo(per.getTiempo());
        obj.setToken(per.getToken());
        obj.setTokenCorto(per.getToken().substring(0, 9) + "...");
        obj.setTransacciones(per.getTransacciones());
        obj.setWsConexion(new WsConexion(per.getWsConexionesId().getId()));
        return obj;
    }
    
}
