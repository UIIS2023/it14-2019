package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends SurfaceShape {

    private Point upperLeftPoint=new Point();
	private int width;
	private int height;
	private boolean selected;
	
	public Rectangle(){
		
	}
	
	public Rectangle(Point upperLeftPoint,int width,int height){
		this.upperLeftPoint=upperLeftPoint;
		this.width=width;
		this.height=height;
	}
	
	public Rectangle(Point upperLeftPoint,int width,int height,boolean selected){
		this(upperLeftPoint,width,height);
		this.selected=selected;
	}
	
	public Rectangle(Point upperLeftPoint,int width,int height,boolean selected,Color color){
		this(upperLeftPoint,width,height,selected);
		this.setColor(color);
	}
	
	public Rectangle(Point upperLeftPoint,int width,int height,boolean selected,Color color,Color innerColor){
		this(upperLeftPoint,width,height,selected,color);
		this.setInnerColor(innerColor);
		
	}
	
	
	public void fill(Graphics g){
		g.setColor(getInnerColor());
		g.fillRect(this.upperLeftPoint.getX()+1, this.upperLeftPoint.getY()+1, this.width-1, this.height-1);
	}
	
	public void draw(Graphics g){
		g.setColor(getColor());
		g.drawRect(this.upperLeftPoint.getX(),this.upperLeftPoint.getY(), this.width,this.height);
		this.fill(g);
		
		
		
		if(isSelected())
		{
			g.setColor(Color.BLUE);
			g.drawRect(this.upperLeftPoint.getX()-3,this.upperLeftPoint.getY()-3, 6, 6);
			g.drawRect(this.upperLeftPoint.getX()-3, this.upperLeftPoint.getY()+this.height-3, 6, 6);
			g.drawRect(this.upperLeftPoint.getX()+this.width-3,this.upperLeftPoint.getY()-3,6,6);
			g.drawRect(this.upperLeftPoint.getX()+this.width-3, this.upperLeftPoint.getY()+this.height-3, 6, 6);
		}
	}
	
	

	public boolean contains(Point p) {
		if(this.upperLeftPoint.getX() <= p.getX()&&
		   this.upperLeftPoint.getY() <= p.getY() &&
	       p.getX() <= this.upperLeftPoint.getX() + this.width &&
	       p.getY() <= this.upperLeftPoint.getY() + this.height)
			return true;
		else 
			return false;
	}



	public boolean contains(int x, int y) {
		if(this.upperLeftPoint.getX() <= x &&
		   this.upperLeftPoint.getY() <= y &&
		   y <= this.upperLeftPoint.getY() + this.height &&
		   x <= this.upperLeftPoint.getX() + this.width)
        return true;
        else 
		return false;
	}
	
	public int compareTo(Object o){
		if(o instanceof Rectangle){
			return (int) (this.area()-((Rectangle)o).area());
			
		}
		return 0;
	}
   
	 public void moveBy(int byX, int byY){
		 this.upperLeftPoint.moveBy(byX, byY);
		 
	 }
	 
	 public void newUpperLeftPoint(int X, int Y){
		 this.upperLeftPoint.newPoint(X, Y);
		 
	 }
	

	public boolean equals(Object obj){
		if(obj instanceof Rectangle){
		Rectangle pomocna=(Rectangle) obj;
		if(this.upperLeftPoint.equals(pomocna.upperLeftPoint)&& this.width==pomocna.width && this.height==pomocna.height){
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
	
	public Rectangle clone(){
		
		Rectangle rectangle = new Rectangle();
		rectangle.getUpperLeftPoint().setX(this.getUpperLeftPoint().getX());
		rectangle.getUpperLeftPoint().setY(this.getUpperLeftPoint().getY());
		rectangle.getUpperLeftPoint().setColor(this.getUpperLeftPoint().getColor());
		rectangle.setWidth(this.getWidth());
		rectangle.setHeight(this.getHeight());
		rectangle.setColor(this.getColor());
		rectangle.setInnerColor(this.getInnerColor());
		return rectangle;
	}
	
	
	
	public double area()
	{
		return width*height;
		
	}
	
	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}
	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public String toString(){
		return "Upper Left Point=" + upperLeftPoint + ", width="+ width +", height="+height;
	}


	
}
