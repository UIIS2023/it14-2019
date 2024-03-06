package mvc;

import java.awt.Color;
import java.awt.TextArea;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import adapter.HexagonAdapter;
import command.AddShapeCommand;
import command.BringToBackCommand;
import command.BringToFrontCommand;
import command.Command;
import command.RemoveShapeCommand;
import command.SetColorCommand;
import command.SetInnerColorCommand;
import command.ToBackCommand;
import command.ToFrontCommand;
import command.UpdateCircleCommand;
import command.UpdateDonutCommand;
import command.UpdateHexagonCommand;
import command.UpdateLineCommand;
import command.UpdatePointCommand;
import command.UpdateRectangleCommand;
import drawing.DlgCircle;
import drawing.DlgDonut;
import drawing.DlgHexagon;
import drawing.DlgLine;
import drawing.DlgPoint;
import drawing.DlgRectangle;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import geometry.SurfaceShape;
import strategy.FromLogDrawing;
import strategy.SaveLoad;
import strategy.SaveLoadManager;
import strategy.WholeDrawing;

public class DrawingController{

	private DrawingModel model;
	private DrawingFrame frame;
	private Point startPoint;
	private boolean mod;
	private List<Shape> listShape;
	private PropertyChangeSupport support;
	private Stack<Command> commandList;
	private int pointer;
	private SaveLoad wholeDrawing;
	private SaveLoad fromLogDrawing;
	private SaveLoadManager manager;
	private SaveLoadManager manager1;
	private String loadedText;
	private int nextCounter;
	

	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model=model;
		this.frame=frame;
		listShape=new ArrayList<Shape>();
		commandList = new Stack<Command>();
		support = new PropertyChangeSupport(this);
		this.addPropertyChangeListener(frame);
		checkSize(listShape);
		pointer = -1;
		wholeDrawing = new WholeDrawing();
		manager = new SaveLoadManager(wholeDrawing);
		fromLogDrawing = new FromLogDrawing();
		manager1 = new SaveLoadManager(fromLogDrawing);
		loadedText = "";
	}

	public void checkSize(List<Shape> list) {
		if(list.size()==0) {
			support.firePropertyChange("noSelected", true, false);
		}else if (list.size()==1) {
			support.firePropertyChange("oneSelected", true, false);
			Shape selected=listShape.get(0);
		
			if(selected instanceof Point) {
				frame.getBtnColorInner().setBackground(Color.WHITE);
				frame.getBtnColorOuter().setBackground(selected.getColor());
				frame.getBtnColorInner().setEnabled(false);
			} else if (selected instanceof Line) {
				frame.getBtnColorInner().setBackground(Color.WHITE);
				frame.getBtnColorOuter().setBackground(selected.getColor());
				frame.getBtnColorInner().setEnabled(false);
			} else{
				frame.getBtnColorInner().setBackground(((SurfaceShape)selected).getInnerColor());
				frame.getBtnColorOuter().setBackground(selected.getColor());
				frame.getBtnColorInner().setEnabled(true);
			}
			frame.repaint();
			
		} else {
			support.firePropertyChange("moreSelected", true, false);
		}
		
		
	}

	public void mouseClicked(MouseEvent e) {
		Shape newShape = null;
		
		if(frame.getTglbtnSelection().isSelected()) {
			int last = model.getShapes().size() - 1;
		    boolean k = false;
            for(int i=last; i >=0 ; i--) {
                Shape shape = model.getShapes().get(i);
                if(shape.contains(e.getX(), e.getY())) {
                	k=true;
                    Iterator<Shape> it2 = listShape.iterator();
					boolean alreadySelected = false;
                    while(it2.hasNext()){
						Shape shape1=it2.next();
                        if(shape1==shape){
                        	alreadySelected = true;
							model.setSelected(shape1);
							model.getSelected().setSelected(false);
							listShape.remove(shape1);
							checkSize(listShape);
							frame.repaint();
							return;
						}
                    }
                    if(!alreadySelected) {
                    	
                        listShape.add(shape);
                        model.setSelected(shape);
                        model.getSelected().setSelected(true);
                        checkSize(listShape);
                        frame.repaint();
                        break;  
                    }
                }
            }
             if(!k){
            	 if(!listShape.isEmpty()) {
     				Iterator<Shape> it1=listShape.iterator();
     				while(it1.hasNext()){
     					Shape shape2=it1.next();
     					model.setSelected(shape2);
     					model.getSelected().setSelected(false);
     				}
     				listShape.clear();
     				checkSize(listShape);
     				frame.repaint();
     				
     			}
             }
            
           
         }else if(frame.getTglbtnPoint().isSelected()) {
			newShape = new Point(e.getX(),e.getY(),false,frame.getBtnColorOuter().getBackground());
		}else if (frame.getTglbtnLine().isSelected()) {
			if(startPoint==null){
				startPoint=new Point(e.getX(), e.getY());
			}else {
				newShape= new Line(startPoint, new Point(e.getX(),e.getY()),false,frame.getBtnColorOuter().getBackground());
				startPoint=null;
			}
		} else if(frame.getTglbtnRectangle().isSelected()) {
			DlgRectangle dlgRec = new DlgRectangle(false);
			dlgRec.setModal(true);
			dlgRec.getTextField_X().setText(String.valueOf(e.getX()));
			dlgRec.getTextField_Y().setText(String.valueOf(e.getY()));
			dlgRec.setInnerColor(frame.getBtnColorInner().getBackground());
			dlgRec.setLineColor(frame.getBtnColorOuter().getBackground());
			dlgRec.setVisible(true);
			if(dlgRec.isOk) {
				newShape = dlgRec.getRectangle();
			}
		}else if(frame.getTglbtnCircle().isSelected()){
			DlgCircle dlgC=new DlgCircle(false);
			dlgC.setModal(true);
			dlgC.getTextField_X().setText(String.valueOf(e.getX()));
			dlgC.getTextField_Y().setText(String.valueOf(e.getY()));
			dlgC.setInnerColor(frame.getBtnColorInner().getBackground());
			dlgC.setLineColor(frame.getBtnColorOuter().getBackground());
			dlgC.setVisible(true);
			if(dlgC.isOk) {
				newShape = dlgC.getCircle();
			}
		} else if(frame.getTglbtnDonut().isSelected()){
			DlgDonut dlgD=new DlgDonut(false);
			dlgD.setModal(true);
			dlgD.getTextField_X().setText(String.valueOf(e.getX()));
			dlgD.getTextField_Y().setText(String.valueOf(e.getY()));
			dlgD.setInnerColor(frame.getBtnColorInner().getBackground());
			dlgD.setLineColor(frame.getBtnColorOuter().getBackground());
			dlgD.setVisible(true);
			if(dlgD.isOk) {
				newShape = dlgD.getDonut();
			}
		} else if(frame.getTglbtnHexagon().isSelected()){
			DlgHexagon dlgH=new DlgHexagon(false);
			dlgH.setModal(true);
			dlgH.getTextField_X().setText(String.valueOf(e.getX()));
			dlgH.getTextField_Y().setText(String.valueOf(e.getY()));
			dlgH.setInnerColor(frame.getBtnColorInner().getBackground());
			dlgH.setLineColor(frame.getBtnColorOuter().getBackground());
			dlgH.setVisible(true);
			if(dlgH.isOk) {
				newShape = dlgH.getHex();
			}
		}

		if(newShape!=null) {
			AddShapeCommand addShapeCommand = new AddShapeCommand(newShape,model);
			executeCommand(addShapeCommand);
			logging(addShapeCommand.appendLog());
			//checkStack();
			frame.repaint();
		}

	}

	public void setOuterColor(Color color) {
		Shape selected=listShape.get(0);
		SetColorCommand setColorCommand = new SetColorCommand(selected.getColor(),color,selected);
		executeCommand(setColorCommand);
		logging(setColorCommand.appendLog());
		
	}


	public void setInnerColor(Color color) {
		Shape selected=listShape.get(0);
		SetInnerColorCommand setInnerColorCommand = new SetInnerColorCommand(((SurfaceShape)selected).getInnerColor(),color,selected);
		executeCommand(setInnerColorCommand);
		logging(setInnerColorCommand.appendLog());
		
	}

	protected void modify(){
		
		Shape selected=listShape.get(0);
		if(model.getSelected()!=null){
			if(model.getSelected() instanceof Point){
				Point point=(Point)selected;

				DlgPoint dlgP=new DlgPoint();
				
				dlgP.setModal(true);
				dlgP.getTextField_X().setText(String.valueOf(point.getX()));
				dlgP.getTextField_Y().setText(String.valueOf(point.getY()));
				dlgP.setLineColor(point.getColor());
				dlgP.setVisible(true);

				if(dlgP.isOk){
					Point newPoint = dlgP.getPoint();
					UpdatePointCommand updatePointCommand = new UpdatePointCommand(point,newPoint);
					executeCommand(updatePointCommand);
					logging(updatePointCommand.appendLog());
					//model.getSelected().setSelected(false);
					frame.repaint();
					frame.revalidate();
				}
			} else if(selected instanceof Line) {
				Line line = (Line)selected;

				DlgLine dlgL = new DlgLine();
				
				dlgL.setModal(true);

				dlgL.getTextField_SPX().setText(String.valueOf(line.getStartPoint().getX()));
				dlgL.getTextField_SPY().setText(String.valueOf(line.getStartPoint().getY()));			 
				dlgL.getTextField_EPX().setText(String.valueOf(line.getEndPoint().getX()));		
				dlgL.getTextField_EPY().setText(String.valueOf(line.getEndPoint().getY()));
				dlgL.setLineColor(line.getColor());

				dlgL.setVisible(true);

				if(dlgL.isOk){
					Line newLine = dlgL.getLine();
					UpdateLineCommand updateLineCommand = new UpdateLineCommand(line,newLine);
					executeCommand(updateLineCommand);
					logging(updateLineCommand.appendLog());
					//model.getSelected().setSelected(false);
					frame.repaint();
					frame.revalidate();
				}
			}else if (selected instanceof Rectangle){

				mod=true;
				Rectangle rec= (Rectangle)selected;

				DlgRectangle dlgR=new DlgRectangle(mod);
				dlgR.setTitle("Modify rectangle");
				
				dlgR.setModal(true);
				dlgR.getTextField_X().setText(String.valueOf(rec.getUpperLeftPoint().getX()));
				dlgR.getTextField_Y().setText(String.valueOf(rec.getUpperLeftPoint().getY()));
				dlgR.getTextField_Width().setText(String.valueOf(rec.getWidth()));
				dlgR.getTextField_Height().setText(String.valueOf(rec.getHeight()));
				dlgR.setLineColor(rec.getColor());
				dlgR.setInnerColor(rec.getInnerColor());
				dlgR.setVisible(true);

				if(dlgR.isOk){
					Rectangle newRectangle = dlgR.getRectangle();
					UpdateRectangleCommand updateRectangleCommand = new UpdateRectangleCommand(rec,newRectangle);
					executeCommand(updateRectangleCommand);
					logging(updateRectangleCommand.appendLog());
				//	model.getSelected().setSelected(false);
					frame.repaint();
					frame.revalidate();


				}
				mod=false;

			}else if(selected instanceof Donut){
				mod=true;
				Donut donut=(Donut)selected;
				DlgDonut dlgD= new DlgDonut(mod);
				dlgD.setTitle("Modify donut");
				
				dlgD.setModal(true);
				dlgD.getTextField_X().setText(String.valueOf(donut.getCenter().getX()));
				dlgD.getTextField_Y().setText(String.valueOf(donut.getCenter().getY()));
				dlgD.getTextField_Radius().setText(String.valueOf(donut.getRadius()));
				dlgD.getTextField_InnerRadius().setText(String.valueOf(donut.getInnerRadius()));
				dlgD.setLineColor(donut.getColor());
				dlgD.setInnerColor(donut.getInnerColor());
				dlgD.setVisible(true);

				if(dlgD.isOk){
					Donut newDonut = dlgD.getDonut();
					UpdateDonutCommand updateDonutCommand = new UpdateDonutCommand(donut,newDonut);
					executeCommand(updateDonutCommand);
					logging(updateDonutCommand.appendLog());
					//model.getSelected().setSelected(false);
					frame.repaint();
					frame.revalidate();
				}
				mod=false;

			}else if(selected instanceof Circle){
				mod=true;
				Circle circle=(Circle)selected;
				DlgCircle dlgC=new DlgCircle(mod);
				dlgC.setTitle("Modify circle");
				
				dlgC.setModal(true);
				dlgC.getTextField_X().setText(String.valueOf(circle.getCenter().getX()));
				dlgC.getTextField_Y().setText(String.valueOf(circle.getCenter().getY()));
				dlgC.getTextField_Radius().setText(String.valueOf(circle.getRadius()));
				dlgC.setLineColor(circle.getColor());
				dlgC.setInnerColor(circle.getInnerColor());
				dlgC.setVisible(true);

				if(dlgC.isOk){
					Circle newCircle = dlgC.getCircle();
					UpdateCircleCommand updateCircleCommand = new UpdateCircleCommand(circle,newCircle);
					executeCommand(updateCircleCommand);
					logging(updateCircleCommand.appendLog());
					//model.getSelected().setSelected(false);
					frame.repaint();
					frame.revalidate();


				}
				mod=false;			
			}else if(selected instanceof HexagonAdapter){
				mod=true;
				HexagonAdapter hex=(HexagonAdapter)selected;
				DlgHexagon dlgH=new DlgHexagon(mod);
				dlgH.setTitle("Modify Hexagon");
				
				dlgH.setModal(true);
				dlgH.getTextField_X().setText(String.valueOf(hex.getHex().getX()));
				dlgH.getTextField_Y().setText(String.valueOf(hex.getHex().getY()));
				dlgH.getTextField_R().setText(String.valueOf(hex.getHex().getR()));
				dlgH.setLineColor(hex.getHex().getBorderColor());
				dlgH.setInnerColor(hex.getHex().getAreaColor());
				dlgH.setVisible(true);

				if(dlgH.isOk){
					HexagonAdapter newHex = dlgH.getHex();
					UpdateHexagonCommand updateHexagonCommand = new UpdateHexagonCommand(hex,newHex);
					executeCommand(updateHexagonCommand);
					logging(updateHexagonCommand.appendLog());
				//	model.getSelected().setSelected(false);
					frame.repaint();
					frame.revalidate();


				}
				mod=false;			
			}
			
			checkSize(listShape);
			frame.repaint();
		}
	}

	protected void delete() {
		int reply = JOptionPane.showConfirmDialog(frame, "Are you sure?", "Delete shape(s)", JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
			if(!listShape.isEmpty()) {
				Iterator<Shape> it1=listShape.iterator();
				while(it1.hasNext()){
					Shape shape=it1.next();
					RemoveShapeCommand removeShapeCommand = new RemoveShapeCommand(shape,model);
					executeCommand(removeShapeCommand);
					logging(removeShapeCommand.appendLog());
					it1.remove();
				}
			}
			checkSize(listShape);
			frame.getTglbtnSelection().setSelected(false);
			frame.repaint();
		}
	}

	protected void toFront() {
		Shape selected=model.getSelected();
		ToFrontCommand toFrontCommand = new ToFrontCommand(selected,model);
		executeCommand(toFrontCommand);
		if(toFrontCommand.isExecuted())
			logging(toFrontCommand.appendLog());
		frame.repaint();
	}

	protected void toBack() {
		Shape selected=model.getSelected();
		ToBackCommand toBackCommand = new ToBackCommand(selected,model);
		executeCommand(toBackCommand);
		if(toBackCommand.isExecuted())
			logging(toBackCommand.appendLog());
		frame.repaint();
	}

	protected void bringToFront() {
		Shape selected=model.getSelected();
		BringToFrontCommand bringToFrontCommand = new BringToFrontCommand(selected, model);
		executeCommand(bringToFrontCommand);
		if(bringToFrontCommand.isExecuted()) {
			logging(bringToFrontCommand.appendLog());
		}
		frame.repaint();
	}

	protected void bringToBack() {
		Shape selected=model.getSelected();
		BringToBackCommand bringToBackCommand = new BringToBackCommand(selected, model);
		executeCommand(bringToBackCommand);
		if(bringToBackCommand.isExecuted()) {
			logging(bringToBackCommand.appendLog());
		}
		frame.repaint();
	}

	protected void checkStack() {
		Command y = (Command) commandList.peek();
		System.out.println("Last command = " + y.appendLog());
	}

	protected void executeCommand(Command command) {
		removeCommands(pointer);
		command.execute();
		commandList.push(command);
		pointer++;
	}

	protected void saveDraw(){
		manager.save(this);
	}

	protected void loadDraw() {
		manager.load(this);
		frame.getView().setModel(model);
		pointer = -1;
		removeCommands(pointer);
		frame.repaint();
	}

	protected void saveLog() {
		manager1.save(this);
	}

	protected void loadLog() {
		manager1.load(this);
		System.out.println(loadedText);
		nextCounter = 0;
		frame.getBtnUndo().setEnabled(false);
		frame.getBtnRedo().setEnabled(false);
		frame.getBtnLoadDraw().setEnabled(false);
		frame.getBtnLoadLog().setEnabled(false);
		frame.getBtnSaveDraw().setEnabled(false);
		frame.getBtnSaveLog().setEnabled(false);
		frame.getBtnNext().setEnabled(true);
	}

	protected void next() {

		String[] komande = loadedText.split("\n");
		Command newCommand = null;
		if(nextCounter<komande.length) {
			if(komande[nextCounter].contains("Add shape") || komande[nextCounter].contains("Remove shape") || komande[nextCounter].contains("BRING TO BACK") || komande[nextCounter].contains("BRING TO FRONT") || komande[nextCounter].contains("TO BACK COMMAND") || komande[nextCounter].contains("TO FRONT COMMAND")) {
				String[] komanda = komande[nextCounter].split(":");
				String shape = komanda[1];
				String value = komanda[2];
				String colorInt = komanda[3];
				Color color = new Color(Integer.parseInt(colorInt));
				Shape newShape = null;
				
				if(shape.contains("Point")) {
					String[] koordinate = value.split("[(,)]+");			
					newShape = new Point(Integer.parseInt(koordinate[1]),Integer.parseInt(koordinate[2]),false,color);
					
				} else if(shape.contains("Line")) {
					String[] koordinate = value.split("[(,)]+");
					newShape = new Line(new Point(Integer.parseInt(koordinate[1]),Integer.parseInt(koordinate[2])),new Point(Integer.parseInt(koordinate[4]),Integer.parseInt(koordinate[5])),false);
					newShape.setColor(color);
				} else if(shape.contains("Rectangle")) {
					String colorInnerInt = komanda[4];
					Color innerColor = new Color(Integer.parseInt(colorInnerInt));
					String[] koordinate = value.split("[(,)=]+");
					newShape = new Rectangle(new Point(Integer.parseInt(koordinate[1]),Integer.parseInt(koordinate[2])),Integer.parseInt(koordinate[4]),Integer.parseInt(koordinate[6]),false,color,innerColor);
				} else if (shape.contains("Circle")){
					String colorInnerInt = komanda[4];
					Color innerColor = new Color(Integer.parseInt(colorInnerInt));
					String[] koordinate = value.split("[(,)=]+");
					newShape = new Circle(new Point(Integer.parseInt(koordinate[1]),Integer.parseInt(koordinate[2])),Integer.parseInt(koordinate[4]),false,color,innerColor);
				} else if (shape.contains("Donut")){
					String colorInnerInt = komanda[4];
					Color innerColor = new Color(Integer.parseInt(colorInnerInt));
					String[] koordinate = value.split("[(,)=]+");
					newShape = new Donut(new Point(Integer.parseInt(koordinate[1]),Integer.parseInt(koordinate[2])),Integer.parseInt(koordinate[4]),Integer.parseInt(koordinate[6]),false,color,innerColor);
				} else if (shape.contains("Hexagon")){
					String colorInnerInt = komanda[4];
					Color innerColor = new Color(Integer.parseInt(colorInnerInt));
					String[] koordinate = value.split("[(,)=]+");
					newShape = new HexagonAdapter(new Point(Integer.parseInt(koordinate[1]),Integer.parseInt(koordinate[2])),Integer.parseInt(koordinate[4]),false,color,innerColor);
				}
				if(newShape!=null) {
					if (komanda[0].contains("Add")){
						newCommand = new AddShapeCommand(newShape,model);
					} else if (komanda[0].contains("Remove")) {
						newCommand = new RemoveShapeCommand(newShape,model);
					}else if(komanda[0].contains("BRING TO BACK")){
						newCommand = new BringToBackCommand(newShape,model);
					} else if (komanda[0].contains("BRING TO FRONT")){
						newCommand = new BringToFrontCommand(newShape,model);
					}else if (komanda[0].contains("TO BACK COMMAND")) {
						newCommand = new ToBackCommand(newShape,model);
					} else if (komanda[0].contains("TO FRONT COMMAND")) {
						newCommand = new ToFrontCommand(newShape,model);
					}
					executeCommand(newCommand);
					logging(newCommand.appendLog());
				}
				frame.repaint();
			}
			else if (komande[nextCounter].contains("Modify point")){
				String[] komanda = komande[nextCounter].split("[:;]+");
				String value1 = komanda[1];
				String value2 = komanda[4];
				String colorInt = komanda[2];
				String colorInt2 = komanda[5];
				Color color = new Color(Integer.parseInt(colorInt));
				Color color2 = new Color(Integer.parseInt(colorInt2));
				String[] koordinate1 = value1.split("[(,)]+");
				String[] koordinate2 = value2.split("[(,)]+");
				Point newPoint = new Point(Integer.parseInt(koordinate2[1]),Integer.parseInt(koordinate2[2]),false,color2);
				Point oldPoint = new Point(Integer.parseInt(koordinate1[1]),Integer.parseInt(koordinate1[2]),false,color);
				Iterator<Shape> it1=model.getShapes().iterator();
				while(it1.hasNext()){
					Shape shape1=it1.next();
					if(shape1.toString().equals(oldPoint.toString())){ 
						oldPoint = (Point)shape1;
					}
				}
				Command newCommand1 = new UpdatePointCommand(oldPoint,newPoint);
				executeCommand(newCommand1);
				logging(newCommand1.appendLog());
				frame.repaint();
			}
			else if (komande[nextCounter].contains("Modify line")){
				String[] komanda = komande[nextCounter].split("[:;]+");
				String value1 = komanda[1];
				String value2 = komanda[4];
				String colorInt = komanda[2];
				String colorInt2 = komanda[5];
				Color color = new Color(Integer.parseInt(colorInt));
				Color color2 = new Color(Integer.parseInt(colorInt2));
				String[] koordinate1 = value1.split("[(,)]+");
				String[] koordinate2 = value2.split("[(,)]+");
				Line newLine = new Line(new Point(Integer.parseInt(koordinate2[1]),Integer.parseInt(koordinate2[2])),new Point(Integer.parseInt(koordinate2[4]),Integer.parseInt(koordinate2[5])),false,color2);
				Line oldLine= new Line(new Point(Integer.parseInt(koordinate1[1]),Integer.parseInt(koordinate1[2])),new Point(Integer.parseInt(koordinate1[4]),Integer.parseInt(koordinate1[5])),false,color);
				Iterator<Shape> it1=model.getShapes().iterator();
				while(it1.hasNext()){
					Shape shape1=it1.next();
					if(shape1.toString().equals(oldLine.toString())){ 
						oldLine = (Line)shape1;
					}
				}
				Command newCommand1 = new UpdateLineCommand(oldLine,newLine);
				executeCommand(newCommand1);
				logging(newCommand1.appendLog());
				frame.repaint();
			} else if (komande[nextCounter].contains("Modify rectangle")){
				String[] komanda = komande[nextCounter].split("[:;]+");
				String value1 = komanda[1];
				String value2 = komanda[5];
				String colorInt = komanda[2];
				String innerColorInt = komanda[3];
				String colorInt2 = komanda[6];
				String innerColorInt2 = komanda[7];
				Color color = new Color(Integer.parseInt(colorInt));
				Color color2 = new Color(Integer.parseInt(colorInt2));
				Color colorInner = new Color(Integer.parseInt(innerColorInt));
				Color colorInner2 = new Color(Integer.parseInt(innerColorInt2));
				String[] koordinate1 = value1.split("[(,)=]+");
				String[] koordinate2 = value2.split("[(,)=]+");
				Rectangle oldRec = new Rectangle(new Point(Integer.parseInt(koordinate1[1]),Integer.parseInt(koordinate1[2])),Integer.parseInt(koordinate1[4]),Integer.parseInt(koordinate1[6]),false,color,colorInner);
				Rectangle newRec= new Rectangle(new Point(Integer.parseInt(koordinate2[1]),Integer.parseInt(koordinate2[2])),Integer.parseInt(koordinate2[4]),Integer.parseInt(koordinate2[6]),false,color2,colorInner2);
				Iterator<Shape> it1=model.getShapes().iterator();
				while(it1.hasNext()){
					Shape shape1=it1.next();
					if(shape1.toString().equals(oldRec.toString())){ 
						oldRec = (Rectangle)shape1;
					}
				}
				Command newCommand1 = new UpdateRectangleCommand(oldRec,newRec);
				executeCommand(newCommand1);
				logging(newCommand1.appendLog());
				frame.repaint();
			}
			else if (komande[nextCounter].contains("Modify circle")){
				String[] komanda = komande[nextCounter].split("[:;]+");
				String value1 = komanda[1];
				String value2 = komanda[5];
				String colorInt = komanda[2];
				String innerColorInt = komanda[3];
				String colorInt2 = komanda[6];
				String innerColorInt2 = komanda[7];
				Color color = new Color(Integer.parseInt(colorInt));
				Color color2 = new Color(Integer.parseInt(colorInt2));
				Color colorInner = new Color(Integer.parseInt(innerColorInt));
				Color colorInner2 = new Color(Integer.parseInt(innerColorInt2));
				String[] koordinate1 = value1.split("[(,)=]+");
				String[] koordinate2 = value2.split("[(,)=]+");
				Circle oldCircle = new Circle(new Point(Integer.parseInt(koordinate1[1]),Integer.parseInt(koordinate1[2])),Integer.parseInt(koordinate1[4]),false,color,colorInner);
				Circle newCircle= new Circle(new Point(Integer.parseInt(koordinate2[1]),Integer.parseInt(koordinate2[2])),Integer.parseInt(koordinate2[4]),false,color2,colorInner2);
				Iterator<Shape> it1=model.getShapes().iterator();
				while(it1.hasNext()){
					Shape shape1=it1.next();
					if(shape1.toString().equals(oldCircle.toString())){ 
						oldCircle = (Circle)shape1;
					}
				}
				Command newCommand1 = new UpdateCircleCommand(oldCircle,newCircle);
				executeCommand(newCommand1);
				logging(newCommand1.appendLog());
				frame.repaint();
			}
			else if (komande[nextCounter].contains("Modify donut")){
				String[] komanda = komande[nextCounter].split("[:;]+");
				String value1 = komanda[1];
				String value2 = komanda[5];
				String colorInt = komanda[2];
				String innerColorInt = komanda[3];
				String colorInt2 = komanda[6];
				String innerColorInt2 = komanda[7];
				Color color = new Color(Integer.parseInt(colorInt));
				Color color2 = new Color(Integer.parseInt(colorInt2));
				Color colorInner = new Color(Integer.parseInt(innerColorInt));
				Color colorInner2 = new Color(Integer.parseInt(innerColorInt2));
				String[] koordinate1 = value1.split("[(,)=]+");
				String[] koordinate2 = value2.split("[(,)=]+");
				Donut oldDonut = new Donut(new Point(Integer.parseInt(koordinate1[1]),Integer.parseInt(koordinate1[2])),Integer.parseInt(koordinate1[4]),Integer.parseInt(koordinate1[6]),false,color,colorInner);
				Donut newDonut= new Donut(new Point(Integer.parseInt(koordinate2[1]),Integer.parseInt(koordinate2[2])),Integer.parseInt(koordinate2[4]),Integer.parseInt(koordinate2[6]),false,color2,colorInner2);
				Iterator<Shape> it1=model.getShapes().iterator();
				while(it1.hasNext()){
					Shape shape1=it1.next();
					if(shape1.toString().equals(oldDonut.toString())){ 
						oldDonut = (Donut)shape1;
					}
				}
				Command newCommand1 = new UpdateDonutCommand(oldDonut,newDonut);
				executeCommand(newCommand1);
				logging(newCommand1.appendLog());
				frame.repaint();
			}
			else if (komande[nextCounter].contains("Modify hexagon")){
				String[] komanda = komande[nextCounter].split("[:;]+");
				String value1 = komanda[1];
				String value2 = komanda[5];
				String colorInt = komanda[2];
				String innerColorInt = komanda[3];
				String colorInt2 = komanda[6];
				String innerColorInt2 = komanda[7];
				Color color = new Color(Integer.parseInt(colorInt));
				Color color2 = new Color(Integer.parseInt(colorInt2));
				Color colorInner = new Color(Integer.parseInt(innerColorInt));
				Color colorInner2 = new Color(Integer.parseInt(innerColorInt2));
				String[] koordinate1 = value1.split("[(,)=]+");
				String[] koordinate2 = value2.split("[(,)=]+");
				HexagonAdapter oldHexagon = new HexagonAdapter(new Point(Integer.parseInt(koordinate1[1]),Integer.parseInt(koordinate1[2])),Integer.parseInt(koordinate1[4]),false,color,colorInner);
				HexagonAdapter newHexagon= new HexagonAdapter(new Point(Integer.parseInt(koordinate2[1]),Integer.parseInt(koordinate2[2])),Integer.parseInt(koordinate2[4]),false,color2,colorInner2);
				Iterator<Shape> it1=model.getShapes().iterator();
				while(it1.hasNext()){
					Shape shape1=it1.next();
					if(shape1.toString().equals(oldHexagon.toString())){ 
						oldHexagon = (HexagonAdapter)shape1;
					}
				}
				Command newCommand1 = new UpdateHexagonCommand(oldHexagon,newHexagon);
				executeCommand(newCommand1);
				logging(newCommand1.appendLog());
				frame.repaint();
			}
			else if (komande[nextCounter].contains("Set outer color")){
				String[] komanda = komande[nextCounter].split("[:;]+");
				String value1 = komanda[6];
				String name = komanda[5];
				String colorInt = komanda[1];
				Color oldColor = new Color(Integer.parseInt(colorInt));
				String colorInt2 = komanda[3];
				Color newColor = new Color(Integer.parseInt(colorInt2));
				String innerColorInt;
				String[] koordinate1 = value1.split("[(,)=]+");
				Shape shape = null;
				if(name.equals("Point")){
					shape = new Point(Integer.parseInt(koordinate1[1]),Integer.parseInt(koordinate1[2]),false,oldColor);
				}else if (name.equals("Line")){
					shape = new Line(new Point(Integer.parseInt(koordinate1[1]),Integer.parseInt(koordinate1[2])),new Point(Integer.parseInt(koordinate1[4]),Integer.parseInt(koordinate1[5])),false,oldColor);
				} else if(name.equals("Rectangle")){
					innerColorInt = komanda[7];
					Color innerColor = new Color(Integer.parseInt(innerColorInt));
					shape = new Rectangle(new Point(Integer.parseInt(koordinate1[1]),Integer.parseInt(koordinate1[2])),Integer.parseInt(koordinate1[4]),Integer.parseInt(koordinate1[6]),false,oldColor,innerColor);
				} else if(name.equals("Circle")){
					innerColorInt = komanda[7];
					Color innerColor = new Color(Integer.parseInt(innerColorInt));
					shape = new Circle(new Point(Integer.parseInt(koordinate1[1]),Integer.parseInt(koordinate1[2])),Integer.parseInt(koordinate1[4]),false,oldColor,innerColor);
				} else if(name.equals("Donut")){
					innerColorInt = komanda[7];
					Color innerColor = new Color(Integer.parseInt(innerColorInt));
					shape = new Donut(new Point(Integer.parseInt(koordinate1[1]),Integer.parseInt(koordinate1[2])),Integer.parseInt(koordinate1[4]),Integer.parseInt(koordinate1[6]),false,oldColor,innerColor);
				} else if (name.equals("HexagonAdapter")){
					innerColorInt = komanda[7];
					Color innerColor = new Color(Integer.parseInt(innerColorInt));
					shape = new HexagonAdapter(new Point(Integer.parseInt(koordinate1[1]),Integer.parseInt(koordinate1[2])),Integer.parseInt(koordinate1[4]),false,oldColor,innerColor);
				}

				Iterator<Shape> it1=model.getShapes().iterator();
				while(it1.hasNext()){
					Shape shape1=it1.next();
					if(shape1.toString().equals(shape.toString())){ 
						shape=shape1;
					}
				}
				Command newCommand1 = new SetColorCommand(oldColor,newColor,shape);
				executeCommand(newCommand1);
				logging(newCommand1.appendLog());
				frame.repaint();
			}
			else if (komande[nextCounter].contains("Set inner color")){
				String[] komanda = komande[nextCounter].split("[:;]+");
				String value1 = komanda[6];
				String name = komanda[5];
				String colorInt = komanda[1];
				Color oldColor = new Color(Integer.parseInt(colorInt));
				String colorInt2 = komanda[3];
				Color newColor = new Color(Integer.parseInt(colorInt2));
				String outerColorInt = komanda[7];
				Color outerColor = new Color(Integer.parseInt(outerColorInt));
				String[] koordinate1 = value1.split("[(,)=]+");
				Shape shape = null;
				if(name.equals("Rectangle")){
					shape = new Rectangle(new Point(Integer.parseInt(koordinate1[1]),Integer.parseInt(koordinate1[2])),Integer.parseInt(koordinate1[4]),Integer.parseInt(koordinate1[6]),false,outerColor,oldColor);
				} else if(name.equals("Circle")){
					shape = new Circle(new Point(Integer.parseInt(koordinate1[1]),Integer.parseInt(koordinate1[2])),Integer.parseInt(koordinate1[4]),false,outerColor,oldColor);
				} else if(name.equals("Donut")){
					shape = new Donut(new Point(Integer.parseInt(koordinate1[1]),Integer.parseInt(koordinate1[2])),Integer.parseInt(koordinate1[4]),Integer.parseInt(koordinate1[6]),false,outerColor,oldColor);
				} else if (name.equals("HexagonAdapter")){
					shape = new HexagonAdapter(new Point(Integer.parseInt(koordinate1[1]),Integer.parseInt(koordinate1[2])),Integer.parseInt(koordinate1[4]),false,outerColor,oldColor);
				}

				Iterator<Shape> it1=model.getShapes().iterator();
				while(it1.hasNext()){
					Shape shape1=it1.next(); 
					if(shape1.toString().equals(shape.toString())){ 
						shape=shape1;
					}
				}
				Command newCommand1 = new SetInnerColorCommand(oldColor,newColor,shape);
				executeCommand(newCommand1);
				logging(newCommand1.appendLog());
				frame.repaint();
			} else if(komande[nextCounter].contains("UNDO")) {
				undo();
			} else if(komande[nextCounter].contains("REDO")) {
				redo();
			}
			nextCounter++;
			if(nextCounter >= komande.length)
			{
				frame.getBtnUndo().setEnabled(true);
				frame.getBtnRedo().setEnabled(true);
				frame.getBtnLoadDraw().setEnabled(true);
				frame.getBtnLoadLog().setEnabled(true);
				frame.getBtnSaveDraw().setEnabled(true);
				frame.getBtnSaveLog().setEnabled(true);
				frame.getBtnNext().setEnabled(false);
			}
		}
	}


	protected void logging(String commandLog) {
		frame.getTextArea().append(commandLog);
	}

	protected void undo() {

		listShape.clear();
		if(model.getSelected()!=null)
			model.getSelected().setSelected(false);
		checkSize(listShape);
		if(pointer >= 0) {
			Command command = commandList.get(pointer);
			command.unexecute();
			frame.repaint();
			pointer--;
			frame.getTextArea().append("Command UNDO \n");
		} else {
			JOptionPane.showMessageDialog(frame, "Already on the first command!");
		}

	}

	protected void redo()
	{
		listShape.clear();
		if(model.getSelected()!=null)
			model.getSelected().setSelected(false);
		checkSize(listShape);
		if(pointer == commandList.size() - 1) {
			JOptionPane.showMessageDialog(frame, "Already on the last command!");
			return;
		}
		pointer++;
		Command command = commandList.get(pointer);
		command.execute();
		frame.repaint();
		frame.getTextArea().append("Command REDO \n");
	}

	protected void removeCommands(int pointer)
	{
		if(commandList.size()<1)
			return;
		for(int i = commandList.size()-1; i > pointer; i--)
		{
			commandList.remove(i);
		}
	}


	public DrawingFrame getFrame() {
		return frame;
	}

	public void setFrame(DrawingFrame frame) {
		this.frame = frame;
	}

	public List<Shape> getListShape() {
		return listShape;
	}

	public void setListShape(List<Shape> listShape) {
		this.listShape = listShape;
	}

	public DrawingModel getModel() {
		return model;
	}

	public void setModel(DrawingModel model) {
		this.model = model;
	}

	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		support.addPropertyChangeListener(pcl);
	}

	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		support.removePropertyChangeListener(pcl);
	}

	public int getPointer() {
		return pointer;
	}

	public void setPointer(int pointer) {
		this.pointer = pointer;
	}

	public String getLoadedText() {
		return loadedText;
	}

	public void setLoadedText(String loadedText) {
		this.loadedText = loadedText;
	}

}
