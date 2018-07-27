package airbnbInfoHost.airbnb_info_host;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import airbnbInfoHost.airbnb_info_host.util.FileHandler;
import airbnbInfoHost.airbnb_info_host.util.FileWriter;
import airbnbInfoHost.airbnb_info_host.util.Host;

/**
 * Hello world!
 *
 */
public class HostsHosts
{
	@SuppressWarnings("static-access")
	public static void main( String[] args )
	{
		
		String filenameIn = new File("").getAbsolutePath()+"/hostID.csv";
		FileHandler.setFileHandler(filenameIn, null);
		LinkedList<String> ids = new LinkedList<String>(); //Arrays.asList("26831919","4326883")
		String s="";
		while((s=FileHandler.readFile())!=null) 
			ids.add(s);
		
		FileHandler.closeFile();
		int c=0;
		
		
		String filenameOut = new File("").getAbsolutePath()+"/hostInfo.csv";
		String header = "hostId == hostedBy";
	
		FileHandler o = FileWriter.addCSV(filenameOut , header);
		for(String id: ids){
			StringBuilder res = new StringBuilder();
			c++;
			Document doc;
			Host host = new Host();
			try {
				doc = Jsoup.connect("https://www.airbnb.it/users/show/"+id).get();

				Elements newsHeadlines = doc.select("main");
				for (Element headline : newsHeadlines) {

					//id
					host.setHostId(id);
					Elements hostReviews = headline.getElementsByClass("reviews_section as_guest space-top-3").select(".reviews").select(".row.text-center-sm");
					
//					Elements people = hostReviews.getElementsByClass("link-reset");
					List<String> hosts = new ArrayList<String>();
					for (Element review : hostReviews) {
						Element person = review.getElementsByClass("col-md-10 col-sm-12").select("span").first();
						hosts.add("("+ person.text() +")");
					}
					host.setHostedBy(new LinkedList<String>(hosts));
					res.append(host.toString()+"\n");

					

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
		System.out.println("File hostInfo.csv written");
	}
}
