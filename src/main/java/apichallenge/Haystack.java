package apichallenge;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

class HayStackTokens {

  public String token;
  public int needle;

}

public class Haystack {

  /**
   * This method works by sending my token to the API, receiving a needle and a
   * hay stack and sending back the array location of the needle.
   */
  public static void findNeedle() {

    String toHaystack = Requests.get("haystack");

    JsonElement jelement = new JsonParser().parse(toHaystack.toString());
    JsonObject jobject = jelement.getAsJsonObject();
    String needle = jobject.get("needle").getAsString();
    JsonArray haystack = jobject.getAsJsonArray("haystack");

    // looking for needle here
    int toReturn;
    for (toReturn = 0; toReturn < haystack.size(); toReturn++) {
      if (needle
              .equals(haystack.get(toReturn).toString().replaceAll("\"", ""))) {
        break;
      }
    }

    HayStackTokens token = new HayStackTokens();
    token.needle = toReturn;
    token.token = "1a958cb7c20674a21e26774589880a30";

    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();
    String toAPI = gson.toJson(token);

    Requests.send("haystack/validate", toAPI);

  }

}
