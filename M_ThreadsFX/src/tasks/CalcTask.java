package tasks;


import java.util.concurrent.ThreadLocalRandom;

public class CalcTask {

	public double runCalculation(int count) {
		double sum = 0;
		for (int i = 1; i <= count; i++) {
			double value = calcValue();
			sum += value;
		}

		return sum;
	}
	
	
	private double calcValue() {
		double result = 0;
		//System.out.println("calculating next value ...");
		ThreadLocalRandom random = ThreadLocalRandom.current();
		for (int i = 0; i < 1000000; i++) {
			if (i % 2 == 0)
				result += random.nextDouble(50);
			else
				result -= random.nextDouble(50);
		}
		return result;
	}

}
