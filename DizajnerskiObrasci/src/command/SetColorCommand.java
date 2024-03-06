package command;

import java.awt.Color;

import geometry.Shape;
import geometry.SurfaceShape;

public class SetColorCommand implements Command{

	private Color oldColor;
	private Color newColor;
	private Color original;
	private Shape shape;
	
	public SetColorCommand(Color oldColor,Color newColor,Shape shape) {
		this.oldColor = oldColor;
		this.newColor = newColor;
		this.shape = shape;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		original = oldColor;
		oldColor = newColor;
		shape.setColor(oldColor);
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		oldColor = original;
		shape.setColor(oldColor);
	}

	@Override
	public String appendLog() {
		// TODO Auto-generated method stub
		String text = "Set outer color from:"+original.getRGB()+"; to:" + oldColor.getRGB()+"; for shape:"+shape.getClass().getSimpleName()+ ":" +shape.toString();
		if(shape instanceof SurfaceShape){
			text = text + ":"+ ((SurfaceShape)shape).getInnerColor().getRGB() + "\n";
		} else {
			text = text + "\n";
		}
		return text;
	}

}
