package airbnbInfoHost.airbnb_info_host;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import airbnbInfoHost.airbnb_info_host.util.FileHandler;
import airbnbInfoHost.airbnb_info_host.util.FileWriter;
import airbnbInfoHost.airbnb_info_host.util.HostGuestReview;

/**
 * Hello world!
 *
 */
public class HostGuestReviews 
{
	@SuppressWarnings("static-access")
	public static void main( String[] args )
	{
		
		String filenameIn = new File("").getAbsolutePath()+"/guests.csv";
		FileHandler.setFileHandler(filenameIn, null);
		LinkedList<String> ids = new LinkedList<String>(); //Arrays.asList("26831919","4326883")
		String s="";
		while((s=FileHandler.readFile())!=null) 
			ids.add(s);
		
		FileHandler.closeFile();
		int c=0;
		
		
		String filenameOut = new File("").getAbsolutePath()+"/revout.csv";
		String header = "guestId == hostId == hostName == month == year == comments == totalReviewsFromHosts";
	
		FileHandler o = FileWriter.addCSV(filenameOut , header);
		for(String id: ids){
			StringBuilder res = new StringBuilder();
			c++;
			Document doc;
			HostGuestReview rev = new HostGuestReview();
			try {
				doc = Jsoup.connect("https://www.airbnb.com/users/show/"+id).get();

				Elements newsHeadlines = doc.select("main");
				for (Element headline : newsHeadlines) {

					//id
					rev.setGuestId(id);

					Elements guestReviews = headline.getElementsByClass("reviews_section as_guest space-top-3").select(".reviews").select(".row.text-center-sm");
					
					//totalNumberOfReviews
					int totalNumberOfReviews = guestReviews.size();
					rev.setTotalReviewsFromHosts(totalNumberOfReviews);
					
					//revCounter 
					int revCounter = 0;
					for (Element review : guestReviews) {	
						
						Elements top_review = review.getElementsByClass("col-md-2 col-sm-12");
						String[] revInfo = top_review.text().split(" " );
						
						//hostName
						String hostName = revInfo[0];
						rev.setHostName(hostName);
						
						if (revInfo.length > 3) {
							
							//month
							rev.setMonth(revInfo[1]);
							//year
							rev.setYear(revInfo[2]);
							
						}
							
						Elements link = top_review.select(".avatar-wrapper > a");
						String[] hostLink = link.attr("href").split("/");
						
						//hostId
						String hostId = hostLink[hostLink.length-1];
						rev.setHostId(hostId);
						
						Elements bottom_review = review.getElementsByClass("col-md-10 col-sm-12").select(".expandable-content");
						
						//comments
						String comments = bottom_review.text();
						rev.setComments(comments);
						
						revCounter++;
						if (revCounter < totalNumberOfReviews ) 
							res.append(rev.toString()+"\n");
						else 
							res.append(rev.toString());

					}

					

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
			int sec = (int)(Math.random()*((60-10)+10)*1000);
			System.out.println(c+") Wait for "+sec +" milliseconds");
			try {
				Thread.sleep(sec);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (res.length() != 0) 
				o.writeFileLine(res.toString());
						
		}
		o.closeFile();
		System.out.println("File revout.csv written");
	}
}
