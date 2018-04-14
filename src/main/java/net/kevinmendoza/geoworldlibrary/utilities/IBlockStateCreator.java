package net.kevinmendoza.geoworldlibrary.utilities;
import org.spongepowered.api.block.BlockState;

import net.kevinmendoza.geoworldlibrary.geology.rockdata.IData;
public interface IBlockStateCreator {
	BlockState getBlockState(IData data);

}
