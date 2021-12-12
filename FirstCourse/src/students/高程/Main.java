package students.高程;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;;


public class Main extends JFrame implements ActionListener{

    public static void main(String[] args) {
        new Main();
    }

    private String[] KEYS = {"7", "8", "9", "*", "4", "5", "6", "-", "1", "2", "3", "+", "0", "e", "π", "/", "c", "%", ".", "=", "(", ")", "sqr", "x*x"};
    private JButton keys[] = new JButton[KEYS.length];
    private JTextField resultText = new JTextField("0.0");
    private String expression = "";

    public Main() {
        super("计算器");
        this.setLayout(null);
        resultText.setBounds(20, 5, 255, 40);
        resultText.setHorizontalAlignment(JTextField.RIGHT);
        resultText.setEditable(false);
        this.add(resultText);
        int x = 20, y = 55;
        for (int i = 0; i < KEYS.length; i++) {
            keys[i] = new JButton();
            keys[i].setText(KEYS[i]);
            keys[i].setBounds(x, y, 60, 40);
            if (x < 215) {
                x += 65;
            } else {
                x = 20;
                y += 45;
            }
            this.add(keys[i]);
        }
        for (int i = 0; i < KEYS.length; i++) {
            keys[i].addActionListener(this);
        }
        this.setResizable(false);
        this.setBounds(500, 200, 300, 400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String label = e.getActionCommand();
        if(Objects.equals(label, "c")){
            expression = "";
        }
        else if(Objects.equals(label, "=")){
            double result = getResult(expression);
            this.resultText.setText(Double.toString(result));
            return ;
        }else{
            expression += label;
        }
        this.resultText.setText(expression);

    }



    private static boolean isNumber(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i)) && str.charAt(i) != '.' && str.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }

    public static Double getResult(String str) {

        // 递归头
        if (str.isEmpty() || isNumber(str)) {
            return str.isEmpty() ? 0 : Double.parseDouble(str);
        }

        //递归体
        if (str.contains(")")) {
            // 最后一个左括号
            int lIndex = str.lastIndexOf("(");
            // 对于的右括号
            int rIndex = str.indexOf(")", lIndex);
            return getResult(str.substring(0, lIndex) + getResult(str.substring(lIndex + 1, rIndex)) + str.substring(rIndex + 1));
        }
        if (str.contains("+")) {
            int index = str.lastIndexOf("+");
            return getResult(str.substring(0, index)) + getResult(str.substring(index + 1));
        }
        if (str.contains("-")) {
            int index = str.lastIndexOf("-");
            return getResult(str.substring(0, index)) - getResult(str.substring(index + 1));
        }
        if (str.contains("*")) {
            int index = str.lastIndexOf("*");
            return getResult(str.substring(0, index)) * getResult(str.substring(index + 1));
        }
        if (str.contains("/")) {
            int index = str.lastIndexOf("/");
            return getResult(str.substring(0, index)) / getResult(str.substring(index + 1));
        }

        // 出错
        return null;
    }
}
