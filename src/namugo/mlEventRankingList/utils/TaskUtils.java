package namugo.mlEventRankingList.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import namugo.mlEventRankingList.entity.Ranking;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

public class TaskUtils implements Callable<Object>
{
	private static CloseableHttpClient client;
	private static HttpGet httpGet = new HttpGet();
	private static CloseableHttpResponse response;
	private int pageNo;

	static
	{
		client = HttpClients.createDefault();
		InitHttpGet(httpGet);
	}

	public TaskUtils(int pageNo)
	{
		this.pageNo = pageNo;
	}

	public List<Ranking> call()
	{
		List<Ranking> list = new ArrayList<Ranking>();
		try
		{
			URI imas = InitURL(PropertiesUtils.EventCode, pageNo);
			httpGet.setURI(imas);
			response = client.execute(httpGet);
			HttpEntity entity = response.getEntity();
			String rankingListStr = CatchRankingListString(entity);

			Document document = Jsoup.parse(rankingListStr);
			document.select("img").remove();
			Elements elements = document.select(".user-list-st");
			Iterator<?> iterator = elements.iterator();
			if (pageNo == 1 || pageNo == 2)
			{
				while (iterator.hasNext())
				{
					Element element = (Element) iterator.next();
					String[] info = element.text().toString().replaceAll(" ショコラpt ", " ")
							.replaceAll(" ", "-").split("-");
					Ranking ranking = new Ranking();
					ranking.setRank(info[0]);
					ranking.setProducer(info[1]);
					ranking.setPt(info[info.length - 1]);
					list.add(ranking);
				}
			}
			else
			{
				String[] info = elements.last().text().toString().replaceAll(" ショコラpt ", " ")
						.replaceAll(" ", "-").split("-");
				Ranking ranking = new Ranking();
				ranking.setRank(info[0]);
				ranking.setProducer(info[1]);
				ranking.setPt(info[info.length - 1]);
				list.add(ranking);
			}
			EntityUtils.consume(entity);
			response.close();
		}
		catch (Exception e)
		{
		}
		return list;
	}

	private static URI InitURL(String eventCode, int pageNo) throws Exception
	{
		return new URIBuilder().setScheme("http").setHost("imas.gree-apps.net")
				.setPath("/app/index.php/event/" + eventCode + "/ranking/general")
				.setParameter("page", "" + pageNo).setParameter("idol", "").build();
	}

	private static void InitHttpGet(HttpGet httpGet)
	{
		httpGet.setHeader("Host", PropertiesUtils.Host);
		httpGet.setHeader("Connection", PropertiesUtils.Connection);
		httpGet.setHeader("Upgrade-Insecure-Requests", PropertiesUtils.Upgrade_Insecure_Requests);
		httpGet.setHeader("User-Agent", PropertiesUtils.User_Agent);
		httpGet.setHeader("Accept", PropertiesUtils.Accept);
		httpGet.setHeader("Referer", PropertiesUtils.Referer);
		httpGet.setHeader("Accept-Encoding", PropertiesUtils.Accept_Encoding);
		httpGet.setHeader("Accept-Language", PropertiesUtils.Accept_Language);
		httpGet.setHeader("Cookie", PropertiesUtils.Cookie);
		httpGet.setHeader("X-Requested-With", PropertiesUtils.X_Requested_With);
	}

	private static String CatchRankingListString(HttpEntity entity) throws Exception
	{
		String html = EntityUtils.toString(entity);
		String subHtml = html.substring(html.lastIndexOf("<ul class=\"list-bg\">"),
				html.lastIndexOf("<div class=\"warn\">"));
		return subHtml;
	}
}
