package Framework.service;

import Framework.model.User;

public class UserCreator {
    private final static String TEST_USER_EMAIL_TEMPLATE = 
        "Framework.test.loginTest.generic.user.%d.email";
    private final static String TEST_USER_PASSWORD_TEMPLATE = 
        "Framework.test.loginTest.generic.user.%d.password";
    private final static String TEST_USER_NAME_TEMPLATE = 
        "Framework.test.loginTest.generic.user.%d.username";

    public static User constructFromProperties(int userNumber) {
        String email = 
            TestDataReader.getTestData(String.format(TEST_USER_EMAIL_TEMPLATE, userNumber));
        String password = 
            TestDataReader.getTestData(String.format(TEST_USER_PASSWORD_TEMPLATE, userNumber));
        String username =
            TestDataReader.getTestData(String.format(TEST_USER_NAME_TEMPLATE, userNumber));

        return new User(
            email,
            password,
            username
        );
    }
}
