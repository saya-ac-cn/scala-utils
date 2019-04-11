package ac.cn.saya.chapter7

import java.text.SimpleDateFormat
import java.util.Date

/**
  * @Title: MergeSort
  * @ProjectName ScalaStudy
  * @Description: TODO
  * @Author liunengkai
  * @Date: 2019-04-11 20:52
  * @Description:
  *              归并排序
  */
object MergeSort {

  def main(args: Array[String]): Unit = {
    val random = new util.Random()
    val arr = new Array[Int](80000)
    //val arr = Array(101,34,119,1,1,23,34)
    for(i <- 0 until 80000){
      arr(i) = random.nextInt(800000)
    }
    val first: Date = new Date()
    val dateFormat:SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    var temp = new Array[Int](arr.length)
    println("排序开始时间："+dateFormat.format(first))
    mergeSort(arr,0,arr.length-1, temp)
    val end: Date = new Date()
    println("排序结束时间："+dateFormat.format(end))
    //println(arr.mkString(" "))
  }

  /**
    * 归并排序
    * @param arr 待排序的数组
    * @param left 最初的左边下标0
    * @param right 最初的右边下标 length-1
    * @param temp 临时数组，大小和arr一样大
    */
  def mergeSort(arr:Array[Int],left:Int,right:Int,temp:Array[Int]):Unit={
    if(left < right){
      val mid = (left + right)/2
      mergeSort(arr,left,mid,temp)
      mergeSort(arr,mid+1,right,temp)
      // 合并排序
      merge(arr,left,mid,right,temp)
    }
  }

  def merge(arr:Array[Int],left: Int,mid:Int,right: Int,temp:Array[Int]): Unit ={
    // 左边的索引
    var i = left
    // 右边的开始索引
    var j = mid + 1
    // 临时数组的索引
    var t = 0
    while(i <= mid && j <= right){
      if(arr(i) <= arr(j)){
        temp(t) = arr(i)
        i += 1
      }else{
        temp(t) = arr(j)
        j += 1
      }
      t += 1
    }
    // 防止右边的全部都放完，而左边存在没放完，此时把左边的全部继续放入
    while(i <= mid){
      temp(t) = arr(i)
      t += 1
      i += 1
    }
    while (j <= right){
      temp(t) = arr(j)
      t += 1
      j +=1
    }
    // 将本轮排好序的数组放入到原来的arr中
    t = 0
    var tempLeft = left
    while (tempLeft <= right){
      arr(tempLeft) = temp(t)
      t += 1
      tempLeft += 1
    }
  }

}
