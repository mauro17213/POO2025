/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.web.cargas.servicio;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.cargas.CarCarga;
import com.saviasaludeps.savia.dominio.cargas.CarCargaRegistro;
import com.saviasaludeps.savia.dominio.cargas.CarPeriodo;
import com.saviasaludeps.savia.dominio.cargas.CarProceso;
import com.saviasaludeps.savia.dominio.cargas.CarProcesoPrestador;
import com.saviasaludeps.savia.dominio.cargas.CarProcesoUsuario;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.cargas.CarCargaGestionRemoto;
import com.saviasaludeps.savia.negocio.cargas.CarCargaRemoto;
import com.saviasaludeps.savia.negocio.cargas.CarPeriodoRemoto;
import com.saviasaludeps.savia.negocio.cargas.CarProcesoPrestadoresRemoto;
import com.saviasaludeps.savia.negocio.cargas.CarProcesoUsuarioRemoto;
import com.saviasaludeps.savia.web.cargas.bean.CargaBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author aguevara
 */
public class CargaServicioImpl extends AccionesBO implements CargaServicioIface {

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }

    private CarCargaRemoto getCargaRemoto() throws Exception {
        return (CarCargaRemoto) RemotoEJB.getEJBRemoto("CarCargaServicio", CarCargaRemoto.class.getName());
    }

    private CarProcesoUsuarioRemoto getCarProcesoUsuarioRemoto() throws Exception {
        return (CarProcesoUsuarioRemoto) RemotoEJB.getEJBRemoto("CarProcesoUsuarioServicio", CarProcesoUsuarioRemoto.class.getName());
    }

    private CarPeriodoRemoto getCarPeriodoRemoto() throws Exception {
        return (CarPeriodoRemoto) RemotoEJB.getEJBRemoto("CarPeriodoServicio", CarPeriodoRemoto.class.getName());
    }

    private CarProcesoPrestadoresRemoto getCarProcesoPrestadoresRemoto() throws Exception {
        return (CarProcesoPrestadoresRemoto) RemotoEJB.getEJBRemoto("CarProcesoPrestadoresServicio", CarProcesoPrestadoresRemoto.class.getName());
    }

    private CarCargaGestionRemoto getCarCargaGestionRemoto() throws Exception {
        return (CarCargaGestionRemoto) RemotoEJB.getEJBRemoto("CarCargaGestionServicio", CarCargaGestionRemoto.class.getName());
    }

    @Override
    public void Accion(CargaBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_VER:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_VER:
                            ver(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            verListaErrores(bean);
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            verAdjuntos(bean);
                            break;
                        case Url.ACCION_ADICIONAL_3:
                            //verContratos(bean);
                            break;

                    }
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
                    //
                    break;
                case Url.ACCION_ADICIONAL_2:
                    //
                    break;
                default:
                    break;
            }
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    @Override
    public void cargasInicial(CargaBean bean) {
    }

    private void listar(CargaBean bean) {
        try {

            bean.getParamConsulta().setCantidadRegistros(getCargaRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getCargaRemoto().consultarLista(bean.getParamConsulta()));
            bean.setListaProcesoUsuarios(getCarProcesoUsuarioRemoto().listarPorIdUsuario(bean.getConexion().getUsuario().getId()));

            List<CarProceso> listaProcesos = getCarProcesoUsuarioRemoto()
                    .listarProcesosUnicosPorUsuario(bean.getConexion().getUsuario().getId(), bean.getParamConsulta());

            bean.setListaProcesos(listaProcesos);
        } catch (Exception e) {
            // Manejo de errores
            bean.addError(e.getMessage());
        }
    }

    private void ver(CargaBean bean) {
        try {
            bean.setObjeto(getCargaRemoto().consultar(bean.getObjeto().getId()));
            bean.setListaProcesoUsuarios(getCarProcesoUsuarioRemoto().consultarLista(bean.getParamConsulta()));
            bean.setListaPeriodos(getCarPeriodoRemoto().consultarListaTotal(bean.getParamConsulta()));
            bean.setListaProcesosPrestadores(getCarProcesoPrestadoresRemoto().consultarListaTotal(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }

    }

    private void verAdjuntos(CargaBean bean) {
        try {
            bean.setRegistros(getCargaRemoto().consultarLista(bean.getParamConsulta()));
            bean.setListaAdjuntos(new ArrayList<>());
            for (CarCarga carga : bean.getRegistros()) {
                if (carga.getId().equals(bean.getObjeto().getId())) {
                    bean.getListaAdjuntos().add(carga);
                }
            }

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }

    }

    private void verListaErrores(CargaBean bean) {
        try {
            // Inicializa lista de registros y errores
            bean.setListaCargaRegistros(getCarCargaGestionRemoto().consultarPorIdCarga(bean.getObjeto().getId()));
            bean.setListaErroresCarga(new ArrayList<>());

            for (CarCargaRegistro registro : bean.getListaCargaRegistros()) {
                if (registro.getFallos() != null && registro.isFallido() == true) {
                    // Divide los errores de la fila actual y los añade a la lista general
                    String[] erroresArray = registro.getFallos().split("\n");
                    for (String error : erroresArray) {
                        bean.getListaErroresCarga().add(error);
                    }
                }
            }

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(CargaBean bean) {
        try {
            // Inicializar el objeto principal
            bean.setObjeto(new CarCarga());
            // Consultar y establecer listas
            bean.setListaPeriodos(getCarPeriodoRemoto().consultarListaTotal(bean.getParamConsulta()));
            bean.setListaProcesosPrestadores(getCarProcesoPrestadoresRemoto().consultarListaTotal(bean.getParamConsulta()));

        } catch (Exception e) {
            // Manejo de errores con mensaje más claro
            String errorMessage = String.format("Hubo un fallo al crear: %s. Por favor, contacte al administrador.", e.getMessage());
            bean.addError(errorMessage);
        }

    }

    private void guardar(CargaBean bean) {
        try {
            bean.auditoriaGuardar(bean.getObjeto());

            if (!bean.isError()) {
                List<CarProcesoUsuario> listaProcesoUsuarios = bean.getListaProcesoUsuarios();

                // Extraer la lista de CarProceso desde la lista de CarProcesoUsuario
                List<CarProceso> listaCarProcesos = listaProcesoUsuarios.stream()
                        .map(CarProcesoUsuario::getProceso)
                        .collect(Collectors.toList());

                // Encontrar el proceso con el id igual a objetoProcesoId
                CarProceso procesoFiltrado = listaCarProcesos.stream()
                        .filter(proceso -> proceso.getId().equals(bean.getObjetoProcesoId()))
                        .findFirst()
                        .orElse(null);

                bean.setObjetoProceso(procesoFiltrado);

                String jsonProceso = bean.getObjetoProceso().getEstructuraJson();

                bean.setObjetoPrestador(bean.getListaProcesosPrestadores().stream()
                        .filter(prestador -> prestador.getPrestador().getId().equals(bean.getObjetoPrestadorId()))
                        .findFirst()
                        .orElse(null));

                // Buscar el objeto Periodo en la lista que coincida con el objetoPeriodoId
                bean.setObjetoPeriodo(bean.getListaPeriodosProceso().stream()
                        .filter(periodo -> periodo.getId().equals(bean.getObjetoPeriodoId()))
                        .findFirst()
                        .orElse(null));

                Date fechaActual = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                String fechaFormateada = sdf.format(fechaActual);

                // Generar el nombre del archivo servidor
                String nombreProcesoLimpio = limpiarCadena(bean.getObjetoProceso().getNombre());
                String nombreArchivoServidor = ("car_" + nombreProcesoLimpio
                        + "_" + bean.getObjetoPrestador().getPrestador().getNumeroDocumento()
                        + "_" + fechaFormateada
                        + ".txt").toLowerCase();

                for (CarCarga adjuntoNuevo : bean.getListaAdjuntos()) {
                    bean.getObjeto().setEmpresaId(new Empresa(bean.getConexion().getEmpresa().getId()));
                    bean.getObjeto().setPrestadorId(bean.getObjetoPrestador());
                    bean.getObjeto().setEstado((short) 0);
                    bean.getObjeto().setCarPeriodoId(bean.getObjetoPeriodo());
                    bean.getObjeto().setCarProcesoId(bean.getObjetoProceso());
                    bean.getObjeto().setFechaHoraInicio(new Date());
                    bean.getObjeto().setNombreArchivo(adjuntoNuevo.getNombreArchivo());
                    bean.getObjeto().setRuta(adjuntoNuevo.getRuta());
                    bean.getObjeto().setArchivo(nombreArchivoServidor);
                    bean.getObjeto().setExiste(true);
                    bean.getObjeto().setExitosos((short) 0);
                    bean.getObjeto().setFallidos((short) 0);
                    bean.getObjeto().setId(getCargaRemoto().insertar(bean.getObjeto()));
                    cambiarNombreArchivo(nombreArchivoServidor, adjuntoNuevo.getRuta(), adjuntoNuevo.getNombreArchivo());
                }

                bean.setListaAdjuntos(null);

                bean.addMensaje("Cargue de archivo realizado con éxito, por favor consulte de nuevo en un momento para validar el estado");
            }

        } catch (Exception e) {
            bean.addError("Error: " + e.toString());
        }
    }

    private void editar(CargaBean bean) {
        try {
            bean.setObjeto(getCargaRemoto().consultar(bean.getObjeto().getId()));
            bean.setListaProcesoUsuarios(getCarProcesoUsuarioRemoto().consultarLista(bean.getParamConsulta()));
            bean.setListaPeriodos(getCarPeriodoRemoto().consultarListaTotal(bean.getParamConsulta()));
            bean.setListaProcesosPrestadores(getCarProcesoPrestadoresRemoto().consultarListaTotal(bean.getParamConsulta()));

            // Inicializar y llenar listaProcesos utilizando un Set para evitar duplicados
            Set<CarProceso> procesosSet = new HashSet<>();
            Integer usuarioId = bean.getConexion().getUsuario().getId(); // Cambiado a Integer

            for (CarProcesoUsuario procesoUsuario : bean.getListaProcesoUsuarios()) {
                if (procesoUsuario.getUsuario().getId().equals(usuarioId)) {
                    procesosSet.add(procesoUsuario.getProceso());
                }
            }
            // Convertir el Set a una lista y asignarla al bean
            bean.setListaProcesos(new ArrayList<>(procesosSet));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }

    }

    private void modificar(CargaBean bean) {
        try {
            List<CarProcesoUsuario> listaProcesoUsuarios = bean.getListaProcesoUsuarios();

            // Extraer la lista de CarProceso desde la lista de CarProcesoUsuario
            List<CarProceso> listaCarProcesos = listaProcesoUsuarios.stream()
                    .map(CarProcesoUsuario::getProceso) // Usa el nombre del método correcto
                    .collect(Collectors.toList());

            // Encontrar el proceso con el id igual a objetoProcesoId
            CarProceso procesoFiltrado = listaCarProcesos.stream()
                    .filter(proceso -> proceso.getId().equals(bean.getObjetoProcesoId()))
                    .findFirst() // Obtiene el primer elemento que coincide (o vacío si no hay coincidencias)
                    .orElse(null);  // Retorna null si no se encuentra ningún proceso

            bean.setObjetoProceso(procesoFiltrado);

            bean.setObjetoPrestador(bean.getListaProcesosPrestadores().stream()
                    .filter(prestador -> prestador.getPrestador().getId().equals(bean.getObjetoPrestadorId()))
                    .findFirst() // Tomar el primer elemento que coincida (o ninguno si no hay coincidencias)
                    .orElse(null));

            // Buscar el objeto Periodo en la lista que coincida con el objetoPeriodoId
            bean.setObjetoPeriodo(bean.getListaPeriodosProceso().stream()
                    .filter(periodo -> periodo.getId().equals(bean.getObjetoPeriodoId()))
                    .findFirst() // Tomar el primer elemento que coincida (o ninguno si no hay coincidencias)
                    .orElse(null));

            bean.getObjeto().setPrestador(bean.getObjetoPrestador().getPrestador());
            bean.getObjeto().setCarPeriodoId(bean.getObjetoPeriodo());
            bean.getObjeto().setCarProcesoId(bean.getObjetoProceso());
            bean.auditoriaModificar(bean.getObjeto());
            getCargaRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("Datos de carga actualizados");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }

    }

    private void borrar(CargaBean bean) {
        try {
            bean.setObjeto(getCargaRemoto().eliminar(bean.getObjeto().getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }

    }

    private void cambiarNombreArchivo(String nombreArchivoServidor, String ruta, String nombreArchivoOriginal) throws FileNotFoundException {
        // Crear el archivo original usando la ruta y el nombre original del archivo
        File archivoOriginal = new File(ruta, nombreArchivoOriginal);

        // Agregar la extensión ".txt" al nombre del archivo destino si no la tiene
        if (!nombreArchivoServidor.endsWith(".txt")) {
            nombreArchivoServidor += ".txt";
        }

        // Crear el archivo destino con el nuevo nombre en la misma ruta
        File archivoRenombrado = new File(ruta, nombreArchivoServidor);

        // Verificar si el archivo original existe y renombrarlo
        if (archivoOriginal.exists()) {
            boolean renombrado = archivoOriginal.renameTo(archivoRenombrado);
            if (!renombrado) {
                throw new RuntimeException("No se pudo renombrar el archivo: " + nombreArchivoOriginal);
            }
        } else {
            throw new FileNotFoundException("Archivo original no encontrado: " + nombreArchivoOriginal);
        }
    }

    // Método para limpiar el nombre del proceso eliminando espacios y caracteres especiales
    private String limpiarCadena(String cadena) {
        if (cadena == null) {
            return "";
        }

        cadena = cadena.replace(" ", "");  // Quitar espacios
        cadena = Normalizer.normalize(cadena, Normalizer.Form.NFD); // Quitar acentos
        cadena = cadena.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        cadena = cadena.replaceAll("[^a-zA-Z0-9]", ""); // Quitar caracteres especiales no deseados

        return cadena;
    }

}
