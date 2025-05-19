package app.model;

import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import java.io.File;

/**
 * Controlador de la vista principal (Home).
 * Muestra posibles coincidencias (matches) para el usuario actual y permite aceptarlas o rechazarlas.
 */
public class HomeController {

    @FXML private ImageView imagenUsuario;
    @FXML private Text nombreUsuario;
    @FXML private Text edadUsuario;
    @FXML private Button botonMatch;
    @FXML private Button botonNo;

    /** Lista de posibles matches compatibles por edad */
    private List<User> posiblesMatches;

    /** Índice del usuario mostrado actualmente */
    private int indiceActual = 0;

    /** Usuario que ha iniciado sesión */
    private User usuarioActual;

    /**
     * Inicializa la vista al cargar el FXML.
     * Carga los posibles matches y muestra el primero si existe.
     */
    @FXML
    public void initialize() {
        usuarioActual = sesion.getUsuarioActual();
        posiblesMatches = cargarPosiblesMatches("usuarios.json", usuarioActual);

        if (!posiblesMatches.isEmpty()) {
            mostrarUsuario(posiblesMatches.get(indiceActual));
        } else {
            nombreUsuario.setText("No se encontró el usuario actual.");
        }
    }

    /**
     * Muestra la información de un usuario en pantalla.
     * @param usuario Usuario a mostrar.
     */
    private void mostrarUsuario(User usuario) {
        usuarioActual = usuario;

        // Mostrar imagen
        String ruta = usuario.getfotoPerfil();
        File archivo = new File(ruta);
        if (archivo.exists()) {
            Image image = new Image(archivo.toURI().toString());
            imagenUsuario.setImage(image);
        } else {
            System.out.println("No se encontró la imagen en: " + ruta);
        }

        // Mostrar nombre y edad
        nombreUsuario.setText(usuario.getnombreUsuario() + " " + usuario.getapellido());
        edadUsuario.setText(usuario.getedad() + " años");

        // Configurar botones
        botonMatch.setOnAction(event -> hacerMatch());
        botonNo.setOnAction(event -> rechazarMatch());
    }

    /**
     * Acción cuando el usuario acepta un match.
     */
    private void hacerMatch() {
        System.out.println("Match con " + usuarioActual.getnombreUsuario());
        avanzarUsuario();
    }

    /**
     * Acción cuando el usuario rechaza un match.
     */
    private void rechazarMatch() {
        System.out.println("Rechazado " + usuarioActual.getnombreUsuario());
        avanzarUsuario();
    }

    /**
     * Avanza al siguiente usuario en la lista de posibles matches.
     * Si no hay más usuarios, desactiva los botones.
     */
    private void avanzarUsuario() {
        indiceActual++;
        if (indiceActual < posiblesMatches.size()) {
            mostrarUsuario(posiblesMatches.get(indiceActual));
        } else {
            nombreUsuario.setText("No hay más usuarios");
            edadUsuario.setText("");
            imagenUsuario.setImage(null);
            botonMatch.setDisable(true);
            botonNo.setDisable(true);
        }
    }

    /**
     * Carga los usuarios compatibles desde el archivo JSON.
     * Solo incluye aquellos cuya edad esté en un rango de ±5 años con respecto al usuario actual,
     * y que no sean el mismo usuario.
     *
     * @param archivoJson Ruta del archivo JSON.
     * @param usuarioActual Usuario que ha iniciado sesión.
     * @return Lista de posibles matches.
     */
    public static List<User> cargarPosiblesMatches(String archivoJson, User usuarioActual) {
        List<User> posiblesMatches = new ArrayList<>();

        try {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<User>>() {}.getType();
            FileReader reader = new FileReader(archivoJson);
            List<User> todosLosUsuarios = gson.fromJson(reader, listType);
            reader.close();

            for (User u : todosLosUsuarios) {
                boolean esOtroUsuario = !u.getnombreUsuario().equals(usuarioActual.getnombreUsuario()) ||
                                        !u.getapellido().equals(usuarioActual.getapellido());

                if (esOtroUsuario) {
                    int edadU = Integer.parseInt(u.getedad());
                    int edadActual = Integer.parseInt(usuarioActual.getedad());
                    int diferenciaEdad = Math.abs(edadU - edadActual);

                    if (diferenciaEdad <= 5) {
                        posiblesMatches.add(u);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return posiblesMatches;
    }
}
