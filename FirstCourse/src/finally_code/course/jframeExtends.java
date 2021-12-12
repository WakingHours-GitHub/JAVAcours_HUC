package finally_code.course;

import Dome02.Analysis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class jframeExtends extends JFrame implements ActionListener {

    private final String[] KEYS = {
            "7", "8", "9", "*",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", "e", "π", "÷",
            "c", "%", ".", "=",
            "(", ")", "sqr", "x*x"
    };
    private final JButton[] JBkeys = new JButton[KEYS.length];
    private JTextField resultText = new JTextField("0.0");
    private String expression = ""; // 显示出来的解析式（String)
    private String  analytic = "";  // 实际上的计算式（String）

    // jframeExtends构造方法
    public jframeExtends() {

        super("计算器");// 调用JFame的构造方法：设置标题

        // 设置文本框相关信息
        this.setLayout(null);   // 布局管理器Layout不设置
        resultText.setFont(new Font("微软雅黑", Font.BOLD,24)); // 设置字体
        resultText.setBounds(20, 23, 490, 60); // 输入文本框的位置以及大小
        resultText.setHorizontalAlignment(JTextField.RIGHT); // 设置该文本框右对齐
        resultText.setEditable(false); // 初始状态：不可编辑
        this.add(resultText); // 添加到顶级容器中

        int x = 34, y = 100;     //  第一个按钮开头的起始位置
        for (int i = 0; i < KEYS.length; i++) {
            JBkeys[i] = new JButton(); // 为每一个按钮new一个JButton
            JBkeys[i].setText(KEYS[i]); // 为每一个按钮设置其中文本
            JBkeys[i].setBounds(x, y, 100, 60); // 每个按钮的大小
            JBkeys[i].setFont(new Font("微软雅黑",Font.BOLD,16)); // 每个按钮容器中的字体
            // 开始将按钮添加到顶级容器
            if (x < 300) {
                x += 120;
            } else {
                x = 34;
                y += 80;
            }
            this.add(JBkeys[i]); // 将每一个按钮添加到顶级容器中
        }

        // 为每一个按钮添加监听者
        for (int i = 0; i < KEYS.length; i++) {
            JBkeys[i].addActionListener(this);
        }

        // 设置顶级容器相关信息
        this.setResizable(false); // 设置此窗体是否可由用户调整大小
        this.setBounds(500, 200, 540, 640); // 顶级容器的起始位置以及大小
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); // 设置顶级容器可以关闭
        this.setVisible(true); // 设置顶级容器可见
    }




    public void actionPerformed(ActionEvent e) {
        String label = e.getActionCommand(); // 监听器返回的对象内容
        if (Objects.equals(expression, "0.0")) expression = ""; // 重置
        // 事响应动的情况分析
        switch (label) {
            case "c" -> expression = "0.0";
            // 当按下"="时计算出计算式，并且清空表达式，显示计算式所计算的结果
            case "=" -> {
                analytic = expression; // 赋值给计算式
                expression = " "; // 清空解析式

                if(analytic.contains("e")){
                    analytic = analytic.replace("e","2.718281828459045235360287471353");
                }
                if(analytic.contains("π")){
                    analytic = analytic.replace("π","3.1415926535898");
                }
                if(analytic.contains("sqr")){
                    String str = Double.toString(finally_code.course.Analysis.isSqr(analytic));
                    analytic = analytic.replace("sqr", str);
                }

                double result = Analysis.getResult(analytic); // 调用Analysis类的getRusult()方法计算 计算式，返回计算式结果
                this.resultText.setText(Double.toString(result)); // 将结果输出在文本框显示
                return;
            }
            default -> {
                if (Objects.equals(expression, "0.0")) expression = ""; // 重置
                expression += label; // 当不是"="时暂存解析式
            }
        }

        // 输出给文本框显示解析式
        this.resultText.setText(expression);
    }
}
