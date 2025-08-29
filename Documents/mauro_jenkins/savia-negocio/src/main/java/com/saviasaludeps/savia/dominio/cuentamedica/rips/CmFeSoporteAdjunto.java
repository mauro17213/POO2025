package com.saviasaludeps.savia.dominio.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.InputStream;

public class CmFeSoporteAdjunto extends Auditoria {

    public static final int TIPO_XML  = 0;
    public static final int TIPO_JSON = 1;
    public static final int TIPO_TXT  = 2;
    public static final int TIPO_ZIP  = 3;
    
    private Integer id;
    private int tipo;
    private String extension;
    private String archivoNombre;
    private String archivoRuta;
    private String archivo;
    private Integer idInsertar;
    private String archivoNombreOriginal;
    private boolean archivoExiste;
    private transient InputStream inputStream;
   

    public CmFeSoporteAdjunto() {
    }

    public CmFeSoporteAdjunto( String nombre, String ruta, InputStream inputStream) {
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
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

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
    
    public String getTipoStr() {
        return CmFeSoporteAdjunto.getTipoStr(getTipo());
    }
    

    public static String getTipoStr(int tipo) {
        switch (tipo) {
            case TIPO_XML:
                return "XML";
            case TIPO_TXT:
                return "TXT";
            case TIPO_JSON:
                return "JSON";
            case TIPO_ZIP:
                return "ZIP";    
            default:
                return "";
        }
    }
    
    
    public  static int getTipoSegunExtension(String tipo) {
        switch (tipo) {
            case "XML":
                return TIPO_XML;
            case "TXT":
                return TIPO_TXT;
            case "JSON":
                return TIPO_JSON;
            case "ZIP":
                return TIPO_ZIP;     
            default:
                return 0;
        }
    }


    @Override
    public String toString() {
    return "CmFeRipsCargaAdjunto{" + "id=" + id + ", tipo=" + tipo + ", extension=" + extension + ", archivoNombre=" + archivoNombre + ", archivoRuta=" + archivoRuta + ", archivo=" + archivo + ", idInsertar=" + idInsertar + ", archivoNombreOriginal=" + archivoNombreOriginal + ", archivoExiste=" + archivoExiste + '}';
    }

}
