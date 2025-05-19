package app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import app.model.sesion;
import app.model.User;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.InputStream;

/**
 * Controlador de la vista de perfil.
 * Muestra los datos del usuario actualmente en sesión, incluyendo nombre, correo, fecha de nacimiento,
 * género y fotografía de perfil.
 */
public class PerfilController {

    @FXML private Label nombreLabel;
    @FXML private Label correoLabel;
    @FXML private Label fechaLabel;
    @FXML private Label generoLabel;
    @FXML private ImageView fotoUsuario;

    /**
     * Método de inicialización llamado automáticamente cuando se carga la vista FXML.
     * Obtiene al usuario en sesión y muestra sus datos en los componentes visuales.
     */
    @FXML
    public void initialize() {
        try {
            User usuario = sesion.getUsuario();
            if (usuario != null) {
                // Mostrar información textual
                nombreLabel.setText("Nombre: " + usuario.getnombre());
                correoLabel.setText("Correo: " + usuario.getcorreo());
                fechaLabel.setText("Cumpleaños: " + usuario.getfechaNacimiento());
                generoLabel.setText("Género: " + usuario.getgenero());

                // Mostrar imagen de perfil
                InputStream stream = Files.newInputStream(Paths.get(usuario.getfoto()));
                fotoUsuario.setImage(new Image(stream));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
