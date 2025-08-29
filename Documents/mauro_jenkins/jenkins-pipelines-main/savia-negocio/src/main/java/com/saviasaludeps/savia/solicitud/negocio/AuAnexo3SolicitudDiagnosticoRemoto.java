package com.saviasaludeps.savia.solicitud.negocio;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;

public interface AuAnexo3SolicitudDiagnosticoRemoto {
    
    /**
     * Crear un objeto Anexo3Diagnostico
     * @param auAnexo3
     * @return
     * @throws Exception
     */
    AuAnexo3 insertar(AuAnexo3 auAnexo3) throws Exception;
    
}
