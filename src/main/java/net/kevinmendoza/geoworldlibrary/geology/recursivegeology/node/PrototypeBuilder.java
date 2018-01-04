package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.node;

import net.kevinmendoza.geoworldlibrary.geology.rockdata.DecayFactory;
import net.kevinmendoza.geoworldlibrary.geology.rockdata.IDataFactory;
import net.kevinmendoza.geoworldlibrary.geology.rockdata.IDecay;
import net.kevinmendoza.geoworldlibrary.geology.rockdata.NullDataFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.IPointModifier;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.PointModifierFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.IRegion;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.shape.ShapeFactory;

public class PrototypeBuilder {

	private IRegion region;
	private String name;
	private IDataFactory dataFactory;
	private IPointModifier pointModifier;
	private IDecay decay;
	
	public PrototypeBuilder() {
		region 	= ShapeFactory.makeEllipse(10, 10);
		name	 	= "default";
		dataFactory = new NullDataFactory();
		decay		= DecayFactory.getDefaultDecay();
		pointModifier = PointModifierFactory.CreateNullPointOffset();
		
	}
	public IRegion getRegion() {
		return region;
	}
	public PrototypeBuilder setRegion(IRegion region) {
		this.region = region; return this;
	}
	public String getName() {
		return name;
	}
	public PrototypeBuilder setName(String name) {
		this.name = name; return this;
	}
	public IPointModifier getPointModifier() {
		return pointModifier; 
	}
	public PrototypeBuilder setPointModifier(IPointModifier pointModifier) {
		this.pointModifier = pointModifier; return this;
	}
	public IDataFactory getDataFactory() {
		return dataFactory;
	}
	public PrototypeBuilder setDataFactory(IDataFactory dataFactory) {
		this.dataFactory = dataFactory; return this;
	}
	public IDecay getDecay() {
		return decay;
	}
	public PrototypeBuilder setDecay(IDecay internalDecay) {
		this.decay = internalDecay; return this;
	}
	
	public INode build() {
		return new Prototype(this);
	}
	
}
