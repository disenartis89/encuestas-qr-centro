// backend/Usuarios.java
// Módulo de registro de usuarios (profesores y alumnos)
// Responsable inicial: equipo de BACK

package com.centro.encuestas;

import java.util.ArrayList;
import java.util.List;

public class Usuarios {

    private final List<Usuario> usuarios = new ArrayList<>();

    public Usuario registrarAlumno(String nombre, String curso) {
        Usuario alumno = new Usuario("alumno", nombre, curso, null);
        usuarios.add(alumno);
        return alumno;
    }

    public Usuario registrarProfesor(String nombre, String asignatura) {
        Usuario profesor = new Usuario("profesor", nombre, null, asignatura);
        usuarios.add(profesor);
        return profesor;
    }

    public List<Usuario> listarUsuarios() {
        return usuarios;
    }

    public static class Usuario {
        public final String rol;
        public final String nombre;
        public final String curso;
        public final String asignatura;

        public Usuario(String rol, String nombre, String curso, String asignatura) {
            this.rol = rol;
            this.nombre = nombre;
            this.curso = curso;
            this.asignatura = asignatura;
        }
    }
}
