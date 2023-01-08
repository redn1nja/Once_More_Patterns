import org.example.Selery.Group;
import org.example.Selery.Signature;
import org.example.Selery.Stamper;
import org.example.Selery.Visitor;
import org.junit.Assert;
import org.junit.Test;

public class TestStamp {
    @Test
    public void testStamp(){
        Visitor<Integer> visitor = new Stamper<Integer>();
        Group<Integer> nestedGroup = new Group<>();
        nestedGroup.addTask(new Signature<>(System.out::println)).addTask(new Signature<>(x -> System.out.println(x * x)));
        Group<Integer> group = new Group<>();
        group.addTask(nestedGroup).addTask(new Signature<>(x -> System.out.println(x * x * x)));
        group.apply(10, visitor);
        Assert.assertEquals(group.getHeaders().size(), 5);
    }
}
