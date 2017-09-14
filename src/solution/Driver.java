package solution;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.List;

import problem.ProblemSpec;

public class Driver {

	
	public static void main(String[] args) throws IOException{
		
		ProblemSpec problemSetup = new ProblemSpec();
		
		String filename = "C:/Users/gohzenhao/Documents/3ASV-easy.txt";
		
		problemSetup.loadProblem(filename);
		
		System.out.println(problemSetup.getASVCount());
		
		List<Point2D> asvConfig = problemSetup.getInitialState().getASVPositions();
		
		for(int i=0;i<asvConfig.size();i++){
			System.out.println(asvConfig.get(i));
		}
		
		problemSetup.getInitialState().convertToAngle();
		
		
		
		
		
	}
	
}
