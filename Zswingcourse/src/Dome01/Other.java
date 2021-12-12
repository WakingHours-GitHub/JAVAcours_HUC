package Dome01;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Other extends JFrame{
    int cnt = 0;
    int pattern=  3;


    public Other(){
        JPanel panel1 = new JPanel();
        JButton jb1 = new JButton("开始游戏");
        JButton jb2 = new JButton("结束游戏");
        JButton jb3 = new JButton("游戏等级");
        JLabel jl1 = new JLabel();
        JLabel jl2 = new JLabel();

        jb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cnt++;
                switch (cnt){
                    case 1->{jl1.setText("简单"); pattern = 3;}
                    case 2->{jl1.setText("中等"); pattern = 4;}
                    case 3->{jl1.setText("困难"); pattern = 5;}
                }
                if(cnt == 3)cnt =0;//重置
            }
        });






        panel1.add(jb1);
        panel1.add(jb2);
//        panel1.add(menuRank);
        panel1.add(jb3);
        panel1.add(jl1);
//        jl2.setText(Integer.toString(pattern));
        panel1.add(jl2);

        this.add(panel1);
        this.setBounds(200,100,300,300);
        this.setVisible(true);

    }
}