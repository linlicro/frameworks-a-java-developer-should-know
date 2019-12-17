package me.icro.java.designpatterns.behavioral;

import java.util.List;

/**
 * 策略interface
 *
 * Created by Lin on 2019/12/17.
 */
public interface SortStrategy {
    List<Integer> sort(List<Integer> list);
}
