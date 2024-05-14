/*PART A*/
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;

public class PlaneManagement {
    /*Initializing and declaring the seats method by using a ragged array*/
    private static int[][] seats = new int[][]{
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    };
    /*Declaring an array (tickets object) of the Ticket class to store the tickets that are booked*/
    private static Ticket[] tickets=new Ticket[52];

    //Declaring a variables to get the total price of  tickets
    private static int totalPrice;

    /*Declaring the ticket object*/
    private static Ticket t1;

    /*Main method*/
    public static void main(String[] args) {
        boolean continue_=true;          //Loop control variable
        while(continue_) {            //Implementing a while loop to control the program if the user enters a string or a character
            try {
                Scanner input = new Scanner(System.in);
                System.out.println("\nWelcome to the Plane Management application!!");

                /*Checks for the user input choice using a do-while loop*/
                int user_choice;    //Declaring a variables to store user's choice
                do {
                    System.out.println("\n***********************************************");
                    System.out.println("**                MENU OPTIONS               **");
                    System.out.println("***********************************************");
                    System.out.println("1)Buy a seat");
                    System.out.println("2)Cancel a seat");
                    System.out.println("3)Find first available seat");
                    System.out.println("4)Show seating plan");
                    System.out.println("5)Print tickets information and total sales");
                    System.out.println("6)Search ticket");
                    System.out.println("0)Quit");
                    System.out.print("Please select an option :");
                    user_choice = input.nextInt();

                    switch (user_choice) {
                        case (1):
                            System.out.println();
                            buy_seat();
                            break;

                        case (2):
                            System.out.println();
                            cancel_seat();
                            break;

                        case (3):
                            System.out.println();
                            System.out.println("Finding the first seat available...");
                            find_first_available();
                            break;

                        case (4):
                            System.out.println();
                            System.out.println("Showing the seat plan...\n'O' Shows the seats available\n'X' shows the seats which are been booked\n");
                            show_seating_plan();
                            break;

                        case (5):
                            System.out.println();
                            print_ticket_info();
                            break;

                        case (6):
                            System.out.println();
                            search_seat();
                            break;

                        case (0):
                            System.out.println();
                            System.out.println("Exiting from the ticket menu...");
                            continue_=false;          //If the user enters 0, exiting the program
                            break;

                        default:
                            System.out.println();
                            System.out.println("Invalid option");
                    }
                }
                while (user_choice != 0);
            }
            catch(InputMismatchException error){
                System.out.println("Invalid option entered");
            }
        }
    }
    public static void buy_seat() {
        Scanner input = new Scanner(System.in);         //Initializing the Scanner object
        System.out.println("NOTE : The system only refer to the first letter you enter:)");
        System.out.print("Enter the seat row letter(A-D) :");
        char seatRowLetter = input.next().toUpperCase().charAt(0);    //Converting the user input to uppercase and only using the first character of it

        /*Checking the validity of the seat letter entered by the user*/
        boolean seatRowLetterValidity = true;     //Declaring an initializing a variables to run the while loop
        while (seatRowLetterValidity) {

            if (seatRowLetter == 'B' || seatRowLetter == 'C') {
                System.out.print("Enter the seat number(1-12) :");
                seatRowLetterValidity = false;
            }
            else if (seatRowLetter == 'A' || seatRowLetter == 'D') {
                System.out.print("Enter the seat number(1-14) :");
                seatRowLetterValidity = false;
            }
            /*If user inputs invalid data*/
            else {
                System.out.println("Invalid Seat Letter(Try (A-D))");
                System.out.print("Enter the seat letter again : ");         //requests for the seat letter again
                seatRowLetter = input.next().toUpperCase().charAt(0);
            }
        }

        int seat_Number = input.nextInt();  //Getting the seat number

        /*Checking the row of the ragged array(seats) using the ASCII representation of the letter;*/
        int array_RowIndex = seatRowLetter - 'A';

        /*Checking the validity of the data entered by the user*/
        boolean seat_NumberValidity = true;
        while (seat_NumberValidity) {
            if (array_RowIndex < 0 || array_RowIndex > seats.length || seat_Number <= 0 || seat_Number > seats[array_RowIndex].length) {
                System.out.println("Invalid seat Number");
                System.out.println("\nSeat numbers(1-14) for row letters A or D\nSeat numbers(1-12) for row letters B or C");
                System.out.print("Enter the seat number again : ");
                seat_Number = input.nextInt();
            }
            else {
                seat_NumberValidity = false;      //If the user has entered a valid seat number --> Exits the loop.
            }
        }

        /*Assigning prices to the seat ranges*/
        int price = 0;
        if (seat_Number >= 1 && seat_Number <= 5){
            price = 200;
        } else if (seat_Number >= 6 && seat_Number <= 9){
            price = 150;
        } else {
            price = 180;
        }

        /*If the seat is already booked*/
        if (seats[array_RowIndex][seat_Number - 1] == 1){
            System.out.println("Sorry, this seat is already booked");
            return;                         //Returns to the menu if the seat is booked
        }

        /*Validating the username*/
        /*Should implement as strings though they are characters since the name is of type string*/
        String[] characters={"0","1","2","3","4","5","6","7","8","9","!","@","#","$","%","^","&","*","(",")","_","-","=","+","|","{","}","[","]",":",";","/",">","<",",",".","'"};
        System.out.print("Enter your name :");
        String name = input.next();

        boolean nameInvalid=true;
        while(nameInvalid) {
            if(name.length()>=3) {
                boolean invalidCharFound=false;                 //A variable to control the while loop(Loop control variable)
                for (int onecharacter = 0; onecharacter < characters.length; onecharacter++) {           //Checks for all the characters in the 'characters' array
                    for(int namechar=0;namechar<name.length();namechar++) {              //Checks one character of the name with all the characters
                        if (name.substring(namechar,namechar+1).equals(characters[onecharacter])) {
                            System.out.println("Invalid name entered");
                            System.out.println("Invalid character found = "+characters[onecharacter]);     //Shows what the invalid character is
                            System.out.print("Enter the name again : ");
                            name = input.next();
                            invalidCharFound=true;
                            break;                  //Breaks the loop once an invalid character is found
                        }
                    }
                }
                if(!invalidCharFound){
                    nameInvalid=false;            //Exiting the while loop if the name is valid
                }
            }
            else{
                System.out.println("Please enter a name which has 3 or more characters");
                System.out.print("Enter the name again : ");
                name= input.next();
            }
        }

        /*Validating the surname*/
        System.out.print("Enter your surname :");
        String surname = input.next();
        boolean surnameInvalid=true;                  //A variable to control the loop(Loop control variable)
        while(surnameInvalid) {
            if(surname.length()>=3) {
                boolean invalidCharFound=false;             //A variable to control the while loop(Loop control variable)
                for (int onecharacter = 0; onecharacter < characters.length; onecharacter++) {
                    for(int surnamechar=0;surnamechar<surname.length();surnamechar++) {
                        if (surname.substring(surnamechar,surnamechar+1).equals(characters[onecharacter])) {
                            System.out.println("Invalid surname entered");
                            System.out.print("Enter the surname again : ");
                            surname = input.next();
                            invalidCharFound=true;
                            break;                  //Breaks the loop once an invalid character is found
                        }
                    }

                }
                if(!invalidCharFound){
                    surnameInvalid=false;               //Exiting the while loop of a valid surname is entered
                }
            }
            else{
                System.out.println("Please enter a surname which has 3 or more characters");
                System.out.print("Enter the surname again : ");
                surname= input.next();
            }
        }

        /*Validating the email entered by the user*/
        boolean emailValidity = true;           //Loop control variable
        while (emailValidity) {
            System.out.print("Enter your email :");
            String email = input.next();
            if (email.contains("@") && email.endsWith(".com") || email.endsWith(".lk")) {

                //Creating the person1 object for the class 'Person'
                Person Person1 = new Person(name, surname, email);

                //Creating the Ticket object
                t1 = new Ticket(seatRowLetter, seat_Number, price, Person1);

                /*Calling the function in Ticket class to print the ticket details along with the person details*/
                Ticket.ticketinfo(t1);

                for (int i = 0; i < tickets.length; i++) {
                    if (tickets[i] == null) {       //Checking whether the array index is null before appending
                        tickets[i] = t1;            //Appending the seat letter and the number to the 'tickets' array
                        break;                      //Breaking the loop once the appending is done
                    }
                }

                Ticket.save(t1);            //Calling the save method in 'Ticket' class to save the information in a text file
                break;       //Breaking the while loop if the email has been entered correctly
            }
            else {
                System.out.println("Invalid email.Email example(*****@gmail.com , *****@hotmail.com)'");
            }
        }

        /*Marking the seat as booked*/
        if (seats[array_RowIndex][seat_Number-1]==0){
            seats[array_RowIndex][seat_Number - 1] = 1;
            System.out.println("Your seat " + seatRowLetter + seat_Number + " is booked successfully");
        }
    }

    /*Cancel seat method*/
    public static void cancel_seat(){
        Scanner input = new Scanner(System.in);
        System.out.println("NOTE : The system only refer to the first letter you enter:)");
        System.out.print("Enter the seat row letter you want to cancel (A-D):");
        char seatRowLetter=input.next().toUpperCase().charAt(0);

        boolean seatRowLetterValidity=true;         //Loop control variable
        while(seatRowLetterValidity) {
            /*Checking for the validity*/
            if (seatRowLetter == 'B' || seatRowLetter == 'C') {
                System.out.print("Enter the seat row number you want to cancel(1-12) :");
                seatRowLetterValidity=false;            //Exiting the while loop
            }
            else if (seatRowLetter == 'A' || seatRowLetter == 'D') {
                System.out.print("Enter the seat row number you want to cancel(1-14) :");
                seatRowLetterValidity=false;            //Exiting the while loop
            }
            else {
                //Asking the user to input the seat letter until the input is valid
                System.out.println("Invalid seat letter");
                System.out.print("Enter the seat letter again : ");
                seatRowLetter=input.next().toUpperCase().charAt(0);
            }
        }

        int seat_Number= input.nextInt();

            //Declaring a variable to get the array index
            int array_row = seatRowLetter - 'A';

            boolean seat_NumberValidity=true;               //Loop control variable
            while(seat_NumberValidity) {
                /*Checking the validity of the data entered*/
                if (array_row <0 || array_row > seats.length || seat_Number <=0 || seat_Number > seats[array_row].length) {
                    System.out.println("Invalid seat row number");
                    System.out.println("\nTry (1-14) if the seat letter is A or D\nTry (1-12) if the seat letter is B or C");
                    System.out.print("Enter the seat row number again : ");
                    seat_Number= input.nextInt();
                }

                /*Cancelling the seat if the seat is booked*/
                else if (seats[array_row][seat_Number - 1] == 1) {
                    seats[array_row][seat_Number - 1] = 0;
                    System.out.println("Your seat " + seatRowLetter + seat_Number + " has been successfully cancelled");
                    seat_NumberValidity=false;
                    File f1=new File(t1.getRow()+t1.getSeat()+".txt");
                    f1.delete();
                }

                //Printing a message if the seat is not booked
                else {
                    System.out.println("Sorry this seat hasn't been booked yet.\nHence cannot be cancelled.");
                    seat_NumberValidity=false;
                }
            }

            /*Updating the 'tickets' array*/
        for(int index=0;index< tickets.length;index++) {
            if(tickets[index]!=null) {
                if (tickets[index].getRow() == seatRowLetter && tickets[index].getSeat() == seat_Number){   //Checking whether the seat is already booked or not before removing it from the array
                    tickets[index] = null;                  //Assigning the array position as null
                    if (tickets[index] == null){
                        System.out.println(" ");
                    }
                }
            }
        }
    }

    //Find first seat available method
    public static void find_first_available(){
        for(int row=0;row< seats.length;row++){             //A variable to go through the rows(A.B.C.D)
            for(int col=0;col<seats[row].length;col++){     //A variable to go through the columns of each row
                if(seats[row][col]==0){
                    char seatPosition=(char)('A'+row);      //Initializing a variable to store the seat letter
                    System.out.println("First available seat is "+seatPosition+(col+1));
                    return;             //Exits the loop after printing the first available seat without printing all the available seats
                }
            }
        }
    }

/*Showing the seat plan */
    public static void show_seating_plan(){
        for(int row=0;row< seats.length;row++){         //A variable to loop within the rows of the array
            switch (row){
                case 0:
                    System.out.print("A : ");
                    break;

                case 1:
                    System.out.println();
                    System.out.print("B : ");
                    break;

                case 2:
                    System.out.println();
                    System.out.print("C : ");
                    break;

                case 3:
                    System.out.println();
                    System.out.print("D : ");
                    break;
            }
            for(int col=0;col<seats[row].length;col++){         //A variable to loop through the columns of the array
                if(seats[row][col]==0){
                    System.out.print("O"+ " ");         //Printing 'O' if the seat is free
                }
                else if(seats[row][col]==1){
                    System.out.print("X"+" ");          //Printing 'X' if the seat is booked
                }
            }
        }
    }

    public static void print_ticket_info(){
        /*Declaring and initializing a variable to get the total price of the tickets*/
        totalPrice=0;
        for(int index=0;index< tickets.length;index++){
            /*Checking if the array index is not null, if so printing the ticket in that position*/
            if(tickets[index]!=null){
                /*Passes the ticket index as the parameter to the method and prints the ticket details accordingly*/
                Ticket.ticketinfo(tickets[index]);
                System.out.println();               //A line space when displaying ticket information
                totalPrice+=tickets[index].getPrice();              //Adding the ticket prices to get the final amount
            }
        }
        System.out.println("Total price = ₤"+totalPrice);           //Printing the total price of the tickets
    }

    /*Search seat method*/
    public static void search_seat() {
        System.out.println("Searching Seat...");
        Scanner input = new Scanner(System.in);

        System.out.println("NOTE : The system only refer to the first letter you enter:)");
        System.out.print("Enter the seat row (A-D) you want to search :");
        char seatRowLetter = input.next().toUpperCase().charAt(0);

        boolean seatRowLetterValidity = true;         //(Loop control variable)
        while (seatRowLetterValidity) {
            /*Checking the validity of the user inputs*/
            if (seatRowLetter == 'A' || seatRowLetter == 'D') {
                System.out.print("Enter the seat number(1-14) you want to search :");
                seatRowLetterValidity = false;      //Exiting the loop
            }
            else if (seatRowLetter == 'B' || seatRowLetter == 'C') {
                System.out.print("Enter the seat number(1-12) you want to search :");
                seatRowLetterValidity = false;      //Exiting the loop
            }
            else {
                System.out.println("Invalid input\n");
                System.out.print("Enter the seat letter(A-D) : ");          //Prompting a message to fill the seat letter again
                seatRowLetter = input.next().toUpperCase().charAt(0);
            }
        }

        int seatNumber = input.nextInt();

        boolean seatNumberValidity = true;        //Loop control variable
        while (seatNumberValidity) {
            /*Checking for the validity of the data entered*/
            if ((seatRowLetter == 'A' || seatRowLetter == 'D') && (seatNumber > 0 && seatNumber < 15) || (seatRowLetter == 'B' || seatRowLetter == 'C') && (seatNumber > 0 && seatNumber < 13)) {
                if (seats[seatRowLetter - 'A'][seatNumber - 1] == 1) {          //Checking whether the seat is booked or not
                    for (int index = 0; index < tickets.length; index++) {
                        /*Checking if the array index is not null, if so printing the ticket in that position*/
                        if (tickets[index] != null) {
                            if(tickets[index].getRow()==seatRowLetter && tickets[index].getSeat()==seatNumber) {
                                System.out.println("\nSORRY! This seat has been booked");
                                Ticket.ticketinfo(tickets[index]);          //Calls the ticketinfo method with the index as the parameter
                                seatNumberValidity = false;
                                break;
                            }
                        }
                    }
                }
                else {
                    System.out.println("Good for you, this seat is available");
                    seatNumberValidity = false;
                }
            }
            else {
                System.out.println("Invalid seat number");
                System.out.print("Enter the seat number again :");
                seatNumber = input.nextInt();
            }
        }
    }
}
