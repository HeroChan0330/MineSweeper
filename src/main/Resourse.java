package main;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Resourse {
	public static Image mineImg;
	public static void load() {
		try {
			mineImg=ImageIO.read(new File("img/Mine.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void resizeMineImg(int w,int h) {
		try {
			mineImg=zoomImage("img/Mine.png",w,h);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Image zoomImage(String src,int w,int h) throws IOException {
		BufferedImage bufferedImage=ImageIO.read(new File(src));
		Image res=bufferedImage.getScaledInstance(w, h, bufferedImage.SCALE_SMOOTH);
		double wr,hr;
		wr=w*1.0/bufferedImage.getWidth();
		hr=h*1.0/bufferedImage.getHeight();
		AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);
		res=ato.filter(bufferedImage, null);
		return res;
	}
}

