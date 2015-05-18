
public class BSTMain 
{
	public static void main(String[] args)
	{
		TreeDisplay display = new TreeDisplay();
		TreeNode t = new TreeNode(3);
		t.setLeft(new TreeNode(2));
		t.setRight(new TreeNode (4));
		display.displayTree(t);
		BSTUtilities.insert(t, 7, display);
		BSTUtilities.insert(t, 6, display);
		BSTUtilities.insert(t, 3, display);
		for (int i = 0; i < 10; i++)
		{
			int alpha = (int) (Math.random() * 9);
			BSTUtilities.insert(t, alpha, display);
			
		}
		for (int k = 0; k < 10; k++)
		{
			int beta = (int) (Math.random() * 9);
			BSTUtilities.delete(t, beta, display);
		}
		display.displayTree(t);
	}
}
