package ac.cn.saya.chapter5

import util.control.Breaks._

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

  def main(args: Array[String]): Unit = {
    val expression = "3+2*6-2"
    // 数栈
    val numStack = new ArrayStackUtils(10)
    // 符号栈
    val operStack = new ArrayStackUtils(10)

    /**
      * 1、设计两个栈，数栈和符号栈
      * 2、对表达式进行扫描，一个一个的取出
      * 3、当取出的字符是数时，就直接入数栈
      * 4、当取出的字符是符号时
      * 4.1 如果当前符号栈没有数据时，直接入栈
      * 4.2 如果当前符号的优先级小于等于符号栈顶的优先级时，则取出该符号，并从数栈依次pop出
      * 两个数据，进行运算，将数据重新push到数栈，并将当前符号push到符号栈
      * 4.3 反之，符号直接入符号栈
      * 5、当整个表达式扫描完毕后，依次从数栈和符号栈取出数据，进行运行，最后在数栈中的数据就是结果
      */

    var index = 0
    var num1, num2 = 0
    var oper = 0
    var res = 0
    var ch = ' '

    // 循环取出exprssion字符
    breakable {
      while (true) {
        // 扫描表达式
        ch = expression.substring(index, index + 1)(0)
        if (operStack.isOper(ch)) {
          if (!operStack.isEmpty()) {
            /**
              * 如果当前符号的优先级小于等于符号栈顶的优先级时，则取出该符号，并从数栈依次pop出
              * 两个数据，进行运算，将数据重新push到数栈，并将当前符号push到符号栈
              */
            if (operStack.priority(ch) <= operStack.priority(operStack.stack(operStack.top))) {
              //  开始计算
              num1 = numStack.pop().toString.toInt
              num2 = numStack.pop().toString.toInt
              oper = operStack.pop().toString.toInt
              res = numStack.cal(num1, num2, oper)
              // 入栈当前结果
              numStack.push(res)
              // 把符号入栈
              operStack.push(ch)
            } else {
              // 符号优先级高，入栈
              operStack.push(ch)
            }
          } else {
            operStack.push(ch)
          }
        } else {
          // 数字直接入栈,注意""
          numStack.push((ch + "").toInt)
        }
        index += 1
        // 判断是否到达表达式的末尾
        if (index >= expression.length()) {
          break()
        }
      }
    }
    // 当整个表达式扫描完毕后，依次从数栈和符号栈取出数据，进行运行，最后在数栈中的数据就是结果
    breakable {
      while (true) {
        if(operStack.isEmpty()){
          break()
        }
        // 运算
        // 开始计算
        num1 = numStack.pop().toString.toInt
        num2 = numStack.pop().toString.toInt
        oper = operStack.pop().toString.toInt
        res = numStack.cal(num1,num2,oper)
        numStack.push(res)
      }
    }
    printf("表达式 %s = %d",expression,numStack.pop())
  }
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

  // 判断是否为操作符
  def isOper(value:Int):Boolean={
    value == '+' || value == '-' || value == '/' || value == '*'
  }

  // 判断优先级
  def priority(oper:Int):Int={
    if(oper == '*' || oper == '/'){
      return 1
    }else if(oper == '+' || oper == '-'){
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
