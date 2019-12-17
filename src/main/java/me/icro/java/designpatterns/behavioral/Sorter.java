package me.icro.java.designpatterns.behavioral;

import java.util.List;

/**
 * 描述:
 *
 * @author Lin
 * @since 2019-12-17 10:00 PM
 */
public class Sorter {
    private SortStrategy sortStrategy;

    public Sorter(SortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    public List<Integer> sort(List<Integer> list) {
        return this.sortStrategy.sort(list);
    }
}
