# Java Best Practices Guide

从代码规范、单元测试、代码审查及审查清单来谈谈怎么实现Java的最佳编码。

## 编码规范

不以规矩，不能成方圆。

参考下阿里巴巴的《阿里巴巴Java开发手册》，覆盖编程规约、异常日志、单元测试、安全规约、MySQL数据库、工程结构、设计规约。

* [Alibaba Java Coding Guidelines](https://github.com/alibaba/p3c): 阿里巴巴的Java代码规约。

### Quick Start in IDEA

refer: <https://github.com/alibaba/p3c/tree/master/idea-plugin>

## 单元测试

单元测试说起来简单，实际操作过程要注意它不是为了测试而测试的 -- 测试驱动开发(Test-Driven Development, TDD)。

测试覆盖率低的病灶常常如下:

* 团队成员没有写测试的习惯，没有意识到测试的重要性，不想写。
* 代码难于测试，不会写。
* 赶进度，没有时间写。

TDD的3个原则:

* 原则1: 无测试，不代码。
* 原则2: 单元测试不在多，能够识别出问题即可。
* 原则3: 代码不在多，让当前单元测试全部通过即可。

具体的操作步骤，如下:

* 第1步，金丝雀测试。在开发具体的测试用例之前，先写一个dummy测试用例，确保整个编译、运行和JUnit环境是正常运行的。
* 第2步，编写第1个最简单的单元测试，比如关于null。
* 第3步，编写第2个单元测试。这是，测试往往不通过了，修改代码，只要求满足当前的需求即可。
* 第4步，继续加需求，可以与上个单元测试合并，再运行、改代码。
* 第5步，重复第4步，直到满足完整的也去需求。
* 第6步，重构，为了更好地前行。往往进行泛化重构，实现了`从特殊到一般`。
* 第7步，继续加需求，这时发现测试依然是通过的。

## 代码审查

代码审查，是指就对源码进行系统审查，以找出并修正软件开发初期末发现的错误，提升软件的质量，主要借助开发者之间的相互阅读及代码审查，来发现代码中的Bug等问题。

**标准: 通常而言，只要代码对系统有明显的提升且正常工作，即便不完美，评审者也应该倾向于通过这次变更。**(google Code Review准则的高级原则)

### 为什么要代码审查

其一，提升代码的质量，找出潜在的Bug，保证代码风格的统一。另外最重要的是一个相互学习的好机会，发现他人的优秀编码，同时对了解整个系统和业务有很好的帮助；同时，通过交互提一些改进意见，很好地相互学习。

### 如何做代码审查

实践中，代码审查的效果往往不明显，以下探讨下如何做好代码审查。

#### 1. 审查清单

* 代码结构: ~~超长代码~~；~~嵌套过深~~；~~循环无跳出点~~；~~if语句缺else~~；~~重复代码~~。
* 代码安全: I/O是否正常关闭；是否有超大的临时对象；线程大小是否合理；异常是否处理；日志记录；参数是否有做检查；RPC服务参数是否可序列化；是否有依赖SNAPSHOT版本的类库等
* 代码性能: SQL是否有性能问题；是否有成熟的类库替代实现；
* 代码注释: 类及方法是否有合理的注释，是否存在FIXME及TODO
* 单元测试: 是否有可测试性，是否有单元测试，单元测试是否覆盖所有场景
* 代码优化: 是否存在魔法数字；是否可以使用Optional替换NPE检查；是否可以使用设计模式
* 其他: 是否有很好的可读性

#### 2. 高效的工具

Gerrit

#### 3. 重点关注的改动点

重点关注改动点并不是只关注改动点，其实，还有很多问题隐藏在非改动点处。

#### 4. 代码审查应该是日常性的工作

代码审查应该是日常性的工作，而不是代码上线前的集中性工作。

#### 5. 不要一次性审查太多代码

每次审查的代码行数最好在200行以内，不超过400行 -- Code Review at Cisco Systems。

#### 6. 进行一次代码审查的时间不要太长

控制在90分钟内。

扩展阅读 [google/eng-practices](https://github.com/google/eng-practices)