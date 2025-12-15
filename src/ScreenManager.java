import javax.swing.*;
import java.awt.*;

public class ScreenManager extends JFrame {
    public static final String WALDORF = "WALDORF";
    public static final String KONING = "KONING";
    public static final String GEDEMPTE = "GEDEMPTE";
    public static final String LOGIN = "login";
    public static final String DASHBOARD = "dashboard";
    public static final String CODE_VIEW = "CODE_VIEW";

    private CardLayout cardLayout;
    private JPanel cards;

    public ScreenManager() {
        setTitle("ParkSense");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(430, 820);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        LoginPanel loginPanel = new LoginPanel(this);
        WaldorfDetailPanel waldorf = new WaldorfDetailPanel(this);
        KoningDetailPanel koning = new KoningDetailPanel(this);
        GedempteDetailPanel gedempte = new GedempteDetailPanel(this);
        CodeViewPanel codePanel = new CodeViewPanel();
        cards.add(loginPanel, LOGIN);
        cards.add(waldorf, WALDORF);
        cards.add(koning, KONING);
        cards.add(gedempte, GEDEMPTE);
        cards.add(codePanel, CODE_VIEW);

        add(cards);
        showScreen(LOGIN);
    }

    public void showScreen(String name) {
        cardLayout.show(cards, name);
    }

    public void showDashboard(User user) {
        try {
        } catch (Exception e) {
        }
        Dashboard dashboard = new Dashboard(this, user);
        cards.add(dashboard, DASHBOARD);

        showScreen(DASHBOARD);
    }
}