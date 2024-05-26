import javax.swing.*;
import java.awt.*;

public class AdminScreen extends JFrame {

    public AdminScreen() {
        setTitle("Panel de Administración");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Panel de Administración", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(33, 150, 243));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        JButton registerLocationButton = createStyledButton("Registrar Localización");
        registerLocationButton.addActionListener(e -> showRegisterLocationScreen());
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(registerLocationButton, gbc);

        JButton registerEventButton = createStyledButton("Registrar Evento");
        registerEventButton.addActionListener(e -> showRegisterEventScreen());
        gbc.gridy = 2;
        panel.add(registerEventButton, gbc);

        JButton openTaquillaButton = createStyledButton("Abrir Taquilla");
        openTaquillaButton.addActionListener(e -> showOpenTaquillaScreen());
        gbc.gridy = 3;
        panel.add(openTaquillaButton, gbc);

        JButton closeTaquillaButton = createStyledButton("Cerrar Taquilla");
        closeTaquillaButton.addActionListener(e -> showCloseTaquillaScreen());
        gbc.gridy = 4;
        panel.add(closeTaquillaButton, gbc);

        add(panel);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(33, 150, 243));
        button.setForeground(Color.WHITE);
        return button;
    }

    private void showRegisterLocationScreen() {
        RegisterLocationView registerLocationView = new RegisterLocationView();
        registerLocationView.setVisible(true);
    }

    private void showRegisterEventScreen() {
        RegisterEventView registerEventView = new RegisterEventView();
        registerEventView.setVisible(true);
    }

    private void showOpenTaquillaScreen() {
        OpenTaquillaView openTaquillaView = new OpenTaquillaView();
        openTaquillaView.setVisible(true);
    }

    private void showCloseTaquillaScreen() {
        CloseTaquillaView closeTaquillaView = new CloseTaquillaView();
        closeTaquillaView.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminScreen().setVisible(true));
    }
}

