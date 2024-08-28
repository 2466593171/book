package org.example.book.schedule;

import org.aspectj.lang.annotation.Aspect;
import org.example.book.aop.HttpAspect;
import org.example.book.config.WebsiteConfig;
import org.example.book.dao.entity.Navigation;
import org.example.book.service.NavigationService;
import org.example.book.util.JsoupUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@EnableScheduling
@Aspect
public class Schedule {

    @Autowired
    NavigationService navigationService;
    private static final Logger log = LoggerFactory.getLogger(HttpAspect.class);

    //    拉取导航 一天一次
//    @Scheduled(cron = "0 */1 * * * ?") //更新时间
    public void getNavigation() {
        try {
            boolean connection = false;

//            ArrayList<Navigation> navigations = new ArrayList<>();
//            HashMap<String, String> hashMap = new HashMap<>();

            while (!connection) {
                connection = JsoupUtil.isConnection(WebsiteConfig.SHU_SQG);

                if (connection) {
                    List<Navigation> list = navigationService.list();
                    Map<String, Navigation> navigationMap = list.stream().collect(Collectors.toMap(Navigation::getType, o -> o));
                    Document doc = JsoupUtil.getDoc(WebsiteConfig.SHU_SQG);
                    Elements as = doc.select("nav a");
                    for (Element a : as) {
                        String href = "";
                        href = a.attr("href");
                        if (href.contains(".")) {
                            int i = href.indexOf(".");
                            href = href.substring(0, i-1);
                        }
                        String type = a.text();
                        log.info("获取导航成功" + href + type);
//                   navigations.add(Navigation.of().setHref(href).setType(text));
                        if (!navigationMap.containsKey(type)) {
                            navigationService.add(Navigation.of().setHref(href).setType(type));
                        }
                    }
                }
                //保存数据库 批量插入失败 原因可能是xml写错了
//               navigationService.addList(navigations);
            }
        } catch (Exception e) {
            System.out.println("连接失败");
        }
    }

    //批量拉取更新 半天一次
    public void getBookUpdate() {
    }

}
