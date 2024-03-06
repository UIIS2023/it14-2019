package strategy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import mvc.DrawingController;
import mvc.DrawingModel;

public class WholeDrawing implements SaveLoad{
	private DrawingModel model;

	@Override
	public void save(DrawingController controller) {
		// TODO Auto-generated method stub
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");   
		int r = fileChooser.showSaveDialog(controller.getFrame());

		if (r == JFileChooser.APPROVE_OPTION) {
			File newFile = fileChooser.getSelectedFile();
			
			//System.out.println("Save as file: " + newFile.getAbsolutePath());
			try {
				FileOutputStream fos = new FileOutputStream(newFile.getAbsolutePath());
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(controller.getModel());
				oos.close();
				fos.close();
				JOptionPane.showMessageDialog(controller.getFrame(),"Saved!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void load(DrawingController controller) {
		// TODO Auto-generated method stub
		JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

		// invoke the showsOpenDialog function to show the save dialog
		int r = fileChooser.showOpenDialog(controller.getFrame());

		// if the user selects a file
		if (r == JFileChooser.APPROVE_OPTION)
		{	
			File newFile = fileChooser.getSelectedFile();
			
			try {
				FileInputStream fis = new FileInputStream(newFile.getAbsolutePath());
				ObjectInputStream ois = new ObjectInputStream(fis);
				try {
					model = (DrawingModel) ois.readObject();
					controller.setModel(model);
					ois.close();
					fis.close();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} 
	}

}
