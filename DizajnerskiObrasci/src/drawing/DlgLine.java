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

import geometry.Line;
import geometry.Point;

public class DlgLine extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField_SPX;
	private JTextField textField_SPY;
	private JTextField textField_EPX;
	private JTextField textField_EPY;
	private Line line;
	public boolean isOk;
	private Color lineColor = Color.BLACK;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgLine dialog = new DlgLine();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgLine() {
		setTitle("Modify line");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(245, 222, 179));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel lblStartPointX = new JLabel("Start Point X");

		textField_SPX = new JTextField();
		textField_SPX.setColumns(10);

		JLabel lblStartPointY = new JLabel("Start Point Y");

		textField_SPY = new JTextField();
		textField_SPY.setColumns(10);

		JLabel lblEndPointX = new JLabel("End Point X");

		textField_EPX = new JTextField();
		textField_EPX.setColumns(10);

		JLabel lblEndPointY = new JLabel("End Point Y");

		textField_EPY = new JTextField();
		textField_EPY.setColumns(10);
		
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
		});*/
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(39)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblStartPointX)
								.addComponent(lblEndPointY)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblStartPointY, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblEndPointX, Alignment.LEADING)))
							.addGap(86)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_EPY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_EPX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_SPX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_SPY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						/*.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(30)
							.addComponent(btnColor))*/)
					.addContainerGap(109, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(40)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStartPointX)
						.addComponent(textField_SPX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_SPY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStartPointY))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEndPointX)
						.addComponent(textField_EPX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEndPointY)
						.addComponent(textField_EPY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
					//.addComponent(btnColor)
					)
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
						if(textField_SPX.getText().trim().isEmpty()||
								textField_SPY.getText().trim().isEmpty()||
								textField_EPX.getText().trim().isEmpty()||
								textField_EPY.getText().trim().isEmpty()||
								!textField_SPX.getText().matches("[0-9]+") ||
								!textField_SPY.getText().matches("[0-9]+")||
								!textField_EPX.getText().matches("[0-9]+") ||
								!textField_EPY.getText().matches("[0-9]+")) {
								isOk = false;
								setVisible(true);
								getToolkit().beep();
								JOptionPane.showMessageDialog(null, "Populate all fields and check their validity!");
						} else {
							isOk = true;
							line = new Line(new Point(Integer.parseInt(textField_SPX.getText()),Integer.parseInt(textField_SPY.getText())),new Point(Integer.parseInt(textField_EPX.getText()),Integer.parseInt(textField_EPY.getText())),false,lineColor);
							//line.setColor(lineColor);
							dispose();
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

	public JTextField getTextField_SPX() {
		return textField_SPX;
	}

	public void setTextField_SPX(JTextField textField_SPX) {
		this.textField_SPX = textField_SPX;
	}

	public JTextField getTextField_SPY() {
		return textField_SPY;
	}

	public void setTextField_SPY(JTextField textField_SPY) {
		this.textField_SPY = textField_SPY;
	}

	public JTextField getTextField_EPX() {
		return textField_EPX;
	}

	public void setTextField_EPX(JTextField textField_EPX) {
		this.textField_EPX = textField_EPX;
	}

	public JTextField getTextField_EPY() {
		return textField_EPY;
	}

	public void setTextField_EPY(JTextField textField_EPY) {
		this.textField_EPY = textField_EPY;
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	public Color getLineColor() {
		return lineColor;
	}

	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}
	
	

}
