/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
 // This solution uses in built iterators and a stack to iterate in DFS fashion to get to the integer element in sequence. We use a temp variable to get the next integer element
public class NestedIterator implements Iterator<Integer> {

    Stack<Iterator> stack = new Stack();
    NestedInteger nextElement;

    public NestedIterator(List<NestedInteger> nestedList) {
        stack.add(nestedList.iterator());
    }

    @Override
    public Integer next() {
        if(nextElement==null) return -1;
        return nextElement.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!stack.isEmpty()) {
            Iterator iterator = stack.peek();
            if(iterator.hasNext()) {
                NestedInteger nextVal = (NestedInteger)iterator.next();
                if(nextVal.isInteger()) {
                    nextElement = nextVal;
                    return true;
                } else {
                    stack.push(nextVal.getList().iterator());
                    continue;
                }
            } else {
                stack.pop();
            }
        }  
        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
