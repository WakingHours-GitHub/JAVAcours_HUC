package Dome01;

// GameFrame类------设置游戏的打开窗口类，创建了一个菜单面板存放游戏开始和游戏结束两个按钮，并且对游戏的窗口进行了基本设置，这是整个游戏的入口。


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame {
    public JPanel pane1 = new JPanel();
    public JButton button1 = new JButton("游戏开始");
    public JButton button2 = new JButton("游戏结束");

    public GameFrame() {
        super("拼图游戏");
        pane1.setLayout(new FlowLayout());
        pane1.add(button1);
        pane1.add(button2);

        Container con = this.getContentPane();
        con.add(pane1, BorderLayout.NORTH);
        GamePanel gamePanel = new GamePanel();
        con.add(gamePanel, BorderLayout.CENTER);
        this.setBounds(100, 100, 600, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                gamePanel.OutOfOrder(); // 开始游戏，开始乱序
            }
        });
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(1); // 结束游戏
            }
        });
    }

    public static void main(String[] args) {
        new GameFrame();
    }
}