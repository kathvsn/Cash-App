import java.io.IOException;
import java.io.BufferedReader;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class socketServer implements Runnable
{
	   Socket csocket;
	   String ipString;
	   char threadType;

	   static Vector<String> vec = new Vector<String>(5);
	   
	   static final String newline = "\n";
	   static int first_time = 1;
	   
	   static int port_num = 3333;
	   
	   static int numOfConnections = 0;
	   static int numOfMessages = 0;
	   static int max_connections = 5;
	   static int numOfTransactions = 0; 
	   public String keyString;

	   socketServer(Socket csocket, String ip)
	   {
	      this.csocket  = csocket;
	      this.ipString = ip;
	   } 

	   public static void runSockServer()   // throws Exception
	   {
	     boolean sessionDone = false;
	  
	     ServerSocket ssock = null;
	   
	     try
	     {
		   ssock = new ServerSocket(port_num);
	     }
	     catch (BindException e)
	     {
		    e.printStackTrace();
	     }
	     catch (IOException e)
	     {
		    e.printStackTrace();
	     }
	 
	     // update the status text area to show progress of program
	     try 
	     {
		     InetAddress ipAddress = InetAddress.getLocalHost();
		     serverSearch.incoming.append("IP Address : " + ipAddress.getHostAddress() + newline);
	     }
	     catch (UnknownHostException e1)
	     {
		    // TODO Auto-generated catch block
		    e1.printStackTrace();
	     }
	 
	     serverSearch.incoming.append("Listening on port " + port_num + "\r\n");
	 
	     
	     sessionDone = false;
	     while (sessionDone == false)
	     {
	        Socket sock = null;
		    try
		    {
		       //	
  	     	   // blocking system call
		       //	
			   sock = ssock.accept();
		    }
		    catch (IOException e)
		    {
			   e.printStackTrace();
		    }
		 
		    // update the status text area to show progress of program
	        serverSearch.incoming.append("Client Connected : " + sock.getInetAddress() + newline);
	        
	        //
	        // start a NEW THREAD process
	        //
	        new Thread(new socketServer(sock, sock.getInetAddress().toString())).start();
	     }
	 
	     try 
	     {
		    ssock.close();
	     }
	     catch (IOException e) 
	     {
		    e.printStackTrace();
	     }
	}	  

	
	//
	// CLIENT THREAD CODE - This is the thread code that ALL clients will run()
	//
	public void run()
	{
	   try
	   {
		  boolean session_done = false; 
		  long threadId;
	      String clientString;
	      keyString = "";
	    
	      threadId = Thread.currentThread().getId();
	      
	      numOfConnections++;
	      
	      serverSearch.incoming.append("Num of Connections = " + numOfConnections + newline);
	      
	      keyString = ipString + ":" + threadId;
	      
	      //
	      // write IP address of the client who just connected to the main screen
	      //
	      if (vec.contains(keyString) == false)
	      {
	    	    int counter = 0;
	        	vec.addElement(keyString);
	        	
	        	serverSearch.bottom.setText("");
	        	Enumeration<String> en = vec.elements();
	        	while (en.hasMoreElements())
	        	{
	        		serverSearch.bottom.append(en.nextElement() + "\r\n");
	        		
	        		if (++counter >= 6)
	        		{
	        			serverSearch.bottom.append("\r\n");
	        			counter = 0;
	        		}
	        	}
	      }
	       
	      PrintStream pstream = new PrintStream (csocket.getOutputStream());
	      BufferedReader rstream = new BufferedReader(new InputStreamReader(csocket.getInputStream()));
	      String reqStr = "";
	      String payStr = "";
	      boolean paid = false;
	      boolean requested = false;
	       
	      while (session_done == false)
	      {
	       	if (rstream.ready())   // check for any data messages
	       	{
	              clientString = rstream.readLine();
	              
	              // update the status text area to show progress of program
	              serverSearch.incoming.append("RECV : "+clientString+newline);
	     	       
	     	       // update the status text area to show progress of program
	              serverSearch.incoming.append("RLEN : "+clientString.length()+newline);
	              
	              if (clientString.length() > 128)
	              {
	           	   session_done = true;
	           	   continue;
	              }
	              // keep this if using same computer //
	              
	              /*if(clientString.contains("paid") || clientString.contains("requested") || clientString.contains("~")) {
		            	 int counter = 0;
		            	 numOfConnections--;
		                 vec.remove(vec.indexOf(keyString));
		                 serverSearch.bottom.setText("");
		                 Enumeration<String> en = vec.elements();
		                 while (en.hasMoreElements()){
		 	        		serverSearch.bottom.append(en.nextElement() + "\r\n");
		 	        		
		 	        		if (++counter >= 6){
		 	        			serverSearch.bottom.append("\r\n");
		 	        			counter = 0;
		 	        		}
		 	        	}
		                serverSearch.incoming.append("Num of Connections = " + numOfConnections);
	              }*/
	              if (clientString.contains("quit") || clientString.contains("QUIT") || clientString.contains("Quit"))
	              {
	            	 int counter = 0;
	                 session_done = true;
	                 vec.remove(vec.indexOf(keyString));
	                 serverSearch.bottom.setText("");
	                 Enumeration<String> en = vec.elements();
	                 while (en.hasMoreElements()){
	 	        		serverSearch.bottom.append(en.nextElement() + "\r\n");
	 	        		
	 	        		if (++counter >= 6){
	 	        			serverSearch.bottom.append("\r\n");
	 	        			counter = 0;
	 	        		}
	 	        	}
	              }
	              else if (clientString.contains("Date>"))
	              {
	            	numOfMessages++;
	            	  
	            	// Create an instance of SimpleDateFormat used for formatting 
	            	// the string representation of date (month/day/year)
	            	DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

	            	// Get the date today using Calendar object.
	            	Date today = Calendar.getInstance().getTime();
	            	
	            	// Using DateFormat format method we can create a string 
	            	// representation of a date with the defined format.
	            	String reportDate = df.format(today);

	            	//
	            	// Print what date is today! Send to the individual THREAD
	            	//
	            	pstream.println("Num Of Messages : " + numOfMessages + "   Simple Date: " + reportDate + newline);
	              }
	              
	              // IF USING DIFFERENT COMPUTERS
	              if(clientString.contains("paid")) {
	            	  fileIO fio = new fileIO("cashAppPayments.txt");
	            	  fio.wrData(clientString + "\n");
	            	  payStr = clientString;
	            	  paid = true;
	              }
	              
	              if(clientString.contains("requested")) {
	                serverSearch.incoming.append("Num of Connections = " + numOfConnections);
	            	  fileIO fio2 = new fileIO("cashAppRequests.txt");
	            	  fio2.wrData(clientString + "\n");
	            	  reqStr = clientString;
	            	  requested = true;
	              }
	              
	              
	              if(clientString.contains("/")) {
		            	 int counter = 0;
		            	 numOfConnections--;
		                 vec.remove(vec.indexOf(keyString));
		                 serverSearch.bottom.setText("");
		                 Enumeration<String> en = vec.elements();
		                 while (en.hasMoreElements()){
		 	        		serverSearch.bottom.append(en.nextElement() + "\r\n");
		 	        		
		 	        		if (++counter >= 6){
		 	        			serverSearch.bottom.append("\r\n");
		 	        			counter = 0;
		 	        		}
		 	        	}
		                serverSearch.incoming.append("Num of Connections = " + numOfConnections);
	            	  String[] tag =  clientString.split("/");
	            	  if(requested == true) {
	            		  String msR = " " + tag[0] + "_REQUEST";
	            		  reqStr = reqStr + msR;
	            		  fileIO fioR = new fileIO("numRequests.txt");
	            		  fioR.wrData(reqStr + "\n");
	            		  requested = false;
	            	  }
	            	  else if(paid == true) {
				    	  String msP = " " + tag[0] + "_PAYMENT";
				    	  payStr = payStr + msP;
				    	  fileIO fioP = new fileIO("numPayments.txt");
				    	  fioP.wrData(payStr + "\n");
				    	  paid = false;
				    	  
			    			fileIO fio3 = new fileIO("userSent.txt");
			    			fileIO fio4 = new fileIO("userRcv.txt");
			    			String sent = tag[0] + ":" + tag[1];
			    			String rcv = tag[2] + ":" + tag[1];
			    			fio3.wrData(sent);
			    			fio4.wrData(rcv);
	            	  }
			    	  
				      reqStr = "";
				      payStr = "";
			    	  
	            	  Vector<String> transactionHistory = new Vector<>();
	            	  Vector<String> requests = new Vector<>();
	            	  Vector<String> payments = new Vector<>();
			    		try {
			    			TransactionSearch fs = new TransactionSearch();
			    			TransactionSearch fs2 = new TransactionSearch();
			    			TransactionSearch fs3 = new TransactionSearch();
			    			transactionHistory = fs.returnInfo("/Users/kathytran/eclipse-workspace/Cash App/cashAppRequests.txt", tag[0], false, false);
			    			requests = fs2.returnInfo("/Users/kathytran/eclipse-workspace/Cash App/numRequests.txt", tag[0], false, true);
			    			payments = fs3.returnInfo("/Users/kathytran/eclipse-workspace/Cash App/numPayments.txt", tag[0], true, false);
			    		}
			    		catch(IOException e){
			    			e.printStackTrace();
			    		}
			    		
			    		
	              }
	              else
	              {
	            	  pstream.println("NACK : ERROR : No such command!");
	              }
	       	   }
	         			    		        	
	           Thread.sleep(500);
	           
	        }    // end while loop
	
            keyString = ipString + ":" + threadId;
	      
	      
	        numOfConnections--;

	        // close client socket
	        csocket.close();
	       
	        // update the status text area to show progress of program
	        serverSearch.incoming.append("Child Thread : " + threadId + " : is Exiting!!!" + newline);
	        serverSearch.incoming.append("Num of Connections = " + numOfConnections);
	        
		     
	     } // end try  
	     catch (SocketException e)
	     {
		  // update the status text area to show progress of program
	    	 serverSearch.incoming.append("ERROR : Socket Exception!" + newline);
	     }
	     catch (InterruptedException e)
	     {
		  // update the status text area to show progress of program
	    	 serverSearch.incoming.append("ERROR : Interrupted Exception!" + newline);
	     }
	     catch (UnknownHostException e)
	     {
		  // update the status text area to show progress of program
	    	 serverSearch.incoming.append("ERROR : Unkonw Host Exception" + newline);
	     }
	     catch (IOException e) 
	     {
	     // update the status text area to show progress of program
	    	 serverSearch.incoming.append("ERROR : IO Exception!" + newline);
	     }     
	     catch (Exception e)
	     { 
	    	 if(numOfConnections-1 >= 0) {
	    		 numOfConnections--;
	    	 };
	    	 
	    	 serverSearch.bottom.setText("");
	    	 serverSearch.bottom.append(vec.get(0));

		  // update the status text area to show progress of program
		  //serverSearch.incoming.append("ERROR : Generic Exception!" + newline);
		  serverSearch.incoming.append("Num of Connections = " + numOfConnections);
	     }
	   
	  }  // end run() thread method
}