package show;

public class bdfiAlg {

    //requires stars, count, currentReview >= 0
    public static int updateReview(int stars, int count, int currentReview) {
        return Math.round((float)((stars+count*currentReview)/((float)(count+1))));
    }

}