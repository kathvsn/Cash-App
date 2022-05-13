import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Enumeration;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.util.Vector;

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
		titleLabel.setBounds(430, 20, 400, 40);
		contentPane.add(titleLabel);
		
		JLabel imagelabel = new JLabel();
		imagelabel.setIcon(new ImageIcon(new ImageIcon("C:/Users/tomto/Downloads/Cashapplogos/CashAppLogo.png").getImage().getScaledInstance(65,65, Image.SCALE_DEFAULT)));
		imagelabel.setBounds(340,20,100,40);
		contentPane.add(imagelabel);
	    
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

	   
	    // display users
		JTextArea userList = new JTextArea();
		userList.setBounds(10, 160, 700, 180);
		userList.setEditable(false);
		userList.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		contentPane.add(userList);
	    JScrollPane scrolls = new JScrollPane (userList);
		scrolls.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrolls.setBounds(10, 160, 700, 180);
	    contentPane.add(scrolls);
		
		// display incoming requests/msgs
		incoming = new JTextArea();
		incoming.setBounds(10, 370, 700, 140);
		incoming.setEditable(false);
		incoming.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		JScrollPane scroll = new JScrollPane (incoming);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(10, 370, 700, 140);
		contentPane.add(scroll);
        
        // bottom area displays IP addresses connected 
        bottom = new JTextArea();
		bottom.setBounds(740, 155, 230, 375);
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
		
		JButton searchButton = new JButton("UPDATE");
		searchButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				userList.setText("");
				userList.append("$Cashtag      |     Num of Requests     |      Num of Payments     |     Amt Sent     |    Amt Received\r\n");
				userList.append("--------------------------------------------------------------------------------\n");
				Vector<String> dataUser = new Vector<>();
				Vector<String> reqs = new Vector<>();
				Vector<String> paid = new Vector<>();
				Double sentAmt = 0.0;
				Double rcvAmt = 0.0;
				String curr = "";
				
				try {
					TransactionSearch fs = new TransactionSearch();
					dataUser = fs.returnInfo("C:/Users/tomto/eclipse-workspace/FinalProject/dataCashtags.txt", "$", false, false);
				}
				catch(IOException e){
					e.printStackTrace();
				}
				if(!(dataUser.isEmpty())) {
					for(int i = 0; i < dataUser.size(); i++) {
						curr = curr + String.valueOf(dataUser.get(i));
						// get requests
						try {
							TransactionSearch fs1 = new TransactionSearch();
							reqs = fs1.returnInfo("C:/Users/tomto/eclipse-workspace/FinalProject/numRequests.txt", String.valueOf(dataUser.get(i)), false, true);
						}
						catch(IOException e){
							e.printStackTrace();
						}
						if(!(reqs.isEmpty())) {
							curr = curr + "\t" + reqs.size();
						}
						else {
							curr = curr + "\t" + "0";
						} 
						
						// get number of payments
						try {
							TransactionSearch fs2 = new TransactionSearch();
							paid = fs2.returnInfo("C:/Users/tomto/eclipse-workspace/FinalProject/numPayments.txt", String.valueOf(dataUser.get(i)), true, false);
						}
						catch(IOException e){
							e.printStackTrace();
						}
						
						if(!(paid.isEmpty())) {
							curr = curr + "\t\t" + paid.size();
						}
						else {
							curr = curr + "\t\t" + "0";
						}
						
						
						// get amount of money user sent
						try {
							parseAmount prSent = new parseAmount();
							sentAmt = prSent.returnInfo("C:/Users/tomto/eclipse-workspace/FinalProject/userSent.txt", String.valueOf(dataUser.get(i)));
						}
						catch(IOException e){
							sentAmt = 0.0;
							e.printStackTrace();
						}
						
						if(sentAmt > 0.00) {
							String s = String.valueOf(sentAmt);
							curr = curr + "\t      " + s;
						}
						else {
							curr = curr + "\t      " + "0.00";
						}
						
						// get amount of money user received
						try {
							parseAmount prRcv = new parseAmount();
							rcvAmt = prRcv.returnInfo("C:/Users/tomto/eclipse-workspace/FinalProject/userRcv.txt", String.valueOf(dataUser.get(i)));
						}
						catch(IOException e){
							rcvAmt = 0.0;
							e.printStackTrace();
						}
						
						if(rcvAmt > 0.00) {
							String r = String.valueOf(rcvAmt);
							curr = curr + "\t         " + r;
						}
						else {
							curr = curr + "\t         " + "0.00";
						}
						
						userList.append(curr + "\r\n");
						curr = "";
					}
					
				}
				
			}
		});
		searchButton.setBounds(170, 520, 110, 30);
		searchButton.setBorderPainted(false);
		searchButton.setOpaque(true);
		searchButton.setBackground(new Color(28,236,79));
		searchButton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		searchButton.setForeground(Color.WHITE);
		contentPane.add(searchButton);
		
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
