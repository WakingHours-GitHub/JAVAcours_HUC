package Dome04;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainPanel extends JPanel {
    Button[] button = new Button[25];// 按钮数组
    ImageIcon[] icon = new ImageIcon[25];// 图片数组
    int[] state = new int[25];// 图片存放顺序
    int nullButton;// 空白按钮位置顺序
    int pattern;// 图片底数
    int total;// 图片总数
    int count;//总步数

    public MainPanel(String path, int pattern) {
        // TODO Auto-generated constructor stubA
        this.pattern = pattern;
        total = pattern * pattern;
        breakRandom(path, pattern);
    }

    public void breakRandom(String path, int pattern) {
        count = 0;
        this.pattern = pattern;
        total = pattern * pattern;
        ImageUtil.cutImage(new File(path + "\\test.jpg"), pattern, path + pattern);
        this.removeAll();
        this.updateUI();
        this.setLayout(new GridLayout(pattern, pattern));
        nullButton = total - 1;
        random(state);
        for (int i = 0; i < total; i++) {
            button[i] = new Button();
            button[i].setRow(i / pattern);
            button[i].setCol(i % pattern);
            this.add(button[i]);
        }

        for (int i = 0; i < total - 1; i++) {
            icon[i] = new ImageIcon(path + pattern + "\\" + state[i] + ".jpg");
            button[i].setIcon(icon[i]);
        }

        for (int i = 0; i < total; i++) {
            button[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    Button button = (Button) e.getSource();
                    remove(button);
                    count++;
                }
            });

        }
    }

    public void random(int a[]) {
        Random cd = new Random();
        int i = 0;
        a[0] = cd.nextInt(total - 1);
        for (i = 0; i < total - 1; i++) {
            int temp = cd.nextInt(total - 1);
            for (int j = 0; j < i; j++) {
                if (a[j] != temp) {
                    a[i] = temp;
                } else {
                    i--;
                    break;
                }
            }
        }
        a[i] = total - 1;
        System.out.println("图片的初始顺序为");
        for (i = 0; i < total; i++) {
            System.out.print(a[i] + " ");
        }
    }

    public void remove(Button clicked) {
        int rowN = button[nullButton].getRow();
        int colN = button[nullButton].getCol();
        int rowC = clicked.getRow();
        int colC = clicked.getCol();
        if (((rowN - rowC) == 1 && (colN - colC) == 0) || ((rowN - rowC) == -1 && (colN - colC) == 0)
                || ((rowN - rowC) == 0 && (colN - colC) == 1) || ((rowN - rowC) == 0 && (colN - colC) == -1)) {
            ImageIcon icon = (ImageIcon) clicked.getIcon();
            button[nullButton].setIcon(icon);
            clicked.setIcon(null);
            int clickState = rowC * pattern + colC;
            nullButton = rowN * pattern + colN;
            state[nullButton] = state[clickState];
            state[clickState] = total - 1;
            nullButton = clickState;
            check();
        }else {
            return;
        }
    }

    public void check() {
        for (int i = 0; i < total; i++) {
            if(state[i]!=i) {//[2,3,1,5,6,0,8,7,4] [0,1,2,3,4,5,6,7,8]
                return;
            }
            JOptionPane.showMessageDialog(this, "拼图完成");
        }
    }

    public int getCount() {
        return count;
    }
}
