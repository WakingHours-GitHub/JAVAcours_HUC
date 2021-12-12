package course;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class frameExtends extends JFrame implements ActionListener{
    JPanel panel1, panel2, panel3;
    JButton actionButton1, actionButton2;
    String[] weekStr = new String[]{"日","一","二","三","四","五","六"}; // 设置星期显示内容
    JButton[] weekButton = new JButton[weekStr.length]; // new星期按钮对象
    JButton[] dateButton; // 网格中的元素：日期按钮
    JLabel now = new JLabel(); // 底下的显示当前年和月：小标签
    CalendarBean calendarBean = new CalendarBean(); // 创建CalendarBean对象
    String[] day; // 日期内容数组

    public frameExtends(){
        showDate_Init(); // 显示日期对其进行初始化
        // 设置顶级容器基本信息
        this.setTitle("日历"); // 设置标题
        this.setBounds(200,50,600,800); // 设置顶级容器起始位置，大小
        this.setVisible(true); // 设置顶级容器可见
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置顶级容器可关闭
    }


    private void showDate_Init() {
        Calendar calendar = Calendar.getInstance(); // 获取日历类对象
        Font defaultFont = new Font("微软雅黑",Font.BOLD,14); // 默认字体
        // 获取当前年、月
        this.calendarBean.setYear(calendar.get(Calendar.YEAR)); // 设置当前所在年
        this.calendarBean.setMonth(calendar.get(Calendar.MONTH) + 1); // 设置当前所在月份（因为是从0开始所以要加1）

        // 为两个切换按钮分别赋予基本信息
        actionButton1 = new JButton("上月");
        actionButton1.setActionCommand("last"); //设置此组件所激发的操作事件的命令名称
        actionButton1.addActionListener(new ActionListener() {
            // 使用匿名内部类，重写actionPerformed方法，调用CalendarBean类中的actionPerformed方法
            @Override
            public void actionPerformed(ActionEvent e) {
                calendarBean.actionPerformed(e);
            }
        });
        // 与上述同理
        actionButton2 = new JButton("下月");
        actionButton2.setActionCommand("next");
        actionButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calendarBean.actionPerformed(e);
            }
        });
        // 设置字体
        actionButton1.setFont(defaultFont);
        actionButton2.setFont(defaultFont);

        // 设置中间容器panel1基本信息
        panel1 = new JPanel();
        panel1.add(actionButton1);
        panel1.add(actionButton2);
        this.add(panel1,BorderLayout.NORTH); // 将panel1添加到顶级容器中

        panel2  = new JPanel(); // new一个JPanel对象

        // 设置网格数目，设置几行几列
        GridLayout gridLayout = new GridLayout(7,7);
        gridLayout.setHgap(10); // 横隔10像素
        gridLayout.setVgap(10); // 竖隔10像素

        // 为每一个星期按钮设置其内容
        for (int i = 0; i < weekStr.length; i++) {
            weekButton[i] = new JButton(weekStr[i]); // 为每一个星期按钮new一个对象
            weekButton[i].setFont(new Font("微1软雅黑",Font.BOLD,18));
            panel2.add(weekButton[i]); // 将每一个星期按键都添加到中间层容器panel2中
        }

        //获取日期
        day = calendarBean.getCalendar(); // 通过calendarBean获取当前月份有多少天,返回一个String[]
        dateButton = new JButton[42];
        for(int i = 0; i < 42; i++){
            dateButton[i] = new JButton(); // 为每一个按钮都new一个对象
            panel2.add(dateButton[i]); // 将每一个按钮添加到panel2中
        }
        calendarBean.dateButton = this.dateButton; // 返回给calendarBean的按钮，在calendarBean中计算出每月起始日期

        for (int i = 0; i < day.length; i++){
            dateButton[i].setText(" "+day[i]); // 为每一个日期按钮都设置其月号
            dateButton[i].setBackground(Color.white); // 每一个按钮都为白色
            dateButton[i].setFont(defaultFont); // 设置其字体

        }
        // 为日期按钮添加监听者
        for(int i = 0; i < 42; i++){
            dateButton[i].addActionListener(this);
        }

        panel2.setLayout(gridLayout); //设置panel2布局为网格7*7
        this.add(panel2,BorderLayout.CENTER); // 将panel2添加到顶级容器的中心

        panel3 = new JPanel();
        now.setText(calendarBean.getYear()+"年"+calendarBean.getMonth()+"月"); // 小标签显示当前年，月
        calendarBean.now = now; // 将小标签返回到calendarBean类中,以便于响应"上月"和"下月
        panel3.add(now); // 将小标签添加到panel3中
        this.add(panel3,BorderLayout.SOUTH); // 将panel3添加到顶级容器的底部


    }
    // 当按下按钮时:更新小标签(now)为显示当前日期:年 月 日
    @Override
    public void actionPerformed(ActionEvent e) {
        String label = e.getActionCommand(); // 返回事件的内容对象
        now.setText(calendarBean.getYear()+"年"+calendarBean.getMonth()+"月"+label+"日"); // 显示年 月 日
    }
}


