import java.util.LinkedList;

public class Hashing{
static  class HashMap<K,V>{
    //Node
        private class Node{
          K key;
          V value;
         public  Node(K key, V value){
            this.key=key;
            this.value=value;
          }
        }

        private int n;//number of nodes
        private int N;//size of nodes
        public LinkedList<Node> buckets[];//int arr[];

        //constructor of HashMap
        @SuppressWarnings("unchecked")
   public HashMap(){
      this.N=4;
      this.buckets=new LinkedList[N];
      for(int i=0 ;  i<N  ;  i++){
          buckets[i]=new LinkedList<>();
      }
   }
   //hash function =>black box
  private int hashFunction (K key){
    return Math.abs(key.hashCode()) %  N;
  }
  //data index
 
 private int searchInLL(K key , int bi){
  LinkedList<Node> ll=buckets[bi];
  for(int i=0 ; i<ll.size() ; i++){
    if(ll.get(i).key==key){
          return i;
    }
  }
  return -1;
 }



//rehash
 @SuppressWarnings("unchecked")
private void rehash(){

  LinkedList<Node> oldBucket[]=buckets;
  int oldN=N;
  N=2*oldN;
  buckets=new LinkedList[N];

  //initialize
for(int i=0;i<N ; i++){
  buckets[i]=new LinkedList<>();
}
//re--insert
for(int i=0 ; i<oldBucket.length ; i++){
     for(int j=0 ; j<oldBucket[i].size() ; j++){
      Node node=oldBucket[i].get(j);
      put(node.key, node.value);
     }
}
}





   public void put(K key , V value){
    int bi=hashFunction(key);
    int di=searchInLL(key,bi);
     if(di==-1){
      buckets[bi].add(new Node(key ,value));
      n++;
     }else{
      Node node=buckets[bi].get(di);
      node.value=value;
     }
     double lambda=(double) n/N;
     if(lambda  > 2.0){
       rehash();
     }
   }

   public V get(K key){
     int bi=hashFunction(key);
    int di=searchInLL(key,bi);
     if(di==-1){
      return null;
     }else{
       Node node=buckets[bi].get(di);
       return  node.value;
     }
   }
public boolean containsKey(K key){
    int bi=hashFunction(key);
    int di=searchInLL(key,bi);
     if(di==-1){
      return false;
     }else{
      return true;
     }
}
 public void remove(K key){
  int bi=hashFunction(key);
    int di=searchInLL(key,bi);
     if(di==-1){
     System.err.println("not exist");
     }else{
      Node node=buckets[bi].remove(di);
      n--;
     }
 }
  }
  public static void main(String args[]){
   HashMap<String,Integer>map=new HashMap<>();
   map.put("shriya", 19);
   map.put("dad", 54);
   map.put("mom", 40);
   System.out.println(  map.containsKey("shriya"));
   System.out.println( map.get("dad"));
  
 
  }
}