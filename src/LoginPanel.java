import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    private ScreenManager manager;
    private RoundedTextField emailField;
    private RoundedPasswordField passwordField;


    private final String CORRECT_EMAIL = "admin@example.com";
    private final String CORRECT_PASSWORD = "12345";

    public LoginPanel(ScreenManager manager) {
        this.manager = manager;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel content = new JPanel();
        content.setBackground(Color.WHITE);
        content.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("parksence");
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);


        ImageIcon icon = new ImageIcon("logo.png");
        JLabel imgLabel;
        if (icon.getIconWidth() > 0) {
            Image scaled = icon.getImage().getScaledInstance(250, 140, Image.SCALE_SMOOTH);
            imgLabel = new JLabel(new ImageIcon(scaled));
        } else {
            imgLabel = new JLabel();
            imgLabel.setPreferredSize(new Dimension(250, 140));
        }
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel welcome = new JLabel("welcome");
        welcome.setFont(new Font("Arial", Font.BOLD, 22));
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);

        emailField = new RoundedTextField("");
        emailField.setMaximumSize(new Dimension(300, 44));
        emailField.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailField.setHint("example@gmail.com");

        passwordField = new RoundedPasswordField("");
        passwordField.setMaximumSize(new Dimension(300, 44));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField.setHint("password");

        JCheckBox remember = new JCheckBox("Remember password");
        remember.setOpaque(false);

        JLabel forgot = new JLabel("Forgot password?");
        forgot.setFont(new Font("Arial", Font.PLAIN, 12));

        JPanel optionRow = new JPanel(new BorderLayout());
        optionRow.setOpaque(false);
        optionRow.add(remember, BorderLayout.WEST);
        optionRow.add(forgot, BorderLayout.EAST);
        optionRow.setMaximumSize(new Dimension(300, 30));

        JButton loginBtn = new JButton("LOG IN");
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginBtn.setPreferredSize(new Dimension(180, 40));
        loginBtn.setBackground(Color.BLACK);
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFocusPainted(false);


        JPanel signupPanel = new JPanel();
        signupPanel.setBackground(Color.BLACK);
        signupPanel.setMaximumSize(new Dimension(160, 60));
        signupPanel.setBorder(BorderFactory.createEmptyBorder(12, 10, 12, 10));
        signupPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        signupPanel.setLayout(new BoxLayout(signupPanel, BoxLayout.Y_AXIS));
        JLabel noAcc = new JLabel("No account?");
        noAcc.setForeground(Color.WHITE);
        noAcc.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel signUp = new JLabel("Sign up now");
        signUp.setForeground(Color.WHITE);
        signUp.setAlignmentX(Component.CENTER_ALIGNMENT);
        signupPanel.add(noAcc);
        signupPanel.add(signUp);

        content.add(Box.createVerticalStrut(10));
        content.add(title);
        content.add(Box.createVerticalStrut(10));
        content.add(imgLabel);
        content.add(Box.createVerticalStrut(8));
        content.add(welcome);
        content.add(Box.createVerticalStrut(18));
        content.add(emailField);
        content.add(Box.createVerticalStrut(12));
        content.add(passwordField);
        content.add(Box.createVerticalStrut(8));
        content.add(optionRow);
        content.add(Box.createVerticalStrut(16));
        content.add(loginBtn);
        content.add(Box.createVerticalStrut(18));
        content.add(signupPanel);

        add(content, BorderLayout.CENTER);


        JLabel help = new JLabel(" ");
        help.setHorizontalAlignment(SwingConstants.CENTER);
        help.setBorder(BorderFactory.createEmptyBorder(8,0,8,0));
        add(help, BorderLayout.SOUTH);


        loginBtn.addActionListener(e -> {
            String email = emailField.getText().trim();
            String pass = new String(passwordField.getPassword());

            if (email.equals(CORRECT_EMAIL) && pass.equals(CORRECT_PASSWORD)) {

                manager.showScreen(ScreenManager.DASHBOARD);
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect email or password",
                        "Login failed", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
