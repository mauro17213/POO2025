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
//public class AseguramientoSingle {
//    
//    public final static int IDENTIFICADOR_DEPARTAMENTO_POR_DEFECTO = 2;
//
//    private List<String> listaTiposMaestrosAseguramiento;
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
//    public void cargarListaTiposAseguramiento() {
//        listaTiposMaestrosAseguramiento = new ArrayList();
//        //adicionamos cada tipo que hace parte de aseguramiento
//        //genericos
////        listaTiposMaestrosAseguramiento.add(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA);
////        listaTiposMaestrosAseguramiento.add(MaestroTipo.GN_SEXO);
////        listaTiposMaestrosAseguramiento.add(MaestroTipo.GN_REGIMEN);
////        listaTiposMaestrosAseguramiento.add(MaestroTipo.GN_TIPO_CONTACTO);
////        listaTiposMaestrosAseguramiento.add(MaestroTipo.GN_ESTADO_CIVIL);
////        listaTiposMaestrosAseguramiento.add(MaestroTipo.GN_ZONA_UBICACION);
////        listaTiposMaestrosAseguramiento.add(MaestroTipo.GN_ETNIA);
////        listaTiposMaestrosAseguramiento.add(MaestroTipo.GN_TIPO_DISCAPACIDAD);
////        listaTiposMaestrosAseguramiento.add(MaestroTipo.GN_COMUNIDAD_ETNIA);
//        //aseguramiento
////        listaTiposMaestrosAseguramiento.add(MaestroTipo.ASEG_ACTIVIDAD_ECONOMICA);
////        listaTiposMaestrosAseguramiento.add(MaestroTipo.ASEG_AFILIADO_TIPO);
////        listaTiposMaestrosAseguramiento.add(MaestroTipo.ASEG_AFP);
////        listaTiposMaestrosAseguramiento.add(MaestroTipo.ASEG_ARL);
////        listaTiposMaestrosAseguramiento.add(MaestroTipo.ASEG_CAJA_COMPENSACION);
////        listaTiposMaestrosAseguramiento.add(MaestroTipo.ASEG_CATEGORIA_IBC);
////        listaTiposMaestrosAseguramiento.add(MaestroTipo.ASEG_CAUSA_NOVEDAD);
////        listaTiposMaestrosAseguramiento.add(MaestroTipo.ASEG_CONDICION_DISCAPACIDAD);
////        listaTiposMaestrosAseguramiento.add(MaestroTipo.ASEG_COTIZANTE_PARENTESCO);
////        listaTiposMaestrosAseguramiento.add(MaestroTipo.ASEG_COTIZANTE_TIPO);
////        listaTiposMaestrosAseguramiento.add(MaestroTipo.ASEG_EPS);
////        listaTiposMaestrosAseguramiento.add(MaestroTipo.ASEG_ESTADO_AFILIACION);
////        listaTiposMaestrosAseguramiento.add(MaestroTipo.ASEG_GENERO_IDENTIFICACION);
////        listaTiposMaestrosAseguramiento.add(MaestroTipo.ASEG_GRUPO_POBLACIONAL);
////        listaTiposMaestrosAseguramiento.add(MaestroTipo.ASEG_METODOLOGIA_GRUPO_POBLACIONAL);
////        listaTiposMaestrosAseguramiento.add(MaestroTipo.ASEG_MODELO_LIQUIDACION);
////        listaTiposMaestrosAseguramiento.add(MaestroTipo.ASEG_ORIGEN_AFILIADO);
////        listaTiposMaestrosAseguramiento.add(MaestroTipo.ASEG_PORTABILIDAD_ESTADO);
////        listaTiposMaestrosAseguramiento.add(MaestroTipo.ASEG_PORTABILIDAD_MOTIVO);
////        listaTiposMaestrosAseguramiento.add(MaestroTipo.ASEG_PORTABILIDAD_ORIGEN);
////        listaTiposMaestrosAseguramiento.add(MaestroTipo.ASEG_PORTABILIDAD_TIPO);
////        listaTiposMaestrosAseguramiento.add(MaestroTipo.ASEG_SISBEN_CATEGORIA);
////        listaTiposMaestrosAseguramiento.add(MaestroTipo.ASEG_SISBEN_NIVEL);
////        listaTiposMaestrosAseguramiento.add(MaestroTipo.ASEG_SISBEN_SUBCATEGORIA);
////        listaTiposMaestrosAseguramiento.add(MaestroTipo.ASEG_SOLIDARIA_PORCENTAJE);
//        //consulta 360
////        listaTiposMaestrosAseguramiento.add(MaestroTipo.CR_A9_ESTADO);
////        listaTiposMaestrosAseguramiento.add(MaestroTipo.AUS_SOLICITUD_ESTADO);
////        listaTiposMaestrosAseguramiento.add(MaestroTipo.PE_PROGRAMA_TIPO);
////        listaTiposMaestrosAseguramiento.add(MaestroTipo.TU_TUTELA_PROCESO);
////        listaTiposMaestrosAseguramiento.add(MaestroTipo.PE_GESTION_TIPO);
//    }
//    
//    public void refrescarMaestrosActivos() {
//        try {
//            cargarListaTiposAseguramiento();
//            this.listaMaestrosActivos = getMaestroRemoto().listarPorTipos(listaTiposMaestrosAseguramiento);
//        } catch (Exception e) {
//            System.out.println("Hubo un error actualizando la lista de maestros");
//        } finally {
//            fechaHoraRefresco = MILISEGUNDOS_REFRESCO + System.currentTimeMillis();
//        }
//    }
//    
//    public void refrescarMaestrosActivosNuevas() {
//        try {
//            cargarListaTiposAseguramiento();
//            List<Maestro> maestrosNuevos = getMaestroRemoto().listarPorTiposNuevos(listaTiposMaestrosAseguramiento,fechaHoraRefresco);//getUbicacionRemoto().consultarActivasNuevas(fechaHoraRefresco);
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
////    public List<Maestro> getListaMaestrosActivos() {
////        if (listaMaestrosActivos == null) {
////            refrescarMaestrosActivos();
////        } else {
////            refrescarMaestrosActivosNuevas();
////        }
////        return listaMaestrosActivos;
////    }
////    
////    public HashMap<Integer, Maestro> getHashMaestrosActivos() throws Exception {
////        if(this.hashMaestrosActivos == null) {
////            this.hashMaestrosActivos = Util.convertToHash(getListaMaestrosActivos());
////        }
////        return this.hashMaestrosActivos;
////    }
////    
////    public HashMap<String, Maestro> getHashValorMaestrosActivos() throws Exception {
////        if(this.hashValorMaestrosActivos == null) {
////            this.hashValorMaestrosActivos = Util.convertToHashValor(getListaMaestrosActivos());
////        }
////        return this.hashValorMaestrosActivos;
////    }
////
////    public List<Maestro> listarPorTipo(String tipo) {
////        return getListaMaestrosActivos().stream().filter(u -> u.getTipo().equals(tipo)).collect(Collectors.toList());
////    }
////    
////    //CAMBIAR
////    public List<Maestro> listarPorTipoYOrdenPorId(String tipo) {
////        return getListaMaestrosActivos().stream().filter(u -> u.getTipo().equals(tipo)).sorted(Comparator.comparingInt(Maestro::getId)).collect(Collectors.toList());
////    }
////    
////    public HashMap<Integer, Maestro> getHashPorTipo(String tipo) throws Exception {
////        return Util.convertToHash(listarPorTipo(tipo));
////    }
////    
////    public HashMap<String, Maestro> getHashValorPorTipo(String tipo) throws Exception {
////        return Util.convertToHashValor(listarPorTipo(tipo));
////    }
////    
////    public Maestro consultarPadre(int idHijo, int idPadre) {
////        try {
////            Maestro maeHijo = getHashMaestrosActivos().get(idHijo);
////            if (maeHijo != null && maeHijo.getMaestroPadres() != null) {
////                for(Maestro aux: maeHijo.getMaestroPadres()) {
////                    if (aux.getId().equals(idPadre)) {
////                        Maestro padre = aux;
////                        return padre;
////                    }
////                }
////            }            
////        } catch (Exception e) {
////        }
////        return null;        
////    }
////    
////    public List<Maestro> consultarHijos(int idPadre) {
////        //PENDIENTE
////        return null;
////    }
//
//}
