package strategy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileSystemView;

import mvc.DrawingController;

public class FromLogDrawing implements SaveLoad{

	@Override
	public void save(DrawingController controller) {
		// TODO Auto-generated method stub
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");   
		int r = fileChooser.showSaveDialog(controller.getFrame());

		if (r == JFileChooser.APPROVE_OPTION) {
			File newFile = fileChooser.getSelectedFile();
			try {
				PrintWriter out = new PrintWriter(newFile);
				out.println(controller.getFrame().getTextArea().getText());
				JOptionPane.showMessageDialog(controller.getFrame(),"Saved!");
				out.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void load(DrawingController controller) {
		// TODO Auto-generated method stub
		String text = "";
		JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

		// invoke the showsOpenDialog function to show the save dialog
		int r = fileChooser.showOpenDialog(controller.getFrame());

		// if the user selects a file
		if (r == JFileChooser.APPROVE_OPTION)
		{	
			File newFile = fileChooser.getSelectedFile();
			try {
				BufferedReader br = new BufferedReader(new FileReader(newFile));
				String st;
				while ((st = br.readLine()) != null){
					text = text + st;
					text = text + "\n";
				}
				controller.setLoadedText(text);
				br.close();
			}
			catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} 
	}

}
