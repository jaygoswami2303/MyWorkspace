public class Graph_Node
{
	private int id;
	private Graph_Directed graph;
	
	public Graph_Node(Graph_Directed graph)
	{
		this.id = graph.getNextNodeId(this);
		this.graph = graph;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public Graph_Edge connectFlowFrom(Graph_Node node)
	{
		return new Graph_Edge(this.graph,node,this);
	}
}