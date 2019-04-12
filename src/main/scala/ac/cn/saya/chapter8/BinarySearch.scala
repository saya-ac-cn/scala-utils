package ac.cn.saya.chapter8

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
    val arr = Array(1,8,10,89,1000,1234)
    val index = binarySearch(arr,0,arr.length-1,89)
    if(index == -1){
      println("未找到")
    }else{
      println(s"找到，下标为：${index}")
    }
  }

  /**
    * 二分查找
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
  def binarySearch(arr:Array[Int],l:Int,r:Int,findVal:Int):Int={
      if(l > r){
        return -1
      }
      val midIndex = (l+r)/2
      val midVal = arr(midIndex)
      if(findVal < midVal){
        binarySearch(arr,l,midIndex-1,findVal)
      } else if(findVal > midVal){
        binarySearch(arr,midIndex+1,r,findVal)
      }else{
        return midIndex
      }
  }

}
