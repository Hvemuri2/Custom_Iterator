import java.util.Iterator;

public class ReviewIterator implements Iterator<String> {

    private String comment;
    private int index = 0;
    public ReviewIterator(String comment){
        this.comment = comment;
    }

    @Override
    public boolean hasNext() {
        return index<comment.split(" ").length;
    }

    @Override
    public String next() {
        String[] comm_arr = comment.split(" ");
        return comm_arr[index++];
    }

    public void remove(){
        throw new UnsupportedOperationException();
    }
}
