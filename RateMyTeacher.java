import java.io.File;

public class RateMyTeacher {
    public static void main(String[] args) {
        File f = new File(args[0]);
        ReviewList list = new ReviewList(f);
        for (Review r : list)
            for (String s : r)
                System.out.println(s);

    }
}



