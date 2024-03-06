package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends SurfaceShape{
	
	private Point center=new Point();
	private int radius;
	private boolean selected;
	
	public Circle() {
		
	}
	
	public Circle(Point center, int radius){
		this.center=center;
		this.radius=radius;
	}
	
	public Circle(Point center,int radius, boolean selected){
		this(center,radius);
		this.selected=selected;
	}
    
	public Circle(Point center,int radius, boolean selected,Color color){
		this(center,radius,selected);
		this.setColor(color);
		
	}
	public Circle(Point center,int radius, boolean selected,Color color,Color innerColor){
		this(center,radius,selected,color);
		this.setInnerColor(innerColor);
		
	}
	
	public void fill(Graphics g){
		g.setColor(getInnerColor());
		g.fillOval(this.center.getX()-this.radius+1, this.center.getY()-this.radius+1,
				this.radius*2-2, this.radius*2-2);
		
	}
    
	public void draw(Graphics g){
		g.setColor(getColor());
		g.drawOval(this.center.getX()-this.radius,this.center.getY()-this.radius, this.radius*2, this.radius*2);
		this.fill(g);
		
		if(isSelected())
		{
			g.setColor(Color.BLUE);
			g.drawRect(this.center.getX()-3, this.center.getY()-3, 6, 6);
			g.drawRect(this.center.getX()-this.radius-3, this.center.getY()-3,6,6);
			g.drawRect(this.center.getX()+this.radius-3, this.center.getY()-3,6,6);
			g.drawRect(this.center.getX()-3, this.center.getY()-this.radius-3, 6, 6);
			g.drawRect(this.center.getX()-3, this.center.getY()+this.radius-3, 6, 6);
		}
	}

	
	public boolean contains(Point p) {
		return this.center.distance(p.getX(), p.getY())<=this.radius;
	
	}
	


	public boolean contains(int x, int y) {
		return this.center.distance(x, y)<=this.radius;
		
	}
	
	public int compareTo(Object o){
		if(o instanceof Circle){
		 return (this.radius-((Circle)o).radius);
			
		}
		return 0;
	}
	
	public void moveBy(int byX,int byY){
		this.center.moveBy(byX, byY);
	}
	
	public void newCenter(int X,int Y){
		this.center.newPoint(X, Y);
	}

	
	public boolean equals(Object obj){
		if(obj instanceof Circle){
		   Circle pomocna=(Circle)obj;
		   if(this.center.equals(pomocna.center)&& this.radius==pomocna.radius){
			   return true;
		   }
		   else {
			   return false;
		   }
		}
		else {
			return false;
		}
	}
	
	public Circle clone() {
		Circle circle = new Circle();
		circle.getCenter().setX(this.getCenter().getX());
		circle.getCenter().setY(this.getCenter().getY());
		circle.setRadius(this.getRadius());
		circle.getCenter().setColor(this.getCenter().getColor());
		circle.setColor(this.getColor());
		circle.setInnerColor(this.getInnerColor());
		return circle;
	}
	
	
	
	public double area()
	{
		return radius*radius*Math.PI;
		
	}
	
	public boolean Contains(Point p4)
	{
		double d=p4.distance(this.center.getX(),this.center.getY());
		if(d<=this.radius)
			return true;
		else
			return false;
		
	}
	public Point getCenter() {
		return center;
	}
	public void setCenter(Point center) {
		this.center = center;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public String toString(){
		return "Center="+ center+ ", radius="+ radius;
	}



}
