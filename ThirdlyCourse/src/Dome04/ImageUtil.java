package Dome04;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageUtil {

	public static boolean cutImage(File sourcePath, int cutNumber, String savePath) {
		try {
			BufferedImage image = ImageIO.read(sourcePath);
			int allWidth = image.getWidth();
			int allHeight = image.getHeight();
			int width = allWidth / cutNumber;
			int height = allHeight / cutNumber;
			for (int i = 0; i < cutNumber; i++) {
				for (int j = 0; j < cutNumber; j++) {
					// 将切割完的图片以特定的名字写入文件夹
					ImageIO.write(image.getSubimage(j * width, i * height, width, height), "jpg",
							new File(savePath + "\\" + (i * cutNumber + j) + ".jpg"));
				}
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

/*	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ImageUtil.cutImage(new File("E:\\HP\\Desktop\\JAVA//test3.jpg"), 4, "E:\\HP\\Desktop\\JAVA\\cut");
	}*/

}
