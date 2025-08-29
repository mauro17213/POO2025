/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.externo.servicio;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.atencionusuario.AusPersona;
import com.saviasaludeps.savia.dominio.atencionusuario.AusPersonaTelefono;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.atencionusuario.AusCasoRemoto;
import com.saviasaludeps.savia.negocio.atencionusuario.AusPersonaRemoto;
import com.saviasaludeps.savia.web.atencionusuario.bean.AusPersonaBean;
import com.saviasaludeps.savia.web.atencionusuario.servicio.AusPersonaServicioImpl;
import com.saviasaludeps.savia.web.externo.bean.AtencionConsultaCasoBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pavacca
 */
public class AtencionConsultaCasoServicioImpl extends AccionesBO implements AtencionConsultaCasoServicioIface{
    
    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }
    
    private AusCasoRemoto getCasoRemoto() throws Exception {
        return (AusCasoRemoto) RemotoEJB.getEJBRemoto("AusCasoServicio", AusCasoRemoto.class.getName());
    }
    
    private AusPersonaRemoto getPersonaRemoto() throws Exception {
        return (AusPersonaRemoto) RemotoEJB.getEJBRemoto("AusPersonaServicio", AusPersonaRemoto.class.getName());
    }
    
    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        return (AfiliadoRemoto) RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
    }
    
    @Override
    public void Accion(AtencionConsultaCasoBean bean) {
        switch (bean.getAccion()) {
            //case SolicitudConsultaCasoBean.ACCION_BUSCAR_SOLICITUDES_CASO_SERVICE:
            case Url.ACCION_LISTAR:
                consultarServicios(bean);
                break;
            default:
                break;
        }
    }
    
    
    
    private void consultarServicios(AtencionConsultaCasoBean bean) {
        try {
            if (bean.getParamConsulta().getFiltros() == null) {
                bean.getParamConsulta().setFiltros(new HashMap<>());            }
            bean.getParamConsulta().setParametroConsulta1(bean.getObjeto().getAsuPersonasId().getMae_tipo_documento_codigo());
            bean.getParamConsulta().setParametroConsulta2(bean.getObjeto().getAsuPersonasId().getDocumento());
            bean.getParamConsulta().setParametroConsulta3(bean.getObjeto().getAsuPersonasId().getFechaNacimiento());
            bean.getParamConsulta().setRegistrosPagina(30);
            bean.getParamConsulta().setCantidadRegistros(getCasoRemoto().consultarCantidadListaExterna(bean.getParamConsulta()));
            bean.setRegistros(getCasoRemoto().consultarListaExterna(bean.getParamConsulta()));
            //bean.setRegistros(getCasoRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    @Override
    public void consultarPersona(AusPersonaBean bean) {
        try {
            //bean.getObjeto().setEmpresa(bean.getConexion().getEmpresa());
            AusPersona ausPersona = getPersonaRemoto().consultarPersona(bean.getObjeto());
            //AsegAfiliado asegAfiliado = getAfiliadoRemoto().consultar(bean.getObjeto());
            if (ausPersona.exitePersona()) {
                List<AusPersonaTelefono> telefonosPersona = getPersonaRemoto().consultarTelefonosPersonas(ausPersona.getDocumento());
                ausPersona.setListaTelefonos(telefonosPersona);
                bean.setObjeto(ausPersona);

                //bean.setAusPersonaTelefono((AusPersonaTelefono) ausPersona.getListaTelefonos());
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    @Override
    public void consultarPersonaAnonima(AusPersonaBean bean) {
        try {
            //bean.getObjeto().setEmpresa(bean.getConexion().getEmpresa());
            AusPersona ausPersona = getPersonaRemoto().consultar(bean.getObjeto().getId());
            //AsegAfiliado asegAfiliado = getAfiliadoRemoto().consultar(bean.getObjeto());
            if (ausPersona.exitePersona()) {
                List<AusPersonaTelefono> telefonosPersona = getPersonaRemoto().consultarTelefonosPersonas(ausPersona.getDocumento());
                ausPersona.setListaTelefonos(telefonosPersona);
                bean.setObjeto(ausPersona);

                //bean.setAusPersonaTelefono((AusPersonaTelefono) ausPersona.getListaTelefonos());
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    
    @Override
    public List<Date> obtenerFechas(Date fecha) {
        List<Date> resultado = new ArrayList<>();
        try {
            resultado = getCasoRemoto().consultarFechasNoHabiles(fecha);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return resultado;
    }
    
    @Override
    public void consultarPersonaAfiliada(AusPersonaBean bean) {
        try {
//          bean.getObjeto().setEmpresa(bean.getConexion().getEmpresa());
            List<AsegAfiliado> ausPersona = getAfiliadoRemoto().consultarListaAfiliados(bean.getObjeto().getMae_tipo_documento_codigo(), bean.getObjeto().getDocumento());// consultarListaAfiliados (bean.getObjeto().getDocumento());
            //AsegAfiliado asegAfiliado = getAfiliadoRemoto().consultar(bean.getObjeto());
            if (ausPersona != null && !ausPersona.isEmpty()) {
                if(ausPersona.size() == 1){
                    castEntidadNegocio(bean, ausPersona.get(0));
                }else{
                    Collections.sort(ausPersona, (o1, o2) -> o1.getFechaHoraCrea().compareTo(o2.getFechaHoraCrea()));
                    castEntidadNegocio(bean, ausPersona.get(ausPersona.size() -1));
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    public void castEntidadNegocio(AusPersonaBean bean, AsegAfiliado afiliadoDto) {
        AusPersona persona = bean.getObjeto();
        persona.setNombres(afiliadoDto.getNombres());
        persona.setApellidos(afiliadoDto.getApellidos());
        persona.setAseg_afiliados_id(afiliadoDto.getId());
        int idMaeTipoDoc = 0;
        String tipoDocumentoCodigo = "";
        String tipoDocumentoValor = "";
        /*for (Map.Entry<Integer, Maestro> entry : bean.getHashTiposDocumento().entrySet()) {
            Maestro maestro = entry.getValue();
            if (afiliadoDto.getMaeTipoDocumentoValor().equalsIgnoreCase(maestro.getValor())) {
                idMaeTipoDoc = maestro.getId();
                break;
            }
        }*/
        for (Map.Entry<Integer, Maestro> entry : bean.getHashTiposDocumento().entrySet()) {
            Maestro maestro = entry.getValue();
            if (afiliadoDto.getMaeTipoDocumento() == maestro.getId()) {
                idMaeTipoDoc = maestro.getId();
                tipoDocumentoCodigo = maestro.getValor();
                tipoDocumentoValor = maestro.getNombre();
                break;
            }
        }
        persona.setMae_tipo_documento_id(idMaeTipoDoc);
        persona.setMae_tipo_documento_codigo(tipoDocumentoCodigo);
        persona.setMae_tipo_documento_valor(tipoDocumentoValor);

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = simpleDateFormat.parse(afiliadoDto.getFechaNacimiento().toString());
        } catch (ParseException ex) {
            Logger.getLogger(AusPersonaServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        persona.setFechaNacimiento(date);
        int idSexo = 0;
        String codigoSexo = "";
        String valorSexo = "";
        if (afiliadoDto.getMaeGeneroValor()!= null && bean.getHashSexo() != null){
            for (Map.Entry<Integer, Maestro> entry : bean.getHashSexo().entrySet()) {
                Maestro maestro = entry.getValue();
                if (afiliadoDto.getMaeGeneroValor().equalsIgnoreCase(maestro.getDescripcion())) {
                    idSexo = maestro.getId();
                    codigoSexo = maestro.getValor();
                    valorSexo = maestro.getNombre();
                    break;
                }
            }
        }
        persona.setMae_sexo_id(idSexo);
        persona.setMae_sexo_codigo(codigoSexo);
        persona.setMae_sexo_valor(valorSexo);
        int idEstadoAfiliacion = 0;
        if(bean.getHashEstadosPersona() != null && afiliadoDto.getMaeEstadoAfiliacionValor() != null){
            for (Map.Entry<Integer, Maestro> entry : bean.getHashEstadosPersona().entrySet()) {
                Maestro maestro = entry.getValue();
                if (afiliadoDto.getMaeEstadoAfiliacionValor().equalsIgnoreCase(maestro.getDescripcion())) {
                    idEstadoAfiliacion = maestro.getId();
                    break;
                }
            }
        }
        //persona.setMae_estado_id(idEstadoAfiliacion);
        persona.setMae_estado_id(afiliadoDto.getMaeEstadoAfiliacion());
        persona.setMae_estado_codigo(afiliadoDto.getMaeEstadoAfiliacionCodigo());
        persona.setMae_estado_valor(afiliadoDto.getMaeEstadoAfiliacionValor());
        persona.setCorreoElectronico(afiliadoDto.getEmail());
        if (afiliadoDto.getResidenciaUbicacion() != null){
            persona.setPersonaUbicacion(new Ubicacion(afiliadoDto.getResidenciaUbicacion().getId()));
        }
        boolean diascapacidad = !afiliadoDto.getDiscapacidadStr().equalsIgnoreCase("NO");
        persona.setDiscapacidad(diascapacidad);
        persona.setDireccion(afiliadoDto.getDireccionResidencia());
        //persona.setContrato(afiliadoDto.getC ContratoInternoAfilado());
        //int regimen = afiliadoDto.getRegimen().equalsIgnoreCase("Subsidiado") ? 1 : 0;
        Boolean regimen = afiliadoDto.getRegimen().equalsIgnoreCase("1") ? true : false;
        //Boolean regimen = afiliadoDto.getRegimen().equalsIgnoreCase("Subsidiado") ? true : false;
        persona.setRegimen(regimen);
        persona.setGestante(false);
        String telefono = afiliadoDto.getTelefonoMovil();
        boolean agregarListaTel = (telefono != null && !telefono.trim().equals(""));
        if (agregarListaTel) {
            if (telefono.length() <= 10){
                try {
                    if(Integer.parseInt(telefono) < 2147483647){
                        List<AusPersonaTelefono> listaTelefonos = new ArrayList<>();
                        AusPersonaTelefono personatTelefono = new AusPersonaTelefono();
                        personatTelefono.setNumero(telefono);
                        listaTelefonos.add(personatTelefono);
                        persona.setListaTelefonos(listaTelefonos);
                        bean.setListaausPersonaTelefono(persona.getListaTelefonos());
                    }
                } catch (Exception e) {
                    bean.addError("El numero de telefono es muy grande");
                }
            }  
        }
        persona.setEsAfiliado(true);
    }
        
    @Override
    public void cargaInicial(AtencionConsultaCasoBean bean) {
        try {
            bean.setListaTiposDocumento(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO));
            bean.setHashTiposDocumento(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO));            
        } catch (Exception ex) {
            bean.addError("Error carga inicial : " + ex.getMessage());
        }
    }
}
