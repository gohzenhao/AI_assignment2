package solution;

import java.awt.geom.Point2D;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import problem.ASVConfig;
import problem.ProblemSpec;
import tester.Tester;

public class Sampler {
	
	
	private int ASVCount;
	
	private ASVConfig currentASV;
	
	private List<ASVConfig> ASVConfigAngles = new ArrayList<ASVConfig>();
	
	private Random random = new Random();
	
	private double pointX;
	
	private double pointY;
	
	private double angle;
	
	private DecimalFormat format = new DecimalFormat("#.0");
	
	private DecimalFormat format2 = new DecimalFormat("#.000");
	
	private Tester tester = new Tester();
	
	private ProblemSpec problemSpec = new ProblemSpec();
	
	
	public Sampler(ProblemSpec inProblemSpec){
		
		this.problemSpec = inProblemSpec;
		this.ASVCount = inProblemSpec.getASVCount();
		
	}
	
	public void Sample(){
		
//		this.pointX = Double.parseDouble(format2.format(random.nextDouble()));
//		this.pointY = Double.parseDouble(format2.format(random.nextDouble()));
		this.pointX = Math.random();
		this.pointY = Math.random();
		
		currentASV = new ASVConfig();
		
		currentASV.addPoints(this.pointX, this.pointY);
		Point2D previousPoint = new Point2D.Double(pointX,pointY);
		Point2D currentPoint;
		double distance;
//		for(int i=0;i<this.ASVCount-1;i++){
//			do
//			{
//				angle = Math.random()*360;
//				double currentX,currentY;
//				currentX = previousPoint.getX() + 0.05 * Math.cos(angle*22/1260.0);
//				currentY = previousPoint.getY() + 0.05 * Math.sin(angle*22/1260.0);
//				currentPoint = new Point2D.Double(currentX,currentY);
//			}while(currentPoint.distance(previousPoint)!=0.05);
//			currentASV.addAngle(angle);
//			previousPoint = currentPoint;
//		}
		currentASV.addAngle(90);
		currentASV.addAngle(0);
		if(!tester.hasCollision(currentASV, this.problemSpec.getObstacles()) && tester.fitsBounds(currentASV)){
		
			if(tester.isConvex(currentASV) && tester.hasEnoughArea(currentASV)){
				
				this.ASVConfigAngles.add(currentASV);
				
			}
		}
		
		
		
		
	}
	
	public List<ASVConfig> ASVConfigs(){
		return this.ASVConfigAngles;
	}
	
	
	
	

}
