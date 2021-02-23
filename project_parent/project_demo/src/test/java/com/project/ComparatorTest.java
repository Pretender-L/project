package com.project;

import com.project.pojo.User;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class ComparatorTest {

    @Test
    public void comparatorTest() {
        List<User> userList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i <= 10; i++) {
            User user = new User();
            user.setFansCount(random.nextInt(10));
            user.setFollowCount(random.nextInt(10));
            userList.add(user);
        }
        Collections.sort(userList, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getFansCount() - o2.getFansCount() == 0 ? o1.getFollowCount() - o2.getFollowCount() : o1.getFansCount() - o2.getFansCount();
                //return o2.getFansCount() - o1.getFansCount() == 0 ? o2.getFollowCount() - o1.getFollowCount() : o2.getFansCount() - o1.getFansCount();
            }
        });
        userList.forEach(user -> System.out.println(user.getFansCount() + "---" + user.getFollowCount()));
    }
}
