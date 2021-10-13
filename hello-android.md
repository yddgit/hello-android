# Android开发

## 无线调试

```bash
adb devices
# output: 
# List of devices attached
# 6969beb11120    device
adb tcpip 5555
adb -s <device-number> tcpip 5555
# output:
# restarting in TCP mode port: 5555
adb connect <android-device-ip-address>:5555
# output:
# connected to <android-device-ip-address>:5555
```

## 一、基础概念

* Android在一个或多个Activity上展示界面
* Activity上可以显示Fragment
* 布局文件一般由XML编写
* Android控件分为View和ViewGroup
* 在Activity中通过findViewById(R.id.控件ID)来获取指定控件对象

### Layout文件

```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">
    <TextView
        android:layout_width="match_content"
        android:layout_height="wrap_content"
        android:text="Hello World!"
    />
</LinearLayout>
```

* 命名空间`xmlns:android="http://schemas.android.com/apk/res/android"`引入android定义的属性
* `layout_width`和`layout_height`取值：`fill_parent`、`match_parent`、`wrap_content`
  * `fill_parent`：强制使用组件扩展，以填充布局单元内尽可能多的空间
  * `match_parent`：Android 2.2开始加入，和`fill_parent`含义相同
  * `wrap_content`：强制使用组件扩展以展示全部内容，布局元素将根据内容更改大小

### 控件通用属性

* `layout_width`：宽度
* `layout_height`：高度
* `padding`：内边距
* `margin`：外边距
* `visibility`：`visible`显示，`invisible`不显示但占用空间，`gone`不显示也不占用空间
* `focusable`：是否可能获取焦点
* `enabled`：是否启用该组件
* `background`：背景颜色（16进制值）
* `id`：唯一id，可以快速定位到该控件

### View和ViewGroup的区别

* View是绘制在屏幕上的用户与之交互的一个对象
* ViewGroup则是一个用于存入View或ViewGroup的布局容器
* Android为我们提供了一些内置的View和ViewGroup集合，如按钮、文本域和各种布局模式
* 属性
  * `layout_margin`：距离父容器的距离
  * `layout_marginTop`：距离父容器顶部的距离
  * `layout_marginBottom`：距离父容器底部的距离
  * `layout_marginLeft`：距离父容器左侧的距离
  * `layout_marginRight`：距离父容器右侧的距离
  * `layout_padding`：子View距离本容器的距离
  * `layout_paddingTop`：子View距离本容器顶部的距离
  * `layout_paddingBottom`：子View距离本容器底部的距离
  * `layout_paddingLeft`：子View距离本容器左侧的距离
  * `layout_paddingRight`：子View距离本容器右侧的距离

## 二、布局

### 1. LinearLayout线性布局

将它所包含的控件在线性方向上依次排列，排列方向有两个：水平、垂直

* `orientation`设置子View的对齐方式
  * `horizontal`让子View水平排列，即从左到右依次排序
  * `vertical`让子View垂直排列，即从上到下依次排序
* `layout_gravity`组件自身在父组件中的位置
* `gravity`组件的子组件在组件中的位置
  * `top`将对象推到容器顶部，而不更改其大小
  * `bottom`将对象推到容器底部，而不更改其大小
  * `left`将对象推到容器的左侧，而不更改其大小
  * `right`将对象推到容器的右侧，而不更改其大小
  * `center_vertical`将对象放置在其容器的垂直中心，不更改其大小
  * `fill_vertical`如果需要，增大对象的垂直大小，使其完全填充其容器
  * `center_horizontal`将对象放置在其容器的水平中心，不更改其大小
  * `fill_horizontal`如果需要，增大对象的水平大小，使其完全填充其容器
  * `center`在垂直和水平轴上将对象放置在其容器中心，不更改其大小
  * `fill`根据需要增加对象的水平和垂直大小，使用其完全填充其容器
  * `clip_vertical`将子对象的上边缘和/或下边缘剪裁到其容器边界
  * `clip_horizontal`将子对象的左边缘和/或右边缘剪裁到其容器边界
  * `start`将对象推到其容器的开头，而不更改其大小
  * `end`将对象推到其容器的结尾，而不更改其大小
* `weightSum`将布局空间平均分为若干份，子View通过`layout_weight`决定自身占用多少份
  * 默认子View的权重都是0，即自身占用多少空间就是多少
  * 如果所有子View只有一个设置了权重，则这个子View将占据所有剩余所有空间
  * 系统会优先绘制没有设置权重的组件，之后将剩余空间按权重比例分配给其他组件

### 2. RelativeLayout相对布局

* `ignoreGravity`是否不受gravity影响
* `layout_alignParentLeft`是否在RelativeLayout中左对齐
* `layout_alignParentRight`是否在RelativeLayout中右对齐
* `layout_alignParentTop`是否在RelativeLayout中顶部对齐
* `layout_alignParentBottom`是否在RelativeLayout中底部对齐
* `layout_centerHorizontal`是否在RelativeLayout中水平对齐
* `layout_centerVertical`是否在RelativeLayout中垂直对齐
* `layout_centerInParent`是否在RelativeLayout中间位置
* `layout_toLeftOf`在同RelativeLayout中谁的左侧
* `layout_toRightOf`在同RelativeLayout中谁的右侧
* `layout_above`在同RelativeLayout中谁的上方
* `layout_below`在同RelativeLayout中谁的下方
* `layout_alignTop`对其RelativeLayout中谁的上边界
* `layout_alignBottom`对其RelativeLayout中谁的下边界
* `layout_alignLeft`对其RelativeLayout中谁的左边界
* `layout_alignRight`对其RelativeLayout中谁的右边界

### 3. TableLayout表格布局

* 表格布局宽度默认占满父容器，无法修改
* 表格布局可以包含多个TableRow，一个TableRow代表一行数据
* TableRow可以包含多个View，每个组件的宽度由组件本身决定，可以修改为固定值
* TableRow的高度可以修改，默认是自适应`wrap_content`
* 属性（以下三个属性都从0开始的，可以设置多个，用逗号隔开，如要所有列都生效则用`*`）
  * `collapseColumns`需要被隐藏的列的序号
  * `shrinkColumns`需要被收缩的列的序号
  * `stretchColumns`需要被拉伸的列的序号
* `layout_column`跳过第几个，从1开始算
* `layout_span`合并几个单元格

### 4. FrameLayout帧布局

FrameLayout直接在屏幕上开辟出一块空白区域，当向其中添加控件时，会默认把他们放到这个块区域的左上角。这种布局没有任何的定位方式，所以应用不多。帧布局的大小由控件中最大的子控件决定，如果控件大小相同，那同一时刻只能看到最上面的那个组件，后添加的覆盖前一个。虽然默认控件被放置在左上角，但是我们也可以通过`layout_gravity`属性，指定到其他的位置

* `foregroud`布局前景图像，但drawable或mipmap中的图片即可
* `forgrroudGravity`布局前景图像的显示位置，取值同LinearLayout的`gravity`属性

### 5. GridLayout网格布局

Android4.0之后加入的布局，相对于TableLayout：

* 可以自己设置布局中组件的排列方式
* 可以自定义网格布局有多少行多少列
* 可以直接设置组件位于某行某列
* 可以设置组件横跨几行或几列

主要属性

* `orientation`与LinearLayout用法一致
* `gravity`与LinearLayout用法一致
* `rowCount`行数
* `columnCount`列数
* `layout_row`设置该组件位于第几行，从0开始
* `layout_column`设置该组件位于第几列，从0开始
* `layout_rowSpan`行合并
* `layout_columnSpan`列合并

## 三、组件

* TextView

  * dp(dip)：device independent pixels(设备独立像素)。不同设备有不同的显示效果，这个和设备硬件有关，一般用dp可以不依赖像素
  * px: pixels，不同设备显示效果相同，一般HVGA代表320x480像素，这个用的比较多
  * pt: point，是一个标准长度单位，1pt=1/72英寸，用于印刷业
  * sp: scaled pixels(放大像素)，主要用于字体显示，Best for textsize
  * 属性
    * `text`显示的文字
    * `textColor`字体颜色，十六进制值
    * `textSize`字体大小
    * `textStyle`字体样式如bold加粗
    * `maxHeight`最大高度
    * `maxLength`最多显示多少个字
    * `maxWidth`最大宽度
    * `maxLines`最多显示多少行
    * `ellipsize`如果不够显示的处理方式，如`end`是在尾部添加省略号
    * `singleLine`是否单行显示
    * `background`字体背景颜色
    * `shadowColor`字体阴影，需要和`shadowRadius`一起使用
    * `shadowRadius`设置阴影的模糊程度，设为0.1就变成字体颜色了，建议使用3.0
    * `shadowDx`设置阴影在水平方向的偏移
    * `shadowDy`设置阴影在竖直方向的偏移
    * `drawableTop`控件顶部图片显示
    * `drawableBottom`控件底部图片显示
    * `drawableLeft`控件左侧图片显示
    * `drawableRight`控件右侧图片显示
    * `drawablePadding`图片距离文字的间距
    * `autoLink`点击对应文字可以跳转至某默认App，如：一串号码点击后跳转至拨号界面
    * `setText(Html.fromHtml())`在Java代码用此方法设置html代码进行渲染
    * `focusable`是否能获取焦点
    * `focusableInTouchMode`是否可以通过触摸获取焦点
    * `marqueeRepeatLimit`跑马灯的滚动次数，-1代表无限循环
    * `scrollHorizontally`让文字可以水平滑动

* EditText

  * `hint`没有输入文字时的提示文字
  * `textColorHint`提示文字的颜色，默认为㳀灰色
  * `selectAllOnFocus`在点击输入框获得焦点后，不是将光标移动到文本的开始或结尾，而是选中全部文字
  * `inputType`转入限制类型，如手机号、邮箱等
    * `number`数字
    * `numberSigned`带符号数字
    * `numberDecimal`浮点数
    * `phone`手机号码
    * `textEmailAddress`邮箱地址
  * `minLines`最小行数
  * `maxLines`最大行数
  * `singleLines`只允许单行输入
  * `textScaleX`文字的水平放大
  * `capitalize`：`sentences`仅第一个字母大写，`words`每个单词首字母大写，`characters`每一个字母都大写
  * `drawable等属性`

* Button

  Button是TextView的子类，日常开发中对Button的操作，比如：按钮按下/弹起/不可用时间的颜色，都是通过`StateListDrawable*`这种Drawable资源来实现，即编写一个drawable的资源文件

  * `shape`

    * `size`控件宽高
    * `solid`设置背景颜色
    * `corners`设置圆角
    * `stroke`设置边框

* ImageView

  * `src`设置图片
  * `background`设置背景图片，占满组件宽高
  * `scaleType`

    * `fitXY`对图像的横向和纵向进行独立缩放，使得该图片完全适应ImageView，图片的纵横比可能会发生改变
    * `fitStart`保持纵横比缩放图片，直到较长的边与ImageView的边长相等，缩放后图片位于左上角
    * `fitCenter`同上，缩放后位于中间
    * `fitEnd`同上，缩放后位于右下角
    * `center`保持的原图大小，显示在ImageView的中心，原图大于ImageView的部分做裁剪处理
    * `centerCrop`保持纵横比缩放图片，直至**完全覆盖**ImageView，图片可能会显示不完全
    * `centerInside`保持纵横比缩放图片，直到ImageView能够完全地显示图片
    * `matrix`默认值，不改变原图的大小，从左上角开始，原图超出ImageView的部分做裁剪处理

* RadioButton和Checkbox

  * `checked`是否选中
  * `checkBox.isChecked`获取对象的选中状态

* ToggleButton

  * `disableAlpha`禁用时的透明度
  * `textOff`关闭时显示的文字
  * `textOn`打开时显示的文字

* Switch

  * `showText`设置on/off时是否显示文字
  * `splitTrack`是否设置一个间隙，让滑块与底部图片分隔
  * `switchMinWidth`设置开关的最小宽度
  * `switchPadding`设置滑块内文字的间隙
  * `switchTextAppearance`设置开关的文字外观
  * `textOff`关闭时显示的文字
  * `textOn`打开时显示的文字
  * `track`底部的图片
  * `thumb`滑块的图片

* ProgressBar

  * `max`进度条的最大值
  * `progress`当前进度条的进度
  * `progressDrawable`轨道对应的Drawable对象
  * `indeterminate`如果设置为true，则进度条不精确显示进度
  * `indeterminateDrawable`不精确显示进度的进度条的Drawable对外象
  * `indeterminateDuration`不精确显示进度的持续时间
  * `secondaryProgress`二级进度条，类似视频播放的一条是当前播放进度，一条是缓冲进度
  * Java方法

    * `getMax`返回最大进度上限
    * `getProgress`返回主要进度
    * `getSecondaryProgress`返回次要进度
    * `incrementProgressBy`指定增加的进度
    * `isIndeterminate`进度条是否在不确定模式下
    * `setIndeterminate`设置不确定模式

* SeekBar(拖拽条)

  * `max`最大拖拽值
  * `progress`当前进度
  * `secondaryProgress`二级滑动条进度
  * `thumb`滑块的drawable，如：视频播放器是一个小圆圈
  * 事件`setOnSeekBarChangeListener`

    * `onProgressChanged`进度发生改变时触发
    * `onStartTrackingTouch`按下拖拽条时触发
    * `onStopTrackingTouch`释放拖拽条时触发

* RatingBar

  * `isIndicator`是否用作指示，用户无法修改，默认false
  * `numStars`星星的数量，必须为整数
  * `rating`默认评分值
  * `stepSize`拖拽时增加/减少的值，必须为浮点数
  * 事件`setOnRatingBarChangeListener`

* StateListDrawable

  StateListDrawable是Drawable资源的一种，通过XML代码编写，存放在Android项目的res/drawable中，可以根据不同的状态，设置不同的图片效果。这里只需要将Button的background属性设置为drawable资源即可轻松实现按下按钮时不同的颜色和背景。

  * `drawable`控件正常状态显示的图片
  * `state_focused`控件是否获取焦点
  * `state_window_focused`控件是否获得窗口焦点
  * `state_enabled`控件是否可用
  * `state_checkable`控件是否可勾选
  * `state_checked`控件是否选中
  * `state_selected`控件是否被选择
  * `state_pressed`控件是否被按下
  * `state_active`控件是否处于活动状态
  * `state_single`控件包含多个子控件时，确定是否只显示一个子控件
  * `state_first`控件包含多个子控件时，确定第一个子控件是否处于显示状态
  * `state_middle`控件包含多个子控件时，确定中间一个子控件是否处于显示状态
  * `state_last`控件包含多个子控件时，确定最后一个子控件是否处于显示状态

* LayerList

  将多个drawable按照顺序层叠在一起显示，默认情况下，所有的item中的drawable都会自动根据它附上view的大小进行缩放。`layer-list`中的`item`是按照顺序从下往上叠加的，即先定义的item在下面，后面的依次往上面叠放。

* ScrollView

  只接收一个View或ViewGroup做为子组件，不允许嵌套ScrollView，`fullScroll`方法传参实现代码控制滚动

  * `ScrollView.FOCUS_DOWN`滚动到底部
  * `ScrollView.FOCUS_UP`滚动到顶部

* HorizontalScrollView

  水平滚动，只接收一个View或ViewGroup做为子组件，`fullScroll`方法传参实现代码控制滚动

  * `HorizontalScrollView.FOCUS_LEFT`滚动到左侧
  * `HorizontalScrollView.FOCUS_RIGHT`滚动到右侧

* AlertDialog

* ListView

  * `setSelection`当前从第几行开始显示，可以达到跳转效果
  * `addHeaderView`设置顶部View
  * `addFooterView`设置底部View
  * `setOnItemClickListener`点击某一行的事件

  如果ListView子组件可以获取焦点，如选择框，输入框，那么`setOnItemClickListener`将不会生效，解决办法：

  * 给子组件设置不能获取焦点`android:focusable="false"`或setFocusable(false)
  * item根节点设置`android:descendantFocusability`

    * `beforeDescendants` viewGroup会优先其子类控件而获取到焦点
    * `afterDescendants` viewGroup只有当其子类控件不需要获取焦点时才获取焦点
    * `blocksDescendants` viewGroup会覆盖子类控件而直接获得焦点

  * Adapter

    ListView显示内容需要基于adapter，通俗讲ListView每一行显示什么由adapter决定，Adapter分为多种类型

    * BaseAdapter自定义视图
    * ArrayAdapter支持泛型操作，最简单的Adapter，只能展示文字
    * SimpleAdapter同样具有良好扩展性的一个Adapter，可以自定义多种效果

* RecyclerView

  RecyclerView是Android一个更强大的列表控件，其不仅可以实现和ListView同样的效果，而且优化了ListView中的各种不足，可实现横向滚动，用法有些相似

* PopupWindow

  PopupWindow可以指定位置并且可以设置动画，更加灵活

  * `setContentView` 设置显示的View
  * `showAsDropDown` 相对某个控件的位置（正左下方）
  * `showAtLocation` 相对于屏幕的位置（如：正中间Gravity.CENTER）
  * `setWidth`
  * `setHeight`
  * `setAnimationStyle` 设置动画效果

* Spinner

  Spinner是Android官方提供的下拉框控件，默认选中第一行数据

  * `dropDownHorizontalOffset`列表框水平偏移距离
  * `dropDownVerticalOffset`列表框竖直偏移距离
  * `dropDownSelector`列表框被选中时的背景
  * `dropDownWidth`设置下拉列表框的宽度
  * `popupBackground`设置列表框的背景
  * `prompt`设置对话框模式的列表框的提示信息（标题），只能引用strings.xml中的资源id
  * `spinnerMode`
    * `dialog`对话框风格的窗口
    * `dropdown`下拉菜单风格的窗口（默认）
  * `entries`使用数组资源设置下拉列表框的列表项目

* AsyncTask & Handler

* Intent

  Android提供Intent机制来协助应用间交互与通讯，Intent负责对应用中一次操作的动作、动作涉及数据、附加数据进行描述。Android根据此Intent的描述，负责找到对应的组件，将Intent传递给调用的组件，并完成组件的调用。

  Intent不仅可用于应用程序之间，也可以用于应用程序内部的Activity/Service之间的交互。因此，Intent在这里起着一个媒介的作用，专门提供组件互相调用的相关信息，实现调用者与被调用者之间的解耦。

  Intent七大属性，其中最常用的是Action和Data属性

  Action

  * `ACTION_MAIN`表示程序入口
  * `ACTION_VIEW`自动以最合适的方式显示Data
  * `ACTION_EDIT`提供可以编辑的
  * `ACTION_PICK`选择一条Data，并且返回它
  * `ACTION_DAIL`显示Data指向的号码在拨号界面Dailer上
  * `ACTION_CALL`拨打Data指向的号码
  * `ACTION_SEND`发送Data到指定的地方
  * `ACTION_SENDTO`发送多组Data到指定的地方
  * `ACTION_RUN`执行Data
  * `ACTION_SEARCH`搜索
  * `ACTION_WEB_SEARCH`在网上搜索
  * `ACTION_SYNC`指同步一个Data
  * `ACTION_INSERT`添加一个空的项目到容器中

  Data

  * 一个URI对象是一个引用的data的表现形式，或是data的MIME类型；data类型由Intent的action决定

  Category

  * 一个包含Intent额外信息的字符串，表示哪种类型的组件来处理这个Intent。任何数量的Category描述都可以添加到Intent中，但是很多Intent不需要Category

  Type
  
  * 一般Intent的数据类型能够根据数据本身进行判定，但是通过这个属性，可以强制显式指定类型而不再进行推导

  Component

  * 指定Intent的目标组件名称，当指定了这个属性后，系统将跳过匹配其他属性，而直接匹配这个属性来启动对应的组件

  Extra

  * Intent可以携带额外key-value数据，可以通过调用putExtra()方法设置数据，每一个key对应一个value数据。也可以通过创建Bundle对象来存储所有数据，然后通过调用putExtras()方法来设置数据

  Flag

  * 用来指示系统如何启用一个Activity，可以通过setFlags()或addFlags()把标签flag用在Intent中
