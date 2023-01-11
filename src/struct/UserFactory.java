package struct;

public class UserFactory {
    public enum UserEnum { UserPremium, UserStandard }

    /**
     * Factory pattern implementation
     * @param userEnum
     * @param credentials
     * @return
     */
    public static User createUser(final UserEnum userEnum, final Credentials credentials) {
        switch (userEnum) {
            case UserPremium:
                return new UserPremium(credentials);
            case UserStandard:
                return new UserStandard(credentials);
            default:
                return null;
        }
    }
}
