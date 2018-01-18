package namugo.mlEventRankingList.utils

class UpdateLogoUtils {

    static void Update() {
        try {
            def url = new URL("http://aimg-m.gree.net/img/application/thumbnail/37/58737/p79.jpg")
            def connection = url.openConnection()
            def inputStream = connection.inputStream
            def fileOutputStream = new FileOutputStream("images/logo.jpg")
            byte[] bytes = new byte[4096]
            int hasread = 0
            while ((hasread = inputStream.read(bytes)) != -1)
                fileOutputStream.write(bytes, 0, hasread)
            fileOutputStream.close()
            inputStream.close()
        } catch (IOException ignored) {
        }
    }

}
