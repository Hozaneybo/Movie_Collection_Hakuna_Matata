package be;

/**
 * The Category class represents a category, it has id and name properties
 */
public class Category {
    // ID of the category
    private int id;
    // Name of the category
    private String name;

    /**
     * Constructor to create a new category
     * @param id ID of the category
     * @param name Name of the category
     */
    public Category( int id, String name){
        this.id = id;
        this.name = name;
    }

    /**
     * Method to get the ID of the category
     * @return ID of the category
     */
    public int getId(){
        return id;
    }

    /**
     * Method to get the name of the category
     * @return name of the category
     */
    public String getName() {
        return name;
    }

    /**
     * Method to set the name of the category
     * @param name New name of the category
     */
    public void setName(String name) {
        this.name = name;
    }
}
