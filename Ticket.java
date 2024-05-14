import java.io.FileWriter;
import java.io.IOException;
public class Ticket {
    //Declaring instance variables
    private char row;
    private int seat;
    private int price;
    private Person person1;

    /*Declaring and initializing the constructor*/
    public Ticket(char row,int seat,int price,Person person1){
        /*(Using of 'this' keyword to mention that it refers to the specific instance variable)*/
        this.row=row;
        this.seat=seat;
        this.price=price;
        this.person1=person1;
    }

    /*A method to print the ticket details*/
    public static void ticketinfo(Ticket details){
        System.out.println("Ticket = "+(details.getRow())+details.getSeat());         // the seat row and seat number
        System.out.println("Price = ₤"+details.getPrice());                                             // the ticket price
        System.out.println("Name : "+details.getPerson1().getName()+" "+details.getPerson1().getSurname());     //the name and the surname of the person
        System.out.println("Email : "+details.getPerson1().getEmail());                                 //The email of the person
    }

    /*All the getters and setters*/
    public char getRow(){       //Getter for the seat row
        return this.row;
    }

    public void setRow(char row){           //Setter for the seat row
        this.row=row;
    }

    public int getSeat(){           //Getter for the seat number
        return this.seat;
    }

    public void setSeat(int seat){              //Setter for the seat number
        this.seat=seat;
    }

    public int getPrice(){              //Getter for the price
        return this.price;
    }

    public void setPrice(int price){            //Setter for the price
        this.price=price;
    }

    public Person getPerson1(){                     //Getter for the person
        return this.person1=person1;
    }

    public void setPerson1(Person person1){         //Setter for the person
        this.person1=person1;
    }

    /*Save method to save the content in a text file*/
    public static void save(Ticket ticket1){
        try {
            /*FileWriter object*/
            FileWriter writer = new FileWriter(Character.toString(ticket1.getRow()) + ticket1.getSeat() + ".txt");
            writer.write("-Person Details-\nName : "+ticket1.getPerson1().getName() +" "+ ticket1.getPerson1().getSurname());
            writer.write("\nEmail : "+ticket1.getPerson1().getEmail());
            writer.write("\n\n-Ticket Details- \nSeat : "+ticket1.getRow()+ ticket1.getSeat());
            writer.write("\nPrice = ₤"+ticket1.getPrice());
            writer.close();
        } catch (IOException e) {
            System.out.println("Error found in file handling");;
        }
    }
}
