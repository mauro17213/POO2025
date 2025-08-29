/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.especial.utilidades;

import com.saviasaludeps.savia.dominio.administracion.GnAlerta;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.especial.PeTipoCorrelacion;
import com.saviasaludeps.savia.dominio.especial.PeTipoValidacion;
import com.saviasaludeps.savia.dominio.especial.PeTipoVariable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Stiven Giraldo
 */
public class PeConstantes {

    //NUMEROS
    public static final int CERO = 0;
    public static final int UNO = 1;
    public static final int DOS = 2;
    public static final int TRES = 3;
    public static final int CUATRO = 4;
    public static final int CINCO = 5;

    //TEXTOS
    public final static String TEXTO_VACIO = "";
    public final static String PUNTO = ".";
    public final static String GUION_BAJO = "_";
    public final static String ESPACIO = " ";
    public final static String SALTO_LINEA = "\n";
    public final static String TEXTO_SI = "Si";
    public final static String TEXTO_NO = "No";
    public final static String LLAVE_ABRE = "{";
    public final static String LLAVE_CIERRA = "}";
    public final static String COMILLA_DOBLE = "\"";
    public final static String DOS_PUNTOS = ":";
    public final static String COMA = ",";
    public final static String PIPE = "|";

    //Formato de fecha orden
    public final static SimpleDateFormat formato1 = new SimpleDateFormat("dd/MM/yyyy");
    public final static SimpleDateFormat formato2 = new SimpleDateFormat("yyyy-MM-dd");
    public final static SimpleDateFormat formato3 = new SimpleDateFormat("dd/MM/yy");
    public final static SimpleDateFormat formato4 = new SimpleDateFormat("YYYYMMddHHmmssSSS");
    public final static SimpleDateFormat formato5 = new SimpleDateFormat("dd/MM/yyyy");
    public final static SimpleDateFormat formato6 = new SimpleDateFormat("YYYYMMddHHmmss");
    public final static SimpleDateFormat formato7 = new SimpleDateFormat("YYYYMMdd");

    //Estados carga masiva 0 -> En cola | 1 -> En Proceso | 2 -> Procesado | 3 - > Cancelado | 4 -> Fallido
    public final static int ESTADO_EN_COLA = 0;
    public final static int ESTADO_EN_PROCESO = 1;
    public final static int ESTADO_PROCESADO = 2;
    public final static int ESTADO_CANCELADO = 3;
    public final static int ESTADO_FALLIDO = 4;
    public final static String ESTADO_EN_COLA_STR = "En Cola";
    public final static String ESTADO_EN_PROCESO_STR = "En Proceso";
    public final static String ESTADO_PROCESADO_STR = "Procesado";
    public final static String ESTADO_CANCELADO_STR = "Cancelado";
    public final static String ESTADO_FALLIDO_STR = "Fallido";

    // Tipos de cargas
    public final static int CARGA_MASIVA_AFILIADOS = 0;
    public final static int CARGA_MASIVA_TECNOLOGIAS = 1;
    public final static int CARGA_MASIVA_DIAGNOSTICO = 2;
    public final static int CARGA_MASIVA_GESTIONES = 2;

    //Fuentes de ogigen
    public final static int ORIGEN_MANUAL = 1;
    public final static int ORIGEN_CARGA_MASIVA = 2;
    public final static int ORIGEN_ANEXO3 = 3;
    public final static int ORIGEN_AFILIACION = 4;
    public final static int ORIGEN_SUGERIDO = 5;
    public final static int ORIGEN_HOSPITALIZACION = 6;
    public final static String ORIGEN_MANUAL_DESCRIPCION = "Manual";
    public final static String ORIGEN_CARGA_MASIVA_DESCRIPCION = "Carga Masiva";
    public final static String ORIGEN_ANEXO3_DESCRIPCION = "Anexo 3";
    public final static String ORIGEN_AFILIACION_DESCRIPCION = "Afiliación";
    public final static String ORIGEN_AFILIACION_SUGERIDO = "Sugerido";
    public final static String ORIGEN_HOSPITALIZACION_STR = "Hospitalización";

    //Tipo contacto
    public static final int PE_TELEFONO_TIPO_FIJO = 1;
    public static final int PE_TELEFONO_TIPO_MOVIL = 2;
    public static final int PE_TELEFONO_TIPO_OFICINA = 3;

    //Estados programas
    public static final int PE_PROGRAMA_INACTIVO = 0;
    public static final int PE_PROGRAMA_ACTIVO = 1;
    public static final Integer PE_PROGRAMA_ESTADO_NULL = null;

    //Estado direccionados
    public static final int PE_DIRECCIONADO_PENDIENTE = 1;
    public static final int PE_DIRECCIONADO_EN_GESTION = 2;
    public static final int PE_DIRECCIONADO_GESTIONADO = 3;
    public static final int PE_DIRECCIONADO_NO_DIRECCIONADO = 4;
    public static final int PE_DIRECCIONADO_ANULADO = 5;
    public static final String PE_DIRECCIONADO_PENDIENTE_DESCRIPCION = "PENDIENTE";
    public static final String PE_DIRECCIONADO_EN_GESTION_DESCRIPCION = "EN GESTION";
    public static final String PE_DIRECCIONADO_GESTIONADO_DESCRIPCION = "GESTIONADO";
    public static final String PE_DIRECCIONADO_NO_DIRECCIONADO_DESCRIPCION = "NO DIRECCIONADO";
    public static final String PE_DIRECCIONADO_ANULADO_DESCRIPCION = "ANULADO";
    //Estado item direccionado
    public static final int PE_DIRECCIONADO_ITEM_PENDIENTE = 1;
    public static final int PE_DIRECCIONADO_ITEM_GESTIONADO = 2;
    public static final int PE_DIRECCIONADO_ITEM_RECHAZADO = 3;
    public static final String PE_DIRECCIONADO_ITEM_PENDIENTE_DESCRIPCION = "Pendiente";
    public static final String PE_DIRECCIONADO_ITEM_GESTIONADO_DESCRIPCION = "Gestionado";
    public static final String PE_DIRECCIONADO_ITEM_RECHAZADO_DESCRIPCION = "Rechazado";
    //Estados afiliados sugeridos 
    public static final String PE_SUGERIDO_PENDIENTE = "Pendiente";
    public static final String PE_SUGERIDO_MARCADO = "Marcado";
    public static final String PE_SUGERIDO_RECHAZADO = "Rechazado";
    public static final String PE_SUGERIDO_MATRICULADO = "Matriculado";
    public static final int PE_ESTADO_SUGERIDO_PENDIENTE = 1;
    public static final int PE_ESTADO_SUGERIDO_MARCADO = 2;
    public static final int PE_ESTADO_SUGERIDO_RECHAZADO = 3;
    public static final int PE_ESTADO_SUGERIDO_MATRICULADO = 4;
    //Tipos registros afiliados 0 --> N/A | 1 --> Sugerido | 2 --> Automatico
    public static final int PE_REGISTRO_NA = 0;
    public static final int PE_REGISTRO_SUGERIDO = 1;
    public static final int PE_REGISTRO_AUTOMATICO = 2;
    public static final String PE_REGISTRO_NA_STR = "N/A";
    public static final String PE_REGISTRO_SUGERIDO_STR = "Sugerido";
    public static final String PE_REGISTRO_AUTOMATICO_STR = "Automático";
    //Fuente origen sugeridos 0 - Hospitalizacion | 3 - Anexo3 | 9 - Anexo9
    //20/02/2023 ibohorquez | Fuente origen sugeridos 0 - Hospitalizacion | 1 - Anexo3 | 2 - Anexo9 | 3 - Manual sugerido por gonzalo
    //19/02/2023 idbhoquez se cambia fuente origen a 0 - Hospitalizacion | 3 - Anexo3 | 9 - Anexo9, aprobado por Gonzalo.
    public static final int PE_SUGERIDO_ORIGEN_HOSPITALIZACION = 0;
    public static final int PE_SUGERIDO_ORIGEN_ANEXO3 = 3;
    public static final int PE_SUGERIDO_ORIGEN_ANEXO9 = 9;
    public static final int PE_SUGERIDO_ORIGEN_MANUAL = 1;
    public static final int PE_SUGERIDO_ORIGEN_MIPRES = 2;
    public static final String PE_SUGERIDO_ORIGEN_HOSPITALIZACION_STR = "Hospitalización";
    public static final String PE_SUGERIDO_ORIGEN_ANEXO3_STR = "Anexo 3";
    public static final String PE_SUGERIDO_ORIGEN_ANEXO9_STR = "Anexo 9";
    public static final String PE_SUGERIDO_ORIGEN_MANUAL_STR = "Manual";
    public static final String PE_SUGERIDO_ORIGEN_MIPRES_STR = "Mipres";
    //Nombre alerta rechazo sugerido
    public static final String NOMBRE_ALERTA_RECHAZO_SUGERIDO = "Rechazo";
    //Tipo paciente en afilido programa 0 - Nuevo |  1 - Prevalente
    public static final int PE_TIPO_PACIENTE_NUEVO = 0;
    public static final int PE_TIPO_PACIENTE_PREVALENTE = 1;
    public static final String PE_TIPO_PACIENTE_NUEVO_STR = "Nuevo";
    public static final String PE_TIPO_PACIENTE_PREVALENTE_STR = "Prevalente";
    //Sexo aplica programas
    public static final int APLICA_SEXO_FEMENINO = 0;
    public static final int APLICA_SEXO_MASCULINO = 1;
    public static final int APLICA_SEXO_AMBOS = 2;
    public static final String APLICA_SEXO_FEMENINO_STR = "Femenino";
    public static final String APLICA_SEXO_MASCULINO_STR = "Masculino";
    public static final String APLICA_SEXO_AMBOS_STR = "Ambos";
    //Sexo aplica diagnosticos    
    public static final int SEXO_APLICA_MASCULINO = 0;
    public static final int SEXO_APLICA_FEMENINO = 1;
    public static final int SEXO_APLICA_AMBOS = 2;
    //Codigos generos
    public static final String APLICA_SEXO_FEMENINO_CODIGO = "F";
    public static final String APLICA_SEXO_MASCULINO_CODIGO = "M";
    //Cantidad registros en programas
    public static final int UN_REGISTRO = 0;
    public static final int VARIOS_REGISTRO = 1;
    public static final String UN_REGISTRO_STR = "Un registro";
    public static final String VARIOS_REGISTRO_STR = "Varios registros";
    //Carga masiva gestion
    public static final int FUENTE_ORIGEN_MANUAL = 1;
    public static final int FUENTE_ORIGEN_CARGA = 2;
    public static final String FUENTE_ORIGEN_MANUAL_STR = "Manual";
    public static final String FUENTE_ORIGEN_CARGA_STR = "Carga masiva";
    //Tipos tecnologia - 1 Tecnologia (CUP) - 2 Medicamento (CUM) - 3 Insumo - 4 Paquete
    public static final int TIPO_TECNOLOGIA_TECNOLOGIA = 1;
    public static final int TIPO_TECNOLOGIA_MEDICAMENTO = 2;
    public static final int TIPO_TECNOLOGIA_INSUMO = 3;
    public static final int TIPO_TECNOLOGIA_PAQUETE = 4;
    public static final String TIPO_TECNOLOGIA_TECNOLOGIA_STR = "Tecnologia";
    public static final String TIPO_TECNOLOGIA_MEDICAMENTO_STR = "Medicamento";
    public static final String TIPO_TECNOLOGIA_INSUMO_STR = "Insumo";
    public static final String TIPO_TECNOLOGIA_PAQUETE_STR = "Paquete";
    //Acciones carga masiva tecnologias
    public static final int ACCION_CREAR = 0;
    public static final int ACCION_MODIFICAR = 1;
    public static final int ACCION_BORRAR = 2;
    //Tipo cargas programas especiales
    //0 -> Carga Afiliados |  1 -> Carga Tecnologías | 2 -> Carga Disgnósticos
    public static final int TIPO_CARGA_AFILIADO = 0;
    public static final int TIPO_CARGA_TECNOLOGIA = 1;
    public static final int TIPO_CARGA_DIAGNOSTICO = 2;
    public static final String TIPO_CARGA_AFILIADO_STR = "Carga Afiliados";
    public static final String TIPO_CARGA_TECNOLOGIA_STR = "Carga Tecnologías";
    public static final String TIPO_CARGA_DIAGNOSTICO_STR = "Carga Disgnósticos";
    
    public static final String STR_PROGRAMA = "programa";
    //Estado sivigila 0-Caso Probable 1- Confirmado 2- Descartado
    public static final int ESTADO_CASO_PROBABLE = 0;
    public static final int ESTADO_CASO_CONFIRMADO = 1;
    public static final int ESTADO_CASO_DESCARTADO = 2;
    public static final String ESTADO_CASO_PROBABLE_STR = "Caso Probable";
    public static final String ESTADO_CASO_CONFIRMADO_STR = "Confirmado";
    public static final String ESTADO_CASO_DESCARTADO_STR = "Descartado";
    //Notificacion sivigila 0-Si | 1- No | 2- No Aplica
    public static final int NOTIFICACION_SIVIGILA_SI = 0;
    public static final int NOTIFICACION_SIVIGILA_NO = 1;
    public static final int NOTIFICACION_SIVIGILA_NOAPLICA = 2;
    public static final String NOTIFICACION_SIVIGILA_SI_STR = "Si";
    public static final String NOTIFICACION_SIVIGILA_NO_STR = "No";
    public static final String NOTIFICACION_SIVIGILA_NOAPLICA_STR = "No Aplica";
    //Constantes para nombre de archivo cargas masivas afiliados
    public static final String NOMBRE_ARCHIVO_CARGA_MASIVA="carga_programa_especial_";
    public static final String NOMBRE_ARCHIVO_RESPUESTA_CARGA_MASIVA="carga_programa_especial_resp_";
    //Constantes para nombre de archivo cargas masivas gestiones
    public static final String NOMBRE_ARCHIVO_CARGA_GESTIONES="carga_gestiones_";
    public static final String NOMBRE_ARCHIVO_RESPUESTA_CARGA_GESTIONES="carga_gestiones_resp_";
    //Constantes para nombre de archivo cargas masivas tecnologia
    public static final String NOMBRE_ARCHIVO_CARGA_TECNOLOGIA="carga_tecnologia_";
    public static final String NOMBRE_ARCHIVO_RESPUESTA_CARGA_TECNOLOGIA="carga_tecnologia_resp_";
    //Constantes para nombre de archivo cargas masivas diagnostico
    public static final String NOMBRE_ARCHIVO_CARGA_DIAGNOSTICO="carga_diagnostico_";
    public static final String NOMBRE_ARCHIVO_RESPUESTA_CARGA_DIAGNOSTICO="carga_diagnostico_resp_";
    //Constantes encabezado cargas masivas
    public static final String ENCABEZADO_CARGA_AFILIADOS = "consecutivo,actualizar,tipo_documento,numero_documento,primer_apellido,segundo_apellido,primer_nombre,segundo_nombre,fecha_nacimiento,numero_telefono,numero_celular,dx_principal,dx_secundarios,fecha_diagnostico,region_corporal,medio_diagnostico,estado_programa,causa_estado,fecha_ingreso,fecha_egreso,codigo_hibilitacion_ips,adherente,disentimiento,notificado,estado_sivigila,causa_descarte,estado_diagnostico,planificacion_familiar";
    public static final String ENCABEZADO_CARGA_GESTIONES = "consecutivo|tipo_doc|numero_documento|codigo_programa|tipo_gestion|observacion";
    public static final String ENCABEZADO_CARGA_TECNOLOGIA = "tipo_tecnologia,codigo_tecnologia,marca_automatica,direcciona,accion";
    public static final String ENCABEZADO_CARGA_DIAGNOSTICO = "codigo_diagnostico,marca_automatica,direcciona,aplica_rescate,accion";
    public static final String ENCABEZADO_LINEA_CONTROL_CARGA_VARIABLES = "cod_prestador|fecha_inicio|fecha_fin|cantidad_registros";
    public static final String ENCABEZADO_CARGA_VARIABLES = "consecutivo|tipo_documento|documento|numeros_telefono";//despues siguen los nombres de las variables
    //Estado diagnostico 0- Sospechoso | 1- Confirmado | 2- Descartado
    public static final int ESTADO_DIAGNOSTICO_SOSPECHOSO = 0;
    public static final int ESTADO_DIAGNOSTICO_CONFIRMADO = 1;
    public static final int ESTADO_DIAGNOSTICO_DESCARTADO = 2;
    public static final String ESTADO_DIAGNOSTICO_SOSPECHOSO_STR = "Sospechoso";
    public static final String ESTADO_DIAGNOSTICO_CONFIRMADO_STR = "Confirmado";
    public static final String ESTADO_DIAGNOSTICO_DESCARTADO_STR = "Descartado";
    //variables
    public static final String PATRON_NOMBRE_VARIABLE = "^[a-z]+(_[a-z]+)*$";
    //carga masiva variables
    public static final int ID_PROGRAMA_RIESGO_CARDIOVASCULAR = 46;
    public static final String FORMATO_FECHA = "\\d{4}-\\d{2}-\\d{2}";
    //constantes para nombre de archivo cargas masivas afiliados
    public static final String NOMBRE_ARCHIVO_CARGA_MASIVA_VARIABLE="carga_variable_especifica_";
    public static final String NOMBRE_ARCHIVO_RESPUESTA_CARGA_MASIVA_VARIABLE="carga_variable_especifica_resp_";
    //constantes de calculo
    public static final String PATRON_OPERACION_POTENCIA = "^\\^([0-9]\\d*)?$";
    
    public static String obtenerNombreFuenteOrigen(int id) {
        switch (id) {
            case ORIGEN_MANUAL:
                return ORIGEN_MANUAL_DESCRIPCION;
            case ORIGEN_CARGA_MASIVA:
                return ORIGEN_CARGA_MASIVA_DESCRIPCION;
            case ORIGEN_ANEXO3:
                return ORIGEN_ANEXO3_DESCRIPCION;
            case ORIGEN_AFILIACION:
                return ORIGEN_AFILIACION_DESCRIPCION;
            case ORIGEN_SUGERIDO:
                return ORIGEN_AFILIACION_SUGERIDO;
            case ORIGEN_HOSPITALIZACION:
                return ORIGEN_HOSPITALIZACION_STR;
            default:
                return TEXTO_VACIO;
        }
    }

    public static List<Maestro> obtenerTiposOrigen() {
        List<Maestro> lista = new ArrayList();
        int contador = 1;
        while (contador < 7) {
            Maestro maestro = new Maestro();
            maestro.setId(contador);
            switch (contador) {
                case ORIGEN_MANUAL:
                    maestro.setNombre(ORIGEN_MANUAL_DESCRIPCION);
                    maestro.setDescripcion(ORIGEN_MANUAL_DESCRIPCION);
                    lista.add(maestro);
                    break;
                case ORIGEN_CARGA_MASIVA:
                    maestro.setNombre(ORIGEN_CARGA_MASIVA_DESCRIPCION);
                    maestro.setDescripcion(ORIGEN_CARGA_MASIVA_DESCRIPCION);
                    lista.add(maestro);
                    break;

                case ORIGEN_ANEXO3:
                    maestro.setNombre(ORIGEN_ANEXO3_DESCRIPCION);
                    maestro.setDescripcion(ORIGEN_ANEXO3_DESCRIPCION);
                    lista.add(maestro);
                    break;
                case ORIGEN_AFILIACION:
                    maestro.setNombre(ORIGEN_AFILIACION_DESCRIPCION);
                    maestro.setDescripcion(ORIGEN_AFILIACION_DESCRIPCION);
                    lista.add(maestro);
                    break;
                case ORIGEN_SUGERIDO:
                    maestro.setNombre(ORIGEN_AFILIACION_SUGERIDO);
                    maestro.setDescripcion(ORIGEN_AFILIACION_SUGERIDO);
                    lista.add(maestro);
                    break;
                case ORIGEN_HOSPITALIZACION:
                    maestro.setNombre(ORIGEN_HOSPITALIZACION_STR);
                    maestro.setDescripcion(ORIGEN_HOSPITALIZACION_STR);
                    lista.add(maestro);
                    break;
            }
            contador++;
        }
        return lista;
    }

    public static List<String> getNumerosFijosNoPermitidos() {
        List<String> valoresNoPermitidos = new ArrayList<>();
        valoresNoPermitidos.add("0000000");
        valoresNoPermitidos.add("1111111");
        valoresNoPermitidos.add("2222222");
        valoresNoPermitidos.add("3333333");
        valoresNoPermitidos.add("4444444");
        valoresNoPermitidos.add("5555555");
        valoresNoPermitidos.add("6666666");
        valoresNoPermitidos.add("7777777");
        valoresNoPermitidos.add("8888888");
        valoresNoPermitidos.add("9999999");
        valoresNoPermitidos.add("0000000000");
        valoresNoPermitidos.add("1111111111");
        valoresNoPermitidos.add("2222222222");
        valoresNoPermitidos.add("3333333333");
        valoresNoPermitidos.add("4444444444");
        valoresNoPermitidos.add("5555555555");
        valoresNoPermitidos.add("6666666666");
        valoresNoPermitidos.add("7777777777");
        valoresNoPermitidos.add("8888888888");
        valoresNoPermitidos.add("9999999999");
        return valoresNoPermitidos;
    }

    public static List<Maestro> listaSino() {
        List<Maestro> lista = new ArrayList();
        int contador = 0;
        while (contador < 2) {
            Maestro maestro = new Maestro();
            maestro.setId(contador);
            switch (contador) {
                case CERO:
                    maestro.setNombre(TEXTO_NO);
                    maestro.setDescripcion(TEXTO_NO);
                    lista.add(maestro);
                    break;
                case UNO:
                    maestro.setNombre(TEXTO_SI);
                    maestro.setDescripcion(TEXTO_SI);
                    lista.add(maestro);
                    break;
            }
            contador++;
        }
        return lista;
    }

    public static String obtenerEstadoDireccionado(int id) {
        switch (id) {
            case PE_DIRECCIONADO_PENDIENTE:
                return PE_DIRECCIONADO_PENDIENTE_DESCRIPCION;
            case PE_DIRECCIONADO_EN_GESTION:
                return PE_DIRECCIONADO_EN_GESTION_DESCRIPCION;
            case PE_DIRECCIONADO_GESTIONADO:
                return PE_DIRECCIONADO_GESTIONADO_DESCRIPCION;
            case PE_DIRECCIONADO_NO_DIRECCIONADO:
                return PE_DIRECCIONADO_NO_DIRECCIONADO_DESCRIPCION;
            case PE_DIRECCIONADO_ANULADO:
                return PE_DIRECCIONADO_ANULADO_DESCRIPCION;
            default:
                return TEXTO_VACIO;
        }
    }

    public static List<Maestro> listaEstadoDireccionado() {
        List<Maestro> lista = new ArrayList();
        int contador = 1;
        while (contador < 6) {
            Maestro maestro = new Maestro();
            maestro.setId(contador);
            switch (contador) {
                case PE_DIRECCIONADO_PENDIENTE:
                    maestro.setNombre(PE_DIRECCIONADO_PENDIENTE_DESCRIPCION);
                    maestro.setDescripcion(PE_DIRECCIONADO_PENDIENTE_DESCRIPCION);
                    lista.add(maestro);
                    break;
                case PE_DIRECCIONADO_EN_GESTION:
                    maestro.setNombre(PE_DIRECCIONADO_EN_GESTION_DESCRIPCION);
                    maestro.setDescripcion(PE_DIRECCIONADO_EN_GESTION_DESCRIPCION);
                    lista.add(maestro);
                    break;
                case PE_DIRECCIONADO_GESTIONADO:
                    maestro.setNombre(PE_DIRECCIONADO_GESTIONADO_DESCRIPCION);
                    maestro.setDescripcion(PE_DIRECCIONADO_GESTIONADO_DESCRIPCION);
                    lista.add(maestro);
                    break;
                case PE_DIRECCIONADO_NO_DIRECCIONADO:
                    maestro.setNombre(PE_DIRECCIONADO_NO_DIRECCIONADO_DESCRIPCION);
                    maestro.setDescripcion(PE_DIRECCIONADO_NO_DIRECCIONADO_DESCRIPCION);
                    lista.add(maestro);
                    break;
                case PE_DIRECCIONADO_ANULADO:
                    maestro.setNombre(PE_DIRECCIONADO_ANULADO_DESCRIPCION);
                    maestro.setDescripcion(PE_DIRECCIONADO_ANULADO_DESCRIPCION);
                    lista.add(maestro);
                    break;
            }
            contador++;
        }
        return lista;
    }

    public static HashMap<Integer, Maestro> obtenerHashMaestro(List<Maestro> listaMaestros) {
        HashMap<Integer, Maestro> hash = new HashMap();
        listaMaestros.forEach(maestro -> {
            hash.put(maestro.getId(), maestro);
        });
        return hash;
    }

    public static HashMap<Integer, Ubicacion> obtenerHashUbicacion(List<Ubicacion> listaUbicaciones) {
        HashMap<Integer, Ubicacion> hash = new HashMap();
        listaUbicaciones.forEach(ubicacion -> {
            hash.put(ubicacion.getId(), ubicacion);
        });
        return hash;
    }

    public static String obtenerNombreEstadoItemDireccionado(int id) {
        switch (id) {
            case PE_DIRECCIONADO_ITEM_PENDIENTE:
                return PE_DIRECCIONADO_ITEM_PENDIENTE_DESCRIPCION;
            case PE_DIRECCIONADO_ITEM_GESTIONADO:
                return PE_DIRECCIONADO_ITEM_GESTIONADO_DESCRIPCION;
            case PE_DIRECCIONADO_ITEM_RECHAZADO:
                return PE_DIRECCIONADO_ITEM_RECHAZADO_DESCRIPCION;
            default:
                return TEXTO_VACIO;
        }
    }

    public static String getEstadoSugerido(Integer id) {
        if(id == null){
            return TEXTO_VACIO;
        }
        switch (id) {
            case UNO:
                return PE_SUGERIDO_PENDIENTE;
            case DOS:
                return PE_SUGERIDO_MARCADO;
            case TRES:
                return PE_SUGERIDO_RECHAZADO;
            case CUATRO:
                return PE_SUGERIDO_MATRICULADO;
            default:
                return TEXTO_VACIO;
        }
    }

    public static List<Maestro> listaEstadoSugeridos() {
        List<Maestro> lista = new ArrayList();
        int contador = 1;
        while (contador < 5) {
            Maestro maestro = new Maestro();
            maestro.setId(contador);
            switch (contador) {
                case PE_ESTADO_SUGERIDO_PENDIENTE:
                    maestro.setNombre(PE_SUGERIDO_PENDIENTE);
                    maestro.setDescripcion(PE_SUGERIDO_PENDIENTE);
                    lista.add(maestro);
                    break;
                case PE_ESTADO_SUGERIDO_MARCADO:
                    maestro.setNombre(PE_SUGERIDO_MARCADO);
                    maestro.setDescripcion(PE_SUGERIDO_MARCADO);
                    lista.add(maestro);
                    break;
                case PE_ESTADO_SUGERIDO_RECHAZADO:
                    maestro.setNombre(PE_SUGERIDO_RECHAZADO);
                    maestro.setDescripcion(PE_SUGERIDO_RECHAZADO);
                    lista.add(maestro);
                    break;
                case PE_ESTADO_SUGERIDO_MATRICULADO:
                    maestro.setNombre(PE_SUGERIDO_MATRICULADO);
                    maestro.setDescripcion(PE_SUGERIDO_MATRICULADO);
                    lista.add(maestro);
                    break;
            }
            contador++;
        }
        return lista;
    }

    public static List<Maestro> listaRegistrosAfiliados() {
        List<Maestro> lista = new ArrayList();
        int contador = 0;
        while (contador < 3) {
            Maestro maestro = new Maestro();
            maestro.setId(contador);
            switch (contador) {
                case PE_REGISTRO_NA:
                    maestro.setNombre(PE_REGISTRO_NA_STR);
                    maestro.setDescripcion(PE_REGISTRO_NA_STR);
                    lista.add(maestro);
                    break;
                case PE_REGISTRO_SUGERIDO:
                    maestro.setNombre(PE_REGISTRO_SUGERIDO_STR);
                    maestro.setDescripcion(PE_REGISTRO_SUGERIDO_STR);
                    lista.add(maestro);
                    break;
                case PE_REGISTRO_AUTOMATICO:
                    maestro.setNombre(PE_REGISTRO_AUTOMATICO_STR);
                    maestro.setDescripcion(PE_REGISTRO_AUTOMATICO_STR);
                    lista.add(maestro);
                    break;
            }
            contador++;
        }
        return lista;
    }

    public static String getRegistrosAfiliados(int id) {
        switch (id) {
            case PE_REGISTRO_NA:
                return PE_REGISTRO_NA_STR;
            case PE_REGISTRO_SUGERIDO:
                return PE_REGISTRO_SUGERIDO_STR;
            case PE_REGISTRO_AUTOMATICO:
                return PE_REGISTRO_AUTOMATICO_STR;
            default:
                return TEXTO_VACIO;
        }
    }

    public static List<Maestro> listaFuenteOrigenSugeridos() {
        List<Maestro> lista = new ArrayList();
        int contador = 0;
        while (contador < 10) {
            Maestro maestro = new Maestro();
            maestro.setId(contador);
            switch (contador) {
                case PE_SUGERIDO_ORIGEN_HOSPITALIZACION:
                    maestro.setNombre(PE_SUGERIDO_ORIGEN_HOSPITALIZACION_STR);
                    maestro.setDescripcion(PE_SUGERIDO_ORIGEN_HOSPITALIZACION_STR);
                    lista.add(maestro);
                    break;
                case PE_SUGERIDO_ORIGEN_ANEXO3:
                    maestro.setNombre(PE_SUGERIDO_ORIGEN_ANEXO3_STR);
                    maestro.setDescripcion(PE_SUGERIDO_ORIGEN_ANEXO3_STR);
                    lista.add(maestro);
                    break;
                case PE_SUGERIDO_ORIGEN_ANEXO9:
                    maestro.setNombre(PE_SUGERIDO_ORIGEN_ANEXO9_STR);
                    maestro.setDescripcion(PE_SUGERIDO_ORIGEN_ANEXO9_STR);
                    lista.add(maestro);
                    break;
                case PE_SUGERIDO_ORIGEN_MANUAL:
                    maestro.setNombre(PE_SUGERIDO_ORIGEN_MANUAL_STR);
                    maestro.setDescripcion(PE_SUGERIDO_ORIGEN_MANUAL_STR);
                    lista.add(maestro);
                    break;
                case PE_SUGERIDO_ORIGEN_MIPRES:
                    maestro.setNombre(PE_SUGERIDO_ORIGEN_MIPRES_STR);
                    maestro.setDescripcion(PE_SUGERIDO_ORIGEN_MIPRES_STR);
                    lista.add(maestro);
                    break;
            }
            contador++;
        }
        return lista;
    }

    public static String getNombreFuenteOrigenSugeridos(Integer id) {
        if(id == null){
            return TEXTO_VACIO;
        }
        switch (id) {
            case PE_SUGERIDO_ORIGEN_HOSPITALIZACION:
                return PE_SUGERIDO_ORIGEN_HOSPITALIZACION_STR;
            case PE_SUGERIDO_ORIGEN_ANEXO3:
                return PE_SUGERIDO_ORIGEN_ANEXO3_STR;
            case PE_SUGERIDO_ORIGEN_ANEXO9:
                return PE_SUGERIDO_ORIGEN_ANEXO9_STR;
            case PE_SUGERIDO_ORIGEN_MIPRES:
                return PE_SUGERIDO_ORIGEN_MIPRES_STR;
            default:
                return TEXTO_VACIO;
        }
    }
    
    public static GnAlerta alertaRechazoSugerido(Usuario usuario, AsegAfiliado afiliado, String observacion, String programa, Integer idHospitalizacion, Date fecha){
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm");
        GnAlerta alerta = new GnAlerta();
        alerta.setGnUsuarioId(usuario);
        alerta.setSeveridad(1);
        alerta.setEstado(1);
        alerta.setNombre(NOMBRE_ALERTA_RECHAZO_SUGERIDO);
        alerta.setDescripcion("Se realizó rechazo de un registro en sugeridos para el afiliado " + afiliado.getMaeTipoDocumentoCodigo() + " " + afiliado.getNumeroDocumento() + " - " + afiliado.getNombres() + " " + afiliado.getApellidos() +", en el programa "+ programa+", fecha de rechazo  "+ sdf.format(fecha)+", por el motivo: " + observacion+ ", con hospitalización No " + idHospitalizacion);
        return  alerta;
    }

    public static String getTipoAfiliado(Integer id) {
        if(id == null){
            return TEXTO_VACIO;
        }
        switch (id) {
            case PE_TIPO_PACIENTE_NUEVO:
                return PE_TIPO_PACIENTE_NUEVO_STR;
            case PE_TIPO_PACIENTE_PREVALENTE:
                return PE_TIPO_PACIENTE_PREVALENTE_STR;
            default:
                return TEXTO_VACIO;
        }
    }
    
    public static List<Maestro> getListaSexoAplica() {
        List<Maestro> lista = new ArrayList();
        int contador = 0;
        while (contador < 3) {
            Maestro maestro = new Maestro();
            maestro.setId(contador);
            switch (contador) {
                case APLICA_SEXO_FEMENINO:
                    maestro.setNombre(APLICA_SEXO_FEMENINO_STR);
                    maestro.setDescripcion(APLICA_SEXO_FEMENINO_STR);
                    lista.add(maestro);
                    break;
                case APLICA_SEXO_MASCULINO:
                    maestro.setNombre(APLICA_SEXO_MASCULINO_STR);
                    maestro.setDescripcion(APLICA_SEXO_MASCULINO_STR);
                    lista.add(maestro);
                    break;
                case APLICA_SEXO_AMBOS:
                    maestro.setNombre(APLICA_SEXO_AMBOS_STR);
                    maestro.setDescripcion(APLICA_SEXO_AMBOS_STR);
                    lista.add(maestro);
                    break;
            }
            contador++;
        }
        return lista;
    }
    
    public static String getListaSexoAplicaDescripcion(Integer id) {
        if(id==null){
            return TEXTO_VACIO;
        }
        switch (id) {
            case APLICA_SEXO_FEMENINO:
                return APLICA_SEXO_FEMENINO_STR;
            case APLICA_SEXO_MASCULINO:
                return APLICA_SEXO_MASCULINO_STR;
            case APLICA_SEXO_AMBOS:
                return APLICA_SEXO_AMBOS_STR;            
            default:
                return TEXTO_VACIO;
        }
    }
    
    public static String getCodigoSexoAplica(Integer id) {
        if(id==null){
            return TEXTO_VACIO;
        }
        switch (id) {
            case APLICA_SEXO_FEMENINO:
                return APLICA_SEXO_FEMENINO_CODIGO;
            case APLICA_SEXO_MASCULINO:
                return APLICA_SEXO_MASCULINO_CODIGO;          
            default:
                return TEXTO_VACIO;
        }
    }
    
    public static String getCodigoSexoAplicaDiagnostico(Integer id) {
        if(id==null){
            return TEXTO_VACIO;
        }
        switch (id) {
            case SEXO_APLICA_MASCULINO:
                return APLICA_SEXO_MASCULINO_CODIGO;
            case SEXO_APLICA_FEMENINO:
                return APLICA_SEXO_FEMENINO_CODIGO;          
            default:
                return TEXTO_VACIO;
        }
    }
    
    /**
     * Funcion encargada de armar lista auxiliar para cantidad de registros en
     * programas especiales
     *
     * @author idbohorquez
     * @fechaCreacion 21/06/2023
     * @return List<Maestro>
     */
    public static List<Maestro> getListaCantidadRegistros() {
        List<Maestro> lista = new ArrayList();
        int contador = 0;
        while (contador < 2) {
            Maestro maestro = new Maestro();
            maestro.setId(contador);
            switch (contador) {
                case UN_REGISTRO:
                    maestro.setNombre(UN_REGISTRO_STR);
                    maestro.setDescripcion(UN_REGISTRO_STR);
                    lista.add(maestro);
                    break;
                case VARIOS_REGISTRO:
                    maestro.setNombre(VARIOS_REGISTRO_STR);
                    maestro.setDescripcion(VARIOS_REGISTRO_STR);
                    lista.add(maestro);
                    break;
            }
            contador++;
        }
        return lista;
    }
    
    public static String getCantidadRegistrosStr(Integer id) {
        if(id==null){
            return TEXTO_VACIO;
        }
        switch (id) {
            case UN_REGISTRO:
                return UN_REGISTRO_STR;
            case VARIOS_REGISTRO:
                return VARIOS_REGISTRO_STR;         
            default:
                return TEXTO_VACIO;
        }
    }
    
    public static String getFuenteOrigenGestionStr(Integer id) {
        if(id==null){
            return TEXTO_VACIO;
        }
        switch (id) {
            case FUENTE_ORIGEN_MANUAL:
                return FUENTE_ORIGEN_MANUAL_STR;
            case FUENTE_ORIGEN_CARGA:
                return FUENTE_ORIGEN_CARGA_STR;         
            default:
                return TEXTO_VACIO;
        }
    }
    
    public static String getEstadoCarga(Integer tipo) {
        if(tipo == null){
            return TEXTO_VACIO;
        }
        switch (tipo) {
            case ESTADO_EN_PROCESO:
                return ESTADO_EN_PROCESO_STR;
            case ESTADO_PROCESADO:
                return ESTADO_PROCESADO_STR;
            case ESTADO_CANCELADO:
                return ESTADO_CANCELADO_STR;
            default:
                return TEXTO_VACIO;
        }
    }
    
    public static String obtenerTipoTecnologia(Integer id) {
        if(id == null){
            return TEXTO_VACIO;
        }
        switch (id) {
            case UNO:
                return TIPO_TECNOLOGIA_TECNOLOGIA_STR;
            case DOS:
                return TIPO_TECNOLOGIA_MEDICAMENTO_STR;
            case TRES:
                return TIPO_TECNOLOGIA_INSUMO_STR;
            case CUATRO:
                return TIPO_TECNOLOGIA_PAQUETE_STR;
            default:
                return TEXTO_VACIO;
        }
    }
    
    public static List<Maestro> obtenerTipoTecnologia() {
        List<Maestro> lista = new ArrayList();
        int contador = 1;
        while (contador <= 4) {
            Maestro mae = new Maestro(contador);
            switch (contador) {
                case UNO:
                    mae.setNombre(TIPO_TECNOLOGIA_TECNOLOGIA_STR);
                    mae.setDescripcion(TIPO_TECNOLOGIA_TECNOLOGIA_STR);
                    lista.add(mae);
                    break;
                case DOS:
                    mae.setNombre(TIPO_TECNOLOGIA_MEDICAMENTO_STR);
                    mae.setDescripcion(TIPO_TECNOLOGIA_MEDICAMENTO_STR);
                    lista.add(mae);
                    break;
                case TRES:
                    mae.setNombre(TIPO_TECNOLOGIA_INSUMO_STR);
                    mae.setDescripcion(TIPO_TECNOLOGIA_INSUMO_STR);
                    lista.add(mae);
                    break;
                case CUATRO:
                    mae.setNombre(TIPO_TECNOLOGIA_PAQUETE_STR);
                    mae.setDescripcion(TIPO_TECNOLOGIA_PAQUETE_STR);
                    lista.add(mae);
                    break;                

            }
            contador++;
        }
        return lista;
    }
        
    public static String obtenerTipoCarga(Integer id) {
        if(id == null){
            return TEXTO_VACIO;
        }
        switch (id) {
            case TIPO_CARGA_AFILIADO:
                return TIPO_CARGA_AFILIADO_STR;
            case TIPO_CARGA_TECNOLOGIA:
                return TIPO_CARGA_TECNOLOGIA_STR;
            case TIPO_CARGA_DIAGNOSTICO:
                return TIPO_CARGA_DIAGNOSTICO_STR;
            default:
                return TEXTO_VACIO;
        }
    }
    
    public static List<Maestro> obtenerMaestroTipoCarga() {
        List<Maestro> lista = new ArrayList();
        int contador = 1;
        while (contador <= 2) {
            Maestro mae = new Maestro(contador);
            switch (contador) {
                case TIPO_CARGA_TECNOLOGIA:
                    mae.setNombre(TIPO_CARGA_TECNOLOGIA_STR);
                    mae.setDescripcion(TIPO_CARGA_TECNOLOGIA_STR);
                    lista.add(mae);
                    break;
                case TIPO_CARGA_DIAGNOSTICO:
                    mae.setNombre(TIPO_CARGA_DIAGNOSTICO_STR);
                    mae.setDescripcion(TIPO_CARGA_DIAGNOSTICO_STR);
                    lista.add(mae);
                    break;              

            }
            contador++;
        }
        return lista;
    }

    public static List<Maestro> listaSivigilaAll() {
        List<Maestro> lista = new ArrayList();
        int contador = 0;
        while (contador <= 2) {
            Maestro mae = new Maestro(contador);
            switch (contador) {
                case ESTADO_CASO_PROBABLE:
                    mae.setNombre(ESTADO_CASO_PROBABLE_STR);
                    mae.setDescripcion(ESTADO_CASO_PROBABLE_STR);
                    lista.add(mae);
                    break;
                case ESTADO_CASO_CONFIRMADO:
                    mae.setNombre(ESTADO_CASO_CONFIRMADO_STR);
                    mae.setDescripcion(ESTADO_CASO_CONFIRMADO_STR);
                    lista.add(mae);
                    break; 
                case ESTADO_CASO_DESCARTADO:
                    mae.setNombre(ESTADO_CASO_DESCARTADO_STR);
                    mae.setDescripcion(ESTADO_CASO_DESCARTADO_STR);
                    lista.add(mae);
                    break; 

            }
            contador++;
        }
        return lista;
    }
    
    public static String obtenerEstadoSivigila(Integer id) {
        if(id == null){
            return TEXTO_VACIO;
        }
        switch (id) {
            case ESTADO_CASO_PROBABLE:
                return ESTADO_CASO_PROBABLE_STR;
            case ESTADO_CASO_CONFIRMADO:
                return ESTADO_CASO_CONFIRMADO_STR;
            case ESTADO_CASO_DESCARTADO:
                return ESTADO_CASO_DESCARTADO_STR;
            default:
                return TEXTO_VACIO;
        }
    }
    
    public static List<Maestro> listaEstadoDiagnostico() {
        List<Maestro> lista = new ArrayList();
        int contador = 0;
        while (contador < 3) {
            Maestro mae = new Maestro(contador);
            switch (contador) {
                case ESTADO_DIAGNOSTICO_SOSPECHOSO:
                    mae.setNombre(ESTADO_DIAGNOSTICO_SOSPECHOSO_STR);
                    mae.setDescripcion(ESTADO_DIAGNOSTICO_SOSPECHOSO_STR);
                    lista.add(mae);
                    break;
                case ESTADO_DIAGNOSTICO_CONFIRMADO:
                    mae.setNombre(ESTADO_DIAGNOSTICO_CONFIRMADO_STR);
                    mae.setDescripcion(ESTADO_DIAGNOSTICO_CONFIRMADO_STR);
                    lista.add(mae);
                    break;  
                case ESTADO_DIAGNOSTICO_DESCARTADO:
                    mae.setNombre(ESTADO_DIAGNOSTICO_DESCARTADO_STR);
                    mae.setDescripcion(ESTADO_DIAGNOSTICO_DESCARTADO_STR);
                    lista.add(mae);
                    break;              

            }
            contador++;
        }
        return lista;
    }
    
    public static String estadoDiagnosticoStr(int id) {
        switch (id) {
            case ESTADO_DIAGNOSTICO_SOSPECHOSO:
                return ESTADO_DIAGNOSTICO_SOSPECHOSO_STR;
            case ESTADO_DIAGNOSTICO_CONFIRMADO:
                return ESTADO_DIAGNOSTICO_CONFIRMADO_STR;
            case ESTADO_DIAGNOSTICO_DESCARTADO:
                return ESTADO_DIAGNOSTICO_DESCARTADO_STR;
            default:
                return TEXTO_VACIO;
        }
    }
    
    public static List<Maestro> listaNotficacionSivigila() {
        List<Maestro> lista = new ArrayList();
        int contador = 0;
        while (contador < 3) {
            Maestro mae = new Maestro(contador);
            switch (contador) {
                case NOTIFICACION_SIVIGILA_SI:
                    mae.setNombre(NOTIFICACION_SIVIGILA_SI_STR);
                    mae.setDescripcion(NOTIFICACION_SIVIGILA_SI_STR);
                    lista.add(mae);
                    break;
                case NOTIFICACION_SIVIGILA_NO:
                    mae.setNombre(NOTIFICACION_SIVIGILA_NO_STR);
                    mae.setDescripcion(NOTIFICACION_SIVIGILA_NO_STR);
                    lista.add(mae);
                    break;  
                case NOTIFICACION_SIVIGILA_NOAPLICA:
                    mae.setNombre(NOTIFICACION_SIVIGILA_NOAPLICA_STR);
                    mae.setDescripcion(NOTIFICACION_SIVIGILA_NOAPLICA_STR);
                    lista.add(mae);
                    break;              

            }
            contador++;
        }
        return lista;
    }

    public static String obtenerNotificacionSivigila(Integer id) {
        if(id == null){
            return TEXTO_VACIO;
        }
        switch (id) {
            case NOTIFICACION_SIVIGILA_SI:
                return NOTIFICACION_SIVIGILA_SI_STR;
            case NOTIFICACION_SIVIGILA_NO:
                return NOTIFICACION_SIVIGILA_NO_STR;
            case NOTIFICACION_SIVIGILA_NOAPLICA:
                return NOTIFICACION_SIVIGILA_NOAPLICA_STR;
            default:
                return TEXTO_VACIO;
        }
    }
    
    public static List<Maestro> listaEstadoCargaMasiva() {
        List<Maestro> lista = new ArrayList();
        int contador = 0;
        while (contador < 5) {
            Maestro mae = new Maestro(contador);
            switch (contador) {
                case ESTADO_EN_COLA:
                    mae.setNombre(ESTADO_EN_COLA_STR);
                    mae.setDescripcion(ESTADO_EN_COLA_STR);
                    lista.add(mae);
                    break;
                case ESTADO_EN_PROCESO:
                    mae.setNombre(ESTADO_EN_PROCESO_STR);
                    mae.setDescripcion(ESTADO_EN_PROCESO_STR);
                    lista.add(mae);
                    break;  
                case ESTADO_PROCESADO:
                    mae.setNombre(ESTADO_PROCESADO_STR);
                    mae.setDescripcion(ESTADO_PROCESADO_STR);
                    lista.add(mae);
                    break;              
                case ESTADO_CANCELADO:
                    mae.setNombre(ESTADO_CANCELADO_STR);
                    mae.setDescripcion(ESTADO_CANCELADO_STR);
                    lista.add(mae);
                    break;              
                case ESTADO_FALLIDO:
                    mae.setNombre(ESTADO_FALLIDO_STR);
                    mae.setDescripcion(ESTADO_FALLIDO_STR);
                    lista.add(mae);
                    break;              

            }
            contador++;
        }
        return lista;
    }
    
    public static String obtenerEstadoCargaMasivaStr(Integer id) {
        if(id == null){
            return TEXTO_VACIO;
        }
        switch (id) {
            case ESTADO_EN_COLA:
                return ESTADO_EN_COLA_STR;
            case ESTADO_EN_PROCESO:
                return ESTADO_EN_PROCESO_STR;
            case ESTADO_PROCESADO:
                return ESTADO_PROCESADO_STR;
            case ESTADO_CANCELADO:
                return ESTADO_CANCELADO_STR;
            case ESTADO_FALLIDO:
                return ESTADO_FALLIDO_STR;
            default:
                return TEXTO_VACIO;
        }
    }
    
    public static List<PeTipoVariable> getListadoTipoVariables(){
        return Arrays.asList(PeTipoVariable.values());
    }
    
    public static List<PeTipoValidacion> getListadoTipoValidaciones(){
        return Arrays.asList(PeTipoValidacion.values());
    }
    
    public static List<Maestro> listaMeses() {
        List<Maestro> lista = new ArrayList();
        int contador = 1;
        while (contador < 13) {
            Maestro mae = new Maestro(contador);
            Month mes = Month.of(contador);
            mae.setNombre(mes.getDisplayName(TextStyle.FULL, new Locale("es", "CO")).toUpperCase());
            lista.add(mae);
            contador++;
        }
        return lista;
    }
    public static List<Maestro> listaPeriodoCargaMasivaVariable() {
        List<Maestro> lista = new ArrayList();
        LocalDate fecha = LocalDate.now().minusMonths(1);
        Month mes = fecha.getMonth();
        String nombreMesFormateado = mes.getDisplayName(TextStyle.FULL, new Locale("es", "CO"));
        Maestro mae = new Maestro(fecha.getMonthValue());
        mae.setNombre(nombreMesFormateado.toUpperCase());
        lista.add(mae);
        return lista;
    }
    
    public static List<Maestro> listaDosUltimosPeriodosCargaMasivaVariable() {
    List<Maestro> lista = new ArrayList<>();
    Locale locale = new Locale("es", "CO");
    LocalDate fecha = LocalDate.now();

    // Calcular los dos últimos meses anteriores
    for (int i = 2; i >= 1; i--) {
        LocalDate fechaAnterior = fecha.minusMonths(i);
        Month mes = fechaAnterior.getMonth();
        String nombreMesFormateado = mes.getDisplayName(TextStyle.FULL, locale);

        Maestro mae = new Maestro(fechaAnterior.getMonthValue());
        mae.setNombre(nombreMesFormateado.toUpperCase());

        lista.add(mae);
    }

    return lista;
}

    
    public static List<PeTipoCorrelacion> getListadoTipoCorrelacion(){
        return Arrays.asList(PeTipoCorrelacion.values());
    }
}
