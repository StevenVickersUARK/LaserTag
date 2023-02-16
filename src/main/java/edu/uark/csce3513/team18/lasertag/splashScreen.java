import java.awt.*;
import javax.swing.*;

public class splashScreen extends JFrame
{
    private final int duration = 3000; // show splash screen for 3 seconds

    public splashScreen()
    {
        this.setSize(1280, 720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.black);

        ImageIcon logo = new ImageIcon("logo.jpg"); // path needs to be changed before file is merged
        Image tempImage1 = logo.getImage();
        Image tempImage2 = tempImage1.getScaledInstance(1280, 720, Image.SCALE_SMOOTH); // resizing image
        logo = new ImageIcon(tempImage2);

        JLabel label = new JLabel(logo);
        this.getContentPane().add(label);
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((size.width - getWidth()) / 2, (size.height - getHeight()) / 2); // place image in center
    }

    public void showSplashScreen()
    {
        this.setVisible(true);
        try 
        {
            Thread.sleep(duration); // wait for duration
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        this.setVisible(false);
    }
    /*
    public static void main(String[] args) 
    {
        splashScreen splash = new splashScreen(); // declare splashScreen and call from main.java
        splash.showSplashScreen();
    }
    */
