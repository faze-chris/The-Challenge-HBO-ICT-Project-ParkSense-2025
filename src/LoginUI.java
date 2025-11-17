import javax.swing.*;
import java.awt.*;

public class LoginUI extends JFrame {

    // Correct login credentials
    private final String CORRECT_EMAIL = "admin@example.com";
    private final String CORRECT_PASSWORD = "12345";

    // UI components
    private RoundedTextField emailField;
    private RoundedPasswordField passwordField;

    public LoginUI() {
        setTitle("parksence - Login");
        setSize(420, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Gradient background panel
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

        // Image placeholder (change this to your image file)
        ImageIcon icon = new ImageIcon("parking.jpg");
        Image scaled = icon.getImage().getScaledInstance(250, 150, Image.SCALE_SMOOTH);
        JLabel imgLabel = new JLabel(new ImageIcon(scaled));
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Subtitle
        JLabel welcome = new JLabel("welcome");
        welcome.setFont(new Font("Arial", Font.BOLD, 22));
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Email field
        emailField = new RoundedTextField("example@gmail.com");
        emailField.setMaximumSize(new Dimension(270, 40));

        // Password field
        passwordField = new RoundedPasswordField("password");
        passwordField.setMaximumSize(new Dimension(270, 40));

        // Remember + forgot row
        JPanel optionRow = new JPanel(new BorderLayout());
        optionRow.setOpaque(false);

        JCheckBox rememberCB = new JCheckBox("Remember password");
        rememberCB.setOpaque(false);

        JLabel forgotLabel = new JLabel("Forgot password?");
        forgotLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        optionRow.add(rememberCB, BorderLayout.WEST);
        optionRow.add(forgotLabel, BorderLayout.EAST);

        // Login button
        JButton loginBtn = new JButton("LOG IN");
        loginBtn.setPreferredSize(new Dimension(200, 40));
        loginBtn.setBackground(Color.BLACK);
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFocusPainted(false);
        loginBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Sign-up section
        JPanel signupPanel = new JPanel();
        signupPanel.setLayout(new BoxLayout(signupPanel, BoxLayout.Y_AXIS));
        signupPanel.setBackground(Color.BLACK);
        signupPanel.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        signupPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel noAcc = new JLabel("No account?");
        noAcc.setForeground(Color.WHITE);

        JLabel signupLabel = new JLabel("Sign up now");
        signupLabel.setForeground(Color.WHITE);

        signupPanel.add(noAcc);
        signupPanel.add(signupLabel);

        // ADD ALL COMPONENTS
        gradientPanel.add(title);
        gradientPanel.add(Box.createVerticalStrut(20));
        gradientPanel.add(imgLabel);
        gradientPanel.add(Box.createVerticalStrut(20));
        gradientPanel.add(welcome);
        gradientPanel.add(Box.createVerticalStrut(25));
        gradientPanel.add(emailField);
        gradientPanel.add(Box.createVerticalStrut(15));
        gradientPanel.add(passwordField);
        gradientPanel.add(Box.createVerticalStrut(10));
        gradientPanel.add(optionRow);
        gradientPanel.add(Box.createVerticalStrut(25));
        gradientPanel.add(loginBtn);
        gradientPanel.add(Box.createVerticalStrut(30));
        gradientPanel.add(signupPanel);

        add(gradientPanel);

        // ✔ LOGIN BUTTON FUNCTIONALITY
        loginBtn.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            if (email.equals(CORRECT_EMAIL) && password.equals(CORRECT_PASSWORD)) {

                new DashboardUI().setVisible(true); // open next UI
                dispose(); // close login window

            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Incorrect email or password",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }
}
