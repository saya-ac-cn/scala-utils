package ac.cn.saya.chapter4

import scala.util.control.Breaks.{break, breakable}

/**
  * @Title: Josephu
  * @ProjectName ScalaStudy
  * @Description: TODO
  * @Author liunengkai
  * @Date: 2019-03-25 21:27
  * @Description:
  *              约瑟夫问题
  */
object Josephu {

  def main(args: Array[String]): Unit = {
    val game = new BoyGame
    game.addBoy(10)
    game.showBoy()
  }

}

// 定义核心类
class BoyGame{
  // 定义初始化的头结点
  var first: Boy = new Boy(0)

  // 添加节点
  def addBoy(nums: Int):Unit={
    if(nums < 1){
      println("nums的值不正确")
      return
    }
    // 辅助指针
    var curBoy: Boy = null
    for(no <- 1 to nums){
      // 要添加的结点
      val boy = new Boy(no)
      // 如果是第一个节点
      if(no == 1){
        first = boy
        // 形成一个环
        first.next = first
        curBoy = boy
      }else{
        curBoy.next = boy
        boy.next = first
        curBoy = boy
      }
    }
  }

  // 遍历节点
  def showBoy():Unit={
    if(first.next == null){
      println("没有任何节点")
      return
    }
    // 辅助指针
    var curBoy: Boy = first
    breakable{
      while (true){
        printf("节点编号 %d\n",curBoy.no)
        if(curBoy.next == first){
          // 已到最后
          break()
        }
        // 继续移动
        curBoy = curBoy.next
      }
    }
  }

}


// 定义boy类
class Boy(number: Int){
  val no: Int = number
  var next: Boy = null
}