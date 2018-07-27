package airbnbInfoHost.airbnb_info_host.util;

import java.util.LinkedList;

public class Host {

	private String hostId;
	private LinkedList<String> hostedBy;


	public Host() {
		super();
		hostId = "null";
		hostedBy = new LinkedList<String>();
	
	}
	public void setHostId( String hostId) {
		this.hostId = hostId;
	}
	public void setHostedBy(LinkedList<String>  hostedBy) {
		this.hostedBy = hostedBy;
	}
	@Override
	public String toString() {
		return  hostId + "==" + hostedBy;
	}


}
