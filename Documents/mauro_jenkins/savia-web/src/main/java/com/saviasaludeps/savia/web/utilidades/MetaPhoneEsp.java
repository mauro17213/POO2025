package com.saviasaludeps.savia.web.utilidades;

public class MetaPhoneEsp {

    public String codificar(String nombres) {
        String metaKey = "";
        //tamaño del código generado
        int keyLength = 256;
        //posición en la cadena
        int currentPos = 0;
        String originalString = nombres + "    ";
        //Reemplazar algunos caracteres del español que generan confusión
        originalString = normalizar(originalString.toLowerCase());
        originalString = originalString.toUpperCase();

        //Iterar cada litra hasta el tamaño del código generado
        while (metaKey.length() < keyLength) {
            if (currentPos > originalString.length() - 1) {
                break;
            }
            char currentChar = originalString.charAt(currentPos);
            //Si empieza por vocal se añade al meta key
            if (empiezaEnVocal(originalString) && currentPos == 0) {
                metaKey += currentChar;
                currentPos++;
            } else {
                //Buscar consonantes con sonidos iguales
                String valores = "DFKMNPTVLJ";
                if (stringAt(originalString, currentPos, 1, valores.toCharArray())) {
                    metaKey += currentChar;
                    //Incrementar por dos si hay una letra repetida
                    if (currentChar == originalString.charAt(currentPos + 1)) {
                        currentPos += 2;
                    } else {
                        currentPos++;
                    }
                } else {
                    //Crear metakey basado en consonantes
                    switch (currentChar) {
                        case 'C':
                    try {
                            //Si hay una C en la siguiente posición suena como X y adelantamos dos posiciones (maccion)
                            if (originalString.charAt(currentPos + 1) == 'C') {
                                metaKey += "X";
                                currentPos += 2;
                                //Si es CE o CI suena como S y adelantamos dos posiciones (caceres)
                            } else if ("CE".equals(originalString.substring(currentPos, currentPos + 2)) || "CI".equals(originalString.substring(currentPos, currentPos + 2))) {
                                metaKey += "S";
                                currentPos += 2;
                                //Si no suena como una K
                            } else {
                                metaKey += "K";
                                currentPos++;
                            }
                        } catch (Exception ex) {
                            System.out.println("Error: " + ex.toString());
                        }
                        break;
                        case 'G':
                            //Si la g la sigue una i o e suena como J
                            if (originalString.substring(currentPos, currentPos + 2).equals("GI") || originalString.substring(currentPos, currentPos + 2).equals("GE")) {
                                metaKey += "J";
                                currentPos += 1;
                                //Si no suena como G
                            } else {
                                metaKey += "G";
                                currentPos += 1;
                            }
                            break;
//                        case 'H':
//                            //Como la h es muda vamos a tomar la siguiente vocal despues de H
//                            /*if (esLaSiguienteVocal(originalString, currentPos + 1)) {
//                                metaKey += originalString.substring(currentPos + 1, currentPos + 2);
//                                currentPos += 1;
//                            } else {*/
////                                metaKey += "H";
////                                currentPos += 1;
//                            //}
//                            break;
                        case 'Y':
                            //Si a la y la sigue una vocal
                            if (esLaSiguienteVocal(originalString, currentPos + 1)) {
                                metaKey += "J";
                                currentPos += 1;
                            } else {
                                metaKey += "Y";
                                currentPos += 1;
                            }
                            break;
                        case 'Q': //Suena como una K si lleva una U se omite
                            if (originalString.substring(currentPos + 1).equals("U")) {
                                currentPos += 2;
                            } else {
                                currentPos += 1;
                            }
                            metaKey += "K";
                            break;
                        case 'W': //Suena como u
                            metaKey += "U";
                            currentPos += 1;
                            break;
                        case 'R': //Suena como r validar si es necesario manejar la doble R que se pronuncia distinto
                            currentPos += 1;
                            metaKey += "R";
                            break;
                        case 'S':
                            if (!esLaSiguienteVocal(originalString, currentPos + 1) && currentPos == 0) {
                                metaKey += "ES";
                                currentPos += 1;
                            } else {
                                currentPos += 1;
                                metaKey += "S";
                            }
                            break;
                        case 'Z': //Suenan como S
                            currentPos += 1;
                            metaKey += "S";
                            break;
                        case 'X':
                            if ((!esLaSiguienteVocal(originalString, currentPos + 1)) && originalString.length() > 1 && currentPos == 0) {
                                metaKey += "EX";
                                currentPos += 1;
                            } else {
                                metaKey += "X";
                                currentPos += 1;
                            }
                            break;
                        default:
                            currentPos += 1;
                            break;
                    }
                }
            }
            metaKey = metaKey.trim();
        }
        return metaKey;
    }

    private boolean esLaSiguienteVocal(String originalString, int i) {
        boolean flag = false;
        if (originalString.substring(i, i + 1).equals("A") || originalString.substring(i, i + 1).equals("E")) {
            flag = true;
        }
        if (originalString.substring(i, i + 1).equals("I") || originalString.substring(i, i + 1).equals("O")) {
            flag = true;
        }
        if (originalString.substring(i, i + 1).equals("U")) {
            flag = true;
        }
        return flag;
    }

    public boolean stringAt(String s, int start, int length, char[] lista) {
        boolean flag = false;
        if ((start < 0) || (start >= s.length())) {
            return false;
        }
        for (char c : lista) {
            try {
                if (c == s.charAt(start)) {
                    return true;
                }
            } catch (Exception ex) {
                System.out.println("Excepción " + ex.toString());
            }
        }
        return flag;
    }

    public boolean empiezaEnVocal(String nombre) {
        boolean flag = false;
        if (nombre.startsWith("A") || nombre.startsWith("E")) {
            flag = true;
        }
        if (nombre.startsWith("I") || nombre.startsWith("O")) {
            flag = true;
        }
        if (nombre.startsWith("U")) {
            flag = true;
        }
        return flag;
    }

    public String normalizar(String s) {
        s = s.replace('á', 'A');
        s = s.replace("ch", "X");
        s = s.replace("ç", "S");
        s = s.replace("é", "E");
        s = s.replace("í", "I");
        s = s.replace("ó", "O");
        s = s.replace("ú", "U");
        s = s.replace("ñ", "N");
        s = s.replace("gü", "W");
        s = s.replace("ü", "U");
        s = s.replace("b", "V");
        s = s.replace("z", "S");
        s = s.replace("ll", "Y");
        return s;
    }
    
    public String normalizarVocales(String s) {
        s = s.replace('á', 'a');
        s = s.replace("é", "e");
        s = s.replace("í", "i");
        s = s.replace("ó", "o");
        s = s.replace("ú", "u");
        s = s.replace('Á', 'A');
        s = s.replace("É", "E");
        s = s.replace("Í", "I");
        s = s.replace("Ó", "O");
        s = s.replace("Ú", "U");
        return s;
    }

}
