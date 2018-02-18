package OneTread;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.google.common.util.concurrent.RateLimiter;

public class ThreadDownloadFile implements Callable<Long> {
	private ConcurrentLinkedQueue<GoalDownload> listFiles;
	private File catalog;
	private int speedlimit;
	private GoalDownload y;
	private Long sizeFile;
	
	ThreadDownloadFile(ConcurrentLinkedQueue<GoalDownload> listFiles, File catalog,int speedlimit) {
		this.listFiles = listFiles;
		this.catalog=catalog;
		this.speedlimit=speedlimit;
		}

	public Long call() {
		try {
			boolean z = true;
			while (z == true) {
				z = getFiles(listFiles);
				}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return sizeFile;
	}

	public boolean getFiles(ConcurrentLinkedQueue<GoalDownload> listFiles) throws Exception {

		y = listFiles.poll();
		if (y == null) {
			return false;
		}
		InputStream in = null;
		FileOutputStream out = null;
		URL url = new URL(y.getUrlFile());
		URLConnection yc = url.openConnection();
		File goalFile=new File(catalog,y.getFileName());
		
		final RateLimiter rateLimiter = RateLimiter.create(speedlimit);
		try {
			
			in = yc.getInputStream();
			int buffersize = in.available();
			byte[] buffer = new byte[buffersize];
			
			out = new FileOutputStream(goalFile);
			int a;
			System.out.println("Начали скачивать файл "+ y.getFileName());
			int b=0;
			while ((a = in.read(buffer)) != -1) {
				rateLimiter.acquire(buffer.length);
				b=a+b;
				
				out.write(buffer, 0, a);
			}
			
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
		
		sizeFile=goalFile.length();
		App.sizeFile=App.sizeFile+sizeFile;
		System.out.println("Завершено скачивания файла "+y.getFileName());
		return true;
	}

}
