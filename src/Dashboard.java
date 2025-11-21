import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.net.URL;




public class Dashboard extends JPanel {

    private ScreenManager manager;

    public Dashboard(ScreenManager manager) {
        this.manager = manager;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);


        JPanel headerWrap = new JPanel();
        headerWrap.setOpaque(false);
        headerWrap.setLayout(new BoxLayout(headerWrap, BoxLayout.Y_AXIS));
        headerWrap.setBorder(BorderFactory.createEmptyBorder(18, 18, 10, 18));

        JPanel header = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(255, 225, 90));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
                g2.dispose();
            }
        };
        header.setPreferredSize(new Dimension(380, 80));
        header.setMaximumSize(new Dimension(400, 80));
        header.setLayout(new GridBagLayout());

        JLabel title = new JLabel("ParkSense");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        header.add(title);
        headerWrap.add(header);


        JPanel searchWrap = new JPanel();
        searchWrap.setBackground(Color.WHITE);
        searchWrap.setBorder(BorderFactory.createEmptyBorder(12, 18, 12, 18));
        searchWrap.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel roundedSearch = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(245, 245, 245));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 35, 35);
                g2.dispose();
            }
        };
        roundedSearch.setPreferredSize(new Dimension(360, 45));
        roundedSearch.setLayout(new BorderLayout(8, 8));
        JTextField searchField = new JTextField("Vul Straatnaam");
        searchField.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        searchField.setOpaque(false);
        roundedSearch.add(new JLabel("🔍"), BorderLayout.WEST);
        roundedSearch.add(searchField, BorderLayout.CENTER);
        roundedSearch.add(new JLabel("🎤"), BorderLayout.EAST);
        searchWrap.add(roundedSearch);


        JPanel cards = new JPanel();
        cards.setLayout(new BoxLayout(cards, BoxLayout.Y_AXIS));
        cards.setOpaque(false);
        cards.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));

        cards.add(Box.createVerticalStrut(8));
        cards.add(createLocationCard("Waldorfstraat", "/images/gedempte.jpg", ScreenManager.WALDORF));
        cards.add(Box.createVerticalStrut(12));
        cards.add(createLocationCard("Koningstraat", "/images/koning.jpg", ScreenManager.KONING));
        cards.add(Box.createVerticalStrut(12));
        cards.add(createLocationCard("Gedempte gracht", "/images/gedempte.jpg", ScreenManager.GEDEMPTE));
        cards.add(Box.createVerticalStrut(12));


        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setOpaque(false);
        center.add(headerWrap);
        center.add(searchWrap);
        center.add(cards);

        JScrollPane scroll = new JScrollPane(center, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getVerticalScrollBar().setUnitIncrement(12);
        scroll.setBorder(null);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);

        add(scroll, BorderLayout.CENTER);


        JPanel bottomNav = new JPanel(new GridLayout(1, 3));
        bottomNav.setPreferredSize(new Dimension(400, 70));
        bottomNav.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(220,220,220)));
        bottomNav.setBackground(Color.WHITE);

        bottomNav.add(makeBottomButton("Home", true, e -> manager.showScreen(ScreenManager.LOGIN)));
        bottomNav.add(makeBottomButton("Zoeken", false, e -> manager.showScreen(ScreenManager.DASHBOARD)));
        bottomNav.add(makeBottomButton("Instellingen", false, e -> {
            JOptionPane.showMessageDialog(this, "Settings not implemented yet.");
        }));

        add(bottomNav, BorderLayout.SOUTH);
    }
    private static BufferedImage highQualityScale(BufferedImage src, int targetW, int targetH) {
        int w = src.getWidth();
        int h = src.getHeight();

        BufferedImage img = src;

        // Downscale in multiple half-steps for better quality
        do {
            if (w > targetW) {
                w = Math.max(w / 2, targetW);
            }
            if (h > targetH) {
                h = Math.max(h / 2, targetH);
            }

            BufferedImage tmp = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = tmp.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2.drawImage(img, 0, 0, w, h, null);
            g2.dispose();
            img = tmp;

        } while (w != targetW || h != targetH);

        return img;
    }

    private JPanel createLocationCard(String titleText, String imagePath, String destination) {
        JPanel wrapper = new JPanel();
        Dimension d = new Dimension(380, 140);
        wrapper.setOpaque(false);
        wrapper.setLayout(new BorderLayout());
        wrapper.setMinimumSize(d);
        wrapper.setMaximumSize(d);
        wrapper.setPreferredSize(d);


        ImageIcon icon = new ImageIcon(Dashboard.class.getResource(imagePath));

        JLabel imageLabel = new JLabel() {

            private Image img;   // <-- FIELD inside the label

            {

                try {
                    URL url = Dashboard.class.getResource(imagePath);
                    BufferedImage original = ImageIO.read(url);

                    img = highQualityScale(original, 380, 140);

                } catch (Exception ex) {
                    ex.printStackTrace();

                    BufferedImagePlaceholder placeholder = new BufferedImagePlaceholder(380, 140);
                    img = placeholder.getImage();
                }

            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Shape clip = new java.awt.geom.RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 28, 28);
                g2.setClip(clip);
                g2.drawImage(img, 0, 0, getWidth(), getHeight(), null);
                g2.dispose();
            }
        };
        imageLabel.setLayout(new BorderLayout());
        imageLabel.setPreferredSize(d);
        imageLabel.setMinimumSize(d);
        imageLabel.setMaximumSize(d);

        JLabel label = new JLabel(titleText);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        imageLabel.add(label, BorderLayout.SOUTH);


        JPanel overlay = new JPanel(new BorderLayout());
        overlay.setOpaque(false);
        overlay.add(imageLabel, BorderLayout.CENTER);


        overlay.addMouseListener(new MouseAdapter() {
            Color shadow = new Color(0, 0, 0, 60);
            @Override
            public void mouseEntered(MouseEvent e) {
                overlay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                overlay.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(4,4,4,4),
                        BorderFactory.createMatteBorder(0,0,8,0, shadow)
                ));
                overlay.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                overlay.setCursor(Cursor.getDefaultCursor());
                overlay.setBorder(null);
                overlay.repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                manager.showScreen(destination);
            }
        });

        wrapper.add(overlay, BorderLayout.CENTER);
        return wrapper;
    }

    private JPanel makeBottomButton(String text, boolean isHome, java.awt.event.ActionListener action) {
        JPanel p = new JPanel();
        p.setOpaque(false);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBorder(BorderFactory.createEmptyBorder(6,6,6,6));

        JButton btn = new JButton(text);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setForeground(isHome ? new Color(240,200,0) : Color.GRAY);

        btn.addActionListener(action);

        p.add(btn);
        return p;
    }


    private static class BufferedImagePlaceholder {
        private final Image image;
        public BufferedImagePlaceholder(int w, int h) {
            BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = img.createGraphics();
            g.setColor(new Color(200,200,200));
            g.fillRect(0,0,w,h);
            g.setColor(new Color(180,180,180));
            g.drawLine(0,0,w,h);
            g.drawLine(w,0,0,h);
            g.dispose();
            image = img;
        }
        public Image getImage() { return image; }
    }
}
