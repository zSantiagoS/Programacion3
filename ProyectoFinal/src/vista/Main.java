import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Crear instancias de las interfaces
            LoginScreen loginScreen = new LoginScreen();
            AdminScreen adminScreen = new AdminScreen();
            EventCatalog eventCatalog = new EventCatalog();
            CloseTaquillaView closeTaquillaView = new CloseTaquillaView();
            ConfirmationScreen confirmationScreen = new ConfirmationScreen("Compra realizada con éxito!");
            EventTicketCounter eventTicketCounter = new EventTicketCounter("Concierto de Jazz");
            RegisterEventView registerEventView = new RegisterEventView();
            RegisterLocationView registerLocationView = new RegisterLocationView();

            // Mostrar la pantalla de inicio de sesión del usuario
            loginScreen.setVisible(true);

            // Mostrar el panel de administración cuando se cierre la pantalla de inicio de sesión del usuario
            loginScreen.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    adminScreen.setVisible(true);
                }
            });

            // Mostrar el catálogo de eventos cuando se cierre el panel de administración
            adminScreen.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    eventCatalog.setVisible(true);
                }
            });

            // Mostrar la taquilla virtual cuando se seleccione un evento en el catálogo de eventos
            eventCatalog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    eventTicketCounter.setVisible(true);
                }
            });

            // Otros eventos y acciones...
        });
    }
}
