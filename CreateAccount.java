import java.util.Arrays;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class CreateAccount extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public CreateAccount(){
		setSize(1000, 600);
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// background color
		getContentPane().setBackground(new Color(12, 182, 53));
		
		// labels
		JLabel addBankDetails = new JLabel("Add a bank using your debit card");
		addBankDetails.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		addBankDetails.setForeground(Color.WHITE);
		addBankDetails.setBounds(160, 45, 400, 20);
		contentPane.add(addBankDetails);
		
		// textfields for card/bank information
		JTextField cardNumber = new JTextField();
		cardNumber.setToolTipText("Enter your card number.");
		cardNumber.setBounds(130, 70, 300, 40);
		contentPane.add(cardNumber);
		
		JTextField cardDate = new JTextField();
		cardDate.setToolTipText("Enter your card date as MM/YYYY.");
		cardDate.setBounds(130, 110, 100, 40);
		contentPane.add(cardDate);
		
		JTextField cardCVV = new JTextField();
		cardCVV.setToolTipText("Enter your card security number.");
		cardCVV.setBounds(230, 110, 100, 40);
		contentPane.add(cardCVV);
		
		JTextField zip = new JTextField();
		zip.setToolTipText("Enter your 5-digit zipcode.");
		zip.setBounds(330, 110, 100, 40);
		contentPane.add(zip);
		
		// display name (first and last name)
		JLabel displayName = new JLabel("First and Last Name");
		displayName.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		displayName.setForeground(Color.WHITE);
		displayName.setBounds(200, 170, 400, 20);
		contentPane.add(displayName);
				
		JTextField fName = new JTextField();
		fName.setToolTipText("Enter your first name.");
		fName.setBounds(130, 190, 150, 40);
		contentPane.add(fName);
		
		JTextField lName = new JTextField();
		lName.setToolTipText("Enter your last name.");
		lName.setBounds(280, 190, 150, 40);
		contentPane.add(lName);

		// address
		JLabel addressLabel = new JLabel("Billing Address");
		addressLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		addressLabel.setForeground(Color.WHITE);
		addressLabel.setBounds(220, 240, 400, 20);
		contentPane.add(addressLabel);
		
		// textfields for card/bank information
		JTextField address = new JTextField();
		address.setToolTipText("Enter your billing address.");
		address.setBounds(130, 260, 300, 40);
		contentPane.add(address);
		
		JTextField city = new JTextField();
		city.setToolTipText("Enter your city or town.");
		city.setBounds(130, 300, 100, 40);
		contentPane.add(city);
		
		String[] statesList = {"--","AK","AL","AR","AS","AZ","CA","CO","CT","DC","DE","FL","GA","GU","HI","IA","ID","IL","IN","KS","KY","LA","MA","MD","ME","MI","MN","MO","MS","MT","NC","ND","NE","NH","NJ","NM","NV","NY","OH","OK","OR","PA","PR","RI","SC","SD","TN","TX","UT","VA","VI","VT","WA","WI","WV","WY"};
		JComboBox<String> states = new JComboBox<String>(statesList);
		states.setToolTipText("Select your state.");
		states.setBounds(230, 300, 100, 40);
		contentPane.add(states);
		
		JTextField zipAdd = new JTextField();
		zipAdd.setToolTipText("Enter your 5-digit zipcode.");
		zipAdd.setBounds(330, 300, 100, 40);
		contentPane.add(zipAdd);
		
		// cashtag 
		JLabel cashtagLabel = new JLabel("$Cashtag");
		cashtagLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		cashtagLabel.setForeground(Color.WHITE);
		cashtagLabel.setBounds(250, 350, 400, 20);
		contentPane.add(cashtagLabel);
		
		JTextField tag = new JTextField();
		tag.setToolTipText("Enter your unique cashtag that begins with $");
		tag.setBounds(130, 375, 300, 40);
		contentPane.add(tag);

		// email or mobile number
		JLabel emailOrNumber = new JLabel("Email or Mobile Number");
		emailOrNumber.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		emailOrNumber.setForeground(Color.WHITE);
		emailOrNumber.setBounds(620, 45, 400, 20);
		contentPane.add(emailOrNumber);
		
		JTextField emNum = new JTextField();
		emNum.setToolTipText("Enter your email or mobile number.");
		emNum.setBounds(550, 70, 300, 40);
		contentPane.add(emNum);
		
		// radio buttons to specify whether the user chose to enter their email or phone number
		JRadioButton email = new JRadioButton("Email");
		JRadioButton mobile = new JRadioButton("Mobile Number");
		email.setForeground(Color.WHITE);
		mobile.setForeground(Color.WHITE);
		
		email.setBounds(580, 130, 100, 20);
		mobile.setBounds(700, 130, 150, 20);
		
		ButtonGroup bg = new ButtonGroup();
		
		bg.add(email);
		bg.add(mobile);
		contentPane.add(email);
		contentPane.add(mobile);
		
		// set new password 
		JLabel pwLabel = new JLabel("Set Password");
		pwLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		pwLabel.setForeground(Color.WHITE);
		pwLabel.setBounds(650, 170, 400, 20);
		contentPane.add(pwLabel);
		
		JPasswordField pass = new JPasswordField();
		pass.setToolTipText("Set your password.");
		pass.setBounds(550, 190, 300, 40);
		contentPane.add(pass);
		
		// re-enter password
		JLabel reEnter = new JLabel("Re-enter Password");
		reEnter.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		reEnter.setForeground(Color.WHITE);
		reEnter.setBounds(630, 240, 400, 20);
		contentPane.add(reEnter);
		
		JPasswordField rePass = new JPasswordField();
		rePass.setToolTipText("Re-enter your password.");
		rePass.setBounds(550, 260, 300, 40);
		contentPane.add(rePass);
		
		// security questions
		JLabel securityLabel = new JLabel("Security Question");
		securityLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		securityLabel.setForeground(Color.WHITE);
		securityLabel.setBounds(630, 310, 400, 20);
		contentPane.add(securityLabel);
		
		String[] secQuestions = {"--", "What is the name of your favorite pet?", "What is your oldest cousin's first name?", "What was your favorite food as a child?"};
		JComboBox<String> questions = new JComboBox<String>(secQuestions);
		questions.setToolTipText("Select a security question to answer.");
		questions.setBounds(550, 330, 300, 40);
		contentPane.add(questions);
		
		JTextField answer = new JTextField();
		answer.setToolTipText("Enter your answer to the security question.");
		answer.setBounds(550, 375, 300, 40);
		contentPane.add(answer);
		
		// save information and head to activity page
		JButton saveInfo = new JButton("Save Information");
		saveInfo.setBounds(350, 540, 300, 30);
		saveInfo.setBorderPainted(false);
		saveInfo.setOpaque(true);
		saveInfo.setBackground(new Color(28,236,79));
		saveInfo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		saveInfo.setForeground(Color.WHITE);
		contentPane.add(saveInfo);
		saveInfo.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	// check if info is empty
		    	if(cardNumber.getText() == null || cardNumber.getText().length() == 0 || cardNumber.getText() == "") {
		    		JOptionPane.showMessageDialog(null, "Card number field cannot be empty!", "Cash App", JOptionPane.WARNING_MESSAGE);
		    	}
		    	else if(cardDate.getText() == null || cardDate.getText().length() == 0 || cardDate.getText() == "") {
		    		JOptionPane.showMessageDialog(null, "Date field cannot be empty!", "Cash App", JOptionPane.WARNING_MESSAGE);
		    	}
		    	else if(cardCVV.getText() == null || cardCVV.getText().length() == 0 || cardCVV.getText() == "") {
		    		JOptionPane.showMessageDialog(null, "Card CVV field cannot be empty!", "Cash App", JOptionPane.WARNING_MESSAGE);
		    	}
		    	else if(zip.getText() == null || zip.getText().length() == 0 || zip.getText() == "") {
		    		JOptionPane.showMessageDialog(null, "Zipcode field for your card cannot be empty!", "Cash App", JOptionPane.WARNING_MESSAGE);
		    	}
		    	else if(zip.getText().length() < 5 || zip.getText().length() > 5) {
		    		JOptionPane.showMessageDialog(null, "Zipcode for your card must be 5 digits!", "Cash App", JOptionPane.WARNING_MESSAGE);
		    	}
		    	else if(fName.getText() == null || fName.getText().length() == 0 || fName.getText() == "") {
		    		JOptionPane.showMessageDialog(null, "First name field cannot be empty!", "Cash App", JOptionPane.WARNING_MESSAGE);
		    	}
		    	else if(lName.getText() == null || lName.getText().length() == 0 || lName.getText() == "") {
		    		JOptionPane.showMessageDialog(null, "Last name field cannot be empty!", "Cash App", JOptionPane.WARNING_MESSAGE);
		    	}
		    	else if(address.getText() == null || address.getText().length() == 0 || address.getText() == "") {
		    		JOptionPane.showMessageDialog(null, "Address field cannot be empty!", "Cash App", JOptionPane.WARNING_MESSAGE);
		    	}
		    	else if(city.getText() == null || city.getText().length() == 0 || city.getText() == "") {
		    		JOptionPane.showMessageDialog(null, "City or town field cannot be empty!", "Cash App", JOptionPane.WARNING_MESSAGE);
		    	}
		    	else if(states.getSelectedItem() == null || states.getSelectedItem() == "--") {
		    		JOptionPane.showMessageDialog(null, "Please select a valid state!", "Cash App", JOptionPane.WARNING_MESSAGE);
		    	}
		    	else if(zipAdd.getText() == null || zipAdd.getText().length() == 0 || zipAdd.getText() == "") {
		    		JOptionPane.showMessageDialog(null, "Zipcode field for your address cannot be empty!", "Cash App", JOptionPane.WARNING_MESSAGE);
		    	}
		    	else if(zipAdd.getText().length() < 5 || zipAdd.getText().length() > 5) {
		    		JOptionPane.showMessageDialog(null, "Zipcode for your address must be 5 digits!", "Cash App", JOptionPane.WARNING_MESSAGE);
		    	}
		    	else if(tag.getText() == null || tag.getText().length() == 0 || tag.getText() == "") {
		    		JOptionPane.showMessageDialog(null, "Cashtag field cannot be empty!", "Cash App", JOptionPane.WARNING_MESSAGE);
		    	}
		    	else if(emNum.getText() == null || emNum.getText().length() == 0 || emNum.getText() == "") {
		    		JOptionPane.showMessageDialog(null, "Email or mobile number field cannot be empty!", "Cash App", JOptionPane.WARNING_MESSAGE);
		    	}
		    	else if(email.isSelected() == false && mobile.isSelected() == false) {
		    		JOptionPane.showMessageDialog(null, "Please select whether you entered your email address or mobile number!", "Cash App", JOptionPane.WARNING_MESSAGE);
		    	}
		    	else if(pass.getPassword() == null || pass.getPassword().length == 0) {
		    		JOptionPane.showMessageDialog(null, "Password field cannot be empty!", "Cash App", JOptionPane.WARNING_MESSAGE);
		    	}
		    	else if(rePass.getPassword() == null || rePass.getPassword().length == 0) {
		    		JOptionPane.showMessageDialog(null, "Please re-enter your password!", "Cash App", JOptionPane.WARNING_MESSAGE);
		    	}
		    	else if(questions.getSelectedItem() == null || questions.getSelectedItem() == "--") {
		    		// one of the security questions has to be selected
		    		JOptionPane.showMessageDialog(null, "Please select a security question!", "Cash App", JOptionPane.WARNING_MESSAGE);
		    	}
		    	else if(answer.getText() == null || answer.getText().length() == 0 || answer.getText() == "") {
		    		JOptionPane.showMessageDialog(null, "The answer field for your security question cannot be empty!", "Cash App", JOptionPane.WARNING_MESSAGE);
		    	}
		    	else if(!(tag.getText().contains("$") && tag.getText().length() > 1)) {
		    		// check if cashtag includes $ + at least another character
		    		JOptionPane.showMessageDialog(null, "Please make sure you include $ in your tag. You also cannot have $ by itself.", "Cash App", JOptionPane.WARNING_MESSAGE);
		    	}
		    	else if(!(Arrays.equals(pass.getPassword(), rePass.getPassword()))) {
		    		// user has to select at least one option
		    		JOptionPane.showMessageDialog(null, "Passwords do not match! Please re-enter your password.", "Cash App", JOptionPane.WARNING_MESSAGE);
		    		rePass.setText("");
		    	}
		    	else {
		    		// convert char[] to string
		    		String pword =  String.valueOf(pass.getPassword());
		    		// pass credentials to hash the password
		    		passwordHash creds = new passwordHash(fName.getText(), lName.getText(), tag.getText(), emNum.getText(), pword);
			        Activity second = new Activity(fName.getText(), lName.getText(), tag.getText());   
			        setVisible(false); // Hide current frame
			        second.setVisible(true);
		    	}
		    }
		});
		
		// return to sign in page
		JButton back = new JButton("Back");
		back.setBounds(10, 540, 110, 30);
		back.setBorderPainted(false);
		back.setOpaque(true);
		back.setBackground(new Color(28,236,79));
		back.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		back.setForeground(Color.WHITE);
		contentPane.add(back);
		
	    // return back to sign in frame
		back.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	int result = JOptionPane.showConfirmDialog(null,"Return to sign in screen? No changes will be saved.", "Return", JOptionPane.INFORMATION_MESSAGE);
		    	if (result==JOptionPane.OK_OPTION) {
		    		SignIn second = new SignIn();   
			        setVisible(false); // hide current frame
			        second.setVisible(true);	
		    	}
		    }
		});
		
		// help button
		JButton help = new JButton("Help");
		help.setBounds(880, 540, 110, 30);
		help.setBorderPainted(false);
		help.setOpaque(true);
		help.setBackground(new Color(28,236,79));
		help.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		help.setForeground(Color.WHITE);
		contentPane.add(help);
		help.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	Help next = new Help();   
			    setVisible(false); // hide current frame
			    next.setVisible(true);	
		    }
		});
		
		
		// clock
        Thread refreshTitleBar = new Thread(){
            public void run(){ 
                 while (true){//Keep running while true (aka always)
                     try {
                       // Display time
                       Date date = new Date();
                       String str = String.format("%tc", date); //format the clock string

                       String titl = "Cash App | " + str; //concatenate the time with the title
                       setTitle(titl);

                       // sleep for 1 secondxw
                       sleep(1000L); 
                       }
                     catch (InterruptedException e) {}//don't know what it does here cuz ide says so
                     finally{}//same as catch
                 }
             }
          };
          refreshTitleBar.start();
       
    }
}