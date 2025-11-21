import javax.swing.*;
import java.awt.*;

public class KoningDetailPanel extends JPanel {
    public KoningDetailPanel(ScreenManager manager) {
        setLayout(new BorderLayout());
        DarkDetailTemplate template = new DarkDetailTemplate(manager, "koningstraat", 7, 6);
        add(template, BorderLayout.CENTER);
    }
}
