package com.saviasaludeps.savia.web.utilidades;

import java.io.UnsupportedEncodingException;

/**
 * <p>
 * </p>
 */
public class Calcular {

    public static final String conc = "-";

    public static String verificar(String _verifica) {
        if (_verifica == null) {
            return "";
        } else {
            if (_verifica.equals("null")) {
                _verifica = "";
            }
            return _verifica;
        }
    }

    /**
     * Completa cantidades según el tamaño para retornarlo en un String llenando
     * la izquierda con ceros '0'
     *
     * @param _val
     * @param tam
     * @return String: La cadeda transformada.
     */
    public static String cantidad(int _val, int tam) {
        String val = String.valueOf(_val);
        val = val.trim();
        int faltante = tam - val.length();
        for (int i = 0; i < faltante; i++) {
            val = "0" + val;
        }
        return val;
    }

    /**
     * Completa cantidades según el tamaño para retornarlo en un String llenando
     * la derecha con ceros espacios
     *
     * @param val
     * @param tam
     * @return String: La cadeda transformada.
     */
    public static String cantidad(double val, int tam) {
        return cantidad(String.valueOf(val), tam);
    }

    /**
     * Completa cantidades según el tamaño para retornarlo en un String llenando
     * la derecha con espacios
     *
     * @param val
     * @param tam
     * @return String: La cadeda transformada.
     */
    public static String cantidad(String val, int tam) {
        if (val == null) {
            val = "";
        }
        val = val.trim();
        int faltante = tam - val.length();
        for (int i = 0; i < faltante; i++) {
            val = val + " ";
        }
        return val;
    }

    /**
     * Completa cantidades según el tamaño para retornarlo en un String
     *
     * @param val
     * @param tam
     * @param car
     * @param lado
     * @return String: La cdeda transformada.
     */
    public static String cantidad(int val, int tam, char car, char lado) {
        return cantidad(String.valueOf(val), tam, car, lado);
    }

    /**
     * Completa cantidades según el tamaño para retornarlo en un String
     *
     * @param val
     * @param tam
     * @param car
     * @param lado
     * @return String: La cdeda transformada.
     */
    public static String cantidad(double val, int tam, char car, char lado) {
        return cantidad(String.valueOf(val), tam, car, lado);
    }

    /**
     * Completa cantidades según el tamaño para retornarlo en un String
     *
     * @param val valor de entrada
     * @param tam tamaño
     * @param car Caracter
     * @param lado I-->Izquierda D-->Derecha
     * @return String: La cdeda transformada.
     */
    public static String cantidad(String val, int tam, char car, char lado) {
        String total = "";
        if (val == null) {
            val = "";
        }
        int real = val.length();
        for (int i = 0; i < tam; i++) {
            if (lado == 'I') {
                if (real > i) {
                    total = total + val.substring(i, i + 1);
                } else {
                    total = total + car;
                }
            } else if (real > i) {
                total = val.substring(real - i - 1, real - i) + total;
            } else {
                total = car + total;
            }
        }
        return total;
    }

    public static byte[] cantidadBytes(String val, int tam) throws UnsupportedEncodingException {
        if (val == null) {
            val = "";
        }
        val = val.trim();
        int faltante = tam - val.length();
        for (int i = 0; i < faltante; i++) {
            val = val + " ";
        }
        return val.getBytes("ASCII");
    }

    /**
     * Descomprime cadenas de BCD
     *
     * @param _cad
     * @param _dec
     * @return byte[] decomprimido
     */
    public static byte[] descomprimeCadena(byte[] _cad, int _dec) {
        int largo = _cad.length;
        byte[] res = new byte[_dec];
        int i, j;
        for (i = 0, j = 0; i < largo; i++) {
            int c1 = _cad[i];
            if (c1 < 0) {
                c1 += 256;
            }
            res[j] = (byte) ((c1 / 16) + 0x30);
            j++;
            res[j] = (byte) ((c1 & 0x0F) + 0x30);
            j++;
        }
        return res;
    }

    public static byte[] comprimeCadena(String _cad, int tam) throws UnsupportedEncodingException {
        return comprimeCadena(_cad.getBytes("UTF-8"), tam);
    }

    public static byte[] comprimeCadena(byte[] _cad, int tam) throws UnsupportedEncodingException {
        _cad = cantidad(_cad, tam * 2);
        int largo = _cad.length / 2;
        byte[] res = new byte[largo];
        char c1, c2;
        for (int x = 0; x < largo; x++) {
            c1 = (char) _cad[x * 2];
            c2 = (char) _cad[(x * 2) + 1];
            if ((c1 >= '0' && c1 <= '9') || (c1 >= 'A' && c1 <= 'F')) {
                if (c1 >= 'A') {
                    c1 -= 55;
                } else {
                    if (c1 >= '0') {
                        c1 -= 48;
                    }
                }
            } else {
                c1 = 0;
            }
            if ((c2 >= '0' && c2 <= '9') || (c2 >= 'A' && c2 <= 'F')) {
                if (c2 >= 'A') {
                    c2 -= 55;
                } else {
                    if (c2 >= '0') {
                        c2 -= 48;
                    }
                }
            } else {
                c2 = 0;
            }
            res[x] = intToPseudoUnsignedByte(c1 * 16 + c2);
//            byte val = intToPseudoUnsignedByte(128);
//            res[x]=(byte)(c1 * 16 + c2);
        }
        return cantidad(res, tam);
    }

    private static byte intToPseudoUnsignedByte(int n) {
        if (n < 128) {
            return (byte) n;
        }
        return (byte) (n - 256);
    }

    /**
     * Return a new byte array containing a sub-portion of the source array
     *
     * @param source
     * @param srcBegin The beginning index (inclusive)
     * @return The new, populated byte array
     */
    public static byte[] subBytes(byte[] source, int srcBegin) {
        return subBytes(source, srcBegin, source.length);
    }

    /**
     * Return a new byte array containing a sub-portion of the source array
     *
     * @param source
     * @param srcBegin The beginning index (inclusive)
     * @param srcEnd The ending index (exclusive)
     * @return The new, populated byte array
     */
    public static byte[] subBytes(byte[] source, int srcBegin, int srcEnd) {
        byte destination[];
        destination = new byte[srcEnd - srcBegin];
        getBytes(source, srcBegin, srcEnd, destination, 0);
        return destination;
    }

    public static String subBytesString(byte[] source, int srcBegin, int srcEnd) throws UnsupportedEncodingException {
        byte destination[] = subBytes(source, srcBegin, srcEnd);
        return new String(destination, "UTF8");
    }

    public static int subBytesInt(byte[] source, int srcBegin, int srcEnd) throws UnsupportedEncodingException {
        return Integer.parseInt(subBytesString(source, srcBegin, srcEnd));
    }

    public static double subBytesDouble(byte[] source, int srcBegin, int srcEnd) throws UnsupportedEncodingException {
        return Double.parseDouble(subBytesString(source, srcBegin, srcEnd));
    }

    public static String subBytesCompString(byte[] source, int srcBegin, int srcEnd) {
        byte destination[] = subBytes(source, srcBegin, srcEnd);
        return convertirStringComp(destination);
    }

    public static int subBytesCompInt(byte[] source, int srcBegin, int srcEnd) throws UnsupportedEncodingException {
        return Integer.parseInt(subBytesCompString(source, srcBegin, srcEnd));
    }

    public static double subBytesCompDouble(byte[] source, int srcBegin, int srcEnd) throws UnsupportedEncodingException {
        return Double.parseDouble(subBytesCompString(source, srcBegin, srcEnd));
    }

    public static String subComoBytesString(byte[] source, int srcBegin, int srcEnd) throws UnsupportedEncodingException {
        byte[] _a = subBytes(source, srcBegin, srcEnd);
        int val = 0;
        if (_a[0] < 0) {
            val = _a[0] + 256;
        } else {
            val = _a[0];
        }
        return String.valueOf(val);
    }

    public static int subComoBytesInt(byte[] source, int srcBegin, int srcEnd) throws UnsupportedEncodingException {
        return Integer.parseInt(subComoBytesString(source, srcBegin, srcEnd));
    }

    /**
     * Copia de bytes desde un array a un array de destino
     *
     * @param source El array origen
     * @param srcBegin Índice de la primera fuente de bytes a copiar
     * @param srcEnd Índice después de la última fuente de bytes a copiar
     * @param destination El array destino
     * @param dstBegin El desplazamiento inicial de la matriz de destino
     */
    public static void getBytes(byte[] source, int srcBegin, int srcEnd, byte[] destination, int dstBegin) {
        System.arraycopy(source, srcBegin, destination, dstBegin, srcEnd - srcBegin);
    }

    public static byte[] concatenarBytes(byte[] a, byte[] b) {
        byte[] c = new byte[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    public static byte[] concatenarBytes(byte[] a, byte b) {
        byte[] bb = new byte[1];
        bb[0] = b;
        return concatenarBytes(a, bb);
    }

    public static byte[] convertirBytes(double _val, int tam) throws UnsupportedEncodingException {
        return convertirBytes(String.valueOf(_val), tam);
    }

    public static byte[] convertirBytes(int _val, int tam) throws UnsupportedEncodingException {
        return convertirBytes(String.valueOf(_val), tam);
    }

    public static byte[] convertirBytes(char _val, int tam) throws UnsupportedEncodingException {
        return cantidad(String.valueOf(_val).getBytes("UTF-8"), tam);
    }

    public static byte[] convertirBytes(String _val, int tam) throws UnsupportedEncodingException {
        if (_val == null) {
            _val = "";
        }
        return cantidad(_val.getBytes("UTF-8"), tam);
    }

    public static byte convertirComoBytes(int _val) throws UnsupportedEncodingException {
        byte aa = (byte) _val;
        return aa;
    }

    public static byte[] convertirBytesComp(int _val, int tam) {
        char[] Temp2 = new char[10];
        int j, i;
        int Modulo, Result;
        Result = _val;
        i = 0;
        while (Result > 0) {
            Modulo = Result % 16;
            Result = Result / 16;
            if (Modulo == 10L) {
                Temp2[i] = 'A';
            } else if (Modulo == 11L) {
                Temp2[i] = 'B';
            } else if (Modulo == 12L) {
                Temp2[i] = 'C';
            } else if (Modulo == 13L) {
                Temp2[i] = 'D';
            } else if (Modulo == 14L) {
                Temp2[i] = 'E';
            } else if (Modulo == 15L) {
                Temp2[i] = 'F';
            } else {
                Temp2[i] = (char) (Modulo + 0x30);
            }
            i++;
        }
        int tam2 = tam * 2;
        char[] Temp1 = new char[tam2];
        for (j = tam2 - 1; j >= 0; j--) {
            if (i > 0) {
                Temp1[j] = Temp2[i - 1];
                i--;
            } else {
                Temp1[j] = '0';
            }
        }
        return Calcular.hexToBytes(Temp1);
    }

    public static String convertirStringComp(byte[] _val) {
        if (_val.length == 2) {
            int _aa = _val[0];
            int _bb = _val[1];
            if (_aa < 0) {
                _aa += 256;
            }
            if (_bb < 0) {
                _bb += 256;
            }
            return String.valueOf((_aa * 256) + _bb);
        } else {
            return "";
        }
    }

    public static String convertirString(byte[] _val) throws UnsupportedEncodingException {
        return new String(_val, "UTF-8");
    }

    public static int convertirInt(byte[] _val) throws UnsupportedEncodingException {
        return Integer.parseInt(convertirString(_val));
    }

    public static double convertirDouble(byte[] _val) throws UnsupportedEncodingException {
        return Double.parseDouble(convertirString(_val));
    }

    public static byte convertirBytes(int _val) throws UnsupportedEncodingException {
        byte aa = (byte) (_val + 48);
        return aa;
    }

    public static byte[] cantidad(byte[] val, int tam) throws UnsupportedEncodingException {
        byte[] resp = new byte[tam];
        if (val.length > tam) {
            resp = subBytes(val, 0, tam);
        } else if (val.length < tam) {
            byte[] aa = new byte[tam - val.length];
            for (int i = 0; i < aa.length; i++) {
                aa[i] = convertirBytes(0);
            }
            resp = concatenarBytes(aa, val);
        } else {
            resp = val;
        }
        return resp;
    }

    private static final char[] kDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static byte[] hexToBytes(char[] hex) {
        int length = hex.length / 2;
        byte[] raw = new byte[length];
        for (int i = 0; i < length; i++) {
            int high = Character.digit(hex[i * 2], 16);
            int low = Character.digit(hex[i * 2 + 1], 16);
            int value = (high << 4) | low;
            if (value > 127) {
                value -= 256;
            }
            raw[i] = (byte) value;
        }
        return raw;
    }

    /**
     * COnvierte 1 número exadecimal en byte
     *
     * @param hex
     * @return
     */
    public static byte hexToBytes(String hex) {
        int high = Character.digit(hex.charAt(0), 16);
        int low = Character.digit(hex.charAt(1), 16);
        int value = (high << 4) | low;
        if (value > 127) {
            value -= 256;
        }
        return (byte) value;
    }

    /**
     * Convertir cadena 
     * @param val convertir caden de 8 caracteres en 1 byte
     * @return 
     */
    public static byte convertirByte(String val) {
        val = cantidad(val, 8, '0', 'I');
        byte param = 0;
        for (int i = 0; i < 8; i++) {
            param = (byte) (param << 1);
            if (val.charAt(i)=='1') {
                param = (byte) (param | 0x01);
            }
        }
        return param;
    }
}
