package net.kevinmendoza.geoworldlibrary.geology.recursivegeology.node;

import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.IDecay;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.IData;
import net.kevinmendoza.geoworldlibrary.geology.compositerockdata.IDataFactory;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.pointmodification.IPointModifier;
import net.kevinmendoza.geoworldlibrary.proceduralgeneration.region.IRegion;

class PrototypeBuilder {

	private IRegion region;
	private String name;
	private IDataFactory dataFactory;
	private IPointModifier pointModifier;
	private IDecay internalDecay;
	private  IDecay externalDecay;
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
	public IDecay getInternalDecay() {
		return internalDecay;
	}
	public PrototypeBuilder setInternalDecay(IDecay internalDecay) {
		this.internalDecay = internalDecay; return this;
	}
	public IDecay getExternalDecay() {
		return externalDecay;
	}
	public PrototypeBuilder setExternalDecay(IDecay externalDecay) {
		this.externalDecay = externalDecay; return this;
	}
	
	

}
