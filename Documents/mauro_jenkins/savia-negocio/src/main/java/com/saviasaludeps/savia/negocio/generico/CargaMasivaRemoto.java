/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.generico;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegCarga;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoCarga;
import com.saviasaludeps.savia.dominio.maestro.MaCarga;

/**
 *
 * @author jyperez
 */
public interface CargaMasivaRemoto {
    
    /**
     * método para llamar a la ejecución de carga masiva de Afiliados
     * @param carga
     * @throws Exception 
     */
    void CargaMasivaAfiliados(AsegCarga carga) throws Exception;
    /**
     * método para llamar a la ejecución de carga masiva de Contratos Detalles
     * @param carga
     * @throws Exception 
     */
    void CargaMasivaContratos(CntContratoCarga carga) throws Exception;
    
    /**
     * método para llamar a la ejecución de carga masiva de Maestros
     * @param carga
     * @throws Exception 
     */
    void CargaMasivaMaestros(MaCarga carga) throws Exception;
    
    
}
