//Person.java
public class Person{
    //Declaring instance variables
    private String name;
    private String surname;
    private String email;

    //Initializing the Constructor
    public Person(String name,String surname,String email){
        this.name=name;             //(Using of 'this' keyword to mention that it refers to the specific instance variable)
        this.surname=surname;
        this.email=email;
    }

    //Initializing the getter and setters
    public String getName(){                //Getter for the name
        return this.name;
    }

    public void setName(String name){       //Setter for the name
        this.name=name;
    }

    public String getSurname(){             //Getter for the surname
        return this.surname;
    }

    public void setSurname(String surname){         //Setter for the surname
        this.surname=surname;
    }

    public String getEmail(){               //Getter for the email
        return this.email;
    }

    public void setEmail(String email){         //Setter for the email
        this.email=email;
    }
}
