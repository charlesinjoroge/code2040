package apichallenge;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

class RegisterTokens {

  public String token;
  public String github;

}

public class Register {

  /**
   * This method sends a request to the API with my token and my github as
   * described in the task
   */
  public static void register() {

    RegisterTokens tokens = new RegisterTokens();
    tokens.token = "1a958cb7c20674a21e26774589880a30";
    tokens.github = "https://github.com/cnjoroge/code2040";
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();
    String toAPI = gson.toJson(tokens);

    Requests.send("register", toAPI);

  }

}
