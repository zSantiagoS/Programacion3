import javax.swing.*;
import java.awt.*;

public class CloseTaquillaView extends JFrame {

    public CloseTaquillaView() {
        setTitle("Cerrar Taquilla");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Cerrar Taquilla para Evento");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        JButton closeButton = new JButton("Cerrar Taquilla");
        closeButton.setBackground(new Color(33, 150, 243));
        closeButton.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 5, 5, 5);
        panel.add(closeButton, gbc);

        add(panel);
    }
}
