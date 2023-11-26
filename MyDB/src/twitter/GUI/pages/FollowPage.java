package twitter.GUI.pages;

import twitter.GUI.designs.FollowPanel;

import javax.swing.*;
import java.awt.*;

public class FollowPage extends JFrame {
    public FollowPage() throws HeadlessException {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = getContentPane();

        JPanel midPanel = new JPanel();
        midPanel.setLayout(new BoxLayout(midPanel,BoxLayout.Y_AXIS));
//        midPanel.setLayout(new FlowLayout());

        FollowPanel followPanel1 = new FollowPanel("../twitterLogo.png","Jkim1202","Jkim");
//        followPanel1.setPreferredSize(new Dimension(350,70));
        FollowPanel followPanel2 = new FollowPanel("../twitterLogo.png","Jkim1202123","Jkim2");
//        followPanel2.setPreferredSize(new Dimension(350,70));

        midPanel.add(followPanel1);
        midPanel.add(followPanel2);

        // 중단 JScrollPane
        JScrollPane midScrollPane = new JScrollPane(midPanel);

        container.add(midScrollPane);

        if (getPreferredSize().height < 450)  setSize(new Dimension(400, getPreferredSize().height + 100));
        else setSize(new Dimension(400, 550));
        setResizable(false); // Make the frame not resizable
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FollowPage::new);
    }

}
