# dcokerfile
   - ## 周末更新
        - docker exec containerid ls / 
        - docker exec containerid java -version
        - docker pull https://registry.docker-cn.com/redis   这才是 pull 的完整命令
        - docker tag myos myos:1.0.0  修改镜像标签或者给镜像添加标签（会多出一个镜像） 类似链接的作用，这些镜像的id 是相同的
        - docker inspect 镜像名 可以获取镜像的详细信息
        - docker history redis 查看一个镜像的历史
        - docker save -o centos.tar.gz centos 镜像的存出（可以和别人共享）
        - docker load --input .\centos.tar.gz 镜像的载入
        - docker export -o cce850d76bb4.tar cce850d76bb4 容器的导出
        - docker import .\centos.tar.gz ss:1.0 导入容器（导入之后是镜像），导入容器快照到本地镜像库
        - docker run --rm -p 127.0.0.1:8080:8080 tomcat 容器运行结束后删除容器
        - 数据卷容器
            - 维护特定数据卷的容器
            - 本身也是一个容器，但是专门用来提供数据卷供其他容器挂载
            - docker run -it -v /data1 -v /data2 --name db1 centos 同时挂载多个卷
            - run -it --volumes-from db1 --name db2 centos 
   - ## 进入容器的基本知识
        - attach 容器id
            - 当多个终端进入同一个容器的时候，多个终端会同步，并且当某个窗口阻塞时，其他窗口也无法操作
        - exec 容器id
            -  docker exec cce850d76bb4 ls / 执行一个普通命令，然后退出容器
            - docker exec -it cce850d76bb4  /bin/bash 获取到一个终端(推荐的方式)
        - nsenter 工具
   - ## 基本知识  
        - 每条保留字指令都必须是大写字母，并且后面至少跟一个参数
        - 指令从上到下，依次执行
        - "#"表示注释  
        - 每条指令都会创建一个新的镜像层，并对镜像进行提交
        - $ 表示引用
        - 每个dockerfile 文件都必须以FROM 指令开始
   - ## 执行流程
        - docker 从基础镜像运行一个容器
        - 执行一条指令并对容器进行修改
        - 执行 docker commit 类似的操作提交一个新的镜像层
        - docker 在基于刚才提交的新镜像运作一个新的容器
        - 执行 dcokerfile 中的下一条命令，直到所有的命令执行完毕
   - ## 什么是 dockerfile
        - 从软件应用角度来看，dockerfile 和docker 镜像和 docker 容器代表着软件的不同的三个阶段
            - dockerfile 就是软件的源材料
            - 镜像是软件的交付品
            - 容器则是软件的运行状态(镜像的运行状态)   
   - ## dockerfile 基本解析
            就像 java 的class 类，有它自己的语法格式
            ```text
                 FROM scratch   # 基础镜像，当前镜像基于那个镜像
                 ADD centos-7-docker.tar.xz /
                 LABEL org.label-schema.schema-version = "1.0" \
                 org.label-schema.name="CentOS Base Image" \
                 org.label-schema.vendor="CentOS" \
                 org.label-schema.license="GPLv2" \
                 org.label-schema.build-date="20180531"
                 CMD ["/bin/bash"]
      ```
   - ## 关键字  
        - MAINTAINER 镜像维护者和维护者邮箱
        - RUN 容器构建时需要执行的指令(每运行一次RUN指令，镜像就添加新的一层)
            - 如果命令比较多，可以合并RUN 命令，减少容器的层数
            - 可以吧命令写在一个脚本里(RUN 或者 CMD 都可以调用)
        - EXPOSE 对外暴露的端口
        - WORKDIR 创建容器之后，终端默认登录进来的工作目录(没有指定的时候是 / 目录)
        - ENV 在构建过程中设置环境变量(ENV ZK_path /usr/local/zkpath)
        - ADD 和 COPY 将宿主机的文件拷贝到镜像
            - ADD 拷贝+解压缩（支持url）(发现它并不能解压zip文件)
            - COPY 直接拷贝
        - VOLUME 容器数据卷(VALUME["/data1","/data2"])
        - CMD 指定一个容器启动时要执行的命令
            -  可以有多个，但是只有最后一个生效，
            -  CMD 会被 docker run 之后的参数替换
        - ENTRYPOINT 指定一个容器启动时要执行的命令
            - ENTRYPOINT 不会被 docker run 替换，会变成追加
        - ONBUILD 当构建一个被继承的镜像时，父镜像的build会被触发(这个命令是写在被触发的镜像里面的，也就是父镜像)
        - USER 什么用户才能运行这个dockerfile
        - LABEL 指定标签 LABEL version="1.0.0"; LABEL desc="我的容器";
   - ## 端口映射和容器联网
        - 默认的docker容器是不能链接网络的
            - iptables
            - ip_forward
                - 决定系统是否允许转发流量，默认是true,即允许
                - 这个是容器联网的关键（容器访问互联网，互联网上的机器访问容器）
            - 允许端口映射
            - 限制IP访问
        - 基本命令
            - p 可以多次使用
            - p 127.0.0.1:5000:5000  127.0.0.1::5000 映射到特定地址的特定端口或者任意端口
            - docker port 75d794229459
            - docker run --name web -d --link db:bd_con tomcat
        - linux 是上的docker 
            - ifconfig---------> docker0 就是docker 服务端的网卡(就行虚拟机的eth0)，既然有虚拟网卡，就可以手动配置
                - 手动配置 ifconfig docker0 129.168.12.122 netmask 255.255.255.0 
            - 当然它还有有其他信息，如mac地址
   - ## docker 容器的互联
        - 默认情况下允许所有的容器相互连接
             - docker run -it centos --icc=true(--icc=true 这个参数默认是true,这个参数是守护进程的，而不是容器的，要修改docker的配置文件才能生效)
                   - 测试
                        - run -it --name cct1/cct2 centos 启动两个容器 cct1和cct2
                        - yum -y install net-tools 安装网络工具
                        - ifconfig 查看各自的ip
                        - 互 ping 
             - 原因在同一个机器上的docker的服务进程都是通过虚拟网桥进行链接的，就像虚拟机的net模式一样，在同一个局域网内 .
                   - docker 容器的ip是动态变化的，也就是在容器重启的时候ip可能发生变化  
                        - docker run -it --name cct3 --link cct1:cct1 centos(--link cct1:cct1 连接到cct1 并个cct1 起别名 cct1)
                            - 改变了什么
                                - CCT1_NAME=/cct3/cct1 环境变量的改变
                                - /etc/hosts 改变了ip映射
                           
        - 拒绝容器之间的链接
             -  docker run -it centos --icc=false(--icc=true 这个参数默认是true)
        - 允许特定的容器之间链接
            - docker run -it centos --icc=false --iptables=true(iptables 来控制特定的ip 访问)
            
            
   - 基本镜像的使用
        -  docker run -d -P -e MYSQL_ROOT_PASSWORD=root mysql
   - ## 创建镜像的方式
        - 基于已有容器的创建
            - 启动镜像后，进入容器对容器进行修改，然后退出容器（记住容器id）
            - docker commit -m "add a new file" -a "kingcall" f8ec10a9d4da centos2
            - 查看本地镜像，会发现多了一个名为 centos2 的镜像
        - 基于本地模板的导入
        - 基于dockerfile的创建
   - ## 创建镜像的原则
        - 精简镜像的用途
        - 选择合适的基础镜像
        - 尽量减少镜像的层数(合并RUN指令)
   - ## 自定义镜像(docker commit)
        - ssh 镜像
            - yum -y install net-tools
            - yum -y install openssh-server
   - ## 自定义镜像(基于dockerfile)
        - 基本流程
            - 编写dockerfile
                - 基础镜像信息   FROM
                - 维护者信息     MAINTAINER 
                - 镜像操作指令   RUN
                - 镜像启动指令   CMD
            - 构建  docker build -f .\mycentos -t myos:0.0.0 . 或者  docker build mycentos -t myos:0.0.0 . 
            - 运行镜像进行测试  docker run -it myos:0.0.0
        - mycentos
            ```text
              FROM centos
              VOLUME ["/data1","/data2"]
              ENV mypath /tmp
              WORKDIR $mypath
              RUN yum -y install vim
              RUN yum -y install net-tools
              ONBUILD RUN echo "father mycentos is rebuild-----------666"
    
              EXPOSE 80
              CMD /bin/bash
            ```  
        - mytomcat  
            需要注意一下，待拷贝的文件都是在构建上下文的路径上（dockerfile 所在目录）
            ```text
             FROM centos
             MAINTAINER kingcall@hotmail.com
             VOLUME ["/data1","/data2"]
             COPY mycat /usr/local/mycat.txt
             ADD apache-tomcat-9.0.12.zip /usr/local/
             ADD jdk-8u191-linux-x64.tar.gz /usr/local/
             ENV mypath /usr/local/
             WORKDIR $mypath
             ENV JAVA_HOME /usr/local/jdk1.8.0_191
             ENV CLASS_HOME $JAVA_HOME/lib/dt.jar: $JAVA_HOME/lib/tools.jar
             ENV TOMCAT_HOME /usr/local/apache-tomcat-9.0.12
             ENV PATH $PATH:$JAVA_HOME/bin:$TOMCAT_HOME/bin:$CLASS_HOME
             RUN yum -y install vim
             RUN yum -y install net-tools
             ONBUILD RUN echo "father mycentos is rebuild-----------666"
             EXPOSE 8080
             CMD /usr/local/apache-tomcat-9.0.12/startup.sh
            ```
            - 运行命令
                docker run -d -p 8080:8080 --name mycat  -v D:\workspace\docker\valume\test:/usr/local/apache-tomcat-9.0.12/test -v D:\workspace\docker\valume\log:/usr/local/apache-tomcat-9.0.12/logs --privileged=true mycat