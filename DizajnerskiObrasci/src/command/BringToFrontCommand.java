package command;

import java.util.Collections;
import java.util.Iterator;

import javax.swing.JOptionPane;

import geometry.Shape;
import geometry.SurfaceShape;
import mvc.DrawingModel;

public class BringToFrontCommand implements Command{
	
	private Shape shape;
	private DrawingModel model;
	private boolean executed;
	
	public BringToFrontCommand(Shape shape,DrawingModel model) {
		this.shape = shape;
		this.model = model;
		executed = true;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(!model.getShapes().isEmpty()) {
			Iterator<Shape> it1=model.getShapes().iterator();
			while(it1.hasNext()){
				Shape shape1=it1.next();
				if(shape1.toString().equals(shape.toString())){
					int j = model.getShapes().indexOf(shape1);
					if(j!=model.getShapes().size()-1) {
						Collections.rotate(model.getShapes().subList(j, model.getShapes().size()), -1);
					} else {
						executed = false;
						JOptionPane.showMessageDialog(null,"Already in front of others!");
					}
					break;
				}
			}
		}
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		if(!model.getShapes().isEmpty()) {
			Iterator<Shape> it1=model.getShapes().iterator();
			while(it1.hasNext()){
				Shape shape1=it1.next();
				if(shape1.toString().equals(shape.toString())){
					int j = model.getShapes().indexOf(shape1);
					if(j!=0) {
						Collections.rotate(model.getShapes().subList(0, j+1), 1);
					} else {
						executed = false;
						JOptionPane.showMessageDialog(null,"Already in back of others!");
					}
					break;
				}
			}
		}
	}

	@Override
	public String appendLog() {
		String text = "BRING TO FRONT shape: " + shape.getClass().getSimpleName() + " : " + shape.toString() + ":" +shape.getColor().getRGB();
		if(shape instanceof SurfaceShape){
			text = text + ":"+ ((SurfaceShape)shape).getInnerColor().getRGB() + "\n";
		} else {
			text = text + "\n";
		}
		return text;
	}

	public boolean isExecuted() {
		return executed;
	}

	public void setExecuted(boolean executed) {
		this.executed = executed;
	}


}
