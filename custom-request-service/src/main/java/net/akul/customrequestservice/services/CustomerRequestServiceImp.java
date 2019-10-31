package net.akul.customrequestservice.services;


import net.akul.customrequestservice.handler.exception.NotFoundRuntimeException;
import net.akul.customrequestservice.model.CustomerRequest;
import net.akul.customrequestservice.repositories.CustomerRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerRequestServiceImp implements CustomerRequestService{

    @Autowired
    private CustomerRequestRepository customerRequestRepository;

    @Override
    public List<CustomerRequest> findAll() {
        return customerRequestRepository.findAll();
    }

    @Override
    public List<CustomerRequest> findAllByIds(List<Long> ids) {
        return customerRequestRepository.findAllById(ids);
    }

    @Override
    public CustomerRequest findByRequestName(String name) {
        return null;
    }

    @Override
    public CustomerRequest findById(Long id) {
        return customerRequestRepository.findById(id)
                .orElseThrow(() -> new NotFoundRuntimeException(id, "CustomerRequest can't be found!"));
    }

    @Override
    public CustomerRequest createCustomerRequest(CustomerRequest customerRequest) {
        return  customerRequestRepository.save(customerRequest);
    }
}
