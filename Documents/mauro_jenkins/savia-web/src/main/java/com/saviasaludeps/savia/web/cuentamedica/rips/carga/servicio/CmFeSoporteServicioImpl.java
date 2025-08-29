package com.saviasaludeps.savia.web.cuentamedica.rips.carga.servicio;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeRipsCarga;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeSoporte;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.rips.CmFeRipsCargaRemoto;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.negocio.cuentamedica.rips.CmFeSoporteRemoto;
import com.saviasaludeps.savia.web.cuentamedica.rips.carga.bean.CmFeSoporteBean;
import com.saviasaludeps.savia.web.cuentamedica.utilidades.CmUtilidades;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CmFeSoporteServicioImpl extends AccionesBO implements CmFeSoporteServicioIface {
    
    public static final String NIT_SAVIA_SALUD  = "900604350";

    private CmFeSoporteRemoto getCmFeSoporteRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("CmFeSoporteServicio", CmFeSoporteRemoto.class.getName());
        return (CmFeSoporteRemoto) object;
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
        return (MaestroRemoto) object;
    }
  
      private CmFeRipsCargaRemoto getCmFeRipsCargaServicioRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("CmFeRipsCargaServicio", CmFeRipsCargaRemoto.class.getName());
        return (CmFeRipsCargaRemoto) object;
    }
      
    @Override
    public void Accion(CmFeSoporteBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_VER:
                    switch (bean.getDoAccion()) {
                        case CmFeSoporteBean.DO_ACCION_VER_CARGAS_PARA_SOPORTE:
                            verCargaSoportes(bean);
                            break;
                    }
                    break;
                case Url.ACCION_CREAR:
                    crear(bean);
                    break;
                case Url.ACCION_GUARDAR:
                    guardar(bean);
                    break;
                case Url.ACCION_EDITAR:

                    break;
                case Url.ACCION_MODIFICAR:

                    break;
                case Url.ACCION_BORRAR:
                    break;
                case Url.ACCION_ADICIONAL_1:
                    break;
                case Url.ACCION_ADICIONAL_2:
                    break;
                case Url.ACCION_ADICIONAL_3:
                    break;
                case Url.ACCION_ADICIONAL_4:
                    break;
                 case Url.ACCION_ADICIONAL_5:
                    break;
                default:
                    break;
            }
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    @Override
    public void cargaInicial(CmFeSoporteBean bean) {
        try {
            List<Maestro> maestros = getMaestroRemoto().consultarPorTipo(MaestroTipo.RIPS_TIPO_SOPORTE);
            Map<String, Maestro> mapaMaestros = new HashMap<>();
            maestros.forEach(maestro -> {
                mapaMaestros.put(maestro.getValor(), maestro);
            });
            bean.setHashTipoSoporte((HashMap<String, Maestro>) mapaMaestros);     
        } catch (Exception ex) {
            bean.addError(CmUtilidades.obtenerErrorStrFormateado(ex));
        }
    }

  
    private void guardar(CmFeSoporteBean bean) {
        try {
            Map<Integer, Integer> contadorSoportesInsertar = new HashMap<>();
            Map<Integer, Integer> contadorSoportesDB = new HashMap<>();
            boolean esInsercionCompletaSoportes = true;
            for (CmFeSoporte soporte : bean.getListaSoportes()) {
                String mensajeResultado = crearArchivo(soporte);
                if (mensajeResultado.isEmpty()) {
                    soporte.setArchivoExiste(true);
                    getCmFeSoporteRemoto().insertar(soporte);
                    contadorSoportesInsertar.compute(soporte.getCmFeRipsCarga().getId(),
                            (k, v) -> (v == null) ? 1 : v + 1);
                    contadorSoportesDB.put(soporte.getCmFeRipsCarga().getId(), 
                            soporte.getCmFeRipsCarga().getSoportesNumero());
                } else {
                    bean.addError(mensajeResultado);
                    esInsercionCompletaSoportes = false;
                }
            }
            
            if(esInsercionCompletaSoportes && !contadorSoportesInsertar.isEmpty()){

                for (Map.Entry<Integer, Integer> entry : contadorSoportesInsertar.entrySet()) {
                    Integer idCarga = entry.getKey();
                    Integer numeroSoportesInsertar = entry.getValue(); 
                    CmFeRipsCarga carga = new CmFeRipsCarga(idCarga);
                    carga.setSoportesNumero(contadorSoportesDB.get(idCarga) + numeroSoportesInsertar);
                    getCmFeRipsCargaServicioRemoto().actualizarNumeroSoportes(carga);
                }
            }

            if (esInsercionCompletaSoportes) {
                bean.addMensaje("El cargue de soportes se ha realizado satisfactoriamente.");
            }
        } catch (Exception ex) {
            bean.addError(CmUtilidades.obtenerErrorStrFormateado(ex));
        }
    }
    
    private void crear(CmFeSoporteBean bean) {
        try {
            bean.setObjeto(new CmFeSoporte());
            bean.getListaSoportes().clear();
            bean.getListaSoportesAdjunto().clear();
        } catch (Exception ex) {
            bean.addError("Error: " + CmUtilidades.obtenerErrorStrFormateado(ex));
        }
    }
    
    public void verCargaSoportes(CmFeSoporteBean bean) {
        try {
            Map<String, CmFeRipsCarga> cargasEncontradas = getCmFeRipsCargaServicioRemoto().
                    consultarCargasSegunNitFactura(new ArrayList<>(bean.getMapNitFacturaUnicos().keySet()), "");
            
            bean.setListaCargasParaSoporte(cargasEncontradas);
        } catch (Exception ex) {
            bean.addError("Error: " + CmUtilidades.obtenerErrorStrFormateado(ex));
        }
    }

    private String crearArchivo(CmFeSoporte soporte) {
        String respuesta = "";
        try {
            CmUtilidades.copyInputStreamToFile(soporte.getInputStream(), new File(soporte.getArchivoRuta(), soporte.getArchivo()));
        } catch (IOException ex) {
            respuesta = "Error: " + CmUtilidades.obtenerErrorStrFormateado(ex);
        } finally {
            soporte.setInputStream(null);
        }
        return respuesta;
    }
    
    public void listar(CmFeSoporteBean bean) {
        try {
            bean.getParamConsulta().setEmpresaId(null);
            if (!bean.getConexion().getEmpresa().isAdministradora()) {
                bean.getParamConsulta().setEmpresaId(bean.getConexion().getEmpresa().getId());
            }
            bean.getParamConsulta().setCantidadRegistros(getCmFeSoporteRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getCmFeSoporteRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception ex) {
            bean.addError("Error: " + CmUtilidades.obtenerErrorStrFormateado(ex));
        }
    }
}