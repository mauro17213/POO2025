package com.saviasaludeps.savia.dominio.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.InputStream;
import java.text.SimpleDateFormat;

public class CmRipsCargaAnexo extends Auditoria {

    public static final int TIPO_AC = 0;
    public static final int TIPO_AF = 1;
    public static final int TIPO_AM = 2;
    public static final int TIPO_AP = 3;
    public static final int TIPO_AT = 4;
    public static final int TIPO_AU = 5;
    public static final int TIPO_CT = 6;
    public static final int TIPO_US = 7;
    public static final int TIPO_AN = 8;
    public static final int TIPO_AH = 9;
    public static final int TIPO_AD = 10;
    public static final String AC = "AC";
    public static final String AF = "AF";
    public static final String AH = "AH";
    public static final String AM = "AM";
    public static final String AN = "AN";
    public static final String AP = "AP";
    public static final String AT = "AT";
    public static final String AU = "AU";
    public static final String CT = "CT";
    public static final String US = "US";
    public static final String AD = "AD";

    private Integer id;
    private Integer idInsertar;
    private int maeTipoArchivoId;
    private String archivoNombreOriginal;
    private String archivoRuta;
    private String archivoNombre;
    private boolean existe;
    private CmRipsCarga cmRipsCarga;
    private transient InputStream inputStream;
    private String tipoArchivo;

    public CmRipsCargaAnexo() {
    }

    public CmRipsCargaAnexo(int tipo, String nombre, String ruta, InputStream inputStream) {
        this.maeTipoArchivoId = tipo;
        this.archivoNombre = nombre;
        this.archivoRuta = ruta;
        this.inputStream = inputStream;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaeTipoArchivoId() {
        return maeTipoArchivoId;
    }

    public void setMaeTipoArchivoId(int maeTipoArchivoId) {
        this.maeTipoArchivoId = maeTipoArchivoId;
    }

    public String getArchivoNombreOriginal() {
        return archivoNombreOriginal;
    }

    public void setArchivoNombreOriginal(String archivoNombreOriginal) {
        this.archivoNombreOriginal = archivoNombreOriginal;
    }

    public String getArchivoRuta() {
        return archivoRuta;
    }

    public void setArchivoRuta(String archivoRuta) {
        this.archivoRuta = archivoRuta;
    }

    public String getArchivoNombre() {
        return archivoNombre;
    }

    public void setArchivoNombre(String archivoNombre) {
        this.archivoNombre = archivoNombre;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public CmRipsCarga getCmRipsCarga() {
        return cmRipsCarga;
    }

    public void setCmRipsCarga(CmRipsCarga cmRipsCargaId) {
        this.cmRipsCarga = cmRipsCargaId;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Integer getIdInsertar() {
        return idInsertar;
    }

    public void setIdInsertar(Integer idInsertar) {
        this.idInsertar = idInsertar;
    }

    public InputStream getInputStream() {
        return inputStream;
    }
    
    public String getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public String getTipoStr() {
        switch (maeTipoArchivoId) {
            case TIPO_AC:
                return "AC";
            case TIPO_AF:
                return "AF";
            case TIPO_AM:
                return "AM";
            case TIPO_AP:
                return "AP";
            case TIPO_AT:
                return "AT";
            case TIPO_AU:
                return "AU";
            case TIPO_CT:
                return "CT";
            case TIPO_US:
                return "US";
            case TIPO_AH:
                return "AH";
            case TIPO_AN:
                return "AN";
            case TIPO_AD:
                return "AD";
            default:
                return "";
        }
    }

    public String getGenerarArchivo() {
        SimpleDateFormat df = new SimpleDateFormat("YYYYMMddHHmmss");
        int indiceExtension = getArchivoNombre().lastIndexOf(".");
        String ext = getArchivoNombre().substring(indiceExtension, getArchivoNombre().length());
        archivoNombre = "carga_" + getCmRipsCarga().getId() + "_" + getMaeTipoArchivoId() + "_" + getIdInsertar() + "_" + df.format(getFechaHoraCrea()) + ext;
        return archivoNombre;
    }

}
