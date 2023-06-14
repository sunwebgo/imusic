本人博客网站,欢迎访问！：http://xhablog.online
# 音乐网站后端

# 项目介绍：本项目是一个音乐网站，前端采用Vue框架，后端采用SpringBoot、Mybatis框架，数据库采用MySQL，项目部署在腾讯云服务器上。

# 项目部署步骤
# 1.项目部署所使用的工具或资源
1. **Xshell**：用于在本机Windows界面访问远端不同系统下的服务器。

   [Xshell官网](https://www.xshell.com/zh/xshell/)

2. **Postman**：Postman是一个[接口测试](https://so.csdn.net/so/search?q=接口测试&spm=1001.2101.3001.7020)工具，本次部署中用于测试后端接口。

   		相较于Postman，可以有更好的选择即ApiFox。在本次部署中为方便采用Postman。

3. **Nginx**：Nginx是一个高性能的 HTTP 和[反向代理](https://so.csdn.net/so/search?q=反向代理&spm=1001.2101.3001.7020)服务器，可以作为静态页面的 web 服务器

4. **ECS服务器或者轻量应用服务器：**
   <font color=red size=3> 可以选择大厂云服务器供应商，如阿里、腾讯、华为。</font>本次部署中采用腾讯云服务器。

   我购买的云服务器配置是2核2GB，镜像为CentOS 7.6

<img src="https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/a4c4d6c041674d3c9b52c9d55803757a.png" alt="img" style="zoom:67%;" />


# 2.SpringBoot项目部署

## 2.1更改跨域配置

==将跨域配置修改为云服务器的公网ip==

<img src="https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/5572a9ddd36e406495cd139100f28ae5.png" alt="img" style="zoom:67%;" />


## 2.2项目打包

1. **首先打开maven，在maven生命周期选择clear选项，clear命令是maven的清除命令，这一步的<font color='red'>目的是为了清空target目录下的所有内容。</font>**

![image-20230612164056909](https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/image-20230612164056909.png)

2. **选择package选项，将项目打成jar包。**

![img](https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/82c9800e33cd402d9390fb99d37e66e5.png)

3. **控制台出现BUILD SUCCESS就表示打包成功**

<img src="https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/650b92d81fb046c99f395b0f0d841fc2.png" alt="img" style="zoom:67%;" />

4. **查看target目录，找到打包好的项目jar包。**

<img src="https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/4ba6f4bd2b5e42b4805b250e136e8f8a.png" alt="img" style="zoom:80%;" />


## 2.3部署jar包

### 2.3.1上传jar包和静态资源文件

1. **查看SpringBoot项目服务端口：我的是8090**

![img](https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/0cb6f3bd5e204f7b965eaecf25ead924.png)

2. **在控制台开放相应端口**

<img src="https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/23aed84e51384efdbd9081873bd235d0.png" alt="img" style="zoom: 67%;" />

<img src="https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/e445d9356159462abc53861e46b4ca9e.png" alt="img" style="zoom:67%;" />

3. **打开Xshell，输入云服务器公网ip创建一个新的会话。**

<img src="https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/d45af4763b944c07877a351608564e6a.png" alt="img" style="zoom:67%;" />

4. **双击刚刚创建的会话进行连接，输入用户名和密码**

<img src="https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/5b9c7c933a8645bb825610827ee2fd91.png" alt="img" style="zoom:67%;" />

<img src="https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/5880f4f0c56e491d855e953ff72adee2.png" alt="img" style="zoom:67%;" />

5. **密码如果忘记的话到控制台中进行修改：**

**连接成功：**

![img](https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/6a04ded8f0d8419cbfc6a45786c01ce4.png)

6. **创建新目录，<font color='red'>并将jar包通过Xftp传输到新创建的目录当中</font>，==如果有静态资源的话就传输到jar包的同级目录下：==**

<img src="https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/e95f4744da964f7d8d93ffb061242d95.png" alt="img" style="zoom:67%;" />

<img src="https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/f196573210ea491cb3e039cb4b998f85.png" alt="img" style="zoom:67%;" />

<img src="https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/207ca780c8654abc972793c054a5794d.png" alt="在这里插入图片描述" style="zoom:67%;" />


### 2.3.2安装JDK

jdk官网：[Java Downloads | Oracle](https://www.oracle.com/java/technologies/downloads/)

**下载Linux版本：**

<img src="https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/6086d8eec6cc4e2c9331b551b0edc46a.png" style="zoom: 67%;" />

1. **将JDK传输到项目目录**

![](https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/4c8a0780908d44358fe0300c718cb50f.png)

2. **解压至自定义目录（一般是 /usr/local）**

```ini
tar -xzvf jdk-8u291-linux-x64.tar.gz -C /usr/local/
```

3. **配置环境变量。进入到/etc目录下，用vim编辑器在 profile 文件中添加四个变量。**

```ini
export JAVA_HOME=/usr/local/jdk1.8.0_291
export JRE_HOME=$JAVA_HOME/jre
export CLASSPATH=.:$JAVA_HOME/lib:$JRE_HOME/lib
export PATH=/bin:/usr/bin:$PATH:$JAVA_HOME/bin:$PATH
```

![](https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/a52f3aba42924ee7ab1e405b83337f8b.png)
JDK11环境配置

```ini
export JAVA_HOME=/usr/local/jdk-11.0.18
export CLASSPATH=.:$JAVA_HOME/lib
export PATH=$JAVA_HOME/bin:$PATH
export JAVA_HOME CLASSPATH PATH
```

**4. 刷新配置**

```ini
source /etc/profile

**四：查看jdk是否安装成功**
java -version
```

![](https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/2b45712c61664c0aa639cb6d66ef5da0.png)


### 2.3.3安装MySQL

1. **在控制台开发MySQL服务端口3306**

<img src="https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/98d084630daa489583549e6273c8a923.png" style="zoom:67%;" />

2. **在jar包同级目录下创建目录用于存放MySQL安装包**

![](https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/78cc355801cd4152acca3e0d965692d0.png)

3. **将MySQL安装包传输到刚刚创建的目录下，并解压**

> [MySQL安装包百度云地址（版本8.0.26）](https://pan.baidu.com/s/1zrf-Tq4HC8i4N9yZ95rEIg?pwd=xha8)
> 				提取码：xha8

解压命令：

```ini
tar -xvf mysql-8.0.26-1.el7.x86_64.rpm-bundle.tar 
```

![](https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/25de1af2146440dbafb87b9b66c928b6.png)

4. **查看解压后的MySQL压缩包**

![](https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/1b6a3c4fffc44bcbaabf250a966d5162.png)

5. **==清空linux自带的数据库==（<font color='red'>因为CentOS自带一个老版本的mariadb-libs与当前mysql包的冲突，故需要先卸载，再安装）</font>**

```sql
rpm -qa | grep Mysql
```

```sql
rpm -qa | grep mariadb
```

![](https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/e0cdf31fd8454149b9896e9ae4e369c2.png)


**卸载已有的数据库：（卸载会把这个包的依赖包也一起卸载）**

```sql
yum -y remove mariadb-libs.x86_64
```

![](https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/b663ce36965643a281cd8a62dc8c1143.png)

6. **开始安装 mysql，依次执行下述命令**

安装依赖：

```java
yum install libaio
```

依次执行以下命令：

```sql
rpm -ivh mysql-community-common-8.0.25-1.el7.x86_64.rpm
rpm -ivh mysql-community-client-plugins-8.0.25-1.el7.x86_64.rpm 
rpm -ivh mysql-community-libs-8.0.25-1.el7.x86_64.rpm 
rpm -ivh mysql-community-client-8.0.25-1.el7.x86_64.rpm 
rpm -ivh mysql-community-server-8.0.25-1.el7.x86_64.rpm
```

7. **查看MySQL的安装情况：**

```sql
rpm -qa|grep -i mysql
```

![](https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/9b87f8283a6d424695720df372b5cfdd.png)

8. **启动MySQL服务并查看服务状态**

```sql
systemctl start mysqld.service
systemctl status mysqld.service 
```

![](https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/0778608b0380414bab8a960d8c230d7b.png)

9. **查看MySQL初始化密码**

```sql
grep "password" /var/log/mysqld.log
```

![](https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/e276607a0e4240979904ca00598f447b.png)

10. **登录MySQL并修改初始密码**

```java
mysql -u root -p
```

![](https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/d9e0b3bb83e441a8843dfb821a494e78.png)


**修改密码：**

注意：==此数据库的密码要和jar包项目所连接的数据库的密码相同，否则会连接不上==

<font color='red'>mysql8.0之后的版本加入密码安全度检测机制，如果设置密码过于简单会导致报错</font>

下面将更改MySQL的密码安全设置：

1. **首先查看当前MySQL的密码安全设置**

```sql
SHOW VARIABLES LIKE 'validate_password%';
```

![](https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/14c3570dbe9742fe86092e6127e2c54d.png)

2. **更改密码长为6，密码安全策略等级为LOW**

```sql
set global validate_password.length=6;
set global validate_password.policy=LOW;
```

![](https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/bdf432fb8a1d4c1b8631b5b560844464.png)

3. **查看更改后的密码安全设置**

![](https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/7fb9d4c48aff4ec1ad0173719e39d468.png)

4. **更改密码**

```sql
ALTER USER 'root'@'localhost' IDENTIFIED BY '密码';
```

![](https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/4a3088c5173e4415afe6dd63b289bae2.png)


### 2.3.4导入sql文件资源

1. **打开Navicat，<font color='skyblue'>右键数据库->转储sql文件->结构和数据</font>**

![](https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/78bd5b843b944c8db6f1a9571fd99662.png)

2. **将sql文件通过Xftp传输到项目目录中**

![img](https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/6f7fa87223d84539b6ebad007d93768a.png)

3. **创建数据库**

==<font color='red'>切记：创建的数据库名要和SpringBoot中配置文件当中配置的数据库url的数据库名保持一致。</font>==

```
create database 数据库名;
show databases;
```

![img](https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/c74f72d8ad764cdaa0fac6f8b03594d7.png)

4. **进入创建的数据库，导入sql文件资源**

```sql
use 数据库名;
source sql文件相对路径/sql文件名
```

<img src="https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/db66daff847943989ec22631c0e63209.png" alt="img" style="zoom:67%;" />


**查询测试：**

```sql
select * from 表名;
```

![img](https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/4f1c9d1e8f2543359c6fcdad7fbfd76c.png)


### 2.3.5运行jar包

1. **进入jar包目录下**

==<font color='cornflowerblue'>nohup 英文全称 no hang up（不挂起），用于在系统后台不挂断地运行命令，退出终端不会影响程序的运行。</font>==

执行命令：

```java
nohup java -jar myMusic-0.0.1-SNAPSHOT.jar &
```

![在这里插入图片描述](https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/9462593b1e9d4a1697d460388cc9be0e.png)
![在这里插入图片描述](https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/641f3b6980e64c4b85f2a8cfcc79a8c0.png)

2. **查看日志文件**

```java
cat -n nohup.out
```

后端项目已经成功在8090端口运行

![img](https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/87ca0b271f8e49288be4ca76c1faee43.png)


<font color='red'>三：注意：如果操作不当导致jar包并没有在相应端口运行，但是已经占用进程的情况，再次运行jar包会报错的解决方案，</font>

查看当前所有进程

```java
ps sux
```

![img](https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/a1b67b7c90834707ae785b46820f68e4.png)


杀死该进程：

```java
kill -9 进程ID
```

再次执行运行jar包命令即可。

## 2.4接口测试

1. **打开Postman，输入要测试的接口，显示测试成功**

<img src="https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/9fbd1b14d07e4c65b311af1d5bb82c15.png" alt="在这里插入图片描述" style="zoom:67%;" />



在nohub.out日志文件中可以查看到日志信息（==需要在SpringBoot配置文件中配置mybatis-plus的日志文件==）

![img](https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/fe0e0b1a2a904772b0ece3b0b6e90321.png)




# 3.Vue项目部署

## 3.1更改代理服务器、axios、Vuex配置

> 将上述三种的url路径由原来的localhost（本地化部署时）更改为云服务器公网IP

1. **代理服务器**

![img](https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/bc9f0141e64a449c91352b2add296df5.png)

2. **axios接口配置**

<img src="https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/27a13cab847d4f05838fb9aeb1aec443.png" alt="在这里插入图片描述" style="zoom:67%;" />



3. **Vuex**

<img src="https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/39128fd985cd4f239de485c0d42d8cf3.png" alt="在这里插入图片描述" style="zoom:67%;" />


## 3.2将项目打包上传到云服务器

1. **打开控制台，输入以下命令打包**

```javascript
npm run build
```

<img src="https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/df2ef09a72354f81b7cddd7307bd483c.png" alt="img" style="zoom:67%;" />

2. **<font color='red'>在项目根目录下会自动生成一个dist文件夹</font>**

![img](https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/8e24809009b943a79e885416ead7065e.png)

3. <font color='cornflowerblue'>**有两个Vue项目的另一个和上述操作一样，将两个dist文件分别重命名，等待Nginx安装完成后上传到服务器**</font>

## 3.3Nginx安装


[Nginx百度云网盘地址](https://pan.baidu.com/s/1hPSRTHlOb5i5RLUhQjDY6w?pwd=xha8)

提取码：xha8

1. **将下载好的Nginx安装包发送到服务器中项目的根目录下**

<img src="https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/8a52d92774cc4a1ab6b3580f0b70a3b5.png" alt="img" style="zoom:67%;" />

2. **解压安装包到当前目录**

```java
tar -xzvf nginx-1.18.0.tar.gz
```

![img](https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/f8bc66609a404aab89af36eaf8b10ede.png)


安装包可以删除

3. **安装相应依赖**

安装gcc

```ini
yum install -y gcc
```

安装perl库

```ini
yum install -y pcre pcre-devel
```

安装zlib库

```ini
yum install -y zlib zlib-devel
```

4. **进入到nginx-1.18.0目录下，并执行以下命令**

源码编译安装

```ini
./configure
```

执行make命令

```ini
make
```

执行make install命令

```ini
make install
```

安装完成

![](https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/2433614f95374bb99e5552a6501d9839.png)

5. **启动Nginx服务**

进入到以下目录并执行./nginx命令启动Nginx服务

```ini
cd /usr/local/nginx/sbin/
./nginx
```

![img](https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/a779845aeed94c0aac2adf62ff9ecff6.png)

在本地输入云服务器公网ip，出现以下界面就表示Nginx安装成功。

![img](https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/e8c041c021184754994e492d455092da.png)


## 3.4部署第一个Vue项目

### 3.4.1将已经打包好的dist文件传送到nginx以下指定目录

```ini
cd /usr/local/nginx/html
```

![img](https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/2849fb32c94e4e32aec2e7a254fa6179.png)


### 3.4.1修改nginx.conf配置文件

1. **进入到nginx目录下的conf目录，找到nginx.conf配置文件**

![img](https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/4c28164288004d958da36bb32e02b8a3.png)

2. **使用vim编辑 nginx.conf配置文件**

> 解决Vue项目部署后刷新404的问题

```ini
try_files $uri $uri/ /index.html;
```

<img src="https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/d1c6b801f5054041852450110e69d3ee.png" alt="img" style="zoom:67%;" />

3. **在控制台开发相应端口**

<img src="https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/a8554d7c9af140f1b4539d8e7117337d.png" alt="img" style="zoom:67%;" />

4. **保存并退出，进入nginx的sbin目录下重启Nginx服务**

```ini
./nginx -s reload
```

![img](https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/fa782654432f454b8d1afc73c1fd4cf8.png)

5. **在本机浏览器输入公网ip和端口进行测试**

![img](https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/328c1fd64f1b4b8988d1f87505e9df87.png)


## 3.5部署第二个Vue项目

1. **通过vim编辑器进入nginx.conf配置文件，复制server部分，放在上一个server下面**

<img src="https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/131af90c6298444780b2b4e48e65200f.png" alt="img" style="zoom:67%;" />

2. **在控制台开发相应端口**

<img src="https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/ed6153c861dd4ef2a959024eea62c815.png" alt="img" style="zoom:67%;" />

3. **保存并退出，进入nginx的sbin目录下重启Nginx服务**

```ini
./nginx -s reload
```

![img](https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/5ccc462934a544a7b0195ed2bc66ff13.png)

4. **在本机浏览器输入公网ip和端口进行测试**

![img](https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/0328daae47da4a2695bcfed54643fc8b.png)



**==<font color='cornflowerblue'>项目部署完成啦</font>==**

![<img src="SpringBoot+Vue项目部署.assets/7479848120d00c122fbbf41fa0f9030f.jpg" alt="查看源图像" style="zoom:50%;" />](https://imagebed-xuhuaiang.oss-cn-shanghai.aliyuncs.com/typora/faae9a686de64e9a84051439ddd04049.png)
