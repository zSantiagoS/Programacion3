import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class LoginController {
    private LoginScreen loginScreen;
    private ArrayList<Usuarios> listaUsuarios;
    private Persistencia p;

    public LoginController(LoginScreen loginScreen) {
        p = Persistencia.getInstance();
        listaUsuarios = new ArrayList<>(p.leerArchivo());
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
                EventCatalog eventCatalog = new EventCatalog();
                eventCatalog.setVisible(true);
                loginScreen.dispose();
                return;
            }
        }

        // Si no se encontró el usuario, mostrar mensaje de error
        loginScreen.getEmailField().setText("");
        loginScreen.getPasswordField().setText("");
        JOptionPane.showMessageDialog(null, "Correo o contraseña incorrectos");
    }

    public void handleRegister() {
        RegisterForm registerForm = new RegisterForm(this); 
        registerForm.setVisible(true);
    }

    public void addUsuario(Usuarios usuario) {
        if (!usuarioExiste(usuario.getCorreo())) {
            listaUsuarios.add(usuario);
            p.escribirArchivo(listaUsuarios);
            JOptionPane.showMessageDialog(null, "Registro exitoso para " + usuario.getNombre());
        } else {
            JOptionPane.showMessageDialog(null, "El usuario con el correo " + usuario.getCorreo() + " YA existe.\nPor favor ingrese un correo válido.");
        }
    }

    public boolean usuarioExiste(String correo) {
        for (Usuarios usuario : listaUsuarios) {
            if (usuario.getCorreo().equals(correo)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Usuarios> getListaUsuarios() {
        return listaUsuarios;
    }

}
