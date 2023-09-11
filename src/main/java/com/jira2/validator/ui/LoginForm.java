package com.jira2.validator.ui;

import com.jira2.validator.Jira2Application;
import com.jira2.validator.services.Jira2Service;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame implements ActionListener
{

    private static final long serialVersionUID = 1L;
    // Components of the Form
    private JPanel contentPane;
    private JLabel lblHeading;
    private JLabel lblUsername;
    private JLabel lblPassword;
    public static JButton btnLogin;
    // private JLabel lblExit;
    public final JCheckBox showPasswordCheckBox;
    private static JTextField username;
    public static JPasswordField password;
    public static String Username;
    public static String Password;
    public static JLabel invalid;
    public static Object actionPerformed;

    // constructor, to initialize the components
    // with default values.
    public LoginForm()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1308, 737);
        contentPane = new JPanel();
        contentPane.setAlignmentX(Component.RIGHT_ALIGNMENT);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new FlowLayout());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBounds(449, 123, 389, 449);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setPreferredSize(new Dimension(400, 100));
        panel.setMaximumSize(new Dimension(400, 100));
        contentPane.add(panel);
        panel.setLayout(null);

        // Create headerpanel JPanel.
        JPanel headderPanel = new JPanel();
        headderPanel.setBackground(Color.BLACK);
        headderPanel.setBounds(0, 0, 389, 84);
        panel.add(headderPanel);
        headderPanel.setLayout(null);

        // Create lblHeading JLabel.
        lblHeading = new JLabel("JIRA Login");
        lblHeading.setFont(new Font("SansSerif", Font.BOLD, 26));
        lblHeading.setHorizontalAlignment(SwingConstants.CENTER);
        lblHeading.setForeground(Color.WHITE);
        lblHeading.setBounds(35, 21, 316, 52);
        headderPanel.add(lblHeading);

        // Create main body panel JPanel.
        JPanel mainbodyPanel = new JPanel();
        mainbodyPanel.setBackground(Color.LIGHT_GRAY);
        mainbodyPanel.setBounds(0, 84, 389, 365);
        panel.add(mainbodyPanel);
        mainbodyPanel.setLayout(null);

        // Create lblUsername JLabel.
        lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Serif", Font.BOLD, 18));
        lblUsername.setBounds(53, 27, 101, 31);
        mainbodyPanel.add(lblUsername);

        // Create lblPassword JLabel.
        lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Serif", Font.BOLD, 18));
        lblPassword.setBounds(53, 120, 101, 31);
        mainbodyPanel.add(lblPassword);

        // Create username JTextField to store user input.
        username = new JTextField();
        username.setFont(new Font("Tahoma", Font.PLAIN, 14));
        username.setBounds(53, 69, 277, 40);

        mainbodyPanel.add(username);
        username.setColumns(10);
        // Create password JTextField to store user password.
        password = new JPasswordField();
        password.setToolTipText("");
        password.setBounds(53, 162, 277, 40);
        mainbodyPanel.add(password);

        // Create Show Password JCheckBox to set visible the user entered password.
        showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.setBounds(53, 209, 129, 23);

        /** Show JPasswordField password if showPasswordCheckBox is selected */
        showPasswordCheckBox.addActionListener(this);
        mainbodyPanel.add(showPasswordCheckBox);

        // Create invalid JTextField to display alert message.
        invalid = new JLabel("");
        invalid.setForeground(new Color(165, 42, 42));
        invalid.setHorizontalAlignment(SwingConstants.CENTER);
        invalid.setBounds(0, 340, 389, 14);
        mainbodyPanel.add(invalid);
        setLocationRelativeTo(null);

        // Create btnLogin JButton to validate the user input and allow access.
        btnLogin = new JButton("LOGIN");
        btnLogin.setBackground(new Color(112, 128, 144));
        btnLogin.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 14));
        btnLogin.setForeground(new Color(0, 100, 0));
        btnLogin.setBounds(114, 267, 129, 40);
        /**
         * component-specific action occurs (such as being pressed). Then event is
         * passed to a ActionListener object that registered to receive such events
         * using the component's addActionListener method.
         *
         */
        btnLogin.addActionListener(this);
        mainbodyPanel.add(btnLogin);

        setVisible(true);
    }

    public JTextField getUsername()
    {
        return username;
    }

    public static void setUsername(String username)
    {
        LoginForm.username.setText(username);
    }

    public JPasswordField getPassword()
    {
        return password;
    }

    public static void setPassword(String password)
    {
        LoginForm.password.setText(password);
    }

    @SuppressWarnings("deprecation")
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == showPasswordCheckBox)
        {
            if(showPasswordCheckBox.isSelected())
            {
                password.setEchoChar((char) 0);
            }
            else
            {
                password.setEchoChar('*');
            }
        }
        else if(e.getSource() == btnLogin)
        {
            if(username.getText().isEmpty() || password.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Please Enter both Username and Password fields");
            }
            else
            {
                System.out.println("Login Clicked");
                Username = username.getText();
                Password = password.getText();
                new Jira2Application();
                if(Jira2Service.jiraLogin(Username, Password))
                {
                    SmartValidatorLauncher.main();

                }
                // LoginController.main(Username, Password);
            }
            setVisible(false);
        }
    }
}

/**
 * 146 // Create lblExit JLabel to close the operation. lblExit = new JLabel("X"); lblExit.addMouseListener(new MouseAdapter() {
 * @Override public void mouseClicked(MouseEvent e) { setVisible(false);
 *         } }); lblExit.setFont(new Font("Times New Roman", Font.BOLD, 16)); lblExit.setVerticalAlignment(SwingConstants.TOP);
 *         lblExit.setHorizontalAlignment(SwingConstants.CENTER); lblExit.setForeground(Color.WHITE); lblExit.setBounds(362, 11,
 *         27, 36); headingPanel.add(lblExit);
 *         115 public void actionPerformed(ActionEvent e) { if (showPasswordCheckBox.isSelected()) { password.setEchoChar((char)
 *         0); } else { password.setEchoChar('*'); } } });
 */

/**
 * 115 public void actionPerformed(ActionEvent e) { if
 * (showPasswordCheckBox.isSelected()) { password.setEchoChar((char) 0); } else
 * { password.setEchoChar('*'); } } });
 */
