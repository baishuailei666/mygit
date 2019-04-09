package com.example.demo.image;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * @author :baisl
 * @Email :baishuailei@xlauncher.io
 * @Date :2019/3/29 0029
 * @Desc :解析/读取XML文件
 **/
public class XMLParser {

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        getAllUserName("F:\\eclipse community\\Date\\Spring-Boot-demo\\src\\main\\java\\com\\example\\demo\\image\\test.xml");
    }

    public static void getAllUserName(String fileName) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        File file = new File(fileName);
        if (file.exists()) {
            Document document = builder.parse(file);
            Element element = document.getDocumentElement();
            System.out.println("ROOT element of document!" + element.getNodeName());

            NodeList nodeList = element.getElementsByTagName("student");
            System.out.println("TOTAL student:" + nodeList.getLength());

            if (nodeList != null && nodeList.getLength() > 0) {
                for (int i=0; i<nodeList.getLength(); i++) {
                    Node node = nodeList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        System.out.println("--------------------");
                        Element element1 = (Element) node;
                        NodeList nodeList1 = element1.getElementsByTagName("name");
                        System.out.println("NAME:" + nodeList1.item(0).getChildNodes().item(0).getNodeType());

                        nodeList1 = element1.getElementsByTagName("grade");
                        System.out.println("GRADE:" + nodeList1.item(0).getChildNodes().item(0).getNodeType());

                        nodeList1 = element1.getElementsByTagName("age");
                        System.out.println("AGE:" + nodeList1.item(0).getChildNodes().item(0).getNodeType());
                    }
                }
            } else {
                System.exit(1);
            }
        }
    }
}
