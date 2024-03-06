package command;

import geometry.Circle;

public class UpdateCircleCommand implements Command {
	
	private Circle oldState;
	private Circle newState;
	private Circle original;
	
	public UpdateCircleCommand(Circle oldState,Circle newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		original = oldState.clone();
		//System.out.println("Original color execute: "+ original.getColor());
		oldState.getCenter().setX(newState.getCenter().getX());
		oldState.getCenter().setY(newState.getCenter().getY());
		oldState.getCenter().setColor(newState.getCenter().getColor());
		oldState.setRadius(newState.getRadius());
		oldState.setColor(newState.getColor());
		oldState.setInnerColor(newState.getInnerColor());		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		//System.out.println("Original color unexecute: "+ original.getColor());
		oldState.getCenter().setX(original.getCenter().getX());
		oldState.getCenter().setY(original.getCenter().getY());
		oldState.getCenter().setColor(original.getCenter().getColor());
		oldState.setRadius(original.getRadius());
		oldState.setColor(original.getColor());
		oldState.setInnerColor(original.getInnerColor());
		
	}

	@Override
	public String appendLog() {
		String text = "Modify circle from: " + original.toString() + ":" + original.getColor().getRGB()+ ":"+original.getInnerColor().getRGB()+"; to:" +oldState.toString()+ ":" + oldState.getColor().getRGB()+ ":"+oldState.getInnerColor().getRGB()+"\n";
		return text;
	}
	
	
	

}
