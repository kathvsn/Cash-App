import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.Image;

// sign in page for the application
public class SignIn extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public static socketUtils test;
	
	public static void main(String[] args) throws InterruptedException {
		SignIn frame = new SignIn();
		frame.setVisible(true);
	}
	
	public SignIn(){
		// set size
		setSize(1000, 600);
		
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// set background color 
		getContentPane().setBackground(new Color(12, 182, 53));
		
		// real time clock 
		Thread refreshAllTitleBar = new Thread(){
            public void run(){ 
                 while (true){
                     try {
                       // Display time
                       Date  date = new Date();
                       String str = String.format("%tc", date);

                       String title = "Cash App | " + str;
                       setTitle(title);

                       // sleep for 1 second
                       sleep(1000L); 

                     }
                     catch (InterruptedException e) {}
                     finally{}
                 }
             }
          };
          refreshAllTitleBar.start();
		
		JLabel titleLabel = new JLabel("Cash App");
		titleLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 45));
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBounds(420, 120, 400, 100);
		contentPane.add(titleLabel);
		
		JLabel imagelabel = new JLabel();
		imagelabel.setIcon(new ImageIcon(new ImageIcon("/Users/kathytran/Downloads/CashAppImgs/CashAppLogo.png").getImage().getScaledInstance(65, 65, Image.SCALE_DEFAULT)));
		imagelabel.setBounds(350, 120, 100, 100);
		contentPane.add(imagelabel);
		
		JLabel signInLabel = new JLabel("Email or mobile number:");
		signInLabel.setFont(new Font("Arial Rounded MT", Font.PLAIN, 12));
		signInLabel.setBounds(352, 230, 300, 15);
		signInLabel.setForeground(Color.WHITE);
		contentPane.add(signInLabel);

		JLabel pwLabel = new JLabel("Password:");
		pwLabel.setFont(new Font("Arial Rounded MT", Font.PLAIN, 12));
		pwLabel.setBounds(352, 290, 300, 15);
		pwLabel.setForeground(Color.WHITE);
		contentPane.add(pwLabel);
		
		// textfields for sign in information
		JTextField emailAndNumber = new JTextField();
		JPasswordField password = new JPasswordField();
		emailAndNumber.setBounds(350, 250, 300, 40);
		emailAndNumber.setToolTipText("Enter your email or mobile number.");
		password.setBounds(350, 310, 300, 40);
		password.setToolTipText("Enter your password.");
		contentPane.add(emailAndNumber);
		contentPane.add(password);
		
		// sign in button
		JButton signInBtn = new JButton("Sign In");
		signInBtn.setBounds(350, 370, 300, 40);
		signInBtn.setBorderPainted(false);
		signInBtn.setOpaque(true);
		signInBtn.setBackground(new Color(28,236,79));
		signInBtn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		signInBtn.setForeground(Color.WHITE);
		contentPane.add(signInBtn);
		
	    // Access next frame
		signInBtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	// check if log in info is empty 
		    	String logInfo = emailAndNumber.getText();
		    	
		    	if(logInfo == null || logInfo.length() == 0 || logInfo == "") {
		    		JOptionPane.showMessageDialog(null, "Email or mobile number field cannot be empty!", "Cash App", JOptionPane.WARNING_MESSAGE);
		    	}
		    	else if(password.getPassword() == null || password.getPassword().length == 0) {
		    		JOptionPane.showMessageDialog(null, "Password field cannot be empty!", "Cash App", JOptionPane.WARNING_MESSAGE);
		    	}
		    	
		    	else if (logInfo.contains("user@user.com")) {
		    		serverSearch serv = new serverSearch();
		    		serv.setVisible(true);
		    		test = new socketUtils();
		    		
		    		boolean connected = test.socketConnect();
		    		if(connected) {
		    			test.sendMessage("Admin of Cash App connected.");
		    		}
		    	}
		    	
		    	else {
		    		//need to check for credentials in the text file and if correct, log in
			        Activity second = new Activity("firstN", "lastN", "tag",true);   
			        setVisible(false); // Hide current frame
			        second.setVisible(true);
		    	}
		    }
		});
		

		// Create account in button
		JButton Createacc = new JButton("New Account");
        Createacc.setBounds(340, 420, 150, 40);
        Createacc.setBorderPainted(false);
        Createacc.setOpaque(false);
        Createacc.setBackground(new Color(28,236,79));
        Createacc.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
        Createacc.setForeground(Color.WHITE);
        contentPane.add(Createacc);
        Createacc.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	CreateAccount newAcc = new CreateAccount();   
			    setVisible(false); // Hide current frame
			    newAcc.setVisible(true);
		    }
		});

        //Forget pass in button
        JButton Forgetpass = new JButton("Forgot Password");
        Forgetpass.setBounds(510, 420, 140, 40);
        Forgetpass.setBorderPainted(false);
        Forgetpass.setOpaque(false);
        Forgetpass.setBackground(new Color(28,255,79));
        Forgetpass.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
        Forgetpass.setForeground(Color.WHITE);
        contentPane.add(Forgetpass);
        
        // help button
		JButton help = new JButton("Help");
		help.setBounds(30, 520, 110, 30);
		help.setBorderPainted(false);
		help.setOpaque(true);
		help.setBackground(new Color(28,236,79));
		help.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		help.setForeground(Color.WHITE);
		contentPane.add(help);
		
		help.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				setVisible(false);
				Help help = new Help(1);
				help.setVisible(true);
			}
		});
		
		// adding exit button
		JButton exitButton = new JButton("EXIT");
		exitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){

				int result = JOptionPane.showConfirmDialog(null, "Do you really want to exit Cash App?", "Exit Application", JOptionPane.INFORMATION_MESSAGE);
				//JOptionPane.INFORMATION_MESSAGE: Yes, No, Exit
				if(result == JOptionPane.OK_OPTION){
					//test.sendMessage("QUIT");
					dispose();
					System.exit(0);
				}
			}
		});
		exitButton.setBounds(860, 520, 110, 30);
		exitButton.setBorderPainted(false);
		exitButton.setOpaque(true);
		exitButton.setBackground(new Color(28,236,79));
		exitButton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		exitButton.setForeground(Color.WHITE);
		contentPane.add(exitButton);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
