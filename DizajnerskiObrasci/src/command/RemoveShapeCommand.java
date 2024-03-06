package command;

import geometry.Shape;
import geometry.SurfaceShape;
import mvc.DrawingModel;

public class RemoveShapeCommand implements Command{
	private Shape shape;
	private DrawingModel model;
	
	public RemoveShapeCommand(Shape shape, DrawingModel model) {
		this.shape = shape;
		this.model = model;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub	
		model.remove(shape);
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		model.add(shape);
	}

	@Override
	public String appendLog() {
		String text = "Remove shape: " + shape.getClass().getSimpleName() + " : " + shape.toString() + ":" +shape.getColor().getRGB();
		if(shape instanceof SurfaceShape){
			text = text + ":"+ ((SurfaceShape)shape).getInnerColor().getRGB() + "\n";
		} else {
			text = text + "\n";
		}
		return text;
	}

}
