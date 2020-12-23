class ListNode //nodes contain drones
{
   Drone d;
   private ListNode next;
   private ListNode previous;

   //ListNode head;

   //constructor with empty parameter when instantiate by creating an object from the class
   public ListNode()
   {
      next = null;
      previous = null;
   }
   
   public ListNode(Drone d_in)
   {
      d = d_in;
      next = null;
      previous = null;
   }
   //sets nodes values passing an int parameter
   public void setData(Drone d)//sets the int data to the member private variable
   {
      this.d = d;
   }

   //sets the next nodes value passing a node parameter(unlinks the node )
   public void setNext(ListNode next)
   {
      //sets the LinkedListNode data to the member private variable
      this.next = next;
   }
   
   public void setPrevious(ListNode previous_in)
   {
      this.previous = previous_in;
   }

   //if head = 3 head.getNext()->2
   public ListNode getNext()
   {
      return next;
   }
   
   public ListNode getPrevious()
   {
      return previous;
   }

   public Drone getData()//head.getData()->3 head.getNext().getData()->2
   {
      return d;
   }

   //lines 44 to 66 are highly questionable 
   public Drone getDrone()
   {
      if(d == null)
      {
         System.out.println("d is null. this should not be happening");
      }
      return d;
   }
   
   
   
   //for setting removal flags
   public boolean hasNext()
   {
      if(next == null)
         return false;
      else
         return true;
   }
   public boolean hasPrevious()
   {
      if(previous == null)
         return false;
      else
         return true;      
   }
} 