package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Donut extends Circle {
	
	private int innerRadius;
	
	public Donut() {
		
	}
	
	public Donut(Point center,int radius,int innerRadius) {
		super(center,radius);
		this.innerRadius=innerRadius;
		
		
	}
	
	public Donut(Point center,int radius, int innerRadius,boolean selected){
		this(center,radius,innerRadius);
		setSelected(selected);
		
	}
	
	public Donut(Point center,int radius, int innerRadius,boolean selected,Color color){
		this(center,radius,innerRadius,selected);
		this.setColor(color);
	}
	
	public Donut(Point center,int radius, int innerRadius,boolean selected,Color color,Color innerColor){
		this(center,radius,innerRadius,selected,color);
		this.setInnerColor(innerColor);
	}
	
	/*public void fill(Graphics g) {
		g.setColor(getInnerColor());
		super.fill(g);
		g.setColor(getColor());
		g.drawOval(getCenter().getX()-this.innerRadius, getCenter().getY()-this.innerRadius,
				this.innerRadius*2, this.innerRadius*2);
		g.setColor(new Color(240,240,240));
		g.fillOval(getCenter().getX()-this.innerRadius+1, getCenter().getY()-this.innerRadius+1,
				this.innerRadius * 2-2, this.innerRadius * 2-2);
		
	}
	*/
	
	//for transparent 
	public void draw(Graphics g){
		
		//super.draw(g);
		//this.fill(g);
		Shape donut= innerTransparent();
		g.setColor(getInnerColor());
		((Graphics2D) g).fill(donut);
		g.setColor(getColor());
		((Graphics2D) g).draw(donut);
		
		
		if(isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.getCenter().getX()-3, this.getCenter().getY()-3, 6, 6);
			g.drawRect(this.getCenter().getX()-this.getRadius()-3, this.getCenter().getY()-3,6,6);
			g.drawRect(this.getCenter().getX()+this.getRadius()-3, this.getCenter().getY()-3,6,6);
			g.drawRect(this.getCenter().getX()-3, this.getCenter().getY()-this.getRadius()-3,6,6);
			g.drawRect(this.getCenter().getX()-3, this.getCenter().getY()+this.getRadius()-3,6,6);
		}
		
		
	}
	
	private Shape innerTransparent() {
		Ellipse2D inner = new Ellipse2D.Double(this.getCenter().getX() - this.innerRadius, this.getCenter().getY()-this.innerRadius,this.innerRadius*2, this.innerRadius *2);
		Ellipse2D outer = new Ellipse2D.Double(super.getCenter().getX()-super.getRadius(), super.getCenter().getY()-super.getRadius(),super.getRadius()*2,super.getRadius()*2);
				
		Area outerArea = new Area(outer);
		Area innerArea = new Area(inner);
		
		outerArea.subtract(innerArea);
		return outerArea;
	}
	


	
	public boolean contains(int x,int y){
		double dFromCenter=this.getCenter().distance(x, y);
		return super.contains(x, y) && dFromCenter > this.innerRadius;
	}
	
	public boolean contains (Point p){
		double dFromCenter=this.getCenter().distance(p.getX(), p.getY());
		return super.contains(p) && dFromCenter > this.innerRadius;
	}
	
	public int compareTo(Object o){
		if(o instanceof Donut){
		return (int) (this.area()-((Donut)o).area());	
		}
		return 0;
		
	}
	public void moveBy(int byX, int byY){
		
		
		super.moveBy(byX, byY);
	}
	
    public void newCenter(int X, int Y){
		
		
		super.newCenter(X, Y);
	}
	
	
	public boolean equals(Object obj){
		if(obj instanceof Donut){
		Donut pomocni=(Donut) obj;
		if(this.innerRadius == pomocni.innerRadius &&
		   this.getCenter().equals(pomocni.getCenter()) &&
		   this.getRadius() == pomocni.getRadius())
          return true;
          else
          return false;
        		 	
		}
		else{
			return false;
		}
	}
	
	public Donut clone(){
		
		Donut donut = new Donut();
		donut.getCenter().setX(this.getCenter().getX());
		donut.getCenter().setY(this.getCenter().getY());
		donut.getCenter().setColor(this.getCenter().getColor());
		donut.setRadius(this.getRadius());
		donut.setInnerRadius(this.getInnerRadius());
		donut.setColor(this.getColor());
		donut.setInnerColor(this.getInnerColor());
		return donut;
	}
	
	public double area(){
           return super.area() - this.innerRadius*this.innerRadius*Math.PI;
	}

	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}
	
	public String toString(){
		return super.toString() + ", innerRadius=" + this.innerRadius;
	}
	

}
