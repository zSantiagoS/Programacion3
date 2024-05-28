import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class LoginController {
    private LoginScreen loginScreen;
    private ArrayList<Usuarios> listaUsuarios;
    private Persistencia p;
    private Semaphore semaphore;  // Semáforo para controlar el acceso
    private Set<String> usuariosActivos;  // Conjunto para rastrear usuarios activos

    public LoginController(LoginScreen loginScreen) {
        p = Persistencia.getInstance();
        listaUsuarios = new ArrayList<>(p.leerArchivo());
        this.loginScreen = loginScreen;
        semaphore = new Semaphore(3);  // Solo 3 usuarios pueden acceder simultáneamente
        usuariosActivos = new HashSet<>();

        // Asociar los botones a los métodos del controlador usando clases internas anónimas
        this.loginScreen.getLoginButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear un nuevo hilo para manejar el inicio de sesión
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handleLogin();
                    }
                }).start();
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
        try {
            semaphore.acquire();  // Adquirir un permiso del semáforo
            String email = loginScreen.getEmailField().getText();
            char[] password = loginScreen.getPasswordField().getPassword();

            // Verificar si los campos están vacíos
            if (email.isEmpty() || password.length == 0) {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos");
                    loginScreen.getEmailField().setText("");
                    loginScreen.getPasswordField().setText("");
                });
                return; // Terminar la ejecución del método si los campos están vacíos
            }

            boolean loginExitoso = false;
            for (Usuarios usuario : listaUsuarios) {
                if (usuario.getCorreo().equals(email) && Arrays.equals(usuario.getContrasena().toCharArray(), password)) {
                    // Verificar si el usuario ya está activo en el proceso de selección de eventos
                    if (usuariosActivos.contains(usuario.getCorreo())) {
                        SwingUtilities.invokeLater(() -> {
                            JOptionPane.showMessageDialog(null, "El usuario ya está realizando la selección de eventos.");
                        });
                        return;
                    }
                    
                    loginExitoso = true;
                    usuariosActivos.add(usuario.getCorreo());  // Agregar usuario al conjunto de usuarios activos
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso");
                        EventCatalog eventCatalog = new EventCatalog();
                        eventCatalog.addWindowListener(new java.awt.event.WindowAdapter() {
                            @Override
                            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                                usuariosActivos.remove(usuario.getCorreo());  // Quitar usuario del conjunto al cerrar la ventana
                            }
                        });
                        eventCatalog.setVisible(true);
                    });
                    break;
                }
            }

            // Si no se encontró el usuario, mostrar mensaje de error
            if (!loginExitoso) {
                SwingUtilities.invokeLater(() -> {
                    loginScreen.getEmailField().setText("");
                    loginScreen.getPasswordField().setText("");
                    JOptionPane.showMessageDialog(null, "Correo o contraseña incorrectos");
                });
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();  // Liberar el permiso del semáforo
        }
    }
    
    //Metodo para llenar un registro de un nuevo usuario 
    public void handleRegister() {
        RegisterForm registerForm = new RegisterForm(this); 
        registerForm.setVisible(true);
    }

    public void addUsuario(Usuarios usuario) {
        if (!usuarioExiste(usuario.getCorreo())) {
            listaUsuarios.add(usuario);
            p.escribirArchivo(listaUsuarios);
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(null, "Registro exitoso para " + usuario.getNombre());
            });
        } else {
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(null, "El usuario con el correo " + usuario.getCorreo() + " YA existe.\nPor favor ingrese un correo válido.");
            });
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
