package system;

import user.User;

import java.util.List;

/**
 * 通用批发零售系统的类，包含着一系列系统的操作
 */
public interface RetailSystem {

    /**
     * 产生空系统的静态方法
     * @return 一个崭新的空系统
     */
    static RetailSystem emptySystem(){
        return new ConcreteSystem();
    }

    /**
     * 添加一个用户
     * @param user 待添加的用户
     */
    void add(User user);

    /**
     * 删除一个用户
     * @param user 待删除的用户
     */
    void delete(User user);

    /**
     * 获得用户列表
     * @return 用户列表
     */
    List<User> getUsers();

    /**
     * 系统可用的功能
     * @return 功能列表
     */
    List<String> getPermissions();
}
