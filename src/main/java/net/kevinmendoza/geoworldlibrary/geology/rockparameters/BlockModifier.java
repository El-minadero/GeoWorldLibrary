package net.kevinmendoza.geoworldlibrary.geology.rockparameters;

import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockTypes;

import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GeologyInterface.Alteration;
import net.kevinmendoza.geoworldlibrary.geology.rockparameters.GeologyInterface.Replacement;

public class BlockModifier {

	public static interface BlockStateModifier {
		public BlockState getBlockState();
	}
	
	public static BlockStateModifier GetNullBlockModifier(Alteration alteration, Replacement replacement) {
		return new NullBlockModifier(alteration,replacement);
	}
	
	private static class NullBlockModifier implements BlockStateModifier {
		
		private final Alteration alteration;
		private final Replacement replacement;

		public NullBlockModifier(Alteration alteration, Replacement replacement) {
			this.alteration = alteration;
			this.replacement = replacement;
		}

		@Override
		public BlockState getBlockState() {
			return BlockTypes.STONE.getDefaultState();
		}
	}
	
}
