package Course;

import javax.swing.*;

// 图片按钮类，用来存图片按钮的基本信息

public class PicButton extends JButton {
    private int row; // 行
    private int col; // 列

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
