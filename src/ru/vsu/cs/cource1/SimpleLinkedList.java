package ru.vsu.cs.cource1;

public class SimpleLinkedList <T> {

    private class SimpleLinkedListNode <T>{
        public T value;
        public SimpleLinkedListNode next;
        public SimpleLinkedListNode(T value, SimpleLinkedListNode next){
            this.value = value;
            this.next = next;
        }
        public SimpleLinkedListNode(T value){
            this(value,null);
        }
        public SimpleLinkedListNode(){
            this(null,null);
        }
    }

    private SimpleLinkedListNode head ;
    private SimpleLinkedListNode tail ;
    private int count;

    public void removeFirst() throws Exception {
        if(head == null){
            throw new Exception("List is empty");
        }
        else {
            head = head.next;
            count--;
        }
    }
    public int size(){
        return count;
    }

    public void remove(int index) throws Exception {
        if (index < 0 || index >= count){
            throw new Exception("Wrong index");
        }
        if(index == 0){
            removeFirst();
        }
        else {
            SimpleLinkedListNode prev = getNode(index - 1);
            prev.next = prev.next.next;
        }

        count--;
    }


    public void set(T value, int index) throws Exception {
        SimpleLinkedListNode curr = getNode(index);
        curr.value = value;
    }
    private SimpleLinkedListNode<T> getNode(int index) throws Exception{
        if (index >= count){
            throw new Exception("Wrong index");
        }
        SimpleLinkedListNode currNode = head;

        for (int i = 0; i < index; i++) {
            currNode = currNode.next;
        }
        return currNode;
    }

    public T get(int index) throws Exception{
        return getNode(index).value;
    }

    public void addFirst(T value){
        if(head == null){
            tail = head = new SimpleLinkedListNode(value, null);
        }
        else {
            head = new SimpleLinkedListNode(value, head);
        }
        count++;
    }


    public void addLast(T value){
        if(tail == null){
            tail = head = new SimpleLinkedListNode(value, null);
        }
        else {
            SimpleLinkedListNode node = new SimpleLinkedListNode(value, null);
            tail.next = node;
            tail = node;
        }
        count++;
    }

    public void copyArray(T[] arr){
        if(arr == null || arr.length == 0){
            return;
        }
        addFirst(arr[0]);
        for (int i = 1; i < arr.length ; i++) {
            addLast(arr[i]);
        }
    }

    public void removeDuplicates() {
        boolean flag = true;
        Object firstValue = null;
        SimpleLinkedListNode ptr1 = head, ptr2 = null;
        while (ptr1 != null && ptr1.next != null) {
            ptr2 = ptr1;
            while (ptr2.next != null) {
                if (flag) {
                    firstValue = ptr1.value;
                    flag = false;
                }
                if (firstValue!=ptr2.value && ptr1.value == ptr2.next.value) {
                    ptr2.next = ptr2.next.next;
                    count--;
                }
                else {
                    ptr2 = ptr2.next;
                }
            }
            ptr1 = ptr1.next;
        }

    }


}
