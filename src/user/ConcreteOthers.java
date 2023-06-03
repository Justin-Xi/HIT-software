package user;

import java.util.HashMap;
import java.util.Map;

public class ConcreteOthers implements Others{

    private final String name;
    private String keyWord;
    private final String character;
    private final Map<String,Boolean> permissions = new HashMap<>();

    public ConcreteOthers(String name, String keyWord,String character){
        this.name = name;
        this.keyWord = keyWord;
        this.character = character;
        permissions.put("添加用户",false);
        permissions.put("删除用户",false);
        permissions.put("用户列表",false);
        permissions.put("更改用户权限",false);
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
    public void setPermissions(String str, boolean flag) {
            permissions.put(str,flag);
    }

    @Override
    public Map<String, Boolean> getPermissions() {
        Map<String,Boolean> copy = new HashMap<>();
        copy.putAll(permissions);
        return copy;
    }

    @Override
    public void changeKeyWord(String oldKeyWord, String newKeyWord) {
        if(!oldKeyWord.equals(keyWord))
            System.out.println("原密码有误");
        else
            keyWord = newKeyWord;
    }

}
