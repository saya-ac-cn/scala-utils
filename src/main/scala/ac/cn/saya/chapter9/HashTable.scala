package ac.cn.saya.chapter9

import util.control.Breaks._
/**
  * @Title: HashTable
  * @ProjectName ScalaStudy
  * @Description: TODO
  * @Author liunengkai
  * @Date: 2019-04-14 20:44
  * @Description:
  *              hash散列表
  */
object HashTable {

}

// 创建Emp类
class Emp(eId:Int,eName:String){
  val id = eId,
  var name = eName
  var next:Emp = null
}

class EmpLinkedList{
  // 定义头指针
  var head : Emp = null

  // 添加方法
  // 假定，添加的id是自增的，
  // 找到链表的最后加入即可
  def add(emp:Emp):Unit={
    // 如果是第一个节点
    if(head == null){
      head == emp
      return
    }
    // 定义辅助指针
    var cur = head
    breakable{
      while (true){
        if(cur.next == null){
          break()
        }
        cur = cur.next
      }
    }
    cur.next = emp
  }
}
