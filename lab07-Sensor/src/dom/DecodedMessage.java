package dom;

import helper.Action;

public class DecodedMessage {
	private Action action;
	private int interval;
	private String host;
	private int port;
	
	public DecodedMessage(Action action){
		this.action = action;
	}
	
	public void setInterval(int interval){
		this.interval = interval;
	}
	
	public void setHost(String host){
		this.host = host;
	}
	
	public void setPort(int port){
		this.port = port;
	}

	public Action getAction() {
		return action;
	}

	public int getInterval() {
		return interval;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}
	
	
}
