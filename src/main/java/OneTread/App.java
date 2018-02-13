package OneTread;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import com.google.common.util.concurrent.RateLimiter;

public class App {


	public static void main(String[] args) throws Exception {
		DGListFromFile text=new DGListFromFile("text.txt");
		text.getListFiles();
	}
}
