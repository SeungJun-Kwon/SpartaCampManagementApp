import java.util.HashMap;
import java.util.Map;

public class Score {
    private String scoreId;
    private int scoreIndex;
    private int scoreValue;

    public Score(String scoreId, int scoreIndex, int scoreValue) {
        this.scoreId = scoreId;
        this.scoreIndex = scoreIndex;
        this.scoreValue = scoreValue;
    }

    public String getScoreId() {
        return scoreId;
    }
    public int getScoreIndex() {
        return scoreIndex;
    }
    public int getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(int value) {
        this.scoreValue = value;
    }
}
