package com.saviasaludeps.savia.web.mipres.servicio;

import java.math.BigDecimal;

/**
 *
 * @author LFRIVERA
 */
public class ParseUtils {

    public static BigDecimal parseBigDecimal(String value) {
        if (value != null) {
            String sanitizedValue = value.trim().replaceAll("[^\\d.]", "");
            if (!sanitizedValue.isEmpty() && sanitizedValue.matches("\\d+(\\.\\d+)?")) {
                try {
                    return new BigDecimal(sanitizedValue);
                } catch (NumberFormatException e) {
                }
            }
        }
        return BigDecimal.ZERO; // Valor por defecto si hay una excepción o valor inválido
    }

    public static short parseShort(String value) {
        if (value != null) {
            String sanitizedValue = value.trim().replaceAll("[^\\d]", "");
            if (!sanitizedValue.isEmpty()) {
                try {
                    int intValue = Integer.parseInt(sanitizedValue);
                    if (intValue >= Short.MIN_VALUE && intValue <= Short.MAX_VALUE) {
                        return (short) intValue;
                    }
                } catch (NumberFormatException e) {
                }
            }
        }
        return (short) 0; // Valor por defecto si hay una excepción o valor inválido
    }
}
