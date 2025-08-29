package com.saviasaludeps.savia.negocio.especial;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadosPrograma;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jaime Andres Olarte
 */
public interface PeAfiliadosProgramaRemoto {

    /**
     * Consulta de cantidad de registros en una lista
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consultar lista de registros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<PeAfiliadosPrograma> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consulta un registro PeAfiliadosPrograma por id
     *
     * @param id
     * @return PeAfiliadosPrograma
     * @throws Exception
     */
    PeAfiliadosPrograma consultar(int id) throws Exception;

    List<PeAfiliadosPrograma> consultarAfiliados(int idAfiliado) throws Exception;

    List<PeAfiliadosPrograma> consultarAfiliadoActivo(int idAfiliado) throws Exception;

    /**
     * Inserta un registro PeAfiliadosProgramas
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(PeAfiliadosPrograma obj) throws Exception;

    /**
     * Actualiza un registro de tipo PeAfiliadosProgramas
     *
     * @param obj
     * @throws Exception
     */
    void actualizar(PeAfiliadosPrograma obj) throws Exception;

    /**
     * Elimina un registro PeAfiliadosPrograma
     *
     * @param id
     * @return
     * @throws Exception
     */
    PeAfiliadosPrograma eliminar(int id) throws Exception;

    /**
     * Consulta la cantidad de registros de un afiliado en un programa segun el
     * estado del programa
     *
     * @param idAfiliado
     * @param idPrograma
     * @param estadoPrograma
     * @return
     * @throws Exception
     */
    int consultarCantidadProgramaEstado(int idAfiliado, int idPrograma, Integer estadoPrograma) throws Exception;

    /**
     * consulta los registros para un afiliado por programa y estado
     * (Activo/Inactivo)
     *
     * @param idAfiliado
     * @param idPrograma
     * @param estado
     * @return
     * @throws Exception
     */
    List<PeAfiliadosPrograma> consultarAfiliadoPorProgramaEstado(int idAfiliado, int idPrograma, boolean estado) throws Exception;

    /**
     *
     * @param idAfiliado
     * @param idPrograma
     * @param fechaFin
     * @return
     * @throws Exception
     */
    PeAfiliadosPrograma consultarAfiliadoPorProgramaFechaFin(int idAfiliado, int idPrograma, Date fechaFin) throws Exception;

    /**
     * Consulta de cantidad de registros en una lista
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadListaPorAfiliado(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consultar lista de registros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<PeAfiliadosPrograma> consultarListaPorAfiliado(ParamConsulta paramConsulta) throws Exception;

    PeAfiliadosPrograma consultarAfiliadoPorPrograma(int idAfiliado, int idPrograma) throws Exception;

    /**
     * Actualiza el estado del afiliado programa por afiliado aseg id y pe
     * programa id
     *
     * @param obj
     * @throws Exception
     */
    void actualizarEstadoActivo(PeAfiliadosPrograma obj) throws Exception;

    /**
     *
     * @param idAfiliado
     * @param idPrograma
     * @param idAfiliadoPrograma
     * @return
     * @throws Exception
     */
    int consultarDuplicadoProgramaAfiliadoActivo(int idAfiliado, int idPrograma, int idAfiliadoPrograma) throws Exception;

    /**
     * Metodo encargado de cambiar el estado de todos los afiliado asociados a
     * un programa especifico
     *
     * @author idbohorquez
     * @param idPrograma
     * @param causaEstado
     * @throws Exception
     */
    void cambiarEstadoAfiliadosPrograma(PePrograma programa, Maestro causaEstado) throws Exception;

    /**
     * Función encargada de validar si un afiliado esta inscrito en el listado
     * de programas recibidos.
     *
     * @author idbohorquez
     * @creado 15/09/2022
     * @param idAfiliado
     * @param idProgramas
     * @return List<PeAfiliadosPrograma>
     * @throws Exception
     */
    List<PeAfiliadosPrograma> programasMatriculadosAfiliado(Integer idAfiliado, String idProgramas) throws Exception;

    /**
     * Función encargada de validar si un afiliado esta inscrito en el listado
     * de programas recibidos sin importar su su sede es sin especificar.
     *
     * @author idbohorquez
     * @creado 15/09/2022
     * @param idAfiliado
     * @param idProgramas
     * @param diagnosticos
     * @return List<PeAfiliadosPrograma>
     * @throws Exception
     */
    List<PeAfiliadosPrograma> programasMatriculadosAfiliadoAllSede(Integer idAfiliado, String idProgramas, String diagnosticos) throws Exception;

    /**
     * Función encargada de consultar afilaidos programas segun filtros
     * recibidos por parametros
     *
     * @author idbohorquez
     * @creado 14/04/2023
     * @param idAfilaido
     * @param ambito
     * @param diagnosticos
     * @return List<PeAfiliadosPrograma>
     * @throws Exception
     */
    List<PeAfiliadosPrograma> aplicarRescateAnexo3(Integer idAfilaido, Integer ambito, String diagnosticos) throws Exception;

    /**
     * Lista los programas del afiliado por codigoPrograma
     *
     * @param idAfiliado
     * @param codigoPrograma
     * @return
     * @throws Exception
     */
    List<PeAfiliadosPrograma> listarPorAfiliadoYCodigoPrograma(Integer idAfiliado, String codigoPrograma) throws Exception;

    /**
     * Consulta la cantidad de afiliados que continene un programa especifico
     *
     * @param idPrograma
     * @return int
     * @throws Exception
     */
    int consultarCantidadAfiliadoPrograma(int idPrograma) throws Exception;
}
