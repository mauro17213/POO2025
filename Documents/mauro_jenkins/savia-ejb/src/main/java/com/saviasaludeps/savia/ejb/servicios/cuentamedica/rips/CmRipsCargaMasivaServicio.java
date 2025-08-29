package com.saviasaludeps.savia.ejb.servicios.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsAcConsulta;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsAdServiciosAgrupado;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsAhHospitalizacion;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsAmMedicamento;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsAnRecienNacido;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsAtOtrosServicio;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsAuUrgencia;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsCarga;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsCargaAnexo;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsAfFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsApProcedimiento;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsCargaEstado;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsCtControlObj;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsEstructuraError;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsSuceso;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsUsUsuario;
import com.saviasaludeps.savia.ejb.entidades.CmRipsCargas;
import com.saviasaludeps.savia.ejb.entidades.CmRipsEstructuraErrores;
import com.saviasaludeps.savia.ejb.entidades.CmRipsCargaUsUsuarios;
import com.saviasaludeps.savia.ejb.entidades.CmRipsAfFacturas;
import com.saviasaludeps.savia.ejb.entidades.CmRipsAcConsultas;
import com.saviasaludeps.savia.ejb.entidades.CmRipsAmMedicamentos;
import com.saviasaludeps.savia.ejb.entidades.CmRipsAtOtrosServicios;
import com.saviasaludeps.savia.ejb.entidades.CmRipsApProcedimientos;
import com.saviasaludeps.savia.ejb.entidades.CmRipsAnRecienNacidos;
import com.saviasaludeps.savia.ejb.entidades.CmRipsAhHospitalizaciones;
import com.saviasaludeps.savia.ejb.entidades.CmRipsCtControl;
import com.saviasaludeps.savia.ejb.entidades.CmRipsUsUsuarios;
import com.saviasaludeps.savia.ejb.entidades.CmRipsAdServiciosAgrupados;
import com.saviasaludeps.savia.ejb.entidades.CmRipsAuUrgencias;
import com.saviasaludeps.savia.ejb.entidades.CmRipsCargaAnexos;
import com.saviasaludeps.savia.ejb.entidades.CmRipsCargaAfFacturas;
import com.saviasaludeps.savia.ejb.entidades.CmRipsCargaAcConsultas;
import com.saviasaludeps.savia.ejb.entidades.CmRipsCargaAmMedicamentos;
import com.saviasaludeps.savia.ejb.entidades.CmRipsCargaAtOtrosServicios;
import com.saviasaludeps.savia.ejb.entidades.CmRipsCargaApProcedimientos;
import com.saviasaludeps.savia.ejb.entidades.CmRipsCargaAnRecienNacidos;
import com.saviasaludeps.savia.ejb.entidades.CmRipsCargaAhHospitalizaciones;
import com.saviasaludeps.savia.ejb.entidades.CmRipsCargaCtControl;
import com.saviasaludeps.savia.ejb.entidades.CmRipsCargaEstados;
import com.saviasaludeps.savia.ejb.entidades.CmRipsCargaAdServiciosAgrupados;
import com.saviasaludeps.savia.ejb.entidades.CmRipsCargaAuUrgencias;
import com.saviasaludeps.savia.ejb.entidades.CmRipsSucesos;
import com.saviasaludeps.savia.ejb.entidades.CntContratos;
import com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.negocio.cuentamedica.rips.CmRipsCargaMasivaRemoto;

@Stateless
@Remote(CmRipsCargaMasivaRemoto.class)
public class CmRipsCargaMasivaServicio extends GenericoServicio implements CmRipsCargaMasivaRemoto {

    final static int CANTIDAD_BATCH = 1000;

    @Override
    public void insertarCargaMasivaAc(List<CmRipsAcConsulta> listaNegocio, int tipo) throws Exception {
        
    }

    @Override
    public void insertarCargaMasivaAu(List<CmRipsAuUrgencia> listaNegocio, int tipo) throws Exception {
        
    }

    @Override
    public void insertarCargaMasivaAp(List<CmRipsApProcedimiento> listaNegocio, int tipo) throws java.lang.Exception {
        
    }

    @Override
    public void insertarCargaMasivaAt(List<CmRipsAtOtrosServicio> listaNegocio, int tipo) throws java.lang.Exception {
        
    }

    @Override
    public void insertarCargaMasivaAh(List<CmRipsAhHospitalizacion> listaNegocio, int tipo) throws java.lang.Exception {
       
    }

    @Override
    public void insertarCargaMasivaAn(List<CmRipsAnRecienNacido> listaNegocio, int tipo) throws java.lang.Exception {
       
    }

    @Override
    public void insertarCargaMasivaAm(List<CmRipsAmMedicamento> listaNegocio, int tipo) throws java.lang.Exception {
       
    }

    @Override
    public void insertarCargaMasivaAf(List<CmRipsAfFactura> listaNegocio, int tipo) throws java.lang.Exception {
        
    }

    @Override
    public void insertarCargaMasivaUs(List<CmRipsUsUsuario> listaNegocio, int tipo) throws java.lang.Exception {
        
    }

    @Override
    public void insertarCargaMasivaAd(List<CmRipsAdServiciosAgrupado> listaNegocio, int tipo) throws java.lang.Exception {
        
    }

    @Override
    public int insertarCargas(int id) throws Exception {
       return 0;
    }

    @Override
    public int insertarCargaCt(CmRipsCtControlObj obj, int tipo) throws java.lang.Exception {
        return 0;
    }

    /*
    NEGOCIO ENTIDAD CAST CARGA
     */
    public static CmRipsCargas castNegocioEntidad(CmRipsCarga obj) {
        CmRipsCargas ent = new CmRipsCargas();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        if (obj.getGnPrestadorSede() != null) {
            ent.setGnPrestadorSedesId(new CntPrestadorSedes(obj.getGnPrestadorSede().getId()));
        }
        if (obj.getCntContrato() != null && obj.getCntContrato().getId() != null) {
            ent.setCntContratosId( new CntContratos( obj.getCntContrato().getId()));
            ent.setCntTipoContratoId(obj.getCntTipoContratoId());
        }
        ent.setValorCarga(obj.getValorCarga());
        //MAE REGIMEN
        if (obj.getMaeRegimenCodigo() != null) {
            ent.setMaeRegimenCodigo(obj.getMaeRegimenCodigo());
            if (obj.getMaeRegimenId() != null) {
                ent.setMaeRegimenId(obj.getMaeRegimenId());
            }
            if (obj.getMaeRegimenValor() != null) {
                ent.setMaeRegimenValor(obj.getMaeRegimenValor());
            }
        }
        //MAE GN_UBICACION REGIONAL
        if (obj.getMaeRegionalCodigo() != null) {
            ent.setMaeReginalCodigo(obj.getMaeRegionalCodigo());
            if (obj.getMaeRegionalId() != null) {
                ent.setMaeRegionalId(obj.getMaeRegionalId());
            }
            if (obj.getMaeRegionalValor() != null) {
                ent.setMaeRegionalValor(obj.getMaeRegionalValor());
            }
        }
        //MAE MODALIDAD
        if (obj.getMaeContratoModalidadCodigo() != null) {
            ent.setMaeContratoModalidadCodigo(obj.getMaeContratoModalidadCodigo());
            if (obj.getMaeContratoModalidadId() != null) {
                ent.setMaeContratoModalidadId(obj.getMaeContratoModalidadId());
            }
            if (obj.getMaeContratoModalidadValor() != null) {
                ent.setMaeContratoModalidadValor(obj.getMaeContratoModalidadValor());
            }
        }
        if (obj.getNumeroCuenta() != null) {
            ent.setNumeroCuenta(obj.getNumeroCuenta());
        }
        ent.setFechaPrestacion(obj.getFechaPrestacion());
        ent.setEstado(obj.getEstado());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    public static CmRipsCargaCtControl castNegocioEntidad(CmRipsCtControlObj obj) {
        CmRipsCargaCtControl ent = new CmRipsCargaCtControl();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        ent.setCodigoReps(obj.getCodigoReps());
        ent.setCmRipsCargasId(new CmRipsCargas(obj.getCmRipsCarga().getId()));
        ent.setArchivoControl(obj.getArchivoControl());
        ent.setArchivoAf(obj.getArchivoAf());
        ent.setArchivoUs(obj.getArchivoUs());
        ent.setArchivoAd(obj.getArchivoAd());
        ent.setArchivoAc(obj.getArchivoAc());
        ent.setArchivoAp(obj.getArchivoAp());
        ent.setArchivoAh(obj.getArchivoAh());
        ent.setArchivoAu(obj.getArchivoAu());
        ent.setArchivoAn(obj.getArchivoAn());
        ent.setArchivoAm(obj.getArchivoAm());
        ent.setArchivoAt(obj.getArchivoAt());
        ent.setRegistrosAf(obj.getRegistrosAf());
        ent.setRegistroAc(obj.getRegistroAc());
        ent.setRegistrosUs(obj.getRegistrosUs());
        ent.setRegistrosAp(obj.getRegistrosAp());
        ent.setRegistrosAh(obj.getRegistrosAh());
        ent.setRegistrosAu(obj.getRegistrosAu());
        ent.setRegistrosAm(obj.getRegistrosAm());
        ent.setRegistrosAt(obj.getRegistrosAt());
        ent.setRegistrosAn(obj.getRegistrosAn());
        ent.setRegistrosAd(obj.getRegistrosAd());
        ent.setFechaAf(obj.getFechaAf());
        ent.setFechaUs(obj.getFechaUs());
        ent.setFechaAc(obj.getFechaAc());
        ent.setFechaAp(obj.getFechaAp());
        ent.setFechaAh(obj.getFechaAh());
        ent.setFechaAu(obj.getFechaAu());
        ent.setFechaAm(obj.getFechaAm());
        ent.setFechaAt(obj.getFechaAt());
        ent.setFechaAn(obj.getFechaAn());
        ent.setFechaAd(obj.getFechaAd());
        ent.setArchivoNombre(obj.getArchivoNombre());
        ent.setArchivoRuta(obj.getArchivoRuta());
        ent.setArchivoNombreOriginal(obj.getArchivoNombreOriginal());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    public static CmRipsCargaAnexos castNegocioEntidad(CmRipsCargaAnexo obj) {
        CmRipsCargaAnexos ent = new CmRipsCargaAnexos();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        ent.setCmRipsCargasId(new CmRipsCargas(obj.getCmRipsCarga().getId()));
        ent.setTipoArchivo(obj.getTipoStr());
        ent.setArchivoNombreOriginal(obj.getArchivoNombreOriginal());
        ent.setArchivoRuta(obj.getArchivoRuta());
        ent.setArchivoNombre(obj.getArchivoNombre());
        ent.setExiste(obj.isExiste());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    public static CmRipsSucesos castNegocioEntidad(CmRipsSuceso obj) {
        CmRipsSucesos ent = new CmRipsSucesos();
//        if (obj.getId() != null) {
//            ent.setId(obj.getId());
//        }
//        ent.setCmRipsCargasId(new CmRipsCargas(obj.getCmRipsCarga().getId()));
//        ent.setArchivoNombre(obj.getArchivoNombre());
//        ent.setArchivoFila(obj.getArchivoFila());
//        ent.setDescripcionError(obj.getDescripcionError());
        return ent;
    }

    public static CmRipsCargaEstados castNegocioEntidad(CmRipsCargaEstado obj) {
        CmRipsCargaEstados ent = new CmRipsCargaEstados();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        ent.setCmRipsCargasId(new CmRipsCargas(obj.getCmRipsCarga().getId()));
        ent.setEstado(obj.getEstado());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    public static CmRipsCargaAcConsultas castNegocioEntidad(CmRipsAcConsulta obj) {
        CmRipsCargaAcConsultas ent = new CmRipsCargaAcConsultas();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        ent.setFila(obj.getFila());
        ent.setCmRipsCargasId(new CmRipsCargas(obj.getCmRipsCarga().getId()));
        ent.setNumFactura(obj.getNumFactura());
        ent.setCodigoReps(obj.getCodigoReps());
        ent.setArchivoRuta(obj.getArchivoRuta());
        ent.setArchivoNombreOriginal(obj.getArchivoNombreOriginal());
        ent.setArchivoNombre(obj.getArchivoNombre());
        ent.setFila(obj.getFila());
        //MAE TIPO DOCUMENTO
        if (obj.getMaeTipoDocumentoCodigo() != null) {
            ent.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
            if (obj.getMaeTipoDocumentoId() != null) {
                ent.setMaeTipoDocumentoId(obj.getMaeTipoDocumentoId());
            }
            if (obj.getMaeTipoDocumentoValor() != null) {
                ent.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());
            }
        }
        ent.setDocumentoAfiliado(obj.getDocumentoAfiliado());
        if (obj.getFechaConsulta() != null) {
            ent.setFechaConsulta(obj.getFechaConsulta());
        }
        ent.setAutorizacion(obj.getAutorizacion());
        if (obj.getMaTecnologiaId() != null) {
            ent.setMaTecnologiaId(obj.getMaTecnologiaId());
        }
        if (obj.getMaTecnologiaCodigo() != null) {
            ent.setMaTecnologiaCodigo(obj.getMaTecnologiaCodigo());
        }
        if (obj.getMaTecnologiaValor() != null) {
            ent.setMaTecnologiaValor(obj.getMaTecnologiaValor());
        }
        //MAE FINALIDAD CONSULTA
        if (obj.getMaeFinalidadConsultaCodigo() != null) {
            ent.setMaeFinalidadConsultaCodigo(obj.getMaeFinalidadConsultaCodigo());
            if (obj.getMaeFinalidadConsultaId() != null) {
                ent.setMaeFinalidadConsultaId(obj.getMaeFinalidadConsultaId());
            }
            if (obj.getMaeFinalidadConsultaValor() != null) {
                ent.setMaeFinalidadConsultaValor(obj.getMaeFinalidadConsultaValor());
            }
        }
        //MAE CAUSA EXTERNA
        if (obj.getMaeCausaExternaCodigo() != null) {
            ent.setMaeCausaExternaCodigo(obj.getMaeCausaExternaCodigo());
            if (obj.getMaeCausaExternaId() != null) {
                ent.setMaeCausaExternaId(obj.getMaeCausaExternaId());
            }
            if (obj.getMaeCausaExternaValor() != null) {
                ent.setMaeCausaExternaValor(obj.getMaeCausaExternaValor());
            }
        }
        //MA DIAGNOSTICO
        if (obj.getMaDiagnosticoPrincipalCodigo() != null) {
            ent.setMaDiagnosticoPrincipalCodigo(obj.getMaDiagnosticoPrincipalCodigo());
            if (obj.getMaDiagnosticoPrincipalId() != null) {
                ent.setMaDiagnosticoPrincipalId(obj.getMaDiagnosticoPrincipalId());
            }
            if (obj.getMaDiagnosticoPrincipalValor() != null) {
                ent.setMaDiagnosticoPrincipalValor(obj.getMaDiagnosticoPrincipalValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO1
        if (obj.getMaDiagnosticoRelacionado1Codigo() != null) {
            ent.setMaDiagnosticoRelacionado1Codigo(obj.getMaDiagnosticoRelacionado1Codigo());
            if (obj.getMaDiagnosticoRelacionado1Id() != null) {
                ent.setMaDiagnosticoRelacionado1Id(obj.getMaDiagnosticoRelacionado1Id());
            }
            if (obj.getMaDiagnosticoRelacionado1Valor() != null) {
                ent.setMaDiagnosticoRelacionado1Valor(obj.getMaDiagnosticoRelacionado1Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO2
        if (obj.getMaDiagnosticoRelacionado2Codigo() != null) {
            ent.setMaDiagnosticoRelacionado2Codigo(obj.getMaDiagnosticoRelacionado2Codigo());
            if (obj.getMaDiagnosticoRelacionado2Id() != null) {
                ent.setMaDiagnosticoRelacionado2Id(obj.getMaDiagnosticoRelacionado2Id());
            }
            if (obj.getMaDiagnosticoRelacionado2Valor() != null) {
                ent.setMaDiagnosticoRelacionado2Valor(obj.getMaDiagnosticoRelacionado2Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO3
        if (obj.getMaDiagnosticoRelacionado3Codigo() != null) {
            ent.setMaDiagnosticoRelacionado3Codigo(obj.getMaDiagnosticoRelacionado3Codigo());
            if (obj.getMaDiagnosticoRelacionado3Id() != null) {
                ent.setMaDiagnosticoRelacionado3Id(obj.getMaDiagnosticoRelacionado3Id());
            }
            if (obj.getMaDiagnosticoRelacionado3Valor() != null) {
                ent.setMaDiagnosticoRelacionado3Valor(obj.getMaDiagnosticoRelacionado3Valor());
            }
        }
        //MA TIPO DIAGNOSTICO 
        if (obj.getMaeTipoDiagnosticoCodigo() != null) {
            ent.setMaeTipoDiagnosticoCodigo(obj.getMaeTipoDiagnosticoCodigo());
            if (obj.getMaeTipoDiagnosticoId() != null) {
                ent.setMaeTipoDiagnosticoId(obj.getMaeTipoDiagnosticoId());
            }
            if (obj.getMaeTipoDiagnosticoValor() != null) {
                ent.setMaeTipoDiagnosticoValor(obj.getMaeTipoDiagnosticoValor());
            }
        }
        ent.setValorConsulta(obj.getValorConsulta());
        ent.setValorCuotaModeradora(obj.getValorCuotaModeradora());
        ent.setValorAPagar(obj.getValorAPagar());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    public static CmRipsCargaAdServiciosAgrupados castNegocioEntidad(CmRipsAdServiciosAgrupado obj) {
        CmRipsCargaAdServiciosAgrupados ent = new CmRipsCargaAdServiciosAgrupados();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        ent.setFila(obj.getFila());
        ent.setCmRipsCargasId(new CmRipsCargas(obj.getCmRipsCarga().getId()));
        ent.setNumeroFactura(obj.getNumeroFactura());
        ent.setCodigoReps(obj.getCodigoReps());
        ent.setCantidadServicios(obj.getCantidadServicio());
        ent.setValorUnitario(obj.getValorUnitario());
        ent.setValorConcepto(obj.getValorConcepto());
        ent.setArchivoNombre(obj.getArchivoNombre());
        ent.setArchivoRuta(obj.getArchivoRuta());
        ent.setArchivoNombreOriginal(obj.getArchivoNombreOriginal());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    public static CmRipsCargaAfFacturas castNegocioEntidad(CmRipsAfFactura obj) {
        CmRipsCargaAfFacturas ent = new CmRipsCargaAfFacturas();
        if (obj.getId() != null) {
            //ent.setId(obj.getId());
        }
        ent.setFila(obj.getFila());
        ent.setCmRipsCargasId(new CmRipsCargas(obj.getCmRipsCarga().getId()));
        ent.setCodigoReps(obj.getCodigoReps());
        //MAE TIPO DOCUMENTO
        if (obj.getMaeTipoDocumentoCodigo() != null) {
            ent.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
            if (obj.getMaeTipoDocumentoId() != null) {
                ent.setMaeTipoDocumentoId(obj.getMaeTipoDocumentoId());
            }
            if (obj.getMaeTipoDocumentoValor() != null) {
                ent.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());
            }
        }
        ent.setRazonSocial(obj.getRazonSocial());
        ent.setNit(obj.getNit().trim());
        ent.setNumeroFactura(obj.getNumeroFactura());
        ent.setFechaFactura(obj.getFechaFactura());
        if (obj.getFechaInicio() != null) {
            ent.setFechaInicio(obj.getFechaInicio());
        }
        if (obj.getFechaFinal() != null) {
            ent.setFechaFinal(obj.getFechaFinal());
        }
        ent.setCodigoEps(obj.getCodigoEps());
        if (obj.getNombreAdministradora() != null) {
            ent.setNombreAdministradora(obj.getNombreAdministradora());
        }
        if (obj.getContrato() != null) {
            ent.setContrato(obj.getContrato());
        }
        if (obj.getPlanBeneficio() != null) {
            ent.setPlanBeneficios(obj.getPlanBeneficio());
        }
        if (obj.getNumeroPoliza() != null) {
            ent.setNumeroPoliza(obj.getNumeroPoliza());
        }
        ent.setValorCopago(obj.getValorCopago());
        ent.setValorAPagar(obj.getValorAPagar());
        if (obj.getValorComision() != null) {
            ent.setValorComision(obj.getValorComision());
        }
        if (obj.getValorDescuento() != null) {
            ent.setValorDescuento(obj.getValorDescuento());
        }
        ent.setArchivoNombre(obj.getArchivoNombre());
        ent.setArchivoRuta(obj.getArchivoRuta());
        ent.setArchivoNombreOriginal(obj.getArchivoNombreOriginal());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    public static CmRipsCargaAhHospitalizaciones castNegocioEntidad(CmRipsAhHospitalizacion obj) {
        CmRipsCargaAhHospitalizaciones ent = new CmRipsCargaAhHospitalizaciones();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        ent.setFila(obj.getFila());
        ent.setCmRipsCargasId(new CmRipsCargas(obj.getCmRipsCarga().getId()));
        ent.setNumeroFactura(obj.getNumeroFactura());
        ent.setCodigoReps(obj.getCodigoReps());
        //MAE TIPO DOCUMENTO
        if (obj.getMaeTipoDocumentoCodigo() != null) {
            ent.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
            if (obj.getMaeTipoDocumentoId() != null) {
                ent.setMaeTipoDocumentoId(obj.getMaeTipoDocumentoId());
            }
            if (obj.getMaeTipoDocumentoValor() != null) {
                ent.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());
            }
        }
        ent.setDocumentoAfiliado(obj.getDocumentoAfiliado());
        //MAE VIA INGRESO
        if (obj.getMaeViaIngresoCodigo() != null) {
            ent.setMaeViaIngresoCodigo(obj.getMaeViaIngresoCodigo());
            if (obj.getMaeViaIngresoId() != null) {
                ent.setMaeViaIngresoId(obj.getMaeViaIngresoId());
            }
            if (obj.getMaeViaIngresoValor() != null) {
                ent.setMaeViaIngresoValor(obj.getMaeViaIngresoValor());
            }
        }
        //MAE CAUSA EXTERNA
        if (obj.getMaeCausaExternaCodigo() != null) {
            ent.setMaeCausaExternaCodigo(obj.getMaeViaIngresoCodigo());
            if (obj.getMaeCausaExternaId() != null) {
                ent.setMaeCausaExternaId(obj.getMaeCausaExternaId());
            }
            if (obj.getMaeCausaExternaValor() != null) {
                ent.setMaeCausaExternaValor(obj.getMaeCausaExternaValor());
            }
        }
        //MA DIAGNOSTICO INGRESO
        if (obj.getMaDiagnosticoIngresoCodigo() != null) {
            ent.setMaDiagnosticoIngresoCodigo(obj.getMaDiagnosticoIngresoCodigo());
            if (obj.getMaDiagnosticoIngresoId() != null) {
                ent.setMaDiagnosticoIngresoId(obj.getMaDiagnosticoIngresoId());
            }
            if (obj.getMaDiagnosticoIngresoValor() != null) {
                ent.setMaDiagnosticoIngresoValor(obj.getMaDiagnosticoIngresoValor());
            }
        }
        //MA DIAGNOSTICO EGRESO
        if (obj.getMaDiagnosticoEgresoCodigo() != null) {
            ent.setMaDiagnosticoEgresoCodigo(obj.getMaDiagnosticoEgresoCodigo());
            if (obj.getMaDiagnosticoEgresoId() != null) {
                ent.setMaDiagnosticoEgresoId(obj.getMaDiagnosticoEgresoId());
            }
            if (obj.getMaDiagnosticoEgresoValor() != null) {
                ent.setMaDiagnosticoEgresoValor(obj.getMaDiagnosticoEgresoValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO1
        if (obj.getMaDiagnosticoRelacionado1Codigo() != null) {
            ent.setMaDiagnosticoRelacionado1Codigo(obj.getMaDiagnosticoRelacionado1Codigo());
            if (obj.getMaDiagnosticoRelacionado1Id() != null) {
                ent.setMaDiagnosticoRelacionado1Id(obj.getMaDiagnosticoRelacionado1Id());
            }
            if (obj.getMaDiagnosticoRelacionado1Valor() != null) {
                ent.setMaDiagnosticoRelacionado1Valor(obj.getMaDiagnosticoRelacionado1Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO2
        if (obj.getMaDiagnosticoRelacionado2Codigo() != null) {
            ent.setMaDiagnosticoRelacionado2Codigo(obj.getMaDiagnosticoRelacionado2Codigo());
            if (obj.getMaDiagnosticoRelacionado2Id() != null) {
                ent.setMaDiagnosticoRelacionado2Id(obj.getMaDiagnosticoRelacionado2Id());
            }
            if (obj.getMaDiagnosticoRelacionado2Valor() != null) {
                ent.setMaDiagnosticoRelacionado2Valor(obj.getMaDiagnosticoRelacionado2Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO3
        if (obj.getMaDiagnosticoRelacionado1Codigo() != null) {
            ent.setMaDiagnosticoRelacionado1Codigo(obj.getMaDiagnosticoMuerteCodigo());
            if (obj.getMaDiagnosticoMuerteId() != null) {
                ent.setMaDiagnosticoMuerteId(obj.getMaDiagnosticoMuerteId());
            }
            if (obj.getMaDiagnosticoMuerteValor() != null) {
                ent.setMaDiagnosticoMuerteValor(obj.getMaDiagnosticoMuerteValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO3
        if (obj.getMaDiagnosticoRelacionado1Codigo() != null) {
            ent.setMaDiagnosticoRelacionado1Codigo(obj.getMaDiagnosticoMuerteCodigo());
            if (obj.getMaDiagnosticoMuerteId() != null) {
                ent.setMaDiagnosticoMuerteId(obj.getMaDiagnosticoMuerteId());
            }
            if (obj.getMaDiagnosticoMuerteValor() != null) {
                ent.setMaDiagnosticoMuerteValor(obj.getMaDiagnosticoMuerteValor());
            }
        }
        //MA DIAGNOSTICO COMPLIACION
        if (obj.getMaDiagnosticoComplicacionCodigo() != null) {
            ent.setMaDiagnosticoComplicacionCodigo(obj.getMaDiagnosticoComplicacionCodigo());
            if (obj.getMaDiagnosticoComplicacionId() != null) {
                ent.setMaDiagnosticoComplicacionId(obj.getMaDiagnosticoComplicacionId());
            }
            if (obj.getMaDiagnosticoComplicacionValor() != null) {
                ent.setMaDiagnosticoComplicacionValor(obj.getMaDiagnosticoComplicacionValor());
            }
        }
        //MA DIAGNOSTICO COMPLIACION
        if (obj.getMaDiagnosticoComplicacionCodigo() != null) {
            ent.setMaDiagnosticoComplicacionCodigo(obj.getMaDiagnosticoComplicacionCodigo());
            if (obj.getMaDiagnosticoComplicacionId() != null) {
                ent.setMaDiagnosticoComplicacionId(obj.getMaDiagnosticoComplicacionId());
            }
            if (obj.getMaDiagnosticoComplicacionValor() != null) {
                ent.setMaDiagnosticoComplicacionValor(obj.getMaDiagnosticoComplicacionValor());
            }
        }
        //MAE ESTADO SALIDA
        if (obj.getMaeEstadoSalidaCodigo() != null) {
            ent.setMaeEstadoSalidaCodigo(obj.getMaeEstadoSalidaCodigo());
            if (obj.getMaeEstadoSalidaId() != null) {
                ent.setMaeEstadoSalidaId(obj.getMaeEstadoSalidaId());
            }
            if (obj.getMaeEstadoSalidaValor() != null) {
                ent.setMaeEstadoSalidaValor(obj.getMaeEstadoSalidaValor());
            }
        }
        //MA DIAGNOSTICO MUERTE
        if (obj.getMaeEstadoSalidaCodigo() != null) {
            ent.setMaeEstadoSalidaCodigo(obj.getMaeEstadoSalidaCodigo());
            if (obj.getMaeEstadoSalidaId() != null) {
                ent.setMaeEstadoSalidaId(obj.getMaeEstadoSalidaId());
            }
            if (obj.getMaeEstadoSalidaValor() != null) {
                ent.setMaeEstadoSalidaValor(obj.getMaeEstadoSalidaValor());
            }
        }
        //MA DIAGNOSTICO MUERTE ID
        if (obj.getMaeEstadoSalidaCodigo() != null) {
            ent.setMaeEstadoSalidaCodigo(obj.getMaeEstadoSalidaCodigo());
            if (obj.getMaeEstadoSalidaId() != null) {
                ent.setMaeEstadoSalidaId(obj.getMaeEstadoSalidaId());
            }
            if (obj.getMaeEstadoSalidaValor() != null) {
                ent.setMaeEstadoSalidaValor(obj.getMaeEstadoSalidaValor());
            }
        }
        //MA DIAGNOSTICO MUERTE VALOR
        if (obj.getMaeEstadoSalidaCodigo() != null) {
            ent.setMaeEstadoSalidaCodigo(obj.getMaeEstadoSalidaCodigo());
            if (obj.getMaeEstadoSalidaId() != null) {
                ent.setMaeEstadoSalidaId(obj.getMaeEstadoSalidaId());
            }
            if (obj.getMaeEstadoSalidaValor() != null) {
                ent.setMaeEstadoSalidaValor(obj.getMaeEstadoSalidaValor());
            }
        }
        //MA DIAGNOSTICO MUERTE ID
        if (obj.getMaDiagnosticoMuerteCodigo() != null) {
            ent.setMaDiagnosticoMuerteCodigo(obj.getMaDiagnosticoMuerteCodigo());
            if (obj.getMaDiagnosticoMuerteId() != null) {
                ent.setMaDiagnosticoMuerteId(obj.getMaDiagnosticoMuerteId());
            }
            if (obj.getMaeEstadoSalidaValor() != null) {
                ent.setMaeEstadoSalidaValor(obj.getMaeEstadoSalidaValor());
            }
        }
        //MAE  VIA INGRESO
        if (obj.getMaeViaIngresoCodigo() != null) {
            ent.setMaeViaIngresoCodigo(obj.getMaeViaIngresoCodigo());
            if (obj.getMaeViaIngresoId() != null) {
                ent.setMaeViaIngresoId(obj.getMaeViaIngresoId());
            }
            if (obj.getMaeViaIngresoValor() != null) {
                ent.setMaeViaIngresoValor(obj.getMaeViaIngresoValor());
            }
        }
        //MAE  CAUSA EXTERNA
        if (obj.getMaeCausaExternaCodigo() != null) {
            ent.setMaeCausaExternaCodigo(obj.getMaeViaIngresoCodigo());
            if (obj.getMaeViaIngresoId() != null) {
                ent.setMaeViaIngresoId(obj.getMaeViaIngresoId());
            }
            if (obj.getMaeViaIngresoValor() != null) {
                ent.setMaeViaIngresoValor(obj.getMaeViaIngresoValor());
            }
        }
        //MA DIAGNOSTICO CODIGO
        ent.setFechaIngreso(obj.getFechaIngreso());
        ent.setHoraIngreso(obj.getHoraIngreso());
        ent.setAutorizacion(obj.getAutorizacion());
        if (obj.getMaDiagnosticoIngresoCodigo() != null) {
            ent.setMaDiagnosticoIngresoCodigo(obj.getMaDiagnosticoIngresoCodigo());
            if (obj.getMaDiagnosticoIngresoId() != null) {
                ent.setMaDiagnosticoIngresoId(obj.getMaDiagnosticoIngresoId());
            }
        }
        if (obj.getMaDiagnosticoIngresoValor() != null) {
            ent.setMaDiagnosticoIngresoValor(obj.getMaDiagnosticoIngresoValor());
        }
        //MA DIAGNOSTICO EGRESO CODIGO
        if (obj.getMaDiagnosticoEgresoCodigo() != null) {
            ent.setMaDiagnosticoEgresoCodigo(obj.getMaDiagnosticoEgresoCodigo());
            if (obj.getMaDiagnosticoEgresoId() != null) {
                ent.setMaDiagnosticoEgresoId(obj.getMaDiagnosticoEgresoId());
            }
            if (obj.getMaDiagnosticoEgresoValor() != null) {
                ent.setMaDiagnosticoEgresoValor(obj.getMaDiagnosticoEgresoValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO1 CODIGO
        if (obj.getMaDiagnosticoRelacionado1Codigo() != null) {
            ent.setMaDiagnosticoRelacionado1Codigo(obj.getMaDiagnosticoEgresoCodigo());
            if (obj.getMaDiagnosticoRelacionado1Id() != null) {
                ent.setMaDiagnosticoRelacionado1Id(obj.getMaDiagnosticoRelacionado1Id());
            }
            if (obj.getMaDiagnosticoRelacionado1Valor() != null) {
                ent.setMaDiagnosticoRelacionado1Valor(obj.getMaDiagnosticoRelacionado1Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO2 CODIGO
        if (obj.getMaDiagnosticoRelacionado1Codigo() != null) {
            ent.setMaDiagnosticoRelacionado1Codigo(obj.getMaDiagnosticoEgresoCodigo());
            if (obj.getMaDiagnosticoRelacionado1Id() != null) {
                ent.setMaDiagnosticoRelacionado1Id(obj.getMaDiagnosticoRelacionado1Id());
            }
            if (obj.getMaDiagnosticoRelacionado1Valor() != null) {
                ent.setMaDiagnosticoRelacionado1Valor(obj.getMaDiagnosticoRelacionado1Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO3 CODIGO
        if (obj.getMaDiagnosticoMuerteCodigo() != null) {
            ent.setMaDiagnosticoMuerteCodigo(obj.getMaDiagnosticoEgresoCodigo());
            if (obj.getMaDiagnosticoMuerteId() != null) {
                ent.setMaDiagnosticoMuerteId(obj.getMaDiagnosticoMuerteId());
            }
            if (obj.getMaDiagnosticoMuerteValor() != null) {
                ent.setMaDiagnosticoMuerteValor(obj.getMaDiagnosticoMuerteValor());
            }
        }
        //MA DIAGNOSTICO COMPLICACION
        if (obj.getMaDiagnosticoComplicacionCodigo() != null) {
            ent.setMaDiagnosticoComplicacionCodigo(obj.getMaDiagnosticoComplicacionCodigo());
            if (obj.getMaDiagnosticoComplicacionId() != null) {
                ent.setMaDiagnosticoComplicacionId(obj.getMaDiagnosticoComplicacionId());
            }
            if (obj.getMaDiagnosticoComplicacionValor() != null) {
                ent.setMaDiagnosticoComplicacionValor(obj.getMaDiagnosticoComplicacionValor());
            }
        }
        //MAE ESTADO SALIDA CODIGO
        if (obj.getMaeEstadoSalidaCodigo() != null) {
            ent.setMaeEstadoSalidaCodigo(obj.getMaeEstadoSalidaCodigo());
            if (obj.getMaeEstadoSalidaId() != null) {
                ent.setMaeEstadoSalidaId(obj.getMaeEstadoSalidaId());
            }
            if (obj.getMaDiagnosticoComplicacionValor() != null) {
                ent.setMaDiagnosticoComplicacionValor(obj.getMaDiagnosticoComplicacionValor());
            }
        }
        //MA DIAGNOSTICO MUERTE CODIGO
        if (obj.getMaDiagnosticoMuerteCodigo() != null) {
            ent.setMaDiagnosticoMuerteCodigo(obj.getMaDiagnosticoMuerteCodigo());
            if (obj.getMaDiagnosticoMuerteId() != null) {
                ent.setMaDiagnosticoMuerteId(obj.getMaDiagnosticoMuerteId());
            }
            if (obj.getMaDiagnosticoComplicacionValor() != null) {
                ent.setMaDiagnosticoComplicacionValor(obj.getMaDiagnosticoComplicacionValor());
            }
        }
        ent.setFechaIngreso(obj.getFechaIngreso());
        ent.setHoraIngreso(obj.getHoraIngreso());
        ent.setAutorizacion(obj.getAutorizacion());
        ent.setFechaSalida(obj.getFechaSalida());
        ent.setHoraSalida(obj.getHoraSalida());
        ent.setArchivoNombre(obj.getArchivoNombre());
        ent.setArchivoRuta(obj.getArchivoRuta());
        ent.setArchivoNombreOriginal(obj.getArchivoNombreOriginal());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    public static CmRipsCargaAmMedicamentos castNegocioEntidad(CmRipsAmMedicamento obj) {
        CmRipsCargaAmMedicamentos ent = new CmRipsCargaAmMedicamentos();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        ent.setFila(obj.getFila());
        ent.setCmRipsCargasId(new CmRipsCargas(obj.getCmRipsCarga().getId()));
        ent.setNumeroFactura(obj.getNumeroFactura());
        //MAE TIPO DOCUMENTO
        ent.setCodigoReps(obj.getCodigoReps());
        if (obj.getMaeTipoDocumentoCodigo() != null) {
            ent.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
            if (obj.getMaeTipoDocumentoId() != null) {
                ent.setMaeTipoDocumentoId(obj.getMaeTipoDocumentoId());
            }
            if (obj.getMaeTipoDocumentoValor() != null) {
                ent.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());
            }
        }
        ent.setDocumentoAfiliado(obj.getDocumentoAfiliado());
        ent.setAutorizacion(obj.getAutorizacion());
        //MA MEDICAMENTO
        if (obj.getMaMedicamentoCodigo() != null) {
            ent.setMaMedicamentoCodigo(obj.getMaMedicamentoCodigo());
            if (obj.getMaMedicamentoId() != null) {
                ent.setMaMedicamentoId(obj.getMaMedicamentoId());
            }
            if (obj.getMaMedicamentoCodigo() != null) {
                ent.setMaMedicamentoValor(obj.getMaMedicamentoValor());
            }
        }
        //MAE TIPO MEDICAMENTO
        if (obj.getMaeTipoMedicamentoCodigo() != null) {
            ent.setMaeTipoMedicamentoCodigo(obj.getMaeTipoMedicamentoCodigo());
            if (obj.getMaeTipoMedicamentoId() != null) {
                ent.setMaeTipoMedicamentoId(obj.getMaeTipoMedicamentoId());
            }
            if (obj.getMaeTipoMedicamentoValor() != null) {
                ent.setMaeTipoMedicamentoValor(obj.getMaeTipoMedicamentoValor());
            }
        }
        ent.setNombreGenerico(obj.getNombreGenerico());
        if (obj.getFormaFarmaceutica() != null) {
            ent.setFormaFarmaceutica(obj.getFormaFarmaceutica());
        }
        if (obj.getConcentracion() != null) {
            ent.setConcentracion(obj.getConcentracion());
        }
        if (obj.getUnidadMedida() != null) {
            ent.setUnidadMedida(obj.getUnidadMedida());
        }
        ent.setNumeroUnidades(obj.getNumeroUnidad());
        ent.setValorUnitario(obj.getValorUnitario());
        ent.setValorAPagar(obj.getValorAPagar());
        ent.setArchivoNombre(obj.getArchivoNombre());
        ent.setArchivoRuta(obj.getArchivoRuta());
        ent.setArchivoNombreOriginal(obj.getArchivoNombreOriginal());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    public static CmRipsCargaAnRecienNacidos castNegocioEntidad(CmRipsAnRecienNacido obj) {
        CmRipsCargaAnRecienNacidos ent = new CmRipsCargaAnRecienNacidos();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        ent.setFila(obj.getFila());
        ent.setCmRipsCargasId(new CmRipsCargas(obj.getCmRipsCarga().getId()));
        ent.setNumeroFactura(obj.getNumeroFactura());
        ent.setCodigoReps(obj.getCodigoReps());
        //MAE TIPO DOCUMENTO
        ent.setCodigoReps(obj.getCodigoReps());
        if (obj.getMaeTipoDocumentoMadreCodigo() != null) {
            ent.setMaeTipoDocumentoMadreCodigo(obj.getMaeTipoDocumentoMadreCodigo());
            if (obj.getMaeTipoDocumentoMadreId() != null) {
                ent.setMaeTipoDocumentoMadreId(obj.getMaeTipoDocumentoMadreId());
            }
            if (obj.getMaeTipoDocumentoMadreValor() != null) {
                ent.setMaeTipoDocumentoMadreValor(obj.getMaeTipoDocumentoMadreValor());
            }
        }
        ent.setCodigoReps(obj.getCodigoReps());
        //MAE CONTROL PENATAL
        if (obj.getMaeControlPenatalCodigo() != null) {
            ent.setMaeControlPenatalCodigo(obj.getMaeControlPenatalCodigo());
            if (obj.getMaeControlPenatalId() != null) {
                ent.setMaeControlPenatalId(obj.getMaeControlPenatalId());
            }
            if (obj.getMaeControlPenatalValor() != null) {
                ent.setMaeControlPenatalValor(obj.getMaeControlPenatalValor());
            }
        }
        //MAE SEXO CODIGO
        if (obj.getMaeSexoCodigo() != null) {
            ent.setMaeSexoCodigo(obj.getMaeSexoCodigo());
            if (obj.getMaeSexoId() != null) {
                ent.setMaeSexoId(obj.getMaeSexoId());
            }
            if (obj.getMaeSexoValor() != null) {
                ent.setMaeSexoValor(obj.getMaeSexoValor());
            }
        }
        //MA DIAGNOSTICO PRINCIPAL
        if (obj.getMaDiagnosticoPrincipalCodigo() != null) {
            ent.setMaDiagnosticoPrincipalCodigo(obj.getMaDiagnosticoPrincipalCodigo());
            if (obj.getMaDiagnosticoPrincipalId() != null) {
                ent.setMaDiagnosticoPrincipalId(obj.getMaDiagnosticoPrincipalId());
            }
            if (obj.getMaDiagnosticoPrincipalValor() != null) {
                ent.setMaDiagnosticoPrincipalValor(obj.getMaDiagnosticoPrincipalValor());
            }
        }
        ent.setDocumentoAfiliadoMadre(obj.getDocumentoAfiliadoMadre());
        ent.setFechaNacimiento(obj.getFechaNacimiento());
        ent.setHoraNacimiento(obj.getHoraNacimiento());
        if (obj.getEdadGestacion() != null) {
            ent.setEdadGestacion(obj.getEdadGestacion());
        }
        //MA CAUSA MUERTE
        if (obj.getMaCausaMuerteDiagnosticoCodigo() != null) {
            ent.setMaCausaMuerteDiagnosticoCodigo(obj.getMaCausaMuerteDiagnosticoCodigo());
            if (obj.getMaCausaMuerteDiagnosticoId() != null) {
                ent.setMaCausaMuerteDiagnosticoId(obj.getMaCausaMuerteDiagnosticoId());
            }
            if (obj.getMaCausaMuerteDiagnosticoValor() != null) {
                ent.setMaCausaMuerteDiagnosticoValor(obj.getMaDiagnosticoPrincipalValor());
            }
        }
        if (obj.getPeso() != null) {
            ent.setPeso(obj.getPeso());
        }
        if (obj.getFechaMuerte() != null) {
            ent.setFechaMuerte(obj.getFechaMuerte());
        }
        if (obj.getHoraMuerte() != null) {
            ent.setHoraMuerte(obj.getHoraMuerte());
        }
        ent.setArchivoNombre(obj.getArchivoNombre());
        ent.setArchivoRuta(obj.getArchivoRuta());
        ent.setArchivoNombreOriginal(obj.getArchivoNombreOriginal());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    public static CmRipsCargaApProcedimientos castNegocioEntidad(CmRipsApProcedimiento obj) {
        CmRipsCargaApProcedimientos ent = new CmRipsCargaApProcedimientos();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        ent.setFila(obj.getFila());
        ent.setCmRipsCargasId(new CmRipsCargas(obj.getCmRipsCarga().getId()));
        ent.setNumeroFactura(obj.getNumeroFactura());
        ent.setCodigoReps(obj.getCodigoReps());
        //MAE TIPO DOCUMENTO
        ent.setCodigoReps(obj.getCodigoReps());
        if (obj.getMaeTipoDocumentoCodigo() != null) {
            ent.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
            if (obj.getMaeTipoDocumentoId() != null) {
                ent.setMaeTipoDocumentoId(obj.getMaeTipoDocumentoId());
            }
            if (obj.getMaeTipoDocumentoValor() != null) {
                ent.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());
            }
        }
        ent.setDocumentoAfiliado(obj.getDocumentoAfiliado());
        ent.setFechaProcedimiento(obj.getFechaProcedimiento());
        ent.setAutorizacion(obj.getAutorizacion());
        //ent.setCodigoServicio(obj.getCodigoServicio());
        //MAE CODIGO AMBITO ATENCION
        ent.setCodigoReps(obj.getCodigoReps());
        if (obj.getMaeAmbitoAtencionCodigo() != null) {
            ent.setMaeAmbitoAtencionCodigo(obj.getMaeAmbitoAtencionCodigo());
            if (obj.getMaeAmbitoAtencionId() != null) {
                ent.setMaeAmbitoAtencionId(obj.getMaeAmbitoAtencionId());
            }
            if (obj.getMaeAmbitoAtencionValor() != null) {
                ent.setMaeAmbitoAtencionCodigoValor(obj.getMaeAmbitoAtencionValor());
            }
        }
        //MAE FINALIDAD PROCEDIMIENTOS
        ent.setCodigoReps(obj.getCodigoReps());
        if (obj.getMaeFinalidadProcedimientoCodigo() != null) {
            ent.setMaeFinalidadProcedimientoCodigo(obj.getMaeFinalidadProcedimientoCodigo());
            if (obj.getMaeFinalidadProcedimientoId() != null) {
                ent.setMaeFinalidadProcedimientoId(obj.getMaeFinalidadProcedimientoId());
            }
            if (obj.getMaeFinalidadProcedimientoValor() != null) {
                ent.setMaeFinalidadProcedimientoValor(obj.getMaeFinalidadProcedimientoValor());
            }
        }
        //MAE PERSONAL ATIENDE
        ent.setCodigoReps(obj.getCodigoReps());
        if (obj.getMaePersonalAtiendeCodigo() != null) {
            ent.setMaePersonalAtiendeCodigo(obj.getMaePersonalAtiendeCodigo());
            if (obj.getMaePersonalAtiendeId() != null) {
                ent.setMaePersonalAtiendeId(obj.getMaePersonalAtiendeId());
            }
            if (obj.getMaePersonalAtiendeValor() != null) {
                ent.setMaePersonalAtiendeValor(obj.getMaePersonalAtiendeValor());
            }
        }
        //MAE DIAGNOSTICO PRINCIPAL
        if (obj.getMaDiagnosticoPrincipalCodigo() != null) {
            ent.setMaDiagnosticoPrincipalCodigo(obj.getMaDiagnosticoPrincipalCodigo());
            if (obj.getMaDiagnosticoPrincipalId() != null) {
                ent.setMaDiagnosticoPrincipalId(obj.getMaDiagnosticoPrincipalId());
            }
            if (obj.getMaDiagnosticoPrincipalValor() != null) {
                ent.setMaDiagnosticoPrincipalValor(obj.getMaDiagnosticoPrincipalValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO1
        if (obj.getMaDiagnosticoRelacionado1Codigo() != null) {
            ent.setMaDiagnosticoRelacionado1Codigo(obj.getMaDiagnosticoRelacionado1Codigo());
            if (obj.getMaDiagnosticoRelacionado1Id() != null) {
                ent.setMaDiagnosticoRelacionado1Id(obj.getMaDiagnosticoRelacionado1Id());
            }
            if (obj.getMaDiagnosticoRelacionado1Valor() != null) {
                ent.setMaDiagnosticoRelacionado1Valor(obj.getMaDiagnosticoRelacionado1Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO2
        if (obj.getMaDiagnosticoRelacionado2Codigo() != null) {
            ent.setMaDiagnosticoRelacionado2Codigo(obj.getMaDiagnosticoRelacionado2Codigo());
            if (obj.getMaDiagnosticoRelacionado2Id() != null) {
                ent.setMaDiagnosticoRelacionado2Id(obj.getMaDiagnosticoRelacionado2Id());
            }
            if (obj.getMaDiagnosticoRelacionado2Valor() != null) {
                ent.setMaDiagnosticoRelacionado2Valor(obj.getMaDiagnosticoRelacionado2Valor());
            }
        }
        //MAE FORMA ACTO
        if (obj.getMaeFormaActoCodigo() != null) {
            ent.setMaeFormaActoCodigo(obj.getMaeFormaActoCodigo());
            if (obj.getMaeFormaActoId() != null) {
                ent.setMaeFormaActoId(obj.getMaeFormaActoId());
            }
            if (obj.getMaeFormaActoValor() != null) {
                ent.setMaeFormaActoValor(obj.getMaeFormaActoValor());
            }
        }
        ent.setValorAPagar(obj.getValorAPagar());
        ent.setArchivoNombre(obj.getArchivoNombre());
        ent.setArchivoRuta(obj.getArchivoRuta());
        ent.setArchivoNombreOriginal(obj.getArchivoNombreOriginal());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    public static CmRipsCargaAtOtrosServicios castNegocioEntidad(CmRipsAtOtrosServicio obj) {
        CmRipsCargaAtOtrosServicios ent = new CmRipsCargaAtOtrosServicios();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        ent.setFila(obj.getFila());
        ent.setCmRipsCargasId(new CmRipsCargas(obj.getCmRipsCarga().getId()));
        ent.setNumeroFactura(obj.getNumeroFactura());
        ent.setCodigoReps(obj.getCodigoReps());
        //MAE TIPO DOCUMENTO
        if (obj.getMaeTipoDocumentoCodigo() != null) {
            ent.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
            if (obj.getMaeTipoDocumentoId() != null) {
                ent.setMaeTipoDocumentoId(obj.getMaeTipoDocumentoId());
            }
            if (obj.getMaeTipoDocumentoValor() != null) {
                ent.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());
            }
        }
        ent.setDocumentoAfiliado(obj.getDocumentoAfiliado());
        ent.setAutorizacion(obj.getAutorizacion());
        //MAE TIPO SERVICIO
        if (obj.getMaeTipoServicioCodigo() != null) {
            ent.setMaeTipoServicioCodigo(obj.getMaeTipoServicioCodigo());
            if (obj.getMaeTipoServicioId() != null) {
                ent.setMaeTipoServicioId(obj.getMaeTipoServicioId());
            }
            if (obj.getMaeTipoServicioValor() != null) {
                ent.setMaeTipoServicioValor(obj.getMaeTipoServicioValor());
            }
        }
        //MA TECNOLOGIAS
        if (obj.getMaTecnologiaCodigo() != null) {
            ent.setCodigoServicio(obj.getMaTecnologiaCodigo());
            ent.setMaTecnologiaCodigo(obj.getMaTecnologiaCodigo());
            if (obj.getMaTecnologiaId() != null) {
                ent.setMaTecnologiaId(obj.getMaTecnologiaId());
            }
            if (obj.getMaTecnologiaValor() != null) {
                ent.setMaTecnologiaValor(obj.getMaTecnologiaValor());
            }
        }
        if (ent.getMaTecnologiaValor().isEmpty() || ent.getMaTecnologiaValor() == null) {
            ent.setMaTecnologiaValor("0");
        }
        if (ent.getCodigoServicio().isEmpty() || ent.getCodigoServicio() == null) {
            ent.setMaTecnologiaValor("0");
        }
        ent.setCodigoServicio("0");
        ent.setMaTecnologiaCodigo("0");
        ent.setNombreServicio(obj.getNombreServicio());
        ent.setUnidades(obj.getUnidades());
        ent.setValorUnidades(obj.getValorUnidades());
        ent.setTotal(obj.getTotal());
        ent.setArchivoNombre(obj.getArchivoNombre());
        ent.setArchivoRuta(obj.getArchivoRuta());
        ent.setArchivoNombreOriginal(obj.getArchivoNombreOriginal());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    public static CmRipsCargaAuUrgencias castNegocioEntidad(CmRipsAuUrgencia obj) {
        CmRipsCargaAuUrgencias ent = new CmRipsCargaAuUrgencias();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        ent.setFila(obj.getFila());
        ent.setCmRipsCargasId(new CmRipsCargas(obj.getCmRipsCarga().getId()));
        ent.setNumeroFactura(obj.getNumeroFactura());
        ent.setCodigoReps(obj.getCodigoReps());
        //MAE TIPO DOCUMENTO
        if (obj.getMaeTipoDocumentoCodigo() != null) {
            ent.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
            if (obj.getMaeTipoDocumentoId() != null) {
                ent.setMaeTipoDocumentoId(obj.getMaeTipoDocumentoId());
            }
            if (obj.getMaeTipoDocumentoValor() != null) {
                ent.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());
            }
        }
        ent.setDocumentoAfiliado(obj.getDocumentoAfiliado());
        ent.setFechaIngreso(obj.getFechaIngreso());
        ent.setHoraIngreso(obj.getHoraIngreso());
        ent.setAutorizacion(obj.getAutorizacion());
        //MAE CAUSA EXTERNA
        if (obj.getMaeCausaExternaCodigo() != null) {
            ent.setMaeCausaExternaCodigo(obj.getMaeCausaExternaCodigo());
            if (obj.getMaeCausaExternaId() != null) {
                ent.setMaeCausaExternaId(obj.getMaeCausaExternaId());
            }
            if (obj.getMaeCausaExternaValor() != null) {
                ent.setMaeCausaExternaValor(obj.getMaeCausaExternaValor());
            }
        }
        if (obj.getMaDiagnosticoSalidaCodigo() != null) {
            ent.setMaDiagnosticoSalidaCodigo(obj.getMaDiagnosticoSalidaCodigo());
            if (obj.getMaDiagnosticoSalidaId() != null) {
                ent.setMaDiagnosticoSalidaId(obj.getMaDiagnosticoSalidaId());
            }
            if (obj.getMaDiagnosticoSalidaValor() != null) {
                ent.setMaDiagnosticoSalidaValor(obj.getMaDiagnosticoSalidaValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO1
        if (obj.getMaDiagnosticoRelacionado1Codigo() != null) {
            ent.setMaDiagnosticoRelacionado1Codigo(obj.getMaDiagnosticoRelacionado1Codigo());
            if (obj.getMaDiagnosticoRelacionado1Id() != null) {
                ent.setMaDiagnosticoRelacionado1Id(obj.getMaDiagnosticoRelacionado1Id());
            }
            if (obj.getMaDiagnosticoRelacionado1Valor() != null) {
                ent.setMaDiagnosticoRelacionado1Valor(obj.getMaDiagnosticoRelacionado1Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO2
        if (obj.getMaDiagnosticoRelacionado2Codigo() != null) {
            ent.setMaDiagnosticoRelacionado2Codigo(obj.getMaDiagnosticoRelacionado2Codigo());
            if (obj.getMaDiagnosticoRelacionado2Id() != null) {
                ent.setMaDiagnosticoRelacionado2Id(obj.getMaDiagnosticoRelacionado2Id());
            }
            if (obj.getMaDiagnosticoRelacionado2Valor() != null) {
                ent.setMaDiagnosticoRelacionado2Valor(obj.getMaDiagnosticoRelacionado2Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO3
        if (obj.getMaDiagnosticoMuerteCodigo() != null) {
            ent.setMaDiagnosticoMuerteCodigo(obj.getMaDiagnosticoMuerteCodigo());
            if (obj.getMaDiagnosticoMuerteId() != null) {
                ent.setMaDiagnosticoMuerteId(obj.getMaDiagnosticoMuerteId());
            }
            if (obj.getMaDiagnosticoMuerteValor() != null) {
                ent.setMaDiagnosticoMuerteValor(obj.getMaDiagnosticoMuerteValor());
            }
        }
        //MAE DESTINO SALIDA
        if (obj.getMaeDestinoSalidaCodigo() != null) {
            ent.setMaeDestinoSalidaCodigo(obj.getMaeDestinoSalidaCodigo());
            if (obj.getMaeDestinoSalidaId() != null) {
                ent.setMaeDestinoSalidaId(obj.getMaeDestinoSalidaId());
            }
            if (obj.getMaeDestinoSalidaValor() != null) {
                ent.setMaeDestinoSalidaValor(obj.getMaeDestinoSalidaValor());
            }
        }
        //MAE ESTADO SALIDA
        if (obj.getMaeEstadoSalidaCodigo() != null) {
            ent.setMaeEstadoSalidaCodigo(obj.getMaeEstadoSalidaCodigo());
            if (obj.getMaeEstadoSalidaId() != null) {
                ent.setMaeEstadoSalidaId(obj.getMaeEstadoSalidaId());
            }
            if (obj.getMaeEstadoSalidaValor() != null) {
                ent.setMaeEstadoSalidaValor(obj.getMaeEstadoSalidaValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO3
        if (obj.getMaDiagnosticoMuerteCodigo() != null) {
            ent.setMaDiagnosticoMuerteCodigo(obj.getMaDiagnosticoMuerteCodigo());
            if (obj.getMaDiagnosticoMuerteId() != null) {
                ent.setMaDiagnosticoMuerteId(obj.getMaDiagnosticoMuerteId());
            }
            if (obj.getMaDiagnosticoMuerteValor() != null) {
                ent.setMaDiagnosticoMuerteValor(obj.getMaDiagnosticoMuerteValor());
            }
        }
        ent.setFechaSalida(obj.getFechaSalida());
        ent.setHoraSalida(obj.getHoraSalida());
        ent.setArchivoNombre(obj.getArchivoNombre());
        ent.setArchivoRuta(obj.getArchivoRuta());
        ent.setArchivoNombreOriginal(obj.getArchivoNombreOriginal());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    public static CmRipsCargaUsUsuarios castNegocioEntidad(CmRipsUsUsuario obj) {
            CmRipsCargaUsUsuarios ent = new CmRipsCargaUsUsuarios();
        if (obj.getId() != null) {
            ent.setId(obj.getId());
        }
        ent.setFila(obj.getFila());
        ent.setCmRipsCargasId(new CmRipsCargas(obj.getCmRipsCarga().getId()));
        //MAE TIPO DOCUMENTO
        if (obj.getMaeTipoDocumentoCodigo() != null) {
            ent.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoCodigo());
            if (obj.getMaeTipoDocumentoId() != null) {
                ent.setMaeTipoDocumentoId(obj.getMaeTipoDocumentoId());
            }
            if (obj.getMaeTipoDocumentoValor() != null) {
                ent.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoValor());
            }
        }
        ent.setDocumentoAfiliado(obj.getDocumentoAfiliado());
        if (obj.getAdministradora() != null) {
            ent.setAdministradora(obj.getAdministradora());
        }
        //MAE TIPO USUARIO
        if (obj.getMaeTipoUsuarioCodigo() != null) {
            ent.setMaeTipoUsuarioCodigo(obj.getMaeTipoUsuarioCodigo());
            if (obj.getMaeTipoUsuarioId() != null) {
                ent.setMaeTipoUsuarioId(obj.getMaeTipoUsuarioId());
            }
            if (obj.getMaeTipoUsuarioValor() != null) {
                ent.setMaeTipoUsuarioValor(obj.getMaeTipoUsuarioValor());
            }
        }
        ent.setPrimerApellido(obj.getPrimerApellido());
        ent.setSegundoApellido(obj.getSegundoApellido());
        ent.setPrimerNombre(obj.getPrimerNombre());
        ent.setSegundoNombre(obj.getSegundoNombre());
        if (obj.getEdad() != null) {
            ent.setEdad(obj.getEdad());
        }
        if (obj.getCodigoUnidadMedidaEdad() != null) {
            ent.setCodigoUnidadMedidaEdad(obj.getCodigoUnidadMedidaEdad());
        }
        //MAE SEXO
        if (obj.getMaeSexoCodigo() != null) {
            ent.setMaeSexoCodigo(obj.getMaeSexoCodigo());
            if (obj.getMaeSexoId() != null) {
                ent.setMaeSexoId(obj.getMaeTipoUsuarioId());
            }
            if (obj.getMaeSexoValor() != null) {
                ent.setMaeSexoValor(obj.getMaeSexoValor());
            }
        }
        //MAE DEPARTAMENTO
        if (obj.getDepartamentoCodigo() != null) {
            ent.setDepartamentoCodigo(obj.getDepartamentoCodigo());
            if (obj.getDepartamentoId() != null) {
                ent.setDepartamentoId(obj.getDepartamentoId());
            }
            if (obj.getDepartamentoNombre() != null) {
                ent.setDepartamentoNombre(obj.getDepartamentoNombre());
            }
        }
        //MAE CIUDAD
        if (obj.getCiudadCodigo() != null) {
            ent.setCiudadCodigo(obj.getCiudadCodigo());
            if (obj.getCiudadId() != null) {
                ent.setCiudadId(obj.getCiudadId());
            }
            if (obj.getCiudadNombre() != null) {
                ent.setCiudadNombre(obj.getCiudadNombre());
            }
        }
        //MAE ZONA RESIDENCIA
        if (obj.getMaeZonaResidenciaCodigo() != null) {
            ent.setMaeZonaResidenciaCodigo(obj.getMaeZonaResidenciaCodigo());
            if (obj.getMaeZonaResidenciaId() != null) {
                ent.setMaeZonaResidenciaId(obj.getMaeZonaResidenciaId());
            }
            if (obj.getMaeZonaResidenciaValor() != null) {
                ent.setMaeZonaResidenciaValor(obj.getMaeZonaResidenciaValor());
            }
        }
        ent.setArchivoNombreOriginal(obj.getArchivoNombreOriginal());
        ent.setArchivoRuta(obj.getArchivoRuta());
        ent.setArchivoNombre(obj.getArchivoNombre());
        ent.setUsuarioCrea(obj.getUsuarioCrea());
        ent.setTerminalCrea(obj.getTerminalCrea());
        ent.setFechaHoraCrea(obj.getFechaHoraCrea());
        return ent;
    }

    /*
    ENTIDAD NEGOCIO FINALES
     */
    public static CmRipsCarga castEntidadNegocio(CmRipsCargas per) {
        CmRipsCarga neg = new CmRipsCarga();
        List<CmRipsEstructuraError> listaCmRipsEstructuraErrores = new ArrayList<>();
        List<CmRipsAcConsulta> listaCmRipsAcConsultas = new ArrayList<>();
        List<CmRipsAdServiciosAgrupado> listaCmRipsAdServiciosAgrupados = new ArrayList<>();
        List<CmRipsAfFactura> listaCmRipsAfFacturas = new ArrayList<>();
        List<CmRipsAhHospitalizacion> listaCmRipsAhHospitalizaciones = new ArrayList<>();
        List<CmRipsAmMedicamento> listaCmRipsAmMedicamentos = new ArrayList<>();
        List<CmRipsAnRecienNacido> listaCmRipsAnRecienNacidos = new ArrayList<>();
        List<CmRipsApProcedimiento> listaCmRipsApProcedimientos = new ArrayList<>();
        List<CmRipsAtOtrosServicio> listaCmRipsAtOtrosServicios = new ArrayList<>();
        List<CmRipsAuUrgencia> listaCmRipsAuUrgencias = new ArrayList<>();
        List<CmRipsCtControlObj> listaCmRipsCtControl = new ArrayList<>();
        List<CmRipsUsUsuario> listaCmRipsUsUsuarios = new ArrayList<>();
        neg.setId(per.getId());
        if (per.getGnPrestadorSedesId() != null) {
            neg.setGnPrestadorSede(new CntPrestadorSede(per.getGnPrestadorSedesId().getId()));
            if (per.getGnPrestadorSedesId().getNombre() != null) {
                neg.getGnPrestadorSede().setNombreSede(per.getGnPrestadorSedesId().getNombre());
                if (per.getGnPrestadorSedesId().getCntPrestadoresId() != null) {
                    CntPrestador cntPrestador = new CntPrestador();
                    cntPrestador.setId(per.getGnPrestadorSedesId().getCntPrestadoresId().getId());
                    cntPrestador.setRazonSocial(per.getGnPrestadorSedesId().getCntPrestadoresId().getRazonSocial());
                    cntPrestador.setNumeroDocumento(per.getGnPrestadorSedesId().getCntPrestadoresId().getNumeroDocumento());
                    cntPrestador.setMaeTipoDocumentoCodigo(per.getGnPrestadorSedesId().getCntPrestadoresId().getMaeTipoDocumentoCodigo());
                    cntPrestador.setMaeTipoDocumentoId(per.getGnPrestadorSedesId().getCntPrestadoresId().getMaeTipoDocumentoId());
                    cntPrestador.setMaeTipoDocumentoValor(per.getGnPrestadorSedesId().getCntPrestadoresId().getMaeTipoDocumentoValor());
                    cntPrestador.setMaeTipoDocumentoRepCodigo(per.getGnPrestadorSedesId().getCntPrestadoresId().getMaeTipoDocumentoRepCodigo());
                    cntPrestador.setMaeTipoDocumentoRepId(per.getGnPrestadorSedesId().getCntPrestadoresId().getMaeTipoDocumentoRepId());
                    cntPrestador.setMaeTipoDocumentoRepValor(per.getGnPrestadorSedesId().getCntPrestadoresId().getMaeTipoDocumentoRepValor());
                    neg.getGnPrestadorSede().setCntPrestador(cntPrestador);
                }
            }
        }
        if (per.getCntContratosId() != null) {
            neg.setCntContrato(new CntContrato(per.getCntContratosId().getId()));
            neg.setCntTipoContratoId(per.getCntTipoContratoId());
        }
        if (per.getCmRipsCargaAcConsultasList() != null) {
            for (CmRipsCargaAcConsultas ent : per.getCmRipsCargaAcConsultasList()) {
                CmRipsAcConsulta cmRipsAcConsulta = castEntidadNegocio(ent);
                listaCmRipsAcConsultas.add(cmRipsAcConsulta);
            }
        }
        if (per.getCmRipsCargaAdServiciosAgrupadosList() != null) {
            for (CmRipsCargaAdServiciosAgrupados ent : per.getCmRipsCargaAdServiciosAgrupadosList()) {
                CmRipsAdServiciosAgrupado cmRipsAdServiciosAgrupado = castEntidadNegocio(ent);
                listaCmRipsAdServiciosAgrupados.add(cmRipsAdServiciosAgrupado);
            }
        }
        if (per.getCmRipsCargaAfFacturasList() != null) {

            for (CmRipsCargaAfFacturas ent : per.getCmRipsCargaAfFacturasList()) {
                CmRipsAfFactura cmRipsAfFactura = castEntidadNegocio(ent);
                listaCmRipsAfFacturas.add(cmRipsAfFactura);
            }
        }
        if (per.getCmRipsCargaAhHospitalizacionesList() != null) {

            for (CmRipsCargaAhHospitalizaciones ent : per.getCmRipsCargaAhHospitalizacionesList()) {
                CmRipsAhHospitalizacion cmRipsAhHospitalizacion = castEntidadNegocio(ent);
                listaCmRipsAhHospitalizaciones.add(cmRipsAhHospitalizacion);
            }
        }
        if (per.getCmRipsCargaAmMedicamentosList() != null) {
            for (CmRipsCargaAmMedicamentos ent : per.getCmRipsCargaAmMedicamentosList()) {
                CmRipsAmMedicamento cmRipsAmMedicamento = castEntidadNegocio(ent);
                listaCmRipsAmMedicamentos.add(cmRipsAmMedicamento);
            }
        }
        if (per.getCmRipsCargaAnRecienNacidosList() != null) {
            for (CmRipsCargaAnRecienNacidos ent : per.getCmRipsCargaAnRecienNacidosList()) {
                CmRipsAnRecienNacido cmRipsAnRecienNacido = castEntidadNegocio(ent);
                listaCmRipsAnRecienNacidos.add(cmRipsAnRecienNacido);
            }
        }
        if (per.getCmRipsCargaApProcedimientosList() != null) {
            for (CmRipsCargaApProcedimientos ent : per.getCmRipsCargaApProcedimientosList()) {
                CmRipsApProcedimiento cmRipsApProcedimiento = castEntidadNegocio(ent);
                listaCmRipsApProcedimientos.add(cmRipsApProcedimiento);
            }
        }
        if (per.getCmRipsCargaAtOtrosServiciosList() != null) {
            for (CmRipsCargaAtOtrosServicios ent : per.getCmRipsCargaAtOtrosServiciosList()) {
                CmRipsAtOtrosServicio cmRipsAtOtrosServicio = castEntidadNegocio(ent);
                listaCmRipsAtOtrosServicios.add(cmRipsAtOtrosServicio);
            }
        }
        if (per.getCmRipsCargaAuUrgenciasList() != null) {
            for (CmRipsCargaAuUrgencias ent : per.getCmRipsCargaAuUrgenciasList()) {
                CmRipsAuUrgencia cmRipsAuUrgencia = castEntidadNegocio(ent);
                listaCmRipsAuUrgencias.add(cmRipsAuUrgencia);
            }
        }
        if (per.getCmRipsCargaUsUsuariosList() != null) {
            for (CmRipsCargaUsUsuarios ent : per.getCmRipsCargaUsUsuariosList()) {
                CmRipsUsUsuario cmRipsUsUsuario = castEntidadNegocio(ent);
                listaCmRipsUsUsuarios.add(cmRipsUsUsuario);
            }
        }
        if (per.getCmRipsCargaCtControlList() != null) {
            for (CmRipsCargaCtControl ent : per.getCmRipsCargaCtControlList()) {
                CmRipsCtControlObj cmRipsCtControl = castEntidadNegocio(ent);
                listaCmRipsCtControl.add(cmRipsCtControl);
            }
        }
        neg.setListaCmRipsAcConsultas(listaCmRipsAcConsultas);
        neg.setListaCmRipsAdServiciosAgrupados(listaCmRipsAdServiciosAgrupados);
        neg.setListaCmRipsAfFacturas(listaCmRipsAfFacturas);
        neg.setListaCmRipsAhHospitalizaciones(listaCmRipsAhHospitalizaciones);
        neg.setListaCmRipsAmMedicamentos(listaCmRipsAmMedicamentos);
        neg.setListaCmRipsAnRecienNacidos(listaCmRipsAnRecienNacidos);
        neg.setListaCmRipsAtOtrosServicios(listaCmRipsAtOtrosServicios);
        neg.setListaCmRipsAuUrgencias(listaCmRipsAuUrgencias);
        neg.setListaCmRipsApProcedimientos(listaCmRipsApProcedimientos);
        neg.setListaCmRipsUsUsuarios(listaCmRipsUsUsuarios);
        neg.setListaCmRipsCtControl(listaCmRipsCtControl);
        neg.setListaCmRipsCargaAnRecienNacidos(listaCmRipsAnRecienNacidos);
        neg.setListaCmRipsCargaAtOtrosServicios(listaCmRipsAtOtrosServicios);
        neg.setListaCmRipsApCargaProcedimientos(listaCmRipsApProcedimientos);
        neg.setListaCmRipsCargaAuUrgencias(listaCmRipsAuUrgencias);
        neg.setListaCmRipsCargaCtControles(listaCmRipsCtControl);
        neg.setListacmRipsCargaUsUsuarios(listaCmRipsUsUsuarios);
        neg.setListaCmRipsEstructuraErrores(listaCmRipsEstructuraErrores);
        if (per.getNumeroCuenta() != null) {
            neg.setNumeroCuenta(per.getNumeroCuenta());
        }
        if (per.getFechaPrestacion() != null) {
            neg.setFechaPrestacion(per.getFechaPrestacion());
        }
        neg.setValorCarga(per.getValorCarga());
        neg.setEstado(per.getEstado());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsCtControlObj castEntidadNegocio(CmRipsCtControl per) {
        CmRipsCtControlObj neg = new CmRipsCtControlObj();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }
        neg.setCodigoReps(per.getCodigoReps());
        neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        neg.setArchivoControl(per.getArchivoControl());
        neg.setArchivoAf(per.getArchivoAf());
        neg.setArchivoUs(per.getArchivoUs());
        neg.setArchivoAd(per.getArchivoAd());
        neg.setArchivoAc(per.getArchivoAc());
        neg.setArchivoAp(per.getArchivoAp());
        neg.setArchivoAh(per.getArchivoAh());
        neg.setArchivoAu(per.getArchivoAu());
        neg.setArchivoAn(per.getArchivoAn());
        neg.setArchivoAm(per.getArchivoAm());
        neg.setArchivoAt(per.getArchivoAt());
        neg.setRegistrosAf(per.getRegistrosAf());
        neg.setRegistroAc(per.getRegistroAc());
        neg.setRegistrosUs(per.getRegistrosUs());
        neg.setRegistrosAp(per.getRegistrosAp());
        neg.setRegistrosAh(per.getRegistrosAh());
        neg.setRegistrosAu(per.getRegistrosAu());
        neg.setRegistrosAm(per.getRegistrosAm());
        neg.setRegistrosAt(per.getRegistrosAt());
        neg.setRegistrosAn(per.getRegistrosAn());
        neg.setRegistrosAd(per.getRegistrosAd());
        neg.setFechaAf(per.getFechaAf());
        neg.setFechaUs(per.getFechaUs());
        neg.setFechaAc(per.getFechaAc());
        neg.setFechaAp(per.getFechaAp());
        neg.setFechaAh(per.getFechaAh());
        neg.setFechaAu(per.getFechaAu());
        neg.setFechaAm(per.getFechaAm());
        neg.setFechaAt(per.getFechaAt());
        neg.setFechaAn(per.getFechaAn());
        neg.setFechaAd(per.getFechaAd());
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setArchivoRuta(per.getArchivoRuta());
        neg.setArchivoNombreOriginal(per.getArchivoNombreOriginal());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsCargaAnexo castEntidadNegocio(CmRipsCargaAnexos per) {
        CmRipsCargaAnexo neg = new CmRipsCargaAnexo();
        neg.setId(per.getId());
        if (per.getCmRipsCargasId() != null) {
            neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        }
        //neg.setMaeTipoArchivoId(per.getMaeTipoArchivoId());
        neg.setArchivoNombreOriginal(per.getArchivoNombreOriginal());
        neg.setArchivoRuta(per.getArchivoRuta());
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setExiste(per.getExiste());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsEstructuraError castEntidadNegocio(CmRipsEstructuraErrores per) {
        CmRipsEstructuraError neg = new CmRipsEstructuraError();
        neg.setId(per.getId());
        if (per.getCmRipsCargasId() != null) {
            neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        }
        neg.setArchivoFila(per.getArchivoFila());
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setDescripcionError(per.getDescripcionError());
        return neg;
    }

    public static CmRipsSuceso castEntidadNegocio(CmRipsSucesos per) {
        CmRipsSuceso neg = new CmRipsSuceso();
        neg.setArchivoFila(per.getArchivoFila());
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setAlerta(per.getAlerta());
        neg.setNombreRegla(per.getNombreRegla());
        neg.setDescripcionMensaje(per.getDescripcionMensaje());
        return neg;
    }

    public static CmRipsAcConsulta castEntidadNegocio(CmRipsAcConsultas per) {
        CmRipsAcConsulta neg = new CmRipsAcConsulta();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }
        neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        neg.setNumFactura(per.getNumFactura());
        per.setCodigoReps(per.getCodigoReps());
        per.setArchivoRuta(per.getArchivoRuta());
        per.setArchivoNombreOriginal(per.getArchivoNombreOriginal());
        per.setArchivoNombre(per.getArchivoNombre());
        //MAE TIPO DOCUMENTO
        if (per.getMaeTipoDocumentoCodigo() != null) {
            neg.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
            if (per.getMaeTipoDocumentoId() != null) {
                neg.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
            }
            if (per.getMaeTipoDocumentoValor() != null) {
                neg.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
            }
        }
        per.setDocumentoAfiliado(per.getDocumentoAfiliado());
        if (per.getFechaConsulta() != null) {
            neg.setFechaConsulta(neg.getFechaConsulta());
        }
        per.setAutorizacion(neg.getAutorizacion());
        if (neg.getMaTecnologiaCodigo() != null) {
            per.setMaTecnologiaCodigo(neg.getMaTecnologiaCodigo());
        }
        if (neg.getMaTecnologiaId() != null) {
            per.setMaTecnologiaId(neg.getMaTecnologiaId());
        }
        if (neg.getMaTecnologiaValor() != null) {
            per.setMaTecnologiaValor(neg.getMaTecnologiaValor());
        }
        //MAE FINALIDAD CONSULTA
        if (per.getMaeFinalidadConsultaCodigo() != null) {
            neg.setMaeFinalidadConsultaCodigo(per.getMaeFinalidadConsultaCodigo());
            if (per.getMaeFinalidadConsultaId() != null) {
                neg.setMaeFinalidadConsultaId(per.getMaeFinalidadConsultaId());
            }
            if (per.getMaeFinalidadConsultaValor() != null) {
                neg.setMaeFinalidadConsultaValor(per.getMaeFinalidadConsultaValor());
            }
        }
        //MAE CAUSA EXTERNA
        if (per.getMaeCausaExternaCodigo() != null) {
            neg.setMaeCausaExternaCodigo(per.getMaeCausaExternaCodigo());
            if (per.getMaeCausaExternaId() != null) {
                neg.setMaeCausaExternaId(per.getMaeCausaExternaId());
            }
            if (per.getMaeCausaExternaValor() != null) {
                neg.setMaeCausaExternaValor(per.getMaeCausaExternaValor());
            }
        }
        //MA DIAGNOSTICO
        if (per.getMaDiagnosticoPrincipalCodigo() != null) {
            neg.setMaDiagnosticoPrincipalCodigo(per.getMaDiagnosticoPrincipalCodigo());
            if (per.getMaDiagnosticoPrincipalId() != null) {
                neg.setMaDiagnosticoPrincipalId(per.getMaDiagnosticoPrincipalId());
            }
            if (per.getMaDiagnosticoPrincipalValor() != null) {
                neg.setMaDiagnosticoPrincipalValor(per.getMaDiagnosticoPrincipalValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO1
        if (per.getMaDiagnosticoRelacionado1Codigo() != null) {
            neg.setMaDiagnosticoRelacionado1Codigo(per.getMaDiagnosticoRelacionado1Codigo());
            if (per.getMaDiagnosticoRelacionado1Id() != null) {
                neg.setMaDiagnosticoRelacionado1Id(per.getMaDiagnosticoRelacionado1Id());
            }
            if (per.getMaDiagnosticoRelacionado1Valor() != null) {
                neg.setMaDiagnosticoRelacionado1Valor(per.getMaDiagnosticoRelacionado1Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO2
        if (per.getMaDiagnosticoRelacionado2Codigo() != null) {
            neg.setMaDiagnosticoRelacionado2Codigo(per.getMaDiagnosticoRelacionado2Codigo());
            if (per.getMaDiagnosticoRelacionado2Id() != null) {
                neg.setMaDiagnosticoRelacionado2Id(neg.getMaDiagnosticoRelacionado2Id());
            }
            if (per.getMaDiagnosticoRelacionado2Valor() != null) {
                neg.setMaDiagnosticoRelacionado2Valor(neg.getMaDiagnosticoRelacionado2Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO3
        if (per.getMaDiagnosticoRelacionado3Codigo() != null) {
            neg.setMaDiagnosticoRelacionado3Codigo(per.getMaDiagnosticoRelacionado3Codigo());
            if (per.getMaDiagnosticoRelacionado3Id() != null) {
                neg.setMaDiagnosticoRelacionado3Id(neg.getMaDiagnosticoRelacionado3Id());
            }
            if (per.getMaDiagnosticoRelacionado3Valor() != null) {
                neg.setMaDiagnosticoRelacionado3Valor(per.getMaDiagnosticoRelacionado3Valor());
            }
        }
        //MA TIPO DIAGNOSTICO 
        if (per.getMaeTipoDiagnosticoCodigo() != null) {
            neg.setMaeTipoDiagnosticoCodigo(per.getMaeTipoDiagnosticoCodigo());
            if (neg.getMaeTipoDiagnosticoId() != null) {
                neg.setMaeTipoDiagnosticoId(per.getMaeTipoDiagnosticoId());
            }
            if (per.getMaeTipoDiagnosticoValor() != null) {
                neg.setMaeTipoDiagnosticoValor(per.getMaeTipoDiagnosticoValor());
            }
        }
        neg.setValorConsulta(per.getValorConsulta());
        neg.setValorCuotaModeradora(per.getValorCuotaModeradora());
        neg.setValorAPagar(per.getValorAPagar());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsAdServiciosAgrupado castEntidadNegocio(CmRipsAdServiciosAgrupados per) {
        CmRipsAdServiciosAgrupado neg = new CmRipsAdServiciosAgrupado();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }
        per.setCmRipsCargasId(new CmRipsCargas(neg.getCmRipsCarga().getId()));
        per.setNumeroFactura(neg.getNumeroFactura());
        per.setCodigoReps(neg.getCodigoReps());
        per.setCantidadServicios(neg.getCantidadServicio());
        per.setValorUnitario(neg.getValorUnitario());
        per.setValorConcepto(neg.getValorConcepto());
        per.setArchivoNombre(neg.getArchivoNombre());
        per.setArchivoRuta(neg.getArchivoRuta());
        per.setArchivoNombreOriginal(neg.getArchivoNombreOriginal());
        per.setUsuarioCrea(neg.getUsuarioCrea());
        per.setTerminalCrea(neg.getTerminalCrea());
        per.setFechaHoraCrea(neg.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsAfFactura castEntidadNegocio(CmRipsAfFacturas per) {
        CmRipsAfFactura neg = new CmRipsAfFactura();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }
        neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        neg.setCodigoReps(per.getCodigoReps());
        //MAE TIPO DOCUMENTO
        if (per.getMaeTipoDocumentoCodigo() != null) {
            neg.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
            if (per.getMaeTipoDocumentoId() != null) {
                neg.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
            }
            if (per.getMaeTipoDocumentoValor() != null) {
                neg.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
            }
        }
        neg.setRazonSocial(per.getRazonSocial());
        neg.setNit(per.getNit().trim());
        neg.setNumeroFactura(per.getNumeroFactura());
        neg.setFechaFactura(per.getFechaFactura());
        if (per.getFechaInicio() != null) {
            neg.setFechaInicio(per.getFechaInicio());
        }
        if (per.getFechaFinal() != null) {
            neg.setFechaFinal(per.getFechaFinal());
        }
        neg.setCodigoEps(per.getCodigoEps());
        if (per.getNombreAdministradora() != null) {
            neg.setNombreAdministradora(per.getNombreAdministradora());
        }
        if (per.getContrato() != null) {
            neg.setContrato(per.getContrato());
        }
        if (per.getPlanBeneficios() != null) {
            neg.setPlanBeneficio(per.getPlanBeneficios());
        }
        if (per.getNumeroPoliza() != null) {
            neg.setNumeroPoliza(per.getNumeroPoliza());
        }
        neg.setValorCopago(per.getValorCopago());
        neg.setValorAPagar(per.getValorAPagar());
        if (per.getValorComision() != null) {
            neg.setValorComision(per.getValorComision());
        }
        if (per.getValorDescuento() != null) {
            neg.setValorDescuento(per.getValorDescuento());
        }
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setArchivoRuta(per.getArchivoRuta());
        neg.setArchivoNombreOriginal(per.getArchivoNombreOriginal());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsAhHospitalizacion castEntidadNegocio(CmRipsAhHospitalizaciones per) {
        CmRipsAhHospitalizacion neg = new CmRipsAhHospitalizacion();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }
        neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        neg.setNumeroFactura(per.getNumeroFactura());
        neg.setCodigoReps(per.getCodigoReps());
        //MAE TIPO DOCUMENTO
        if (per.getMaeTipoDocumentoCodigo() != null) {
            neg.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
            if (per.getMaeTipoDocumentoId() != null) {
                neg.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
            }
            if (per.getMaeTipoDocumentoValor() != null) {
                neg.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
            }
        }
        neg.setDocumentoAfiliado(per.getDocumentoAfiliado());
        //MAE VIA INGRESO
        if (per.getMaeViaIngresoCodigo() != null) {
            neg.setMaeViaIngresoCodigo(per.getMaeViaIngresoCodigo());
            if (per.getMaeViaIngresoId() != null) {
                neg.setMaeViaIngresoId(per.getMaeViaIngresoId());
            }
            if (per.getMaeViaIngresoValor() != null) {
                neg.setMaeViaIngresoValor(per.getMaeViaIngresoValor());
            }
        }
        //MAE CAUSA EXTERNA
        if (per.getMaeCausaExternaCodigo() != null) {
            neg.setMaeCausaExternaCodigo(per.getMaeViaIngresoCodigo());
            if (per.getMaeCausaExternaId() != null) {
                neg.setMaeCausaExternaId(per.getMaeCausaExternaId());
            }
            if (per.getMaeCausaExternaValor() != null) {
                neg.setMaeCausaExternaValor(per.getMaeCausaExternaValor());
            }
        }
        //MA DIAGNOSTICO INGRESO
        if (per.getMaDiagnosticoIngresoCodigo() != null) {
            neg.setMaDiagnosticoIngresoCodigo(per.getMaDiagnosticoIngresoCodigo());
            neg.setMaDiagnosticoIngresoId(per.getMaDiagnosticoIngresoId());
            if (per.getMaDiagnosticoIngresoValor() != null) {
                neg.setMaDiagnosticoIngresoValor(per.getMaDiagnosticoIngresoValor());
            }
        }
        //MA DIAGNOSTICO EGRESO
        if (per.getMaDiagnosticoEgresoCodigo() != null) {
            neg.setMaDiagnosticoEgresoCodigo(per.getMaDiagnosticoEgresoCodigo());
            if (per.getMaDiagnosticoEgresoId() != null) {
                neg.setMaDiagnosticoEgresoId(per.getMaDiagnosticoEgresoId());
            }
            if (per.getMaDiagnosticoEgresoValor() != null) {
                neg.setMaDiagnosticoEgresoValor(per.getMaDiagnosticoEgresoValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO1
        if (per.getMaDiagnosticoRelacionado1Codigo() != null) {
            neg.setMaDiagnosticoRelacionado1Codigo(per.getMaDiagnosticoRelacionado1Codigo());
            if (per.getMaDiagnosticoRelacionado1Id() != null) {
                neg.setMaDiagnosticoRelacionado1Id(per.getMaDiagnosticoRelacionado1Id());
            }
            if (per.getMaDiagnosticoRelacionado1Valor() != null) {
                neg.setMaDiagnosticoRelacionado1Valor(per.getMaDiagnosticoRelacionado1Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO2
        if (per.getMaDiagnosticoRelacionado2Codigo() != null) {
            neg.setMaDiagnosticoRelacionado2Codigo(per.getMaDiagnosticoRelacionado2Codigo());
            if (per.getMaDiagnosticoRelacionado2Id() != null) {
                neg.setMaDiagnosticoRelacionado2Id(per.getMaDiagnosticoRelacionado2Id());
            }
            if (per.getMaDiagnosticoRelacionado2Valor() != null) {
                neg.setMaDiagnosticoRelacionado2Valor(per.getMaDiagnosticoRelacionado2Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO3
        if (per.getMaDiagnosticoRelacionado1Codigo() != null) {
            neg.setMaDiagnosticoRelacionado1Codigo(per.getMaDiagnosticoMuerteCodigo());
            if (per.getMaDiagnosticoMuerteId() != null) {
                neg.setMaDiagnosticoMuerteId(per.getMaDiagnosticoMuerteId());
            }
            if (per.getMaDiagnosticoMuerteValor() != null) {
                neg.setMaDiagnosticoMuerteValor(per.getMaDiagnosticoMuerteValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO3
        if (per.getMaDiagnosticoRelacionado1Codigo() != null) {
            neg.setMaDiagnosticoRelacionado1Codigo(per.getMaDiagnosticoMuerteCodigo());
            if (per.getMaDiagnosticoMuerteId() != null) {
                neg.setMaDiagnosticoMuerteId(per.getMaDiagnosticoMuerteId());
            }
            if (per.getMaDiagnosticoMuerteValor() != null) {
                neg.setMaDiagnosticoMuerteValor(per.getMaDiagnosticoMuerteValor());
            }
        }
        //MA DIAGNOSTICO COMPLIACION
        if (per.getMaDiagnosticoComplicacionCodigo() != null) {
            neg.setMaDiagnosticoComplicacionCodigo(per.getMaDiagnosticoComplicacionCodigo());
            if (per.getMaDiagnosticoComplicacionId() != null) {
                neg.setMaDiagnosticoComplicacionId(per.getMaDiagnosticoComplicacionId());
            }
            if (per.getMaDiagnosticoComplicacionValor() != null) {
                neg.setMaDiagnosticoComplicacionValor(per.getMaDiagnosticoComplicacionValor());
            }
        }
        //MA DIAGNOSTICO COMPLIACION
        if (per.getMaDiagnosticoComplicacionCodigo() != null) {
            neg.setMaDiagnosticoComplicacionCodigo(per.getMaDiagnosticoComplicacionCodigo());
            if (per.getMaDiagnosticoComplicacionId() != null) {
                neg.setMaDiagnosticoComplicacionId(per.getMaDiagnosticoComplicacionId());
            }
            if (per.getMaDiagnosticoComplicacionValor() != null) {
                neg.setMaDiagnosticoComplicacionValor(per.getMaDiagnosticoComplicacionValor());
            }
        }
        //MAE ESTADO SALIDA
        if (per.getMaeEstadoSalidaCodigo() != null) {
            neg.setMaeEstadoSalidaCodigo(per.getMaeEstadoSalidaCodigo());
            if (per.getMaeEstadoSalidaId() != null) {
                neg.setMaeEstadoSalidaId(per.getMaeEstadoSalidaId());
            }
            if (per.getMaeEstadoSalidaValor() != null) {
                neg.setMaeEstadoSalidaValor(per.getMaeEstadoSalidaValor());
            }
        }
        //MA DIAGNOSTICO MUERTE
        if (per.getMaeEstadoSalidaCodigo() != null) {
            neg.setMaeEstadoSalidaCodigo(per.getMaeEstadoSalidaCodigo());
            if (per.getMaeEstadoSalidaId() != null) {
                neg.setMaeEstadoSalidaId(per.getMaeEstadoSalidaId());
            }
            if (per.getMaeEstadoSalidaValor() != null) {
                neg.setMaeEstadoSalidaValor(per.getMaeEstadoSalidaValor());
            }
        }
        //MA DIAGNOSTICO MUERTE ID
        if (per.getMaeEstadoSalidaCodigo() != null) {
            neg.setMaeEstadoSalidaCodigo(per.getMaeEstadoSalidaCodigo());
            if (per.getMaeEstadoSalidaId() != null) {
                neg.setMaeEstadoSalidaId(per.getMaeEstadoSalidaId());
            }
            if (per.getMaeEstadoSalidaValor() != null) {
                neg.setMaeEstadoSalidaValor(per.getMaeEstadoSalidaValor());
            }
        }
        //MA DIAGNOSTICO MUERTE VALOR
        if (per.getMaeEstadoSalidaCodigo() != null) {
            neg.setMaeEstadoSalidaCodigo(per.getMaeEstadoSalidaCodigo());
            if (per.getMaeEstadoSalidaId() != null) {
                neg.setMaeEstadoSalidaId(per.getMaeEstadoSalidaId());
            }
            if (per.getMaeEstadoSalidaValor() != null) {
                neg.setMaeEstadoSalidaValor(per.getMaeEstadoSalidaValor());
            }
        }
        //MA DIAGNOSTICO MUERTE ID
        if (per.getMaDiagnosticoMuerteCodigo() != null) {
            neg.setMaDiagnosticoMuerteCodigo(per.getMaDiagnosticoMuerteCodigo());
            if (per.getMaDiagnosticoMuerteId() != null) {
                neg.setMaDiagnosticoMuerteId(per.getMaDiagnosticoMuerteId());
            }
            if (per.getMaeEstadoSalidaValor() != null) {
                neg.setMaeEstadoSalidaValor(per.getMaeEstadoSalidaValor());
            }
        }
        //MAE  VIA INGRESO
        if (per.getMaeViaIngresoCodigo() != null) {
            neg.setMaeViaIngresoCodigo(per.getMaeViaIngresoCodigo());
            if (per.getMaeViaIngresoId() != null) {
                neg.setMaeViaIngresoId(per.getMaeViaIngresoId());
            }
            if (per.getMaeViaIngresoValor() != null) {
                neg.setMaeViaIngresoValor(per.getMaeViaIngresoValor());
            }
        }
        //MAE  CAUSA EXTERNA
        if (per.getMaeCausaExternaCodigo() != null) {
            neg.setMaeCausaExternaCodigo(per.getMaeViaIngresoCodigo());
            if (per.getMaeViaIngresoId() != null) {
                neg.setMaeViaIngresoId(per.getMaeViaIngresoId());
            }
            if (per.getMaeViaIngresoValor() != null) {
                neg.setMaeViaIngresoValor(per.getMaeViaIngresoValor());
            }
        }
        //MA DIAGNOSTICO CODIGO
        neg.setFechaIngreso(per.getFechaIngreso());
        neg.setHoraIngreso(per.getHoraIngreso());
        neg.setAutorizacion(per.getAutorizacion());
        if (per.getMaDiagnosticoIngresoCodigo() != null) {
            neg.setMaDiagnosticoIngresoCodigo(per.getMaDiagnosticoIngresoCodigo());
            neg.setMaDiagnosticoIngresoId(per.getMaDiagnosticoIngresoId());
        }
        if (per.getMaDiagnosticoIngresoValor() != null) {
            neg.setMaDiagnosticoIngresoValor(per.getMaDiagnosticoIngresoValor());
        }
        //MA DIAGNOSTICO EGRESO CODIGO
        if (per.getMaDiagnosticoEgresoCodigo() != null) {
            neg.setMaDiagnosticoEgresoCodigo(per.getMaDiagnosticoEgresoCodigo());
            if (per.getMaDiagnosticoEgresoId() != null) {
                neg.setMaDiagnosticoEgresoId(per.getMaDiagnosticoEgresoId());
            }
            if (per.getMaDiagnosticoEgresoValor() != null) {
                neg.setMaDiagnosticoEgresoValor(per.getMaDiagnosticoEgresoValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO1 CODIGO
        if (per.getMaDiagnosticoRelacionado1Codigo() != null) {
            neg.setMaDiagnosticoRelacionado1Codigo(per.getMaDiagnosticoEgresoCodigo());
            if (per.getMaDiagnosticoRelacionado1Id() != null) {
                neg.setMaDiagnosticoRelacionado1Id(per.getMaDiagnosticoRelacionado1Id());
            }
            if (per.getMaDiagnosticoRelacionado1Valor() != null) {
                neg.setMaDiagnosticoRelacionado1Valor(per.getMaDiagnosticoRelacionado1Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO2 CODIGO
        if (per.getMaDiagnosticoRelacionado1Codigo() != null) {
            neg.setMaDiagnosticoRelacionado1Codigo(per.getMaDiagnosticoEgresoCodigo());
            if (per.getMaDiagnosticoRelacionado1Id() != null) {
                neg.setMaDiagnosticoRelacionado1Id(per.getMaDiagnosticoRelacionado1Id());
            }
            if (per.getMaDiagnosticoRelacionado1Valor() != null) {
                neg.setMaDiagnosticoRelacionado1Valor(per.getMaDiagnosticoRelacionado1Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO3 CODIGO
        if (per.getMaDiagnosticoMuerteCodigo() != null) {
            neg.setMaDiagnosticoMuerteCodigo(per.getMaDiagnosticoEgresoCodigo());
            if (per.getMaDiagnosticoMuerteId() != null) {
                neg.setMaDiagnosticoMuerteId(per.getMaDiagnosticoMuerteId());
            }
            if (per.getMaDiagnosticoMuerteValor() != null) {
                neg.setMaDiagnosticoMuerteValor(per.getMaDiagnosticoMuerteValor());
            }
        }
        //MA DIAGNOSTICO COMPLICACION
        if (per.getMaDiagnosticoComplicacionCodigo() != null) {
            neg.setMaDiagnosticoComplicacionCodigo(per.getMaDiagnosticoComplicacionCodigo());
            if (per.getMaDiagnosticoComplicacionId() != null) {
                neg.setMaDiagnosticoComplicacionId(per.getMaDiagnosticoComplicacionId());
            }
            if (per.getMaDiagnosticoComplicacionValor() != null) {
                neg.setMaDiagnosticoComplicacionValor(per.getMaDiagnosticoComplicacionValor());
            }
        }
        //MAE ESTADO SALIDA CODIGO
        if (per.getMaeEstadoSalidaCodigo() != null) {
            neg.setMaeEstadoSalidaCodigo(per.getMaeEstadoSalidaCodigo());
            if (per.getMaeEstadoSalidaId() != null) {
                neg.setMaeEstadoSalidaId(per.getMaeEstadoSalidaId());
            }
            if (per.getMaDiagnosticoComplicacionValor() != null) {
                neg.setMaDiagnosticoComplicacionValor(per.getMaDiagnosticoComplicacionValor());
            }
        }
        //MA DIAGNOSTICO MUERTE CODIGO
        if (per.getMaDiagnosticoMuerteCodigo() != null) {
            neg.setMaDiagnosticoMuerteCodigo(per.getMaDiagnosticoMuerteCodigo());
            if (per.getMaDiagnosticoMuerteId() != null) {
                neg.setMaDiagnosticoMuerteId(per.getMaDiagnosticoMuerteId());
            }
            if (per.getMaDiagnosticoComplicacionValor() != null) {
                neg.setMaDiagnosticoComplicacionValor(per.getMaDiagnosticoComplicacionValor());
            }
        }
        neg.setFechaIngreso(per.getFechaIngreso());
        neg.setHoraIngreso(per.getHoraIngreso());
        neg.setAutorizacion(per.getAutorizacion());
        neg.setFechaSalida(per.getFechaSalida());
        neg.setHoraSalida(per.getHoraSalida());
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setArchivoRuta(per.getArchivoRuta());
        neg.setArchivoNombreOriginal(per.getArchivoNombreOriginal());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsAmMedicamento castEntidadNegocio(CmRipsAmMedicamentos per) {
        CmRipsAmMedicamento neg = new CmRipsAmMedicamento();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }
        neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        neg.setNumeroFactura(per.getNumeroFactura());
        //MAE TIPO DOCUMENTO
        neg.setCodigoReps(per.getCodigoReps());
        if (per.getMaeTipoDocumentoCodigo() != null) {
            neg.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
            if (per.getMaeTipoDocumentoId() != null) {
                neg.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
            }
            if (per.getMaeTipoDocumentoValor() != null) {
                neg.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
            }
        }
        neg.setDocumentoAfiliado(per.getDocumentoAfiliado());
        neg.setAutorizacion(per.getAutorizacion());
        //MA MEDICAMENTO
        if (per.getMaMedicamentoCodigo() != null) {
            neg.setMaMedicamentoCodigo(per.getMaMedicamentoCodigo());
            if (per.getMaMedicamentoId() != null) {
                neg.setMaMedicamentoId(per.getMaMedicamentoId());
            }
            if (per.getMaMedicamentoValor() != null) {
                neg.setMaMedicamentoValor(per.getMaMedicamentoValor());
            }
        }
        //MAE TIPO MEDICAMENTO
        if (per.getMaeTipoMedicamentoCodigo() != null) {
            neg.setMaeTipoMedicamentoCodigo(per.getMaeTipoMedicamentoCodigo());
            if (per.getMaeTipoMedicamentoId() != null) {
                neg.setMaeTipoMedicamentoId(per.getMaeTipoMedicamentoId());
            }
            if (per.getMaeTipoMedicamentoValor() != null) {
                neg.setMaeTipoMedicamentoValor(per.getMaeTipoMedicamentoValor());
            }
        }
        neg.setNombreGenerico(per.getNombreGenerico());
        if (per.getFormaFarmaceutica() != null) {
            neg.setFormaFarmaceutica(per.getFormaFarmaceutica());
        }
        if (per.getConcentracion() != null) {
            neg.setConcentracion(per.getConcentracion());
        }
        if (neg.getUnidadMedida() != null) {
            per.setUnidadMedida(neg.getUnidadMedida());
        }
        neg.setNumeroUnidad(per.getNumeroUnidades());
        neg.setValorUnitario(per.getValorUnitario());
        neg.setValorAPagar(per.getValorAPagar());
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setArchivoRuta(per.getArchivoRuta());
        neg.setArchivoNombreOriginal(per.getArchivoNombreOriginal());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsAnRecienNacido castEntidadNegocio(CmRipsAnRecienNacidos per) {
        CmRipsAnRecienNacido neg = new CmRipsAnRecienNacido();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }
        neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        neg.setNumeroFactura(per.getNumeroFactura());
        neg.setCodigoReps(per.getCodigoReps());
        //MAE TIPO DOCUMENTO
        neg.setCodigoReps(per.getCodigoReps());
        if (per.getMaeTipoDocumentoMadreCodigo() != null) {
            neg.setMaeTipoDocumentoMadreCodigo(per.getMaeTipoDocumentoMadreCodigo());
            neg.setMaeTipoDocumentoMadreId(per.getMaeTipoDocumentoMadreId());
            if (per.getMaeTipoDocumentoMadreValor() != null) {
                neg.setMaeTipoDocumentoMadreValor(per.getMaeTipoDocumentoMadreValor());
            }
        }
        //MAE CONTROL PENATAL
        neg.setCodigoReps(per.getCodigoReps());
        if (per.getMaeControlPenatalCodigo() != null) {
            neg.setMaeControlPenatalCodigo(per.getMaeControlPenatalCodigo());
            if (per.getMaeControlPenatalId() != null) {
                neg.setMaeControlPenatalId(per.getMaeControlPenatalId());
            }
            if (per.getMaeControlPenatalValor() != null) {
                neg.setMaeControlPenatalValor(per.getMaeControlPenatalValor());
            }
        }
        //MAE CODIGO SEXO
        if (per.getMaeSexoCodigo() != null) {
            neg.setMaeSexoCodigo(per.getMaeSexoCodigo());
            if (per.getMaeSexoId() != null) {
                neg.setMaeSexoId(per.getMaeSexoId());
            }
            if (per.getMaeSexoValor() != null) {
                neg.setMaeSexoValor(per.getMaeSexoValor());
            }
        }
        //MA DIAGNOSTICO PRINCIPAL
        if (per.getMaDiagnosticoPrincipalCodigo() != null) {
            neg.setMaDiagnosticoPrincipalCodigo(per.getMaDiagnosticoPrincipalCodigo());
            if (per.getMaDiagnosticoPrincipalId() != null) {
                neg.setMaDiagnosticoPrincipalId(per.getMaDiagnosticoPrincipalId());
            }
            if (per.getMaDiagnosticoPrincipalValor() != null) {
                neg.setMaDiagnosticoPrincipalValor(per.getMaDiagnosticoPrincipalValor());
            }
        }
        neg.setDocumentoAfiliadoMadre(per.getDocumentoAfiliadoMadre());
        neg.setFechaNacimiento(per.getFechaNacimiento());
        neg.setHoraNacimiento(per.getHoraNacimiento());
        if (per.getEdadGestacion() != null) {
            neg.setEdadGestacion(per.getEdadGestacion());
        }
        //MA CAUSA MUERTE
        if (per.getMaCausaMuerteDiagnosticoCodigo() != null) {
            neg.setMaCausaMuerteDiagnosticoCodigo(per.getMaCausaMuerteDiagnosticoCodigo());
            if (per.getMaCausaMuerteDiagnosticoId() != null) {
                neg.setMaCausaMuerteDiagnosticoId(per.getMaCausaMuerteDiagnosticoId());
            }
            if (per.getMaCausaMuerteDiagnosticoValor() != null) {
                neg.setMaCausaMuerteDiagnosticoValor(per.getMaDiagnosticoPrincipalValor());
            }
        }
        if (per.getPeso() != null) {
            neg.setPeso(per.getPeso());
        }
        if (per.getFechaMuerte() != null) {
            neg.setFechaMuerte(per.getFechaMuerte());
        }
        if (per.getHoraMuerte() != null) {
            neg.setHoraMuerte(per.getHoraMuerte());
        }
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setArchivoRuta(per.getArchivoRuta());
        neg.setArchivoNombreOriginal(per.getArchivoNombreOriginal());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsApProcedimiento castEntidadNegocio(CmRipsApProcedimientos per) {
        CmRipsApProcedimiento neg = new CmRipsApProcedimiento();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }
        neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        neg.setNumeroFactura(per.getNumeroFactura());
        neg.setCodigoReps(per.getCodigoReps());
        //MAE TIPO DOCUMENTO
        if (per.getMaeTipoDocumentoCodigo() != null) {
            neg.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
            if (per.getMaeTipoDocumentoId() != null) {
                neg.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
            }
            if (per.getMaeTipoDocumentoValor() != null) {
                neg.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
            }
        }
        neg.setDocumentoAfiliado(per.getDocumentoAfiliado());
        neg.setFechaProcedimiento(per.getFechaProcedimiento());
        neg.setAutorizacion(per.getAutorizacion());
        //neg.setCodigoServicio(per.getCodigoServicio());
        //MAE CODIGO AMBITO ATENCION
        neg.setCodigoReps(per.getCodigoReps());
        if (per.getMaeAmbitoAtencionCodigo() != null) {
            neg.setMaeAmbitoAtencionCodigo(per.getMaeAmbitoAtencionCodigo());
            if (per.getMaeAmbitoAtencionId() != null) {
                neg.setMaeAmbitoAtencionId(per.getMaeAmbitoAtencionId());
            }
            if (per.getMaeAmbitoAtencionCodigoValor() != null) {
                neg.setMaeAmbitoAtencionValor(per.getMaeAmbitoAtencionCodigoValor());
            }
        }
        //MAE FINALIDAD PROCEDIMIENTOS
        neg.setCodigoReps(per.getCodigoReps());
        if (per.getMaeFinalidadProcedimientoCodigo() != null) {
            neg.setMaeFinalidadProcedimientoCodigo(per.getMaeFinalidadProcedimientoCodigo());
            if (per.getMaeFinalidadProcedimientoId() != null) {
                neg.setMaeFinalidadProcedimientoId(per.getMaeFinalidadProcedimientoId());
            }
            if (per.getMaeFinalidadProcedimientoValor() != null) {
                neg.setMaeFinalidadProcedimientoValor(per.getMaeFinalidadProcedimientoValor());
            }
        }
        //MAE PERSONAL ATIENDE
        neg.setCodigoReps(per.getCodigoReps());
        if (per.getMaePersonalAtiendeCodigo() != null) {
            neg.setMaePersonalAtiendeCodigo(per.getMaePersonalAtiendeCodigo());
            if (per.getMaePersonalAtiendeId() != null) {
                neg.setMaePersonalAtiendeId(per.getMaePersonalAtiendeId());
            }
            if (per.getMaePersonalAtiendeValor() != null) {
                neg.setMaePersonalAtiendeValor(per.getMaePersonalAtiendeValor());
            }
        }
        //MAE DIAGNOSTICO PRINCIPAL
        if (per.getMaDiagnosticoPrincipalCodigo() != null) {
            neg.setMaDiagnosticoPrincipalCodigo(per.getMaDiagnosticoPrincipalCodigo());
            if (per.getMaDiagnosticoPrincipalId() != null) {
                neg.setMaDiagnosticoPrincipalId(per.getMaDiagnosticoPrincipalId());
            }
            if (per.getMaDiagnosticoPrincipalValor() != null) {
                neg.setMaDiagnosticoPrincipalValor(per.getMaDiagnosticoPrincipalValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO1
        if (per.getMaDiagnosticoRelacionado1Codigo() != null) {
            neg.setMaDiagnosticoRelacionado1Codigo(per.getMaDiagnosticoRelacionado1Codigo());
            if (per.getMaDiagnosticoRelacionado1Id() != null) {
                neg.setMaDiagnosticoRelacionado1Id(per.getMaDiagnosticoRelacionado1Id());
            }
            if (per.getMaDiagnosticoRelacionado1Valor() != null) {
                neg.setMaDiagnosticoRelacionado1Valor(per.getMaDiagnosticoRelacionado1Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO2
        if (per.getMaDiagnosticoRelacionado2Codigo() != null) {
            neg.setMaDiagnosticoRelacionado2Codigo(per.getMaDiagnosticoRelacionado2Codigo());
            if (per.getMaDiagnosticoRelacionado2Id() != null) {
                neg.setMaDiagnosticoRelacionado2Id(per.getMaDiagnosticoRelacionado2Id());
            }
            if (per.getMaDiagnosticoRelacionado2Valor() != null) {
                neg.setMaDiagnosticoRelacionado2Valor(per.getMaDiagnosticoRelacionado2Valor());
            }
        }
        //MAE FORMA ACTO
        if (per.getMaeFormaActoCodigo() != null) {
            neg.setMaeFormaActoCodigo(per.getMaeFormaActoCodigo());
            if (per.getMaeFormaActoId() != null) {
                neg.setMaeFormaActoId(per.getMaeFormaActoId());
            }
            if (per.getMaeFormaActoValor() != null) {
                neg.setMaeFormaActoValor(per.getMaeFormaActoValor());
            }
        }
        neg.setValorAPagar(per.getValorAPagar());
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setArchivoRuta(per.getArchivoRuta());
        neg.setArchivoNombreOriginal(per.getArchivoNombreOriginal());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsAtOtrosServicio castEntidadNegocio(CmRipsAtOtrosServicios per) {
        CmRipsAtOtrosServicio neg = new CmRipsAtOtrosServicio();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }
        neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        neg.setNumeroFactura(per.getNumeroFactura());
        neg.setCodigoReps(per.getCodigoReps());
        //MAE TIPO DOCUMENTO
        if (per.getMaeTipoDocumentoCodigo() != null) {
            neg.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
            if (per.getMaeTipoDocumentoId() != null) {
                neg.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
            }
            if (per.getMaeTipoDocumentoValor() != null) {
                neg.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
            }
        }
        neg.setDocumentoAfiliado(per.getDocumentoAfiliado());
        neg.setAutorizacion(per.getAutorizacion());
        //MAE TIPO SERVICIO
        if (per.getMaeTipoServicioCodigo() != null) {
            neg.setMaeTipoServicioCodigo(per.getMaeTipoServicioCodigo());
            if (per.getMaeTipoServicioId() != null) {
                neg.setMaeTipoServicioId(per.getMaeTipoServicioId());
            }
            if (per.getMaeTipoServicioValor() != null) {
                neg.setMaeTipoServicioValor(per.getMaeTipoServicioValor());
            }
        }
        neg.setNombreServicio(per.getNombreServicio());
        neg.setUnidades(per.getUnidades());
        neg.setValorUnidades(per.getValorUnidades());
        neg.setTotal(per.getTotal());
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setArchivoRuta(per.getArchivoRuta());
        neg.setArchivoNombreOriginal(per.getArchivoNombreOriginal());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsAuUrgencia castEntidadNegocio(CmRipsAuUrgencias per) {
        CmRipsAuUrgencia neg = new CmRipsAuUrgencia();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }
        neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        neg.setNumeroFactura(per.getNumeroFactura());
        neg.setCodigoReps(per.getCodigoReps());
        //MAE TIPO DOCUMENTO
        if (per.getMaeTipoDocumentoCodigo() != null) {
            neg.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
            if (per.getMaeTipoDocumentoId() != null) {
                neg.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
            }
            if (per.getMaeTipoDocumentoValor() != null) {
                neg.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
            }
        }
        neg.setDocumentoAfiliado(per.getDocumentoAfiliado());
        neg.setFechaIngreso(per.getFechaIngreso());
        neg.setHoraIngreso(per.getHoraIngreso());
        neg.setAutorizacion(per.getAutorizacion());
        //MAE CAUSA EXTERNA
        if (per.getMaeCausaExternaCodigo() != null) {
            neg.setMaeCausaExternaCodigo(per.getMaeCausaExternaCodigo());
            if (per.getMaeCausaExternaId() != null) {
                neg.setMaeCausaExternaId(per.getMaeCausaExternaId());
            }
            if (per.getMaeCausaExternaValor() != null) {
                neg.setMaeCausaExternaValor(per.getMaeCausaExternaValor());
            }
        }
        if (per.getMaDiagnosticoSalidaCodigo() != null) {
            neg.setMaDiagnosticoSalidaCodigo(per.getMaDiagnosticoSalidaCodigo());
            if (per.getMaDiagnosticoSalidaId() != null) {
                neg.setMaDiagnosticoSalidaId(per.getMaDiagnosticoSalidaId());
            }
            if (per.getMaDiagnosticoSalidaValor() != null) {
                neg.setMaDiagnosticoSalidaValor(per.getMaDiagnosticoSalidaValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO1
        if (per.getMaDiagnosticoRelacionado1Codigo() != null) {
            neg.setMaDiagnosticoRelacionado1Codigo(per.getMaDiagnosticoRelacionado1Codigo());
            if (per.getMaDiagnosticoRelacionado1Id() != null) {
                neg.setMaDiagnosticoRelacionado1Id(per.getMaDiagnosticoRelacionado1Id());
            }
            if (per.getMaDiagnosticoRelacionado1Valor() != null) {
                neg.setMaDiagnosticoRelacionado1Valor(per.getMaDiagnosticoRelacionado1Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO2
        if (per.getMaDiagnosticoRelacionado2Codigo() != null) {
            neg.setMaDiagnosticoRelacionado2Codigo(per.getMaDiagnosticoRelacionado2Codigo());
            if (per.getMaDiagnosticoRelacionado2Id() != null) {
                neg.setMaDiagnosticoRelacionado2Id(per.getMaDiagnosticoRelacionado2Id());
            }
            if (per.getMaDiagnosticoRelacionado2Valor() != null) {
                neg.setMaDiagnosticoRelacionado2Valor(per.getMaDiagnosticoRelacionado2Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO3
        if (per.getMaDiagnosticoMuerteCodigo() != null) {
            neg.setMaDiagnosticoMuerteCodigo(per.getMaDiagnosticoMuerteCodigo());
            if (per.getMaDiagnosticoMuerteId() != null) {
                neg.setMaDiagnosticoMuerteId(per.getMaDiagnosticoMuerteId());
            }
            if (per.getMaDiagnosticoMuerteValor() != null) {
                neg.setMaDiagnosticoMuerteValor(per.getMaDiagnosticoMuerteValor());
            }
        }
        //MAE DESTINO SALIDA
        if (per.getMaeDestinoSalidaCodigo() != null) {
            neg.setMaeDestinoSalidaCodigo(per.getMaeDestinoSalidaCodigo());
            if (per.getMaeDestinoSalidaId() != null) {
                neg.setMaeDestinoSalidaId(per.getMaeDestinoSalidaId());
            }
            if (per.getMaeDestinoSalidaValor() != null) {
                neg.setMaeDestinoSalidaValor(per.getMaeDestinoSalidaValor());
            }
        }
        //MAE ESTADO SALIDA
        if (per.getMaeEstadoSalidaCodigo() != null) {
            neg.setMaeEstadoSalidaCodigo(per.getMaeEstadoSalidaCodigo());
            if (per.getMaeEstadoSalidaId() != null) {
                neg.setMaeEstadoSalidaId(per.getMaeEstadoSalidaId());
            }
            if (per.getMaeEstadoSalidaValor() != null) {
                neg.setMaeEstadoSalidaValor(per.getMaeEstadoSalidaValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO3
        if (per.getMaDiagnosticoMuerteCodigo() != null) {
            neg.setMaDiagnosticoMuerteCodigo(per.getMaDiagnosticoMuerteCodigo());
            if (per.getMaDiagnosticoMuerteId() != null) {
                neg.setMaDiagnosticoMuerteId(per.getMaDiagnosticoMuerteId());
            }
            if (per.getMaDiagnosticoMuerteValor() != null) {
                neg.setMaDiagnosticoMuerteValor(per.getMaDiagnosticoMuerteValor());
            }
        }
        neg.setFechaSalida(per.getFechaSalida());
        neg.setHoraSalida(per.getHoraSalida());
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setArchivoRuta(per.getArchivoRuta());
        neg.setArchivoNombreOriginal(per.getArchivoNombreOriginal());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsUsUsuario castEntidadNegocio(CmRipsUsUsuarios per) {
        CmRipsUsUsuario neg = new CmRipsUsUsuario();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }
        neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        //MAE TIPO DOCUMENTO
        if (per.getMaeTipoDocumentoCodigo() != null) {
            neg.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
            if (per.getMaeTipoDocumentoId() != null) {
                neg.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
            }
            if (per.getMaeTipoDocumentoValor() != null) {
                neg.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
            }
        }
        neg.setDocumentoAfiliado(per.getDocumentoAfiliado());
        if (per.getAdministradora() != null) {
            neg.setAdministradora(per.getAdministradora());
        }
        //MAE TIPO USUARIO
        if (per.getMaeTipoUsuarioCodigo() != null) {
            neg.setMaeTipoUsuarioCodigo(per.getMaeTipoUsuarioCodigo());
            if (per.getMaeTipoUsuarioId() != null) {
                neg.setMaeTipoUsuarioId(per.getMaeTipoUsuarioId());
            }
            if (per.getMaeTipoUsuarioValor() != null) {
                neg.setMaeTipoUsuarioValor(per.getMaeTipoUsuarioValor());
            }
        }
        neg.setPrimerApellido(per.getPrimerApellido());
        neg.setSegundoApellido(per.getSegundoApellido());
        neg.setPrimerNombre(per.getPrimerNombre());
        neg.setSegundoNombre(per.getSegundoNombre());
        if (per.getEdad() != null) {
            neg.setEdad(per.getEdad());
        }
        if (per.getCodigoUnidadMedidaEdad() != null) {
            neg.setCodigoUnidadMedidaEdad(per.getCodigoUnidadMedidaEdad());
        }
        //MAE SEXO
        if (per.getMaeSexoCodigo() != null) {
            neg.setMaeSexoCodigo(per.getMaeSexoCodigo());
            if (per.getMaeSexoId() != null) {
                neg.setMaeSexoId(per.getMaeTipoUsuarioId());
            }
            if (per.getMaeSexoValor() != null) {
                neg.setMaeSexoValor(per.getMaeSexoValor());
            }
        }
        //MAE DEPARTAMENTO
        if (per.getDepartamentoCodigo() != null) {
            neg.setDepartamentoCodigo(per.getDepartamentoCodigo());
            if (per.getDepartamentoId() != null) {
                neg.setDepartamentoId(per.getDepartamentoId());
            }
            if (per.getDepartamentoNombre() != null) {
                neg.setDepartamentoNombre(per.getDepartamentoNombre());
            }
        }
        //MAE CIUDAD
        if (per.getCiudadCodigo() != null) {
            neg.setCiudadCodigo(per.getCiudadCodigo());
            if (per.getCiudadId() != null) {
                neg.setCiudadId(per.getCiudadId());
            }
            if (per.getCiudadNombre() != null) {
                neg.setCiudadNombre(per.getCiudadNombre());
            }
        }
        //MAE ZONA RESIDENCIA
        if (per.getMaeZonaResidenciaCodigo() != null) {
            neg.setMaeZonaResidenciaCodigo(per.getMaeZonaResidenciaCodigo());
            if (per.getMaeZonaResidenciaId() != null) {
                neg.setMaeZonaResidenciaId(per.getMaeZonaResidenciaId());
            }
            if (per.getMaeZonaResidenciaValor() != null) {
                neg.setMaeZonaResidenciaValor(per.getMaeZonaResidenciaValor());
            }
        }
        neg.setArchivoNombreOriginal(per.getArchivoNombreOriginal());
        neg.setArchivoRuta(per.getArchivoRuta());
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    /*
    ENTIDAD NEGOCIO CARGAS
     */
    public static CmRipsCtControlObj castEntidadNegocio(CmRipsCargaCtControl per) {
        CmRipsCtControlObj neg = new CmRipsCtControlObj();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }
        neg.setCodigoReps(per.getCodigoReps());
        neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        neg.setArchivoControl(per.getArchivoControl());
        neg.setArchivoAf(per.getArchivoAf());
        neg.setArchivoUs(per.getArchivoUs());
        neg.setArchivoAd(per.getArchivoAd());
        neg.setArchivoAc(per.getArchivoAc());
        neg.setArchivoAp(per.getArchivoAp());
        neg.setArchivoAh(per.getArchivoAh());
        neg.setArchivoAu(per.getArchivoAu());
        neg.setArchivoAn(per.getArchivoAn());
        neg.setArchivoAm(per.getArchivoAm());
        neg.setArchivoAt(per.getArchivoAt());
        neg.setRegistrosAf(per.getRegistrosAf());
        neg.setRegistroAc(per.getRegistroAc());
        neg.setRegistrosUs(per.getRegistrosUs());
        neg.setRegistrosAp(per.getRegistrosAp());
        neg.setRegistrosAh(per.getRegistrosAh());
        neg.setRegistrosAu(per.getRegistrosAu());
        neg.setRegistrosAm(per.getRegistrosAm());
        neg.setRegistrosAt(per.getRegistrosAt());
        neg.setRegistrosAn(per.getRegistrosAn());
        neg.setRegistrosAd(per.getRegistrosAd());
        neg.setFechaAf(per.getFechaAf());
        neg.setFechaUs(per.getFechaUs());
        neg.setFechaAc(per.getFechaAc());
        neg.setFechaAp(per.getFechaAp());
        neg.setFechaAh(per.getFechaAh());
        neg.setFechaAu(per.getFechaAu());
        neg.setFechaAm(per.getFechaAm());
        neg.setFechaAt(per.getFechaAt());
        neg.setFechaAn(per.getFechaAn());
        neg.setFechaAd(per.getFechaAd());
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setArchivoRuta(per.getArchivoRuta());
        neg.setArchivoNombreOriginal(per.getArchivoNombreOriginal());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsAcConsulta castEntidadNegocio(CmRipsCargaAcConsultas per) {
        CmRipsAcConsulta neg = new CmRipsAcConsulta();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }
        neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        neg.setNumFactura(per.getNumFactura());
        neg.setCodigoReps(per.getCodigoReps());
        neg.setArchivoRuta(per.getArchivoRuta());
        neg.setArchivoNombreOriginal(per.getArchivoNombreOriginal());
        neg.setArchivoNombre(per.getArchivoNombre());
        if (per.getMaeTipoDocumentoCodigo() != null) {
            neg.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
            if (per.getMaeTipoDocumentoId() != null) {
                neg.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
            }
            if (per.getMaeTipoDocumentoValor() != null) {
                neg.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
            }
        }
        neg.setDocumentoAfiliado(per.getDocumentoAfiliado());
        if (per.getFechaConsulta() != null) {
            neg.setFechaConsulta(per.getFechaConsulta());
        }
        neg.setAutorizacion(per.getAutorizacion());
        if (per.getMaTecnologiaCodigo() != null) {
            neg.setMaTecnologiaCodigo(per.getMaTecnologiaCodigo());
        }
        if (per.getMaTecnologiaId() != null) {
            neg.setMaTecnologiaId(per.getMaTecnologiaId());
        }
        if (per.getMaTecnologiaValor() != null) {
            neg.setMaTecnologiaValor(per.getMaTecnologiaValor());
        }
        //MAE FINALIDAD CONSULTA
        if (per.getMaeFinalidadConsultaCodigo() != null) {
            neg.setMaeFinalidadConsultaCodigo(per.getMaeFinalidadConsultaCodigo());
            if (per.getMaeFinalidadConsultaId() != null) {
                neg.setMaeFinalidadConsultaId(per.getMaeFinalidadConsultaId());
            }
            if (per.getMaeFinalidadConsultaValor() != null) {
                neg.setMaeFinalidadConsultaValor(per.getMaeFinalidadConsultaValor());
            }
        }
        //MAE CAUSA EXTERNA
        if (per.getMaeCausaExternaCodigo() != null) {
            neg.setMaeCausaExternaCodigo(per.getMaeCausaExternaCodigo());
            if (per.getMaeCausaExternaId() != null) {
                neg.setMaeCausaExternaId(per.getMaeCausaExternaId());
            }
            if (per.getMaeCausaExternaValor() != null) {
                neg.setMaeCausaExternaValor(per.getMaeCausaExternaValor());
            }
        }
        //MA DIAGNOSTICO
        if (per.getMaDiagnosticoPrincipalCodigo() != null) {
            neg.setMaDiagnosticoPrincipalCodigo(per.getMaDiagnosticoPrincipalCodigo());
            if (per.getMaDiagnosticoPrincipalId() != null) {
                neg.setMaDiagnosticoPrincipalId(per.getMaDiagnosticoPrincipalId());
            }
            if (per.getMaDiagnosticoPrincipalValor() != null) {
                neg.setMaDiagnosticoPrincipalValor(per.getMaDiagnosticoPrincipalValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO1
        if (per.getMaDiagnosticoRelacionado1Codigo() != null) {
            neg.setMaDiagnosticoRelacionado1Codigo(per.getMaDiagnosticoRelacionado1Codigo());
            if (per.getMaDiagnosticoRelacionado1Id() != null) {
                neg.setMaDiagnosticoRelacionado1Id(per.getMaDiagnosticoRelacionado1Id());
            }
            if (per.getMaDiagnosticoRelacionado1Valor() != null) {
                neg.setMaDiagnosticoRelacionado1Valor(per.getMaDiagnosticoRelacionado1Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO2
        if (per.getMaDiagnosticoRelacionado2Codigo() != null) {
            neg.setMaDiagnosticoRelacionado2Codigo(per.getMaDiagnosticoRelacionado2Codigo());
            if (per.getMaDiagnosticoRelacionado2Id() != null) {
                neg.setMaDiagnosticoRelacionado2Id(per.getMaDiagnosticoRelacionado2Id());
            }
            if (per.getMaDiagnosticoRelacionado2Valor() != null) {
                neg.setMaDiagnosticoRelacionado2Valor(per.getMaDiagnosticoRelacionado2Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO3
        if (per.getMaDiagnosticoRelacionado3Codigo() != null) {
            neg.setMaDiagnosticoRelacionado3Codigo(per.getMaDiagnosticoRelacionado3Codigo());
            if (per.getMaDiagnosticoRelacionado3Id() != null) {
                neg.setMaDiagnosticoRelacionado3Id(per.getMaDiagnosticoRelacionado3Id());
            }
            if (per.getMaDiagnosticoRelacionado3Valor() != null) {
                neg.setMaDiagnosticoRelacionado3Valor(per.getMaDiagnosticoRelacionado3Valor());
            }
        }
        //MA TIPO DIAGNOSTICO 
        if (per.getMaeTipoDiagnosticoCodigo() != null) {
            neg.setMaeTipoDiagnosticoCodigo(per.getMaeTipoDiagnosticoCodigo());
            if (neg.getMaeTipoDiagnosticoId() != null) {
                neg.setMaeTipoDiagnosticoId(per.getMaeTipoDiagnosticoId());
            }
            if (per.getMaeTipoDiagnosticoValor() != null) {
                neg.setMaeTipoDiagnosticoValor(per.getMaeTipoDiagnosticoValor());
            }
        }
        neg.setValorConsulta(per.getValorConsulta());
        neg.setValorCuotaModeradora(per.getValorCuotaModeradora());
        neg.setValorAPagar(per.getValorAPagar());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsAdServiciosAgrupado castEntidadNegocio(CmRipsCargaAdServiciosAgrupados per) {
        CmRipsAdServiciosAgrupado neg = new CmRipsAdServiciosAgrupado();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }
        per.setCmRipsCargasId(new CmRipsCargas(neg.getCmRipsCarga().getId()));
        per.setNumeroFactura(neg.getNumeroFactura());
        per.setCodigoReps(neg.getCodigoReps());
        per.setCantidadServicios(neg.getCantidadServicio());
        per.setValorUnitario(neg.getValorUnitario());
        per.setValorConcepto(neg.getValorConcepto());
        per.setArchivoNombre(neg.getArchivoNombre());
        per.setArchivoRuta(neg.getArchivoRuta());
        per.setArchivoNombreOriginal(neg.getArchivoNombreOriginal());
        per.setUsuarioCrea(neg.getUsuarioCrea());
        per.setTerminalCrea(neg.getTerminalCrea());
        per.setFechaHoraCrea(neg.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsAfFactura castEntidadNegocio(CmRipsCargaAfFacturas per) {
        CmRipsAfFactura neg = new CmRipsAfFactura();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }
        neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        neg.setCodigoReps(per.getCodigoReps());
        //MAE TIPO DOCUMENTO
        if (per.getMaeTipoDocumentoCodigo() != null) {
            neg.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
            if (per.getMaeTipoDocumentoId() != null) {
                neg.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
            }
            if (per.getMaeTipoDocumentoValor() != null) {
                neg.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
            }
        }
        neg.setRazonSocial(per.getRazonSocial());
        neg.setNit(per.getNit().trim());
        neg.setNumeroFactura(per.getNumeroFactura());
        neg.setFechaFactura(per.getFechaFactura());
        if (per.getFechaInicio() != null) {
            neg.setFechaInicio(per.getFechaInicio());
        }
        if (per.getFechaFinal() != null) {
            neg.setFechaFinal(per.getFechaFinal());
        }
        neg.setCodigoEps(per.getCodigoEps());
        if (per.getNombreAdministradora() != null) {
            neg.setNombreAdministradora(per.getNombreAdministradora());
        }
        if (per.getContrato() != null) {
            neg.setContrato(per.getContrato());
        }
        if (per.getPlanBeneficios() != null) {
            neg.setPlanBeneficio(per.getPlanBeneficios());
        }
        if (per.getNumeroPoliza() != null) {
            neg.setNumeroPoliza(per.getNumeroPoliza());
        }
        neg.setValorCopago(per.getValorCopago());
        neg.setValorAPagar(per.getValorAPagar());
        if (per.getValorComision() != null) {
            neg.setValorComision(per.getValorComision());
        }
        if (per.getValorDescuento() != null) {
            neg.setValorDescuento(per.getValorDescuento());
        }
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setArchivoRuta(per.getArchivoRuta());
        neg.setArchivoNombreOriginal(per.getArchivoNombreOriginal());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsAhHospitalizacion castEntidadNegocio(CmRipsCargaAhHospitalizaciones per) {
        CmRipsAhHospitalizacion neg = new CmRipsAhHospitalizacion();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }
        neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        neg.setNumeroFactura(per.getNumeroFactura());
        neg.setCodigoReps(per.getCodigoReps());
        //MAE TIPO DOCUMENTO
        if (per.getMaeTipoDocumentoCodigo() != null) {
            neg.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
            if (per.getMaeTipoDocumentoId() != null) {
                neg.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
            }
            if (per.getMaeTipoDocumentoValor() != null) {
                neg.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
            }
        }
        neg.setDocumentoAfiliado(per.getDocumentoAfiliado());
        //MAE VIA INGRESO
        if (per.getMaeViaIngresoCodigo() != null) {
            neg.setMaeViaIngresoCodigo(per.getMaeViaIngresoCodigo());
            if (per.getMaeViaIngresoId() != null) {
                neg.setMaeViaIngresoId(per.getMaeViaIngresoId());
            }
            if (per.getMaeViaIngresoValor() != null) {
                neg.setMaeViaIngresoValor(per.getMaeViaIngresoValor());
            }
        }
        //MAE CAUSA EXTERNA
        if (per.getMaeCausaExternaCodigo() != null) {
            neg.setMaeCausaExternaCodigo(per.getMaeViaIngresoCodigo());
            if (per.getMaeCausaExternaId() != null) {
                neg.setMaeCausaExternaId(per.getMaeCausaExternaId());
            }
            if (per.getMaeCausaExternaValor() != null) {
                neg.setMaeCausaExternaValor(per.getMaeCausaExternaValor());
            }
        }
        //MA DIAGNOSTICO INGRESO
        if (per.getMaDiagnosticoIngresoCodigo() != null) {
            neg.setMaDiagnosticoIngresoCodigo(per.getMaDiagnosticoIngresoCodigo());
            neg.setMaDiagnosticoIngresoId(per.getMaDiagnosticoIngresoId());
            if (per.getMaDiagnosticoIngresoValor() != null) {
                neg.setMaDiagnosticoIngresoValor(per.getMaDiagnosticoIngresoValor());
            }
        }
        //MA DIAGNOSTICO EGRESO
        if (per.getMaDiagnosticoEgresoCodigo() != null) {
            neg.setMaDiagnosticoEgresoCodigo(per.getMaDiagnosticoEgresoCodigo());
            if (per.getMaDiagnosticoEgresoId() != null) {
                neg.setMaDiagnosticoEgresoId(per.getMaDiagnosticoEgresoId());
            }
            if (per.getMaDiagnosticoEgresoValor() != null) {
                neg.setMaDiagnosticoEgresoValor(per.getMaDiagnosticoEgresoValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO1
        if (per.getMaDiagnosticoRelacionado1Codigo() != null) {
            neg.setMaDiagnosticoRelacionado1Codigo(per.getMaDiagnosticoRelacionado1Codigo());
            if (per.getMaDiagnosticoRelacionado1Id() != null) {
                neg.setMaDiagnosticoRelacionado1Id(per.getMaDiagnosticoRelacionado1Id());
            }
            if (per.getMaDiagnosticoRelacionado1Valor() != null) {
                neg.setMaDiagnosticoRelacionado1Valor(per.getMaDiagnosticoRelacionado1Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO2
        if (per.getMaDiagnosticoRelacionado2Codigo() != null) {
            neg.setMaDiagnosticoRelacionado2Codigo(per.getMaDiagnosticoRelacionado2Codigo());
            if (per.getMaDiagnosticoRelacionado2Id() != null) {
                neg.setMaDiagnosticoRelacionado2Id(per.getMaDiagnosticoRelacionado2Id());
            }
            if (per.getMaDiagnosticoRelacionado2Valor() != null) {
                neg.setMaDiagnosticoRelacionado2Valor(per.getMaDiagnosticoRelacionado2Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO3
        if (per.getMaDiagnosticoRelacionado1Codigo() != null) {
            neg.setMaDiagnosticoRelacionado1Codigo(per.getMaDiagnosticoMuerteCodigo());
            if (per.getMaDiagnosticoMuerteId() != null) {
                neg.setMaDiagnosticoMuerteId(per.getMaDiagnosticoMuerteId());
            }
            if (per.getMaDiagnosticoMuerteValor() != null) {
                neg.setMaDiagnosticoMuerteValor(per.getMaDiagnosticoMuerteValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO3
        if (per.getMaDiagnosticoRelacionado1Codigo() != null) {
            neg.setMaDiagnosticoRelacionado1Codigo(per.getMaDiagnosticoMuerteCodigo());
            if (per.getMaDiagnosticoMuerteId() != null) {
                neg.setMaDiagnosticoMuerteId(per.getMaDiagnosticoMuerteId());
            }
            if (per.getMaDiagnosticoMuerteValor() != null) {
                neg.setMaDiagnosticoMuerteValor(per.getMaDiagnosticoMuerteValor());
            }
        }
        //MA DIAGNOSTICO COMPLIACION
        if (per.getMaDiagnosticoComplicacionCodigo() != null) {
            neg.setMaDiagnosticoComplicacionCodigo(per.getMaDiagnosticoComplicacionCodigo());
            if (per.getMaDiagnosticoComplicacionId() != null) {
                neg.setMaDiagnosticoComplicacionId(per.getMaDiagnosticoComplicacionId());
            }
            if (per.getMaDiagnosticoComplicacionValor() != null) {
                neg.setMaDiagnosticoComplicacionValor(per.getMaDiagnosticoComplicacionValor());
            }
        }
        //MA DIAGNOSTICO COMPLIACION
        if (per.getMaDiagnosticoComplicacionCodigo() != null) {
            neg.setMaDiagnosticoComplicacionCodigo(per.getMaDiagnosticoComplicacionCodigo());
            if (per.getMaDiagnosticoComplicacionId() != null) {
                neg.setMaDiagnosticoComplicacionId(per.getMaDiagnosticoComplicacionId());
            }
            if (per.getMaDiagnosticoComplicacionValor() != null) {
                neg.setMaDiagnosticoComplicacionValor(per.getMaDiagnosticoComplicacionValor());
            }
        }
        //MAE ESTADO SALIDA
        if (per.getMaeEstadoSalidaCodigo() != null) {
            neg.setMaeEstadoSalidaCodigo(per.getMaeEstadoSalidaCodigo());
            if (per.getMaeEstadoSalidaId() != null) {
                neg.setMaeEstadoSalidaId(per.getMaeEstadoSalidaId());
            }
            if (per.getMaeEstadoSalidaValor() != null) {
                neg.setMaeEstadoSalidaValor(per.getMaeEstadoSalidaValor());
            }
        }
        //MA DIAGNOSTICO MUERTE
        if (per.getMaeEstadoSalidaCodigo() != null) {
            neg.setMaeEstadoSalidaCodigo(per.getMaeEstadoSalidaCodigo());
            if (per.getMaeEstadoSalidaId() != null) {
                neg.setMaeEstadoSalidaId(per.getMaeEstadoSalidaId());
            }
            if (per.getMaeEstadoSalidaValor() != null) {
                neg.setMaeEstadoSalidaValor(per.getMaeEstadoSalidaValor());
            }
        }
        //MA DIAGNOSTICO MUERTE ID
        if (per.getMaeEstadoSalidaCodigo() != null) {
            neg.setMaeEstadoSalidaCodigo(per.getMaeEstadoSalidaCodigo());
            if (per.getMaeEstadoSalidaId() != null) {
                neg.setMaeEstadoSalidaId(per.getMaeEstadoSalidaId());
            }
            if (per.getMaeEstadoSalidaValor() != null) {
                neg.setMaeEstadoSalidaValor(per.getMaeEstadoSalidaValor());
            }
        }
        //MA DIAGNOSTICO MUERTE VALOR
        if (per.getMaeEstadoSalidaCodigo() != null) {
            neg.setMaeEstadoSalidaCodigo(per.getMaeEstadoSalidaCodigo());
            if (per.getMaeEstadoSalidaId() != null) {
                neg.setMaeEstadoSalidaId(per.getMaeEstadoSalidaId());
            }
            if (per.getMaeEstadoSalidaValor() != null) {
                neg.setMaeEstadoSalidaValor(per.getMaeEstadoSalidaValor());
            }
        }
        //MA DIAGNOSTICO MUERTE ID
        if (per.getMaDiagnosticoMuerteCodigo() != null) {
            neg.setMaDiagnosticoMuerteCodigo(per.getMaDiagnosticoMuerteCodigo());
            if (per.getMaDiagnosticoMuerteId() != null) {
                neg.setMaDiagnosticoMuerteId(per.getMaDiagnosticoMuerteId());
            }
            if (per.getMaeEstadoSalidaValor() != null) {
                neg.setMaeEstadoSalidaValor(per.getMaeEstadoSalidaValor());
            }
        }
        //MAE  VIA INGRESO
        if (per.getMaeViaIngresoCodigo() != null) {
            neg.setMaeViaIngresoCodigo(per.getMaeViaIngresoCodigo());
            if (per.getMaeViaIngresoId() != null) {
                neg.setMaeViaIngresoId(per.getMaeViaIngresoId());
            }
            if (per.getMaeViaIngresoValor() != null) {
                neg.setMaeViaIngresoValor(per.getMaeViaIngresoValor());
            }
        }
        //MAE  CAUSA EXTERNA
        if (per.getMaeCausaExternaCodigo() != null) {
            neg.setMaeCausaExternaCodigo(per.getMaeViaIngresoCodigo());
            if (per.getMaeViaIngresoId() != null) {
                neg.setMaeViaIngresoId(per.getMaeViaIngresoId());
            }
            if (per.getMaeViaIngresoValor() != null) {
                neg.setMaeViaIngresoValor(per.getMaeViaIngresoValor());
            }
        }
        //MA DIAGNOSTICO CODIGO
        neg.setFechaIngreso(per.getFechaIngreso());
        neg.setHoraIngreso(per.getHoraIngreso());
        neg.setAutorizacion(per.getAutorizacion());
        if (per.getMaDiagnosticoIngresoCodigo() != null) {
            neg.setMaDiagnosticoIngresoCodigo(per.getMaDiagnosticoIngresoCodigo());
            neg.setMaDiagnosticoIngresoId(per.getMaDiagnosticoIngresoId());
        }
        if (per.getMaDiagnosticoIngresoValor() != null) {
            neg.setMaDiagnosticoIngresoValor(per.getMaDiagnosticoIngresoValor());
        }
        //MA DIAGNOSTICO EGRESO CODIGO
        if (per.getMaDiagnosticoEgresoCodigo() != null) {
            neg.setMaDiagnosticoEgresoCodigo(per.getMaDiagnosticoEgresoCodigo());
            if (per.getMaDiagnosticoEgresoId() != null) {
                neg.setMaDiagnosticoEgresoId(per.getMaDiagnosticoEgresoId());
            }
            if (per.getMaDiagnosticoEgresoValor() != null) {
                neg.setMaDiagnosticoEgresoValor(per.getMaDiagnosticoEgresoValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO1 CODIGO
        if (per.getMaDiagnosticoRelacionado1Codigo() != null) {
            neg.setMaDiagnosticoRelacionado1Codigo(per.getMaDiagnosticoEgresoCodigo());
            if (per.getMaDiagnosticoRelacionado1Id() != null) {
                neg.setMaDiagnosticoRelacionado1Id(per.getMaDiagnosticoRelacionado1Id());
            }
            if (per.getMaDiagnosticoRelacionado1Valor() != null) {
                neg.setMaDiagnosticoRelacionado1Valor(per.getMaDiagnosticoRelacionado1Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO2 CODIGO
        if (per.getMaDiagnosticoRelacionado1Codigo() != null) {
            neg.setMaDiagnosticoRelacionado1Codigo(per.getMaDiagnosticoEgresoCodigo());
            if (per.getMaDiagnosticoRelacionado1Id() != null) {
                neg.setMaDiagnosticoRelacionado1Id(per.getMaDiagnosticoRelacionado1Id());
            }
            if (per.getMaDiagnosticoRelacionado1Valor() != null) {
                neg.setMaDiagnosticoRelacionado1Valor(per.getMaDiagnosticoRelacionado1Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO3 CODIGO
        if (per.getMaDiagnosticoMuerteCodigo() != null) {
            neg.setMaDiagnosticoMuerteCodigo(per.getMaDiagnosticoEgresoCodigo());
            if (per.getMaDiagnosticoMuerteId() != null) {
                neg.setMaDiagnosticoMuerteId(per.getMaDiagnosticoMuerteId());
            }
            if (per.getMaDiagnosticoMuerteValor() != null) {
                neg.setMaDiagnosticoMuerteValor(per.getMaDiagnosticoMuerteValor());
            }
        }
        //MA DIAGNOSTICO COMPLICACION
        if (per.getMaDiagnosticoComplicacionCodigo() != null) {
            neg.setMaDiagnosticoComplicacionCodigo(per.getMaDiagnosticoComplicacionCodigo());
            if (per.getMaDiagnosticoComplicacionId() != null) {
                neg.setMaDiagnosticoComplicacionId(per.getMaDiagnosticoComplicacionId());
            }
            if (per.getMaDiagnosticoComplicacionValor() != null) {
                neg.setMaDiagnosticoComplicacionValor(per.getMaDiagnosticoComplicacionValor());
            }
        }
        //MAE ESTADO SALIDA CODIGO
        if (per.getMaeEstadoSalidaCodigo() != null) {
            neg.setMaeEstadoSalidaCodigo(per.getMaeEstadoSalidaCodigo());
            if (per.getMaeEstadoSalidaId() != null) {
                neg.setMaeEstadoSalidaId(per.getMaeEstadoSalidaId());
            }
            if (per.getMaDiagnosticoComplicacionValor() != null) {
                neg.setMaDiagnosticoComplicacionValor(per.getMaDiagnosticoComplicacionValor());
            }
        }
        //MA DIAGNOSTICO MUERTE CODIGO
        if (per.getMaDiagnosticoMuerteCodigo() != null) {
            neg.setMaDiagnosticoMuerteCodigo(per.getMaDiagnosticoMuerteCodigo());
            if (per.getMaDiagnosticoMuerteId() != null) {
                neg.setMaDiagnosticoMuerteId(per.getMaDiagnosticoMuerteId());
            }
            if (per.getMaDiagnosticoComplicacionValor() != null) {
                neg.setMaDiagnosticoComplicacionValor(per.getMaDiagnosticoComplicacionValor());
            }
        }
        neg.setFechaIngreso(per.getFechaIngreso());
        neg.setHoraIngreso(per.getHoraIngreso());
        neg.setAutorizacion(per.getAutorizacion());
        neg.setFechaSalida(per.getFechaSalida());
        neg.setHoraSalida(per.getHoraSalida());
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setArchivoRuta(per.getArchivoRuta());
        neg.setArchivoNombreOriginal(per.getArchivoNombreOriginal());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsAmMedicamento castEntidadNegocio(CmRipsCargaAmMedicamentos per) {
        CmRipsAmMedicamento neg = new CmRipsAmMedicamento();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }
        neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        neg.setNumeroFactura(per.getNumeroFactura());
        //MAE TIPO DOCUMENTO
        neg.setCodigoReps(per.getCodigoReps());
        if (per.getMaeTipoDocumentoCodigo() != null) {
            neg.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
            if (per.getMaeTipoDocumentoId() != null) {
                neg.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
            }
            if (per.getMaeTipoDocumentoValor() != null) {
                neg.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
            }
        }
        neg.setDocumentoAfiliado(per.getDocumentoAfiliado());
        neg.setAutorizacion(per.getAutorizacion());
        //MA MEDICAMENTO
        if (per.getMaMedicamentoCodigo() != null) {
            neg.setMaMedicamentoCodigo(per.getMaMedicamentoCodigo());
            if (per.getMaMedicamentoId() != null) {
                neg.setMaMedicamentoId(per.getMaMedicamentoId());
            }
            if (per.getMaMedicamentoValor() != null) {
                neg.setMaMedicamentoValor(per.getMaMedicamentoValor());
            }
        }
        //MAE TIPO MEDICAMENTO
        if (per.getMaeTipoMedicamentoCodigo() != null) {
            neg.setMaeTipoMedicamentoCodigo(per.getMaeTipoMedicamentoCodigo());
            if (per.getMaeTipoMedicamentoId() != null) {
                neg.setMaeTipoMedicamentoId(per.getMaeTipoMedicamentoId());
            }
            if (per.getMaeTipoMedicamentoValor() != null) {
                neg.setMaeTipoMedicamentoValor(per.getMaeTipoMedicamentoValor());
            }
        }
        neg.setNombreGenerico(per.getNombreGenerico());
        if (per.getFormaFarmaceutica() != null) {
            neg.setFormaFarmaceutica(per.getFormaFarmaceutica());
        }
        if (per.getConcentracion() != null) {
            neg.setConcentracion(per.getConcentracion());
        }
        if (neg.getUnidadMedida() != null) {
            per.setUnidadMedida(neg.getUnidadMedida());
        }
        neg.setNumeroUnidad(per.getNumeroUnidades());
        neg.setValorUnitario(per.getValorUnitario());
        neg.setValorAPagar(per.getValorAPagar());
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setArchivoRuta(per.getArchivoRuta());
        neg.setArchivoNombreOriginal(per.getArchivoNombreOriginal());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsAnRecienNacido castEntidadNegocio(CmRipsCargaAnRecienNacidos per) {
        CmRipsAnRecienNacido neg = new CmRipsAnRecienNacido();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }
        neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        neg.setNumeroFactura(per.getNumeroFactura());
        neg.setCodigoReps(per.getCodigoReps());
        //MAE TIPO DOCUMENTO
        neg.setCodigoReps(per.getCodigoReps());
        if (per.getMaeTipoDocumentoMadreCodigo() != null) {
            neg.setMaeTipoDocumentoMadreCodigo(per.getMaeTipoDocumentoMadreCodigo());
            neg.setMaeTipoDocumentoMadreId(per.getMaeTipoDocumentoMadreId());
            if (per.getMaeTipoDocumentoMadreValor() != null) {
                neg.setMaeTipoDocumentoMadreValor(per.getMaeTipoDocumentoMadreValor());
            }
        }
        //MAE CONTROL PENATAL
        neg.setCodigoReps(per.getCodigoReps());
        if (per.getMaeControlPenatalCodigo() != null) {
            neg.setMaeControlPenatalCodigo(per.getMaeControlPenatalCodigo());
            if (per.getMaeControlPenatalId() != null) {
                neg.setMaeControlPenatalId(per.getMaeControlPenatalId());
            }
            if (per.getMaeControlPenatalValor() != null) {
                neg.setMaeControlPenatalValor(per.getMaeControlPenatalValor());
            }
        }
        //MAE CODIGO SEXO
        if (per.getMaeSexoCodigo() != null) {
            neg.setMaeSexoCodigo(per.getMaeSexoCodigo());
            if (per.getMaeSexoId() != null) {
                neg.setMaeSexoId(per.getMaeSexoId());
            }
            if (per.getMaeSexoValor() != null) {
                neg.setMaeSexoValor(per.getMaeSexoValor());
            }
        }
        //MA DIAGNOSTICO PRINCIPAL
        if (per.getMaDiagnosticoPrincipalCodigo() != null) {
            neg.setMaDiagnosticoPrincipalCodigo(per.getMaDiagnosticoPrincipalCodigo());
            if (per.getMaDiagnosticoPrincipalId() != null) {
                neg.setMaDiagnosticoPrincipalId(per.getMaDiagnosticoPrincipalId());
            }
            if (per.getMaDiagnosticoPrincipalValor() != null) {
                neg.setMaDiagnosticoPrincipalValor(per.getMaDiagnosticoPrincipalValor());
            }
        }
        neg.setDocumentoAfiliadoMadre(per.getDocumentoAfiliadoMadre());
        neg.setFechaNacimiento(per.getFechaNacimiento());
        neg.setHoraNacimiento(per.getHoraNacimiento());
        if (per.getEdadGestacion() != null) {
            neg.setEdadGestacion(per.getEdadGestacion());
        }
        //MA CAUSA MUERTE
        if (per.getMaCausaMuerteDiagnosticoCodigo() != null) {
            neg.setMaCausaMuerteDiagnosticoCodigo(per.getMaCausaMuerteDiagnosticoCodigo());
            if (per.getMaCausaMuerteDiagnosticoId() != null) {
                neg.setMaCausaMuerteDiagnosticoId(per.getMaCausaMuerteDiagnosticoId());
            }
            if (per.getMaCausaMuerteDiagnosticoValor() != null) {
                neg.setMaCausaMuerteDiagnosticoValor(per.getMaDiagnosticoPrincipalValor());
            }
        }
        if (per.getPeso() != null) {
            neg.setPeso(per.getPeso());
        }
        if (per.getFechaMuerte() != null) {
            neg.setFechaMuerte(per.getFechaMuerte());
        }
        if (per.getHoraMuerte() != null) {
            neg.setHoraMuerte(per.getHoraMuerte());
        }
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setArchivoRuta(per.getArchivoRuta());
        neg.setArchivoNombreOriginal(per.getArchivoNombreOriginal());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsApProcedimiento castEntidadNegocio(CmRipsCargaApProcedimientos per) {
        CmRipsApProcedimiento neg = new CmRipsApProcedimiento();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }
        neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        neg.setNumeroFactura(per.getNumeroFactura());
        neg.setCodigoReps(per.getCodigoReps());
        //MAE TIPO DOCUMENTO
        if (per.getMaeTipoDocumentoCodigo() != null) {
            neg.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
            if (per.getMaeTipoDocumentoId() != null) {
                neg.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
            }
            if (per.getMaeTipoDocumentoValor() != null) {
                neg.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
            }
        }
        neg.setDocumentoAfiliado(per.getDocumentoAfiliado());
        neg.setFechaProcedimiento(per.getFechaProcedimiento());
        neg.setAutorizacion(per.getAutorizacion());
        //neg.setCodigoServicio(per.getCodigoServicio());
        //MAE CODIGO AMBITO ATENCION
        neg.setCodigoReps(per.getCodigoReps());
        if (per.getMaeAmbitoAtencionCodigo() != null) {
            neg.setMaeAmbitoAtencionCodigo(per.getMaeAmbitoAtencionCodigo());
            if (per.getMaeAmbitoAtencionId() != null) {
                neg.setMaeAmbitoAtencionId(per.getMaeAmbitoAtencionId());
            }
            if (per.getMaeAmbitoAtencionCodigoValor() != null) {
                neg.setMaeAmbitoAtencionValor(per.getMaeAmbitoAtencionCodigoValor());
            }
        }
        //MAE FINALIDAD PROCEDIMIENTOS
        neg.setCodigoReps(per.getCodigoReps());
        if (per.getMaeFinalidadProcedimientoCodigo() != null) {
            neg.setMaeFinalidadProcedimientoCodigo(per.getMaeFinalidadProcedimientoCodigo());
            if (per.getMaeFinalidadProcedimientoId() != null) {
                neg.setMaeFinalidadProcedimientoId(per.getMaeFinalidadProcedimientoId());
            }
            if (per.getMaeFinalidadProcedimientoValor() != null) {
                neg.setMaeFinalidadProcedimientoValor(per.getMaeFinalidadProcedimientoValor());
            }
        }
        //MAE PERSONAL ATIENDE
        neg.setCodigoReps(per.getCodigoReps());
        if (per.getMaePersonalAtiendeCodigo() != null) {
            neg.setMaePersonalAtiendeCodigo(per.getMaePersonalAtiendeCodigo());
            if (per.getMaePersonalAtiendeId() != null) {
                neg.setMaePersonalAtiendeId(per.getMaePersonalAtiendeId());
            }
            if (per.getMaePersonalAtiendeValor() != null) {
                neg.setMaePersonalAtiendeValor(per.getMaePersonalAtiendeValor());
            }
        }
        //MAE DIAGNOSTICO PRINCIPAL
        if (per.getMaDiagnosticoPrincipalCodigo() != null) {
            neg.setMaDiagnosticoPrincipalCodigo(per.getMaDiagnosticoPrincipalCodigo());
            if (per.getMaDiagnosticoPrincipalId() != null) {
                neg.setMaDiagnosticoPrincipalId(per.getMaDiagnosticoPrincipalId());
            }
            if (per.getMaDiagnosticoPrincipalValor() != null) {
                neg.setMaDiagnosticoPrincipalValor(per.getMaDiagnosticoPrincipalValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO1
        if (per.getMaDiagnosticoRelacionado1Codigo() != null) {
            neg.setMaDiagnosticoRelacionado1Codigo(per.getMaDiagnosticoRelacionado1Codigo());
            if (per.getMaDiagnosticoRelacionado1Id() != null) {
                neg.setMaDiagnosticoRelacionado1Id(per.getMaDiagnosticoRelacionado1Id());
            }
            if (per.getMaDiagnosticoRelacionado1Valor() != null) {
                neg.setMaDiagnosticoRelacionado1Valor(per.getMaDiagnosticoRelacionado1Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO2
        if (per.getMaDiagnosticoRelacionado2Codigo() != null) {
            neg.setMaDiagnosticoRelacionado2Codigo(per.getMaDiagnosticoRelacionado2Codigo());
            if (per.getMaDiagnosticoRelacionado2Id() != null) {
                neg.setMaDiagnosticoRelacionado2Id(per.getMaDiagnosticoRelacionado2Id());
            }
            if (per.getMaDiagnosticoRelacionado2Valor() != null) {
                neg.setMaDiagnosticoRelacionado2Valor(per.getMaDiagnosticoRelacionado2Valor());
            }
        }
        //MAE FORMA ACTO
        if (per.getMaeFormaActoCodigo() != null) {
            neg.setMaeFormaActoCodigo(per.getMaeFormaActoCodigo());
            if (per.getMaeFormaActoId() != null) {
                neg.setMaeFormaActoId(per.getMaeFormaActoId());
            }
            if (per.getMaeFormaActoValor() != null) {
                neg.setMaeFormaActoValor(per.getMaeFormaActoValor());
            }
        }
        neg.setValorAPagar(per.getValorAPagar());
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setArchivoRuta(per.getArchivoRuta());
        neg.setArchivoNombreOriginal(per.getArchivoNombreOriginal());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsAtOtrosServicio castEntidadNegocio(CmRipsCargaAtOtrosServicios per) {
        CmRipsAtOtrosServicio neg = new CmRipsAtOtrosServicio();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }
        neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        neg.setNumeroFactura(per.getNumeroFactura());
        neg.setCodigoReps(per.getCodigoReps());
        //MAE TIPO DOCUMENTO
        if (per.getMaeTipoDocumentoCodigo() != null) {
            neg.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
            if (per.getMaeTipoDocumentoId() != null) {
                neg.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
            }
            if (per.getMaeTipoDocumentoValor() != null) {
                neg.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
            }
        }
        neg.setDocumentoAfiliado(per.getDocumentoAfiliado());
        neg.setAutorizacion(per.getAutorizacion());
        //MAE TIPO SERVICIO
        if (per.getMaeTipoServicioCodigo() != null) {
            neg.setMaeTipoServicioCodigo(per.getMaeTipoServicioCodigo());
            if (per.getMaeTipoServicioId() != null) {
                neg.setMaeTipoServicioId(per.getMaeTipoServicioId());
            }
            if (per.getMaeTipoServicioValor() != null) {
                neg.setMaeTipoServicioValor(per.getMaeTipoServicioValor());
            }
        }
        //MA TECNOLOGIA 
        if (per.getMaTecnologiaCodigo() != null) {
            neg.setMaTecnologiaCodigo(per.getMaTecnologiaCodigo());
            if (per.getMaTecnologiaId() != null) {
                neg.setMaTecnologiaId(per.getMaTecnologiaId());
            }
            if (per.getMaTecnologiaValor() != null) {
                neg.setMaTecnologiaValor(per.getMaTecnologiaValor());
            }
        }
        neg.setNombreServicio(per.getNombreServicio());
        neg.setUnidades(per.getUnidades());
        neg.setValorUnidades(per.getValorUnidades());
        neg.setTotal(per.getTotal());
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setArchivoRuta(per.getArchivoRuta());
        neg.setArchivoNombreOriginal(per.getArchivoNombreOriginal());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsAuUrgencia castEntidadNegocio(CmRipsCargaAuUrgencias per) {
        CmRipsAuUrgencia neg = new CmRipsAuUrgencia();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }
        neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        neg.setNumeroFactura(per.getNumeroFactura());
        neg.setCodigoReps(per.getCodigoReps());
        //MAE TIPO DOCUMENTO
        if (per.getMaeTipoDocumentoCodigo() != null) {
            neg.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
            if (per.getMaeTipoDocumentoId() != null) {
                neg.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
            }
            if (per.getMaeTipoDocumentoValor() != null) {
                neg.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
            }
        }
        neg.setDocumentoAfiliado(per.getDocumentoAfiliado());
        neg.setFechaIngreso(per.getFechaIngreso());
        neg.setHoraIngreso(per.getHoraIngreso());
        neg.setAutorizacion(per.getAutorizacion());
        //MAE CAUSA EXTERNA
        if (per.getMaeCausaExternaCodigo() != null) {
            neg.setMaeCausaExternaCodigo(per.getMaeCausaExternaCodigo());
            if (per.getMaeCausaExternaId() != null) {
                neg.setMaeCausaExternaId(per.getMaeCausaExternaId());
            }
            if (per.getMaeCausaExternaValor() != null) {
                neg.setMaeCausaExternaValor(per.getMaeCausaExternaValor());
            }
        }
        if (per.getMaDiagnosticoSalidaCodigo() != null) {
            neg.setMaDiagnosticoSalidaCodigo(per.getMaDiagnosticoSalidaCodigo());
            if (per.getMaDiagnosticoSalidaId() != null) {
                neg.setMaDiagnosticoSalidaId(per.getMaDiagnosticoSalidaId());
            }
            if (per.getMaDiagnosticoSalidaValor() != null) {
                neg.setMaDiagnosticoSalidaValor(per.getMaDiagnosticoSalidaValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO1
        if (per.getMaDiagnosticoRelacionado1Codigo() != null) {
            neg.setMaDiagnosticoRelacionado1Codigo(per.getMaDiagnosticoRelacionado1Codigo());
            if (per.getMaDiagnosticoRelacionado1Id() != null) {
                neg.setMaDiagnosticoRelacionado1Id(per.getMaDiagnosticoRelacionado1Id());
            }
            if (per.getMaDiagnosticoRelacionado1Valor() != null) {
                neg.setMaDiagnosticoRelacionado1Valor(per.getMaDiagnosticoRelacionado1Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO2
        if (per.getMaDiagnosticoRelacionado2Codigo() != null) {
            neg.setMaDiagnosticoRelacionado2Codigo(per.getMaDiagnosticoRelacionado2Codigo());
            if (per.getMaDiagnosticoRelacionado2Id() != null) {
                neg.setMaDiagnosticoRelacionado2Id(per.getMaDiagnosticoRelacionado2Id());
            }
            if (per.getMaDiagnosticoRelacionado2Valor() != null) {
                neg.setMaDiagnosticoRelacionado2Valor(per.getMaDiagnosticoRelacionado2Valor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO3
        if (per.getMaDiagnosticoMuerteCodigo() != null) {
            neg.setMaDiagnosticoMuerteCodigo(per.getMaDiagnosticoMuerteCodigo());
            if (per.getMaDiagnosticoMuerteId() != null) {
                neg.setMaDiagnosticoMuerteId(per.getMaDiagnosticoMuerteId());
            }
            if (per.getMaDiagnosticoMuerteValor() != null) {
                neg.setMaDiagnosticoMuerteValor(per.getMaDiagnosticoMuerteValor());
            }
        }
        //MAE DESTINO SALIDA
        if (per.getMaeDestinoSalidaCodigo() != null) {
            neg.setMaeDestinoSalidaCodigo(per.getMaeDestinoSalidaCodigo());
            if (per.getMaeDestinoSalidaId() != null) {
                neg.setMaeDestinoSalidaId(per.getMaeDestinoSalidaId());
            }
            if (per.getMaeDestinoSalidaValor() != null) {
                neg.setMaeDestinoSalidaValor(per.getMaeDestinoSalidaValor());
            }
        }
        //MAE ESTADO SALIDA
        if (per.getMaeEstadoSalidaCodigo() != null) {
            neg.setMaeEstadoSalidaCodigo(per.getMaeEstadoSalidaCodigo());
            if (per.getMaeEstadoSalidaId() != null) {
                neg.setMaeEstadoSalidaId(per.getMaeEstadoSalidaId());
            }
            if (per.getMaeEstadoSalidaValor() != null) {
                neg.setMaeEstadoSalidaValor(per.getMaeEstadoSalidaValor());
            }
        }
        //MA DIAGNOSTICO RELACIONADO3
        if (per.getMaDiagnosticoMuerteCodigo() != null) {
            neg.setMaDiagnosticoMuerteCodigo(per.getMaDiagnosticoMuerteCodigo());
            if (per.getMaDiagnosticoMuerteId() != null) {
                neg.setMaDiagnosticoMuerteId(per.getMaDiagnosticoMuerteId());
            }
            if (per.getMaDiagnosticoMuerteValor() != null) {
                neg.setMaDiagnosticoMuerteValor(per.getMaDiagnosticoMuerteValor());
            }
        }
        neg.setFechaSalida(per.getFechaSalida());
        neg.setHoraSalida(per.getHoraSalida());
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setArchivoRuta(per.getArchivoRuta());
        neg.setArchivoNombreOriginal(per.getArchivoNombreOriginal());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

    public static CmRipsUsUsuario castEntidadNegocio(CmRipsCargaUsUsuarios per) {
        CmRipsUsUsuario neg = new CmRipsUsUsuario();
        if (per.getId() != null) {
            neg.setId(per.getId());
        }
        neg.setCmRipsCarga(new CmRipsCarga(per.getCmRipsCargasId().getId()));
        //MAE TIPO DOCUMENTO
        if (per.getMaeTipoDocumentoCodigo() != null) {
            neg.setMaeTipoDocumentoCodigo(per.getMaeTipoDocumentoCodigo());
            if (per.getMaeTipoDocumentoId() != null) {
                neg.setMaeTipoDocumentoId(per.getMaeTipoDocumentoId());
            }
            if (per.getMaeTipoDocumentoValor() != null) {
                neg.setMaeTipoDocumentoValor(per.getMaeTipoDocumentoValor());
            }
        }
        neg.setDocumentoAfiliado(per.getDocumentoAfiliado());
        if (per.getAdministradora() != null) {
            neg.setAdministradora(per.getAdministradora());
        }
        //MAE TIPO USUARIO
        if (per.getMaeTipoUsuarioCodigo() != null) {
            neg.setMaeTipoUsuarioCodigo(per.getMaeTipoUsuarioCodigo());
            if (per.getMaeTipoUsuarioId() != null) {
                neg.setMaeTipoUsuarioId(per.getMaeTipoUsuarioId());
            }
            if (per.getMaeTipoUsuarioValor() != null) {
                neg.setMaeTipoUsuarioValor(per.getMaeTipoUsuarioValor());
            }
        }
        neg.setPrimerApellido(per.getPrimerApellido());
        neg.setSegundoApellido(per.getSegundoApellido());
        neg.setPrimerNombre(per.getPrimerNombre());
        neg.setSegundoNombre(per.getSegundoNombre());
        if (per.getEdad() != null) {
            neg.setEdad(per.getEdad());
        }
        if (per.getCodigoUnidadMedidaEdad() != null) {
            neg.setCodigoUnidadMedidaEdad(per.getCodigoUnidadMedidaEdad());
        }
        //MAE SEXO
        if (per.getMaeSexoCodigo() != null) {
            neg.setMaeSexoCodigo(per.getMaeSexoCodigo());
            if (per.getMaeSexoId() != null) {
                neg.setMaeSexoId(per.getMaeTipoUsuarioId());
            }
            if (per.getMaeSexoValor() != null) {
                neg.setMaeSexoValor(per.getMaeSexoValor());
            }
        }
        //MAE DEPARTAMENTO
        if (per.getDepartamentoCodigo() != null) {
            neg.setDepartamentoCodigo(per.getDepartamentoCodigo());
            if (per.getDepartamentoId() != null) {
                neg.setDepartamentoId(per.getDepartamentoId());
            }
            if (per.getDepartamentoNombre() != null) {
                neg.setDepartamentoNombre(per.getDepartamentoNombre());
            }
        }
        //MAE CIUDAD
        if (per.getCiudadCodigo() != null) {
            neg.setCiudadCodigo(per.getCiudadCodigo());
            if (per.getCiudadId() != null) {
                neg.setCiudadId(per.getCiudadId());
            }
            if (per.getCiudadNombre() != null) {
                neg.setCiudadNombre(per.getCiudadNombre());
            }
        }
        //MAE ZONA RESIDENCIA
        if (per.getMaeZonaResidenciaCodigo() != null) {
            neg.setMaeZonaResidenciaCodigo(per.getMaeZonaResidenciaCodigo());
            if (per.getMaeZonaResidenciaId() != null) {
                neg.setMaeZonaResidenciaId(per.getMaeZonaResidenciaId());
            }
            if (per.getMaeZonaResidenciaValor() != null) {
                neg.setMaeZonaResidenciaValor(per.getMaeZonaResidenciaValor());
            }
        }
        neg.setArchivoNombreOriginal(per.getArchivoNombreOriginal());
        neg.setArchivoRuta(per.getArchivoRuta());
        neg.setArchivoNombre(per.getArchivoNombre());
        neg.setUsuarioCrea(per.getUsuarioCrea());
        neg.setTerminalCrea(per.getTerminalCrea());
        neg.setFechaHoraCrea(per.getFechaHoraCrea());
        return neg;
    }

}
