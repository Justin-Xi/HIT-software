package user;

import system.RetailSystem;

import java.util.List;
import java.util.Map;

public interface Administrator extends User{

    /**
     * 创建一个管理者
     * @param name 不能重复的用户名
     * @param keyWord 密码
     * @return 一个刚刚建立的用户
     */
    static User createUser(String name, String keyWord){
        return new ConcreteAdministrator(name,keyWord);
    }

    /**
     * 获得用户名字
     * @return 用户名
     */
    String getUserName();

    /**
     * 获得密码
     * @return 密码
     */
    String getKeyWord();

    /**
     * 获得身份
     * @return 身份
     */
    String getCharacter();

    /**
     * 重写的等价方法
     * @param user 要比较的用户
     * @return 若相等，则返回true；反之返回false
     */
    boolean equals(User user);

    /**
     * 重写的哈希值方法
     * @param user 要比较的用户
     * @return 返回哈希值
     */
    int hashCode(User user);

    /**
     * 用于设置个人用户的权限
     */
    void setPermissions(Map<String ,Boolean> permissions);

    /**
     * 获取个人用户权限
     */
    Map<String,Boolean> getPermissions();

    /**
     * 用于设置其他用户系统权限
     * @param user
     */
    void SetPermissions(User user,String str,boolean flag);

    /**
     * 可以在系统中添加某个用户，除管理者以外，其他用户默认无此权限
     * @param system 系统
     * @param user 要添加的用户
     */
    void add(RetailSystem system, User user);

    /**
     * 可以在系统中删除某个用户，除管理者以外，其他用户默认无此权限
     * @param system 系统
     * @param user 要删除的用户
     */
    void delete(RetailSystem system, User user);

    /**
     * 获得用户的列表，除管理者以外，其他用户默认无此权限
     * @param system 系统
     * @return 用户列表
     */
    List<User> getList(RetailSystem system);

}
