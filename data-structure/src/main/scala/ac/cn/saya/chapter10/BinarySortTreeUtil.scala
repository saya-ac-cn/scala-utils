package ac.cn.saya.chapter10

/**
  * @Title: BinarySortTreeUtil
  * @ProjectName ScalaStudy
  * @Description: TODO
  * @Author liunengkai
  * @Date: 2019-04-20 20:39
  * @Description:
  *              二叉排序树
  */
object BinarySortTreeUtil {

  def main(args: Array[String]): Unit = {
    val arr = Array(7,3,10,12,5,1,9)
    val binarySortTree = new BinarySortTree
    for (item <- arr){
      binarySortTree.add(new BinarySortTreeNode(item))
    }
    binarySortTree.delNode(1)
    binarySortTree.infixOrder()
  }

}

class BinarySortTreeNode(var value:Int){
  var left:BinarySortTreeNode = null
  var right:BinarySortTreeNode = null

  // 查找节点
  def search(value:Int):BinarySortTreeNode={
    if(value == this.value){
      return this
    }else if(value < this.value){
      // 递归左边进行查找
      if(this.left == null){
        return null
      }else{
        return this.left.search(value)
      }
    }else{
      // 递归右边进行查找
      if(this.right == null){
        return null
      }else{
        return this.right.search(value)
      }
    }
  }

  // 查找指定节点的父节点
  def searchParent(value:Int):BinarySortTreeNode={
    if((this.left != null && this.left.value == value)||
      (this.right != null && this.right.value == value)){
      return this
    }else{
      if(this.left != null && value < this.value){
        return this.left.searchParent(value)
      }else if(this.right != null && value > this.value){
        return this.right.searchParent(value)
      }else{
        return null
      }
    }
  }

  // 添加方法
  def add(node:BinarySortTreeNode):Unit={
    if(node == null){
      return
    }
    if(node.value < this.value){
      // 插入的节点值小于左边
      if(this.left == null){
        this.left = node
      }else{
        this.left.add(node)
      }
    }else{
      // 插入的节点值小于左边
      if(this.right == null){
        this.right = node
      }else{
        this.right.add(node)
      }
    }
  }

  // 中序遍历
  def infixOrder():Unit = {
    // 向左递归左子树
    if(this.left != null){
      this.left.infixOrder()
    }
    // 输出当前节点的值
    println(s"节点信息 no:${value}")
    // 向右遍历右子树
    if(this.right != null){
      this.right.infixOrder()
    }
  }
}

class BinarySortTree{
  var root:BinarySortTreeNode = null

  def search(value:Int):BinarySortTreeNode={
    if(root == null){
      return null
    }else{
      return root.search(value)
    }
  }

  def searchParent(value:Int):BinarySortTreeNode={
    if(root == null){
      return null
    }else{
      return root.searchParent(value)
    }
  }

  def delRightTreeMin(node:BinarySortTreeNode):Int={
    var target = node
    // 循环查找有右子树最小的值
    while(target.left != null){
      target = target.left
    }
    val minValue = target.value
    // 删除最小值的对应节点信息
    delNode(minValue)
    return minValue
  }

  def delNode(value:Int):Unit={
    if(root == null){
      return
    }
    // 查找要删除的节点
    var targetNode = search(value)
    if(targetNode == null){
      return
    }
    // 查找父节点
    var parentNode = searchParent(value)
    if (parentNode == null){
      root = null
      return
    }
    // 判断是否是叶子节点
    if(targetNode.left == null && targetNode.right == null){
      // 判断是左节点还是右子节点
      if(parentNode.left != null && parentNode.left.value == value){
        parentNode.left = null
      }else{
        parentNode.right = null
      }
    }else if(parentNode.left != null && parentNode.right != null){
      // 有两个叶子节点
      val value = delRightTreeMin(targetNode.right)
      targetNode.value = value
    }else{
      // 有一个叶子节点
      // 判断targetNode是parentNode的左还是右
      if (targetNode.left != null){
        if(parentNode.left.value == value){
          parentNode.left = targetNode.left
        }else{
          parentNode.right = targetNode.left
        }
      }else{
        if(parentNode.left.value == value){
          parentNode.left = targetNode.right
        }else{
          parentNode.right = targetNode.right
        }
      }
    }
  }

  def add(node:BinarySortTreeNode):Unit={
    if(root == null){
      root = node
    }else{
      root.add(node)
    }
  }

  def infixOrder():Unit={
    if(root==null){
      println("当前二叉树为空")
    }else{
      root.infixOrder()
    }
  }

}
