package apichallenge;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

class PrefixTokens {

  public String token;
  public String[] array;

}

class PrefixToken {
  public String token;
}

public class Prefix {

  /**
   * This method works by sending my token to the API, receiving a dictionary
   * and then returning an array of items that do not start with the prefix.
   */
  public static void nonPrefix() {

    String toPrefix = Requests.get("prefix");

    JsonElement jelement = new JsonParser().parse(toPrefix);
    JsonObject jobject = jelement.getAsJsonObject();
    String prefix = jobject.get("prefix").getAsString();
    JsonArray array = jobject.getAsJsonArray("array");

    // looking for items that don't start with the prefix here
    List<String> nonPrefix = new ArrayList<String>();
    for (int toReturn = 0; toReturn < array.size(); toReturn++) {
      if (!array.get(toReturn).toString().replaceAll("\"", "")
              .startsWith(prefix)) {
        nonPrefix.add(array.get(toReturn).toString().replaceAll("\"", ""));
      }
    }

    PrefixTokens token = new PrefixTokens();

    String[] stockArr = new String[nonPrefix.size()];
    stockArr = nonPrefix.toArray(stockArr);

    token.array = stockArr;
    token.token = "1a958cb7c20674a21e26774589880a30";

    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();
    String toAPI = gson.toJson(token);

    Requests.send("prefix/validate", toAPI);

  }

}
