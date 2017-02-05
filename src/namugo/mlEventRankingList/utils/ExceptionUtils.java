package namugo.mlEventRankingList.utils;

import java.io.File;

public class ExceptionUtils
{
	private static File folder;

	static
	{
		folder = new File("log");
		if (!folder.exists())
			folder.mkdirs();
	}

	public static void Log(Exception e)
	{
//		try
//		{
//			File file = new File(folder, new Date() + " - " + e.getClass().getName() + ".txt");
//			file.createNewFile();
//			String msg = e.getMessage();
//			PrintWriter pw = new PrintWriter(file);
//			pw.print(msg);
//			pw.close();
//		}
//		catch (Exception e2)
//		{
//		}
	}
}
