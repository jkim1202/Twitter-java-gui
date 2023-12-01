package twitter.GUI.pages;

import twitter.GUI.dao.UserDao;
import twitter.GUI.designs.ImageJLabel;
import twitter.GUI.designs.RoundJPanel;
import twitter.GUI.repository.UserRepository;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.sql.Connection;
import java.util.Objects;

public class AccountPage extends JFrame {
    private final UserRepository userRepository = new UserRepository();

    public AccountPage(UserDao userDao, Connection con) throws HeadlessException {

        super("Account Page");
        setTitle("Account Page");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationByPlatform(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                AccountPage.this.dispose();
                MainPage mainPage = new MainPage(userDao, con);
                mainPage.setVisible(true);
            }
        });

        JPanel mainP = new JPanel();
        mainP.setLayout(null);
        mainP.setBackground(Color.white);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(mainP, "Center");
        getContentPane().setBackground(Color.white);

        RoundJPanel rPanel = new RoundJPanel();
        rPanel.setLayout(new BorderLayout(10, 0));
        rPanel.setBounds(10, 10, 370, 80);
        rPanel.setBackground(Color.white);

        RoundJPanel rPanel2 = new RoundJPanel();
        rPanel2.setBounds(10, 90, 370, 360);
        rPanel2.setBackground(Color.white);
        rPanel2.setLayout(new BorderLayout(10, 0));

        mainP.add(rPanel);
        mainP.add(rPanel2);

        JPanel labels = new JPanel();
        JPanel fields = new JPanel();
        labels.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 17));
        fields.setLayout(new GridLayout(0, 1, 0, 1));
        rPanel.add(labels, BorderLayout.LINE_START);
        rPanel.add(fields, BorderLayout.CENTER);
        labels.setBackground(new Color(255, 0, 0, 0));
        fields.setBackground(Color.white);
        fields.setBorder(BorderFactory.createEmptyBorder());

        // pro image
        JLabel lbl = new ImageJLabel(userDao.getProfile_image_url(), 40, 40);
        setBackground(Color.white);
        labels.add(new JPanel());
        labels.add(lbl);

        JPanel textP = new JPanel();
        textP.setLayout(new BorderLayout());

        // id, introduce
        JLabel userId = new JLabel(userDao.getId() + " / " + userDao.getEmail());
        JLabel userIntroduce = new JLabel(userDao.getProfile());
        userIntroduce.setFont(new Font("sans-serif", Font.PLAIN, 12));
        JPanel idIntro = new JPanel(new GridLayout(5, 0, 0, 0));
        idIntro.setBackground(new Color(255, 0, 0, 0));
        idIntro.add(new JLabel());
        idIntro.add(userId);
        idIntro.add(userIntroduce);
        idIntro.add(new JLabel());
        idIntro.add(new JLabel());
        fields.add(idIntro);


        // Labels
        JLabel myInfo = new JLabel("Account");
        myInfo.setBorder(BorderFactory.createEmptyBorder(15, 15, 5, 0));
        myInfo.setFont(new Font("sans-serif", Font.BOLD, 20));
        getContentPane().add(myInfo, "North");
        JLabel account = new JLabel("Edit Info");
        account.setFont(new Font("sans-serif", Font.BOLD, 16));
        MouseAdapter changeEvent = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String title = ((JLabel) e.getComponent()).getText();
                System.out.println("title = " + title);
                String newInfo = null;
                if (!(e.getComponent() instanceof ImageJLabel)) {
                    newInfo = JOptionPane.showInputDialog(null, "Type your new " + title, "Change" + title);
                }
                if (newInfo != null) {
                    if (Objects.equals(title, "ID")) userDao.setId(newInfo);
                    else if (Objects.equals(title, "Email")) userDao.setEmail(newInfo);
                    else if (Objects.equals(title, "Profile")) userDao.setProfile(newInfo);
                    else if (Objects.equals(title, "Password")) userDao.setPassword(newInfo);
                    int result = userRepository.updateUser(userDao, con);
                    if (result > 0)
                        JOptionPane.showMessageDialog(null, "Success", "Success", JOptionPane.INFORMATION_MESSAGE);
                    else JOptionPane.showMessageDialog(null, "Duplicated!", "Failure", JOptionPane.WARNING_MESSAGE);
                } else {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Choose an Image");
                    fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif"));

                    int result = fileChooser.showOpenDialog(AccountPage.this);

                    if (result == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        userDao.setProfile_image_url(selectedFile.getPath());
                    }
                    int resultI = userRepository.updateUser(userDao, con);
                    if (resultI > 0)
                        JOptionPane.showMessageDialog(null, "Success", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
                AccountPage.this.dispose();
                AccountPage page = new AccountPage(userDao, con);
            }
        };

        lbl.addMouseListener(changeEvent);
        JLabel id = new JLabel("ID");
        id.addMouseListener(changeEvent);
        JLabel email = new JLabel("Email");
        email.addMouseListener(changeEvent);
        JLabel profile = new JLabel("Profile");
        profile.addMouseListener(changeEvent);
        JLabel password = new JLabel("Password");
        password.addMouseListener(changeEvent);
        JLabel logOut = new JLabel("Log Out");
        logOut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LogInPage logInPage = new LogInPage(con);
                AccountPage.this.dispose();
                logInPage.setVisible(true);
            }
        });
        JPanel centerP = new JPanel(new GridLayout(0, 1, 0, 0));
        centerP.setBackground(new Color(255, 0, 0, 0));
        centerP.add(account);
        centerP.add(id);
        centerP.add(email);
        centerP.add(profile);
        centerP.add(password);
        centerP.add(logOut);

        JPanel a = new JPanel();
        a.setBackground(new Color(255, 0, 0, 0));
        rPanel2.add(a, BorderLayout.LINE_START);
        rPanel2.add(centerP, BorderLayout.CENTER);

//        pack();
        setSize(400, 550);
        setResizable(false); // Make the frame not resizable
        setLocationRelativeTo(null);
        setVisible(true);

    }


}
