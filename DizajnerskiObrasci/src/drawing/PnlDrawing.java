package drawing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import geometry.Line;
import geometry.Point;
import geometry.Shape;

public class PnlDrawing extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private Point startPoint;
	private DrawingFrame df;
	private Shape selected;
	
	public PnlDrawing(DrawingFrame df) {
		this.df = df;
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				thisMouseClicked(arg0); 
			}
		});
		
	}
	
	protected void thisMouseClicked (MouseEvent e) {
		Shape newShape = null;
		if(df.getTglbtnSelection().isSelected()) {
			selected = null;
			Iterator<Shape> it = shapes.iterator();
			while(it.hasNext()){
				Shape shape = it.next();
				shape.setSelected(false);
				if(shape.contains(e.getX(), e.getY())) {
					selected = shape;
				}
			}
			if(selected!=null) {
				selected.setSelected(true);
				repaint();
			}
		} else if(df.getTglbtnPoint().isSelected()) {
			newShape = new Point(e.getX(),e.getY(),false,Color.BLACK);
		}else if (df.getTglbtnLine().isSelected()) {
			if(startPoint==null){
				startPoint=new Point(e.getX(), e.getY());
			}else {
				newShape= new Line(startPoint, new Point(e.getX(),e.getY()),false,Color.BLACK);
				startPoint=null;
			}
		} else if(df.getTglbtnRectangle().isSelected()) {
			DlgRectangle dlgRec = new DlgRectangle(false);
			dlgRec.setModal(true);
			dlgRec.getTextField_X().setText(String.valueOf(e.getX()));
			dlgRec.getTextField_Y().setText(String.valueOf(e.getY()));
			dlgRec.setVisible(true);
			if(dlgRec.isOk) {
				newShape = dlgRec.getRectangle();
			}
		}else if(df.getTglbtnCircle().isSelected()){
			DlgCircle dlgC=new DlgCircle(false);
			dlgC.setModal(true);
			dlgC.getTextField_X().setText(String.valueOf(e.getX()));
			dlgC.getTextField_Y().setText(String.valueOf(e.getY()));
			dlgC.setVisible(true);
			if(dlgC.isOk) {
				newShape = dlgC.getCircle();
			}
		} else if(df.getTglbtnDonut().isSelected()){
			DlgDonut dlgD=new DlgDonut(false);
			dlgD.setModal(true);
			dlgD.getTextField_X().setText(String.valueOf(e.getX()));
			dlgD.getTextField_Y().setText(String.valueOf(e.getY()));
			dlgD.setVisible(true);
			if(dlgD.isOk) {
				newShape = dlgD.getDonut();
			}
		}
	
		if(newShape!=null) {
			shapes.add(newShape);
			repaint();
		}
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		Iterator<Shape> it = shapes.iterator();
		while(it.hasNext()) {
			it.next().draw(g);
		}
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}

	public Shape getSelected() {
		return selected;
	}

	public void setSelected(Shape selected) {
		this.selected = selected;
	}
	
	

}
