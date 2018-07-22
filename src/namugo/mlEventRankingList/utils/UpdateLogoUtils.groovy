package namugo.mlEventRankingList.utils

class UpdateLogoUtils {

  static void Update() {
    try {
      def url = new URL("http://aimg-m.gree.net/img/application/thumbnail/37/58737/p79.jpg")
      url.openConnection().inputStream.withStream { input ->
        new FileOutputStream("images/logo.jpg").withStream { output ->
          def bytes = new byte[1024]
          input.read(bytes) == -1 ?: output.write(bytes)
        }
      }
    } catch (IOException ignored) {
    }
  }

}
