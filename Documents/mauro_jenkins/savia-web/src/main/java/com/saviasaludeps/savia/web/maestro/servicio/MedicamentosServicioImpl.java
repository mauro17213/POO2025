/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.servicio;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.maestro.MaMedicamento;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoDetalleRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaAgrupadorMedicamentoRemoto;
import com.saviasaludeps.savia.web.maestro.bean.MedicamentosBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.negocio.maestro.MaMedicamentoRemoto;
import com.saviasaludeps.savia.web.maestro.utilidades.MaestroParametro;
import com.saviasaludeps.savia.web.singleton.MaestroSingle;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jyperez
 */
public class MedicamentosServicioImpl extends AccionesBO implements MedicamentosServicioIface {
    
    private MaMedicamentoRemoto getMedicamentoRemoto() throws Exception {
        return (MaMedicamentoRemoto) RemotoEJB.getEJBRemoto(("MaMedicamentoServicio"), MaMedicamentoRemoto.class.getName());
    }
    
    private MaAgrupadorMedicamentoRemoto getAgrupadorMedicamentoRemoto() throws Exception {
        return (MaAgrupadorMedicamentoRemoto) RemotoEJB.getEJBRemoto(("MaAgrupadorMedicamentoServicio"), MaAgrupadorMedicamentoRemoto.class.getName());
    }
    
    private CntContratoDetalleRemoto getContratoDetalleRemoto() throws Exception {
        return (CntContratoDetalleRemoto) RemotoEJB.getEJBRemoto(("CntContratoDetalleServicio"), CntContratoDetalleRemoto.class.getName());
    }

    @Override
    public void Accion(MedicamentosBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_VER:
                    ver(bean);
                    break;
                case Url.ACCION_CREAR:
                    crear(bean);
                    break;
                case Url.ACCION_GUARDAR:
                    guardar(bean);
                    break;
                case Url.ACCION_EDITAR:
                    editar(bean);
                    break;
                case Url.ACCION_MODIFICAR:
                    modificar(bean);
                    break;
                case Url.ACCION_BORRAR:
                    borrar(bean);
                    break;
                case Url.ACCION_ADICIONAL_1:
                    break;
                case Url.ACCION_ADICIONAL_2:
                    break;
                
            }
            cargas(bean);
        }
    }

    private void listar(MedicamentosBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getMedicamentoRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getMedicamentoRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(MedicamentosBean bean) {
        try {
            bean.setObjeto(getMedicamentoRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(MedicamentosBean bean) {
        try {
            bean.setObjeto(new MaMedicamento());

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(MedicamentosBean bean) {
        Maestro maestro;
        try {
            // almacenamos los valores correspondientes al código y valor de los maestros
            if (bean.getObjeto().getMaeConcentracionId() != null) {
                maestro = bean.getHashConcentracion().get(bean.getObjeto().getMaeConcentracionId());
                bean.getObjeto().setMaeConcentracionCodigo(maestro.getValor());
                bean.getObjeto().setMaeConcentracionValor(maestro.getNombre());
            }
            if (bean.getObjeto().getMaeFormaFarmaceuticaId()!= null) {
                maestro = bean.getHashFormaFarmeceutica().get(bean.getObjeto().getMaeFormaFarmaceuticaId());
                bean.getObjeto().setMaeFormaFarmaceuticaCodigo(maestro.getValor());
                bean.getObjeto().setMaeFormaFarmaceuticaValor(maestro.getNombre());
            }
            if (bean.getObjeto().getMaePrincipioActivoId()!= null) {
                maestro = bean.getHashPrincipioActivo().get(bean.getObjeto().getMaePrincipioActivoId());
                bean.getObjeto().setMaePrincipioActivoCodigo(maestro.getValor());
                bean.getObjeto().setMaePrincipioActivoValor(maestro.getNombre());
            }
            if (bean.getObjeto().getMaeCoberturaId()!= null) {
                maestro = bean.getHashTipos().get(bean.getObjeto().getMaeCoberturaId());
                bean.getObjeto().setMaeCoberturaCodigo(maestro.getValor());
                bean.getObjeto().setMaeCoberturaValor(maestro.getNombre());
                //2021-03-12 jyperez INC 713 incluimos el valor del código en el campo de cobertura obligatorio
                try {
                    bean.getObjeto().setCobertura(Integer.valueOf(maestro.getValor()));
                }catch (Exception e) {
                    bean.getObjeto().setCobertura(0);
                }
            }
            if (bean.getObjeto().getMaeTipoPpmId()!= null) {
                maestro = bean.getHashTipoPpm().get(bean.getObjeto().getMaeTipoPpmId());
                bean.getObjeto().setMaeTipoPpmCodigo(maestro.getValor());
                bean.getObjeto().setMaeTipoPpmValor(maestro.getNombre());
            }
            // validaciones
            //2021-07-22 jyperez INC 893 validamos que no exista otro medicamento con el mismo código cum
            MaMedicamento medicamento = getMedicamentoRemoto().consultarPorCodigoCum(bean.getObjeto().getCum());
            if (medicamento != null) {
                bean.addError("El código CUM ingresado se encuentra asignado a otro medicamento.");
            }
            //verificamos si se pasa las validaciones para poder ejecutar la acción
            if (!bean.isError()) {
                bean.auditoriaGuardar(bean.getObjeto());
                //guardamos el registro
                bean.getObjeto().setId(getMedicamentoRemoto().insertar(bean.getObjeto()));
                bean.addMensaje("Se creó un registro de manera exitosa.");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(MedicamentosBean bean) {
        try {
            bean.setObjeto(getMedicamentoRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(MedicamentosBean bean) {
        Maestro maestro;
        List<CntContrato> listaContratos = new ArrayList();
        try {
            // almacenamos los valores correspondientes al código y valor de los maestros
            if (bean.getObjeto().getMaeConcentracionId() != null) {
                maestro = bean.getHashConcentracion().get(bean.getObjeto().getMaeConcentracionId());
                bean.getObjeto().setMaeConcentracionCodigo(maestro.getValor());
                bean.getObjeto().setMaeConcentracionValor(maestro.getNombre());
            }
            if (bean.getObjeto().getMaeFormaFarmaceuticaId()!= null) {
                maestro = bean.getHashFormaFarmeceutica().get(bean.getObjeto().getMaeFormaFarmaceuticaId());
                bean.getObjeto().setMaeFormaFarmaceuticaCodigo(maestro.getValor());
                bean.getObjeto().setMaeFormaFarmaceuticaValor(maestro.getNombre());
            }
            if (bean.getObjeto().getMaePrincipioActivoId()!= null) {
                maestro = bean.getHashPrincipioActivo().get(bean.getObjeto().getMaePrincipioActivoId());
                bean.getObjeto().setMaePrincipioActivoCodigo(maestro.getValor());
                bean.getObjeto().setMaePrincipioActivoValor(maestro.getNombre());
            }
            if (bean.getObjeto().getMaeCoberturaId()!= null) {
                maestro = bean.getHashTipos().get(bean.getObjeto().getMaeCoberturaId());
                bean.getObjeto().setMaeCoberturaCodigo(maestro.getValor());
                bean.getObjeto().setMaeCoberturaValor(maestro.getNombre());
                //2021-03-12 jyperez INC 713 incluimos el valor del código en el campo de cobertura obligatorio
                try {
                    bean.getObjeto().setCobertura(Integer.valueOf(maestro.getValor()));
                }catch (Exception e) {
                    bean.getObjeto().setCobertura(0);
                }
            }
            if (bean.getObjeto().getMaeTipoPpmId()!= null) {
                maestro = bean.getHashTipoPpm().get(bean.getObjeto().getMaeTipoPpmId());
                bean.getObjeto().setMaeTipoPpmCodigo(maestro.getValor());
                bean.getObjeto().setMaeTipoPpmValor(maestro.getNombre());
            }
            //VALIDACIONES
            //2021-04-22 jyperez validamos si hubo cambio de estado en el objeto, para lanzar la validación
            if (bean.isEstadoInicialTecnologia() != bean.getObjeto().isActivo() && !bean.getObjeto().isActivo()) {
                listaContratos = getContratoDetalleRemoto().consultarPorTecnologia(MaestroParametro.TIPO_TECNOLOGIA_MEDICAMENTO,bean.getObjeto().getId());
                if (!listaContratos.isEmpty()) {
                    String mensajeContratos = "";
                    for (CntContrato contrato : listaContratos) {
                        if (mensajeContratos.equals("")) {
                            mensajeContratos = contrato.getContrato();
                        }else {
                            mensajeContratos = mensajeContratos  + ", " + contrato.getContrato();
                        }
                    }
                    bean.addError("La tecnología se encuentra relacionada con los contratos : " + mensajeContratos);
                }
            }
            //2021-07-22 jyperez INC 893 validamos que no exista otro medicamento con el mismo código cum
            MaMedicamento medicamento = getMedicamentoRemoto().consultarPorCodigoCum(bean.getObjeto().getCum());
            if (medicamento != null && !medicamento.getId().equals(bean.getObjeto().getId())) {
                bean.addError("El código CUM ingresado se encuentra asignado a otro medicamento.");
            }
            //2022-08-02 jyperez validamos que si se encuentra la fecha Inactivo con valor, el estado sea activo
            if (bean.getObjeto().getFechaInactivo() != null && bean.getObjeto().isActivo()) {
                bean.addError("No debe ingresarse fecha Inactivo si el medicamento esta activo.");
            }
            //verificamos si se pasa las validaciones para poder ejecutar la acción
            if (!bean.isError()) {
                bean.auditoriaModificar(bean.getObjeto());
                //guardamos el registro
                getMedicamentoRemoto().actualizar(bean.getObjeto());
                bean.addMensaje("Se actualizó un registro de manera exitosa.");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(MedicamentosBean bean) {
        try {
            bean.setObjeto(getMedicamentoRemoto().eliminar(bean.getObjeto().getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void cargas(MedicamentosBean bean) {
        try {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    break;
                case Url.ACCION_VER:
                    break;
                case Url.ACCION_CREAR:
                    break;
                case Url.ACCION_EDITAR:
                    //Estado
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void cargaInicial(MedicamentosBean bean) {
        try {
            bean.setAgrupadores(getAgrupadorMedicamentoRemoto().consultarTodos());
            bean.setAgrupadoresRecursivo(getAgrupadorMedicamentoRemoto().consultarTodosHashMap());
            //carga de maestros
            //2021-03-02 jyperez Se cambia la lista Tipos a cobertura. se carga el maestros 
            bean.setListaTipos(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.GN_COBERTURA));
            bean.setHashTipos(Util.convertToHash(bean.getListaTipos()));
            bean.setListaConcentracion(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.MA_MEDICAMENTO_CONCENTRACION));
            bean.setHashConcentracion(Util.convertToHash(bean.getListaConcentracion()));
            bean.setListaFormaFarmeceutica(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.MA_MEDICAMENTO_FROMA_FARMACEUTICA));
            bean.setHashFormaFarmeceutica(Util.convertToHash(bean.getListaFormaFarmeceutica()));
            bean.setListaPrincipioActivo(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.MA_MEDICAMENTO_PRINCIPO_ACTIVO));
            bean.setHashPrincipioActivo(Util.convertToHash(bean.getListaPrincipioActivo()));
            bean.setListaTipoPpm(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.MA_MEDICAMENTO_TIPO_PPM));
            bean.setHashTipoPpm(Util.convertToHash(bean.getListaTipoPpm()));
            //2021-06-21 jyperez Sprint 1 nuevos maestros campos adicionales
            bean.setListaGrupoTerapeutico(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.MA_MEDICAMENTO_GRUPO_TERAPEUTICO));
            bean.setHashGrupoTerapeutico(Util.convertToHash(bean.getListaGrupoTerapeutico()));
            bean.setListaGrupoAnatomico(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.MA_MEDICAMENTO_GRUPO_ANATOMICO));
            bean.setHashGrupoAnatomico(Util.convertToHash(bean.getListaGrupoAnatomico()));
            bean.setListaATC(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.MA_MEDICAMENTO_ATC));
            bean.setHashATC(Util.convertToHash(bean.getListaATC()));
            //2021-06-28 jyperez maestro estado registro sanitario
            bean.setListaEstadoRegistroSanitario(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.MA_MEDICAMENTO_ESTADO_REGISTRO_SANITARIO));
            bean.setHashEstadoRegistroSanitario(Util.convertToHash(bean.getListaEstadoRegistroSanitario()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

}
