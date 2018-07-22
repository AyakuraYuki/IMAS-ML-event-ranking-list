package namugo.mlEventRankingList.utils

class PropertiesUtils {

  private static String CONFIG = "/config.properties"
  private static InputStream INPUT_STREAM = PropertiesUtils.class.getResourceAsStream(CONFIG)
  private static Properties PROPERTIES = new Properties()
  public static String Host
  public static String Connection
  public static String Upgrade_Insecure_Requests
  public static String User_Agent
  public static String Accept
  public static String Referer
  public static String Accept_Encoding
  public static String Accept_Language
  public static String Cookie
  public static String X_Requested_With
  public static String EventCode
  public static String Color

  static
  {
    try {
      PROPERTIES.load(INPUT_STREAM)

      Host = PROPERTIES.getProperty("Host")
      Connection = PROPERTIES.getProperty("Connection")
      Upgrade_Insecure_Requests = PROPERTIES.getProperty("Upgrade-Insecure-Requests")
      User_Agent = PROPERTIES.getProperty("User-Agent")
      Accept = PROPERTIES.getProperty("Accept")
      Referer = PROPERTIES.getProperty("Referer")
      Accept_Encoding = PROPERTIES.getProperty("Accept-Encoding")
      Accept_Language = PROPERTIES.getProperty("Accept-Language")
      Cookie = PROPERTIES.getProperty("Cookie")
      X_Requested_With = PROPERTIES.getProperty("X-Requested-With")
      EventCode = PROPERTIES.getProperty("EventCode")
      Color = PROPERTIES.getProperty("Color")
    }
    catch (IOException ignored) {
    }
  }

  static void Reload() {
    try {
      PROPERTIES.load(PropertiesUtils.class.getResourceAsStream(CONFIG))

      Host = PROPERTIES.getProperty("Host")
      Connection = PROPERTIES.getProperty("Connection")
      Upgrade_Insecure_Requests = PROPERTIES.getProperty("Upgrade-Insecure-Requests")
      User_Agent = PROPERTIES.getProperty("User-Agent")
      Accept = PROPERTIES.getProperty("Accept")
      Referer = PROPERTIES.getProperty("Referer")
      Accept_Encoding = PROPERTIES.getProperty("Accept-Encoding")
      Accept_Language = PROPERTIES.getProperty("Accept-Language")
      Cookie = PROPERTIES.getProperty("Cookie")
      X_Requested_With = PROPERTIES.getProperty("X-Requested-With")
      EventCode = PROPERTIES.getProperty("EventCode")
      Color = PROPERTIES.getProperty("Color")
    }
    catch (IOException ignored) {
    }
  }

}
