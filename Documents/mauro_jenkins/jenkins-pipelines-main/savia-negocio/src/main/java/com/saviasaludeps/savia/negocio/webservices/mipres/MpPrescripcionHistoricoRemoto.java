
package com.saviasaludeps.savia.negocio.webservices.mipres;

import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionHistorico;

public interface MpPrescripcionHistoricoRemoto {
    
    /**
     * Función para insertar una prescripción programada
     * @param mpPrescripcionHistorico
     * @return
     */
    public int insertar(MpPrescripcionHistorico mpPrescripcionHistorico);
    
 
}
