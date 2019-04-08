package ac.cn.saya.chapter7

import java.text.SimpleDateFormat
import java.util.Date

/**
  * @Title: SelectSort
  * @ProjectName ScalaStudy
  * @Description: TODO
  * @Author liunengkai
  * @Date: 2019-04-08 21:59
  * @Description:
  *              选择排序
  */
object SelectSort {

  def main(args: Array[String]): Unit = {
    val random = new util.Random()
    val arr = new Array[Int](80000)
    for(i <- 0 until 80000){
      arr(i) = random.nextInt(800000)
    }
    val first: Date = new Date()
    val dateFormat:SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    println("排序开始时间："+dateFormat.format(first))
    selectSort(arr)
    val end: Date = new Date()
    println("排序结束时间："+dateFormat.format(end))

  }

  def selectSort(arr:Array[Int]):Unit={
    for(i <- 0 until arr.length -1){
      var min = arr(i)
      var mainIndex = 0
      for(j <- (i+1) until arr.length){
        if(min < arr(j)){
          min = arr(j)
          mainIndex = j
        }
      }
      //判断是否需要交换
      if(mainIndex != i){
        arr(mainIndex) = arr(i)
        arr(i) = min
      }
    }
  }

}
