package net.kevinmendoza.geoworldlibrary.proceduralgeneration.region;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.boundingbox.BoundingBoxTest;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.conics.BoxConicTest;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.conics.ConicTest;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.conics.EllipseTest;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.conics.EllipsoidTest;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.conics.RectangleConicTest;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.relativelocation.RelativeSpaceTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({ BoundingBoxTest.class, ConicTest.class, RelativeSpaceTest.class })
public class RegionTest {

}
