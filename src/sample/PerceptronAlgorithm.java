package sample;

import sample.creators.ClassCreator;
import sample.creators.ShapeCreator;
import sample.entity.SClass;
import sample.entity.Weigher;
import sample.random.RandomizeWithBound;

import java.util.ArrayList;
import java.util.List;

import static sample.Constants.*;

public class PerceptronAlgorithm {

    private Weigher[] weigherList;
    private List<SClass> classList;
    private final ClassCreator classCreator;

    public PerceptronAlgorithm(int shapeAmount, int signAmount) {
        classCreator = new ClassCreator(new ShapeCreator(min, max, shapeAmount, signAmount, new RandomizeWithBound()));
        classList = classCreator.createClass(shapeAmount);

        initWeigherList(shapeAmount, signAmount);
    }

    private void initWeigherList(int size, int signAmount) {
        weigherList = new Weigher[size];

        for (int i = 0; i < size; i++) {
            Weigher weigher = new Weigher();
            weigher.addDefSign(signAmount);
            weigherList[i] = weigher;
        }
    }

    public void start() {

        boolean isCorrect = false;

        for (int i = 0; !isCorrect; i = Math.floorMod(++i, classList.size())) {

            for (int q = 0; q < classList.get(i).getShapeList().size() && !isCorrect ; q++ ) {

                int[] distArray = new int[weigherList.length];

                for (int j = 0; j < weigherList.length; j++) {

                    int[] ar1 = weigherList[j].getSignList();
                    int[] ar2 = classList.get(i).getShapeList().get(q).getSighArray();

                    distArray[j] = multiVector(ar1, ar2);
                }

                for (int j = 0; j < distArray.length; j++) {

                    if (i == j) continue;

                    if (distArray[j] >= distArray[i]) {

                        int[] x = classList.get(i).getShapeList().get(q).getSighArray();

                        for (int k = 0; k < weigherList.length; k++) {

                            int[] signArray = weigherList[k].getSignList();

                            int c = 1;
                            if (k == i) {
                                for (int t = 0; t < signArray.length; t++) {
                                    signArray[t] += c * x[t];
                                }
                            } else {
                                for (int t = 0; t < signArray.length; t++) {
                                    signArray[t] -= c * x[t];
                                }
                            }
                            weigherList[k].setSignList(signArray);
                        }
                        isCorrect = false;
                        break;
                    }
                    isCorrect = true;
                }
            }
        }
    }

    public List<String> getFunctions() {

        List<String> result = new ArrayList<>();

        for (int i = 0; i < classList.size(); i++) {

            StringBuilder builder = new StringBuilder("d")
                    .append(i + 1)
                    .append("(x) = ");
            int[] weigherArray = weigherList[i].getSignList();

            for (int j = 0; j < weigherArray.length; j++) {

                if (j == 0)
                    builder.append(weigherArray[j]);
                else
                    if (weigherArray[j] < 0)
                        builder.append(" - ").append(Math.abs(weigherArray[j]));
                    else builder.append(" + ").append(Math.abs(weigherArray[j]));

                if (j != weigherArray.length - 1) {
                    builder
                            .append("*")
                            .append("x")
                            .append(j + 1);
                }
            }
            result.add(builder.toString());
        }
        return result;
    }

    private int multiVector(int[] ar1, int[] ar2) {
        int result = 0;

        for (int i = 0; i < ar1.length; i++) {
            result += ar1[i] * ar2[i];
        }
        return result;
    }

    public List<SClass> getClassList() {
        return classList;
    }

    public Weigher[] getWeigherList() {
        return weigherList;
    }

    public int getClassification (int classIndex, int shapeIndex) {

        int[] singClassArray = classList.get(classIndex).getShapeList().get(shapeIndex).getSighArray();

        int maxValue = -1;
        int includeClassIndex = 0-1;



        for (int i = 0; i < weigherList.length; i++) {

            int[] singList = weigherList[i].getSignList();
            int value = 0;

            for (int j = 0 ; j < singList.length; j++)
                value += singList[j] * singClassArray[j];

            if (value > maxValue) {
                maxValue = value;
                includeClassIndex = i;
            }

        }
        return includeClassIndex;
    }

}
