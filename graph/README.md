# NOTE

### DFS (recursive)
```
visited = {}
dfs(node)
1. if (node not in visited) 
2. process(node)
3. add(node, visited)
4. foreach(neighbour in node.adjList)
5.   dfs(neighbour)
```
### DFS (non-recursive)
```
dfs(node)
1. visited = {} 
2. stack = {node}
3. while(isNotEmpty(stack))
4.   n = pop(stack)
5.   if (n not in visited)
6.     process(n)
7.     add(n, visited)
8.     pushAll(n.adjList, stack)
```

### BFS
```
bfs(node)
1. visited = {} 
2. queue = {node}
3. while(isNotEmpty(queue))
4.   n = dequee(queue)
5.   if (n not in visited)
6.     process(n)
7.     add(n, visited)
8.     enqueueAll(n.adjList, stack)
```

### Topological Sort (Kahn's algorithm)
```
buildInDegreeMap(graph)
1. result = EMPTY_MAP
2. foreach(node in graph)
3.   result(node) = 0
4. foreach((edge -> node) in graph)
5.   result(node) = result(node) + 1
6. return result

topologicalSort(graph)
1. result = {}
2. inDegree = buildIndegreeMap(graph)
3. startNodes = findStartNodes(inDegree)
4. while(notEmpty(startNodes))
5.   node = removeFirst(startNodes)
6.   add(node, result)
7.   foreach(nodeM in node.adjList)
8.     inDegree(nodeM) = inDegree(nodeM) - 1
9.     if (inDegree(nodeM) == 0) add(nodeM, startNodes)
10. if (size(result) != sizeOfNodes(graph)) return ERROR
11. return result

```

### Topological Sort using DFS
```
topologicalSort(graph)
1. result = EMPTY_STACK
2. visited = EMPTY_SET
3. foreach(node in graph)
4.   if (node not in visited)
5.     topologicalDFS(node, result, visited)
6. return result

topologicalDFS(node, result, visited)
1. add(node, visited)
2. foreach(nodeM in node.adjList)
3.   if (nodeM not in visited)
4.     topologicalDFS(nodeM, result, visited)
5. push(node, result)

```