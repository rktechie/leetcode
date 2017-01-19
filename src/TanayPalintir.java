import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class TanayPalintir {

    public static void main(String[] args) {
        String[] persons = {"Shilpa|5000|California|63","Tom|25000|New York|63","Krasi|9000|California|1230","Tom|25|New York|1235","Tom|25|New York|1238","Shilpa|50|Michigan|1300","Matt|90000|Georgia|1305","Jay|100000|Virginia|1310","Krasi|49|Florida|1320","Krasi|83|California|1325","Shilpa|50|California|1350"};
       // String[] persons =  null;
        String x[] = getSuspiciousList(persons);
        for (String t : x)
            System.out.println("answer: " + t);
    }

    public static final int MINIMUM_AMOUNT = 3000;
    public static final int MINIMUM_TIME = 60;

    static String[] getSuspiciousList(String[] transactions) {
        
        if(transactions == null || transactions.length == 0) {
            return new String[0];
        }
        HashMap<String, Person> personHistory = new HashMap<String, Person>();
        
        PriorityQueue<Person> priorityQueue = new PriorityQueue<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return Integer.compare(o1.time, o2.time);
            }
        });
        
        HashSet<String> uniquePersons = new HashSet<String>();
        
        for (int i = 0; i < transactions.length; i++) {
            String[] inputSplit = transactions[i].split("\\|");
            Person p = new Person(inputSplit[0], inputSplit[1], inputSplit[2], inputSplit[3]);
            if (!uniquePersons.contains(p.name)) {
                if (personHistory.containsKey(p.name)) {
                    Person tempPerson = personHistory.get(p.name);
                    if (!p.location.equals(tempPerson.location) && (p.time - tempPerson.time) < MINIMUM_TIME) {
                        priorityQueue.add(tempPerson);
                        uniquePersons.add(p.name);
                    }
                }
                if (p.amount > MINIMUM_AMOUNT) {
                    priorityQueue.add(p);
                    uniquePersons.add(p.name);
                }
            }
            personHistory.put(p.name, p);
        }
        
        String[] result = new String[priorityQueue.size()];
        int counter = 0;
        while (priorityQueue.size() > 0){
            result[counter++] = priorityQueue.poll().name;
        }
        return result;
    }

    static class Person {
        private String name;
        private int amount;
        private String location;
        private int time;

        public Person(String t1, String t2, String t3, String t4) {
            this.name = t1;
            this.amount = Integer.parseInt(t2);
            this.location = t3;
            this.time = Integer.parseInt(t4);
        }

    }
}
