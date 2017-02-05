package namugo.mlEventRankingList.entity;

import static java.lang.System.out;

/**
 * Created by YukiAyakura on 2017-02-04.
 */
public class Ranking
{
	private String rank;
	private String producer;
	private String pt;

	public String getRank()
	{
		return rank;
	}

	public void setRank(String rank)
	{
		this.rank = rank;
	}

	public String getProducer()
	{
		return producer;
	}

	public void setProducer(String producer)
	{
		this.producer = producer;
	}

	public String getPt()
	{
		return pt;
	}

	public void setPt(String pt)
	{
		this.pt = pt;
	}

	public void display()
	{
		out.printf("%8s\t%-10s\t\t\t%20s\n", this.rank, this.producer, this.pt);
	}

	@Override
	public String toString()
	{
		return rank + "\t\t" + producer + "\t\t" + pt;
	}
}
