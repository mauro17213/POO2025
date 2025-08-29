/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.web.especial.servicio;

import com.google.gson.reflect.TypeToken;
import com.saviasaludeps.savia.dominio.especial.PeCorrelacion;
import com.saviasaludeps.savia.dominio.especial.PeEtapaCalculo;
import com.saviasaludeps.savia.dominio.especial.PeInsumoCalculo;
import com.saviasaludeps.savia.dominio.especial.PeOperacion;
import com.saviasaludeps.savia.dominio.especial.PeTipoValidacion;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.especial.PeTipoCorrelacion;
import com.saviasaludeps.savia.dominio.especial.PeTipoVariable;
import com.saviasaludeps.savia.dominio.especial.PeValidacion;
import com.saviasaludeps.savia.dominio.especial.PeVariable;
import com.saviasaludeps.savia.negocio.especial.PeProgramaRemoto;
import com.saviasaludeps.savia.negocio.especial.PeVariableRemoto;
import com.saviasaludeps.savia.web.especial.bean.GestionVariablesEspecificasBean;
import com.saviasaludeps.savia.web.especial.utilidades.GsonUtil;
import com.saviasaludeps.savia.web.especial.utilidades.PeConstantes;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class GestionVariablesEspecificasServicioImpl extends AccionesBO implements
        GestionVariablesEspecificasServicioIface {

    private PeProgramaRemoto getPeProgramaRemoto() throws Exception {
        return (PeProgramaRemoto) RemotoEJB.getEJBRemoto("PeProgramaServicio", PeProgramaRemoto.class.getName());
    }

    private PeVariableRemoto getPeVariableRemoto() throws Exception {
        return (PeVariableRemoto) RemotoEJB.getEJBRemoto("PeVariableServicio", PeVariableRemoto.class.getName());
    }

    @Override
    public void Accion(GestionVariablesEspecificasBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    this.listar(bean);
                    break;
                case Url.ACCION_VER:
                    this.ver(bean);
                    break;
                case Url.ACCION_CREAR:
                    this.crear(bean);
                    break;
                case Url.ACCION_GUARDAR:
                    this.guardar(bean);
                    break;
                case Url.ACCION_EDITAR:
                    this.editar(bean);
                    break;
                case Url.ACCION_MODIFICAR:
                    this.modificar(bean);
                    break;
                case Url.ACCION_BORRAR:
                    this.borrar(bean);
                    break;
            }
        }
    }

    @Override
    public void cargaInicial(GestionVariablesEspecificasBean bean) {
        try {
            PePrograma pePrograma = new PePrograma();
            PeVariable peVariable = new PeVariable();
            peVariable.setPePrograma(pePrograma);
            bean.setVariable(peVariable);
            bean.setProgramas(getPeProgramaRemoto().consultarTodosEstado(PeConstantes.PE_PROGRAMA_ACTIVO));
            bean.setListadoTipoVariables(PeConstantes.getListadoTipoVariables());
            bean.setListaSiNo(PeConstantes.listaSino());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(GestionVariablesEspecificasBean bean) {
        try {
            if (!validacionesAdicionales(bean)) {
                return;
            }
            bean.auditoriaGuardar(bean.getVariable());
            this.getPeVariableRemoto().insertar(bean.getVariable());
            bean.addMensaje("Se creó la variable de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void listar(GestionVariablesEspecificasBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getPeVariableRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getPeVariableRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    private PeVariable consultarVariableIdProgramaNombre(GestionVariablesEspecificasBean bean) {
        try {
            return this.getPeVariableRemoto().consultarPorIdProgramaNombre(bean.getVariable().getPePrograma().getId(),
                    bean.getVariable().getNombre());
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
            return null;
        }
    }

    private boolean validacionesAdicionales(GestionVariablesEspecificasBean bean) throws Exception {
        boolean validacionesOk = true;

        if (bean.getVariable().getNombre().isBlank()) {
            validacionesOk = false;
            bean.addError("Nombre: Error de validación: se necesita un valor.");
        }

        if (!bean.getVariable().getNombre().matches(PeConstantes.PATRON_NOMBRE_VARIABLE)) {
            validacionesOk = false;
            bean.addError("Nombre: Error de validación: solo debe contener letras minúsculas, si es una palabra no debe tener ningún separador, si son dos o mas palabras "
                    + "deben estar separadas por guiones bajos (_). Ejemplo: abc o abc_def .");
        }

        PeVariable variableConsultada = this.consultarVariableIdProgramaNombre(bean);
        if (variableConsultada != null && bean.getVariable().getId() == null) {
            validacionesOk = false;
            bean.addError("Nombre: Error de validación: ya existe otra variable en el programa con el mismo nombre.");
        }

        if (bean.getVariable().getDescripcion().isBlank()) {
            validacionesOk = false;
            bean.addError("Descripción: Error de validación: se necesita un valor.");
        }

        //***atributo validaciones en la variable:
        if (Objects.nonNull(bean.getValidacionesJson()) && !bean.getValidacionesJson().isBlank()) {
            List<PeValidacion> validaciones
                    = GsonUtil.deserializar(bean.getValidacionesJson(), new TypeToken<ArrayList<PeValidacion>>() {
                    });

            if (Objects.isNull(validaciones) || validaciones.isEmpty()) {
                validacionesOk = false;
                bean.addError("Validaciones: Error de validación: existe error en la estructura del json, "
                        + "el json debe tener un arreglo con la siguiente estructura: [{ \"nombre\": \"nombre x\", \"tipo\": 0, \"valor\": \"valor y\"}] .");
            }

            if (Objects.nonNull(validaciones)) {

                List<PeTipoValidacion> tipoValidaciones = PeConstantes.getListadoTipoValidaciones();
                int ultimoId = tipoValidaciones.get(tipoValidaciones.size() - 1).getId();
                String nombreTipoVariable = PeTipoVariable.getNombreById(bean.getVariable().getTipo());

                for (PeValidacion validacion : validaciones) {//verificaciones por cada validacion

                    if ((Objects.isNull(validacion.getTipo()))) {
                        validacionesOk = false;
                        bean.addError("Validaciones: Error de validación: el campo \"tipo\" es obligatorio.");
                        break;

                    }
                    if (this.validarNombreNulo(validacion)) {
                        validacionesOk = false;
                        bean.addError("Validaciones: Error de validación: el campo \"nombre\" es obligatorio.");
                        break;
                    }

                    if (this.validarValorNulo(validacion)) {
                        validacionesOk = false;
                        bean.addError("Validaciones: Error de validación: el campo \"valor\" es obligatorio.");
                        break;

                    }

                    if (validacion.getTipo() > ultimoId) {
                        validacionesOk = false;
                        bean.addError("Validaciones: Error de validación: el campo \"tipo\". debe tener un numero entre [0 - " + ultimoId + "]");
                        break;
                    }

                    if (Objects.equals(validacion.getTipo(), PeTipoValidacion.ANTERIORIDAD.getId())) {

                        if (bean.getVariable().getTipo() != PeTipoVariable.FECHA.getId()) {
                            validacionesOk = false;
                            bean.addError("Validaciones: Error de validación: La validación tipo 3 solo se puede asignar a una variable tipo fecha.");
                            break;
                        }

                        if (!this.validarInstanciaEntero(validacion.getValor())) {
                            validacionesOk = false;
                            bean.addError("Validaciones: Error de validación: el campo \"valor\" con validación tipo 3 debe ser un entero.");
                            break;
                        }
                    }

                    if (Objects.equals(validacion.getTipo(), PeTipoValidacion.ACTUALIDAD.getId())) {

                        if (bean.getVariable().getTipo() != PeTipoVariable.FECHA.getId()) {
                            validacionesOk = false;
                            bean.addError("Validaciones: Error de validación: La validación tipo 4 solo se puede asignar a una variable tipo fecha.");
                            break;
                        }

                    }

                    if (Objects.equals(validacion.getTipo(), PeTipoValidacion.CORRELACION.getId())) {

                        if (!this.validarValidacionCorrelacion(bean, validaciones, validacion)) {
                            validacionesOk = false;
                            break;
                        }

                    }

                    if ((!Objects.equals(validacion.getTipo(), PeTipoValidacion.ANTERIORIDAD.getId()) && !Objects.equals(validacion.getTipo(), PeTipoValidacion.ACTUALIDAD.getId()))
                            && !Objects.equals(validacion.getTipo(), PeTipoValidacion.CORRELACION.getId())) {
                        //valida que el valor sea una instancia del tipo de variable
                        if (!this.validacionValor(bean.getVariable(), validacion.getValor())) {
                            validacionesOk = false;
                            bean.addError("Validaciones: Error de validación: el campo \"valor\" debe ser igual al tipo de variable. En este caso debe ser un valor tipo " + nombreTipoVariable);
                            break;
                        }
                    }

                }

                if (validacionesOk) {
                    validacionesOk = this.validarRangoMinimoMaximo(bean, validaciones)
                            && this.validarAnidacionAnterioridad(bean, validaciones)
                            && this.validarAnidacionActualidad(bean, validaciones);
                }
            }

            if (validacionesOk) {
                bean.getVariable().setValidaciones(validaciones);
            }

        }
        //***atributo insumo calculo en la variable:
        if (Objects.nonNull(bean.getInsumoCalculoJson()) && !bean.getInsumoCalculoJson().isBlank()) {
            PeInsumoCalculo insumoCalculo = GsonUtil.deserializar(bean.getInsumoCalculoJson(), new TypeToken<PeInsumoCalculo>() {
            });

            if (Objects.isNull(insumoCalculo)) {
                bean.addError("Insumo calculo: existe error en la estructura del json, "
                        + "el json debe tener la siguiente estructura: { \"variables\": [\"variable_a\", \"variable_b\", ...], \"etapas\": [{ \"operandos\": [\"variable_a\", \"variable_b\", ...],"
                        + "\"operacion\": \"+\", \"asignacion_temporal\": \"variable_a\" }]} .");
                return false;
            } else {
                if (validacionesOk) {
                    validacionesOk = this.validacionesInsumoCalculo(insumoCalculo, bean);
                    bean.getVariable().setInsumoCalculo(insumoCalculo);
                }
            }
        }
        return validacionesOk;
    }

    private void obtenerVariablePorId(GestionVariablesEspecificasBean bean) throws Exception {
        PeVariable resultado = this.getPeVariableRemoto().consultarPorId(bean.getVariable().getId());
        bean.setVariable(resultado);

        if (Objects.nonNull(resultado.getValidaciones())) {
            bean.setValidacionesJson(GsonUtil.serializar(resultado.getValidaciones()));
        }

        if (Objects.nonNull(resultado.getInsumoCalculo())) {
            bean.setInsumoCalculoJson(GsonUtil.serializar(resultado.getInsumoCalculo()));
        }
    }

    private void ver(GestionVariablesEspecificasBean bean) {
        try {
            this.obtenerVariablePorId(bean);
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    private void crear(GestionVariablesEspecificasBean bean) {
        try {
            PeVariable peVariable = new PeVariable();
            peVariable.setPePrograma(bean.getPrograma());
            peVariable.setValidaciones(null);
            bean.setVariable(peVariable);
            bean.setValidacionesJson(null);
            bean.setMostrarPanelValidaciones(false);
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    private void editar(GestionVariablesEspecificasBean bean) {
        try {
            this.obtenerVariablePorId(bean);
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    private void modificar(GestionVariablesEspecificasBean bean) {
        try {
            if (!validacionesAdicionales(bean)) {
                return;
            }
            bean.auditoriaModificar(bean.getVariable());
            this.getPeVariableRemoto().actualizar(bean.getVariable());
            bean.addMensaje("Se actualizó la variable de manera exitosa");
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    private void borrar(GestionVariablesEspecificasBean bean) {
        try {
            this.getPeVariableRemoto().eliminar(bean.getVariable().getId());
            bean.addMensaje("Se eliminó la variable de manera exitosa");
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    private boolean validarInstanciaEntero(Object valor) {
        if (valor instanceof Double) {
            return (Double) valor % 1 == 0;
        }
        return false;
    }

    /**
     * Valida la llave valor contenida en una validacion de la variable
     */
    private boolean validacionValor(PeVariable variable, Object valor) {
        switch (variable.getTipo()) {
            case 0://FECHA
                try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                sdf.setLenient(true);
                sdf.parse(valor.toString());
                return true;
            } catch (ParseException e) {
                return false;
            }

            case 1://ENTERO
                return this.validarInstanciaEntero(valor);
            case 2://DECIMAL
                return valor instanceof Double;
            case 3:
            case 4://TEXTO Y TEXTO LARGO
                return valor instanceof String;
            default:
                return true; //5 -> calculo
        }
    }

    /*
    * Valida que no haya mas de dos rangos minimos o maximos y que el rango minimo sea inferior al maximo
     */
    private boolean validarRangoMinimoMaximo(GestionVariablesEspecificasBean bean, List<PeValidacion> validaciones) {
        boolean validacionOk = true;

        List<PeValidacion> validacionesRangoMinimo = validaciones.stream()
                .filter(v -> Objects.equals(v.getTipo(), 0))
                .collect(Collectors.toList());

        List<PeValidacion> validacionesRangoMaximo = validaciones.stream()
                .filter(v -> Objects.equals(v.getTipo(), 1))
                .collect(Collectors.toList());

        if (!validacionesRangoMinimo.isEmpty()) {
            if (validacionesRangoMinimo.size() > 1) {
                validacionOk = false;
                bean.addError("Validaciones: Error de validación: no se permite tener mas de 1 rango mínimo.");
            }
        }

        if (!validacionesRangoMaximo.isEmpty()) {
            if (validacionesRangoMaximo.size() > 1) {
                validacionOk = false;
                bean.addError("Validaciones: Error de validación: no se permite tener mas de 1 rango máximo.");
            }
        }

        if (!validacionesRangoMinimo.isEmpty() && !validacionesRangoMaximo.isEmpty()) {
            if (validacionOk) {
                Object rangoMinimo = validacionesRangoMinimo.get(0).getValor();
                Object rangoMaximo = validacionesRangoMaximo.get(0).getValor();
                boolean rangoMinimoInferior = false;

                switch (bean.getVariable().getTipo()) {
                    case 0: //FECHA
                        LocalDate fechaMinima = LocalDate.parse(rangoMinimo.toString());
                        LocalDate fechaMaxima = LocalDate.parse(rangoMaximo.toString());
                        rangoMinimoInferior = fechaMinima.isBefore(fechaMaxima);
                        break;
                    case 1: //ENTERO
                        int enteroMinimo = ((Double) rangoMinimo).intValue();
                        int enteroMaximo = ((Double) rangoMaximo).intValue();
                        rangoMinimoInferior = enteroMaximo > enteroMinimo;
                        break;
                    case 2: //DOUBLE
                        double doubleMinimo = ((Double) rangoMinimo);
                        double doubleMaximo = ((Double) rangoMaximo);
                        rangoMinimoInferior = doubleMaximo > doubleMinimo;
                        break;
                    default:
                        validacionOk = false;
                        break;
                }

                if (!rangoMinimoInferior) {
                    validacionOk = false;
                    bean.addError("Validaciones: Error de validación: el rango mínimo debe ser inferior al máximo.");
                }

            }
        }
        return validacionOk;
    }

    private boolean validarNombreNulo(PeValidacion validacion) {
        if (!Objects.equals(validacion.getTipo(), PeTipoValidacion.IGUAL.getId())) {
            return false; //en los otros tipos de validacion no se requiere el nombre
        }
        return Objects.isNull(validacion.getNombre());//en este tipo de validacion es indispensable el nombre
    }

    private boolean validarValorNulo(PeValidacion validacion) {
        if (Objects.equals(validacion.getTipo(), PeTipoValidacion.ACTUALIDAD.getId())) {
            return false; //en este tipo de validacion no se requiere un valor
        }
        return Objects.isNull(validacion.getValor());
    }

    /*
     * Valida que no haya mas de una validacion tipo 3.
     */
    private boolean validarAnidacionAnterioridad(GestionVariablesEspecificasBean bean, List<PeValidacion> validaciones) {
        boolean validacionOk = true;
        List<PeValidacion> validacionesAnterioridad = validaciones.stream()
                .filter(v -> Objects.equals(v.getTipo(), 3))
                .collect(Collectors.toList());

        if (!validacionesAnterioridad.isEmpty()) {
            if (validacionesAnterioridad.size() > 1) {
                validacionOk = false;
                bean.addError("Validaciones: Error de validación: no se permite tener mas de una validacion tipo 3.");
            }
        }
        return validacionOk;
    }

    /*
     * Valida que no haya mas de una validacion tipo 4.
     */
    private boolean validarAnidacionActualidad(GestionVariablesEspecificasBean bean, List<PeValidacion> validaciones) {
        boolean validacionOk = true;
        List<PeValidacion> validacionesAnterioridad = validaciones.stream()
                .filter(v -> Objects.equals(v.getTipo(), 4))
                .collect(Collectors.toList());

        if (!validacionesAnterioridad.isEmpty()) {
            if (validacionesAnterioridad.size() > 1) {
                validacionOk = false;
                bean.addError("Validaciones: Error de validación: no se permite tener mas de una validacion tipo 4.");
            }
        }
        return validacionOk;
    }

    private boolean validarValidacionCorrelacion(GestionVariablesEspecificasBean bean, List<PeValidacion> validaciones, PeValidacion validacion) throws Exception {

        PeCorrelacion peCorrelacion = GsonUtil.deserializar(validacion.getValor().toString(), new TypeToken<PeCorrelacion>() {
        });
        if (Objects.isNull(peCorrelacion) || Objects.isNull(peCorrelacion.getTipo())) {
            bean.addError("Validaciones: Error de validación: existe error en el campo \"valor\", "
                    + "el json debe tener el campo \"tipo\" como requerido. "
                    + "Si el tipo es [0-3] debe tambien contar con el campo \"correlacion\" como requerido. "
                    + "Si el tipo es 4 debe tambien contar con los campos \"match\" y \"combinaciones\" (las llaves no pueden estar vacias)"
                    + " como requeridos. ");
            return false;
        }

        List<PeTipoCorrelacion> tipoCorrelaciones = PeConstantes.getListadoTipoCorrelacion();//validacion de tipos disponibles
        int ultimoId = tipoCorrelaciones.get(tipoCorrelaciones.size() - 1).getId();
        if (peCorrelacion.getTipo() > ultimoId || peCorrelacion.getTipo() < 0) {
            bean.addError("Validaciones: Error de validación: existe error en el campo \"valor\", el campo \"tipo\" debe tener un numero entre [0 - " + ultimoId + "]. ");
            return false;
        }

        if (!peCorrelacion.getTipo().equals(PeTipoCorrelacion.COMBINACION.getId())) {//validaciones para correlaciones 1,2,3
            if (Objects.isNull(peCorrelacion.getCorrelacion())) {
                bean.addError("Validaciones: Error de validación: existe error en el campo \"valor\", para validaciones 1,2,3 el json debe tener la siguiente estructura: { \"correlacion\": \"nombre_variable\", \"tipo\": 0} . (todos los campos son requeridos**)");
                return false;
            }
            String nombreVariable = peCorrelacion.getCorrelacion().trim();//nombre de variable de correlacion
            if (nombreVariable.isBlank()) {
                bean.addError("Validaciones: Error de validación: existe error en el campo \"valor\", el campo \"correlacion\" no puede estar vacío.");
                return false;
            }
            PeVariable variableCorrelacionada = this.getPeVariableRemoto().consultarPorIdProgramaNombre(bean.getVariable().getPePrograma().getId(), nombreVariable);
            if (Objects.isNull(variableCorrelacionada)) {
                bean.addError("Validaciones: Error de validación: existe error en el campo \"valor\", la variable indicada \"correlacion\" no existe en el programa.");
                return false;
            }

            if (!variableCorrelacionada.isActiva()) {
                bean.addError("Validaciones: Error de validación: existe error en el campo \"valor\", la variable indicada \"correlacion\" no puede estar inactiva.");
                return false;
            }

            //validacion de correlacion tipo 0-1
            if (peCorrelacion.getTipo().equals(PeTipoCorrelacion.MAYOR.getId()) || peCorrelacion.getTipo().equals(PeTipoCorrelacion.MENOR.getId())) {
                return this.validacionCorrelacionMayorMenor(bean, variableCorrelacionada);
            }
            //validacion de correlacion tipo 2
            if (peCorrelacion.getTipo().equals(PeTipoCorrelacion.COMODIN_REQUERIDO.getId())) {
                return this.validacionCorrelacionComodinRequerido(bean, validaciones, variableCorrelacionada);
            }
            //validacion de correlacion tipo 3
            return this.validacionCorrelacionIgualdad(bean, peCorrelacion, variableCorrelacionada);
        }
        //validacion de correlacion tipo 4
        return this.validacionCorrelacionCombinacion(bean, peCorrelacion);
    }

    private boolean validacionCorrelacionMayorMenor(GestionVariablesEspecificasBean bean, PeVariable variableCorrelacion) {
        String nombreTipoVariable = PeTipoVariable.getNombreById(bean.getVariable().getTipo());
        if (!Objects.equals(bean.getVariable().getTipo(), variableCorrelacion.getTipo())) {
            bean.addError("Validaciones: Error de validación: existe error en el campo \"valor\", la variable indicada \"correlacion\" debe ser del mismo tipo de la cual se esta asociando (" + nombreTipoVariable + ").");
            return false;
        }
        return true;
    }

    private boolean validacionCorrelacionComodinRequerido(GestionVariablesEspecificasBean bean, List<PeValidacion> validaciones, PeVariable variable) {

        boolean existeValidacionIgualdad = validaciones.stream()
                .anyMatch(v -> Objects.equals(v.getTipo(), 2));

        if (!existeValidacionIgualdad) {
            bean.addError("Validaciones: Error de validación: se debe de tener almenos un comodin para poder establecer la validacion tipo 5 con correlacion tipo 2. ");
            return false;
        }

        if (Objects.isNull(variable.getValidaciones()) || variable.getValidaciones().isEmpty()) {
            bean.addError("Validaciones: Error de validación: se debe de tener almenos un comodin en la variable indicada \"correlacion\" para poder establecer la validacion tipo 5 con correlacion tipo 2. ");
            return false;
        }
        boolean existeOtrasValidaciones;

        existeValidacionIgualdad = variable.getValidaciones().stream()
                .anyMatch(v -> Objects.equals(v.getTipo(), 2));

        existeOtrasValidaciones = variable.getValidaciones().stream()
                .anyMatch(v -> !Objects.equals(v.getTipo(), 2) && !Objects.equals(v.getTipo(), 5));

        if (!existeValidacionIgualdad || !existeOtrasValidaciones) {
            bean.addError("Validaciones: Error de validación: se debe de tener almenos un comodin en la variable indicada \"correlacion\" para poder establecer la validacion tipo 5 con correlacion tipo 2. ");
            return false;
        }

        return true;
    }

    private boolean validacionCorrelacionIgualdad(GestionVariablesEspecificasBean bean, PeCorrelacion peCorrelacion, PeVariable variableCorrelacionada) {
        if (!peCorrelacion.getTipo().equals(PeTipoCorrelacion.IGUALDAD.getId())) { //la validacion a revisar Igualdad de correlacion
            return true;
        }

        /*if (peCorrelacion.getValorVariable() == null || peCorrelacion.getValorVariable().toString().isBlank()) {
            bean.addError("Validaciones: Error de validación: \"valorVariable\" es requerido en la validacion de correlacion tipo 3. ");
            return false;
        }*/
 /*if (peCorrelacion.getValorCorrelacion()== null || peCorrelacion.getValorCorrelacion().toString().isBlank()) {
            bean.addError("Validaciones: Error de validación: \"valorCorrelacion\" es requerido en la validacion de correlacion tipo 3. ");
            return false;
        }*/
        String nombreTipoVariable;
        if (peCorrelacion.getValorVariable() != null) {
            if (!this.validacionValor(bean.getVariable(), peCorrelacion.getValorVariable())) {
                nombreTipoVariable = PeTipoVariable.getNombreById(bean.getVariable().getTipo());
                bean.addError("Validaciones: Error de validación: \"valorVariable\" debe ser igual al tipo de variable (" + nombreTipoVariable + "). ");
                return false;
            }
        }

        if (peCorrelacion.getValorCorrelacion() != null) {
            if (!this.validacionValor(variableCorrelacionada, peCorrelacion.getValorCorrelacion())) {
                nombreTipoVariable = PeTipoVariable.getNombreById(variableCorrelacionada.getTipo());
                bean.addError("Validaciones: Error de validación: \"valorCorrelacion\" debe ser igual al tipo de variable de correlacion (" + nombreTipoVariable + ").");
                return false;
            }
        }

        if (peCorrelacion.getValorVariable() == null && peCorrelacion.getValorCorrelacion() == null) {
            if (!Objects.equals(bean.getVariable().getTipo(), variableCorrelacionada.getTipo())) {
                nombreTipoVariable = PeTipoVariable.getNombreById(variableCorrelacionada.getTipo());
                bean.addError("Validaciones: Error de validación: para aplicar la validacion tipo 3 la variable debe ser del mismo tipo de la variable correlacionada (" + nombreTipoVariable + "). ");
                return false;
            }
        }
        return true;
    }

    /**
     * Valida la variable correlacionada en la validacion tipo 4. valida que el
     * nombre no este vacio, la variable exista, este activa y sea tipo ENTERO.
     *
     * @param bean
     * @param nombreVariableCorrelacionada
     * @param iteracion
     * @return variable correlacionada
     * @throws Exception
     */
    private PeVariable validacionVariableCorrelacionadaCombinacion(GestionVariablesEspecificasBean bean, String nombreVariableCorrelacionada, int iteracion) throws Exception {
        if (nombreVariableCorrelacionada.isBlank()) {
            bean.addError("Validaciones: Error de validación: existe error"
                    + " en el campo \"combinaciones\" la variable en la posicion " + iteracion + " no puede estar vacía. ");
            return null;
        }

        PeVariable variableCorrelacionada = null;
        if (!nombreVariableCorrelacionada.equals(bean.getVariable().getNombre())) {//se revisa si se debe consultar variable o no
            variableCorrelacionada = this.getPeVariableRemoto().consultarPorIdProgramaNombre(bean.getVariable().getPePrograma().getId(), nombreVariableCorrelacionada);
            if (Objects.isNull(variableCorrelacionada)) {
                bean.addError("Validaciones: Error de validación: existe error"
                        + " en el campo \"combinaciones\" la variable " + nombreVariableCorrelacionada + " (" + iteracion + ") no existe en el programa. ");
                return null;
            }
        } else {
            variableCorrelacionada = bean.getVariable();
        }
        if (!variableCorrelacionada.isActiva()) {
            bean.addError("Validaciones: Error de validación: existe error"
                    + " en el campo \"combinaciones\"  la variable " + variableCorrelacionada.getNombre() + " (" + iteracion + ") esta inactiva. ");
            return null;
        }

        if (!variableCorrelacionada.getTipo().equals(PeTipoVariable.ENTERO.getId())) {
            bean.addError("Validaciones: Error de validación: existe error"
                    + " en el campo \"combinaciones\" todas las variables asignadas deben ser tipo ENTERO. "
                    + "La variable " + variableCorrelacionada.getNombre() + " (" + iteracion + ") "
                    + "es tipo " + PeTipoVariable.getNombreById(variableCorrelacionada.getTipo()).toUpperCase() + ". ");
            return null;
        }
        return variableCorrelacionada;
    }

    /**
     * Valida los valores dentro el mapa combinaciones
     *
     * @param bean
     * @param variableCorrelacionada
     * @return
     */
    private boolean validacionValoresCorrelacionadosCombinacion(GestionVariablesEspecificasBean bean, PeVariable variableCorrelacionada, List<Object> valores) {
        String nombreTipoVariable = PeTipoVariable.getNombreById(variableCorrelacionada.getTipo());

        if (valores == null || valores.isEmpty()) {
            bean.addError(
                        "Validaciones: Error de validación: El campo \"combinaciones\" tiene un valor inválido\n"
                        + ", Variable correlacionada: " + variableCorrelacionada.getNombre() + "\n"
                        + ", Error: No puede haber valores vacios. ");
            return false;
        }

        for (Object valor : valores) {
            if (!this.validacionValor(bean.getVariable(), valor)) {
                nombreTipoVariable = PeTipoVariable.getNombreById(bean.getVariable().getTipo());
                bean.addError(
                        "Validaciones: Error de validación: El campo \"combinaciones\" tiene un valor inválido\n"
                        + ", Variable correlacionada: " + variableCorrelacionada.getNombre() + "\n"
                        + ", Valor analizado: " + valor.toString() + "\n"
                        + ", Error: El tipo de dato no coincide. Se esperaba: " + nombreTipoVariable.toUpperCase() + ". ");
                return false;
            }
        }
        return true;
    }

    /**
     * Valida la correlacion tipo 4
     *
     * @param bean
     * @param peCorrelacion
     * @return
     */
    private boolean validacionCorrelacionCombinacion(GestionVariablesEspecificasBean bean, PeCorrelacion peCorrelacion) throws Exception {
        if (Objects.isNull(peCorrelacion.getMatch())
                || Objects.isNull(peCorrelacion.getCombinaciones())) {
            bean.addError("Validaciones: Error de validación: existe error en el campo \"valor\", para la validacion 4 el json debe tener la siguiente estructura: {\n"
                    + "    \"tipo\": 4,\n"
                    + "    \"match\": boolean,	\n"
                    + "    \"combinaciones\": {\n"
                    + "      \"variable_a\": [valor1, ..],\n"
                    + "      \"variable_b\": [valor1, ..],\n"
                    + "      \"variable_c\": [valor1, ..]\n "
                    + "       .........                     "
                    + "    }\n"
                    + "  } . (todos los campos son requeridos**)");
            return false;
        }

        if (peCorrelacion.getCombinaciones().isEmpty() || peCorrelacion.getCombinaciones().size() < 2) {
            bean.addError("Validaciones: Error de validación: el campo \"combinaciones\" debe tener almenos 2 variables para ser valido. ");
            return false;
        }

        //validacion de combinaciones
        int iteracion = 0;
        for (Map.Entry<String, List<Object>> entry : peCorrelacion.getCombinaciones().entrySet()) {
            String nombreVariableCorrelacionada = entry.getKey().trim();
            PeVariable variableCorrelacionada = this.validacionVariableCorrelacionadaCombinacion(bean, nombreVariableCorrelacionada, iteracion);
            if (variableCorrelacionada == null) {
                return false;
            }
            if (!this.validacionValoresCorrelacionadosCombinacion(bean, variableCorrelacionada, entry.getValue())) {
                return false;
            }
            iteracion++;
        }
        return true;
    }

    private List<PeVariable> validacionVariablesInsumoCalculo(PeInsumoCalculo insumoCalculo, GestionVariablesEspecificasBean bean) throws Exception {
        //validacion cantidad de variables
        if (insumoCalculo.getVariables() == null || insumoCalculo.getVariables().isEmpty()) {
            bean.addError("Insumo calculo: error: el campo \"variables\" debe tener almenos una variable asignada. ");
            return null;
        }

        int idPrograma = Objects.nonNull(bean.getPrograma()) ? bean.getPrograma().getId() : bean.getVariable().getPePrograma().getId();
        //validacion existencia de variables
        List<PeVariable> variablesInsumo = new ArrayList<>();
        for (String nombreVariable : insumoCalculo.getVariables()) {
            PeVariable variableConsultada = getPeVariableRemoto().consultarPorIdProgramaNombre(idPrograma, nombreVariable);
            if (variableConsultada == null || !variableConsultada.isActiva() || (variableConsultada.getTipo().equals(PeTipoVariable.TEXTO.getId())
                    || variableConsultada.getTipo().equals(PeTipoVariable.TEXTO_LARGO.getId())
                    || variableConsultada.getTipo().equals(PeTipoVariable.CALCULO.getId()))) {
                bean.addError("Insumo calculo: error: la variable \"" + nombreVariable + "\" debe existir, estar activa y ser tipo FECHA, ENTERO o DECIMAL. ");
                return null;
            }

            boolean insertada = variablesInsumo.stream().anyMatch(v -> v.getId().equals(variableConsultada.getId()));
            if (insertada) {
                bean.addError("Insumo calculo: error: la variable \"" + nombreVariable + "\" no puede estar repetida en el campo \"variables\". ");
                return null;
            }
            variablesInsumo.add(variableConsultada);
        }
        //validaciones por tipo de variable
        long variablesTipoFecha = variablesInsumo.stream()
                .filter(v -> v.getTipo().equals(PeTipoVariable.FECHA.getId()))
                .count();

        if (variablesTipoFecha > 0 && variablesInsumo.size() != variablesTipoFecha) {
            bean.addError("Insumo calculo: error : si se ingresa una variable tipo fecha en el campo \"variables\" las demas deben ser del mismo tipo. ");
            return null;
        }

        return variablesInsumo;
    }

    private boolean validacionEtapasInsumoCalculo(PeInsumoCalculo insumoCalculo, GestionVariablesEspecificasBean bean, List<PeVariable> variablesInsumo) {
        if (insumoCalculo.getEtapas() == null || insumoCalculo.getEtapas().isEmpty()) {
            bean.addError("Insumo calculo: error: el campo \"etapas\" debe tener almenos una etapa asignada. ");
            return false;
        }

        boolean calculoTipoFecha = variablesInsumo.stream().allMatch(v -> v.getTipo().equals(PeTipoVariable.FECHA.getId()));
        int cantidadEtapas = insumoCalculo.getEtapas().size();
        int contadorEtapas = 1;

        for (PeEtapaCalculo etapa : insumoCalculo.getEtapas()) {
            //validacion operandos
            if (etapa.getOperandos() == null || etapa.getOperandos().isEmpty()) {
                bean.addError("Insumo calculo: error: el campo  \"operandos\" es requerido etapa " + contadorEtapas + ". ");
                return false;
            }

            for (String operando : etapa.getOperandos()) {
                boolean existeVariable = insumoCalculo.getVariables().contains(operando);
                if (!existeVariable) {

                    bean.addError("Insumo calculo: error: el operando " + operando + " de la etapa " + contadorEtapas + " no esta declarado en el campo \"variables\". ");
                    return false;
                }
            }
            int cantidadOperandos = etapa.getOperandos().size();
            //validacion operacion
            if (etapa.getOperacion() == null || etapa.getOperacion().isBlank()) {
                bean.addError("Insumo calculo: error: la etapa " + contadorEtapas + " debe contener una \"operacion\". ");
                return false;
            }

            if (!calculoTipoFecha) {
                if (!etapa.getOperacion().equals(PeOperacion.SUMA.getSimbolo()) && !etapa.getOperacion().equals(PeOperacion.RESTA.getSimbolo())
                        && !etapa.getOperacion().equals(PeOperacion.MULTIPLICACION.getSimbolo()) && !etapa.getOperacion().equals(PeOperacion.DIVISION.getSimbolo())
                        && !etapa.getOperacion().matches(PeConstantes.PATRON_OPERACION_POTENCIA)) {
                    bean.addError("Insumo calculo: error: la operacion de la etapa " + contadorEtapas + " no esta permitida, las operaciones validas son +, -, *, / o ^[0-9] . ");
                    return false;
                }
            } else {
                if (!etapa.getOperacion().equals(PeOperacion.RESTA.getSimbolo())) {
                    bean.addError("Insumo calculo: error: la operacion de la etapa " + contadorEtapas + " no esta permitida, la operacion valida para calculos tipo fecha es - . ");
                    return false;
                }
            }
            //validacion operacion y cantidad de operadores
            if (!etapa.getOperacion().matches(PeConstantes.PATRON_OPERACION_POTENCIA) && cantidadOperandos < 2) {
                bean.addError("Insumo calculo: error: en la etapa " + contadorEtapas + " para las operaciones  +, -, * o  / debe haber minimo 2 operadores. ");
                return false;
            }

            //validacion asignacion temporal
            if (contadorEtapas != cantidadEtapas) {
                if (etapa.getAsignacionTemporal() == null || etapa.getAsignacionTemporal().isBlank()) {
                    bean.addError("Insumo calculo: error: el campo \"asignacionTemporal\" en la etapa " + contadorEtapas + " es requerido. ");
                    return false;
                }

                boolean existeVariable = insumoCalculo.getVariables().contains(etapa.getAsignacionTemporal());
                if (!existeVariable) {

                    bean.addError("Insumo calculo: error: la asignacion temporal de la etapa " + contadorEtapas + " no esta declarado en el campo \"variables\". ");
                    return false;
                }
            }
            contadorEtapas++;
        }
        return true;
    }

    private boolean validacionesInsumoCalculo(PeInsumoCalculo insumoCalculo, GestionVariablesEspecificasBean bean) throws Exception {
        List<PeVariable> variablesInsumo = this.validacionVariablesInsumoCalculo(insumoCalculo, bean);
        if (variablesInsumo == null) {
            return false;
        }
        return this.validacionEtapasInsumoCalculo(insumoCalculo, bean, variablesInsumo);

    }

}
