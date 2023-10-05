package com.clk.ailatrieuphujava.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuestionDAO {
    String SELECT_15_QUESTION = "SELECT * FROM (SELECT * FROM Question WHERE level = 1 ORDER BY RANDOM() LIMIT 1)\n" +
            "UNION SELECT * FROM(SELECT * FROM Question WHERE level = 2 ORDER BY RANDOM() LIMIT 1)\n" +
            "UNION SELECT * FROM(SELECT * FROM Question WHERE level = 3 ORDER BY RANDOM() LIMIT 1)\n" +
            "UNION SELECT * FROM(SELECT * FROM Question WHERE level = 4 ORDER BY RANDOM() LIMIT 1)\n" +
            "UNION SELECT * FROM(SELECT * FROM Question WHERE level = 5 ORDER BY RANDOM() LIMIT 1)\n" +
            "UNION SELECT * FROM(SELECT * FROM Question WHERE level = 6 ORDER BY RANDOM() LIMIT 1)\n" +
            "UNION SELECT * FROM(SELECT * FROM Question WHERE level = 7 ORDER BY RANDOM() LIMIT 1)\n" +
            "UNION SELECT * FROM(SELECT * FROM Question WHERE level = 8 ORDER BY RANDOM() LIMIT 1)\n" +
            "UNION SELECT * FROM(SELECT * FROM Question WHERE level = 9 ORDER BY RANDOM() LIMIT 1)\n" +
            "UNION SELECT * FROM(SELECT * FROM Question WHERE level = 10 ORDER BY RANDOM() LIMIT 1)\n" +
            "UNION SELECT * FROM(SELECT * FROM Question WHERE level = 11 ORDER BY RANDOM() LIMIT 1)\n" +
            "UNION SELECT * FROM(SELECT * FROM Question WHERE level = 12 ORDER BY RANDOM() LIMIT 1)\n" +
            "UNION SELECT * FROM(SELECT * FROM Question WHERE level = 13 ORDER BY RANDOM() LIMIT 1)\n" +
            "UNION SELECT * FROM(SELECT * FROM Question WHERE level = 14 ORDER BY RANDOM() LIMIT 1)\n" +
            "UNION SELECT * FROM(SELECT * FROM Question WHERE level = 15 ORDER BY RANDOM() LIMIT 1)\n";

    @Query(SELECT_15_QUESTION)
    List<Question> getAllQuestion();

    @Query("SELECT * FROM Question WHERE level = :level ORDER BY RANDOM() LIMIT 1")
    Question getQuestionByLevel(int level);

    @Query("SELECT * FROM HighScore ORDER BY Score DESC")
    HighScore getQuestionByLevel();

    @Insert
    void insertHighScore(HighScore... highScores);
}
