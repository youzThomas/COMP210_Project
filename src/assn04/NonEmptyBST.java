package assn04;

public class NonEmptyBST<T extends Comparable<T>> implements BST<T> {
	private T _element;
	private BST<T> _left;
	private BST<T> _right;

	public NonEmptyBST(T element) {
		_left = new EmptyBST<T>();
		_right = new EmptyBST<T>();
		_element = element;
	}

	// TODO: insert
	@Override
	public BST<T> insert(T element){
		int compareResult = element.compareTo(_element);

		if (compareResult < 0){
			_left = _left.insert(element);
		}else if (compareResult > 0){
			_right = _right.insert(element);
		}
		return this;
	}
	
	// TODO: remove
	@Override
	public BST<T> remove(T element) {
		int compareResult = element.compareTo(_element);

		if (compareResult < 0){
			_left = _left.remove(element);
		}else if (compareResult > 0){
			_right = _right.remove(element);
		}else {
			if (!_left.isEmpty() && !_right.isEmpty()){
				_element = _right.findMin();
				_right = _right.remove(_element);
			}else {
				return (_left.isEmpty())? _right : _left;
			}
		}
		return this;
	}
	
	// TODO: remove all in range (inclusive)
	@Override
	public BST<T> remove_range(T start, T end) {
		_left = _left.remove_range(start, end);
		_right = _right.remove_range(start, end);

		if (start.compareTo(_element) <= 0 && end.compareTo(_element) >= 0){
			return this.remove(_element);
		}
		return this;
	}

	// TODO: printPreOrderTraversal
	@Override
	public void printPreOrderTraversal() {
		System.out.print(_element + " ");
		_left.printPreOrderTraversal();
		_right.printPreOrderTraversal();
	}

	// TODO: printPostOrderTraversal
	@Override
	public void printPostOrderTraversal() {
		_left.printPostOrderTraversal();
		_right.printPostOrderTraversal();
		System.out.print(_element + " ");
	}

	// The findMin method returns the minimum value in the tree.
	@Override
	public T findMin() {
		if(_left.isEmpty()) {
			return _element;
		}
		return _left.findMin();
	}

	@Override
	public int getHeight() {
		   return Math.max(_left.getHeight(), _right.getHeight())+1;
	}

	@Override
	public BST<T> getLeft() {
		return _left;
	}

	@Override
	public BST<T> getRight() {
		return _right;
	}

	@Override
	public T getElement() {
		return _element;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

}
