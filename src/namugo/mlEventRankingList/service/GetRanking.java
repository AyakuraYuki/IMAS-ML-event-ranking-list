package namugo.mlEventRankingList.service;

import java.util.ArrayList;
import java.util.List;

import namugo.mlEventRankingList.entity.Ranking;
import namugo.mlEventRankingList.utils.TaskUtils;

public class GetRanking
{
	public static List<Ranking> GetRankingList()
	{
		List<Ranking> list = new ArrayList<Ranking>();

		TaskUtils[] tasks = new TaskUtils[13];
		tasks[0] = new TaskUtils(1);
		tasks[1] = new TaskUtils(2);
		for (int i = 2; i < tasks.length - 1; i++)
			tasks[i] = new TaskUtils((i - 1) * 10);
		tasks[12] = new TaskUtils(120);

		for (int i = 0; i < tasks.length; i++)
			list.addAll(tasks[i].call());

		return (List<Ranking>) list;
	}
}
