/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.autorizacion.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Impresion;
import com.saviasaludeps.savia.web.autorizacion.servicio.ImpresionAutorizacionServicioIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.DateUtil;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author Stiven Giraldo
 */
@ManagedBean
@ViewScoped
public class ImpresionAutorizacionBean extends Url {

    @Autowired
    private ImpresionAutorizacionServicioIface impresionAutorizacionServicio;
    private AuAnexo4Impresion objeto;
    private List<AuAnexo4Impresion> registros;
    private LazyDataModel<AuAnexo4Impresion> lazyImpresion;

    //Auxiliares
    private List<Maestro> listaTipoDocumentoAfiliado;
    private HashMap<Integer, Maestro> hashTipoDocumentoAfiliado;

    public ImpresionAutorizacionBean() {
        this.objeto = new AuAnexo4Impresion();
        Modulo _mod = super.validarModulo(Modulo.ID_AUTORIZACIONES_IMPRESIONES);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            lazyImpresion = new LazyDataModel<AuAnexo4Impresion>() {

                private List<AuAnexo4Impresion> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<AuAnexo4Impresion> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    lista = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return lista;
                }

                @Override
                public String getRowKey(AuAnexo4Impresion objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public AuAnexo4Impresion getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (AuAnexo4Impresion objeto : lista) {
                        if (id.equals(objeto.getId())) {
                            return objeto;
                        }
                    }
                    return null;
                }
            };
        }
    }

    @PostConstruct
    public void postConstruct() {
        FacesContextUtils
                .getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
                .getAutowireCapableBeanFactory().autowireBean(this);
        Calendar calSemana = Calendar.getInstance();
        calSemana.add(Calendar.DAY_OF_MONTH, -30);
        this.getParamConsulta().setParametroConsulta1(DateUtil.removerTiempo(calSemana.getTime()));
        this.getParamConsulta().setParametroConsulta2(DateUtil.setFinalDia(new Date()));
        getImpresionAutorizacionServicio().cargaInicial(this);
        listar();
    }

    public void editar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_EDITAR);
        getImpresionAutorizacionServicio().Accion(this);
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getImpresionAutorizacionServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void ver(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_VER);
        getImpresionAutorizacionServicio().Accion(this);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getImpresionAutorizacionServicio().Accion(this);
        procesoFinal();
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_VER:
                    PrimeFaces.current().ajax().update("frmVer");
                    PrimeFaces.current().executeScript("PF('dialogoVer').show()");
                    crearLog("Ver", getObjeto().toString());
                    break;
                case Url.ACCION_GUARDAR:
                    break;
                case Url.ACCION_MODIFICAR:
                    break;
                case Url.ACCION_BORRAR:
                    break;
                case Url.ACCION_LISTAR:
                    PrimeFaces.current().ajax().update("frmImpresionAutorizacion");
                    break;
                case Url.ACCION_CREAR:
                    break;
                case Url.ACCION_EDITAR:
                    break;
                case Url.ACCION_ADICIONAL_1:
                    break;
                case Url.ACCION_ADICIONAL_2:
                    break;
            }
        }
        generarMensajes();
    }

    public void postProcessXLS(Object document) {
        /*HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        CellStyle style = wb.createCellStyle();
        style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
        for (Row row : sheet) {
            for (Cell cell : row) {
                cell.setCellValue(cell.getStringCellValue().toUpperCase());
                cell.setCellStyle(style);
            }
        }*/
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow header = sheet.getRow(0);
        HSSFCellStyle cellStyle = wb.createCellStyle();
        for(int i=0; i < header.getPhysicalNumberOfCells();i++) {
            HSSFCell cell = header.getCell(i);
            cell.setCellStyle(cellStyle);
        }
    }

    public String obtenerTipoDocumentoAfiliado(int id) {
        try {
            Maestro tipoDocumento = getHashTipoDocumentoAfiliado().get(id);
            return tipoDocumento.getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public ImpresionAutorizacionServicioIface getImpresionAutorizacionServicio() {
        return impresionAutorizacionServicio;
    }

    public void setImpresionAutorizacionServicio(ImpresionAutorizacionServicioIface impresionAutorizacionServicio) {
        this.impresionAutorizacionServicio = impresionAutorizacionServicio;
    }

    public AuAnexo4Impresion getObjeto() {
        return objeto;
    }

    public void setObjeto(AuAnexo4Impresion objeto) {
        this.objeto = objeto;
    }

    public List<AuAnexo4Impresion> getRegistros() {
        return registros;
    }

    public void setRegistros(List<AuAnexo4Impresion> registros) {
        this.registros = registros;
    }

    public LazyDataModel<AuAnexo4Impresion> getLazyImpresion() {
        return lazyImpresion;
    }

    public void setLazyImpresion(LazyDataModel<AuAnexo4Impresion> lazyImpresion) {
        this.lazyImpresion = lazyImpresion;
    }

    public List<Maestro> getListaTipoDocumentoAfiliado() {
        return listaTipoDocumentoAfiliado;
    }

    public void setListaTipoDocumentoAfiliado(List<Maestro> listaTipoDocumentoAfiliado) {
        this.listaTipoDocumentoAfiliado = listaTipoDocumentoAfiliado;
    }

    public HashMap<Integer, Maestro> getHashTipoDocumentoAfiliado() {
        return hashTipoDocumentoAfiliado;
    }

    public void setHashTipoDocumentoAfiliado(HashMap<Integer, Maestro> hashTipoDocumentoAfiliado) {
        this.hashTipoDocumentoAfiliado = hashTipoDocumentoAfiliado;
    }

}
