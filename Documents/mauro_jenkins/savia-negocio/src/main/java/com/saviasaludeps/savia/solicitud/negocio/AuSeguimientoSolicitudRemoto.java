package com.saviasaludeps.savia.solicitud.negocio;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Item;
import java.util.HashMap;

public interface AuSeguimientoSolicitudRemoto {

    /**
     * Retorna el id de la solicitud validada
     *
     * @param auAnexo3
     * @param hashEstadosSeguimiento
     * @return
     * @throws Exception
     */
    AuAnexo3 insertar(AuAnexo3 auAnexo3, HashMap<String, Maestro> hashEstadosSeguimiento) throws Exception;
    
    /**
     * verifica si aplica el seguimiento
     * @param item
     * @return
     * @throws Exception 
     */
    boolean validarAplicaSeguimiento(AuAnexo3Item item) throws Exception;

}
