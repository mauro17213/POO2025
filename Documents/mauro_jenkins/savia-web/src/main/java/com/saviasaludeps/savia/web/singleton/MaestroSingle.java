package com.saviasaludeps.savia.web.singleton;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.administracion.MaestroTipoRemoto;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class MaestroSingle {

    private List<String> listaTiposMaestrosMaestro;
    private List<Maestro> listaMaestrosActivos;
    private HashMap<Integer, Maestro> hashMaestrosActivos;
    private HashMap<String, Maestro> hashValorMaestrosActivos;
    private List<MaestroTipo> listaMaestroTipo;

    private static MaestroSingle maestroInstance = null;

    /* GET INTERFACES EJB */
    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }
    /* GET INTERFACES EJB */
    private MaestroTipoRemoto getMaestroTipoRemoto() throws Exception {
        return (MaestroTipoRemoto) RemotoEJB.getEJBRemoto("MaestroTipoServicio", MaestroTipoRemoto.class.getName());
    }

    private MaestroSingle() {
    }

    public static MaestroSingle getInstance() {
        if (maestroInstance == null) {
            maestroInstance = new MaestroSingle();
        }
        return maestroInstance;
    }

    public void actualizar() {
        try {
            listaMaestrosActivos = new ArrayList<>();
            agregarGenericos();
            agregarMaestrosMA();
            agregarMaestroAseguramiento();
            agregarMaestroCentroRegulador();
            agregarMaestroProgramaEspecial();
            agregarMaestroTutela();
            agregarMaestroAtencionUsuario();
            agregarMaestroContratacion();
            agregarMaestroContratacionJuridica();
            agregarMaestroTipoActivo();
        } catch (Exception e) {
            limpiar();
        }
    }

    public void agregarGenericos() {
        try {
            listaTiposMaestrosMaestro = new ArrayList();
            listaTiposMaestrosMaestro.add(MaestroTipo.GN_COBERTURA);
            listaTiposMaestrosMaestro.add(MaestroTipo.GN_AMBITO);
            listaTiposMaestrosMaestro.add(MaestroTipo.GN_TIPO_DOCUMENTO_EMPRESA);
            listaTiposMaestrosMaestro.add(MaestroTipo.GN_TIPO_DOCUMENTO);
            listaTiposMaestrosMaestro.add(MaestroTipo.GN_TIPO_DOCUMENTO_PROFESIONAL);
            listaMaestrosActivos.addAll(getMaestroRemoto().listarPorTipos(listaTiposMaestrosMaestro));
            listaTiposMaestrosMaestro = new ArrayList();
            listaTiposMaestrosMaestro.add(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA);
            listaTiposMaestrosMaestro.add(MaestroTipo.GN_SEXO);
            listaTiposMaestrosMaestro.add(MaestroTipo.GN_REGIMEN);
            listaTiposMaestrosMaestro.add(MaestroTipo.GN_TIPO_CONTACTO);
            listaTiposMaestrosMaestro.add(MaestroTipo.GN_CARGO);
            listaMaestrosActivos.addAll(getMaestroRemoto().listarPorTipos(listaTiposMaestrosMaestro));
            listaTiposMaestrosMaestro = new ArrayList();
            listaTiposMaestrosMaestro.add(MaestroTipo.GN_ESTADO_CIVIL);
            listaTiposMaestrosMaestro.add(MaestroTipo.GN_ZONA_UBICACION);
            listaTiposMaestrosMaestro.add(MaestroTipo.GN_ETNIA);
            listaTiposMaestrosMaestro.add(MaestroTipo.GN_TIPO_DISCAPACIDAD);
            listaTiposMaestrosMaestro.add(MaestroTipo.GN_COMUNIDAD_ETNIA);
            listaMaestrosActivos.addAll(getMaestroRemoto().listarPorTipos(listaTiposMaestrosMaestro));
        } catch (Exception e) {
            System.out.println("Fallo al cargar genericos en el singleton de maestros");
        }
    }

    public void agregarMaestrosMA() {
        try {
            listaMaestrosActivos.addAll(getMaestroRemoto().consultarPorTipo(MaestroTipo.MA_DIAGNOSTICO_CAPITULO));
            listaMaestrosActivos.addAll(getMaestroRemoto().consultarPorTipo(MaestroTipo.MA_DIAGNOSTICO_CATEGORIA));
            listaTiposMaestrosMaestro = new ArrayList();
            listaTiposMaestrosMaestro.add(MaestroTipo.MA_TECNOLOGIA_GRUPO);
            listaTiposMaestrosMaestro.add(MaestroTipo.MA_INSUMO_TIPO);
            listaMaestrosActivos.addAll(getMaestroRemoto().listarPorTipos(listaTiposMaestrosMaestro));
            listaMaestrosActivos.addAll(getMaestroRemoto().consultarPorTipo(MaestroTipo.MA_MEDICAMENTO_CONCENTRACION));
            listaMaestrosActivos.addAll(getMaestroRemoto().consultarPorTipo(MaestroTipo.MA_MEDICAMENTO_PRINCIPO_ACTIVO));
            listaTiposMaestrosMaestro = new ArrayList();
            listaTiposMaestrosMaestro.add(MaestroTipo.MA_MEDICAMENTO_FROMA_FARMACEUTICA);
            listaTiposMaestrosMaestro.add(MaestroTipo.MA_MEDICAMENTO_TIPO_PPM);
            listaMaestrosActivos.addAll(getMaestroRemoto().listarPorTipos(listaTiposMaestrosMaestro));
            listaTiposMaestrosMaestro = new ArrayList();
            listaTiposMaestrosMaestro.add(MaestroTipo.MA_MEDICAMENTO_GRUPO_TERAPEUTICO);
            listaTiposMaestrosMaestro.add(MaestroTipo.MA_MEDICAMENTO_GRUPO_ANATOMICO);
            listaMaestrosActivos.addAll(getMaestroRemoto().listarPorTipos(listaTiposMaestrosMaestro));
            listaMaestrosActivos.addAll(getMaestroRemoto().consultarPorTipo(MaestroTipo.MA_MEDICAMENTO_ATC));
            listaTiposMaestrosMaestro = new ArrayList();            
            listaTiposMaestrosMaestro.add(MaestroTipo.MA_MEDICAMENTO_ESTADO_REGISTRO_SANITARIO);
            listaTiposMaestrosMaestro.add(MaestroTipo.MA_GRUPO_SERVICIOS_HABILITACION);
            listaMaestrosActivos.addAll(getMaestroRemoto().listarPorTipos(listaTiposMaestrosMaestro));
        } catch (Exception e) {
            System.out.println("Fallo al cargar maestro maestros en el singleton de maestros");
        }
    }

    public void agregarMaestroAseguramiento() {
        try {
            listaTiposMaestrosMaestro = new ArrayList();
            listaTiposMaestrosMaestro.add(MaestroTipo.ASEG_ACTIVIDAD_ECONOMICA);
            listaTiposMaestrosMaestro.add(MaestroTipo.ASEG_AFILIADO_TIPO);
            listaMaestrosActivos.addAll(getMaestroRemoto().listarPorTipos(listaTiposMaestrosMaestro));
            listaTiposMaestrosMaestro = new ArrayList();
            listaTiposMaestrosMaestro.add(MaestroTipo.ASEG_AFP);
            listaTiposMaestrosMaestro.add(MaestroTipo.ASEG_ARL);
            listaMaestrosActivos.addAll(getMaestroRemoto().listarPorTipos(listaTiposMaestrosMaestro));
            listaTiposMaestrosMaestro = new ArrayList();
            listaTiposMaestrosMaestro.add(MaestroTipo.ASEG_CAJA_COMPENSACION);
            listaTiposMaestrosMaestro.add(MaestroTipo.ASEG_CATEGORIA_IBC);
            listaMaestrosActivos.addAll(getMaestroRemoto().listarPorTipos(listaTiposMaestrosMaestro));
            listaTiposMaestrosMaestro = new ArrayList();
            listaTiposMaestrosMaestro.add(MaestroTipo.ASEG_CAUSA_NOVEDAD);
            listaTiposMaestrosMaestro.add(MaestroTipo.ASEG_CONDICION_DISCAPACIDAD);
            listaMaestrosActivos.addAll(getMaestroRemoto().listarPorTipos(listaTiposMaestrosMaestro));
            listaTiposMaestrosMaestro = new ArrayList();
            listaTiposMaestrosMaestro.add(MaestroTipo.ASEG_COTIZANTE_PARENTESCO);
            listaTiposMaestrosMaestro.add(MaestroTipo.ASEG_COTIZANTE_TIPO);
            listaMaestrosActivos.addAll(getMaestroRemoto().listarPorTipos(listaTiposMaestrosMaestro));
            listaTiposMaestrosMaestro = new ArrayList();
            listaTiposMaestrosMaestro.add(MaestroTipo.ASEG_EPS);
            listaTiposMaestrosMaestro.add(MaestroTipo.ASEG_ESTADO_AFILIACION);
            listaMaestrosActivos.addAll(getMaestroRemoto().listarPorTipos(listaTiposMaestrosMaestro));
            listaTiposMaestrosMaestro = new ArrayList();
            listaTiposMaestrosMaestro.add(MaestroTipo.ASEG_GENERO_IDENTIFICACION);
            listaTiposMaestrosMaestro.add(MaestroTipo.ASEG_GRUPO_POBLACIONAL);
            listaMaestrosActivos.addAll(getMaestroRemoto().listarPorTipos(listaTiposMaestrosMaestro));
            listaTiposMaestrosMaestro = new ArrayList();
            listaTiposMaestrosMaestro.add(MaestroTipo.ASEG_METODOLOGIA_GRUPO_POBLACIONAL);
            listaTiposMaestrosMaestro.add(MaestroTipo.ASEG_MODELO_LIQUIDACION);
            listaMaestrosActivos.addAll(getMaestroRemoto().listarPorTipos(listaTiposMaestrosMaestro));
            listaTiposMaestrosMaestro = new ArrayList();
            listaTiposMaestrosMaestro.add(MaestroTipo.ASEG_ORIGEN_AFILIADO);
            listaTiposMaestrosMaestro.add(MaestroTipo.ASEG_PORTABILIDAD_ESTADO);
            listaMaestrosActivos.addAll(getMaestroRemoto().listarPorTipos(listaTiposMaestrosMaestro));
            listaTiposMaestrosMaestro = new ArrayList();
            listaTiposMaestrosMaestro.add(MaestroTipo.ASEG_PORTABILIDAD_MOTIVO);
            listaTiposMaestrosMaestro.add(MaestroTipo.ASEG_PORTABILIDAD_ORIGEN);
            listaMaestrosActivos.addAll(getMaestroRemoto().listarPorTipos(listaTiposMaestrosMaestro));
            listaTiposMaestrosMaestro = new ArrayList();
            listaTiposMaestrosMaestro.add(MaestroTipo.ASEG_PORTABILIDAD_TIPO);
            listaTiposMaestrosMaestro.add(MaestroTipo.ASEG_SISBEN_CATEGORIA);
            listaMaestrosActivos.addAll(getMaestroRemoto().listarPorTipos(listaTiposMaestrosMaestro));
            listaTiposMaestrosMaestro = new ArrayList();
            listaTiposMaestrosMaestro.add(MaestroTipo.ASEG_SISBEN_NIVEL);
            listaTiposMaestrosMaestro.add(MaestroTipo.ASEG_SISBEN_SUBCATEGORIA);
            listaMaestrosActivos.addAll(getMaestroRemoto().listarPorTipos(listaTiposMaestrosMaestro));
            listaTiposMaestrosMaestro = new ArrayList();
            listaTiposMaestrosMaestro.add(MaestroTipo.ASEG_SOLIDARIA_PORCENTAJE);
            listaMaestrosActivos.addAll(getMaestroRemoto().listarPorTipos(listaTiposMaestrosMaestro));
        } catch (Exception e) {
            System.out.println("Fallo al cargar maestros aseguramiento en el singleton maestro");
        }
    }

    public void agregarMaestroCentroRegulador() {
        try {
            listaMaestrosActivos.addAll(getMaestroRemoto().consultarPorTipo(MaestroTipo.CR_A9_ESTADO));
        } catch (Exception e) {
            System.out.println("Fallo al cargar maestros centro regulador en el singleton maestro");
        }
    }

    public void agregarMaestroTutela() {
        try {
            listaMaestrosActivos.addAll(getMaestroRemoto().consultarPorTipo(MaestroTipo.TU_TUTELA_PROCESO));
            listaMaestrosActivos.addAll(getMaestroRemoto().consultarPorTipo(MaestroTipo.TU_CLASE_SANCION));
            listaMaestrosActivos.addAll(getMaestroRemoto().consultarPorTipo(MaestroTipo.TU_CLASE_ARRESTO));
            listaMaestrosActivos.addAll(getMaestroRemoto().consultarPorTipo(MaestroTipo.TU_TIPO_FALLO));
            listaMaestrosActivos.addAll(getMaestroRemoto().consultarPorTipo(MaestroTipo.TU_TUTELA_SMLV));
            listaMaestrosActivos.addAll(getMaestroRemoto().consultarPorTipo(MaestroTipo.TU_SERVICIO_TIPO_PRESTACION));
            listaMaestrosActivos.addAll(getMaestroRemoto().consultarPorTipo(MaestroTipo.TU_SERVICIO_PRESENTACION));
            listaMaestrosActivos.addAll(getMaestroRemoto().consultarPorTipo(MaestroTipo.TU_PRESTACION_CAUSA));
            listaMaestrosActivos.addAll(getMaestroRemoto().consultarPorTipo(MaestroTipo.TU_TUTELA_ESTADO));
            listaMaestrosActivos.addAll(getMaestroRemoto().consultarPorTipo(MaestroTipo.TU_PRESTACION_TIPO_SERVICIO));
            
        } catch (Exception e) {
            System.out.println("Fallo al cargar maestros tutela en el singleton maestro");
        }
    }

    public void agregarMaestroProgramaEspecial() {
        try {
            listaTiposMaestrosMaestro = new ArrayList();
            listaTiposMaestrosMaestro.add(MaestroTipo.PE_PROGRAMA_TIPO);
            listaTiposMaestrosMaestro.add(MaestroTipo.PE_GESTION_TIPO);
            listaMaestrosActivos.addAll(getMaestroRemoto().listarPorTipos(listaTiposMaestrosMaestro));
        } catch (Exception e) {
            System.out.println("Fallo al cargar maestros programa especial en el singleton maestro");
        }
    }

    public void agregarMaestroAtencionUsuario() {
        try {
            //casos
            listaMaestrosActivos.addAll(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SERVICIO_ESTADO));
            listaMaestrosActivos.addAll(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SEGUIMIENTO_TIPO));
            listaMaestrosActivos.addAll(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SOLICITUD_TIPO));
            listaMaestrosActivos.addAll(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SOLICITUD_ORIGEN));
            listaMaestrosActivos.addAll(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SOLICITUD_ESTADO));
            //servicio gestion
            listaMaestrosActivos.addAll(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SOLICITUD_ENTE_CONTROL));
            listaMaestrosActivos.addAll(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SERVICIO_MOTIVO));
            listaMaestrosActivos.addAll(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SOLICITUD_RIESGO_VIDA));
            //solicitudes
            listaMaestrosActivos.addAll(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_SOLICITUDES_CASOS_ESTADOS));
            listaMaestrosActivos.addAll(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_TECNOLOGIA_ALTO_COSTO));
            listaMaestrosActivos.addAll(getMaestroRemoto().consultarPorTipo(MaestroTipo.AUS_CASO_MOTIVO_ESPECIFICO));
            
        } catch (Exception e) {
            System.out.println("Fallo al cargar maestros atencion al usuario en el singleton maestro");
        }
    }

    public void agregarMaestroContratacion() {
        try {
            listaTiposMaestrosMaestro = new ArrayList();
            listaTiposMaestrosMaestro.add(MaestroTipo.CNT_MODALIDAD);
            listaTiposMaestrosMaestro.add(MaestroTipo.CNT_ESTADO);
            listaMaestrosActivos.addAll(getMaestroRemoto().listarPorTipos(listaTiposMaestrosMaestro));
            listaTiposMaestrosMaestro = new ArrayList();
            listaTiposMaestrosMaestro.add(MaestroTipo.CNT_CLASE_PRESTADOR);
            listaTiposMaestrosMaestro.add(MaestroTipo.CNT_TIPO_CONTACTO);
            listaTiposMaestrosMaestro.add(MaestroTipo.CNT_AREA_CONTACTO);
            listaMaestrosActivos.addAll(getMaestroRemoto().listarPorTipos(listaTiposMaestrosMaestro));
        } catch (Exception e) {
            System.out.println("Fallo al cargar maestros contratacion en el singleton maestro");
        }
    }
    
    public void agregarMaestroContratacionJuridica() {
        try {
            listaTiposMaestrosMaestro = new ArrayList();
            listaTiposMaestrosMaestro.add(MaestroTipo.CNTJ_OTROSIES_ADJUNTOS);
            listaTiposMaestrosMaestro.add(MaestroTipo.CNTJ_CONTRATOS_CLASES);
            listaMaestrosActivos.addAll(getMaestroRemoto().listarPorTipos(listaTiposMaestrosMaestro));
            listaTiposMaestrosMaestro = new ArrayList();
            listaTiposMaestrosMaestro.add(MaestroTipo.CNTJ_CONTRATOS_GARANTIAS);
            listaTiposMaestrosMaestro.add(MaestroTipo.CNTJ_INFORMES_ARCHIVOS);
            listaMaestrosActivos.addAll(getMaestroRemoto().listarPorTipos(listaTiposMaestrosMaestro));
        } catch (Exception e) {
            System.out.println("Fallo al cargar maestros contratacion juridica en el singleton maestro");
        }
    }

    public void limpiar() {
        listaTiposMaestrosMaestro = new ArrayList();
        listaMaestrosActivos = new ArrayList();
        hashMaestrosActivos = new HashMap<>();
        hashValorMaestrosActivos = new HashMap<>();
    }

    public boolean isEmpty() {
        return listaMaestrosActivos.isEmpty();
    }
    
    public void agregarMaestroTipoActivo(){
        try {
            listaMaestroTipo = new ArrayList<>();
            listaMaestroTipo = getMaestroTipoRemoto().consultarActivos();
        } catch (Exception e) {
            System.out.println("Fallo al cargar maestros tipos en el singleton maestro");
        }
    }

    //Getters
    public List<Maestro> getListaMaestrosActivos() {
        return listaMaestrosActivos;
    }

    public HashMap<Integer, Maestro> getHashMaestrosActivos() throws Exception {
        if (this.hashMaestrosActivos == null) {
            this.hashMaestrosActivos = Util.convertToHash(getListaMaestrosActivos());
        }
        return this.hashMaestrosActivos;
    }

    public HashMap<String, Maestro> getHashValorMaestrosActivos() throws Exception {
        if (this.hashValorMaestrosActivos == null) {
            this.hashValorMaestrosActivos = Util.convertToHashValor(getListaMaestrosActivos());
        }
        return this.hashValorMaestrosActivos;
    }

    public List<Maestro> listarPorTipo(String tipo) {
        List<Maestro> lista = getListaMaestrosActivos().stream().filter(u -> u.getTipo().equals(tipo)).collect(Collectors.toList());
        if (lista.isEmpty()) {
            try {
                lista = getMaestroRemoto().consultarPorTipo(tipo);
                listaMaestrosActivos.addAll(lista);
            } catch (Exception e) {
            }
        }
        return lista;
    }
    
    public List<MaestroTipo> getlistaMaestroTipo() {
        if(listaMaestroTipo == null || listaMaestroTipo.isEmpty()){
            agregarMaestroTipoActivo();
        }
        return listaMaestroTipo;
    }

    //CAMBIAR
    public List<Maestro> listarPorTipoYOrdenPorId(String tipo) {
        return getListaMaestrosActivos().stream().filter(u -> u.getTipo().equals(tipo)).sorted(Comparator.comparingInt(Maestro::getId)).collect(Collectors.toList());
    }

    public HashMap<Integer, Maestro> getHashPorTipo(String tipo) throws Exception {
        return Util.convertToHash(listarPorTipo(tipo));
    }

    public HashMap<String, Maestro> getHashValorPorTipo(String tipo) throws Exception {
        return Util.convertToHashValor(listarPorTipo(tipo));
    }

    public Maestro consultarPadre(int idHijo, int idPadre) {
        try {
            Maestro maeHijo = getHashMaestrosActivos().get(idHijo);
            if (maeHijo != null && maeHijo.getMaestroPadres() != null) {
                for (Maestro aux : maeHijo.getMaestroPadres()) {
                    if (aux.getId().equals(idPadre)) {
                        Maestro padre = aux;
                        return padre;
                    }
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Maestro> consultarHijos(int idPadre) {
        //PENDIENTE
        return null;
    }

    /**
     * @return the listaTiposMaestrosMaestro
     */
    public List<String> getListaTiposMaestrosMaestro() {
        return listaTiposMaestrosMaestro;
    }

    /**
     * @param listaTiposMaestrosMaestro the listaTiposMaestrosMaestro to set
     */
    public void setListaTiposMaestrosMaestro(List<String> listaTiposMaestrosMaestro) {
        this.listaTiposMaestrosMaestro = listaTiposMaestrosMaestro;
    }
    
    public Maestro consultarMaestro(int idmaestro) throws Exception{
        return getMaestroRemoto().consultar(idmaestro);
    }

    }
