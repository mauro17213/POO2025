package com.saviasaludeps.savia.solicitud.negocio;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Carga;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;

public interface AuAnexo3CargaRemoto {

    AuAnexo3Carga consultarSiguienteCarga(int estado) throws Exception; 
    AuAnexo3Carga consultarCarga(int id) throws Exception; 
    boolean actualizarEstado(int id, int estado) throws Exception; 
    CntPrestadorSede consultarIdPrestadorPorCodigoHabilitacion(String codigo_habilitacion) throws Exception; 
}
