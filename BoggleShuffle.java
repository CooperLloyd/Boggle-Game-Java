import java.sql.Array;
import java.util.ArrayList;

public class BoggleShuffle<T> implements Shuffler<T> {
    public static final int BOARD_SIZE = 16;

    public BoggleShuffle(){ }
    /**
     * Starts at the beginning of the list and sets a random element
     * in that list to the given position, if element has already been used
     * another element will be chosen.
     * @param t the list of ordered dice to be randomized
     */
    @Override
    public void shuffle(ArrayList<T> t) {
        for (int i = 0; i < t.size(); i++){
            t.add(t.remove((int) (Math.random() * BOARD_SIZE)));
        }
    }
}