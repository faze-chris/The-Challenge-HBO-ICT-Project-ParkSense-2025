import javax.swing.*;
import java.awt.*;

public class LoginUI extends JFrame {

    public LoginUI() {
        setTitle("parksence - Login");
        setSize(420, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Gradient panel
        JPanel gradientPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(200, 200, 200),
                        getWidth(), getHeight(), Color.WHITE
                );
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        gradientPanel.setLayout(new BoxLayout(gradientPanel, BoxLayout.Y_AXIS));
        gradientPanel.setBorder(BorderFactory.createEmptyBorder(40, 30, 40, 30));

        // Title
        JLabel title = new JLabel("parksence");
        title.setFont(new Font("Arial", Font.BOLD, 38));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Image
        ImageIcon icon = new ImageIcon("parking.jpg"); // replace path
        Image scaled = icon.getImage().getScaledInstance(250, 150, Image.SCALE_SMOOTH);
        JLabel imgLabel = new JLabel(new ImageIcon(scaled));
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Subtitle
        JLabel welcome = new JLabel("welcome");
        welcome.setFont(new Font("Arial", Font.BOLD, 22));
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Email field
        RoundedTextField emailField = new RoundedTextField("example@gmail.com");
        emailField.setPreferredSize(new Dimension(270, 40));

        // Password field
        RoundedPasswordField passwordField = new RoundedPasswordField("password");
        passwordField.setPreferredSize(new Dimension(270, 40));

        // Remember + forgot
        JPanel optionRow = new JPanel(new BorderLayout());
        optionRow.setOpaque(false);
        JCheckBox remember = new JCheckBox("Remember password");
        remember.setOpaque(false);

        JLabel forgot = new JLabel("Forgot password?");
        forgot.setFont(new Font("Arial", Font.PLAIN, 12));
        forgot.setForeground(Color.BLACK);

        optionRow.add(remember, BorderLayout.WEST);
        optionRow.add(forgot, BorderLayout.EAST);

        // Login button
        JButton loginBtn = new JButton("LOG IN");
        loginBtn.setPreferredSize(new Dimension(200, 40));
        loginBtn.setBackground(Color.BLACK);
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFocusPainted(false);
        loginBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Sign-up panel
        JPanel signupPanel = new JPanel();
        signupPanel.setLayout(new BoxLayout(signupPanel, BoxLayout.Y_AXIS));
        signupPanel.setBackground(Color.BLACK);
        signupPanel.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        signupPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel noAcc = new JLabel("No account?");
        noAcc.setForeground(Color.WHITE);
        JLabel signup = new JLabel("Sign up now");
        signup.setForeground(Color.WHITE);

        signupPanel.add(noAcc);
        signupPanel.add(signup);

        // Add components to gradient panel
        gradientPanel.add(title);
        gradientPanel.add(Box.createVerticalStrut(20));
        gradientPanel.add(imgLabel);
        gradientPanel.add(Box.createVerticalStrut(20));
        gradientPanel.add(welcome);
        gradientPanel.add(Box.createVerticalStrut(20));
        gradientPanel.add(emailField);
        gradientPanel.add(Box.createVerticalStrut(15));
        gradientPanel.add(passwordField);
        gradientPanel.add(Box.createVerticalStrut(10));
        gradientPanel.add(optionRow);
        gradientPanel.add(Box.createVerticalStrut(20));
        gradientPanel.add(loginBtn);
        gradientPanel.add(Box.createVerticalStrut(25));
        gradientPanel.add(signupPanel);

        add(gradientPanel);
    }
}
