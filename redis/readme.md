# Redis

Redis可以看成就是一个数据库，与MySQL之类数据库不同的是 数据放在内存中，so 读写很快。

广泛用于缓存，也经常用来做分布式锁，还支持事务 、持久化、LUA脚本、LRU驱动事件、多种集群方案。

## 为什么用Redis

* 高性能
* 高并发

相比从数据库读取，需从硬盘上读，redis的操作都是在内存上，速度相当快。 -- 高性能

直接操作缓存的并发量远大于直接访问数据库的 -- 高并发

### 为什么不用map/guava做缓存，非用Redis

map/guava可作为本地缓存，特点是轻量、快速，当在多实例的情况下，每个实例都会保存一份缓存，这样会出现缓存不一致问题。

Redis作为分布式缓存，在多实例时，各实例共用一份缓存，保证一致性，缺点是程序/架构较为复杂。

## Redis的线程模式

单线程模式: Redis使用文件事件处理器 `file event handler`，这个文件事件处理器是单线程。文件事件处理器采用 IO 多路复用机制同时监听多个 socket，根据 socket 上的事件来选择对应的事件处理器进行处理。

文件事件处理器 包含四部分:

* 多个 socket
* IO 多路复用程序
* 文件事件分派器
* 事件处理器（连接应答处理器、命令请求处理器、命令回复处理器）

多个 socket 可能会并发产生不同的操作，每个操作对应不同的文件事件，但是 IO 多路复用程序会监听多个 socket，会将 socket 产生的事件放入队列中排队，事件分派器每次从队列中取出一个事件，把该事件交给对应的事件处理器进行处理。

来看客户端与 redis 的一次通信过程：

![redis-single-thread-model](img/redis-single-thread-model.png)

* 客户端 `socket01` 向 `redis` 的 `server socket` 请求建立连接
  * 此时 `server socket` 会产生一个 `AE_READABLE` 事件
  * IO 多路复用程序监听到 `server socket` 产生的事件后，将该事件压入队列中。
  * 文件事件分派器从队列中获取该事件，交给连接应答处理器。
  * 连接应答处理器会创建一个能与客户端通信的 `socket01`，并将该 `socket01` 的 `AE_READABLE` 事件与命令请求处理器关联。
* 客户端发送了一个 `set key value` 请求
  * 此时 `redis` 中的 `socket01` 会产生 `AE_READABLE` 事件
  * IO 多路复用程序将事件压入队列，
  * 事件分派器从队列中获取到该事件，由于前面 `socket01` 的 `AE_READABLE` 事件已经与命令请求处理器关联，因此事件分派器将事件交给命令请求处理器来处理。
  * 命令请求处理器读取 `socket01` 的 `key value` 并在自己内存中完成 `key value` 的设置。
  * 操作完成后，它会将 `socket01` 的 `AE_WRITABLE` 事件与命令回复处理器关联。
* 当客户端准备好接收返回结果了，
  * `redis` 中的 `socket01` 会产生一个 `AE_WRITABLE` 事件，同样压入队列中，
  * 事件分派器找到相关联的命令回复处理器，由命令回复处理器对 `socket01` 输入本次操作的一个结果，比如 ok，
  * 之后解除 `socket01` 的 `AE_WRITABLE` 事件与命令回复处理器的关联。

以上，完成了一次通信。

## redis 和 memcached 的区别

* redis支持更丰富的数据类型（支持更复杂的应用场景）：Redis不仅仅支持简单的k/v类型的数据，同时还提供list，set，zset，hash等数据结构的存储。memcache支持简单的数据类型，String。
* Redis支持数据的持久化，可以将内存中的数据保持在磁盘中，重启的时候可以再次加载进行使用,而Memecache把数据全部存在内存之中。
* 集群模式：memcached没有原生的集群模式，需要依靠客户端来实现往集群中分片写入数据；但是 redis 目前是原生支持 cluster 模式的.
* Memcached是多线程，非阻塞IO复用的网络模型；Redis使用单线程的多路 IO 复用模型。

## redis 常见数据结构

### 1. String

> 常用命令: set,get,decr,incr,mget 等。

String数据结构是简单的key-value类型，value其实不仅可以是String，也可以是数字。 常规key-value缓存应用； 常规计数。

### 2. Hash

> 常用命令： hget,hset,hgetall 等。

hash 是一个 string 类型的 field 和 value 的映射表，hash 特别适合用于存储对象，后续操作的时候，你可以直接仅仅修改这个对象中的某个字段的值。 比如我们可以 hash 数据结构来存储用户信息，商品信息等等。比如下面我就用 hash 类型存放了我本人的一些信息：

```text
key=JavaUser293847
value={
  “id”: 1,
  “name”: “SnailClimb”,
  “age”: 22,
  “location”: “Wuhan, Hubei”
}
```

### 3. List

> 常用命令: lpush,rpush,lpop,rpop,lrange等

list 就是链表，Redis list 的应用场景非常多，也是Redis最重要的数据结构之一，比如微博的关注列表，粉丝列表，消息列表等功能都可以用Redis的 list 结构来实现。

Redis list 的实现为一个双向链表，即可以支持反向查找和遍历，更方便操作，不过带来了部分额外的内存开销。

另外可以通过 lrange 命令，就是从某个元素开始读取多少个元素，可以基于 list 实现分页查询，这个很棒的一个功能，基于 redis 实现简单的高性能分页。

### 4.Set

> 常用命令： sadd,spop,smembers,sunion 等

set 对外提供的功能与list类似是一个列表的功能，特殊之处在于 set 是可以自动排重的。

当你需要存储一个列表数据，又不希望出现重复数据时，set是一个很好的选择，并且set提供了判断某个成员是否在一个set集合内的重要接口，这个也是list所不能提供的。可以基于 set 轻易实现交集、并集、差集的操作。

### 5. Sorted Set

> 常用命令： zadd,zrange,zrem,zcard等

和set相比，sorted set增加了一个权重参数score，使得集合中的元素能够按score进行有序排列。

## redis 设置过期时间

set key 的时候，都可以给一个 expire time，就是过期时间，通过过期时间我们可以指定这个 key 可以存活的时间。

redis是怎么对这批key进行删除的？

* 定期删除: redis默认是每隔 100ms 就随机抽取一些设置了过期时间的key，检查其是否过期，如果过期就删除。注意这里是随机抽取的。为什么要随机呢？你想一想假如 redis 存了几十万个 key ，每隔100ms就遍历所有的设置过期时间的 key 的话，就会给 CPU 带来很大的负载！
* 惰性删除: 定期删除可能会导致很多过期 key 到了时间并没有被删除掉。所以就有了惰性删除。假如你的过期 key，靠定期删除没有被删除掉，还停留在内存里，除非你的系统去查一下那个 key，才会被redis给删除掉。这就是所谓的惰性删除，也是够懒的哈！

## redis 内存淘汰机制

如果定期删除漏掉了很多过期 key，然后你也没及时去查，也就没走惰性删除，此时会怎么样？如果大量过期key堆积在内存里，导致redis内存块耗尽了。

Redis使用'内存淘汰机制'来保障。

redis 提供 6种数据淘汰策略:

* volatile-lru：从已设置过期时间的数据集（server.db[i].expires）中挑选最近最少使用的数据淘汰
* 从已设置过期时间的数据集（server.db[i].expires）中挑选将要过期的数据淘汰
* volatile-random：从已设置过期时间的数据集（server.db[i].expires）中任意选择数据淘汰
* allkeys-lru：当内存不足以容纳新写入数据时，在键空间中，移除最近最少使用的key（这个是最常用的）
* allkeys-random：从数据集（server.db[i].dict）中任意选择数据淘汰
* no-eviction：禁止驱逐数据，也就是说当内存不足以容纳新写入数据时，新写入操作会报错。
* volatile-lfu：从已设置过期时间的数据集(server.db[i].expires)中挑选最不经常使用的数据淘汰(4.0新增)
* allkeys-lfu：当内存不足以容纳新写入数据时，在键空间中，移除最不经常使用的key(4.0新增)

redis.conf 中有相关注释参考: <http://download.redis.io/redis-stable/redis.conf>

## redis 持久化机制

持久化数据也就是将内存中的数据写入到硬盘里面，为了之后重用数据（比如重启机器、机器故障之后恢复数据），或者是为了防止系统故障而将数据备份到一个远程位置。

Redis支持两种不同的持久化操作。一种持久化方式叫快照（snapshotting，RDB），另一种方式是只追加文件（append-only file,AOF）。

### 快照（snapshotting）持久化（RDB）

通过创建快照来获得存储在内存里面的数据在某个时间点上的副本。Redis创建快照之后，可以对快照进行备份，可以将快照复制到其他服务器从而创建具有相同数据的服务器副本（Redis主从结构，主要用来提高Redis性能），还可以将快照留在原地以便重启服务器的时候使用。

有此下配置:

* save 900 1           #在900秒(15分钟)之后，如果至少有1个key发生变化，Redis就会自动触发BGSAVE命令创建快照。
* save 300 10          #在300秒(5分钟)之后，如果至少有10个key发生变化，Redis就会自动触发BGSAVE命令创建快照。
* save 60 10000        #在60秒(1分钟)之后，如果至少有10000个key发生变化，Redis就会自动触发BGSAVE命令创建快照。

### AOF（append-only file）持久化

，AOF持久化 的实时性更好，因此已成为主流的持久化方案。默认情况下Redis没有开启AOF（append only file）方式的持久化，可以通过appendonly参数开启：

```xml
appendonly yes
```

开启AOF持久化后每执行一条会更改Redis中的数据的命令，Redis就会将该命令写入硬盘中的AOF文件。AOF文件的保存位置和RDB文件的位置相同，都是通过dir参数设置的，默认的文件名是appendonly.aof。

更多`持久化内容`参考: <https://www.cnblogs.com/kismetv/p/9137897.html>

## redis 事务

Redis 通过 MULTI、EXEC、WATCH 等命令来实现事务(transaction)功能。事务提供了一种将多个命令请求打包，然后一次性、按顺序地执行多个命令的机制，并且在事务执行期间，服务器不会中断事务而改去执行其他客户端的命令请求，它会将事务中的所有命令都执行完毕，然后才去处理其他客户端的命令请求。

## 问题1: 缓存雪崩

什么是缓存雪崩？A: 缓存同一时间大面积的失效，所以，后面的请求都会落到数据库上，造成数据库短时间内承受大量请求而崩掉。

解决方案:

* (事前)尽量保证整个 redis 集群的高可用性，发现机器宕机尽快补上。选择合适的内存淘汰策略。
* (事中)本地ehcache缓存/Map/Guava + hystrix限流&降级，避免MySQL崩掉
* (事后)利用 redis 持久化机制保存的数据尽快恢复缓存

![缓存雪崩解决方案](img/缓存雪崩解决方案.jpg)

## 问题2: 缓存穿透

缓存穿透说简单点就是大量请求的 key 根本不存在于缓存中，导致请求直接到了数据库上，根本没有经过缓存这一层。举个例子：某个黑客故意制造我们缓存中不存在的 key 发起大量请求，导致大量请求落到数据库。

### 布隆过滤器

布隆过滤器（Bloom Filter）是一个叫做 Bloom 的老哥于1970年提出的。我们可以把它看作由二进制向量（或者说位数组）和一系列随机映射函数（哈希函数）两部分组成的数据结构。相比于我们平时常用的的 List、Map 、Set 等数据结构，它占用空间更少并且效率更高，但是缺点是其返回的结果是概率性的，而不是非常准确的。理论情况下添加到集合中的元素越多，误报的可能性就越大。并且，存放在布隆过滤器的数据不容易删除。

位数组中的每个元素都只占用 1 bit ，并且每个元素只能是 0 或者 1。这样申请一个 100w 个元素的位数组只占用 1000000Bit / 8 = 125000 Byte = 125000/1024 kb ≈ 122kb 的空间。

总结：一个名叫 Bloom 的人提出了一种来检索元素是否在给定大集合中的数据结构，这种数据结构是高效且性能很好的，但缺点是具有一定的错误识别率和删除难度。并且，理论情况下，添加到集合中的元素越多，误报的可能性就越大。

### 原理

当一个元素加入布隆过滤器中的时候，会进行如下操作：

* 使用布隆过滤器中的哈希函数对元素值进行计算，得到哈希值（有几个哈希函数得到几个哈希值）。
* 根据得到的哈希值，在位数组中把对应下标的值置为 1。

当我们需要判断一个元素是否存在于布隆过滤器的时候，会进行如下操作：

* 对给定元素再次进行相同的哈希计算；
* 得到值之后判断位数组中的每个元素是否都为 1，如果值都为 1，那么说明这个值在布隆过滤器中，如果存在一个值不为 1，说明该元素不在布隆过滤器中。

布隆过滤器说某个元素存在，小概率会误判。布隆过滤器说某个元素不在，那么这个元素一定不在。

布隆过滤器使用场景:

* 判断给定数据是否存在：比如判断一个数字是否在于包含大量数字的数字集中（数字集很大，5亿以上！）、 防止缓存穿透（判断请求的数据是否有效避免直接绕过缓存请求数据库）等等、邮箱的垃圾邮件过滤、黑名单功能等等。
* 去重：比如爬给定网址的时候对已经爬取过的 URL 去重。

### Java 自定义布隆过滤器

需要：

* 一个合适大小的位数组保存数据
* 几个不同的哈希函数
* 添加元素到位数组（布隆过滤器）的方法实现
* 判断给定元素是否存在于位数组（布隆过滤器）的方法实现。

```java
/**
 * 描述: 自定义的布隆过滤器
 *
 * @author Lin
 * @since 2019-12-23 11:35 AM
 */
public class MyBloomFilter {
    /**
     * 位数组的大小
     */
    private static final int DEFAULT_SIZE = 2 << 24;
    /**
     * 通过这个数组可以创建 6 个不同的哈希函数
     */
    private static final int[] SEEDS = new int[]{3, 13, 46, 71, 91, 134};
    /**
     * 位数组。数组中的元素只能是 0 或者 1
     */
    private BitSet bits = new BitSet(DEFAULT_SIZE);
    /**
     * 存放包含 hash 函数的类的数组
     */
    private SimpleHash[] func = new SimpleHash[SEEDS.length];

    /**
     * 初始化多个包含 hash 函数的类的数组，每个类中的 hash 函数都不一样
     */
    public MyBloomFilter() {
        // 初始化多个不同的 Hash 函数
        for (int i = 0; i < SEEDS.length; i++) {
            func[i] = new SimpleHash(DEFAULT_SIZE, SEEDS[i]);
        }
    }

    /**
     * 添加元素到位数组
     */
    public void add(Object value) {
        for (SimpleHash f : func) {
            bits.set(f.hash(value), true);
        }
    }

    /**
     * 判断指定元素是否存在于位数组
     */
    public boolean contains(Object value) {
        boolean ret = true;
        for (SimpleHash f : func) {
            ret = ret && bits.get(f.hash(value));
        }
        return ret;
    }

    public static class SimpleHash {

        private int cap;
        private int seed;

        public SimpleHash(int cap, int seed) {
            this.cap = cap;
            this.seed = seed;
        }

        public int hash(Object value) {
            int h;
            return (value == null) ? 0 : Math.abs(seed * (cap - 1) & ((h = value.hashCode()) ^ (h >>> 16)));
        }
    }

    public static void main(String[] args) {
        // test1
        String value1 = "https://javaguide.cn/";
        String value2 = "https://github.com/Snailclimb";
        MyBloomFilter filter = new MyBloomFilter();
        System.out.println(filter.contains(value1));
        System.out.println(filter.contains(value2));
        filter.add(value1);
        filter.add(value2);
        System.out.println(filter.contains(value1));
        System.out.println(filter.contains(value2));

        ////test2
        //Integer value1 = 13423;
        //Integer value2 = 22131;
        //MyBloomFilter filter = new MyBloomFilter();
        //System.out.println(filter.contains(value1));
        //System.out.println(filter.contains(value2));
        //filter.add(value1);
        //filter.add(value2);
        //System.out.println(filter.contains(value1));
        //System.out.println(filter.contains(value2));
    }
}
```

### Guava中自带的布隆过滤器

## 参考

* [JavaGuide](https://github.com/Snailclimb/JavaGuide)