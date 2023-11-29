package twitter.GUI.designs;

import javax.swing.*;
import java.awt.*;

public class RoundJButton extends JButton {
    private int arcSize = 0;
    private Color textC = new Color(255,255,255);
    private Color bntC = new Color(82, 182, 236);
    public RoundJButton() { super(); decorate(); }
    public RoundJButton(String text) { super(text); decorate(); }
    public RoundJButton(String text, int newArcSize) { super(text); arcSize=newArcSize; decorate(); }
    public RoundJButton(String text, int newArcSize, Color bntColor, Color textColor) { super(text);
        arcSize=newArcSize; decorate();
        textC = textColor;
        bntC = bntColor;
    }
    public RoundJButton(Action action) { super(action); decorate(); }
    public RoundJButton(Icon icon) { super(icon); decorate(); }
    public RoundJButton(String text, Icon icon) { super(text, icon); decorate(); }
    protected void decorate() { setBorderPainted(false); setOpaque(false); }
    @Override
    protected void paintComponent(Graphics g) {
        Color c= bntC; //배경색 결정
        Color o= textC; //글자색 결정
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(c);
        if (getModel().isArmed()) { graphics.setColor(c.darker()); }
//        else if (getModel().isRollover()) { graphics.setColor(c.brighter()); }
//        else { graphics.setColor(c); }
        graphics.fillRoundRect(0, 0, width, height, arcSize, arcSize);
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds();
        int textX = (width - stringBounds.width) / 2;
        int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent();
        graphics.setColor(o);
//        graphics.setFont(getFont());
        graphics.setFont(new Font("sans-serif",Font.PLAIN,16));
        graphics.drawString(getText(), textX, textY);
        graphics.dispose();
        super.paintComponent(g);
    }
}