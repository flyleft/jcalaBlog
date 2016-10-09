package me.jcala.blog.cnf;

import me.jcala.blog.Application;
import me.jcala.blog.domain.ReversePath;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ReverseConfTest {
    @Autowired
    @Qualifier("reversePathBean")
    private ReversePath reversePath;
    @Test
    public void getReversePath(){
        System.out.println(reversePath.toString());
    }
}
