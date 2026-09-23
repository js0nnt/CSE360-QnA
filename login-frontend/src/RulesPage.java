import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RulesPage {

    private static final int DESIGN_WIDTH = 1087;
    private static final int DESIGN_HEIGHT = 1447;
    private static final int DISPLAY_HEIGHT = 750;

    private static final double SCALE =
        DISPLAY_HEIGHT / (double) DESIGN_HEIGHT;

    public RulesPage() {

        // Main window
        JFrame frame =
            new JFrame("Rules");

        frame.setDefaultCloseOperation(
            JFrame.DISPOSE_ON_CLOSE
        );

        // Rules background
        ImageIcon originalBackground =
            new ImageIcon(
                "resources/rules-background.png"
            );

        int displayWidth =
            s(DESIGN_WIDTH);

        Image scaledBackground =
            originalBackground
                .getImage()
                .getScaledInstance(
                    displayWidth,
                    DISPLAY_HEIGHT,
                    Image.SCALE_SMOOTH
                );

        JLabel background =
            new JLabel(
                new ImageIcon(scaledBackground)
            );

        background.setLayout(null);

        background.setPreferredSize(
            new Dimension(
                displayWidth,
                DISPLAY_HEIGHT
            )
        );

        frame.setContentPane(background);

        // Back arrow image
        ImageIcon originalArrow =
            new ImageIcon(
                "resources/back-arrow.png"
            );

        Image scaledArrow =
            originalArrow
                .getImage()
                .getScaledInstance(
                    s(260),
                    s(190),
                    Image.SCALE_SMOOTH
                );

        JLabel backArrow =
            new JLabel(
                new ImageIcon(scaledArrow)
            );

        backArrow.setBounds(
            s(410),
            s(1040),
            s(260),
            s(190)
        );

        backArrow.setCursor(
            new Cursor(
                Cursor.HAND_CURSOR
            )
        );

        background.add(backArrow);

        // Make the arrow clickable
        backArrow.addMouseListener(
            new MouseAdapter() {

                @Override
                public void mouseClicked(
                    MouseEvent e
                ) {

                    frame.dispose();
                }
            }
        );

        // Window settings
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Scale positions and sizes
    private static int s(
        int value
    ) {

        return (int) Math.round(
            value * SCALE
        );
    }

    public static void main(
        String[] args
    ) {

        SwingUtilities.invokeLater(
            () -> new RulesPage()
        );
    }
}