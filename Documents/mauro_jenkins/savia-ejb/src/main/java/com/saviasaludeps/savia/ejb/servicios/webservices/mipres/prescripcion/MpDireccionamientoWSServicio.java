package com.saviasaludeps.savia.ejb.servicios.webservices.mipres.prescripcion;

import com.saviasaludeps.savia.dominio.mipres.MpDescripcionEntregaCoigo;
import com.saviasaludeps.savia.dominio.mipres.MpDireccionamiento;
import com.saviasaludeps.savia.dominio.mipres.MpDireccionamientoEntregado;
import com.saviasaludeps.savia.dominio.mipres.MpEntregaFactura;
import com.saviasaludeps.savia.dominio.mipres.MpEntregaSuministro;
import com.saviasaludeps.savia.dominio.mipres.MpNoDireccionado;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcion;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionInsumo;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionMedicamento;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionTecnologia;
import com.saviasaludeps.savia.ejb.entidades.MpDireccionamientos;
import com.saviasaludeps.savia.ejb.entidades.MpNoDireccionados;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionInsumos;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionMedicamentos;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripcionTecnologias;
import com.saviasaludeps.savia.ejb.entidades.MpDireccionamientoEntregados;
import com.saviasaludeps.savia.ejb.entidades.MpEntregaFacturas;
import com.saviasaludeps.savia.ejb.entidades.MpEntregaSuministros;
import com.saviasaludeps.savia.ejb.utilidades.GenericoServicio;
import com.saviasaludeps.savia.ejb.entidades.MpPrescripciones;
import com.saviasaludeps.savia.negocio.webservices.mipres.prescripcion.MpDireccionamientoWSRemoto;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

@Stateless
@Remote(MpDireccionamientoWSRemoto.class)
public class MpDireccionamientoWSServicio extends GenericoServicio implements MpDireccionamientoWSRemoto {

    private final SimpleDateFormat formatoFH = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final SimpleDateFormat formatoF = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public int insertarMpDireccionamiento(
            MpDireccionamiento direccionamiento) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(
                    castEntidadNegocioMpDireccionamiento(direccionamiento)).getId();
        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    @Override
    public int mergeEntregaFactura(
            MpEntregaFactura factura) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(
                    castEntidadNegocioEntregaFactura(factura)).getId();
        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    public static MpEntregaFacturas castEntidadNegocioEntregaFactura(MpEntregaFactura obj) throws java.lang.Exception {
        MpEntregaFacturas per = new MpEntregaFacturas();
        try {

            if (obj.getMpDireccionamientoEntregadoId().getId() != null) {
                MpDireccionamientoEntregados dir = new MpDireccionamientoEntregados();
                dir.setId(obj.getMpDireccionamientoEntregadoId().getId());
                per.setMpDireccionamientoEntregadosId(dir);
            }
            per.setNit(obj.getNit());
            per.setCodigoFacturado(obj.getCodigoFacturado());
            per.setIdTransaccion(obj.getIdTransaccion());
            per.setIdFacturacion(obj.getIdFacturacion());
            per.setNoSubEntrega(obj.getNoSubEntrega());
            if (obj.getNoFactura() != null) {
                per.setNoFactura(obj.getNoFactura().trim());
            }
            if (obj.getCufe() != null) {
                per.setCufe(obj.getCufe().trim());
            }
            per.setNoidEPS(obj.getNoidEPS());
            per.setCodEPS(obj.getCodEPS());
            per.setCantUnitaria(obj.getCantUnitaria());
            per.setValorTotal(obj.getValorTotal());
            per.setValorUnitario(obj.getValorUnitario());
            per.setCuotaModeradora(obj.getCuotaModeradora());
            per.setCopago(obj.getCopago());
            per.setFechaFacturacion(obj.getFechaFacturacion());
            per.setEstado(obj.getEstado());
            per.setFechaAnulacion(obj.getFechaAnulacion());
            per.setMpEntregaFacturascol(obj.getMpEntregaFacturascol());
            per.setFechaConsumo(obj.getFechaConsumo());
            per.setUsuarioCrea(obj.getUsuarioCrea());
            per.setTerminalCrea(obj.getTerminalCrea());
            per.setFechaHoraCrea(obj.getFechaHoraCrea());
        } catch (Exception ex) {
            throw new Exception("Error en castEntidadNegocioMpDireccionamiento - insertarMpDireccionamiento ");
        }
        return per;
    }

    @Override
    public int insertarSuministroEntrega(
            MpEntregaSuministro suministro) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(
                    castEntidadNegocioSuministro(suministro)).getId();
        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    public static MpEntregaSuministros castEntidadNegocioSuministro(MpEntregaSuministro obj) throws java.lang.Exception {
        MpEntregaSuministros per = new MpEntregaSuministros();
        try {
            // Entrega
            if (obj.getMpDireccionamientoEntregadoId() != null) {
                per.setMpDireccionamientoEntregadosId(new MpDireccionamientoEntregados(obj.getMpDireccionamientoEntregadoId().getId()));
            }
            per.setIdSuministro(obj.getIdSuministro());
            per.setFechaHoraSuminisro(obj.getFechaSuministro());
            per.setUltimaEntrega(obj.getUltimaEntrega());
            per.setAnulado(obj.getAnulado());
            per.setFechaHoraAnula(obj.getFechaAnulacion());
            per.setEstadoMipres(1);
            per.setNumeroPrescripcionAsociada(obj.getNumeroPrescripcionAsociada());
            //per.setFechaConsumo(obj.getFechaConsumo());
            per.setUsuarioCrea(obj.getUsuarioCrea());
            per.setTerminalCrea(obj.getTerminalCrea());
            per.setFechaHoraCrea(obj.getFechaHoraCrea());

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new Exception("Error en castEntidadNegocioMpDireccionamientoEntregado: " + e.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Error en castEntidadNegocioMpDireccionamientoEntregado: " + ex.getMessage(), ex);

        }
        return per;
    }

    public static MpDireccionamientos castEntidadNegocioMpDireccionamiento(MpDireccionamiento obj) throws java.lang.Exception {
        MpDireccionamientos per = new MpDireccionamientos();
        try {
            // Prescripcion
            MpPrescripciones pre = new MpPrescripciones();
            pre.setId(obj.getMpPrescripcionId().getId());
            per.setMpPrescripcionesId(pre);
            // Tecnologia
            if (obj.getMpPrescripcionMedicamentoId().getId() != null) {
                MpPrescripcionMedicamentos med = new MpPrescripcionMedicamentos();
                med.setId(obj.getMpPrescripcionMedicamentoId().getId());
                per.setMpPrescripcionMedicamentosId(med);
            }
            if (obj.getMpPrescripcionTecnologiaId().getId() != null) {
                MpPrescripcionTecnologias tec = new MpPrescripcionTecnologias();
                tec.setId(obj.getMpPrescripcionTecnologiaId().getId());
                per.setMpPrescripcionTecnologiasId(tec);
            }
            if (obj.getMpPrescripcionInsumoId().getId() != null) {
                MpPrescripcionInsumos ins = new MpPrescripcionInsumos();
                ins.setId(obj.getMpPrescripcionInsumoId().getId());
                per.setMpPrescripcionInsumosId(ins);
            }
            // Direccionamiento
            per.setIdTransaccion(obj.getIdTransaccion());
            per.setIdDireccionamiento(obj.getIdDireccionamiento());
            per.setConsecutivoTecAsociada(obj.getConsecutivoTecAsociada());
            per.setEntregaTotal(obj.getEntregaTotal());
            per.setSubEntrega(obj.getSubEntrega());
            per.setFechaMaxEntrega(obj.getFechaMaxEntrega());
            per.setEntregadoPendiente(obj.getEntregadoPendiente());
            per.setFechaDireccionamiento(obj.getFechaDireccionamiento());
            per.setFechaAnulacionDireccionamiento(obj.getFechaAnulacionDireccionamiento());
            per.setMaeTipoDocumentoPrestadorId(obj.getMaeTipoDocumentoPrestadorId());
            per.setMaeTipoDocumentoPrestadorCodigo(obj.getMaeTipoDocumentoPrestadorCodigo());
            per.setMaeTipoDocumentoPrestadorValor(obj.getMaeTipoDocumentoPrestadorValor());
            per.setPrestadorNumeroDocumento(obj.getPrestadorNumeroDocumento().intValue());
            per.setMaeTipoDocumentoId(obj.getMaeTipoDocumentoPacienteId());
            per.setMaeTipoDocumentoCodigo(obj.getMaeTipoDocumentoPacienteCodigo());
            per.setMaeTipoDocumentoValor(obj.getMaeTipoDocumentoPacienteValor());
            per.setConsecutivoEntrega(obj.getConsecutivoEntrega());
            per.setUbicacionSedeId(obj.getUbicacionSedeIdStr());
            per.setTipoTecnologia(obj.getTipoTecnologia());
            per.setEstado(obj.getEstado());
            per.setCodigoMpEntrega(obj.getCodigoMpEntrega());
            per.setUsuarioCrea(obj.getUsuarioCrea());
            per.setTerminalCrea(obj.getTerminalCrea());
            per.setFechaHoraCrea(obj.getFechaHoraCrea());
        } catch (Exception ex) {
            throw new Exception("Error en castEntidadNegocioMpDireccionamiento - insertarMpDireccionamiento ");
        }
        return per;
    }

    @Override
    public int insertarMpNoDireccionamiento(
            MpNoDireccionado noDireccionamiento) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(
                    castEntidadNegocioMpNoDireccionamiento(noDireccionamiento)).getId();
        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    public static MpNoDireccionados castEntidadNegocioMpNoDireccionamiento(MpNoDireccionado obj) throws java.lang.Exception {
        MpNoDireccionados per = new MpNoDireccionados();
        try {
            // Prescripcion
            MpPrescripciones pre = new MpPrescripciones();
            pre.setId(obj.getMpPrescripcionesId().getId());
            per.setMpPrescripcionesId(pre);
            // Tecnologia
            if (obj.getMpPrescripcionMedicamentosId() != null) {
                MpPrescripcionMedicamentos med = new MpPrescripcionMedicamentos();
                med.setId(obj.getMpPrescripcionMedicamentosId().getId());
                per.setMpPrescripcionMedicamentosId(med);
            }
            if (obj.getMpPrescripcionTecnologiasId() != null) {
                MpPrescripcionTecnologias tec = new MpPrescripcionTecnologias();
                tec.setId(obj.getMpPrescripcionTecnologiasId().getId());
                per.setMpPrescripcionTecnologiasId(tec);
            }
            if (obj.getMpPrescripcionInsumosId() != null) {
                MpPrescripcionInsumos ins = new MpPrescripcionInsumos();
                ins.setId(obj.getMpPrescripcionInsumosId().getId());
                per.setMpPrescripcionInsumosId(ins);
            }
            //NoDireccionamiento
            
            per.setIdNoDireccionamiento(obj.getIdNoDireccionamiento());
            per.setTipoTecnologia(obj.getTipoTecnologia());
            per.setCodigoNoDireccionamiento(obj.getCodigoNoDireccionamiento());
            per.setCodigoNoDireccionamiento(obj.getCodigoNoDireccionamiento());
            per.setNumeroPrescripcionAsociada(obj.getNumeroPrescripcionAsociada());
            per.setConTecAsociada(obj.getConTecAsociada());
//            per.setFecNoDireccionamiento(pendiente);
            per.setFechaAnulacion(obj.getFechaAnulacion());
            per.setUsuarioCrea(obj.getUsuarioCrea());
            per.setTerminalCrea(obj.getTerminalCrea());
            per.setFechaHoraCrea(obj.getFechaHoraCrea());
        } catch (Exception ex) {
            throw new Exception("Error en castEntidadNegocioMpDireccionamiento - insertarMpDireccionamiento ");
        }
        return per;
    }

    @Override
    public Map<String, MpDireccionamiento> consultarListaMpDireccionamientos(
            List<String> listaPrescripciones) throws java.lang.Exception {

        Map<String, MpDireccionamiento> mapDireccionamientos = new HashMap<>();
        try {
            if (!listaPrescripciones.isEmpty()) {
                StringBuilder strQuery = new StringBuilder();
                strQuery.append("FROM MpDireccionamientos p ")
                        .append(" WHERE p.estado != 0 ")
                        .append(" AND (p.mpPrescripcionesId.numeroPrescripcion) IN (");
                StringBuilder strWhere = new StringBuilder();
                for (String pre : listaPrescripciones) {
                    strWhere.append("('").append(pre).append("'),");
                }
                String whereIn = strWhere.toString();
                whereIn = whereIn.substring(0, whereIn.length() - 1);
                strQuery.append(whereIn);
                strQuery.append(")");
                List<MpDireccionamientos> list = getEntityManager()
                        .createQuery(strQuery.toString())
                        .getResultList();

                for (MpDireccionamientos per : list) {
                    MpDireccionamiento obj = new MpDireccionamiento();
                    obj.setId(per.getId());
                    obj.setConsecutivoEntrega(per.getConsecutivoEntrega());
                    obj.setEntregaTotal(per.getEntregaTotal());
                    obj.setUltimoDireccionamiento(per.getUltimoDireccionamiento());
                    obj.setFechaMaxEntrega(per.getFechaMaxEntrega());

                    //Prescripcion
                    MpPrescripcion pre = new MpPrescripcion();
                    pre.setId(obj.getMpPrescripcionId().getId());
                    pre.setNumeroPrescripcion((obj.getMpPrescripcionId().getNumeroPrescripcion()));
                    obj.setMpPrescripcionId(pre);
                    //Medicamento
                    if (obj.getMpPrescripcionMedicamentoId() != null) {
                        MpPrescripcionMedicamento med = new MpPrescripcionMedicamento();
                        med.setId(obj.getMpPrescripcionMedicamentoId().getId());
                        obj.setMpPrescripcionMedicamentoId(med);
                    }
                    //Tecnologia
                    if (obj.getMpPrescripcionTecnologiaId() != null) {
                        MpPrescripcionTecnologia tec = new MpPrescripcionTecnologia();
                        tec.setId(obj.getMpPrescripcionTecnologiaId().getId());
                        obj.setMpPrescripcionTecnologiaId(tec);
                    }
                    //Insumo
                    if (obj.getMpPrescripcionInsumoId() != null) {
                        MpPrescripcionInsumo ins = new MpPrescripcionInsumo();
                        ins.setId(obj.getMpPrescripcionInsumoId().getId());
                        obj.setMpPrescripcionInsumoId(ins);
                    }
                    mapDireccionamientos.put(per.getMpPrescripcionesId().getNumeroPrescripcion() + "||" + per.getConsecutivoEntrega(), obj);
                }
            }
        } catch (NoResultException e) {
            mapDireccionamientos = null;
        } catch (Exception e) {
            throw new Exception("Error consultarListaMpDireccionamientos  - " + e.getMessage());
        } finally {
            cerrarEntityManager();
        }
        return mapDireccionamientos;
    }

    @Override
    public Map<String, MpDireccionamiento> consultarListaMpDireccionamientosTransaccion(
            List<String> listaTransacciones) throws Exception {

        Map<String, MpDireccionamiento> mapDireccionamientos = new HashMap<>();
        try {
            if (!listaTransacciones.isEmpty()) {
                StringBuilder strQuery = new StringBuilder();
                strQuery.append("FROM MpDireccionamientos p WHERE p.idTransaccion IN (");
                strQuery.append(listaTransacciones.stream()
                        .map(pre -> "'" + pre + "'")
                        .collect(Collectors.joining(",")));
                strQuery.append(")");

                List<MpDireccionamientos> list = getEntityManager()
                        .createQuery(strQuery.toString(), MpDireccionamientos.class)
                        .getResultList();

                for (MpDireccionamientos per : list) {
                    // Mapear la clave del mapa
                    String clave = per.getIdTransaccion().toString();

                    // Reutilizar el objeto original si ya está cargado
                    MpDireccionamiento obj = new MpDireccionamiento();
                    obj.setId(per.getId());
                    obj.setConsecutivoEntrega(per.getConsecutivoEntrega());
                    obj.setEntregaTotal(per.getEntregaTotal());
                    obj.setCodigoMpEntrega(per.getCodigoMpEntrega());
                    obj.setUltimoDireccionamiento(per.getUltimoDireccionamiento());
                    obj.setFechaMaxEntrega(per.getFechaMaxEntrega());
                    obj.setEstado(per.getEstado());

                    // Relación con prescripción
                    MpPrescripcion pre = new MpPrescripcion();
                    pre.setId(per.getMpPrescripcionesId().getId());
                    pre.setNumeroPrescripcion(per.getMpPrescripcionesId().getNumeroPrescripcion());
                    obj.setMpPrescripcionId(pre);

                    // Relación con medicamento
                    if (per.getMpPrescripcionMedicamentosId() != null) {
                        MpPrescripcionMedicamento med = new MpPrescripcionMedicamento();
                        med.setId(per.getMpPrescripcionMedicamentosId().getId());
                        obj.setMpPrescripcionMedicamentoId(med);
                    }

                    // Relación con tecnología
                    if (per.getMpPrescripcionTecnologiasId() != null) {
                        MpPrescripcionTecnologia tec = new MpPrescripcionTecnologia();
                        tec.setId(per.getMpPrescripcionTecnologiasId().getId());
                        obj.setMpPrescripcionTecnologiaId(tec);
                    }

                    // Relación con insumo
                    if (per.getMpPrescripcionInsumosId() != null) {
                        MpPrescripcionInsumo ins = new MpPrescripcionInsumo();
                        ins.setId(per.getMpPrescripcionInsumosId().getId());
                        obj.setMpPrescripcionInsumoId(ins);
                    }

                    mapDireccionamientos.put(clave, obj);
                }
            }
        } catch (NoResultException e) {
            // Retornar mapa vacío en caso de no encontrar resultados
            return mapDireccionamientos;
        } catch (Exception e) {
            throw new Exception("Error en consultarListaMpDireccionamientosTransaccion - " + e.getMessage(), e);
        } finally {
            cerrarEntityManager();
        }
        return mapDireccionamientos;
    }

    @Override
    public int insertarMpEntregaFacturada(
            MpEntregaFactura factura) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(
                    castEntidadNegocioMpEntregaFactura(factura)).getId();
        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    public static MpEntregaFacturas castEntidadNegocioMpEntregaFactura(MpEntregaFactura obj) throws java.lang.Exception {
        MpEntregaFacturas per = new MpEntregaFacturas();
        try {
            // Entrega
            if (obj.getMpDireccionamientoEntregadoId() != null) {
                MpDireccionamientoEntregados dir = new MpDireccionamientoEntregados();
                dir.setId(obj.getMpDireccionamientoEntregadoId().getId());
                per.setMpDireccionamientoEntregadosId(dir);
            }
            per.setEstado(obj.getEstado());
            per.setIdTransaccion(obj.getIdTransaccion());
            per.setIdFacturacion(obj.getIdFacturacion());
            per.setNoSubEntrega(obj.getNoSubEntrega());
            per.setCufe(obj.getNoFactura());
            per.setNoFactura(obj.getNoFactura());
            per.setNoidEPS(obj.getNoidEPS());
            per.setCodEPS(obj.getCodEPS());
            per.setCantUnitaria(obj.getCantUnitaria());
            per.setValorTotal(obj.getValorTotal());
            per.setValorUnitario(obj.getValorUnitario());
            per.setCuotaModeradora(obj.getCuotaModeradora());
            per.setCopago(obj.getCopago());
            per.setFechaFacturacion(obj.getFechaFacturacion());
            per.setFechaAnulacion(obj.getFechaAnulacion());
            per.setMpEntregaFacturascol(obj.getMpEntregaFacturascol());
            per.setUsuarioCrea(obj.getUsuarioCrea());
            per.setTerminalCrea(obj.getTerminalCrea());
            per.setFechaHoraCrea(obj.getFechaHoraCrea());

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new Exception("Error en castEntidadNegocioMpDireccionamientoEntregado: " + e.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Error en castEntidadNegocioMpDireccionamientoEntregado: " + ex.getMessage(), ex);

        }
        return per;
    }

    public static MpEntregaFactura castNegocioDtoMpEntregaFactura(MpEntregaFacturas obj) {
        MpEntregaFactura per = new MpEntregaFactura();
        try {
            // Entrega
            if (obj.getMpDireccionamientoEntregadosId() != null) {
                MpDireccionamientoEntregado dir = new MpDireccionamientoEntregado();
                dir.setId(obj.getMpDireccionamientoEntregadosId().getId());
                per.setMpDireccionamientoEntregadoId(dir);
            }
            per.setId(obj.getId());
            per.setEstado(obj.getEstado());
            per.setIdTransaccion(obj.getIdTransaccion());
            per.setIdFacturacion(obj.getIdFacturacion());
            per.setNoSubEntrega(obj.getNoSubEntrega());
            per.setCufe(obj.getNoFactura());
            per.setNoFactura(obj.getNoFactura());
            per.setNoidEPS(obj.getNoidEPS());
            per.setCodEPS(obj.getCodEPS());
            per.setCantUnitaria(obj.getCantUnitaria());
            per.setValorTotal(obj.getValorTotal());
            per.setValorUnitario(obj.getValorUnitario());
            per.setCuotaModeradora(obj.getCuotaModeradora());
            per.setCopago(obj.getCopago());
            per.setFechaFacturacion(obj.getFechaFacturacion());
            per.setFechaAnulacion(obj.getFechaAnulacion());
            per.setMpEntregaFacturascol(obj.getMpEntregaFacturascol());
            per.setUsuarioCrea(obj.getUsuarioCrea());
            per.setTerminalCrea(obj.getTerminalCrea());
            per.setFechaHoraCrea(obj.getFechaHoraCrea());

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return per;
    }

    @Override
    public int insertarMpDireccionamientoEntregado(
            MpDireccionamientoEntregado entrega) throws Exception {
        int _id = 0;
        try {
            _id = (int) getEntityManager().merge(
                    castEntidadNegocioMpDireccionamientoEntregado(entrega)).getId();
        } catch (NoResultException e) {
            _id = 0;
        } catch (Exception e) {
            Exception(INSERTAR, e);
        } finally {
            cerrarEntityManager();
        }
        return _id;
    }

    public static MpDireccionamientoEntregados castEntidadNegocioMpDireccionamientoEntregado(MpDireccionamientoEntregado obj) throws java.lang.Exception {
        MpDireccionamientoEntregados per = new MpDireccionamientoEntregados();
        try {
//            // Direccionamiento
            if (obj.getMpDireccionamientoId() != null) {
                MpDireccionamientos dir = new MpDireccionamientos();
                dir.setId(obj.getMpDireccionamientoId().getId());
                per.setMpDireccionamientoId(dir);
            }
            // MpPrescripciones
            MpPrescripciones pr = new MpPrescripciones();
            pr.setId(obj.getMpPrescripcion().getId());
            per.setMpPrescripcionId(pr);
            // MpPrescripcionMedicamentos
            if (obj.getMpPrescripcionMedicamentoId() != null) {
                MpPrescripcionMedicamentos med = new MpPrescripcionMedicamentos();
                med.setId(obj.getMpPrescripcionMedicamentoId().getId());
                per.setMpPrescripicionMedicamentosId(med);
            }
            // MpPrescripcionTecnologias
            if (obj.getMpPrescripcionTecnologiaId() != null) {
                MpPrescripcionTecnologias tec = new MpPrescripcionTecnologias();
                tec.setId(obj.getMpPrescripcionTecnologiaId().getId());
                per.setMpPrescripicionTecnologiasId(tec);
            }
            // MpPrescripcionTecnologias
            if (obj.getMpPrescripcionInsumoId() != null) {
                MpPrescripcionInsumos ins = new MpPrescripcionInsumos();
                ins.setId(obj.getMpPrescripcionInsumoId().getId());
                per.setMpPrescripicionInsumosId(ins);;
            }
            //Entrega
            per.setTipoTecnologia(obj.getTipoTecnologia());

            per.setEntregaCompleta(Boolean.TRUE.equals(obj.getEntregaCompleta()) ? 1 : 0);
//           
            per.setIdTransaccion(obj.getIdTransaccion());
            per.setIdReporteEntrega(obj.getIdReporteEntrega());
            per.setCantidadEntrega(obj.getCantidadEntrega());
            per.setNumeroEntrega(obj.getNumeroEntrega());
            per.setCausaNoEntrega(obj.getCausaNoEntrega());
            per.setEstadoEntrega(obj.getEstadoEntrega());
            per.setFechaEntrega(obj.getFechaEntrega());
//            per.setCierreCiclo(obj.getCierreCiclo());
            per.setFechaAnulacion(obj.getFechaAnulacion());
            per.setFechaReporteFactura(obj.getFechaReporteFactura());
            per.setCodTecEntregado(obj.getCodTecEntregado());
            per.setDescTecEntregado(obj.getDescTecEntregado());
            per.setNumeroLote(obj.getNumeroLote());
            per.setValorTotal(obj.getValorTotal());
            per.setEstRepEntrega(obj.getEstRepEntrega());
//            per.setIdSuministro(obj.getIdSuministro());
            per.setFechaConsumo(obj.getFechaConsumo());
            per.setUsuarioCrea(obj.getUsuarioCrea());
            per.setTerminalCrea(obj.getTerminalCrea());
            per.setFechaHoraCrea(obj.getFechaHoraCrea());

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new Exception("Error en castEntidadNegocioMpDireccionamientoEntregado: " + e.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Error en castEntidadNegocioMpDireccionamientoEntregado: " + ex.getMessage(), ex);

        }
        return per;
    }

    @Override
    public String consultarDescripcion(String codigo, Integer tipo) {
        String descripcion = "";
        if (null != tipo) {
            switch (tipo) {
                case 1:
                    descripcion = consultarDescripcionMaMedicamento(codigo);
                    if (descripcion == null) {
                        descripcion = consultarDescripcionMaInsumo(codigo);
                    }
                    if (descripcion == null) {
                        descripcion = "";
                    }
                    break;
                case 2:
                    descripcion = consultarDescripcionMaTenologia(codigo);
                    if (descripcion == null) {
                        descripcion = consultarDescripcionMaInsumo(codigo);
                    }
                    if (descripcion == null) {
                        descripcion = "";
                    }
                    break;
                case 3:
                case 4:
                case 5:
                    descripcion = consultarDescripcionMaInsumo(codigo);
                    if (descripcion == null) {
                        descripcion = "";
                    }
                    break;

                default:
                    break;
            }
        }
        return descripcion;
    }

    @Override
    public MpDescripcionEntregaCoigo consultarDescripciond(String codigo, Integer tipo) {
        MpDescripcionEntregaCoigo obj = new MpDescripcionEntregaCoigo();
        if (null != tipo) {
            switch (tipo) {
                case 1:
                    obj.setDescripcion(consultarDescripcionMaMedicamento(codigo));
                    if (obj.getDescripcion() == null) {
                        obj.setDescripcion(consultarDescripcionMaInsumo(codigo));
                    }
                    if (obj.getDescripcion() == null) {
                        obj.setDescripcion(null);
                        obj.setCodigo(null);
                    } else {
                        obj.setCodigo(2);
                    }
                    break;
                case 2:
                    obj.setDescripcion(consultarDescripcionMaTenologia(codigo));
                    if (obj.getDescripcion() == null) {
                        obj.setDescripcion(consultarDescripcionMaInsumo(codigo));
                    }
                    if (obj.getDescripcion() == null) {
                        obj.setDescripcion(null);
                        obj.setCodigo(null);
                    } else {
                        obj.setCodigo(1);
                    }
                    break;
                case 3:
                    obj.setDescripcion(consultarDescripcionMaInsumo(codigo));
                    if (obj.getDescripcion() == null) {
                        obj.setDescripcion(consultarDescripcionMaTenologiaMipres(codigo));
                    }
                    if (obj.getDescripcion() == null) {
                        obj.setDescripcion(consultarDescripcionMaTenologia(codigo));
                    }
                    if (obj.getDescripcion() == null) {
                        obj.setDescripcion(null);
                        obj.setCodigo(null);
                    } else {
                        obj.setCodigo(3);
                    }
                    break;
                case 4:
                    obj.setDescripcion(consultarDescripcionMaInsumo(codigo));
                    if (obj.getDescripcion() == null) {
                        obj.setDescripcion(null);
                        obj.setCodigo(null);
                    } else {
                        obj.setCodigo(3);
                    }
                    break;
                case 5:
                    obj.setDescripcion(consultarDescripcionMaInsumo(codigo));
                    if (obj.getDescripcion() == null) {
                        obj.setDescripcion(null);
                        obj.setCodigo(null);
                    } else {
                        obj.setCodigo(3);
                    }
                    break;
                default:
                    break;
            }
        }
        return obj;
    }

    public String consultarDescripcionMaMedicamento(String codigo) {
        String descripcion = null;

        codigo = codigo.trim();
        String[] partes = codigo.split("-");
        if (partes.length == 2) {
            String parte1 = partes[0].replaceFirst("^0+", "");
            String parte2 = partes[1].replace("0", "");
            codigo = parte1 + "-" + parte2;
        }
        try {
            String strQuery = " SELECT e.descripcionEstandarizada "
                    + " FROM MaMedicamentos e "
                    + " WHERE e.cum = :cum ";
            descripcion = (String) getEntityManager()
                    .createQuery(strQuery)
                    .setParameter("cum", codigo)
                    .getSingleResult();
        } catch (NoResultException e) {
            descripcion = null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarEntityManager();
        }
        return descripcion;
    }

    public String consultarDescripcionMaInsumo(String codigo) {
        String descripcion = null;
        try {
            String strQuery = "SELECT e.descripcion FROM MpCodigoInsumos e WHERE e.codigoMipres = :cod";
            descripcion = (String) getEntityManager()
                    .createQuery(strQuery)
                    .setParameter("cod", codigo)
                    .getSingleResult();
        } catch (NoResultException e) {
            descripcion = null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarEntityManager();
        }
        return descripcion;
    }

    public String consultarDescripcionMaTenologia(String codigo) {
        String descripcion = null;
        try {
            String strQuery = "SELECT e.cupsDescipcion "
                    + "FROM MaTecnologias e "
                    + "WHERE e.cups = :cod "
                    + "ORDER BY (CASE WHEN e.cups = e.codigoPropio THEN 1 ELSE 2 END)";
            descripcion = (String) getEntityManager()
                    .createQuery(strQuery)
                    .setParameter("cod", codigo)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException e) {
            descripcion = null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarEntityManager();
        }
        return descripcion;
    }

    public String consultarDescripcionMaTenologiaMipres(String codigo) {
        String descripcion = null;
        try {
            String strQuery = "SELECT e.descripcion FROM MaTecnologiasMipres e WHERE e.codigoMipres = :cod";
            descripcion = (String) getEntityManager()
                    .createQuery(strQuery)
                    .setParameter("cod", codigo)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException e) {
            descripcion = null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarEntityManager();
        }
        return descripcion;
    }

    @Override
    public Map<String, MpDireccionamientoEntregado> consultarListaMpDireccionamientoEntregado(
            List<Integer> listaTransacciones) throws java.lang.Exception {

        Map<String, MpDireccionamientoEntregado> mapDireccionamientoEnt = new HashMap<>();
        try {
            if (!listaTransacciones.isEmpty()) {
                StringBuilder strQuery = new StringBuilder();
                strQuery.append("FROM MpDireccionamientoEntregados p ")
                        .append("WHERE (p.idTransaccion) IN (");
                StringBuilder strWhere = new StringBuilder();
                for (Integer pre : listaTransacciones) {
                    strWhere.append("('").append(pre).append("'),");
                }
                String whereIn = strWhere.toString();
                whereIn = whereIn.substring(0, whereIn.length() - 1);
                strQuery.append(whereIn);
                strQuery.append(")");
                List<MpDireccionamientoEntregados> list = getEntityManager()
                        .createQuery(strQuery.toString())
                        .getResultList();

                for (MpDireccionamientoEntregados per : list) {
                    MpDireccionamientoEntregado obj = new MpDireccionamientoEntregado();
                    MpPrescripcion prescripcion = new MpPrescripcion();
                    obj.setId(per.getId());

//                    MpDireccionamiento mpDireccionamiento = new MpDireccionamiento();
//                    mpDireccionamiento.setId(per.getMpDireccionamientosId().getId());
//                    obj.setMpDireccionamientoId(mpDireccionamiento);
//                    mapDireccionamientoEnt.put(prescripcion.getStrTipoTecnologia(per.getTipoTecnologia()) + per.getIdTransaccion().toString(), obj);
                }
            }
        } catch (NoResultException e) {
            mapDireccionamientoEnt = null;
        } catch (Exception e) {
            throw new Exception("Error consultarListaMpDireccionamientoEntregado  - " + e.getMessage());
        } finally {
            cerrarEntityManager();
        }
        return mapDireccionamientoEnt;
    }

    @Override
    public List<MpDireccionamientoEntregado> consultarListaMpDireccionamientoEntregadoList(
            Integer transaccion) throws java.lang.Exception {

        List<MpDireccionamientoEntregado> entregas = new ArrayList<>();
        try {
            if (transaccion != null) {
                StringBuilder strQuery = new StringBuilder();
                strQuery.append("FROM MpDireccionamientoEntregados p ")
                        .append("WHERE p.idTransaccion = :idTransaccion");

                List<MpDireccionamientoEntregados> list = getEntityManager()
                        .createQuery(strQuery.toString(), MpDireccionamientoEntregados.class)
                        .setParameter("idTransaccion", transaccion)
                        .getResultList();

                for (MpDireccionamientoEntregados per : list) {
                    MpDireccionamientoEntregado obj = new MpDireccionamientoEntregado();
                    obj.setId(per.getId());
                    if (per.getMpPrescripcionId() != null) {
                        MpPrescripcion mpPrescripcion = new MpPrescripcion();
                        mpPrescripcion.setNumeroPrescripcion(per.getMpPrescripcionId().getNumeroPrescripcion());
                        obj.setMpPrescripcion(mpPrescripcion);
                    }
                    obj.setEstRepEntrega(per.getEstRepEntrega());
                    obj.setIdTransaccion(per.getIdTransaccion());
                    obj.setCantidadEntrega(per.getCantidadEntrega());
                    obj.setNumeroEntrega(per.getNumeroEntrega());
                    obj.setFechaEntrega(per.getFechaEntrega());
                    obj.setFechaConsumo(per.getFechaConsumo());
                    obj.setFechaAnulacion(per.getFechaAnulacion());
                    obj.setFechaReporteFactura(per.getFechaReporteFactura());

                    entregas.add(obj);
                }
            }
        } catch (NoResultException e) {
            entregas = null;
        } catch (Exception e) {
            throw new Exception("Error consultando MpDireccionamientoEntregado Lista Transacciones  - " + e.getMessage());
        } finally {
            cerrarEntityManager();
        }
        return entregas;
    }

    @Override
    public List<MpEntregaFactura> consultarListaMpEntegaFactura(
            String transaccion) throws java.lang.Exception {

        List<MpEntregaFactura> entregas = new ArrayList<>();
        try {
            if (transaccion != null) {
                StringBuilder strQuery = new StringBuilder();
                strQuery.append("FROM MpEntregaFacturas p ")
                        .append("WHERE p.idTransaccion = :idTransaccion ")
                        .append("AND p.estado = 2");

                List<MpEntregaFacturas> list = getEntityManager()
                        .createQuery(strQuery.toString(), MpEntregaFacturas.class)
                        .setParameter("idTransaccion", transaccion)
                        .getResultList();

                for (MpEntregaFacturas per : list) {
                    MpEntregaFactura obj = new MpEntregaFactura();
                    obj.setId(per.getId());

                    obj.setIdTransaccion(per.getIdTransaccion());
                    obj.setEstado(per.getEstado());
                    obj.setFechaAnulacion(per.getFechaAnulacion());
                    obj.setFechaFacturacion(per.getFechaFacturacion());
                    obj.setFechaConsumo(per.getFechaConsumo());

                    entregas.add(obj);
                }
            }
        } catch (NoResultException e) {
            entregas = null;
        } catch (Exception e) {
            throw new Exception("Error consultando MpDireccionamientoEntregado Lista Transacciones  - " + e.getMessage());
        } finally {
            cerrarEntityManager();
        }
        return entregas;
    }

    @Override
    public List<MpEntregaFactura> consultarListaMpEntegaFacturaAnulada(
            String transaccion) throws java.lang.Exception {

        List<MpEntregaFactura> entregas = new ArrayList<>();
        try {
            if (transaccion != null) {
                StringBuilder strQuery = new StringBuilder();
                strQuery.append("FROM MpEntregaFacturas p ")
                        .append("WHERE p.idTransaccion = :idTransaccion ")
                        .append("AND p.estado IN (0, 1)");

                List<MpEntregaFacturas> list = getEntityManager()
                        .createQuery(strQuery.toString(), MpEntregaFacturas.class)
                        .setParameter("idTransaccion", transaccion)
                        .getResultList();

                for (MpEntregaFacturas per : list) {
                    MpEntregaFactura obj = new MpEntregaFactura();
                    obj.setId(per.getId());

                    obj.setIdTransaccion(per.getIdTransaccion());
                    obj.setEstado(per.getEstado());
                    obj.setFechaAnulacion(per.getFechaAnulacion());
                    obj.setFechaFacturacion(per.getFechaFacturacion());
                    obj.setFechaConsumo(per.getFechaConsumo());

                    entregas.add(obj);
                }
            }
        } catch (NoResultException e) {
            entregas = null;
        } catch (Exception e) {
            throw new Exception("Error consultando MpDireccionamientoEntregado Lista Transacciones  - " + e.getMessage());
        } finally {
            cerrarEntityManager();
        }
        return entregas;
    }

    @Override
    public Map<String, MpDireccionamientoEntregado> consultarListaMpDireccionamientoEntregadoLista(String pres, String tipo, String consecutivo, String entrega) {
        Map<String, MpDireccionamientoEntregado> mapDireccionamientoEnt = new HashMap<>();
        StringBuilder strQuery = new StringBuilder();

        switch (tipo.toUpperCase()) {
            case "M":
            case "N":
                strQuery.append("SELECT md FROM MpDireccionamientoEntregados md ")
                        .append("JOIN md.mpPrescripcionId mp ")
                        .append("JOIN md.mpPrescripicionMedicamentosId mpm ")
                        .append("WHERE md.fechaAnulacion IS NULL ")
                        .append("AND mpm.consecutivoOrden = :consecutivoOrden ")
                        .append("AND md.numeroEntrega = :numeroEntrega ")
                        .append("AND mp.numeroPrescripcion = :numeroPrescripcion ");
                break;

            case "D":
            case "S":
                strQuery.append("SELECT md FROM MpDireccionamientoEntregados md ")
                        .append("JOIN md.mpPrescripcionId mp ")
                        .append("JOIN md.mpPrescripcionInsumosId mpi ")
                        .append("WHERE md.fechaAnulacion IS NULL ")
                        .append("AND mpi.consecutivoOrden = :consecutivoOrden ")
                        .append("AND md.numeroEntrega = :numeroEntrega ")
                        .append("AND mp.numeroPrescripcion = :numeroPrescripcion ");
                break;

            case "P":
                strQuery.append("SELECT md FROM MpDireccionamientoEntregados md ")
                        .append("JOIN md.mpPrescripcionId mp ")
                        .append("JOIN md.mpPrescripcionTecnologiasId mpt ")
                        .append("WHERE md.fechaAnulacion IS NULL ")
                        .append("AND mpt.consecutivoOrden = :consecutivoOrden ")
                        .append("AND md.numeroEntrega = :numeroEntrega ")
                        .append("AND mp.numeroPrescripcion = :numeroPrescripcion ");
                break;

            default:
                throw new IllegalArgumentException("Tipo no válido: " + tipo);
        }

        Query query = getEntityManager().createQuery(strQuery.toString())
                .setParameter("numeroEntrega", Integer.parseInt(entrega))
                .setParameter("numeroPrescripcion", pres)
                .setParameter("consecutivoOrden", Integer.parseInt(consecutivo));

        List<MpDireccionamientoEntregados> list = query.getResultList();

        for (MpDireccionamientoEntregados per : list) {
            MpDireccionamientoEntregado entreg = castEntidadNegocioEntrega(per);

            entreg.setId(entreg.getId());

            // Generar la clave basada en pres + entrega
            String clave = pres + entrega;

//            // Verificar si la clave ya existe en el mapa
//            if (!mapDireccionamientoEnt.containsKey(clave)) {
//                mapDireccionamientoEnt.put(clave, entreg);
//            }
        }

        return mapDireccionamientoEnt;
    }

    @Override
    public List<MpDireccionamientoEntregado> consultarListaMpEntregaSuministroLista(String pres, String tipo, String consecutivo, String entrega) {

        List<MpDireccionamientoEntregado> entregas = new ArrayList<>();
        StringBuilder strQuery = new StringBuilder();
        try {
            switch (tipo.toUpperCase()) {
                case "M":
                case "N":
                    strQuery.append("SELECT de FROM MpDireccionamientoEntregados de ")
                            .append("JOIN de.mpPrescripcionId mp ")
                            .append("JOIN de.mpPrescripicionMedicamentosId mpm ")
                            .append("WHERE mp.numeroPrescripcion = :numeroPrescripcion ")
                            .append("AND mpm.consecutivoOrden = :consecutivoOrden ")
                            .append("AND de.numeroEntrega = :numeroEntrega");
                    break;

                case "D":
                case "S":
                    strQuery.append("SELECT de FROM MpDireccionamientoEntregados de ")
                            .append("JOIN de.mpPrescripcionId mp ")
                            .append("JOIN de.mpPrescripicionInsumosId mpi ")
                            .append("WHERE mp.numeroPrescripcion = :numeroPrescripcion ")
                            .append("AND mpi.consecutivoOrden = :consecutivoOrden ")
                            .append("AND de.numeroEntrega = :numeroEntrega");
                    break;

                case "P":
                    strQuery.append("SELECT de FROM MpDireccionamientoEntregados de ")
                            .append("JOIN de.mpPrescripcionId mp ")
                            .append("JOIN de.mpPrescripicionTecnologiasId mpt ")
                            .append("WHERE mp.numeroPrescripcion = :numeroPrescripcion ")
                            .append("AND mpt.consecutivoOrden = :consecutivoOrden ")
                            .append("AND de.numeroEntrega = :numeroEntrega");
                    break;

                default:
                    throw new IllegalArgumentException("Tipo no válido: " + tipo);
            }

            List<MpDireccionamientoEntregados> list = getEntityManager()
                    .createQuery(strQuery.toString(), MpDireccionamientoEntregados.class)
                    .setParameter("numeroEntrega", Integer.parseInt(entrega))
                    .setParameter("numeroPrescripcion", pres)
                    .setParameter("consecutivoOrden", Integer.parseInt(consecutivo))
                    .getResultList();

            for (MpDireccionamientoEntregados per : list) {
                MpDireccionamientoEntregado obj = new MpDireccionamientoEntregado();

                obj.setId(per.getId());
                obj.setNumeroEntrega(per.getNumeroEntrega());
                obj.setEstRepEntrega(per.getEstRepEntrega());
                obj.setIdReporteEntrega(per.getIdReporteEntrega());
                obj.setIdTransaccion(per.getIdTransaccion());
                obj.setEstadoEntrega(per.getEstadoEntrega());

                if (per.getMpDireccionamientoId() != null) {
                    MpDireccionamiento mpDireccionamiento = new MpDireccionamiento();
                    mpDireccionamiento.setId(per.getMpDireccionamientoId().getId());
                    mpDireccionamiento.setPrestadorNumeroDocumento(
                            per.getMpDireccionamientoId().getPrestadorNumeroDocumento() != null
                            ? BigInteger.valueOf(per.getMpDireccionamientoId().getPrestadorNumeroDocumento())
                            : null
                    );
                    obj.setMpDireccionamientoId(mpDireccionamiento);
                }

                if (per.getMpPrescripcionId() != null) {
                    MpPrescripcion mpPrescripcion = new MpPrescripcion();
                    mpPrescripcion.setId(per.getMpPrescripcionId().getId());
                    mpPrescripcion.setNumeroPrescripcion(per.getMpPrescripcionId().getNumeroPrescripcion());
                    mpPrescripcion.setPrestadorNumeroDocumento(per.getMpPrescripcionId().getPrestadorNumeroDocumento());
                    obj.setMpPrescripcion(mpPrescripcion);
                }
                if (per.getMpPrescripicionMedicamentosId() != null) {
                    MpPrescripcionMedicamento medicamento = new MpPrescripcionMedicamento();
                    medicamento.setId(per.getMpPrescripicionMedicamentosId().getId());
                    medicamento.setConsecutivoOrden(per.getMpPrescripicionMedicamentosId().getConsecutivoOrden());
                    obj.setMpPrescripcionMedicamentoId(medicamento);
                }
                if (per.getMpPrescripicionInsumosId() != null) {
                    MpPrescripcionInsumo insumo = new MpPrescripcionInsumo();
                    insumo.setId(per.getMpPrescripicionInsumosId().getId());
                    insumo.setConsecutivoOrden(per.getMpPrescripicionInsumosId().getConsecutivoOrden());
                    obj.setMpPrescripcionInsumoId(insumo);
                }
                if (per.getMpPrescripicionTecnologiasId() != null) {
                    MpPrescripcionTecnologia tecnologia = new MpPrescripcionTecnologia();
                    tecnologia.setId(per.getMpPrescripicionTecnologiasId().getId());
                    tecnologia.setConsecutivoOrden(per.getMpPrescripicionTecnologiasId().getConsecutivoOrden());
                    obj.setMpPrescripcionTecnologiaId(tecnologia);
                }

                obj.setFechaConsumo(per.getFechaConsumo());
                obj.setFechaReporteFactura(per.getFechaReporteFactura());
                obj.setFechaEntrega(per.getFechaEntrega());
                obj.setFechaAnulacion(per.getFechaAnulacion());
                obj.setAnulado(per.getAnulado());

                entregas.add(obj);
            }

        } catch (NoResultException e) {
            entregas = null;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            cerrarEntityManager();
        }
        return entregas;
    }

    @Override
    public MpEntregaFactura consultarEntregaFactura(String idTransaccion, String idFacturacion) {
        StringBuilder strQuery = new StringBuilder();
        strQuery.append("SELECT mpEntFac FROM MpEntregaFacturas mpEntFac ")
                .append("WHERE mpEntFac.idTransaccion = :id ")
                .append("AND mpEntFac.idFacturacion = :idFacturacion");

        try {
            MpEntregaFacturas entregaFactura = getEntityManager()
                    .createQuery(strQuery.toString(), MpEntregaFacturas.class)
                    .setParameter("id", idTransaccion)
                    .setParameter("idFacturacion", idFacturacion)
                    .getSingleResult();

            // Convertir la entidad a DTO
            return castNegocioDtoMpEntregaFactura(entregaFactura);
        } catch (NoResultException e) {
            // Registrar el evento de no encontrar resultados
            return null; // O puedes lanzar una excepción personalizada, si prefieres
        } catch (Exception e) {
            // Manejar otras excepciones de manera adecuada
            throw new RuntimeException("Error al consultar la entrega de factura: " + e.getMessage(), e);
        }
    }

    @Override
    public MpEntregaSuministro consultarEntregaSuministro(Integer idTransaccion, Integer idSuministro) {
        StringBuilder strQuery = new StringBuilder();
        strQuery.append("SELECT mpEntSum FROM MpEntregaSuministros mpEntSum ")
                //                .append("WHERE mpEntSum.idTransaccion = :id ")
                .append("WHERE mpEntSum.idSuministro = :idSuministro");

        try {
            MpEntregaSuministros entregaSum = getEntityManager()
                    .createQuery(strQuery.toString(), MpEntregaSuministros.class)
                    //                    .setParameter("id", idTransaccion)
                    .setParameter("idSuministro", idSuministro.toString())
                    .getSingleResult();

            // Convertir la entidad a 
            return castNegocioDtoMpEntregaSuministro(entregaSum);
        } catch (NoResultException e) {
            // Registrar el evento de no encontrar resultados
            return null; // O puedes lanzar una excepción personalizada, si prefieres
        } catch (Exception e) {
            // Manejar otras excepciones de manera adecuada
            throw new RuntimeException("Error al consultar la entrega de factura: " + e.getMessage(), e);
        }
    }

    public static MpEntregaSuministro castNegocioDtoMpEntregaSuministro(MpEntregaSuministros obj) {
        MpEntregaSuministro per = new MpEntregaSuministro();
        try {
            // Entrega
            if (obj.getMpDireccionamientoEntregadosId() != null) {
                MpDireccionamientoEntregado dir = new MpDireccionamientoEntregado();
                dir.setId(obj.getMpDireccionamientoEntregadosId().getId());
                per.setMpDireccionamientoEntregadoId(dir);
            }
            per.setId(obj.getId());
            per.setIdSuministro(obj.getIdSuministro());
            per.setFechaSuministro(obj.getFechaHoraSuminisro());
            per.setAnulado(obj.getAnulado());
            per.setNumeroPrescripcionAsociada(obj.getNumeroPrescripcionAsociada());
            per.setEstadoMipres(obj.getEstadoMipres());

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return per;
    }

    public static MpDireccionamientoEntregado castEntidadNegocioEntrega(MpDireccionamientoEntregados ent) {
        MpDireccionamientoEntregado obj = new MpDireccionamientoEntregado();

        obj.setId(ent.getId());
        if (ent.getMpPrescripcionId() != null) {
            obj.setMpPrescripcion(new MpPrescripcion(ent.getMpPrescripcionId().getId()));
        }
        if (ent.getMpPrescripicionMedicamentosId() != null) {
            obj.setMpPrescripcionMedicamentoId(new MpPrescripcionMedicamento(ent.getMpPrescripicionMedicamentosId().getId()));
        }
        if (ent.getMpPrescripicionTecnologiasId() != null) {
            obj.setMpPrescripcionTecnologiaId(new MpPrescripcionTecnologia(ent.getMpPrescripicionTecnologiasId().getId()));
        }
        if (ent.getMpPrescripicionInsumosId() != null) {
            obj.setMpPrescripcionInsumoId(new MpPrescripcionInsumo(ent.getMpPrescripicionInsumosId().getId()));
        }

        return obj;
    }

    @Override
    public Map<String, MpDireccionamientoEntregado> consultarListaMpDireccionamientoEntregadoXFecha(
            Date fecha) throws java.lang.Exception {
        Map<String, MpDireccionamientoEntregado> mapDireccionamientoEnt = new HashMap<>();
        try {
            StringBuilder strQuery = new StringBuilder();
            strQuery.append("FROM MpDireccionamientoEntregados p ")
                    .append("WHERE p.fechaHoraCrea > '").append(formatoFH.format(fecha))
                    .append("'");
            List<MpDireccionamientoEntregados> list = getEntityManager()
                    .createQuery(strQuery.toString())
                    .getResultList();
            for (MpDireccionamientoEntregados per : list) {
                MpDireccionamientoEntregado obj = new MpDireccionamientoEntregado();
                obj.setId(per.getId());
                MpDireccionamiento mpDireccionamiento = new MpDireccionamiento();
//                mpDireccionamiento.setId(per.getMpDireccionamientosId().getId());
                obj.setMpDireccionamientoId(mpDireccionamiento);
                obj.setIdTransaccion(per.getIdTransaccion());
//                mapDireccionamientoEnt.put(per.getIdTransaccion().toString(), obj);
            }
        } catch (NoResultException e) {
            mapDireccionamientoEnt = null;
        } catch (Exception e) {
            throw new Exception("Error consultarListaMpDireccionamientoEntregado  - " + e.getMessage());
        } finally {
            cerrarEntityManager();
        }
        return mapDireccionamientoEnt;
    }

    @Override
    public List<MpDireccionamientoEntregado> consultarSuministrosAEnviar(String regimen) throws Exception {
        List<MpDireccionamientoEntregado> listResult = new ArrayList();
        try {
            String strQuery = "FROM MpDireccionamientoEntregados mpd ";
            strQuery += " WHERE mpd.cierreSuministro = TRUE "
                    + " AND mpd.mpPrescripcionId.asegAfiliadosId.maeRegimenCodigo = '" + regimen + "'";

            Query query = getEntityManager().createQuery(strQuery).setMaxResults(100);
            List<MpDireccionamientoEntregados> list = query
                    .getResultList();
            for (MpDireccionamientoEntregados per : list) {
                MpDireccionamientoEntregado dir = new MpDireccionamientoEntregado();
                dir.setId(per.getId());
                // prescripcion
                MpPrescripcion prc = new MpPrescripcion();
                prc.setId(per.getMpPrescripcionId().getId());
                prc.setNumeroPrescripcion(per.getMpPrescripcionId().getNumeroPrescripcion());
                // tipo tecnologia
                dir.setTipoTecnologia(per.getTipoTecnologia());
                switch (per.getTipoTecnologia()) {
                    case MpPrescripcion.TIPO_MEDICAMENTO:
                        MpPrescripcionMedicamento mpMed = new MpPrescripcionMedicamento();
                        mpMed.setId(per.getMpPrescripicionMedicamentosId().getId());
                        mpMed.setConsecutivoOrden(per.getMpPrescripicionMedicamentosId().getConsecutivoOrden());
                        mpMed.setCodigoFormulaFarmaceutica(per.getMpPrescripicionMedicamentosId().getCodigoFormulaFarmaceutica());
                        dir.setMpPrescripcionMedicamentoId(mpMed);
                        break;
                    case MpPrescripcion.TIPO_TECNOLOGIA:
                        MpPrescripcionTecnologia mpTec = new MpPrescripcionTecnologia();
                        mpTec.setId(per.getMpPrescripicionTecnologiasId().getId());
                        mpTec.setConsecutivoOrden(per.getMpPrescripicionTecnologiasId().getConsecutivoOrden());
                        mpTec.setMaTecnologiaCodigo(per.getMpPrescripicionTecnologiasId().getMaTecnologiaCodigo());
                        dir.setMpPrescripcionTecnologiaId(mpTec);
                        break;
                    case MpPrescripcion.TIPO_DISPOSITIVO_MEDICO:
                        MpPrescripcionInsumo mpIns = new MpPrescripcionInsumo();
                        mpIns.setId(per.getMpPrescripicionInsumosId().getId());
                        mpIns.setConsecutivoOrden(per.getMpPrescripicionInsumosId().getConsecutivoOrden());
                        mpIns.setMaeDispositivosCodigo(per.getMpPrescripicionInsumosId().getMaeDispositivosCodigo());
                        dir.setMpPrescripcionInsumoId(mpIns);
                        break;
                    case MpPrescripcion.TIPO_PRODUCTO_NUTRICIONAL:
                        MpPrescripcionMedicamento mpPN = new MpPrescripcionMedicamento();
                        mpPN.setId(per.getMpPrescripicionMedicamentosId().getId());
                        mpPN.setConsecutivoOrden(per.getMpPrescripicionMedicamentosId().getConsecutivoOrden());
                        mpPN.setMaeProductosNutricionalesCodigo(per.getMpPrescripicionMedicamentosId().getMaeProductosNutricionalesCodigo());
                        dir.setMpPrescripcionMedicamentoId(mpPN);
                        break;
                    case MpPrescripcion.TIPO_SERVICIO_COMPLEMENTARIO:
                        MpPrescripcionInsumo mpSerC = new MpPrescripcionInsumo();
                        mpSerC.setId(per.getMpPrescripicionInsumosId().getId());
                        mpSerC.setConsecutivoOrden(per.getMpPrescripicionInsumosId().getConsecutivoOrden());
                        mpSerC.setMaeServiciosComplementariosCodigo(per.getMpPrescripicionInsumosId().getMaeServiciosComplementariosCodigo());
                        dir.setMpPrescripcionInsumoId(mpSerC);
                        break;
                    default:
                        throw new AssertionError();
                }
                dir.setMpPrescripcion(prc);
                dir.setIdTransaccion(per.getIdTransaccion());

//                if (per.getUltimaEntrega() == 1) {
//                    dir.setUltimaEntrega(true);
//                } else {
//                    dir.setUltimaEntrega(false);
//                }
                if (per.getEntregaCompleta() == 1) {
                    dir.setEntregaCompleta(true);
                } else {
                    dir.setEntregaCompleta(false);
                }
//                if (per.getUltimaEntrega() == 1) {
//                    dir.setUltimaEntrega(true);
//                } else {
//                    dir.setUltimaEntrega(false);
//                }
                dir.setCausaNoEntrega(per.getCausaNoEntrega());
                dir.setCantidadEntrega(per.getCantidadEntrega());
                dir.setValorTotal(per.getValorTotal());
                dir.setNumeroLote(per.getNumeroLote());
                listResult.add(dir);
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    //ACTUALIZAR DIRECCIONAMIENTO
    @Override
    public void actualizaSuministro(
            Integer idTransaccion,
            String idSuministro) throws Exception {
        try {

            StringBuilder sql = new StringBuilder();
            if (!idSuministro.isEmpty()) {
                sql.append("UPDATE MpDireccionamientoEntregados ");
                sql.append("SET  idSuministro =:idSum , cierreSuministro = 0 ");
                sql.append("WHERE idTransaccion = :id ");
                Query query = getEntityManager().createQuery(sql.toString());
                query.setParameter("id", idTransaccion);
                query.setParameter("idSum", idSuministro);
                query.executeUpdate();
            } else {
                throw new Exception("idSuministro incorrecto");
            }

        } catch (Exception e) {
            throw new Exception("Error junta: " + e.getMessage() + "CAUSE" + e.getCause().getMessage());
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void actualizarMpDireccionamientoEntregado(MpDireccionamientoEntregado entrega) throws Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE MpDireccionamientoEntregados SET numeroEntrega = :noEntrega");

            if (entrega.getUltimaEntrega() != null) {
                sql.append(", ultimaEntrega = :ultima");
            }
            if (entrega.getEntregaCompleta() != null) {
                sql.append(", entregaCompleta = :completa");
            }
            if (entrega.getCausaNoEntrega() != null) {
                sql.append(", causaNoEntrega = :causaNo");
            }
            if (entrega.getCantidadEntrega() != null) {
                sql.append(", cantidadEntrega = :cantidad");
            }
            if (entrega.getValorTotal() != null) {
                sql.append(", valorTotal = :valorTotal");
            }
            if (entrega.getEstadoSuministro() != null) {
                sql.append(", estadoSuministro = :estado");
            }
            if (entrega.getFechaAnulacion() != null) {
                sql.append(", fechaAnulacion = :dAnulacion");
            }
            if (entrega.getIdSuministro() != null) {
                sql.append(", idSuministro = :idSuministro");
            }
            if (entrega.getFechaSuministro() != null) {
                sql.append(", fechaHoraSuminisro = :dSuministro");
            }

            sql.append(" WHERE id = :id");

            Query query = getEntityManager().createQuery(sql.toString());
            query.setParameter("noEntrega", entrega.getNumeroEntrega());

            if (entrega.getUltimaEntrega() != null) {
                query.setParameter("ultima", entrega.getUltimaEntrega() ? 1 : 0);
            }
            if (entrega.getEntregaCompleta() != null) {
                query.setParameter("completa", entrega.getEntregaCompleta() ? 1 : 0);
            }
            if (entrega.getCausaNoEntrega() != null) {
                query.setParameter("causaNo", entrega.getCausaNoEntrega());
            }
            if (entrega.getCantidadEntrega() != null) {
                query.setParameter("cantidad", entrega.getCantidadEntrega());
            }
            if (entrega.getValorTotal() != null) {
                query.setParameter("valorTotal", entrega.getValorTotal());
            }
            if (entrega.getEstadoSuministro() != null) {
                query.setParameter("estado", entrega.getEstadoSuministro());
            }
            if (entrega.getFechaAnulacion() != null) {
                query.setParameter("dAnulacion", entrega.getFechaAnulacion());
            }
            if (entrega.getIdSuministro() != null) {
                query.setParameter("idSuministro", entrega.getIdSuministro());
            }
            if (entrega.getFechaSuministro() != null) {
                query.setParameter("dSuministro", entrega.getFechaSuministro());
            }

            query.setParameter("id", entrega.getId());
            query.executeUpdate();

        } catch (NoResultException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarEntityManager(); // Asegúrate de cerrar el EntityManager correctamente
        }
    }

    @Override
    public void actualizarMpDireccionamientoEntregadoFacturacion(MpDireccionamientoEntregado entrega) throws Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE MpDireccionamientoEntregados SET ");

            // Agregar campos condicionalmente si no son null
            if (entrega.getUnidadAdminstrativo() != null) {
                sql.append("unidadAdminstrativo = :unidadAdminstrativo ");
            }
            if (entrega.getUnidadHomologado() != null) {
                sql.append(", unidadHomologado = :unidadHomologado ");
            }
            if (entrega.getTipoComparador() != null) {
                sql.append(", tipoComparador = :tipoComparador ");
            }
            if (entrega.getCodigoComparador() != null) {
                sql.append(", codigoComparador = :codigoComparador ");
            }
            if (entrega.getValorMinimaConcentracion() != null) {
                sql.append(", valorMinimaConcentracion = :valorMinimaConcentracion ");
            }
            if (entrega.getValorTotalComparador() != null) {
                sql.append(", valorTotalComparador = :valorTotalComparador ");
            }
            if (entrega.getFechaReporteFactura() != null) {
                sql.append(", fechaReporteFactura = :fechaReporteFactura ");
            }
            if (entrega.getFechaAnulacion() != null) {
                sql.append(", fechaAnulacion = :fechaAnulacion ");
            }

            sql.append(" WHERE id = :id ");

            Query query = getEntityManager().createQuery(sql.toString());

            // Establecer parámetros solo si no son null
            if (entrega.getUnidadAdminstrativo() != null) {
                query.setParameter("unidadAdminstrativo", entrega.getUnidadAdminstrativo());
            }
            if (entrega.getUnidadHomologado() != null) {
                query.setParameter("unidadHomologado", entrega.getUnidadHomologado());
            }
            if (entrega.getTipoComparador() != null) {
                query.setParameter("tipoComparador", entrega.getTipoComparador());
            }
            if (entrega.getCodigoComparador() != null) {
                query.setParameter("codigoComparador", entrega.getCodigoComparador());
            }
            if (entrega.getValorMinimaConcentracion() != null) {
                query.setParameter("valorMinimaConcentracion", entrega.getValorMinimaConcentracion());
            }
            if (entrega.getValorTotalComparador() != null) {
                query.setParameter("valorTotalComparador", entrega.getValorTotalComparador());
            }
            if (entrega.getFechaReporteFactura() != null) {
                query.setParameter("fechaReporteFactura", entrega.getFechaReporteFactura());
            }
            if (entrega.getFechaAnulacion() != null) {
                query.setParameter("fechaAnulacion", entrega.getFechaAnulacion());
            }

            query.setParameter("id", entrega.getId());
            query.executeUpdate();

        } catch (NoResultException e) {
            throw new Exception("No se encontró el registro a actualizar", e);
        } catch (Exception e) {
            throw new Exception("Error actualizando registro: " + e.getMessage(), e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void actualizarMpDireccionamientoEntregadoFactura(MpDireccionamientoEntregado entrega) throws Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE MpDireccionamientoEntregados SET ");

            // Agregar campos condicionalmente si no son null
            if (entrega.getNoSubEntrega() != null) {
                sql.append("noSubEntrega = :noSubEntrega ");
            }
            if (entrega.getCufe() != null) {
                sql.append(", cufe = :cufe ");
            }
            if (entrega.getCodFactura() != null) {
                sql.append(", codFactura = :codFactura ");
            }
            if (entrega.getNoidEPS() != null) {
                sql.append(", noidEPS = :noidEPS ");
            }
            if (entrega.getCodEPS() != null) {
                sql.append(", codEPS = :codEPS ");
            }
            if (entrega.getCodTecEntregado() != null) {
                sql.append(", codTecEntregado = :codTecEntregado ");
            }
            if (entrega.getCantUnMinDis() != null) {
                sql.append(", cantUnMinDis = :cantUnMinDis ");
            }
            if (entrega.getValorTotFacturado() != null) {
                sql.append(", valorTotFacturado = :valorTotFacturado ");
            }
            if (entrega.getValorReportado() != null) {
                sql.append(", valorReportado = :valorReportado ");
            }
            if (entrega.getCuotaModeradora() != null) {
                sql.append(", cuotaModeradora = :cuotaModeradora ");
            }
            if (entrega.getCopago() != null) {
                sql.append(", copago = :copago ");
            }
            if (entrega.getFechaReporteFactura() != null) {
                sql.append(", fechaReporteFactura = :fechaReporteFactura ");
            }
            if (entrega.getFechaAnulacion() != null) {
                sql.append(", fechaAnulacion = :fechaAnulacion ");
            }
            if (entrega.getEstFacturacion() != null) {
                sql.append(", estFacturacion = :estFacturacion ");
            }

            sql.append(" WHERE id = :id");

            Query query = getEntityManager().createQuery(sql.toString());

            // Establecer parámetros solo si no son null
            if (entrega.getNoSubEntrega() != null) {
                query.setParameter("noSubEntrega", entrega.getNoSubEntrega());
            }
            if (entrega.getCufe() != null) {
                query.setParameter("cufe", entrega.getCufe());
            }
            if (entrega.getCodFactura() != null) {
                query.setParameter("codFactura", entrega.getCodFactura());
            }
            if (entrega.getNoidEPS() != null) {
                query.setParameter("noidEPS", entrega.getNoidEPS());
            }
            if (entrega.getCodEPS() != null) {
                query.setParameter("codEPS", entrega.getCodEPS());
            }
            if (entrega.getCodTecEntregado() != null) {
                query.setParameter("codTecEntregado", entrega.getCodTecEntregado());
            }
            if (entrega.getCantUnMinDis() != null) {
                query.setParameter("cantUnMinDis", entrega.getCantUnMinDis());
            }
            if (entrega.getValorTotFacturado() != null) {
                query.setParameter("valorTotFacturado", entrega.getValorTotFacturado());
            }
            if (entrega.getValorReportado() != null) {
                query.setParameter("valorReportado", entrega.getValorReportado());
            }
            if (entrega.getCuotaModeradora() != null) {
                query.setParameter("cuotaModeradora", entrega.getCuotaModeradora());
            }
            if (entrega.getCopago() != null) {
                query.setParameter("copago", entrega.getCopago());
            }
            if (entrega.getFechaReporteFactura() != null) {
                query.setParameter("fechaReporteFactura", entrega.getFechaReporteFactura());
            }
            if (entrega.getFechaAnulacion() != null) {
                query.setParameter("fechaAnulacion", entrega.getFechaAnulacion());
            }
            if (entrega.getEstFacturacion() != null) {
                query.setParameter("estFacturacion", entrega.getEstFacturacion());
            }

            query.setParameter("id", entrega.getId());
            query.executeUpdate();

        } catch (NoResultException e) {
            throw new Exception("No se encontró el registro a actualizar", e);
        } catch (Exception e) {
            throw new Exception("Error actualizando registro: " + e.getMessage(), e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void actualizarEntregaFactura(MpEntregaFactura dato) throws Exception {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE MpEntregaFacturas SET ");

            // Agregar campos condicionalmente si no son null
            if (dato.getIdCicloFacturacion() != null) {
                sql.append("idCicloFacturacion = :idCicloFacturacion ");
            }
            if (dato.getConfirmaTipoComparador() != null) {
                sql.append(", confirmaTipoComparador = :confirmaTipoComparador ");
            }
            if (dato.getConfirmaFecha() != null) {
                sql.append(", confirmaFecha = :confirmaFecha ");
            }
            if (dato.getFechaConsumoDato() != null) {
                sql.append(", fechaConsumoDato = :fechaConsumoDato ");
            }
            sql.append(" WHERE id = :id");

            Query query = getEntityManager().createQuery(sql.toString());

            if (dato.getIdCicloFacturacion() != null) {
                query.setParameter("idCicloFacturacion", dato.getIdCicloFacturacion());
            }
            if (dato.getConfirmaTipoComparador() != null) {
                query.setParameter("confirmaTipoComparador", dato.getConfirmaTipoComparador());
            }
            if (dato.getConfirmaFecha() != null) {
                query.setParameter("confirmaFecha", dato.getConfirmaFecha());
            }
            if (dato.getFechaConsumoDato() != null) {
                query.setParameter("fechaConsumoDato", dato.getFechaConsumoDato());
            }

            query.setParameter("id", dato.getId());
            query.executeUpdate();

        } catch (NoResultException e) {
            throw new Exception("No se encontró el registro a actualizar", e);
        } catch (Exception e) {
            throw new Exception("Error actualizando registro: " + e.getMessage(), e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public MpDireccionamientoEntregado consultarExistente(String idTransaccion, String idReporteEntrega, Integer numeroEntrega) {
        MpDireccionamientoEntregado entrega = null;
        try {
            String strQuery = "SELECT e FROM MpDireccionamientoEntregados e WHERE e.idTransaccion = :idTransaccion ";
            strQuery += " AND e.idReporteEntrega = :idReporteEntrega";
            strQuery += " AND e.numeroEntrega = :numeroEntrega";

            MpDireccionamientoEntregados obj = (MpDireccionamientoEntregados) getEntityManager()
                    .createQuery(strQuery)
                    .setParameter("idTransaccion", Integer.parseInt(idTransaccion))
                    .setParameter("idReporteEntrega", Integer.parseInt(idReporteEntrega))
                    .setParameter("numeroEntrega", numeroEntrega)
                    .getSingleResult();

            entrega = castDireccionamientoEntregado(obj);
        } catch (NoResultException e) {
            // Entidad no encontrada, devuelve null
            entrega = null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarEntityManager();
        }
        return entrega;
    }

    public static MpDireccionamientoEntregado castDireccionamientoEntregado(MpDireccionamientoEntregados per) {
        MpDireccionamientoEntregado obj = new MpDireccionamientoEntregado();
        obj.setId(per.getId());
        obj.setEstRepEntrega(per.getEstRepEntrega());
        obj.setFechaAnulacion(per.getFechaAnulacion());
        obj.setIdTransaccion(per.getIdTransaccion());
        obj.setIdReporteEntrega(per.getIdReporteEntrega());
        return obj;
    }

    @Override
    public MpEntregaSuministro consultarIdExistente(String idSuministro) {
        MpEntregaSuministro entrega = null;
        try {
            String strQuery = "SELECT e FROM MpEntregaSuministros e WHERE e.idSuministro = :idSuministro ";

            MpEntregaSuministros obj = (MpEntregaSuministros) getEntityManager()
                    .createQuery(strQuery)
                    .setParameter("idSuministro", idSuministro)
                    .getSingleResult();

            entrega = castSuministro(obj);
        } catch (NoResultException e) {
            // Entidad no encontrada, devuelve null
            entrega = null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarEntityManager();
        }
        return entrega;
    }

    public static MpEntregaSuministro castSuministro(MpEntregaSuministros per) {
        MpEntregaSuministro obj = new MpEntregaSuministro();
        obj.setId(per.getId());
        obj.setEstadoMipres(per.getEstadoMipres());
        obj.setFechaAnulacion(per.getFechaHoraAnula());
        obj.setFechaSuministro(per.getFechaHoraSuminisro());
        return obj;
    }

    @Override
    public void actualizarExistente(Integer id, Integer estadoReporte, Date fechaAnulacion) throws Exception {
        try {

            String hql = "UPDATE MpDireccionamientoEntregados SET ";
            hql += " estRepEntrega = :estadoReporte ";
            if (fechaAnulacion != null) {
                hql += ", fechaAnulacion = :fechaAnulacion ";
            }
            hql += " WHERE id = :id";

            Query query = getEntityManager().createQuery(hql);
            query.setParameter("estadoReporte", estadoReporte);
            if (fechaAnulacion != null) {
                query.setParameter("fechaAnulacion", fechaAnulacion);
            }
            query.setParameter("id", id);

            query.executeUpdate();
            getEntityManager().flush();
        } catch (Exception e) {
            throw new Exception("Error al actualizar MpDireccionamientoEntregados", e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void actualizarEntregaFacturaExistente(String id, Short estado, String idCicloFacturacion, Date fechaAnulacion) throws Exception {
        try {

            StringBuilder hql = new StringBuilder("UPDATE MpEntregaFacturas SET "
                    + " estado = :estado, "
                    + " estadoMipres = :estado ");

            if (idCicloFacturacion != null) {
                hql.append(", idCicloFacturacion = :idCicloFacturacion ");
            }

            if (fechaAnulacion != null) {
                hql.append(", fechaAnulacion = :fechaAnulacion ");
            } else {
                hql.append(", confirmaFecha = :confirmaFecha ");
            }

            hql.append(" WHERE idFacturacion = :id");

            Query query = getEntityManager().createQuery(hql.toString());
            query.setParameter("estado", estado);
            query.setParameter("id", id);

            if (idCicloFacturacion != null) {
                query.setParameter("idCicloFacturacion", idCicloFacturacion);
            }

            if (fechaAnulacion != null) {
                query.setParameter("fechaAnulacion", fechaAnulacion);
            } else {
                query.setParameter("confirmaFecha", new Date());
            }

            query.executeUpdate();
            getEntityManager().flush();
        } catch (Exception e) {
            throw new Exception("Error al actualizar MpEntregaFactura", e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void actualizarIdEntregaFacturaExistente(Integer id, Integer idEntrega) throws Exception {
        try {

            StringBuilder hql = new StringBuilder("UPDATE MpEntregaFacturas SET "
                    + " mpDireccionamientoEntregadosId.id = :idEntrega ");
            hql.append(" WHERE id = :id");

            Query query = getEntityManager().createQuery(hql.toString());
            query.setParameter("idEntrega", idEntrega);
            query.setParameter("id", id);

            query.executeUpdate();
            getEntityManager().flush();
        } catch (Exception e) {
            throw new Exception("Error al actualizar MpEntregaFactura en mpDireccionamientoEntregadosId", e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void actualizarIdEntregaSuministroExistente(Integer id, Integer idEntrega) throws Exception {
        try {

            StringBuilder hql = new StringBuilder("UPDATE MpEntregaSuministros SET "
                    + " mpDireccionamientoEntregadosId.id = :idEntrega ");
            hql.append(" WHERE id = :id");

            Query query = getEntityManager().createQuery(hql.toString());
            query.setParameter("idEntrega", idEntrega);
            query.setParameter("id", id);

            query.executeUpdate();
            getEntityManager().flush();
        } catch (Exception e) {
            throw new Exception("Error al actualizar MpEntregaFactura en mpDireccionamientoEntregadosId", e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public void actualizarCodigoFacturado(Integer id, String cod) throws Exception {
        try {

            StringBuilder hql = new StringBuilder("UPDATE MpEntregaFacturas SET "
                    + " codigoFacturado = :codigoFacturado ");
            hql.append(" WHERE id = :id");

            Query query = getEntityManager().createQuery(hql.toString());
            query.setParameter("codigoFacturado", cod);
            query.setParameter("id", id);

            query.executeUpdate();
            getEntityManager().flush();
        } catch (Exception e) {
            throw new Exception("Error al actualizar MpEntregaFactura en codigoFacturado", e);
        } finally {
            cerrarEntityManager();
        }
    }

    @Override
    public List<MpEntregaSuministro> consultarSuministrosAEnviar(Integer estado, String regimen) throws Exception {
        List<MpEntregaSuministro> listResult = new ArrayList();
        try {
            String strQuery = "FROM MpEntregaSuministros es ";
            strQuery += " WHERE es.estadoMipres = '" + estado + "'"
                    + " AND es.mpDireccionamientoEntregadosId.mpPrescripcionId.asegAfiliadosId.maeRegimenCodigo = '" + regimen + "'";

            Query query = getEntityManager().createQuery(strQuery);
            List<MpEntregaSuministros> list = query
                    .getResultList();
            for (MpEntregaSuministros per : list) {
                listResult.add(castSumistro(per));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    public static MpEntregaSuministro castSumistro(MpEntregaSuministros per) {
        MpEntregaSuministro obj = new MpEntregaSuministro();
        obj.setId(per.getId());
        if (per.getMpDireccionamientoEntregadosId() != null) {
            MpDireccionamientoEntregado entrega = new MpDireccionamientoEntregado();
            entrega.setId(per.getMpDireccionamientoEntregadosId().getId());
            entrega.setIdTransaccion(per.getMpDireccionamientoEntregadosId().getIdTransaccion());
            entrega.setTipoTecnologia(per.getMpDireccionamientoEntregadosId().getTipoTecnologia());

            if (per.getMpDireccionamientoEntregadosId().getEntregaCompleta() == 1) {
                entrega.setEntregaCompleta(true);
            } else {
                entrega.setEntregaCompleta(false);
            }

            entrega.setCantidadEntrega(per.getMpDireccionamientoEntregadosId().getCantidadEntrega());
            entrega.setCausaNoEntrega(per.getMpDireccionamientoEntregadosId().getCausaNoEntrega());
            entrega.setNumeroLote(per.getMpDireccionamientoEntregadosId().getNumeroLote());
            entrega.setValorTotal(per.getMpDireccionamientoEntregadosId().getValorTotal());
            obj.setMpDireccionamientoEntregadoId(entrega);
        }
        obj.setUltimaEntrega(per.getUltimaEntrega());
        obj.setNumeroPrescripcionAsociada(per.getNumeroPrescripcionAsociada());

        switch (per.getMpDireccionamientoEntregadosId().getTipoTecnologia()) {
            case MpPrescripcion.TIPO_MEDICAMENTO:
                MpPrescripcionMedicamento mpMed = new MpPrescripcionMedicamento();
                mpMed.setId(per.getMpDireccionamientoEntregadosId().getMpPrescripicionMedicamentosId().getId());
                mpMed.setConsecutivoOrden(per.getMpDireccionamientoEntregadosId().getMpPrescripicionMedicamentosId().getConsecutivoOrden());
                obj.getMpDireccionamientoEntregadoId().setMpPrescripcionMedicamentoId(mpMed);
                break;
            case MpPrescripcion.TIPO_TECNOLOGIA:
                MpPrescripcionTecnologia mpTec = new MpPrescripcionTecnologia();
                mpTec.setId(per.getMpDireccionamientoEntregadosId().getMpPrescripicionTecnologiasId().getId());
                mpTec.setConsecutivoOrden(per.getMpDireccionamientoEntregadosId().getMpPrescripicionTecnologiasId().getConsecutivoOrden());
                obj.getMpDireccionamientoEntregadoId().setMpPrescripcionTecnologiaId(mpTec);
                break;
            case MpPrescripcion.TIPO_DISPOSITIVO_MEDICO:
                MpPrescripcionInsumo mpIns = new MpPrescripcionInsumo();
                mpIns.setId(per.getMpDireccionamientoEntregadosId().getMpPrescripicionInsumosId().getId());
                mpIns.setConsecutivoOrden(per.getMpDireccionamientoEntregadosId().getMpPrescripicionInsumosId().getConsecutivoOrden());
                obj.getMpDireccionamientoEntregadoId().setMpPrescripcionInsumoId(mpIns);
                break;
            case MpPrescripcion.TIPO_PRODUCTO_NUTRICIONAL:
                MpPrescripcionMedicamento mpPN = new MpPrescripcionMedicamento();
                mpPN.setId(per.getMpDireccionamientoEntregadosId().getMpPrescripicionMedicamentosId().getId());
                mpPN.setConsecutivoOrden(per.getMpDireccionamientoEntregadosId().getMpPrescripicionMedicamentosId().getConsecutivoOrden());
                obj.getMpDireccionamientoEntregadoId().setMpPrescripcionMedicamentoId(mpPN);
                break;
            case MpPrescripcion.TIPO_SERVICIO_COMPLEMENTARIO:
                MpPrescripcionInsumo mpSerC = new MpPrescripcionInsumo();
                mpSerC.setId(per.getMpDireccionamientoEntregadosId().getMpPrescripicionInsumosId().getId());
                mpSerC.setConsecutivoOrden(per.getMpDireccionamientoEntregadosId().getMpPrescripicionInsumosId().getConsecutivoOrden());
                obj.getMpDireccionamientoEntregadoId().setMpPrescripcionInsumoId(mpSerC);
                break;
            default:
                throw new AssertionError();
        }

        return obj;
    }

    @Override
    public List<MpEntregaSuministro> consultarSuministrosAAnular(Integer estado, String regimen) throws Exception {
        List<MpEntregaSuministro> listResult = new ArrayList();
        try {
            String strQuery = "FROM MpEntregaSuministros es ";
            strQuery += " WHERE es.estadoMipres = '" + estado + "'"
                    + " AND es.mpDireccionamientoEntregadosId.mpPrescripcionId.asegAfiliadosId.maeRegimenCodigo = '" + regimen + "'";

            Query query = getEntityManager().createQuery(strQuery);
            List<MpEntregaSuministros> list = query
                    .getResultList();
            for (MpEntregaSuministros per : list) {
                listResult.add(castSumistroAAnular(per));
            }
        } catch (NoResultException e) {
            listResult = new ArrayList();
        } catch (Exception e) {
            Exception(CONSULTAR_TODOS, e);
        } finally {
            cerrarEntityManager();
        }
        return listResult;
    }

    public static MpEntregaSuministro castSumistroAAnular(MpEntregaSuministros per) {
        MpEntregaSuministro obj = new MpEntregaSuministro();
        obj.setId(per.getId());
        obj.setIdSuministro(per.getIdSuministro());
        if (per.getMpDireccionamientoEntregadosId() != null) {
            obj.setMpDireccionamientoEntregadoId(new MpDireccionamientoEntregado(per.getMpDireccionamientoEntregadosId().getId()));
        }
        return obj;
    }

    @Override
    public void ajustarSuministro(Integer id, String fecha, Integer estado, boolean anulado) throws Exception {
        try {
            StringBuilder hql = new StringBuilder("UPDATE MpEntregaSuministros SET "
                    + " anulado = :anulado, "
                    + " estadoMipres = :estado ");
            if (fecha != null) {
                hql.append(", fechaHoraAnula = :fecha ");
            } else {
                hql.append(", fechaHoraAnula = null ");
            }
            hql.append(" WHERE id = :id");
            Query query = getEntityManager().createQuery(hql.toString());

            query.setParameter("estado", estado);
            query.setParameter("anulado", anulado);
            query.setParameter("id", id);
            if (fecha != null) {
                query.setParameter("fecha", parseFecha(fecha)); // Convertir el String a formato `Date`
            }
            query.executeUpdate();
            getEntityManager().flush();
        } catch (Exception e) {
            throw new Exception("Error al actualizar MpEntregaSuministro", e);
        } finally {
            cerrarEntityManager();
        }
    }

    private Date parseFecha(String fecha) throws Exception {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return formatter.parse(fecha);
        } catch (ParseException e) {
            throw new Exception("Formato de fecha inválido. Debe ser 'yyyy-MM-dd HH:mm'", e);
        }
    }

}
