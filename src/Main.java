import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        final int size = 10000000;
        final int h = size / 2;
        float[] arr = new float[size];
        float[] arr2 = new float[size];

        Arrays.fill(arr, 1);

        long a = System.currentTimeMillis();
        //------------------------первый метод-----------------------

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + (i+1) / 5) * Math.cos(0.2f + (i+1) / 5) * Math.cos(0.4f + (i+1) / 2));
        }
//        System.out.println(Arrays.toString(arr));
//        firstMethod(arr);


        String first = "Первый метод выполнился за " + (double) ((System.currentTimeMillis() - a));
        System.out.println(first);

        //------------------------первый метод-----------------------

        //------------------------второй метод-----------------------

        long b = System.currentTimeMillis();

        float[] a1 = new float[h];
        float[] a2 = new float[h];

        Thread thread1 = new Thread(() -> {
            System.arraycopy(arr, 0, a1, 0, h);
            for (int i = 0; i < a1.length; i++) {
                a1[i] = (float)(a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i+1 / 5) * Math.cos(0.4f + i+1 / 2));
            }
        });


        Thread thread2 = new Thread(() -> {
            System.arraycopy(arr, h, a2, 0, h);
            for (int i = 0; i < a1.length; i++) {
                a1[i] = (float)(a1[i] * Math.sin(0.2f + (i+5000) / 5) * Math.cos(0.2f + (i+5000) / 5) * Math.cos(0.4f + (i+5001) / 2));
            }
        });
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(a2, 0, arr2, 0, h);
        System.arraycopy(a2, 0, arr2, h, h);
        System.out.println("Второй метод выполнился за " + (double) ((System.currentTimeMillis() - b)));
        //------------------------второй метод-----------------------
    }
}


