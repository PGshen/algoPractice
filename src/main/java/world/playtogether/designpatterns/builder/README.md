创建型模式总结
创建型模式总体上比较简单，它们的作用就是为了产生实例对象，算是各种工作的第一步了，因为我们写的是面向对象的代码，所以我们第一步当然是需要创建一个对象了。

- 简单工厂模式最简单；
- 工厂模式在简单工厂模式的基础上增加了选择工厂的维度，需要第一步选择合适的工厂；
- 抽象工厂模式有产品族的概念，如果各个产品是存在兼容性问题的，就要用抽象工厂模式。
- 单例模式就不说了，为了保证全局使用的是同一对象，一方面是安全性考虑，一方面是为了节省资源；
- 建造者模式专门对付属性很多的那种类，为了让代码更优美；
- 原型模式用得最少，了解和 Object 类中的 clone() 方法相关的知识即可。

> 来自 [设计模式](https://javadoop.com/post/design-pattern)