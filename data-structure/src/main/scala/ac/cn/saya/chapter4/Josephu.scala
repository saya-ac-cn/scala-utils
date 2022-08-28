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
    game.addBoy(7)
    game.showBoy()
    game.countBoy(4,3,7)
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

  /** 主方法
   * @描述
   * @参数   startNo 从startNo开始数
   * @参数   countNum 数countNum下
   * @参数   nums 多少人
   * @返回值
   * @创建人  saya.ac.cn-刘能凯
   * @创建时间  2019-03-26
   * @修改人和其它信息
   */
  def countBoy(startNo:Int,countNum:Int,nums:Int):Unit={
    // 参数判断
    if(first.next == null || startNo < 1 || startNo > nums){
      println("参数不合法")
      return
    }

    /** 思路
    1）在first前面 设计一个辅助指针(helper),辅助helper定位到first前面
    2）将first 指针移动到  startNo 节点
    3）开始数  countNum个数，first和helper都要移动
    4）删除first指向的节点
    **/
    //将辅助helper定位到first前面
    var helper = first
    breakable{
      while (true){
        if(helper.next == first){
          break()
        }
        helper = helper.next
      }
    }

    //将first 指针移动到  startNo 节点
    for(i <- 0 until startNo -1){
      first = first.next
      helper = helper.next
    }

    // 开始数数，按照给定的值，每数到一个节点就出圈，直到链表只有一个节点
    breakable{
      while (true){
        if(helper == first){
          // 只有一个节点
          break()
        }
        // 开始数  countNum个数，first和helper都要移动
        for(i <- 0 until countNum -1){
          first = first.next
          helper = helper.next
        }
        // 给出出圈人的信息
        printf("编号%d\n",first.no)
        // 移除该节点的信息
        first = first.next
        helper.next = first
      }
    }
    printf("最后的节点是%d",first.no)
  }

}


// 定义boy类
class Boy(number: Int){
  val no: Int = number
  var next: Boy = null
}