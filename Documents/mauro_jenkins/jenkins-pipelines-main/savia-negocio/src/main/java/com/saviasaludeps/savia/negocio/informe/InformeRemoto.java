package com.saviasaludeps.savia.negocio.informe;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.informe.InfInforme;
import java.util.List;

public interface InformeRemoto {

    /**
     * Metodo para contar listar informes
     *
     * @param paramConsulta
     * @return
     * @throws java.lang.Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Metodo para listar informes
     *
     * @param paramConsulta
     * @return
     * @throws java.lang.Exception
     */
    List<InfInforme> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Metodo para listar informes
     *
     * @param paramConsulta
     * @return
     * @throws java.lang.Exception
     */
    List<InfInforme> consultarListaPlantillas(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consultar un informe por id
     *
     * @param id
     * @return
     * @throws java.lang.Exception
     */
    InfInforme consultar(int id) throws Exception;

    /**
     * Consultar un informe por nombre
     *
     * @param nombre
     * @return
     * @throws java.lang.Exception
     */
    boolean existeInformePorNombre(String nombre) throws Exception;

    /**
     * Guardar un informe
     *
     * @param informe
     * @return
     * @throws java.lang.Exception
     */
    int insertar(InfInforme informe) throws Exception;

    /**
     * Modificar un informe
     *
     * @param informe
     * @throws java.lang.Exception
     */
    void actualizar(InfInforme informe) throws Exception;

    /**
     * Borrar un informe por id
     *
     * @param id
     * @return
     * @throws java.lang.Exception
     */
    InfInforme eliminar(int id) throws Exception;
    
    /**
     * Lista los informes no programados
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<InfInforme> consultarListaPlantillasNoProgramados(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consulta todos los informes
     * @return
     * @throws Exception 
     */
    List<InfInforme> consultarTodos() throws Exception;
    
    /**
     * Lista los informes por grupos
     * @param lista
     * @return
     * @throws Exception 
     */
    List<InfInforme> consultarPorListaGrupo(String lista) throws Exception;
    
    /**
     * Lista de empresas por informe
     * @param idInforme
     * @return
     * @throws Exception 
     */
    List<Empresa> consultarEmpresasPorInforme(int idInforme) throws Exception;    

}
