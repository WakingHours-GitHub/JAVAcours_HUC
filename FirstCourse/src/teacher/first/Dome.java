package teacher.first;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;;

public class Dome extends JFrame implements ActionListener {

    private String[] KEYS = {"7", "8", "9", "*", "4", "5", "6", "-", "1", "2", "3", "+", "0", "e", "π", "÷", "c", "%", ".", "=", "(", ")", "sqr", "x*x"};
    private JButton keys[] = new JButton[KEYS.length];
    private JTextField resultText = new JTextField("0.0");
    private String b = "";

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

    public void actionPerformed(ActionEvent e) {
        String label = e.getActionCommand();
        this.resultText.setText(label);

    }


    public static void main(String[] args) {
        Dome a = new Dome();
    }

}
