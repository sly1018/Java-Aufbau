package animals.filter;

public class HeavyCarnivoreFilter implements AnimalFilter {
	
	private AnimalFilter herbivorFilter;// = new HerbivorFilter();
	private AnimalFilter weightFilter;// = new WeightFilter(200);
	
	public HeavyCarnivoreFilter(int minWeight) {
		herbivorFilter = new HerbivorFilter();
		weightFilter = new WeightFilter(minWeight);
	}

	@Override
	public boolean isTrueFor(Animal a) {
		// True, wenn kein Herbivore und wenn Gewicht passt
		return !herbivorFilter.isTrueFor(a) && weightFilter.isTrueFor(a);
	}

}
