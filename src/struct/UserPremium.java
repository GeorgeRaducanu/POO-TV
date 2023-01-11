package struct;

public class UserPremium extends User {

    /**
     * Premium user constructor
     * @param user
     */
    public UserPremium(final User user) {
        super(user);
    }

    /**
     * Premium user constructor
     * @param cred
     */
    public UserPremium(final Credentials cred) {
        super(cred);
    }
}
