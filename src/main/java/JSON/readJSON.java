package JSON;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class readJSON {

    public void JSONreader() throws IOException {
        ObjectMapper reader = new ObjectMapper();

        File JSONUser = new File ("C:/Users/bendo/IdeaProjects/JSONPractice/src/main/java/JSON/JSONFiles/Users.json");

        UserName user = reader.readValue(JSONUser , UserName.class);

        System.out.println("user : " + user.getuserName() + " my favret film " + user.getFilm() + " number of watches " + user.getWatches());



    }
    public ArrayList<UserName> jsonArrayReader() throws IOException{

        ObjectMapper ArrayReader = new ObjectMapper();

        File JSONUsers = new File("C:/Users/bendo/IdeaProjects/JSONPractice/src/main/java/JSON/JSONFiles/Users.json");

         ArrayList<UserName> usersArray  = ArrayReader.readValue(JSONUsers , ArrayReader.getTypeFactory().constructCollectionType(ArrayList.class, UserName.class));

        return usersArray;
    }



}
