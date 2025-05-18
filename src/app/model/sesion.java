package app.model;

public class sesion {
    private static User usuarioActual;

    // Método para establecer el usuario actual
    public static void setUsuario(User user) {
        usuarioActual = user;
    }

    // Método para obtener el usuario actual
    public static User getUsuario() {
        return usuarioActual;
    }

    // Método corregido para obtener el usuario actual
    public static User getUsuarioActual() {
        return getUsuario();
    }
}
