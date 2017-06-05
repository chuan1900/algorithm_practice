/*
* Implement a queue with two stacks
*
*/

public class myQueue <T> {
	private stack<T> stackNew, stackOld;

	public int size(){
		return stackNew.size() + stackOld.size();
	}

	public void add (T value){
		stackNew.push(value);
	}

	public void reverse(){
		if(stackOld.isEmpty()){
			while(!stackNew.isEmpty()){
				stackOld.push(stackNew.pop());
			}
		}
	}

	public T peek(){
		reverse();
		return stackOld.peek();
	}

	public T pop(){
		reverse();
		return stackOld.pop();
	}
}