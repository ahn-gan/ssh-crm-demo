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
    //ʹ��ģ��������װ��ȡ������
    public LinkMan getModel() {
        return linkMan;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void setLinkManService(LinkManService linkManService) {
        this.linkManService = linkManService;
    }

    /*��ȡ�ϴ����ļ�
    * 1����������������һ�����ϴ����ļ���һ�����ϴ����ļ���
    * 2�������淶���ļ���������Ҫ�ͱ������name����һ��
    *   �ļ���Ϊ�ļ�������+FileName
    * 3�����ɶ�Ӧ��set��get����
    * 4������һ���������ļ���mime���ͣ�������Ҫ����*/

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

    //���ҳ��
    public String toAddPage(){
        //��������ת�����ҳ��ʱ����Ҫ��ҳ������ʾ�����ͻ�����Щ�ͻ��б�
        /*1����ѯ�õ����пͻ��ļ���
        ��֮ǰ��customerService�Ѿ������˲�ѯ�õ����пͻ��ķ�����ֻ����ü��ɣ�����Ҫ����һ��customerService����
        * 2�������õļ�����ӵ������
        * 3����ҳ��ʹ��EL���ʽ�������ʾ*/
        List<Customer> customerList=customerService.findall();
        ServletActionContext.getRequest().setAttribute("customerList",customerList);

        return "toadd";
    }

    //�������
    public String insert() throws IOException {
        //�ж��Ƿ���Ҫ�ϴ��ļ�
        if(upload!=null){
            System.out.println("��Ϊ�գ���");
            //ʵ���ļ��ϴ�
            /*1���ڷ������ϱ����ϴ����ļ����ﴴ���ļ�
            * 2���������ϴ����ļ����Ƶ����������ļ���*/
            File file =new File("D:\\crm-files"+"/"+uploadFileName);
            //��һ��������Դ�ļ�
            //�ڶ���������Ŀ���ļ������������ϵ��ļ���
            FileUtils.copyFile(upload,file);
        }
        /*�ļ����ϴ��д�С���ƣ�������2M�����ϴ�ʧ�ܣ��ɵ�sttruts.xml�ļ����Գ�����ʽ�����ϴ��ļ��Ĵ�С����
        * ��Σ�����ļ������趨�Ĵ�С�����ҳ��input����*/
        //������Ӳ���
        linkManService.insert(linkMan);
        return "insert";
    }

    //��ϵ���б���
    public String list(){
        //��ѯ������ϵ��
        List<LinkMan> list=linkManService.list();
        //���õ��Ľ����װ��������У��ڽ��ҳ��ʹ��EL���ʽ��ȡ
        /*����ϵ���б��У�Ӧ����ʾ������ϵ�˵����ƣ��������ݿ��е����
        * ��ʹ��${list.customer.custName}
        * 1���ȸ��ݵõ���customer����õ�clid����ͨ�������customer�в��Ҷ�Ӧ������
        * ���⣺����ֱ��ʹ�û��׳�no session�쳣����Ϊ�ڵõ�list������Ѿ��ر���session��
        * ���ڸ���clid�����ݿ���Ҷ�Ӧ�Ŀͻ�����ʱ�Ѿ��رջỰ
        *
        * ���������ʹ�ù����������ӳٹر�session������actionִ��֮�󣨵����ҳ����ʾ���ٹر�session
        * */
        ServletActionContext.getRequest().setAttribute("list",list);
        return "list";
    }

    //��ת���޸�ҳ��
    public String showedit(){
        //��ȡҪ�����Ķ������ݣ����մ��ݵ�idֵ
        int lid=linkMan.getLid();
        //��ò�ѯ����ϵ��
        LinkMan lm=linkManService.fineOneById(lid);
        //���õ���linkMan�����װ���������
        ServletActionContext.getRequest().setAttribute("linkman",lm);
        //����linkMan��Ҫ�����ͻ���id������Ҫcustomer�ļ��϶���
        List<Customer> customerList=customerService.findall();
        ServletActionContext.getRequest().setAttribute("customerList",customerList);
        return "showedit";
    }
    //�����޸Ĳ���
    public String edit(){
        //�������ݷ�װ��֮�󴫵ݵ����²����ķ���
        linkManService.update(linkMan);
        return "edit";
    }
    //�򿪲�ѯҳ��
    public String openQuery(){
        //����õĿͻ����������
        List<Customer> list=customerService.findall();
        ServletActionContext.getRequest().setAttribute("list",list);
        return "queryView";
    }

    //ִ�в�ѯ����
    public String query(){
        List<LinkMan> list=linkManService.query(linkMan);
        ServletActionContext.getRequest().setAttribute("list",list);
        return "query";
    }
}
