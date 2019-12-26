# macOS设置指南

## 系统

第一件事件是**升级系统**，左上角图标 -> 关于本机 -> 软件更新...

### 系统偏好设置

... 待补充

## Homebrew

[Homebrew](https://brew.sh/) calls itself The missing package manager for macOS and is an essential tool for any developer. 小编注: macOS缺失的软件包的管理器，通过命令行安装 Mac 软件的工具(大部分是命令行工具)。

### 安装

安装依赖:

```sh
xcode-select --install
```

安装:

```sh
/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
```

最后确认是否安装成功:

```sh
brew doctor
```

### 使用手册

安装软件:

```sh
brew install <formula>
```

查看是否有需更新软件:

```sh
brew outdated
```

更新软件:

```sh
brew upgrade <formula>
```

删除老版本的软件:

```sh
brew cleanup
```

查看已安装的软件列表:

```sh
brew list --versions
```

搜索软件:

```sh
brew search <formula>
```

查看软件详情:

```sh
brew info <formula>
```

卸载软件:

```sh
brew uninstall <formula>
```

### 安装常用工具

工具名 | 安装命令 | 备注
---- | ---- | ----
git | brew install git | 版本管理工具
wget | brew install wget | 下载工具
curl | brew install curl | 下载工具
tmux | brew install tmux | 终端复用神器
vim | brew install vim | 升级 vim 至8.x版本，替代系统自带的
JDK | brew cask install java | open jdk java
tree | brew install tree | 目录展示工具
lua | brew install lua | lua编程语言
coreutils | brew install coreutils | gnu 核心工具
bin | brew install binutils | gnu bin工具
diff | brew install | diffutils | gnu 比较工具
find | brew install findutils | gnu 查找工具
ag | brew install the_silver_searcher | 高效内容查找
rzsz | brew install lrzsz | ssh 上传下载文件，适用于跳板机环境
go | brew install go | go 语言
node | brew install node | node
jq | brew install jq | 命令行json处理工具
htop | brew install htop | 替代top
axel | brew install axel | 多线程下载工具
cloc | brew install cloc | 代码统计工具
shellcheck | brew install shellcheck | shell 脚本检查工具
tldr | brew install tldr | 命令行示例
ncdu | brew install ncdu | 磁盘空间占用分
glances | brew install glances | 监控工具
figlet | brew install figlet | 艺术字转换
screenFetch | brew install screenFetch | 系统信息
nmap | brew install nmap | 网络扫描工具
ctop | brew install ctop | docker 容器监控工具
pstree | brew install pstree | 进程树查看
bash-completion | brew install bash-completion | bash补全
lolcat | brew install lolcat | 彩虹文字
peco | brew install peco | go 写的极简过滤工具
cowsay | brew install cowsay | ascii图片
graphviz | brew install graphviz | 拓扑图绘制工具

### 扩展1 - brew cask

使用 brew cask 安装 macOS 应用程序、字体和插件以及其他非开源软件。不会再出现了“要安装，请拖动此图标......”。

安装:

```sh
brew tap caskroom/cask
```

搜索:

```sh
brew search <package>
```

brew cask 安装常用工具:

[quick-look-plugins](https://github.com/sindresorhus/quick-look-plugins)

* QLColorCode - Preview source code files with syntax highlighting
* QLStephen - Preview plain text files without or with unknown file extension. Example: README, CHANGELOG, index.styl, etc.
* QLMarkdown - Preview Markdown files
* QuickLookJSON - Preview JSON files
* BetterZipQL - Preview archives
* qlImageSize - Display image size and resolution
* ...

APP类

```sh
brew cask install \
    alfred \
    android-file-transfer \
    appcleaner \
    caffeine \
    cheatsheet \
    docker \
    doubletwist \
    dropbox \
    google-chrome \
    google-hangouts \
    flux \
    1password \
    spectacle \
    sublime-text \
    superduper \
    transmission \
    valentina-studio \
    vlc
```

### 扩展2 - Homebrew Services

A nice extension to Homebrew is [homebrew-services](https://github.com/Homebrew/homebrew-services). It will automatically launch things like databases when your computer starts, so you don't have to do it manually every time.

手动添加服务(一般情况 安装时已经添加):

```sh
brew services <formula>
```

启动服务:

```sh
brew services start <formula>
```

查看服务状态:

```sh
brew services list
```

## iTerm2

更优雅地 command-line 工作姿势，请使用 [iTerm2](http://www.iterm2.com/)。

使用 `Homebrew` 安装

```sh
brew cask install iterm2
```

再说说配置。

### 偏好设置(Preference)

前往 **iTerm2 > Preferences...**，**General** tab - Closing，取消"Confirm closing multiple sessions"和"Confirm 'Quit iTerm2(Cmd+Q)'"的打勾。

在 **Profiles** tab，点击"+"图标，新建一个以自己名字命名的Profile，同时设置为默认的(Other Actions... > Set as Default)，然后在**General**中设置**Working Directory**为**Reuse previous session's directory**。最后，在**Window**中调整显示大小至合适的(i.e. Columns: 125 and Rows: 35)。

最后记得设置静音模式，Go to profiles -> Default(or yours) -> Terminal -> Check silence bell to disable the terminal session from making any sound。

### 快捷键(⌘←, ⌘→ and ⌥←, ⌥→)

日常编码习惯使用⌘←, ⌘→ and ⌥←, ⌥→来快速切换光标，通过以下方式来设置iTerm2实现这四个快捷键。

设置路径: preferences (⌘ + ,) -> Profiles -> Keys -> Click on + icon (add new Keyboard shortcut)

快捷键 | action | Esc+
---- | ---- | ----
⌘← | Send Escape Sequence | OH
⌘→ | Send Escape Sequence | OF
⌥← | Send Escape Sequence | b
⌥→ | Send Escape Sequence | f

### 配色

[iterm2 配色官网](https://iterm2colorschemes.com/)

> iterm2 > preference > profiles > colors > Color Presets > solarized dark

### zsh

Mac系统自带了zsh，但不是新版的，如果喜欢新版可以通过Homebrew来安装:

```sh
brew install zsh
```

[官网](https://ohmyz.sh/)

## 参考

* [awesome-mac](https://github.com/jaywcjlove/awesome-mac/blob/master/README-zh.md)
* [macOS Setup Guide](http://sourabhbajaj.com/mac-setup/)
* [mac-dev-setup](https://github.com/nicolashery/mac-dev-setup)
* [还在用 Win？教你从零把 Mac 打造成开发利器](https://mp.weixin.qq.com/s/qRzpNHZSL6hnZNwUnoaO1g)

