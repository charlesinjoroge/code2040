package apichallenge;

import org.joda.time.DateTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

class DatingTokens {

  public String token;
  public String datestamp;

}

class DatingToken {
  public String token;
}

public class Dating {

  /**
   * This method works by sending my token to the API, receiving a date and an
   * interval to add and then sending the new date back to the API
   */
  public static void addSeconds() {

    String toDate = Requests.get("dating");

    JsonElement jelement = new JsonParser().parse(toDate);
    JsonObject jobject = jelement.getAsJsonObject();
    String dateStamp = jobject.get("datestamp").getAsString();
    int interval = jobject.get("interval").getAsInt();

    // Adding to the date here
    DateTime datetime = DateTime.parse(dateStamp);
    datetime = datetime.plusSeconds(interval);

    DatingTokens token = new DatingTokens();
    token.token = "1a958cb7c20674a21e26774589880a30";
    token.datestamp = datetime.toString("yyyy-MM-dd'T'HH:mm:ss") + "Z";

    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();
    String toAPI = gson.toJson(token);

    Requests.send("dating/validate", toAPI);
  }

}
