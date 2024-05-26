import javax.swing.*;
import java.awt.*;

public class RegisterEventView extends JFrame {

    public RegisterEventView() {
        setTitle("Registrar Evento");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Registrar Nuevo Evento");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        JLabel eventLabel = new JLabel("Nombre del Evento:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(eventLabel, gbc);

        JTextField eventField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(eventField, gbc);

        JButton registerButton = new JButton("Registrar");
        registerButton.setBackground(new Color(33, 150, 243));
        registerButton.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 5, 5, 5);
        panel.add(registerButton, gbc);

        add(panel);
    }
}
