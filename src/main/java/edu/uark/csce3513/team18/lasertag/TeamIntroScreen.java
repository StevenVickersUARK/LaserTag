package edu.uark.csce3513.team18.lasertag;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.Timer;

public class TeamIntroScreen extends JFrame {
    private static final String LOGO_NAME = "team18.png";

    Timer t;
    int x;

    public TeamIntroScreen() {
        this.setSize(1280, 720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.black);
        teamIntro();

    }

    public void teamIntro() {
        try {
            ImageIcon logo = ResourceUtil.loadImageIcon(LOGO_NAME);
            Image tempImage1 = logo.getImage();
            Image tempImage2 = tempImage1.getScaledInstance(500, 500, Image.SCALE_SMOOTH); // resizing image
            logo = new ImageIcon(tempImage2);

            JLabel label = new JLabel(logo);
            label.setIcon(logo);
            label.setForeground(new Color(255, 255, 255, 0));
            label.setOpaque(true);
            getContentPane().add(label);
            t = new Timer(10, new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    label.setForeground(new Color(0, 0, 0, x++));
                    label.setBackground(new Color(255, 255, 255, x));
                    if (x == 255)
                        t.stop();
                }
            });
            t.start();
            Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
            setLocation((size.width - getWidth()) / 2, (size.height - getHeight()) / 2); // place image in center
            showTeamIntro();
        } catch (ResourceLoadingException ex) {
            System.err.println("Failed to load intro image!");
        }
    }

    public void showTeamIntro() {
        int duration = 2600; // show splash screen for 2.6 seconds
        setVisible(true);
        try {
            setFile("heavyBeam.wav");
            Thread.sleep(duration); // wait for duration
        } catch (Exception e) {
            e.printStackTrace();
        }
        setVisible(false);

    }

    public void setFile(String soundFileName) {
        try {
            Clip clip = ResourceUtil.loadAudioClip(soundFileName);
            clip.start();
        } catch (ResourceLoadingException ex) {
            System.out.println("Error loading audio clip!");
            ex.printStackTrace();
        }
    }
}
