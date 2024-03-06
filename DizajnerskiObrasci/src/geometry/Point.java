package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape implements Cloneable{
	
	private int x;
	private int y;
	private boolean selected;
	
	public Point(){
		
	}
	
	public Point(int x,int y){
		this.x=x;
		this.y=y;
	}
	
	public Point(int x, int y, boolean selected){
		this(x,y);
		this.selected=selected;
	}
	
	public Point(int x, int y, boolean selected,Color color){
		this(x,y,selected);
		this.setColor(color);
	}
	
	public void draw(Graphics g){
		g.setColor(getColor());
		g.drawLine(this.x-2, this.y, this.x+2, this.y);
		g.drawLine(this.x, this.y-2, this.x, this.y+2);
		
		
		if(isSelected()){
			g.setColor(Color.BLUE);
			g.drawRect(this.x-3,this.y-3,6,6);
			
		}
		
	}
	

	
	public boolean contains(int x, int y) {
		
		return this.distance(x, y)<=3;
	}

    public int compareTo(Object o){
    	if(o instanceof Point){
    		Point pocetak=new Point(0,0);
    	return (int)(this.distance(pocetak.getX(), pocetak.getY())-((Point)o).distance(pocetak.getX(),pocetak.getY()));
    		
    	} 
    	return 0;
    }
	
	public void moveBy(int byX, int byY){
		this.x=this.x+byX;
		this.y=this.y+byY;
	}
	
	public void newPoint(int byX, int byY){
		this.x=byX;
		this.y=byY;
	}
	
	public boolean equals(Object obj){
		if(obj instanceof Point){
			Point pomocni=(Point) obj; 
			if(this.x==pomocni.x && this.y==pomocni.y){
				return true;	
			}
			else {
				return false;
			}
			
		}
		
		else{
			return false;
		}
	}
	
	public Point clone() {
		Point p = new Point();
		p.setX(this.x);
		p.setY(this.y);
		p.setColor(getColor());
		return p;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public void setX(int x)
	{
		this.x=x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public void setY(int y)
	{
		this.y=y;	
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public double distance(int x2, int y2)
	{
		double dx=this.x-x2;
		double dy=this.y-y2;
		double d=Math.sqrt(dx*dx+dy*dy);
		return d;
	}
	
	public String toString(){
		return "("+x+","+y+")";
			
	}



}
