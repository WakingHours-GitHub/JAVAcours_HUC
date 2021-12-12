package teacher.first;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // 顶级容器
        JFrame jf = new JFrame();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
        jf.setTitle("实验一");
        jf.setSize(500, 500);


        // 中间层容器
        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();
        jp2.setBackground(Color.white);
        jp2.setBounds(200, 200, 100, 100); // 在全局上面没什么作用
        jp2.setVisible(true);


        jp1.setVisible(true);
        jp1.setBounds(100, 100, 100, 100);
        jp1.setBackground(Color.CYAN);

        jf.add(jp1);
        jf.add(jp2);


    }
}
