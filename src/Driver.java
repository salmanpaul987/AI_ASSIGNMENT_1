import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 *
 * @author Salman Saeed Paul
 */
public class Driver {

    static int m, n, t;
    static ArrayList<String> states;
    static ArrayList<String> actions;
    static int[][] transitionTable;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //System.out.println("Enter m ");
        m = input.nextInt();
        //System.out.println("Enter n");
        n = input.nextInt();
        //System.out.println("Enter t");
        t = input.nextInt();

        System.out.println("m=" + m);
        System.out.println("n=" +n);
        System.out.println("t=" + t);
        input.nextLine();
        //input.nextLine();

        states = new ArrayList<String>();
        for (int i = 0; i < m; i++) {
         //   System.out.println("Enter State "+i);
            states.add(input.nextLine());
        }
        for (int i = 0; i < m; i++) {
            System.out.println("State "+i +" : "+ states.get(i));
           
        }

        //input.nextLine();

        actions = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            actions.add(input.nextLine());
        }
        
        for (int i = 0; i < n; i++) {
            System.out.println("Action "+i +" : "+ actions.get(i));
           
        }
        

        transitionTable = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                transitionTable[i][j] = input.nextInt();
            }
        }
        for (int i = 0; i < m; i++) {
            
                System.out.println(transitionTable[i][0] +" "+transitionTable[i][1] + " "+transitionTable[i][2]);
            
        }

        input.nextLine();
        //input.nextLine();

        for (int i = 0; i < t; i++) {
            StringTokenizer t1 = new StringTokenizer(input.nextLine(), "\t");
            String start = (String) t1.nextElement();
            String end = (String) t1.nextElement();
            String[] p = new String[2];
            p[0] = start;
            p[1] = end;
            Node result = BFS(p, n);

            Stack<String> stack = new Stack<String>();
            while (result != null) {
                stack.push(result.action);
                result = result.parent;
            }
            stack.pop();
            while(!stack.isEmpty())
            {
                System.out.print(stack.pop() + "->");
            }
            System.out.println("");
        }
    }

    public static Node BFS(String[] problem, int noOfActions) {
        Node node = new Node(problem[0], null, "");
        Queue<Node> frontier = new LinkedList<>();
        frontier.add(node);
        Queue<String> explored = new LinkedList<>();
        while (true) {
            if (frontier.isEmpty()) {
                return null;
            }
            node = frontier.poll();
            if (goalTest(node, problem[1])) {
                return node;
            }
            explored.add(node.state);
            for (int i = 0; i < noOfActions; i++) {
                Node child = new Node(states.get(transitionTable[states.indexOf(node.state)][i]), node, actions.get(i));
                if (!isMemberOfFrontier(frontier, child) && !isMemberOfExploredSet(explored, child.state)) {
                    if (goalTest(child, problem[1])) {
                        return child;
                    } else {
                        frontier.add(child);
                    }
                }
            }
        }
    }


    public static boolean isMemberOfFrontier(Queue<Node> frontier, Node node) {
        for (Node item : frontier) {
            if (item.state == node.state) {
                return true;
            }
        }
        return false;
    }

    public static boolean isMemberOfExploredSet(Queue<String> ex, String state) {
        for (String str : ex) {
            if (str.equals(state)) {
                return true;
            }
        }
        return false;
    }

    public static boolean goalTest(Node node, String goal) {
        return node.state.equals(goal);
    }
}