package app.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.event.ActionEvent;

/**
 * Controlador para la vista del menú principal.
 * Gestiona la navegación hacia las pantallas de inicio de sesión y registro.
 */
public class MenuController {

    /**
     * Maneja el evento cuando el usuario hace clic en el botón "Iniciar sesión".
     * Cambia la escena a la vista de login.
     *
     * @param event Evento generado por el botón.
     * @throws Exception Si ocurre un error al cargar el archivo FXML.
     */
    @FXML
    void irLogin(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/app/resources/login.fxml"));
        stage.setScene(new Scene(root));
    }

    /**
     * Maneja el evento cuando el usuario hace clic en el botón "Registrarse".
     * Cambia la escena a la vista de registro.
     *
     * @param event Evento generado por el botón.
     * @throws Exception Si ocurre un error al cargar el archivo FXML.
     */
    @FXML
    void irRegistro(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/app/resources/register.fxml"));
        stage.setScene(new Scene(root));
    }
}
