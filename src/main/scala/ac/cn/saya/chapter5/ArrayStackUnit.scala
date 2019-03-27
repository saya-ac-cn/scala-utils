package ac.cn.saya.chapter5

import scala.io.StdIn

/**
  * @Title: ArrayStackUnit
  * @ProjectName ScalaStudy
  * @Description: TODO
  * @Author liunengkai
  * @Date: 2019-03-27 20:45
  * @Description:
  *              栈相关的操作
  */
object ArrayStackUnit {

  def main(args: Array[String]): Unit = {
    val arrayStack = new ArrayStack(4)
    // 保存用户输入的指令
    var keyWord = ""
    while (true){
      println("show：显示栈")
      println("push：加入元素")
      println("pop：取出元素")
      println("exit：退出程序")

      keyWord = StdIn.readLine()
      keyWord match {
        case "show" => arrayStack.list()
        case "push" => {
          println("请输入一个数")
          val value = StdIn.readInt()
          arrayStack.push(value)
        }
        case "pop" => {
          val res = arrayStack.pop()
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

class ArrayStack(size:Int){
  // 栈的深度
  val maxSize = size
  var stack = new Array[Int](maxSize)
  // 栈顶初始为-1
  var top = -1

  // 栈满
  def isFull():Boolean={
    top == maxSize - 1
  }

  // 栈空
  def isEmpty():Boolean={
    top == -1
  }

  // 入栈
  def push(value:Int):Unit={
    if(isFull()){
      println("栈满")
      return
    }
    top += 1
    stack(top) = value
  }

  // 出栈
  def pop():Any={
    if(isEmpty()){
      return new Exception("栈空")
    }
    val value = stack(top)
    top -= 1
    return value
  }

  // 遍历栈
  def list():Unit={
    if(isEmpty()){
      println("栈中没有数据")
      return
    }
    for(i <- 0 to top reverse){
      printf("stack[%d]=%d\n",i,stack(i))
    }
  }

}
