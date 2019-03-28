package ac.cn.saya.chapter5

/**
  * @Title: Calculator
  * @ProjectName ScalaStudy
  * @Description: TODO
  * @Author liunengkai
  * @Date: 2019-03-28 21:41
  * @Description:
  *              堆栈计算器
  */
object Calculator {

}

class ArrayStackUtils(size:Int){
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

  // 判断优先级
  def priority(oper:Int):Int={
    if(oper == "*" || oper == "/"){
      return 1
    }else if(oper == "+" || oper == "-"){
      return 0
    }else{
      return -1
    }
  }

  def cal(num1:Int,num2:Int,oper:Int):Int={
    var res = 0
    oper match {
      case '+' =>{
        res = num1+num2
      }
      case '-' =>{
        res = num2 - num1
      }
      case '*' =>{
        res = num1 * num2
      }
      case '/' =>{
        res = num2 / num1
      }
    }
    res
  }

}
