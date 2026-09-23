import javax.swing.*;
import java.awt.*;

public class CongratulationsPage {

    private static final int DESIGN_WIDTH = 1212;
    private static final int DESIGN_HEIGHT = 1297;
    private static final int DISPLAY_HEIGHT = 750;

    private static final double SCALE = DISPLAY_HEIGHT / (double) DESIGN_HEIGHT;

    public CongratulationsPage() {

        // Main window
        JFrame frame = new JFrame("Congratulations");

        frame.setDefaultCloseOperation(
            JFrame.DISPOSE_ON_CLOSE
        );

        // Background image
        ImageIcon originalImage =
            new ImageIcon(
                "resources/congratulations-background.png"
            );

        int displayWidth = s(DESIGN_WIDTH);

        Image scaledImage =
            originalImage
                .getImage()
                .getScaledInstance(
                    displayWidth,
                    DISPLAY_HEIGHT,
                    Image.SCALE_SMOOTH
                );

        JLabel background =
            new JLabel(
                new ImageIcon(scaledImage)
            );

        background.setLayout(null);

        background.setPreferredSize(
            new Dimension(
                displayWidth,
                DISPLAY_HEIGHT
            )
        );

        frame.setContentPane(background);

        // Accept button
        GradientButton acceptButton = new GradientButton("A C C E P T");

        acceptButton.setBounds(
            s(430),
            s(1080),
            s(350),
            s(65)
        );

        acceptButton.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                fontSize(20)
            )
        );

        acceptButton.setForeground(
            Color.WHITE
        );

        acceptButton.setFocusPainted(
            false
        );

        background.add(
            acceptButton
        );

        // Accept click
        acceptButton.addActionListener(
            e -> {

                System.out.println(
                    "Accept clicked"
                );

                // For now, just close this page
                frame.dispose();
            }
        );

        // Window settings
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Scale coordinates
    private static int s(int value) {

        return (int) Math.round(
            value * SCALE
        );
    }

    // Scale font size
    private static int fontSize( int value) {

        return Math.max(
            11,
            s(value)
        );

    }

    // Gradient button
    static class GradientButton extends JButton {

        public GradientButton(String text) {

            super(text);

            setOpaque(false);
            setContentAreaFilled(false);
            setBorderPainted(false);

            setCursor(
                new Cursor(
                    Cursor.HAND_CURSOR
                )
            );
        }

        @Override
        protected void paintComponent(Graphics g) {

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
            );

            GradientPaint gradient =
                new GradientPaint(
                    0,
                    0,
                    new Color(
                        20,
                        137,
                        232
                    ),
                    getWidth(),
                    0,
                    new Color(
                        59,
                        203,
                        218
                    )
                );

            g2.setPaint(
                gradient
            );

            g2.fillRoundRect(
                0,
                0,
                getWidth(),
                getHeight(),
                s(14),
                s(14)
            );

            g2.dispose();

            super.paintComponent(g);
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
            () -> new CongratulationsPage()
        );
    }
}