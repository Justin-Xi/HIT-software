package system;

import user.User;

import java.util.ArrayList;
import java.util.List;

public class ConcreteSystem implements RetailSystem {
    List<User> userList = new ArrayList<>();

    @Override
    public void add(User user) {
        if(userList.contains(user))
            java.lang.System.out.println("用户已经存在");
        else
            userList.add(user);
    }

    @Override
    public void delete(User user) {
        if(userList.contains(user))
            java.lang.System.out.println("用户不存在");
        else
            userList.remove(user);
    }

    @Override
    public List<User> getUsers() {
        List<User> copy = new ArrayList<>();
        copy.addAll(userList);
        return copy;
    }
}
