package com.bootcamp_2024_1.api_personas.validation;

public class Persona {

    public static String validarYProcesarPersona(String apellido) {
        if (apellido == null || apellido.isEmpty()) {
            return "";
        }
        return apellido;
    }
}
