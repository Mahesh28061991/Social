package java.main.social.db;

import java.main.social.model.Post;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Timestamp;

public class PostDBUtil {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/EMP";

    //  Database credentials
    static final String USER = "username";
    static final String PASS = "password";

    Connection conn;

    public Statement getStatement(){

        conn = null;
        Statement stmt = null;

        try{
            Class.forName(JDBC_DRIVER);

            if(conn == null){
                conn = DriverManager.getConnection(DB_URL,USER,PASS);
            }
            stmt = conn.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }

        return stmt;
    }


    public void createPost(Post post) throws Exception {

        Statement stmt = getStatement();

        String data = post.getData();
        int likes = post.getLikes();
        int userId = post.getUserId();
        Timestamp createdTimestamp = post.getCreatedTimestamp();

        try {

            String sql = String.format("INSERT INTO post VALUES('%s','%s','%s','%s','%s')", data, likes, userId, createdTimestamp);

            stmt.executeUpdate(sql);

        } catch(Exception e){
            e.printStackTrace();
        }
        finally {
            // TODO: handle finally clause
            close(stmt);
        }
    }

    public void updatePost(int postId, String data) throws Exception {

        Statement stmt = getStatement();

        try {

            String sql = String.format("UPDATE post SET data = '%s' WHERE id = '%s'", data, postId);

            stmt.executeUpdate(sql);

        } catch(Exception e){
            e.printStackTrace();
        }
        finally {
            // TODO: handle finally clause
            close(stmt);
        }
    }

    public void deletePost(int postId) throws Exception{
        Statement stmt = getStatement();

        try {

            String sql = String.format("DELETE FROM post WHERE id = '%s'", postId);

            stmt.executeUpdate(sql);

        } catch(Exception e){
            e.printStackTrace();
        }
        finally {
            // TODO: handle finally clause
            close(stmt);
        }

    }

    public void likePost(int postId) throws Exception{

        Statement stmt = getStatement();

        try {

            int likes = getLikes(postId);

            String sql = String.format("UPDATE post SET likes = '%s' WHERE id = '%s'", ++likes);

            stmt.executeUpdate(sql);

        } catch(Exception e){
            e.printStackTrace();
        }
        finally {
            // TODO: handle finally clause
            close(stmt);
        }
    }

    public void unLikePost(int postId) throws Exception{

        Statement stmt = getStatement();

        try {

            int likes = getLikes(postId);

            String sql = String.format("UPDATE post SET likes = '%s' WHERE id = '%s'", --likes);

            stmt.executeUpdate(sql);

        } catch(Exception e){
            e.printStackTrace();
        }
        finally {
            // TODO: handle finally clause
            close(stmt);
        }
    }

    public int getLikes(int postId){

        Statement stmt = getStatement();

        String sql = "";
        int likes = 0;
        try {

            sql = String.format("SELECT likes FROM post WHERE id = '%s'", postId);
            likes = stmt.executeUpdate(sql);

        } catch(Exception e){
            e.printStackTrace();
        }
        finally {
            // TODO: handle finally clause
            close(stmt);
        }

        return likes;

    }


    private void close(Statement stmt) {

        try {

            if(conn != null){
                conn.close();
            }

            if(stmt != null) {
                stmt.close();
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
