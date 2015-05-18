import java.util.*;

/**
 * TreeUtil contains the following methods for manipulating binary trees:
 * leftMost, rightMost, maxDepth, createRandom, countNodes, countLeaves, 
 * preOrder, inOrder, postOrder, fillList, saveTree, buildTree, loadTree, 
 * copy, sameShape, createDecodingTree, insertMorse and decodeMorse.
 *
 * @author Aashish Jain
 *      With assistance from Richard Page
 * 
 * @version November 13, 2014
 *
 */
public class TreeUtil
{
    //used to prompt for command line input
    private static Scanner in = new Scanner(System.in);

    private static final boolean debug = false;

    /**
     * Gets to left most node of a tree. 
     * 
     * @param t The tree to be traversed.
     * 
     * @return The object of the left most node of the tree.
     */
    public static Object leftmost(TreeNode t)
    {
        while (t.getLeft()!= null)
        {
            t = t.getLeft();
        }
        return t.getValue();
    }

    /**
     * Gets the right most node of a tree recursively. 
     * 
     * @param t The treeNode to be traversed.
     * 
     * @return The object of the right most node of the tree.
     */
    public static Object rightmost(TreeNode t)
    {
        if (t.getRight() == null)
        {
            return t.getValue();
        }
        else
        {
            return rightmost(t.getRight());
        }
    }

    /**
     * Finds the maxDepth of a tree.
     * 
     * @param t The treeNode to find the maxDepth of
     * 
     * @return An integer value of the the maxDepth of a tree. 
     */
    public static int maxDepth(TreeNode t)
    {
        if (t == null)
        {
            return 0;
        }
        else
        {
            int leftHeight = maxDepth(t.getLeft());
            int rightHeight = maxDepth(t.getRight());
            return Math.max(leftHeight + 1, rightHeight + 1);
        }
    }

    /**
     * Create a random tree of the specified depth. No attempt to balance the
     * tree is provided.
     *
     * @param depth of the tree
     * 
     * @return TreeNode object that points to the generated tree
     */
    public static TreeNode createRandom(int depth)
    {
        if (Math.random() * Math.pow(2, depth) < 1)
        {
            return null;
        }
        return new TreeNode(((int) (Math.random() * 10)),
                createRandom(depth - 1),
                createRandom(depth - 1));
    }

    /**
     * Counts the number of nodes in a tree. 
     * 
     * @param t The tree in which the nodes are counted. 
     * 
     * @return An Integer value of the number of nodes in a tree. 
     */
    public static int countNodes(TreeNode t)
    {
        if (t == null)
        {
            return 0;
        }
        else if (t.getLeft() == null && t.getRight() == null)
        {
            return 1;
        }
        else
        {
            return 1 + countNodes(t.getLeft()) + countNodes(t.getRight());
        }
    }

    /**
     * Counts the number of leaves in a tree. 
     * 
     * @param t The tree in which the leaves are counted.
     * 
     * @return An Integer value of the number of leaves in a tree. 
     */
    public static int countLeaves(TreeNode t)
    {
        if (t == null)
        {
            return 0;
        }
        else if (t.getLeft() == null && t.getRight() == null)
        {
            return 1;
        }
        else
        {
            return countLeaves(t.getLeft()) + countLeaves(t.getRight()); 
        }
    }

    /**
     * Visits a node, then goes down the left, then goes to the right.
     * Visits the nodes in a tree in a preOrder fashion. 
     * 
     * @param t The tree to traverse through
     * 
     * @param display The display that lights up when visit is called
     */
    public static void preOrder(TreeNode t, TreeDisplay display)
    {
        if (t != null)
        {
            display.visit(t);
            preOrder(t.getLeft(), display);
            preOrder(t.getRight(), display);
        }
    }

    /**
     * Goes left, visits the node, then goes right.
     * Visits each node in a tree in an inOrder fashion. 
     * 
     * @param t The tree to traverse through. 
     * 
     * @param display The display that lights up when visit is called.
     */
    public static void inOrder(TreeNode t, TreeDisplay display)
    {
        if (t != null)
        {
            inOrder(t.getLeft(), display);
            display.visit(t);
            inOrder(t.getRight(), display);
        }
    }

    /**
     * Goes left, goes right, then visits the node.
     * Visits each node in a tree in a postOrder fashion.
     * 
     * @param t The tree to traverse through.
     * 
     * @param display  The display that lights up when visit is called. 
     */
    public static void postOrder(TreeNode t, TreeDisplay display)
    {
        if (t != null)
        {
            postOrder(t.getLeft(), display);
            postOrder(t.getRight(), display);
            display.visit(t);
        }
    }

    /**
     * Builds a list of strings based on a tree. 
     * 
     * @param t The tree to build the list of strings on.
     * 
     * @param list The list to populate with strings.
     */
    public static void fillList(TreeNode t, List<String> list)
    {
        if (t != null)
        {
            list.add(t.getValue().toString());
            fillList(t.getLeft(), list);
            fillList(t.getRight(), list); 
        }
        else 
        {
            list.add("$");
        }
    }

    /**
     * Saves the tree rooted at t as a file with the given file name.
     *
     * @param fileName the name of the file to create which will hold the
     * data values in the tree
     * 
     * @param t the root of the tree to save
     */
    public static void saveTree(String fileName, TreeNode t)
    {
        List<String> list = new ArrayList<String>();
        fillList(t, list);
        FileUtil.saveFile(fileName, list.iterator());
    }

    /**
     * Takes in an iterator which will iterate through a valid
     * description of a binary tree with String values. Null nodes are indicated
     * by "$" markers.
     *
     * @param it the iterator which will iterate over the tree description
     * 
     * @return a pointer to the root of the tree built by the iteration
     */
    public static TreeNode buildTree(Iterator<String> it)
    {
        String alpha = it.next();
        if (alpha.equals("$"))
        {
            return null;
        }
        TreeNode t = new TreeNode(alpha, buildTree(it), buildTree(it));
        return t;
    }

    /**
     * Reads a file description of a tree and then build the tree.
     *
     * @param fileName is a valid file name for a file that describes a binary
     * tree
     * 
     * @return a pointer to the root of the tree
     */
    public static TreeNode loadTree(String fileName)
    {
        return buildTree(FileUtil.loadFile(fileName));
    }

    /**
     * utility method that waits for a user to type text into Std Input and then
     * press enter.
     *
     * @return the string entered by the user
     */
    private static String getUserInput()
    {
        return in.nextLine();
    }

    /**
     * plays a single round of 20 questions postcondition: plays a round of
     * twenty questions, asking the user questions as it walks down the given
     * knowledge tree, lighting up the display as it goes; modifies the tree to
     * include information learned.
     *
     * @param t a pointer to the root of the game tree
     * @param display which will show the progress of the game
     */
    private static void twentyQuestionsRound(TreeNode t, TreeDisplay display)
    {
        throw new RuntimeException("Write ME!");
    }

    /**
     * plays a game of 20 questions Begins by reading in a starting file and
     * then plays multiple rounds until the user enters "quit". Then the final
     * tree is saved
     */
    public static void twentyQuestions()
    {
        throw new RuntimeException("Write ME!");
    }

    /**
     * copy a binary tree
     *
     * @param t the root of the tree to copy
     * @return a new tree, which is a complete copy of t with all new TreeNode
     * objects pointing to the same values as t (in the same order, shape, etc)
     */
    public static TreeNode copy(TreeNode t)
    {
        if (t == null)
        {
            return null;
        }
        else
        {
            TreeNode n = new TreeNode(t.getValue());
            n.setLeft(copy(t.getLeft()));
            n.setRight(copy(t.getRight()));
            return n;
        }
    }

    /**
     * tests to see if two trees have the same shape, but not necessarily the
     * same values. Two trees have the same shape if they have TreeNode objects
     * in the same locations relative to the root
     *
     * @param t1 pointer to the root of the first tree
     * @param t2 pointer to the root of the second tree
     * @return true if t1 and t2 describe trees having the same shape, false
     * otherwise
     */
    public static boolean sameShape(TreeNode t1, TreeNode t2)
    {
        if (t1 == null)
        {
            if (t2 != null)
            {
                return false;
            }
            return true;
        }
        else
        {
            return (sameShape(t1.getLeft(), t2.getLeft())
                    && sameShape(t1.getRight(), t2.getRight()));
        }
    }

    /**
     * Generates a tree for decoding Morse code.
     *
     * @param display the display that will show the decoding tree
     * @return the decoding tree
     */
    public static TreeNode createDecodingTree(TreeDisplay display)
    {
        TreeNode tree = new TreeNode("Morse Tree");
        display.displayTree(tree);
        insertMorse(tree, "a", ".-", display);
        insertMorse(tree, "b", "-...", display);
        insertMorse(tree, "c", "-.-.", display);
        insertMorse(tree, "d", "-..", display);
        insertMorse(tree, "e", ".", display);
        insertMorse(tree, "f", "..-.", display);
        insertMorse(tree, "g", "--.", display);
        insertMorse(tree, "h", "....", display);
        insertMorse(tree, "i", "..", display);
        insertMorse(tree, "j", ".---", display);
        insertMorse(tree, "k", "-.-", display);
        insertMorse(tree, "l", ".-..", display);
        insertMorse(tree, "m", "--", display);
        insertMorse(tree, "n", "-.", display);
        insertMorse(tree, "o", "---", display);
        insertMorse(tree, "p", ".--.", display);
        insertMorse(tree, "q", "--.-", display);
        insertMorse(tree, "r", ".-.", display);
        insertMorse(tree, "s", "...", display);
        insertMorse(tree, "t", "-", display);
        insertMorse(tree, "u", "..-", display);
        insertMorse(tree, "v", "...-", display);
        insertMorse(tree, "w", ".--", display);
        insertMorse(tree, "x", "-..-", display);
        insertMorse(tree, "y", "-.--", display);
        insertMorse(tree, "z", "--..", display);
        return tree;
    }

    /**
     * helper method for building a Morse code decoding tree. postcondition:
     * inserts the given letter into the decodingTree, in the appropriate
     * position, as determined by the given Morse code sequence; lights up the
     * display as it walks down the tree
     *
     * @param decodingTree is the partial decoding tree
     * @param letter is the letter to add
     * @param code is the Morse code for letter
     * @param display is the display that will show progress as the method walks
     * down the tree
     */
    private static void insertMorse(TreeNode decodingTree, String letter,
            String code, TreeDisplay display)
    {
        TreeNode t = decodingTree;
        if (code.length() == 0)
        {
            t.setValue(letter);
        }
        else
        {
            if (code.substring(0, 1).equals("."))
            {
                if (t.getLeft() == null)
                {
                    t.setLeft(new TreeNode(null));
                }
                insertMorse(decodingTree.getLeft(), letter, 
                        code.substring(1, code.length()), display);
            }
            else
            {
                if (t.getRight() == null)
                {
                    t.setRight(new TreeNode(null));
                }
                insertMorse(decodingTree.getRight(), letter, 
                        code.substring(1, code.length()), display);
            }
        }
    }

    /**
     * Decodes Morse code by walking the decoding tree according to the input
     * code.
     *
     * @param decodingTree is the Morse code decoding tree
     * 
     * @param cipherText is Morse code consisting of dots, dashes, and spaces
     * 
     * @param display is the display object that will show the decoding progress
     * 
     * @return the string represented by cipherText
     */
    public static String decodeMorse(TreeNode decodingTree, 
            String cipherText, TreeDisplay display)
    {
        String alpha = new String();
        cipherText += " "; 
        TreeNode t = decodingTree;
        for (int i = 0; i < cipherText.length(); i++)
        {
            display.visit(t);
            if (cipherText.charAt(i) == '.')
            {
                t = t.getLeft();
            }
            if (cipherText.charAt(i) == '-')
            {
                t = t.getRight();
            }
            if (cipherText.charAt(i) == ' ')
            {
                alpha += t.getValue();
                t = decodingTree;
            } 
        }
        return alpha;
    }

}
