package me.icro.java.springboot.scaffold.utils.impl;

import me.icro.java.springboot.scaffold.utils.RedisUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 描述:
 *
 * @author Lin
 * @since 2020-01-09 3:39 PM
 */
@Component
public class RedisUtilsImpl implements RedisUtils {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 指定缓存时间
     *
     * @param key  键
     * @param time 时间（秒）
     * @return
     */
    @Override
    public boolean expire(String key, long time) {
        try {
            if (0 < time) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key 键
     * @return 时间（秒） 返回0代表永久有效
     */
    @Override
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true存在 false不存在
     */
    @Override
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     *
     * @param key 键
     */
    @Override
    public void del(String... key) {
        if (null == key
                || 0 == key.length) {
            return;
        }

        if (1 == key.length) {
            redisTemplate.delete(key[0]);
        } else {
            redisTemplate.delete(Arrays.asList(key));
        }
    }

    // ==============String====================

    /**
     * String缓存获取
     *
     * @param key 键
     * @return 值
     */
    @Override
    public Object get(String key) {
        if (null == key) {
            return null;
        }

        return redisTemplate.opsForValue().get(key);
    }

    /**
     * String缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    @Override
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * String缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间（秒） time要大于0 如果time小于等于0 将设置无限期
     * @return true 成功 false失败
     */
    @Override
    public boolean set(String key, Object value, long time) {
        try {
            if (0 < time) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    /**
     * 递增
     *
     * @param key   键
     * @param delta 要增加几（小于0）
     * @return
     */
    @Override
    public long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     *
     * @param key   键
     * @param delta 要减少几（小于0）
     * @return
     */
    @Override
    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().decrement(key, delta);
    }

    // ==============Map====================

    /**
     * HashGet
     * @param key  键
     * @param item
     * @return 对应的多个值
     */
    @Override
    public Object hget(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hashkey对应的所有键值
     * @param key 键
     * @return 对应的多个值
     */
    @Override
    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     *
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false失败
     */
    @Override
    public boolean hmset(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * HashSet 并设置时间
     * @param key 键
     * @param map
     * @param time
     * @return true 成功 false 失败
     */
    @Override
    public boolean hmset(String key, Map<String, Object> map, long time) {
        try {
            hmset(key, map);
            if (0 < time) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表放入数据，如果不存在将创建
     * @param key 键
     * @param item
     * @param value
     * @return
     */
    @Override
    public boolean hset(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表放入数据，如果不存在将创建
     * @param key 键
     * @param item
     * @param value
     * @param time 时间（秒） 注意：如果已存在的hash表有时间，这里将会替换原有的时间
     * @return
     */
    @Override
    public boolean hset(String key, String item, Object value, long time) {
        try {
            hset(key, item, value);
            if (0 < time) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除hash表中的值
     * @param key 键
     * @param item
     */
    @Override
    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断hash表中是否有该项的值
     * @param key 键
     * @param item
     * @return
     */
    @Override
    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * hash递增，如果不存在，就会创建一个 并把新增的值返回
     * @param key 键
     * @param item
     * @param by 要增加几（大于0）
     * @return
     */
    @Override
    public double hincr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * hash递减
     * @param key 键
     * @param item
     * @param by
     * @return
     */
    @Override
    public double hdecr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    // ==============Set====================

    /**
     * key获取set中的所有值
     * @param key 键
     * @return
     */
    @Override
    public Set<Object> sGet(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 根据value从一个set中查询，是否存在
     * @param key 键
     * @param value
     * @return
     */
    @Override
    public boolean sHasKey(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    /**
     * 将数据放入set缓存
     * @param key 键
     * @param values
     * @return 成功个数
     */
    @Override
    public long sSet(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 将set数据放入缓存 并且设置时间
     * @param key 键
     * @param time
     * @param values
     * @return 成功个数
     */
    @Override
    public long sSetAndTime(String key, long time, Object... values) {
        try {
            long count = redisTemplate.opsForSet().add(key, values);
            expire(key, time);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取set缓存的长度
     * @param key 键
     * @return
     */
    @Override
    public long sGetSetSize(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * 移除set缓存中的key与values
     * @param key 键
     * @param values 可以多个
     * @return 移除个数
     */
    @Override
    public long setRemove(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().remove(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

// ==============List====================

    /**
     * 获取list的缓存的内容
     * @param key
     * @param start 开始
     * @param end 结束 0 到 -1 代表所有值
     * @return
     */
    @Override
    public List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取list缓存的长度
     * @param key
     * @return
     */
    @Override
    public long lGetListSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 通过索引 获取list中的值
     * @param key
     * @param index 索引 index >= 0 时， 0代表，1第二元素，依此类推；index<0时， -1 表尾 -2 代表倒数第二
     * @return
     */
    @Override
    public Object lGetIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将list放入缓存
     * @param key
     * @param value
     * @return
     */
    @Override
    public boolean lSet(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存，并且设置时间
     * @param key
     * @param value
     * @param time
     * @return
     */
    @Override
    public boolean lSet(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据索引修改list中的某条数据
     * @param key
     * @param index
     * @param value
     * @return
     */
    @Override
    public boolean lUpdateIndex(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 移除N个值为value
     * @param key
     * @param count 移除数量
     * @param value
     * @return 移除数量
     */
    @Override
    public long lReomve(String key, long count, Object value) {
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
