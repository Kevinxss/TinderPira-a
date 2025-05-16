package app.model;

import java.util.ArrayList;
import java.util.List;

public class UserDatabase {
    private List<User> users = new ArrayList<>();

    // Método para agregar un usuario
    public void addUser(User user) {
        users.add(user);
    }

    // Método para obtener todos los usuarios
    public List<User> getAllUsers() {
        return users;
    }
}
