import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
    private Semaphore semaphore; // Semáforo para controlar el acceso
    private Set<String> usuariosActivos; // Conjunto para rastrear usuarios activos

    public LoginController(LoginScreen loginScreen) {
        p = Persistencia.getInstance();
        listaUsuarios = new ArrayList<>(p.leerArchivo());
        this.loginScreen = loginScreen;
        semaphore = new Semaphore(3); // Solo 3 usuarios pueden acceder simultáneamente
        usuariosActivos = new HashSet<>();
        final EventClient client;

        // Asociar los botones a los métodos del controlador usando clases internas
        // anónimas
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

        // Boton de registarar
        this.loginScreen.getRegisterButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRegister();
            }
        });
    }

    public void handleLogin() {
        try {
            semaphore.acquire(); // Adquirir un permiso del semáforo
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

            // For que recorre todos los usuarios
            boolean loginExitoso = false;
            final EventClient client; // Declarar la instancia de EventClient

            for (Usuarios usuario : listaUsuarios) {
                if (usuario.getCorreo().equals(email)
                        && Arrays.equals(usuario.getContrasena().toCharArray(), password)) {
                    if (usuariosActivos.contains(usuario.getCorreo())) {
                        SwingUtilities.invokeLater(() -> {
                            JOptionPane.showMessageDialog(null,
                                    "El usuario ya está realizando la selección de eventos.");
                        });
                        return;
                    }

                    loginExitoso = true;
                    usuariosActivos.add(usuario.getCorreo());

                    try {
                        // Crear una instancia de EventClient para este usuario
                        client = new EventClient("localhost", 8080);
                        client.sendRequest(new LoginRequest(usuario));
                        System.out.println(new LoginRequest(usuario));
                        LoginResponse response = (LoginResponse) client.receiveResponse();
                        
                        if (response.isLoginExitoso()) {
                            SwingUtilities.invokeLater(() -> {
                                JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso");
                                EventCatalog eventCatalog = new EventCatalog(usuario);
                                System.out.println(eventCatalog);
                                
                                eventCatalog.addWindowListener(new java.awt.event.WindowAdapter() {
                                    @Override
                                    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                                        usuariosActivos.remove(usuario.getCorreo());
                                        // Cerrar el cliente cuando se cierre la ventana de EventCatalog
                                        try {
                                            client.close();
                                        } catch (Exception ex) {
                                            ex.printStackTrace();
                                        }
                                    }
                                    
                                });
                                loginScreen.dispose();
                            });
                        } else {
                            SwingUtilities.invokeLater(() -> {
                                JOptionPane.showMessageDialog(null, "Error en el inicio de sesión");
                            });
                        }
                    } catch (IOException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
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
            semaphore.release(); // Liberar el permiso del semáforo
        }
    }

    // Metodo para llenar un registro de un nuevo usuario
    public void handleRegister() {
        RegisterForm registerForm = new RegisterForm(this);
        registerForm.setVisible(true);
    }

    // Metodo para agregar un nuevo usuario a la lista de usuarios
    public void addUsuario(Usuarios usuario) {
        if (!usuarioExiste(usuario.getCorreo())) {
            listaUsuarios.add(usuario);
            p.escribirArchivo(listaUsuarios);
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(null, "Registro exitoso para " + usuario.getNombre());
            });
        } else {
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(null, "El usuario con el correo " + usuario.getCorreo()
                        + " YA existe.\nPor favor ingrese un correo válido.");
            });
        }
    }

    // Verificar si existe el usuario
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
