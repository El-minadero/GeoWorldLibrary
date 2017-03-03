package net.kevinmendoza.geoworldlibrary.geology.rockparameters;

import java.util.ArrayList;
import java.util.List;

import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.world.extent.ImmutableBiomeVolume;
import org.spongepowered.api.world.extent.ImmutableBlockVolume;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3i;

import net.kevinmendoza.geoworldlibrary.geology.Geology;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GeologyInterface.Alteration;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GeologyInterface.Replacement;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GeologyInterface.Surface;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.other.GeologyData;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GeologyInterface.RockCache;
import net.kevinmendoza.geoworldlibrary.utilities.GeoWorldPlugin;

public class GeologyContainer {

	public static GeologySythesis SynthesizeGeology(List<GeoWorldPlugin> modules) {
		GeologyBuilder builder = new GeologyBuilder();
		for(GeoWorldPlugin module : modules) {
			builder.addGeology(module.getGeologicMap());
		}
		return builder.build();
	}
	
	public static interface GeologySythesis {
		public void primeGeneration(GenerationData data);
		public RockCache getRockCache(Vector2i coordinates);
	
	}
	
	private final static class GeologySynthesisContainer implements GeologySythesis {
		
		private final int MAX_HEIGHT;
		private final int MIN_HEIGHT;
		
		private final List<BlockState> air;
		private final List<Geology> maps;
		
		private GeologySynthesisContainer(GeologyBuilder builder) {
			air 		= builder.getAirBlockStates();
			MAX_HEIGHT  = builder.getMaxHeight();
			MIN_HEIGHT  = builder.getMinHeight();
			maps 		= builder.getGeology();
		}
		
		@Override
		public void primeGeneration(GenerationData data) {
			for(Geology map : maps) {
				map.primeGeneration(data);
			}
		}
		
		@Override
		public RockCache getRockCache(Vector2i coordinates) {
			GeologyData<Surface> surf = null;
			RockCache cache = RockModifiers.GetRockCache();
			for(Geology map : maps) {
				if(surf==null)
					surf = map.getSurface(coordinates);
				else
					surf.merge(map.getSurface(coordinates));
			}
			Surface s = surf.getObject(Order.FIRST);
			Vector3i query = new Vector3i(coordinates.getX(),0,coordinates.getY());
		/*	for(int i = maxHeight; i > 0; i--) {
				Replacement replace = RockModifiers.GetEmptyReplacement();
				Alteration alterate = RockModifiers.GetEmptyAlteration();
				query = new Vector3i(coordinates.getX(),i,coordinates.getY());
				for(Geology map : maps) {
					replace.merge(map.getReplacement(query));
					alterate.merge(map.getAlteration(query));
				}
				cache.placeEntry(query,replace,alterate);
			}*/
			return cache;
		}
		/*
		private int getBaseHeight(GenerationData data) {
			for(int i =MAX_HEIGHT; i >= MIN_HEIGHT;i--) {
				if(!isAirBlock(volume.getBlock(new Vector3i(coordinates.getX(),i,coordinates.getY() )))){
					return i;
				}
			}
			return 0;
		}
		
		private boolean isAirBlock(BlockState block) {
			boolean b = false;
			for(BlockState state : air) {
				if(block.getType().getDefaultState().equals(state))
				{b=true; break;}
			}
			return b;
		}
		*/
		
	}
	
	private final static class GeologyBuilder {

		private List<Geology> map;
		private List<BlockState> airStates;
		private int MIN_HEIGHT = 1;
		private int MAX_HEIGHT = 256;
		
		private GeologyBuilder() {
			map = new ArrayList<>();
			airStates = new ArrayList<>();
			airStates.add(BlockState.builder().blockType(BlockTypes.AIR).build());
			airStates.add(BlockState.builder().blockType(BlockTypes.WATER).build());
			airStates.add(BlockState.builder().blockType(BlockTypes.FLOWING_WATER).build());
			airStates.add(BlockState.builder().blockType(BlockTypes.FLOWING_LAVA).build());
		}
		
		private GeologyBuilder addGeology(Geology map) { this.map.add(map); return this; }
		private List<Geology> getGeology() { return map; }
		private int getMinHeight() { return MIN_HEIGHT; }
		private int getMaxHeight() { return MAX_HEIGHT; }
		private List<BlockState> getAirBlockStates() { return airStates; }
		GeologySythesis build() {
			return new GeologySynthesisContainer(this);
		}
	}
}
