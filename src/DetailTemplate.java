import javax.swing.*;
import java.awt.*;


public class DetailTemplate extends JPanel {

    private ScreenManager manager;
    private String title;
    private int occupied;
    private int free;

    public DetailTemplate(ScreenManager manager, String title, int occupied, int free) {
        this.manager = manager;
        this.title = title;
        this.occupied = occupied;
        this.free = free;

        setLayout(new BorderLayout());
        setBackground(new Color(208, 255, 242));


        JPanel topBox = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(207, 255, 150)); // lime
                g2.fillRoundRect(10, 6, getWidth()-20, getHeight()-12, 28, 28);
            }
        };
        topBox.setOpaque(false);
        topBox.setPreferredSize(new Dimension(420, 84));
        topBox.setLayout(new GridBagLayout());
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        titleLabel.setForeground(new Color(10, 35, 150)); // deep blue
        topBox.add(titleLabel);
        add(topBox, BorderLayout.NORTH);


        JPanel center = new JPanel();
        center.setOpaque(false);
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));


        JPanel pills = new JPanel();
        pills.setOpaque(false);
        pills.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 6));
        String[] pillTexts = {"notificatie geschiedenis", "download gegevens", "deel gegevens", "instellingen"};
        for (String p : pillTexts) {
            JLabel pill = new JLabel(p, SwingConstants.CENTER);
            pill.setOpaque(true);
            pill.setBackground(new Color(255, 90, 90)); // red pill
            pill.setForeground(Color.WHITE);
            pill.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));
            pill.setFont(new Font("Arial", Font.PLAIN, 12));
            pill.setPreferredSize(new Dimension(150, 28));
            pill.setHorizontalAlignment(SwingConstants.CENTER);
            pills.add(pill);
        }
        center.add(pills);
        center.add(Box.createVerticalStrut(8));


        JPanel bigBar = new JPanel();
        bigBar.setOpaque(false);
        bigBar.setLayout(new BorderLayout());
        JPanel redBar = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(255, 60, 70));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            }
        };
        redBar.setPreferredSize(new Dimension(380, 64));
        redBar.setOpaque(false);
        redBar.setLayout(new FlowLayout(FlowLayout.CENTER, 24, 12));

        JLabel bell = iconLabel("🔔");
        JLabel download = iconLabel("⬇");
        JLabel arrow = iconLabel("➡");
        JLabel gear = iconLabel("⚙");

        redBar.add(bell);
        redBar.add(download);
        redBar.add(arrow);
        redBar.add(gear);
        bigBar.add(redBar, BorderLayout.CENTER);
        center.add(bigBar);
        center.add(Box.createVerticalStrut(12));


        JPanel infoBox = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(200, 240, 230));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 18, 18);
            }
        };
        infoBox.setOpaque(false);
        infoBox.setPreferredSize(new Dimension(360, 72));
        infoBox.setMaximumSize(new Dimension(380, 90));
        infoBox.setLayout(new BorderLayout());
        JPanel infoInner = new JPanel();
        infoInner.setOpaque(false);
        infoInner.setLayout(new BoxLayout(infoInner, BoxLayout.Y_AXIS));
        JLabel meld = new JLabel("meldingen");
        meld.setFont(new Font("Arial", Font.BOLD, 16));
        meld.setForeground(new Color(10, 35, 150));
        JLabel sub = new JLabel(String.format("vak 3a is al %d uur leegstaand\n\ngemiddelde bezetting", free), SwingConstants.CENTER);
        sub.setFont(new Font("Arial", Font.PLAIN, 12));
        sub.setForeground(new Color(10, 35, 150));
        sub.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoInner.add(Box.createVerticalStrut(6));
        infoInner.add(meld);
        infoInner.add(Box.createVerticalStrut(4));
        infoInner.add(sub);
        infoBox.add(infoInner, BorderLayout.CENTER);
        center.add(infoBox);
        center.add(Box.createVerticalStrut(14));


        JPanel donutCard = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(250, 220, 200)); // peach card
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 36, 36);
            }
        };
        donutCard.setOpaque(false);
        donutCard.setPreferredSize(new Dimension(380, 360));
        donutCard.setMaximumSize(new Dimension(380, 360));
        donutCard.setLayout(new BorderLayout());


        DonutChart chart = new DonutChart(occupied, free);
        chart.setPreferredSize(new Dimension(320, 260));
        chart.setOpaque(false);


        JPanel legend = new JPanel();
        legend.setBackground(new Color(250, 220, 200));
        legend.setOpaque(false);
        legend.setLayout(new GridLayout(1, 2));
        JLabel left = new JLabel(String.format("<html><center>leeg<br>%d</center></html>", free));
        left.setHorizontalAlignment(SwingConstants.CENTER);
        left.setFont(new Font("Arial", Font.BOLD, 14));
        left.setForeground(new Color(50, 50, 50));
        JLabel right = new JLabel(String.format("<html><center>bezet<br>%d</center></html>", occupied));
        right.setHorizontalAlignment(SwingConstants.CENTER);
        right.setFont(new Font("Arial", Font.BOLD, 14));
        right.setForeground(new Color(150, 20, 20));
        legend.add(left);
        legend.add(right);

        JPanel chartWrap = new JPanel();
        chartWrap.setOpaque(false);
        chartWrap.setLayout(new BoxLayout(chartWrap, BoxLayout.Y_AXIS));
        chartWrap.add(Box.createVerticalStrut(12));
        JPanel centerChart = new JPanel(new GridBagLayout());
        centerChart.setOpaque(false);
        centerChart.add(chart);
        chartWrap.add(centerChart);
        chartWrap.add(Box.createVerticalStrut(8));
        chartWrap.add(legend);

        donutCard.add(chartWrap, BorderLayout.CENTER);
        center.add(donutCard);
        center.add(Box.createVerticalStrut(14));


        JPanel bottomBtnWrap = new JPanel();
        bottomBtnWrap.setOpaque(false);
        bottomBtnWrap.setLayout(new GridBagLayout());
        JButton navigateBtn = new JButton("navigeer naar locatie   📱");
        navigateBtn.setPreferredSize(new Dimension(340, 64));
        navigateBtn.setBackground(new Color(60, 60, 160)); // purple-blue
        navigateBtn.setForeground(Color.WHITE);
        navigateBtn.setFont(new Font("Arial", Font.BOLD, 18));
        navigateBtn.setFocusPainted(false);
        navigateBtn.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));
        navigateBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Navigating to " + title);
        });
        bottomBtnWrap.add(navigateBtn);
        center.add(bottomBtnWrap);
        center.add(Box.createVerticalStrut(14));


        JScrollPane sp = new JScrollPane(center, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setBorder(null);
        sp.getViewport().setOpaque(false);
        sp.setOpaque(false);
        add(sp, BorderLayout.CENTER);


        JButton backBtn = new JButton("←");
        backBtn.setFocusPainted(false);
        backBtn.setContentAreaFilled(false);
        backBtn.addActionListener(e -> manager.showScreen(ScreenManager.DASHBOARD));
        backBtn.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));

        JPanel backWrap = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backWrap.setOpaque(false);
        backWrap.setBorder(BorderFactory.createEmptyBorder(6, 8, 0, 0));
        backWrap.add(backBtn);
        add(backWrap, BorderLayout.PAGE_START);
    }

    private static JLabel iconLabel(String emoji) {
        JLabel l = new JLabel(emoji);
        l.setFont(new Font("Dialog", Font.PLAIN, 22));
        l.setForeground(Color.WHITE);
        l.setHorizontalAlignment(SwingConstants.CENTER);
        l.setPreferredSize(new Dimension(54, 40));
        return l;
    }


    public static class DonutChart extends JComponent {
        private final int occupied;
        private final int free;

        public DonutChart(int occupied, int free) {
            this.occupied = occupied;
            this.free = free;
            setPreferredSize(new Dimension(260, 260));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int total = Math.max(occupied + free, 1);
            double occAngle = 360.0 * occupied / total;
            double freeAngle = 360.0 * free / total;

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();
            int size = Math.min(w, h) - 20;
            int x = (w - size) / 2;
            int y = (h - size) / 2;

            //red donut
            g2.setColor(new Color(220, 40, 50));
            g2.fillArc(x, y, size, size, 90, (int) -Math.round(occAngle));

            //green donut
            g2.setColor(new Color(100, 200, 100));
            g2.fillArc(x, y, size, size, 90 - (int) Math.round(occAngle), (int) -Math.round(freeAngle));


            int inner = (int) (size * 0.5);
            int ix = x + (size - inner) / 2;
            int iy = y + (size - inner) / 2;
            g2.setColor(getParent() != null ? getParent().getBackground() : new Color(250, 220, 200));
            g2.fillOval(ix, iy, inner, inner);


            double percent = 100.0 * occupied / total;
            String pct = String.format("%.0f%%", percent);
            g2.setColor(new Color(10, 35, 150));
            g2.setFont(getFont().deriveFont(Font.BOLD, 20f));
            FontMetrics fm = g2.getFontMetrics();
            int tx = (w - fm.stringWidth(pct)) / 2;
            int ty = (h + fm.getAscent()) / 2 - 6;
            g2.drawString(pct, tx, ty);

            g2.dispose();
        }
    }
}
