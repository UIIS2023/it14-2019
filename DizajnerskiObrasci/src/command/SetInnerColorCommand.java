package command;

import java.awt.Color;

import geometry.Shape;
import geometry.SurfaceShape;

public class SetInnerColorCommand implements Command{

	private Color oldColor;
	private Color newColor;
	private Color original;
	private Shape shape;
	
	public SetInnerColorCommand(Color oldColor,Color newColor,Shape shape) {
		this.oldColor = oldColor;
		this.newColor = newColor;
		this.shape = shape;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		original = oldColor;
		oldColor = newColor;
		((SurfaceShape)shape).setInnerColor(oldColor);
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		oldColor = original;
		((SurfaceShape)shape).setInnerColor(oldColor);
	}

	@Override
	public String appendLog() {
		String text = "Set inner color from:"+original.getRGB()+"; to:" + oldColor.getRGB()+"; for shape:"+shape.getClass().getSimpleName()+ ":" +shape.toString()+":"+shape.getColor().getRGB()+"\n";
		return text;
	}

}
