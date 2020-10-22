# DomainSearch
Java子域名爆破，高并发快速收集

App类的main方法控制线程总线 

        distinctDomainList最后对域名去重
        
EnumDomain类下的getDomainList将字典加入到List中

                tryDomainEnum将对字典进行尝试/多线程
                
WriteFileDomain类下的writeFileContext将对收集完毕的域名写入到文件中


程序开启----------------

    请输入主域名
    
    baidu.com
    
    请输入字典的绝对路径
    
    F:\1.txt
    
    请输入爆破后的绝对路径
    
    F:\2.txt
    
    生成结束,前往F:\2.txt查看
    
程序运行时字典必须存在，且是绝对路径

输出的文件可以不存在，将自动创建写入

    
