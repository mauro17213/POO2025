package com.saviasaludeps.savia.negocio.informe;

import com.saviasaludeps.savia.dominio.informe.InfDescargado;
import java.util.List;

public interface InformeDescargadoRemoto {

    /**
     * Guardar un reporte de informe descargado
     *
     * @param descargado
     * @return
     * @throws java.lang.Exception
     */
    int insertar(InfDescargado descargado) throws Exception;

    /**
     * Guardar un reporte de informe descargado
     *
     * @param id
     * @return
     * @throws java.lang.Exception
     */
    List<InfDescargado> listarPorGenerado(int id) throws Exception;

}
