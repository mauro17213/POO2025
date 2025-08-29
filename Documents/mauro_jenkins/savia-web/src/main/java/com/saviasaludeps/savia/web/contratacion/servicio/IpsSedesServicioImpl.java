/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.contratacion.servicio;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorContacto;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSedeServicioHabilitacion;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorContactoRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorSedeRemoto;
import com.saviasaludeps.savia.web.contratacion.bean.IpsSedesBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
//import com.saviasaludeps.savia.web.utilidades.NotificacionesGP;
import java.util.ArrayList;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorSedeServicioHabilitacionRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorUnionTemporalRemoto;
import com.saviasaludeps.savia.web.singleton.MaestroSingle;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.util.List;

/**
 *
 * @author jyperez
 */
public class IpsSedesServicioImpl extends AccionesBO implements IpsSedesServicioIface {
    
    private CntPrestadorRemoto getPrestadoresRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }
    
    private CntPrestadorSedeRemoto getSedesRemoto() throws Exception {
        return (CntPrestadorSedeRemoto) RemotoEJB.getEJBRemoto("CntPrestadorSedeServicio", CntPrestadorSedeRemoto.class.getName());
    }
    
    private CntPrestadorSedeServicioHabilitacionRemoto getCntPrestadorSedeServicioHabilitacionRemoto() throws Exception {
        return (CntPrestadorSedeServicioHabilitacionRemoto) RemotoEJB.getEJBRemoto("CntPrestadorSedeServicioHabilitacionServicio", CntPrestadorSedeServicioHabilitacionRemoto.class.getName());
    }
    
    private CntPrestadorContactoRemoto getContactoRemoto() throws Exception {
        return (CntPrestadorContactoRemoto) RemotoEJB.getEJBRemoto("CntPrestadorContactoServicio", CntPrestadorContactoRemoto.class.getName());
    }
    
    private CntPrestadorUnionTemporalRemoto getPrestadorUnionTemporalRemoto() throws Exception {
        return (CntPrestadorUnionTemporalRemoto) RemotoEJB.getEJBRemoto("CntPrestadorUnionTemporalServicio", CntPrestadorUnionTemporalRemoto.class.getName());
    }

    @Override
    public void Accion(IpsSedesBean bean) {
        if (super.ValidarSesion(bean)) {
            //bean.getObjeto().setEmpresa(bean.getConexion().getEmpresa());
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
                    switch(bean.getDoAccion()){
                        case Url.ACCION_LISTAR:
                            listarGestionarSedes(bean);
                        break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_2:
                    break;
                case Url.ACCION_ADICIONAL_3:
                    switch(bean.getDoAccion()){
                        case Url.ACCION_LISTAR:
                            listarGestionarServiciosREPS(bean);
                        break;
                        case Url.ACCION_VER:
                            verServicioREPS(bean);
                        break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_4:
                    switch(bean.getDoAccion()){
                        case Url.ACCION_CREAR:
                            crearServicioREPS(bean);
                        break;
                        case Url.ACCION_GUARDAR:
                            guardarServicioREPS(bean);
                        break;
                        case Url.ACCION_EDITAR:
                            editarServicioREPS(bean);
                        break;
                        case Url.ACCION_MODIFICAR:
                            modificarServicioREPS(bean);
                        break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_5:
                        borrarServicioREPS(bean);
                    break;
                case Url.ACCION_ADICIONAL_6:
                        switch(bean.getDoAccion()){
                            case Url.ACCION_LISTAR:
                                listarUnionTemporal(bean);
                            break;
                        }
                    break;
                case Url.ACCION_ADICIONAL_7:
                        switch(bean.getDoAccion()){
                            case Url.ACCION_GUARDAR:
                                guardarUnionTemporal(bean);
                            break;
                        }
                    break;
                case Url.ACCION_ADICIONAL_8:
                        borrarUnionTemporal(bean);
                    break;
                default:
                    break;
            }
            cargas(bean);
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    private void listar(IpsSedesBean bean) {
        try {
            /*Persona objPersona = bean.getConexion().getUsuario().getPersona();
            Area objAreaCargo = bean.getConexion().getAreaCargo();
            if (objPersona == null) {
                bean.addError("No esta definida la persona para el usuario actual");
            }
            if (!bean.isAccionAdicional1()) {
                bean.getParamConsulta().setParametroConsulta1((objAreaCargo == null) ? null : objAreaCargo.getId());
                bean.getParamConsulta().setParametroConsulta2((objPersona == null) ? null : objPersona.getId());
            }*/
            bean.getParamConsulta().setCantidadRegistros(getPrestadoresRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getPrestadoresRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(IpsSedesBean bean) {
        try {
            bean.setObjeto(getPrestadoresRemoto().consultar(bean.getObjeto().getId()));
            bean.setListaSedes(bean.getObjeto().getListaPrestadorSedes());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(IpsSedesBean bean) {
        try {
            bean.setObjeto(new CntPrestador());
            bean.setListaSedes(new ArrayList<>());
            /*bean.setObjeto(new Proyecto());
            bean.setListaAgrupadores(new ArrayList());
            bean.setListaAgrupadores(new ArrayList());
            bean.setListaPersonas(new ArrayList());*/
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(IpsSedesBean bean) {
        try {
            bean.setObjeto(getPrestadoresRemoto().consultar(bean.getObjeto().getId()));
            // se carga la lista de Sedes desde el objeto, debido a que vamos a editarla
            bean.setListaSedes(bean.getObjeto().getListaPrestadorSedes());
            // creamos una lista vacia donde almacenaremos aquellos registros existentes que se borraran
            bean.setListaSedesBorrar(new ArrayList<>());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(IpsSedesBean bean) {
        List <CntPrestadorSede> listaSedesAuditar = new ArrayList<>();
        CntPrestador prestador;
        try {
                // validaciones de negoc
                //2021-04-05 jyperez Validamos que no exista un Prestador con los mismos datos
                prestador = getPrestadoresRemoto().consultarPorCodigoMinSalud(bean.getObjeto().getCodigoMinSalud());
                if (prestador != null) {
                    bean.addError("Existe un prestador con el mismo código MinSalud ingresado.");
                }
                if (!bean.isError()) {
                    // generación de almacenamiento del Prestador
                    bean.auditoriaGuardar(bean.getObjeto());
                    bean.getObjeto().setId(getPrestadoresRemoto().insertar(bean.getObjeto()));
                    if (bean.getListaSedes() != null) {
                        for (CntPrestadorSede sede : bean.getListaSedes()) {
                            //reseteamos el valor de id asignado
                            sede.setId(null);
                            sede.setCntPrestador(bean.getObjeto());
                            sede.setCodigoPrestador(bean.getObjeto().getCodigoMinSalud());
                            bean.auditoriaGuardar(sede);
                            sede.setId(getSedesRemoto().insertar(sede));
                            //2021-03-15 jyperez adicionamos el registro con el id correspondiente
                            listaSedesAuditar.add(sede);
                            //2022-05-31 jyperez realizamos la gestión de contactos a agregar
                            for (CntPrestadorContacto contacto: sede.getListaCntPrestadorContactos()) {
                                contacto.setId(null);
                                contacto.setCntPrestadorSede(sede);
                                contacto.setCntPrestador(bean.getObjeto());
                                bean.auditoriaGuardar(contacto);
                                contacto.setId(getContactoRemoto().insertar(contacto));
                            }
                        }
                    }
                    //2021-03-15 jyperez adicionamos la lista editada al objeto
                    bean.getObjeto().setListaPrestadorSedes(listaSedesAuditar);
                    bean.addMensaje("Se creó el registro de manera exitosa");
                }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(IpsSedesBean bean) {
        List <CntPrestadorSede> listaSedesAuditar = new ArrayList<>();
        CntPrestador prestador;
        try {
            //validaciones de negocio
            //2021-04-05 jyperez Validamos que no exista un Prestador con los mismos datos
                prestador = getPrestadoresRemoto().consultarPorIdYCodigoMinSalud(bean.getObjeto().getId(),bean.getObjeto().getCodigoMinSalud());
                if (prestador != null) {
                    bean.addError("Existe un prestador con el mismo código MinSalud ingresado.");
                }
            if (!bean.isError()) {
                // procedemos a modificar el prestador
                bean.auditoriaModificar(bean.getObjeto());
                getPrestadoresRemoto().actualizar(bean.getObjeto());
                //ahora procedemos a realizar la validación de los registros a Editar y a Crear
                for (CntPrestadorSede sede: bean.getListaSedes()) {
                    if (sede.isNuevoRegistro()) {
                        sede.setId(null);
                        sede.setCntPrestador(bean.getObjeto());
                        sede.setCodigoPrestador(bean.getObjeto().getCodigoMinSalud());
                        bean.auditoriaGuardar(sede);
                        sede.setId(getSedesRemoto().insertar(sede));
                        //2021-03-15 jyperez adicionamos el registro con el id correspondiente
                        listaSedesAuditar.add(sede);
                    }else if (sede.isEditado()) {
                        bean.auditoriaModificar(sede);
                        getSedesRemoto().actualizar(sede);
                        //2021-03-15 jyperez adicionamos el registro modificado
                        listaSedesAuditar.add(sede);
                    } else {
                        listaSedesAuditar.add(sede);
                    }
                    //2022-05-31 jyperez realizamos la gestión de contactos a agregar
                    for (CntPrestadorContacto contacto: sede.getListaCntPrestadorContactos()) {
                        if (contacto.isNuevoRegistro()) {
                            contacto.setId(null);
                            contacto.setCntPrestadorSede(sede);
                            contacto.setCntPrestador(bean.getObjeto());
                            bean.auditoriaGuardar(contacto);
                            contacto.setId(getContactoRemoto().insertar(contacto));
                        }
                    }
                    // procedemos a ejecutar los procesos de los contactos a eliminar
                    for (CntPrestadorContacto contacto: sede.getListaCntPrestadorContactosBorrar()) {
                        getContactoRemoto().eliminar(contacto.getId());
                    }
                }
                //2021-03-15 jyperez adicionamos la lista editada al objeto
                bean.getObjeto().setListaPrestadorSedes(listaSedesAuditar);
                // procedemos por ultimo a ejecutar los procesos de los registros a eliminar
                for (CntPrestadorSede sede: bean.getListaSedesBorrar()) {
                    getSedesRemoto().eliminar(sede.getId());
                }
                bean.addMensaje("Se actualizó un registro de manera exitosa");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(IpsSedesBean bean) {
        try {
            bean.setObjeto(getPrestadoresRemoto().eliminar(bean.getObjeto().getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void cargas(IpsSedesBean bean) {
        try {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    break;
                case Url.ACCION_VER:
                    break;
                case Url.ACCION_CREAR:
                case Url.ACCION_EDITAR:
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {

        }
    }

    @Override
    public void cargaInicial(IpsSedesBean bean) {
        try {
//            bean.setListaTiposDocumento(bean.getContratacionSingle().listarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_EMPRESA));
            bean.setListaTiposDocumento(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_EMPRESA));
            bean.setHashTiposDocumento(Util.convertToHash(bean.getListaTiposDocumento()));
            // TODO cambiar por el maestro correcto
//            bean.setListaClasePrestador(bean.getContratacionSingle().listarPorTipo(MaestroTipo.CNT_CLASE_PRESTADOR));
            bean.setListaClasePrestador(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.CNT_CLASE_PRESTADOR));
            bean.setHashClasePrestador(Util.convertToHash(bean.getListaClasePrestador()));
            //carga maestros contactos
//            bean.setListaTiposContacto(bean.getContratacionSingle().listarPorTipo(MaestroTipo.CNT_TIPO_CONTACTO));
            bean.setListaTiposContacto(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.CNT_TIPO_CONTACTO));
            bean.setHashTiposContacto(Util.convertToHash(bean.getListaTiposContacto()));
//            bean.setListaAreasContacto(bean.getContratacionSingle().listarPorTipo(MaestroTipo.CNT_AREA_CONTACTO));
            bean.setListaAreasContacto(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.CNT_AREA_CONTACTO));
            bean.setHashAreasContacto(Util.convertToHash(bean.getListaAreasContacto()));
            //validar cuales son las ubicaciones que vamos a obtener -- activas, o las de antioquia
            //bean.setListaUbicaciones(getUbicacionRemoto().consultarPorTipo(Ubicacion.TIPO_MUNICIPIO));
            try {
                bean.setListaUbicaciones(UbicacionSingle.getInstance().getListaMunicipios());
            } catch (Exception e) {
                bean.setListaUbicaciones(new ArrayList<>());
            }
        } catch (Exception ex) {
            
        }
    }

    private void listarGestionarSedes(IpsSedesBean bean) {
        // obtenemos la lista de sedes
        try {
            //obtenemos los datos del objeto ContratoSede
            bean.setObjeto(getPrestadoresRemoto().consultar(bean.getObjeto().getId()));
            bean.getParamConsultaSedes().setCantidadRegistros(getSedesRemoto().consultarCantidadListaGestionar(bean.getParamConsultaSedes()));
            bean.setListaGestionSedes(getSedesRemoto().consultarListaGestionar(bean.getParamConsultaSedes()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void listarGestionarServiciosREPS(IpsSedesBean bean) {
        // obtenemos la lista de Servicios REPS de la sede
        try {
            bean.getParamConsultaServiciosREPS().setCantidadRegistros(getCntPrestadorSedeServicioHabilitacionRemoto().consultarCantidadLista(bean.getParamConsultaServiciosREPS()));
            bean.setListaServiciosREPS(getCntPrestadorSedeServicioHabilitacionRemoto().consultarLista(bean.getParamConsultaServiciosREPS()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void verServicioREPS(IpsSedesBean bean) {
        try {
            bean.setObjetoSedeServicioHabilitacion(getCntPrestadorSedeServicioHabilitacionRemoto().consultar(bean.getObjetoSedeServicioHabilitacion().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crearServicioREPS(IpsSedesBean bean) {
        try {
            bean.setObjetoSedeServicioHabilitacion(new CntPrestadorSedeServicioHabilitacion());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardarServicioREPS(IpsSedesBean bean) {
        CntPrestadorSedeServicioHabilitacion servicio;
        try {
            //validaciones maestros
            //validaciones negocio
            //20219-09-08 jyperez validar selección de campos de complejidad, que sólo uno esté en true
            bean.addError(validarCamposComplejidad(bean.getObjetoSedeServicioHabilitacion()));
            //validamos que no exista otro registro con el mismo servicio de habilitación seleccionado
            if (bean.getObjetoSedeServicioHabilitacion().getMaServicioHabilitacion() != null) {
                servicio = getCntPrestadorSedeServicioHabilitacionRemoto().consultarPorSedeYServicioHabilitacion(bean.getObjetoSedeServicioHabilitacion().getCntPrestadorSede().getId(),bean.getObjetoSedeServicioHabilitacion().getMaServicioHabilitacion().getId());
                if (servicio != null){
                    bean.addError("Existe un registro configurado para el Servicio de Habilitación seleccionado.");
                }
            } else {
                bean.addError("Se debe seleccionar un Servicio de Habilitación.");
            }
            if(!bean.isError()) {
                bean.auditoriaGuardar(bean.getObjetoSedeServicioHabilitacion());
                getCntPrestadorSedeServicioHabilitacionRemoto().insertar(bean.getObjetoSedeServicioHabilitacion());
                bean.addMensaje("Se creó el registro de manera exitosa");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editarServicioREPS(IpsSedesBean bean) {
        try {
            bean.setObjetoSedeServicioHabilitacion(getCntPrestadorSedeServicioHabilitacionRemoto().consultar(bean.getObjetoSedeServicioHabilitacion().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificarServicioREPS(IpsSedesBean bean) {
        try {
            //validaciones maestros
            //validaciones negocio
            //20219-09-08 jyperez validar selección de campos de complejidad, que sólo uno esté en true
            bean.addError(validarCamposComplejidad(bean.getObjetoSedeServicioHabilitacion()));
            if(!bean.isError()) {
                bean.auditoriaModificar(bean.getObjetoSedeServicioHabilitacion());
                getCntPrestadorSedeServicioHabilitacionRemoto().actualizar(bean.getObjetoSedeServicioHabilitacion());
                bean.addMensaje("Se actualizó el registro de manera exitosa");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void borrarServicioREPS(IpsSedesBean bean) {
        try {
                bean.setObjetoSedeServicioHabilitacion(getCntPrestadorSedeServicioHabilitacionRemoto().eliminar(bean.getObjetoSedeServicioHabilitacion().getId()));
                bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private String validarCamposComplejidad(CntPrestadorSedeServicioHabilitacion objeto) {
        String mensaje = "";
        //validamos que sólo un campo sea verdadero, los demás deben estar en falso
        if(objeto.isAplicaComplejidadBaja()) {
            if (objeto.isAplicaComplejidadMedia() || objeto.isAplicaComplejidadAlta() || objeto.isAplicaSinComplejidad()) {
                mensaje = "Se debe seleccionar sólo un campo relacionado a Complejidad (Baja, Media, Alta, Sin Complejidad).";
            }
        } else if (objeto.isAplicaComplejidadMedia()) {
            if (objeto.isAplicaComplejidadAlta() || objeto.isAplicaSinComplejidad()) {
                mensaje = "Se debe seleccionar sólo un campo relacionado a Complejidad (Baja, Media, Alta, Sin Complejidad).";
            }
        } else if (objeto.isAplicaComplejidadAlta()) {
            if (objeto.isAplicaSinComplejidad()) {
                mensaje = "Se debe seleccionar sólo un campo relacionado a Complejidad (Baja, Media, Alta, Sin Complejidad).";
            }
        } // no importa si aplica Sin Complejidad esta seleccionado o no. Cumple al menos el máximo
        return mensaje;
    }
    
    private void listarUnionTemporal(IpsSedesBean bean) {
        // obtenemos la lista de Uniones Temporales del Prestador
        try {
            bean.getParamConsultaUnionTemporal().setCantidadRegistros(getPrestadorUnionTemporalRemoto().consultarCantidadLista(bean.getParamConsultaUnionTemporal()));
            bean.setListaUnionTemporal(getPrestadorUnionTemporalRemoto().consultarLista(bean.getParamConsultaUnionTemporal()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void guardarUnionTemporal(IpsSedesBean bean) {
        int cant;
        try {
            //validaciones maestros
            //validaciones negocio
            //validamos que no exista otro registro con el mismo prestador seleccionado
            if (bean.getObjetoUnionTemporal().getCntPrestador() != null) {
                // validamos que no se haya seleccionado el mismo prestador
                if (bean.getObjetoUnionTemporal().getCntPrestadorUnionTemporal() != null && 
                        bean.getObjetoUnionTemporal().getCntPrestadorUnionTemporal().getId().equals(bean.getObjetoUnionTemporal().getCntPrestador().getId()) ) {
                    bean.addError("Un prestador no puede tener una unión temporal con el mismo.");
                } else if (bean.getObjetoUnionTemporal().getCntPrestador().getUnionTemporal()) {
                    bean.addError("El prestador seleccionado es una unión temporal. No es posible agregarlo.");
                } else {
                    cant = getPrestadorUnionTemporalRemoto().consultarPorPrestadorUnionTemporalIdyPrestadorId(bean.getObjetoUnionTemporal().getCntPrestadorUnionTemporal().getId(),bean.getObjetoUnionTemporal().getCntPrestador().getId());
                    if (cant > 0){
                        bean.addError("Existe un registro configurado para el Prestador seleccionado.");
                    }
                }
            } else {
                bean.addError("Se debe seleccionar un Prestador.");
            }
            if(!bean.isError()) {
                bean.auditoriaGuardar(bean.getObjetoUnionTemporal());
                getPrestadorUnionTemporalRemoto().insertar(bean.getObjetoUnionTemporal());
                bean.addMensaje("Se creó el registro de manera exitosa");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void borrarUnionTemporal(IpsSedesBean bean) {
        try {
                bean.auditoriaModificar(bean.getObjetoUnionTemporal());
                //actualizamos los campos del objeto a eliminar lógicamente
                bean.getObjetoUnionTemporal().setUsuarioBorra(bean.getObjetoUnionTemporal().getUsuarioModifica());
                bean.getObjetoUnionTemporal().setTerminalBorra(bean.getObjetoUnionTemporal().getTerminalModifica());
                bean.getObjetoUnionTemporal().setFechaHoraBorra(bean.getObjetoUnionTemporal().getFechaHoraModifica());
                bean.getObjetoUnionTemporal().setBorrado(true);
                getPrestadorUnionTemporalRemoto().actualizar(bean.getObjetoUnionTemporal());
                bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
}
