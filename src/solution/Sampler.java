package solution;

import java.awt.geom.Point2D;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import problem.ASVConfig;
import problem.Obstacle;
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
	
	public void Sample(int times){
		
//		this.pointX = Double.parseDouble(format2.format(random.nextDouble()));
//		this.pointY = Double.parseDouble(format2.format(random.nextDouble()));
		ASVConfig currentASV;
		ASVConfig lastASV = problemSpec.getInitialState();
		this.ASVConfigAngles.add(lastASV);
		lastASV.getASVPositions();
		for(int u=0;u<times;u++){
			this.pointX = Math.random();
			this.pointY = Math.random();
			
			currentASV = new ASVConfig();
			
			currentASV.addPoints(this.pointX, this.pointY);
			
			
			Point2D previousPoint = new Point2D.Double(pointX,pointY);
			Point2D currentPoint;
			double distance;
			for(int i=0;i<this.ASVCount-1;i++){
				do
				{
					angle = Math.random()*360;
					double currentX,currentY;
					currentX = previousPoint.getX() + 0.05 * Math.cos(angle*22/1260.0);
					currentY = previousPoint.getY() + 0.05 * Math.sin(angle*22/1260.0);
					currentPoint = new Point2D.Double(currentX,currentY);
				}while(currentPoint.distance(previousPoint)!=0.05);
				currentASV.addAngle(angle);
				previousPoint = currentPoint;
			}
			
			
			
//			currentASV.addAngle(90);
//			currentASV.addAngle(0);
			
//			currentASV.addAngle(210);
//			currentASV.addAngle(240);
//			currentASV.addAngle(300);
//			currentASV.addAngle(0);
//			currentASV.addAngle(60);
//			currentASV.addAngle(90);
			
			currentASV.getASVPositions();
			
			boolean result = false;

				
					if(!tester.hasCollision(currentASV, this.problemSpec.getObstacles()) && tester.fitsBounds(currentASV)){
					
						if(tester.isConvex(currentASV) && tester.hasEnoughArea(currentASV)){
							
							for(int i=0;i<this.ASVConfigAngles.size();i++){
								lastASV = this.ASVConfigAngles.get(i);
								if(this.validPath(lastASV, currentASV)){
									result = lastASV.addEdge(currentASV);
								}
								
								
							}
							this.ASVConfigAngles.add(currentASV);
							
						}
					}
				
			
			
				
//				if(!tester.hasCollision(currentASV, this.problemSpec.getObstacles()) && tester.fitsBounds(currentASV)){
//				
//					if(tester.isConvex(currentASV) && tester.hasEnoughArea(currentASV)){
//					
//						this.ASVConfigAngles.add(currentASV);
//					}
//				}
				
		}
		
		
		
		
	}
	
	public boolean validPath (ASVConfig asv1, ASVConfig asv2) {
		
		for(int u=0;u<asv1.getASVCount();u++){
			
			double x1 = asv1.getPosition(u).getX();
			double y1 = asv1.getPosition(u).getY();
			double x2 = asv2.getPosition(u).getX();
			double y2 = asv2.getPosition(u).getY();
			
			List<Obstacle> obstacles = this.problemSpec.getObstacles();
			
			for(int i=0;i<obstacles.size();i++){
				if (obstacles.get(i).getRect().intersectsLine(x1, y1, x2, y2)) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public List<ASVConfig> ASVConfigs(){
		return this.ASVConfigAngles;
	}
	
	
	
	

}
