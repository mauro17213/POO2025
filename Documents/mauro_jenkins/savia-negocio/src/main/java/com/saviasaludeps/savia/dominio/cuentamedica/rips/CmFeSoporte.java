package com.saviasaludeps.savia.dominio.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.InputStream;
import java.text.SimpleDateFormat;

public class CmFeSoporte extends Auditoria {


    
    private Integer id;
    private Integer maeTipoSoporteId;
    private String maeTipoSoporteCodigo;
    private String maeTipoSoporteValor;
    private String archivoNombre;
    private String archivoRuta;
    private String archivo;
    private String nitFactura;
    private boolean archivoExiste;
    private CmFeRipsCarga cmFeRipsCarga = new CmFeRipsCarga();
    private CmFactura cmFactura = new CmFactura();
    private transient InputStream inputStream;
    private Empresa empresa = new Empresa();
    private boolean multiUsuario;
    
    public CmFeSoporte() {

    }

    public CmFeSoporte(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaeTipoSoporteId() {
        return maeTipoSoporteId;
    }

    public void setMaeTipoSoporteId(Integer maeTipoSoporteId) {
        this.maeTipoSoporteId = maeTipoSoporteId;
    }

    public String getMaeTipoSoporteCodigo() {
        return maeTipoSoporteCodigo;
    }

    public void setMaeTipoSoporteCodigo(String maeTipoSoporteCodigo) {
        this.maeTipoSoporteCodigo = maeTipoSoporteCodigo;
    }

    public String getMaeTipoSoporteValor() {
        return maeTipoSoporteValor;
    }

    public void setMaeTipoSoporteValor(String maeTipoSoporteValor) {
        this.maeTipoSoporteValor = maeTipoSoporteValor;
    }

    public String getArchivoNombre() {
        return archivoNombre;
    }

    public void setArchivoNombre(String archivoNombre) {
        this.archivoNombre = archivoNombre;
    }

    public String getArchivoRuta() {
        return archivoRuta;
    }

    public void setArchivoRuta(String archivoRuta) {
        this.archivoRuta = archivoRuta;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public boolean isArchivoExiste() {
        return archivoExiste;
    }
    
    public boolean getArchivoExiste() {
        return archivoExiste;
    }

    public void setArchivoExiste(boolean archivoExiste) {
        this.archivoExiste = archivoExiste;
    }

    public CmFeRipsCarga getCmFeRipsCarga() {
        return cmFeRipsCarga;
    }

    public void setCmFeRipsCarga(CmFeRipsCarga cmFeRipsCarga) {
        this.cmFeRipsCarga = cmFeRipsCarga;
    }

    public CmFactura getCmFactura() {
        return cmFactura;
    }

    public void setCmFactura(CmFactura cmFactura) {
        this.cmFactura = cmFactura;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getNitFactura() {
        return nitFactura;
    }

    public void setNitFactura(String nitFactura) {
        this.nitFactura = nitFactura;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public boolean isMultiUsuario() {
        return multiUsuario;
    }

    public void setMultiUsuario(boolean multiUsuario) {
        this.multiUsuario = multiUsuario;
    }
        
    public void generarNombreArchivo(String nombre) {
        SimpleDateFormat df = new SimpleDateFormat("YYYYMMddHHmmss");
        int indiceExtension = getArchivoNombre().lastIndexOf(".");
        String ext = getArchivoNombre().substring(indiceExtension, getArchivoNombre().length());
        nombre = nombre.substring(0 , indiceExtension);
        archivo =  nombre + "_" + df.format(getFechaHoraCrea()) + ext;
    }
   
    @Override
    public String toString() {
        return "CmFeSoporte{" + "id=" + id + ", maeTipoSoporteId=" + maeTipoSoporteId + ", maeTipoSoporteCodigo=" + maeTipoSoporteCodigo + ", maeTipoSoportedValor=" + maeTipoSoporteValor + ", archivoNombre=" + archivoNombre + ", archivoRuta=" + archivoRuta + ", archivo=" + archivo + ", archivoExiste=" + archivoExiste + '}';
    }
    
}
