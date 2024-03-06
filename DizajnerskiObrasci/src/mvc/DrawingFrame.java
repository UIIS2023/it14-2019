package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.util.Iterator;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import geometry.Shape;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;

/**
 * @author Irena
 *
 */
public class DrawingFrame extends JFrame implements PropertyChangeListener{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JToggleButton tglbtnSelection;
	private JToggleButton tglbtnPoint;
	private JToggleButton tglbtnLine;
	private JToggleButton tglbtnRectangle;
	private JToggleButton tglbtnCircle;
	private JToggleButton tglbtnDonut;
	private JToggleButton tglbtnHexagon;
	private JButton btnModify;
	private JButton btnDelete;
	private JButton btnToFront;
	private JButton btnToBack;
	private JButton btnBringToFront;
	private JButton btnBringToBack;
	private JTextArea textArea;
	
	private DrawingView view=new DrawingView();
	private DrawingController controller;
	private JButton btnColorInner;
	private JButton btnColorOuter;
	private JLabel lblInnerColor;
	private JLabel lblOuterColor;
	private JButton btnUndo;
	private JButton btnRedo;
	private Color outerColor;
	private Color innerColor;
	private JButton btnSaveDraw;
	private JButton btnLoadDraw;
	private JButton btnSaveLog;
	private JButton btnLoadLog;
	private JButton btnNext;
	
	public DrawingFrame() {
		
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.mouseClicked(e);
			}
		});
		
		
		setTitle("IT 14-2019 Ijacic Irena");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel mainPanel = new JPanel(new BorderLayout());

		mainPanel.add(view,BorderLayout.CENTER);




		JPanel northPanel = new JPanel();
		northPanel.setBackground(new Color(244, 164, 96));
		
		JPanel eastPanel = new JPanel();
		eastPanel.setBackground(new Color(244, 164, 96));
		
		JPanel westPanel = new JPanel();
		westPanel.setBackground(new Color(244, 164, 96));
		
		JPanel southPanel = new JPanel(new BorderLayout());
		southPanel.setBackground(new Color(244, 164, 96));

		mainPanel.add(northPanel,BorderLayout.NORTH);
		mainPanel.add(eastPanel,BorderLayout.EAST);
		mainPanel.add(southPanel,BorderLayout.SOUTH);
		mainPanel.add(westPanel,BorderLayout.WEST);
		
		textArea = new JTextArea(5,20);
		textArea.setEditable(false);
		JScrollPane jp = new JScrollPane(textArea);
		southPanel.add(jp,BorderLayout.CENTER);

		ButtonGroup bg = new ButtonGroup();
		tglbtnSelection = new JToggleButton("Selection");
		northPanel.add(tglbtnSelection);

		tglbtnPoint = new JToggleButton("Point");
		tglbtnPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearSelected();
			}
		});
		northPanel.add(tglbtnPoint);

		tglbtnLine = new JToggleButton("Line");
		tglbtnLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearSelected();
			}
		});
		northPanel.add(tglbtnLine);

		tglbtnRectangle = new JToggleButton("Rectangle");
		tglbtnRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearSelected();
			}
		});
		northPanel.add(tglbtnRectangle);

		tglbtnCircle = new JToggleButton("Circle");
		tglbtnCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearSelected();
			}
		});
		northPanel.add(tglbtnCircle);

		tglbtnDonut = new JToggleButton("Donut");
		tglbtnDonut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearSelected();
			}
		});
		northPanel.add(tglbtnDonut);

		tglbtnHexagon = new JToggleButton("Hexagon");
		tglbtnHexagon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearSelected();
			}
		});
		northPanel.add(tglbtnHexagon);


		bg.add(tglbtnSelection);
		bg.add(tglbtnPoint);
		bg.add(tglbtnLine);
		bg.add(tglbtnRectangle);
		bg.add(tglbtnCircle);
		bg.add(tglbtnDonut);
		bg.add(tglbtnHexagon);
		
		btnModify = new JButton("Modify");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.modify();
			}
		});
		northPanel.add(btnModify);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.delete();
			}
		});
		northPanel.add(btnDelete);
		GridBagLayout gbl_eastPanel = new GridBagLayout();
		gbl_eastPanel.columnWidths = new int[]{20, 0, 20, 0};
		gbl_eastPanel.rowHeights = new int[]{25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_eastPanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_eastPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		eastPanel.setLayout(gbl_eastPanel);
		
		GridBagLayout gbl_westPanel = new GridBagLayout();
		gbl_westPanel.columnWidths = new int[]{20, 0, 20, 0};
		gbl_westPanel.rowHeights = new int[]{25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_westPanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_westPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		westPanel.setLayout(gbl_westPanel);
		
		btnUndo = new JButton("Undo");
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.undo();
			}
		});		
		
		btnRedo = new JButton("Redo");
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.redo();
			}
		});
				
		btnToBack = new JButton("To Back");
		btnToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.toBack();
			}
		});
		
		btnToFront = new JButton("To Front");
		btnToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.toFront();
			}
		});
		
		btnBringToFront = new JButton("Bring to front");
		btnBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.bringToFront();
			}
		});
		
		btnBringToBack = new JButton("Bring to back");
		btnBringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.bringToBack();
			}
		});
		
		btnSaveDraw = new JButton("Save draw");
		btnSaveDraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.saveDraw();
			}
		});
		
		btnLoadDraw = new JButton("Load draw");
		btnLoadDraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.loadDraw();
			}
		});
		
		btnSaveLog = new JButton("Save log");
		btnSaveLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.saveLog();
			}
		});
		
		btnLoadLog = new JButton("Load log");
		btnLoadLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.loadLog();
			}
		});
		
		btnNext = new JButton("Next");
		btnNext.setEnabled(false);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.next();
			}
		});
		
		GridBagConstraints gbc_btnUndo = new GridBagConstraints();
		gbc_btnUndo.insets = new Insets(0, 0, 5, 5);
		gbc_btnUndo.gridx = 1;
		gbc_btnUndo.gridy = 1;
		westPanel.add(btnUndo, gbc_btnUndo);
		GridBagConstraints gbc_btnRedo = new GridBagConstraints();
		gbc_btnRedo.insets = new Insets(0, 0, 5, 5);
		gbc_btnRedo.gridx = 1;
		gbc_btnRedo.gridy = 2;
		westPanel.add(btnRedo, gbc_btnRedo);
		GridBagConstraints gbc_btnSaveDraw = new GridBagConstraints();
		gbc_btnSaveDraw.insets = new Insets(50, 0, 5, 5);
		gbc_btnSaveDraw.gridx = 1;
		gbc_btnSaveDraw.gridy = 4;
		westPanel.add(btnSaveDraw, gbc_btnSaveDraw);
		GridBagConstraints gbc_btnLoadDraw = new GridBagConstraints();
		gbc_btnLoadDraw.insets = new Insets(0, 0, 5, 5);
		gbc_btnLoadDraw.gridx = 1;
		gbc_btnLoadDraw.gridy = 5;
		westPanel.add(btnLoadDraw, gbc_btnLoadDraw);
		GridBagConstraints gbc_btnSaveLog = new GridBagConstraints();
		gbc_btnSaveLog.insets = new Insets(50, 0, 5, 5);
		gbc_btnSaveLog.gridx = 1;
		gbc_btnSaveLog.gridy = 7;
		westPanel.add(btnSaveLog, gbc_btnSaveLog);
		GridBagConstraints gbc_btnLoadLog = new GridBagConstraints();
		gbc_btnLoadLog.insets = new Insets(0, 0, 5, 5);
		gbc_btnLoadLog.gridx = 1;
		gbc_btnLoadLog.gridy = 8;
		westPanel.add(btnLoadLog, gbc_btnLoadLog);
		GridBagConstraints gbc_btnNext = new GridBagConstraints();
		gbc_btnNext.insets = new Insets(0, 0, 5, 5);
		gbc_btnNext.gridx = 1;
		gbc_btnNext.gridy = 9;
		westPanel.add(btnNext, gbc_btnNext);
		GridBagConstraints gbc_btnToFront = new GridBagConstraints();
		gbc_btnToFront.anchor = GridBagConstraints.CENTER;
		gbc_btnToFront.insets = new Insets(0, 0, 5, 5);
		gbc_btnToFront.gridx = 1;
		gbc_btnToFront.gridy = 1;
		eastPanel.add(btnToFront, gbc_btnToFront);
		GridBagConstraints gbc_btnToBack = new GridBagConstraints();
		gbc_btnToBack.insets = new Insets(5, 0, 5, 5);
		gbc_btnToBack.anchor = GridBagConstraints.CENTER;
		gbc_btnToBack.gridx = 1;
		gbc_btnToBack.gridy = 3;
		eastPanel.add(btnToBack, gbc_btnToBack);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(5, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 5;
		eastPanel.add(btnBringToFront, gbc_btnNewButton);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(5, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 7;
		eastPanel.add(btnBringToBack, gbc_btnNewButton_1);
		
		lblInnerColor = new JLabel("Inner Color");
		GridBagConstraints gbc_lblInnerColor = new GridBagConstraints();
		gbc_lblInnerColor.insets = new Insets(50, 0, 5, 5);
		gbc_lblInnerColor.gridx = 1;
		gbc_lblInnerColor.gridy = 10;
		eastPanel.add(lblInnerColor, gbc_lblInnerColor);
		
		btnColorInner = new JButton();
		btnColorInner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final JColorChooser chooser = new JColorChooser();
		        chooser.setColor(Color.WHITE);
		        innerColor = Color.WHITE;
		        chooser.getSelectionModel().addChangeListener(new ChangeListener() {
		            @Override
		            public void stateChanged(ChangeEvent arg0) {
		                innerColor = chooser.getColor();
		               
		            }
		        });
		        JDialog dialog = JColorChooser.createDialog(null, "Color Chooser",
		                true, chooser, null, null);
		        dialog.setVisible(true);
		        btnColorInner.setBackground(innerColor);
                if(controller.getListShape().size()==1)
                	controller.setInnerColor(innerColor);
                repaint();
			}
		});
		btnColorInner.setPreferredSize(new Dimension(100,40));
		btnColorInner.setBackground(Color.WHITE);
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 1;
		gbc_btnNewButton_2.gridy = 11;
		eastPanel.add(btnColorInner, gbc_btnNewButton_2);
		
		lblOuterColor = new JLabel("Outer Color");
		GridBagConstraints gbc_lblOuterColor = new GridBagConstraints();
		gbc_lblOuterColor.insets = new Insets(0, 0, 5, 5);
		gbc_lblOuterColor.gridx = 1;
		gbc_lblOuterColor.gridy = 13;
		eastPanel.add(lblOuterColor, gbc_lblOuterColor);
		
		btnColorOuter = new JButton();
		btnColorOuter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final JColorChooser chooser = new JColorChooser();
		        chooser.setColor(Color.BLACK);
		        outerColor = Color.BLACK;
		        chooser.getSelectionModel().addChangeListener(new ChangeListener() {
		            @Override
		            public void stateChanged(ChangeEvent arg0) {
		                outerColor = chooser.getColor();
		            }
		        });
		        JDialog dialog = JColorChooser.createDialog(null, "Color Chooser",
		                true, chooser, null, null);
		        dialog.setVisible(true);
		        btnColorOuter.setBackground(outerColor);
                if(controller.getListShape().size()==1)
                	controller.setOuterColor(outerColor);
                repaint();
			}
		});
		btnColorOuter.setPreferredSize(new Dimension(100,40));
		btnColorOuter.setBackground(Color.BLACK);
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_3.gridx = 1;
		gbc_btnNewButton_3.gridy = 14;
		eastPanel.add(btnColorOuter, gbc_btnNewButton_3);
		
		getContentPane().add(mainPanel);
		
		

	}
	
	public DrawingView getView() {
		return view;
	}
	
	public void clearSelected() {
		if(!controller.getListShape().isEmpty()) {
			Iterator<Shape> it1=controller.getListShape().iterator();
			while(it1.hasNext()){
				Shape shape=it1.next();
				controller.getModel().setSelected(shape);
				controller.getModel().getSelected().setSelected(false);	
			}
			controller.getListShape().clear();
		}
		btnColorInner.setBackground(Color.WHITE);
		btnColorOuter.setBackground(Color.BLACK);
		repaint();
	}
	
	
	public void setController(DrawingController controller) {
		this.controller = controller;
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

	public JToggleButton getTglbtnHexagon() {
		return tglbtnHexagon;
	}

	public void setTglbtnHexagon(JToggleButton tglbtnHexagon) {
		this.tglbtnHexagon = tglbtnHexagon;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("noSelected")) {
			btnModify.setEnabled((Boolean)evt.getNewValue());
			btnDelete.setEnabled((Boolean)evt.getNewValue());
			btnToFront.setEnabled((Boolean)evt.getNewValue());
			btnToBack.setEnabled((Boolean)evt.getNewValue());
			btnBringToFront.setEnabled((Boolean)evt.getNewValue());
			btnBringToBack.setEnabled((Boolean)evt.getNewValue());
			btnColorInner.setEnabled((Boolean)evt.getOldValue());
			btnColorOuter.setEnabled((Boolean)evt.getOldValue());
			this.repaint();
		} else if(evt.getPropertyName().equals("oneSelected")){
			btnModify.setEnabled((Boolean)evt.getOldValue());
			btnDelete.setEnabled((Boolean)evt.getOldValue());
			btnToFront.setEnabled((Boolean)evt.getOldValue());
			btnToBack.setEnabled((Boolean)evt.getOldValue());
			btnBringToFront.setEnabled((Boolean)evt.getOldValue());
			btnBringToBack.setEnabled((Boolean)evt.getOldValue());
			btnColorInner.setEnabled((Boolean)evt.getOldValue());
			btnColorOuter.setEnabled((Boolean)evt.getOldValue());
			this.repaint();
		} else {
			btnModify.setEnabled((Boolean)evt.getNewValue());
			btnDelete.setEnabled((Boolean)evt.getOldValue());
			btnToFront.setEnabled((Boolean)evt.getNewValue());
			btnToBack.setEnabled((Boolean)evt.getNewValue());
			btnBringToFront.setEnabled((Boolean)evt.getNewValue());
			btnBringToBack.setEnabled((Boolean)evt.getNewValue());
			btnColorInner.setEnabled((Boolean)evt.getNewValue());
			btnColorOuter.setEnabled((Boolean)evt.getNewValue());
			this.repaint();
		}
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public JButton getBtnColorInner() {
		return btnColorInner;
	}

	public void setBtnColorInner(JButton btnColorInner) {
		this.btnColorInner = btnColorInner;
	}

	public JButton getBtnColorOuter() {
		return btnColorOuter;
	}

	public void setBtnColorOuter(JButton btnColorOuter) {
		this.btnColorOuter = btnColorOuter;
	}

	public JButton getBtnUndo() {
		return btnUndo;
	}

	public void setBtnUndo(JButton btnUndo) {
		this.btnUndo = btnUndo;
	}

	public JButton getBtnRedo() {
		return btnRedo;
	}

	public void setBtnRedo(JButton btnRedo) {
		this.btnRedo = btnRedo;
	}

	public JButton getBtnSaveDraw() {
		return btnSaveDraw;
	}

	public void setBtnSaveDraw(JButton btnSaveDraw) {
		this.btnSaveDraw = btnSaveDraw;
	}

	public JButton getBtnLoadDraw() {
		return btnLoadDraw;
	}

	public void setBtnLoadDraw(JButton btnLoadDraw) {
		this.btnLoadDraw = btnLoadDraw;
	}

	public JButton getBtnSaveLog() {
		return btnSaveLog;
	}

	public void setBtnSaveLog(JButton btnSaveLog) {
		this.btnSaveLog = btnSaveLog;
	}

	public JButton getBtnLoadLog() {
		return btnLoadLog;
	}

	public void setBtnLoadLog(JButton btnLoadLog) {
		this.btnLoadLog = btnLoadLog;
	}

	public JButton getBtnNext() {
		return btnNext;
	}

	public void setBtnNext(JButton btnNext) {
		this.btnNext = btnNext;
	}
	
}
