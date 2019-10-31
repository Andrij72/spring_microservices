package net.akul.customrequestservice.controllers;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.akul.customrequestservice.dto.CustomerRequestDto;
import net.akul.customrequestservice.model.CustomerRequest;
import net.akul.customrequestservice.repositories.CustomerRequestRepository;
import net.akul.customrequestservice.services.CustomerRequestService;
import net.akul.customrequestservice.transformers.CustomerRequestTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class CustomerRequestController implements InitializingBean {
   final Logger logger = LoggerFactory.getLogger(CustomerRequestController.class);

    @Autowired
    DiscoveryClient client;

    Map<String, Double> pricingMap = new HashMap<>();

    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private CustomerRequestTransformer customerRequestTransformer;

    @Autowired
    private CustomerRequestService customerRequestService;

    @Autowired
    private CustomerRequestRepository customerRequestRepository;




    @GetMapping( value = "")
    public List<CustomerRequestDto> findAll() {

        List<ServiceInstance> instances
                = client.getInstances("custom-request-service");
        ServiceInstance instance
                = instances.stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("not found"));
        String url = String.format("%s/api",
                instance.getUri());
        List<CustomerRequest> custRequest;
        List<CustomerRequest> customerRequests = customerRequestService.findAll();
        //custRequest = restTemplate.getForObject("http://localhost:8001/api",customerRequests.toArray());

        ResponseEntity<List<String>> quoteResponse = restTemplate.exchange("http://localhost:8300/rest/db/"+customerRequests , HttpMethod.GET,
                null, new ParameterizedTypeReference<List<String>>() {
                });


        List<CustomerRequest> quotes = quoteResponse.getBody();



        return customerRequestTransformer.buildListCustomerRequestDto(quotes);
    }

    @ApiOperation(value = "Add an customer_request")
    @PostMapping("/")
    public CustomerRequest createEmployee(
            @ApiParam(value = "Employee object store in database table", required = true)
            @Valid @RequestBody CustomerRequest customerRequest) {
        return customerRequestRepository.save(customerRequest);
    }
    @PostMapping( value = "")
       public CustomerRequest create(@RequestBody CustomerRequest customerRequest) {
        logger.info("Create customer_request:" + customerRequest);

        customerRequestService.createCustomerRequest(customerRequest);
        logger.info("Customer_request created successfully with info: " + customerRequest);
return customerRequest;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Successful init been CustomerRequestController");
    }
}
