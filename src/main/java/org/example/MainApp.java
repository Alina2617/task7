package org.example;

public class MainApp {
    public static void main(String[] args) {
        WebScraper scraper = new WebScraper();
        scraper.scrape("https://tsutmb.ru/");
    }
}
