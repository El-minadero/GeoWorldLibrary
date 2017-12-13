package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache;

import java.util.ArrayList;
import java.util.LinkedList;
import com.flowpowered.math.vector.Vector3i;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.factory.INodeFactory;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.node.INode;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.prototype.IPrototype;
import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.cache.MapCache.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.flowpowered.math.vector.Vector2i;

import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointgeneration.IPointGenerator;
public class MapCacheTest {

	private INodeCache cache;
	@Before
	public void initialize() {
		
	}
	@Test
	public void type() throws Exception {
		assertThat(MapCache.class, notNullValue());
	}

	@Test
	public void instantiation() throws Exception {
		
	}

	@Test
	public void getLocalKeys_A$Vector2i() throws Exception {
	
	}

	@Test
	public void createPrototypeFromKey_A$Vector2i() throws Exception {
		
		
	}

	@Test
	public void makeGeologyNode_A$IGeologyNode() throws Exception {
		
	}

	@Test
	public void getLocalKeys_A$Vector3i() throws Exception {
		
	}

	@Test
	public void populateKeyValue_A$Vector2i() throws Exception {
		
	}

	@Test
	public void getLocationString_A$Vector3i() throws Exception {
		
	}

	@Test
	public void getClosestNodesToLocation_A$Vector3i() throws Exception {
		
	}

	@Test
	public void populateIfNecessary_A$() throws Exception {
		
	}

	@Test
	public void setSeed_A$long() throws Exception {
		
	}

}
