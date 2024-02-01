package movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import dto.MovieDto;

public class MovieChart {

	public static List<MovieDto> getCGVdata() throws IOException{
		
		Document doc = Jsoup.connect("http://www.cgv.co.kr/movies/?lt=1&ft=0").get();

		Elements titles = doc.select("div.box-contents strong.title");
		Elements percents = doc.select("div.box-contents div.score strong.percent span");

		List<MovieDto> list = new ArrayList<MovieDto>();
		
		for(int i = 0; i < 10; i++) {
			Element title = titles.get(i);
			Element persent = percents.get(i);
			
			System.out.println(title.text() + " " + persent.text());
			
			Double per = Double.parseDouble(persent.text().replace("%",""));
			
			MovieDto dto = new MovieDto(title.text(), per);
			list.add(dto);
		}
		
		return list;
	}
}
