package MovieTicketBooking;
import java.util.*;
class movies{
    String mvName;
    String mvTime;
    String mvPrice;
    int availSeat=100;
    int noftic;
    int[][] seats = new int[10][10];
    Scanner sc = new Scanner(System.in);
    movies(String mvName,String mvPrice,String mvTime){
        this.mvName=mvName;
        this.mvPrice=mvPrice;
        this.mvTime=mvTime;
        int s=1;
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                seats[i][j]=s;
                s++;
            }
        }
    }
    public void showMovie(){
        System.out.println("-------------------------------------");
        System.out.println("Movie Name:"+mvName);
        System.out.println("Movie Time:"+mvTime);
        System.out.println("Movie Price:"+mvPrice);
        System.out.println("Seat Availability:"+availSeat);
        System.out.println("-------------------------------------");
    }
    public int[] book(){
        System.out.println("Enter no of tickets you want to book:");
        noftic=sc.nextInt();
        viewSeat();
        int[] bookseat = new int[noftic];
        for(int i=0;i<noftic;i++){
            System.out.println(" Enter the seat no  for person:"+(i+1));
            int seno=sc.nextInt();
            for(int i1=0;i1<10;i1++){
                for(int j=0;j<10;j++){
                    if(seats[i1][j]==seno){
                        seats[i1][j]= -1;
                        bookseat[i]=seno;
                    }
                }
            }
            System.out.println(seno+" is booked");
        }
        return bookseat;
    }
    public void viewSeat(){
        System.out.println("--------------------------------------------------");
        System.out.println("----------------------SCREEN----------------------");
        System.out.println("--------------------------------------------------");
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                System.out.print(" | "+seats[i][j]);
            }
            System.out.println("\n-------------------------------------------------");
        }
        System.out.println("\n-1 means Seat is booked..\n");
    }
}
class ticket{
    int ticketId;
    String mvName;
    String mvTime;
    String mvPrice;
    int tofper;
    int[] bookseats;
    ticket(int ticketId,String mvName,String mvTime, String mvPrice,int tofper,int[] bookseats){
        this.ticketId=ticketId;
        this.mvName=mvName;
        this.mvTime=mvTime;
        this.mvPrice=mvPrice;
        this.tofper=tofper;
        this.bookseats=bookseats;
    }
    public void showTicket(){
        System.out.println("-------------------------------------------------------");
        System.out.println("Ticket Id : "+ticketId);
        System.out.println("Movie Name : "+mvName);
        System.out.println("Movie Time : "+mvTime);
        System.out.println("Ticket Price : "+mvPrice);
        System.out.println("Total no of persons :"+tofper);
        System.out.println("seats are :"+Arrays.toString(bookseats));
        System.out.println("-------------------------------------------------------");

    }

}
class ticketBS{
    Scanner sc = new Scanner(System.in);
    String mvname="Toy Story",mvprice="$50",mvtime="10 AM";
    ArrayList<movies> mov = new ArrayList<movies>();
    ArrayList<ticket> tickets = new ArrayList<ticket>();
    public void getChoice(){
        System.out.println("1.Show Movies");
        System.out.println("2.Book Ticket");
        System.out.println("3.View Ticket");
        System.out.println("4.Cancel Ticket");
        System.out.println("5.Exit");
        int ch = sc.nextInt();
        switch(ch){
            case 1:{
                mov.add(new movies(mvname,mvprice,mvtime));
                movies m2 = new movies("BOSS BABY","$45","1 PM");
                mov.add(m2);
                movies m3 = new movies("ICE AGE","$65","5 PM");
                mov.add(m3);
                for(movies x:mov){
                    x.showMovie();
                }
                getChoice();
                break;
            }
            case 2:{
                sc.nextLine();
                System.out.println("Enter movie Name:");
                String mn=sc.nextLine();
                String mt="";
                String pr="";
                int noftic=0;
                int loc=0;
                for(movies x:mov){
                    if((x.mvName).equalsIgnoreCase(mn)){
                        mn=x.mvName;
                        mt=x.mvTime;
                        pr=x.mvPrice;
                        noftic=x.noftic;
                        int[] seats=x.book();
                        mov.set(loc,x);
                        int id=tickets.size();
                        tickets.add(new ticket(id,mn,mt,pr,x.noftic,seats));
                        System.out.println("your Ticket Id is "+id);
                        System.out.println("seats are :"+Arrays.toString(seats));
                    }
                    loc++;
                }
                getChoice();
                break;
            }
            case 3:{
                sc.nextLine();
                System.out.println("Enter ticket id:");
                int tid=sc.nextInt();
                ticket t= tickets.get(tid);
                t.showTicket();
                getChoice();
                break;
            }
            case 4:{
                sc.nextLine();
                System.out.println("Enter ticket id:");
                int tid=sc.nextInt();
                ticket t= tickets.get(tid);
                int loc1=0;
                for(movies a:mov){
                    if((a.mvName).equalsIgnoreCase(t.mvName)){
                        for(int sn:t.bookseats){
                            int ids=1;
                            for(int i=0;i<10;i++){
                                for(int j=0;j<10;j++){
                                    if(ids==sn){
                                        a.seats[i][j]=ids;
                                    }
                                    ids++;
                                }
                            }
                        }
                        mov.set(loc1,a);
                    }
                    loc1++;
                }
                tickets.remove(tid);
                System.out.println("Ticket cancelled...successfully");
                getChoice();
                break;
            }
            case 5:{
                return;
            }
            default:{
                System.out.println("Sorry Invalid Option");
                break;
            }
        }
    }
    public static void main(String args[]){
        ticketBS tbs = new ticketBS();
        tbs.getChoice();
    }
}