import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;


public class DarkDetailTemplate extends JPanel {

    private final ScreenManager manager;
    private final String streetName;


    private DonutChart donut;
    private JLabel centerNumberLabel;
    private JLabel notificationLabel;
    private JLabel subtitleLabel;
    private JLabel occupiedLabel;
    private JLabel freeLabel;

    private final Random rnd = new Random();

    public DarkDetailTemplate(ScreenManager manager, String streetName, int initialOccupied, int initialFree) {
        this.manager = manager;
        this.streetName = streetName;
        setLayout(new BorderLayout());
        setBackground(new Color(12, 18, 22));


        JPanel topSpacer = new JPanel();
        topSpacer.setOpaque(false);
        topSpacer.setPreferredSize(new Dimension(10, 6));
        add(topSpacer, BorderLayout.NORTH);


        JPanel main = new JPanel();
        main.setOpaque(false);
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBorder(BorderFactory.createEmptyBorder(18, 16, 16, 16));


        JPanel profileCard = createRoundedPanel(new Color(34, 43, 51), 18);
        profileCard.setLayout(new BorderLayout(8, 8));
        profileCard.setMaximumSize(new Dimension(380, 84));
        profileCard.setPreferredSize(new Dimension(380, 84));


        JPanel leftProfile = new JPanel();
        leftProfile.setOpaque(false);
        leftProfile.setLayout(new BoxLayout(leftProfile, BoxLayout.Y_AXIS));
        JLabel hi = new JLabel("Hi,");
        hi.setForeground(Color.WHITE);
        hi.setFont(hi.getFont().deriveFont(Font.BOLD, 16f));
        JLabel phone = new JLabel("Admin 0078");
        phone.setForeground(new Color(180, 200, 210));
        phone.setFont(phone.getFont().deriveFont(12f));
        leftProfile.add(Box.createVerticalGlue());
        leftProfile.add(hi);
        leftProfile.add(phone);
        leftProfile.add(Box.createVerticalGlue());


        JButton profileBtn = new JButton("\uD83D\uDC64");
        profileBtn.setFocusPainted(false);
        profileBtn.setContentAreaFilled(false);
        profileBtn.setBorderPainted(false);
        profileBtn.setForeground(Color.WHITE);
        profileBtn.setFont(profileBtn.getFont().deriveFont(20f));
        profileBtn.addActionListener(e -> manager.showScreen(ScreenManager.LOGIN));

        profileCard.add(leftProfile, BorderLayout.WEST);
        profileCard.add(profileBtn, BorderLayout.EAST);


        JLabel streetTitle = new JLabel(streetName);
        streetTitle.setForeground(new Color(120, 255, 170));
        streetTitle.setFont(streetTitle.getFont().deriveFont(Font.BOLD, 22f));
        streetTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        main.add(profileCard);
        main.add(Box.createVerticalStrut(12));
        main.add(streetTitle);
        main.add(Box.createVerticalStrut(12));


        JPanel notifBox = createRoundedPanel(new Color(28, 40, 35), 14);
        notifBox.setMaximumSize(new Dimension(380, 68));
        notifBox.setPreferredSize(new Dimension(380, 68));
        notifBox.setLayout(new BorderLayout());
        notificationLabel = new JLabel("no notifications", SwingConstants.CENTER);
        notificationLabel.setForeground(new Color(170, 230, 190));
        notificationLabel.setFont(notificationLabel.getFont().deriveFont(Font.PLAIN, 13f));
        notifBox.add(notificationLabel, BorderLayout.CENTER);

        main.add(notifBox);
        main.add(Box.createVerticalStrut(12));


        JPanel iconsRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 18, 6));
        iconsRow.setOpaque(false);
        iconsRow.setMaximumSize(new Dimension(380, 48));
        JButton settingsBtn = iconButton("⚙");
        JButton shareBtn = iconButton("🔗");
        JButton downloadBtn = iconButton("◉");
        iconsRow.add(settingsBtn);
        iconsRow.add(shareBtn);
        iconsRow.add(downloadBtn);

        main.add(iconsRow);
        main.add(Box.createVerticalStrut(8));


        JPanel donutCard = createRoundedPanel(new Color(36, 50, 46), 28);
        donutCard.setLayout(new BoxLayout(donutCard, BoxLayout.Y_AXIS));
        donutCard.setMaximumSize(new Dimension(380, 380));
        donutCard.setPreferredSize(new Dimension(380, 360));
        donutCard.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));


        JLabel meldLabel = new JLabel("meldingen", SwingConstants.CENTER);
        meldLabel.setForeground(new Color(150, 255, 200));
        meldLabel.setFont(meldLabel.getFont().deriveFont(Font.BOLD, 16f));
        meldLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        subtitleLabel = new JLabel("vak 3a is al 36 uur leegstaand", SwingConstants.CENTER);
        subtitleLabel.setForeground(new Color(180, 220, 200));
        subtitleLabel.setFont(subtitleLabel.getFont().deriveFont(Font.PLAIN, 12f));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        donutCard.add(meldLabel);
        donutCard.add(Box.createVerticalStrut(6));
        donutCard.add(subtitleLabel);
        donutCard.add(Box.createVerticalStrut(8));


        donut = new DonutChart(initialOccupied, initialFree);
        donut.setAlignmentX(Component.CENTER_ALIGNMENT);
        donutCard.add(donut);

        donutCard.add(Box.createVerticalStrut(12));


        JPanel counts = new JPanel(new GridLayout(1, 2, 6, 6));
        counts.setOpaque(false);
        occupiedLabel = new JLabel("bezet  " + initialOccupied, SwingConstants.CENTER);
        occupiedLabel.setForeground(new Color(255, 120, 120));
        occupiedLabel.setFont(occupiedLabel.getFont().deriveFont(Font.BOLD, 14f));
        freeLabel = new JLabel("leeg  " + initialFree, SwingConstants.CENTER);
        freeLabel.setForeground(new Color(140, 255, 160));
        freeLabel.setFont(freeLabel.getFont().deriveFont(Font.BOLD, 14f));
        counts.add(freeLabel);
        counts.add(occupiedLabel);
        donutCard.add(counts);

        main.add(donutCard);
        main.add(Box.createVerticalStrut(12));


        JButton navigateBtn = new JButton("navigeer naar locatie   \uD83D\uDCF1");
        navigateBtn.setMaximumSize(new Dimension(360, 64));
        navigateBtn.setPreferredSize(new Dimension(360, 64));
        navigateBtn.setBackground(new Color(28, 90, 160));
        navigateBtn.setForeground(Color.WHITE);
        navigateBtn.setFocusPainted(false);
        navigateBtn.setFont(navigateBtn.getFont().deriveFont(Font.BOLD, 16f));
        navigateBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        main.add(navigateBtn);
        main.add(Box.createVerticalStrut(18));


        JPanel bottomRow = new JPanel();
        bottomRow.setOpaque(false);
        bottomRow.setLayout(new BoxLayout(bottomRow, BoxLayout.Y_AXIS));
        bottomRow.setMaximumSize(new Dimension(380, 120));

        JButton reloadBtn = new JButton("↺"); // reload icon
        reloadBtn.setPreferredSize(new Dimension(60, 60));
        reloadBtn.setMaximumSize(new Dimension(60, 60));
        reloadBtn.setBackground(new Color(24, 160, 90));
        reloadBtn.setForeground(Color.WHITE);
        reloadBtn.setFocusPainted(false);
        reloadBtn.setFont(reloadBtn.getFont().deriveFont(Font.BOLD, 20f));
        reloadBtn.setAlignmentX(Component.CENTER_ALIGNMENT);


        reloadBtn.addActionListener(e -> refresh());

        bottomRow.add(Box.createVerticalStrut(6));
        bottomRow.add(reloadBtn);
        bottomRow.add(Box.createVerticalStrut(6));

        main.add(bottomRow);


        JPanel navRow = new JPanel(new BorderLayout());
        navRow.setOpaque(false);
        navRow.setMaximumSize(new Dimension(380, 40));
        JButton profileIcon = new JButton("\uD83D\uDC64"); //profile guy
        profileIcon.setFocusPainted(false);
        profileIcon.setContentAreaFilled(false);
        profileIcon.setBorderPainted(false);
        profileIcon.setForeground(new Color(200, 240, 200));
        profileIcon.setFont(profileIcon.getFont().deriveFont(40f));
        profileIcon.addActionListener(e -> manager.showScreen(ScreenManager.LOGIN));

        JButton gridIcon = new JButton("▤");
        gridIcon.setFocusPainted(false);
        gridIcon.setContentAreaFilled(false);
        gridIcon.setBorderPainted(false);
        gridIcon.setForeground(new Color(200, 240, 200));
        gridIcon.setFont(gridIcon.getFont().deriveFont(40f));
        gridIcon.addActionListener(e -> manager.showScreen(ScreenManager.DASHBOARD));

        navRow.add(profileIcon, BorderLayout.WEST);
        navRow.add(gridIcon, BorderLayout.EAST);

        main.add(Box.createVerticalStrut(6));
        main.add(navRow);


        add(main, BorderLayout.CENTER);


        refresh();
    }


    private JPanel createRoundedPanel(Color bg, int arc) {
        JPanel p = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(bg);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
                g2.dispose();
            }
        };
        p.setOpaque(false);
        return p;
    }

    private JButton iconButton(String emoji) {
        JButton b = new JButton(emoji);
        b.setFocusPainted(false);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
        b.setForeground(new Color(220, 245, 220));
        b.setFont(b.getFont().deriveFont(20f));
        return b;
    }


    public void refresh() {
        int total = 8 + rnd.nextInt(7); // 8..14
        int occupied = 3 + rnd.nextInt(Math.max(1, total - 2)); // somewhat random
        int free = Math.max(0, total - occupied);

        donut.setValues(occupied, free);
        donut.repaint();


        occupiedLabel.setText("bezet  " + occupied);
        freeLabel.setText("leeg  " + free);




        int choice = rnd.nextInt(3);
        if (choice == 0) {
            int spot = 1 + rnd.nextInt(9); // below 10
            notificationLabel.setText("spot " + spot + " has been full for an extended period of time");
            subtitleLabel.setText("vak 3a is al " + (10 + rnd.nextInt(48)) + " uur leegstaand");
        } else if (choice == 1) {
            notificationLabel.setText("street data not updating, contact support.");
            subtitleLabel.setText("vak 3a is al " + (2 + rnd.nextInt(6)) + " uur leegstaand");
        } else {
            notificationLabel.setText("no notifications");
            subtitleLabel.setText("vak 3a is al " + (1 + rnd.nextInt(12)) + " uur leegstaand");
        }
    }


    private static class DonutChart extends JComponent {
        private int occupied;
        private int free;

        public DonutChart(int occupied, int free) {
            this.occupied = occupied;
            this.free = free;
            setPreferredSize(new Dimension(260, 160));
        }

        public void setValues(int occupied, int free) {
            this.occupied = occupied;
            this.free = free;
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


            g2.setColor(new Color(40, 60, 50));
            Stroke old = g2.getStroke();
            g2.setStroke(new BasicStroke(size * 0.14f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.drawArc(x + (int)(size*0.07), y + (int)(size*0.07), (int)(size*0.86), (int)(size*0.86), 0, 360);


            g2.setColor(new Color(220, 40, 50));
            g2.setStroke(new BasicStroke(size * 0.14f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            int start = 90;
            int occ = -(int)Math.round(occAngle);
            g2.drawArc(x + (int)(size*0.07), y + (int)(size*0.07), (int)(size*0.86), (int)(size*0.86), start, occ);


            g2.setColor(new Color(100, 200, 120));
            int freeStart = start + occ;
            int freeArc = -(int)Math.round(freeAngle);
            g2.drawArc(x + (int)(size*0.07), y + (int)(size*0.07), (int)(size*0.86), (int)(size*0.86), freeStart, freeArc);


            double percent = 100.0 * occupied / total;
            String pct = String.format("%.0f%%", percent);
            g2.setFont(getFont().deriveFont(Font.BOLD, 20f));
            FontMetrics fm = g2.getFontMetrics();
            int tx = (getWidth() - fm.stringWidth(pct)) / 2;
            int ty = (getHeight() + fm.getAscent()) / 2 - 6;
            g2.setColor(new Color(200, 240, 210));
            g2.drawString(pct, tx, ty);

            g2.setStroke(old);
            g2.dispose();
        }
    }
}
