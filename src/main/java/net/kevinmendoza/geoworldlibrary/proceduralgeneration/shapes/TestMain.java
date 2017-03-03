package net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.flowpowered.math.vector.Vector2i;


public class TestMain {

	/*public static void main(String[] args) {
		Random rand = new Random();
		BufferedImage img = new BufferedImage(501,501,BufferedImage.TYPE_INT_RGB);
		Region region = RegionFactory.MakeRegionType(RegionTypes.RECTANGLE,new Vector2i(200,200),100,100,Math.PI/4);
		for(int x=0;x<500;x++) {
			for(int z=0;z<500;z++) {
				if(x%100==0) {
					int i = 0;
				}
				if(region.isInside(new Vector2i(x,z)))
					img.setRGB(x,z,100);
			}
			System.out.println("loop: x" + x);
		}
		File outputfile = new File("RectangleTest.png");
		try {
			ImageIO.write(img, "png", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/

}
