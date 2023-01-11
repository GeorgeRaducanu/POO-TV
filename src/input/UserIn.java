package input;

public class UserIn {
    private CredentialsIn credentials;

    public UserIn() {

    }

    public final CredentialsIn getCredentials() {
        return credentials;
    }

    public final void setCredentials(final CredentialsIn credentials) {
        this.credentials = credentials;
    }

    @Override
    public final String toString() {
        return "UserIn{"
                +
                "credentials=" + credentials
                +
                '}';
    }
}
