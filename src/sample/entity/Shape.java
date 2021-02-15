package sample.entity;

public class Shape {

    int[] sighList;

    public Shape() {
    }

    public Shape(int[] sighList) {

        this.sighList = sighList;
    }

    public int[] getSighArray() {
        return sighList;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Shape : {");
        for (Integer sign : sighList) {
            builder.append(sign).append(", ");
        }
        builder.replace(builder.length()-2,builder.length()-1,"}");
        return builder.toString();
    }
}
