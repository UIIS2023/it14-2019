package command;

import geometry.Point;
import geometry.SurfaceShape;

public class UpdatePointCommand implements Command{

	private Point oldState;
	private Point newState;
	private Point original;
	
	public UpdatePointCommand(Point oldState, Point newState) {
		this.newState = newState;
		this.oldState = oldState;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		original = oldState.clone();
		oldState.setX(newState.getX());
		oldState.setY(newState.getY());
		oldState.setColor(newState.getColor());
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		oldState.setX(original.getX());
		oldState.setY(original.getY());
		oldState.setColor(original.getColor());
	}

	@Override
	public String appendLog() {
		// TODO Auto-generated method stub
		String text = "Modify point from: " +original.toString()+":"+original.getColor().getRGB()+"; to: " +oldState.toString()+ ":" +oldState.getColor().getRGB()+"\n";
		
		//String text = "Modified point: " + oldState.toString() + "\n";
		return text;
	}

}
