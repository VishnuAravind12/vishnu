# My Java Journey in First Tri (Not in Order, only the Somewhat Advanced Stuff)

## 1. **HashMaps and Data Structures**
- **Key Takeaways**:
  - Learned the basics of **HashMaps** in Java, focusing on key-value pair structure.
  - Explored methods like `put()`, `get()`, and `remove()`.
  - Understood the importance of HashMaps in handling non-sequential data.
  - Grasped the internal workings of HashMaps, like hashing and collision handling.
  - Discussed real-world applications of HashMaps.
- **Code Snippet**:
  ```java
  HashMap<String, Integer> map = new HashMap<>();
  map.put("key1", 100);
  int value = map.get("key1");
  System.out.println("Value: " + value);
  ```

## 2. **Arrays and Their Manipulations**:
- **Key Takeaways**:
  - Delved into array declaration, initialization, and accessing elements.
  - Covered array traversal using loops.
  - Discussed the fixed-size nature of arrays and its implications
  - Explored multi-dimensional arrays.
  - Touched on common algorithms like sorting and searching in arrays.
- **Code Snippet**:
  ```java
  int[] arr = {1, 2, 3, 4};
  for(int num : arr) {
      System.out.println(num);
  }
  ```

## 3. **Understanding Booleans and Conditional Logic**
- **Key Takeaways**:
  - Grasped the fundamental Boolean operators: AND, OR, and NOT.
  - Learned about conditional statements like `if`, `else if`, and `else`.
  - Discussed the use of Boolean expressions in decision-making.
  - Explored complex conditional statements with nested if-else structures.
  - Covered the concept of short-circuit evaluation in Boolean logic.
- **Code Snippet**:
  ```java
  boolean condition = true;
  if (condition) {
      System.out.println("True");
  } else {
      System.out.println("False");
  }
  ```

## 4. **Iterations and Looping Constructs**
- **Key Takeaways**:
  - Mastered the use of for-loops for indexed iteration.
  - Explored while-loops for condition-based iteration.
  - Learned about do-while loops and their use-cases.
  - Discussed the importance of loop control statements like `break` and `continue`.
  - Covered nested loops and their applications in complex algorithms.
- **Code Snippet**:
  ```java
  for(int i = 0; i < 5; i++) {
      System.out.println("i: " + i);
  }
  ```

## 5. **Advanced Array Operations**
- **Key Takeaways**:
  - Delved into dynamic arrays and their advantages.
  - Covered advanced array manipulation techniques.
  - Explored the use of arrays in data structures like stacks and queues.
  - Discussed array algorithms for sorting and searching.
  - Analyzed the time complexity of various array operations.
- **Code Snippet**:
  ```java
  int[] arr = {5, 4, 3, 2, 1};
  Arrays.sort(arr);
  System.out.println(Arrays.toString(arr));
  ```

## 6. **ArrayList - Dynamic Arrays**
- **Key Takeaways**:
  - Learned about the ArrayList class in Java and its dynamic nature.
  - Explored methods like `add()`, `remove()`, `set()`, and `get()`.
  - Discussed the auto-resizing feature of ArrayLists.
  - Compared the performance of ArrayLists with traditional arrays.
  - Covered practical use-cases of ArrayLists in programming.
- **Code Snippet**:
  ```java
  ArrayList<String> list = new ArrayList<>();
  list.add("Hello");
  list.add("World");
  System.out.println(list);
  ```

## 7. **2D Arrays and Multidimensional Data Handling**
- **Key Takeaways**:
  - Mastered the concept of 2D arrays and their applications.
  - Learned how to iterate over rows and columns of a 2D array.
  - Explored real-world examples where 2D arrays are useful.
  - Discussed memory layout of 2D arrays.
  - Analyzed common algorithms and operations on 2D arrays.
- **Code Snippet**:
  ```java
  int[][] matrix = { {1, 2}, {3, 4} };
  for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
          System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
  }
  ```

## 8. **Inheritance in Object-Oriented Programming**
- **Key Takeaways**:
  - Gained an understanding of the concept of inheritance in OOP.
  - Learned about class hierarchies and the reuse of code through inheritance.
  - Discussed the difference between inheritance and composition.
  - Explored the use of `super` keyword and method overriding.
  - Covered practical examples demonstrating the power of inheritance.
- **Code Snippet**:
  ```java
  class Animal {
      void eat() {
          System.out.println("Animal eats");
      }
  }

  class Dog extends Animal {
      void bark() {
          System.out.println("Dog barks");
      }
  }

  Dog dog = new Dog();
  dog.eat();
  dog.bark();
  ```