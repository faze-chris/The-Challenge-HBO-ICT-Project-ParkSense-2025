import javax.swing.*;
import java.awt.*;

public class WaldorfDetailPanel extends JPanel {
    public WaldorfDetailPanel(ScreenManager manager) {
        setLayout(new BorderLayout());
        add(new DetailTemplate(manager, "waldorpstraat", 10, 4), BorderLayout.CENTER);
    }
}
