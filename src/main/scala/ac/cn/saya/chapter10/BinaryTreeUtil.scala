package ac.cn.saya.chapter10

/**
  * @Title: BinaryTreeUtil
  * @ProjectName ScalaStudy
  * @Description: TODO
  * @Author liunengkai
  * @Date: 2019-04-17 20:25
  * @Description:
  */
object BinaryTreeUtil {

  def main(args: Array[String]): Unit = {
    val root = new HeroNode(1,"a")
    val root1 = new HeroNode(2,"b")
    val root2 = new HeroNode(3,"c")
    val root3 = new HeroNode(4,"d")
    val root4 = new HeroNode(5,"e")

    root.left = root1
    root.right = root2

    root2.left = root4
    root2.right = root3

    val binaryTree = new BinaryTree
    binaryTree.root = root

    // binaryTree.preOrder()
    val find = binaryTree.preOrderSearch(5)
    if(find == null){
      println("未找到")
    }else{
      println(s"no：${find.no} name：${find.user}")
    }

  }

}

// 定义节点
class HeroNode(number:Int,name:String){
  val no = number
  val user = name
  var left: HeroNode = null
  var right:HeroNode = null

  // 前序遍历
  def preOrder():Unit = {
    // 先输出当前节点的值
    println(s"节点信息 no:${no} name:${name}")
    // 向左递归左子树
    if(this.left != null){
      this.left.preOrder()
    }
    // 向右遍历右子树
    if(this.right != null){
      this.right.preOrder()
    }
  }

  // 前序查找
  def preOrderSearch(no:Int):HeroNode={
    if(no == this.no){
      return this
    }
    var temp : HeroNode = null
    // 向左找
    if(this.left != null){
      temp = this.left.preOrderSearch(no)
    }
    if(temp != null){
      return temp
    }
    // 向右边查找
    if(this.right != null){
      temp = this.right.preOrderSearch(no)
    }
    return temp
  }

  // 中序遍历
  def infixOrder():Unit = {
    // 向左递归左子树
    if(this.left != null){
      this.left.infixOrder()
    }
    // 输出当前节点的值
    println(s"节点信息 no:${no} name:${name}")
    // 向右遍历右子树
    if(this.right != null){
      this.right.infixOrder()
    }
  }

  // 中序查找
  def infixOrderSearch(no:Int):HeroNode={
    var temp : HeroNode = null
    // 向左找
    if(this.left != null){
      temp = this.left.infixOrderSearch(no)
    }
    if(temp != null){
      return temp
    }
    if(no == this.no){
      return this
    }
    // 向右边查找
    if(this.right != null){
      temp = this.right.infixOrderSearch(no)
    }
    return temp
  }

  // 后序遍历
  def postOrder():Unit = {
    // 向左递归左子树
    if(this.left != null){
      this.left.postOrder()
    }
    // 向右遍历右子树
    if(this.right != null){
      this.right.postOrder()
    }
    // 输出当前节点的值
    println(s"节点信息 no:${no} name:${name}")
  }

  // 后序查找
  def postOrderSearch(no:Int):HeroNode={
    var temp : HeroNode = null
    // 向左找
    if(this.left != null){
      temp = this.left.postOrderSearch(no)
    }
    if(temp != null){
      return temp
    }
    // 向右边查找
    if(this.right != null){
      temp = this.right.postOrderSearch(no)
    }
    if(temp != null){
      return temp
    }
    if(no == this.no){
      return this
    }
    return temp
  }

  /**
    * 删除节点
    * 如果是叶子节点，直接删除
    * 如果是非叶子节点，删除子树
    * @param no
    */
  def delNode(no:Int):Unit={
    if(this.left != null && this.left.no == no){
      this.left = null
      return
    }
    if(this.right != null && this.right.no == no){
      this.right = null
      return
    }
    if(this.left != null){
      this.left.delNode(no)
    }
    if(this.right != null){
      this.right.delNode(no)
    }
  }

}

//
class BinaryTree{
  var root:HeroNode = null

  def preOrder():Unit={
    if(root==null){
      println("当前二叉树为空")
    }else{
      root.preOrder()
    }
  }

  def infixOrder():Unit={
    if(root==null){
      println("当前二叉树为空")
    }else{
      root.infixOrder()
    }
  }

  def postOrder():Unit={
    if(root==null){
      println("当前二叉树为空")
    }else{
      root.postOrder()
    }
  }

  def preOrderSearch(no:Int):HeroNode={
    if(root==null){
      return null
    }else{
      return root.preOrderSearch(no)
    }
  }

  def delNode(no:Int):Unit={
    if(root != null){
      if(root.no == no){
        root = null
      }else{
        root.delNode(no)
      }
    }
  }

  def infixOrderSearch(no:Int):HeroNode={
    if(root==null){
      return null
    }else{
      return root.infixOrderSearch(no)
    }
  }

  def postOrderSearch(no:Int):HeroNode={
    if(root==null){
      return null
    }else{
      return root.postOrderSearch(no)
    }
  }

}
