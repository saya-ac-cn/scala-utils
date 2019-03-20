package ac.cn.saya.chapter4
import util.control.Breaks._
/**
  * @Title: SingleLinkListUnit1
  * @ProjectName ScalaStudy
  * @Description: TODO
  * @Author liunengkai
  * @Date: 2019-03-20 20:47
  * @Description:
  *              单向链表（有头结点）
  */
object SingleLinkListUnit1 {

  def main(args: Array[String]): Unit = {
    val linklist = new SingleLinkList
    val node1 = new SingleLinkNode(1,"hadoop")
    val node2 = new SingleLinkNode(2,"zookeeper")
    val node3 = new SingleLinkNode(3,"hive")
    val node4 = new SingleLinkNode(4,"kafka")
    linklist.add(node1)
    linklist.add(node2)
    linklist.add(node3)
    linklist.add(node4)
    linklist.list()
  }

}

class SingleLinkList{

  // 初始化头结点，不存数据，存下一跳地址
  val head = new SingleLinkNode(0,"")

  // 在链表尾部添加数据
  def add(singleLinkNode: SingleLinkNode):Unit={
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
    temp.thisNext = singleLinkNode
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

class SingleLinkNode(number:Int,value:String){
  var thisNumber:Int = number
  var thisValue:String = value
  var thisNext:SingleLinkNode = null
}
