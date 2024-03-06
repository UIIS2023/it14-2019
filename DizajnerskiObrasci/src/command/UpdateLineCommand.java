package command;

import geometry.Line;

public class UpdateLineCommand implements Command{

	private Line oldState;
	private Line newState;
	private Line original;
	
	public UpdateLineCommand(Line oldState,Line newState) {
		this.oldState = oldState;
		this.newState = newState;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		original = oldState.clone();
		oldState.getStartPoint().setX(newState.getStartPoint().getX());
		oldState.getStartPoint().setY(newState.getStartPoint().getY());
		oldState.getEndPoint().setX(newState.getEndPoint().getX());
		oldState.getEndPoint().setY(newState.getEndPoint().getY());
		oldState.getStartPoint().setColor(newState.getStartPoint().getColor());
		oldState.getEndPoint().setColor(newState.getEndPoint().getColor());	
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		oldState.getStartPoint().setX(original.getStartPoint().getX());
		oldState.getStartPoint().setY(original.getStartPoint().getY());
		oldState.getEndPoint().setX(original.getEndPoint().getX());
		oldState.getEndPoint().setY(original.getEndPoint().getY());
		oldState.getStartPoint().setColor(original.getStartPoint().getColor());
		oldState.getEndPoint().setColor(original.getEndPoint().getColor());	
	}

	@Override
	public String appendLog() {
		String text = "Modify line from: " +original.toString()+":"+original.getColor().getRGB()+"; to: " +oldState.toString()+ ":" +oldState.getColor().getRGB()+"\n";
		return text;
	}

}
