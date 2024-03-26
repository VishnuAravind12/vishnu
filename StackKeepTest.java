public class StackKeepTest {
    public int n = 6; // primitive data type on heap
    public static void changeIntMethod(int nValue, int nRefn, StackKeepTest nRef, Integer wrapN, String charL) {
        System.out.println("\tBefore Increment: " + nValue + ", nRefn: " + nRefn + ", nRef.n: " + nRef.n + ", wrapN: " + wrapN + " with hash " + System.identityHashCode(wrapN));
        nValue += 10;
        nRefn += 10;
        nRef.n += 10;
        wrapN += 10;
        System.out.println("\tAfter Increment: " + nValue + ", nRefn: " + nRefn + ", nRef.n: " + nRef.n + ", wrapN: " + wrapN + " with hash " + System.identityHashCode(wrapN));
    }
    public static void main(String[] args) {
        int n = 6; // primitive data type on the stack
        Integer wrapN = 6;
        StackKeepTest nRef = new StackKeepTest();
        System.out.println("Before the method: " + n + " with hash " + System.identityHashCode(n) + ", nRef: " + nRef + " or " + System.identityHashCode(nRef) + ", nRef.n: " + nRef.n + System.identityHashCode(nRef.n) + ", wrapN: " + wrapN + " with hash " + System.identityHashCode(wrapN));
        changeIntMethod(n, nRef.n, nRef, wrapN);
        System.out.println("After the method: " + n + " with hash " + System.identityHashCode(n) + ", nRef: " + nRef + " or " + System.identityHashCode(nRef) + ", nRef.n: " + nRef.n + System.identityHashCode(nRef.n) + ", wrapN: " + wrapN + " with hash " + System.identityHashCode(wrapN));
    }
}