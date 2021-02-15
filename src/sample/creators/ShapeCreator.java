package sample.creators;

import sample.entity.Shape;
import sample.random.Randomize;

import java.util.ArrayList;
import java.util.List;

public class ShapeCreator {

    // наблюдать на изменения размеров графика

    private final int min;
    private final int max;
    private final int defShapeAmount;
    private final int defSignAmount;

    private final Randomize randomize;

    public ShapeCreator(int min, int max, int defShapeAmount, int defSignAmount, Randomize randomize) {
        this.min = min;
        this.max = max;
        this.defShapeAmount = defShapeAmount;
        this.defSignAmount = defSignAmount;
        this.randomize = randomize;
    }

    public List<Shape> createShape() {
        return createShape(defShapeAmount,defSignAmount);
    }


   public List<Shape> createShape(int shapeAmount, int signAmount) {

        List<Shape> shapeList = new ArrayList<>();

        for (int i = 0; i < shapeAmount; i++) {
            shapeList.add(createShape(signAmount));
        }

       return shapeList;
    }

    public Shape createShape(int signAmount) {

        int[] signArr = new int[signAmount];

        for (int i=0; i < signAmount; i++){
            signArr[i] = randomize.random(min, max);
        }
        return new Shape(signArr);
    }

    public Shape createConcreteShape(int signAmount, int value) {

        int[] signArr = new int[signAmount];

        for (int i=0; i < signAmount; i++){
            signArr[i] = value;
        }
        return new Shape(signArr);
    }

}
