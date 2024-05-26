import javax.swing.*;
import java.awt.*;

public class RegisterLocationView extends JFrame {

    public RegisterLocationView() {
        setTitle("Registrar Localización");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Registrar Nueva Localización");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        JLabel locationLabel = new JLabel("Nombre de la Localización:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(locationLabel, gbc);

        JTextField locationField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(locationField, gbc);

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
