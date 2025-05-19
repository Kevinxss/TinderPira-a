package app.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

/**
 * Controlador de la vista principal.
 * Gestiona la carga dinámica de las diferentes secciones de la aplicación:
 * Home, Notificaciones, Perfil y Match.
 */
public class MainController {

    /**
     * Contenedor principal donde se cargan dinámicamente las vistas (FXML).
     */
    @FXML
    private StackPane mainContent;

    /**
     * Muestra la vista de inicio (Home).
     * Se ejecuta al hacer clic en el botón de inicio.
     */
    public void mostrarHome() {
        System.out.println("Cargando vista Home...");
        cargarVista("Home.fxml");
    }

    /**
     * Muestra la vista de notificaciones.
     */
    public void mostrarNotificaciones() {
        cargarVista("notificaciones.fxml");
    }

    /**
     * Muestra la vista del perfil del usuario.
     */
    public void mostrarPerfil() {
        cargarVista("perfil.fxml");
    }

    /**
     * Muestra la vista de coincidencias (match).
     */
    public void mostrarMatch() {
        cargarVista("match.fxml");
    }

    /**
     * Carga una vista FXML específica en el panel principal.
     *
     * @param fxml Nombre del archivo FXML a cargar (ej. "perfil.fxml").
     */
    private void cargarVista(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/resources/" + fxml));
            Parent vista = loader.load();
            mainContent.getChildren().setAll(vista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
