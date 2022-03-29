package ru.vsu.cs.pekshev.T2;

public class Execution {
    /*23. Дано два списка чисел, отсортированных по возрастанию. Необходимо объединить два
эти списка в один итоговый список так, чтобы числа в списке также оказались
отсортированными по возрастанию. Новых объектов ListNode / ListItem – не создавать
(после выполнения операции объединения исходные списки окажутся пустыми.). По
каждому из 2-х исходных списков можно пройти не более одного раза.
*/
    public static SimpleLinkedList<Integer> execute(SimpleLinkedList<Integer> entryList1, SimpleLinkedList<Integer> entryList2) throws Exception {
        final SimpleLinkedList<Integer> answerList = new SimpleLinkedList<>();

        while ((entryList1.size() != 0) || (entryList2.size() != 0)) {
            if (entryList1.size() == 0){
                answerList.addLast(entryList2.getFirst());
                entryList2.removeFirst();
                continue;
            }
            if (entryList2.size() == 0){
                answerList.addLast(entryList1.getFirst());
                entryList1.removeFirst();
                continue;
            }
            if (entryList1.getFirst() > entryList2.getFirst()) {
                answerList.addLast(entryList2.getFirst());
                entryList2.removeFirst();
            } else {
                answerList.addLast(entryList1.getFirst());
                entryList1.removeFirst();
            }
        }
        return answerList;
    }
}
