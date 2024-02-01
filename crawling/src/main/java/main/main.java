package main;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class main {

	public static void main(String[] args) throws IOException {
		/*
		 * Carwling : 웹페이지를 읽어서 데이터를 추출하는 처리 -> (통계) -> 시각화(차트)
		 *
		 * JSoup : java
		 * BeautufulSoup : python
		 * */
		
		Document doc = Jsoup.connect("http://www.cgv.co.kr/movies/?lt=1&ft=0").get();

		Elements titles = doc.select("div.box-contents strong.title");
		Elements percents = doc.select("div.box-contents div.score strong.percent span");

		for(int i = 0; i < 10; i++) {
			Element title = titles.get(i);
			Element persent = percents.get(i);
			
			System.out.println(title.text() + " " + persent.text());
			
		}
	}
}
