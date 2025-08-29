package com.saviasaludeps.savia.negocio.webservices.mipres.prescripcion;

import com.saviasaludeps.savia.dominio.mipres.DireccionamientoBaseDTO;
import com.saviasaludeps.savia.dominio.mipres.MpDireccionamiento;
import com.saviasaludeps.savia.dominio.mipres.MpNoDireccionado;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionInsumo;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionMedicamento;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionTecnologia;
import java.util.List;
import java.util.Map;

public interface MpPrescripcionTecnologiaWSRemoto {

    Map<String, MpPrescripcionMedicamento> consultarListaMpMedicamentos(
            List<MpPrescripcionMedicamento> listamedicamentos) throws Exception;

    Map<String, MpPrescripcionTecnologia> consultarListaMpTecnologias(
            List<MpPrescripcionTecnologia> listaTecnologia) throws Exception;

    Map<String, MpPrescripcionInsumo> consultarListaMpInsumos(
            List<MpPrescripcionInsumo> listaInsumos) throws Exception;

//    Map<String, MpPrescripcionInsumo> consultarListaMpInsumosSublistas(
//            List<List<MpPrescripcionInsumo>> sublistasInsumos) throws Exception;
    void actualizarJuntaMpPrescripcionTecnologia(
            MpPrescripcionTecnologia tecnologia) throws Exception;

    void actualizarJuntaMpPrescripcionMedicamento(
            MpPrescripcionMedicamento medicamento) throws Exception;

    void actualizarJuntaMpPrescripcionInsumo(
            MpPrescripcionInsumo insumo) throws Exception;

    List<MpDireccionamiento> consultarDireccionamientosPorIdPrescripcion(int id);

    List<MpNoDireccionado> consultarNoDireccionamientosPorIdPrescripcion(int id);

    void cambiarEstadoDireccionamiento(
            Integer id, Integer estado) throws Exception;
    
     void cambiarEstadoNoDireccionamiento(
            Integer id, Integer estado,String fechaAnula) throws Exception;

  

   
}
