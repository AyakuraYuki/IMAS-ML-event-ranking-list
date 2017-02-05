package namugo.mlEventRankingList.utils;

public class ColorUtils
{
	public static int R;
	public static int G;
	public static int B;

	static
	{
		String hexColor = PropertiesUtils.Color;
		if (hexColor.length() != 6)
		{
			R = 255;
			G = 255;
			B = 255;
		}
		else
		{
			String rPart = hexColor.substring(0, 2);
			String gPart = hexColor.substring(2, 4);
			String bPart = hexColor.substring(4, 6);
			R = Integer.parseInt(rPart, 16);
			G = Integer.parseInt(gPart, 16);
			B = Integer.parseInt(bPart, 16);
		}
	}
}
