import javax.swing.*;
import java.awt.*;        // <-- required

public class KoningDetailPanel extends JPanel {
    public KoningDetailPanel(ScreenManager manager) {
        setLayout(new BorderLayout());
        add(new DetailTemplate(manager, "koningstraat", 6, 8), BorderLayout.CENTER);
    }
}
