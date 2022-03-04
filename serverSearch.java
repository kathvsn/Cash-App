import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import javax.swing.*;

public class serverSearch extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) throws InterruptedException {
		serverSearch frame = new serverSearch();
		frame.setVisible(true);
	}
	
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
	  
	    
	    // display IP and ports
	    JTextArea displayIP = new JTextArea();
	    displayIP.setBounds(10, 100, 980, 30);
		displayIP.setEditable(false);
		contentPane.add(displayIP);
		
		InetAddress ipAddress = null;
		try{
			ipAddress = InetAddress.getLocalHost();
		}catch (UnknownHostException el){
			el.printStackTrace();
		}
	 
	    // search bar: filter activity
	    JTextField searchField = new JTextField("Search for user (Name, Cashtag, Email, Phone Number)");
	    searchField.setToolTipText("Search for user (Name, Cashtag, Email, Phone Number)");
	    searchField.setBounds(10, 135, 680, 40);
	    contentPane.add(searchField);
	    
	   
		JTextArea userList = new JTextArea();
		userList.setBounds(10, 180, 680, 180);
		userList.setEditable(false);
		contentPane.add(userList);
		
		JTextArea incoming = new JTextArea();
		incoming.setBounds(10, 370, 680, 140);
		incoming.setEditable(false);
		contentPane.add(incoming);
	   
	    // filter by label + uneditable text field
		JLabel filterBy = new JLabel("Filter By");
		filterBy.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 20));
		filterBy.setForeground(new Color(147,141,141));
		filterBy.setBounds(800, 145, 200, 25);
		contentPane.add(filterBy);
		
	    
		JButton nameBtn = new JButton("Name");
		nameBtn.setBounds(700, 200, 150, 25);
		nameBtn.setBorderPainted(false);
		nameBtn.setOpaque(false);
		nameBtn.setForeground(new Color(83,71,71));
        nameBtn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 22));
        contentPane.add(nameBtn);
        
        JButton tagBtn = new JButton("Cashtag");
        tagBtn.setBounds(710, 240, 150, 25);
		tagBtn.setBorderPainted(false);
		tagBtn.setOpaque(false);
		tagBtn.setForeground(new Color(147,141,141));
		tagBtn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 22));
        contentPane.add(tagBtn);
       
        JButton locationBtn = new JButton("Location");
        locationBtn.setBounds(710, 290, 150, 25);
        locationBtn.setBorderPainted(false);
        locationBtn.setOpaque(false);
        locationBtn.setForeground(new Color(147,141,141));
		locationBtn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 22));
        contentPane.add(locationBtn);
        
        JButton accAgeBtn = new JButton("Account Age");
        accAgeBtn.setBounds(710, 340, 200, 25);
		accAgeBtn.setBorderPainted(false);
		accAgeBtn.setOpaque(false);
		accAgeBtn.setForeground(new Color(147,141,141));
		accAgeBtn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 22));
        contentPane.add(accAgeBtn);
        
        JButton mostReqBtn = new JButton("Most Requested");
        mostReqBtn.setBounds(700, 390, 250, 25);
        mostReqBtn.setBorderPainted(false);
        mostReqBtn.setOpaque(false);
        mostReqBtn.setForeground(new Color(147,141,141));
		mostReqBtn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 22));
        contentPane.add(mostReqBtn);
        
        JButton mostPaidBtn = new JButton("Most Requested");
        mostPaidBtn.setBounds(700, 440, 250, 25);
        mostPaidBtn.setBorderPainted(false);
        mostPaidBtn.setOpaque(false);
        mostPaidBtn.setForeground(new Color(147,141,141));
        mostPaidBtn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 22));
        contentPane.add(mostPaidBtn);
		
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
                 while (true){//Keep running while true (aka always)
                     try {
                       // Display time
                       Date date = new Date();
                       String str = String.format("%tc", date); //format the clock string

                       String title = "Cash App Server | " + str; //concatenate the time with the title
                       setTitle(title);

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