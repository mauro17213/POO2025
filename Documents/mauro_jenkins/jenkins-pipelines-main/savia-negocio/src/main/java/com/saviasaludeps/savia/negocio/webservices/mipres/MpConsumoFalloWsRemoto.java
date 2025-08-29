
package com.saviasaludeps.savia.negocio.webservices.mipres;

import com.saviasaludeps.savia.dominio.mipres.MpConsumoFallo;
import java.util.Date;
import java.util.List;

public interface MpConsumoFalloWsRemoto {

    /**
     * insertar registro en mp_consumo_fallo
     *
     * @param fallo
     * @return
     * @throws Exception
     */
    Integer insertarConsumoFallo(MpConsumoFallo fallo) throws Exception;

    /**
     * actualizar registro en mp_consumo_fallo
     * @param obj
     * @throws Exception
     */
     void actualizarEstado(MpConsumoFallo obj) throws Exception;
    
     
    /**
     * actualizar estado en mp_consumo_fallo de registros que fallaron en los ultimos 2 meses
     * @throws Exception
     */
     public void actualizarEstadoFallosResincronizar() throws Exception;
     
    /**
     * Consulta registros de mp_consumo_fallo por estado y tiposervicio
     * @param estado
     * @param servicio
     * @return 
     * @throws Exception
     */
    public List<MpConsumoFallo> consultarxEstadoyServicio(int estado, String servicio) throws Exception;
    
}
