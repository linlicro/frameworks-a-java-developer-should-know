package me.icro.java.redis;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * 描述: 利用Google开源的 Guava中自带的布隆过滤器
 *
 * @author Lin
 * @since 2019-12-24 10:58 AM
 */
public class GuavaBloomFilter {

    public static void main(String[] args) {
        // 最多存放 1500个整数的布隆过滤器，容忍的 误判的概率是万分之1
        BloomFilter<Integer> filter = BloomFilter.create(
                Funnels.integerFunnel(),
                1500,
                0.01);
        // 判断是否存在
        System.out.println(filter.mightContain(1));
        System.out.println(filter.mightContain(2));
        // 添加
        filter.put(1);
        filter.put(2);
        System.out.println(filter.mightContain(1));
        System.out.println(filter.mightContain(2));
    }
}
