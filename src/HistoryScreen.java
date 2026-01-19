import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class HistoryScreen extends JPanel {

    private static final String URL = "jdbc:mysql://localhost:3306/challenge_2025?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private JTable table;
    private DefaultTableModel model;

    public HistoryScreen(ScreenManager manager) {
        setLayout(new BorderLayout());

        model = new DefaultTableModel();
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Reload button
        JButton reloadBtn = new JButton("↺");
        reloadBtn.setPreferredSize(new Dimension(60, 60));
        reloadBtn.setMaximumSize(new Dimension(60, 60));
        reloadBtn.setBackground(new Color(24, 160, 90));
        reloadBtn.setForeground(Color.WHITE);
        reloadBtn.setFocusPainted(false);
        reloadBtn.setFont(reloadBtn.getFont().deriveFont(Font.BOLD, 20f));
        reloadBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        reloadBtn.addActionListener(e -> refresh());

        // Top panel with back button
        JButton backBtn = new JButton("← Back");
        backBtn.addActionListener(e -> manager.showScreen(ScreenManager.DASHBOARD));
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(backBtn);
        topPanel.add(reloadBtn);
        add(topPanel, BorderLayout.NORTH);

        refresh();


    }

    public void refresh() {
        model.setRowCount(0);
        model.setColumnCount(0);

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM parking_history")) {

            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(meta.getColumnName(i));
            }

            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching data:\n" + e.getMessage());
            e.printStackTrace();
        }

        }
    }

