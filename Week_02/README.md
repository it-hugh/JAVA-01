
### 1.堆内存
jvm中的堆内存主要用来存储java对象实例以及数组，堆内存分为年轻代（Young Generation）、老年代（Old Generation）。

其中年轻代又分为Eden和Survivor区。Survivor区由FromSpace和ToSpace组成。Eden区占大容量，Survivor两个区占小容量，默认比例是8:1:1（注意自适应参数带来的影响）。

老年代主要存放新生代多次gc后存活的对象，一般情况下发生gc的频率较低，gc回收过程较慢。

可以看出通过分代划分堆内存区域，可以有效的减少长时间存活对象的扫描与gc，并针对年轻代与老年代使用不同的gc策略，使堆内存的使用与清理更加高效。

### 2.GC
【young gc】对象创建后在 eden 分配内存，当 eden 区内存无法为一个新对象分配内存时，就会触发年轻代的gc，此时，eden区中所有存活的对象都会被复制到to 区，from区中，存活的对象会根据年龄决定去向。年龄设置的阀值时，对象将会被移动到老年代中，年龄未达到阈值的对象将会被复制到to区，当to区空间不足，无法接收所有对象时，部分对象也会直接进入老年代。

【full gc】当老年代空间不足时将会触发fullgc。
##### Serial GC: 
单线程，进行垃圾回收时会进入stw状态。

##### Parallel GC：
 在JDK8等版本中，是server模式JVM的默认GC选择，多线程，stw时间相对串行较短，适合对吞吐要求较高的场景。

##### CMS GC :
多线程并发标记和清除，将垃圾回收过程分为初始标记、并发标记、并发预清理、最终标记、并发清除、并发重置，仅有很小的暂停时间（初始标记、最终标记），因此适用于对响应时间要求较高的场景。
 
##### G1 GC：
划分多个内存区域做增量整理和回收，垃圾回收过程有初始标记 、 Root 区扫描、并发标记、再次标记 、清理等阶段，伴随极少暂停时间（再次标记 、清理）。 某些情况下G1会退化使用Serial收集器来完成垃圾的清理工作，它仅仅使用单线程来完成 GC 工作，GC 暂停时间将达到秒级别的。

##### ZGC: 
通过着色指针和读屏障，实现几乎全部的并发执行，几毫秒级别的延迟，线性可扩展。

### 3.测试与思考
使用GCLogAnasis执行1s测试结果如下：

GC | xmx大小|生成对象次数
---|---|---
SerialGC | 512m| 9000
ParallelGC | 512m| 7900
CmsGC | 512m| 9500
G1GC | 512m| 9500
SerialGC | 1g| 11000
ParallelGC | 1g| 13000
CmsGC | 1g| 13000
G1GC | 1g| 11500
SerialGC | 2g| 11000
ParallelGC | 2g| 14000
CmsGC | 2g| 12000
G1GC | 2g| 12500
SerialGC | 4g| 9200
ParallelGC | 4g| 12000
CmsGC | 4g| 11500
G1GC | 4g| 13500

> 注：在256m下进行测试大部分程序运行后均发生oom，所以可以看出堆内存配置的太小是不行的。

经过测试发现，在堆内存较小时，young gc与full gc发生次数较多，1s的执行时间进行频繁gc执行业务执行效率降低。


随着内存的增大，young gc与full gc发生的频率降低，但是并非内存越大，效率就越高。由数据可以看出，几种gc在1g、2g内存条件下，生成对象次数普遍较多，但是再上升到4g内存，次数并没有升高有的甚至剑帝，原因在于内存越大，每次gc所需要清理的内存就越大，gc时间相应增大，因此1s内gc占据时间比例标高造成生成次数减少。所以说堆内存也不是越大就越好，在更多的情况下我们要追求资源与效率的平衡，通过测试来找个一个平衡点，才是最佳的配置。

那么如何选择gc呢，我认为串行gc来说，目前使用的场景已经不多，在业务吞吐要求较高、响应时间要求不高的情况下可以使用并行gc，在业务响应时间要求较高的情况下，选择cms gc或者g1 gc,尤其是当内存较大时，使用G1效果较明显，从测试数据中就可以体会到，4g内存时G1生成对象次数有明显提高。如果追求更极致的低延迟就需要研究研究zgc了,同时也需要注意内存相关配置能否发挥其优势。