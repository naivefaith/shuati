import java.util.*;

//剑指II 037
public class AsteroidCollision {
    public int[] asteroidCollision(int[] asteroids) {
        ArrayDeque<Integer> myStack = new ArrayDeque<>();
        myStack.add(-1);
        for (int i = 0; i < asteroids.length; i++) {
            if (myStack.getLast() < 0 || asteroids[i] > 0) {
                myStack.addLast(asteroids[i]);
            }else {
                if (myStack.getLast() < -asteroids[i]) {
                    myStack.removeLast();
                    i--;
                    continue;
                }else if(myStack.getLast() == -asteroids[i]){
                    myStack.removeLast();
                }
            }
        }
        myStack.pollFirst();
        int stackSize = myStack.size();
        int[] result = new int[myStack.size()];
        for (int i = 0; i < stackSize; i++) {
            result[i] = myStack.pollFirst();
        }
        return result;
    }
}
