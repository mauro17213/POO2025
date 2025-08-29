package com.saviasaludeps.savia.solicitud.negocio;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3CargaDetalle;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3CargaSuceso;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Diagnostico;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoDetalle;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.contratacion.CntProfesional;
import com.saviasaludeps.savia.dominio.maestro.MaAgrupadorMedicamento;
import com.saviasaludeps.savia.dominio.maestro.MaDiagnostico;
import com.saviasaludeps.savia.dominio.maestro.MaEspecialidad;
import com.saviasaludeps.savia.dominio.maestro.MaInsumo;
import com.saviasaludeps.savia.dominio.maestro.MaMedicamento;
import com.saviasaludeps.savia.dominio.maestro.MaPaquete;
import com.saviasaludeps.savia.dominio.maestro.MaServicioHabilitacion;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologia;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologiaServicioHabilitacion;
import com.saviasaludeps.savia.solicitud.dominio.TecnologiaDTO;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface CargaMasivaRemoto {

    List<AuAnexo3> insertarCargaMasivaAnexos3(List<AuAnexo3> listaAnexo3, List<AuAnexo3Diagnostico> listaDiagnostico,
            int idCarga, int exitosos, int fallidos) throws Exception;

    boolean insertarAuAnexo3CargaDetalles(List<AuAnexo3CargaDetalle> listaNegocio) throws ClassNotFoundException, SQLException;

    boolean eliminarCargaDetalle(int id);

    List<AsegAfiliado> consultarAfiliados(List<AsegAfiliado> listaAfiliadosCarga);

    List<CntPrestadorSede> consultarSedes(List<CntPrestadorSede> listaSedesCarga);

    List<MaDiagnostico> consultarDiagnosticos();

    List<MaServicioHabilitacion> consultarServiciosHabilitacion();

    List<MaEspecialidad> consultarEspecialidades();

    List<MaTecnologia> consultarTecnologias();

    List<MaTecnologiaServicioHabilitacion> consultarTecnologiaServiciosHabilitacion();

    List<CntProfesional> consultarProfesionales();

    List<MaMedicamento> consultarMedicamentos();

    List<MaAgrupadorMedicamento> consultarAgrupadoresMedicamento();

    List<MaInsumo> consultarInsumos();

    List<MaPaquete> consultarPaquetes();

    int consultarIdAgrupadorPorMedicamento(String cum);

    List<CntContratoDetalle> consultarPorTecnologias(List<Integer> listaTecnologias, int tipoTecnologia);

    List<String> itemsByAfiliadoByFechaOrdenMedica(int afiliado, String tecnologias, String estados, Date fechaOrdenMedica);

    List<TecnologiaDTO> validarTecnologiasDuplicadas(List<TecnologiaDTO> listaValidaciones);

    boolean insertarAuAnexo3CargaSucesos(List<AuAnexo3CargaSuceso> listaNegocio) throws ClassNotFoundException, SQLException;

    String validarCantidadTecnologia(String tipoTecnologia, int cantidad, String codigoTecnologia);
}
