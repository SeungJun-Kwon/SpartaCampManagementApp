import java.util.HashMap;
import java.util.Map;

public class Score {
    private static String scoreId;
    private static int scoreIndex;
    private static int score;
    private static Map<Integer, Integer> data;

    public Score(int scoreIndex, int score) {
        this.scoreId = scoreId;
        this.scoreIndex = scoreIndex;
        this.score = score;
        this.data = new HashMap<>();
    }

    public static String getScoreId() {
        return scoreId;
    }
    public static int getScoreIndex() {
        return scoreIndex;
    }
    public static int getScore() {
        return score;
    }
    public static void getDate(int scoreIndex, int score){
        data.put(scoreIndex, score);
    }
}
