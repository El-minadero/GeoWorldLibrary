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
		Vector2i vec = new Vector2i(rand.nextInt(300) + 150,rand.nextInt(300) + 150);
		int d = rand.nextInt(100) + 50;
		double[] dims = {d,d};
		Region region = AbstractRegionFactory
				.getRegionFactoryType(RegionTypes.ELLIPSE)
				.getRegion(vec, dims, true);
		System.out.println(region.toString());
		for(int x=0;x<500;x++) {
			for(int z=0;z<500;z++) {
				if(region.isInside(new Vector3i(x,0,z)))
					img.setRGB(x,z,100);
			}
		}
		for(int i =0; i<5; i++) {
			Vector2i v2 = new Vector2i(rand.nextInt(200) + 150,rand.nextInt(200) + 150);
			dims[0] = rand.nextInt(100) + 50;
			dims[1] = rand.nextInt(100) + 50;
			region = AbstractRegionFactory
					.getRegionFactoryType(RegionTypes.ELLIPSE)
					.getRegion(v2, dims, true);
			int rgb=0;
			System.out.println(region.toString());
			for(int x=0;x<500;x++) {
				for(int z=0;z<500;z++) {
					if(region.isInside(new Vector3i(x,0,z))) {
						rgb = img.getRGB(x, z) + 200;
						img.setRGB(x,z,rgb);
						rgb=0;
					}
				}
			}
			for(int j=0;j<15;j++) {
				Vector2i vec5 = region.getRandomInternalPoint2i();
				rgb = img.getRGB(vec5.getX(), vec5.getY()) + 6535;
				img.setRGB(vec5.getX(), vec5.getY(),rgb);
				rgb=0;
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
