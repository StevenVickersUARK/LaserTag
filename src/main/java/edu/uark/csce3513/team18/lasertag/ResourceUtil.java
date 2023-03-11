package edu.uark.csce3513.team18.lasertag;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class ResourceUtil {
    private static final int IMAGE_DATA_SIZE = 16384;

    public static ImageIcon loadImageIcon(String name) throws ResourceLoadingException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(name);
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[IMAGE_DATA_SIZE];
        try {
            while ((nRead = is.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
        } catch (Exception ex) {
            throw new ResourceLoadingException(ex);
        }
        ImageIcon imageIcon = new ImageIcon(buffer.toByteArray());
        return imageIcon;
    }

    public static Clip loadAudioClip(String name) throws ResourceLoadingException {
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream(name);
            AudioInputStream sound = AudioSystem.getAudioInputStream(is);
            Clip clip = AudioSystem.getClip();
            clip.open(sound);
            return clip;
        } catch (Exception ex) {
            throw new ResourceLoadingException(ex);
        }
    }
}
