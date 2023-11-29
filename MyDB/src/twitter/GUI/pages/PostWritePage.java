package twitter.GUI.pages;

import twitter.GUI.dao.PostDao;
import twitter.GUI.dao.UserDao;
import twitter.GUI.designs.ActionJLabel;
import twitter.GUI.service.PostService;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;

public class PostWritePage extends JFrame {
    private final PostService postService = new PostService();
    private JTextArea postContentTextArea;
    private JTextField imageURLTextField;
    private JButton submitButton;
    private Connection con;
    private UserDao userDao;

    public PostWritePage(Connection con, UserDao userDao) {
        this.con = con;
        this.userDao = userDao;
        initializeUI();
    }

    private void initializeUI() {
        ArrayList<String> urls = new ArrayList<>();
        setTitle("Post Creation Page");
        setSize(450, 550);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        Container container = getContentPane();
        container.setLayout(new GridLayout(3, 1));
        JPanel topP = new JPanel();
        JPanel urlP = new JPanel();
        JPanel botP = new JPanel();

        // 포스트 내용 입력 필드
        postContentTextArea = new JTextArea();
        postContentTextArea.setLineWrap(true);
        JScrollPane contentScrollPane = new JScrollPane(postContentTextArea);
        contentScrollPane.setPreferredSize(new Dimension(400, 150)); // 크기 조정

        // 이미지 URL 입력 필드
        imageURLTextField = new JTextField();
        imageURLTextField.setPreferredSize(new Dimension(300, 30));

        // 제출 버튼
        submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(100, 30));
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onPostSubmit(urls);
            }
        });
        botP.add(submitButton);

        // 레이아웃 설정
        topP.setLayout(new FlowLayout(FlowLayout.LEFT)); // 왼쪽 정렬
        topP.add(new JLabel("Enter your post content:"));
        container.add(topP);
        topP.add(contentScrollPane);
//        container.add(contentScrollPane);

        urlP.setLayout(new FlowLayout());
        JButton chooseButton = new JButton("Choose Image");
        urlP.add(new JLabel("Choose images to add: "));
        urlP.add(chooseButton);
        container.add(urlP);
        chooseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String imagePath = chooseImage();
                if (imagePath != null) {
                    // 여기에서 imagePath를 사용하거나 반환하십시오.
                    urls.add(imagePath);
                    ActionJLabel img = new ActionJLabel(imagePath);
                    urlP.add(img);
                    img.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            int result = JOptionPane.showConfirmDialog(null, "Want to remove?", "Remove Image",JOptionPane.YES_NO_OPTION);
                            if(result == 0){
                                img.setVisible(false);
                                urlP.remove(img);
                                PostWritePage.this.setVisible(true);
                                urls.remove(img.getText());
                            }
                        }
                    });

                    setVisible(true);
                }
            }
        });

        container.add(botP);
    }

    private void onPostSubmit(ArrayList<String> urls) {
        String postContent = postContentTextArea.getText();;
        // 예시로 콘솔에 출력
        System.out.println("Submitted Post Content: " + postContent);
        System.out.println("Submitted Image URL: " + urls.toString());

        PostDao postDao = new PostDao(null, postContent, java.sql.Date.valueOf(LocalDate.now()), 0, PostWritePage.this.userDao.getUser_no());
        postService.createPost(urls,postDao, PostWritePage.this.con);

        // 포스트 작성 페이지 닫기 (선택적)
        dispose();
        MainPage mainPage = new MainPage(userDao,con);
        mainPage.setVisible(true);
    }
    private String chooseImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose an Image");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif"));

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            return selectedFile.getPath();
        }

        return null; // 파일을 선택하지 않은 경우
    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new PostWritePage().setVisible(true);
//            }
//        });
//    }
}
