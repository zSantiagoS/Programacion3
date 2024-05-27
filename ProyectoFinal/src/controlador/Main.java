import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Menú Inicial");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new BorderLayout());
            frame.getContentPane().setBackground(new Color(240, 240, 240));

            JPanel panel = new JPanel(new GridBagLayout());
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JLabel titleLabel = new JLabel("Bienvenido", SwingConstants.CENTER);
            titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
            titleLabel.setForeground(new Color(33, 150, 243));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            panel.add(titleLabel, gbc);

            JButton adminButton = new JButton("Panel Administrador");
            adminButton.setBackground(new Color(33, 150, 243));
            adminButton.setForeground(Color.WHITE);
            adminButton.setFocusPainted(false);
            adminButton.addActionListener(e -> {
                frame.dispose(); // Cerrar el menú inicial
                openAdminPanel();
            });
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            panel.add(adminButton, gbc);

            JButton userButton = new JButton("Login/Register Usuario");
            userButton.setBackground(new Color(33, 150, 243));
            userButton.setForeground(Color.WHITE);
            userButton.setFocusPainted(false);
            userButton.addActionListener(e -> {
                frame.dispose(); // Cerrar el menú inicial
                openLoginRegister();
            });
            gbc.gridx = 1;
            panel.add(userButton, gbc);

            frame.add(panel, BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }

    private static void openAdminPanel() {
        // Aquí iría la lógica para abrir el panel de administrador
        AdminScreen adminScreen = new AdminScreen();
        adminScreen.setVisible(true);
    }

    private static void openLoginRegister() {
        // Aquí iría la lógica para abrir la pantalla de login/registro de usuario
        LoginScreen loginScreen = new LoginScreen();
        new LoginController(loginScreen);
        loginScreen.setVisible(true);
    }
}
