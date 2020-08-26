package mocking;


public class MessageService {

  public void send(String sendType,String message){
    if("Email".equals(sendType)){
      MessageUtil.sendEmail(message);
    }else{
      MessageUtil.sendFax(message);
    }
  }
}
