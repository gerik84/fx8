package com.manager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by redline on 11.09.17.
 */
public class ParserManager {

    public static void test(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.getElementsByClass("model-name");
            Elements a = doc.getElementsByTag("a");
            System.out.print("asd");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
