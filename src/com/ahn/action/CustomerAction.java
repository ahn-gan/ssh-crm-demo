package com.ahn.action;

import com.ahn.entity.Customer;
import com.ahn.entity.Dict;
import com.ahn.entity.PageBean;
import com.ahn.service.CustomerService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import java.util.ArrayList;
import java.util.List;

public class CustomerAction implements ModelDriven<Customer>{
    private CustomerService customerService;
    private Customer customer=new Customer();

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    //使用模型驱动封装表单数据
    @Override
    public Customer getModel() {
        return customer;
    }
    /*转向添加页面*/
    public String toAddPage(){
        //将数据字典的客户级别信息进行获取并封装
        List<Dict> listLevel=customerService.findLevel();
        ServletActionContext.getRequest().setAttribute("listLevel",listLevel);
        return "toadd";
    }
    //crud操作方法
    public String add(){
        customerService.add(customer);
        return "insert";
    }

    private List<Customer> list;

    public List<Customer> getList() {
        return list;
    }

    public String list(){
/*        List<Customer> list=customerService.findall();
        //将数据放到作用域对象
        ServletActionContext.getRequest().setAttribute("list",list);*/

        /*将获取的客户信息列表放到值栈对象中
        * 1、声明一个list对象
        * 2、生成list的get方法
        * 3、将list对象存放查询返回的结果
        * 4、在页面使用struts2的标签库和ognl表达式获取值栈对象的值*/
        list=customerService.findall();
        return "list";
    }

    //删除操作
    public String delete(){
        //获取id值
        int cid=customer.getCid();
        //System.out.println(cid);
        //删除数据时，需要先进行数据库的查询再执行删除操作
        //根据id查询
        Customer c=customerService.findOne(cid);
        //判断要删除的对象是否为空
        if(c!=null){
            //执行删除操作
            customerService.delete(c);
        }
        return "delete";
    }

    //修改操作
    public String showupdate(){
        //使用模型驱动获取id值
        int cid=customer.getCid();
        //System.out.println(cid);
        //修改数据时，需要先进行数据库的查询再执行修改操作
        //根据id查询
        Customer c=customerService.findOne(cid);
        //将数据放到域对象
        ServletActionContext.getRequest().setAttribute("customer",c);
        //将数据字典的客户级别信息进行获取并封装
        List<Dict> listLevel=customerService.findLevel();
        ServletActionContext.getRequest().setAttribute("listLevel",listLevel);
        //判断要删除的对象是否为空
/*        if(c!=null){
            //执行删除操作
            customerService.delete(c);
        }*/
        return "showupdate";
    }

    //执行修改保存操作
    public String update(){
        //使用模型驱动封装获得表单数据
        customerService.update(customer);
        return "update";
    }
    //获取传过来的currentPage的值使用属性封装
    private Integer currentPage;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    //分页查询
    public String pageList(){
        //调用service的方法进行分页操作
        PageBean pageBean=customerService.findByPagenum(currentPage);
        //将获取的数据对象放到域对象
        ServletActionContext.getRequest().setAttribute("pageBean",pageBean);

        //使用数据字典后新增
        //将数据字典的客户级别信息进行获取并封装
        List<Dict> listLevel=customerService.findLevel();
        ServletActionContext.getRequest().setAttribute("listLevel",listLevel);
        return "pagelist";
    }

    //条件查询
    public String conditionQuery(){
        if(customer.getCustName()!=null||customer.getDictLevel().getDname()!=null||customer.getCustSource()!=null){
            //查询条件不为空，则调用条件查询的方法
/*            List<Customer> list=customerService.findByCondition(customer);
            //将查询得到的结果封装到域对象
            PageBean pageBean=new PageBean();
            pageBean.setList(list);*/
            PageBean pageBean=customerService.findByConditions(customer,currentPage);
            ServletActionContext.getRequest().setAttribute("pageBean",pageBean);
        }else{
            //查询条件为空，则执行查询所有的方法
            pageList();
        }
        return "querylist";
    }

    //打开查询界面
    public String openQuery(){
        //将某些字段的信息作为集合传递到前台页面
        //List<Customer> levelList=customerService.findall();
        List<Customer> sourceList=customerService.findall();
/*        for (int i=0;i<levelList.size();i++){
            Customer c=levelList.get(i);
            //除去具有相同的custLevel的对象
            for(int j=i+1;j<levelList.size();j++){
                if(c.getCustLevel().equals(levelList.get(j).getCustLevel()))
                    levelList.remove(j);
            }
        }*/
        for (int i=0;i<sourceList.size();i++){
            Customer c=sourceList.get(i);
            //除去具有相同的custSource的对象
            for(int j=i+1;j<sourceList.size();j++){
                if(c.getCustSource().equals(sourceList.get(j).getCustSource()))
                    sourceList.remove(j);
            }
        }
        //ServletActionContext.getRequest().setAttribute("levelList",levelList);
        ServletActionContext.getRequest().setAttribute("sourceList",sourceList);
        return "queryView";
    }

    //查询操作
    public String query(){
        if(customer.getCustName()!=null||customer.getCustSource()!=null||customer.getCustAddress()!=null) {
            list=customerService.queryByParameters(customer);
        }else{
            pageList();
        }
        return "query";
    }
}
