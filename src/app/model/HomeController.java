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

public class HomeController {

    @FXML
    private ImageView imagenUsuario;
    @FXML
    private Text nombreUsuario;
    @FXML
    private Text edadUsuario;
    @FXML
    private Button botonMatch;
    @FXML
    private Button botonNo;
    private List<User> posiblesMatches;
    private int indiceActual =0;
    private User usuarioActual;

    @FXML
    public void initialize() {
        // Obtener el usuario actual desde la sesión
        usuarioActual =sesion.getUsuarioActual();
        posiblesMatches = cargarPosiblesMatches("usuarios.json", usuarioActual);

        if (!posiblesMatches.isEmpty()) {
            mostrarUsuario(posiblesMatches.get(indiceActual));
        } else {
            nombreUsuario.setText("No se encontró el usuario actual.");
        }
    }

    private void mostrarUsuario(User usuario) {
        
        usuarioActual = usuario;
        // Mostrar la foto desde archivo
        String ruta = usuario.getfotoPerfil();
        File archivo = new File(ruta);
        if (archivo.exists()) {
            Image image = new Image(archivo.toURI().toString());
            imagenUsuario.setImage(image);
        } else {
            System.out.println("No se encontró la imagen en: " + ruta);
        }

        // Mostrar el nombre y la edad
        nombreUsuario.setText(usuario.getnombreUsuario() + " " + usuario.getapellido());
        edadUsuario.setText(usuario.getedad() + " años");

        // Funcionalidad de los botones
        botonMatch.setOnAction(event -> hacerMatch());
        botonNo.setOnAction(event -> rechazarMatch());
    }

    private void hacerMatch() {
        System.out.println("Match con " + usuarioActual.getnombreUsuario());
        avanzarUsuario();
    }

    private void rechazarMatch() {
        // Lógica de rechazo
        System.out.println("Rechazado " + usuarioActual.getnombreUsuario());
        avanzarUsuario();
    }
    
    private void avanzarUsuario(){
        indiceActual ++;
        if(indiceActual < posiblesMatches.size()){
            mostrarUsuario(posiblesMatches.get(indiceActual));
        }else{
            nombreUsuario.setText("No hay mas usuarios");
            edadUsuario.setText("");
            imagenUsuario.setImage(null);
            botonMatch.setDisable(true);
            botonNo.setDisable(true);
            
        }
    }
    public static List<User> cargarPosiblesMatches(String archivoJson, User usuarioActual){
        List<User>posiblesMatches = new ArrayList<>();
        
        try{
            //leemos los datos
            Gson gson = new Gson();
            Type listType = new TypeToken<List<User>>(){}.getType();
            FileReader reader = new FileReader(archivoJson);
            List<User> todosLosUsuarios = gson.fromJson(reader, listType);
            reader.close();
            
            for(User u: todosLosUsuarios){
                if(!u.getnombreUsuario().equals(usuarioActual.getnombreUsuario())||!u.getapellido().equals(usuarioActual.getapellido())){
                    int edadU = Integer.parseInt(u.getedad());
                    int edadActual = Integer.parseInt(usuarioActual.getedad());
                    int diferenciaEdad = Math.abs(edadU - edadActual);
                    if (diferenciaEdad <= 5){
                        posiblesMatches.add(u);
                    }
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        
        return posiblesMatches;
    }
}
