package problem;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import solution.Edge;
import tester.Tester;

import java.awt.geom.Point2D;
import java.text.DecimalFormat;

/**
 * Represents a configuration of the ASVs. This class doesn't do any validity
 * checking - see the code in tester.Tester for this. 
 *
 * @author lackofcheese
 */
public class ASVConfig {
	private double baseASVx;
	private double baseASVy;
	private List<Double> asvAngle = new ArrayList<Double>();
	/** The position of each ASV */
	private List<Point2D> asvPositions = new ArrayList<Point2D>();
	
	private DecimalFormat format = new DecimalFormat("#.000");
	
	private int stepsToMove;
	
	private Tester tester = new Tester();
	
	private List<Edge> edges = new ArrayList<Edge>();
	
	private ASVConfig parent;
	
	private double distanceToGoal;
	
	private double costFromStart;
	

	/**
	 * Constructor. Takes an array of 2n x and y coordinates, where n is the
	 * number of ASVs
	 *
	 * @param coords
	 *            the x- and y-coordinates of the ASVs.
	 */
	public ASVConfig(double[] coords) {
		baseASVx = coords[0];
		baseASVy = coords[1];
		for (int i = 1; i < coords.length / 2; i++) {
			double angle = (double) Math.toDegrees(Math.atan2(coords[i * 2 + 1]-coords[(i-1) * 2 + 1], coords[i * 2]-coords[(i-1) * 2]));
			if(angle<0 && angle >-180)
				angle+=360;				
			asvAngle.add(angle);
		}
	}
	
	public ASVConfig(){
		
		
	}

	/**
	 * Constructs an ASVConfig from a space-separated string of x- and y-
	 * coordinates
	 *
	 * @param asvCount
	 *            the number of ASVs to read.
	 * @param str
	 *            the String containing the coordinates.
	 */
	public ASVConfig(int asvCount, String str) throws InputMismatchException {
		Scanner s = new Scanner(str);
		baseASVx = s.nextDouble();
		baseASVy = s.nextDouble();
		double previousX = baseASVx;
		double previousY = baseASVy;
		double currentX,currentY;
		for (int i = 1; i < asvCount; i++) {
			currentX = s.nextDouble();
			currentY = s.nextDouble();
			double angle = (double) Math.toDegrees(Math.atan2(currentY-previousY, currentX-previousX));
			if(angle<0 && angle >-180)
				angle+=360;				
			asvAngle.add(angle);
			previousX = currentX;
			previousY = currentY;
		}
		s.close();
	}

	/**
	 * Copy constructor.
	 *
	 * @param cfg
	 *            the configuration to copy.
	 */
	public ASVConfig(ASVConfig cfg) {
		asvPositions = cfg.getASVPositions();
	}

	/**
	 * Returns a space-separated string of the ASV coordinates.
	 *
	 * @return a space-separated string of the ASV coordinates.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Point2D point : asvPositions) {
			if (sb.length() > 0) {
				sb.append(" ");
			}
			sb.append(point.getX());
			sb.append(" ");
			sb.append(point.getY());
		}
		return sb.toString();
	}

	/**
	 * Returns the maximum straight-line distance between the ASVs in this state
	 * vs. the other state, or -1 if the ASV counts don't match.
	 *
	 * @param otherState
	 *            the other state to compare.
	 * @return the maximum straight-line distance for any ASV.
	 */
	public double maxDistance(ASVConfig otherState) {
		if (this.getASVCount() != otherState.getASVCount()) {
			return -1;
		}
		double maxDistance = 0;
		for (int i = 0; i < this.getASVCount(); i++) {
			double distance = this.getPosition(i).distance(
					otherState.getPosition(i));
			if (distance > maxDistance) {
				maxDistance = distance;
			}
		}
		return maxDistance;
	}

	/**
	 * Returns the total straight-line distance over all the ASVs between this
	 * state and the other state, or -1 if the ASV counts don't match.
	 *
	 * @param otherState
	 *            the other state to compare.
	 * @return the total straight-line distance over all ASVs.
	 */
	public double totalDistance(ASVConfig otherState) {
		if (this.getASVCount() != otherState.getASVCount()) {
			return -1;
		}
		double totalDistance = 0;
		for (int i = 0; i < this.getASVCount(); i++) {
			totalDistance += this.getPosition(i).distance(
					otherState.getPosition(i));
		}
		return totalDistance;
	}

	/**
	 * Returns the position of the ASV with the given number.
	 *
	 * @param asvNo
	 *            the number of the ASV.
	 * @return the position of the ASV with the given number.
	 */
	public Point2D getPosition(int asvNo) {
		return asvPositions.get(asvNo);
	}

	/**
	 * Returns the number of ASVs in this configuration.
	 *
	 * @return the number of ASVs in this configuration.
	 */
	public int getASVCount() {
		return asvAngle.size()+1;
	}
	
	public List<Double> getAngles()
	{
		return asvAngle;
	}

	/**
	 * Returns the positions of all the ASVs, in order.
	 *
	 * @return the positions of all the ASVs, in order.
	 */
	public List<Point2D> getASVPositions() {
		
		asvPositions = new ArrayList<Point2D>();
		asvPositions.add(new Point2D.Double(baseASVx,baseASVy));

		double previousX = baseASVx;
		double previousY = baseASVy;
		double currentX,currentY;
		for(int i = 0;i < asvAngle.size();i++)
		{
			double angle = asvAngle.get(i);
			currentX = previousX + 0.05 * Math.cos(angle*22/1260.0);
			currentY = previousY + 0.05 * Math.sin(angle*22/1260.0) ;
	
			currentX = Double.parseDouble(format.format(currentX));
			currentY = Double.parseDouble(format.format(currentY));
			asvPositions.add(new Point2D.Double(currentX,currentY));
			previousX = currentX;
			previousY = currentY;			
		}
		return new ArrayList<Point2D>(asvPositions);
	}
	
	public void addPoints(double x,double y){
		
		this.baseASVx = x;
		this.baseASVy = y;
		
	}
	
	public void addAngle(double inAngle){
		
		this.asvAngle.add(inAngle);
		
	}
	
	public int stepsX(ASVConfig anotherASV){
		
		double xDiff = anotherASV.baseASVx - this.baseASVx;
		xDiff = xDiff/0.001;
		
		return (int)Math.round(xDiff);
	}
	
	public int stepsY(ASVConfig anotherASV){
		
		double yDiff = anotherASV.baseASVy - this.baseASVy;
		yDiff = yDiff/0.001;
		
		return (int)Math.round(yDiff);
		
	}
	public double moveFrontX(){
		this.baseASVx += 0.001;
		return this.baseASVx;
	}
	
	public double moveFrontY(){
		this.baseASVy+= 0.001;
		return this.baseASVy;
	}
	
	public double moveBackX(){
		this.baseASVx -= 0.001;
		return this.baseASVx;
	}
	
	public double moveBackY(){
		this.baseASVy -=0.001;
		return this.baseASVy;
	}
	
	public double getX(){
		return this.baseASVx;
	}
	
	public double getY(){
		return this.baseASVy;
	}
	
	public double angleDiff(ASVConfig anotherASV,int i){
		
		double angle1 = this.asvAngle.get(i);
		
		double angle2 = anotherASV.getAngles().get(i);
		
		double diff = angle2 - angle1;
		
		return diff;
		
		
	}
	
	
	public List<ASVConfig> generatePath(ASVConfig anotherASV){
		
		int stepsX = this.stepsX(anotherASV);
		int stepsY = this.stepsY(anotherASV);
		
//		System.out.println("Steps X to get to another ASV : "+stepsX);
//		System.out.println("Steps Y to get to another ASV : "+stepsY);
		
		
		Double newPosX;
		Double newPosY;
		
		List<ASVConfig> allSteps = new ArrayList<ASVConfig>();
		
		while(stepsX!=0 || stepsY!=0){
			
			ASVConfig oneStep = new ASVConfig();
			
			newPosX = this.getX();
			newPosY = this.getY();
			
			if(stepsX!=0){
				if(stepsX>0){
					stepsX -= 1;
					newPosX = this.moveFrontX();
//					System.out.println("Moving front X..");
				}
				else if(stepsX<0){
					stepsX += 1;
					newPosX = this.moveBackX();
//					System.out.println("Moving back X");
				}

			}
			
			if(stepsY!=0){
				if(stepsY>0){
					stepsY -= 1;
					newPosY = this.moveFrontY();
//					System.out.println("Moving front Y");
				}
				else if(stepsY<0){
					stepsY += 1;
					newPosY = this.moveBackY();
//					System.out.println("Moving back Y");
				}

			}
			
			oneStep.addPoints(newPosX, newPosY);
			for(int i=0;i<anotherASV.getAngles().size();i++){
				oneStep.addAngle(anotherASV.getAngles().get(i));
			}
			oneStep.getASVPositions();
			allSteps.add(oneStep);
		}
		
		return allSteps;
		
	}
	
	public boolean addEdge(ASVConfig inASV){
		
		Edge edge = new Edge(inASV);
		edges.add(edge);
		return true;
		
	}
	
	public List<Edge> getEdges(){
		return this.edges;
	}
	
	public ASVConfig getParent(){
		return this.parent;
	}
	
	public double getCostToGoal(ASVConfig goal){
		
		double x = Math.abs(goal.getX() - this.getX());
		double y = Math.abs(goal.getY() - this.getY());
		double value = (x*x)+(y*y);
		return value;
		
	}
	
	public void setCostToGoal(double cost){
		this.distanceToGoal = cost;
	}
	
	public void setParent(ASVConfig inParent){
		this.parent = inParent;
	}
	
	public double getCostFromStart(ASVConfig start){
		
		double x = Math.abs(start.getX() - this.getX());
		double y = Math.abs(start.getY() - this.getY());
		double value = (x*x)+(y*y);
		return value;
		
	}
	
	public void SetCostFromStart(double cost){
		this.costFromStart = cost;
	}
	

	
//	public ASVConfig findNextPosition(){
//	
//		
//	}
	
}
