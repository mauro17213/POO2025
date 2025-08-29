/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.tutela;

import com.saviasaludeps.savia.dominio.tutela.TuMemorialFirma;

/**
 *
 * @author pavacca
 */
public interface TuMemorialFirmaRemoto {

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (TuMemorialPersona) cargado
     * @throws java.lang.Exception
     */
    TuMemorialFirma consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo TuMemorialFirma
     *
     * @param per (TuMemorialPersona)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(TuMemorialFirma per) throws Exception;

    /**
     * Método para eliminar un usuario
     *
     * @param id
     * @return (TuMemorialFirma) Objetop eliminado
     * @throws java.lang.Exception
     */
    TuMemorialFirma eliminar(int id) throws Exception;
    
    /**
     * Método para consultar un registro por ID memorial persona 
     *
     * @param idTuMemoriaPersonal
     * @return (TuMemorialPersona) cargado
     * @throws java.lang.Exception
     */
    TuMemorialFirma consultarMemorialFirmaPorPersonal(Integer idTuMemoriaPersonal) throws Exception;
}
