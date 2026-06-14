// Abstract parent class for all person-related entities
public abstract class Person {

    // Store name of the person
    protected String name;

    // Constructor to initialize name
    public Person(String name) {
        this.name = name;
    }

    // Getter method to return name
    public String getName() {
        return name;
    }

    // Abstract method that must be implemented
    // by all child classes
    public abstract void display();

}
