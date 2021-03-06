# Dynamic Programming

> 理解了`动态规划`算法后，你还是不能确定该使用什么样的公式，那就使用`费曼算法(Feynman algorithm)`，其步骤如下:
> (1) 将问题写下来
> (2) 好好思考
> (3) 将答案写下来

* 学习动态规划，它将问题分成小问题，并先解决这些小问题
* 学习如何设计问题的动态规划解决方案

## 背包问题

先看个背包问题，假设有个贪婪的小偷，背着`4千克`重东西的背包，在商场盗窃商品，力图在背包中装入价值最高的商品。可选的商品有: 吉他(1500元/1kg)，音响(3000元/4kg)、笔记本电脑(2000元/3kg)。

使用「贪婪策略」，解法很简单，先选择最贵的电脑，然后看剩余空间再选择其他商品，但得不到最优解(注: 贪婪算法往往得到一个近似值)。

另一种简单算法，尝试罗列各种可能的商品组合，从中找出价值最好的组合:

* 组合0: 无 = 0元
* 组合1: 吉他 = 1500元
* 组合2: 音响 = 3000元
* 组合3: 电脑 = 2000元
* 组合4: 吉他 + 电脑 = 3500元
* 组合5: 吉他 + 音响 = 装不下
* 组合6: 音响 + 电脑 = 装不下
* 组合7: 吉他 + 音响 + 电脑 = 装不下

结果是 组合4 价值最高。

这种解决方案可行，但速度非常慢。3种商品有8个不同的组合；4种商品的话，有16个不同集合。这种算法的运行时间为 O(2^n)，太慢了。

使用**动态规划**算法，动态规划先解决子问题，再逐步解决大问题，对于背包问题，先解决小背包(子背包)问题，再逐步解决原来的问题。

演示下这种算法的执行过程。

第一步，画一个网格，`列`为不同容量的背包，`行`为可选的商品。

空 | 1 | 2 | 3 | 4
---|---|---|---|---
吉他 | 0 | 0 | 0 | 0
音响 | 0 | 0 | 0 | 0
电脑 | 0 | 0 | 0 | 0

填充吉他行，当前只能装 吉他。

空 | 1 | 2 | 3 | 4
---|---|---|---|---
吉他 | 1500 | 1500 | 1500 | 1500
音响 | 0 | 0 | 0 | 0
电脑 | 0 | 0 | 0 | 0

填充音响行，可以装的有 吉他和音响

空 | 1 | 2 | 3 | 4
---|---|---|---|---
吉他 | 1500 | 1500 | 1500 | 1500
音响 | 1500 | 1500 | 1500 | 3000
电脑 | 0 | 0 | 0 | 0

当前最大值变成 3000 元，接着填充电脑行

空 | 1 | 2 | 3 | 4
---|---|---|---|---
吉他 | 1500 | 1500 | 1500 | 1500
音响 | 1500 | 1500 | 1500 | 3000
电脑 | 1500 | 1500 | 2000 | 3500

最终，确认将吉他和电脑装入，价值最高，为3500.

最后总结出公式为，cell[i][j] = max(cell[i-1][j], (当前商品价值 + 剩余空间的价值))，剩余空间的价值 = cell[i-1][j-当前商品重量]。

### 再增加一件商品

新增第四件商品 -- iPhone，价值为2000元(1kg)，填充结果为，

空 | 1 | 2 | 3 | 4
---|---|---|---|---
吉他 | 1500 | 1500 | 1500 | 1500
音响 | 1500 | 1500 | 1500 | 3000
电脑 | 1500 | 1500 | 2000 | 3500
iPhone | 2000 | 3500 | 3500 | 4000

### 假如可以装入部分商品

动态规划只能考虑装入整个商品，或者整个不装入，无法解决装入部分的问题。但可以使用「贪婪算法搞定这个问题！

## 另一个问题，旅游行程最优化

假如去伦敦度假，假期有两天，但想游览的地方很多，没法前往每个地方浏览，因此列个单子:

名胜 | 时间 | 评分
---|---|---
威斯敏斯特教堂 | 0.5天 | 7
环球剧场 | 0.5天 | 6
英国国家美术馆 | 1天 | 9
大英博物馆 | 2天 | 9
圣保罗大教堂 | 0.5天 | 8

这也是一个背包问题。这个网格的列是 有限的时间，行是 那些想浏览的名胜。

下面画出网格，并列出答案。

空 | 0.5 | 1 | 1.5 | 2
---|---|---|---|---
威斯敏斯特教堂 | 7 | 7 | 7 | 7
环球剧场 | 7 | 13 | 13 | 13
英国国家美术馆 | 7 | 13 | 16 | 22
大英博物馆 | 7 | 13 | 16 | 22
圣保罗大教堂 | 8 | 15 | 21 | 24

## 最长公共子串

通过前面的动态规划问题，可以得到:

* 动态规划可以在给定约束条件下找到最优解。
* 问题可分解成彼此独立且离散的子问题时，可使用动态规划来解决。
* 每种动态规划的解决方案都涉及到网格。
* 单元格中的值通常就是要优化的结果。
* 每个单元格都是一个子问题，因此要考虑如何将问题分成子问题。

再来看一个例子，在一个英文翻译网站，输入单词后，给出其定义。当用户拼错单词时，例如想着输入fish，不小心输入了hish，假设只有两个类似单词 fish 和 vista，怎么判断是哪一个？

### 制作网格

* 单元格的值是什么？
* 如何将这个问题划分为子问题？
* 网格的坐标轴是什么？

在这个例子中，你要找出两个单词的最长公共子串。单元格的值就是两个字符串都包含的最长子串的长度。

如何划分成子问题呢？在比较hish和fish之前，你需要先比较his和fis。

因此，网格类似于下面这样，同时也列出答案:

空 | H | I | S | H
---|---|---|---|---
F | 0 | 0 | 0 | 0
I | 0 | 1 | 0 | 0
S | 0 | 0 | 2 | 0
H | 0 | 0 | 0 | 3

伪代码就是

```python
if word_a[i] == word_b[j]:
  cell[i][j] = cell[i-1][j-1] + 1
else:
  cell[i][j] = 0
```

## 最长公共子序列

用户不小心输入了fosh，那原本是fish 还是 fort呢？

使用上面的方法来比较，得到的最长公共子串的长度是相同的，都是2。

但 fosh 于 fish 更新，应该是有3个字符类似。

其实，应比较最长公共子序列: 两个单词中都有的序列包含的字母数。

空 | F | O | S | H
---|---|---|---|---
F | 1 | 1 | 1 | 1
I | 1 | 1 | 1 | 1
S | 1 | 1 | 2 | 1
H | 1 | 1 | 1 | 3

伪代码 如下:

```python
if word_a[i] == word_b[j]:
  cell[i][j] = cell[i-1][j-1] + 1
else:
  cell[i][j] = max(cell[i-1][j], cell[i][j-1])
```

## 动态规划有哪些实际应用

* 生物学家根据最长公共序列来确定DNA链的相似性，进而判断度两种动物或疾病有多相 似。最长公共序列还被用来寻找多发性硬化症治疗方案。
* 你使用过诸如git diff等命令吗?它们指出两个文件的差异，也是使用动态规划实现的。
* 编辑距离(levenshtein distance)指出了两个字符串的相 似程度，也是使用动态规划计算得到的。
* 诸如Microsoft Word等具有断字功能的应用程序，它们如何确定在什么地方断字以确保行长一致呢?使用动态规划!

## 小结

* 需要在给定约束条件下优化某种指标时，动态规划很有用。
* 问题可分解为离散子问题时，可使用动态规划来解决。
* 每种动态规划解决方案都涉及到网格
* 单元格中的值通常就是你要优化的结果
* 每个单元都是一个子问题
* 没有放之四海皆准的计算动态规划解决方案的公式。
