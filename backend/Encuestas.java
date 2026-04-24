// backend/Encuestas.java
// Módulo de encuestas (TODO: por terminar)
// Responsable: equipo de BACK

package com.centro.encuestas;

import java.util.ArrayList;
import java.util.List;

public class Encuestas {

    private final List<Encuesta> encuestas = new ArrayList<>();

    public Encuesta crearEncuesta(String titulo, List<String> preguntas) {
        // Pendiente: validar que el título no esté vacío y que haya al menos una pregunta
        Encuesta encuesta = new Encuesta(titulo, preguntas);
        encuestas.add(encuesta);
        return encuesta;
    }

    // TODO: método para registrar respuestas
    // TODO: método para generar estadísticas

    public static class Encuesta {
        public final String titulo;
        public final List<String> preguntas;
        public final List<String> respuestas = new ArrayList<>();

        public Encuesta(String titulo, List<String> preguntas) {
            this.titulo = titulo;
            this.preguntas = preguntas;
        }
    }
}
