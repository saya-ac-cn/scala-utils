package ac.cn.saya.chapter1

import scala.collection.mutable.ArrayBuffer

/**
 * @描述  稀疏数组
 * @创建人  saya.ac.cn-刘能凯
 * @创建时间  2019-03-17
 * @修改人和其它信息
 */
object SparseArray {

  def main(args: Array[String]): Unit = {
    // 定义数组的属性（行、列）
    val rowSize = 11;
    val colSize = 11;

    // 稀疏数组
    val chessMap = Array.ofDim[Int](rowSize,colSize);
    // 初始化地图
    // 1为黑子、2为白子
    chessMap(1)(2) = 1;
    chessMap(2)(3) = 2;

    println("原始数组")
    for(item <- chessMap){
      for(item2 <- item){
        printf("%d\t",item2);
      }
      println();
    }

    // 开始压缩
    // 在压缩前写入数组的信息（行数、列数）
    val properties = new Node(rowSize, colSize, 0)
    val SparseArr = new ArrayBuffer[Node]()
    // 放入压缩的重要信息
    SparseArr.append(properties)
    for(i <- 0 until chessMap.length){
      for (j <- 0 until chessMap(i).length){
        if(chessMap(i)(j) != 0){
          SparseArr.append(new Node(i, j, chessMap(i)(j)))
        }
      }
    }

    println("输出压缩后的数组")
    for (node <- SparseArr){
      printf("%d\t%d\t%d\n",node.row, node.col, node.value)
    }

    // 恢复数组-注意，数组的对象信息在之前是放在了第一位
    val propertiesNode = SparseArr(0);
    val rows = propertiesNode.row;
    val cols = propertiesNode.col;
    val reductionArray = Array.ofDim[Int](rows, cols);
    for(i <- 1 until SparseArr.length){
      val node = SparseArr(i);
      reductionArray(node.row)(node.col) = node.value;
    }

    println("输出恢复后的稀疏数组")
    for(item <- reductionArray){
      for(item2 <- item){
        printf("%d\t",item2);
      }
      println();
    }
  }

}

/**
 * 每个节点的信息
 * @创建人  saya.ac.cn-刘能凯
 * @创建时间  2019-03-17
 * @修改人和其它信息
 */
class Node(val row:Int, val col:Int, val value:Int)
