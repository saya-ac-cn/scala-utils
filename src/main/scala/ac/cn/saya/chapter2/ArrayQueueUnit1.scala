package ac.cn.saya.chapter2

import scala.io.StdIn

/**
  * @Title: ArrayQueueUnit1
  * @ProjectName ScalaStudy
  * @Description: TODO
  * @Author liunengkai
  * @Date: 2019-03-18 20:53
  * @Description:
  * 队列的相关操作
  * 队列的特征：
  * 先进先出
  */
object ArrayQueueUnit1 {

  def main(args: Array[String]): Unit = {
    // 初始化一个队列
    val queue = new ArrayQueue(3)
    // 保存用户输入的指令
    var keyWord = ""
    while (true){
      println("show：显示队列")
      println("add：加入元素")
      println("get：取出元素")
      println("head：取出头元素")
      println("exit：退出程序")

      keyWord = StdIn.readLine()
      keyWord match {
        case "show" => queue.showQueue()
        case "add" => {
          println("请输入一个数")
          val value = StdIn.readInt()
          queue.addQueue(value)
        }
        case "get" => {
          val res = queue.getQueue()
          if(res.isInstanceOf[Exception]){
            println(res.asInstanceOf[Exception].getMessage)
          }else{
            println("取出的数据是：" + res)
          }
        }
        case "head" => {
          val res = queue.getHead()
          if(res.isInstanceOf[Exception]){
            println(res.asInstanceOf[Exception].getMessage)
          }else{
            println("取出的数据是：" + res)
          }
        }
        case "exit" => System.exit(0)
      }
    }
  }

}

// 使用数组模拟队列
class ArrayQueue(ArrayMaxSize:Int){
  // 初始化队列的相关信息
  // 队列的大小
  val maxSize = ArrayMaxSize;
  // 存放数据的数组，模拟队列
  val arr = new Array[Int](maxSize);
  // 指向队列的头部，注意这里定义的是指向队列的前一个位置
  var front = -1;
  // 指向队列的尾部，注意这里定义的是指向队列的而最后一个数据的位置（含）
  var rear = -1;

  // 判断队列是否已满
  def isFull():Boolean = {
    rear == (maxSize-1)
  }

  // 判断队列是否为空
  def isEmpty():Boolean = {
    rear == front
  }

  // 显示队列
  def showQueue():Unit = {
    if(isEmpty()){
      println("当前队列为空，无法打印")
      return
    }
    for(i <- front+1 to rear){
      printf("arr[%d]=%d\n", i, arr(i))
    }
  }

  // 加入队列
  def addQueue(value:Int):Unit={
    // 判断是否已满
    if(isFull()){
      println("队列已满")
      return
    }
    // 先移动尾部指针
    rear += 1
    arr(rear) = value
  }

  // 退出队列
  def getQueue():Any={
    // 判断队列是否为空
    if (isEmpty()){
      return new Exception("队列为空")
    }
    front += 1
    return arr(front)
  }

  // 取出队列头元素
  def getHead():Any={
    // 判断队列是否为空
    if (isEmpty()){
      return new Exception("队列为空")
    }
    return arr(front+1)
  }

}