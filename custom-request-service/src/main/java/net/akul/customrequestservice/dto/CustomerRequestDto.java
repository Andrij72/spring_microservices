package net.akul.customrequestservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.akul.customrequestservice.model.Status;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
public class CustomerRequestDto {
    private Long customerid;
    private Timestamp registrationDate;
    private String requestName;
    private String customerName;
    private String attachedDocuments;
    private Status status;
}
