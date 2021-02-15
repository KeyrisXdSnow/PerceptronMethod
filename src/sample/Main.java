package sample;

import sample.entity.SClass;
import sample.entity.Shape;
import sample.entity.Weigher;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static  int classAmount = 0;
    private static int shapeAmount;
    private static int signAmount;


    public static void main(String[] args) {
        classAmount = 3;
        shapeAmount = 3;
        signAmount = 6;

        PerceptronAlgorithm perceptronAlgorithm = new PerceptronAlgorithm(shapeAmount, signAmount);

        System.out.println("Обьекты: ");
        showClasses(perceptronAlgorithm.getClassList());


        System.out.println("Начальные весы: ");
        showWeigher(Arrays.asList(perceptronAlgorithm.getWeigherList().clone()));


        perceptronAlgorithm.start();

        System.out.println("обучение окончено");
        System.out.println();

        System.out.println("Конечные весы: ");
        showWeigher(Arrays.asList(perceptronAlgorithm.getWeigherList()));

        System.out.println("Полученные решающие функции: ");
        showFunctions(perceptronAlgorithm.getFunctions());

        int code = 1;
        Scanner in = new Scanner(System.in);

        System.out.println();
        System.out.println("---------------------");
        System.out.println("Классификация точки");
        System.out.println("Классификация");
        System.out.println("-1 выход из программы");
        System.out.println("---------------------");
        System.out.println();

        while (code != -1) {
            System.out.print("Введите команду: ");
            code = in.nextInt();

            if (code == 1) {
                try {
                    System.out.print("Введите класс: ");
                    int classCode = in.nextInt();
                    System.out.print("Введите образ: ");
                    int shapeCode = in.nextInt();

                    int index = perceptronAlgorithm.getClassification(classCode-1, shapeCode-1);
                    System.out.println("Обьект №" + classCode);
                    System.out.println(perceptronAlgorithm.getClassList().get(classCode-1).getShapeList().get(shapeCode-1));
                    System.out.println("Принадлежит " + index + " классу");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Таких данных нет =)");
                }

            }
        }



        in.close();



    }

    private static void showClasses(List<SClass> classList) {

        int i=0;
        for (SClass sClass : classList) {
            System.out.println("Обьект №"+ ++i);
            for (Shape shape : sClass.getShapeList()){
                System.out.println(shape);
            }
        }
    }

    private static void  showWeigher(List<Weigher> weighersList) {
        weighersList.forEach( weigher -> {
            System.out.println(weigher);
        });
    }

    private static void showFunctions (List<String> funcList) {
        funcList.forEach( action -> System.out.println(action));
    }


}
