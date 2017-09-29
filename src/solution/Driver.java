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
import java.util.Collections;
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
		Tester tester = new Tester(problemSetupAngle);
		String filename = "C:/Users/gohzenhao/Documents/3ASV.txt";
		
		File outputFile = new File("answer.txt");
		
		VisualHelper visual = new VisualHelper();
		
		
		problemSetupAngle.loadProblem(filename);
		
		System.out.println(problemSetupAngle.getASVCount());
		
		Sampler sampler = new Sampler(problemSetupAngle);
		
		sampler.Sample(1000);
//		System.out.println(sampler.ASVConfigs().size());
//		ArrayList<Rectangle2D> rects = new ArrayList<Rectangle2D>();
//		
//		for(int i=0;i<problemSetupAngle.getObstacles().size();i++){
//			rects.add(problemSetupAngle.getObstacles().get(i).getRect());
//		}
//		visual.addRectangles(rects);
//		for(int i=0;i<sampler.ASVConfigs( ).size();i++){
////		System.out.println(sampler.ASVConfigs().get(i).getAngles());			
////			System.out.println(sampler.ASVConfigs().get(i).getASVPositions());
//			visual.addPoints(sampler.ASVConfigs().get(i).getASVPositions());
//			visual.repaint();
//			visual.waitKey();
//			visual.addLinkedPoints(sampler.ASVConfigs().get(i).getASVPositions());
//
//		}
			
		
		Environment environment = new Environment(problemSetupAngle,sampler.ASVConfigs(),sampler);
		
		List<ASVConfig> path = environment.compute();
		System.out.println(path);
		Collections.reverse(path);
		ASVConfig current = problemSetupAngle.getInitialState();
		List<ASVConfig> steps = new ArrayList<ASVConfig>();
		for(int i=0;i<path.size();i++){
			
			ASVConfig next = path.get(i);
			
			System.out.println(next.getASVPositions());
			
			steps.addAll(current.generatePath(next));
			
			current = next;
				
		}
		
		steps.add(0,problemSetupAngle.getInitialState());
		System.out.println("Initial node generated : "+steps.get(0));
		System.out.println("Initial node from problem spec :"+problemSetupAngle.getInitialState());
		System.out.println("Last node generated : "+steps.get(steps.size()-1));
		System.out.println("Last node from problem spec :"+problemSetupAngle.getGoalState());
		
		problemSetupAngle.setPath(steps);
		path.add(0,problemSetupAngle.getInitialState());
//		problemSetupAngle.setPath(path);
		problemSetupAngle.saveSolution("answer.txt");
		
		System.out.println(tester.hasGoalLast());
		System.out.println(tester.hasInitialFirst());
		
//		List<ASVConfig> path = new ArrayList<ASVConfig>();
//		ASVConfig asv1 = problemSetupAngle.getInitialState();
//		ASVConfig asv2 = new ASVConfig();
//		asv2.addPoints(0.3, 0.4);
//		asv2.addAngle(320);
//		asv2.addAngle(260);
//		visual.addPoints(asv1.getASVPositions());
//		visual.addPoints(asv2.getASVPositions());
//		visual.repaint();
//		System.out.println(asv2.getASVPositions());
//		asv1.getASVPositions();
//		path.add(asv1);
//		path = asv1.generatePath(asv2);
//		path.get(0).getASVPositions();
//		path.get(0).getPosition(0);
//		problemSetupAngle.setPath(path);
//		problemSetupAngle.saveSolution("answer.txt");
//		System.out.println(tester.hasGoalLast());
//		System.out.println(tester.hasInitialFirst());

		
		
		
		
		
		
		
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
//		

		
	
		
			
	}

}
