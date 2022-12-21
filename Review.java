import java.util.ArrayList;
import java.util.Iterator;

public class Review implements Iterable<String> {

    private String username;
    private int rating;
    private String comment;

    public Review(String username, int rating, String comment){
        this.comment = comment;
        this.rating = rating;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public int getRating() {
        return rating;
    }

    public int getCommentSize(){
        return comment.split(" ").length;
    }

    public String getCommentWord(int N){
        String[] str_arr = comment.split(" ");
        return str_arr[N];
    }


    @Override
    public Iterator<String> iterator() {
        return new ReviewIterator(comment);
    }

    public Iterator<String> iterator(boolean skipSuspiciousWords) {
        ArrayList<String> sus_words = new ArrayList<>();
        sus_words.add("veritas");
        sus_words.add("moribus");
        sus_words.add("inmaturitas");
        sus_words.add("malignus");


        if(!skipSuspiciousWords){
            return new ReviewIterator(comment);
        }
        else{
            StringBuilder edited_comm = new StringBuilder();
            String[] comment_arr = comment.split(" ");
            for(int i=0;i< comment_arr.length;i++){
                if(edited_comm.toString().isEmpty()){
                    if(!sus_words.contains(comment_arr[i])){
                        edited_comm.append(comment_arr[i]);
                    }
                }
                else{
                    if(!sus_words.contains(comment_arr[i])){
                        edited_comm.append(" ");
                        edited_comm.append(comment_arr[i]);
                    }
                }
            }
            return new ReviewIterator(edited_comm.toString());
        }
    }

}
