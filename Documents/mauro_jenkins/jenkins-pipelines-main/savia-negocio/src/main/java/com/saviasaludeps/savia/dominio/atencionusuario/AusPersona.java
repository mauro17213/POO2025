/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.atencionusuario;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AusPersona extends Auditoria {

    public static final int REGIMEN_CONTRIBUTIVO = 0;
    public static final int REGIMEN_SUBSIDIADO = 1;

    public static final int ESTRATO_CERO = 0;
    public static final int ESTRATO_UNO = 1;
    public static final int ESTRATO_DOS = 2;
    public static final int ESTRATO_TRES = 3;
    public static final int ESTRATO_CUATRO = 4;
    public static final int ESTRATO_CINCO = 5;
    public static final int ESTRATO_SEIS = 6;

    private Empresa empresa;
    private Integer id;
    private int aseg_afiliados_id;
    private int gn_ubicaciones_id;
    private int mae_estado_id;
    private String mae_estado_codigo;
    private String mae_estado_valor;
    private int maeTipoParentescoId;
    private String maeTipoParentescoCodigo;
    private String maeTipoParentescoValor;
    private int mae_tipo_documento_id;
    private String mae_tipo_documento_codigo;
    private String mae_tipo_documento_valor;
    private String documento;
    private String nombres;
    private String apellidos;
    private Date fechaNacimiento;
    private int mae_sexo_id;
    private String mae_sexo_codigo;
    private String mae_sexo_valor;
    private String correoElectronico;
    private String direccion;
    private Integer estrato;
    private String contrato;
    private Boolean regimen;
    private Boolean discapacidad;
    private Boolean esAfiliado;
    private Boolean esRegimen;
    private Boolean gestante;
    private Boolean personaAnonima;
    private Ubicacion personaUbicacion;
//    private Ubicacion ubicacion;
    private List<AusPersonaTelefono> listaTelefonos;
//    private List<PersonaDocumento> listaDocumentos;

    private String datosCreacion;
    private String datosModificacion;
    private Integer edad;
    private Maestro tipoDocumento;
    private Boolean desHabilitarCampoPersona;
    private String telefonoFijo;
    private String numeroCelular;
    private Integer direccionOcorreo;
    
    //se utiliza para el iframe del modulo externo
    private String direccionVia;
    private String direccionNro;
    private String direccionOrd1;
    private String direccionOrientacion;
    private String direccionPlaca1;
    private String direccionOrd2;
    private String direccionOrientacion2;     
    private String direccionPlaca2;
    private String direccionDescripcion;
            
    
    public AusPersona() {
    }

    public AusPersona(Integer id) {
        this.id = id;
    }

    public AusPersona(Integer id, int maeTipoDocumento,String tipoDocumentoCodigo, String tipoDocumentoValor, String documento, String nombres, String apellidos, String nombreCompleto) {
        this.id = id;
        this.mae_tipo_documento_codigo = tipoDocumentoCodigo;
        this.mae_tipo_documento_valor = tipoDocumentoValor;
        this.mae_tipo_documento_id = maeTipoDocumento;
        this.documento = documento;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }
    public AusPersona(Integer id, int maeTipoDocumento,String tipoDocumentoCodigo, String tipoDocumentoValor, String documento, String nombres, String apellidos, String correoElectronico, String nombreCompleto) {
        this.id = id;
        this.mae_tipo_documento_codigo = tipoDocumentoCodigo;
        this.mae_tipo_documento_valor = tipoDocumentoValor;
        this.mae_tipo_documento_id = maeTipoDocumento;
        this.documento = documento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correoElectronico = correoElectronico;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAseg_afiliados_id() {
        return aseg_afiliados_id;
    }

    public void setAseg_afiliados_id(int aseg_afiliados_id) {
        this.aseg_afiliados_id = aseg_afiliados_id;
    }

    

    public int getMae_estado_id() {
        return mae_estado_id;
    }

    public void setMae_estado_id(int mae_estado_id) {
        this.mae_estado_id = mae_estado_id;
    }

    public String getMae_estado_codigo() {
        return mae_estado_codigo;
    }

    public void setMae_estado_codigo(String mae_estado_codigo) {
        this.mae_estado_codigo = mae_estado_codigo;
    }

    public String getMae_estado_valor() {
        return mae_estado_valor;
    }

    public void setMae_estado_valor(String mae_estado_valor) {
        this.mae_estado_valor = mae_estado_valor;
    }

    public Ubicacion getPersonaUbicacion() {
        if(personaUbicacion == null){
            personaUbicacion = new Ubicacion();
        }
        return personaUbicacion;
    }

    public void setPersonaUbicacion(Ubicacion personaUbicacion) {
        this.personaUbicacion = personaUbicacion;
    }

    public int getGn_ubicaciones_id() {
        return gn_ubicaciones_id;
    }

    public void setGn_ubicaciones_id(int gn_ubicaciones_id) {
        this.gn_ubicaciones_id = gn_ubicaciones_id;
    }

    public int getMaeTipoParentescoId() {
        return maeTipoParentescoId;
    }

    public void setMaeTipoParentescoId(int maeTipoParentescoId) {
        this.maeTipoParentescoId = maeTipoParentescoId;
    }

    public String getMaeTipoParentescoCodigo() {
        return maeTipoParentescoCodigo;
    }

    public void setMaeTipoParentescoCodigo(String maeTipoParentescoCodigo) {
        this.maeTipoParentescoCodigo = maeTipoParentescoCodigo;
    }

    public String getMaeTipoParentescoValor() {
        return maeTipoParentescoValor;
    }

    public void setMaeTipoParentescoValor(String maeTipoParentescoValor) {
        this.maeTipoParentescoValor = maeTipoParentescoValor;
    }

    public int getMae_tipo_documento_id() {
        return mae_tipo_documento_id;
    }

    public void setMae_tipo_documento_id(int mae_tipo_documento_id) {
        this.mae_tipo_documento_id = mae_tipo_documento_id;
    }

    public String getMae_tipo_documento_codigo() {
        return mae_tipo_documento_codigo;
    }

    public void setMae_tipo_documento_codigo(String mae_tipo_documento_codigo) {
        this.mae_tipo_documento_codigo = mae_tipo_documento_codigo;
    }

    public String getMae_tipo_documento_valor() {
        return mae_tipo_documento_valor;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
    public void setMae_tipo_documento_valor(String mae_tipo_documento_valor) {
        this.mae_tipo_documento_valor = mae_tipo_documento_valor;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public Integer getEdad() {
        if (edad == null || edad == 0) {
            if (getFechaNacimiento() != null) {
                edad = calcularEdad(getFechaNacimiento(), new Date()) / 12;
            } else {
                edad = 18;
            }

        }
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        setEdad(calcularEdad(fechaNacimiento, new Date()));
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getMae_sexo_id() {
        return mae_sexo_id;
    }

    public void setMae_sexo_id(int mae_sexo_id) {
        this.mae_sexo_id = mae_sexo_id;
    }

    public String getMae_sexo_codigo() {
        return mae_sexo_codigo;
    }

    public void setMae_sexo_codigo(String mae_sexo_codigo) {
        this.mae_sexo_codigo = mae_sexo_codigo;
    }

    public String getMae_sexo_valor() {
        return mae_sexo_valor;
    }

    public void setMae_sexo_valor(String mae_sexo_valor) {
        this.mae_sexo_valor = mae_sexo_valor;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getEstrato() {
        return estrato;
    }

    public void setEstrato(Integer estrato) {
        this.estrato = estrato;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public Boolean getRegimen() {
        return regimen;
    }

    public void setRegimen(Boolean regimen) {
        this.regimen = regimen;
    }

    public Boolean isDiscapacidad() {
        return discapacidad;
    }

    public Boolean getDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(Boolean discapacidad) {
        this.discapacidad = discapacidad;
    }

    public Boolean isGestante() {
        return gestante;
    }

    public void setGestante(Boolean gestante) {
        this.gestante = gestante;
    }

    public Boolean getGestante() {
        return gestante;
    }

    public Maestro getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(Maestro tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    
//    public Ubicacion getUbicacion() {
//        return ubicacion;
//    }
//
//    public void setUbicacion(Ubicacion ubicacion) {
//        this.ubicacion = ubicacion;
//    }
     public List<AusPersonaTelefono> getListaTelefonos() {
        if(listaTelefonos == null){
            listaTelefonos = new ArrayList<>();
        }
        return listaTelefonos;
    }

    public void setListaTelefonos(List<AusPersonaTelefono> listaTelefonos) {
        this.listaTelefonos = listaTelefonos;
    }
//
//    public List<AusPersonaDocumento> getListaDocumentos() {
//        return listaDocumentos;
//    }
//
//    public void setListaDocumentos(List<AusPersonaDocumento> listaDocumentos) {
//        this.listaDocumentos = listaDocumentos;
//    }
    public Boolean getEsAfiliado() {
        return esAfiliado;
    }

    public void setEsAfiliado(Boolean esAfiliado) {
        this.esAfiliado = esAfiliado;
    }

    public Boolean getEsRegimen() {
        return esRegimen;
    }

    public void setEsRegimen(Boolean esRegimen) {
        this.esRegimen = esRegimen;
    }

    public static Map<Integer, Integer> getTiposEstratos() {
        Map<Integer, Integer> tiposEstratos = new LinkedHashMap<>();
        tiposEstratos.put(AusPersona.ESTRATO_CERO, AusPersona.ESTRATO_CERO);
        tiposEstratos.put(AusPersona.ESTRATO_UNO, AusPersona.ESTRATO_UNO);
        tiposEstratos.put(AusPersona.ESTRATO_DOS, AusPersona.ESTRATO_DOS);
        tiposEstratos.put(AusPersona.ESTRATO_TRES, AusPersona.ESTRATO_TRES);
        tiposEstratos.put(AusPersona.ESTRATO_CUATRO, AusPersona.ESTRATO_CUATRO);
        tiposEstratos.put(AusPersona.ESTRATO_CINCO, AusPersona.ESTRATO_CINCO);
        tiposEstratos.put(AusPersona.ESTRATO_SEIS, AusPersona.ESTRATO_SEIS);

        return tiposEstratos;
    }

    public Boolean getPersonaAnonima() {
        return personaAnonima;
    }

    public void setPersonaAnonima(Boolean personaAnonima) {
        this.personaAnonima = personaAnonima;
    }

    public Boolean getDesHabilitarCampoPersona() {
        return desHabilitarCampoPersona;
    }

    public void setDesHabilitarCampoPersona(Boolean desHabilitarCampoPersona) {
        this.desHabilitarCampoPersona = desHabilitarCampoPersona;
    }
    
    public boolean exitePersona() {
        return this.getId() != null && this.getId() > 0;
    }

    public String getDatosCreacion() {
        datosCreacion = "El usuario " + this.getUsuarioCrea() + " el dia " + this.getFechaHoraCrea()
                + " desde la terminal " + this.getTerminalCrea() + " realizó la creación del registro.";

        return datosCreacion;
    }

    public void setDatosCreacion(String datosCreacion) {
        this.datosCreacion = datosCreacion;
    }
    
    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public Integer getDireccionOcorreo() {
        return direccionOcorreo;
    }

    public void setDireccionOcorreo(Integer direccionOcorreo) {
        this.direccionOcorreo = direccionOcorreo;
    }

    public String getDireccionVia() {
        return direccionVia;
    }

    public void setDireccionVia(String direccionVia) {
        this.direccionVia = direccionVia;
    }

    public String getDireccionNro() {
        return direccionNro;
    }

    public void setDireccionNro(String direccionNro) {
        this.direccionNro = direccionNro;
    }

    public String getDireccionOrd1() {
        return direccionOrd1;
    }

    public void setDireccionOrd1(String direccionOrd1) {
        this.direccionOrd1 = direccionOrd1;
    }

    public String getDireccionOrientacion() {
        return direccionOrientacion;
    }

    public void setDireccionOrientacion(String direccionOrientacion) {
        this.direccionOrientacion = direccionOrientacion;
    }

    public String getDireccionPlaca1() {
        return direccionPlaca1;
    }

    public void setDireccionPlaca1(String direccionPlaca1) {
        this.direccionPlaca1 = direccionPlaca1;
    }

    public String getDireccionOrd2() {
        return direccionOrd2;
    }

    public void setDireccionOrd2(String direccionOrd2) {
        this.direccionOrd2 = direccionOrd2;
    }

    public String getDireccionOrientacion2() {
        return direccionOrientacion2;
    }

    public void setDireccionOrientacion2(String direccionOrientacion2) {
        this.direccionOrientacion2 = direccionOrientacion2;
    }

    public String getDireccionPlaca2() {
        return direccionPlaca2;
    }

    public void setDireccionPlaca2(String direccionPlaca2) {
        this.direccionPlaca2 = direccionPlaca2;
    }

    public String getDireccionDescripcion() {
        return direccionDescripcion;
    }

    public void setDireccionDescripcion(String direccionDescripcion) {
        this.direccionDescripcion = direccionDescripcion;
    }
    
    public String getDatosModificacion() {
        if (!"".equals(this.getUsuarioModifica()) && this.getUsuarioModifica() != null) {
            datosModificacion = "El usuario " + this.getUsuarioModifica() + " el dia " + this.getFechaHoraModifica()
                    + " desde la terminal " + this.getTerminalModifica() + " realizó la modificación del registro.";
        }
        return datosModificacion;
    }

    public void setDatosModificacion(String datosModificacion) {
        this.datosModificacion = datosModificacion;
    }

    @Override
    public String toString() {
        return "AusPersona{" + "id=" + id + ", mae_estado_id=" + mae_estado_id + ", mae_estado_codigo=" + mae_estado_codigo + ", mae_estado_valor=" + mae_estado_valor + ", maeTipoParentescoId=" + maeTipoParentescoId + ", maeTipoParentescoCodigo=" + maeTipoParentescoCodigo + ", maeTipoParentescoValor=" + maeTipoParentescoValor + ", mae_tipo_documento_id=" + mae_tipo_documento_id + ", mae_tipo_documento_codigo=" + mae_tipo_documento_codigo + ", mae_tipo_documento_valor=" + mae_tipo_documento_valor + ", documento=" + documento + ", nombres=" + nombres + ", apellidos=" + apellidos + ", fechaNacimiento=" + fechaNacimiento + ", mae_sexo_id=" + mae_sexo_id + ", mae_sexo_codigo=" + mae_sexo_codigo + ", mae_sexo_valor=" + mae_sexo_valor + ", correoElectronico=" + correoElectronico + ", direccion=" + direccion + ", edad=" + edad + ", tipoDocumento=" + tipoDocumento + ", telefonoFijo=" + telefonoFijo + ", numeroCelular=" + numeroCelular + '}';
    }

    private Integer calcularEdad(Date fechaNacimiento, Date date) {
        try {
            /*Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(fechaNacimiento);
            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTime(date);
            int startMes = (startCalendar.get(Calendar.YEAR) * 12) + startCalendar.get(Calendar.MONTH);
            int endMes = (endCalendar.get(Calendar.YEAR) * 12) + endCalendar.get(Calendar.MONTH);
            int diffMonth = endMes - startMes;*/
            //return diffMonth;
            SimpleDateFormat formatterFechaEgreso = new SimpleDateFormat("dd/MM/yyyy");
            String formatFechaSolicitud = formatterFechaEgreso.format(fechaNacimiento);
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            Period periodo = Period.between(LocalDate.parse(formatFechaSolicitud, fmt), LocalDate.now());
            return periodo.getYears();
        } catch (Exception e) {
            return 0;
        }
    }
    public Boolean existeTelefonoPersona(List<AusPersonaTelefono> listaTelefonoBD, String telefonoPersona){
        Boolean existe = false;
        for(int i = 0; i < listaTelefonoBD.size(); i++){
            if (listaTelefonoBD.get(i).getNumero().trim().equals(telefonoPersona.trim())){
               existe = true;
               break;
            }
        }
        return existe;
    }
    
    public String getDatosAuditoria() {
        String datos = "";
        if (getNombres() != null) {
            datos += " " + getNombres();
        }
        if (getApellidos() != null) {
            datos += " " + getApellidos();
        }
        if (getMae_tipo_documento_codigo()!= null) {
            datos += " " + getMae_tipo_documento_codigo();
        }
        if (getDocumento()!= null) {
            datos += " " + getDocumento() + " ";
        }
        return datos;
    }
}
