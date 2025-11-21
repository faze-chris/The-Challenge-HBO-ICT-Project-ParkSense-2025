import javax.swing.*;
import java.awt.*;

public class GedempteDetailPanel extends JPanel {
    public GedempteDetailPanel(ScreenManager manager) {
        setLayout(new BorderLayout());
        DarkDetailTemplate template = new DarkDetailTemplate(manager, "gedempte gracht", 8, 5);
        add(template, BorderLayout.CENTER);
    }
}
