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
import problem.Obstacle;
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
		String filename = "C:/Users/gohzenhao/Documents/3ASV.txt";
		
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
		
		for(int i=0;i<300;i++){
			sampler.Sample();
		}
		System.out.println(sampler.ASVConfigs().size());
		ArrayList<Rectangle2D> rects = new ArrayList<Rectangle2D>();
		
		for(int i=0;i<problemSetupAngle.getObstacles().size();i++){
			rects.add(problemSetupAngle.getObstacles().get(i).getRect());
		}
		visual.addRectangles(rects);
		for(int i=0;i<sampler.ASVConfigs().size();i++){
//		System.out.println(sampler.ASVConfigs().get(i).getAngles());			
//			System.out.println(sampler.ASVConfigs().get(i).getASVPositions());
			visual.addPoints(sampler.ASVConfigs().get(i).getASVPositions());
			visual.repaint();
			visual.waitKey();
			visual.addLinkedPoints(sampler.ASVConfigs().get(i).getASVPositions());
			

			
		}
		
		
		
		
		ASVConfig asv1 = problemSetupAngle.getInitialState();
		
		ASVConfig asv2 = problemSetupAngle.getGoalState();
		
//		asv2.addPoints(0.350, 0.8);
//		asv2.addAngle(90);
//		asv2.addAngle(0);
		
//		List<ASVConfig> path = asv1.generatePath(asv2);
		
		
//		for(int i=0;i<path.size();i++){
//			visual.addPoints(path.get(i).getASVPositions());
//			visual.repaint();
//			visual.waitKey();
//			visual.addLinkedPoints(path.get(i).getASVPositions());
//		}
//		problemSetupAngle.setPath(path);
//		System.out.println(problemSetupAngle.solutionLoaded());
//		problemSetupAngle.saveSolution("answer.txt");
		System.out.println("--");
		asv1.getASVPositions();
		asv2.getASVPositions();
		System.out.println(validPath(problemSetupAngle,asv1,asv2));
		
		
		
		
		
		
//		ASVConfig currentASV = problemSetupAngle.getInitialState();
//		
//		for(int i=0;i<sampler.ASVConfigs().size();i++){
//			ASVConfig nextASV = sampler.ASVConfigs().get(i);
//			List<ASVConfig> path = currentASV.generatePath(nextASV);
//			currentASV = nextASV;
//			
//			for(int u=0;u<path.size();u++){
//				
//				visual.addPoints(path.get(u).getASVPositions());
////				visual.waitKey();
//				visual.addLinkedPoints(path.get(u).getASVPositions());
//				
//			}
//			
//		}
		

		
	
		
			
	}

	
	private static boolean validPath (ProblemSpec problemSpec, ASVConfig asv1, ASVConfig asv2) {
		
		System.out.println(asv1.getASVCount());
		for(int u=0;u<asv1.getASVCount();u++){
			
			double x1 = asv1.getPosition(u).getX();
			double y1 = asv1.getPosition(u).getY();
			double x2 = asv2.getPosition(u).getX();
			double y2 = asv2.getPosition(u).getY();
			
			List<Obstacle> obstacles = problemSpec.getObstacles();
			
			System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);
			System.out.println(obstacles.size());
			
			for(int i=0;i<obstacles.size();i++){
				System.out.println(obstacles.get(i).toString());
				if (obstacles.get(i).getRect().intersectsLine(x1, y1, x2, y2)) {
					System.out.println(u);
					return false;
				}
			}
		}
		
		return true;
	}

}
