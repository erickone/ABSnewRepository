/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.support;

/**
 * Clase de utileria para generar un folio consutivo crea el siguiente secuencia
 * de dato puede ser numerico o alfanumerico
 *
 * @author Israel Ruiz
 */
public class UtilGenerateFolio {


    /**
     * Generar el siguiente folio dado el ultimo folio y un patron
     *  el patrón deberá de tener "n" para los numeros o "a" para las o "-" como separador
     *  ejemplo de patrones "nnnn", "aaaaa", "aaa-nnn", "nnnn-aa-nn" 
     *  Ejemplo  si mandas un "0" u el patron es  "aa-nnn-nn" -> AA-000-01 
     *  El resultado del folio (longitud)
     * será en base al patrón indicado
     *
     * @param lastFolio folio que se evalúa para obtener el siguiente valor
     * @param pattern patron con que se deberá de generar el folio 
     * @return  siguiente dato de la secuencia
     */

    public static String generateNextFolio(String lastFolio, String pattern) {
        if (lastFolio == null || lastFolio.trim().length() == 0) {
            lastFolio = "0";
        }

        if (pattern == null || pattern.trim().length() == 0  ) {

            throw new IllegalArgumentException("debe de "
                    + "indicar un patron de folio, \"aann\" ");
        }
        StringBuilder strFolio = new StringBuilder(lastFolio);
        StringBuilder strPattern = new StringBuilder(pattern);

        char[] chFolio = strFolio.reverse().toString().toCharArray();
        char[] patern = strPattern.reverse().toString().toCharArray();
        //El folio se genera en base el patron y el tamaño indicado en él
        char[] finalFolio = new char[patern.length];
        boolean haveAcarreo = false;
        boolean nextValue = true;


        int indx = 0;
        for (int i = 0; i < chFolio.length
                && i < patern.length && nextValue; i++, indx++) {
            //Revisa el patron si requiere datos numericos
            if (patern[i] == 'n') {
                if (!Character.isDigit(chFolio[i])) {
                    if (haveAcarreo) {
                        finalFolio[i] = '1';
                    } else {
                        finalFolio[i] = '0';
                    }
                } else if (Character.isDigit(chFolio[i])) {
                    //Es digito el dato del Folio
                    int data = Integer.parseInt("" + chFolio[i]);
                    data++;

                    if (data >= 10) {
                        haveAcarreo = true;
                        data = data % 10;

                    } else {
                        haveAcarreo = false;
                    }
                    finalFolio[i] = ("" + data).charAt(0);
                }
                //Para el caso que requiera Letras
            } else if (patern[i] == 'a') {
                if (!Character.isAlphabetic(chFolio[i])) {
                    if (haveAcarreo) {
                        finalFolio[i] = 'B';
                    } else {
                        finalFolio[i] = 'A';
                    }
                } else if (Character.isAlphabetic(chFolio[i])) {
                    char dataChar = chFolio[i];
                    dataChar++;

                    if (dataChar > 'Z') {
                        finalFolio[i] = 'A';
                        haveAcarreo = true;
                    } else {
                        haveAcarreo = false;
                        finalFolio[i] = dataChar;
                    }

                }

            } else {
                finalFolio[i] = patern[i];
            }
            nextValue = haveAcarreo;
        }
        //Coloca los datos restantes en base el patron que se coloca
        for (int i = indx; i < chFolio.length
                && i < patern.length; i++, indx++) {
            if (patern[i] == 'n') {
                if (haveAcarreo) {
                    finalFolio[i] = '1';
                    haveAcarreo = false;
                } else {
                    if (Character.isDigit(chFolio[i])) {
                        finalFolio[i] = chFolio[i];
                    } else {
                        finalFolio[i] = '0';
                    }
                }
            } else if (patern[i] == 'a') {
                if (haveAcarreo) {
                    finalFolio[i] = 'A';
                    haveAcarreo = false;
                } else {
                    if (Character.isAlphabetic(chFolio[i])) {
                        finalFolio[i] = chFolio[i];
                    } else {
                        finalFolio[i] = 'A';
                    }
                }
            } else {
                finalFolio[i] = patern[i];
            }
        }

        for (int i = indx; i < finalFolio.length; i++) {
            if (patern[i] == 'n') {
                if (haveAcarreo) {
                    finalFolio[i] = '1';
                    haveAcarreo = false;
                } else {
                    finalFolio[i] = '0';
                }
            } else if (patern[i] == 'a') {
                finalFolio[i] = 'A';
            } else {
                finalFolio[i] = patern[i];
            }

        }
        StringBuilder result = new StringBuilder(new String(finalFolio));

        return result.reverse().toString();
    }
//    public static void main(String arg[]) {
//        String srt = generateNextFolio("ABE12203", "nnn-nn");
//        System.out.println(srt);
//    }
}
