package namugo.mlEventRankingList;

import namugo.mlEventRankingList.ui.GUIMain;
import namugo.mlEventRankingList.utils.UpdateLogoUtils;

public class Run
{
	static
	{
		UpdateLogoUtils.Update();
	}

	public static void main(String[] args)
	{
		new GUIMain();
	}
}
