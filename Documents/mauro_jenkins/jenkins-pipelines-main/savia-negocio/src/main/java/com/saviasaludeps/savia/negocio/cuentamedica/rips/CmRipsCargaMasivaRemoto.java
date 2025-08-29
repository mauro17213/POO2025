package com.saviasaludeps.savia.negocio.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsAcConsulta;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsAdServiciosAgrupado;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsAfFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsAhHospitalizacion;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsAmMedicamento;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsAnRecienNacido;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsApProcedimiento;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsAtOtrosServicio;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsAuUrgencia;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsCtControlObj;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsUsUsuario;
import java.util.List;

public interface CmRipsCargaMasivaRemoto {

    /**
     * Insertar Lista de cargas RIPS
     *
     * @param obj CmRipsCarga
     * @param tipo carga o tabla final
     * @throws Exception
     */
    void insertarCargaMasivaAc(List<CmRipsAcConsulta> obj, int tipo) throws Exception;

    void insertarCargaMasivaAp(List<CmRipsApProcedimiento> obj, int tipo) throws Exception;

    void insertarCargaMasivaAt(List<CmRipsAtOtrosServicio> obj, int tipo) throws Exception;

    void insertarCargaMasivaAh(List<CmRipsAhHospitalizacion> obj, int tipo) throws Exception;

    void insertarCargaMasivaAn(List<CmRipsAnRecienNacido> obj, int tipo) throws Exception;

    void insertarCargaMasivaAm(List<CmRipsAmMedicamento> obj, int tipo) throws Exception;

    void insertarCargaMasivaAf(List<CmRipsAfFactura> obj, int tipo) throws Exception;

    void insertarCargaMasivaUs(List<CmRipsUsUsuario> obj, int tipo) throws Exception;

    void insertarCargaMasivaAd(List<CmRipsAdServiciosAgrupado> obj, int tipo) throws Exception;
    
    void insertarCargaMasivaAu(List<CmRipsAuUrgencia> obj, int tipo) throws Exception;

    /**
     * Insertar la tabla temporal de cargas en la tabla final
     *
     * @param id de CmRipsCarga
     * @throws Exception
     */

    /**
     * Insertar datos del RIPS en tablas de carga hacia tablas finales de RIPS
     *
     * @param id de carga
     * @return valor incremental
     * @throws Exception
     */
    int insertarCargas(int id) throws Exception;

    /**
     * Insertar control de carga Rips CT
     *
     * @param obj CmRipsCtControl
     * @throws Exception
     */
    int insertarCargaCt(CmRipsCtControlObj obj, int tipo) throws Exception;

    //TODO void eliminarCargas(intid) throws Exception;
}
