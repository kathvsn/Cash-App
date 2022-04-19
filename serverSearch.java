import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class serverSearch extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public static JTextArea displayIP;
	public static JTextArea incoming;
	public static JTextArea bottom;
	
	public serverSearch(){

		setSize(1000, 600);
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// background color
		getContentPane().setBackground(new Color(247, 247, 247));
		
		JLabel titleLabel = new JLabel("Cash App");
		titleLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 40));
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBounds(400, 20, 400, 40);
		contentPane.add(titleLabel);
	    
		// green panel on top
		JPanel greenPanel = new JPanel();
	    greenPanel.setBackground(new Color(12, 182, 53));
	    greenPanel.setLocation(0, 0);
	    greenPanel.setSize(1000, 85);
	    contentPane.add(greenPanel);
	    greenPanel.setLayout(null);
	  
	    // IP address
	    InetAddress ipAddress = null;
		try{
			ipAddress = InetAddress.getLocalHost();
		}catch (UnknownHostException el){
			el.printStackTrace();
		}
		
		String ip = ipAddress.getHostAddress();
	    
	    // display IP and port
		String display = "IP: " + ip + "        Listening on Port #: 3333";
	    displayIP = new JTextArea(display);
	    displayIP.setBounds(10, 100, 980, 30);
		displayIP.setEditable(false);
		displayIP.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		contentPane.add(displayIP);
	 
	    // search bar: filter activity
	    JTextField searchField = new JTextField("Search for user (Name, Cashtag, Email, Phone Number)");
	    searchField.setToolTipText("Search for user (Name, Cashtag, Email, Phone Number)");
	    searchField.setBounds(10, 135, 680, 40);
	    contentPane.add(searchField);
	   
	    // display users
		JTextArea userList = new JTextArea();
		userList.setBounds(10, 180, 680, 180);
		userList.setEditable(false);
		userList.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		contentPane.add(userList);
		
		// display incoming requests/msgs
		incoming = new JTextArea();
		incoming.setBounds(10, 370, 680, 140);
		incoming.setEditable(false);
		incoming.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		JScrollPane scroll = new JScrollPane (incoming);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(10, 370, 680, 140);
		contentPane.add(scroll);
        
        // bottom area displays IP addresses connected 
        bottom = new JTextArea();
		bottom.setBounds(710, 135, 250, 374);
		bottom.setEditable(false);
		bottom.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		contentPane.add(bottom);
		
		JButton exitButton = new JButton("EXIT");
		exitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				int result = JOptionPane.showConfirmDialog(null, "Do you really want to exit the Cash App Server?", "Exit Application", JOptionPane.INFORMATION_MESSAGE);
				//JOptionPane.INFORMATION_MESSAGE: Yes, No, Exit
				if(result == JOptionPane.OK_OPTION){
					dispose();
					System.exit(0);
				}
			}
		});
		exitButton.setBounds(25, 520, 110, 30);
		exitButton.setBorderPainted(false);
		exitButton.setOpaque(true);
		exitButton.setBackground(new Color(28,236,79));
		exitButton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		exitButton.setForeground(Color.WHITE);
		contentPane.add(exitButton);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton viewButton = new JButton("VIEW");
		viewButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				dataAnalytics second = new dataAnalytics();   
		        setVisible(false); // Hide current frame
		        second.setVisible(true);
			}
		});
		viewButton.setBounds(150, 520, 110, 30);
		viewButton.setBorderPainted(false);
		viewButton.setOpaque(true);
		viewButton.setBackground(new Color(28,236,79));
		viewButton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		viewButton.setForeground(Color.WHITE);
		contentPane.add(viewButton);
		
		// clock
        Thread refreshTitleBar = new Thread(){
            public void run(){ 
                 while (true){//Keep running while true 
                     try {
                       // Display time
                       Date date = new Date();
                       String str = String.format("%tc", date); //format the clock string

                       String title = "Cash App Server | " + str; //concatenate the time with the title
                       setTitle(title);

                       // sleep for 1 second
                       sleep(1000L); 
                       }
                     catch (InterruptedException e) {}//don't know what it does here cuz ide says so
                     finally{}//same as catch
                 }
             }
          };
          refreshTitleBar.start();
          
          Thread refreshThreadCA = new Thread(){
  			public void run(){ 	
  				socketServer.runSockServer();
  		    }
  		 };

  		 refreshThreadCA.start();
    }
	
}
