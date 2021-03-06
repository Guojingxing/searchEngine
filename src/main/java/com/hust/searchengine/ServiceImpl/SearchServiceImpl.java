package com.hust.searchengine.ServiceImpl;

import com.hust.searchengine.Entity.*;
import com.hust.searchengine.Mapper.SearchMapper;
import com.hust.searchengine.Service.SearchService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchMapper searchMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleCharVerifyCodeGenImpl.class);

    @Override
    public User UserLogin(String username, String password) {
        return searchMapper.UserLogin(username,password);
    }

    @Override
    public List<Article> findAllBookmarks(String username) {
        return searchMapper.findAllBookmarks(username);
    }

    @Override
    public User findUserByUsername(String username) {
        return searchMapper.findUserByUsername(username);
    }

    @Override
    public Integer updateUserByUsername(String oldusername,
                                        String newusername,
                                        String newpassword,
                                        boolean newsex,
                                        String newinstitution,
                                        String newemail,
                                        String image_url) {
        return searchMapper.updateUserByUsername(oldusername, newusername, newpassword,
                newsex, newinstitution, newemail,image_url);
    }

    @Override
    public String FindIfUsernameAlreadyExists(String username) {
        return searchMapper.FindIfUsernameAlreadyExists(username);
    }

    @Override
    public User newUserSignup(String email, String username, String password) {
        boolean isInserted = searchMapper.newUserSignup(email, username, password) != 0;
        if(isInserted)return new User(username, password, email);
        return null;
    }

    @Override
    public PageInfo<Article> findArticleByKeywords(Integer pageIndex, Integer pageSize, String keywords) {
        //按照时间排序，最新的文章出现在最前面
        String orderByDate = "time"+" desc"; //按照数据库的time字段排序，desc代表倒序

        PageHelper.startPage(pageIndex, pageSize, orderByDate);
        List<Article> lists = senate(keywords);
        return new PageInfo<>(lists);
    }

    @Override
    public PageInfo<Author> findAllSubAuthorsByUsername(Integer pageIndex, Integer pageSize, String username) {
        PageHelper.startPage(pageIndex, pageSize);
        List<Author> lists = searchMapper.findAllSubAuthorsByUsername(username);
        return new PageInfo<>(lists);
    }

    @Override
    public PageInfo<Field> findAllSubFieldsByUsername(Integer pageIndex, Integer pageSize, String username) {
        PageHelper.startPage(pageIndex, pageSize);
        List<Field> lists = searchMapper.findAllSubFieldsByUsername(username);
        return new PageInfo<>(lists);
    }

    @Override
    public PageInfo<Journal> findAllSubJournalsByUsername(Integer pageIndex, Integer pageSize, String username) {
        PageHelper.startPage(pageIndex, pageSize);
        List<Journal> lists = searchMapper.findAllSubJournalsByUsername(username);
        return new PageInfo<>(lists);
    }

    @Override
    public PageInfo<Article> findArticleByJournal(Integer pageIndex, Integer pageSize, String journal) {
        String orderByDate = "time"+" desc";
        PageHelper.startPage(pageIndex, pageSize, orderByDate);
        List<Article> lists = searchMapper.findArticleByJournal(journal);
        return new PageInfo<>(lists);
    }

    @Override
    public PageInfo<Article> findArticleByField(Integer pageIndex, Integer pageSize,String field) {
        String orderByDate = "time"+" desc";
        PageHelper.startPage(pageIndex, pageSize, orderByDate);
        List<Article> lists = searchMapper.findArticleByField(field);
        return new PageInfo<>(lists);
    }

    @Override
    public PageInfo<Article> findArticleByAuthor(Integer pageIndex, Integer pageSize,String author) {
        String orderByDate = "time"+" desc";
        PageHelper.startPage(pageIndex, pageSize, orderByDate);
        List<Article> lists = searchMapper.findArticleByAuthor(author);
        return new PageInfo<>(lists);
    }

    @Override
    public Integer insertField(String username, String field) {
        return searchMapper.insertField(username, field);
    }

    @Override
    public Integer insertAuthor(String username, String author) {
        return searchMapper.insertAuthor(username, author);
    }

    @Override
    public Integer deleteField(String username, String field) {
        return searchMapper.deleteField(username, field);
    }

    @Override
    public Integer deleteAuthor(String username, String author) {
        return searchMapper.deleteAuthor(username, author);
    }

    @Override
    public Article findArticleByDoi(String doi) {
        return searchMapper.findArticleByDoi(doi);
    }

    @Override
    public List<Journal> findAllSubJournalsByUsernameNotPaged(String username) {
        return searchMapper.findAllSubJournalsByUsername(username);
    }

    @Override
    public List<Article> findArticleByJournalNotPaged(String journal) {
        return searchMapper.findArticleByJournal(journal);
    }

    @Override
    public Integer InsertBookmark(String username, String doi) {
        Date insert_time = new Date();
        return searchMapper.InsertBookmark(username, doi, insert_time);
    }

    @Override
    public Integer addHistory(String username, String doi) {
        Date access_time = new Date();
        return searchMapper.addHistory(username, doi, access_time);
    }

    @Override
    public Bookmark findBookmark() {
        return searchMapper.findBookmark();
    }

    @Override
    public Integer deleteBookMark(String username, String doi) {
        return searchMapper.deleteBookMark(username, doi);
    }

    @Override
    public PageInfo<Article> advancedSearchByVariableConditions(String type1, String keyword1, String selector1, String type2, String keyword2, String selector2, String type3, String keyword3, String start_date, String end_date, Integer pageIndex, Integer pageSize) {
        String orderByDate = "time"+" desc";
        PageHelper.startPage(pageIndex, pageSize, orderByDate);
        List<Article> lists = searchMapper.advancedSearchByVariableConditions(type1, keyword1, selector1, type2, keyword2, selector2, type3, keyword3, start_date, end_date);
        return new PageInfo<>(lists);
    }

    @Override
    public List<Article> getAllHistory(String username) {
        return searchMapper.getAllHistory(username);
    }

    @Override
    public Integer updateHistoryRecord(String username, String his_doi) {
        Date access_time = new Date();
        return searchMapper.updateHistoryRecord(username, his_doi, access_time);
    }

    @Override
    public Integer deleteHistory(String username, String his_doi){
        return searchMapper.deleteHistory(username, his_doi);
    }

    @Override
    public Integer postFeedback(String username, String feedback) {
        Date feedback_time = new Date();
        return searchMapper.postFeedback(username, feedback, feedback_time);
    }

    @Override
    public List<Feedback> getFeedbackHistory(String username) {
        return searchMapper.getFeedbackHistory(username);
    }

    @Override
    public Integer deleteFeedbackRecord(Integer feedback_id) {
        return searchMapper.deleteFeedbackRecord(feedback_id);
    }

    @Override
    public PageInfo<Article> searchByPDF(Integer pageIndex, Integer pageSize, String fileName) {
        //按照时间排序，最新的文章出现在最前面
        String orderByDate = "time"+" desc"; //按照数据库的time字段排序，desc代表倒序

        PageHelper.startPage(pageIndex, pageSize, orderByDate);

        File file = new File(fileName);
        String content = "";
        try {
            //字符串前加一个感叹号以示区别
            content = "!" + pdf2String(file);
        }catch (IOException e){
            e.printStackTrace();
            content = "!";
        }

        List<Article> lists = senate(content);
        return new PageInfo<>(lists);
    }

    /**
     * 将pdf中的内容转成字符串
     * @param file
     * @return
     * @throws IOException
     */
    public static String pdf2String(File file) throws IOException {
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        PDDocument pdfDocument = PDDocument.load(inputStream);
        StringWriter writer = new StringWriter();
        PDFTextStripper stripper = new PDFTextStripper();
        stripper.writeText(pdfDocument, writer);
        String contents = writer.getBuffer().toString();
        pdfDocument.close();
//        PDDocumentInformation documentInformation = pdfDocument.getDocumentInformation();
        return contents;
    }

    /**
     * 将字符串转成字节流的形式传至服务器
     * @param data
     * @return
     */
    public List<Article> senate(String data) {
        String ip = "localhost";
        int port = 9999;
        Socket socket = null;
        try {
            socket = new Socket(ip, port);
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            BufferedReader inRead = new BufferedReader(new InputStreamReader(in));

            out.write(data.getBytes(StandardCharsets.UTF_8),0,data.length());

            LOGGER.info("搜索服务器连接成功！");

            String result = inRead.readLine();
            List<Article> articles = new ArrayList<>();
            //List<Article> articles = null;
            for(String i:result.split("\\!",-1)){
                if(i.equals(""))continue;
                String []articleArray = i.split("\\|",-1);
                Article article = new Article();

                //字符串分割格式
                //title+'|'+author+'|'+abstract+'|'+str(time)+'|'+field+'|'+link+'|'+journal+'|'+doi
                article.setArticlename(articleArray[0]);
                article.setAuthor(articleArray[1]);
                article.setArticle_abstract(articleArray[2]);
                try{
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    article.setTime(sdf.parse(articleArray[3]));
                }catch (Exception ignored){}
                article.setField(articleArray[4]);
                article.setLink(articleArray[5]);
                article.setJournal(articleArray[6]);
                article.setDoi(articleArray[7]);
                articles.add(article);
            }
            return articles;
        }
        catch(Exception e) {
            LOGGER.info("search.py可能未启动或者启动失败，将使用数据库搜索");
        }
        return searchMapper.findArticleByKeywords(data);
    }
}
