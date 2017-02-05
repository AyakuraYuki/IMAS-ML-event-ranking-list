# IMAS-ML-event-ranking-list
这是一个用于查看Million Live活动排名的小工具，利用Java编写，是一个开源项目。

## 开发工具及项目环境<br>
1.先期于Intellij Idea开发，后来转移到Eclipse<br>
2.项目使用Maven创建（好像没什么好说的吧就是懒得上传lib）<br>
3.JavaSE 1.8<br>
4.Maven compiler plugin 3.6.1<br>
<br>
## 使用技术<br>
1.Httpclient ——Ver4.5.2<br>
2.Jsoup ——Ver1.10.2<br>
3.log4j ——Ver1.2.17<br>
<br>
## 使用说明<br>
配置文件里可以设置当前活动的活动编号，这个编号可以从URL中提取，例如当期「夢の国！？ショコラティエの大冒険」的编号是348。<br>
<br>
## 已知问题<br>
1.需要配合使用Chrome调试、Firebug、Fiddler等软件抓取请求头，获得Cookie之后设置配置文件config.properties<br>
2.异常错误转储为日志尚未完成<br>
3.Cookie失效的情况，刷新排名之后界面不刷新任何数据，也不会有异常错误提示<br>
4.由于是单线程加Swing编程，点击刷新按钮后界面会卡住“假死”，但不是真正的程序崩溃<br>
<br>
