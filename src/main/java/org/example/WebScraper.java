package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class WebScraper {
    private final DatabaseManager dbManager = new DatabaseManager();

    public void scrape(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            // Найти все текстовые элементы, содержащие телефоны (регулярное выражение для телефонов)
            Elements elements = document.select("*");
            Set<String> phones = new HashSet<>();

            for (Element element : elements) {
                String text = element.text();
                if (text.matches("\\+?[0-9\\-\\s()]{7,15}")) { // Примерный паттерн для номеров
                    phones.add(text);
                }
            }

            for (String phone : phones) {
                dbManager.saveData(url, phone, "phone");
                System.out.println("Сохранено: " + phone);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
