package mocking;

public class CustomerService {

  public int addCustomer(Customer customer, CustomerDao dao){
    //add customer logic
    if(customer != null){
      return dao.save(customer);
    }
    return -1;
  }

  public void updateCustomer(Customer customer, CustomerDao dao){
    if(customer != null){
      dao.save(customer);
    }else
      throw new RuntimeException("Customer is null");
  }

}
