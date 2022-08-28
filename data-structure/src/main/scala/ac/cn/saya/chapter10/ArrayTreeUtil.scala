package ac.cn.saya.chapter10

/**
  * @Title: ArrayTreeUtil
  * @ProjectName ScalaStudy
  * @Description: TODO
  * @Author liunengkai
  * @Date: 2019-04-19 21:03
  * @Description:
  */
object ArrayTreeUtil {

  def main(args: Array[String]): Unit = {
    val arr = Array(1,2,3,4,5,6,7)
    val arrayTree = new ArrayTree(arr)
    arrayTree.preOrder()
  }

}

class ArrayTree(val arr:Array[Int]){

  def preOrder():Unit={
    preOrder(0)
  }

  def preOrder(index:Int):Unit={
    if(arr == null || arr.length == 0){
      println("数组为空，不能按照二叉树进行打印")
    }
    println(arr(index))
    // 向左遍历
    if((index*2+1)<arr.length){
      preOrder(index*2+1)
    }
    // 向右遍历
    if((index*2+2)<arr.length){
      preOrder(index*2+2)
    }
  }

}
