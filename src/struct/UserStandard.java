package struct;

public class UserStandard extends User {
    /**
     * Standard user constructor
     * @param user
     */
    public UserStandard(final User user) {
        super(user);
    }

    /**
     * Standard user constructor from credentials
     * @param cred
     */
    public UserStandard(final Credentials cred) {
        super(cred);
    }
}
