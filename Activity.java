import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.*;

public class Activity extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public static socketUtils test;
	
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
        
        // active button is highlighted in white while the rest are light green
        activity.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			setVisible(true); 
			activity.setForeground(Color.WHITE);
			newTrans.setForeground(new Color(101,210,69));
			settings.setForeground(new Color(101,210,69));
			help.setForeground(new Color(101,210,69));
			    
		}
	});
        newTrans.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
			    setVisible(false); 
			    Transaction tran = new Transaction(firstN, lastN, tag);
			    tran.setVisible(true);
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
			    Help helpFrame = new Help(3);   
		        setVisible(false); // Hide current frame
		        helpFrame.setVisible(true);
			    help.setForeground(Color.WHITE);
			    newTrans.setForeground(new Color(101,210,69));
			    activity.setForeground(new Color(101,210,69));
			    settings.setForeground(new Color(101,210,69));
			    
		    }
		});
        
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
	    pendingTransactions.setBounds(320, 140, 680, 100);
	    pendingTransactions.setEditable(false);
	    contentPane.add(pendingTransactions);
	   
	    // completed label + uneditable text field
	    JLabel completedLabel = new JLabel("COMPLETED");
	    completedLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 20));
	    completedLabel.setForeground(new Color(147,141,141));
	    completedLabel.setBounds(330, 270, 400, 100);
	    contentPane.add(completedLabel);
	    JTextArea completedTransactions = new JTextArea();
	    completedTransactions.setBounds(320, 340, 680, 100);
	    completedTransactions.setEditable(false);
	    contentPane.add(completedTransactions);
	
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
