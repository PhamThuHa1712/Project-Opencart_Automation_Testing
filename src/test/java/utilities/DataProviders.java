package utilities;

import java.io.IOException;
import java.util.ResourceBundle;

import org.testng.annotations.DataProvider;

public class DataProviders {
	private ResourceBundle rb = TestDataFactory.rb;
	//DataProvider 1
	@DataProvider(name="Newsletter")
	public Object[][] newsLetterData() throws IOException
	{
		Object newsdata[][] = {
				{true},
				{false}
		};
	    
	    return newsdata; 
	}
	
	//DataProvider 2
	@DataProvider(name="emailValidationData")
	public Object[][] emailValidationData() {
		return new Object[][] {
			{"TC_RF_010_INVALID_FORMAT_MISSING_DOMAIN", "abc@", "E-Mail Address does not appear to be valid!"},
	        
	        {"TC_RF_010_EMPTY_EMAIL", "", "E-Mail Address does not appear to be valid!"},
	        
	        {"TC_RF_010_MISSING_AT_SIGN", "hapham.gmail.com", "E-Mail Address does not appear to be valid!"},

	        {"TC_RF_010_EMAIL_WITH_SPACE", "ha pham@gmail.com", "E-Mail Address does not appear to be valid!"},
	        
	        {"TC_RF_010_MISSING_DOT_COM", "hapham@gmail", "E-Mail Address does not appear to be valid!"},
	        	        
	        {"TC_RF_010_EMAIL_TOO_LONG", "thisisaverylongemailaddressexceedingthelimitofopencartvalidationatthismoment@gmail.com", "E-Mail Address does not appear to be valid!"}
		};
	}
	
	//DataProvider 3
	@DataProvider(name = "phoneValidationData")
	public Object[][] phoneValidationData() {
	    return new Object[][] {
	        { "Số điện thoại quá ngắn", "01", "Telephone must be between 3 and 32 characters!" },
	        
	        { "Số điện thoại quá dài", "0123456789012345678901234567890123", "Telephone must be between 3 and 32 characters!" },
	        
	        { "Số điện thoại chứa chữ cái", "0987abc123", "Telephone is invalid!" },
	        
	        { "Số điện thoại trống", "", "Telephone must be between 3 and 32 characters!" },
	        
	        { "Số điện thoại chứa kí tự đặc biệt", "@#$%^&*", "Telephone is invalid!" }
	    };
	}
	
	// DataProvider 4
	@DataProvider(name="passwordValidationData")
	public Object[][] passwordValidationData() {
		return new Object[][] {
			{"Độ dài mật khẩu không đủ 4 ký tự", "Ab@", "Password must be between 4 and 20 characters!"},
			
			{"Độ dài mật khẩu vượt quá 20 ký tự", "Test@1234567890LongPassword!!", "Password must be between 4 and 20 characters!"},
			
			{"Mật khẩu không chứa chữ hoa", "abc@1234", "Password must be between 4 and 20 characters!"},
			
			{"Mật khẩu không chứa chữ thường", "ABC@1234", "Password must be between 4 and 20 characters!"},
			
			{"Mật khẩu thiếu chữ số", "Abc@defg", "Password must be between 4 and 20 characters!"},
			
			{"Mật khẩu thiếu ký tự đặc biệt", "Abc12345", "Password must be between 4 and 20 characters!"},
			
			{"Mật khẩu chứa dấu cách", "Abc 1234@", "Password must be between 4 and 20 characters!"},
			
			{"Mật khẩu chứa ký tự lạ", "Abc@1234<", "Password must be between 4 and 20 characters!"}
		};
	}
	
	//DataProvider 5
	@DataProvider(name="loginValidationData")
	public Object[][] loginValidationData() {
		return new Object[][] {
			{"TC_LF_002_LoginWithInvalidCredentials", "wrong_email@gmail.com", "wrong_pass", "Warning: No match for E-Mail Address and/or Password."},
			
			{"TC_LF_003_LoginWithInvalidEmailValidPassword", "wrong_email@gmail.com", rb.getString("password"), "Warning: No match for E-Mail Address and/or Password."},
			
			{"TC_LF_004_LoginWithValidEmailInvalidPassword", "hathuha@gmail.com", "wrong_pass", "Warning: No match for E-Mail Address and/or Password."},
			
			{"TC_LF_005_LoginWithoutCredentials", "", "", "Warning: No match for E-Mail Address and/or Password."}
		};
	}
}
