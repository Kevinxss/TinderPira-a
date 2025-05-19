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

/**
 * Controlador para la vista de inicio de sesión (login).
 * Administra la autenticación del usuario mediante un archivo JSON.
 */
public class LoginController {

    /**
     * Ruta del archivo JSON donde se almacenan los usuarios registrados.
     */
    private static final String ARCHIVO_USUARIOS = "usuarios.json";

    /**
     * Campo de texto para ingresar el correo electrónico.
     */
    @FXML
    private TextField correoField;

    /**
     * Campo de texto para ingresar la contraseña.
     */
    @FXML
    private PasswordField passwordField;

    /**
     * Método que se ejecuta al hacer clic en el botón de "Iniciar Sesión".
     * Valida las credenciales ingresadas contra los datos del archivo JSON.
     *
     * @param event El evento generado por el botón.
     */
    @FXML
    private void iniciarSesion(ActionEvent event) {
        String correo = correoField.getText();
        String contrasena = passwordField.getText();

        List<User> usuarios = cargarUsuarios();

        // Buscar el usuario que coincida con el correo y la contraseña ingresados
        User usuario = usuarios.stream()
            .filter(u -> u.getcorreo().equals(correo) && u.getcontraseña().equals(contrasena))
            .findFirst()
            .orElse(null);

        if (usuario != null) {
            System.out.println("¡Login exitoso!");
            sesion.setUsuario(usuario); // Guardar el usuario en sesión

            try {
                // Cambiar a la vista principal (main.fxml)
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

    /**
     * Carga la lista de usuarios desde el archivo JSON.
     *
     * @return Una lista de objetos {@link User}. Si ocurre un error, retorna una lista vacía.
     */
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
