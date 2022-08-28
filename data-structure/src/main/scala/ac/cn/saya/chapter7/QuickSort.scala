package ac.cn.saya.chapter7

import java.text.SimpleDateFormat
import java.util.Date

import util.control.Breaks._

/**
  * @Title: QuickSort
  * @ProjectName ScalaStudy
  * @Description: TODO
  * @Author liunengkai
  * @Date: 2019-04-10 21:15
  * @Description:
  *              快速排序
  */
object QuickSort {

  def main(args: Array[String]): Unit = {
    val random = new util.Random()
    val arr = new Array[Int](80000)
    //val arr = Array(101,34,119,1,1,23,34)
    for(i <- 0 until 80000){
      arr(i) = random.nextInt(800000)
    }
    val first: Date = new Date()
    val dateFormat:SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    println("排序开始时间："+dateFormat.format(first))
    quickSort(0,arr.length-1, arr)
    val end: Date = new Date()
    println("排序结束时间："+dateFormat.format(end))
    //println(arr.mkString(" "))
  }

  /**
    * 快速排序
    * @param left 左边开始位置
    * @param right 右边开始位置
    * @param arr
    */
  def quickSort(left:Int, right:Int, arr:Array[Int]):Unit={
    // 左下标
    var l = left
    // 右下标
    var r = right
    // 以中间值作为分割依据
    var pivot = arr((left+right)/2)
    var temp  = 0
    breakable {
      // while 语句 把比pivot 小的数放左边，比pivot大的放右边
      while (l < r){
        // 从左边找一个比pivot大的值，对应下标
        while (arr(l)<pivot){
          l += 1
        }
        // 从右边找一个比pivot小的值，对应下标
        while (arr(r)>pivot){
          r -= 1
        }
        // 本轮循环结束
        if(l >= r){
          break()
        }
        var temp = arr(l)
        arr(l) = arr(r)
        arr(r) = temp
        if(arr(l)==pivot){
          r -= 1
        }
        if(arr(r) == pivot){
          l += 1
        }
      }
    }
    if(l == r){
      l += 1
      r -= 1
    }
    // 左右递归
    if(left < r)
      quickSort(left,r,arr)
    if(right > l)
      quickSort(l,right,arr)
  }


}
