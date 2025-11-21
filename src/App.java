import javax.swing.SwingUtilities;

// de startup, runt screenmanager die alle andere classes cycled

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ScreenManager manager = new ScreenManager();
            manager.setVisible(true);
        });
    }
}
