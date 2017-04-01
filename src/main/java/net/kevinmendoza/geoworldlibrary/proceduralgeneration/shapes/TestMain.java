package net.kevinmendoza.geoworldlibrary.proceduralgeneration.shapes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;


public class TestMain {

	public static void main(String[] args) {
		Random rand = new Random();
		BufferedImage img = new BufferedImage(501,501,BufferedImage.TYPE_INT_RGB);
		Vector2i vec = new Vector2i(250,250);
		Region region = RegionFactory.MakeRegionType(RegionTypes.ELLIPSE, vec, 50.0, 70.0, 2.5);
		for(int x=0;x<500;x++) {
			for(int z=0;z<500;z++) {
				if(region.isInside(new Vector3i(x,0,z)))
					img.setRGB(x,z,100);
			}
		}
		File outputfile = new File("RectangleTest.png");
		try {
			ImageIO.write(img, "png", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
