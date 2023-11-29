package twitter.GUI.designs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ActionJLabel extends JLabel {

    public ActionJLabel(String text) {
        super(text);
        setForeground(new Color(82, 182, 236));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setForeground(new Color(82, 182, 236).darker());
            }
            @Override
            public void mouseExited(MouseEvent e) {
                setForeground(new Color(82, 182, 236));
            }
        });
    }
    public ActionJLabel(String text, Color c) {
        super(text);
        setForeground(c);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setForeground(c.darker().darker());
            }
            @Override
            public void mouseExited(MouseEvent e) {
                setForeground(c);
            }
        });
    }
}

