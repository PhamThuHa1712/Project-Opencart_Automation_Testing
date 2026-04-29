package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutData {
	private String firstName;
    private String lastName;
    private String company;
    private String address1;
    private String address2;
    private String city;
    private String postCode;
    private String country;
    private String regionState;
}