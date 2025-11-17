import javax.swing.*;
import java.awt.*;

/**
 * Rounded dark text field with white text.
 */
public class RoundedTextField extends JTextField {

    public RoundedTextField(String text) {
        super(text);
        setOpaque(false);                // we paint the background ourselves
        setForeground(Color.WHITE);
        setCaretColor(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12)); // internal padding
        setFont(getFont().deriveFont(14f));
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Paint rounded background then let super paint the text & caret
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // background fill
        g2.setColor(new Color(50, 50, 50));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Optionally paint border (here we rely on same color as background so it's invisible)
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // draw a faint border if you'd like:
        // g2.setColor(new Color(70,70,70));
        // g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 30, 30);
        g2.dispose();
    }
}
