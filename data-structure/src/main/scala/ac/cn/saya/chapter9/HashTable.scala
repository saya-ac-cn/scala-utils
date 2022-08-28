package ac.cn.saya.chapter9

import scala.io.StdIn
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

  def main(args: Array[String]): Unit = {
    val hashTable = new HashTable(7)
    // 保存用户输入的指令
    var keyWord = ""
    while (true){
      println("add：添加")
      println("find：查找")
      println("list：显示")
      println("exit：退出程序")

      keyWord = StdIn.readLine()
      keyWord match {
        case "add" => {
          println("请输入id")
          val id = StdIn.readInt()
          println("请输入name")
          val name = StdIn.readLine()
          val emp = new Emp(id,name)
          hashTable.add(emp)
        }
        case "find" => {
          println("请输入id")
          val id = StdIn.readInt()
          hashTable.find(id)
        }
        case "list" => {
          hashTable.list()
        }
        case "exit" => System.exit(0)
      }
    }
  }

}

// 创建Emp类
class Emp(eId:Int,eName:String){
  val id = eId
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
      head = emp
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

  def list(i:Int):Unit={
    // 如果是第一个节点
    if(head == null){
      println(s"链表${i}为空！")
      return
    }
    // 定义辅助指针
    var cur = head
    breakable{
      while (true){
        if(cur == null){
          break()
        }
        println(s"=>id:${cur.id},name:${cur.name}")
        cur = cur.next
      }
    }
    println()
  }

  // 根据id查找
  def findById(id:Int):Emp={
    // 如果是第一个节点
    if(head == null){
      println("链表为空！")
      return null
    }
    // 定义辅助指针
    var cur = head
    breakable{
      while (true){
        if(cur == null){
          break()
        }
        if(cur.id == id){
          break()
        }
      }
    }
    return cur
  }

}

class HashTable(val size:Int){
  val empLinkedListArr:Array[EmpLinkedList] = new Array[EmpLinkedList](size)

  // 初始化个元素
  for(i <- 0 until size){
    empLinkedListArr(i) = new EmpLinkedList
  }

  def add(emp: Emp):Unit={
    val index = hashCode(emp.id)
    empLinkedListArr(index).add(emp)
  }

  def list():Unit={
    for (i <- 0 until size){
      empLinkedListArr(i).list(i)
    }
  }

  def find(id:Int):Unit={
    val index = hashCode(id)
    val emp = empLinkedListArr(index).findById(id)
    if(emp == null){
      println("未找到")
    }else{
      println(s"=>${emp.id},name=>${emp.name}")
    }
  }

  // 散列函数
  def hashCode(id:Int):Int={
    id%size
  }
}
