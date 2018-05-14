package airbnbInfoHost.airbnb_info_host;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import airbnbInfoHost.airbnb_info_host.util.Host;

/**
 * Hello world!
 *
 */
public class ScrapingInfo 
{
	public static void main( String[] args )
	{
		Document doc;
		Host h = new Host();
		try {
			String hostId="4326883";
			
			doc = Jsoup.connect("https://www.airbnb.it/users/show/"+hostId).get();

			Elements newsHeadlines = doc.select("main");
			for (Element headline : newsHeadlines) {
				String name= headline.select("h1").html().replace("Ciao, sono ", "").replace("!", "");
				h.setName(name);
				String[] cityDate = headline.getElementsByClass("h5 space-top-2").text().split("· Membro dal ");
				h.setCity(cityDate[0]);
				h.setMembershipDate(cityDate[1]);
				System.out.println(headline.html());
				Elements description = headline.select(".space-top-2 p");
				h.setDescription(description.text());
				String numReview = headline.getElementsByClass("_e296pg").text();
				h.setReviewNumber(Integer.parseInt(numReview));
				String superHostVerified = headline.getElementsByClass("_mstzcu").text();
				if(superHostVerified.contains("Superhost"))
					h.setSuperhost(true);
				if(superHostVerified.contains("Verified"))
					h.setVerified(true);
				String verifiedElWhishGuideSocial = headline.getElementsByClass("space-3").text();
				if(verifiedElWhishGuideSocial.contains("Whish List")) {
					String wl = verifiedElWhishGuideSocial.split("Wish List (")[1].split(")")[0];
					h.setWhishListNumber(Integer.parseInt(wl));
				}
//				if(verifiedElWhishGuideSocial.contains("Guide")) {
//					String g = verifiedElWhishGuideSocial.split("Guide (")[1].split(")")[0];
//					h.setGuideNumber(Integer.parseInt(g));
//				}
				h.setLinkedAccountVerified(new LinkedList<String>(Arrays.asList(verifiedElWhishGuideSocial.split("verificato"))));
				System.out.println(headline.getElementsByAttribute("dl").text());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(h.toString());

	}
}
