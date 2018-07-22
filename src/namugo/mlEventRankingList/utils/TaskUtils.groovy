package namugo.mlEventRankingList.utils

import namugo.mlEventRankingList.entity.Ranking
import org.apache.http.HttpEntity
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.utils.URIBuilder
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils
import org.jsoup.Jsoup
import org.jsoup.nodes.Element

import java.util.concurrent.Callable

class TaskUtils implements Callable<Object> {
  private static CloseableHttpClient client
  private static HttpGet httpGet = new HttpGet()
  private int pageNo

  static {
    client = HttpClients.createDefault()
    InitHttpGet(httpGet)
  }

  TaskUtils(int pageNo) {
    this.pageNo = pageNo
  }

  List<Ranking> call() {
    List<Ranking> list = new ArrayList<Ranking>()
    try {
      def imas = InitURL(PropertiesUtils.EventCode, pageNo)
      httpGet.setURI(imas)
      def response = client.execute(httpGet)
      def entity = response.entity
      def rankingListStr = CatchRankingListString(entity)

      def document = Jsoup.parse(rankingListStr)
      document.select("img").remove()
      def elements = document.select(".user-list-st")
      def iterator = elements.iterator()
      if (pageNo == 1 || pageNo == 2) {
        while (iterator.hasNext()) {
          Element element = (Element) iterator.next()
          def info = element.text().replaceAll(" ショコラpt ", " ")
              .replaceAll(" ", "-").split("-")
          def ranking = new Ranking()
          ranking.setRank(info[0])
          ranking.setProducer(info[1])
          ranking.setPt(info[info.length - 1])
          list.add(ranking)
        }
      } else {
        def info = elements.last().text().replaceAll(" ショコラpt ", " ")
            .replaceAll(" ", "-").split("-")
        def ranking = new Ranking()
        ranking.setRank(info[0])
        ranking.setProducer(info[1])
        ranking.setPt(info[info.length - 1])
        list.add(ranking)
      }
      EntityUtils.consume(entity)
      response.close()
    } catch (Exception ignored) {
    }
    return list
  }

  private static URI InitURL(String eventCode, int pageNo) throws Exception {
    return new URIBuilder().setScheme("http").setHost("imas.gree-apps.net")
        .setPath("/app/index.php/event/" + eventCode + "/ranking/general")
        .setParameter("page", "" + pageNo).setParameter("idol", "").build()
  }

  private static void InitHttpGet(HttpGet httpGet) {
    httpGet.setHeader("Host", PropertiesUtils.Host)
    httpGet.setHeader("Connection", PropertiesUtils.Connection)
    httpGet.setHeader("Upgrade-Insecure-Requests", PropertiesUtils.Upgrade_Insecure_Requests)
    httpGet.setHeader("User-Agent", PropertiesUtils.User_Agent)
    httpGet.setHeader("Accept", PropertiesUtils.Accept)
    httpGet.setHeader("Referer", PropertiesUtils.Referer)
    httpGet.setHeader("Accept-Encoding", PropertiesUtils.Accept_Encoding)
    httpGet.setHeader("Accept-Language", PropertiesUtils.Accept_Language)
    httpGet.setHeader("Cookie", PropertiesUtils.Cookie)
    httpGet.setHeader("X-Requested-With", PropertiesUtils.X_Requested_With)
  }

  private static String CatchRankingListString(HttpEntity entity) throws Exception {
    String html = EntityUtils.toString(entity)
    return html.substring(html.lastIndexOf("<ul class=\"list-bg\">"),
        html.lastIndexOf("<div class=\"warn\">"))
  }
}
