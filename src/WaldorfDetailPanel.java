import javax.swing.*;
import java.awt.*;

public class WaldorfDetailPanel extends JPanel {
    public WaldorfDetailPanel(ScreenManager manager) {
        setLayout(new BorderLayout());
        DarkDetailTemplate template = new DarkDetailTemplate(manager, "waldorpstraat", 10, 4);
        add(template, BorderLayout.CENTER);
    }
}
