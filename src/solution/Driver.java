package solution;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
		
		for(int i=0;i<50;i++){
			sampler.Sample();
		}
		ArrayList<Rectangle2D> rects = new ArrayList<Rectangle2D>();
		
//		System.out.println(sampler.ASVConfigs().size());
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
		
		System.out.println(problemSetupAngle.getInitialState().getASVPositions());
		System.out.println(problemSetupAngle.getGoalState().getASVPositions());
		String answer = "";
//		try
//		{
//			FileWriter fw= new FileWriter(outputFile);
//			BufferedWriter bw= new BufferedWriter(fw);
//			
//			List<ASVConfig> asvConfigs = sampler.ASVConfigs();
//			List<Point2D> positions;
//			
//			String x;
//			
//			String y;
//			bw.write("10 30");
//			bw.newLine();
//            bw.write("0.15 0.225 0.15 0.275 0.2 0.275 ");
//            bw.newLine();
//			for(int i=0;i<asvConfigs.size();i++){
//				
//				positions = asvConfigs.get(i).getASVPositions();
//				for(int u=0;u<positions.size();u++){
//					x = Double.toString(positions.get(u).getX());
//					y = Double.toString(positions.get(u).getY());
//				
//					bw.write(x+" "+y+" ");
//				}
//				
//				bw.newLine();
//					
//			}
//			bw.write("0.85 0.225 0.85 0.275 0.9 0.275 ");
//			
//			
//			bw.flush();
//			bw.close();
//			
//			
//		}
//		catch (IOException e)
//		{
//			System.out.println(e.getMessage());
//		}
		
		
	
		
		
		
		
		
		
		
		
		
	}
	
}
