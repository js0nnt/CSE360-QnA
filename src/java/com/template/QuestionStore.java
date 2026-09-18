package com.template;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;



public class QuestionStore {
    //connection string
    private static final String DB_URL = "jdbc:sqlite:questions.db";

    public static Connection connect() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL);

        try (PreparedStatement stmt = conn.prepareStatement("PRAGMA foreign_keys = ON")) 
        {
            stmt.execute();
        } // every key enables foreign key enforcment

        return conn;
    }

    public static void initSchema() throws SQLException {
    String questionsSql = "CREATE TABLE IF NOT EXISTS questions (" +
            "id TEXT PRIMARY KEY, " +
            "author TEXT NOT NULL, " +
            "text TEXT NOT NULL, " +
            "timestamp INTEGER NOT NULL, " +
            "resolved INTEGER NOT NULL DEFAULT 0)"; // 0 for false, 1 for true

    String answersSql = "CREATE TABLE IF NOT EXISTS answers (" +
            "id TEXT PRIMARY KEY, " +
            "question_id TEXT NOT NULL, " +
            "author TEXT NOT NULL, " +
            "text TEXT NOT NULL, " +
            "timestamp INTEGER NOT NULL, " +
            "FOREIGN KEY (question_id) REFERENCES questions(id))";

    try (Connection conn = connect();
        PreparedStatement q = conn.prepareStatement(questionsSql);
        PreparedStatement a = conn.prepareStatement(answersSql)) 
        {
            q.execute();
            a.execute();
        }
    }

    private static void validatePost(String author, String text){
        if(author == null || author.isBlank()){
            throw new IllegalArgumentException("Author blank");
        }
        if(text == null || text.isBlank()){
            throw new IllegalArgumentException("Text cannot be blank");
        }
    }

    public static Questions createQuestion(String author, String text) throws SQLException {

        validatePost(author, text);
        String id = UUID.randomUUID().toString();
        long timestamp = System.currentTimeMillis();
        String sql = "INSERT INTO questions (id, author, text, timestamp) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql))
            {
                stmt.setString(1, id);
                stmt.setString(2, author);
                stmt.setString(3, text);
                stmt.setLong(4, timestamp);
                stmt.executeUpdate();
            }

            return new Questions(id, author, text, timestamp, false);
    }


    public static List<Questions> listQuestions() throws SQLException {
        List<Questions> questions = new ArrayList<>();
        String sql = "SELECT id, author, text, timestamp, resolved FROM questions ORDER BY timestamp DESC";

        try (Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery())
            {
                while(rs.next()){
                    questions.add(new Questions(
                    rs.getString("id"),
                    rs.getString("author"),
                    rs.getString("text"),
                    rs.getLong("timestamp"),
                    rs.getInt("resolved") == 1)); // convert integer to boolean
                }
            }

        return questions;
    }

    public static List<Questions> searchQuestions(String keyword) throws SQLException {
        
        List<Questions> matches = new ArrayList<>();
        String sql = "SELECT id, author, text, timestamp, resolved FROM questions WHERE text LIKE ? ORDER BY timestamp DESC";

        try (Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql))
            {
                stmt.setString(1, "%" + keyword + "%");
                
                try (ResultSet rs = stmt.executeQuery()){
                    while(rs.next()){
                        matches.add(new Questions(
                            rs.getString("id"),
                            rs.getString("author"),
                            rs.getString("text"),
                            rs.getLong("timestamp"),
                            rs.getInt("resolved") == 1)); // convert integer to boolean
                    }
                }
            }
        return matches;
    }

    public static Answers addAnswer(String questionId, String author, String text) throws SQLException {

        validatePost(author, text);
        String id = UUID.randomUUID().toString();
        long timestamp = System.currentTimeMillis();
        String sql = "INSERT INTO answers (id, question_id, author, text, timestamp) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql))
            {
                stmt.setString(1, id);
                stmt.setString(2, questionId);
                stmt.setString(3, author);
                stmt.setString(4, text);
                stmt.setLong(5, timestamp);
                stmt.executeUpdate();
            }

            return new Answers(id, questionId, author, text, timestamp);
    }

    public static List<Answers> listAnswersFor(String questionId) throws SQLException {
        List<Answers> answers = new ArrayList<>();
        String sql = "SELECT id, question_id, author, text, timestamp FROM answers WHERE question_id = ? ORDER BY timestamp ASC";

        try (Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql))
            {
                stmt.setString(1, questionId);
                
                try (ResultSet rs = stmt.executeQuery()){
                    while(rs.next()){
                        answers.add(new Answers(
                            rs.getString("id"),
                            rs.getString("question_id"),
                            rs.getString("author"),
                            rs.getString("text"),
                            rs.getLong("timestamp")));
                    }
                }
            }
        return answers;
    }

    public static List<Questions> listUnresolvedQuestions() throws SQLException {
        List<Questions> questions = new ArrayList<>();
        String sql = "SELECT id, author, text, timestamp, resolved FROM questions WHERE resolved = 0 ORDER BY timestamp DESC";

        try (Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery())
            {
                while(rs.next()){
                    questions.add(new Questions(
                    rs.getString("id"),
                    rs.getString("author"),
                    rs.getString("text"),
                    rs.getLong("timestamp"),
                    rs.getInt("resolved") == 1)); // convert integer to boolean
                }
            }

        return questions;
    }

    public static boolean markQuestionsResolved(String questionId) throws SQLException{
        if(questionId == null || questionId.isBlank()){
            throw new IllegalArgumentException("Question ID cannot be blank");
        }

        String sql = "UPDATE questions SET resolved = 1 WHERE id = ?";

        try(Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql))
            {
                stmt.setString(1, questionId);
                int rowsUpdated = stmt.executeUpdate();
                return rowsUpdated == 1; // returns true if exactly one row was updated
            }
    }
}

