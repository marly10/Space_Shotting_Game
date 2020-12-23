public class LinkedList
{
   //ListNode head;

   //member varaibles
   private ListNode head = null;
   static int numberofitems = 0;


    //linked list methods

    public LinkedList() //constructor that with no parameter
    {
        head = null;
        numberofitems = 0;
    }

   public  ListNode getNode(int index)
   {

       ListNode current = new ListNode();
       int count = 0;
       current = head;
       for(int i = 0; i < getSize(); i ++)
       {
           if(index == i)
           {
               if(current != null) //while(current != null) 
               {
                   return current;
               }
               System.out.println("The count: "+count+"Index is: "+index);

               System.out.println("Could not find specified node. You should not be seeing this. (LinkedList.java, getNode() method)");

           }
           count++;
      }
      assert(false);
      return null;
   }



   public void insert(/*LinkedList list, */Drone d_in)
   {

      ListNode newNode = new ListNode(d_in);
      ListNode headToBeMoved = head;

      if(head != null)
      {
         if(headToBeMoved.getNext() != null)
         {
            System.out.println("@@@@@@@@@@@@@@@@@ normal insertion");
            newNode.setNext(headToBeMoved);
            headToBeMoved.setPrevious(newNode); //should enable doubly-linked LinkedList
            head = newNode;
         }
         else
         {
            //getting to this point assumes that the head is not null, but there is no following node
            //therefore, new node must take the place of head, and set the previous head as it's "next"
            //subsequently, the previous head must set its "previous" as the new node
            
            head = newNode;
            head.setNext(headToBeMoved);
            headToBeMoved.setPrevious(head);
         }
      }
      else
      {
         System.out.println("head was null. setting new node as head.");
         head = newNode;
      }

       
      /*
       //new node is now the head
       head = newNode;

       ListNode last = head;

       while(last != null)
       {
          last = last.getNext();
       }
       numberofitems++;

      return list;
      */
      
      //^^^^^^^^^^
      //does any of the above do anything important???
      //^^^^^^^^^^
   }



    public  int getSize() //method for the size/lenght of linkedlist
    {
        //variables being used
        ListNode current = head;
        int temp = 0;

        while (current != null) //while loop used to traverse through the linked list(accessing every object)
        {
            //numberofitems++;
            temp++;
            current = current.getNext();
        }
        //return numberofitems;
        return temp;
    }
   
   
   public ListNode getHead()
   {
      return head;
   }
   
   
   public void changeHead(ListNode newHead_in)
   {
      //need to make sure that new head is linked to the next item in the list, if there is one
      if(head != null)
      {
         ListNode temp = head;
         head = newHead_in;
         head.setNext(temp);
         head.setPrevious(null);
      }
      else
      {
         head = newHead_in;
         head.setNext(null);
         head.setPrevious(null);
      }
   }
      

/*
    //clear method
   public void clearList()
   {
      //code here
      head = null;
      System.out.println("this should be clearing the llist of drones right now. It's not, because the code for it isn't there, but seeing this line means it's working.");
   }
*/
   
   
   /*
   //removeNode (based on index) method
   public void deleteNode(Drone key)
   {
       //variables being used
       ListNode current = head;
       int indexData = 0;

      if(current.d == null)
      {
         System.out.println("current.d is null");
      }

       while (current.getNext() != null) //while loop used to traverse through the linked list(accessing every object)
       {
           System.out.println("while loop is happening ajsdjkbdhjbvjkfbvgshdfg");
           
           current = current.getNext();
           indexData++;
           
           

           if(current.d == key)
           {
               for (int i = 0; i < indexData - 1; i++) 
               {
                   //previous node of the one found in the loop
                   current = current.getNext();
               }
               
               //unlinks the node from current and points over to the next one after current
               current.setNext(current.getNext().getNext());
           }
       }
   }*/
}