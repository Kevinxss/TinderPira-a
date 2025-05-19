package app.model;

/**
 * Clase que gestiona la sesión del usuario actual en la aplicación.
 * Almacena y proporciona acceso al usuario que ha iniciado sesión.
 */
public class sesion {

    /** Usuario actual que está registrado en la sesión */
    private static User usuarioActual;

    /**
     * Establece el usuario actual.
     * 
     * @param user El usuario a establecer como el usuario actual.
     */
    public static void setUsuario(User user) {
        usuarioActual = user;
    }

    /**
     * Obtiene el usuario actual de la sesión.
     * 
     * @return El usuario actual o null si no hay ninguno establecido.
     */
    public static User getUsuario() {
        return usuarioActual;
    }

    /**
     * Método redundante para obtener el usuario actual.
     * Este método es el mismo que getUsuario() y solo se deja por compatibilidad.
     * 
     * @return El usuario actual.
     */
    public static User getUsuarioActual() {
        return getUsuario();
    }
}
