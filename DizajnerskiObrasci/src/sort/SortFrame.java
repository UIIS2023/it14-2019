package sort;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import geometry.Point;
import geometry.Rectangle;

public class SortFrame extends JFrame {

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
					
					SortFrame frame = new SortFrame();
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
	public SortFrame() {
		list=new ArrayList<Rectangle>();
		setTitle("IT 14-2019 Ijacic Irena");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
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
				SortDialog sd = new SortDialog();
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
		
		JButton sort = new JButton("Sort");
		sort.setToolTipText("Sort rectangle");
		sort.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){ 
			Collections.sort(list);
			writeRectangles();
				
		}  
		});  
		
		southPanel.add(sort);
		mainPanel.add(southPanel,BorderLayout.SOUTH);		
		getContentPane().add(mainPanel);
	}
	public  void writeRectangles() {
		model.removeAllElements();
		for (int i=0;i<list.size();i++) {
			model.addElement(list.get(i).toString());
			
		}
		list1.setModel(model);
		
	}

	public  ArrayList<Rectangle> getList() {
		return list;
	}

	public void setList(ArrayList<Rectangle> list) {
		this.list = list;
	}
	
}
