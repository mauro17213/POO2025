/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacionEstancia;
import java.util.Date;
import java.util.List;

/**
 *
 * @author sgiraldov
 */
public interface AucHospitalizacionEstanciaRemoto {

    /**
     * Consulta el objeto dado el id
     *
     * @param id
     * @return
     * @throws Exception
     */
    AucHospitalizacionEstancia consultar(int id) throws Exception;

    /**
     * Inserta el objeto
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(AucHospitalizacionEstancia obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizar(AucHospitalizacionEstancia obj) throws Exception;

    /**
     * Actualiza la descripcion, causa y remision
     *
     * @param obj
     * @throws Exception
     */
    //void actualizarEsctanciaDescripcion(AucHospitalizacionEstancia obj) throws Exception;
    /**
     * Elimina el objeto
     *
     * @param id
     * @return
     * @throws Exception
     */
    AucHospitalizacionEstancia eliminar(int id) throws Exception;

    /**
     * Consulta las Estancias de una hospitalizacion
     *
     * @param idHospitalizacion
     * @return
     * @throws Exception
     */
    List<AucHospitalizacionEstancia> consultarPorIdHospitalizacion(int idHospitalizacion) throws Exception;

    /**
     * Valida el rango de fechas para poder insertar la base de datos
     *
     * @param idHospitalizacion
     * @param fechaIngreso
     * @return
     * @throws Exception
     */
    int validarRangoFechasParaInsertar(Integer idHospitalizacion, Date fechaIngreso) throws Exception;

    /**
     * Consulta todas las estancias abiertas
     *
     * @return
     * @throws Exception
     */
    List<AucHospitalizacionEstancia> consultarActivas() throws Exception;

    /**
     * Actualiza la fecha egreso
     *
     * @param obj
     * @throws Exception
     */
    void actualizarFechaEgreso(AucHospitalizacionEstancia obj) throws Exception;

    /**
     * consulta Estancias activa de afiliados que no estan en el excel
     *
     * @param idAfiliados
     * @param sede
     * @return
     * @throws Exception
     */
    List<AucHospitalizacionEstancia> consultarActivasExcel(String idAfiliados, int sede) throws Exception;

    /**
     * permite consultar ultima hospitalizacion del afiliado con fecha egreso
     * @param idAfiliado
     * @return
     */
    AucHospitalizacionEstancia consultarUltimaHospitalizacionAfiliado(int idAfiliado) throws Exception;

}
