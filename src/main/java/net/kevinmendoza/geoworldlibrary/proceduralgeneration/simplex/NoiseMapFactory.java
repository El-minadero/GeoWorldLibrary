package net.kevinmendoza.geoworldlibrary.proceduralgeneration.simplex;

public class NoiseMapFactory {

	public static NoiseMap MakePowerOctaveMap(double baseOctave, int octaveNumber,int octavePower,long seed){
		double[] frequencies = new double[octaveNumber];
		double[] weights 	 = new double[octaveNumber];
		for(int i = 0; i<octaveNumber;i++) {
			frequencies[i]  = baseOctave/(i+1);
			weights[i]		= Math.pow(octaveNumber-i, octavePower);
		}
		return MakeSimplexNoiseMap(frequencies,weights,seed);
	}
	public static NoiseMap MakeDomainMap(NoiseMap map1, NoiseMap map2,double cutoff){
		return new DomainMap(map1,map2,cutoff);
	}
	
	public static NoiseMap MakeSmoothDomainMap(NoiseMap map1, NoiseMap map2,double cutoff){
		return new DomainMap(map1,map2,cutoff);
	}
	
	public static NoiseMap MakeSimplexNoiseMap(double[] frequencies,double[] weight,long seed) {
		return new MultiFrequencySimplexNoise(frequencies,weight,seed);
	}
	
	public static NoiseMap MakeSimpleDomainMap(NoiseMap map1,double cutoff) {
		return new SimpleDomainMap(map1,cutoff);
	}
	
	public static NoiseMap MakeMultiplierNoiseMap(NoiseMap map, double multiplier) {
		return new ImplementedMap(map,multiplier);
	}
	
	public static NoiseMap MakeValueOffsetNoiseMap(NoiseMap map, double offset) {
		return new ValueOffsetMap(map,offset);
	}
	
	public static NoiseMap MakeSingleValueMap(double value) {
		return new SingleValueMap(value);
	}
	
	static NoiseMap SimpleSimplexMap(double frequencies,double weight,long seed) {
		return new SimpleSimplexNoiseMap(frequencies,weight,seed);
	}
	
}
