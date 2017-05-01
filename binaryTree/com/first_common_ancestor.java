/*
* First common ancestor (not necessarily BST)
* 
* With link to parent
*
*/

TreeNode commonAncestor(TreeNode p, Treenode q){
	int delta = depth(p) - depth(q);
	TreeNode shallow = delta > 0 ? q : p;
	TreeNode deep = delta > 0 ? p : q;
	deep = goUpBy(deep, delta); //move deeper node up

	//Find the intersection
	while(shallow != deep && shallow != null && deep != null){
		shallow = shallow.parent;
		deep = deep.parent;
	}
	return shallow == null || deep == null ? null : shallow;
}

TreeNode goUpBy(TreeNode node, int delta){
	while(delta > 0 && node != null){
		node = node.parent;
		delta--;
	}
	return node;
}

int depth(TreeNode node){
	int depth = 0;
	while(node != null){
		node = node.parent;
		depth++;
	}
	return node;
}