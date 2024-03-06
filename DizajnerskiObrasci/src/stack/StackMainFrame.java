package stack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import geometry.Point;
import geometry.Rectangle;

public class StackMainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ArrayList<Rectangle> list; 
	private DefaultListModel<String> model = new DefaultListModel<String>(); 
	private JList<String> list1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StackMainFrame frame = new StackMainFrame(); 
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
	public StackMainFrame() { 
		list = new ArrayList<Rectangle>(); 
		setTitle("IT 14-2019 Ijacic Irena");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		setBounds(100, 100, 520, 500); 
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		JScrollPane jsp = new JScrollPane(); 
		mainPanel.add(jsp, BorderLayout.CENTER);
		
		list1 = new JList<String>(model); 
		jsp.setViewportView(list1); 
		JPanel southPanel = new JPanel(); 
		southPanel.setBackground(new Color(244, 164, 96));
		
		JButton add = new JButton("Add");
		add.setToolTipText("Add new rectangle");
		add.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				StackDialog sd = new StackDialog("add"); 
				sd.setTitle("Add rectangle");
				sd.setVisible(true);
				if(sd.isOk) {
					Point p = new Point(Integer.parseInt(sd.getTextField_X().getText()),Integer.parseInt(sd.getTextField_Y().getText()));
					Rectangle rec = new Rectangle(p,Integer.parseInt(sd.getTextField_Width().getText()),Integer.parseInt(sd.getTextField_Height().getText()));
					list.add(rec); 
					writeRectangles(); 
				}
		}  
		});  
		southPanel.add(add); 
		JButton remove = new JButton("Remove");
		remove.setToolTipText("Remove rectangle");
		remove.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				StackDialog sd = new StackDialog("remove");
				sd.setTitle("Remove rectangle");
				if(list.size()!=0) {
					sd.getTextField_X().setText(String.valueOf(list.get(list.size()-1).getUpperLeftPoint().getX()));
					sd.getTextField_Y().setText(String.valueOf(list.get(list.size()-1).getUpperLeftPoint().getY()));
					sd.getTextField_Width().setText(String.valueOf(list.get(list.size()-1).getWidth()));
					sd.getTextField_Height().setText(String.valueOf(list.get(list.size()-1).getHeight()));
					sd.getTextField_X().setEditable(false);
					sd.getTextField_Y().setEditable(false);
					sd.getTextField_Width().setEditable(false);
					sd.getTextField_Height().setEditable(false);
					sd.setVisible(true);
				} else {
					JOptionPane.showMessageDialog( StackMainFrame.this, "List of rectangles is empty","Warning",
					        JOptionPane.WARNING_MESSAGE);
				}
				if(sd.isOk){
					int size = list.size();
					list.remove(size-1);
					writeRectangles();
				}
		}  
		});  
		southPanel.add(remove);
		mainPanel.add(southPanel,BorderLayout.SOUTH);		
		getContentPane().add(mainPanel);
	}
	
	
	public void writeRectangles() {
		model.removeAllElements();
		for (int i=list.size()-1;i>=0;i--) {
			model.addElement(list.get(i).toString());
			
		}
		list1.setModel(model);
		
	}

	public ArrayList<Rectangle> getList() {
		return list;
	}

	public void setList(ArrayList<Rectangle> list) {
		this.list = list;
	}
	
}
