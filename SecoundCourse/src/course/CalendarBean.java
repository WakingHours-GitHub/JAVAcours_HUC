package course;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class CalendarBean implements ActionListener {

    private int year = 0, month = 0; //当前的年份和月份
    JLabel now; // 下面的小标题
    JButton[] dateButton; // 日期的按钮
    String[] day; // 日期文本

    // year和month的setting和getting方法
    public void setYear(int year) {
        this.year = year;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public int getYear() {
        return year;
    }
    public int getMonth() {
        return month;
    }

    // 返回带有月份数字的数组: 每月从第几个星期开始显示月号,到多少月号结束
    public String[] getCalendar() {
        String[] resultDate = new String[42]; // 创建String数组对象，为以后填入Button准备
        Calendar rill = Calendar.getInstance();// 初始化一个日历对象
        rill.set(year, month - 1, 1); // 设置当前的日期
        int weekday = rill.get(Calendar.DAY_OF_WEEK) - 1; // 获取当前日期是星期几
        int day = 0;
        // 判断每月是多少天
        switch(month){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12: day = 31;break;

            case 4:
            case 6:
            case 9:
            case 11: day = 30; break;
            default:
                // 判断闰年
                if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
                         day = 29;
                     } else
                         day = 28; break;
        }


        // 计算从每一月起始的日期
        for (int i = 0; i < weekday; i++)
            resultDate[i] = "";
        for (int i = weekday, n = 1; i < weekday + day; i++) {
            resultDate[i] = String.valueOf(n);
            n++;
        }
        for (int i = weekday + day; i < resultDate.length; i++)
            resultDate[i] = "";

        return resultDate;

    }
    // 当按下"下月"和"上月"时的事件响应
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand(); // 获取事件内容对象
        // 如果是"上月"对应的"last",则月份减1,并且当月份减到0时,年份减1,并且月份置于12
        if (str.equals("last")) {
            month--;
            if (month == 0) {
                year--;
                month = 12;
            }
        // 如果是"下月"对应的"next",则月份加1,并且当月份加到12时,年份加1,并且月份置于1
        } else if (str.equals("next")) {
            month++;
            if (month == 13) {
                year++;
                month = 1;
            }
        }
        // 显示小标题
        now.setText(year + "年" + month + "月");
        day = getCalendar(); // 获取当前年月对应的月号
        // 利用循环显示到日期按钮中
        for (int i = 0; i < day.length; i++) {
            dateButton[i].setText(" " + day[i]);
        }
    }

}
