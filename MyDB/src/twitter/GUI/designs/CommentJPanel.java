package twitter.GUI.designs;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.util.Objects;

public class CommentJPanel extends JPanel {
    public CommentJPanel(String proUrl, Connection con) {
        super();
        setBorder(BorderFactory.createLineBorder(Color.black,1));
        // west panel --> profile image
        JPanel westP = new JPanel();
        westP.setBackground(Color.white);
        westP.setLayout(new BorderLayout());
        add(westP, "West");

        // profile image 생성
        ImageJLabel profileImg;
        profileImg = new ImageJLabel(Objects.requireNonNullElse(proUrl, "../TwitterLogo.png"), 40, 40);
        profileImg.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        profileImg.setVerticalAlignment(JLabel.TOP);
        westP.add(profileImg, "North");

        // center panel --> text, content images, id
        JPanel centerP = new JPanel();
        centerP.setBackground(Color.white);
        centerP.setLayout(new BoxLayout(centerP, BoxLayout.Y_AXIS));
        add(centerP, "Center");


        // id JLabel and Panel
        JLabel id = new JLabel("ID: jkim");
        JPanel idP = new JPanel();
        idP.add(id);
        centerP.add(idP,"West");

        // comment JTextArea
        JTextArea comment = new JTextArea("Comment: asdfjhk");
        JPanel commentP = new JPanel();
        commentP.add(comment);
        centerP.add(commentP);
    }

}
