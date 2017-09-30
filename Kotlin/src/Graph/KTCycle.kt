package Graph

/**
 * 检测无环图
 *
 * @author igaozp
 * @since 2017-09-08
 * @version 1.0
 */
class KTCycle(G: Graph) {
    /**
     * 存储每个顶点的访问情况
     */
    private var marked: MutableList<Boolean?>? = null
    /**
     * 是否有环
     */
    private var hasCycle: Boolean? = null

    /**
     * 构造方法
     */
    init {
        marked = MutableList(G.V(), { null })
        (0 until G.V())
                .filterNot { marked?.get(it)!! }
                .forEach { dfs(G, it, it) }
    }

    /**
     * 深度优先搜索
     *
     * @param G 搜索的图
     * @param v 搜索的起点
     * @param u 对比顶点
     */
    private fun dfs(G: Graph, v: Int, u: Int) {
        marked!![v] = true
        for (w in G.adj(v)) {
            if (!marked?.get(w)!!) {
                dfs(G, w, v)
            } else if (w != u) {
                hasCycle = true
            }
        }
    }

    /**
     * 检查图是否有环
     *
     * @return `true` 有环
     *         `false` 无环
     */
    fun hasCycle(): Boolean = hasCycle!!
}