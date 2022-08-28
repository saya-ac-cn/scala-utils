package ac.cn.saya.chapter6

/**
  * @Title: Maze
  * @ProjectName ScalaStudy
  * @Description: TODO
  * @Author liunengkai
  * @Date: 2019-04-04 21:53
  * @Description:
  *              运用递归求解迷宫问题
  */
object Maze {

  def main(args: Array[String]): Unit = {
    // 地图
    val map = Array.ofDim[Int](8,7)
    // 1 为边界
    // 上下设置为1
    for(i <- 0 until 7){
      map(0)(i) = 1
      map(7)(i) = 1
    }
    // 左右设置为1
    for(i <- 0 until 8){
      map(i)(0) = 1
      map(i)(6) = 1
    }
    // 设置挡板
    map(3)(1) = 1
    map(3)(2) = 1
    findWay(map,1,1)
    // 打印地图
    for(i <- 0 until 8){
      for(j <-0 until 7){
        print(map(i)(j)+" ")
      }
      println()
    }
  }

  /**
   * @描述 递归找路
   * @参数  map 地图，i，j表示开始找的位置
   * @返回值
   * @创建人  saya.ac.cn-刘能凯
   * @创建时间  2019-04-04
   * @修改人和其它信息
    *          约定 元素的值：0 可以走但还没有走；1 墙 ；2 可以走；3 已经走过但是死路
   */
  def  findWay(map:Array[Array[Int]],i:Int,j:Int):Boolean={
    if(map(6)(5) == 2){
      return true
    }else{
      // 0: 可以走
      if(map(i)(j) == 0){
        map(i)(j) = 2//认为该节点可以走，但不一定
        if(findWay(map,i+1,j)){
          // 下找
          return true
        }else if(findWay(map,i,j+1)){
          // 右找
          return true
        }else if(findWay(map,i-1,j)){
          // 上找
          return true
        }else if(findWay(map,i,j-1)){
          // 左找
          return true
        }else{
          // 此路不同
          map(i)(j) = 3
          return false
        }
      }else{
        return false
      }
    }
  }

}
