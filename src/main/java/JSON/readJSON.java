package JSON;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class readJSON {

    public void JSONreader() throws IOException {
        ObjectMapper reader = new ObjectMapper();

        File JSONUsers = new File ("C:/Users/bendo/IdeaProjects/JSONPractice/src/main/java/JSON/JSONFiles/Users.json");

        UserName user = reader.readValue(JSONUsers , UserName.class);

        System.out.println("user : " + user.getuserName() + " my favret film " + user.getFilm() + " number of watches " + user.getWatches());



    }


}
