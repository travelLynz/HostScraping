package airbnbInfoHost.airbnb_info_host;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import airbnbInfoHost.airbnb_info_host.util.Constants;
import airbnbInfoHost.airbnb_info_host.util.Host;

/**
 * Hello world!
 *
 */
public class ScrapingInfo 
{
	public static void main( String[] args )
	{
		LinkedList<String> hostIds = new LinkedList<String>(Arrays.asList("26831919","4326883"));

		for(String hostId: hostIds){
			Document doc;
			Host h = new Host();
			try {
				doc = Jsoup.connect("https://www.airbnb.it/users/show/"+hostId).get();

				Elements newsHeadlines = doc.select("main");
				for (Element headline : newsHeadlines) {

					//name
					String name= headline.select("h1").html().replace(Constants.GREETING, "").replace("!", "");
					h.setName(name);
					String[] cityDate = headline.getElementsByClass("h5 space-top-2").text().split(Constants.MEMBERSHIP_DATE);

					//city
					h.setCity(cityDate[0]);

					//membership daye
					h.setMembershipDate(cityDate[1]);

					//description
					Elements description = headline.select(".space-top-2 p");
					h.setDescription(description.text());

					//number of review
					String numReview = headline.getElementsByClass("_e296pg").text();
					h.setReviewNumber(Integer.parseInt(numReview));

					String superHostVerified = headline.getElementsByClass("_mstzcu").text();

					//superhost
					if(superHostVerified.contains(Constants.SUPERHOST))
						h.setSuperhost(true);

					//verified
					if(superHostVerified.contains(Constants.VERIFIED))
						h.setVerified(true);
					String verifiedElWhishGuideSocial = headline.getElementsByClass("space-3").text();

					//number of wishlist
					if(verifiedElWhishGuideSocial.contains(Constants.WISHLIST)) {
						String wl = verifiedElWhishGuideSocial.split(Constants.WISHLIST)[1].split(" ")[0];
						h.setWhishListNumber(Integer.parseInt(wl.replace("(", "").replace(")", "")));
					}

					//number of guide
					if(verifiedElWhishGuideSocial.contains(Constants.GUIDE)) {
						String g = verifiedElWhishGuideSocial.split(Constants.GUIDE)[1].split(" ")[0];
						h.setGuideNumber(Integer.parseInt(g.replace("(", "").replace(")", "")));
					}
					h.setLinkedAccountVerified(new LinkedList<String>(Arrays.asList(verifiedElWhishGuideSocial.split("verificato"))));
					String info = headline.select("div.panel-body dl").text();

					//school
					if(info.contains(Constants.SCHOOL))
						h.setSchoolInfo(info.split(Constants.SCHOOL)[1].split(" ")[0]);

					//job
					if(info.contains(Constants.JOB))
						h.setJobInfo(info.split(Constants.JOB)[1].split(" ")[0]);

					//Language
					if(info.contains(Constants.LANG))
						h.setLanguages(info.split(Constants.LANG)[1].split(" ")[0]);

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println(h.toString());
			int sec = (int)(Math.random()*((60-10)+10)*1000);
			System.out.println("Wait for "+sec +" milliseconds");
			try {
				Thread.sleep(sec);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
