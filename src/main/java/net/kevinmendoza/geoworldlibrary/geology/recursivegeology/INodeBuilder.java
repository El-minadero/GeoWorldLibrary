package net.kevinmendoza.geoworldlibrary.geology.recursivegeology;

public interface INodeBuilder {

	public NodeBuilder setPrototype(AbstractPrototype prototype);
	public NodeBuilder setSubObjectNumber(int number);
	public NodeBuilder setFactory(AbstractPrototypeFactory factory);
	public Node build();

}
