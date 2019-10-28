package com.stackroute.helpdesk.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPubSub;

@Component
public class JedisPubSubConfiguration {

	@Autowired
	private ChannelHandler channelHandler;

		private static JedisPubSub jedisPubSub = new JedisPubSub() {
		@Override
		public void onPMessage(String pattern, String channel, String message) {
			System.out.println("Channel " +channel+ " has sent a message : " +message+ " on pattern " + pattern);
			onMessage(channel, message);
			if(pattern.equals("C*")) {
				/* Unsubscribe from pattern C* after first message is received. */
				punsubscribe(pattern);
			}
		}

		@Override
		public void onPSubscribe(String pattern, int subscribedChannels) {
			psubscribe(pattern);
			ChannelHandler channelHandler = new ChannelHandler();
//			channelHandler.addMessageListener();
//			onMe
//			System.out.println(subscribedChannels);
		}

		@Override
		public void onPUnsubscribe(String pattern, int subscribedChannels) {
			System.out.println("Client is Unsubscribed from pattern : "+ pattern);
			System.out.println("Client is Subscribed to "+ subscribedChannels + " no. of patterns");
		}

		@Override
		public void onMessage(String channel, String message) {
			System.out.println("  <<< SUBSCRIBE< Channel:" + channel + " >Message received:" + message );
			//When a quit message is received, the subscription is canceled (passively)
			if(message.equalsIgnoreCase("quit")){
				this.unsubscribe(channel);
			}
		}
	};

	public static JedisPubSub getJedisPubSub() {
		return jedisPubSub;
	}
}
