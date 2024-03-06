package adapter;

import java.awt.Color;
import java.awt.Graphics;

import geometry.Point;
import geometry.SurfaceShape;
import hexagon.Hexagon;

public class HexagonAdapter extends SurfaceShape{

	private Hexagon hex;
	
	public HexagonAdapter(Hexagon hex) {
		this.hex = hex;
	}
	
	public HexagonAdapter(Point p,int R,boolean selected,Color lineColor, Color innerColor) {
		hex = new Hexagon(p.getX(),p.getY(),R);
	/*	this.hex.setX(p.getX());
		this.hex.setY(p.getY());
		this.hex.setR(R);*/
		this.hex.setSelected(selected);
		this.hex.setBorderColor(lineColor);
		this.hex.setAreaColor(innerColor);
	}

	public HexagonAdapter() {
	}

	@Override
	public void moveBy(int byX, int byY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean contains(Point p) {
		// TODO Auto-generated method stub
		return this.hex.doesContain(p.getX(), p.getY());
	}

	@Override
	public void fill(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(int x, int y) {
		// TODO Auto-generated method stub
		return this.hex.doesContain(x, y);
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		this.hex.paint(g);
	}

	public HexagonAdapter clone(){
		HexagonAdapter hexagon = new HexagonAdapter();
		Hexagon hex1 = new Hexagon(this.getHex().getX(),this.getHex().getY(),this.getHex().getR());
		hexagon.setHex(hex1);
		hexagon.setColor(this.getColor());
		hexagon.setInnerColor(this.getInnerColor());
		return hexagon;
	}
	
	public Hexagon getHex() {
		return hex;
	}

	public void setHex(Hexagon hex) {
		this.hex = hex;
	}
	
	public void setSelected(boolean flag){
		this.hex.setSelected(flag);
	}
	
	public void setColor(Color color) {
		this.hex.setBorderColor(color);
	}
	
	public void setInnerColor(Color color) {
		this.hex.setAreaColor(color);
	}
	
	public Color getInnerColor() {
		return this.hex.getAreaColor();
	}
	
	public Color getColor() {
		return this.hex.getBorderColor();
	}
	
	public String toString() {
		Point p = new Point(hex.getX(),hex.getY());
		return "Center =" + p + ", Radius =" + hex.getR();
	}
}
