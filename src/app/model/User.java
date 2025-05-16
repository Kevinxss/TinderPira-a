package app.model;

import java.net.URI;
import java.nio.file.Paths;

public class User {
    private String nombreUsuario;
    private String apellido;
    private String edad;
    private String genero;
    private String cumpleanos;
    private String correo;
    private String contrasena;
    private String fotoPerfil;

    // Constructor
    public User(String nombreUsuario, String apellido, String edad, String genero, String cumpleanos, String correo, String contrasena, String fotoPerfil) {
        this.nombreUsuario = nombreUsuario;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.cumpleanos = cumpleanos;
        this.correo = correo;
        this.contrasena = contrasena;
        this.fotoPerfil = fotoPerfil;
    }

    // Getters y setters
    public String getnombreUsuario() {
        return nombreUsuario;
    }

    public void setnombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getcontraseña() {
        return contrasena;
    }

    public void setcontraseña(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getcorreo() {
        return correo;
    }

    public void setcorreo(String correo) {
        this.correo = correo;
    }

    public String getapellido() {
        return apellido;
    }

    public void setapellido(String apellido) {
        this.apellido = apellido;
    }

    public String getedad() {
        return edad;
    }

    public void setedad(String edad) {
        this.edad = edad;
    }

    public String getgenero() {
        return genero;
    }

    public void setgenero(String genero) {
        this.genero = genero;
    }

    public String getcumpleanos() {
        return cumpleanos;
    }

    public void setcumpleanos(String cumpleanos) {
        this.cumpleanos = cumpleanos;
    }

    public String getfotoPerfil() {
        return fotoPerfil;
    }

    public void setfotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    // Métodos implementados
    public String getnombre() {
        return nombreUsuario + " " + apellido;
    }

    public URI getfoto() {
        return Paths.get(fotoPerfil).toUri();
    }

    public String getfechaNacimiento() {
        return cumpleanos;
    }

}