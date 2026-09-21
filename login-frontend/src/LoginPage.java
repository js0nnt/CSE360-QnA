import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class LoginPage {

    private final Color blue = new Color(20, 126, 230);
    private final Color gray = new Color(100, 100, 100);
    private final Color placeholderGray = new Color(165, 165, 165);
    private final Color lineBlue = new Color(38, 164, 232);

    public LoginPage() {

        // Main window
        JFrame frame = new JFrame("Q&A Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Background image
        ImageIcon backgroundImage = new ImageIcon("resources/login-background.png");

        JLabel background = new JLabel(backgroundImage);
        background.setLayout(null);
        background.setPreferredSize(new Dimension(1280, 708));

        frame.setContentPane(background);

        // Login title
        JLabel loginText = new JLabel("Login");
        loginText.setBounds(247, 290, 80, 40);
        loginText.setFont(new Font("Arial", Font.PLAIN, 27));
        loginText.setForeground(
            new Color(65, 65, 65)
        );

        JLabel accountText = new JLabel("Your Account");
        accountText.setBounds(318, 290, 190, 40);
        accountText.setFont(new Font("Arial", Font.PLAIN, 27));
        accountText.setForeground(
            new Color(65, 65, 65)
        );

        background.add(loginText);
        background.add(accountText);

        // Email icon
        JLabel emailIcon = new JLabel("✉");
        emailIcon.setBounds(179, 354, 30, 30);
        emailIcon.setFont(new Font("Dialog", Font.PLAIN, 22));
        emailIcon.setForeground(gray);

        background.add(emailIcon);

        // Email field
        JTextField emailField = new JTextField("Email Address");

        emailField.setBounds(220, 348, 350, 43);
        emailField.setFont(new Font("Arial", Font.PLAIN, 18));
        emailField.setForeground(placeholderGray);
        emailField.setOpaque(false);
        emailField.setBorder(new EmptyBorder(0, 0, 0, 0));

        emailField.addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {

                if (emailField.getText().equals("Email Address")) {

                    emailField.setText("");

                    emailField.setForeground(
                        new Color(50, 50, 50)
                    );
                }
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (emailField.getText().isEmpty()) {

                    emailField.setText("Email Address");
                    emailField.setForeground(placeholderGray);
                }
            }
        });

        background.add(emailField);

        // Email underline
        JPanel emailLine = new JPanel();

        emailLine.setBounds(
            169,
            392,
            408,
            2
        );

        emailLine.setBackground(lineBlue);

        background.add(emailLine);

        // Lock icon
        LockIcon lockIcon = new LockIcon();

        lockIcon.setBounds(
            178,
            421,
            27,
            30
        );

        background.add(lockIcon);

        // Password field
        JPasswordField passwordField =
            new JPasswordField("Password");

        passwordField.setBounds(
            220,
            415,
            300,
            43
        );

        passwordField.setFont(
            new Font("Arial", Font.PLAIN, 18)
        );

        passwordField.setForeground(placeholderGray);
        passwordField.setOpaque(false);
        passwordField.setBorder(
            new EmptyBorder(0, 0, 0, 0)
        );

        char normalEcho =
            passwordField.getEchoChar();

        passwordField.setEchoChar((char) 0);

        passwordField.addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {

                String password =
                        new String(
                            passwordField.getPassword()
                        );

                if (password.equals("Password")) {

                    passwordField.setText("");
                    passwordField.setEchoChar(normalEcho);

                    passwordField.setForeground(
                        new Color(50, 50, 50)
                    );
                }
            }

            @Override
            public void focusLost(FocusEvent e) {

                String password =
                        new String(
                            passwordField.getPassword()
                        );

                if (password.isEmpty()) {

                    passwordField.setEchoChar((char) 0);

                    passwordField.setText(
                        "Password"
                    );

                    passwordField.setForeground(
                        placeholderGray
                    );
                }
            }
        });

        background.add(passwordField);

        // Password underline
        JPanel passwordLine = new JPanel();

        passwordLine.setBounds(
            169,
            459,
            408,
            2
        );

        passwordLine.setBackground(lineBlue);

        background.add(passwordLine);

        // Eye button
        EyeButton eyeButton =
            new EyeButton();

        eyeButton.setBounds(
            527,
            417,
            45,
            35
        );

        eyeButton.addActionListener(e -> {

            String password = new String(passwordField.getPassword());

            if (password.equals("Password")) {
                return;
            }

            if (passwordField.getEchoChar() == 0) {

                passwordField.setEchoChar(normalEcho);

                eyeButton.setShowing(false);

            } else {

                passwordField.setEchoChar((char) 0);

                eyeButton.setShowing(true);
            }
        });

        background.add(eyeButton);

        // Remember checkbox
        JCheckBox remember = new JCheckBox("Remember");

        remember.setBounds(
            177,
            477,
            150,
            34
        );

        remember.setFont(
            new Font("Arial", Font.PLAIN, 16)
        );

        remember.setForeground(
            new Color(50, 50, 50)
        );

        remember.setOpaque(false);
        remember.setFocusPainted(false);

        background.add(remember);

        // Forgot password
        JLabel forgotPassword = new JLabel("Forgot Password ?");

        forgotPassword.setBounds(
            428,
            477,
            160,
            34
        );

        forgotPassword.setFont(
            new Font("Arial", Font.PLAIN, 16) 
        );

        forgotPassword.setForeground(
            new Color(8, 121, 231)
        );

        forgotPassword.setCursor(
            new Cursor(Cursor.HAND_CURSOR)
        );

        background.add(forgotPassword);

        // Submit button
        GradientButton submit = new GradientButton("S U B M I T");

        submit.setBounds(
            169,
            531,
            408,
            55
        );

        submit.setFont(
            new Font("Arial", Font.BOLD, 19)
        );

        submit.setForeground(Color.WHITE);
        submit.setFocusPainted(false);

        background.add(submit);

        // Create account
        JLabel createAccount = new JLabel("Create Account");

        createAccount.setBounds(
            306,
            602,
            140,
            30
        );

        createAccount.setFont(
            new Font("Arial", Font.PLAIN, 16)
        );

        createAccount.setForeground(
            new Color(8, 121, 231)
        );

        createAccount.setCursor(
            new Cursor(Cursor.HAND_CURSOR)
        );

        background.add(createAccount);

        // Window settings
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Lock icon
    static class LockIcon extends JComponent {

        @Override
        protected void paintComponent(Graphics g) {

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(
                new Color(100, 100, 100)
            );

            g2.setStroke(
                new BasicStroke(2.2f)
            );

            g2.drawRoundRect(
                    4,
                    13,
                    18,
                    14,
                    2,
                    2
            );

            g2.drawArc(
                    7,
                    3,
                    12,
                    18,
                    0,
                    180
            );

            g2.dispose();
        }
    }

    // Eye button
    static class EyeButton extends JButton {

        private boolean showing = false;

        public EyeButton() {

            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);

            setCursor(new Cursor(Cursor.HAND_CURSOR)
            );
        }

        public void setShowing(boolean showing) {

            this.showing = showing;

            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            Graphics2D g2 =
                (Graphics2D) g.create();

            g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(
                new Color(125, 125, 125)
            );

            g2.setStroke(
                new BasicStroke(2.2f)
            );

            g2.drawOval(
                7,
                9,
                27,
                15
            );

            g2.fillOval(
                18,
                14,
                5,
                5
            );

            if (!showing) {

                g2.drawLine(
                    7,
                    6,
                    35,
                    27
                );
            }

            g2.dispose();
        }
    }

    // Submit button
    static class GradientButton extends JButton {

        public GradientButton(String text) {

            super(text);

            setOpaque(false);
            setContentAreaFilled(false);
            setBorderPainted(false);

            setCursor(new Cursor(Cursor.HAND_CURSOR)

            );
        }

        @Override
        protected void paintComponent(Graphics g) {

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
                        new Color(20, 137, 232),
                        getWidth(),
                        0,
                        new Color(59, 203, 218)
                    );

            g2.setPaint(gradient);

            g2.fillRoundRect(
                0,
                0,
                getWidth(),
                getHeight(),
                16,
                16
            );

            g2.dispose();

            super.paintComponent(g);
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
            () -> new LoginPage()
        );
    }
}
