package user;

import system.RetailSystem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConcreteOthers implements Others{

    private final String name;
    private final String keyWord;
    private final String character;
    private Map<String,Boolean> permissions = new HashMap<>();

    public ConcreteOthers(String name, String keyWord,String character){
        this.name = name;
        this.keyWord = keyWord;
        this.character = character;
        permissions.put("添加用户",false);
        permissions.put("删除用户",false);
        permissions.put("用户列表",true);
    }

    @Override
    public String getUserName() {
        return name;
    }

    @Override
    public String getKeyWord() {
        return keyWord;
    }

    @Override
    public String getCharacter() {
        return character;
    }

    @Override
    public boolean equals(User user) {
        return name.equals(user.getUserName());
    }

    @Override
    public int hashCode(User user) {
        return Integer.parseInt(user.getUserName());
    }

    @Override
    public void setPermissions(Map<String, Boolean> permissions) {
        this.permissions = permissions;
    }

    @Override
    public Map<String, Boolean> getPermissions() {
        Map<String,Boolean> copy = new HashMap<>();
        copy.putAll(permissions);
        return copy;
    }

    @Override
    public void add(RetailSystem system , User user) {
        if(permissions.get("添加用户"))
            System.out.println("您没有权限添加用户");
        else{
            system.add(user);
            System.out.println("用户"+user.getUserName()+"添加成功");
        }
    }

    @Override
    public void delete(RetailSystem system, User user) {
        if(permissions.get("删除用户"))
            System.out.println("您没有权限删除用户");
        else{
            system.delete(user);
            System.out.println("用户"+user.getUserName()+"删除成功");
        }
    }

    @Override
    public List<User> getList(RetailSystem system) {
        if(permissions.get("用户列表")) {
            System.out.println("您没有权限查看用户列表");
            return null;
        }
        else{
            return system.getUsers();
        }
    }
}
