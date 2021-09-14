package Course;

// 游戏的核心设计


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Random;

public class CorePanel extends JPanel {
    PicButton[] picButton = new PicButton[25]; // 按钮数组
    ImageIcon[] picIcon = new ImageIcon[25]; // 图片数组
    int[] state = new int[25]; // 图片存放顺序

    int nullButton ; // 空白按钮位置顺序
    int pattern; // 分成几乘几个网格
    int total; // 总网格数

    public CorePanel(String socPath, int pattern) {
        this.pattern = pattern;
        total = pattern * pattern;
        setBreakRandom(socPath, pattern); // 设置面板
    }

    public void setBreakRandom(String path, int pattern){
        this.pattern = pattern; // 设置图片底数
        total = pattern * pattern; // 设置总的图片数量
        PicSpite.cutImage(new File(path + "\\test.jpg"), pattern,path + pattern );
        // 更新界面
        this.removeAll(); // 移除全部组件
        this.updateUI();// 更新UI

        this.setLayout(new GridLayout(pattern, pattern)); // 设置为Pattern * Pattern网格
        nullButton = total - 1; // 为空白按钮分配一个号

        myRandom(state); // 将state乱序

        for (int i = 0; i < total; i++){
            picButton[i] = new PicButton(); // new一个按钮对象（绑定）
            picButton[i].setRow(i / pattern); // 设置行号
            picButton[i].setCol(i % pattern); // 设置列号
            this.add(picButton[i]); // 将每一个按钮添加到中间容器中
        }

        for(int i = 0; i < total - 1; i++){
            // 打乱顺序
            picIcon[i] = new ImageIcon(path + pattern + "\\" + state[i] + ".jpg");
            picButton[i].setIcon(picIcon[i]);
        }

        for (int i = 0; i < total; i++){
            // 为每一个按钮添加鼠标监听
            picButton[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // 将鼠标点击的对象移动
                    PicButton clickButton = (PicButton)e.getSource();
                    move(clickButton);
                }
            });
        }
    }


    /**
     * 图片的乱序数组
     *
     * @param randomArray
     * @author WakingHours
     */
    public void myRandom(int[] randomArray) {
        Random r = new Random();
        int i = 0;
        randomArray[0] = r.nextInt(total -1);
        for (i = 0; i < total - 1; i++){
            int temp = r.nextInt(total - 1);
            for (int j = 0; j < i; j++){
                if(randomArray[j] != temp){
                    randomArray[i] = temp;
                }else{
                    i--;
                    break;
                }
            }
        }
        // 空白格
        randomArray[i] = total - 1;

        System.out.println("图片的初始顺序为");
        for (i = 0; i < total; i++) {
            System.out.print(randomArray[i] + " ");
        }
    }

    /**
     * 按钮的移动
     *
     * @param clicked
     * @author WakingHours
     */

    public void move(PicButton clicked) {
        // 获取空白格的行号和列号
        int rowN = picButton[nullButton].getRow();
        int colN = picButton[nullButton].getCol();
        // 获取当前鼠标点击对象的行号和列号
        int rowC = clicked.getRow();
        int colC = clicked.getCol();
        // 进行比较：
        // 原则是：先判断是否为同一行或者同一列，然后判断列或者行是否相差1
        if (((rowN - rowC) == 1 && (colN - colC) == 0) || ((rowN - rowC) == -1 && (colN - colC) == 0)
                || ((rowN - rowC) == 0 && (colN - colC) == 1) || ((rowN - rowC) == 0 && (colN - colC) == -1)) {
            // 如果是空白格临近的，则交换
            ImageIcon icon = (ImageIcon) clicked.getIcon(); // 获取点击对象的图标
            picButton[nullButton].setIcon(icon); // 将图标设给原来空白格对象
            clicked.setIcon(null);  // 设置点击的按钮对象为空
            int clickState = rowC * pattern + colC; // 重新设置点击图片的ID
            nullButton = rowN * pattern + colN; // 重新设置现在所点击的按钮变为空白格的ID
            state[nullButton] = state[clickState]; // 序号的赋值
            state[clickState] = total - 1; // 点击的按钮ID变为空白格ID
            nullButton = clickState;
            // 每移动一下（即鼠标点击一下）就判断一下赢没赢
            if(check()){
                // 参数列表分别是（显示在哪个面板上，显示内容
                JOptionPane.showMessageDialog(this, "拼图完成");
            }
        }else {
            return;
        }
    }


    // 判断玩家是否赢了
    // 判断玩完后的顺序和一开始是否一样
    public boolean check() {
        for (int i = 0; i <total ; i++) {
            if(state[i] != i) {
                return false;
            }
        }
        return true;
    }

}