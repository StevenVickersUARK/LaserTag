package edu.uark.csce3513.team18.lasertag;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.*;

public class ImageUtil {
    private static final int IMAGE_DATA_SIZE = 16384;

    public static ImageIcon loadImageIcon(String name) throws ImageException {
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
            throw new ImageException(ex);
        }
        ImageIcon imageIcon = new ImageIcon(buffer.toByteArray());
        return imageIcon;
    }
}
