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
}
