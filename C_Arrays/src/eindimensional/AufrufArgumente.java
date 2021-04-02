package eindimensional;

public class AufrufArgumente {
	// args enthält Aufrufargumente an unser Programm
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Keine Aufrufargumente vorhanden.");
		} else {
			System.out.println("Aufrufargumente: ");
			for (int i = 0; i < args.length; i++) {
				String arg = args[i];
				System.out.printf("%d. Argument: %s\n", i +1, arg);
			}
		}
	}

}
