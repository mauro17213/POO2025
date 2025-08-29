package com.saviasaludeps.savia.negocio.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsInhabilitado;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

public interface CmRipsInhabilitadoRemoto {
    
    /**
     * Consultar si la modalidad se encuentra inhabilitada en un rango de fecha
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<CmRipsInhabilitado> consultarModalidadInhabilitada(ParamConsulta paramConsulta) throws Exception;
}
