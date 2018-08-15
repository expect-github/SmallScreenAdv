package com.hyt.advsmallscreen.utils;


import com.hyt.advsmallscreen.domain.data.advData.AdvBaseData;

import java.util.ArrayList;

/**
 * Created by ${Tao} on 2017-11-1709.
 */

public class OrderUtil {


    public static ArrayList<AdvBaseData> OrderList(ArrayList<AdvBaseData> builders) {

        if (builders != null && builders.size() > 0) {
            int size1 = builders.size();
            for (int i = 0; i < size1 - 1; i++) {
                for (int j = 0; j < size1 - 1 - i; j++) {
                    AdvBaseData builder1 = builders.get(j);
                    AdvBaseData builder2 = builders.get(j + 1);

                    if ((builder1).getOrderNumber() > (builder2).getOrderNumber()) {
                        builders.set(j, builder2);
                        builders.set(j+1, builder1);

                    }
                }
            }
        }
        return builders;
    }

    public void bubbleSort(int array[]) {
        int t = 0;
        for (int i = 0; i < array.length - 1; i++)
            for (int j = 0; j < array.length - 1 - i; j++)
                if (array[j] > array[j + 1]) {
                    t = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = t;
                }
    }

}
