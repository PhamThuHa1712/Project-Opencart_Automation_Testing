package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterData {
	private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private String confirmPassword;
    private boolean newsletter;
    private boolean privacyPolicy;
    
    // getter
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getPassword() { return password; }
    public String getConfirmPassword() { return confirmPassword; }
    public boolean isNewsletter() { return newsletter; }
    public boolean isPrivacyPolicy() { return privacyPolicy; }
    
    public String getExpectedFirstNameTrimmed() {
        return firstName.trim();
    }

    public String getExpectedLastNameTrimmed() {
        return lastName.trim();
    }
    
    public String getExpectedEmailTrimmed() {
        return email.trim();
    }

    public String getExpectedPhoneTrimmed() {
        return phone.trim();
    }
}
