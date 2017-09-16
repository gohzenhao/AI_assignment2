package solution;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import problem.ASVConfig;
import problem.ProblemSpec;
import tester.Tester;
import visdebug.VisualHelper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException; 
public class Driver {

	
	public static void main(String[] args) throws IOException{
		

		ProblemSpec problemSetupAngle = new ProblemSpec();
		Tester tester = new Tester();
		String filename = "C:/Users/gohzenhao/Documents/3ASV-easy.txt";
		
		File outputFile = new File("answer.txt");
		
		VisualHelper visual = new VisualHelper();
		
		
//		for(int i=0;i<asvConfig.size();i++){
//			System.out.println(asvConfig.get(i));
//		}
//		
//		problemSetup.getInitialState().convertToAngle();
		problemSetupAngle.loadProblem(filename);
		
		System.out.println(problemSetupAngle.getASVCount());
		
//		System.out.println(tester.hasValidBoomLengths(problemSetupAngle.getGoalState()));
//		
//		System.out.println(tester.isConvex(problemSetupAngle.getInitialState()));
		
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
		
		Sampler sampler = new Sampler(problemSetupAngle);
		
		for(int i=0;i<500;i++){
			sampler.Sample();
		}
		System.out.println(sampler.ASVConfigs().size());
//		ArrayList<Rectangle2D> rects = new ArrayList<Rectangle2D>();
//		
//		for(int i=0;i<problemSetupAngle.getObstacles().size();i++){
//			rects.add(problemSetupAngle.getObstacles().get(i).getRect());
//		}
//		visual.addRectangles(rects);
//		for(int i=0;i<sampler.ASVConfigs().size();i++){
////		System.out.println(sampler.ASVConfigs().get(i).getAngles());			
////			System.out.println(sampler.ASVConfigs().get(i).getASVPositions());
//			visual.addPoints(sampler.ASVConfigs().get(i).getASVPositions());
//			visual.repaint();
//			visual.waitKey();
//			visual.addLinkedPoints(sampler.ASVConfigs().get(i).getASVPositions());
//			
//
//			
//		}
		
		ASVConfig asv1 = problemSetupAngle.getInitialState();
		ASVConfig asv2 = new ASVConfig();
		asv2.addPoints(0.251, 0.235);
		asv2.addAngle(90);
		asv2.addAngle(0);
		
//		visual.addPoints(asv1.getASVPositions());
//		visual.addPoints(asv2.getASVPositions());
//		visual.repaint();
		
		int stepsX = asv1.stepsX(asv2);
		int stepsY = asv1.stepsY(asv2);
		
		
		Double newPosX;
		Double newPosY;
		
		for(int i=0;i<asv1.getAngles().size();i++){
			
		}
		List<ASVConfig> allSteps = new ArrayList<ASVConfig>();
		
		while(stepsX!=0 || stepsY!=0){
			
			ASVConfig oneStep = new ASVConfig();
			
			newPosX = asv1.getX();
			newPosY = asv1.getY();
			
			if(stepsX!=0){
				stepsX -= 1;
				newPosX = asv1.moveX();
			}
			
			if(stepsY!=0){
				stepsY -= 1;
				newPosY = asv1.moveY();
			}
			
			oneStep.addPoints(newPosX, newPosY);
			for(int i=0;i<asv1.getAngles().size();i++){
				oneStep.addAngle(asv1.getAngles().get(i));
			}
			
			allSteps.add(oneStep);
		}
		
		for(int i=0;i<allSteps.size();i++){
			visual.addPoints(allSteps.get(i).getASVPositions());
			visual.repaint();
			visual.waitKey();
			visual.addLinkedPoints(allSteps.get(i).getASVPositions());
		}
		
		

		
//		System.out.println(problemSetupAngle.getInitialState().getASVPositions());
//		System.out.println(problemSetupAngle.getGoalState().getASVPositions());
		
		

		
		
	
		
		
		
		
		
		
		
		
		
	}
	
}
