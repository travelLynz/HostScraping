package airbnbInfoHost.airbnb_info_host.util;

import java.util.LinkedList;

public class Guest {

	private String id;
	private String name;
	private String city;
	private String membershipDate;
	private boolean superhost;
	private boolean verified;
	private String description;
	private LinkedList<String> linkedAccountVerified;
	private String schoolInfo;
	private String jobInfo;
	private String languages;
	private int reviewNumber;
	private int guideNumber;
	private int whishListNumber;

	public Guest() {
		super();
		id = "null";
		name = "null";
		city = "null";
		membershipDate = "null";
		description = "null";
		linkedAccountVerified = new LinkedList<String>();
		schoolInfo = "null";
		jobInfo = "null";
		languages = "null";
		reviewNumber = 0;
		guideNumber = 0;
		whishListNumber = 0;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getMembershipDate() {
		return membershipDate;
	}
	public void setMembershipDate(String membershipDate) {
		this.membershipDate = membershipDate;
	}
	public boolean isSuperhost() {
		return superhost;
	}
	public void setSuperhost(boolean superhost) {
		this.superhost = superhost;
	}
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getSchoolInfo() {
		return schoolInfo;
	}
	public void setSchoolInfo(String schoolInfo) {
		this.schoolInfo = schoolInfo;
	}
	public String getJobInfo() {
		return jobInfo;
	}
	public void setJobInfo(String jobInfo) {
		this.jobInfo = jobInfo;
	}
	public int getReviewNumber() {
		return reviewNumber;
	}
	public void setReviewNumber(int reviewNumber) {
		this.reviewNumber = reviewNumber;
	}
	public int getGuideNumber() {
		return guideNumber;
	}
	public void setGuideNumber(int guideNumber) {
		this.guideNumber = guideNumber;
	}
	public int getWhishListNumber() {
		return whishListNumber;
	}
	public void setWhishListNumber(int whishListNumber) {
		this.whishListNumber = whishListNumber;
	}

	public LinkedList<String> getLinkedAccountVerified() {
		return linkedAccountVerified;
	}
	public void setLinkedAccountVerified(LinkedList<String> linkedAccountVerified) {
		this.linkedAccountVerified = linkedAccountVerified;
	}
	public String getLanguages() {
		return languages;
	}
	public void setLanguages(String languages) {
		this.languages = languages;
	}
	@Override
	public String toString() {
		return  id + "==" + name + "==" + city + "==" + membershipDate  + "==" + superhost + "==" + 
				verified+ "==" +description+ "==" + linkedAccountVerified + "==" + schoolInfo+ "==" +
				jobInfo+ "==" + languages+ "==" +reviewNumber+ "==" + guideNumber+ "==" + whishListNumber;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}


}
