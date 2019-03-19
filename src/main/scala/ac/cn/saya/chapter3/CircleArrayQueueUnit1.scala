package ac.cn.saya.chapter3

import scala.io.StdIn

/**
  * @Title: CircleArrayQueue
  * @ProjectName ScalaStudy
  * @Description: TODO
  * @Author liunengkai
  * @Date: 2019-03-19 21:25
  * @Description:
  *              环形队列->此法会浪费一个位置
  */
object CircleArrayQueueUnit1 {
  def main(args: Array[String]): Unit = {
    // 初始化一个队列
    val queue = new CircleArrayQueue(4)
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

class CircleArrayQueue(ArrayMaxSize:Int){
  // 初始化队列的相关信息
  // 队列的大小
  val maxSize = ArrayMaxSize;
  // 存放数据的数组，模拟队列
  val arr = new Array[Int](maxSize);
  /*  指向队列的头部，注意这里定义的是指向队列的前一个位置（不含）
   *  0     1     2     3
   *  front
   *  指针
   *  取出后后移
   */
  var front = 0;
  /*  指向队列的尾部，注意这里定义的是指向队列的最后一个进入的元素的下一个位置
   *  0     1     2     3
   *              rear
   *                    指针
   *  添加后后移
   */
  var rear = 0;

  // 判断队列满的方法
  def isFull():Boolean = {
    (rear + 1) % maxSize == front
    //(rear % maxSize == front) && arr(rear) != 0
  }

  // 判断队列空的方法
  def isEmpty():Boolean={
    front == rear
  }

  // 添加数据到队列
  def addQueue(value:Int):Unit={
    // 判断队列是否已满
    if(isFull()){
      println("队列已满无法加入")
      return
    }
    // 将数据加入
    arr(rear) = value
    // 将rear后移，这里必须考虑取模
    rear = (rear + 1) % maxSize
  }

  // 退出队列
  def getQueue():Any={
    // 判断队列是否为空
    if (isEmpty()){
      return new Exception("队列为空")
    }
    val value = arr(front)
    front = (front + 1) % maxSize
    return value
  }

  // 显示队列
  def showQueue():Unit = {
    if(isEmpty()){
      println("当前队列为空，无法打印")
      return
    }
    // until不包含
    for(i <- front until front + maxSize){
      printf("arr[%d]=%d\n", i % maxSize, arr(i % maxSize))
    }
  }

  // 求出当前队列有几个元素
  def size():Int = {
    (rear + maxSize - front) % maxSize
  }

  // 取出队列头元素
  def getHead():Any={
    // 判断队列是否为空
    if (isEmpty()){
      return new Exception("队列为空")
    }
    return arr(front)
  }


}
