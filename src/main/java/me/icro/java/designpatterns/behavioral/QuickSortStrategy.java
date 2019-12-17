package me.icro.java.designpatterns.behavioral;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述: 快排策略实现
 *
 * @author Lin
 * @since 2019-12-17 9:59 PM
 */
public class QuickSortStrategy implements SortStrategy {
    @Override
    public List<Integer> sort(List<Integer> list) {
        // Do sorting
        return new ArrayList<>();
    }
}
