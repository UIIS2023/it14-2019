package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

public class DrawingFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JToggleButton tglbtnSelection;
	private JToggleButton tglbtnPoint;
	private JToggleButton tglbtnLine;
	private JToggleButton tglbtnRectangle;
	private JToggleButton tglbtnCircle;
	private JToggleButton tglbtnDonut;
	private PnlDrawing pd=new PnlDrawing(this);
	private JButton btnModify;
	private JButton btnDelete;
	private boolean mod;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DrawingFrame frame = new DrawingFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DrawingFrame() {
		setTitle("IT 14-2019 Ijacic Irena");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel mainPanel = new JPanel(new BorderLayout());

		mainPanel.add(pd,BorderLayout.CENTER);




		JPanel northPanel = new JPanel();
		northPanel.setBackground(new Color(244, 164, 96));

		mainPanel.add(northPanel,BorderLayout.NORTH);		

		ButtonGroup bg = new ButtonGroup();
		tglbtnSelection = new JToggleButton("Selection");
		northPanel.add(tglbtnSelection);

		tglbtnPoint = new JToggleButton("Point");
		northPanel.add(tglbtnPoint);

		tglbtnLine = new JToggleButton("Line");
		northPanel.add(tglbtnLine);

		tglbtnRectangle = new JToggleButton("Rectangle");
		northPanel.add(tglbtnRectangle);

		tglbtnCircle = new JToggleButton("Circle");
		northPanel.add(tglbtnCircle);

		tglbtnDonut = new JToggleButton("Donut");
		northPanel.add(tglbtnDonut);



		bg.add(tglbtnSelection);
		bg.add(tglbtnPoint);
		bg.add(tglbtnLine);
		bg.add(tglbtnRectangle);
		bg.add(tglbtnCircle);
		bg.add(tglbtnDonut);
		
		btnModify = new JButton("Modify");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modify();
			}
		});
		northPanel.add(btnModify);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				delete();
			}
		});
		northPanel.add(btnDelete);
		getContentPane().add(mainPanel);

	}
	
	protected void delete() {
		Shape selected = pd.getSelected();
		if(selected!=null) {
			pd.getShapes().remove(selected);
		}
		pd.repaint();
	}
	
	protected void modify(){
		Shape selected=pd.getSelected();
		if(selected!=null){
			if(selected instanceof Point){
				Point point=(Point)selected;
				
				DlgPoint dlgP=new DlgPoint();
				dlgP.setPoint(point);
				dlgP.setModal(true);
				dlgP.getTextField_X().setText(String.valueOf(point.getX()));
				dlgP.getTextField_Y().setText(String.valueOf(point.getY()));
				dlgP.setLineColor(point.getColor());
				dlgP.setVisible(true);
				
				if(dlgP.isOk){
					pd.getSelected().setSelected(false);
					pd.repaint();
					pd.revalidate();
				}
			} else if(selected instanceof Line) {
				Line line = (Line)selected;
				
				DlgLine dlgL = new DlgLine();
				dlgL.setLine(line);
				dlgL.setModal(true);
				
				dlgL.getTextField_SPX().setText(String.valueOf(line.getStartPoint().getX()));
			    dlgL.getTextField_SPY().setText(String.valueOf(line.getStartPoint().getY()));			 
				dlgL.getTextField_EPX().setText(String.valueOf(line.getEndPoint().getX()));		
				dlgL.getTextField_EPY().setText(String.valueOf(line.getEndPoint().getY()));
				dlgL.setLineColor(line.getColor());
				
				dlgL.setVisible(true);
				
				if(dlgL.isOk){
					pd.getSelected().setSelected(false);
					pd.repaint();
					pd.revalidate();
				}
			}else if (selected instanceof Rectangle){
				
				mod=true;
				Rectangle rec= (Rectangle)selected;
				
				DlgRectangle dlgR=new DlgRectangle(mod);
				dlgR.setTitle("Modify rectangle");
				dlgR.setRectangle(rec);
				dlgR.setModal(true);
				dlgR.getTextField_X().setText(String.valueOf(rec.getUpperLeftPoint().getX()));
				dlgR.getTextField_Y().setText(String.valueOf(rec.getUpperLeftPoint().getY()));
				dlgR.getTextField_Width().setText(String.valueOf(rec.getWidth()));
				dlgR.getTextField_Height().setText(String.valueOf(rec.getHeight()));
				dlgR.setLineColor(rec.getColor());
				dlgR.setInnerColor(rec.getInnerColor());
				dlgR.setVisible(true);
				
				if(dlgR.isOk){
					pd.getSelected().setSelected(false);
					pd.repaint();
					pd.revalidate();
				
				
				}
				mod=false;
				
			}else if(selected instanceof Donut){
				mod=true;
				Donut donut=(Donut)selected;
				DlgDonut dlgD= new DlgDonut(mod);
				dlgD.setTitle("Modify donut");
				dlgD.setDonut(donut);
				dlgD.setModal(true);
				dlgD.getTextField_X().setText(String.valueOf(donut.getCenter().getX()));
				dlgD.getTextField_Y().setText(String.valueOf(donut.getCenter().getY()));
				dlgD.getTextField_Radius().setText(String.valueOf(donut.getRadius()));
				dlgD.getTextField_InnerRadius().setText(String.valueOf(donut.getInnerRadius()));
				dlgD.setLineColor(donut.getColor());
				dlgD.setInnerColor(donut.getInnerColor());
				dlgD.setVisible(true);
				
				if(dlgD.isOk){
					pd.getSelected().setSelected(false);
					pd.repaint();
					pd.revalidate();
				
				
				}
				mod=false;
				
			}else if(selected instanceof Circle){
				mod=true;
				Circle circle=(Circle)selected;
				DlgCircle dlgC=new DlgCircle(mod);
				dlgC.setTitle("Modify circle");
				dlgC.setCircle(circle);
				dlgC.setModal(true);
				dlgC.getTextField_X().setText(String.valueOf(circle.getCenter().getX()));
				dlgC.getTextField_Y().setText(String.valueOf(circle.getCenter().getY()));
				dlgC.getTextField_Radius().setText(String.valueOf(circle.getRadius()));
				dlgC.setLineColor(circle.getColor());
				dlgC.setInnerColor(circle.getInnerColor());
				dlgC.setVisible(true);
				
				if(dlgC.isOk){
					pd.getSelected().setSelected(false);
					pd.repaint();
					pd.revalidate();
				
				
				}
				mod=false;			
			}
			
		}
	}

	public JToggleButton getTglbtnSelection() {
		return tglbtnSelection;
	}

	public void setTglbtnSelection(JToggleButton tglbtnSelection) {
		this.tglbtnSelection = tglbtnSelection;
	}

	public JToggleButton getTglbtnPoint() {
		return tglbtnPoint;
	}

	public void setTglbtnPoint(JToggleButton tglbtnPoint) {
		this.tglbtnPoint = tglbtnPoint;
	}

	public JToggleButton getTglbtnLine() {
		return tglbtnLine;
	}

	public void setTglbtnLine(JToggleButton tglbtnLine) {
		this.tglbtnLine = tglbtnLine;
	}

	public JToggleButton getTglbtnRectangle() {
		return tglbtnRectangle;
	}

	public void setTglbtnRectangle(JToggleButton tglbtnRectangle) {
		this.tglbtnRectangle = tglbtnRectangle;
	}

	public JToggleButton getTglbtnCircle() {
		return tglbtnCircle;
	}

	public void setTglbtnCircle(JToggleButton tglbtnCircle) {
		this.tglbtnCircle = tglbtnCircle;
	}

	public JToggleButton getTglbtnDonut() {
		return tglbtnDonut;
	}

	public void setTglbtnDonut(JToggleButton tglbtnDonut) {
		this.tglbtnDonut = tglbtnDonut;
	}
	
}
