import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @description 模块介绍
 * @Author yangTao
 * @Date 2020/10/22 10/31
 */
public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("请输入主域名");
        Scanner input=new Scanner(System.in);
        String domain=input.next();
        System.out.println("请输入字典的绝对路径");
        String readPath=input.next();
        System.out.println("请输入爆破后的绝对路径");
        String writePath=input.next();
        EnumDomain enumDomain=new EnumDomain();
        List<String> domainList=enumDomain.getDomainList(readPath);
        List<String> realDomain=enumDomain.tryDomainEnum(domain,domainList);
        List<String> distinctList=distinctDomainList(realDomain);
        new WriteFileDomain().writeFileContext(distinctList,writePath);
        System.out.println("生成结束,前往"+writePath+"查看");
    }
    public static List<String> distinctDomainList(List<String> list){
        List<String> distinctList = list.stream().distinct().collect(Collectors.toList());
        return distinctList;
    }
}
