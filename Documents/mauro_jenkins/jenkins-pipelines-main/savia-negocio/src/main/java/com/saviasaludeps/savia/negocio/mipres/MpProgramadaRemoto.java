/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.mipres;

import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionProgramada;
import java.util.List;

/**
 *
 * @author Jaime Andres Olarte
 */
public interface MpProgramadaRemoto {
    
    List<MpPrescripcionProgramada> consultarPorTipoTecnologia(int mpMedicamentoId, int tipoTecnologia)  throws Exception ;
}
