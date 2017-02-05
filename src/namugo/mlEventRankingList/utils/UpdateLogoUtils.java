package namugo.mlEventRankingList.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class UpdateLogoUtils
{

	public static void Update()
	{
		try
		{
			URL url = new URL("http://aimg-m.gree.net/img/application/thumbnail/37/58737/p79.jpg");
			URLConnection connection = url.openConnection();
			InputStream inputStream = connection.getInputStream();
			FileOutputStream fileOutputStream = new FileOutputStream("images/logo.jpg");
			byte[] bytes = new byte[4096];
			int hasread = 0;
			while ((hasread = inputStream.read(bytes)) != -1)
				fileOutputStream.write(bytes, 0, hasread);
			fileOutputStream.close();
			inputStream.close();
		}
		catch (MalformedURLException e)
		{
			ExceptionUtils.Log(e);
		}
		catch (IOException e)
		{
			ExceptionUtils.Log(e);
		}
	}
}
