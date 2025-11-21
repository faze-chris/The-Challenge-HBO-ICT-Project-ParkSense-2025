import javax.swing.*;
import java.awt.*;


public class RoundedPasswordField extends JPasswordField {

    private String hint = "";

    public RoundedPasswordField(String text) {
        super(text);
        setOpaque(false);
        setForeground(Color.WHITE);
        setCaretColor(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        setFont(getFont().deriveFont(14f));
    }


    public void setHint(String hint) {
        this.hint = hint;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(new Color(50, 50, 50));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

        g2.dispose();
        super.paintComponent(g);


        if (getPassword().length == 0 && hint != null && !hint.isEmpty()) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.GRAY);
            g2d.setFont(getFont().deriveFont(Font.ITALIC));
            g2d.drawString(hint, 14, getHeight() / 2 + 5);
            g2d.dispose();
        }
    }

    @Override
    protected void paintBorder(Graphics g) {

    }
}
