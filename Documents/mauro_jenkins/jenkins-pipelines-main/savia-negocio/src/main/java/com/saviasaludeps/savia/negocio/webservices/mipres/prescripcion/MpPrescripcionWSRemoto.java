package com.saviasaludeps.savia.negocio.webservices.mipres.prescripcion;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.contratacion.CntProfesional;
import com.saviasaludeps.savia.dominio.mipres.MpAfiliado;
import com.saviasaludeps.savia.dominio.mipres.MpCodigoUnirss;
import com.saviasaludeps.savia.dominio.mipres.MpMedicamentoIndicacionUnirs;
import com.saviasaludeps.savia.dominio.mipres.MpMedicamentoPrincipioActivo;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcion;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionHistorico;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionInsumo;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionMedicamento;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionRecobrante;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionTecnologia;
import java.util.List;
import java.util.Map;

public interface MpPrescripcionWSRemoto {

    Map<String, AsegAfiliado> consultarListaAfiliados(
            List<String> listaAfiliadoPrescripcion) throws Exception;

    Map<String, CntProfesional> consultarListaProfesionales(
            List<String> listaProfesionalPrescripcion) throws Exception;

    Map<String, MpPrescripcion> consultarListaPrescripciones(
            List<String> listaNoPrescripciones) throws java.lang.Exception;

    Map<String, CntPrestadorSede> consultarListaSedes(
            List<String> listaSedes) throws Exception;

    MpAfiliado consultarMpAfiliado(String tipodocumento, String numeroDocumento) throws Exception;

    int insertarMpAfiliado(MpAfiliado afiliado) throws Exception;

    int insertarMpPrescripcion(MpPrescripcion prescripcion) throws Exception;

    int insertarMpPrescripcionTecnologia(MpPrescripcionTecnologia tecnologia) throws Exception;

    int insertarMpPrescripcionMedicamento(MpPrescripcionMedicamento medicamento) throws Exception;

    int insertarMpPrescripcionHistorico(MpPrescripcionHistorico historico) throws Exception;

    int insertarMpPrescripcionPrincAct(MpMedicamentoPrincipioActivo principio) throws Exception;

    int insertarMpPrescripcionIndicacionUnirs(MpMedicamentoIndicacionUnirs unirs) throws Exception;

    int insertarMpPrescripcionInsumo(MpPrescripcionInsumo insumo) throws Exception;

    int insertarMpPrescripcionRecobrante(MpPrescripcionRecobrante prescripcion) throws Exception;

    String consultarRazonSocial(String documento) throws Exception;

    List<MpPrescripcion> consultarListaPrescripcionesPorAnular() throws java.lang.Exception;

    void gestionarDetalleMedicamento(String prescripcion, Integer consecutivoOrden, Integer tipoTecnologia, String dosisUM) throws Exception;

    void gestionarDetalleNutri(String prescripcion, Integer consecutivoOrden, Integer tipoTecnologia, String dosisUM, String tippProNut, String descProdNutr, String codForma) throws Exception;

    void gestionarDetalleComple(String prescripcion, Integer consecutivoOrden, Integer tipoTecnologia, String descSerComp,
            Integer codFreUso,
            Integer tipoTrans,
            Integer reqAcom,
            String tipoIDAcomAlb,
            String nroIDAcomAlb,
            Integer parentAcomAlb,
            String nombAlb,
            Integer codMunOriAlb,
            Integer codMunDesAlb
    ) throws Exception;

    void gestionarDetalleProce(String prescripcion, Integer consecutivoOrden, Integer tipoTecnologia, String cadaFreUso, String indRec) throws Exception;

    MpCodigoUnirss consultarMpCodigoUnir(String codigo) throws Exception;
}
