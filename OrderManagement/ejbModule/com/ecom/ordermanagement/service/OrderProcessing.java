package com.ecom.ordermanagement.service;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import com.shoppingcart.model.dto.Order;
import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;

/**
 * Message-Driven Bean implementation class for: OrderProcessing
 */
@MessageDriven(mappedName = "test/myJMSQueue", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class OrderProcessing implements MessageListener {

	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {
			ObjectMessage tMessage = (ObjectMessage) message;
			try {
				Order order = (Order) tMessage.getObject();
				System.out.println("we are processing the "+order);
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
		
		/*if (message instanceof TextMessage) {
			TextMessage tMessage = (TextMessage) message;
			try {
				String order = (String) tMessage.getText();
				System.out.println(order);
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}*/
	}

}
