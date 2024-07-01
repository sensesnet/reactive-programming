package com.epam.reactiveprogramming.config;

import com.epam.reactiveprogramming.model.Sport;
import com.epam.reactiveprogramming.repository.SportRepository;
import org.bson.json.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.client.RestTemplate;

@Configuration
public class InitConfig {

    private static final String DATA = "sports"; //data
    private static final String ATTRIBUTES = "attributes";
    private static final String NAME = "name";
    private final SportRepository sportRepository;
    private final String sportUrl;

    @Autowired
    public InitConfig(SportRepository sportRepository, @Value("${sport.url}") String sportUrl) {
        this.sportRepository = sportRepository;
        this.sportUrl = sportUrl;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        JSONObject response = new JSONObject(new RestTemplate().getForObject(sportUrl, String.class));
        JSONArray data = response.getJSONArray(DATA);

//        for (Object element : data) {
//            JSONObject jsonObject = new JSONObject(element.toString());
//            if(jsonObject.has(ATTRIBUTES)) {
//                JSONObject attributes = jsonObject.getJSONObject(ATTRIBUTES);
//                if (attributes != null && attributes.has(NAME)) {
//                    Object name = attributes.get(NAME);
//                    if (name != null) {
//                        sportRepository.insert(new Sport(name.toString())).subscribe();
//                    }
//                }
//            }
//        }

        for (Object element : data) {
            JSONObject sport = (JSONObject) element;
            String idSport = sport.getString("idSport");
            String strSport = sport.getString("strSport");

            sportRepository.insert(new Sport(strSport)).subscribe();
        }
    }
}
