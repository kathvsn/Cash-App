import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import javax.swing.*;

public class dataAnalytics extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public dataAnalytics(){

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
	  
	    InetAddress ipAddress = null;
	    try{
	      ipAddress = InetAddress.getLocalHost();
	    }catch (UnknownHostException el){
	      el.printStackTrace();
	    }

	    String ip = ipAddress.getHostAddress();

	    // display IP and ports
	    String display = "IP: " + ip + "        Listening on Port #: 8888";
	    JTextArea displayIP = new JTextArea(display);
	    displayIP.setBounds(10, 100, 980, 30);
		displayIP.setEditable(false);
		contentPane.add(displayIP);
	   
	    // filter by label + uneditable text field
		JLabel filterBy = new JLabel("Filtered By: ");
		filterBy.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 20));
		filterBy.setForeground(new Color(147,141,141));
		filterBy.setBounds(10, 145, 150, 25);
		contentPane.add(filterBy);
		
		// list to choose what values to filter by
		String[] filterDate = {"--", "All time", "Weekly", "Monthly", "Yearly"};
		JComboBox<String> dateFilter = new JComboBox<String>(filterDate);
		dateFilter.setBounds(780, 135, 200, 40);
		contentPane.add(dateFilter);
		
		
		JTextArea cashtags = new JTextArea("Cashtags");
		cashtags.setBounds(10, 180, 150, 320);
		cashtags.setEditable(false);
		contentPane.add(cashtags);
		
		JTextArea totalReqAmt = new JTextArea("Total Req Amt: ");
		totalReqAmt.setBounds(170, 180, 180, 320);
		totalReqAmt.setEditable(false);
		contentPane.add(totalReqAmt);
		
		JTextArea totalReqNum = new JTextArea("Total Req Num: ");
		totalReqNum.setBounds(360, 180, 140, 320);
		totalReqNum.setEditable(false);
		contentPane.add(totalReqNum);
		
		JTextArea totalPaidAmt = new JTextArea("Total Paid Amt: ");
		totalPaidAmt.setBounds(510, 180, 180, 320);
		totalPaidAmt.setEditable(false);
		contentPane.add(totalPaidAmt);
		
		JTextArea totalPaidNum = new JTextArea("Total Paid Num: ");
		totalPaidNum.setBounds(700, 180, 140, 320);
		totalPaidNum.setEditable(false);
		contentPane.add(totalPaidNum);
		
		JTextArea totalFees = new JTextArea("Total Fees: ");
		totalFees.setBounds(850, 180, 140, 320);
		totalFees.setEditable(false);
		contentPane.add(totalFees);
		
		
		
		JButton backBtn = new JButton("BACK");
		backBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				serverSearch mainPage = new serverSearch();   
		        setVisible(false); // Hide current frame
		        mainPage.setVisible(true);
			}
		});
		backBtn.setBounds(25, 520, 110, 30);
		backBtn.setBorderPainted(false);
		backBtn.setOpaque(true);
		backBtn.setBackground(new Color(28,236,79));
		backBtn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		backBtn.setForeground(Color.WHITE);
		contentPane.add(backBtn);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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

                       // sleep for 1 second
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
