package com.project.demo;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class ArithmeticTest {
    /**
     * 选择排序
     * 9 5 8 3 7
     * 5 9 8 3 7
     * 5 8 9 3 7
     * 5 8 3 9 7
     * 5 8 3 7 9
     * ...
     */
    @Test
    public void demo1() {
        int[] array = {9, 5, 8, 3, 7,2};
        int temp = 0;
        for (int i = 0; i < array.length; i++) {
            for (int i1 = 0; i1 < array.length - 1; i1++) {
                if (array[i1] > array[i1 + 1]) {
                    temp = array[i1];
                    array[i1] = array[i1 + 1];
                    array[i1 + 1] = temp;
                }
            }
        }
        for (int i : array) {
            System.out.println(i);
        }
    }

    /**
     * 冒泡排序
     * 6 4 5 2 1
     * 4 6 5 2 1
     * 4 5 6 2 1
     * 4 5 2 6 1
     * 4 5 2 1 6
     * 4 2 5 1
     * 4 2 1 5
     * ...
     */
    @Test
    public void demo2() {
        int[] array = {6, 4, 5, 2, 1,3};
        int temp = 0;
        for (int i = 0; i < array.length; i++) {
            for (int i1 = 0; i1 < array.length - i - 1; i1++) {
                if (array[i1] > array[i1 + 1]) {
                    temp = array[i1];
                    array[i1] = array[i1 + 1];
                    array[i1 + 1] = temp;
                }
            }
        }
        for (int i : array) {
            System.out.println(i);
        }
    }

    /**
     * 二分查找法
     */
    @Test
    public void demo3() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arrayList.add(i);
        }
        int targetNum = 4;
        int start = 0;
        int end = arrayList.size() - 1;
        int middle = (start + end) / 2;
        int count = 0;

        while (end >= start) {
            count++;
            if (arrayList.get(middle) > targetNum) {
                end = middle - 1;
                middle = (start + end) / 2;
            } else if (arrayList.get(middle) < targetNum) {
                start = middle + 1;
                middle = (start + end) / 2;
            } else {
                System.out.println(count + "次循环获得结果" + arrayList.get(middle));
                return;
            }
        }
    }
}
