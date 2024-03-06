package command;

import geometry.Donut;

public class UpdateDonutCommand implements Command {
	
	private Donut oldState;
	private Donut newState;
	private Donut original;
	
	public UpdateDonutCommand(Donut oldState, Donut newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		original = oldState.clone();
		
		oldState.getCenter().setX(newState.getCenter().getX());
		oldState.getCenter().setY(newState.getCenter().getY());
		oldState.getCenter().setColor(newState.getCenter().getColor());
		oldState.setRadius(newState.getRadius());
		oldState.setInnerRadius(newState.getInnerRadius());
		oldState.setColor(newState.getColor());
		oldState.setInnerColor(newState.getInnerColor());
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		oldState.getCenter().setX(original.getCenter().getX());
		oldState.getCenter().setY(original.getCenter().getY());
		oldState.getCenter().setColor(original.getCenter().getColor());
		oldState.setRadius(original.getRadius());
		oldState.setInnerRadius(original.getInnerRadius());
		oldState.setColor(original.getColor());
		oldState.setInnerColor(original.getInnerColor());	
	}

	@Override
	public String appendLog() {
		String text = "Modify donut from: " + original.toString() + ":" + original.getColor().getRGB()+ ":"+original.getInnerColor().getRGB()+"; to:" +oldState.toString()+ ":" + oldState.getColor().getRGB()+ ":"+oldState.getInnerColor().getRGB()+"\n";
		return text;
	}
}
