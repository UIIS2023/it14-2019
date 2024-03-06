package mvc;

import java.io.Serializable;
import java.util.ArrayList;

import geometry.Shape;

public class DrawingModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private Shape selected;
	
	public Shape getSelected() {
		return selected;
	}


	public void setSelected(Shape selected) {
		this.selected = selected;
	}


	public void add(Shape s){
		shapes.add(s);
	}
		
	public void remove(Shape s){
		shapes.remove(s);
	}
	
	public Shape get(int index){
		return shapes.get(index);
	}
	
	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}


}
