import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

public class LoginController {
    private LoginScreen loginScreen;
    private List<Usuarios> listaUsuarios;

    public LoginController(LoginScreen loginScreen) {
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

    @SuppressWarnings("unlikely-arg-type")
    private void handleLogin() {
        String email = loginScreen.getEmailField().getText();
        char[] password = loginScreen.getPasswordField().getPassword();
        
        if (loginScreen.getEmailField().getText().equals("") || loginScreen.getPasswordField().getPassword().equals("")) {
            loginScreen.getEmailField().setText("");
            loginScreen.getPasswordField().setText("");
            JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos");
        }

        for (Usuarios usuario : listaUsuarios) {
            if (usuario.getCorreo().equals(email) && usuario.getContrasena().equals(password)) {
                JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso");
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Correo o contraseña incorrectos");
    
    }

    private void handleRegister() {
        // Abrir el formulario de registro
        RegisterForm registerForm = new RegisterForm(this);
        registerForm.setVisible(true);
    }

    public void addUsuario(Usuarios usuario) {
        listaUsuarios.add(usuario);
    }

}
