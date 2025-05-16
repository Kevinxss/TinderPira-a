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

public class MatchController {

    @FXML
    private ListView<String> listaMatches;

    private static final String ARCHIVO_USUARIOS = "usuarios.json";

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

    private List<User> obtenerMatches(User usuarioActual) {
        List<User> todos = cargarUsuarios();
        List<User> posiblesMatches = new ArrayList<>();

        try {
            int edadActual = Integer.parseInt(usuarioActual.getedad());

            for (User u : todos) {
                if (u.getcorreo().equals(usuarioActual.getcorreo())) {
                    continue; // No compararse consigo mismo
                } else {
                }

                try {
                    int edadU = Integer.parseInt(u.getedad());
                    if (Math.abs(edadU - edadActual) <= 5) {
                        posiblesMatches.add(u);
                    }
                } catch (NumberFormatException ignored) {
                    // Ignorar usuarios con edad inválida
                }
            }

        } catch (NumberFormatException e) {
            System.err.println("Edad inválida del usuario actual.");
        }

        return posiblesMatches;
    }

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
