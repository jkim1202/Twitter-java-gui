package twitter.GUI.designs;

import twitter.GUI.pages.LogInPage;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class PostJPanel extends JPanel {
    // 추후 패러미터 DAO로 변경
    public PostJPanel(String postText, ArrayList<String> urls, String proUrl, String id) {
        /*
         * 게시물 정보 쿼리로 조회해서 넘겨 받으면 패널 안에 게시물 형태로 생성 시키기*/
        super();
        setBackground(Color.white);
        setLayout(new BorderLayout());
        // west panel --> profile image
        JPanel westP = new JPanel();
        westP.setBackground(Color.white);
        westP.setLayout(new BorderLayout());
        add(westP,"West");

        // center panel --> text, content images, id
        JPanel centerP = new JPanel();
        centerP.setBackground(Color.white);
        centerP.setLayout(new BoxLayout(centerP, BoxLayout.Y_AXIS));
        add(centerP,"Center");

        // profile image 생성
        JLabel profileImg = new JLabel();
        ImageIcon icon = new ImageIcon(
                Objects.requireNonNull(LogInPage.class.getResource(proUrl))
        );
        Image img = icon.getImage();
        Image updateImg = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon updateIcon = new ImageIcon(updateImg);
        profileImg.setIcon(updateIcon);
        profileImg.setVerticalAlignment(JLabel.TOP);
        westP.add(profileImg,"North");


        // id 생성
        var userId = new JLabel(id);
        JPanel idP = new JPanel();
        userId.setHorizontalAlignment(JLabel.LEFT);
        userId.setBackground(Color.red);
        idP.add(userId);
        centerP.add(idP);

        // text 생성
        var text = new JTextArea(postText);
        text.setSize(new Dimension(400,text.getPreferredSize().height));
        text.setLineWrap(true);
        text.setEditable(false);
        centerP.add(text);

        // 이미지 생성
        for(String url : urls){
            JPanel p = new JPanel();
            p.setBackground(Color.WHITE);
            JLabel contentImg = new JLabel();
            ImageIcon icon2 = new ImageIcon(
                    Objects.requireNonNull(LogInPage.class.getResource(url))
            );
            Image img2 = icon2.getImage();
            Image updateImg2 = img2.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            ImageIcon updateIcon2 = new ImageIcon(updateImg2);
            contentImg.setIcon(updateIcon2);
            contentImg.setHorizontalAlignment(JLabel.LEFT);
            p.add(contentImg);
            centerP.add(p);
        }

        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    }
}
