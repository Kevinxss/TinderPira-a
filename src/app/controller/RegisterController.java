package app.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import app.model.User;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador de la vista de registro.
 * Permite registrar nuevos usuarios con datos personales y una imagen de perfil.
 */
public class RegisterController {

    @FXML private TextField nombreField;
    @FXML private TextField correoField;
    @FXML private PasswordField passwordField;
    @FXML private TextField apellidoField;
    @FXML private TextField edadField;
    @FXML private TextField cumpleanosField;
    @FXML private TextField generoField;
    @FXML private ImageView imageViewPerfil;

    /** Ruta local de la imagen seleccionada para el perfil del usuario */
    private String rutaImagenPerfil;

    /** Archivo JSON donde se almacenan los usuarios registrados */
    private static final String ARCHIVO_USUARIOS = "usuarios.json";

    /**
     * Método ejecutado cuando el usuario presiona el botón "Seleccionar imagen".
     * Abre un FileChooser para elegir una imagen desde el sistema.
     */
    @FXML
    private void seleccionarImagen() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar imagen de perfil");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg")
        );
        File archivo = fileChooser.showOpenDialog(null);
        if (archivo != null) {
            rutaImagenPerfil = archivo.getAbsolutePath();
            Image imagen = new Image(archivo.toURI().toString());
            imageViewPerfil.setImage(imagen);
        }
    }

    /**
     * Método ejecutado al presionar el botón de registro.
     * Crea un nuevo objeto {@link User}, lo agrega a la lista y guarda los datos en el archivo JSON.
     */
    @FXML
    private void registrarUsuario() {
        String nombre = nombreField.getText().trim();
        String apellido = apellidoField.getText().trim();
        String edad = edadField.getText().trim();
        String genero = generoField.getText().trim();
        String cumpleanos = cumpleanosField.getText().trim();
        String correo = correoField.getText().trim();
        String contraseña = passwordField.getText();

        if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || contraseña.isEmpty()) {
            mostrarAlerta("Campos obligatorios", "Por favor, llena al menos nombre, apellido, correo y contraseña.");
            return;
        }

        if (rutaImagenPerfil == null) {
            rutaImagenPerfil = ""; // Imagen opcional
        }

        User nuevoUsuario = new User(nombre, apellido, edad, genero, cumpleanos, correo, contraseña, rutaImagenPerfil);
        List<User> usuarios = cargarUsuarios();

        usuarios.add(nuevoUsuario);
        guardarUsuarios(usuarios);

        mostrarAlerta("Registro exitoso", "El usuario ha sido registrado correctamente.");
        limpiarCampos();
    }

    /**
     * Carga todos los usuarios desde el archivo JSON.
     * @return Lista de usuarios existentes.
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
     * Guarda la lista de usuarios en el archivo JSON.
     * @param usuarios Lista de usuarios a guardar.
     */
    private void guardarUsuarios(List<User> usuarios) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(ARCHIVO_USUARIOS)) {
            gson.toJson(usuarios, writer);
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo guardar el archivo de usuarios.");
        }
    }

    /**
     * Muestra una alerta de tipo informativa con un mensaje personalizado.
     * @param titulo Título de la alerta.
     * @param mensaje Contenido del mensaje.
     */
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Limpia todos los campos del formulario después del registro.
     */
    private void limpiarCampos() {
        nombreField.clear();
        apellidoField.clear();
        edadField.clear();
        generoField.clear();
        cumpleanosField.clear();
        correoField.clear();
        passwordField.clear();
        imageViewPerfil.setImage(null);
        rutaImagenPerfil = null;
    }
}
