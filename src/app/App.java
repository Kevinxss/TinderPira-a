package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase principal de la aplicación JavaFX.
 * Esta clase inicia la interfaz gráfica cargando el archivo FXML correspondiente.
 */
public class App extends Application {

    /**
     * Método de inicio de la aplicación JavaFX.
     * Se encarga de cargar la interfaz gráfica definida en el archivo FXML
     * y mostrar la ventana principal.
     *
     * @param primaryStage El escenario principal donde se carga la escena.
     * @throws Exception Si ocurre un error al cargar el archivo FXML.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Carga el archivo FXML que contiene el diseño de la ventana principal
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/resources/menu.fxml"));
        Parent root = loader.load();

        // Crea la escena con el contenido del archivo FXML
        Scene scene = new Scene(root);

        // Configura y muestra la ventana principal
        primaryStage.setScene(scene);
        primaryStage.setTitle("Mi App");
        primaryStage.show();
    }

    /**
     * Método principal que lanza la aplicación JavaFX.
     *
     * @param args Argumentos de línea de comandos (si los hay).
     */
    public static void main(String[] args) {
        launch(args);
    }
}
