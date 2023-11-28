package twitter.GUI.pages;

import javax.swing.*;
import java.awt.*;

public class TestPage {
    public TestPage() {
        final JFrame frame = new JFrame();
        frame.setTitle("Test vertical alignement");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel label1 = new JLabel("label1");
        JLabel label2 = new JLabel("label2");
        panel.add(label1, gbc);
        panel.add(label2, gbc);
        frame.add(panel);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        TestPage testPage = new TestPage();
    }
}
