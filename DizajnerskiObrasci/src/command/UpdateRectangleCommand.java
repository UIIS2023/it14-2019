package command;

import geometry.Rectangle;
import geometry.SurfaceShape;

public class UpdateRectangleCommand implements Command{
	
	private Rectangle oldState;
	private Rectangle newState;
	private Rectangle original;
	
	public UpdateRectangleCommand(Rectangle oldState, Rectangle newState){
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		original = oldState.clone();
		
		oldState.getUpperLeftPoint().setX(newState.getUpperLeftPoint().getX());
		oldState.getUpperLeftPoint().setY(newState.getUpperLeftPoint().getY());
		oldState.getUpperLeftPoint().setColor(newState.getUpperLeftPoint().getColor());
		oldState.setHeight(newState.getHeight());
		oldState.setWidth(newState.getWidth());
		oldState.setColor(newState.getColor());
		oldState.setInnerColor(newState.getInnerColor());
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		oldState.getUpperLeftPoint().setX(original.getUpperLeftPoint().getX());
		oldState.getUpperLeftPoint().setY(original.getUpperLeftPoint().getY());
		oldState.getUpperLeftPoint().setColor(original.getUpperLeftPoint().getColor());
		oldState.setHeight(original.getHeight());
		oldState.setWidth(original.getWidth());
		oldState.setColor(original.getColor());
		oldState.setInnerColor(original.getInnerColor());
		
	}

	@Override
	public String appendLog() {
		String text = "Modify rectangle from: " + original.toString() + ":" + original.getColor().getRGB()+ ":"+original.getInnerColor().getRGB()+"; to:" +oldState.toString()+ ":" + oldState.getColor().getRGB()+ ":"+oldState.getInnerColor().getRGB()+ "\n";
	return text;
	}
	
	

}
