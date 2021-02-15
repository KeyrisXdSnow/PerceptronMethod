package sample.random;

public class RandomizeWithBound implements Randomize {

    public RandomizeWithBound() {
    }

    @Override
    public int random(int min, int max) {
        return min + (int) (Math.random() * (max - min + 1));
    }
}
