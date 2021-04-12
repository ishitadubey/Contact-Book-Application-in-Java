import java.util.*;
public class Doubly
{
    Scanner sc=new Scanner(System.in);
    static Node head; int count=0;
    public static void main(String args[])
    {
        Scanner scan=new Scanner(System.in);
        Doubly contact=new Doubly();
        do{
            System.out.println("---------Contact Book---------");
            System.out.println("Enter\n1. Insert Contact\n2. Delete a Contact\n3. View a Contact\n4. Update a Contact\n5. View all Contacts\n6. Exit");
            int x=scan.nextInt();
            int y;
            switch(x)
            {
                case 1:
                contact.insertcontact();
                break;

                case 2:
                if(contact.emptylist()){}
                else
                contact.delete();
                break;

                case 3:
                if(contact.emptylist()){}
                else{
                    System.out.println("Enter\n1. View a Contact By Name\n2. View a Contact By Number\n3. View a Contact By Email");
                    y=scan.nextInt();
                    if(y==1)
                    contact.viewbyname();
                    else if(y==2)
                    contact.viewbynum();
                    else
                    contact.viewbyemail();
                }
                break;

                case 4:
                if(contact.emptylist()){}
                else
                contact.update();
                break;

                case 5:
                contact.display();
                break;

                case 6:
                break;

                default:
                System.out.println("Wrong input");
                break;

            }
            if(x==6)
            {
                scan.close();
                break;
            }
        }while(true);
    }
    boolean emptylist()
    {
        if(head==null)
        {
            System.out.println("The contact list is empty!");
            return true;
        }
        else
        return false;
    }
    void insertcontact()
    {
        String name1, address1, email1, phone1;
        boolean val;
        do{ //perform at least once
            System.out.println("Enter contact details:");
            System.out.println("Enter name:");
            name1=sc.nextLine().toUpperCase();
            System.out.println("Enter number:");
            phone1=sc.nextLine();
            System.out.println("Enter address:");
            address1=sc.nextLine();
            System.out.println("Enter email:");
            email1=sc.nextLine();
            val=validate(name1, email1, phone1); //validate the details
        }while(!val); //if incorrect details

        Node node=new Node();
        node.name=name1;
        node.address=address1;
        node.email=email1;
        node.phone=phone1;
        node.next=null;

        if(head==null)
        head=node;
        else
        {
            Node ptr=head;
            while(ptr.next!=null)
            {
                ptr=ptr.next;
            }
            ptr.next=node;
            node.prev=ptr;
        }
        sort();
        System.out.println("Contact Inserted!");

    }
    boolean validate(String name2, String email2, String phone2)
    {
        if(name2.matches("^[a-zA-Z\\s]*$")) //regex for name (first name+last name) allowing only letters and spaces
            if(email2.matches("^(.+)@(.+)$")) //regex for email
                if(phone2.matches("[7-9][0-9]{9}")) //regex for phone number
                return true;
                else{
                    System.out.println("Invalid Phone number.");
                    return false;
                }
            else{
                System.out.println("Invalid Email Id.");
                return false;
            }
        else{
            System.out.println("Invalid Name.");
            return false;
        }
    }
    void delete()
    { //deletion by name
        System.out.print("Enter the name of the contact: ");
        String n=sc.nextLine().toUpperCase();
        Node ptr=head; 
        while(ptr!=null)
        {
            if(ptr.name.equals(n))
            {
                if(ptr==head)
                { //if name found at head
                    if(ptr.next==null)
                    head=null;
                    else{
                    head=ptr.next;
                    (ptr.next).prev=null;}
                }
                else if(ptr.next==null) //if name found at the end
                (ptr.prev).next=null;
                else
                { //if name neither at end nor beginning
                    (ptr.prev).next=ptr.next;
                    (ptr.next).prev=ptr.prev;
                }
                count++; // no break beacuse there may be duplicates
            }
            ptr=ptr.next;
        }
        if(count==0)//if no name found
        System.out.println("No such contact name.");
        else
        System.out.println("Contact deleted!");
    }

    String displayhead=String.format("%-20s","NAME")+" "+String.format("%-12s","PHONE NUMBER")+" "+String.format("%-20s","ADDRESS")+" "+String.format("%-15s","EMAIL ID");
    void viewbyname()
    {
        System.out.println("Enter the name of the contact you wish to view:");
        String n=sc.nextLine().toUpperCase();
        Node ptr=head; count=0;
        System.out.println(displayhead);
        while(ptr!=null)
        { 
            if(ptr.name.contains(n)) //if the contact conatins the input string
            {
                System.out.println(String.format("%-20s",ptr.name)+" "+String.format("%-12s",ptr.phone)+" "+String.format("%-20s",ptr.address)+" "+String.format("%-15s",ptr.email));
                count++;
            }
            ptr=ptr.next;
        }
        if(count==0)
        System.out.println("No such contact name.");
    }
    void viewbynum()
    {
        System.out.println("Enter the phone number of the contact you wish to view:");
        String n=sc.nextLine();
        Node ptr=head; count=0;
        System.out.println(displayhead);
        while(ptr!=null)
        {
            if(ptr.phone.contains(n))
            {
                System.out.println(String.format("%-20s",ptr.name)+" "+String.format("%-12s",ptr.phone)+" "+String.format("%-20s",ptr.address)+" "+String.format("%-15s",ptr.email));
                count++;
            }
            ptr=ptr.next;
        }
        if(count==0)
        System.out.println("No such contact with this number.");
    }
    void viewbyemail()
    {
        System.out.println("Enter the email id of the contact you wish to view:");
        String n=sc.nextLine();
        Node ptr=head; count=0;
        System.out.println(displayhead);
        while(ptr!=null)
        { 
            if(ptr.email.equals(n))
            {
                System.out.println(displayhead);
                System.out.println(String.format("%-20s",ptr.name)+" "+String.format("%-12s",ptr.phone)+" "+String.format("%-20s",ptr.address)+" "+String.format("%-15s",ptr.email));
                count++;
            }
            ptr=ptr.next;
        }
        if(count==0)
        System.out.println("No such contact with this email.");
    }
    void update()
    {
        System.out.println("Enter the name of the contact whose details you want to update: ");
        String n=sc.nextLine().toUpperCase();        
        Node ptr=head; String nn; count=0;
        while(ptr!=null)
        {
            if(ptr.name.equals(n))
            {
                System.out.println("Which detail do you want to update?\n1. Name\n2. Phone number\n3. Address\n4. Email");
                int ch=sc.nextInt();
                sc.nextLine();
                switch(ch)
                {
                    case 1:
                    System.out.println("Enter new name: ");
                    nn=sc.nextLine();
                    ptr.name=nn;
                    break;
                    
                    case 2:
                    System.out.println("Enter new phone number: ");
                    nn=sc.nextLine();
                    ptr.phone=nn;
                    break;

                    case 3:
                    System.out.println("Enter new address: ");
                    nn=sc.nextLine();
                    ptr.address=nn;
                    break;

                    case 4:
                    System.out.println("Enter new email id: ");
                    nn=sc.nextLine();
                    ptr.email=nn;
                    break;
                }
                System.out.println("Contact Updated!");
                count++;
                break;
            }
            ptr=ptr.next;
        }
        if(count==0)
        System.out.println("No such contact.");
    }
    void sort()
    {
        Node i, j;
	    for(i=head;i!=null;i=i.next)
	    {
	        for(j=i.next;j!=null;j=j.next)
	        {
	            if((i.name).compareTo(j.name)>0)
	            { //sort name, phone, address and email.
                    //swapping without using a third variable
                    i.name=i.name+j.name;
                    j.name=i.name.substring(0, i.name.length()-j.name.length());
                    i.name=i.name.substring(j.name.length());

                    i.phone=i.phone+j.phone;
                    j.phone=i.phone.substring(0, i.phone.length()-j.phone.length());
                    i.phone=i.phone.substring(j.phone.length());

                    i.address=i.address+j.address;
                    j.address=i.address.substring(0, i.address.length()-j.address.length());
                    i.address=i.address.substring(j.address.length());

                    i.email=i.email+j.email;
                    j.email=i.email.substring(0, i.email.length()-j.email.length());
                    i.email=i.email.substring(j.email.length());

	            }
	       }
        }
    }
    void display()
    { //to view all contacts
        if(emptylist()){}//check if the contatc list is empty
        else{
            Node ptr=head; 
            System.out.println("All Contacts: ");
            System.out.println(displayhead);
            while(ptr!=null)
            { 
                System.out.println(String.format("%-20s",ptr.name)+" "+String.format("%-12s",ptr.phone)+" "+String.format("%-20s",ptr.address)+" "+String.format("%-15s",ptr.email));//String.format(,) function to set the padding and width of each attribute of contact.
                ptr=ptr.next;
            }
        }
    }
}
