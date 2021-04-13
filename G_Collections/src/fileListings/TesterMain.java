package fileListings;

public class TesterMain {

	public static void main(String[] args) {
		
		String dirName = args.length == 0 ? "." : args[0];
		
		//New FileNameStatistics(dirName);
		new FileStatistics(dirName);
	}

}
