package Course;

 // 游戏入口，启动游戏

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GameFrameExtends extends JFrame {
    int cntPattern = 1; // 设置难度的数字,计数
    int cntSwitchPic = 1; // 设置图片，计数
    CorePanel corePanel;
    JPanel totalPanel = new JPanel();

    int imageWidth; // 图片高度
    int imageHeight; //图片宽度

    String socPath; // 图片文件路径
    int pattern = 3; // 图片底数，设置初始值为3

    JButton beginButton = new JButton("游戏开始");
    JButton endButton = new JButton("游戏结束");
    JButton rankButton = new JButton("游戏等级");
    JButton socButton = new JButton("显示原始图片");
    JButton switchPic = new JButton("切换图片");
    JLabel rankLabel = new JLabel(); // 游戏难度显示 标签
    JLabel picLabel = new JLabel(); // 显示现在是第几张照片 标签

    public GameFrameExtends() {
        // 调用父类构造方法，设置标题
        super("拼图游戏");

        totalPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // 设置为流式布局，并且中心对齐
        totalPanel.add(beginButton); // 添加开始开始按钮
        totalPanel.add(endButton); // 添加结束游戏按钮
        totalPanel.add(rankButton); // 添加游戏等级按钮
        totalPanel.add(socButton); // 添加显示原始图片按钮
        totalPanel.add(switchPic); // 添加切换图片按钮
        totalPanel.add(rankLabel); // 添加等级标签：显示现在是什么等级
        totalPanel.add(picLabel); // 添加图片: 显示现在是第几张图片

        rankLabel.setText("简单"); // 显示现在是什么难度（初始为3，简单）
        picLabel.setText("现在是第"+cntSwitchPic+"图片"); // 显示现在是第几张图片（初始为1，第一张照照片）

        this.add(totalPanel, BorderLayout.NORTH); // 将panel1添加到本顶级容器中

        // 图片部分
        corePanel = new CorePanel(socPath, pattern); // 把文件路径和图片底数传入CorePanel的构造方法
        this.add(corePanel, BorderLayout.CENTER);

        // 对顶级容器基本设置
        this.setBounds(200, 200, 620, 660); // 设置顶级容器位置以及大小
        this.setResizable(false); // 设置此窗体是否可由用户调整大小。
        this.setVisible(true); // 设置此窗体是否显示
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE); // 设置此窗体是否可以关闭

        // 当点击“开始游戏”时的事件响应
        beginButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                setLabelTest(pattern); // 根据初始底数显示等级
                breakStart(); // 游戏的启动
            }
        });

        // 当点击“结束游戏”时的事件响应
        endButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(1); // 结束游戏
            }
        });

        // 当点击“显示原始图片”时的事件响应
        socButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SocPicFrame(socPath); // 显示原始图片
            }
        });

        // 当点击切换图片时的事件响应
        switchPic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cntSwitchPic++;
                if(cntSwitchPic == 5) cntSwitchPic = 1; // 重置
                setLabelTest(pattern); // 根据初始底数显示等级
                breakStart(); // 游戏的启动
                picLabel.setText("现在是第"+cntSwitchPic+"图片");
            }
        });

        // 当按下“切换等级”时的事件响应
        rankButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cntPattern++;
                // 当按下切换等级时，设置等级，并且充值pattern图片底数
                switch (cntPattern){
                    case 1->{
                        rankLabel.setText("简单"); pattern = 3;}
                    case 2->{
                        rankLabel.setText("中等"); pattern = 4;}
                    case 3->{
                        rankLabel.setText("困难"); pattern = 5;}
                }
                if(cntPattern == 3) cntPattern = 0;//重置
                breakStart(); // 游戏的启动
            }

        });
    }

    /**
     * 根据pattern切换显示内容
     *
     * @param pattern
     * @author WakingHours
     */
    public void setLabelTest(int pattern){
        switch (pattern) {
            case 3 -> rankLabel.setText("简单");
            case 4 -> rankLabel.setText("中等");
            case 5 -> rankLabel.setText("困难");
        }

    }

    /**
     *设置开始游戏的方法：开始切割和重新排序图片
     *
     * @author WakingHours
     */
    public void breakStart(){
        /*socPath = */setPath(); // 设置图片路径
        corePanel.setBreakRandom(socPath, pattern); // 调用
    }

    /**
     *设置图片路径
     *
     * @author WakingHours
     */
    public void setPath() {
        // 这里cntSwitchPic是为了切换图片文件夹做准备(用于后面切换图片)
        socPath = "E:\\HP\\Desktop\\JAVA\\pic" + cntSwitchPic +"\\";
    }

}