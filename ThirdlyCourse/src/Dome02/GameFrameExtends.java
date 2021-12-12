package Dome02;

import Dome01.GamePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 *  游戏的入口，存放整个游戏整个基本框架
 *
 *  @ WakingHours
 */
public class GameFrameExtends extends JFrame {
    JPanel panel1,panel2;
    JButton beginButton, endButton;
    BufferedImage socPic;//读取文件图像

    {
        try {
            socPic = ImageIO.read(new File("E:\\HP\\Desktop\\JAVA\\test3.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    JButton[] picButton;


    public GameFrameExtends(){
        super("拼图游戏");
        beginButton = new JButton("开始游戏");
        endButton = new JButton("结束游戏");

        panel1 = new JPanel();
        panel1.add(beginButton);
        panel1.add(endButton);
        this.add(panel1, BorderLayout.NORTH);

        this.add(new GameCore(), BorderLayout.CENTER);

        panel2 = new JPanel();// 用于存放中间的图片（制作拼图）
        JLabel jb = new JLabel(new ImageIcon(socPic));
        panel2.add(jb);
        this.add(panel2);


        beginButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // 游戏开始

            }
        });
        endButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 游戏结束
            }
        });



        this.setBounds(100,100,600,600);
        this.setVisible(true);

    }





}
