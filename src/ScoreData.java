import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ScoreData {
    private static final String INDEX_TYPE_SCORE = "SC";
    private static Map<String, Score> scoreStore;

    public static Map<String, Score> getScoreStore() {
        return scoreStore;
    }

    public static Score getScoreById(String id) {
        return scoreStore.get(id);
    }

    public static List<Score> getScoreByIndex(String index) {
        return scoreStore.values().stream().filter(s -> s.getScoreId().equals(index)).toList();
    }

    public static boolean addScore(Score score) {
        if(scoreStore.containsKey(score.getScoreId())) {
            return false;
        }

        scoreStore.put(score.getScoreId(), score);
        return true;
    }

    public static boolean setScoreValue(String id, int value) {
        if(scoreStore.containsKey(id)) {
            scoreStore.get(id).setScoreValue(value);
            return true;
        }

        return false;
    }
}
