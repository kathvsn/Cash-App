import java.util.Arrays;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class forgotPassword extends JFrame {
	private static final long serialVersionUID = 1L;
	boolean userExists = false;
	
	public forgotPassword(){
		setSize(1000, 600);
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// background color
		getContentPane().setBackground(new Color(12, 182, 53));
		
		JLabel imagelabel = new JLabel();
		imagelabel.setIcon(new ImageIcon(new ImageIcon("/Users/kathytran/Downloads/CashAppImgs/CashAppLogo.png").getImage().getScaledInstance(65, 65, Image.SCALE_DEFAULT)));
		imagelabel.setBounds(25, 5, 100, 100);
		contentPane.add(imagelabel);
		
		// enter details 
		JLabel cashtagLabel = new JLabel("Enter your $Cashtag, Email Address, or Mobile Number");
		cashtagLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		cashtagLabel.setForeground(Color.WHITE);
		cashtagLabel.setBounds(300, 120, 500, 20);
		contentPane.add(cashtagLabel);
		
		JTextField tag = new JTextField();
		tag.setToolTipText("Enter $Cashtag, email address, or mobile number");
		tag.setBounds(350, 150, 300, 40);
		contentPane.add(tag);
		
		// set new password 
		JLabel pwLabel = new JLabel("Set New Password");
		pwLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		pwLabel.setForeground(Color.WHITE);
		pwLabel.setBounds(420, 200, 400, 20);
		contentPane.add(pwLabel);
		pwLabel.setVisible(false);
		
		JPasswordField pass = new JPasswordField();
		pass.setToolTipText("Set your password.");
		pass.setBounds(350, 230, 300, 40);
		contentPane.add(pass);
		pass.setVisible(false);
		
		// re-enter password
		JLabel reEnter = new JLabel("Re-enter Password");
		reEnter.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		reEnter.setForeground(Color.WHITE);
		reEnter.setBounds(420, 280, 400, 20);
		contentPane.add(reEnter);
		reEnter.setVisible(false);
		
		JPasswordField rePass = new JPasswordField();
		rePass.setToolTipText("Re-enter your password.");
		rePass.setBounds(350, 300, 300, 40);
		contentPane.add(rePass);
		rePass.setVisible(false);
		
		// security questions
		JLabel securityLabel = new JLabel("Select Security Question");
		securityLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		securityLabel.setForeground(Color.WHITE);
		securityLabel.setBounds(400, 200, 400, 20);
		contentPane.add(securityLabel);
		
		String[] secQuestions = {"--", "What is the name of your favorite pet?", "What is your oldest cousin's first name?", "What was your favorite food as a child?"};
		JComboBox<String> questions = new JComboBox<String>(secQuestions);
		questions.setToolTipText("Select a security question to answer.");
		questions.setBounds(350, 240, 300, 40);
		contentPane.add(questions);
		
		JTextField answer = new JTextField();
		answer.setToolTipText("Enter your answer to the security question.");
		answer.setBounds(350, 300, 300, 40);
		contentPane.add(answer);
		
		// save information and head to activity page
		JButton savePw = new JButton("Save Password");
		savePw.setBounds(330, 520, 300, 30);
		savePw.setBorderPainted(false);
		savePw.setOpaque(true);
		savePw.setBackground(new Color(28,236,79));
		savePw.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		savePw.setForeground(Color.WHITE);
		contentPane.add(savePw);
		savePw.setVisible(false);
		
		// save information and head to activity page
		JButton saveInfo = new JButton("Next");
		saveInfo.setBounds(330, 520, 300, 30);
		saveInfo.setBorderPainted(false);
		saveInfo.setOpaque(true);
		saveInfo.setBackground(new Color(28,236,79));
		saveInfo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		saveInfo.setForeground(Color.WHITE);
		contentPane.add(saveInfo);
		saveInfo.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	
		    	// check if info is empty
		    	if(tag.getText() == null || tag.getText().length() == 0 || tag.getText() == "") {
		    		JOptionPane.showMessageDialog(null, "Enter your details!", "Cash App", JOptionPane.WARNING_MESSAGE);
		    	}
		    	else if(questions.getSelectedItem() == null || questions.getSelectedItem() == "--") {
		    		// one of the security questions has to be selected
		    		JOptionPane.showMessageDialog(null, "Please select a security question!", "Cash App", JOptionPane.WARNING_MESSAGE);
		    	}
		    	else if(answer.getText() == null || answer.getText().length() == 0 || answer.getText() == "") {
		    		JOptionPane.showMessageDialog(null, "The answer field for your security question cannot be empty!", "Cash App", JOptionPane.WARNING_MESSAGE);
		    	}
		    	else {
		    		// need to check for credentials in the text file and if correct, user exists
		    		String information = "";
		    		String logInfo = tag.getText();
		    		try {
		    			FileSearch fs = new FileSearch();
		    			information = fs.returnInfo("/Users/kathytran/eclipse-workspace/Cash App/accountCredentials.txt", logInfo);
		    		}
		    		catch(IOException e){
		    			e.printStackTrace();
		    		}
		    		
		    		if(information != "") {
			    		userExists = true;
			    		String[] userInfo =  information.split(":");
			    		String savedQuestion = String.valueOf(userInfo[5]);
			    		String currQuestion = String.valueOf(questions.getSelectedItem());
			    		String savedAns = String.valueOf(userInfo[6]);
			    		String currAns = answer.getText();
			    		savedAns = savedAns.toUpperCase();
			    		currAns = currAns.toUpperCase();
			    		// error message if security question + answer don't match what is saved for that user
			    		if(!(currQuestion.equals(savedQuestion))) {
			    			JOptionPane.showMessageDialog(null, "Wrong security question!", "Cash App", JOptionPane.WARNING_MESSAGE);
			    		}
			    		else if(!(currAns.equals(savedAns))) {
			    			JOptionPane.showMessageDialog(null, "Wrong answer!", "Cash App", JOptionPane.WARNING_MESSAGE);
			    			answer.setText("");
			    		}
			    		else {
			    			saveInfo.setVisible(false);
			    			savePw.setVisible(true);
				    		pwLabel.setVisible(true);
				    		pass.setVisible(true);
				    		reEnter.setVisible(true);
				    		rePass.setVisible(true);
				    		questions.setVisible(false);
				    		answer.setVisible(false);
				    		securityLabel.setVisible(false);
				    		savePw.addActionListener(new ActionListener() {
				    			public void actionPerformed(ActionEvent arg0) {
					    			// go to activity if log in is successful
						    		if(pass.getPassword() == null || pass.getPassword().length == 0) {
							    		JOptionPane.showMessageDialog(null, "Password field cannot be empty!", "Cash App", JOptionPane.WARNING_MESSAGE);
							    	}
							    	else if(rePass.getPassword() == null || rePass.getPassword().length == 0) {
							    		JOptionPane.showMessageDialog(null, "Please re-enter your password!", "Cash App", JOptionPane.WARNING_MESSAGE);
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
							    		resetPassword creds = new resetPassword(userInfo[0], userInfo[1], userInfo[2], userInfo[3], userInfo[4], userInfo[5], userInfo[6], userInfo[7], pword);
							    		if(userExists) {
									        Activity second = new Activity(userInfo[0], userInfo[1], userInfo[2],true);   
									        setVisible(false); // Hide current frame
									        second.setVisible(true);
								    	}
							    	}
				    			}
				    		});
				    		
			    		}
		    		}
		    		else {
		    			JOptionPane.showMessageDialog(null, "No user exists with that information.", "Cash App", JOptionPane.WARNING_MESSAGE);
		    		}
		    		
		    	}
		    	
		    }
		});
		
		// return to sign in page
		JButton back = new JButton("Back");
		back.setBounds(30, 520, 110, 30);
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
		help.setBounds(860, 520, 110, 30);
		help.setBorderPainted(false);
		help.setOpaque(true);
		help.setBackground(new Color(28,236,79));
		help.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		help.setForeground(Color.WHITE);
		contentPane.add(help);
		help.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	Help next = new Help(3);   
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

                       // sleep for 1 second
                       sleep(1000L); 
                       }
                     catch (InterruptedException e) {}
                     finally{}
                 }
             }
          };
          refreshTitleBar.start();
       
    }
}