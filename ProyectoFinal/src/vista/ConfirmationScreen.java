import javax.swing.*;
import java.awt.*;

public class ConfirmationScreen extends JFrame {
    private JLabel confirmationLabel;
    private JButton okButton;

    public ConfirmationScreen(String message) {
        setTitle("Confirmación");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        confirmationLabel = new JLabel(message, SwingConstants.CENTER);
        confirmationLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        confirmationLabel.setForeground(new Color(33, 150, 243));
        panel.add(confirmationLabel, BorderLayout.CENTER);

        okButton = new JButton("Aceptar");
        okButton.setBackground(new Color(33, 150, 243));
        okButton.setForeground(Color.WHITE);
        panel.add(okButton, BorderLayout.SOUTH);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConfirmationScreen("Compra realizada con éxito!").setVisible(true));
    }
}
