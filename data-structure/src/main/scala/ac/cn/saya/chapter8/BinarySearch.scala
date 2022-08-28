package ac.cn.saya.chapter8

import scala.collection.mutable.ArrayBuffer
import util.control.Breaks._
/**
  * @Title: BInarySearch
  * @ProjectName ScalaStudy
  * @Description: TODO
  * @Author liunengkai
  * @Date: 2019-04-12 20:33
  * @Description:
  *              二分查找(建立在有序的数组基础上)
  */
object BinarySearch {

  def main(args: Array[String]): Unit = {
//    val arr = Array(1,8,10,89,1000,1234)
//    val index = binarySearch1(arr,0,arr.length-1,89)
//    if(index == -1){
//      println("未找到")
//    }else{
//      println("找到，下标为：${index}")
//    }

    val arr = Array(1,8,10,89,1000,1000,1000,1000,1234)
    val index = binarySearch2(arr,0,arr.length-1,1000)
    if(index.length <= 0){
      println("未找到")
    }else{
      println("找到:"+index.mkString(" "))
    }

  }

  /**
    * 二分查找不支持查找重复
    * @param arr 原数组
    * @param l 左边的索引 下界
    * @param r 右边的索引 上界
    * @param findVal 待查找的值
    * @return
    *         1、先找中间的值
    *         2、然后将中间的值和查找的值进行比较
    *         2，1 查找的值小于中间的值，在左边找
    *         2，2 查找的值大于中间的值，在右边找
    *         找不到返回-1
    */
  def binarySearch1(arr:Array[Int],l:Int,r:Int,findVal:Int):Int={
      if(l > r){
        return -1
      }
      val midIndex = (l+r)/2
      val midVal = arr(midIndex)
      if(findVal < midVal){
        binarySearch1(arr,l,midIndex-1,findVal)
      } else if(findVal > midVal){
        binarySearch1(arr,midIndex+1,r,findVal)
      }else{
        return midIndex
      }
  }

  /**
    * 二分查找支持查找重复
    * @param arr 原数组
    * @param l 左边的索引 下界
    * @param r 右边的索引 上界
    * @param findVal 待查找的值
    * @return
    *         1、先找中间的值
    *         2、然后将中间的值和查找的值进行比较
    *         2，1 查找的值小于中间的值，在左边找
    *         2，2 查找的值大于中间的值，在右边找
    *         找不到返回-1
    *         3、找到后，向左和右再次查找，判断是否有重复值
    */
  def binarySearch2(arr:Array[Int],l:Int,r:Int,findVal:Int):ArrayBuffer[Int]={
    if(l > r){
      return ArrayBuffer()
    }
    val midIndex = (l+r)/2
    val midVal = arr(midIndex)
    if(findVal < midVal){
      binarySearch2(arr,l,midIndex-1,findVal)
    } else if(findVal > midVal){
      binarySearch2(arr,midIndex+1,r,findVal)
    }else{
      // 定义一个可变数组
      val result = new ArrayBuffer[Int]()
      // 向左边查找
      var temp = midIndex - 1
      breakable{
        while (true){
          if(temp < 0 || arr(temp) != findVal){
            break()
          }
          if(arr(temp) == findVal){
            result.append(temp)
          }
          temp -= 1
        }
      }
      // 放入中间找到的索引
      result.append(midIndex)
      // 向右查找
      temp = midIndex + 1
      breakable{
        while (true){
          if(temp > arr.length || arr(temp) != findVal){
            break()
          }
          if(arr(temp) == findVal){
            result.append(temp)
          }
          temp += 1
        }
      }
      return result
    }
  }

}
