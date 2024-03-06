package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import adapter.HexagonAdapter;
import geometry.Point;

public class DlgHexagon extends JDialog{
	
	private final JPanel contentPanel = new JPanel();
	private static final long serialVersionUID = 1L;

	private JTextField textField_X;
	private JTextField textField_Y;
	private JTextField textField_R;
	private HexagonAdapter hex;
	public boolean isOk;
	private Color lineColor = Color.BLACK;
	private Color innerColor = Color.WHITE;
	
	public static void main(String[] args) {
		try {
			DlgHexagon dialog = new DlgHexagon();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public DlgHexagon() {

	}
	
	public DlgHexagon(boolean mod) {
		setTitle("Create hexagon");
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

		JLabel lblR = new JLabel("R");

		textField_R = new JTextField();
		textField_R.setColumns(10);

	/*	JButton btnColor = new JButton("Color");
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
												.addComponent(lblR)
												)
										.addGap(86)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(textField_R, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
								.addComponent(lblR)
								.addComponent(textField_R, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
					/*	.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
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
									textField_R.getText().trim().isEmpty()||
									!textField_X.getText().matches("[0-9]+") ||
									!textField_Y.getText().matches("[0-9]+")||
									!textField_R.getText().matches("[0-9]+")) {
								isOk = false;
								setVisible(true);
								getToolkit().beep();
								JOptionPane.showMessageDialog(null, "Populate all fields and check their validity!");
							} else {
								isOk = true;
								hex = new HexagonAdapter(new Point(Integer.parseInt(textField_X.getText()),Integer.parseInt(textField_Y.getText())),Integer.parseInt(textField_R.getText()),false,lineColor,innerColor);
								dispose();
							}		
						}else {
							if(	textField_X.getText().trim().isEmpty()||
									textField_Y.getText().trim().isEmpty()||
									textField_R.getText().trim().isEmpty()||
									!textField_X.getText().matches("[0-9]+") ||
									!textField_Y.getText().matches("[0-9]+")||
									!textField_R.getText().matches("[0-9]+")) {
								isOk = false;
								setVisible(true);
								getToolkit().beep();
								JOptionPane.showMessageDialog(null, "Populate all fields and check their validity!");
							} else {
								
								isOk = true;
								hex = new HexagonAdapter(new Point(Integer.parseInt(textField_X.getText()),Integer.parseInt(textField_Y.getText())),Integer.parseInt(textField_R.getText()),false,lineColor,innerColor);
								//hex.getHex().setX(Integer.parseInt(textField_X.getText()));
								//hex.getHex().setY(Integer.parseInt(textField_Y.getText()));
								//hex.getHex().setR(Integer.parseInt(textField_R.getText()));
								//hex.getHex().setBorderColor(lineColor);
								//hex.getHex().setAreaColor(innerColor);
								lineColor = Color.BLACK;
								innerColor= Color.WHITE;
								dispose();
							}		

						}}}

						);  
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
	public JTextField getTextField_R() {
		return textField_R;
	}
	public void setTextField_R(JTextField textField_R) {
		this.textField_R = textField_R;
	}
	public HexagonAdapter getHex() {
		return hex;
	}
	public void setHex(HexagonAdapter hex) {
		this.hex = hex;
	}
	public boolean isOk() {
		return isOk;
	}
	public void setOk(boolean isOk) {
		this.isOk = isOk;
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
	public JPanel getContentPanel() {
		return contentPanel;
	}
}
