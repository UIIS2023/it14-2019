package mvc;

public class DrawingApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DrawingModel model = new DrawingModel();
		DrawingFrame frame = new DrawingFrame();
		
		frame.getView().setModel(model);
		
		DrawingController controller = new DrawingController(model, frame);
		frame.setController(controller);
		frame.setVisible(true);
		
	}

}
