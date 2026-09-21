import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CreateAccountPage {

    private static final int DESIGN_WIDTH = 1212;
    private static final int DESIGN_HEIGHT = 1297;
    private static final int DISPLAY_HEIGHT = 750;

    private static final double SCALE = DISPLAY_HEIGHT / (double) DESIGN_HEIGHT;

    private final Color placeholderGray = new Color(145, 150, 170);

    private final Color normalText = new Color(40, 40, 40);

    private final Color borderGray = new Color(195, 200, 214);

    private final Color linkBlue = new Color(20, 126, 230);

    private final Color darkBlue = new Color(15, 35, 70);

    public CreateAccountPage() {

        // Main window
        JFrame frame = new JFrame("Create Your Account");

        frame.setDefaultCloseOperation(
            JFrame.DISPOSE_ON_CLOSE
        );

        // Background image
        ImageIcon originalImage = new ImageIcon("resources/create-account-background.png");

        int displayWidth = s(DESIGN_WIDTH);

        Image scaledImage =
            originalImage
                .getImage()
                .getScaledInstance(
                    displayWidth,
                    DISPLAY_HEIGHT,
                    Image.SCALE_SMOOTH
                );

        JLabel background = new JLabel(
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

        // First name
        JTextField firstName = createTextField("Enter your first name");

        firstName.setBounds(
            s(190),
            s(206),
            s(404),
            s(53)
        );

        background.add(firstName);

        // Last name
        JTextField lastName = createTextField("Enter your last name");

        lastName.setBounds(
            s(622),
            s(206),
            s(405),
            s(53)
        );

        background.add(lastName);

        // Email
        JTextField email = createTextField("Enter your email");

        email.setBounds(
            s(190),
            s(321),
            s(837),
            s(54)
        );

        background.add(email);

        // Username
        JTextField username = createTextField("Create a username");

        username.setBounds(
            s(190),
            s(441),
            s(837),
            s(55)
        );

        background.add(username);

        // Phone box
        JPanel phoneBox = new JPanel(null);

        phoneBox.setBounds(
            s(190),
            s(560),
            s(837),
            s(55)
        );

        phoneBox.setBackground(
            Color.WHITE
        );

        phoneBox.setBorder(
            new LineBorder(
                borderGray,
                1,
                true
            )
        );

        background.add(phoneBox);

        // United States flag
        USFlag flag = new USFlag();

        flag.setBounds(
            s(18),
            s(16),
            s(34),
            s(23)
        );

        phoneBox.add(flag);

        // Country code
        JLabel countryCode = new JLabel("+1");

        countryCode.setBounds(
            s(58),
            s(9),
            s(45),
            s(35)
        );

        countryCode.setFont(
            new Font(
                "Arial",
                Font.PLAIN,
                fontSize(18)
            )
        );

        countryCode.setForeground(
            darkBlue
        );

        phoneBox.add(countryCode);

        // Phone number
        JTextField phoneNumber =
            createBorderlessField(
                "000 000 0000"
            );

        phoneNumber.setBounds(
            s(92),
            s(7),
            s(690),
            s(40)
        );

        phoneBox.add(phoneNumber);

        // Date of birth
        JTextField dateOfBirth =
            createTextField(
                "DD.MM.YYYY"
            );

        dateOfBirth.setBounds(
            s(190),
            s(680),
            s(403),
            s(55)
        );

        background.add(dateOfBirth);

        // Favorite color dropdown
        String[] colors = {
            "Select your favorite color",
            "Blue",
            "Red",
            "Green",
            "Purple",
            "Orange",
            "Yellow",
            "Pink",
            "Black",
            "White"
        };

        JComboBox<String> favoriteColor =
            new JComboBox<>(colors);

        favoriteColor.setBounds(
            s(622),
            s(680),
            s(405),
            s(55)
        );

        favoriteColor.setFont(
            new Font(
                "Arial",
                Font.PLAIN,
                fontSize(18)
            )
        );

        favoriteColor.setForeground(
            darkBlue
        );

        favoriteColor.setBackground(
            Color.WHITE
        );

        favoriteColor.setBorder(
            new LineBorder(
                borderGray,
                1,
                true
            )
        );

        background.add(favoriteColor);

        // Password
        JPasswordField password =
            createPasswordField(
                "Create a password"
            );

        password.setBounds(
            s(190),
            s(798),
            s(837),
            s(54)
        );

        background.add(password);

        // Confirm password
        JPasswordField confirmPassword =
            createPasswordField(
                "Re-enter your password"
            );

        confirmPassword.setBounds(
            s(190),
            s(949),
            s(837),
            s(56)
        );

        background.add(confirmPassword);

        // Agreement checkbox
        JCheckBox agreement =
            new JCheckBox();

        agreement.setBounds(
            s(190),
            s(1033),
            s(30),
            s(30)
        );

        agreement.setOpaque(false);
        agreement.setFocusPainted(false);

        background.add(agreement);

        // Agreement text panel
        JPanel agreementText =
            new JPanel(
                new FlowLayout(
                    FlowLayout.LEFT,
                    0,
                    0
                )
            );

        agreementText.setBounds(
            s(230),
            s(1034),
            s(700),
            s(40)
        );

        agreementText.setOpaque(false);

        // Agreement beginning
        JLabel agreementStart =
            new JLabel(
                "I agree to the "
            );

        agreementStart.setFont(
            new Font(
                "Arial",
                Font.PLAIN,
                fontSize(18)
            )
        );

        agreementStart.setForeground(
            darkBlue
        );

        agreementText.add(
            agreementStart
        );

        // Clickable rules and conditions
        JLabel rulesLink =
            new JLabel(
                "<html><u><b>rules and conditions</b></u></html>"
            );

        rulesLink.setFont(
            new Font(
                "Arial",
                Font.PLAIN,
                fontSize(18)
            )
        );

        rulesLink.setForeground(
            linkBlue
        );

        rulesLink.setCursor(
            new Cursor(
                Cursor.HAND_CURSOR
            )
        );

        agreementText.add(
            rulesLink
        );

        // Agreement ending
        JLabel agreementEnd =
            new JLabel(
                " and acknowledge the privacy policy."
            );

        agreementEnd.setFont(
            new Font(
                "Arial",
                Font.PLAIN,
                fontSize(18)
            )
        );

        agreementEnd.setForeground(
            darkBlue
        );

        agreementText.add(
            agreementEnd
        );

        background.add(
            agreementText
        );

        // Rules link click
        rulesLink.addMouseListener(
            new MouseAdapter() {

                @Override
                public void mouseClicked(
                    MouseEvent e
                ) {

                    System.out.println("Rules and conditions clicked");
                }
            }
        );

        // Register button
        GradientButton registerButton =
            new GradientButton(
                "Register"
            );

        registerButton.setBounds(
            s(429),
            s(1090),
            s(357),
            s(58)
        );

        registerButton.setFont(
            new Font(
                "Arial",
                Font.BOLD,
                fontSize(18)
            )
        );

        registerButton.setForeground(
            Color.WHITE
        );

        registerButton.setFocusPainted(
            false
        );

        background.add(
            registerButton
        );

        // Register click
        registerButton.addActionListener(
            e -> {
                
                System.out.println("Register clicked");

            }
        );

        // Window settings
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Normal text field
    private JTextField createTextField(
        String placeholder
    ) {

        JTextField field =
            new JTextField(
                placeholder
            );

        field.setFont(
            new Font(
                "Arial",
                Font.PLAIN,
                fontSize(18)
            )
        );

        field.setForeground(
            placeholderGray
        );

        field.setBackground(
            Color.WHITE
        );

        field.setBorder(
            new LineBorder(
                borderGray,
                1,
                true
            )
        );

        field.setMargin(
            new Insets(
                0,
                s(18),
                0,
                s(18)
            )
        );

        addPlaceholderBehavior(
            field,
            placeholder
        );

        return field;
    }

    // Phone number text field
    private JTextField createBorderlessField(
        String placeholder
    ) {

        JTextField field =
            new JTextField(
                placeholder
            );

        field.setFont(
            new Font(
                "Arial",
                Font.PLAIN,
                fontSize(18)
            )
        );

        field.setForeground(
            placeholderGray
        );

        field.setBackground(
            Color.WHITE
        );

        field.setBorder(null);

        addPlaceholderBehavior(
            field,
            placeholder
        );

        return field;
    }

    // Placeholder behavior
    private void addPlaceholderBehavior(
        JTextField field,
        String placeholder
    ) {

        field.addFocusListener(
            new FocusAdapter() {

                @Override
                public void focusGained(
                    FocusEvent e
                ) {

                    if (
                        field.getText()
                            .equals(placeholder)
                    ) {

                        field.setText("");

                        field.setForeground(
                            normalText
                        );
                    }
                }

                @Override
                public void focusLost(
                    FocusEvent e
                ) {

                    if (
                        field.getText()
                            .isEmpty()
                    ) {

                        field.setText(
                            placeholder
                        );

                        field.setForeground(
                            placeholderGray
                        );
                    }
                }
            }
        );
    }

    // Password field
    private JPasswordField createPasswordField(
        String placeholder
    ) {

        JPasswordField field =
            new JPasswordField(
                placeholder
            );

        field.setFont(
            new Font(
                "Arial",
                Font.PLAIN,
                fontSize(18)
            )
        );

        field.setForeground(
            placeholderGray
        );

        field.setBackground(
            Color.WHITE
        );

        field.setBorder(
            new LineBorder(
                borderGray,
                1,
                true
            )
        );

        field.setMargin(
            new Insets(
                0,
                s(18),
                0,
                s(18)
            )
        );

        char normalEcho =
            field.getEchoChar();

        field.setEchoChar(
            (char) 0
        );

        field.addFocusListener(
            new FocusAdapter() {

                @Override
                public void focusGained(
                    FocusEvent e
                ) {

                    String value =
                        new String(
                            field.getPassword()
                        );

                    if (
                        value.equals(
                            placeholder
                        )
                    ) {

                        field.setText("");

                        field.setEchoChar(
                            normalEcho
                        );

                        field.setForeground(
                            normalText
                        );
                    }
                }

                @Override
                public void focusLost(
                    FocusEvent e
                ) {

                    String value =
                        new String(
                            field.getPassword()
                        );

                    if (
                        value.isEmpty()
                    ) {

                        field.setEchoChar(
                            (char) 0
                        );

                        field.setText(
                            placeholder
                        );

                        field.setForeground(
                            placeholderGray
                        );
                    }
                }
            }
        );

        return field;
    }

    // Scale coordinates
    private static int s(
        int value
    ) {

        return (int) Math.round(
            value * SCALE
        );
    }

    // Scale fonts
    private static int fontSize(
        int value
    ) {

        return Math.max(
            11,
            s(value)
        );
    }

    // United States flag
    static class USFlag
        extends JComponent {

        @Override
        protected void paintComponent(
            Graphics g
        ) {

            Graphics2D g2 =
                (Graphics2D) g.create();

            int width =
                getWidth();

            int height =
                getHeight();

            int stripeHeight =
                Math.max(
                    1,
                    height / 13
                );

            // White background
            g2.setColor(
                Color.WHITE
            );

            g2.fillRect(
                0,
                0,
                width,
                height
            );

            // Red stripes
            g2.setColor(
                new Color(
                    220,
                    30,
                    40
                )
            );

            for (
                int i = 0;
                i < 13;
                i += 2
            ) {

                g2.fillRect(
                    0,
                    i * stripeHeight,
                    width,
                    stripeHeight
                );
            }

            // Blue corner
            g2.setColor(
                new Color(
                    35,
                    60,
                    150
                )
            );

            g2.fillRect(
                0,
                0,
                width / 2,
                height / 2
            );

            g2.dispose();
        }
    }

    // Gradient Register button
    static class GradientButton
        extends JButton {

        public GradientButton(
            String text
        ) {

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
        protected void paintComponent(
            Graphics g
        ) {

            Graphics2D g2 =
                (Graphics2D) g.create();

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
                s(12),
                s(12)
            );

            g2.dispose();

            super.paintComponent(g);
        }
    }

    public static void main(
        String[] args
    ) {

        SwingUtilities.invokeLater(
            () -> new CreateAccountPage()
        );
    }
}