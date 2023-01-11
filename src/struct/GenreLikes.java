package struct;

public class GenreLikes {
    private String genreName;

    private int numLikes;

    public final String getGenreName() {
        return genreName;
    }

    public final void setGenreName(final String genreName) {
        this.genreName = genreName;
    }

    public final int getNumLikes() {
        return numLikes;
    }

    public final void setNumLikes(final int numLikes) {
        this.numLikes = numLikes;
    }

    /**
     * Constructor for this class
     * @param genreName
     * @param numLikes
     */
    public GenreLikes(final String genreName, final int numLikes) {
        this.genreName = genreName;
        this.numLikes = numLikes;
    }

    /**
     * tostring method for this class
     * @return
     */
    @Override
    public String toString() {
        return "struct.Genre_Likes{"
                +
                "genreName='" + genreName + '\''
                +
                ", numeroLikes=" + numLikes
                +
                '}';
    }
}
