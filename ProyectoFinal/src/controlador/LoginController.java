import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

public class LoginController {
    private LoginScreen loginScreen;
    private List<Usuarios> listaUsuarios;

    public LoginController(LoginScreen loginScreen) {
        listaUsuarios = new ArrayList<>();
        this.loginScreen = loginScreen;

        // Asociar los botones a los métodos del controlador usando clases internas
        // anónimas
        this.loginScreen.getLoginButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        this.loginScreen.getRegisterButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRegister();
            }
        });
    }

    public void handleLogin() {
    String email = loginScreen.getEmailField().getText();
    char[] password = loginScreen.getPasswordField().getPassword();

    // Verificar si los campos están vacíos
    if (email.isEmpty() || password.length == 0) {
        JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos");
        loginScreen.getEmailField().setText("");
        loginScreen.getPasswordField().setText("");
        return; // Terminar la ejecución del método si los campos están vacíos
    }

    // Buscar en la lista de usuarios
    for (Usuarios usuario : listaUsuarios) {
        if (usuario.getCorreo().equals(email) && Arrays.equals(usuario.getContrasena().toCharArray(), password)) {
            JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso");
            return; // Terminar la ejecución del método si el login es exitoso
        }
    }

    // Si no se encontró el usuario, mostrar mensaje de error
    JOptionPane.showMessageDialog(null, "Correo o contraseña incorrectos");
}


    public void handleRegister() {
        // Abrir el formulario de registro
        RegisterForm registerForm = new RegisterForm(this);
        registerForm.setVisible(true);
    }

    public void addUsuario(Usuarios usuario) {
        listaUsuarios.add(usuario);
    }

}
