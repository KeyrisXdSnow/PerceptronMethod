package sample.entity;

import java.util.List;

public class SClass {

    private final List<Shape> shapeList;

    public SClass(List<Shape> shapeList) {
        this.shapeList = shapeList;
    }

    public List<Shape> getShapeList() {
        return shapeList;
    }
}
