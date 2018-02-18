package OneTread;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.google.common.util.concurrent.RateLimiter;

public class ThreadDownloadFile extends Thread {
	private ConcurrentLinkedQueue<GoalDownload> listFiles;
	private File catalog;
	private int speedlimit;
	
	ThreadDownloadFile(ConcurrentLinkedQueue<GoalDownload> listFiles, File catalog,int speedlimit) {
		this.listFiles = listFiles;
		this.catalog=catalog;
		this.speedlimit=speedlimit;
		}

	public void run() {
		try {
			boolean z = true;
			while (z == true) {
				z = getFiles(listFiles);
				}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public boolean getFiles(ConcurrentLinkedQueue<GoalDownload> listFiles) throws Exception {

		GoalDownload y = listFiles.poll();
		if (y == null) {
			return false;
		}
		InputStream in = null;
		FileOutputStream out = null;
		URL url = new URL(y.getUrlFile());
		URLConnection yc = url.openConnection();
		int size=yc.getContentLength();
		final RateLimiter rateLimiter = RateLimiter.create(speedlimit);
		try {
			long startTime = System.currentTimeMillis();
			in = yc.getInputStream();
			int buffersize = in.available();
			byte[] buffer = new byte[buffersize];
			File goalFile=new File(catalog,y.getFileName());
			out = new FileOutputStream(goalFile);
			int a;
			System.out.println("Начали скачивать файл "+ y.getFileName());
			int b=0;
			while ((a = in.read(buffer)) != -1) {
				rateLimiter.acquire(buffer.length);
				b=a+b;
				System.out.println("Скачено "+b+" из файла "+ y.getFileName()+" размером "+size);
				out.write(buffer, 0, a);
			}
			long estimatedTime = System.currentTimeMillis() - startTime;
			System.out.println("Время скачивания файла "+y.getFileName()+": " + estimatedTime / 1000 + "сек");
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
					
		System.out.println("Завершение скачивания файла "+y.getFileName());
		return true;
	}

}
