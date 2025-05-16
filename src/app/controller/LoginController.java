package app.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;

import app.model.User;
import app.model.sesion;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class LoginController {

    private static final String ARCHIVO_USUARIOS = "usuarios.json";

    @FXML
    private TextField correoField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void iniciarSesion(ActionEvent event) {
        String correo = correoField.getText();
        String contrasena = passwordField.getText();

        List<User> usuarios = cargarUsuarios();

        // Buscar el usuario que coincida con el correo y contraseña
        User usuario = usuarios.stream()
            .filter(u -> u.getcorreo().equals(correo) && u.getcontraseña().equals(contrasena))
            .findFirst()
            .orElse(null);

        if (usuario != null) {
            System.out.println("¡Login exitoso!");
            sesion.setUsuario(usuario); // Guardar el usuario en sesión

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/resources/main.fxml"));
                Scene scene = new Scene(loader.load());
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Inicio");
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Correo o contraseña incorrectos.");
        }
    }

    private List<User> cargarUsuarios() {
        try (FileReader reader = new FileReader(ARCHIVO_USUARIOS)) {
            Type tipoLista = new TypeToken<List<User>>() {}.getType();
            return new Gson().fromJson(reader, tipoLista);
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
