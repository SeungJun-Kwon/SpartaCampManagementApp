import java.util.ArrayList;
import java.util.List;

public class ScoreData {
    List<Score> scoreStore = new ArrayList<>();

    private static int scoreIndex;
    private static final String INDEX_TYPE_SCORE = "SC";

    public static int getScoreIndex() {
        return scoreIndex++;
    }

    public static String getUID() {
        return INDEX_TYPE_SCORE + getScoreIndex();
    }

    public List<Score> getScoreStore() {
        return scoreStore;
    }
}
