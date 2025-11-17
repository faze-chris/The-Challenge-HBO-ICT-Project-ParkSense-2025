import javax.swing.*;
import java.awt.*;

public class GedempteDetailPanel extends JPanel {

    private ScreenManager manager;

    public GedempteDetailPanel(ScreenManager manager) {
        this.manager = manager;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(Color.WHITE);
        top.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        JButton back = new JButton("←");
        back.setFocusPainted(false);
        back.setContentAreaFilled(false);
        back.addActionListener(e -> manager.showScreen(ScreenManager.DASHBOARD));
        top.add(back, BorderLayout.WEST);

        JLabel title = new JLabel("Gedempte gracht");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        top.add(title, BorderLayout.CENTER);

        add(top, BorderLayout.NORTH);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(Color.WHITE);
        content.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        ImageIcon icon = new ImageIcon("gedempte.jpg");
        JLabel img;
        if (icon.getIconWidth() > 0) {
            Image s = icon.getImage().getScaledInstance(380, 220, Image.SCALE_SMOOTH);
            img = new JLabel(new ImageIcon(s));
        } else {
            img = new JLabel();
            img.setPreferredSize(new Dimension(380,220));
            img.setOpaque(true);
            img.setBackground(new Color(230,230,230));
        }
        img.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(img);
        content.add(Box.createVerticalStrut(12));

        JTextArea info = new JTextArea("Information about Gedempte gracht...\n\nEvents, closures, sensors info.");
        info.setLineWrap(true);
        info.setWrapStyleWord(true);
        info.setEditable(false);
        info.setFont(new Font("Arial", Font.PLAIN, 14));
        info.setBackground(Color.WHITE);
        content.add(info);

        add(new JScrollPane(content), BorderLayout.CENTER);
    }
}
