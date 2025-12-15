import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class CodeViewPanel extends JPanel {

    private JTextArea codeArea;
    private JLabel statusLabel;

    public CodeViewPanel() {
        // 1. Layout & Styling (Dark Theme)
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 30)); // Dark grey background

        // 2. Header Title
        JLabel title = new JLabel("Uploaded C++ Code", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        // 3. The Text Area (Where code goes)
        codeArea = new JTextArea();
        codeArea.setFont(new Font("Monospaced", Font.PLAIN, 14)); // Code font
        codeArea.setBackground(new Color(45, 45, 45));
        codeArea.setForeground(new Color(76, 175, 80)); // Hacker Green text
        codeArea.setCaretColor(Color.WHITE);
        codeArea.setEditable(false); // Read-only

        // Add scroll bars
        JScrollPane scrollPane = new JScrollPane(codeArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(scrollPane, BorderLayout.CENTER);

        // 4. Bottom Button Panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(30, 30, 30));

        JButton refreshButton = new JButton("Refresh Code");
        refreshButton.addActionListener(e -> fetchCode()); // Click to reload
        bottomPanel.add(refreshButton);

        statusLabel = new JLabel("Ready");
        statusLabel.setForeground(Color.LIGHT_GRAY);
        bottomPanel.add(statusLabel);

        add(bottomPanel, BorderLayout.SOUTH);

        // 5. Load code automatically when screen opens
        fetchCode();
    }

    private void fetchCode() {
        String url = "jdbc:mysql://localhost:3306/parkingsystem";
        String user = "root";
        String pass = "";

        statusLabel.setText("Data ophalen...");

        new Thread(() -> {
            try {
                Connection conn = DriverManager.getConnection(url, user, pass);
                String sql = "SELECT * FROM arduino_log ORDER BY tijdstip DESC";

                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();

                StringBuilder logText = new StringBuilder();
                logText.append("=== ARDUINO SENSOR LOGBOEK ===\n\n");

                int count = 0;
                while (rs.next()) {
                    count++;
                    String tijd = rs.getString("tijdstip");
                    String sensor = rs.getString("sensor_naam");
                    String bericht = rs.getString("bericht");

                    logText.append("[" + tijd + "] " + sensor + ": " + bericht + "\n");
                }

                // ... de while loop is klaar ...

                if (count == 0) {
                    logText.append("Nog geen data ontvangen.");
                }

                String finalText = logText.toString();

                // --- DE FIX ZIT HIER ---
                int finalCount = count; // Maak een vaste kopie van de teller

                SwingUtilities.invokeLater(() -> {
                    codeArea.setText(finalText);
                    codeArea.setCaretPosition(0);
                    // Gebruik nu 'finalCount' in plaats van 'count'
                    statusLabel.setText("Aantal meldingen: " + finalCount);
                });

                conn.close();

            } catch (Exception e) {
                e.printStackTrace();
                SwingUtilities.invokeLater(() -> {
                    codeArea.setText("Fout:\n" + e.getMessage());
                });
            }
        }).start();
    }
}