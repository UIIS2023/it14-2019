package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import geometry.Donut;
import geometry.Point;

public class DlgDonut extends JDialog {

	private final JPanel contentPanel = new JPanel();


	private static final long serialVersionUID = 1L;

	private JTextField textField_X;
	private JTextField textField_Y;
	private JTextField textField_Radius;
	private JTextField textField_InnerRadius;
	private Donut donut;
	public boolean isOk;
	private Color lineColor = Color.BLACK;
	private Color innerColor = Color.WHITE;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgDonut dialog = new DlgDonut();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public DlgDonut() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Create the dialog.
	 */
	public DlgDonut(boolean mod) {
		setTitle("Create donut");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(245, 222, 179));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel lblXCoordinate = new JLabel("X coordinate");

		textField_X = new JTextField();
		textField_X.setColumns(10);

		JLabel lblYCoordinate = new JLabel("Y coordinate");

		textField_Y = new JTextField();
		textField_Y.setColumns(10);

		JLabel lblRadius = new JLabel("Radius");

		textField_Radius = new JTextField();
		textField_Radius.setColumns(10);

		JLabel lblInnerRadius = new JLabel("Inner radius");

		textField_InnerRadius = new JTextField();
		textField_InnerRadius.setColumns(10);
		
		/*JButton btnColor = new JButton("Color");
		btnColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final JColorChooser chooser = new JColorChooser();
		        chooser.setColor(Color.BLUE);
		        chooser.getSelectionModel().addChangeListener(new ChangeListener() {
		            @Override
		            public void stateChanged(ChangeEvent arg0) {
		                Color color = chooser.getColor();
		                btnColor.setBackground(color);
		                lineColor = color;
		            }
		        });
		        JDialog dialog = JColorChooser.createDialog(null, "Color Chooser",
		                true, chooser, null, null);
		        dialog.setVisible(true);
			}
		});
		
		JButton btnInnerColor = new JButton("Inner Color");
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JColorChooser chooser = new JColorChooser();
		        chooser.setColor(Color.BLUE);
		        chooser.getSelectionModel().addChangeListener(new ChangeListener() {
		            @Override
		            public void stateChanged(ChangeEvent arg0) {
		                Color color = chooser.getColor();
		                btnInnerColor.setBackground(color);
		                innerColor = color;
		            }
		        });
		        JDialog dialog = JColorChooser.createDialog(null, "Color Chooser",
		                true, chooser, null, null);
		        dialog.setVisible(true);
			}
		});*/
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(39)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(lblYCoordinate, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblXCoordinate, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(lblRadius)
								.addComponent(lblInnerRadius))
							.addGap(86)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_InnerRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_Radius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_X, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_Y, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					/*	.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(30)
							.addComponent(btnColor)
							.addGap(18)
							.addComponent(btnInnerColor))*/)
					.addContainerGap(109, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(40)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblXCoordinate)
						.addComponent(textField_X, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblYCoordinate)
						.addComponent(textField_Y, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRadius)
						.addComponent(textField_Radius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInnerRadius)
						.addComponent(textField_InnerRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
					/*.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnColor)
						.addComponent(btnInnerColor))*/)
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(244, 164, 96));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener(){  
					public void actionPerformed(ActionEvent e){ 
						if(mod==false){
							
						if(textField_X.getText().trim().isEmpty()||
								textField_Y.getText().trim().isEmpty()||
								textField_Radius.getText().trim().isEmpty()||
								textField_InnerRadius.getText().trim().isEmpty()||
								!textField_X.getText().matches("[0-9]+") ||
						        !textField_Y.getText().matches("[0-9]+") ||
								!textField_Radius.getText().matches("[0-9]+") ||
								!textField_InnerRadius.getText().matches("[0-9]+")) {
								isOk = false;
								setVisible(true);
								getToolkit().beep();
								JOptionPane.showMessageDialog(null, "Populate all fields and check their validity!");
						} else {
							isOk = true;
							donut = new Donut(new Point(Integer.parseInt(textField_X.getText()),Integer.parseInt(textField_Y.getText())),Integer.parseInt(textField_Radius.getText()),Integer.parseInt(textField_InnerRadius.getText()),false,lineColor,innerColor);
							dispose();
						}
						
						} else {
							if( textField_X.getText().trim().isEmpty()||
									textField_Y.getText().trim().isEmpty()||
									textField_Radius.getText().trim().isEmpty()||
									textField_InnerRadius.getText().trim().isEmpty()||
									!textField_X.getText().matches("[0-9]+") ||
							        !textField_Y.getText().matches("[0-9]+") ||
									!textField_Radius.getText().matches("[0-9]+") ||
									!textField_InnerRadius.getText().matches("[0-9]+")) {
									isOk = false;
									setVisible(true);
									getToolkit().beep();
									JOptionPane.showMessageDialog(null, "Populate all fields and check their validity!");
							} else {
								isOk = true;
								donut = new Donut(new Point(Integer.parseInt(textField_X.getText()), Integer.parseInt(textField_Y.getText())),Integer.parseInt(textField_Radius.getText()), Integer.parseInt(textField_InnerRadius.getText()),false,lineColor,innerColor);
								//donut.newCenter(Integer.parseInt(textField_X.getText()), Integer.parseInt(textField_Y.getText()));
								//donut.setRadius(Integer.parseInt(textField_Radius.getText()));
								//donut.setInnerRadius(Integer.parseInt(textField_InnerRadius.getText()));
								//donut.setColor(lineColor);
								lineColor = Color.BLACK;
								//donut.setInnerColor(innerColor);
								innerColor= Color.WHITE;
								dispose();
								
							}
							
						}
					
					}});  
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener(){  
					public void actionPerformed(ActionEvent e){  
						dispose();
					}  
				}); 
				buttonPane.add(cancelButton);
			}
		}
	}
	

	public JTextField getTextField_X() {
		return textField_X;
	}

	public void setTextField_X(JTextField textField_X) {
		this.textField_X = textField_X;
	}

	public JTextField getTextField_Y() {
		return textField_Y;
	}

	public void setTextField_Y(JTextField textField_Y) {
		this.textField_Y = textField_Y;
	}

	public JTextField getTextField_Radius() {
		return textField_Radius;
	}

	public void setTextField_Radius(JTextField textField_Radius) {
		this.textField_Radius = textField_Radius;
	}

	public JTextField getTextField_InnerRadius() {
		return textField_InnerRadius;
	}

	public void setTextField_InnerRadius(JTextField textField_InnerRadius) {
		this.textField_InnerRadius = textField_InnerRadius;
	}

	public Donut getDonut() {
		return donut;
	}

	public void setDonut(Donut donut) {
		this.donut = donut;
	}
	public Color getLineColor() {
		return lineColor;
	}
	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}
	public Color getInnerColor() {
		return innerColor;
	}
	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}
	
}

	