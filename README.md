# lambda 系列学习 
## 函数式接口
#### 定义: 只有一个抽象方法的接口是函数式接口
### JDK 1.8 新增加的函数接口
###  (1)BiConsumer<T,U>             接受两个输入参数，无返回值
###  (2)BiFunction<T,U,R>           接受两个输入参数，有返回值（输入输出一般不同类型）
###  (3)BinaryOperator<T>           输入两个同类型参数，返回相同参数类型  （输入输出必须相同类型）
####  (4)BiPredicate<T,U>           两个参数的boolean值方法
####  (5)BooleanSupplier            boolean值结果的提供方
###  (6)Consumer<T>                 无返回值入参（入参为Object）
####  (7)DoubleBinaryOperator       两个double值操作符的操作，返回一个double值的结果
####  (8)DoubleConsumer             无返回值入参（入参为Double）
####  (9)DoubleFunction<R>          接受一个double值参数的方法，并且返回结果
####  (10)DoublePredicate           一个拥有double值参数的boolean值方法
####  (11)DoubleSupplier            代表一个double值结构的提供方
####  (12)DoubleToIntFunction       接受一个double类型输入，返回一个int类型结果
####  (13)DoubleToLongFunction      接受一个double类型输入，返回一个long类型结果
####  (14)DoubleUnaryOperator       接受一个参数同为类型double,返回值类型也为double 
###   (15)Function<T,R>             接受一个输入参数，返回一个结果
####  (16)IntBinaryOperator         接受两个参数同为类型int,返回值类型也为int 
####  (17)IntConsumer               接受一个int类型的输入参数，无返回值 
####  (18)IntFunction<R>            接受一个int类型输入参数，返回一个结果 
####  (19)IntPredicate              接受一个int输入参数，返回一个布尔值的结果
####  (20)IntSupplier               无参数，返回一个int类型结果
####  (21)IntToDoubleFunction        接受一个int类型输入，返回一个double类型结果 
####  (22)IntToLongFunction          接受一个int类型输入，返回一个long类型结果
####  (23)IntUnaryOperator           接受一个参数同为类型int,返回值类型也为int 
####  (24)LongBinaryOperator         接受两个参数同为类型long,返回值类型也为long
####  (25)LongConsumer               接受一个long类型的输入参数，无返回值
####  (26)LongFunction<R>            接受一个long类型输入参数，返回一个结果
####  (27)LongPredicate              R接受一个long输入参数，返回一个布尔值类型结果
####  (28)LongSupplier               无参数，返回一个结果long类型的值
####  (29)LongToDoubleFunction       接受一个long类型输入，返回一个double类型结果
####  (30)LongToIntFunction          接受一个long类型输入，返回一个int类型结果
####  (31)LongUnaryOperator          接受一个参数同为类型long,返回值类型也为long
####  (32)ObjDoubleConsumer<T>       接受一个object类型和一个double类型的输入参数，无返回值
####  (33)ObjIntConsumer<T>          接受一个object类型和一个int类型的输入参数，无返回值
####  (34)ObjLongConsumer<T>         接受一个object类型和一个long类型的输入参数，无返回值
####  (35)Predicate<T>               接受一个输入参数，返回一个布尔值结果
####  (36)ToDoubleFunction<T>        接受一个输入参数，返回一个double类型结果
####  (37)ToIntBiFunction<T,U>       接受两个输入参数，返回一个int类型结果
####  (38)ToIntFunction<T>           接受一个输入参数，返回一个int类型结果
####  (39)Supplier<T>                无参数，返回一个结果
####  (40)ToDoubleBiFunction<T,U>    接受两个输入参数，返回一个double类型结果
####  (41)ToLongBiFunction<T,U>      接受两个输入参数，返回一个long类型结果
####  (42)ToLongFunction<T>          接受一个输入参数，返回一个long类型结果
###   (43)UnaryOperator<T>           接受一个参数为类型T,返回值类型也为T

## Stream 流学习

### Stream 流 处理数组、集合的Api，不是数据机构，没有内部存储，不支持索引访问，具有延迟计算并支持并行、过滤、查找、转换、聚合等系列操作
###   Stream 分为 源 source（数组、集合、生成器方法、I/O流通道等）、中间操作（filter过滤、distinct去重、sorted排序、limit、skip截取、转换map/flatMao/peek其它）、终止操作（forEach循环、min/max/count/average计算、anyMatch/allMatch/noneMatch/findFirst/findAny匹配、reduce汇聚、toArray/collect收集器）
###   Stream 流可以有n（n>=0）个中间操作、1个终止操作。
