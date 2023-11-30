package twitter.GUI.pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

public class FindPage extends JFrame {
    public FindPage(Connection con) throws HeadlessException {
        super();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                FindPage.this.dispose();
                LogInPage logInPage = new LogInPage(con);
                logInPage.setVisible(true);
            }
        });
    }


}
