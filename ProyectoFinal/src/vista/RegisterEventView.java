import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class RegisterEventView extends JFrame {

    private JTextField eventField;
    private JTextField dateField;
    private JComboBox<String> categoryComboBox;
    private JButton registerButton;
    private JButton datePickerButton;

    public RegisterEventView() {
        setTitle("Registrar Evento");
        setSize(400, 300); // Aumenté la altura para acomodar más componentes
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

        eventField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(eventField, gbc);

        JLabel dateLabel = new JLabel("Fecha:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(dateLabel, gbc);

        dateField = new JTextField(10);
        gbc.gridx = 1;
        panel.add(dateField, gbc);

        datePickerButton = new JButton("Seleccionar Fecha");
        datePickerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                dateField.setText(new SimpleDatePicker(RegisterEventView.this).setPickedDate());
            }
        });
        gbc.gridx = 2;
        panel.add(datePickerButton, gbc);

        JLabel categoryLabel = new JLabel("Categoría:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(categoryLabel, gbc);

        String[] categories = {"Categoria 1", "Categoria 2", "Categoria 3"}; // Ejemplo de opciones para el ComboBox
        categoryComboBox = new JComboBox<>(categories);
        gbc.gridx = 1;
        panel.add(categoryComboBox, gbc);

        registerButton = new JButton("Registrar");
        registerButton.setBackground(new Color(33, 150, 243));
        registerButton.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 5, 5, 5);
        panel.add(registerButton, gbc);

        add(panel);
    }

    public JTextField getEventField() {
        return eventField;
    }

    public JTextField getDateField() {
        return dateField;
    }

    public JComboBox<String> getCategoryComboBox() {
        return categoryComboBox;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    
    class SimpleDatePicker extends JPanel {
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int year = Calendar.getInstance().get(Calendar.YEAR);
        JLabel l = new JLabel("", JLabel.CENTER);
        String day = "";
        JDialog d;
        JButton[] button = new JButton[49];

        public SimpleDatePicker(JFrame parent) {
            d = new JDialog();
            d.setModal(true);
            String[] header = {"Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"};
            JPanel p1 = new JPanel(new GridLayout(7, 7));
            p1.setPreferredSize(new Dimension(700, 200));

            for (int x = 0; x < button.length; x++) {
                final int selection = x;
                button[x] = new JButton();
                button[x].setFocusPainted(false);
                button[x].setBackground(Color.white);
                if (x > 6) {
                    button[x].addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent ae) {
                            day = button[selection].getActionCommand();
                            d.dispose();
                        }
                    });
                }
                if (x < 7) {
                    button[x].setText(header[x]);
                    button[x].setForeground(Color.red);
                }
                p1.add(button[x]);
            }
            JPanel p2 = new JPanel(new GridLayout(1, 3));
            JButton previous = new JButton("<< Anterior");
            previous.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    month--;
                    displayDate();
                }
            });
            p2.add(previous);
            p2.add(l);
            JButton next = new JButton("Siguiente >>");
            next.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    month++;
                    displayDate();
                }
            });
            p2.add(next);
            d.add(p1, BorderLayout.CENTER);
            d.add(p2, BorderLayout.SOUTH);
            d.pack();
            d.setLocationRelativeTo(parent);
            displayDate();
            d.setVisible(true);
        }

        public void displayDate() {
            for (int x = 7; x < button.length; x++)
                button[x].setText("");
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MMMM yyyy");
            Calendar cal = new GregorianCalendar(year, month, 1);
            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++)
                button[x].setText("" + day);
            l.setText(sdf.format(cal.getTime()));
            d.setTitle("Seleccione la fecha");
        }

        public String setPickedDate() {
            if (day.equals(""))
                return day;
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
            Calendar cal = new GregorianCalendar(year, month, Integer.parseInt(day));
            return sdf.format(cal.getTime());
        }
    }
}
