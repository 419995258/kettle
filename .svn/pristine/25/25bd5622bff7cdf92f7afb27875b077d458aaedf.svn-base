package org.my431.activeMQ;

import javax.jms.Topic;

import org.springframework.jms.core.JmsTemplate;

/**
 * 
 * @author wangzhen
 * 
 * topic 生产者
 *
 */
public class TopicMessageProducer {
    
    private JmsTemplate template;

	private Topic destination;

	public void setTemplate(JmsTemplate template) {
		this.template = template;
	}

	public void setDestination(Topic destination) {
		this.destination = destination;
	}

	public void send(String message) {
		template.convertAndSend(this.destination, message);
	}
}
