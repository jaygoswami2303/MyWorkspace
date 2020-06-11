public class Graph_Edge
{
	private int id;
	private Graph_Node nodeFrom;
	private Graph_Node nodeTo;
	private Graph_Directed graph;
	
	public Graph_Edge(Graph_Directed graph,Graph_Node nodeFrom,Graph_Node nodeTo)
	{
		this.id = graph.getNextEdgeId(this);
		this.graph = graph;
		this.nodeFrom = nodeFrom;
		this.nodeTo = nodeTo;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public Graph_Node getNodeTo()
	{
		return this.nodeTo;
	}
	
	public Graph_Node getNodeFrom()
	{
		return this.nodeFrom;
	}
	
	public Graph_Directed getGraph()
	{
		return this.graph;
	}
}