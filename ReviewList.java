import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class ReviewList implements Iterable<Review>{

    private ArrayList<Review> reviews = new ArrayList<>();



    ReviewList(File f){
        Scanner sc;
        try {
            sc = new Scanner(f);
        }catch (Exception e){
            throw new RuntimeException();
        }

        String[] param = new String[3];
        int count = 0;

        while(sc.hasNext()){
            String s = sc.nextLine();
            param[count%3] = s;
            count++;
            if(count%3==0){
                String username = param[0];
                int rating = Integer.parseInt(param[1]);
                String comment = param[2];
                Review r = new Review(username,rating,comment);
                reviews.add(r);
            }
        }
    }


    @Override
    public Iterator<Review> iterator() {
        return new Iterator<Review>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < reviews.size();
            }

            @Override
            public Review next() {
                Review r = reviews.get(index);
                index++;
                return r;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private String comment_editor(Review r){
        StringBuilder comment = new StringBuilder();
        Iterator<String> i = r.iterator(true);
        while(i.hasNext()){
            if(comment.length()==0){
                comment.append(i.next());
            }
            else{
                comment.append(" ");
                comment.append(i.next());
            }
        }
        return comment.toString();
    }

    public Iterator<Review> iterator(boolean skipSuspiciousWords){
        ArrayList<Review> reviews_excludeSusRews = new ArrayList<>();

        if(skipSuspiciousWords){
            for(Review r:reviews){
                int rating = r.getRating();
                String edit_comm = comment_editor(r);
                boolean skip = rating==5&&edit_comm.split(" ").length<=10;
                if(!skip){
                    reviews_excludeSusRews.add(r);
                }
            }
            return new Iterator<Review>() {
                private int index = 0;

                @Override
                public boolean hasNext() {
                    return index < reviews_excludeSusRews.size();
                }

                @Override
                public Review next() {
                    return reviews_excludeSusRews.get(index++);
                }

                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }

        return this.iterator();
    }

}
