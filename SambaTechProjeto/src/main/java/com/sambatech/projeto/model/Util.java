package com.sambatech.projeto.model;

import java.util.Date;

/***
 * 
 * @author Talita
 *
 */
public class Util {
    
	/***
     * Constroi nome do arquivo para armazenar no bucket
     * @param multiPart: Arquivo
     * @return nome do arquivo
     */
    public static String GenerateFileName(String name) {
        return new Date().getTime() + "-" + name.replace(" ", "_");
    }
}
