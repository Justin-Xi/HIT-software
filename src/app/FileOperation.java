package app;

import system.RetailSystem;
import user.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileOperation {

    public static void readFile(RetailSystem system){
        List<String> list = system.getPermissions();
        File file = new File("log\\log.txt");
        try(FileReader reader = new FileReader(file); BufferedReader br = new BufferedReader(reader)){
            String MyLine;
            List<String> word = new ArrayList<>();
            while((MyLine= br.readLine())!=null){
                word.add(MyLine);
            }
            for(String s:word){
                String[] Users = s.split(";");
                User user = User.createUser(Users[0],Users[1],Users[2]);
                for(int i=3;i<Users.length;i++){
                    if(Users[i].equals("true"))
                        user.setPermissions(list.get(i-3),true);
                    else
                        user.setPermissions(list.get(i-3),false);
                }
                system.add(user);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void writeFile(RetailSystem system){
        List<User> userlist = system.getUsers();
        List<String> list = system.getPermissions();
        try {
            File file = new File("log\\log.txt");
            file.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            for(User user:userlist){
                Map<String,Boolean> permissions = user.getPermissions();
                out.write(user.getUserName()+";");
                out.write(user.getKeyWord()+";");
                out.write(user.getCharacter()+";");
                for(int i=0;i<list.size()-1;i++){
                    if(permissions.get(list.get(i)))
                        out.write("true;");
                    else
                        out.write("false;");
                }
                if(permissions.get(list.get(list.size()-1)))
                    out.write("true\n");
                else
                    out.write("false\n");
            }
            out.flush(); // 把缓存区内容压入文件
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
