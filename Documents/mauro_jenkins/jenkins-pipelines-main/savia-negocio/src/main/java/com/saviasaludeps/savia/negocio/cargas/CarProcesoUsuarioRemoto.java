package com.saviasaludeps.savia.negocio.cargas;

import com.saviasaludeps.savia.dominio.cargas.CarProceso;
import com.saviasaludeps.savia.dominio.cargas.CarProcesoUsuario;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author aguevara
 */
public interface CarProcesoUsuarioRemoto {
    
    
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    List<CarProcesoUsuario> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    CarProcesoUsuario consultar(int id) throws Exception;
    
    boolean validarExiste(String nombre) throws Exception;
    
    int insertar(CarProcesoUsuario obj) throws Exception;
    
    CarProcesoUsuario eliminar(int id) throws Exception;
    
    void actualizar(CarProcesoUsuario per) throws Exception;
    
    List<CarProcesoUsuario> listarPorIdProceso(int id) throws Exception;
    
    List<CarProcesoUsuario> listarPorIdUsuario(int id) throws Exception;

    List<CarProceso> listarProcesosUnicosPorUsuario(int idUsuario, ParamConsulta paramConsulta) throws Exception;
}
