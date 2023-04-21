package com.jira2.validator.ui;

import com.jira2.validator.response.JFormsResponse;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class LoginLauncher {
    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:\\Java Development\\jiraValidator\\img\\demo.png")))));
        frame.setVisible(true);
    }
}
