package net.kevinmendoza.geoworldlibrary.geology.test;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworldlibrary.geology.Geology;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.Order;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.geologydata.GeologyDataContainer;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.geologydatainterface.Surface;
import net.kevinmendoza.geoworldlibrary.geology.test.data.DataFactory;

public class TestMain {

	public static void main(String[] args) {
		BufferedImage img = new BufferedImage(251,251,BufferedImage.TYPE_INT_RGB);
		ObjectParameters p = new Object1Parameters();
		TestFactoryHub.setSeed(101);
		Geology geology = TestFactoryHub.GetMapFactory().createGeologyMap();
		System.out.println("Starting test.");
		long start = System.nanoTime();
		long totalIterations = 0;
		for(int x=0;x<250;x++) {
			for(int z=0;z<250;z++) {
				totalIterations++;
				Vector2i testPoint = new Vector2i(x*90,z*90);
				PrimeData data = new PrimeData(testPoint); 
				geology.primeGeneration(data);
				GeologyDataContainer<Surface> container = geology
						.<Surface>get2DGeologyData(DataFactory.GetEmptySurfaceInstance(), testPoint);
				Surface surf = container.getObject(Order.FIRST);
				int h = surf.getHeight();
				Color color = new Color(h,h,h);
				img.setRGB(x,z,color.getRGB());
			}
		}
		long end = System.nanoTime();
		double totalTime =(end-start)/1000000;
		double timePerIteration = totalTime/totalIterations;
		System.out.println("test Complete. Generation took " + totalTime + "ms and "
		+ timePerIteration + "ms per iteration.");
		File outputfile = new File("CoordinateVariations1.png");
		try {
			ImageIO.write(img, "png", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
