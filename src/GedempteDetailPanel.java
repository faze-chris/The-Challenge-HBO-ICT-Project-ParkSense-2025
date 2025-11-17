import javax.swing.*;
import java.awt.*;        // <-- required

public class GedempteDetailPanel extends JPanel {
    public GedempteDetailPanel(ScreenManager manager) {
        setLayout(new BorderLayout());
        add(new DetailTemplate(manager, "gedempte gracht", 8, 6), BorderLayout.CENTER);
    }
}
