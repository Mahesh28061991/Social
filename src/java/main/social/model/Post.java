package social.model;

import social.db.PostDBUtil;

import java.sql.Timestamp;
import java.util.Date;

public class Post {
    private int id;
    private String data;
    private int likes;
    private int userId;
    private Timestamp createdTimestamp;

    private PostDBUtil postDBUtil;

    public Post(String data, int userId) {
        this.userId = userId;
        this.data = data;
        this.likes = 0;

        Date date = new Date();
        createdTimestamp = new Timestamp(date.getTime());
    }

    //CreatePost
    public boolean createPost(int userId, String data){
    Post post = new Post(data, userId);
    PostDBUtil postDBUtil = getPostDBUtil();

    //save the post to db
        try{
            postDBUtil.createPost(post);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    return true;
}

    //DeletePost
    public boolean deletePost(int postId){

        PostDBUtil postDBUtil = getPostDBUtil();

        //delete the post from db where post.id = postId
        try{
            postDBUtil.deletePost(postId);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    //EditPost
//    public boolean editPost(int postId, String data){
//
//        //update the post.data where post.id = postId
//
//        return true;
//    }

    //SavePost
    public boolean savePost(int postId, String data){

        PostDBUtil postDBUtil = getPostDBUtil();

        //update the post.data where post.id = postId
        try{
            postDBUtil.updatePost(postId, data);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    //LikePost
    public boolean likePost(int postId){

        PostDBUtil postDBUtil = getPostDBUtil();

        //update post.likes = post.likes++ where post.id = postId
        try{
            postDBUtil.likePost(postId);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    //UnlikePost
    public boolean unlikePost(int postId){

        PostDBUtil postDBUtil = getPostDBUtil();

        //update post.likes = post.likes-- where post.id = postId
        try{
            postDBUtil.unLikePost(postId);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private PostDBUtil getPostDBUtil(){
        if(postDBUtil != null){
            return postDBUtil;
        }
        return new PostDBUtil();
    }
    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public int getLikes() {
        return likes;
    }

    public int getUserId() {
        return userId;
    }

    public Timestamp getCreatedTimestamp() {
        return createdTimestamp;
    }

}
