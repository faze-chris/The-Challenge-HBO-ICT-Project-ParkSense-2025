import javax.swing.*;
import java.awt.*;

/**
 * Rounded dark password field with white text.
 */
public class RoundedPasswordField extends JPasswordField {

    public RoundedPasswordField(String text) {
        super(text);
        setOpaque(false);
        setForeground(Color.WHITE);
        setCaretColor(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        setFont(getFont().deriveFont(14f));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(new Color(50, 50, 50));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // same as text field — no visible border by default
    }
}
