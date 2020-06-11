import java.util.ArrayList;

public class Graph_Directed
{
	private int maxNodeId = 0;
	private int maxEdgeId = 0;
	private ArrayList<Graph_Node> nodes;
	private ArrayList<Graph_Edge> edges;
	
	public int getNextNodeId(Graph_Node node)
	{
		this.maxNodeId++;
		this.nodes.add(node);
		return this.maxNodeId;
	}
	
	public int getNextEdgeId(Graph_Edge edge)
	{
		this.maxEdgeId++;
		this.edges.add(edge);
		return this.maxEdgeId;
	}
	
	public ArrayList<Graph_Node> getNodes()
	{
		return this.nodes;
	}
	
	public int getNodeCount()
	{
		return this.nodes.size();
	}
	
	public ArrayList<Graph_Edge> getEdges()
	{
		return this.edges;
	}
	
	public int getEdgeCount()
	{
		return this.edges.size();
	}
}