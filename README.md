# searchEngine
#### **Notice:本项目是源搜索引擎项目的存档**

*源项目地址：* https://github.com/lzwroot/searchEngine

---

### 项目简介
传统的搜索是当用户向搜索引擎提交关键词查询请求时，搜索引擎通过关键词匹配方式在数据库中检索满足用户查询请求的页面，并将结果页面反馈给用户。这种搜索引擎对查询的处理局限于词的表面形式，使得传统的Web搜索引擎在表达问题、表达差异问题、等方面存在缺陷，基于语义的搜索引擎是通过对网络中的资源对象进行语义上的标注，以及对用户的查询表达进行语义处理，使得自然语言具备语义上的逻辑关系，能够在网络环境下进行广泛有效的语义推理，从而更加准确、全面的实现用户的检索。
### 涉及技术
* **Python 机器学习库：** pytorch；
* **深度学习模型：** transformer, bert, word2vec, annoy, knn；
* **聚类和相似性搜索库：** fasis；
* **JavaWeb 框架：** springboot；
* **前端开发框架：** vuejs、echarts；

---

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

![主页界面](https://github.com/Guojingxing/searchEngine/blob/master/README.assets/image-20210310130207975.png)

### 具体使用

以版本0.0为例，在启动服务器之前，需要先建立相关的数据库，首先需要修改[配置文件](.\src\main\resources\application.yml)中的用户名和密码（与自己电脑一致），之后在系统的数据库文件中新建立一个数据库，名为search，之后在该数据库下执行测试数据库文件（在sql_executable文件夹下），即可在相应数据库文件中创建相应的表和数据。之后便可以开始正常使用了。

引擎服务器启动端口为9999，需要在引擎的文件夹下执行：

```console
python .\search.py
```

