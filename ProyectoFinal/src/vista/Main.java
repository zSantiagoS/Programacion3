import javax.swing.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        // Ejecutar cada interfaz en un hilo separado
        executor.execute(() -> {
            LoginScreen loginScreen = new LoginScreen();
            loginScreen.setVisible(true);
            loginScreen.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    executor.execute(() -> {
                        AdminScreen adminScreen = new AdminScreen();
                        adminScreen.setVisible(true);
                        adminScreen.addWindowListener(new java.awt.event.WindowAdapter() {
                            @Override
                            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                                executor.execute(() -> {
                                    EventCatalog eventCatalog = new EventCatalog();
                                    eventCatalog.setVisible(true);
                                    eventCatalog.addWindowListener(new java.awt.event.WindowAdapter() {
                                        @Override
                                        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                                            executor.execute(() -> {
                                                EventTicketCounter eventTicketCounter = new EventTicketCounter("Concierto de Jazz");
                                                eventTicketCounter.setVisible(true);
                                            });
                                        }
                                    });
                                });
                            }
                        });
                    });
                }
            });
        });

        // Apagar el executor cuando todas las tareas estén completas
        executor.shutdown();
    }
}
