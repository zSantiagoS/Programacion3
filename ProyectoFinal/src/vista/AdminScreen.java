import javax.swing.*;
import java.awt.*;

public class AdminScreen extends JFrame {
    private JButton registerLocationButton;
    private JButton registerEventButton;
    private JButton openTaquillaButton;
    private JButton closeTaquillaButton;

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

        gbc.gridwidth = 1;

        registerLocationButton = new JButton("Registrar Localización");
        registerLocationButton.setBackground(new Color(33, 150, 243));
        registerLocationButton.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(registerLocationButton, gbc);

        registerEventButton = new JButton("Registrar Evento");
        registerEventButton.setBackground(new Color(33, 150, 243));
        registerEventButton.setForeground(Color.WHITE);
        gbc.gridy = 2;
        panel.add(registerEventButton, gbc);

        openTaquillaButton = new JButton("Abrir Taquilla");
        openTaquillaButton.setBackground(new Color(33, 150, 243));
        openTaquillaButton.setForeground(Color.WHITE);
        gbc.gridy = 3;
        panel.add(openTaquillaButton, gbc);

        closeTaquillaButton = new JButton("Cerrar Taquilla");
        closeTaquillaButton.setBackground(new Color(33, 150, 243));
        closeTaquillaButton.setForeground(Color.WHITE);
        gbc.gridy = 4;
        panel.add(closeTaquillaButton, gbc);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminScreen().setVisible(true));
    }
}
