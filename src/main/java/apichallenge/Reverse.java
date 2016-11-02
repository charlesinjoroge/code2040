package apichallenge;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

class ReverseTokens {

  public String token;
  public String string;

}

public class Reverse {

  /**
   * This method will send my token to the API, receive a string to reverse and
   * then send back the reverse string.
   */
  public static void reverse() {

    String toReverse = Requests.get("reverse");

    // reversing string here
    ReverseTokens token = new ReverseTokens();
    token.string = new StringBuffer(toReverse).reverse().toString();
    token.token = "1a958cb7c20674a21e26774589880a30";

    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();
    String toAPI = gson.toJson(token);

    Requests.send("reverse/validate", toAPI);

  }

}
