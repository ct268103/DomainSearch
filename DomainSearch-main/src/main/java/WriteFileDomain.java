import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

/**
 * @description 模块介绍
 * @Author yangTao
 * @Date 2020/10/22 15/10
 */
public class WriteFileDomain {
    /**
     * 输出IO方法
     *      strings : 要输出的List
     *      path : 输出的路径
     * @param strings
     * @param path
     * @throws Exception
     */
    public void writeFileContext(List<String> strings, String path) throws Exception {
        File file = new File(path);
        //如果没有文件就创建
        if (!file.isFile()) {
            file.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        for (String l:strings){
            writer.write(l + "\r\n");
        }
        writer.close();
    }
}
