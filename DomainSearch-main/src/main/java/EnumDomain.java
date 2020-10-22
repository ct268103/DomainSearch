import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description 枚举域名
 * @Author yangTao
 * @Date 2020/10/22 10/45
 */
public class EnumDomain {

    /**
     * 获取二级前缀字典
     *      path : 字典存放路径
     * @param path
     * @return
     */
    public List<String> getDomainList(String path){
        List<String> domainList=new ArrayList<String>();
        File file=new File(path);
        BufferedReader reader = null;
        String tempString = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
            while ((tempString = reader.readLine()) != null) {
                domainList.add(tempString);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found, please check the path");
        } catch (IOException e) {
            System.out.println("IO Throws Exception");
        }finally{
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("IO Close Exception");
                }
            }
        }
        return domainList;
    }

    /**
     * 域名A记录识别
     *      domain : 需要拼接的根域名
     *      domainList : 需要拼接的域名前缀集合
     * @param domain
     * @param domainList
     * @return
     */
    public List<String> tryDomainEnum(final String domain, final List<String> domainList) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        AtomicInteger index=new AtomicInteger();
        final CountDownLatch latch = new CountDownLatch(domainList.size());
        List<String> realDomainList=new ArrayList<>();
        for(int t=0;t<3;t++){
            pool.execute(() -> {
                while (index.get()<domainList.size()){
                    try {
                        int i = index.getAndIncrement();
                        InetAddress[] addresses = InetAddress.getAllByName(domainList.get(i)+"."+domain);
                        if(addresses.length>0){
                            realDomainList.add(domainList.get(i)+"."+domain);
                        }
                    } catch (UnknownHostException e) {
                        // System.out.println("UnknownHostException");
                    }
                    latch.countDown();
                }
            });
        }
        pool.shutdown();
        latch.await();
        return realDomainList;
    }
}
