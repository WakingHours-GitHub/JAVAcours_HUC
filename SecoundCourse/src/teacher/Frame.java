package teacher;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;
public class Frame extends JFrame{
    JPanel panel1,panel2,panel3;//三个面板展示不同的内容
    JButton button1,button2;//两个按钮分别代表上月和下月
    String []str={"日","一","二","三","四","五","六"};
    JButton button[]=new JButton[str.length];
    JLabel [] label;
    JLabel now=new JLabel();
    calendarBean calen=new calendarBean();
    String []day;
    Frame(){
        init();
    }
    void init(){
        calen.setYear(2020);
        calen.setMonth(11);
        button1=new JButton("上月");
        button1.setActionCommand("last");
        button1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                calen.actionPerformed(e);
            }
        });
        button2=new JButton("下月");
        button2.setActionCommand("next");
        button2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                calen.actionPerformed(e);
            }
        });
        panel1=new JPanel();
        panel1.add(button1);
        panel1.add(button2);
        GridLayout grid=new GridLayout(7,7);
        add(panel1,BorderLayout.NORTH);
        panel2=new JPanel();
        for(int i=0;i<str.length;i++){
            button[i]=new JButton(str[i]);
            panel2.add(button[i]);
        }
        day=calen.getCalendar();
        label=new JLabel[42];
        for(int i=0;i<42;i++){
            label[i]=new JLabel();
            panel2.add(label[i]);
        }
        calen.label=this.label;
        for(int i=0;i<day.length;i++){
            label[i].setText("        "+day[i]);
        }
        add(panel2,BorderLayout.CENTER);
        panel2.setLayout(grid);
        panel3=new JPanel();
        now.setText(calen.year+"年"+calen.month+"月");
        calen.now=now;
        panel3.add(now);
        add(panel3,BorderLayout.SOUTH);
    }
}
