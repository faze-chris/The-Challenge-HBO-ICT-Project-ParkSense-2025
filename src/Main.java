public class Main {
    public static void main(String[] args) {
        // Always start Swing on the Event Dispatch Thread (EDT)
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Replace MyUI() with your UI class
                login ui = new login();
                ui.setVisible(true);
            }
        });
    }
}