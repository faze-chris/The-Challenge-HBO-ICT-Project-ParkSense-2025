import javax.swing.*;
import java.awt.*;

public class ScreenManager extends JFrame {

    public static final String WALDORF = "WALDORF";
    public static final String KONING = "KONING";
    public static final String GEDEMPTE = "GEDEMPTE";
    public static final String LOGIN = "login";
    public static final String DASHBOARD = "dashboard";
    public static final String HISTORY = "history";

    private CardLayout cardLayout;
    private JPanel cards;

    public ScreenManager() {
        setTitle("ParkSense");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(430, 820);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        // Create screens
        LoginPanel loginPanel = new LoginPanel(this);
        WaldorfDetailPanel waldorf = new WaldorfDetailPanel(this);
        KoningDetailPanel koning = new KoningDetailPanel(this);
        GedempteDetailPanel gedempte = new GedempteDetailPanel(this);
        HistoryScreen historyScreen = new HistoryScreen(this);

        // Add screens to cards
        cards.add(loginPanel, LOGIN);
        cards.add(waldorf, WALDORF);
        cards.add(koning, KONING);
        cards.add(gedempte, GEDEMPTE);
        cards.add(historyScreen, HISTORY);

        add(cards);
        showScreen(LOGIN);
    }

    /**
     * Switch to a screen by name
     */
    public void showScreen(String name) {
        cardLayout.show(cards, name);
    }

    /**
     * Creates or refreshes the dashboard for the logged-in user
     */
    public void showDashboard(User user) {
        // Remove existing dashboard if it exists
        for (Component comp : cards.getComponents()) {
            if (comp instanceof Dashboard) {
                cards.remove(comp);
                break;
            }
        }

        // Create new dashboard with user info
        Dashboard dashboard = new Dashboard(this, user);
        cards.add(dashboard, DASHBOARD);

        showScreen(DASHBOARD);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ScreenManager().setVisible(true);
        });
    }
}
