package ac.cn.saya.chapter7

import java.text.SimpleDateFormat
import java.util.Date

/**
  * @Title: InsertSort
  * @ProjectName ScalaStudy
  * @Description: TODO
  * @Author liunengkai
  * @Date: 2019-04-09 21:45
  * @Description:
  *              插入排序
  */
object InsertSort {

  def main(args: Array[String]): Unit = {
    val random = new util.Random()
    val arr = new Array[Int](80000)
    //val arr = Array(101,34,119,1)
    for(i <- 0 until 80000){
      arr(i) = random.nextInt(800000)
    }
    val first: Date = new Date()
    val dateFormat:SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    println("排序开始时间："+dateFormat.format(first))
    InsertSort(arr)
    val end: Date = new Date()
    println("排序结束时间："+dateFormat.format(end))

  }

  def InsertSort(arr:Array[Int]):Unit={
    for(i <- 1 until arr.length){
      var insertVal = arr(i)
      // 表示有序表的最后那个元素的索引
      var sortIndex = i-1
      while (sortIndex >= 0 && insertVal < arr(sortIndex)){
        // 把大的元素后移
        arr(sortIndex+1)=arr(sortIndex)
        sortIndex -= 1
      }
      // 退出，说明插入的位置找到了
      arr(sortIndex+1) = insertVal
    }
    //println(arr.mkString(" "))
  }

}
