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
	private List<ASVConfig> ASVConfigs = new ArrayList<ASVConfig>();	
	private Tester tester = new Tester();	
	private ProblemSpec problemSpec;	
	
	public Sampler(ProblemSpec inProblemSpec){		
		this.problemSpec = inProblemSpec;
		this.ASVCount = inProblemSpec.getASVCount();
		
	}
	
	public void Sample(int times){
		ASVConfig currentASV;
		this.ASVConfigs.add(problemSpec.getInitialState());
		for(int u=0;u<times;u++){
			currentASV = new ASVConfig();
			double baseX = Math.random();
			double baseY = Math.random();			
			currentASV.addPoints(baseX, baseY);
			currentASV.addAngle(Math.random()*360);
			if(ASVCount==3)
				currentASV.addAngle(Math.random()*360);
			if(ASVCount>3)
			{
				double eachAngle = (180.00*(this.ASVCount*2-2))/(ASVCount*2);
				System.out.println(eachAngle);
				for(int i = 1 ; i < this.ASVCount-1;i++)
				{
					double prevAngle = currentASV.getAngles().get(i-1);
					double angle;
					if(prevAngle>0 && prevAngle<180)
					{
						double conseqInAngle = 180 - prevAngle;
						double deltaAngle = Math.abs(conseqInAngle-eachAngle);
						if(eachAngle>conseqInAngle)
							angle = deltaAngle;
						else
							angle = 360 - deltaAngle; 
					}
					else if(prevAngle>180 && prevAngle<360)
					{
						double suppAngle = 360 - prevAngle;
						double conseqInAngle = 180 - suppAngle;
						angle = eachAngle + conseqInAngle;
					}
					else if(prevAngle == 0 || prevAngle==360)
						angle = 180 + eachAngle;
					else
						angle = eachAngle;
					currentASV.addAngle(angle);
				}
			}
//			randomly generated angles
//			for(int i=0;i<this.ASVCount-1;i++){
//				do
//				{
//					angle = Math.random()*360;
//					double currentX,currentY;
//					currentX = previousPoint.getX() + 0.05 * Math.cos(angle*22/1260.0);
//					currentY = previousPoint.getY() + 0.05 * Math.sin(angle*22/1260.0);
//					currentPoint = new Point2D.Double(currentX,currentY);
//				}while(currentPoint.distance(previousPoint)!=0.05);
//				currentASV.addAngle(angle);
//				previousPoint = currentPoint;
//			}

			
			currentASV.getASVPositions();
			System.out.println("Sample "+u +":");
			System.out.println(currentASV.getAngles());
			System.out.println(currentASV.getASVPositions());
			boolean result = false;

				
			if(!tester.hasCollision(currentASV, this.problemSpec.getObstacles()) && tester.fitsBounds(currentASV)){
			
				if(tester.isConvex(currentASV) && tester.hasEnoughArea(currentASV)){
					//adding edge to each existing ASVConfig in the list if valid
//					for(int i=0;i<this.ASVConfigs.size();i++){
//						ASVConfig lastASV = this.ASVConfigs.get(i);
//						if(this.validPath(lastASV, currentASV)){
//							result = lastASV.addEdge(currentASV);
//						}					
//					}
					this.ASVConfigs.add(currentASV);
				}
			}
				
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
		return this.ASVConfigs;
	}
	
	
	
	

}
