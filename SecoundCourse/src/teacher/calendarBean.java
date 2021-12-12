package teacher;


import javax.swing.*;
import java.awt.event.*;
import java.util.Calendar;

public class calendarBean implements ActionListener {
    int year = 0, month = 0;
    JLabel now;
    JLabel[] label;
    String[] day;

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String[] getCalendar() {
        String[] a = new String[42];
        Calendar rill = Calendar.getInstance();// 初始化一个日历对象
        rill.set(year, month - 1, 1);
        int weekday = rill.get(Calendar.DAY_OF_WEEK) - 1;
        int day = 0;
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
                || month == 10 || month == 12)
            day = 31;
        if (month == 4 || month == 6 || month == 9 || month == 11)
            day = 30;
        if (month == 2) {
            if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
                day = 29;
            } else
                day = 28;
        }
        for (int i = 0; i < weekday; i++)
            a[i] = "";
        for (int i = weekday, n = 1; i < weekday + day; i++) {
            a[i] = String.valueOf(n);
            n++;
        }
        for (int i = weekday + day; i < a.length; i++)
            a[i] = "";
        return a;

    }

    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        if (str.equals("last")) {
            month--;
            if (month == 0) {
                year--;
                month = 12;
            }
        } else if (str.equals("next")) {
            month++;
            if (month == 13) {
                year++;
                month = 1;
            }
        }
        now.setText(year + "年" + month + "月");
        day = getCalendar();
        for (int i = 0; i < day.length; i++) {
            label[i].setText("        " + day[i]);
        }
    }
}
