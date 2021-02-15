package sample.creators;

import sample.entity.SClass;
import sample.entity.Shape;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClassCreator {

    private final ShapeCreator shapeCreator;

    public ClassCreator(ShapeCreator shapeCreator) {
        this.shapeCreator = shapeCreator;
    }

    public SClass createClass() {

        return new SClass(shapeCreator.createShape());
    }

    public List<SClass> createClass(int size) {
        List<SClass> sClassList = new ArrayList<>();

        for (int i=0; i<size; i++) {
            sClassList.add(createClass());
        }
        return sClassList;
    }

    public List<SClass> createTestClass() {
        List<SClass> sClassList = new ArrayList<>();

        for (int i=0; i< 3; i++) {
            int[] shapes = new int[0];

            switch (i) {
                case 0: {
                    shapes = new int[] {0,0,1};
                   break;
                }
                case 1: {
                    shapes = new int[] {1,1,1};
                    break;
                }
                case 2: {
                    shapes = new int[] {-1,1,1};
                    break;
                }
            }
            Shape shape = new Shape(shapes);
            SClass sClass = new SClass(Collections.singletonList(shape));
            sClassList.add(sClass);
        }
        return sClassList;
    }



}
