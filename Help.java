import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Help extends JFrame {
	private static final long serialVersionUID = 1L;
	public Help(int fmnum) {
		setSize(1000, 600);
		JPanel Help= new JPanel();
		setContentPane(Help);
		Help.setLayout(null);
		
		getContentPane().setBackground(new Color(247, 247, 247));
		
		// drop down box to be worked on later
//		 String[] optionsToChoose = {"Invalid Card", "Forgot my Password", "Restricted Account", "Scammed"};
//		 JComboBox<String> jComboBox = new JComboBox<>(optionsToChoose);
//		 jComboBox.setBounds(20, 165, 140, 20);
//		 JButton jButton;
//		 JLabel jLabel = new JLabel();
//		 jLabel.setBounds(20, 165, 400, 130);
//		 jButton = new JButton ("Search");
//		 // action to select
//		 jButton.addActionListener(new ActionListener() {
//	    
//			 		public void actionPerformed(ActionEvent e) {
//	                String selectedFruit = "You selected " + jComboBox.getItemAt(jComboBox.getSelectedIndex());
//	                jLabel.setText(selectedFruit);
//	           }
//	        });

			//JLabel c = new JLabel("Cash App");
			
			//ctag.setText("$CashTag");
//			c.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 30));
//			c.setForeground(Color.black);
//			c.setBounds(17, -30,300,300);
//			
//			JLabel b = new JLabel("What is Your Issue?");
//			//ctag.setText("$CashTag");
//			b.setFont(new Font("Arial Rounded MT ", Font.PLAIN, 15));
//			b.setForeground(Color.black);
//			b.setBounds(20, 0,300,300);
			//so what the drop down is going to do is go to the question and quick link it below
			//with the faqs on the right
			
			//FAQS
			//First Question
			JLabel  f1= new JLabel("Cash App FAQ's");
			f1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 20));
			f1.setForeground(Color.black);
			f1.setBounds(50, -120,300,300);
			
			JLabel  f2= new JLabel("How do I deposit funds into my Cashapp?");
			f2.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
			f2.setForeground(Color.black);
			f2.setBounds(50, -70, 400, 300);
			
			JLabel  f3= new JLabel("1. You log into your Cashapp");
			f3.setFont(new Font("Arial Rounded MT", Font.PLAIN, 13));
			f3.setForeground(Color.black);
			f3.setBounds(50, -30, 500, 300);
			
			JLabel  f4= new JLabel("2. You click on the Cash Card Tab");
			f4.setFont(new Font("Arial Rounded MT", Font.PLAIN, 13));
			f4.setForeground(Color.black);
			f4.setBounds(50, 0, 300, 300);
			
			JLabel  f5= new JLabel("3. Then from this page you would be able to add new card information or deposit or withdraw funds into Cash App");
			f5.setFont(new Font("Arial Rounded MT", Font.PLAIN, 13));
			f5.setForeground(Color.black);
			f5.setBounds(50, 30, 600, 300);
			
			
			JLabel  f6= new JLabel("What if someone stole my account information and made transactions?");
			f6.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
			f6.setForeground(Color.black);
			f6.setBounds(50, 70, 700, 300);
			
			JLabel  f7= new JLabel("1. You should contact us at Cash App Support at 1-800-8888 or you can email us at TotalyCashApp@hotmail.com");
			f7.setFont(new Font("Arial Rounded MT", Font.PLAIN, 13));
			f7.setForeground(Color.black);
			f7.setBounds(50, 100, 600, 300);
			
			JLabel  f8= new JLabel("2. Before contacting us you should have your passport, drivers license, and bank information handy in order to confirm your identity");
			f8.setFont(new Font("Arial Rounded MT", Font.PLAIN, 13));
			f8.setForeground(Color.black);
			f8.setBounds(50, 130, 800, 300);
			
			JLabel  f9= new JLabel("Does CashApp have a transaction fee when transferring money?");
			f9.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
			f9.setForeground(Color.black);
			f9.setBounds(50, 170, 800, 300);
			
			JLabel  f10= new JLabel("While using Cash App and its related Cash Card is totally free, there are a few fees you will face as you start using the app.");
			f10.setFont(new Font("Arial Rounded MT", Font.PLAIN, 13));
			f10.setForeground(Color.black);
			f10.setBounds(50, 210, 800, 300);
			
			JLabel  f11= new JLabel("Fortunately, these fees are avoidable.");
			f11.setFont(new Font("Arial Rounded MT", Font.PLAIN, 13));
			f11.setForeground(Color.black);
			f11.setBounds(50, 240, 700, 300);
			
			JLabel  f12= new JLabel("Cash App charges a 3 percent fee if you use a credit card to send money, but making payments with a debit card or bank account is free.");
			f12.setFont(new Font("Arial Rounded MT", Font.PLAIN, 13));
			f12.setForeground(Color.black);
			f12.setBounds(50, 270, 700, 300);
	
			JLabel  f13= new JLabel("Cash App also charges a 3 percent fee if you request an Instant Transfer of funds from your Cash App account to your linked debit card.");
			f13.setFont(new Font("Arial Rounded MT", Font.PLAIN, 13));
			f13.setForeground(Color.black);
			f13.setBounds(50, 300, 700, 300);
			
			JLabel  f14= new JLabel("If you request to transfer money to transfer money to a bank account, the transfer is free — however, you must wait 1-3 business days ");
			f14.setFont(new Font("Arial Rounded MT", Font.PLAIN, 13));
			f14.setForeground(Color.black);
			f14.setBounds(50, 320, 700, 300);
			
			JLabel f15=new JLabel("for the transaction to process.");
			f15.setFont(new Font("Arial Rounded MT", Font.PLAIN, 13));
			f15.setForeground(Color.black);
			f14.setBounds(50, 340, 700, 300);
			
			JButton logOut = new JButton("Back");
			logOut.setBounds(870, 525, 110, 30);
			logOut.setBorderPainted(false);
			logOut.setOpaque(true);
			logOut.setBackground(new Color(28,236,79));
			logOut.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
			logOut.setForeground(Color.WHITE);;
			
			logOut.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent arg0) {
			    	int result = JOptionPane.showConfirmDialog(null,"Do you really want to go to the previous page?", "Back", JOptionPane.INFORMATION_MESSAGE);
			    	if (result==JOptionPane.OK_OPTION) {
			    		if (fmnum == 1) {
			    			SignIn second = new SignIn();   
				        	setVisible(false); //Hide current frame
				        	second.setVisible(true);
			    		}
			    		else if (fmnum == 2) {
			    			CreateAccount second = new CreateAccount();   
				        	setVisible(false); //Hide current frame
				        	second.setVisible(true);
			    		}
			    		else if (fmnum == 3) {
				        	setVisible(false); //Hide current frame
			    		}
			    	}
			    }
			});
			
		 // adding compenents to the panel
//		 Help.add(jComboBox);
//		 Help.add(jButton);
//	s	 Help.add(c);
		// Help.add(b);
		 Help.add(f1);
		 Help.add(f2);
		 Help.add(f3);
		 Help.add(f4);
		 Help.add(f5);
		 Help.add(f6);
		 Help.add(f7);
		 Help.add(f8);
		 Help.add(f9);
		 Help.add(f10);
		 Help.add(f11);
		 Help.add(f12);
		 Help.add(f13);
		 Help.add(f14);
		 Help.add(logOut);
		
	}

}
