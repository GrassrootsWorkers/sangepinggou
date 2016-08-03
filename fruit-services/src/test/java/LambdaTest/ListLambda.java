package LambdaTest;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by liuzhi on 2016/7/30.
 */
public class ListLambda {

    public void printPersonName(Person person){
        System.out.print(person.getName());
    }
    @Test
    public void test(){
        List<Person> persons = Arrays.asList(new Person("liujinchao"),new Person("luzhi"),new Person("liran"),new Person("zhaojinhui"),new Person("shushiliang"));
        persons.forEach(this::printPersonName);
        persons.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        System.out.println("");
        persons.forEach(this::printPersonName);
    }
}
class Person{
    private String name;
    public Person(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
