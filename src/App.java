import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ScreenManager manager = new ScreenManager();
            manager.setVisible(true);
        });
    }
}
