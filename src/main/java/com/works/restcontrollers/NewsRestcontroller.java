package com.works.restcontrollers;

import com.google.gson.Gson;
import com.works.entities.NewsData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class NewsRestcontroller {


    @GetMapping("/news")
    public Map<String, Object> news() {
        Map<String, Object> hm = new HashMap<>();

        String url = "https://newsapi.org/v2/everything?q=bitcoin&from=2022-07-02&sortBy=publishedAt&apiKey=38a9e086f10b445faabb4461c4aa71f8";
        RestTemplate template = new RestTemplate();
        String stData = template.getForObject( url, String.class );

        Gson gson = new Gson();
        NewsData data = gson.fromJson(stData, NewsData.class);

        data.getArticles().forEach( item -> {
            System.out.println( item.getTitle() );
        } );

        hm.put("news", data.getArticles());

        return hm;
    }


}
