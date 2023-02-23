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


public class TeamIntroScreen extends JFrame
{
    Timer t;
    int x;

    public TeamIntroScreen(){
        this.setSize(1280, 720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.black);
        teamIntro();
        
    }

    public void teamIntro()
    {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("team18.png");
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[16384];

        try {
            while ((nRead = is.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageIcon logo = new ImageIcon(buffer.toByteArray()); // path needs to be changed before file is merged
        Image tempImage1 = logo.getImage();
        Image tempImage2 = tempImage1.getScaledInstance(500, 500, Image.SCALE_SMOOTH); // resizing image
        logo = new ImageIcon(tempImage2);


        JLabel label = new JLabel(logo);
        label.setIcon(logo);
        label.setForeground(new Color(255,255,255,0));
        label.setOpaque(true);
        getContentPane().add(label);
        t = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                label.setForeground(new Color(0,0,0,x++));
                label.setBackground(new Color(255,255,255,x));
                if (x==255) t.stop();
            }
        });
        t.start();
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((size.width - getWidth()) / 2, (size.height - getHeight()) / 2); // place image in center
        showTeamIntro();
    }

    public void showTeamIntro()
    {
        int duration = 2600; // show splash screen for 2.6 seconds
        setVisible(true);
        try 
        {
            setFile("heavyBeam.wav");
            Thread.sleep(duration); // wait for duration
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        setVisible(false);

    }

    Clip clip;
    AudioInputStream sound;
    public void setFile(String soundFileName) {
        try {        
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream(soundFileName);
            sound = AudioSystem.getAudioInputStream(is);
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (Exception e) {
            System.out.println("Error with audio.");
            System.out.println(e);
        }
        clip.start();
    }
}