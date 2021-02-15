package sample.entity;

public class Weigher {

    private int[] signList;

    public Weigher() {
    }

    public Weigher(int[] signList) {
        this.signList = signList;
    }

    public void addDefSign(int size ){
        signList = new int[size];
        for (int i = 0 ; i < size; i++)
            signList[i] = 0; //TODO: добавить константу
    }

    public int[] getSignList() {
        return signList;
    }

    public void setSignList(int[] signList) {
        this.signList = signList;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Weigher : {");
        for (Integer sign : signList) {
            builder.append(sign).append(", ");
        }
        builder.replace(builder.length()-2,builder.length()-1,"}");
        return builder.toString();
    }
}
