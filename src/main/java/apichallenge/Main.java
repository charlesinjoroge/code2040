package apichallenge;

/**
 * The Main method for the API Challenge
 * @author Charles Njoroge
 */
public final class Main {

  /**
   * The main method
   * @param args The command line arguments
   */
  public static void main(String[] args) {
    new Main().run();
  }

  /**
   * @param args The command line arguments
   */
  private Main() {

  }

  /**
   * The run method called in main that runs every task - register : registers
   * github - reverse : reverses string - prefix : find words not starting with
   * prefix - haystack : find needle location in haystack - dating : add certain
   * amount of seconds to date
   */
  private void run() {
    Register.register();
    Reverse.reverse();
    Prefix.nonPrefix();
    Haystack.findNeedle();
    Dating.addSeconds();

  }

}
