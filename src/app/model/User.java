package app.model;

import java.net.URI;
import java.nio.file.Paths;

/**
 * Representa un usuario en la aplicación. Contiene toda la información personal
 * relacionada con el usuario, incluyendo su nombre, correo, contraseña, foto de perfil, etc.
 */
public class User {
    private String nombreUsuario;
    private String apellido;
    private String edad;
    private String genero;
    private String cumpleanos;
    private String correo;
    private String contrasena;
    private String fotoPerfil;

    /**
     * Constructor de la clase User.
     * 
     * @param nombreUsuario El nombre de usuario.
     * @param apellido El apellido del usuario.
     * @param edad La edad del usuario.
     * @param genero El género del usuario.
     * @param cumpleanos La fecha de cumpleaños del usuario.
     * @param correo El correo electrónico del usuario.
     * @param contrasena La contraseña del usuario.
     * @param fotoPerfil La ruta de la foto de perfil del usuario.
     */
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

    /**
     * Obtiene el nombre de usuario.
     * 
     * @return El nombre de usuario.
     */
    public String getnombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Establece el nombre de usuario.
     * 
     * @param nombreUsuario El nombre de usuario.
     */
    public void setnombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Obtiene la contraseña del usuario.
     * 
     * @return La contraseña del usuario.
     */
    public String getcontraseña() {
        return contrasena;
    }

    /**
     * Establece la contraseña del usuario.
     * 
     * @param contrasena La contraseña del usuario.
     */
    public void setcontraseña(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     * 
     * @return El correo electrónico del usuario.
     */
    public String getcorreo() {
        return correo;
    }

    /**
     * Establece el correo electrónico del usuario.
     * 
     * @param correo El correo electrónico del usuario.
     */
    public void setcorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Obtiene el apellido del usuario.
     * 
     * @return El apellido del usuario.
     */
    public String getapellido() {
        return apellido;
    }

    /**
     * Establece el apellido del usuario.
     * 
     * @param apellido El apellido del usuario.
     */
    public void setapellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Obtiene la edad del usuario.
     * 
     * @return La edad del usuario.
     */
    public String getedad() {
        return edad;
    }

    /**
     * Establece la edad del usuario.
     * 
     * @param edad La edad del usuario.
     */
    public void setedad(String edad) {
        this.edad = edad;
    }

    /**
     * Obtiene el género del usuario.
     * 
     * @return El género del usuario.
     */
    public String getgenero() {
        return genero;
    }

    /**
     * Establece el género del usuario.
     * 
     * @param genero El género del usuario.
     */
    public void setgenero(String genero) {
        this.genero = genero;
    }

    /**
     * Obtiene la fecha de cumpleaños del usuario.
     * 
     * @return La fecha de cumpleaños del usuario.
     */
    public String getcumpleanos() {
        return cumpleanos;
    }

    /**
     * Establece la fecha de cumpleaños del usuario.
     * 
     * @param cumpleanos La fecha de cumpleaños del usuario.
     */
    public void setcumpleanos(String cumpleanos) {
        this.cumpleanos = cumpleanos;
    }

    /**
     * Obtiene la ruta de la foto de perfil del usuario.
     * 
     * @return La ruta de la foto de perfil.
     */
    public String getfotoPerfil() {
        return fotoPerfil;
    }

    /**
     * Establece la ruta de la foto de perfil del usuario.
     * 
     * @param fotoPerfil La ruta de la foto de perfil.
     */
    public void setfotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    // Métodos adicionales

    /**
     * Obtiene el nombre completo del usuario (nombre + apellido).
     * 
     * @return El nombre completo del usuario.
     */
    public String getnombre() {
        return nombreUsuario + " " + apellido;
    }

    /**
     * Obtiene la URI de la foto de perfil del usuario.
     * 
     * @return La URI de la foto de perfil.
     */
    public URI getfoto() {
        return Paths.get(fotoPerfil).toUri();
    }

    /**
     * Obtiene la fecha de nacimiento del usuario (cumpleaños).
     * 
     * @return La fecha de nacimiento del usuario.
     */
    public String getfechaNacimiento() {
        return cumpleanos;
    }
}
