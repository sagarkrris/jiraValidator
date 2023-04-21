package com.jira2.validator.ui;

import com.jira2.validator.response.JFormsResponse;
import com.jira2.validator.response.Jira2Response;
import com.jira2.validator.reviewchecklist.Jira2CheckList;
import com.jira2.validator.services.Jira2Service;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class SmartValidatorLauncher extends JFrame implements ActionListener
{

    private JFrame frame;
    private JPanel contentPane;
    private JLabel lblHeading;
    private JLabel lblUsername;
    public static JButton btnJIRA;
    public static JButton btnJform;
    private static JTextField jiraId;
    public static JLabel invalid;
    public static Object actionPerformed;

    /**
     * Launch the application.
     */
    public static void main()
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    SmartValidatorLauncher window = new SmartValidatorLauncher();
                    window.frame.setVisible(true);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public SmartValidatorLauncher()
    {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize()
    {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 1308, 737);

        contentPane = new JPanel();
        contentPane.setAlignmentX(Component.RIGHT_ALIGNMENT);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);

        frame.setContentPane(contentPane);
        frame.setLocationRelativeTo(null);

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
        lblHeading = new JLabel("Smart Validator");
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
        lblUsername = new JLabel("JIRA Id");
        lblUsername.setFont(new Font("Serif", Font.BOLD, 18));
        lblUsername.setBounds(142, 56, 101, 31);
        mainbodyPanel.add(lblUsername);

        // Create username JTextField to store user input.
        jiraId = new JTextField();
        jiraId.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jiraId.setBounds(51, 114, 277, 40);

        mainbodyPanel.add(jiraId);
        jiraId.setColumns(10);

        // Create invalid JTextField to display alert message.
        invalid = new JLabel("");
        invalid.setForeground(new Color(165, 42, 42));
        invalid.setHorizontalAlignment(SwingConstants.CENTER);
        invalid.setBounds(0, 340, 389, 14);
        mainbodyPanel.add(invalid);
        setLocationRelativeTo(null);

        // Create btnLogin JButton to validate the user input and allow access.
        btnJIRA = new JButton("JIRA");
        btnJIRA.setBackground(new Color(112, 128, 144));
        btnJIRA.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 14));
        btnJIRA.setForeground(new Color(0, 100, 0));
        btnJIRA.setBounds(10, 204, 177, 60);
        /**
         * component-specific action occurs (such as being pressed). Then event is
         * passed to a ActionListener object that registered to receive such events
         * using the component's addActionListener method.
         *
         */
        btnJIRA.addActionListener(this);
        mainbodyPanel.add(btnJIRA);

        btnJform = new JButton("JFORM");
        btnJform.setForeground(new Color(0, 100, 0));
        btnJform.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 14));
        btnJform.setBackground(new Color(112, 128, 144));
        btnJform.setBounds(202, 204, 177, 60);
        mainbodyPanel.add(btnJform);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == btnJIRA)
        {
            if (jiraId.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Please enter a valid JIRA Id");
            }
            Jira2Service jira2Service= new Jira2Service();
            Jira2Response jiraDetails= jira2Service.getIssueDetails(jiraId.getText());
            Jira2CheckList jira2CheckList = new Jira2CheckList();
            jira2CheckList.jiraChecklist(jiraDetails);
            loadJiraDetails(jiraDetails);

        }
        if (e.getSource() == btnJform)
        {
            if (jiraId.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Please enter a valid JIRA Id");
            }
            Jira2Service jira2Service= new Jira2Service();
            JFormsResponse jformDetails= jira2Service.getJFormDetails(jiraId.getText());
            loadJformDetails(jformDetails);

        }
        setVisible(false);
    }

    private void loadJformDetails(JFormsResponse jformDetails) {
    }


    public void loadJiraDetails(Jira2Response jiraDetails) {


    }
}
