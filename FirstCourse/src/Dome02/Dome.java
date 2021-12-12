package Dome02;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Objects;


public class Dome extends JFrame implements ActionListener {

    private String[] KEYS = {"7", "8", "9", "*", "4", "5", "6", "-", "1", "2", "3", "+", "0", "e", "π", "÷", "c", "%", ".", "=", "(", ")", "sqr", "x*x"};
    private JButton keys[] = new JButton[KEYS.length];
    private JTextField resultText = new JTextField("0.0");
    private String expression = "";

    public Dome() {

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

    /*    public void actionPerformed(ActionEvent e) {
            String label = e.getActionCommand();
            if(Objects.equals(label, "c")){
                expression = "0.0";

            }else if(Objects.equals(label, "=")){
                double result = Analysis.getResult(expression);
                this.resultText.setText(Double.toString(result));
                return ;
            }else{
                if(Objects.equals(expression, "0.0")) expression = "";
                expression += label;
            }
            this.resultText.setText(expression);
        }*/
    public void actionPerformed(ActionEvent e) {
        HashMap<String, Double> map = new HashMap<>();
        map.put("e", 2.718281828459045235360287471353);
        map.put("π", 3.1415926535898);
        String label = e.getActionCommand();
        switch (label) {
            case "c" -> expression = "0.0";
            case "e" -> expression = Double.toString(map.get("e"));
            case "π" -> expression = Double.toString(map.get("π"));
            case "=" -> {
                double result = Analysis.getResult(expression);
                this.resultText.setText(Double.toString(result));
                return;
            }
            default -> {
                if (Objects.equals(expression, "0.0")) expression = "";
                expression += label;
            }
        }
        this.resultText.setText(expression);
    }


}
