package solution;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.List;

import problem.ProblemSpec;
import tester.Tester;
public class Driver {

	
	public static void main(String[] args) throws IOException{
		
		ProblemSpec problemSetup = new ProblemSpec();
		ProblemSpec problemSetupAngle = new ProblemSpec();
		Tester tester = new Tester();
		String filename = "C:/Users/gohzenhao/Documents/3ASV-easy.txt";
		
		problemSetup.loadProblem(filename);
		
		List<Point2D> asvConfig = problemSetup.getInitialState().getASVPositions();
		
//		for(int i=0;i<asvConfig.size();i++){
//			System.out.println(asvConfig.get(i));
//		}
//		
//		problemSetup.getInitialState().convertToAngle();
		problemSetupAngle.loadProblem(filename);
		
		System.out.println(problemSetupAngle.getASVCount());
		
//		List<Point2D> asvConfig2 = problemSetupAngle.getInitialState().getASVPositions();
//		List<Double> asvConfig3 = problemSetupAngle.getInitialState().getAngles();
		
//		System.out.println(tester.isConvex(problemSetupAngle.getInitialState()));
		
//		for(int i=0;i<asvConfig2.size();i++){
//			System.out.println(asvConfig2.get(i));
//		}
//		
//		for(int i=0;i<asvConfig3.size();i++){
//			System.out.println(asvConfig3.get(i));
//		}
		
		Sampler sampler = new Sampler(3);
		
		for(int i=0;i<10;i++){
			sampler.Sample();
		}
		
		for(int i=0;i<sampler.ASVConfigs().size();i++){
			System.out.println(sampler.ASVConfigs().get(i).getAngles());
//			
			System.out.println(sampler.ASVConfigs().get(i).getASVPositions());
			
//			System.out.println("Has valid broom lengths:");
//			
//			System.out.println(tester.hasValidBoomLengths(sampler.ASVConfigs().get(i)));
//			
//			System.out.println("Has enough area : ");
//			
//			System.out.println(tester.hasEnoughArea(sampler.ASVConfigs().get(i)));
//			
//			System.out.println("Is it convex?");
//			
//			System.out.println(tester.isConvex(sampler.ASVConfigs().get(i)));
//			
//			System.out.println();
			
		}
		
	
		
		
		
		
		
		
		
		
		
	}
	
}
