import java.util.HashMap;
import java.util.Map;

// start
public class Score {
    private String scoreId;
    private int scoreIndex;
    private int scoreValue;

    public Score(String scoreId, int scoreIndex, int scoreValue) {
        this.scoreId = scoreId;
        this.scoreIndex = scoreIndex;
        this.scoreValue = scoreValue;
    }

    // 스코어 객체의 고유 키를 반환하는 메소드
    public String getScoreId() {
        return scoreId;
    }

    // 스코어 객체의 회차를 반환하는 메소드
    public int getScoreIndex() {
        return scoreIndex;
    }

    // 스코어 객체의 점수를 반환하는 메소드
    public int getScore() {
        return scoreValue;
    }

    // 스코어 객체의 점수를 수정하는 메소드
    public void setScore(int newScore) {
        this.scoreValue = newScore;
    }
}
