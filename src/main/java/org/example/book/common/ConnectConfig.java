package org.example.book.common;

import org.example.book.config.WebsiteConfig;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class ConnectConfig {
    public Document Conneect(String type){
        //123
        //456
        //伪装成为浏览器，有的网站爬取数据会阻止访问，伪装成浏览器可以访问，这里我伪装成Google浏览器
//        Connection conn = Jsoup.connect(WebsiteConfig.SHU_QI+type)
//                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0")
//                .header("Connection", "keep-alive")
//                .followRedirects(true) // 是否允许重定向
//                .ignoreContentType(true)
//                .ignoreHttpErrors(true);
        Connection conn  = Jsoup.connect(WebsiteConfig.SHU_BA_69+type).header("Host", WebsiteConfig.SHU_BA_69+type).header("Accept",
                        "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                .header("Accept-Encoding", "gzip, deflate")
                .header("Accept-Language", "zh-CN,zh;q=0.9").header("Cache-Control", "no-cache")
                .header("Connection", "keep-alive").header("Pragma", "no-cache")
                .header("Upgrade-Insecure-Requests", "1").timeout(5000)
                .userAgent("Mozilla")//模拟浏览器
                ;
        try {
            Document doc = conn.get();
            return doc;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
