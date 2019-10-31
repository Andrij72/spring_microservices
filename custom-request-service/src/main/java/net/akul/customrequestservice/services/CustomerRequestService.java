package net.akul.customrequestservice.services;


import net.akul.customrequestservice.model.CustomerRequest;

import java.util.List;


public interface CustomerRequestService {

    List<CustomerRequest> findAll();

    CustomerRequest findById(Long id);

  List<CustomerRequest> findAllByIds(List<Long> ids);

    CustomerRequest findByRequestName(String name);

   CustomerRequest createCustomerRequest(CustomerRequest customerRequest);
}
