package twitter.GUI.designs;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ImageJLabel extends JLabel {
    public ImageJLabel(String path, int width, int height) {
        super();
        ImageIcon icon = createImageIcon(path);
        System.out.println("icon = " + icon);
        if (icon != null) {
            Image img = icon.getImage();
            Image updateImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon updateIcon = new ImageIcon(updateImg);
            setIcon(updateIcon);
        } else {
            System.err.println("Image not found: " + path);
        }
    }

    private ImageIcon createImageIcon(String path) {
        ImageIcon icon = null;
        // todo: null image >> 기본 이미지로 설정
        if(path == null){
            path = "../defaultProfile.png";
        }
        // Check if the path is absolute or relative
        if (path.contains(":")) {
            // Absolute path, try to load directly
            icon = new ImageIcon(path);
        } else {
            // Relative path, load using class loader
            URL resource = getClass().getResource(path);
            if (resource != null) {
                icon = new ImageIcon(resource);
            }
        }

        return icon;
    }
}

