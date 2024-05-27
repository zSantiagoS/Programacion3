import javax.swing.*;
import java.awt.*;

public class OpenTaquillaView extends JFrame {

    public OpenTaquillaView() {
        setTitle("Abrir Taquilla");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Abrir Taquilla para Evento");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        JLabel bronzeLabel = new JLabel("Bronce:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(bronzeLabel, gbc);

        JTextField bronzeField = new JTextField(10);
        gbc.gridx = 1;
        panel.add(bronzeField, gbc);

        JLabel silverLabel = new JLabel("Plata:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(silverLabel, gbc);

        JTextField silverField = new JTextField(10);
        gbc.gridx = 1;
        panel.add(silverField, gbc);

        JLabel goldLabel = new JLabel("Oro:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        panel.add(goldLabel, gbc);

        JTextField goldField = new JTextField(10);
        gbc.gridx = 1;
        panel.add(goldField, gbc);

        JLabel timeLabel = new JLabel("Hora de Apertura:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        panel.add(timeLabel, gbc);

        JTextField timeField = new JTextField(10);
        gbc.gridx = 1;
        panel.add(timeField, gbc);

        JButton openButton = new JButton("Abrir Taquilla");
        openButton.setBackground(new Color(33, 150, 243));
        openButton.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 5, 5, 5);
        panel.add(openButton, gbc);

        add(panel);
    }
}
