package airbnbInfoHost.airbnb_info_host.util;

public class HostGuestReview {

	private String guestId;
	private String month;
	private String year;
	private String hostId;
	private String hostName;
	private String comments;
	private int totalReviewsFromHosts;


	public HostGuestReview() {
		super();
		guestId = "null";
		month = "null";
		year = "null";
		hostId = "null";
		hostName = "null";
		comments = "null";
		totalReviewsFromHosts = 0;
	
	}
	public void setGuestId(String guestId) {
		this.guestId = guestId;
	}
	public void setHostId( String hostId) {
		this.hostId = hostId;
	}
	public void setHostName( String hostName) {
		this.hostName = hostName;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public void setTotalReviewsFromHosts(int totalReviewsFromHosts) {
		this.totalReviewsFromHosts = totalReviewsFromHosts;
	}
	@Override
	public String toString() {
		return  guestId + "==" + hostId + "==" + hostName + "==" + month  + "==" + year  + "==" + comments + "==" + totalReviewsFromHosts;
	}


}
