package com.ahn.action;

import com.ahn.entity.Customer;
import com.ahn.entity.LinkMan;
import com.ahn.service.CustomerService;
import com.ahn.service.LinkManService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {
    private LinkManService linkManService;

    private CustomerService customerService;

    private LinkMan linkMan=new LinkMan();
    //使用模型驱动封装获取表单对象
    public LinkMan getModel() {
        return linkMan;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void setLinkManService(LinkManService linkManService) {
        this.linkManService = linkManService;
    }

    /*获取上传的文件
    * 1、定义两个变量，一个是上传的文件，一个是上传的文件名
    * 2、命名规范：文件变量名称要和表单对象的name属性一致
    *   文件名为文件变量名+FileName
    * 3、生成对应的set和get方法
    * 4、还有一个变量，文件的mime类型，但不需要配置*/

    private File upload;
    private String uploadFileName;

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    //添加页面
    public String toAddPage(){
        //在这里跳转到添加页面时，需要在页面上显示所属客户的那些客户列表
        /*1、查询得到所有客户的集合
        （之前在customerService已经定义了查询得到所有客户的方法，只需调用即可，故需要配置一个customerService对象）
        * 2、将所得的集合添加到域对象
        * 3、在页面使用EL表达式将结果显示*/
        List<Customer> customerList=customerService.findall();
        ServletActionContext.getRequest().setAttribute("customerList",customerList);

        return "toadd";
    }

    //添加数据
    public String insert() throws IOException {
        //判断是否需要上传文件
        if(upload!=null){
            System.out.println("不为空！！");
            //实现文件上传
            /*1、在服务器上保存上传的文件夹里创建文件
            * 2、将本地上传的文件复制到服务器的文件夹*/
            File file =new File("D:\\crm-files"+"/"+uploadFileName);
            //第一个参数：源文件
            //第二个参数：目标文件（即服务器上的文件）
            FileUtils.copyFile(upload,file);
        }
        /*文件的上传有大小限制，若超过2M，则上传失败，可到sttruts.xml文件中以常量形式设置上传文件的大小限制
        * 其次，解决文件超过设定的大小发结果页面input配置*/
        //调用添加操作
        linkManService.insert(linkMan);
        return "insert";
    }

    //联系人列表功能
    public String list(){
        //查询所有联系人
        List<LinkMan> list=linkManService.list();
        //将得到的结果封装到域对象中，在结果页面使用EL表达式获取
        /*在联系人列表中，应该显示所属联系人的名称，而非数据库中的外键
        * 若使用${list.customer.custName}
        * 1、先根据得到的customer对象得到clid，再通过外键到customer中查找对应的名称
        * 问题：若是直接使用会抛出no session异常，因为在得到list对象后已经关闭了session，
        * 则在根据clid到数据库查找对应的客户名称时已经关闭会话
        *
        * 解决方案：使用过滤器配置延迟关闭session，即在action执行之后（到结果页面显示后）再关闭session
        * */
        ServletActionContext.getRequest().setAttribute("list",list);
        return "list";
    }

    //跳转到修改页面
    public String showedit(){
        //获取要操作的对象数据，接收传递的id值
        int lid=linkMan.getLid();
        //获得查询的联系人
        LinkMan lm=linkManService.fineOneById(lid);
        //将得到的linkMan对象封装到域对象中
        ServletActionContext.getRequest().setAttribute("linkman",lm);
        //由于linkMan需要所属客户的id，则需要customer的集合对象
        List<Customer> customerList=customerService.findall();
        ServletActionContext.getRequest().setAttribute("customerList",customerList);
        return "showedit";
    }
    //保存修改操作
    public String edit(){
        //将表单数据封装好之后传递到更新操作的方法
        linkManService.update(linkMan);
        return "edit";
    }
    //打开查询页面
    public String openQuery(){
        //将获得的客户存入域对象
        List<Customer> list=customerService.findall();
        ServletActionContext.getRequest().setAttribute("list",list);
        return "queryView";
    }

    //执行查询操作
    public String query(){
        List<LinkMan> list=linkManService.query(linkMan);
        ServletActionContext.getRequest().setAttribute("list",list);
        return "query";
    }
}
