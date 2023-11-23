package twitter.GUI.designs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;

public class RoundJTextField extends JTextField {
    private Shape shape;

    public RoundJTextField() {
        super();
        setOpaque(false); // As suggested by @AVD in comment
    }

    public RoundJTextField(String text) {
        super(text);
        setOpaque(false); // As suggested by @AVD in comment.
    }
    public RoundJTextField(String text, boolean bool) {
        super(text);
        setOpaque(false); // As suggested by @AVD in comment.
        if(bool){
            addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    if(getText().equals("")||getText().equals(text)){
                        setText("");
                    }
                }
                @Override
                public void focusLost(FocusEvent e) {
                    if(getText().equals("")){
                        setText(text);
                    }
                }
            });
        }
    }
    public RoundJTextField(int size) {
        super(size);
        setOpaque(false); // As suggested by @AVD in comment.
    }

    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
    }

    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
        }
        return shape.contains(x, y);
    }
}


