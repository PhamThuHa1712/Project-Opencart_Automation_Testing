package utilities;

import java.util.ResourceBundle;

import com.github.javafaker.Faker;

import models.CheckoutData;
import models.GuestCheckoutData;
import models.RegisterCheckoutData;
import models.RegisterData;

public class TestDataFactory {
	public static final Faker faker = new Faker();
	public static final ResourceBundle rb;
	
	static {
		try {
			rb = ResourceBundle.getBundle("config");
		} catch(Exception e) {
			throw new RuntimeException("CRITICAL: File config.properties bị thiếu hoặc lỗi!");
		}
	}
	
    public static RegisterData validData() {
        String password = faker.internet().password(6, 10);
        return RegisterData.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .phone(faker.phoneNumber().cellPhone())
                .password(password)
                .confirmPassword(password)
                .newsletter(true)
                .privacyPolicy(true)
                .build();
    }
    
    public static RegisterData multipleSpaceData(int spaceCount) {
    		String spaces = " ".repeat(spaceCount);
    		return RegisterData.builder()
    				.firstName(spaces)
    	            .lastName(spaces)
    	            .email(spaces)
    	            .phone(spaces)
    	            .password(spaces)
    	            .confirmPassword(spaces)
    	            .newsletter(true)
    	            .privacyPolicy(false)
    	            .build();
    }

    public static String getExistingEmail() {
    		try {
            return rb.getString("email");
        } catch (Exception e) {
            throw new RuntimeException("FAILED: Key 'email' không tồn tại trong file config.properties!");
        }
    }
    
    public static CheckoutData checkoutData() {
    		return CheckoutData.builder()
    				.firstName(faker.name().firstName())
    				.lastName(faker.name().lastName())
    				.company(faker.company().name())
    				.address1("Minh Khai - Bắc Từ Liêm")
    				.address2("Minh Khai - Bắc Từ Liêm")
    				.city("Hà Nội")
    				.postCode(faker.number().digits(6))
    				.country("Viet Nam")
    				.regionState("Ha Noi")
    				.build();
    }
    
    public static GuestCheckoutData guestCheckoutData() {
		return GuestCheckoutData.builder()
				.firstName(faker.name().firstName())
				.lastName(faker.name().lastName())
				.email(faker.internet().emailAddress())
				.telephone(faker.phoneNumber().cellPhone())
				.company(faker.company().name())
				.address1("Minh Khai - Bắc Từ Liêm")
				.address2("Minh Khai - Bắc Từ Liêm")
				.city("Hà Nội")
				.postCode(faker.number().digits(6))
				.country("Viet Nam")
				.regionState("Ha Noi")
				.build();
    }
    
    public static RegisterCheckoutData registerCheckoutData() {
    		String password = faker.internet().password();
    		return RegisterCheckoutData.builder()
				.firstName(faker.name().firstName())
				.lastName(faker.name().lastName())
				.email(faker.internet().emailAddress())
				.telephone(faker.phoneNumber().cellPhone())
				.password(password)
				.passwordConfirm(password)
				.company(faker.company().name())
				.address1("Minh Khai - Bắc Từ Liêm")
				.address2("Minh Khai - Bắc Từ Liêm")
				.city("Hà Nội")
				.postCode(faker.number().digits(6))
				.country("Viet Nam")
				.regionState("Ha Noi")
				.build();
    }
    
    public static RegisterData leadingAndTrailingSpaceData() {
    		String space = " ".repeat(3);
	    	String password = space + faker.internet().password(6, 10) + space;
	        return RegisterData.builder()
	                .firstName(space + faker.name().firstName() + space)
	                .lastName(space + faker.name().lastName() + space)
	                .email(space + faker.internet().emailAddress() + space)
	                .phone(space + faker.phoneNumber().cellPhone() + space)
	                .password(password)
	                .confirmPassword(password)
	                .newsletter(true)
	                .privacyPolicy(true)
	                .build();
    }
}
