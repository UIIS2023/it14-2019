package sort;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class SortDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField_X;
	private JTextField textField_Y;
	private JTextField textField_Width;
	private JTextField textField_Height;
	public boolean isOk;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SortDialog dialog = new SortDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SortDialog() {
		setTitle("Add rectangle");
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

		JLabel lblWidth = new JLabel("Width");

		textField_Width = new JTextField();
		textField_Width.setColumns(10);

		JLabel lblHeight = new JLabel("Height");

		textField_Height = new JTextField();
		textField_Height.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
				gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
						.addGap(39)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lblYCoordinate, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblXCoordinate, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(lblWidth)
								.addComponent(lblHeight))
						.addGap(86)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_Height, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_Width, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_X, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_Y, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
								.addComponent(lblWidth)
								.addComponent(textField_Width, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblHeight)
								.addComponent(textField_Height, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(47, Short.MAX_VALUE))
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
						if(textField_X.getText().trim().isEmpty()||
								textField_Y.getText().trim().isEmpty()||
								textField_Width.getText().trim().isEmpty()||
								textField_Height.getText().trim().isEmpty()||
								!textField_X.getText().matches("[0-9]+") ||
								!textField_Y.getText().matches("[0-9]+")||
								!textField_Width.getText().matches("[0-9]+") ||
								!textField_Height.getText().matches("[0-9]+")) {
							isOk = false;
							setVisible(true);
							getToolkit().beep();
							JOptionPane.showMessageDialog(null, "Populate all fields and check their validity!");
						} else {
							isOk = true;
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

	public JTextField getTextField_Width() {
		return textField_Width;
	}

	public void setTextField_Width(JTextField textField_Width) {
		this.textField_Width = textField_Width;
	}

	public JTextField getTextField_Height() {
		return textField_Height;
	}

	public void setTextField_Height(JTextField textField_Height) {
		this.textField_Height = textField_Height;
	}
}
