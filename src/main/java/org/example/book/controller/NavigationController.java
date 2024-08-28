package org.example.book.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.book.config.WebsiteConfig;
import org.example.book.dao.entity.Chapter;
import org.example.book.dao.entity.ChapterContent;
import org.example.book.dao.entity.Fiction;
import org.example.book.dao.entity.Navigation;
import org.example.book.util.JsoupUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2024/03/10
 */
@Slf4j
@RestController
@RequestMapping("/navigation")
public class NavigationController {
    /**
     * 根据类型返回小说列表
     */
    @PostMapping("/getFictionListByType")
    public List<Fiction> getFictionListByType(@RequestBody Navigation navigation) {

        List<Fiction> fictions = new ArrayList<>();
        if(!navigation.getHref().equals("/")){
            navigation.setHref(navigation.getHref()+navigation.getPage()+".html");
        }

        try {
            boolean connection = JsoupUtil.isConnection(WebsiteConfig.SHU_SQG + navigation.getHref());
            if (connection) {
                Document doc = JsoupUtil.getDoc(WebsiteConfig.SHU_SQG + navigation.getHref());
                Elements bookList = doc.select("div.category-div");
                for (Element book : bookList) {
                    // 选择书籍链接元素
                    Element bookLink = book.select("a").first();
                    // 书籍链接地址
                    String addr = bookLink.attr("href");

                    // 选择书籍图片元素
                    Element img = bookLink.select("img").first();
                    // 图片地址
                    String imgAddr = img.attr("data-original");
                    // 书名
                    String bookName = img.attr("alt");

                    // 选择书籍简介元素
                    Element introElement = book.select("div.intro.indent").first();
                    // 简介
                    String introduction = introElement.text();

                    // 选择作者元素
                    Element authorElement = book.select("div.flex a span").first();
                    // 作者
                    String author = authorElement.text();

                    fictions.add(Fiction.of().setFictionName(bookName)
                            .setAuthor(author)
                            .setImgUrl(imgAddr)
                            .setBrief(introduction)
                            .setFictionUrl(addr));
                }
                Elements bookList2 = doc.select("div.list-out");
                for (Element b : bookList2) {
                    // 获取 <a> 元素的 href 属性值
                    String bookAddr = b.select("a").next().attr("href");
                    // 获取 <a> 元素的 title 属性值
                    String bookName = b.select("a").next().attr("title").replaceAll("txt下载", "");
                    // 获取 <span> 元素的文本内容
                    String author = b.select("span.gray.dispc").first().text();

                    fictions.add(Fiction.of().setFictionName(bookName)
                            .setAuthor(author)
                            .setFictionUrl(bookAddr));
                }
            }
        } catch (Exception e) {
            System.out.println("拉取小说列表异常");
        }
        return fictions;
    }

    /**
     * 根据地址返回单本小说数据
     */
    @GetMapping("/getFictionByAddr")
    public Fiction getFictionByAddr(@RequestParam(value = "href", required = true) String href) {
        Fiction fiction = null;
        try {
            boolean connection = JsoupUtil.isConnection(WebsiteConfig.SHU_SQG + href);
            if (connection) {
                Document doc = JsoupUtil.getDoc(WebsiteConfig.SHU_SQG + href);

                String type = doc.select("div.info-title a").get(1).text();

                String imgAddr = doc.select("img.lazy").attr("data-original");

                String bookName = doc.select("img.lazy").attr("alt");

                String author = doc.select("div.w100.dispc span a").attr("title");

                String text = doc.select("div.info-main-intro p").text();


                Elements chapters = doc.select("div.info-chapters ").get(1).select("a");

                List<Chapter> chapterList = new ArrayList<>();

                for (Element chapter : chapters) {
                    String chapterUrl = chapter.attr("href");

                    chapter.attr("href");

                    String chapterTitle = chapter.text();

                    chapterList.add(Chapter.of().setChapterUrl(chapterUrl)
                            .setChapterTitle(chapterTitle));

                }
                fiction = Fiction.of().setImgUrl(imgAddr)
                        .setAuthor(author)
                        .setBrief(text)
                        .setFictionName(bookName)
                        .setType(type)
                        .setFictionUrl(WebsiteConfig.SHU_SQG + href)
                        .setChapterList(chapterList);
            }
        } catch (Exception e) {
          log.info("拉取单本小说数据异常");
        }
        return fiction;
    }



    /**
     * 拉取小说章节内容
     */
    @GetMapping("/getFictionByChapter")
    public ChapterContent getFictionByChapter(@RequestParam(value = "chapterUrl", required = true) String chapterUrl) {
        ChapterContent chapterC = ChapterContent.of();
        boolean hasMoreChapters = true;
        StringBuilder chapterContentBuilder = new StringBuilder();
        while (hasMoreChapters) {
            try {
                boolean connectionEstablished = JsoupUtil.isConnection(WebsiteConfig.SHU_SQG + chapterUrl);
                if (connectionEstablished) {
                    Document doc = JsoupUtil.getDoc(WebsiteConfig.SHU_SQG + chapterUrl);

                    // 解析章节内容
                    Elements contentElements = doc.select("article p");

                    String chapterName = doc.select("h1").text();

                    int i = chapterName.indexOf("（");
                    chapterC.setChapterName(chapterName.substring(0, i));

                    for (Element c : contentElements) {
                        chapterContentBuilder.append(c.text()).append(System.lineSeparator());
                    }

                    // 设置上一章和目录信息
                    String preChapterText = doc.getElementById("prev_url").text();
                    String preChapterUrl = doc.getElementById("prev_url").attr("href");
                    if ("上一章".equals(preChapterText)) {
                        chapterC.setPrechapter(preChapterUrl);
                    }

                    String directoryUrl = doc.getElementById("info_url").attr("href");
                    chapterC.setDirectory(directoryUrl);

                    // 判断是否是最后一章（即“下一章”文本）
                    String nextChapterText = doc.getElementById("next_url").text();
                    String nextChapterUrl = doc.getElementById("next_url").attr("href");
                    hasMoreChapters = !"下一章".equals(nextChapterText);  // 反转判断逻辑

                    if (hasMoreChapters) {
                        chapterUrl = nextChapterUrl;  // 当没有时，更新chapterUrl
                    } else {
                        chapterC.setNextchapter(nextChapterUrl);
                    }
                } else {
                    break;  // 如果连接失败，则跳出循环
                }
            } catch (Exception e) {
                log.info("连接失败 拉取小说章节异常"+e.getMessage());
                break;  // 出现异常也应跳出循环
            }
        }
        chapterC.setContent(chapterContentBuilder.toString());
        return chapterC;
        }

    }