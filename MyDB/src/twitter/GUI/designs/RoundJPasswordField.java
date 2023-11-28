package twitter.GUI.designs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.geom.RoundRectangle2D;

public class RoundJPasswordField extends JPasswordField {
    private Shape shape;

    public RoundJPasswordField() {
        super();
        setOpaque(false);
    }

    public RoundJPasswordField(String text) {
        super(text);
        setOpaque(false);
    }
    
    public RoundJPasswordField(String text, boolean bool) {
        super(text);
        setOpaque(false); // As suggested by @AVD in comment.
        if(bool){
            addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    String password = new String(getPassword());
                    if(password.equals("")||password.equals(text)){
                        setText("");
                    }
                }
                @Override
                public void focusLost(FocusEvent e) {
                    String password = new String(getPassword());
                    if(password.equals("")){
                        setText(text);
                    }
                }
            });
        }
    }

    public RoundJPasswordField(int size) {
        super(size);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        super.paintComponent(g);

    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
    }

    @Override
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
        }
        return shape.contains(x, y);
    }
}
