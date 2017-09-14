package solution;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.List;

import problem.ProblemSpec;
import problem.ProblemSpecAngle;
public class Driver {

	
	public static void main(String[] args) throws IOException{
		
		ProblemSpec problemSetup = new ProblemSpec();
		ProblemSpecAngle problemSetupAngle = new ProblemSpecAngle();
		
		String filename = "C:\\Users\\User-PC\\eclipse-workspace\\AI-ass2\\testcases\\7ASV-easy.txt";
		
		problemSetup.loadProblem(filename);
		
		System.out.println(problemSetup.getASVCount());
		
		List<Point2D> asvConfig = problemSetup.getInitialState().getASVPositions();
		
		for(int i=0;i<asvConfig.size();i++){
			System.out.println(asvConfig.get(i));
		}
		
		problemSetup.getInitialState().convertToAngle();
		System.out.println("");
		problemSetupAngle.loadProblem(filename);
		
		System.out.println(problemSetupAngle.getASVCount());
		
		List<Point2D> asvConfig2 = problemSetupAngle.getInitialState().getASVPositions();
		List<Double> asvConfig3 = problemSetupAngle.getInitialState().getAngles();
		
		for(int i=0;i<asvConfig2.size();i++){
			System.out.println(asvConfig2.get(i));
		}
		
		for(int i=0;i<asvConfig3.size();i++){
			System.out.println(asvConfig3.get(i));
		}
		
		
		
		
		
		
	}
	
}
