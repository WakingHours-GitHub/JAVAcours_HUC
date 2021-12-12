package dome02;

import javax.swing.*;

public class main extends JFrame {
    public static void main(String[] args) {
        JFrame jf  = new JFrame("测试");
        JPanel jp = new JPanel();
        JButton jb1 = new JButton("按钮");
        jp.add(jb1);
        jf.add(jp);

    }
}
