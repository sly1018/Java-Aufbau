package animals.lambdaUndAnonym;

public class HerbivorFilter implements AnimalFilter {

	@Override
	public boolean isTrueFor(Animal a) {
		return a.isHerbivore();
	}

}
