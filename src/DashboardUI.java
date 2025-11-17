import javax.swing.*;
import java.awt.*;

public class DashboardUI extends JFrame {

    public DashboardUI() {
        setTitle("Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Welcome!", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 26));
        add(label);
    }
}
