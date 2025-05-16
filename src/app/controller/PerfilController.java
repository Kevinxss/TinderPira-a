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

public class PerfilController {

    @FXML private Label nombreLabel;
    @FXML private Label correoLabel;
    @FXML private Label fechaLabel;
    @FXML private Label generoLabel;
    @FXML private ImageView fotoUsuario;

    @FXML
    public void initialize() {
        try {
            User usuario = sesion.getUsuario();
            if (usuario != null) {
                nombreLabel.setText("Nombre: " + usuario.getnombre());
                correoLabel.setText("Correo: " + usuario.getcorreo());
                fechaLabel.setText("Cumpleaños: " + usuario.getfechaNacimiento());
                generoLabel.setText("Género: " + usuario.getgenero());

                InputStream stream = Files.newInputStream(Paths.get(usuario.getfoto()));
                fotoUsuario.setImage(new Image(stream));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
