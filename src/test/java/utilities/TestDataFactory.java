package utilities;

import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.javafaker.Faker;

import models.CheckoutData;
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
    
    public static RegisterData multipleSpaceData() {
    		return RegisterData.builder()
    				.firstName("     ")
                .lastName("     ")
                .email("     ")
                .phone("     ")
                .password("     ")
                .confirmPassword("     ")
                .newsletter(true)
                .privacyPolicy(true)
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
    				.address1("Minh Khai - Bắc Từ Liêm - Hà Nội - Việt Nam")
    				.address2("Minh Khai - Bắc Từ Liêm - Hà Nội - Việt Nam")
    				.city("Hà Nội")
    				.postCode(faker.number().digits(6))
    				.country("Viet Nam")
    				.region_state("Ha Noi")
    				.build();
    }
}
