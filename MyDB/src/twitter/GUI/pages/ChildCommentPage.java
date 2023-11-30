package twitter.GUI.pages;

import twitter.GUI.dao.ChildCommentUserDao;
import twitter.GUI.dao.CommentUserDao;
import twitter.GUI.designs.RoundJButton;
import twitter.GUI.service.ChildCommentService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

public class ChildCommentPage extends JFrame {
    private final ChildCommentService childCommentService = new ChildCommentService();
    private JPanel panel;
    public ChildCommentPage(Integer user_no,JFrame frame, CommentUserDao commentUserDao, Connection con) throws HeadlessException {
        super("Comment");
        setLocationByPlatform(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ChildCommentPage.this.dispose();
                frame.setVisible(true);
            }
        });

        panel = new JPanel(new BorderLayout());
        JPanel labels = new JPanel(new GridLayout(0, 1, 0, 1));
        JPanel fields = new JPanel(new GridLayout(0, 1, 0, 1));
        panel.add(labels, BorderLayout.LINE_START);
        panel.add(fields, BorderLayout.CENTER);

        // get comment and add JPanels
        boolean flag = childCommentService.findAllChildCommentsByComment(this,labels,fields,commentUserDao, con);

        RoundJButton btn = new RoundJButton("Add Comment", 0, Color.GRAY, Color.white);
        add(btn, BorderLayout.PAGE_END);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String childCommentText = JOptionPane.showInputDialog(null, "Enter Comment:");
                if(childCommentText!=null){
                    ChildCommentUserDao childCommentUserDao = new ChildCommentUserDao();
                    childCommentUserDao.setText(childCommentText);
                    childCommentUserDao.setUser_user_no(user_no);
                    childCommentUserDao.setComment_id(commentUserDao.getComment_id());
                    childCommentService.createChildComment(childCommentUserDao,con);
                    ChildCommentPage.this.dispose();
                    ChildCommentPage newPage = new ChildCommentPage(user_no, frame, commentUserDao, con);
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
        System.out.println("h = " + h);
        h = (h > 400 ? 400 : h);
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
