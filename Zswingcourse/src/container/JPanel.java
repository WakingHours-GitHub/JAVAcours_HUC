package container;

import javax.swing.*;

public class JPanel {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("这是顶级容器");
        javax.swing.JPanel jPanel = new javax.swing.JPanel();
        jPanel.add(new JTextField("这是一个测试文本框"));
        jPanel.add(new JButton("这是一个测试按钮"));

        jFrame.add(jPanel);

        jFrame.setBounds(100,100,500,500);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

}
