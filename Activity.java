import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;
import java.util.Vector;
import java.util.Enumeration;
import javax.swing.*;

public class Activity extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public static socketUtils test;
	public boolean immediateQuit = false;
	
	public Activity(String firstN, String lastN, String tag, boolean connect){

		if (connect) {
			test = new socketUtils();
    		boolean connected = test.socketConnect();
    		if(connected) {
    			test.sendMessage("User connected.");
    		}
		}
		
		setSize(1000, 600);
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// background color
		getContentPane().setBackground(new Color(247, 247, 247));
		
		// log out button
		JButton logOut = new JButton("Log out");
		logOut.setBounds(873, 525, 110, 30);
		logOut.setBorderPainted(false);
		logOut.setOpaque(true);
		logOut.setBackground(new Color(28,236,79));
		logOut.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		logOut.setForeground(Color.WHITE);
		contentPane.add(logOut);
		
	    // confirm if user meant to log out
		logOut.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	int result = JOptionPane.showConfirmDialog(null,"Do you really want to log out of this page?", "Log out", JOptionPane.INFORMATION_MESSAGE);
		    	if (result==JOptionPane.OK_OPTION) {
		    		SignIn second = new SignIn();   
			        setVisible(false); //Hide current frame
			        second.setVisible(true);	
		    		test.sendMessage("QUIT");
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
	    
	    // side buttons
	    JButton activity = new JButton("Activity");
	    activity.setBounds(5, 250, 200, 25);
	    activity.setBorderPainted(false);
	    activity.setOpaque(false);
        activity.setBackground(new Color(28,255,79));
        activity.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 22));
        activity.setForeground(Color.WHITE);
        greenPanel.add(activity);
        
        JButton newTrans = new JButton("New Transaction");
        newTrans.setBounds(35, 300, 240, 25);
        newTrans.setBorderPainted(false);
        newTrans.setOpaque(false);
        newTrans.setBackground(new Color(28,255,79));
        newTrans.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 22));
        newTrans.setForeground(new Color(101,210,69));
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
        
	    // search bar: filter activity
	    JTextField searchField = new JTextField();
	    searchField.setToolTipText("Search for previous transactions.");
	    searchField.setBounds(320, 5, 680, 80);
	    contentPane.add(searchField);
	    
	    // pending label + uneditable text field
	    JLabel pendingLabel = new JLabel("PENDING");
	    pendingLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 20));
	    pendingLabel.setForeground(new Color(147,141,141));
	    pendingLabel.setBounds(330, 70, 400, 100);
	    contentPane.add(pendingLabel);    
	    JTextArea pendingTransactions = new JTextArea();
	    JScrollPane scroll = new JScrollPane (pendingTransactions);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    pendingTransactions.setBounds(320, 140, 680, 100);
	    pendingTransactions.setEditable(false);
	    scroll.setBounds(320, 140, 680, 100);
	    contentPane.add(scroll);
	   
	    // completed label + uneditable text field
	    JLabel completedLabel = new JLabel("COMPLETED");
	    completedLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 20));
	    completedLabel.setForeground(new Color(147,141,141));
	    completedLabel.setBounds(330, 270, 400, 100);
	    contentPane.add(completedLabel);
	    JTextArea completedTransactions = new JTextArea();
	    JScrollPane scroll1 = new JScrollPane (completedTransactions);
		scroll1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    completedTransactions.setBounds(320, 340, 680, 100);
	    completedTransactions.setEditable(false);
	    scroll1.setBounds(320, 340, 680, 100);
	    contentPane.add(scroll1);
	    
	    // DISPLAY PENDING AND COMPLETED TRANSACTIONS //
	    Vector<String> pending = new Vector<>();
		Vector<String> completed = new Vector<>();
		try {
			TransactionSearch pendTrans = new TransactionSearch();
			TransactionSearch compTrans = new TransactionSearch();
			pending = pendTrans.returnInfo("/Users/kathytran/eclipse-workspace/Cash App/cashAppRequests.txt", tag, false, false);
			completed = compTrans.returnInfo("/Users/kathytran/eclipse-workspace/Cash App/cashAppPayments.txt", tag, false, false);
		}
		catch(IOException e){
			e.printStackTrace();
		}
		if(!(pending.isEmpty())) {
			Enumeration en = pending.elements();
			pendingTransactions.setText("");
			while (en.hasMoreElements()) {
				pendingTransactions.append(en.nextElement() + "\r\n");
			}
		}
		
		if(!(completed.isEmpty())) {
			Enumeration en1 = completed.elements();
			completedTransactions.setText("");
			while (en1.hasMoreElements()) {
				completedTransactions.append(en1.nextElement() + "\r\n");
			}
		}
		
		
	    // TRANSACTION //
	    // enter amount to pay
	    JTextField amountField = new JTextField();
	    amountField.setVisible(false);
	    amountField.setToolTipText("Enter an amount.");
	    amountField.setBounds(500, 80, 300, 100);
	    contentPane.add(amountField);
	    
	    // contact information of receiver
	    JTextField receiverField = new JTextField("$Cashtag, Email, or Mobile Number");
	    receiverField.setVisible(false);
	    receiverField.setToolTipText("Enter the $Cashtag, email, or mobile number of the recipient.");
	    receiverField.setBounds(500, 200, 300, 40);
	    contentPane.add(receiverField);
	    receiverField.setVisible(false);
	   
	    JButton requestBtn = new JButton("REQUEST");
	    requestBtn.setVisible(false);
	    requestBtn.setBounds(500, 250, 130, 50);
	    requestBtn.setBorderPainted(false);
	    requestBtn.setOpaque(true);
	    requestBtn.setBackground(new Color(28,236,79));
	    requestBtn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		requestBtn.setForeground(Color.WHITE);
		contentPane.add(requestBtn);
		requestBtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	// need to check for credentials if that person exists
	    		String information = "";
	    		try {
	    			FileSearch fs = new FileSearch();
	    			information = fs.returnInfo("/Users/kathytran/eclipse-workspace/Cash App/accountCredentials.txt", receiverField.getText());
	    		}
	    		catch(IOException e){
	    			e.printStackTrace();
	    		}
	    		
	    		if(information != "") {
			    	int result = JOptionPane.showConfirmDialog(null,"Are you sure you want to request $" + amountField.getText() + " from " + receiverField.getText() + "?", "Confirm Request", JOptionPane.INFORMATION_MESSAGE);
			    	// send message to server
			    	if(result == JOptionPane.OK_OPTION){
			    		immediateQuit = true;
				    	test = new socketUtils();
			    		boolean connected = test.socketConnect();
			    		if(connected) {
			    			String reqMsg = tag + " requested $" + amountField.getText() + " from " + receiverField.getText();
			    			String ms = " " + tag + "_REQUEST";
			    			String pMsg = tag + " requested $" + amountField.getText() + " from " + receiverField.getText() + ms;
			    			fileIO fio = new fileIO("cashAppRequests.txt");
			    			fileIO fio2 = new fileIO("numRequests.txt");
			    			fio.wrData(reqMsg);
			    			fio2.wrData(pMsg);
			    			Vector<String> transactionHistory = new Vector<>();
			    			Vector<String> requests = new Vector<>();
				    		try {
				    			TransactionSearch fs = new TransactionSearch();
				    			TransactionSearch fs2 = new TransactionSearch();
				    			transactionHistory = fs.returnInfo("/Users/kathytran/eclipse-workspace/Cash App/cashAppRequests.txt", tag, false, false);
				    			requests = fs2.returnInfo("/Users/kathytran/eclipse-workspace/Cash App/numRequests.txt", tag, false, true);
				    		}
				    		catch(IOException e){
				    			e.printStackTrace();
				    		}
				    		if(!(transactionHistory.isEmpty())) {
				    			Enumeration enu = transactionHistory.elements();
				    			pendingTransactions.setText("");
				    			while (enu.hasMoreElements()) {
				    				pendingTransactions.append(enu.nextElement() + "\r\n");
				    			}
				    		}

			    			test.sendMessage(reqMsg);
			    			// test for updating server on diff computer
			    			test.sendMessage(tag + "~");
			    			test.sendMessage("QUIT");
			    			// end test
			    			amountField.setText("");
			    			receiverField.setText("$Cashtag, Email, or Mobile Number");
			    		}
			    		
			    		if(immediateQuit) {
			    			test.sendMessage("QUIT");
			    			immediateQuit = false;
			    		}
			    	}
	    		}
	    		else {
	    			// if information came back as an empty string, no user exists
	    			JOptionPane.showMessageDialog(null, "Error! The person you are trying to request is not registered with Cash App!", "Cash App", JOptionPane.WARNING_MESSAGE);
	    		}
		    	
		    }
		});
		
		JButton payBtn = new JButton("PAY");
		payBtn.setVisible(false);
		payBtn.setBounds(670, 250, 130, 50);
		payBtn.setBorderPainted(false);
		payBtn.setOpaque(true);
		payBtn.setBackground(new Color(28,236,79));
		payBtn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		payBtn.setForeground(Color.WHITE);
		contentPane.add(payBtn);
		payBtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	// need to check for credentials if that person exists
	    		String information = "";
	    		try {
	    			FileSearch fs = new FileSearch();
	    			information = fs.returnInfo("/Users/kathytran/eclipse-workspace/Cash App/accountCredentials.txt", receiverField.getText());
	    		}
	    		catch(IOException e){
	    			e.printStackTrace();
	    		}
	    		if(information != "") {
			    	int result = JOptionPane.showConfirmDialog(null,"Are you sure you want to pay $" + amountField.getText() + " to " + receiverField.getText() + "?", "Confirm Payment", JOptionPane.INFORMATION_MESSAGE);
			    	// send message to server
			    	if(result == JOptionPane.OK_OPTION){
			    		immediateQuit = true;
				    	test = new socketUtils();
			    		boolean connected = test.socketConnect();
			    		if(connected) {
			    			String reqMsg = tag + " paid $" + amountField.getText() + " to " + receiverField.getText();
			    			String ms = " " + tag + "_PAYMENT";
			    			String pMsg = tag + " paid $" + amountField.getText() + " to " + receiverField.getText() + ms;
			    			fileIO fio = new fileIO("cashAppPayments.txt");
			    			fileIO fio2 = new fileIO("numPayments.txt");
			    			fio.wrData(reqMsg);
			    			fio2.wrData(pMsg);
			    			Vector<String> transactionHistory = new Vector<>();
			    			Vector<String> payments = new Vector<>();
				    		try {
				    			TransactionSearch fs = new TransactionSearch();
				    			TransactionSearch fs2 = new TransactionSearch();
				    			transactionHistory = fs.returnInfo("/Users/kathytran/eclipse-workspace/Cash App/cashAppPayments.txt", tag, false, false);
				    			payments = fs2.returnInfo("/Users/kathytran/eclipse-workspace/Cash App/numPayments.txt", tag, true, false);
				    		}
				    		catch(IOException e){
				    			e.printStackTrace();
				    		}
				    		if(!(transactionHistory.isEmpty())) {
				    			Enumeration enu = transactionHistory.elements();
				    			completedTransactions.setText("");
				    			while (enu.hasMoreElements()) {
				    				completedTransactions.append(enu.nextElement() + "\r\n");
				    			}
				    		}

			    			test.sendMessage(reqMsg);
			    			// test for updating server on diff computer
			    			test.sendMessage(tag + "~");
			    			test.sendMessage("QUIT");
			    			// end test
			    			amountField.setText("");
			    			receiverField.setText("$Cashtag, Email, or Mobile Number");
			    		}
			    		
			    		if(immediateQuit) {
			    			test.sendMessage("QUIT");
			    			immediateQuit = false;
			    		}
			    	}
	    		}
	    		else {
	    			// if information came back as an empty string, no user exists
	    			JOptionPane.showMessageDialog(null, "Error! The person you are trying to pay is not registered with Cash App!", "Cash App", JOptionPane.WARNING_MESSAGE);
	    		}
		    }
		});
        
        // active button is highlighted in white while the rest are light green
        activity.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			setVisible(true); 
			activity.setForeground(Color.WHITE);
			newTrans.setForeground(new Color(101,210,69));
			settings.setForeground(new Color(101,210,69));
			help.setForeground(new Color(101,210,69));
			pendingLabel.setVisible(true);
		    completedLabel.setVisible(true);
		    searchField.setVisible(true);
		    scroll.setVisible(true);
		    scroll1.setVisible(true);
		    receiverField.setVisible(false);
		    requestBtn.setVisible(false);
		    payBtn.setVisible(false);
		    receiverField.setVisible(false);
		    amountField.setVisible(false);
			    
		}
	});
        newTrans.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
			    newTrans.setForeground(Color.WHITE);
			    activity.setForeground(new Color(101,210,69));
			    settings.setForeground(new Color(101,210,69));
			    help.setForeground(new Color(101,210,69));
			    pendingLabel.setVisible(false);
			    completedLabel.setVisible(false);
			    searchField.setVisible(false);
			    scroll.setVisible(false);
			    scroll1.setVisible(false);
			    receiverField.setVisible(true);
			    requestBtn.setVisible(true);
			    payBtn.setVisible(true);
			    receiverField.setVisible(true);
			    amountField.setVisible(true);
			    
		    }
		});
        settings.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
			    setVisible(true); 
			    settings.setForeground(Color.WHITE);
			    newTrans.setForeground(new Color(101,210,69));
			    activity.setForeground(new Color(101,210,69));
			    help.setForeground(new Color(101,210,69));
			    pendingLabel.setVisible(false);
			    completedLabel.setVisible(false);
			    searchField.setVisible(false);
			    scroll.setVisible(false);
			    scroll1.setVisible(false);
			    receiverField.setVisible(false);
			    requestBtn.setVisible(false);
			    payBtn.setVisible(false);
			    receiverField.setVisible(false);
			    amountField.setVisible(false);
			
			    
		    }
		});
        help.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) { 
			    Help helpFrame = new Help(3);   
		        helpFrame.setVisible(true);
			    help.setForeground(Color.WHITE);
			    newTrans.setForeground(new Color(101,210,69));
			    activity.setForeground(new Color(101,210,69));
			    settings.setForeground(new Color(101,210,69));
			    
		    }
		});
        
	
	    // clock
           Thread refreshTitleBar = new Thread(){
            public void run(){ 
                 while (true){//Keep running while true 
                     try {
                       // Display time
                       Date date = new Date();
                       String str = String.format("%tc", date); //format the clock string

                       String title = "Cash App | " + str; //concatenate the time with the title
                       setTitle(title);

                       // sleep for 1 secondxw
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
