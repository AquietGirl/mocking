package mocking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {MessageUtil.class})

public class PowerMockSample {

  @Test
  public void mock_static_method_not_the_real_method() throws Exception {
    mockStatic(MessageUtil.class);
    assertNotEquals("real Send message", MessageUtil.sendMessage(""));


  }

  @Test
  public void mock_static_method_return() throws Exception {
    mockStatic(MessageUtil.class);
    when(MessageUtil.sendMessage(anyString())).thenReturn("1");
    assertEquals("1",MessageUtil.sendMessage(""));
  }

  @Test
  public void mock_static_verify() throws Exception {
    mockStatic(MessageUtil.class);
    MessageService service = new MessageService();
    String message = "aa";
    service.send("Email", message);
    verifyStatic(times(1));
    MessageUtil.sendEmail(message);
  }





}
