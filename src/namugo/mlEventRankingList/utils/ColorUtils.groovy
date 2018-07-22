package namugo.mlEventRankingList.utils

class ColorUtils {

  public static int R
  public static int G
  public static int B

  static {
    def hexColor = PropertiesUtils.Color
    if (hexColor.size() != 6) {
      R = 255
      G = 255
      B = 255
    } else {
      def rPart = hexColor.substring(0, 2)
      def gPart = hexColor.substring(2, 4)
      def bPart = hexColor.substring(4, 6)
      R = Integer.parseInt rPart, 16
      G = Integer.parseInt gPart, 16
      B = Integer.parseInt bPart, 16
    }
  }

}
