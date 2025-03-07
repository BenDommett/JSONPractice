package JSON;

import java.io.IOException;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;



public class JSONWriter{

    public void jsonWriter(ArrayList<UserName> users , int index , int update  ) throws IOException {
        UserName user;
        user = users.get(index);
        ObjectMapper objectMapper = new ObjectMapper();
        File JSONUser = new File ("C:/Users/bendo/IdeaProjects/JSONPractice/src/main/java/JSON/JSONFiles/Users.json");
        String getuser = user.getuserName();
        JsonNode root = objectMapper.readTree(JSONUser);
        if (root.isArray()) {
            ArrayNode usersArray = (ArrayNode) root;

            // Iterate through users to find the correct one
            for (JsonNode node : usersArray) {
                if (node.has("userName") && node.get("userName").asText().equals(getuser)) {
                    // Update the "watches" field
                    if (node.has("watches") && node.get("watches").isInt()) {
                        ((ObjectNode) node).put("watches", update + user.getWatches());
                        break; // Stop once found
                    }
                }
            }
        }

        // Write the updated JSON back to file
        objectMapper.writeValue(JSONUser, root);

    }

    public void JSONWriterAddNewUser(UserName newUser) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File JSONUser = new File ("C:/Users/bendo/IdeaProjects/JSONPractice/src/main/java/JSON/JSONFiles/Users.json");

        //JsonNode root = objectMapper.readTree(JSONUser);

        ArrayNode usersArray;
        if(JSONUser.exists() && JSONUser.length() > 0 ){
            JsonNode root = objectMapper.readTree(JSONUser);
            if (root.isArray()) {
                usersArray = (ArrayNode) root;
            } else {

                usersArray = objectMapper.createArrayNode();
                usersArray.add(root);
            }
        } else {

            usersArray = objectMapper.createArrayNode();
        }

        ObjectNode newUserNode = objectMapper.createObjectNode();
        newUserNode.put("userName", newUser.getuserName());
        newUserNode.put("age", newUser.getAge());
        newUserNode.put("height", newUser.getHeight());
        newUserNode.put("country", newUser.getCountry());
        newUserNode.put("film", newUser.getFilm());
        newUserNode.put("watches", newUser.getWatches());

        // Append the new user to the array
        usersArray.add(newUserNode);

        // Write back the updated array to the file
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(JSONUser, usersArray);






    }

}
