package com.saviasaludeps.savia.negocio.crue;

import com.saviasaludeps.savia.dominio.crue.AuAnexo2RescateSede;

/**
 *
 * @author jdlopez
 */
public interface AuAnexo2RescateSedeRemoto {

    /**
     * Guarda un registro en AuAnexo2RescateSedes
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(AuAnexo2RescateSede obj) throws Exception;

    /**
     * Consulta registro de acuerdo al id en AuAnexo2RescateSedes
     *
     * @param idAnexo2RescateSede
     * @return
     * @throws Exception
     */
    AuAnexo2RescateSede consultar(int idAnexo2RescateSede) throws Exception;

    /**
     * Consulta registro de acuerdo a la relacion de prestadorSedesCapita y
     * prestadorSedesOrigen
     *
     * @param idPrestadorSedeCapita
     * @param idPrestadorSedeOrigen
     * @return
     * @throws Exception
     */
    AuAnexo2RescateSede consultarIdSedeCapitaIdSedeOrigen(int idPrestadorSedeCapita, int idPrestadorSedeOrigen) throws Exception;
}
