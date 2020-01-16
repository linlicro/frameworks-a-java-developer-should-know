# macOS 设置指南

## 系统

第一件事件是**升级系统**，左上角图标 -> 关于本机 -> 软件更新...

### 系统偏好设置

安全性与隐私:

* 通用，勾选 进入睡眠后活开始屏幕保护程序`立即`要求输入密码

触发角:

* 系统偏好设置/屏幕保护程序/触发角, 选择(右上桌面，左下启动台，右下启动屏幕保护)

触摸板(Trackpad):

* 选中`轻点来点按`(用起来方便)
* 修改`辅助点按`为`点按右下角`

程序坞(Docker):

* 大小设置到最小(拖到最左边)，以显示最小图标
* 选中`自动显示和隐藏程序坞`

键盘(Keyboard):

* 设置`按键重复` 最快
* 设置`重复前延迟` 最短

节能:

* 系统偏好设置/辅助功能/显示, 勾选 (减弱动态效果、减少透明度)

## Homebrew

[Homebrew](https://brew.sh/) calls itself The missing package manager for macOS and is an essential tool for any developer. 小编注: macOS缺失的软件包的管理器，通过命令行安装 Mac 软件的工具(大部分是命令行工具)。

### Homebrew的安装

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

### Homebrew的常用命令

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

### 使用Homebrew中遇到的问题记录

Q1: cannot load such file -- active_support/core_ext/object/blank (LoadError):

```sh
brew doctor
Traceback (most recent call last):
 4: from /usr/local/Homebrew/Library/Homebrew/brew.rb:23:in `<main>'
 3: from /usr/local/Homebrew/Library/Homebrew/brew.rb:23:in `require_relative'
 2: from /usr/local/Homebrew/Library/Homebrew/global.rb:13:in `<top (required)>'
 1: from /System/Library/Frameworks/Ruby.framework/Versions/2.6/usr/lib/ruby/2.6.0/rubygems/core_ext/kernel_require.rb:54:in `require'
/System/Library/Frameworks/Ruby.framework/Versions/2.6/usr/lib/ruby/2.6.0/rubygems/core_ext/kernel_require.rb:54:in `require': cannot load such file -- active_support/core_ext/object/blank (LoadError)
```

解决方案:

```sh
brew update-reset
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
# 安装 zsh 及 补全
brew install zsh zsh-completions
```

接下来，修改相关配置，在/etc/shells文件中加入如下一行:

```sh
/usr/local/bin/zsh
```

然后运行命令

```sh
chsh -s /usr/local/bin/zsh
```

注意，zsh的配置文件是`.zshrc`。`~/.bash_profile`将不再被加载，该文件是默认shell(bash)的配置文件。

下面你需要[Oh My Zsh](https://github.com/robbyrussell/oh-my-zsh) 或者 [Prezto](https://github.com/sorin-ionescu/prezto) 来管理`zsh`的配置，这里选择`oh-my-zsh`。

#### Oh My Zsh

安装

```sh
sh -c "$(curl -fsSL https://raw.githubusercontent.com/robbyrussell/oh-my-zsh/master/tools/install.sh)"
```

配置

```sh
# 设置主题
ZSH_THEME=pygmalion
# 设置插件
plugins=(git colored-man colorize github jira vagrant virtualenv pip python brew osx zsh-syntax-highlighting)

# ls 配色生效
unset LSCOLORS
export CLICOLOR=1
export CLICOLOR_FORCE=1

# 生效
source ~/.env.sh
```

其他`插件`、`主题`请参考[oh-my-zsh wiki](https://github.com/ohmyzsh/ohmyzsh/wiki)。

#### env.sh

可以用`env.sh`做些预定义设置，在`~/.zshrc`添加，

```sh
source ~/<path to file>/env.sh
```

添加配置信息(个人的配置，大伙自定义):

```sh
#!/bin/zsh

# Add commonly used folders to $PATH
export PATH="/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin"

# Specify default editor. Possible values: vim, nano, ed etc.
export EDITOR=vim

# File search functions
function f() { find . -iname "*$1*" ${@:2} }
function r() { grep "$1" ${@:2} -R . }

# Create a folder and move into it in one command
function mkcd() { mkdir -p "$@" && cd "$_"; }

# Example aliases
alias cppcompile='c++ -std=c++11 -stdlib=libc++'
alias g='git'
```

## Git

Mac有预安装 [Git](https://git-scm.com/)，不过 还是通过Homebrew 来安装、管理Git。

```sh
brew install git

which git
```

将得到 `/usr/local/bin/git`。

### 基本配置信息

下载 [.gitconfig](https://raw.githubusercontent.com/nicolashery/mac-dev-setup/master/.gitconfig)

```sh
cd ~
curl -O https://raw.githubusercontent.com/nicolashery/mac-dev-setup/master/.gitconfig
```

以上做个一些基本命令(`status`,`branch`, `diff`)的配色，以及新增一些别名(tags, branches, remotes)。

用户信息配置:

```sh
git config --global user.name "Your Name Here"
git config --global user.email "your_email@youremail.com"
```

添加全部`.gitignore`，小编的工作环境是Mac，需要将`.DS_Store`默认加入每个项目的`.gitignore`。

```sh
cd ~
curl -O https://raw.githubusercontent.com/nicolashery/mac-dev-setup/master/.gitignore
git config --global core.excludesfile ~/.gitignore
```

常用的gitignore信息 请参见<https://www.gitignore.io/>。

### GitHub配置

... 待补充

## Python

MacOS自身已经带有[Python](https://python.org/)，这个Python主要用于支持系统文件和XCode，我们为了不跟系统的混淆，安装自己所需的Python吧。

安装方式有两种:

* Homebrew
* Pyenv - Python 版本管理工具

### 安装 Homebrew方式

```sh
brew install python # Python 3(default)

brew install python@2 # Python 2.7
```

### 安装 通过Pyenv方式

通过 [Pyenv](https://github.com/pyenv/pyenv) 方式来安装，为方便管理不同版本的Python。

```sh
brew install pyenv
```

配置环境变量:

```sh
echo 'eval "$(pyenv init -)"' >> ~/.zshrc
eval "$(pyenv virtualenv-init -)" >> ~/.zshrc
# 如果使用默认的shell，添加配置至 ~/.bash_profile
```

根据[pyenv wiki](https://github.com/pyenv/pyenv/wiki)建议，安装一些依赖:

```sh
brew install openssl readline sqlite3 xz zlib
```

### pyenv 常用命令

```sh
pyenv commands # 显示所有可用命令

pyenv install --list # 查看可安装 Python 版本

pyenv install 3.x.x # look for the latest 3.x version, and install it (replace the .x.x with actual numbers)
pyenv uninstall 3.x.x

pyenv versions # 查看本机安装 Python 版本

pyenv shell 3.x.x # 设置面向 shell 的 Python 版本，通过设置当前 shell 的 PYENV_VERSION 环境变量的方式。这个版本的优先级比 local 和 global 都要高。–unset 参数可以用于取消当前 shell 设定的版本。
pyenv shell --unset

pyenv global 3.6.8 # 设置全局的 Python 版本，通过将版本号写入 ~/.pyenv/version 文件的方式
pyenv rehash # 创建垫片路径

pyenv local 3.x.x # 独立项目的python版本设置，保存在项目目录里的`.python-version`

pyenv virtualenv 3.6.8 env-3.6.8 # 创建虚拟环境
pyenv activate env-3.6.8 # 激活虚拟环境
pyenv deactivate #退出虚拟环境
```

全部命令参见 [pyenv commands](https://github.com/pyenv/pyenv/blob/master/COMMANDS.md)。

### pyenv 安装慢问题

pyenv install 下载非常慢甚至卡住，还会出错`error: failed to download`。

加速方式:

* 使用淘宝镜像源加速：<https://npm.taobao.org/mirrors/python/>
* 下载需要的版本放到~/.pyenv/cache文件夹下面
* 然后执行 pyenv install 版本号 安装对应的python版本

### pip python包管理

Python安装时，同时会安装[pip](https://pip.pypa.io/en/stable/)。

#### pip 配置文件

```sh
# 创建 pip 配置目录
mkdir ~/.pip

# 编辑配置
vim ~/.pip/.pip.conf

# 添加以下内容
[list]
format=columns
[global]
download_cache = ~/.cache/pip
index-url = http://mirrors.aliyun.com/pypi/simple/
[install]
trusted-host=mirrors.aliyun.com
```

#### pip 常用命令

```sh
pip install <package> # to install a Python package

pip install --upgrade <package> # to upgrade a package

pip freeze # to see what's installed

pip uninstall <package> # to uninstall a package
```

### virtualenv

[virtualenv](https://virtualenv.pypa.io/) is a tool that creates an isolated Python environment for each of your projects.

pyenv 插件：pyenv-virtualenv

## Java 环境

先检查`是否已经安装`:

```sh
java -version
```

若得到以下信息，说明本地具有Java的开发环境。

```sh
java version "1.8.0_74"
Java(TM) SE Runtime Environment (build 1.8.0_74-b02)
Java HotSpot(TM) 64-Bit Server VM (build 25.74-b02, mixed mode)
```

若没有，则参考下面信息安装。

### 通过Oracle官网下载

[Oracle website](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

### Homebrew(推荐)

#### 注意: 更新

Java8 is no longer available on Homebrew. Use below command instead :

```sh
brew cask install adoptopenjdk/openjdk/adoptopenjdk8
```

* [Java8 not working anymore](https://github.com/Homebrew/homebrew-cask-versions/issues/7253)
* [adoptopenjdk](https://adoptopenjdk.net/)

```sh
brew update
brew tap caskroom/versions
```

安装 java 9:

```sh
brew cask install java
```

或者，安装java 8:

```sh
brew cask install java8
```

### 设置`JAVA_HOME`

往`env.sh`添加环境变量 `JAVA_HOME`:

```sh
export JAVA_HOME="`/usr/libexec/java_home -v 1.8`"
```

或者，你使用Java 9:

```sh
export JAVA_HOME="`/usr/libexec/java_home -v 9`"
```

Java开发IDE推荐[IntelliJ](https://www.jetbrains.com/idea/download/)，另附 [IntelliJ-IDEA-Tutorial](https://github.com/judasn/IntelliJ-IDEA-Tutorial)。

## Visual Studio Code

[Visual Studio Code](https://code.visualstudio.com/) 是一款免费开源的现代化轻量级代码编辑器，支持几乎所有主流的开发语言的语法高亮、智能代码补全、自定义热键、括号匹配、代码片段、代码对比 Diff、GIT 等特性，支持[插件扩展](https://code.visualstudio.com/docs/editor/extension-gallery)，并针对网页开发和云端应用开发做了优化。

### vscode 安装

使用`Homebrew`方式来安装:

```sh
brew cask install visual-studio-code
```

也可以通过[下载](https://code.visualstudio.com/Download)来安装。

Note: 建议在`程序坞`保留Visual Studio Code(和iterm一样)，右击Docker中的图标 **选项 > 在程序坞中保留**。

### vscode 配置

进入**Code > Preferences > Settings**(快捷键是 **Cmd + ,**)，然后在右上角点击`打开设置(json)`，粘贴以下配置信息(参考):

```json
{
    "files.autoSave": "afterDelay",
    "files.insertFinalNewline": true,
    "files.trimTrailingWhitespace": true,
    "editor.formatOnPaste": true,
    "editor.tabSize": 2,
    "editor.rulers": [
        80
    ],
    "workbench.editor.enablePreview": false
}
```

以上方式可以在你的新设备，快速配置vscode。日常修改配置时，你可以通过配置(ui)的搜索功能，快速定位配置项来做修改。

### 配置命令行中打开

如果你只想记住一个vscode的快捷键，那它会是`Cmd+Shift+P(⇧ ⌘ P)`，用来打开`命令面板`。

接着，马上使用下`命令面板`来安装插件，用来通过[`终端命令`快速打开VS Code](https://code.visualstudio.com/docs/setup/mac#_launching-from-the-command-line)。

`Cmd+Shift+P` > `Shell Command: Install 'code' command in PATH`。

这样，你可以在终端直接打开项目:

```sh
cd myproject/
code . ## 打开文件夹

code myfile.txt ## 打开文件
```

### vscode 插件安装

* Chinese (Simplified) Language Pack for Visual Studio Code(本地化)
* markdownlint(markdown格式检查器)
* atom one dark theme(ATOM 风格主题)
* one dark pro(主题)
* material theme(主题)
* vscode-icons(图标)
* Image preview(图片预览: 光标悬浮在图片路径上时显示)

#### vscode python 解释器选择

`Cmd+Shift+P(⇧ ⌘ P)` > `select interpreter`

#### python 编程

[Editing Python in Visual Studio Code](https://code.visualstudio.com/docs/python/editing)

## Vim

虽然VS Code将是我们的主要编辑器，但日常工作中还需掌握[Vim](https://www.vim.org/)的基本用法。

Vim是一个高度可配置的文本编辑器，用于高效地创建和更改任何类型的文本。

### Vim 安装

通过`homebrew`安装最新版:

```sh
brew install vim
```

### vimrc - The Ultimate vimrc

[The Ultimate vimrc](https://github.com/amix/vimrc) 是史上最强、最全的vimrc配置。

提供两个版本:

* The Basic 基础版本
* The Awesome

#### 安装 Awesome 版本

```sh
git clone --depth=1 https://github.com/amix/vimrc.git ~/.vim_runtime
sh ~/.vim_runtime/install_awesome_vimrc.sh
```

#### 安装 Basic 版本

```sh
git clone --depth=1 https://github.com/amix/vimrc.git ~/.vim_runtime
sh ~/.vim_runtime/install_basic_vimrc.sh
```

### 更新 amix/vimrc.git

```sh
cd ~/.vim_runtime
git pull --rebase
python update_plugins.py
```

## Sublime Text

[Sublime Text](http://www.sublimetext.com/) 是广泛使用到的编辑器，官方定义: a sophisticated text editor for code, markup and prose.

### 安装

与常规App安装一样，前往官网[下载](http://www.sublimetext.com/)，拖拽至`Applications`文件夹。

### 设置CLI方式打开文件

```sh
ln -s /Applications/Sublime\ Text.app/Contents/SharedSupport/bin/subl /usr/local/bin/subl
```

然后，你可以在终端快速打开文件 `subl myfile.py`，或者打开当前目录 `subl .`。

### Sublime Text 设置

偏好设置，这是一份基础开发配置(参考):

```json
{
    "auto_complete_delay": 5,
    "auto_complete_selector": "source, text",
    "color_scheme": "Packages/User/Monokai (SL).tmTheme",
    "create_window_at_startup": false,
    "folder_exclude_patterns":
    [
        ".svn",
        ".git",
        ".DS_Store",
        "__pycache__",
        "*.pyc",
        "*.pyo",
        "*.exe",
        "*.dll",
        "*.obj",
        "*.o",
        "*.a",
        "*.lib",
        "*.so",
        "*.dylib",
        "*.ncb",
        "*.sdf",
        "*.suo",
        "*.pdb",
        "*.idb",
        "*.psd"
    ],
    "font_face": "Source Code Pro",
    "font_size": 13,
    "ignored_packages":
    [
        "Markdown",
        "Vintage"
    ],
    "open_files_in_new_window": false,
    "rulers":
    [
        80
    ],
    "translate_tabs_to_spaces": true,
    "word_wrap": true
}
```

### 安装 Package Control

Package Control 可方便管理Sublime text的插件，最方便的安装方式是 如下:

* 打开`Console`: ctrl+\` 快捷键 或者通过菜单 `View > Show Console`打开
* 参考<https://packagecontrol.io/installation> 执行相关命令

推荐的插件有:

* [Git](https://github.com/kemayo/sublime-text-git): Plugin for some Git integration
* [SublimeLinter](https://sublimelinter.readthedocs.io/en/stable/): Interactive code linting framework for Sublime Text 3
* [IndentXML](https://github.com/alek-sys/sublimetext_indentxml): Plugin for re-indenting XML and JSON files

## 参考

* [awesome-mac](https://github.com/jaywcjlove/awesome-mac/blob/master/README-zh.md)
* [macOS Setup Guide](http://sourabhbajaj.com/mac-setup/)
* [mac-dev-setup](https://github.com/nicolashery/mac-dev-setup)
* [使用 pyenv 管理 Python 版本](http://einverne.github.io/post/2017/04/pyenv.html)
* [还在用 Win？教你从零把 Mac 打造成开发利器](https://mp.weixin.qq.com/s/qRzpNHZSL6hnZNwUnoaO1g)