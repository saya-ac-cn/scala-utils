package ac.cn.saya.chapter4

import scala.util.control.Breaks.{break, breakable}

/**
  * @Title: DoubleLinkListUnit1
  * @ProjectName ScalaStudy
  * @Description: TODO
  * @Author liunengkai
  * @Date: 2019-03-22 21:13
  * @Description:
  *              双向链表
  */
object DoubleLinkListUnit1 {

  def main(args: Array[String]): Unit = {
    val linklist = new DoubleLinkList
    val node1 = new DoubleinkNode(1,"hadoop")
    val node2 = new DoubleinkNode(2,"zookeeper")
    val node3 = new DoubleinkNode(5,"hive")
    val node4 = new DoubleinkNode(4,"kafka")
    linklist.add(node1)
    linklist.add(node2)
    linklist.add(node3)
    linklist.add(node4)
    linklist.list()
    println("---------------")
    linklist.dell(new DoubleinkNode(4,"saprk"))
    linklist.add(node2)
    linklist.list()
  }
}

class DoubleLinkList{

  // 初始化头结点，不存数据，存下一跳地址
  val head = new DoubleinkNode(0,"")

  // 修改链表
  def update(singleLinkNode: SingleLinkNode):Unit={
    if(head.thisNext == null){
      println("链表为空")
      return
    }
    // 头结点一般不动，一般用临时的变量来遍历
    var temp = head.thisNext
    // 是否找到的标志位
    var flog = false
    // 寻找要修改元素的位置
    breakable{
      while (true){
        if(temp == null){
          // 已到最后
          break()
        }
        if(temp.thisNumber == singleLinkNode.thisNumber){
          flog = true
          break()
        }
        // 没有到最后，继续移动
        temp = temp.thisNext
      }
    }
    if(flog){
      temp.thisValue = singleLinkNode.thisValue
    }else{
      println("未能找到")
    }
  }

  // 在链表尾部添加数据
  def add(doubleLinkNode: DoubleinkNode):Unit={
    // 头结点一般不动，一般用临时的变量来遍历
    var temp = head
    // 寻找最后一个元素的位置
    breakable{
      while (true){
        if(temp.thisNext == null){
          // 已到最后
          break()
        }
        // 没有到最后，继续移动
        temp = temp.thisNext
      }
    }
    // 挂到最后
    temp.thisNext = doubleLinkNode
    doubleLinkNode.thisPre = temp
  }

  // 删除
  def dell(doubleLinkNode: DoubleinkNode):Unit={
    if(head.thisNext == null){
      println("链表为空")
      return
    }
    // 比较时，是用当前的节点和temp.next比较
    // 头结点一般不动，一般用临时的变量来遍历
    var temp = head.thisNext
    // 判断当前序号是否存在
    var flag = false
    // 寻找最后一个元素的位置
    breakable{
      while (true){
        if(temp == null){
          // 已到最后
          break()
        }
        if(temp.thisNumber == doubleLinkNode.thisNumber){
          // 找到
          flag = true
          break()
        }
        // 没有到最后，继续移动
        temp = temp.thisNext
      }
    }
    if(flag){
      // 节点存在
      if(temp.thisNext != null){
        (temp.thisNext).thisPre = temp.thisPre
        (temp.thisPre).thisNext = temp.thisNext
      }else{
        (temp.thisPre).thisNext = null
        temp.thisPre = null
      }
    }else{
      println("要删除的节点存在")
    }
  }

  def list():Unit={
    if(head.thisNext == null){
      println("链表为空")
      return
    }
    // 头结点一般不动，一般用临时的变量来遍历
    var temp = head.thisNext
    breakable{
      while (true){
        if(temp == null){
          // 已到最后
          break()
        }
        printf("节点信息number=%d value=%s\n",temp.thisNumber,temp.thisValue)
        // 没有到最后，继续移动
        temp = temp.thisNext
      }
    }
  }

}

class DoubleinkNode(number:Int,value:String){
  var thisNumber:Int = number
  var thisValue:String = value
  var thisPre:DoubleinkNode = null
  var thisNext:DoubleinkNode = null
}
