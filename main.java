package com.company;

public class Main {


        static final int size = 10000000;
        static final int h = size / 2;

        public static void main(String[] args) throws InterruptedException {
            float[] array1 = new float[size];
            float[] array2 = new float[size];

            firstArray(array1);
            secondArray(array2);
        }

        private static void firstArray(float[] array) {
            for (int i = 0; i < size; i++) {
                array[i] = 1;
            }
            long a = System.currentTimeMillis();

            for (int i = 0; i < size; i++) {
                array[i] = formula(array[i], i);
            }

            System.out.println(System.currentTimeMillis() - a);
        }

    private static void secondArray(float[] array) throws InterruptedException {
        for (int i = 0; i < size; i++) {
            array[i] = 1;
        }

        long a = System.currentTimeMillis();

        float[] a1 = new float[h];
        float[] a2 = new float[h];

        System.arraycopy(array, 0, a1, 0, h);
        System.arraycopy(array, h, a2, 0, h);

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < a1.length; i++) {
                a1[i] = formula(a1[i], i);
            }
        });
            thread1.start();


        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < a2.length; i++) {
                a2[i] = formula(a2[i], i);
            }
        });
        thread2.start();

        thread1.join();
        thread2.join();

        System.arraycopy(a1, 0, array, 0, h);
        System.arraycopy(a2, 0, array, h, h);

        System.out.println(System.currentTimeMillis() - a);

    }




    private static float formula(float array, int i) {
        return (float) (array * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
    }
}
