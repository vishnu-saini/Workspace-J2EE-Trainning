package com;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

public class Consumer implements MessageListener {
	public static void main(String[] args) {
		Connection con = null;
		try {
			InitialContext ctx = new InitialContext();
			ConnectionFactory cf = (ConnectionFactory) ctx.lookup("test/myJMSQueueFC");
			Destination destination = (Destination) ctx.lookup("test/myJMSQueue");

			con = cf.createConnection();

			Session session = con
					.createSession(false, Session.AUTO_ACKNOWLEDGE);

			MessageConsumer consumer = session.createConsumer(destination);
			consumer.setMessageListener(new Consumer());
			con.start();
			
			Thread.sleep(70000);

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			 if(con!=null){
				 try{
					 con.close();
				 }
				 catch(Exception e){}
			 }
		}
	}

	@Override
	public void onMessage(Message message) {
			try{
				if(message instanceof TextMessage){
					TextMessage msg=(TextMessage) message;
					System.out.println("Messasge received:"+msg);
				}
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
	}

}