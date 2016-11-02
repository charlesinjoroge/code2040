package apichallenge;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * This class will handle all of the get and post requests for the API Challenge
 * @author Charles Njoroge
 */
public class Requests {

  private static String mainURL = "http://challenge.code2040.org/api/";
  private static String token = "1a958cb7c20674a21e26774589880a30";

  static class Token {
    public String token;
  }

  /**
   * Gets the information to be used for each task
   * @param destination : The location to send the token to
   * @param data : The data to send to the API consisting of my token
   * @return The data to be manipulated in the task
   */
  public static String get(String destination) {
    try {

      Token toURL = new Token();
      toURL.token = token;

      GsonBuilder readbuilder = new GsonBuilder();
      Gson readgson = readbuilder.create();
      String toRead = readgson.toJson(toURL);

      URL url = new URL(mainURL.concat(destination));
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("POST");
      connection.setRequestProperty("Content-Type", "application/json");
      connection.setDoOutput(true);

      DataOutputStream stream =
              new DataOutputStream(connection.getOutputStream());

      stream.writeBytes(toRead);
      stream.flush();
      stream.close();
      connection.disconnect();

      BufferedReader in = new BufferedReader(
              new InputStreamReader(connection.getInputStream()));

      StringBuilder readBuilder = new StringBuilder();
      String line;

      while ((line = in.readLine()) != null) {
        readBuilder.append(line);
      }

      error(connection);

      in.close();
      connection.disconnect();

      return readBuilder.toString();

    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;

  }

  /**
   * Sends the correct data to the API
   * @param destination : The location to send the data to
   * @param data : The solution to the task that is being sent to the API
   */
  public static void send(String destination, String data) {
    URL outURL;
    try {
      outURL = new URL(mainURL.concat(destination));
      HttpURLConnection outConnection =
              (HttpURLConnection) outURL.openConnection();

      outConnection.setRequestMethod("POST");
      outConnection.setRequestProperty("Content-Type", "application/json");
      outConnection.setDoOutput(true);

      DataOutputStream ds =
              new DataOutputStream(outConnection.getOutputStream());

      ds.writeBytes(data);
      error(outConnection);

      ds.flush();
      ds.close();
      outConnection.disconnect();
    } catch (MalformedURLException e) {
      System.out.println(e.getMessage());
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }

  }

  /**
   * Checks if there was an error in the result sent to the API
   * @param connection : A connection to the API
   */
  public static void error(HttpURLConnection connection) {
    try {
      if (connection.getResponseCode() == 400) {
        BufferedReader out = new BufferedReader(
                new InputStreamReader(connection.getErrorStream()));
        String thisLine;
        StringBuilder build = new StringBuilder();

        while ((thisLine = out.readLine()) != null) {
          build.append(thisLine);
        }
        System.out.println(build.toString());
      } else {
        System.out.println(
                connection.getResponseCode() + " " + connection.getURL());
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());

    }

  }

}
