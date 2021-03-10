# searchEngine
#### **Notice:本项目是源搜索引擎项目的存档**

*源项目地址：*https://github.com/lzwroot/searchEngine

---

SearchEngine是一款基于语义的文本搜索引擎，采用了bert、transformer等深度学习框架匹配单词向量中最佳的结果显示在网站前端。

### 下载地址

最新release版本可单击此打开：[最新版本下载地址](https://github.com/Guojingxing/searchEngine/releases/latest)

往期release版本查看和下载：[往期版本](https://github.com/Guojingxing/searchEngine/releases)

### 如何启动

以发布的[第一个版本](https://github.com/Guojingxing/searchEngine/releases/tag/0.0.1)为例，Windows系统中可以通过命令行的方式启动，在当前文件夹中打开命令行，通过在命令行输入：

```console
java -jar demo-0.0.1-SNAPSHOT.jar
```

启动服务器，启动时间大概不超过10s，服务器开启过程中始终不要关闭命令行界面。之后在浏览器中输入：

```http
http://localhost:8080/search/main
```

即可打开相应的页面。页面如下图所示（要允许定位功能）：

![image-20210310130207975](.\README.assets\image-20210310130207975.png)

### 具体使用

以版本0.0为例，在启动服务器之前，需要先建立相关的数据库，首先需要修改[配置文件](.\src\main\resources\application.yml)中的用户名和密码（与自己电脑一致），之后在系统的数据库文件中新建立一个数据库，名为search，之后在该数据库下执行测试数据库文件（在sql_executable文件夹下），即可在相应数据库文件中创建相应的表和数据。之后便可以开始正常使用了。

引擎服务器启动端口为9999，需要在引擎的文件夹下执行：

```console
python .\search.py
```

