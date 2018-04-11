package net.kevinmendoza.geoworldlibrary.utilities;

import org.spongepowered.api.block.BlockState;

import net.kevinmendoza.geoworldlibrary.geology.recursivegeology.IGeology;
import net.kevinmendoza.geoworldlibrary.geology.rockdata.IData;

public interface IGeoWorldRockTransformer {

	public IBlockStateCreator getBlockCreator(long seed,boolean newInstance);
	
	public String GetPluginID();
}
