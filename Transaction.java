import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.*;

public class Transaction extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public Transaction(String firstN, String lastN, String tag){

		setSize(1000, 600);
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// background color
		getContentPane().setBackground(new Color(247, 247, 247));
		
		JButton logOut = new JButton("Log out");
		logOut.setBounds(873, 525, 110, 30);
		logOut.setBorderPainted(false);
		logOut.setOpaque(true);
		logOut.setBackground(new Color(28,236,79));
		logOut.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		logOut.setForeground(Color.WHITE);
		contentPane.add(logOut);
		
	    //Log out access next frame
		logOut.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	int result = JOptionPane.showConfirmDialog(null,"Do you really want to log out of this page?", "Log out", JOptionPane.INFORMATION_MESSAGE);
		    	if (result==JOptionPane.OK_OPTION) {
		    		SignIn second = new SignIn();   
			        setVisible(false); //Hide current frame
			        second.setVisible(true);	
		    	}
		    }
		});
	    
		// green side panel
		JPanel greenPanel = new JPanel();
	    greenPanel.setBackground(new Color(12, 182, 53));
	    greenPanel.setLocation(0, 0);
	    greenPanel.setSize(320,600);
	    contentPane.add(greenPanel);
	    greenPanel.setLayout(null);
	   
	    // temporary user icon
	    JLabel icon = new JLabel("•");
	    icon.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 400));
	    icon.setBounds(90, 0, 400, 200);
	    icon.setForeground(new Color(222,247,180));
	    greenPanel.add(icon);
	    
	    // display name
	    JLabel disName = new JLabel(firstN + " " + lastN);
	    disName.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
	    disName.setBounds(120, 180, 200, 20);
	    disName.setForeground(Color.WHITE);
	    greenPanel.add(disName);
	    
	    // cashtag
	    JLabel cashTag = new JLabel(tag);
	    cashTag.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
	    cashTag.setBounds(130, 200, 200, 20);
	    cashTag.setForeground(Color.WHITE);
	    greenPanel.add(cashTag);
	    
	    JButton activity = new JButton("Activity");
	    activity.setBounds(5, 250, 200, 25);
	    activity.setBorderPainted(false);
	    activity.setOpaque(false);
        activity.setBackground(new Color(28,255,79));
        activity.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 22));
        activity.setForeground(new Color(101,210,69));
        greenPanel.add(activity);
        
        JButton newTrans = new JButton("New Transaction");
        newTrans.setBounds(35, 300, 240, 25);
        newTrans.setBorderPainted(false);
        newTrans.setOpaque(false);
        newTrans.setBackground(new Color(28,255,79));
        newTrans.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 22));
        newTrans.setForeground(Color.WHITE);
        greenPanel.add(newTrans);
        
        JButton settings = new JButton("Settings");
        settings.setBounds(5, 350, 200, 25);
        settings.setBorderPainted(false);
        settings.setOpaque(false);
        settings.setBackground(new Color(28,255,79));
        settings.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 22));
        settings.setForeground(new Color(101,210,69));
        greenPanel.add(settings);
        
        JButton help = new JButton("Help");
        help.setBounds(5, 400, 165, 25);
        help.setBorderPainted(false);
        help.setOpaque(false);
        help.setBackground(new Color(28,255,79));
        help.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 22));
        help.setForeground(new Color(101,210,69));
        greenPanel.add(help);
        
        // active button is highlighted in white while the rest are light green
        activity.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
			    setVisible(false); 
			    Activity act = new Activity(firstN, lastN, tag);
			    act.setVisible(true);
			    activity.setForeground(Color.WHITE);
			    newTrans.setForeground(new Color(101,210,69));
			    settings.setForeground(new Color(101,210,69));
			    help.setForeground(new Color(101,210,69));
			    
		    }
		});
        newTrans.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
			    setVisible(true); 
			    newTrans.setForeground(Color.WHITE);
			    activity.setForeground(new Color(101,210,69));
			    settings.setForeground(new Color(101,210,69));
			    help.setForeground(new Color(101,210,69));
			    
		    }
		});
        settings.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
			    setVisible(true); 
			    settings.setForeground(Color.WHITE);
			    newTrans.setForeground(new Color(101,210,69));
			    activity.setForeground(new Color(101,210,69));
			    help.setForeground(new Color(101,210,69));
			    
		    }
		});
        help.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) { 
			    //Help helpFrame = new Help();   
		        setVisible(false); // Hide current frame
		        //helpFrame.setVisible(true);
			    help.setForeground(Color.WHITE);
			    newTrans.setForeground(new Color(101,210,69));
			    activity.setForeground(new Color(101,210,69));
			    settings.setForeground(new Color(101,210,69));
			    
		    }
		});
        
	    // enter amount to pay
	    JTextField amountField = new JTextField();
	    amountField.setToolTipText("Enter an amount.");
	    amountField.setBounds(500, 80, 300, 100);
	    contentPane.add(amountField);
	    
	    // contact information of receiver
	    JTextField receiverField = new JTextField("$Cashtag, Email, or Mobile Number");
	    receiverField.setToolTipText("Enter the $Cashtage, email, or mobile number of the recipient.");
	    receiverField.setBounds(500, 200, 300, 40);
	    contentPane.add(receiverField);
	   
	    JButton requestBtn = new JButton("REQUEST");
	    requestBtn.setBounds(500, 250, 130, 50);
	    requestBtn.setBorderPainted(false);
	    requestBtn.setOpaque(true);
	    requestBtn.setBackground(new Color(28,236,79));
	    requestBtn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		requestBtn.setForeground(Color.WHITE);
		contentPane.add(requestBtn);
		requestBtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	JOptionPane.showConfirmDialog(null,"Are you sure you want to request this amount?", "Confirm Request", JOptionPane.INFORMATION_MESSAGE);
		    	// display some message
		    }
		});
		
		JButton payBtn = new JButton("PAY");
		payBtn.setBounds(670, 250, 130, 50);
		payBtn.setBorderPainted(false);
		payBtn.setOpaque(true);
		payBtn.setBackground(new Color(28,236,79));
		payBtn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		payBtn.setForeground(Color.WHITE);
		contentPane.add(payBtn);
		payBtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	JOptionPane.showConfirmDialog(null,"Are you sure you want to pay this amount?", "Confirm Payment", JOptionPane.INFORMATION_MESSAGE);
		    	// display some message
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