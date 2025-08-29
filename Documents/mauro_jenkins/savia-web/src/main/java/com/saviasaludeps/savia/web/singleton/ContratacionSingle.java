//package com.saviasaludeps.savia.web.singleton;
//
//import com.saviasaludeps.savia.dominio.administracion.Maestro;
//import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
//import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
//import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
//import com.saviasaludeps.savia.web.utilidades.Util;
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.HashMap;
//import java.util.List;
//import java.util.stream.Collectors;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ContratacionSingle {
//
//    private List<String> listaTiposMaestrosContratacion;
//    private List<Maestro> listaMaestrosActivos;
//    private HashMap<Integer, Maestro> hashMaestrosActivos;
//    private HashMap<String, Maestro> hashValorMaestrosActivos;
//
//    private long fechaHoraRefresco = MILISEGUNDOS_REFRESCO + System.currentTimeMillis();
//    
//    private static final int MILISEGUNDOS_REFRESCO = 10000 * 60 * 1000; //Primer digito cantidad de minutos
//        
//    /* GET INTERFACES EJB */
//
//    private MaestroRemoto getMaestroRemoto() throws Exception {
//        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
//    }
//    
//    public void cargarListaTiposMaestro() {
//        listaTiposMaestrosContratacion = new ArrayList();
//        //adicionamos cada tipo que hace parte de aseguramiento
//        //genericos
////        listaTiposMaestrosContratacion.add(MaestroTipo.GN_TIPO_DOCUMENTO_EMPRESA);
////        listaTiposMaestrosContratacion.add(MaestroTipo.GN_AMBITO);
////        listaTiposMaestrosContratacion.add(MaestroTipo.GN_REGIMEN);
////        listaTiposMaestrosContratacion.add(MaestroTipo.GN_TIPO_DOCUMENTO_PROFESIONAL);
//        //maestros
////        listaTiposMaestrosContratacion.add(MaestroTipo.CNT_MODALIDAD);
////        listaTiposMaestrosContratacion.add(MaestroTipo.CNT_ESTADO);
////        listaTiposMaestrosContratacion.add(MaestroTipo.CNT_CLASE_PRESTADOR);
////        listaTiposMaestrosContratacion.add(MaestroTipo.CNT_TIPO_CONTACTO);
////        listaTiposMaestrosContratacion.add(MaestroTipo.CNT_AREA_CONTACTO);
//    }
//    
//    public void refrescarMaestrosActivos() {
//        try {
//            cargarListaTiposMaestro();
//            this.listaMaestrosActivos = getMaestroRemoto().listarPorTipos(listaTiposMaestrosContratacion);
//        } catch (Exception e) {
//            System.out.println("Hubo un error actualizando la lista de maestros");
//        } finally {
//            fechaHoraRefresco = MILISEGUNDOS_REFRESCO + System.currentTimeMillis();
//        }
//    }
//    
//    public void refrescarMaestrosActivosNuevas() {
//        try {
//            cargarListaTiposMaestro();
//            List<Maestro> maestrosNuevos = getMaestroRemoto().listarPorTiposNuevos(listaTiposMaestrosContratacion,fechaHoraRefresco);//getUbicacionRemoto().consultarActivasNuevas(fechaHoraRefresco);
//            if (!maestrosNuevos.isEmpty()) {
//                this.listaMaestrosActivos.addAll(maestrosNuevos);
//                limpiarTodos();
//            }
//        } catch (Exception e) {
//            System.out.println("Hubo un error actualizando la lista de maestros");
//        } finally {
//            fechaHoraRefresco = MILISEGUNDOS_REFRESCO + System.currentTimeMillis();
//        }
//    }
//    
//    public void limpiarTodos() {
//        
//    }
//    
//    //Getters
//    public List<Maestro> getListaMaestrosActivos() {
//        if (listaMaestrosActivos == null) {
//            refrescarMaestrosActivos();
//        } else {
//            refrescarMaestrosActivosNuevas();
//        }
//        return listaMaestrosActivos;
//    }
//    
//    public HashMap<Integer, Maestro> getHashMaestrosActivos() throws Exception {
//        if(this.hashMaestrosActivos == null) {
//            this.hashMaestrosActivos = Util.convertToHash(getListaMaestrosActivos());
//        }
//        return this.hashMaestrosActivos;
//    }
//    
//    public HashMap<String, Maestro> getHashValorMaestrosActivos() throws Exception {
//        if(this.hashValorMaestrosActivos == null) {
//            this.hashValorMaestrosActivos = Util.convertToHashValor(getListaMaestrosActivos());
//        }
//        return this.hashValorMaestrosActivos;
//    }
//
//    public List<Maestro> listarPorTipo(String tipo) {
//        return getListaMaestrosActivos().stream().filter(u -> u.getTipo().equals(tipo)).collect(Collectors.toList());
//    }
//    
//    //CAMBIAR
//    public List<Maestro> listarPorTipoYOrdenPorId(String tipo) {
//        return getListaMaestrosActivos().stream().filter(u -> u.getTipo().equals(tipo)).sorted(Comparator.comparingInt(Maestro::getId)).collect(Collectors.toList());
//    }
//    
//    public HashMap<Integer, Maestro> getHashPorTipo(String tipo) throws Exception {
//        return Util.convertToHash(listarPorTipo(tipo));
//    }
//    
//    public HashMap<String, Maestro> getHashValorPorTipo(String tipo) throws Exception {
//        return Util.convertToHashValor(listarPorTipo(tipo));
//    }
//    
//    public Maestro consultarPadre(int idHijo, int idPadre) {
//        try {
//            Maestro maeHijo = getHashMaestrosActivos().get(idHijo);
//            if (maeHijo != null && maeHijo.getMaestroPadres() != null) {
//                for(Maestro aux: maeHijo.getMaestroPadres()) {
//                    if (aux.getId().equals(idPadre)) {
//                        Maestro padre = aux;
//                        return padre;
//                    }
//                }
//            }            
//        } catch (Exception e) {
//        }
//        return null;        
//    }
//    
//    public List<Maestro> consultarHijos(int idPadre) {
//        //PENDIENTE
//        return null;
//    }
//
//    /**
//     * @return the listaTiposMaestrosContratacion
//     */
//    public List<String> getListaTiposMaestrosContratacion() {
//        return listaTiposMaestrosContratacion;
//    }
//
//    /**
//     * @param listaTiposMaestrosContratacion the listaTiposMaestrosContratacion to set
//     */
//    public void setListaTiposMaestrosContratacion(List<String> listaTiposMaestrosContratacion) {
//        this.listaTiposMaestrosContratacion = listaTiposMaestrosContratacion;
//    }
//
//}
