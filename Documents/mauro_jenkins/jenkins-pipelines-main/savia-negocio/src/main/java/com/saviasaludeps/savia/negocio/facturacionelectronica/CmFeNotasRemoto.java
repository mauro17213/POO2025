package com.saviasaludeps.savia.negocio.facturacionelectronica;

import com.saviasaludeps.savia.dominio.facturacionelectronica.CmFeNota;

/**
 *
 * @author LFRIVERA
 */
public interface CmFeNotasRemoto {

    int insertar(CmFeNota nota) throws Exception;

    void actualizar(CmFeNota nota) throws Exception;

    CmFeNota eliminar(int id) throws Exception;
    
    CmFeNota consultarPorCmFeCargaId(int cmFeCargaId) throws Exception;
   
    CmFeNota consultarPorCmFeRipsFacturasId(int cmFeRipsFacturasId) throws Exception;
   
    CmFeNota consultarPorCmFacturasId(int cmFeCargaId) throws Exception;
}
