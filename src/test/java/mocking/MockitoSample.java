package mocking;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

public class MockitoSample {

  @Test
  public void test_basicMocking() {
    CustomerDao customerDao = mock(CustomerDao.class);
    when(customerDao.save(any())).thenReturn(2);

    Customer kwanfo = new Customer();
    CustomerService service = new CustomerService();
    int customerId = service.addCustomer(kwanfo, customerDao);
    Assert.assertEquals(2, customerId);

  }


  @Test
  public void test_mocking_verify_call() {
    CustomerDao customerDao = mock(CustomerDao.class);
    when(customerDao.save(any())).thenReturn(2);

    Customer kwanfo = new Customer();
    CustomerService service = new CustomerService();
    int customerId = service.addCustomer(kwanfo, customerDao);

    verify(customerDao,times(1)).save(kwanfo);
  }

  @Test
  public void test_mocking_verify_no_call() {
    CustomerDao customerDao = mock(CustomerDao.class);
    when(customerDao.save(any())).thenReturn(2);

    CustomerService service = new CustomerService();
    service.addCustomer(null, customerDao);

    verify(customerDao,times(0));
    customerDao.save(any(Customer.class));

  }

  @Rule
  public ExpectedException expectedException = ExpectedException.none();
  @Test
  public void mock_expect_exception() throws Exception{
    expectedException.expect(RuntimeException.class);
    expectedException.expectMessage("Customer is null");
    CustomerService service = new CustomerService();
    service.updateCustomer(null,null);
  }

  @Test
  public void mock_expect_no_exception() throws Exception{
    expectedException = ExpectedException.none();
    CustomerDao customerDao = mock(CustomerDao.class);
    when(customerDao.save(any())).thenReturn(2);

    CustomerService service = new CustomerService();
    service.updateCustomer(new Customer(),customerDao);
  }
}
