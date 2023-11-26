package twitter.GUI.pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestPage {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JTextArea Without Scrollbar");
        JTextArea textArea = new JTextArea("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        JTextArea textArea2 = new JTextArea("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");

        // 줄 바꿈 비활성화
        textArea.setLineWrap(true);
        textArea2.setLineWrap(true);

        JButton addButton = new JButton("Add Text");

        Container container = frame.getContentPane();
        JPanel testPanel = new JPanel();
        testPanel.add(textArea);
        testPanel.setBackground(Color.YELLOW);
        JPanel testPanel2 = new JPanel();
        testPanel2.add(textArea2);
        testPanel2.setBackground(Color.BLUE);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        mainPanel.add(testPanel2);
        mainPanel.add(testPanel);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        container.add(scrollPane);
        frame.add(addButton, "South");
        var topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1,3));
        topPanel.add(new JButton("test button 1"));
        topPanel.add(new JButton("test button 2"));
        topPanel.add(new JButton("test button 3"));
        frame.add(topPanel,"North");
/*        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 텍스트 추가
                textArea.append("New line of textNew line of textNew line of textNew line of textNew line of text\n");
                testPanel.setSize(new Dimension(200,textArea.getPreferredSize().height));
                textArea2.append("New line of textNew line of textNew line of textNew line of textNew line of text\n");
//                textArea2.setSize(new Dimension(200,textArea.getPreferredSize().height));
            }
        });*/

        frame.setSize(300, 500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
