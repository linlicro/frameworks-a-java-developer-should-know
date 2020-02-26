# 约定式提交规范（Conventional Commits）

约定式提交规范是一种基于提交消息的轻量级约定。它提供了一组用于创建清晰的提交历史的简单规则；这使得编写基于规范的自动化工具变得更容易。这个约定与SemVer相吻合，在提交信息中描述新特性、bug 修复和破坏性变更。它的 message 格式如下:

```txt
<类型>[可选的作用域]: <描述>

[可选的正文]

[可选的脚注]
```

## Quick Start

全局安装commitizen & cz-conventional-changelog。

在(.git同目录)目录下自己创建一个package.json 并填入:

```json
{
  "name": "commitizen",
  "version": "0.0.0",
  "private": true,
  "description": "git commitizen config"
}
```

终端运行安装，以及日常使用`git cz`替代`git commit -m`:

```shell script
# 需 node 环境
npm install -g commitizen
# Angular 的 Commit message 格式
commitizen init cz-conventional-changelog --save --save-exact

# 使用
git cz
```

## Commit message规范

针对团队目前使用的情况，我们讨论后拟定了commit message每一部分的填写规则。

### 1、type

type为必填项，用于指定commit的类型，约定了feat、fix两个主要type，以及docs、style、build、refactor、revert五个特殊type，其余type暂不使用。

```txt

# 主要type
feat:     增加新功能
fix:      修复bug

# 特殊type
docs:     只改动了文档相关的内容
style:    不影响代码含义的改动，例如去掉空格、改变缩进、增删分号
build:    构造工具的或者外部依赖的改动，例如webpack，npm
refactor: 代码重构时使用
revert:   执行git revert打印的message

# 暂不使用type
test:     添加测试或者修改现有测试
perf:     提高性能的改动
ci:       与CI（持续集成服务）有关的改动
chore:    不修改src或者test的其余修改，例如构建过程或辅助工具的变动
```

当一次改动包括主要type与特殊type时，统一采用主要type。

### 2、scope

scope也为必填项，用于描述改动的范围，格式为项目名/模块名。如果一次commit修改多个模块，建议拆分成多次commit，以便更好追踪和维护。

### 3、body

body填写详细描述，主要描述改动之前的情况及修改动机，对于小的修改不作要求，但是重大需求、更新等必须添加body来作说明。

### 4、break changes

break changes指明是否产生了破坏性修改，涉及break changes的改动必须指明该项，类似版本升级、接口参数减少、接口删除、迁移等。

### 5、affect issues

affect issues指明是否影响了某个问题。例如我们使用jira时，我们在commit message中可以填写其影响的JIRA_ID，若要开启该功能需要先打通jira与gitlab。

填写方式例如：

```txt
re #JIRA_ID
fix #JIRA_ID
```

## 参考

* [conventional commits](https://www.conventionalcommits.org/en/v1.0.0/) `必读` 介绍约定式提交标准。
* [Angular规范](https://github.com/angular/angular/blob/22b96b9/CONTRIBUTING.md#-commit-message-guidelines) `必读` 介绍Angular标准每个部分该写什么、该怎么写。
* [@commitlint/config-conventional](https://github.com/conventional-changelog/commitlint/tree/master/%40commitlint/config-conventional#type-enum) 介绍commitlint的校验规则config-conventional，以及一些常见passes/fails情况。
