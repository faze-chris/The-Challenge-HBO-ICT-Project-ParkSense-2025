import javax.swing.*;
import java.awt.*;

public class ScreenManager extends JFrame {

    public static final String LOGIN = "login";
    public static final String DASHBOARD = "dashboard";
    public static final String WALDORF = "waldorf";
    public static final String KONING = "koning";
    public static final String GEDEMPTE = "gedempte";

    private CardLayout cardLayout;
    private JPanel cards;

    public ScreenManager() {
        setTitle("ParkSense");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(430, 820);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        // Create panels
        LoginPanel loginPanel = new LoginPanel(this);
        DashboardPanel dashboardPanel = new DashboardPanel(this);
        WaldorfDetailPanel waldorfPanel = new WaldorfDetailPanel(this);
        KoningDetailPanel koningPanel = new KoningDetailPanel(this);
        GedempteDetailPanel gedemptePanel = new GedempteDetailPanel(this);

        // Add to card layout
        cards.add(loginPanel, LOGIN);
        cards.add(dashboardPanel, DASHBOARD);
        cards.add(waldorfPanel, WALDORF);
        cards.add(koningPanel, KONING);
        cards.add(gedemptePanel, GEDEMPTE);

        add(cards);
        showScreen(LOGIN);
    }

    public void showScreen(String name) {
        cardLayout.show(cards, name);
    }
}
