<img id="app" src="images/app.png"/>

# 马克笔记——Android端开源的Markdown笔记应用

> 马克笔记是运行在Android设备上面的一款开源的Markdown笔记，它的功能开发得已经比较完善，已经能够满足大部分用户的需求。现在将其开源到Github上面，用来交流和学习。当然，更希望你能够参与到项目的开发当中，帮助马克笔记变得更加有用。

## 1、关于马克笔记

<img src="images/mark.png" align="left" width="160" hspace="10" vspace="10"/>

马克笔记是一款开源的Markdown笔记应用，它的界面设计采用了Google最新的Material Design风格。该笔记现在的功能已经比较完善，能够满足用户大多数场景的需求。开源该软件的目的是希望与更多的人交流和学习，同时也希望能够有人参与到项目的开发中，一起帮助马克笔记，让它变得更加有用。

你可以通过加入[Google+社区](https://plus.google.com/u/1/communities/102252970668657211916)来关注该软件开发的最新动态，并且可以参与Beta测试。

马克笔记现在已经发布到了[酷安网](https://www.coolapk.com/apk/178276)上面，也欢迎你下载和使用该软件。

## 2、应用展示图

<a href="#app">这里</a>是该应用的一些截图通过Photoshop调整之后得到的展示图，通过展示图，你大概可以了解一下该软件的主要功能和开发状态。在接下来的行文中，我会向你更详细地介绍它使用到的一些技术以及现在开发完成的一些功能和特性。

## 3、功能和特性

我把该软件当前已经支持的功能列了一个清单：

|编号|功能|
|:-:|:-:|
|1|基本的**添加、修改、归档、放进垃圾箱、彻底删除**操作|
|2|基本的Markdown语法，外加**MathJax**等高级特性|
|3|特色的**时间线**功能，通过类似于AOP的操作记录用户的操作信息|
|4|多种形式的媒体数据，包括**文件、视频、音频、图片、手写和位置信息**等|
|5|**多主题**，支持**夜间主题**，并且有多种可选的**主题色和强调色**|
|6|多彩的**图表**用于统计用户的数据信息|
|7|三种形式的**桌面小控件**，并且可以为每个笔记添加快捷方式|
|8|允许你为笔记指定多个多彩的标签|
|9|使用“树结构”模拟文件夹操作，支持**多层文件夹**，并可以进行层级的搜索|
|10|允许将笔记**导出为PDF、TXT、MD格式的文本、HTML和图片**|
|11|使用**应用独立锁**，加强数据安全|
|12|允许用户**备份数据到外部存储空间和OneDrive**|
|13|图片**自动压缩**，节省本地的数据存储空间|

将来希望开发和完善的功能:

|编号|功能描述|
|:-:|:-:|
|1|数据同步，本地的文件管理容易导致多平台的不一致，增加同步服务，能够实现多平台操作|
|2|文件服务器，用于获取图片和文件的链接|
|3|富文本编辑，即使的编辑预览|
|4|允许添加闹钟，并且复选框可以编辑|
|5|添加地图来展示用户的位置信息的变更|

你可以从[更新日志](app/src/main/res/raw/changelog.xml)中获取到软件的更新信息。

## Dependencies

They're all listed into the build.gradle files but due to the fact that many of the dependences have been customized by me I'd like to say thanks here to the original developers of these great libraries:

* http://commons.apache.org/io/
* https://github.com/chrisbanes/PhotoView
* https://github.com/afollestad/material-dialogs
* https://github.com/bumptech/glide
* https://github.com/square/retrofit
* https://github.com/square/okhttp
* https://github.com/facebook/stetho
* https://github.com/ReactiveX/RxAndroid
* https://github.com/ReactiveX/RxJava
* https://github.com/JakeWharton/RxBinding
* https://github.com/google/gson
* https://github.com/JodaOrg/joda-time
* https://github.com/lecho/hellocharts-android
* https://github.com/Clans/FloatingActionButton
* https://github.com/LarsWerkman/HoloColorPicker
* https://github.com/CymChad/BaseRecyclerViewAdapterHelper
* https://github.com/hdodenhof/CircleImageView
* https://github.com/gabrielemariotti/changeloglib
* https://github.com/aritraroy/PinLockView
* https://github.com/Kennyc1012/BottomSheet
* https://github.com/Curzibn/Luban
* https://github.com/vsch/flexmark-java
* https://github.com/ocpsoft/prettytime

## Contributing

### Code & Issue

If you are a developer and you wish to contribute to the app please fork the project and submit a pull request. You can trace the status of known issues on [waffle.io](https://waffle.io/Shouheng88/NotePal),
also feel free to file a new issue (helpful description, screenshots and logcat are appreciated), or send me an [email](mailto:shouheng2015@gmail.com) if you have any questions.

### Translations

If you are able to contribute with a new translation of a missing language or if you want to improve an existing one, we greatly appreciate any suggestion!
The project uses [transifex](https://www.transifex.com/null-20/notepal), a platform that allows anybody to contribute to translating the app

## Contact 
