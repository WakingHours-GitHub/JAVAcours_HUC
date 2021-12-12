package Dome03;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main extends JFrame {
    public static void main(String[] args) {
        new Main();
    }

    public Main(){
        try {
            JPanel jp = new JPanel();
            BufferedImage buf = ImageIO.read(new File("E:\\HP\\Desktop\\JAVA\\test2.jpg"));
            JLabel jl = new JLabel(new ImageIcon(buf));
            jp.add(jl);
            this.add(jp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setVisible(true);
        this.setBounds(100,200,600,600);

    }

}
