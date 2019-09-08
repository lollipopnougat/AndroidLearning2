# AndroidLearning2

# Android开发上机作业2

## 记录

* 要求似乎很难懂的样子
* 又踩坑(`double` 的精度问题), 最后换成 `BigDecimal` 类计算解决
* `Intent` 类的使用和 `component` 之间的通信
* `Toast` 第一个参数要求传递一个 `Context` 对象, 经过测试发现 `this` 和 `getApplicationContext()` 在主类中使用时有点区别.
    * 如果在Google Pixel 2XL(x86 VM)上运行看起来一切正常
    * 如果在真机上(Honor V9 `HUAWEI DUK-AL20`)上, 在子 `Activity` 中主类里传递 `this`, 显示的 `Toast` 会丢失Padding(原因不明), 而使用 `getApplicationContext()` 就不会受影响
