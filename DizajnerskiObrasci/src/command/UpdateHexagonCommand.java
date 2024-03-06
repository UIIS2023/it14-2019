package command;

import adapter.HexagonAdapter;

public class UpdateHexagonCommand implements Command{
	
	private HexagonAdapter oldState;
	private HexagonAdapter newState;
	private HexagonAdapter original;
	
	public UpdateHexagonCommand(HexagonAdapter oldState, HexagonAdapter newState){
		
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		
		original = oldState.clone();
		
		oldState.setHex(newState.getHex());
	
	}

	@Override
	public void unexecute() {
		
		oldState.setHex(original.getHex());
		
		
	}

	@Override
	public String appendLog() {
		
		String text = "Modify hexagon from: " + original.toString() + ":" + original.getColor().getRGB()+ ":"+original.getInnerColor().getRGB()+"; to:" +oldState.toString()+ ":" + oldState.getColor().getRGB()+ ":"+oldState.getInnerColor().getRGB()+"\n";
		return text;
	}
	
	

}
