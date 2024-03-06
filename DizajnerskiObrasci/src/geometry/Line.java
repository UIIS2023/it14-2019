package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape implements Cloneable{
	
	private Point startPoint=new Point();
	private Point endPoint=new Point();
	private boolean selected;
	
	public Line(){
		
	}
	
	public Line(Point startPoint, Point endPoint){
		this.startPoint=startPoint;
		this.endPoint=endPoint;
	}
	
	public Line(Point startPoint,Point endPoint,boolean selected){
		this(startPoint,endPoint);
		this.selected=selected;
	}
	
	public Line(Point startPoint,Point endPoint,boolean selected,Color color){
		this(startPoint,endPoint,selected);
		this.setColor(color);
		
	}
	
	public Point middleOfLine(){
		int middleByX=(startPoint.getX()+endPoint.getX())/2;
		int middleByY=(startPoint.getY()+ endPoint.getY())/2;
		Point middlePoint=new Point(middleByX,middleByY);
		return middlePoint;
	}
	
    public void draw(Graphics g){
    	g.setColor(getColor());
    	g.drawLine(this.startPoint.getX(), this.startPoint.getY(), this.endPoint.getX(), this.endPoint.getY());
    	
    	
    	if(isSelected()){
    		g.setColor(Color.BLUE);
    		g.drawRect(this.endPoint.getX()-3, this.endPoint.getY()-3, 6, 6);
    		g.drawRect(this.startPoint.getX()-3, this.startPoint.getY()-3,6,6);
    		g.drawRect(this.middleOfLine().getX()-3, this.middleOfLine().getY()-3,6,6);
    	}
    }
    
    
	
	public boolean contains(int x, int y) {
		
		if ((startPoint.distance(x, y) + endPoint.distance(x, y)) - length() <= 0.05) {
			return true;
		} else {
			return false;
		}
	}
	
    public int compareTo(Object o){
    	 if(o instanceof Line){
    	 return (int)(this.length()- ((Line)o).length());
    	  
    	 }
         return 0;
    		
    }
	public void moveBy(int byX, int byY){
		this.startPoint.moveBy(byX, byY);
		this.endPoint.moveBy(byX, byY);	}
	
	public void newLine(int sX, int sY, int eX, int eY){
		this.startPoint.newPoint(sX, sY);
		this.endPoint.newPoint(eX, eY);	}
	
	
	public boolean equals(Object obj){
		if(obj instanceof Line){
			Line pomocna=(Line) obj;
			if(this.startPoint.equals(pomocna.startPoint)&& this.endPoint.equals(pomocna.endPoint)){
				return true;
			}
			else{
				return false;
			}	
		}
		else{
			return false;
		}
	}
	
	public Line clone() {
		Line line = new Line();
		line.getStartPoint().setX(this.getStartPoint().getX());
		line.getStartPoint().setY(this.getStartPoint().getY());
		line.getEndPoint().setX(this.getEndPoint().getX());
		line.getEndPoint().setY(this.getEndPoint().getY());
		line.getStartPoint().setColor(this.getStartPoint().getColor());
		line.getEndPoint().setColor(this.getEndPoint().getColor());
		line.setColor(getColor());
		return line;
	}
	
	
	
	
	public double length()
	{
		return startPoint.distance(endPoint.getX(),endPoint.getY());
		
	}
	
	
	public Point getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	public Point getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public String toString(){
		return  startPoint + " --> " + endPoint;
	}

	
	
	
	

}
