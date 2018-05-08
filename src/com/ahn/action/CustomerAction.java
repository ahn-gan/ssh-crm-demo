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

    //ʹ��ģ��������װ������
    @Override
    public Customer getModel() {
        return customer;
    }
    /*ת�����ҳ��*/
    public String toAddPage(){
        //�������ֵ�Ŀͻ�������Ϣ���л�ȡ����װ
        List<Dict> listLevel=customerService.findLevel();
        ServletActionContext.getRequest().setAttribute("listLevel",listLevel);
        return "toadd";
    }
    //crud��������
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
        //�����ݷŵ����������
        ServletActionContext.getRequest().setAttribute("list",list);*/

        /*����ȡ�Ŀͻ���Ϣ�б�ŵ�ֵջ������
        * 1������һ��list����
        * 2������list��get����
        * 3����list�����Ų�ѯ���صĽ��
        * 4����ҳ��ʹ��struts2�ı�ǩ���ognl���ʽ��ȡֵջ�����ֵ*/
        list=customerService.findall();
        return "list";
    }

    //ɾ������
    public String delete(){
        //��ȡidֵ
        int cid=customer.getCid();
        //System.out.println(cid);
        //ɾ������ʱ����Ҫ�Ƚ������ݿ�Ĳ�ѯ��ִ��ɾ������
        //����id��ѯ
        Customer c=customerService.findOne(cid);
        //�ж�Ҫɾ���Ķ����Ƿ�Ϊ��
        if(c!=null){
            //ִ��ɾ������
            customerService.delete(c);
        }
        return "delete";
    }

    //�޸Ĳ���
    public String showupdate(){
        //ʹ��ģ��������ȡidֵ
        int cid=customer.getCid();
        //System.out.println(cid);
        //�޸�����ʱ����Ҫ�Ƚ������ݿ�Ĳ�ѯ��ִ���޸Ĳ���
        //����id��ѯ
        Customer c=customerService.findOne(cid);
        //�����ݷŵ������
        ServletActionContext.getRequest().setAttribute("customer",c);
        //�������ֵ�Ŀͻ�������Ϣ���л�ȡ����װ
        List<Dict> listLevel=customerService.findLevel();
        ServletActionContext.getRequest().setAttribute("listLevel",listLevel);
        //�ж�Ҫɾ���Ķ����Ƿ�Ϊ��
/*        if(c!=null){
            //ִ��ɾ������
            customerService.delete(c);
        }*/
        return "showupdate";
    }

    //ִ���޸ı������
    public String update(){
        //ʹ��ģ��������װ��ñ�����
        customerService.update(customer);
        return "update";
    }
    //��ȡ��������currentPage��ֵʹ�����Է�װ
    private Integer currentPage;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    //��ҳ��ѯ
    public String pageList(){
        //����service�ķ������з�ҳ����
        PageBean pageBean=customerService.findByPagenum(currentPage);
        //����ȡ�����ݶ���ŵ������
        ServletActionContext.getRequest().setAttribute("pageBean",pageBean);

        //ʹ�������ֵ������
        //�������ֵ�Ŀͻ�������Ϣ���л�ȡ����װ
        List<Dict> listLevel=customerService.findLevel();
        ServletActionContext.getRequest().setAttribute("listLevel",listLevel);
        return "pagelist";
    }

    //������ѯ
    public String conditionQuery(){
        if(customer.getCustName()!=null||customer.getDictLevel().getDname()!=null||customer.getCustSource()!=null){
            //��ѯ������Ϊ�գ������������ѯ�ķ���
/*            List<Customer> list=customerService.findByCondition(customer);
            //����ѯ�õ��Ľ����װ�������
            PageBean pageBean=new PageBean();
            pageBean.setList(list);*/
            PageBean pageBean=customerService.findByConditions(customer,currentPage);
            ServletActionContext.getRequest().setAttribute("pageBean",pageBean);
        }else{
            //��ѯ����Ϊ�գ���ִ�в�ѯ���еķ���
            pageList();
        }
        return "querylist";
    }

    //�򿪲�ѯ����
    public String openQuery(){
        //��ĳЩ�ֶε���Ϣ��Ϊ���ϴ��ݵ�ǰ̨ҳ��
        //List<Customer> levelList=customerService.findall();
        List<Customer> sourceList=customerService.findall();
/*        for (int i=0;i<levelList.size();i++){
            Customer c=levelList.get(i);
            //��ȥ������ͬ��custLevel�Ķ���
            for(int j=i+1;j<levelList.size();j++){
                if(c.getCustLevel().equals(levelList.get(j).getCustLevel()))
                    levelList.remove(j);
            }
        }*/
        for (int i=0;i<sourceList.size();i++){
            Customer c=sourceList.get(i);
            //��ȥ������ͬ��custSource�Ķ���
            for(int j=i+1;j<sourceList.size();j++){
                if(c.getCustSource().equals(sourceList.get(j).getCustSource()))
                    sourceList.remove(j);
            }
        }
        //ServletActionContext.getRequest().setAttribute("levelList",levelList);
        ServletActionContext.getRequest().setAttribute("sourceList",sourceList);
        return "queryView";
    }

    //��ѯ����
    public String query(){
        if(customer.getCustName()!=null||customer.getCustSource()!=null||customer.getCustAddress()!=null) {
            list=customerService.queryByParameters(customer);
        }else{
            pageList();
        }
        return "query";
    }
}
