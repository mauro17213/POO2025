package com.saviasaludeps.savia.negocio.administracion;

import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author rpalacic
 */
public interface UbicacionRemoto {

    /**
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<Ubicacion> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para consultar una empresa por ID
     *
     * @param id
     * @return
     * @throws java.lang.Exception
     */
    Ubicacion consultar(int id) throws Exception;

    /**
     * Método para consultar una ubicacion por nombre y tipo
     *
     * @param nombre
     * @param tipo
     * @return
     * @throws java.lang.Exception
     */
    Ubicacion consultarPorNombreTipo(String nombre, int tipo) throws Exception;

    /**
     * Método para crear una nueva Ubicacion
     *
     * @param obj (Ubicaciones)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(Ubicacion obj) throws Exception;

    /**
     * Método para actualizar la información de una Ubicacion
     *
     * @param obj (Ubicaciones)
     * @throws java.lang.Exception
     */
    void actualizar(Ubicacion obj) throws Exception;

    /**
     * Método para eliminar una Ubicacion
     *
     * @param id
     * @return (Ubicaciones) Objeto eliminado
     * @throws java.lang.Exception
     */
    Ubicacion eliminar(int id) throws Exception;

    /**
     * Método para consultar todas las Ubicaciones activas.
     *
     * @return
     * @throws java.lang.Exception
     */
    List<Ubicacion> consultarActivas() throws Exception;

    /**
     * Metodo que permite actualizar Municipo por prefijo
     *
     * @param departamentoPrefijo
     * @param municipioPrefijo
     * @return
     * @throws Exception
     */
    Ubicacion consultarMunicipiosPorPrefijo(String departamentoPrefijo, String municipioPrefijo) throws Exception;

    /**
     * Metodo para consultar pais a partir de su prefijo
     *
     * @param paisPrefijo
     * @return
     * @throws Exception
     */
    Ubicacion consultarPaisPorPrefijo(String paisPrefijo) throws Exception;

    /**
     * Metodo que permite actualizar Municipo por prefijo y que apliquen
     * cobertura
     *
     * @param departamentoPrefijo
     * @param municipioPrefijo
     * @return
     * @throws Exception
     */
    Ubicacion consultarMunicipiosPorPrefijoCobertura(String departamentoPrefijo, String municipioPrefijo) throws Exception;

    /**
     * Consultar Hash ubicaciones
     *
     * @return
     * @throws Exception
     */
    HashMap<Integer, Ubicacion> consultarHashActivas() throws Exception;

    /**
     * Metodo que consulta solo las nuevas creadas
     *
     * @param time
     * @return
     * @throws Exception
     */
    List<Ubicacion> consultarActivasNuevas(Long time) throws Exception;

}
