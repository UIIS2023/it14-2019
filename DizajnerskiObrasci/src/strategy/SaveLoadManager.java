package strategy;


import mvc.DrawingController;


public class SaveLoadManager implements SaveLoad{
	private SaveLoad saveLoad;
	
	public SaveLoadManager(SaveLoad saveLoad) {
		this.saveLoad = saveLoad;
	}
	
	@Override
	public void save(DrawingController controller) {
		// TODO Auto-generated method stub
		saveLoad.save(controller);
	}

	@Override
	public void load(DrawingController controller) {
		// TODO Auto-generated method stub
		saveLoad.load(controller);
	}

}
