package helpers;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateTripPanel extends JPanel {
	private JTextField fullName;
	private JTextField mobileNumber;
	private JTextField password;
	private JTextField email;
	private JTextField barangay;
	private JTextField city;
	private JTextField zipCode;

	/**
	 * Create the panel.
	 */
	public CreateTripPanel() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Full Name:");
		lblNewLabel.setBounds(10, 11, 50, 14);
		add(lblNewLabel);
		
		fullName = new JTextField();
		fullName.setBounds(66, 8, 239, 20);
		add(fullName);
		fullName.setColumns(10);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSave.setBounds(67, 191, 198, 23);
		add(btnSave);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.setBounds(66, 219, 198, 23);
		add(btnCancel);
		
		JLabel lblMobile = new JLabel("Mobile #:");
		lblMobile.setBounds(10, 35, 50, 14);
		add(lblMobile);
		
		mobileNumber = new JTextField();
		mobileNumber.setColumns(10);
		mobileNumber.setBounds(66, 32, 239, 20);
		add(mobileNumber);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 59, 50, 14);
		add(lblPassword);
		
		password = new JTextField();
		password.setColumns(10);
		password.setBounds(66, 56, 239, 20);
		add(password);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(10, 83, 50, 14);
		add(lblEmail);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(66, 80, 239, 20);
		add(email);
		
		JLabel lblBarangay = new JLabel("Barangay:");
		lblBarangay.setBounds(10, 108, 50, 14);
		add(lblBarangay);
		
		barangay = new JTextField();
		barangay.setColumns(10);
		barangay.setBounds(66, 105, 239, 20);
		add(barangay);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setBounds(10, 135, 50, 14);
		add(lblCity);
		
		city = new JTextField();
		city.setColumns(10);
		city.setBounds(66, 132, 239, 20);
		add(city);
		
		JLabel lblZipCode = new JLabel("Zip Code:");
		lblZipCode.setBounds(10, 163, 50, 14);
		add(lblZipCode);
		
		zipCode = new JTextField();
		zipCode.setColumns(10);
		zipCode.setBounds(66, 160, 239, 20);
		add(zipCode);

	}
}
