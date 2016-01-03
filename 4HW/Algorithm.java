/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shortestpath;

import java.util.List;

public class Algorithm
{
    Node source;
    List<Node> nodeList;
    int numNodes;

    Algorithm(Node source, List<Node> v)
    {
        this.source = source;
        this.nodeList = v;
        this.numNodes = nodeList.size();
    }

    void modifiedDijkstra()
    {
        initDijkstra();
        boolean notAllSeen = true;

        while(notAllSeen)
        {
            //Loop to make sure that there are still nodes to be checked out
            for(int x = 0; x < numNodes; x++)
            {
                if(nodeList.get(x).inqueue == true)
                {
                    break;
                }

                if( (x == numNodes-1) )
                {
                    notAllSeen = false;
                }
            }
            //If all nodes have been observed, we're done
            if(!notAllSeen)
            {
                break;
            }

            //MODIFIED RELAX
            for(int x = 0; x < source.out_list.size(); x++)
            {   
                //Relax as usual, set path count to 1
                if(source.out_list.get(x).end.dist > source.out_list.get(x).len + source.dist)
                {
                    source.out_list.get(x).end.dist = source.out_list.get(x).len + source.dist;
                    source.out_list.get(x).end.parent = source;
                    source.out_list.get(x).end.x = 1;
                }
                //If same dist, add the shortest path count from source to dest
                else if(source.out_list.get(x).end.dist == source.out_list.get(x).len + source.dist)
                {
                    source.out_list.get(x).end.x += source.x;
                }
            }
            //Mark the node as seen
            source.inqueue = false;
            //Get the next node
            source = getNextNode(source);
        }
    }
    void initDijkstra()
    {
        for(int x = 0; x < numNodes; x++)
        {
            //Initializes node distances to pseudo infinity
            if(nodeList.get(x) != source)
            {
                nodeList.get(x).dist = Integer.MAX_VALUE;
                nodeList.get(x).inqueue = true;
            }
            //Source node is set to 0
            else
            {
                nodeList.get(x).dist = 0;
                nodeList.get(x).inqueue = true;
                nodeList.get(x).x = 1;

            }
        }
    }
    //Grab the next node according to Dijkstra's alg
    Node getNextNode(Node s)
    {
        int lowest = Integer.MAX_VALUE;
        Node ret = null;

        //Search for the lowest edge from the source that hasn't been looked at yet
        for(int x = 0; x < s.out_list.size(); x++)
        {
            if(s.out_list.get(x).end.dist < lowest && s.out_list.get(x).end.inqueue == true)
            {
                lowest = s.out_list.get(x).end.dist;
                ret = s.out_list.get(x).end;
            }
        }
        //If that doesn't exist, select a node that has not been looked at yet.
        if(ret == null)
        {
            for(int x = 0; x < numNodes; x++)
            {
                if(nodeList.get(x).inqueue == true)
                    return nodeList.get(x);
            }
        }
        
        return ret;
    }
}