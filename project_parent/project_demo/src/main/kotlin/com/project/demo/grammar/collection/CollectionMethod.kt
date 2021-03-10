package com.project.demo.grammar.collection

/**
元素操作符
  contains(元素) : 检查集合中是否包含指定的元素，若存在则返回true，反之返回false
  elementAt(index) : 获取对应下标的元素。若下标越界，会抛出IndexOutOfBoundsException（下标越界）异常，同get(index)一样
  elementAtOrElse(index,{…}) : 获取对应下标的元素。若下标越界，返回默认值，此默认值就是你传入的下标的运算值
  elementAtOrNull(index) : 获取对应下标的元素。若下标越界，返回null
  first() : 获取第一个元素，若集合为空集合，这会抛出NoSuchElementException异常
  first{} : 获取指定元素的第一个元素。若不满足条件，则抛出NoSuchElementException异常
  firstOrNull() : 获取第一个元素，若集合为空集合，返回null
  firstOrNull{} : 获取指定元素的第一个元素。若不满足条件，返回null
  getOrElse(index,{…}) : 同elementAtOrElse一样
  getOrNull(index) : 同elementAtOrNull一样
  last() : 同first()相反
  last{} : 同first{}相反
  lastOrNull{} : 同firstOrNull()相反
  lastOrNull() : 同firstOrNull{}相反
  indexOf(元素) : 返回指定元素的下标，若不存在，则返回-1
  indexOfFirst{…} : 返回第一个满足条件元素的下标，若不存在，则返回-1
  indexOfLast{…} : 返回最后一个满足条件元素的下标，若不存在，则返回-1
  single() : 若集合的长度等于0,则抛出NoSuchElementException异常，若等于1，则返回第一个元素。反之，则抛出IllegalArgumentException异常
  single{} : 找到集合中满足条件的元素，若元素满足条件，则返回该元素。否则会根据不同的条件，抛出异常。这个方法慎用
  singleOrNull() : 若集合的长度等于1,则返回第一个元素。否则，返回null
  singleOrNull{} : 找到集合中满足条件的元素，若元素满足条件，则返回该元素。否则返回null
  forEach{…} : 遍历元素。一般用作元素的打印
  forEachIndexed{index,value} : 遍历元素，可获得集合中元素的下标。一般用作元素以及下标的打印
  componentX() ： 这个函数在前面的章节中提过多次了。用于获取元素。其中的X只能代表1..5。详情可看下面的例子
  find{…} : 找到集合中第一个满足条件的元素，返回值是集合元素类型，可能为null
*/

/**
顺序操作符
  reversed() : 反序。即和初始化的顺序反过来。
  sorted() : 自然升序。
  sortedBy{} : 根据条件升序，即把不满足条件的放在前面，满足条件的放在后面
  sortedDescending() : 自然降序。
  sortedByDescending{} : 根据条件降序。和sortedBy{}相反
*/

/**
映射操作符
  map{…} : 把每个元素按照特定的方法进行转换，组成一个新的集合。
  mapNotNull{…} : 同map{}函数的作用相同，只是过滤掉转换之后为null的元素
  mapIndexed{index,result} : 把每个元素按照特定的方法进行转换，只是其可以操作元素的下标(index)，组成一个新的集合。
  mapIndexedNotNull{index,result} : 同mapIndexed{}函数的作用相同，只是过滤掉转换之后为null的元素
  flatMap{…} : 根据条件合并两个集合，组成一个新的集合。
  groupBy{…} : 分组。即根据条件把集合拆分为为一个Map&gt;类型的集合。具体看实例
*/

/**
过滤操作符
  filter{…} : 把不满足条件的元素过滤掉
  filterIndexed{…} : 和filter{}函数作用类似，只是可以操作集合中元素的下标（index）
  filterNot{…} : 和filter{}函数的作用相反
  filterNotNull() : 过滤掉集合中为null的元素。
  take(num) : 返回集合中前num个元素组成的集合
  takeWhile{…} : 循环遍历集合，从第一个元素开始遍历集合，当第一个出现不满足条件元素的时候，退出遍历。然后把满足条件所有元素组成的集合返回。
  takeLast(num) : 返回集合中后num个元素组成的集合
  takeLastWhile{…} : 循环遍历集合，从最后一个元素开始遍历集合，当第一个出现不满足条件元素的时候，退出遍历。然后把满足条件所有元素组成的集合返回。
  drop(num) : 过滤集合中前num个元素
  dropWhile{…} : 相同条件下，和执行takeWhile{…}函数后得到的结果相反
  dropLast(num) : 过滤集合中后num个元素
  dropLastWhile{…} : 相同条件下，和执行takeLastWhile{…}函数后得到的结果相反
  distinct() : 去除重复元素
  distinctBy{…} : 根据操作元素后的结果去除重复元素
  slice : 过滤掉所有不满足执行下标的元素。
*/

/**
生产操作符
  plus() : 合并两个集合中的元素，组成一个新的集合。也可以使用符号+
  zip : 由两个集合按照相同的下标组成一个新集合。该新集合的类型是：List
  unzip : 和zip的作用相反。把一个类型为List的集合拆分为两个集合。看下面的例子
  partition : 判断元素是否满足条件把集合拆分为由两个Pair组成的新集合。
*/

/**
统计操作符
  any() : 判断是不是一个集合，若是，则在判断集合是否为空，若为空则返回false,反之返回true,若不是集合，则返回hasNext
  any{…} : 判断集合中是否存在满足条件的元素。若存在则返回true,反之返回false
  all{…} : 判断集合中的所有元素是否都满足条件。若是则返回true,反之则返回false
  none() : 和any()函数的作用相反
  none{…} : 和all{…}函数的作用相反
  max() : 获取集合中最大的元素，若为空元素集合，则返回null
  maxBy{…} : 获取方法处理后返回结果最大值对应那个元素的初始值，如果没有则返回null
  min() : 获取集合中最小的元素，若为空元素集合，则返回null
  minBy{…} : 获取方法处理后返回结果最小值对应那个元素的初始值，如果没有则返回null
  sum() : 计算出集合元素累加的结果。
  sumBy{…} : 根据元素运算操作后的结果，然后根据这个结果计算出累加的值。
  sumByDouble{…} : 和sumBy{}相似，不过sumBy{}是操作Int类型数据，而sumByDouble{}操作的是Double类型数据
  average() : 获取平均数
  reduce{…} : 从集合中的第一项到最后一项的累计操作。
  reduceIndexed{…} : 和reduce{}作用相同，只是其可以操作元素的下标(index)
  reduceRight{…} : 从集合中的最后一项到第一项的累计操作。
  reduceRightIndexed{…} : 和reduceRight{}作用相同，只是其可以操作元素的下标(index)
  fold{…} : 和reduce{}类似，但是fold{}有一个初始值
  foldIndexed{…} : 和reduceIndexed{}类似，但是foldIndexed{}有一个初始值
  foldRight{…} : 和reduceRight{}类似，但是foldRight{}有一个初始值
  foldRightIndexed{…} : 和reduceRightIndexed{}类似，但是foldRightIndexed{}有一个初始值
*/

class CollectionMethod {
}