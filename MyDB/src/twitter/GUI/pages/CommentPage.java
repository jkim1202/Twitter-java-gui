package twitter.GUI.pages;

import twitter.GUI.dao.PostDao;
import twitter.GUI.dao.PostInfoDao;
import twitter.GUI.dao.UserDao;
import twitter.GUI.designs.ImageJLabel;
import twitter.GUI.designs.RoundJButton;
import twitter.GUI.service.CommentService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.sql.Connection;

public class CommentPage extends JFrame{
    private final CommentService commentService = new CommentService();
    private JPanel panel;
    private JLabel lbl[];
    private JTextArea eq_Fields[];

    public CommentPage(int Count) {
        super("Solver");
        setLocationByPlatform(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel(new BorderLayout());
        JPanel labels = new JPanel(new GridLayout(0, 1, 0, 1));
//        JPanel fields = new JPanel(new GridLayout(0, 1, 0, 1));
        JPanel fields = new JPanel(new GridLayout(0, 1, 0, 1));
        lbl = new JLabel[Count];
        panel.add(labels, BorderLayout.LINE_START);

        panel.add(fields, BorderLayout.CENTER);
        eq_Fields = new JTextArea[Count];

        for (int i = 0; i < Count; i++) {
            var fieldsContainer = new JPanel(new BorderLayout());
            fields.add(fieldsContainer);
            lbl[i] = new ImageJLabel("../T2.png", 40, 40);
            eq_Fields[i] = new JTextArea(4, 30);

            JPanel j = new JPanel();
            j.setBackground(Color.white);
            j.add(lbl[i]);

            JPanel t = new JPanel();
            t.setBackground(Color.white);
            t.setLayout(new FlowLayout());
            t.add(eq_Fields[i]);

            // 버튼 panel
            var butsPanel = new JPanel(new FlowLayout());

            // like 이미지
            JPanel t2 = new JPanel(new GridLayout(0, 1, 0, 2));
            JPanel likeP = new JPanel();
            likeP.setBackground(Color.white);
            ImageJLabel like = new ImageJLabel("../Like.png", 20, 20);
            JLabel likeCnt = new JLabel("cnt");
            like.addMouseListener(new MouseAdapter() {
                // todo: 댓글 좋아요 추가
//                @Override
//                public void mouseClicked(MouseEvent e) {
//                    int newNo = likePostService.updateLike(con, new LikePostDao(user.getUser_no(), postInfoDao.getId()));
//                    likeCnt.setText(Integer.toString(newNo));
//                }
            });
            likeP.add(like);
            likeP.add(likeCnt);
            butsPanel.add(likeP);

            // comment 이미지
            var commentP = new JPanel();
            var comment = new ImageJLabel("../Comment.png",20,20);
            commentP.add(comment);
            butsPanel.add(commentP);

            t.add(t2);
            labels.add(j);
//            fields.add(t);
            fieldsContainer.add(t,"Center");
            fieldsContainer.add(butsPanel, "South");
        }
        RoundJButton btnSolve = new RoundJButton("Solve Equations!", 0, Color.GRAY, Color.white);
        add(btnSolve, BorderLayout.PAGE_END);

        JScrollPane jp = new JScrollPane(
                panel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(jp);

        pack();
        Dimension d = getSize();
        int w = (int) d.getWidth();
        int h = (int) d.getHeight();
        System.out.println("h = " + h);
        h = (h > 400 ? 400 : h);
        Dimension shrinkHeight = new Dimension(w, h);
        setSize(shrinkHeight);
    }
    public CommentPage(PostInfoDao postInfoDao, Connection con) {
        super("Solver");
        setLocationByPlatform(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel(new BorderLayout());
        JPanel labels = new JPanel(new GridLayout(0, 1, 0, 1));
//        JPanel fields = new JPanel(new GridLayout(0, 1, 0, 1));
        JPanel fields = new JPanel(new GridLayout(0, 1, 0, 1));
        panel.add(labels, BorderLayout.LINE_START);
        panel.add(fields, BorderLayout.CENTER);

        // get comment and add JPanels
        commentService.findAllCommentsByPost(labels,fields,postInfoDao, con);

        RoundJButton btnSolve = new RoundJButton("Solve Equations!", 0, Color.GRAY, Color.white);
        add(btnSolve, BorderLayout.PAGE_END);

        JScrollPane jp = new JScrollPane(
                panel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(jp);

        pack();
        Dimension d = getSize();
        int w = (int) d.getWidth();
        int h = (int) d.getHeight();
        System.out.println("h = " + h);
        h = (h > 400 ? 400 : h);
        Dimension shrinkHeight = new Dimension(w, h);
        setSize(shrinkHeight);
    }
    public static void main(String[] args) {
        Runnable r = new Runnable() {

            @Override
            public void run() {
                CommentPage sui = new CommentPage(3);
                sui.showUserInterface();
            }
        };
        SwingUtilities.invokeLater(r);
    }

    public void showUserInterface() {
        setVisible(true);
    }
}
