import javax.swing.*;

public class login extends JFrame {

    // These belong to your login form, not inside a nested class
    private JPasswordField passwordPasswordField;
    private JFormattedTextField exampleFormattedTextField;
    private JCheckBox rememberMeCheckBox;
    private JButton forgotPasswordButton;
    private JButton logInButton;
    private JButton signUpButton;

    public login() {
        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Build a very basic UI just to show components
        JPanel panel = new JPanel();
        add(panel);

        passwordPasswordField = new JPasswordField(15);
        exampleFormattedTextField = new JFormattedTextField();
        rememberMeCheckBox = new JCheckBox("Remember me");
        forgotPasswordButton = new JButton("Forgot Password");
        logInButton = new JButton("Log In");
        signUpButton = new JButton("Sign Up");

        panel.add(passwordPasswordField);
        panel.add(exampleFormattedTextField);
        panel.add(rememberMeCheckBox);
        panel.add(forgotPasswordButton);
        panel.add(logInButton);
        panel.add(signUpButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new login().setVisible(true);
        });
    }
}

