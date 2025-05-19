package app.controller;

import app.model.User;
import app.model.sesion;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador para la vista de coincidencias (Match).
 * Se encarga de mostrar una lista de usuarios cuya edad esté dentro de un rango de ±5 años
 * respecto al usuario actual en sesión.
 */
public class MatchController {

    /**
     * ListView para mostrar los posibles matches del usuario.
     */
    @FXML
    private ListView<String> listaMatches;

    /**
     * Ruta del archivo JSON que contiene los usuarios registrados.
     */
    private static final String ARCHIVO_USUARIOS = "usuarios.json";

    /**
     * Método que se ejecuta automáticamente al cargar la vista.
     * Busca al usuario actual y genera su lista de coincidencias.
     */
    @FXML
    public void initialize() {
        User usuarioActual = sesion.getUsuarioActual();
        if (usuarioActual != null) {
            List<User> matches = obtenerMatches(usuarioActual);
            mostrarMatches(matches);
        } else {
            listaMatches.getItems().add("No se encontró el usuario actual.");
        }
    }

    /**
     * Filtra y obtiene una lista de usuarios cuya edad esté dentro del rango ±5 años
     * respecto a la edad del usuario actual.
     *
     * @param usuarioActual El usuario que ha iniciado sesión.
     * @return Lista de posibles coincidencias (matches).
     */
    private List<User> obtenerMatches(User usuarioActual) {
        List<User> todos = cargarUsuarios();
        List<User> posiblesMatches = new ArrayList<>();

        try {
            int edadActual = Integer.parseInt(usuarioActual.getedad());

            for (User u : todos) {
                if (u.getcorreo().equals(usuarioActual.getcorreo())) {
                    continue; // Evitar compararse a sí mismo
                }

                try {
                    int edadU = Integer.parseInt(u.getedad());
                    if (Math.abs(edadU - edadActual) <= 5) {
                        posiblesMatches.add(u);
                    }
                } catch (NumberFormatException ignored) {
                    // Ignorar usuarios con edad no válida
                }
            }

        } catch (NumberFormatException e) {
            System.err.println("Edad inválida del usuario actual.");
        }

        return posiblesMatches;
    }

    /**
     * Carga todos los usuarios desde el archivo JSON.
     *
     * @return Lista de usuarios registrados. Si ocurre un error, retorna una lista vacía.
     */
    private List<User> cargarUsuarios() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(ARCHIVO_USUARIOS)) {
            Type tipoLista = new TypeToken<List<User>>() {}.getType();
            List<User> usuarios = gson.fromJson(reader, tipoLista);
            return usuarios != null ? usuarios : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Muestra en la interfaz la lista de usuarios que coinciden con el rango de edad.
     *
     * @param matches Lista de usuarios que coinciden.
     */
    private void mostrarMatches(List<User> matches) {
        if (matches.isEmpty()) {
            listaMatches.getItems().add("No se encontraron coincidencias.");
        } else {
            for (User u : matches) {
                String texto = u.getnombreUsuario() + " " + u.getapellido() + " (" + u.getedad() + " años)";
                listaMatches.getItems().add(texto);
            }
        }
    }
}
