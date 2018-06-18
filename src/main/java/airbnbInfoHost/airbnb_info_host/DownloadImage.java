package airbnbInfoHost.airbnb_info_host;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import airbnbInfoHost.airbnb_info_host.util.FileHandler;

public class DownloadImage {

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		
		
		//loading properties
		Properties prop = new Properties();
		int min=10;
		int max=20;
		String file="guestID.csv";
		String path="img";
		int start=0;
		try {       
			prop.load(new FileInputStream("launcher.properties"));
			min=Integer.parseInt(prop.getProperty("min_wait"));
			min=Integer.parseInt(prop.getProperty("max_wait"));
			file=prop.getProperty("id_file_name");
			path=prop.getProperty("img_path");
			start=Integer.parseInt(prop.getProperty("start_from"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String filenameIn = file;
		FileHandler f = new FileHandler();
		f.setFileHandler(filenameIn, null);
		String s=f.readFile();
		System.out.println("Starting from line "+start);
		int c=0;
		while((s=f.readFile())!=null) {
			String id = s;
			c++;
			if(c>=start) {
				String imageOut = new File("").getAbsolutePath()+"/"+path+"/"+id+".jpg";
				Document doc;

				try {
					doc = Jsoup.connect("https://www.airbnb.it/users/show/"+id).get();

					String img=doc.select("main").first().getElementsByClass("img-responsive").attr("src");

					URL url = new URL(img);

					InputStream in = new BufferedInputStream(url.openStream());
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					byte[] buf = new byte[1024];
					int n = 0;
					while (-1!=(n=in.read(buf)))
					{
						out.write(buf, 0, n);
					}
					out.close();
					in.close();
					byte[] response = out.toByteArray();
					FileOutputStream fos = new FileOutputStream(imageOut);
					fos.write(response);
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 


				int sec = (int)(Math.random()*((max-min)+min)*1000);
				System.out.println(c+") Wait for "+sec +" ms");
				try {
					Thread.sleep(sec);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		f.closeFile();
	}
}
