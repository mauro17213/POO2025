package com.saviasaludeps.savia.web.singleton;

import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.negocio.administracion.UbicacionRemoto;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class UbicacionSingle {

    private final int ID_DEPARTAMENTO_ANTIOQUIA = 2;

    private List<Ubicacion> listaUbicaciones;
    private HashMap<Integer, Ubicacion> hashUbicaciones;

    private List<Ubicacion> listaPaises;
    private List<Ubicacion> listaDepartamentos;
    private List<Ubicacion> listaMunicipios;
    private List<Ubicacion> listaMunicipiosAntioquia;
    private HashMap<Integer, Ubicacion> hashPaises;
    private HashMap<Integer, Ubicacion> hashDepartamentos;
    private HashMap<Integer, Ubicacion> hashMunicipios;
    private HashMap<Integer, Ubicacion> hashMunicipiosAntioquia;

    private static UbicacionSingle ubicacionInstance = null;

    private UbicacionRemoto getUbicacionRemoto() throws Exception {
        return (UbicacionRemoto) RemotoEJB.getEJBRemoto("UbicacionServicio", UbicacionRemoto.class.getName());
    }

    private UbicacionSingle() {

    }

    public static UbicacionSingle getInstance() {
        if (ubicacionInstance == null) {
            ubicacionInstance = new UbicacionSingle();
        }
        return ubicacionInstance;
    }

    public void actualizar() {
        try {
            List<Ubicacion> listaPaisesInt = new ArrayList();
            List<Ubicacion> listaDepartamentosInt = new ArrayList();
            List<Ubicacion> listaMunicipiosInt = new ArrayList();
            List<Ubicacion> listaMunicipiosAntioquiaInt = new ArrayList();
            HashMap<Integer, Ubicacion> hashPaisesInt = new HashMap();
            HashMap<Integer, Ubicacion> hashDepartamentosInt = new HashMap();
            HashMap<Integer, Ubicacion> hashMunicipiosInt = new HashMap();
            HashMap<Integer, Ubicacion> hashMunicipiosAntioquiaInt = new HashMap();
            HashMap<Integer, Ubicacion> hashUbicacionesInt = new HashMap();
            List<Ubicacion> listaUbicacionesInt = getUbicacionRemoto().consultarActivas();
            for (Ubicacion ubi : listaUbicacionesInt) {
                hashUbicacionesInt.put(ubi.getId(), ubi);
                switch (ubi.getTipo()) {
                    case Ubicacion.TIPO_MUNICIPIO:
                        listaMunicipiosInt.add(ubi);
                        hashMunicipiosInt.put(ubi.getId(), ubi);
                        if (ubi.getUbicacionPadre() != null && ubi.getUbicacionPadre().getId() != null) {
                            listaMunicipiosAntioquiaInt.add(ubi);
                            hashMunicipiosAntioquiaInt.put(ubi.getId(), ubi);
                        }
                        break;
                    case Ubicacion.TIPO_DEPARTAMENTO:
                        listaDepartamentosInt.add(ubi);
                        hashDepartamentosInt.put(ubi.getId(), ubi);
                        break;
                    case Ubicacion.TIPO_PAIS:
                        listaPaisesInt.add(ubi);
                        hashPaisesInt.put(ubi.getId(), ubi);
                        break;
                }
            }
            this.listaUbicaciones = new ArrayList(listaUbicacionesInt);
            this.listaMunicipios = new ArrayList(listaMunicipiosInt);
            this.listaMunicipiosAntioquia = new ArrayList(listaMunicipiosAntioquiaInt);
            this.listaDepartamentos = new ArrayList(listaDepartamentosInt);
            this.listaPaises = new ArrayList(listaPaisesInt);
            this.hashMunicipios = (HashMap<Integer, Ubicacion>) hashMunicipiosInt.clone();
            this.hashMunicipiosAntioquia = (HashMap<Integer, Ubicacion>) hashMunicipiosAntioquiaInt.clone();
            this.hashDepartamentos = (HashMap<Integer, Ubicacion>) hashDepartamentosInt.clone();
            this.hashPaises = (HashMap<Integer, Ubicacion>) hashPaisesInt.clone();
            this.hashUbicaciones = (HashMap<Integer, Ubicacion>) hashUbicacionesInt.clone();
        } catch (Exception e) {
            limpiar();
        }
    }

    public boolean isEmpty() {
        return listaUbicaciones.isEmpty();
    }

    private void limpiar() {
        listaUbicaciones = new ArrayList();
        this.listaMunicipios = null;
        this.listaDepartamentos = null;
        this.listaPaises = null;
        this.hashUbicaciones = null;
        this.hashMunicipios = null;
        this.hashDepartamentos = null;
        this.hashPaises = null;
        this.hashMunicipiosAntioquia = null;
    }

    public List<Ubicacion> getListaUbicaciones() {
        return listaUbicaciones;
    }

    public List<Ubicacion> getListaMunicipios() throws Exception {
        return this.listaMunicipios;
    }

    public List<Ubicacion> getListaDepartamentos() throws Exception {
        return this.listaDepartamentos;
    }

    public List<Ubicacion> getListaPaises() throws Exception {
        return this.listaPaises;
    }

    public HashMap<Integer, Ubicacion> getHashUbicaciones() throws Exception {
        return this.hashUbicaciones;
    }

    public HashMap<Integer, Ubicacion> getHashMunicipios() throws Exception {
        return this.hashMunicipios;
    }

    public HashMap<Integer, Ubicacion> getHashDepartamentos() throws Exception {
        return this.hashDepartamentos;
    }

    public HashMap<Integer, Ubicacion> getHashPaises() throws Exception {
        return this.hashPaises;
    }

    public HashMap<Integer, Ubicacion> getHashMunicipiosAntioquia() throws Exception {
        return this.hashMunicipiosAntioquia;
    }

    public List<Ubicacion> getListaMunicipiosAntioquia() {
        return this.listaMunicipiosAntioquia;
    }

    public List<Ubicacion> listarPorTipo(int tipo) {
        return getListaUbicaciones().stream().filter(u -> u.getTipo() == tipo).collect(Collectors.toList());
    }

    public Ubicacion consultarPadre(int idHijo) {
        try {
            Ubicacion ubiHijo = getHashUbicaciones().get(idHijo);
            if (ubiHijo != null && ubiHijo.getUbicacionPadre() != null && ubiHijo.getUbicacionPadre().getId() != null) {
                Ubicacion padre = getHashUbicaciones().get(ubiHijo.getUbicacionPadre().getId());
                return padre;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Ubicacion> consultarHijos(int idPadre) {
        return getListaUbicaciones().stream().filter(u -> (u.getUbicacionPadre().getId() != null && u.getUbicacionPadre().getId().equals(idPadre))).collect(Collectors.toList());
    }

    public Ubicacion consultarMunicipiPorPrefijos(String prefijoDepartamento, String prefijoMunicipio) {
        return getListaUbicaciones().stream().filter(u -> (u.getPrefijo().equals(prefijoMunicipio) && u.getUbicacionPadre().getPrefijo() != null && u.getUbicacionPadre().getPrefijo().equals(prefijoDepartamento))).findFirst().get();
    }
    
    public List<Ubicacion> consultarMunicipiosConCobertura() throws Exception {
        return getListaMunicipios().stream().filter(u -> u.isCobertura()).collect(Collectors.toList());
    }

    public String obtenerPrefijoCompleto(int ciudadId) {
        Ubicacion ciudad = hashUbicaciones.get(ciudadId);
        if (ciudad != null && ciudad.getUbicacionPadre() != null) {
            Ubicacion departamento = hashUbicaciones.get(ciudad.getUbicacionPadre().getId());
            if (departamento != null) {
                return departamento.getPrefijo() + ciudad.getPrefijo();
            }
        }
        return null;
    }

}
