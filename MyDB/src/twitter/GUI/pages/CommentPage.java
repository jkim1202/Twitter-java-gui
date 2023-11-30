package twitter.GUI.pages;

import twitter.GUI.dao.CommentUserDao;
import twitter.GUI.dao.PostInfoDao;
import twitter.GUI.dao.UserDao;
import twitter.GUI.designs.RoundJButton;
import twitter.GUI.service.CommentService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

public class CommentPage extends JFrame{
    private final CommentService commentService = new CommentService();
    private JPanel panel;

    public CommentPage(PostInfoDao postInfoDao,UserDao userDao, Connection con) {
        super("Comment");
        setLocationByPlatform(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                CommentPage.this.dispose();
                MainPage mainPage = new MainPage(userDao, con);
                mainPage.setVisible(true);
            }
        });


        panel = new JPanel(new BorderLayout());
        JPanel labels = new JPanel(new GridLayout(0, 1, 0, 1));
        JPanel fields = new JPanel(new GridLayout(0, 1, 0, 1));
        panel.add(labels, BorderLayout.LINE_START);
        panel.add(fields, BorderLayout.CENTER);

        // get comment and add JPanels
        boolean flag = commentService.findAllCommentsByPost(this,labels,fields,postInfoDao,userDao, con);

        RoundJButton btn = new RoundJButton("Add Comment", 0, Color.GRAY, Color.white);
        add(btn, BorderLayout.PAGE_END);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String commentText = JOptionPane.showInputDialog(null, "Enter Comment:");
                if(commentText!=null){
                    System.out.println("userNo >> postInfoDao.getUser_no() = " + postInfoDao.getUser_no());
                    System.out.println("PostId >> postInfoDao.getId() = " + postInfoDao.getId());
                    commentService.createComment(new CommentUserDao(commentText,userDao.getUser_no(),postInfoDao.getId()),con);
                    dispose();
                    CommentPage newPage = new CommentPage(postInfoDao,userDao,con);
                    newPage.setVisible(true);
                }
            }
        });

        JScrollPane jp = new JScrollPane(
                panel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(jp);

        pack();
        Dimension d = getSize();
        int w = (int) d.getWidth();
        int h = (int) d.getHeight();
        h = (Math.min(h, 400));
        Dimension shrinkHeight = new Dimension(w, h);
        if(flag) {
            setSize(shrinkHeight);
        } else{
            setSize(400,100);
            JLabel noCommentT = new JLabel("No comment exists in this post yet.");
            noCommentT.setHorizontalAlignment(SwingConstants.CENTER);
            add(noCommentT, "Center");
        }
    }
}
