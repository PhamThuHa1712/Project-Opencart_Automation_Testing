package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CheckoutData {
	private String firstName;
    private String lastName;
    private String company;
    private String address1;
    private String address2;
    private String city;
    private String postCode;
    private String country;
    private String region_state;
}
