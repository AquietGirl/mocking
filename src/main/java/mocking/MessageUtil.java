package mocking;

public class MessageUtil {
  public static String sendMessage(String message){
    return "real Send message";
  }


  public static String sendEmail(String message){
    return "email";
  }

  public static String sendFax(String message){
    return "fax";
  }

}
